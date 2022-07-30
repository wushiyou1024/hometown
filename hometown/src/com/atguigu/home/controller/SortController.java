package com.atguigu.home.controller;

import com.atguigu.home.pojo.h_category;
import com.atguigu.home.pojo.listUtil;
import com.atguigu.home.service.CategoryService;
import com.atguigu.myssm.util.StringUtil;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-23 9:23
 */
public class SortController {
    private CategoryService categoryService;

    public String getSorts(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
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
        List<h_category> all = categoryService.getAll(keyword, pageNo);


        long count = categoryService.getCount(keyword);
        long pageCount = (count + 10 - 1) / 10;
        listUtil glist = new listUtil();

        glist.setCategory(all);
        glist.setKeyword(keyword);
        glist.setCount(count);
        glist.setPage(pageNo);
        glist.setPageCount(pageCount);
        Gson gson = new Gson();
        String json = gson.toJson(glist);
        return "json:" + json;
    }

    public String deleteById(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String id = request.getParameter("id");
        categoryService.deleteById(Integer.parseInt(id));
        String substring = getSorts(request, response).substring("json:".length());
        return "json:" + substring;
    }

    public String updateSortById(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        h_category sort = categoryService.getSort(Integer.parseInt(id));
        Gson gson = new Gson();
        String s = gson.toJson(sort);
        return "json:" + s;
    }

    public String updateSort(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String sort = request.getParameter("sort");
        if ("".equals(id)) {
            //新增方法
            categoryService.add(sort);
        } else {
            //修改方法
            categoryService.update(sort, Integer.parseInt(id));
        }
        String sorts = getSorts(request, response).substring("json:".length());

        return "json:"+sorts;
    }
}
