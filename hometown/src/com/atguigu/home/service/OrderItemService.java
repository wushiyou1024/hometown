package com.atguigu.home.service;

import com.atguigu.home.pojo.h_order;
import com.atguigu.home.pojo.h_order_item;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 15:32
 */
public interface OrderItemService {
    List<h_order_item> getAllOrderItemById(Integer id);
    List<h_order_item> getAllorderItemByMid(Integer status,Integer mid,Integer pageNo);

    h_order_item getOrderItemByGid(Integer gid);
    /**
     * @Description 参数一 0 代表查询 未发货的总数 1 代表查询已发货的总数
     * @param staus
     * @param mid
     * @return
     */
    Long getAllorderCount(Integer staus,Integer  mid);
    Long  getAllCount(Integer oid);
    void delOrderItem(Integer id);
    void addOrderItem(h_order_item orderItem);
    void updateExpreeNoByid(String express,Integer id);
    void updateStatusById(Integer id,Integer status);
    List<h_order_item> getAllOrderItemByOid(Integer orderId);

}
