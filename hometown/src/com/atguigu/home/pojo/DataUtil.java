package com.atguigu.home.pojo;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-22 18:27
 */
public class DataUtil {
    private long goodsSum;
    private long orderSum;
    private long commentSum;
    private  long inforSum;

    public DataUtil(long goodsSum, long orderSum, long commentSum, long inforSum) {
        this.goodsSum = goodsSum;
        this.orderSum = orderSum;
        this.commentSum = commentSum;
        this.inforSum = inforSum;
    }

    public long getGoodsSum() {
        return goodsSum;
    }

    public void setGoodsSum(long goodsSum) {
        this.goodsSum = goodsSum;
    }

    public long getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(long orderSum) {
        this.orderSum = orderSum;
    }

    public long getCommentSum() {
        return commentSum;
    }

    public void setCommentSum(long commentSum) {
        this.commentSum = commentSum;
    }

    public long getInforSum() {
        return inforSum;
    }

    public void setInforSum(long inforSum) {
        this.inforSum = inforSum;
    }
}
