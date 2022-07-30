package com.atguigu.home.dao.Impl;

import com.atguigu.home.dao.InforDAO;
import com.atguigu.home.pojo.h_information;
import com.atguigu.home.service.InforService;
import com.atguigu.myssm.basedao.BaseDAO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 17:57
 */
public class InforDAOImpl extends BaseDAO<h_information> implements InforDAO {
   @Override
    public List<h_information> getInfoById(Integer id) {
        return executeQuery("select * from h_information where mid = ? and status=1 order by infoDate  desc limit 1",id);
    }
    @Override
    public List<h_information> getInfolistById(Integer id) {
        return executeQuery("select * from h_information where mid=?",id);
    }

    @Override
    public void delInfor(Integer id) {
        executeUpdate("delete from h_information where id=?",id);
    }

    @Override
    public h_information getInforById(Integer id) {
        return load("select * from h_information where id=?",id);
    }

    @Override
    public void addinfo(String content,Integer mid, LocalDateTime dateTime) {
        executeUpdate("insert into h_information values(0,?,?,2,?)",content,mid,dateTime);
    }

    @Override
    public void updateInfor(String content, LocalDateTime dateTime, Integer id) {
       executeUpdate("update h_information set status=2, content=?,infoDate=? where id=?",content,dateTime,id);
    }

    @Override
    public List<h_information> getAllInfo(Integer status) {
       if (status==1)
        return executeQuery("select * from h_information where status=1");
       else
           return executeQuery("select * from h_information where status=0 or status=2");
    }

    @Override
    public void updateStatus(Integer status,Integer id) {
        executeUpdate("update h_information set status=? where id=?",status,id);
    }


}
