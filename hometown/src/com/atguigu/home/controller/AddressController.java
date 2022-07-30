package com.atguigu.home.controller;

import com.atguigu.home.pojo.h_address;
import com.atguigu.home.pojo.h_user;
import com.atguigu.home.service.AddressService;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class AddressController {
    private AddressService addressService;

    public String getAddress(HttpSession session, Integer addressId) {
        h_address defaultAddress = addressService.getAddressById(addressId);
        if (defaultAddress==null)
        {

            defaultAddress.setAddress("默认地址列表为空！请新增地址");
            defaultAddress.setTelephone("默认电话列表为空！请新增电话");
            defaultAddress.setUname("默认姓名列表为空！请新增姓名");
            session.setAttribute("defaultAddress",defaultAddress);
            System.out.println(defaultAddress.getUname());
            return "user/settlement";

        }
        session.setAttribute("defaultAddress", defaultAddress);

        return "user/settlement";
    }
//`
public String savaAddress(HttpSession session, HttpServletRequest request) {
    String id = request.getParameter("id");
    h_address addressById = addressService.getAddressById(Integer.parseInt(id));
    Gson gson = new Gson();
    String s = gson.toJson(addressById);
    return "json:" + s;
}
public String getAddressList(HttpSession session) {
    String filed=null;
    session.setAttribute("filed",filed);
    h_user currUser = (h_user) session.getAttribute("currUser");
    List<h_address> addressByUid = addressService.getAddressByUid(currUser.getId());
    session.setAttribute("addressByUid", addressByUid);
    return "user/address";
}
    public String setAddressDefault(HttpSession session, Integer addressId) {
        h_user currUser = (h_user) session.getAttribute("currUser");
        h_address defaultAddress = addressService.getDefaultAddress(currUser.getId());
        addressService.setDefaultAddress(addressId, 1);
        addressService.setDefaultAddress(defaultAddress.getId(),0);
        return "user/settlement";
    }
//
public String deleteAddress(HttpSession session, Integer id) {

    addressService.deleteAddress(id);

    return "redirect:order.do?operate=checkout";
}
    //6.27
    public String updateAddress(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String uname = request.getParameter("uname");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        h_address addressById = addressService.getAddressById(Integer.parseInt(id));
        addressById.setUname(uname);
        addressById.setAddress(address);
        addressById.setTelephone(telephone);
        addressService.updateAddress(addressById);
        return "json:1";
    }
    public String addAddress(HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
        h_user currUser = (h_user) session.getAttribute("currUser");
        request.setCharacterEncoding("utf-8");
        String uname = request.getParameter("uname");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        h_address hAddress = new h_address();
        hAddress.setAddress(address);
        hAddress.setUname(uname);
        hAddress.setTelephone(telephone);
        hAddress.setUid(currUser);
        addressService.addAddrss(hAddress);
        return "json:1";
    }
}
