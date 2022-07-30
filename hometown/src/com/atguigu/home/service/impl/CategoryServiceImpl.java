package com.atguigu.home.service.impl;

import com.atguigu.home.dao.CategoryDAO;
import com.atguigu.home.pojo.h_category;
import com.atguigu.home.service.CategoryService;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-21 10:57
 */
public class CategoryServiceImpl implements CategoryService {
 private  CategoryDAO categoryDAO;
    @Override
    public h_category getSort(Integer id) {
        return categoryDAO.getSort(id);

    }

    @Override
    public List<h_category> getAll() {
         return categoryDAO.getAll();
    }

    @Override
    public List<h_category> getAll(String keyword, Integer page) {
        return categoryDAO.getAll(keyword,page);
    }

    @Override
    public Long getCount(String keyword) {
        return categoryDAO.getCount(keyword);
    }

    @Override
    public void deleteById(Integer id) {
        categoryDAO.deleteById(id);
    }

    @Override
    public void update(String sort, Integer id) {
        categoryDAO.update(sort,id);
    }

    @Override
    public void add(String sort) {
categoryDAO.add(sort);
    }

}
