package com.atguigu.home.dao.Impl;

import com.atguigu.home.dao.CategoryDAO;
import com.atguigu.home.pojo.h_category;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-21 10:58
 */
public class CategoryDAOImpl extends BaseDAO<h_category> implements CategoryDAO {
    @Override
    public h_category getSort(Integer id) {
        return load("select * from h_category where id=?",id);
    }

    @Override
    public List<h_category> getAll() {
        return executeQuery("select * from h_category");
    }

    @Override
    public List<h_category> getAll(String keyword, Integer page) {
        return executeQuery("SELECT * FROM  h_category  where sort like ? LIMIT ?,10","%"+keyword+"%",(page-1)*10);

    }

    @Override
    public Long getCount(String keyword) {
        String sql = "select count(*)  from h_category where sort like ?";
        return getValue(sql,"%"+keyword+"%");

    }

    @Override
    public void deleteById(Integer id) {
        executeUpdate("delete from h_category where id=?",id);
    }

    @Override
    public void update(String sort, Integer id) {
        executeUpdate("update h_category set sort=? where id=?",sort,id);
    }

    @Override
    public void add(String sort) {
      executeUpdate("insert into h_category values(0,?)",sort);
    }
}
