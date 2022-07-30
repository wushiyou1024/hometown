package com.atguigu.home.service.impl;

import com.atguigu.home.dao.ReplyDAO;
import com.atguigu.home.pojo.h_reply;
import com.atguigu.home.service.ReplyService;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-24 11:04
 */
public class ReplyServiceImpl implements ReplyService {
    private ReplyDAO replyDAO;
    @Override
    public h_reply getreplyByCid(Integer id) {
        return replyDAO.getreplyByCid(id);
    }

    @Override
    public void addReply(h_reply reply) {
        replyDAO.addReply(reply);
    }


}
