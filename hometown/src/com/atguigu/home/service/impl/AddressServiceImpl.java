package com.atguigu.home.service.impl;

import com.atguigu.home.dao.Impl.AddressDAOImpl;
import com.atguigu.home.pojo.h_address;
import com.atguigu.home.service.AddressService;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-24 19:28
 */
public class AddressServiceImpl implements AddressService {
    private AddressDAOImpl addressDAO;
    @Override
    public h_address getAddressById(Integer id) {
        return addressDAO.getAddressById(id);
    }
    @Override
    public List<h_address> getAddressByUid(Integer uid) {
        return addressDAO.getAddressByUid(uid);
    }

    @Override
    public h_address getDefaultAddress(Integer uid) {
        return addressDAO.getDefaultAddress(uid);
    }

    @Override
    public void setDefaultAddress(Integer id, Integer status) {
        addressDAO.setDefaultAddress(id,status);
    }

    @Override
    public int deleteAddress(Integer id) {
        return addressDAO.deleteAddress(id);
    }

    @Override
    public int addAddrss(h_address address) {
        return addressDAO.addAddrss(address);
    }

    @Override
    public int updateAddress(h_address address) {
        return addressDAO.updateAddress(address);
    }
}
