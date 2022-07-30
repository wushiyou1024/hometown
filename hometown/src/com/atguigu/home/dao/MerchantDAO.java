package com.atguigu.home.dao;

import com.atguigu.home.pojo.h_merchant;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 21:46
 */
public interface MerchantDAO {
    h_merchant login(String munmae,String mpwd);
    h_merchant getMerchant(String muname);
    void regist(h_merchant merchant);
    void   setStatus(Integer id);
    h_merchant getMerchantByid(Integer id);
    List<h_merchant> getAllMerchant(Integer id);
    void updateProfile(h_merchant merchant);
    Long getAllCount();
    void deleteMerchantById(Integer id);
}
