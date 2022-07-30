package com.atguigu.home.dao;

import com.atguigu.home.pojo.h_comment;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 17:56
 */
public interface CommentDAO {
    List<h_comment> getCommentListById(Integer status,Integer id,Integer page);
    Long getCommentedCountById(Integer status,Integer id);
    void updateStatus(Integer id);

    h_comment getCommentById(Integer cid);
}
