package com.atguigu.home.service;

import com.atguigu.home.pojo.h_comment;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 17:56
 */
public interface CommentService {
    List<h_comment> getCommentListById(Integer status,Integer id,Integer page);
    Long getCommentedCountById(Integer status,Integer id);
    void updateStatus(Integer id);

    h_comment getCommentById(Integer cid);
}
