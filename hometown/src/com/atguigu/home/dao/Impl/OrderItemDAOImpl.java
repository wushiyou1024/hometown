package com.atguigu.home.dao.Impl;

import com.atguigu.home.dao.OrderItemDAO;
import com.atguigu.home.pojo.h_order_item;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 15:39
 */
public class OrderItemDAOImpl extends BaseDAO<h_order_item> implements OrderItemDAO {
    @Override
    public List<h_order_item> getAllOrderItemById(Integer id) {
        return executeQuery("select * from h_order_item where gid in ( SELECT id FROM h_goods  where mid=?)", id);
    }

    @Override
    public List<h_order_item> getAllorderItemByMid(Integer status, Integer mid,Integer pageNo) {
        if (status == 1) {
            //查询已发货的包括已收货的
            return executeQuery("SELECT * FROM h_order_item where gid in (SELECT id FROM h_goods WHERE mid=?) AND (orderStatus=1 OR orderStatus=2) limit ?,10", mid,(pageNo-1)*10);
        }else
        {   //查询未发货的包括已收货的
            return executeQuery("SELECT * FROM h_order_item where gid in (SELECT id FROM h_goods WHERE mid=?) AND (orderStatus=0) limit ?,10", mid,(pageNo-1)*10);


        }
    }

    @Override
    public Long getAllorderCount(Integer staus, Integer mid) {
       String sql;
        if (staus==1)
        {
            sql = "SELECT  count(*) FROM h_order_item where gid in (SELECT id FROM h_goods WHERE mid=?) AND (orderStatus=1 or orderStatus=2)";
            return getValue(sql, mid);
        }else {
            sql = "SELECT  count(*) FROM h_order_item where gid in (SELECT id FROM h_goods WHERE mid=?) AND (orderStatus=0)";
            return getValue(sql, mid);
        }
    }

    @Override
    public void updateExpreeNoByid(String express, Integer id) {
        executeUpdate("update h_order_item set expressId=? where id=?",express,id);
    }

    @Override
    public void updateStatusById(Integer id, Integer status) {
        executeUpdate("update h_order_item set orderStatus=? where id=?",status,id);
    }

    @Override
    public void addOrderItem(h_order_item orderItem) {
        super.executeUpdate("insert into h_order_item(gid,buyCount,oid,orderStatus,address) values(?,?,?,?,?)",
                orderItem.getGid().getId(),orderItem.getBuyCount(),orderItem.getOid().getId(),orderItem.getOrderStatus(),orderItem.getAddress().getId());
    }
    @Override
    public void delOrderItem(Integer id) {
        super.executeUpdate("delete from h_cart where gid=?",id);

    }
    @Override
    public List<h_order_item> getAllOrderItemByOid(Integer orderId) {
        return executeQuery("SELECT * FROM h_order_item where oid=?",orderId);
    }

    @Override
    public Long getAllCount(Integer oid) {
        return getValue("select count(*) from h_order_item where oid=?",oid);
    }


    @Override
    public h_order_item getOrderItemByGid(Integer gid) {
        return load("select * from h_order_item where gid=? ",gid);
    }

}
