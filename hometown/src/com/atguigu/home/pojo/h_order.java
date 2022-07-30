package com.atguigu.home.pojo;

import java.time.LocalDateTime;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 15:32
 */
public class h_order {
    private Integer id;
    private h_user uid;
    private String orderNo;
    private LocalDateTime orderDate;
    private Double orderMoney ;

    private Integer totalGoodsCount;
   private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public h_order(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getTotalGoodsCount() {
        return totalGoodsCount;
    }

    public void setTotalGoodsCount(Integer totalGoodsCount) {
        this.totalGoodsCount = totalGoodsCount;
    }

    public h_order() {
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public h_user getUid() {
        return uid;
    }

    public void setUid(h_user uid) {
        this.uid = uid;
    }

    public h_order(Integer id, h_user uid, String orderNo, LocalDateTime orderDate, Double orderMoney, Integer totalGoodsCount) {
        this.id = id;
        this.uid = uid;
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.orderMoney = orderMoney;
        this.totalGoodsCount = totalGoodsCount;
    }
}
