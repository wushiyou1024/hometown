package com.atguigu.home.dao.Impl;

import com.atguigu.home.dao.CommentDAO;
import com.atguigu.home.pojo.h_comment;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 17:56
 */
public class CommentDAOImpl extends BaseDAO<h_comment> implements CommentDAO {
    @Override
    public List<h_comment> getCommentListById(Integer status, Integer id,Integer pageNo) {
        if (status == 3) {
            return executeQuery("select * from h_comment where mid=?", id);
        } else{

            return executeQuery("select * from h_comment where status=? and mid=? limit ?,10", status, id,(pageNo-1)*10);
        }
    }

    @Override
    public Long getCommentedCountById(Integer status, Integer id) {
        String sql;
        if (status == 3) {
            sql = "select count(*)  from h_comment where  mid=?";
            return getValue(sql, id);
        } else {
            sql = "select count(*)  from h_comment where status=? and mid=?";
            return getValue(sql, status, id);
        }
    }

    @Override
    public void updateStatus(Integer id) {
        executeUpdate("update h_comment set status=1 where id=?",id);
    }

    @Override
    public h_comment getCommentById(Integer cid) {
        return load("select * from h_comment where o_I_id=?",cid);
    }
}
