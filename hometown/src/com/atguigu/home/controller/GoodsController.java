package com.atguigu.home.controller;

import com.atguigu.home.pojo.*;
import com.atguigu.home.service.CategoryService;
import com.atguigu.home.service.GoodsService;
import com.atguigu.home.service.InforService;
import com.atguigu.myssm.util.FileUploadUtil;
import com.atguigu.myssm.util.StringUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 16:17
 */
public class GoodsController {
    private GoodsService goodsService;
    private CategoryService categoryService;
 private InforService inforService;
    public String userGetGoodsById(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        h_goods goodsByid = goodsService.getGoodsByid(Integer.parseInt(id));

        session.setAttribute("goodsByid", goodsByid);


        return "json:1";
    }

    public String getGoodsById(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        //判断是不是搜索
        String oper = request.getParameter("oper");
        //默认值设置
        Integer pageNo = 1;
        String keyword = null;
        keyword = request.getParameter("keyword");
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            pageNo = 1;

            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
        } else {
            String pageNoStr = request.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }

            if (keyword != null) {
                keyword = (String) keyword;
            } else {
                keyword = "";
            }
        }
        h_merchant currMerchant = (h_merchant) session.getAttribute("currMerchant");
        Integer id = currMerchant.getId();
        List<h_goods> goodsListById = goodsService.getGoodsListById(keyword, pageNo, id);
        List<h_category> all = categoryService.getAll();
        Long count = goodsService.getCountByid(keyword, id);
        Long pageCount = (count + 10 - 1) / 10;
        listUtil glist = new listUtil();
        session.setAttribute("categoryAll", all);
        glist.setGoodsList(goodsListById);
        glist.setKeyword(keyword);
        glist.setCategory(all);
        glist.setCount(count);
        glist.setPage(pageNo);
        glist.setPageCount(pageCount);
        Gson gson = new Gson();
        String json = gson.toJson(glist);
        return "json:" + json;
    }
    public String getAllGoods(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        //默认值设置
        Integer pageNo = 1;


            String pageNoStr = request.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }

        List<h_goods> goodsListById = goodsService.getGoodsList("",pageNo);

        Long count =   goodsService.getCount("");
        Long pageCount = (count + 10 - 1) / 10;
        listUtil glist = new listUtil();

        glist.setGoodsList(goodsListById);

        glist.setCount(count);
        glist.setPage(pageNo);
        glist.setPageCount(pageCount);
        Gson gson = new Gson();
        String json = gson.toJson(glist);
        return "json:" + json;
    }


    public String index(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        //判断是不是搜索
        String oper = request.getParameter("oper");

        //默认值设置
        Integer pageNo = 1;
        String keyword = null;
        String id = request.getParameter("sid");



        if (id!=null){
            session.setAttribute("sid",id);
            session.setAttribute("sorted","ed");
        }else
        {
            session.setAttribute("sorted","will");
        }

        keyword = request.getParameter("keyword");
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            pageNo = 1;

            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
                String sorted = (String) session.getAttribute("sorted");
                if (sorted.equals("will"))
                    session.removeAttribute("sid");
            }
            if (StringUtil.isEmpty(id)) {
                id = "";
            }

        } else {
            String pageNoStr = request.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }

            if (keyword != null) {
                keyword = (String) keyword;
            } else {
                keyword = "";
            }
            if (id != null) {
                id = (String) id;
            } else {
                id = "";
            }

        }
        String sid = (String) session.getAttribute("sid");
        List<h_goods> goodsList=null;

        Long count=null;

        if (session.getAttribute("sid")!=null)
        {
            goodsList = goodsService.getGoodsListBySid(sid,keyword, pageNo);
            count = goodsService.getCountBySid(sid,keyword);
        }
        else {
            goodsList   = goodsService.getGoodsList(keyword, pageNo);
            count = goodsService.getCount(keyword);
        }

        List<h_category> categories = categoryService.getAll();

        List<h_category> categoryList = new ArrayList<>();

        for (int i = 0; i <categories.size() ; i++) {
            if (i<=7){
                categoryList.add(i,categories.get(i));
            }

        }

        Long pageCount = (count + 10 - 1) / 10;
        listUtil glist = new listUtil();
        glist.setCategory(categoryList);
        glist.setGoodsList(goodsList);
        glist.setId(id);
        glist.setKeyword(keyword);
        glist.setCategory(categories);
        glist.setCount(count);
        glist.setPage(pageNo);
        glist.setPageCount(pageCount);
        Gson gson = new Gson();
        String json = gson.toJson(glist);
        return "json:" + json;

    }
    public String addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        Map<String, String> map = FileUploadUtil.imgFile(request, response);
        String price = map.get("price");
        String id = map.get("id");
        String imgName = map.get("imgName");

        String title = map.get("title");
        String content = map.get("content");
        String sort = map.get("sort");
        String sum = map.get("sum");

        goodsService.addGoods(new h_goods(0, Double.parseDouble(price), imgName, title, content, new h_category(Integer.parseInt(sort)), Integer.parseInt(sum), new h_merchant(Integer.parseInt(id))));
        return "mechant/goodsManage";
    }

    public String deleteById(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String id = request.getParameter("id");
        goodsService.deleteById(Integer.parseInt(id));

        String goodsById = getGoodsById(session, request, response).substring("json:".length());

        return "json:" + goodsById;
    }
    public String adminDeleteById(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String id = request.getParameter("id");
        goodsService.deleteById(Integer.parseInt(id));

        String goodsById = getAllGoods(session, request, response).substring("json:".length());

        return "json:" + goodsById;
    }

    public String updateGoodsById(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        h_goods goods = goodsService.getGoods(Integer.parseInt(id));
        session.setAttribute("thisGoods", goods);
        return "json:1";
    }

    public String updateGoods(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        Map<String, String> map = FileUploadUtil.imgFile(request, response);
        String price = map.get("price");
        String id = map.get("id");
        String imgName = map.get("imgName");
        h_goods goods = goodsService.getGoods(Integer.parseInt(id));
        if (imgName == null || imgName == "") {
            imgName = goods.getImg();
        }
        String title = map.get("title");
        String content = map.get("content");
        String sort = map.get("sort");
        String sum = map.get("sum");

        goodsService.updateGoods(new h_goods(Integer.parseInt(id), Double.parseDouble(price), imgName, title, content, new h_category(Integer.parseInt(sort)), Integer.parseInt(sum)));
        return "mechant/goodsManage";
    }

    public String priceSearch(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        //判断是不是搜索
        String oper = request.getParameter("oper");
        //默认值设置
        Integer pageNo = 1;
        String first = null;
        String ending = null;
        ending = request.getParameter("ending");
        first = request.getParameter("first");
        session.setAttribute("first",first);
        session.setAttribute("ending",ending);
        String sid = (String) session.getAttribute("sid");

        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            pageNo = 1;
            if (StringUtil.isEmpty(ending)) {
                ending = "";
            }
            if (StringUtil.isEmpty(sid)) {
                sid = "";
            }

            if (StringUtil.isEmpty(first)) {
                first = "";
            }
        } else {
            String pageNoStr = request.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }

            if (first != null) {
                first = (String) first;
            } else {
                first = "";
            }
            if (ending != null) {
                ending = (String) ending;
            } else {
                ending = "";
            }
            if (sid != null) {
                sid = (String) ending;
            } else {
                sid = "";
            }

        }
        List<h_goods> goodsList = null;
        Long count = null;

//        if ((ending.equals("") || ending == null) && (first.equals("") || first == null)) {
//            goodsList = goodsService.getGoodsList("", pageNo);
//            count = goodsService.getCount("");
//        } else {
//            goodsList = goodsService.getGoods(first, ending, pageNo);
//
//            count = goodsService.getCounts(first, ending);
//        }

        if (session.getAttribute("sid")!=null){
            goodsList = goodsService.getGoods(sid,first, ending, pageNo);
            count = goodsService.getCounts(sid,first, ending);
        }else {
            goodsList = goodsService.getGoods("",first, ending, pageNo);
            count = goodsService.getCounts("",first, ending);
        }


        List<h_category> categories = categoryService.getAll();

        List<h_category> categoryList = new ArrayList<>();

        for (int i = 0; i <categories.size() ; i++) {
            if (i<=7){
                categoryList.add(i,categories.get(i));
            }

        }

        Long pageCount = (count + 10 - 1) / 10;
        listUtil glist = new listUtil();
        glist.setCategory(categoryList);
        glist.setGoodsList(goodsList);
        glist.setFirst(first);
        glist.setEnding(ending);

        glist.setCount(count);
        glist.setPage(pageNo);
        glist.setPageCount(pageCount);
        Gson gson = new Gson();
        String json = gson.toJson(glist);
        return "json:" + json;

    }
    // 商家信息
    public String merchantInfo(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        //判断是不是搜索
        String oper = request.getParameter("oper");
        //默认值设置
        Integer pageNo = 1;
        String keyword = null;
        keyword = request.getParameter("keyword");

        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            pageNo = 1;

            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
        } else {
            String pageNoStr = request.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }

            if (keyword != null) {
                keyword = (String) keyword;
            } else {
                keyword = "";
            }
        }
        h_goods goodsByid = (h_goods) session.getAttribute("goodsByid");
        Integer id = goodsByid.getMid().getId();
        List<h_information> infolistById = inforService.getInfoById(id);
//        List<h_goods> goodsListById = goodsService.UserGetGoodsListById(keyword, pageNo, id);
        //获取公告 最新
        //session （information）

        List<h_goods> goodsListById = goodsService.UserGetGoodsListById(keyword, pageNo, id);

        Long count = goodsService.getMeCount(keyword, id);
        Long pageCount = (count + 10 - 1) / 10;
        listUtil glist = new listUtil();
        glist.setGoodsList(goodsListById);
        glist.setKeyword(keyword);
        if (infolistById.size()!=0){
            h_information h_information = infolistById.get(0);
            glist.setInformation(h_information);
        }else
        {
            LocalDateTime nowDateTime = LocalDateTime.now();

            glist.setInformation(new h_information("暂无最新公告",nowDateTime));
        }

        glist.setCount(count);
        glist.setPage(pageNo);
        glist.setPageCount(pageCount);
        Gson gson = new Gson();
        String json = gson.toJson(glist);
        return "json:" + json;

    }
     // 商家价格查询
    public String priceMeSearch(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");

        //判断是不是搜索
        String oper = request.getParameter("oper");
        //默认值设置
        Integer pageNo = 1;
        String first = null;
        String ending = null;
        ending = request.getParameter("ending");
        first = request.getParameter("first");
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            pageNo = 1;
            if (StringUtil.isEmpty(ending)) {
                ending = "";
            }

            if (StringUtil.isEmpty(first)) {
                first = "";
            }
        } else {
            String pageNoStr = request.getParameter("pageNo");
            if (StringUtil.isNotEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }

            if (first != null) {
                first = (String) first;
            } else {
                first = "";
            }
            if (ending != null) {
                ending = (String) ending;
            } else {
                ending = "";
            }

        }
        List<h_goods> goodsList = null;

        h_goods goodsByid = (h_goods) session.getAttribute("goodsByid");
        Integer id = goodsByid.getMid().getId();//商家id
        //获取公告 最新
        //session （information）

        List<h_goods> goodsListById = null;

        Long count = null;
        if ((ending.equals("") || ending == null) && (first.equals("") || first == null)) {
            goodsListById = goodsService.UserGetGoodsListById("", pageNo, id);
//            goodsList = goodsService.getGoodsList("", pageNo);
            count = goodsService.getMeCount("",id);
        } else {
            goodsListById = goodsService.getGoodsMe(first, ending, pageNo,id);
            count = goodsService.getCountsMe(first, ending,id);
        }

        Long pageCount = (count + 10 - 1) / 10;
        listUtil glist = new listUtil();
        List<h_information> infolistById = inforService.getInfoById(id);
        if (infolistById.size()!=0){
            h_information h_information = infolistById.get(0);
            glist.setInformation(h_information);
        }else
        {
            LocalDateTime nowDateTime = LocalDateTime.now();

            glist.setInformation(new h_information("暂无最新公告",nowDateTime));
        }
        glist.setGoodsList(goodsListById);
        glist.setFirst(first);
        glist.setEnding(ending);

        glist.setCount(count);
        glist.setPage(pageNo);
        glist.setPageCount(pageCount);
        Gson gson = new Gson();
        String json = gson.toJson(glist);
        return "json:" + json;


    }
}
