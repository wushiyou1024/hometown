package com.atguigu.home.pojo;

import java.time.LocalDateTime;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-24 10:47
 */
public class Commented {

    private Integer id;//评论表id
    private String gimg;//商品图片
    private String gname;

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    private String userName;//消费者
    private  String comment;
    private LocalDateTime cdate;
    private String reply;
    private LocalDateTime rdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGimg() {
        return gimg;
    }

    public void setGimg(String gimg) {
        this.gimg = gimg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCdate() {
        return cdate;
    }

    public void setCdate(LocalDateTime cdate) {
        this.cdate = cdate;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public LocalDateTime getRdate() {
        return rdate;
    }

    public void setRdate(LocalDateTime rdate) {
        this.rdate = rdate;
    }
}
