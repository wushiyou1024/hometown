package com.atguigu.home.service.impl;

import com.atguigu.home.dao.InforDAO;
import com.atguigu.home.pojo.h_information;
import com.atguigu.home.service.InforService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 17:57
 */
public class infoServiceImpl implements InforService {
    private InforDAO inforDAO;

    @Override
    public List<h_information> getInfolistById(Integer id) {
        return inforDAO.getInfolistById(id);
    }

    @Override
    public void delInfor(Integer id) {
         inforDAO.delInfor(id);
    }

    @Override
    public h_information getInforById(Integer id) {
        return inforDAO.getInforById(id);
    }

    @Override
    public void addinfo(String content, Integer mid,LocalDateTime dateTime) {
        inforDAO.addinfo(content,mid,dateTime);
    }

    @Override
    public void updateInfor(String content, LocalDateTime dateTime, Integer id) {
inforDAO.updateInfor(content,dateTime,id);
    }

    @Override
    public List<h_information> getInfoById(Integer id) {
        return inforDAO.getInfoById(id);
    }

    @Override
    public List<h_information> getAllInfo(Integer status) {
        return inforDAO.getAllInfo(status);
    }

    @Override
    public void updateStatus(Integer status,Integer id) {
        inforDAO.updateStatus(status,id);
    }


}
