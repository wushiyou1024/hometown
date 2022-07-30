package com.atguigu.home.service.impl;

import com.atguigu.home.dao.UserDAO;
import com.atguigu.home.pojo.h_user;
import com.atguigu.home.service.UserService;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 20:11
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Override
    public void regist(h_user user) {
        userDAO.regist(user);
    }

    @Override
    public void updateUser(h_user user) {
        userDAO.updateUser(user);
    }

    @Override
    public void updateUserById(h_user user) {
        userDAO.updateUserById(user);
    }

    @Override
    public h_user getUser(Integer id) {
        return userDAO.getUser(id);
    }

    @Override
    public List<h_user> getAll(Integer pageNo) {
        return userDAO.getAll(pageNo);
    }

    @Override
    public Long getCount() {
        return userDAO.getCount();
    }

    @Override
    public void delUserById(Integer id) {
        userDAO.delUserById(id);
    }

    @Override
    public h_user getUser(String uname) {
        return userDAO.getUser(uname);
    }

    @Override
    public void setStatus(Integer id) {
        userDAO.setStatus(id);
    }

    @Override
    public h_user login(String uname, String pwd) {
        return userDAO.login(uname, pwd);
    }
}
