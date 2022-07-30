package com.atguigu.home.dao;

import com.atguigu.home.pojo.h_goods;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 16:34
 */
public interface GoodsDAO {
    List<h_goods> getGoodsMe(String first, String ending, Integer pageNo, Integer id);

    Long getCountsMe(String first, String ending, Integer id);

    List<h_goods> getGoodsList(String keyword, Integer pageNo);
    Long getCount(String keyword);
    List<h_goods> getGoodsListById(String keyword, Integer pageNo,Integer id);
    Long getCountByid(String keyword,Integer id);
    void addGoods(h_goods goods);
    void deleteById(Integer id);
    h_goods getGoods(Integer id);
    void updateGoods(h_goods goods);
    Long getSavedCountById(Integer id);
    List<h_goods> getGoods(String first, String ending, Integer pageNo);

    Long getCounts(String first, String ending);


    h_goods getGoodsByid(int id);
    Long getMeCount(String keyword,Integer id);
    List<h_goods> UserGetGoodsListById(String keyword, Integer pageNo,Integer id);//这边是用户查询商家店铺的所有商品
    List<h_goods> getGoodsListBySid(String id, String keyword,Integer pageNo);
    Long getCountBySid(String id ,String keyword);
    List<h_goods> getGoods(String sid,String first, String ending, Integer pageNo);

    Long getCounts(String sid,String first, String ending);

}
