package com.atguigu.home.controller;

import com.atguigu.home.pojo.*;


import com.atguigu.home.service.CartService;
import com.atguigu.home.service.MerchantService;
import com.atguigu.home.service.UserService;
import com.atguigu.myssm.util.FileUploadUtil;
import com.atguigu.myssm.util.MailUtil;
import com.atguigu.myssm.util.StringUtil;
import com.google.gson.Gson;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 20:09
 */
public class UserController {
    private CartService cartService;
    private UserService userService;
    private MerchantService merchantService;

    public String update(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer stringBuffer = new StringBuffer("");
        BufferedReader reader = request.getReader();
        String str = null;
        while ((str = reader.readLine()) != null) {
            stringBuffer.append(str);
        }
        str = stringBuffer.toString();
        Gson gson = new Gson();

        Ouser ouser = gson.fromJson(str, Ouser.class);
        h_user user = ouser.getUser();

        userService.updateUser(user);

        String json = gson.toJson(user);
        session.setAttribute("currUser", user);
        return "json:" + json;

    }

    public String getUser(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        h_user currUser = (h_user) session.getAttribute("currUser");
        if (currUser.getPhone() == null) {
            currUser.setPhone("");
        }
        if (currUser.getWechat() == null) {
            currUser.setWechat("");
        }
        if (currUser.getRealName() == null)
            currUser.setRealName("");

        if (currUser != null) {
            Gson gson = new Gson();
            String toJson = gson.toJson(currUser);
            return "json:" + toJson;
        }
        throw new RuntimeException("獲取用戶時異常");
    }

    //用户注册
    public String regist(String uname, String pwd, String email, String verifyCode, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String role = request.getParameter("role");
        //获取校验码
        Object kaptcha_session_key = session.getAttribute("KAPTCHA_SESSION_KEY");
        if (kaptcha_session_key == null || !verifyCode.equals(kaptcha_session_key)) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script language='javascript'>alert('验证码错误!')</script>");
            return "user/regist";
        } else {
            if (verifyCode.equals(kaptcha_session_key)) {
                if (role.equals("user")) {
                    userService.regist(new h_user(0, uname, pwd, 0, 0, email));
                    h_user user = userService.getUser(uname);
                    Integer id = user.getId();
                    String content = "<a href='http://localhost:8080/hometown/user.do?operate=active&ope=u&id=" + id + "'>点击激活</a>";

                    MailUtil.sendMail(email, content, "邮件激活");
                    return "user/login";
                } else {
                    //商人注册
                    merchantService.regist(new h_merchant(0, 0, uname, pwd, email, 0));
                    h_merchant merchant = merchantService.getMerchant(uname);
                    Integer id = merchant.getId();
                    String content = "<a href='http://localhost:8080/hometown/user.do?operate=active&ope=m&id=" + id + "'>点击激活</a>";

                    MailUtil.sendMail(email, content, "邮件激活");
                    return "admin/login";

                }

            }
        }
        return "user/login";
    }

    //邮箱验证
    public String active(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String ope = request.getParameter("ope");

        if (id != null) {
            if (ope.equals("u")) {
                userService.setStatus(Integer.parseInt(id));
            } else {
                merchantService.setStatus(Integer.parseInt(id));
            }

        }
        return "user/login";
    }

    //账号检查
    public String ckUname(String uname) {
        h_user user = userService.getUser(uname);
        h_merchant merchant = merchantService.getMerchant(uname);

        if (user != null || merchant != null) {
            //用户名已经被注册 不可以注册
            return "json:{'uname':'1'}";
        } else {
            //用户名可以注册
            return "json:{'uname':'0'}";
        }

    }

    //登录管理
    public String login(String uname, String pwd, HttpSession session, HttpServletResponse response) throws IOException {

        h_user user = userService.login(uname, pwd);

        if (user == null) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script language='javascript'>alert('账号不存在!')</script>");

        } else {
            if (user.getRole() == 0) {

                h_cart cart = cartService.getCart(user.getId());
                if (cart == null)
                    cart = new h_cart();
                user.setCart(cart);
                session.setAttribute("currUser", user);
                return "index";
            }

            //以上全删
        }

        return "user/login";
    }

    public String cancelUser(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1.销魂session
        request.getSession().removeAttribute("currUser");
        // 2.跳转登录页面
        return "user/login";
    }

    public String adminLogin(String username, String password, HttpSession session, HttpServletResponse response) throws IOException {
        h_merchant merchant = merchantService.login(username, password);
        h_user user = userService.login(username, password);
        if (merchant != null && merchant.getMstatus() != 0 && merchant.getStatus() != 0) {
            //跳转商家界面
            session.setAttribute("currMerchant", merchant);
            if (merchant.getMname() == null || merchant.getMname().equals("") || merchant.getMintro() == null || merchant.getMintro().equals("")) {
                session.setAttribute("whether", "true");
                return "mechant/profile";

            } else {

                return "mechant/index";
            }
        } else if (user != null && user.getRole() == 1) {
            //跳转管理员
            session.setAttribute("currAdmin", user);
            return "admin/index";

        } else {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            if (merchant != null) {
                writer.println("<script language='javascript'>alert('商家邮箱未激活或管理员未通过!')</script>");
            } else {
                writer.println("<script language='javascript'>alert('密码错误!')</script>");

            }
        }
        return "admin/login";
    }

    public String getAllUser(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        Integer pageNo = 1;
        String pageNoStr = request.getParameter("pageNo");
        if (StringUtil.isNotEmpty(pageNoStr)) {
            pageNo = Integer.parseInt(pageNoStr);
        }
        List<h_user> all = userService.getAll(pageNo);
        Long count = userService.getCount();
        listUtil glist = new listUtil();
        long pageCount = (count + 10 - 1) / 10;
        glist.setUsers(all);
        glist.setCount(count);
        glist.setPage(pageNo);
        glist.setPageCount(pageCount);
        Gson gson = new Gson();
        String json = gson.toJson(glist);
        return "json:" + json;

    }

    public String deleteUserById(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        String id = request.getParameter("id");
        userService.delUserById(Integer.parseInt(id));
        String substring = getAllUser(session, request, response).substring("json:".length());
        return "json:" + substring;
    }

    public String updateUserById(HttpServletRequest request, HttpSession session) {
        String id = request.getParameter("id");
        h_user user = userService.getUser(Integer.parseInt(id));
        Gson gson = new Gson();
        String s = gson.toJson(user);
        return "json:" + s;
    }

    public String updateUser(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        h_user user = userService.getUser(Integer.parseInt(id));
        String uname = request.getParameter("uname");
        if (uname.equals("") || uname == null)
            uname = user.getUname();
        user.setUname(uname);
        String pwd = request.getParameter("pwd");
        if (pwd.equals("") || pwd == null)
            pwd = user.getPwd();
        user.setPwd(pwd);
        String email = request.getParameter("email");
        user.setEmail(email);
        String phone = request.getParameter("phone");
        user.setPhone(phone);
        String wechat = request.getParameter("wechat");
        user.setWechat(wechat);
        String realName = request.getParameter("realName");
        user.setRealName(realName);
        userService.updateUserById(user);
        String substring = getAllUser(session, request, response).substring("json:".length());
        return "json:" + substring;

    }
    public String getAllMerchant(HttpServletRequest request)
    {    Integer pageNo = 1;
        String pageNoStr = request.getParameter("pageNo");
        if (StringUtil.isNotEmpty(pageNoStr)) {
            pageNo = Integer.parseInt(pageNoStr);
        }
        List<h_merchant> allMerchant = merchantService.getAllMerchant(pageNo);
        Long count = merchantService.getAllCount();
        listUtil glist = new listUtil();
        long pageCount = (count + 10 - 1) / 10;
        glist.setMerchants(allMerchant);
        glist.setCount(count);
        glist.setPage(pageNo);
        glist.setPageCount(pageCount);
        Gson gson = new Gson();
        String json = gson.toJson(glist);
        return "json:" + json;
    }

    public String updateMerchantById(HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        h_merchant merchantByid = merchantService.getMerchantByid(Integer.parseInt(id));
         session.setAttribute("updateMer",merchantByid);
         Gson gson=new Gson();
        String s = gson.toJson(merchantByid);
        return "json:"+s;
    }
    public String updateMer(HttpServletResponse response,HttpSession session,HttpServletRequest request) throws ServletException, IOException {
        Map<String, String> map = FileUploadUtil.imgFile(request, response);
        String id = map.get("id");
        h_merchant currMerchant = merchantService.getMerchantByid(Integer.parseInt(id));
        String imgName = map.get("imgName");
         if (imgName==null)
             imgName=currMerchant.getMimg();
         currMerchant.setMimg(imgName);

        String mname = map.get("mname");
        currMerchant.setMname(mname);
        String mintro = map.get("mintro");
        currMerchant.setMintro(mintro);
        String muname = map.get("muname");
       if (muname==null)
           muname=currMerchant.getMuname();
       currMerchant.setMuname(muname);
        String mpwd = map.get("mpwd");
        currMerchant.setMpwd(mpwd);
        String email = map.get("email");
        currMerchant.setEmail(email);
        String status = map.get("status");
         currMerchant.setStatus(Integer.parseInt(status));
        merchantService.updateProfile(currMerchant);
        return "admin/merchantManage";
    }
   public String deleteMerchantById(HttpSession session,HttpServletRequest request,HttpServletResponse response)
   {
       String id = request.getParameter("id");
           merchantService.deleteMerchantById(Integer.parseInt(id));
       String allMerchant = getAllMerchant(request).substring("json:".length());
       return "json:"+allMerchant;
   }
  }
