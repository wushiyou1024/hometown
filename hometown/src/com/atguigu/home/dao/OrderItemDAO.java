package com.atguigu.home.dao;

import com.atguigu.home.pojo.h_order;
import com.atguigu.home.pojo.h_order_item;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 15:39
 */
public interface OrderItemDAO {
    List<h_order_item> getAllOrderItemById(Integer id);
    List<h_order_item> getAllorderItemByMid(Integer status,Integer mid,Integer pageNo);
    Long getAllorderCount(Integer staus,Integer  mid);
    void addOrderItem(h_order_item orderItem);
    void delOrderItem(Integer id);
    void updateExpreeNoByid(String express,Integer id);
    void updateStatusById(Integer id,Integer status);
    List<h_order_item> getAllOrderItemByOid(Integer orderId);
    Long  getAllCount(Integer oid);

    h_order_item getOrderItemByGid(Integer gid);
}
