package com.atguigu.home.service;

import com.atguigu.home.pojo.h_user;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 20:10
 */
public interface UserService {
    void regist(h_user user);

    h_user getUser(String uname);

    void setStatus(Integer id);

    h_user login(String uname, String pwd);

    void updateUser(h_user user);
    void updateUserById(h_user user);

    h_user getUser(Integer id);

    List<h_user> getAll(Integer pageNo);

    Long getCount();

    void delUserById(Integer id);
}
