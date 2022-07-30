package com.atguigu.home.pojo;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 16:31
 */
public class listUtil {
    private Long pageCount;
    private Integer page;
    private String keyword;
    private Long count;
    private List<h_goods> goodsList;
    private List<h_category> category;
    private List<Commented> commenteds;
    private List<h_user> users;
   private List<h_merchant> merchants;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<h_merchant> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<h_merchant> merchants) {
        this.merchants = merchants;
    }

    public List<h_user> getUsers() {
        return users;
    }

    public void setUsers(List<h_user> users) {
        this.users = users;
    }

    private List<Ordered> orderedList;

    public List<Commented> getCommenteds() {
        return commenteds;
    }

    public List<Ordered> getOrderedList() {
        return orderedList;
    }

    public void setOrderedList(List<Ordered> orderedList) {
        this.orderedList = orderedList;
    }

    public void setCommenteds(List<Commented> commenteds) {
        this.commenteds = commenteds;
    }

    private String first;
    private String ending;
    private h_information information;

    public h_information getInformation() {
        return information;
    }

    public void setInformation(h_information information) {
        this.information = information;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }

    public List<h_category> getCategory() {
        return category;
    }

    public void setCategory(List<h_category> category) {
        this.category = category;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<h_goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<h_goods> goodsList) {
        this.goodsList = goodsList;
    }

    public listUtil() {
    }

    public listUtil(Long pageCount, Integer page, String keyword, Long count, List<h_goods> goodsList) {
        this.pageCount = pageCount;
        this.page = page;
        this.keyword = keyword;
        this.count = count;
        this.goodsList = goodsList;
    }
}
