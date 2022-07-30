package com.atguigu.home.service;

import com.atguigu.home.pojo.h_order;
import com.atguigu.home.pojo.h_user;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    void addOrderBean(h_order orderBean);

    h_order selectOrder(Integer id);

    List<h_order> getOrderList(h_user user);

    void updateStatusById(Integer id, Integer status);

    List<h_order> getAllOrder();


    void insertComment(Integer mid, Integer gid, Integer uid, String content, LocalDateTime nowDateTime, Integer orderId);


    void updateOsByGidAndOid(Integer goodsid, Integer orderId);


    Integer getIdByGidAndOid(Integer goodsid, Integer orderId);
}
