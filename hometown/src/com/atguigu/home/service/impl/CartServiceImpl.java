package com.atguigu.home.service.impl;

import com.atguigu.home.dao.CartDAO;
import com.atguigu.home.pojo.h_cart;
import com.atguigu.home.service.CartService;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 21:21
 */
public class CartServiceImpl implements CartService {
    private CartDAO cartDAO;
    @Override
    public h_cart getCart(Integer uid) {
        return cartDAO.getCart(uid);
    }
    @Override
    public int  addcart(h_cart c)
    {
        return cartDAO.addcart(c);
    }
    @Override
    public int delAllCart() {
        return cartDAO.delAllCart();
    }
}
