package com.atguigu.home.service;

import com.atguigu.home.pojo.h_merchant;
import com.atguigu.home.pojo.h_user;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 21:45
 */
public interface MerchantService {
    h_merchant getMerchant(String muname);

    void regist(h_merchant merchant);

    void setStatus(Integer id);

    h_merchant login(String munmae, String mpwd);

    List<h_merchant> getAllMerchant(Integer pageNo);

    h_merchant getMerchantByid(Integer id);

    void updateProfile(h_merchant merchant);
    Long getAllCount();
    void deleteMerchantById(Integer id);
}
