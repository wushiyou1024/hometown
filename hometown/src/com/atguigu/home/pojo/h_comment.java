package com.atguigu.home.pojo;

import java.time.LocalDateTime;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 17:14
 */
public class h_comment {
    private Integer id;
    private h_merchant mid;

    public h_comment(Integer id) {
        this.id = id;
    }

    private h_goods gid;
    private h_user uid;
    private String content;
    private LocalDateTime cdate;
    private Integer status;
    private h_order_item o_I_id;

    public h_order_item getO_I_id() {
        return o_I_id;
    }

    public void setO_I_id(h_order_item o_I_id) {
        this.o_I_id = o_I_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public h_merchant getMid() {
        return mid;
    }

    public void setMid(h_merchant mid) {
        this.mid = mid;
    }

    public h_goods getGid() {
        return gid;
    }

    public void setGid(h_goods gid) {
        this.gid = gid;
    }

    public h_user getUid() {
        return uid;
    }

    public void setUid(h_user uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCdate() {
        return cdate;
    }

    public void setCdate(LocalDateTime cdate) {
        this.cdate = cdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public h_comment() {
    }

    public h_comment(Integer id, h_merchant mid, h_goods gid, h_user uid, String content, LocalDateTime cdate, Integer status) {
        this.id = id;
        this.mid = mid;
        this.gid = gid;
        this.uid = uid;
        this.content = content;
        this.cdate = cdate;
        this.status = status;
    }
}
