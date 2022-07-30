package com.atguigu.home.dao.Impl;

import com.atguigu.home.dao.MerchantDAO;
import com.atguigu.home.pojo.h_merchant;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 21:46
 */
public class MerchantDAOImpl extends BaseDAO<h_merchant>  implements MerchantDAO {
    @Override
    public h_merchant getMerchantByid(Integer id)
    {
        return load("select * from h_merchant where id=?",id);
    }

    @Override
    public List<h_merchant> getAllMerchant(Integer pageNo) {
        return executeQuery("select * from h_merchant limit ?,10", (pageNo - 1) * 10);

    }

    @Override
    public void updateProfile(h_merchant merchant) {
        executeUpdate("update h_merchant set mimg=?,mname=?,mintro=?,muname=?,mpwd=?,email=?,status=? where id=?",merchant.getMimg(),merchant.getMname(),merchant.getMintro(),merchant.getMuname(),merchant.getMpwd(),merchant.getEmail(),merchant.getStatus(),merchant.getId());

    }

    @Override
    public Long getAllCount() {
        return getValue("select count(*) from h_merchant");
    }

    @Override
    public void deleteMerchantById(Integer id) {
        executeUpdate("delete from h_merchant where id=?",id);
    }


    @Override
    public h_merchant login(String munmae, String mpwd) {
        return load("select * from h_merchant where muname=? and mpwd=?",munmae,mpwd);
    }

    @Override
    public h_merchant getMerchant(String muname) {
        return load("select * from h_merchant where muname=?",muname);
    }

    @Override
    public void regist(h_merchant merchant) {
        executeUpdate("insert into h_merchant (id,muname,mpwd,mstatus,email,status) values(0,?,?,0,?,0)",merchant.getMuname(),merchant.getMpwd(),merchant.getEmail());

    }

    @Override
    public void setStatus(Integer id) {
        executeUpdate("update h_merchant set mstatus=1 where id=?",id);

    }
}
