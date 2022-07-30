package com.atguigu.home.service.impl;

import com.atguigu.home.dao.MerchantDAO;
import com.atguigu.home.pojo.h_merchant;
import com.atguigu.home.service.MerchantService;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 21:45
 */
public class MerchantServiceImpl implements MerchantService {
    private MerchantDAO merchantDAO;
    @Override
    public h_merchant getMerchantByid(Integer id)
    {
        return merchantDAO. getMerchantByid(id);
    }

    @Override
    public void updateProfile(h_merchant merchant) {
        merchantDAO.updateProfile(merchant);
    }

    @Override
    public Long getAllCount() {
        return merchantDAO.getAllCount();
    }

    @Override
    public void deleteMerchantById(Integer id) {
        merchantDAO.deleteMerchantById(id);
    }

    @Override
    public h_merchant getMerchant(String muname) {
        return merchantDAO.getMerchant(muname);
    }

    @Override
    public void regist(h_merchant merchant) {
merchantDAO.regist(merchant);
    }

    @Override
    public void setStatus(Integer id) {
        merchantDAO.setStatus(id);
    }

    @Override
    public h_merchant login(String munmae, String mpwd) {
        return merchantDAO.login(munmae,mpwd);
    }

    @Override
    public List<h_merchant> getAllMerchant(Integer id) {
        return merchantDAO.getAllMerchant(id);
    }

}
