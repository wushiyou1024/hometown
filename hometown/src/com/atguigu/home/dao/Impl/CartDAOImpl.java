package com.atguigu.home.dao.Impl;

import com.atguigu.home.dao.CartDAO;
import com.atguigu.home.pojo.h_cart;
import com.atguigu.myssm.basedao.BaseDAO;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 21:23
 */
public class CartDAOImpl extends BaseDAO<h_cart> implements CartDAO {
    @Override
    public h_cart getCart(Integer uid) {
        return load("select * from h_cart where uid=?",uid);
    }
    @Override
    public int addcart(h_cart cart)
    {
        return executeUpdate("insert into h_cart(gid,buyCount,uid) values(?,?,?)",cart.getGid(),cart.getBuyCount(),cart.getUid());
    }

    @Override
    public int delAllCart() {
        return executeUpdate("delete from h_cart");
    }
}
