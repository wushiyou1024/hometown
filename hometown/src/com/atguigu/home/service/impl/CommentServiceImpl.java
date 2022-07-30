package com.atguigu.home.service.impl;

import com.atguigu.home.dao.CommentDAO;
import com.atguigu.home.pojo.h_comment;
import com.atguigu.home.service.CommentService;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 17:56
 */
public class CommentServiceImpl implements CommentService {
    private CommentDAO commentDAO;

    @Override
    public List<h_comment> getCommentListById(Integer status,Integer id,Integer page) {
        return commentDAO.getCommentListById(status,id,page);
    }

    @Override
    public Long getCommentedCountById(Integer status,Integer id) {
        return commentDAO.getCommentedCountById(status,id);
    }

    @Override
    public void updateStatus(Integer id) {
        commentDAO.updateStatus(id);
    }

    @Override
    public h_comment getCommentById(Integer cid) {
        return commentDAO.getCommentById(cid);
    }
}
