package com.atguigu.home.pojo;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-21 10:53
 */
public class h_category {
    private Integer id;
    private String sort;

    public h_category() {
    }

    public h_category(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public h_category(Integer id, String sort) {
        this.id = id;
        this.sort = sort;
    }
}
