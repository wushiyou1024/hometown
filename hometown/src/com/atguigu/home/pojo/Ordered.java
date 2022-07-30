package com.atguigu.home.pojo;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-24 19:19
 */
public class Ordered {
    private Integer id;
    private String img;
    private String gname;
    private Integer total;
    private Double price;
   private   Double totalMoney;
   private String express;
   private String address;
  private String status;
 private String expressNo;//快递单号

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Ordered(Integer id, String img, String gname, Integer total, Double price, Double totalMoney, String express, String address, String status) {
        this.id = id;
        this.img = img;
        this.gname = gname;
        this.total = total;
        this.price = price;
        this.totalMoney = totalMoney;
        this.express = express;
        this.address = address;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Ordered(Integer id) {
        this.id = id;
    }

    public Ordered() {
    }
}
