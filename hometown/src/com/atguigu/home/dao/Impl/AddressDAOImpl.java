package com.atguigu.home.dao.Impl;

import com.atguigu.home.dao.AddressDAO;
import com.atguigu.home.pojo.h_address;
import com.atguigu.myssm.basedao.BaseDAO;
import javassist.compiler.ast.Keyword;

import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-24 19:29
 */
public class AddressDAOImpl extends BaseDAO<h_address> implements AddressDAO {
    @Override
    public h_address getAddressById(Integer id) {
        return load("select * from h_address where id=?",id);
    }

    @Override
    public List<h_address> getAddressByUid(Integer uid) {
        return executeQuery("select * from h_address where uid=?", uid);
    }

    @Override
    public h_address getDefaultAddress(Integer uid) {
        return load("select * from h_address where uid=? and defaultAddress=1", uid);
    }

    @Override
    public void setDefaultAddress(Integer id, Integer status) {
        super.executeUpdate("update h_address set defaultAddress=? where id=?", status, id);
    }

    @Override
    public int deleteAddress(Integer id) {
        return executeUpdate("delete from h_address where id=?", id);
    }

    @Override
    public int addAddrss(h_address address) {
        return executeUpdate("insert into h_address(uid,address,uname,telephone,defaultAddress) values(?,?,?,?,0)", address.getUid().getId(), address.getAddress(), address.getUname(), address.getTelephone());
    }

    @Override
    public int updateAddress(h_address address) {
        return executeUpdate("update h_daaress set address=?,uname=?,telephone=? where id=?",address.getAddress(),address.getUname(),address.getTelephone());
    }
}
