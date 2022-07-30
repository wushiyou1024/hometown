package com.atguigu.home.controller;

import com.atguigu.home.dao.CartDAO;
import com.atguigu.home.pojo.*;
import com.atguigu.home.service.CartItemService;
import com.atguigu.home.service.CartService;
import com.atguigu.home.service.GoodsService;
import com.atguigu.home.service.impl.CartItemServiceImpl;
import com.atguigu.home.service.impl.CartServiceImpl;
import com.atguigu.home.service.impl.GoodsServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

public class CartController extends HttpServlet {
    private CartItemService cartItemService;
    private GoodsService goodsService;
    private h_goods goods;//
    private CartService cartService;
    public String delAllCart()
    {
        cartService.delAllCart();
        return "redirect:cart.do";
    }

    public String deleteCart(Integer cartItemId)
    {

        cartItemService.delCartItem(cartItemId);
        return "redirect:cart.do";
    }
    //新方法
    public String index(HttpSession session) {
        h_user user = (h_user) session.getAttribute("currUser");
        h_cartContent cart = cartItemService.getCart(user.getId());
//
        Map<Integer, h_cart> map = cart.getCartItemMap();
        //方法一: 用entrySet()

        Iterator it = map.entrySet().iterator();

        while (it.hasNext()) {

            Map.Entry m = (Map.Entry) it.next();
            h_cart value = (h_cart) m.getValue();
            h_goods gid = value.getGid();
            h_goods newGoods = goodsService.getGoods(gid.getId());
            value.setGid(newGoods);

        }

        user.setCartContent(cart);
        session.setAttribute("currUser", user);
        return "user/cart";
    }

    public String addCart(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        Integer mid = Integer.valueOf(request.getParameter("mid"));//商户id
        Integer uid = Integer.parseInt(request.getParameter("uid"));//用户id
        Integer gid = Integer.valueOf(request.getParameter("gid"));//商品id
        Integer goodsCount = Integer.valueOf(request.getParameter("goodsCount"));//购买总量
        Double goodsPrice = Double.valueOf(request.getParameter("price"));//物品单价
        String content = request.getParameter("goodscontent");//商品简介
        String title = request.getParameter("title");//商品标题
        String img = request.getParameter("img");//商品图片
        int sum = Integer.parseInt(request.getParameter("sum"));//商品库存量
        if (goodsCount>sum)
        {
            String attention="您选择的数量超过库存量无法加入购物车！！！";
            session.setAttribute("attention",attention);
            return "user/detail";
        }
        h_goods h_goods = goodsService.getGoodsByid(gid);
        h_cart cart = new h_cart(h_goods, goodsCount, uid, goodsPrice, null);
        cartItemService.addOrUPdateCartItem(cart, cartItemService.getCart(uid));
        h_cartContent cart1 = cartItemService.getCart(uid);
        return "redirect:cart.do";


    }

    public String  editCart(Integer cartItemId,Integer buyCount)
    {
        if (buyCount<1)
        {

            cartItemService.updateCartItem(new h_cart(cartItemId,1));
            return "redirect:cart.do";
        }

        cartItemService.updateCartItem(new h_cart(cartItemId,buyCount));

        return "redirect:cart.do";
    }

}
