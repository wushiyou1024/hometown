package com.atguigu.home.dao;

import com.atguigu.home.pojo.h_reply;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-24 11:03
 */
public interface ReplyDAO {
    h_reply getreplyByCid(Integer id);
    void  addReply(h_reply reply);

}
