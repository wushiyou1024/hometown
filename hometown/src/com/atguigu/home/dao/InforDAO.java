package com.atguigu.home.dao;

import com.atguigu.home.pojo.h_information;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 17:57
 */
public interface InforDAO {
    List<h_information> getInfolistById(Integer id);
    List<h_information> getInfoById(Integer id);
    void delInfor(Integer id);
    h_information getInforById(Integer id);
    void  addinfo(String content, Integer mid,LocalDateTime dateTime);
    void  updateInfor(String content,LocalDateTime dateTime,Integer id);
    List<h_information> getAllInfo(Integer status);
    void updateStatus(Integer status,Integer id);
}
