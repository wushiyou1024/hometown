package com.atguigu.home.controller;

import com.atguigu.home.pojo.*;
import com.atguigu.home.service.*;
import com.atguigu.myssm.util.FileUploadUtil;
import com.atguigu.myssm.util.StringUtil;
import com.google.gson.Gson;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-21 12:42
 */
public class MerchantController {
    private GoodsService goodsService;
    private OrderItemService orderItemService;
    private CommentService commentService;
    private InforService inforService;
    private UserService userService;
    private ReplyService replyService;
    private AddressService addressService;
    private MerchantService merchantService;

    public String getMerchant(HttpSession session, HttpServletResponse response) {

        h_merchant currMerchant = (h_merchant) session.getAttribute("currMerchant");
        Gson gson = new Gson();
        String s = gson.toJson(currMerchant);
        return "json:" + s;
    }

    public String getdatatest(HttpSession session, HttpServletResponse response) {
        DataUtil dataUtil = (DataUtil) session.getAttribute("dataUtil");
        long sum = dataUtil.getCommentSum();
        long goodsSum = dataUtil.getGoodsSum();
        long inforSum = dataUtil.getInforSum();
        long orderSum = dataUtil.getOrderSum();
        Gson gson = new Gson();
        Map map = new HashMap();

        String s = gson.toJson("[" + goodsSum + "," + sum + "," + inforSum + "," + orderSum + "]");
        System.out.println(s);
        int i = s.lastIndexOf("]");
        System.out.println(s.substring(1, i + 1));
        return "json:" + s.substring(1, i + 1);

    }

    public String getData(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        h_merchant currMerchant = (h_merchant) session.getAttribute("currMerchant");
        //商家发布的商品总数
        Long goodsSum = goodsService.getCountByid("", currMerchant.getId());

        //商家订单总数
        List<h_order_item> allOrderItemById = orderItemService.getAllOrderItemById(currMerchant.getId());

        //评论统计
        List<h_comment> commentListById = commentService.getCommentListById(3, currMerchant.getId(), 0);
        //公告统计
        List<h_information> infolistById = inforService.getInfolistById(currMerchant.getId());
        DataUtil dataUtil = new DataUtil(goodsSum, allOrderItemById.size(), commentListById.size(), infolistById.size());
        session.setAttribute("dataUtil", dataUtil);
        return "mechant/main";
    }

    public String getInfor(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        h_merchant merchant = (h_merchant) session.getAttribute("currMerchant");
        List<h_information> infolist = inforService.getInfolistById(merchant.getId());
        List<infoFormat> infoFormats = new ArrayList<>();
        for (int i = 0; i < infolist.size(); i++) {
            Integer id = infolist.get(i).getId();
            String content = infolist.get(i).getContent();
            Integer status = infolist.get(i).getStatus();
            LocalDateTime infoDate = infolist.get(i).getInfoDate();
            infoFormat infoFormat = new infoFormat();
            infoFormat.setId(id);
            infoFormat.setContent(content);
            if (status == 1)
                infoFormat.setStatus("通过");
            else if (status == 0)
                infoFormat.setStatus("失败");
            else
                infoFormat.setStatus("待审核");
            //  重点：自定义的格式。如：ofPattern(“yyyy-MM-dd hh:mm:ss”)
            DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            //格式化
            String str4 = formatter3.format(infoDate.now());
            infoFormat.setInfoDate(str4);
            infoFormats.add(i, infoFormat);
        }
        Gson gson = new Gson();
        String json = gson.toJson(infoFormats);
        return "json:" + json;
    }

    public String delInfor(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        inforService.delInfor(Integer.parseInt(id));
        return "mechant/infor";
    }

    public String editInforByid(HttpSession session, HttpServletRequest request) {
        String id = request.getParameter("id");
        h_information h_information = new h_information();
        if (id != null) {
            //先把信息查询出来
            h_information = inforService.getInforById(Integer.parseInt(id));

        } else {
            h_information.setId(0);
            h_information.setContent("请输入公告内容");
        }
        session.setAttribute("thisInfor", h_information);
        return "mechant/updateInfo";
    }

    public String updateInfo(HttpServletRequest request) {
        String id = request.getParameter("id");
        String mid = request.getParameter("mid");
        String content = request.getParameter("content");

        if (id.equals("0") || id == null) {
            //新增

            inforService.addinfo(content, Integer.parseInt(mid), LocalDateTime.now());
        } else {
            //修改
            inforService.updateInfor(content, LocalDateTime.now(), Integer.parseInt(id));
        }
        return "mechant/infor";
    }

    public String getCommentedById(HttpSession session, HttpServletRequest request) {
        Integer pageNo = 1;
        String pageNoStr = request.getParameter("pageNo");
        if (StringUtil.isNotEmpty(pageNoStr)) {
            pageNo = Integer.parseInt(pageNoStr);
        }
        h_merchant currMerchant = (h_merchant) session.getAttribute("currMerchant");
        List<h_comment> commentListById = commentService.getCommentListById(1, currMerchant.getId(), pageNo);
        List<Commented> list = new ArrayList<>();


        for (int i = 0; i < commentListById.size(); i++) {
            Commented commented = new Commented();
            h_goods goods = goodsService.getGoods(commentListById.get(i).getGid().getId());
            commented.setGimg(goods.getImg());
            commented.setId(commentListById.get(i).getId());
            commented.setComment(commentListById.get(i).getContent());
            Integer id = commentListById.get(i).getUid().getId();
            h_user user = userService.getUser(id);
            String uname = user.getUname();
            commented.setUserName(uname);

            commented.setCdate(commentListById.get(i).getCdate());
            Integer id1 = commentListById.get(i).getId();
            h_reply h_reply = replyService.getreplyByCid(id1);
            commented.setReply(h_reply.getMcontent());
            commented.setRdate(replyService.getreplyByCid(commentListById.get(i).getId()).getReplyDate());
            commented.setGname(goods.getTitle());
            list.add(i, commented);

        }

        Gson gson = new Gson();
        Long count = commentService.getCommentedCountById(1, currMerchant.getId());
        Long pageCount = (count + 10 - 1) / 10;
        listUtil glist = new listUtil();
        glist.setCommenteds(list);
        glist.setCount(count);
        glist.setPage(pageNo);
        glist.setPageCount(pageCount);
        String s = gson.toJson(glist);
        return "json:" + s;
    }

    public String getUncommentById(HttpSession session, HttpServletRequest request) {
        Integer pageNo = 1;
        String pageNoStr = request.getParameter("pageNo");
        if (StringUtil.isNotEmpty(pageNoStr)) {
            pageNo = Integer.parseInt(pageNoStr);
        }

        h_merchant currMerchant = (h_merchant) session.getAttribute("currMerchant");
        List<h_comment> commentListById = commentService.getCommentListById(0, currMerchant.getId(), pageNo);
        List<Commented> list = new ArrayList<>();


        for (int i = 0; i < commentListById.size(); i++) {
            Commented commented = new Commented();
            h_goods goods = goodsService.getGoods(commentListById.get(i).getGid().getId());
            commented.setGimg(goods.getImg());
            commented.setId(commentListById.get(i).getId());
            commented.setComment(commentListById.get(i).getContent());
            Integer id = commentListById.get(i).getUid().getId();
            h_user user = userService.getUser(id);
            String uname = user.getUname();
            commented.setUserName(uname);

            commented.setCdate(commentListById.get(i).getCdate());
            commented.setGname(goods.getTitle());
            list.add(i, commented);

        }

        Gson gson = new Gson();
        Long count = commentService.getCommentedCountById(0, currMerchant.getId());
        Long pageCount = (count + 10 - 1) / 10;
        listUtil glist = new listUtil();
        glist.setCommenteds(list);
        glist.setCount(count);
        glist.setPage(pageNo);
        glist.setPageCount(pageCount);
        String s = gson.toJson(glist);
        return "json:" + s;
    }

    public String addReply(HttpSession session, HttpServletRequest request) {
        String id = request.getParameter("id");//评论id
        String reply = request.getParameter("reply");//商家回复内容
        h_merchant currMerchant = (h_merchant) session.getAttribute("currMerchant");
        //插入reply表
        replyService.addReply(new h_reply(0, new h_merchant(currMerchant.getId()), reply, LocalDateTime.now(), new h_comment(Integer.parseInt(id))));
        //修改comment状态 根据评论id
        commentService.updateStatus(Integer.parseInt(id));
        String substring = getUncommentById(session, request).substring("json:".length());
        return "json:" + substring;
    }

    public String getOrderedById(HttpSession session, HttpServletRequest request) {
        Integer pageNo = 1;
        String pageNoStr = request.getParameter("pageNo");
        if (StringUtil.isNotEmpty(pageNoStr)) {
            pageNo = Integer.parseInt(pageNoStr);
        }

        h_merchant currMerchant = (h_merchant) session.getAttribute("currMerchant");
        List<h_order_item> allorderItemByMid = orderItemService.getAllorderItemByMid(1, currMerchant.getId(), pageNo);
        List<Ordered> orderedList = new ArrayList<>();
        for (int i = 0; i < allorderItemByMid.size(); i++) {
            h_goods goods = goodsService.getGoods(allorderItemByMid.get(i).getGid().getId());
            Ordered ordered = new Ordered();
            ordered.setId(allorderItemByMid.get(i).getId());
            ordered.setImg(goods.getImg());
            ordered.setGname(goods.getTitle());
            ordered.setTotal(allorderItemByMid.get(i).getBuyCount());
            ordered.setPrice(goods.getPrice());
            ordered.setTotalMoney(goods.getPrice() * allorderItemByMid.get(i).getBuyCount());
            h_address address = allorderItemByMid.get(i).getAddress();
            Integer id = address.getId();
            h_address h_address = addressService.getAddressById(id);
            if (h_address == null)
                ordered.setExpress("用户已删除收货地址");
            else
                ordered.setExpress(h_address.getAddress() + " " + h_address.getUname() + " " + h_address.getTelephone());//设置收货地址 根据allorderItemByMid.get(i).getExpressId()
            ordered.setExpressNo(allorderItemByMid.get(i).getExpressId());
            Integer orderStatus = allorderItemByMid.get(i).getOrderStatus();
            if (orderStatus == 1) {
                ordered.setStatus("已发货");
            } else if (orderStatus == 2) {
                ordered.setStatus("已收货");
            } else if (orderStatus == 3) {

                ordered.setStatus("已完成");
            } else {
                ordered.setStatus("getOrderedById方法出错");
            }
            orderedList.add(i, ordered);
        }
        Long count = orderItemService.getAllorderCount(1, currMerchant.getId());
        Long pageCount = (count + 10 - 1) / 10;
        listUtil glist = new listUtil();
        glist.setOrderedList(orderedList);
        glist.setCount(count);
        glist.setPage(pageNo);
        Gson gson = new Gson();
        glist.setPageCount(pageCount);
        String s = gson.toJson(glist);
        return "json:" + s;
    }

    public String getOrderItem(HttpSession session, HttpServletRequest request) {
        Integer pageNo = 1;
        String pageNoStr = request.getParameter("pageNo");
        if (StringUtil.isNotEmpty(pageNoStr)) {
            pageNo = Integer.parseInt(pageNoStr);
        }
        String oid = (String) session.getAttribute("adminOid");
        List<h_order_item> allOrderItemByOid = orderItemService.getAllOrderItemByOid(Integer.parseInt(oid));
        Long count = orderItemService.getAllCount(Integer.parseInt(oid));

        List<Ordered> orderedList = new ArrayList<>();
        for (int i = 0; i < allOrderItemByOid.size(); i++) {
            h_goods goods = goodsService.getGoods(allOrderItemByOid.get(i).getGid().getId());
            Ordered ordered = new Ordered();
            ordered.setId(allOrderItemByOid.get(i).getId());
            ordered.setImg(goods.getImg());
            ordered.setGname(goods.getTitle());
            ordered.setTotal(allOrderItemByOid.get(i).getBuyCount());
            ordered.setPrice(goods.getPrice());
            ordered.setTotalMoney(goods.getPrice() * allOrderItemByOid.get(i).getBuyCount());
            h_address address = allOrderItemByOid.get(i).getAddress();
            Integer id = address.getId();
            h_address h_address = addressService.getAddressById(id);

            ordered.setExpress(h_address.getAddress() + " " + h_address.getUname() + " " + h_address.getTelephone());//设置收货地址 根据allorderItemByMid.get(i).getExpressId()
            ordered.setExpressNo(allOrderItemByOid.get(i).getExpressId());
            Integer orderStatus = allOrderItemByOid.get(i).getOrderStatus();
            if (orderStatus == 1) {
                ordered.setStatus("已发货");
            } else if (orderStatus == 2) {
                ordered.setStatus("已收货");
            } else if (orderStatus == 0) {
                ordered.setStatus("待发货");
            } else {
                ordered.setStatus("已完成");
            }
            orderedList.add(i, ordered);
        }

        Long pageCount = (count + 10 - 1) / 10;
        listUtil glist = new listUtil();
        glist.setOrderedList(orderedList);
        glist.setCount(count);
        glist.setPage(pageNo);
        Gson gson = new Gson();
        glist.setPageCount(pageCount);
        String s = gson.toJson(glist);
        return "json:" + s;
    }

    public String getOrderedAll(HttpSession session, HttpServletRequest request) {
        String oid = request.getParameter("oid");

        session.setAttribute("adminOid", oid);
        return "admin/orderItem";
    }

    public String updateExpressNo(HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");//orderItemid
        String expressNo = request.getParameter("reply");//新单号

        orderItemService.updateExpreeNoByid(expressNo, Integer.parseInt(id));
        String orderedById = getOrderedById(session, request).substring("json:".length());
        return "json:" + orderedById;
    }

    public String updateExpressNoByAdmin(HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");//orderItemid
        String expressNo = request.getParameter("reply");//新单号

        orderItemService.updateExpreeNoByid(expressNo, Integer.parseInt(id));
        String orderItem = getOrderItem(session, request).substring("json:".length());
        return "json:" + orderItem;
    }

    public String UngetOrderedById(HttpSession session, HttpServletRequest request) {
        Integer pageNo = 1;
        String pageNoStr = request.getParameter("pageNo");
        if (StringUtil.isNotEmpty(pageNoStr)) {
            pageNo = Integer.parseInt(pageNoStr);
        }

        h_merchant currMerchant = (h_merchant) session.getAttribute("currMerchant");
        List<h_order_item> allorderItemByMid = orderItemService.getAllorderItemByMid(0, currMerchant.getId(), pageNo);
        List<Ordered> orderedList = new ArrayList<>();
        for (int i = 0; i < allorderItemByMid.size(); i++) {
            h_goods goods = goodsService.getGoods(allorderItemByMid.get(i).getGid().getId());
            Ordered ordered = new Ordered();
            ordered.setId(allorderItemByMid.get(i).getId());
            ordered.setImg(goods.getImg());
            ordered.setGname(goods.getTitle());
            ordered.setTotal(allorderItemByMid.get(i).getBuyCount());
            ordered.setPrice(goods.getPrice());
            ordered.setTotalMoney(goods.getPrice() * allorderItemByMid.get(i).getBuyCount());
            h_address address = allorderItemByMid.get(i).getAddress();
            Integer id = address.getId();
            h_address addressById = addressService.getAddressById(id);
            String address1;
            if (addressById == null)
                address1 = "历史订单地址无法查询";
            else
                address1 = addressService.getAddressById(id).getAddress();

            ordered.setExpress(address1);//设置收货地址 根据allorderItemByMid.get(i).getExpressId()
            orderedList.add(i, ordered);
        }
        Long count = orderItemService.getAllorderCount(0, currMerchant.getId());
        Long pageCount = (count + 10 - 1) / 10;
        listUtil glist = new listUtil();
        glist.setOrderedList(orderedList);
        glist.setCount(count);
        glist.setPage(pageNo);
        Gson gson = new Gson();
        glist.setPageCount(pageCount);
        String s = gson.toJson(glist);
        return "json:" + s;
    }

    public String updateOrderStatus(HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");//订单id
        String reply = request.getParameter("reply");//新的单号
        //根据id新增单号，修改状态
        orderItemService.updateExpreeNoByid(reply, Integer.parseInt(id));
        orderItemService.updateStatusById(Integer.parseInt(id), 1);
        String s = UngetOrderedById(session, request).substring("json:".length());
        return "json:" + s;
    }

    public String updateProfile(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> map = FileUploadUtil.imgFile(request, response);
        String id = map.get("id");
        String imgName = map.get("imgName");
        String mname = map.get("mname");
        String mintro = map.get("mintro");
        String mpwd = map.get("mpwd");
        String email = map.get("email");
        h_merchant currMerchant = (h_merchant) session.getAttribute("currMerchant");
        if (imgName == null)
            imgName = currMerchant.getMimg();
        if (mpwd == null)
            mpwd = currMerchant.getMpwd();
        currMerchant.setId(Integer.parseInt(id));
        currMerchant.setMimg(imgName);
        currMerchant.setMname(mname);
        currMerchant.setMintro(mintro);
        currMerchant.setMpwd(mpwd);
        currMerchant.setEmail(email);
        merchantService.updateProfile(currMerchant);
        session.setAttribute("currMerchant", currMerchant);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("<script language='javascript'>alert('修改成功!')</script>");
        String whether = (String) session.getAttribute("whether");
        if (whether != null) {
            return "mechant/index";
        } else {
            return "mechant/profile";
        }
    }

    public String updatePwd(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        h_merchant merchant = merchantService.getMerchantByid(Integer.parseInt(id));
        String oldPwd = request.getParameter("oldPwd");
        String newPwd = request.getParameter("newPwd");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (merchant.getMpwd().equals(oldPwd)) {
            //
            h_merchant currMerchant = (h_merchant) session.getAttribute("currMerchant");
            currMerchant.setMpwd(newPwd);
            merchantService.updateProfile(currMerchant);

            writer.println("<script language='javascript'>alert('修改成功!')</script>");
            session.setAttribute("currMerchant", currMerchant);
        } else {

            writer.println("<script language='javascript'>alert('密码错误!')</script>");
        }
        return "mechant/updatePwd";
    }
}
