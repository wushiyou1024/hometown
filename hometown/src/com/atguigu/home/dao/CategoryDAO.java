package com.atguigu.home.dao;

import com.atguigu.home.pojo.h_category;

import java.util.List;
import java.util.PrimitiveIterator;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-21 10:57
 */
public interface CategoryDAO {
    h_category getSort(Integer id);
    List<h_category> getAll();
    List<h_category> getAll(String keyword,Integer page);
    Long getCount(String keyword);
    void deleteById(Integer id);
    void update(String sort,Integer id);
    void add(String sort);
}
