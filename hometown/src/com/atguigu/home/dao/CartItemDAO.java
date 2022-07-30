package com.atguigu.home.dao;

import com.atguigu.home.pojo.h_cart;
import com.atguigu.home.pojo.h_user;

import java.util.List;

public interface CartItemDAO {
    void addCartItem(h_cart cartItem);
    void updateCartItem(h_cart cartItem);
    List<h_cart> getCartItemList(Integer id);
    //新方法--删除指定沟武校和项
    void delCartItem(Integer cartId);
}
