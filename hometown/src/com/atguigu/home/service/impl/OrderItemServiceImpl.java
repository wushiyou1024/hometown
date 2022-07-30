package com.atguigu.home.service.impl;

import com.atguigu.home.dao.OrderItemDAO;
import com.atguigu.home.pojo.h_order_item;
import com.atguigu.home.service.OrderItemService;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 15:38
 */
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemDAO orderItemDAO;
    @Override
    public void delOrderItem(Integer id) {
        orderItemDAO.delOrderItem(id);
    }
    @Override
    public void addOrderItem(h_order_item orderItem) {
        orderItemDAO.addOrderItem(orderItem);
    }

    @Override
    public List<h_order_item> getAllOrderItemById(Integer id) {
return orderItemDAO.getAllOrderItemById(id);
    }

    /**
     * @Descrption 参数status 0代表 查询未发货 1代表已发货
     * @param status
     * @param mid
     * @return
     */
    @Override
    public List<h_order_item> getAllorderItemByMid(Integer status,Integer mid,Integer pageNo) {
        return orderItemDAO.getAllorderItemByMid(status,mid,pageNo);
    }

    @Override
    public h_order_item getOrderItemByGid(Integer gid) {
        return orderItemDAO.getOrderItemByGid(gid);
    }

    @Override
    public Long getAllorderCount(Integer staus, Integer mid) {
        return orderItemDAO.getAllorderCount(staus,mid);
    }

    @Override
    public Long getAllCount(Integer oid) {
        return orderItemDAO.getAllCount(oid);
    }

    @Override
    public void updateExpreeNoByid(String express, Integer id) {
        orderItemDAO.updateExpreeNoByid(express,id);
    }

    @Override
    public void updateStatusById(Integer id, Integer status) {
        orderItemDAO.updateStatusById(id,status);
    }
    @Override
    public List<h_order_item> getAllOrderItemByOid(Integer orderId) {
        return orderItemDAO.getAllOrderItemByOid(orderId);
    }


}
