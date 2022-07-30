package com.atguigu.home.pojo;

import java.time.LocalDateTime;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-23 18:26
 */
public class infoFormat {
    private Integer id;
    private String content;
    private String status;
    private String infoDate;

    public infoFormat() {
    }

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


    public String getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(String infoDate) {
        this.infoDate = infoDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
