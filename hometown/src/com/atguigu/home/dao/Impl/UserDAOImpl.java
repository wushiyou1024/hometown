package com.atguigu.home.dao.Impl;

import com.atguigu.home.dao.UserDAO;
import com.atguigu.home.pojo.h_user;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 20:14
 */
public class UserDAOImpl extends BaseDAO<h_user> implements UserDAO {
    @Override
    public void updateUser(h_user user) {
        executeUpdate("update h_user set pwd=?,phone=?,wechat=?,realName=?,email=? where uname=?",
                user.getPwd(), user.getPhone(), user.getWechat(), user.getRealName(), user.getEmail(), user.getUname());
    }

    @Override
    public void regist(h_user user) {
        executeUpdate("insert into h_user(id,uname,pwd,role,status,email) values(0,?,?,0,0,?)", user.getUname(), user.getPwd(), user.getEmail());

    }

    @Override
    public h_user getUser(String uname) {
        return load("select * from h_user where uname=?", uname);
    }

    @Override
    public h_user getUser(Integer id) {
        return load("select * from h_user where id=?", id);
    }

    @Override
    public void updateUserById(h_user user) {
        executeUpdate("update h_user set pwd=?,phone=?,wechat=?,realName=?,email=?,uname=? where id=?",
                user.getPwd(), user.getPhone(), user.getWechat(), user.getRealName(), user.getEmail(), user.getUname(),user.getId());

    }

    @Override
    public void setStatus(Integer id) {
        executeUpdate("update h_user set status=1 where id=?", id);
    }

    @Override
    public h_user login(String uname, String pwd) {
        return load("select * from h_user where uname=? and pwd=?", uname, pwd);
    }

    @Override
    public List<h_user> getAll(Integer pageNo) {
        return executeQuery("select * from h_user where role=0 limit ?,10", (pageNo - 1) * 10);
    }

    @Override
    public Long getCount() {
        return getValue("select count(*) from h_user where role=0");
    }

    @Override
    public void delUserById(Integer id) {
        executeUpdate("SET foreign_key_checks = 0");
        executeUpdate("delete from h_user where id=?", id);
        executeUpdate("SET foreign_key_checks = 1");
    }
}
