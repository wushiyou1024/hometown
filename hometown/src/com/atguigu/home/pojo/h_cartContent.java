package com.atguigu.home.pojo;

import java.util.*;

public class h_cartContent {
    private Integer totalCount;//购物车项的数量
    private Double totalPrice;
    private Map<Integer, h_cart> cartItemMap = new LinkedHashMap<>();//key为Goods集合中的id

    private Integer totalGoodsCount;//新参数

    public h_cartContent(Integer totalGoodsCount) {
        this.totalGoodsCount = totalGoodsCount;
    }

    public h_cartContent() {
    }

    //新方法
    public Integer getTotalGoodsCount() {
        totalGoodsCount=0;
        for(h_cart cart:cartItemMap.values())
        {
            totalGoodsCount+=cart.getBuyCount();
        }

        return totalGoodsCount;
    }

    public Integer getTotalCount() {
        totalCount=0;
        if (cartItemMap!=null && cartItemMap.size()>0)
        {
            totalCount=cartItemMap.size();
        }
        return totalCount;
    }


    public Double getTotalPrice() {
        totalPrice=0.0;
        if (cartItemMap!=null && cartItemMap.size()>0)
        {
            Set<Map.Entry<Integer, h_cart>> entries = cartItemMap.entrySet();
            for (Map.Entry<Integer,h_cart> cartItemEntry:entries)
            {
                h_cart cartItem = cartItemEntry.getValue();
                totalPrice += cartItem.getPrice() * cartItem.getBuyCount();

            }
        }

        return totalPrice;
    }





    public Map<Integer, h_cart> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, h_cart> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }
}