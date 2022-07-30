package com.atguigu.home.pojo;

import java.time.LocalDateTime;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 17:15
 */
public class h_information
{
    private Integer id;
    private String content;
    private h_merchant mid;
    private Integer status;
    private LocalDateTime infoDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public h_merchant getMid() {
        return mid;
    }

    public void setMid(h_merchant mid) {
        this.mid = mid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(LocalDateTime infoDate) {
        this.infoDate = infoDate;
    }

    public h_information() {
    }

    public h_information(String content, LocalDateTime infoDate) {
        this.content = content;
        this.infoDate = infoDate;
    }

    public h_information(Integer id, String content, h_merchant mid, Integer status, LocalDateTime infoDate) {
        this.id = id;
        this.content = content;
        this.mid = mid;
        this.status = status;
        this.infoDate = infoDate;
    }
}
