package com.atguigu.home.pojo;

import java.time.LocalDateTime;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-24 11:00
 */
public class h_reply {
    private Integer id;
    private h_merchant mid;
    private String mcontent;
    private LocalDateTime replyDate;
    private h_comment cid;

    public h_reply() {
    }

    public h_reply(Integer id, h_merchant mid, String mcontent, LocalDateTime replyDate, h_comment cid) {
        this.id = id;
        this.mid = mid;
        this.mcontent = mcontent;
        this.replyDate = replyDate;
        this.cid = cid;
    }

    public h_reply(Integer id) {
        this.id = id;
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

    public String getMcontent() {
        return mcontent;
    }

    public void setMcontent(String mcontent) {
        this.mcontent = mcontent;
    }

    public LocalDateTime getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(LocalDateTime replyDate) {
        this.replyDate = replyDate;
    }

    public h_comment getCid() {
        return cid;
    }

    public void setCid(h_comment cid) {
        this.cid = cid;
    }
}
