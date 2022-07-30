package com.atguigu.home.pojo;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 16:28
 */
public class h_goods {
    private Integer id;
    private Double price;

    public h_goods(Integer id) {
        this.id = id;
    }

    private String img;
    private String title;
    private  String detail_content;
    private h_category categoryid;
    private Integer sum;
   private h_merchant mid;

    public h_category getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(h_category categoryid) {
        this.categoryid = categoryid;
    }

    public h_merchant getMerchant() {
        return mid;
    }

    public void setMerchant(h_merchant merchant) {
        this.mid = merchant;
    }

    public h_goods(Integer id, Double price, String img, String title, String detail_content, h_category categoryid, Integer sum) {
        this.id = id;
        this.price = price;
        this.img = img;
        this.title = title;
        this.detail_content = detail_content;
        this.categoryid = categoryid;
        this.sum = sum;
    }

    public h_goods(Integer id, Double price, String img, String title, String detail_content, h_category categoryid, Integer sum, h_merchant merchant) {
        this.id = id;
        this.price = price;
        this.img = img;
        this.title = title;
        this.detail_content = detail_content;
        this.categoryid = categoryid;
        this.sum = sum;
        this.mid = merchant;
    }

    public h_goods() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail_content() {
        return detail_content;
    }

    public void setDetail_content(String detail_content) {
        this.detail_content = detail_content;
    }


    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public h_merchant getMid() {
        return mid;
    }

    public void setMid(h_merchant mid) {
        this.mid = mid;
    }

    public h_category getCategory() {
        return categoryid;
    }

    public void setCategory(h_category category) {
        this.categoryid = category;
    }


}
