package com.atguigu.home.dao.Impl;

import com.atguigu.home.dao.CartItemDAO;
import com.atguigu.home.pojo.h_cart;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

public class CartItemDAOImpl extends BaseDAO<h_cart> implements CartItemDAO {
    @Override
    public void delCartItem(Integer cartId) {
        super.executeUpdate("delete from h_cart where id=?",cartId);
    }

    @Override
    public void addCartItem(h_cart cartItem) {
        executeUpdate("insert into h_cart(gid,buyCount,uid,price) values(?,?,?,?)",cartItem.getGid().getId(),cartItem.getBuyCount(),cartItem.getUid(),cartItem.getPrice());

    }

    @Override
    public void updateCartItem(h_cart cartItem) {

        executeUpdate("update h_cart set buyCount=? where id=?",cartItem.getBuyCount(),cartItem.getId());

    }

    @Override
    public List<h_cart> getCartItemList(Integer id) {
     return   executeQuery("select * from h_cart where uid=?",id);
    }
}
