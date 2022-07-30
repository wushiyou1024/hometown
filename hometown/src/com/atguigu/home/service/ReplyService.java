package com.atguigu.home.service;

import com.atguigu.home.pojo.h_reply;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-24 11:02
 */
public interface ReplyService {
    h_reply getreplyByCid(Integer id);
    void  addReply(h_reply reply);

}
