package com.atguigu.home.dao;

import com.atguigu.home.pojo.h_user;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 20:14
 */
public interface UserDAO {
    void updateUser(h_user user);

    void regist(h_user user);

    h_user getUser(String uname);

    h_user getUser(Integer id);
    void updateUserById(h_user user);

    void setStatus(Integer id);

    h_user login(String uname, String pwd);

    List<h_user> getAll(Integer pageNo);

    Long getCount();

    void delUserById(Integer id);
}
