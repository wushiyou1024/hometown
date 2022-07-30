package com.atguigu.home.pojo;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 15:47
 */
public class h_order_item {
    private Integer id;
    private h_goods gid;
    private Integer buyCount;
    private h_order oid;
    private String expressId;
  private h_address address;

    public h_order_item(Integer id) {
        this.id = id;
    }

    public h_address getAddress() {
        return address;
    }

    public void setAddress(h_address address) {
        this.address = address;
    }

    public h_order_item(Integer id, h_goods gid, Integer buyCount, h_order oid, String expressId, h_address address, Integer orderStatus) {
        this.id = id;
        this.gid = gid;
        this.buyCount = buyCount;
        this.oid = oid;
        this.expressId = expressId;
        this.address = address;
        this.orderStatus = orderStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public h_goods getGid() {
        return gid;
    }

    public void setGid(h_goods gid) {
        this.gid = gid;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public h_order getOid() {
        return oid;
    }

    public void setOid(h_order oid) {
        this.oid = oid;
    }

    public String getExpressId() {
        return expressId;
    }

    public void setExpressId(String expressId) {
        this.expressId = expressId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public h_order_item() {
    }

    public h_order_item(Integer id, h_goods gid, Integer buyCount, h_order oid, String expressId, Integer orderStatus) {
        this.id = id;
        this.gid = gid;
        this.buyCount = buyCount;
        this.oid = oid;
        this.expressId = expressId;
        this.orderStatus = orderStatus;
    }

    private Integer orderStatus;
}
