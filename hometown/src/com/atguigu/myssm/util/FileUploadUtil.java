package com.atguigu.myssm.util;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-05-01 22:53
 */

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: Yang
 * @Date 2021/11/24 12:10
 * @Description: 上传文件工具类  需要在web下创建userFile文件夹，该文件夹不能为空（在里面随便创一个文本文件）
 */
public class FileUploadUtil {
    public static Map<String, String> imgFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Description: 上传图片文件方法
         * @Param: [request, response]
         * @Return: java.lang.String
         */
        // 准备上传
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        // 请求信息中的内容是否是multipart类型
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        // 获取到服务器项目的根目录下的userFile目录
        String uploadFilePath = request.getServletContext().getRealPath("/static/uploads");
        //判断请求信息中的内容 是否是“multipart/form-data”类型
        if (isMultipart) {
            //new一个 map用来保存图片名和普通表单的值
            HashMap<String, String> map = new HashMap<>();
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("utf-8");
            //设置上传文件大小：单位 byte
            upload.setSizeMax(1024 * 1024 * 4);
            try {
                // 解析form表单中所有文件
                List<FileItem> items = upload.parseRequest(request);
                for (FileItem item : items) {
                    // 普通表单字段true, 文件表单字段false
                    if (!item.isFormField()) {
                        //文件名
                        String fileName = item.getName();
                        if (fileName != null && !"".equals(fileName)) {
                            String s = fileName.substring(fileName.lastIndexOf('.') + 1);
                            if (!"jpg".equals(s) && !"gif".equals(s) && !"bmp".equals(s) && !"png".equals(s) && !"txt".equals(s)) {
                                // 跳转路径
                                out.print("<script>alert('文件格式不正确！只能上传图片');location.href='/stuhome/index/homeAdd.html'</script>");
                            } else {
                                // 处理文件同名的问题，在文件名前添加UUID
                                String uuid = UUID.randomUUID().toString();
                                File saveFile = new File(uploadFilePath + "/" + uuid + "_" + fileName);
                                //上传
                                item.write(saveFile);
                                // 文件名
                                String homeImg = uuid + "_" + fileName;
                                map.put("imgName", homeImg);
                            }
                        }
                    } else {
                        String fieldName = item.getFieldName();

                        String string = item.getString("utf-8");
                        map.put(fieldName, string);
                    }

                }
                return map;
            } catch (Exception e) {
                e.printStackTrace();
                out.print("<script>alert('文件大小超出范围，只能上传最多4M的文件！');location.href='/stuhome/index/homeAdd.html'</script>");
            }
        }
        return null;
    }
}

