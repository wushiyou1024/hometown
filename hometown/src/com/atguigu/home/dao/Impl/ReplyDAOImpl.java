package com.atguigu.home.dao.Impl;

import com.atguigu.home.dao.ReplyDAO;
import com.atguigu.home.pojo.h_reply;
import com.atguigu.myssm.basedao.BaseDAO;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-24 11:06
 */
public class ReplyDAOImpl extends BaseDAO<h_reply> implements ReplyDAO {
    @Override
    public h_reply getreplyByCid(Integer id) {
        return load("select * from h_reply where cid=?",id);
    }

    @Override
    public void addReply(h_reply reply) {
        executeUpdate("insert into h_reply values(0,?,?,?,?)",reply.getMid().getId(),reply.getMcontent(),reply.getReplyDate(),reply.getCid().getId());
    }


}
