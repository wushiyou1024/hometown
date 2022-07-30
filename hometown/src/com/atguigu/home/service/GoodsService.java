package com.atguigu.home.service;

import com.atguigu.home.pojo.h_goods;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 16:32
 */
public interface GoodsService {
   List<h_goods> getGoodsMe(String first, String ending, Integer pageNo, Integer id);
   List<h_goods> getGoodsListBySid(String id, String keyword,Integer pageNo);
   Long getCountBySid(String id, String keyword);
   Long getCountsMe(String first, String ending, Integer id);

   List<h_goods> getGoodsList(String keyword, Integer pageNo);
   Long getCount(String keyword);
   List<h_goods> getGoodsListById(String keyword, Integer pageNo,Integer id);//这边是后台管理员使用，keyword为类别
   List<h_goods> UserGetGoodsListById(String keyword, Integer pageNo,Integer id);//这边是用户查询商家店铺的所有商品
   Long getCountByid(String keyword,Integer id);
   void  addGoods(h_goods goods);
   void  updateGoods(h_goods goods);
   void  deleteById(Integer id);
   h_goods getGoods(Integer id);
   Long getSavedCountById(Integer id);
   List<h_goods> getGoods(String first, String ending, Integer pageNo);

   Long getCounts(String first, String ending);

   h_goods getGoodsByid(int id);
   Long getMeCount(String keyword,Integer mid);

   //ysw代码处
   List<h_goods> getGoods(String sid,String first, String ending, Integer pageNo);
   Long getCounts(String sid,String first, String ending);

}
