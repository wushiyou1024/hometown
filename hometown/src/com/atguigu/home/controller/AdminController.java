package com.atguigu.home.controller;

import com.atguigu.home.pojo.Ouser;
import com.atguigu.home.pojo.h_information;
import com.atguigu.home.pojo.infoFormat;
import com.atguigu.home.service.InforService;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-26 15:07
 */
public class AdminController {
   private InforService inforService;
    public  String getAllInfor(HttpServletRequest request)
      {
          String ope = request.getParameter("ope");
          List<h_information> allInfo;
          if (ope.equals("will"))
          {
              //查询未审核的
              allInfo= inforService.getAllInfo(0);//传入1查询 1  传入0 查0和2
          }
else
          {
              allInfo= inforService.getAllInfo(1);//传入1查询 1  传入0 查0和2
          }
        List<infoFormat> infoFormats = new ArrayList<>();
        for (int i = 0; i < allInfo.size(); i++) {
            Integer id = allInfo.get(i).getId();
            String content = allInfo.get(i).getContent();
            Integer status = allInfo.get(i).getStatus();
            LocalDateTime infoDate = allInfo.get(i).getInfoDate();
            infoFormat infoFormat = new infoFormat();
            infoFormat.setId(id);
            infoFormat.setContent(content);
             if (status == 0)
                infoFormat.setStatus("失败");
            else if (status==2)
                infoFormat.setStatus("待审核");
            else
                infoFormat.setStatus("已同意");
            //  重点：自定义的格式。如：ofPattern(“yyyy-MM-dd hh:mm:ss”)
            DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            //格式化
            String str4 = formatter3.format(infoDate.now());
            infoFormat.setInfoDate(str4);
            infoFormats.add(i, infoFormat);
        }
        Gson gson = new Gson();
        String json = gson.toJson(infoFormats);
        return "json:" + json;
    }
    public String passInforById(HttpServletRequest request)
    {
        String ope = request.getParameter("ope");
        String id = request.getParameter("id");
        if (ope.equals("pass"))
        {
            inforService.updateStatus(1,Integer.parseInt(id));
        }else
            inforService.updateStatus(0,Integer.parseInt(id));
        return "admin/infor";
    }
    public  String passArr(HttpServletRequest request) throws IOException {
        String arr = request.getParameter("arr");
        String[] split = arr.split(",");

        for (int i=0;i<split.length;i++)
        {
            inforService.updateStatus(1,Integer.parseInt(split[i]));
        }
        return "admin/infor";
    }
}
