package com.atguigu.home.pojo;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 20:12
 */
public class h_user {
    private Integer id;
    private String uname;
    private  String pwd;
    private Integer role;
    private Integer status;
 private String email;
 private String phone;
 private  String wechat;
 private String realName;
private h_cart cart;
    private h_cartContent cartContent;//新设置

    public h_cartContent getCartContent() {
        return cartContent;
    }

    public void setCartContent(h_cartContent cartContent) {
        this.cartContent = cartContent;
    }

    public h_user(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public h_cart getCart() {
        return cart;
    }

    public void setCart(h_cart cart) {
        this.cart = cart;
    }

    public h_user(Integer id, String uname, String pwd, Integer role, Integer status, String email, String phone, String wechat, String realName) {
        this.id = id;
        this.uname = uname;
        this.pwd = pwd;
        this.role = role;
        this.status = status;
        this.email = email;
        this.phone = phone;
        this.wechat = wechat;
        this.realName = realName;
    }

    public h_user(int i, String uname, String pwd, int i1, int i2, String email) {
         this.id=i;
         this.uname=uname;
         this.pwd=pwd;
         this.role=i1;
         this.status=i2;
         this.email=email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public h_user() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
