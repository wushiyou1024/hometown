package com.atguigu.home.service;


import com.atguigu.home.pojo.h_cart;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 21:21
 */
public interface CartService {
   h_cart getCart(Integer uid);
   int addcart(h_cart c);
   int delAllCart();//新方法
}
