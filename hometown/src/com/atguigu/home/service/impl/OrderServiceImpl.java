package com.atguigu.home.service.impl;

import com.atguigu.home.dao.CartItemDAO;
import com.atguigu.home.dao.OrderDAO;
import com.atguigu.home.dao.OrderItemDAO;
import com.atguigu.home.pojo.h_cart;
import com.atguigu.home.pojo.h_order;
import com.atguigu.home.pojo.h_order_item;
import com.atguigu.home.pojo.h_user;
import com.atguigu.home.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator;

public class OrderServiceImpl implements OrderService {

    //1、订单表添加一条记录
    //2、订单详情添加记录
    //3、车项表中删除对应记录
   private OrderDAO orderDAO;
   private OrderItemDAO orderItemDAO;
   private CartItemDAO cartItemDAO;
    @Override
    public void addOrderBean(h_order orderBean) {
        //第一步
      orderDAO.addOrderBean(orderBean);//orderBean中id有值
//        //第二步
//        List<h_order_item> orderItemList = orderBean.getOrderItemList();
//
//        for (h_order_item orderItem:orderItemList)
//        {
//            orderItemDAO.addOrderItem(orderItem);
//        }
//
//        //第三步
//        h_user currUser = orderBean.getUid();
//        Map<Integer, h_cart> cartItemMap = currUser.getCartContent().getCartItemMap();
//for (h_cart cartItem:cartItemMap.values())
//{
//    cartItemDAO.delCartItem(cartItem);
//}


    }

    @Override
    public h_order selectOrder(Integer id) {
        return orderDAO.selectOrder(id);
    }

    @Override
    public List<h_order> getOrderList(h_user user) {
        return orderDAO.getOrderList(user);
    }

    @Override
    public void updateStatusById(Integer id, Integer status) {
        orderItemDAO.updateStatusById(id,status);
    }

    @Override
    public List<h_order> getAllOrder() {
        return orderDAO.getAllOrder();
    }

    @Override
    public void insertComment(Integer mid, Integer gid, Integer uid, String content, LocalDateTime nowDateTime, Integer orderId) {
        orderDAO.insertComment(mid,gid,uid,content,nowDateTime,orderId);
    }

    @Override
    public void updateOsByGidAndOid(Integer goodsid, Integer orderId) {
orderDAO.updateOsByGidAndOid(goodsid,orderId);
    }

    @Override
    public Integer getIdByGidAndOid(Integer goodsid, Integer orderId) {
        return orderDAO.getIdByGidAndOid(goodsid,orderId);
    }
}
