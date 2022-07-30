package com.atguigu.home.dao.Impl;

import com.atguigu.home.dao.OrderDAO;
import com.atguigu.home.pojo.h_order;
import com.atguigu.home.pojo.h_user;
import com.atguigu.myssm.basedao.BaseDAO;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDAOImpl extends BaseDAO<h_order> implements OrderDAO {

    @Override
    public void addOrderBean(h_order orderBean) {
        int orderBeanId = super.executeUpdate(("insert into h_order(uid,orderNo,orderDate,orderMoney,totalGoodsCount) values(?,?,?,?,?)"), orderBean.getUid().getId(), orderBean.getOrderNo(), orderBean.getOrderDate(), orderBean.getOrderMoney(), orderBean.getTotalGoodsCount());
    orderBean.setId(orderBeanId);
    }

    @Override
    public List<h_order> getOrderList(h_user user) {
        return executeQuery("select * from h_order where uid=?",user.getId());
    }
    @Override
    public h_order selectOrder(Integer id) {
        return load("select * from h_order where id=?",id);
    }

    @Override
    public List<h_order> getAllOrder() {
        return executeQuery("select * from h_order");
    }


    @Override
    public void insertComment(Integer mid, Integer gid, Integer uid, String content, LocalDateTime nowDateTime,Integer orderId) {
        executeUpdate("insert into h_comment values(0,?,?,?,?,?,0,?)",mid,gid,uid,content,nowDateTime,orderId);
    }

    @Override
    public void updateOsByGidAndOid(Integer goodsid, Integer orderId) {
        executeUpdate("update h_order_item set orderStatus=3 where gid=? and id=?",goodsid,orderId);
    }

    @Override
    public Integer getIdByGidAndOid(Integer goodsid, Integer orderId) {
        String sql = "select id from h_order_item where gid=? and oid=?";
        return getValue(sql, goodsid,orderId);
    }
}
