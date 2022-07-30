package com.atguigu.home.pojo;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 21:18
 */
public class h_cart {
    private Integer id;
    private h_goods gid;//商品id
    private Integer buyCount=0;
    private Integer  uid;//用户id
   private Double price;
private Double totalPrice;


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }




    public Double getTotalPrice() {
        totalPrice=price*buyCount;
        return totalPrice;
    }

    public h_cart( h_goods gid, Integer buyCount, Integer uid, Double price, Double totalPrice) {

        this.gid = gid;
        this.buyCount = buyCount;
        this.uid = uid;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public void setGid(h_goods gid) {
        this.gid = gid;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getBuyCount() {
        return buyCount;
    }

    public h_goods getGid() {
        return gid;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public h_cart() {
    }

    public h_cart(Integer id, Integer buyCount) {
        this.id = id;
        this.buyCount = buyCount;
    }

    @Override
    public String toString() {
        return "h_cart{" +
                "id=" + id +
                ", gid=" + gid +
                ", buyCount=" + buyCount +
                ", uid=" + uid +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
