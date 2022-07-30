package com.atguigu.home.pojo;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 21:39
 */
public class h_merchant {
    private  Integer id;
    private  String mimg;
    private String mname;//店铺名称
    private  String mintro;
    private Integer mstatus;//邮箱激活状态
    private String muname;
    private String mpwd;
    private String email;
    private  Integer status;

    public h_merchant(Integer id) {
        this.id = id;
    }

    public h_merchant(Integer id, Integer mstatus, String muname, String mpwd, String email, Integer status) {
        this.id = id;
        this.mstatus = mstatus;
        this.muname = muname;
        this.mpwd = mpwd;
        this.email = email;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMimg() {
        return mimg;
    }

    public void setMimg(String mimg) {
        this.mimg = mimg;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMintro() {
        return mintro;
    }

    public void setMintro(String mintro) {
        this.mintro = mintro;
    }



    public String getMuname() {
        return muname;
    }

    public void setMuname(String muname) {
        this.muname = muname;
    }

    public String getMpwd() {
        return mpwd;
    }

    public void setMpwd(String mpwd) {
        this.mpwd = mpwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public h_merchant() {
    }

    public Integer getMstatus() {
        return mstatus;
    }

    public void setMstatus(Integer mstatus) {
        this.mstatus = mstatus;
    }

    public h_merchant(Integer id, String mimg, String mname, String mintro, Integer mstatus, String muname, String mpwd, String email, Integer status) {
        this.id = id;
        this.mimg = mimg;
        this.mname = mname;
        this.mintro = mintro;
        this.mstatus = mstatus;
        this.muname = muname;
        this.mpwd = mpwd;
        this.email = email;
        this.status = status;
    }
}
