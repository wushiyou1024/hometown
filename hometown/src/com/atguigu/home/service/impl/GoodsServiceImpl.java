package com.atguigu.home.service.impl;

import com.atguigu.home.dao.GoodsDAO;
import com.atguigu.home.pojo.h_category;
import com.atguigu.home.pojo.h_goods;
import com.atguigu.home.pojo.h_merchant;
import com.atguigu.home.service.CategoryService;
import com.atguigu.home.service.GoodsService;
import com.atguigu.home.service.MerchantService;
import com.sun.mail.imap.protocol.ID;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 16:33
 */
public class GoodsServiceImpl implements GoodsService {
    private GoodsDAO goodsDAO;
      private CategoryService categoryService;
      private MerchantService merchantService;
    @Override
    public Long getCountBySid(String id, String keyword) {
        return goodsDAO.getCountBySid(id,keyword);
    }
    @Override
    public List<h_goods> getGoodsListBySid(String id,String keyword ,Integer pageNo) {
        List<h_goods> goodsList= goodsDAO.getGoodsListBySid(id, keyword, pageNo);
        for (int i=0;i<goodsList.size();i++)
        {
            h_category h_category = categoryService.getSort(goodsList.get(i).getCategory().getId());
            goodsList.get(i).setCategory(h_category);
        }
        return goodsList;
    }

    @Override
    public List<h_goods> getGoodsMe(String first, String ending, Integer pageNo, Integer id) {
        List<h_goods> goodsList = goodsDAO.getGoodsMe(first, ending, pageNo, id);
        for (int i=0;i<goodsList.size();i++)
        {
            h_category h_category = categoryService.getSort(goodsList.get(i).getCategory().getId());
            goodsList.get(i).setCategory(h_category);
        }
        return goodsList;
    }

    @Override
    public Long getCountsMe(String first, String ending, Integer id) {
        return goodsDAO.getCountsMe(first,ending,id);
    }

    @Override
    public List<h_goods> getGoodsList(String keyword, Integer pageNo) {
        List<h_goods> goodsList = goodsDAO.getGoodsList(keyword, pageNo);
        for (int i=0;i<goodsList.size();i++)
        {
            h_category h_category = categoryService.getSort(goodsList.get(i).getCategory().getId());
            goodsList.get(i).setCategory(h_category);
        }
        return goodsList;
    }

    @Override
    public Long getCount(String keyword) {
        return goodsDAO.getCount(keyword);
    }

    @Override
    public List<h_goods> getGoodsListById(String keyword, Integer pageNo,Integer id) {

        List<h_goods> goodsListById = goodsDAO.getGoodsListById(keyword, pageNo, id);
        for (int i=0;i<goodsListById.size();i++)
        {
            h_category h_category = categoryService.getSort(goodsListById.get(i).getCategory().getId());
            goodsListById.get(i).setCategory(h_category);

        }
        return goodsListById;
    }

    @Override
    public List<h_goods> UserGetGoodsListById(String keyword, Integer pageNo, Integer id) {
        List<h_goods> goodsList = goodsDAO.UserGetGoodsListById(keyword, pageNo, id);
        for (int i=0;i<goodsList.size();i++)
        {
            h_category h_category = categoryService.getSort(goodsList.get(i).getCategory().getId());
            goodsList.get(i).setCategory(h_category);
        }
        return goodsList;
    }

    @Override
    public Long getCountByid(String keyword,Integer id) {

        return goodsDAO.getCountByid(keyword,id);
    }

    @Override
    public void addGoods(h_goods goods) {
        goodsDAO.addGoods(goods);
    }

    @Override
    public void updateGoods(h_goods goods) {
        goodsDAO.updateGoods(goods);
    }

    @Override
    public void deleteById(Integer id) {
        goodsDAO.deleteById(id);
    }

    @Override
    public h_goods getGoods(Integer id) {
        return goodsDAO.getGoods(id);
    }

    @Override
    public Long getSavedCountById(Integer id) {
        return goodsDAO.getSavedCountById(id);
    }
    @Override
    public h_goods getGoodsByid(int id)
    {

        h_goods goodsByid = goodsDAO.getGoodsByid(id);
        h_merchant merchantByid = merchantService.getMerchantByid(goodsByid.getMid().getId());
        goodsByid.setMid(merchantByid);
        return goodsByid;
    }

    @Override
    public List<h_goods> getGoods(String first, String ending, Integer pageNo) {
        return goodsDAO.getGoods(first,ending,pageNo);
    }

    @Override
    public Long getCounts(String first, String ending) {
        return goodsDAO.getCounts(first,ending);
    }



    @Override
    public Long getMeCount(String keyword,Integer mid) {
        return goodsDAO.getMeCount(keyword, mid);
    }
    @Override
    public List<h_goods> getGoods(String sid,String first, String ending, Integer pageNo) {
        List<h_goods> goodsList= goodsDAO.getGoods(sid, first,ending, pageNo);
        for (int i=0;i<goodsList.size();i++)
        {
            h_category h_category = categoryService.getSort(goodsList.get(i).getCategory().getId());
            goodsList.get(i).setCategory(h_category);
        }
        return goodsList;
    }

    @Override
    public Long getCounts(String sid,String first, String ending) {
        return goodsDAO.getCounts(sid,first,ending);
    }

}
