package com.atguigu.home.pojo;

import com.jhlabs.math.RidgedFBM;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-26 20:35
 */
public class orderFormat {
    private Integer id;
    private Integer uid;
    private  String orderNo;
    private String orderDate;
    private Double oderMoney;
    private Integer totalGoodsCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Double getOderMoney() {
        return oderMoney;
    }

    public void setOderMoney(Double oderMoney) {
        this.oderMoney = oderMoney;
    }

    public Integer getTotalGoodsCount() {
        return totalGoodsCount;
    }

    public void setTotalGoodsCount(Integer totalGoodsCount) {
        this.totalGoodsCount = totalGoodsCount;
    }
}
