package com.atguigu.home.service.impl;

import com.atguigu.home.dao.CartItemDAO;
import com.atguigu.home.dao.Impl.CartItemDAOImpl;
import com.atguigu.home.pojo.h_cart;
import com.atguigu.home.pojo.h_cartContent;
import com.atguigu.home.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//新类
public class CartItemServiceImpl implements CartItemService {
    private CartItemDAO cartItemDAO;
    @Override
    public void addCartItem(h_cart cartItem) {
cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(h_cart cartItem) {
cartItemDAO.updateCartItem(cartItem);
    }
    //判断购物车中是否有这本书有update无add
    @Override
    public void addOrUPdateCartItem(h_cart cartItem, h_cartContent cart) {

        if (cart!=null)
        {
            Map<Integer, h_cart> cartItemMap = cart.getCartItemMap();
            if (cartItemMap==null)
            {
                cartItemMap=new HashMap<>();
            }
            if (cartItemMap.containsKey(cartItem.getGid().getId()))
            {
                h_cart cartItemTemp  = cartItemMap.get(cartItem.getGid().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount()+cartItem.getBuyCount());
                updateCartItem(cartItemTemp);
            }
            else {
                addCartItem(cartItem);
            }
        }
        else
        {
            addCartItem(cartItem);
        }

    }

    @Override
    public h_cartContent getCart(Integer id) {

        List<h_cart> cartItemList = cartItemDAO.getCartItemList(id);
        Map<Integer,h_cart> cartItemMap=new HashMap<>();
        for (h_cart cartItem:cartItemList)
        {
            cartItemMap.put(cartItem.getGid().getId(),cartItem);
        }

        h_cartContent h_cartContent=new h_cartContent();
        h_cartContent.setCartItemMap(cartItemMap);
        return h_cartContent;
    }
    @Override
    public void delCartItem(Integer cartId) {
        cartItemDAO.delCartItem(cartId);
    }

}
