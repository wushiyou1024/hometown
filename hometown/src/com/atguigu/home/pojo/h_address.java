package com.atguigu.home.pojo;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-24 13:38
 */
public class h_address {
    private Integer id;
    private h_user uid;
    private String address;
    private String uname;
    private String telephone;
   private Integer defaultAddress;


    public Integer getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Integer defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public h_address(Integer id, h_user uid, String address, String uname, String telephone) {
        this.id = id;
        this.uid = uid;
        this.address = address;
        this.uname = uname;
        this.telephone = telephone;
    }

    public h_address() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public h_user getUid() {
        return uid;
    }

    public void setUid(h_user uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public h_address(Integer id) {
        this.id = id;
    }
}
