package com.atguigu.home.dao;

import com.atguigu.home.pojo.h_address;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-24 19:28
 */
public interface AddressDAO {

    h_address getAddressById(Integer id);
    List<h_address> getAddressByUid(Integer uid);
    h_address getDefaultAddress(Integer uid);
    void setDefaultAddress(Integer id,Integer status);
    int deleteAddress(Integer id);
    int addAddrss(h_address address);
    int updateAddress(h_address address);
}
