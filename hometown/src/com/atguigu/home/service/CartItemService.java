package com.atguigu.home.service;

import com.atguigu.home.pojo.h_cart;
import com.atguigu.home.pojo.h_cartContent;
import com.atguigu.home.pojo.h_user;

import java.util.List;

//新方法
public interface CartItemService {
    void addCartItem(h_cart cartItem);
    void updateCartItem(h_cart cartItem);
    void addOrUPdateCartItem(h_cart cartItem, h_cartContent cart);
    h_cartContent getCart(Integer id);
    void delCartItem(Integer cartId);
}
