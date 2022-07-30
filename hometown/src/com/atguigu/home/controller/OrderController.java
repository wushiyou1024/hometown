package com.atguigu.home.controller;

import com.atguigu.home.pojo.*;
import com.atguigu.home.service.*;
import com.google.gson.Gson;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class OrderController {

    private OrderService orderService;
    private OrderItemService orderItemService;
    private GoodsService goodsService;
   private AddressService addressService;
   private MerchantService merchantService;
   private CategoryService categoryService;
   private CommentService commentService;
   private ReplyService replyService;
    public String pay(HttpSession session,Integer id)
    {
        if (id==null)
        {
            String attention="请选择收货地址";
            session.setAttribute("attention",attention);
            return "user/settlement";
        }

        h_order orderBean = new h_order();
        Date now = new Date();
        int year = now.getYear() + 1900;
        int month = now.getMonth() + 1;
        int day = now.getDate();
        int hours = now.getHours();
        int minutes = now.getMinutes();
        int seconds = now.getSeconds();
        orderBean.setOrderNo(UUID.randomUUID().toString() + year + month + day + hours + minutes + seconds);
        orderBean.setOrderDate(LocalDateTime.now());
        h_user currUser = (h_user) session.getAttribute("currUser");
        orderBean.setUid(currUser);
        orderBean.setTotalGoodsCount(currUser.getCartContent().getTotalGoodsCount());
        orderBean.setOrderMoney(currUser.getCartContent().getTotalPrice());
        orderService.addOrderBean(orderBean);
        Integer oid = orderBean.getId();

        h_address address = addressService.getAddressById(id);
        h_cartContent cartContent = currUser.getCartContent();
        Collection<h_cart> checkoutGoods = cartContent.getCartItemMap().values();
        for (h_cart cart : checkoutGoods) {
            h_order_item orderItem = new h_order_item();
            orderItem.setGid(cart.getGid());
            orderItem.setOrderStatus(0);
            orderItem.setBuyCount(cart.getBuyCount());
            orderItem.setOid(orderService.selectOrder(oid));
            orderItem.setAddress(address);
            //付款成功修改库存量
            orderItemService.addOrderItem(orderItem);
            orderItemService.delOrderItem(orderItem.getGid().getId());
            Integer buyCount = orderItem.getBuyCount();
            Integer gid = orderItem.getGid().getId();
            h_goods goodsByid = goodsService.getGoodsByid(gid);
            goodsByid.setSum(goodsByid.getSum()-buyCount);
            goodsService.updateGoods(goodsByid);
        }
        session.setAttribute("address",address);
        session.setAttribute("checkoutGoods",checkoutGoods);
        session.setAttribute("orderBean", orderBean);
        return "user/checkout";
    }
    //结账
    public String checkout(HttpSession session) {
        h_order orderBean = new h_order();
        h_user currUser = (h_user) session.getAttribute("currUser");
        orderBean.setUid(currUser);
        orderBean.setTotalGoodsCount(currUser.getCartContent().getTotalGoodsCount());
        orderBean.setOrderMoney(currUser.getCartContent().getTotalPrice());
        Integer oid = orderBean.getId();
        h_cartContent cartContent = currUser.getCartContent();
        if (cartContent.getCartItemMap().size()==0)
        {
            return "user/cart";
        }
        Collection<h_cart> checkoutGoods = cartContent.getCartItemMap().values();
        h_address defaultAddress=new h_address();
        if (addressService.getDefaultAddress(currUser.getId())==null)
        {
            defaultAddress.setAddress("默认地址列表为空！请新增地址");
            defaultAddress.setTelephone("默认电话列表为空！请新增电话");
            defaultAddress.setUname("默认姓名列表为空！请新增姓名");
        }
        else
        {
            defaultAddress=addressService.getDefaultAddress(currUser.getId());
        }


        session.setAttribute("defaultAddress",defaultAddress);
        session.setAttribute("checkoutGoods",checkoutGoods);
        session.setAttribute("orderBean", orderBean);
        return "user/settlement";
    }
    //以上6.26新增加
    //结账


    //新方法
    public String getOrderList(HttpSession session) {

        h_user currUser = (h_user) session.getAttribute("currUser");
        List<h_order> orderList = orderService.getOrderList(currUser);
        session.setAttribute("orderList", orderList);

        return "user/order";

    }

    public String getOrderDetail(Integer orderId, HttpSession session) {
        List<h_order_item> allOrderItemByOid = orderItemService.getAllOrderItemByOid(orderId);
        session.setAttribute("orderId",orderId); // 新
        for (h_order_item orderItem : allOrderItemByOid) {
            h_goods goods = goodsService.getGoods(orderItem.getGid().getId());
            orderItem.setGid(goods);
            System.out.println(orderItem.getGid().getImg());
        }

        session.setAttribute("allOrderItemByOid", allOrderItemByOid);

        return "user/orderDetail";
    }

    public String updateOrderStatus(Integer id, Integer status) {
        orderService.updateStatusById(id, status);
        return "user/order";
    }

    public String getAllOrder(HttpServletRequest request, HttpServletResponse response) {
        List<h_order> allOrder = orderService.getAllOrder();
         List<orderFormat> formats= new ArrayList<>();
        for (int i = 0; i <allOrder.size() ; i++) {
            orderFormat orderFormat = new orderFormat();
            orderFormat.setId(allOrder.get(i).getId());
            orderFormat.setUid(allOrder.get(i).getUid().getId());
            orderFormat.setOrderNo(allOrder.get(i).getOrderNo());
            orderFormat.setTotalGoodsCount(allOrder.get(i).getTotalGoodsCount());
            orderFormat.setOderMoney(allOrder.get(i).getOrderMoney());
            LocalDateTime orderDate = allOrder.get(i).getOrderDate();
            DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            String format = formatter3.format(orderDate);
            orderFormat.setOrderDate(format);
            formats.add(i,orderFormat);
        }
        Gson gson=new Gson();
        String s = gson.toJson(formats);
        return "json:"+s;}
    // 6.27评论商品显示
    public String commentGoodsById(Integer gid,HttpSession session){
        h_goods goods = goodsService.getGoods(gid);
        session.setAttribute("goodsGid",goods);

        Integer id = goods.getMid().getId();
        Integer cid = goods.getCategoryid().getId();
        h_merchant merchantByid = merchantService.getMerchantByid(id);
        h_category sort = categoryService.getSort(cid);
        session.setAttribute("mnameByid",merchantByid);
        session.setAttribute("sortByid",sort);
        session.setAttribute("goodsid",gid);
        h_order_item getOrderItemByGid = orderItemService.getOrderItemByGid(gid);
        session.setAttribute("OrderItemByGid",getOrderItemByGid);

        return "user/comment";
    }
    //  查看评论
    public String checkCommentById(HttpSession session, HttpServletRequest request){

        String id = request.getParameter("id");
        Integer cid = Integer.valueOf(id);

        h_comment h_comment = commentService.getCommentById(cid);
        String content = h_comment.getContent();
        LocalDateTime cdate = h_comment.getCdate();
        Integer commentId = h_comment.getId();
        h_reply h_reply = replyService.getreplyByCid(commentId);
        String mcontent = null;
        LocalDateTime replyDate = null;
        if (h_reply!=null){
            mcontent  = h_reply.getMcontent();
            replyDate = h_reply.getReplyDate();
        }

        LocalDateTime time = LocalDateTime.now();
        String nowTime = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        orderUtil orderUtil=new orderUtil();
        orderUtil.setContent(content);
        String format = cdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        orderUtil.setCdate(format);

        if (mcontent==null && replyDate==null){
            orderUtil.setReply("暂时没有回复");
            orderUtil.setRdate("没有回复时间");

        }else {
            orderUtil.setReply(mcontent);
            String format1 = replyDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            orderUtil.setRdate(format1);
        }


        Gson gson=new Gson();
        String s = gson.toJson(orderUtil);
        return "json:"+s;
    }

    // 提交评论
    public String commentByGid(HttpSession session,HttpServletRequest request){
        Integer goodsid = (Integer) session.getAttribute("goodsid");
        Integer orderId = (Integer) session.getAttribute("orderId");
        Integer h = orderService.getIdByGidAndOid(goodsid,orderId);
        h_goods goods = goodsService.getGoods(goodsid);
//        Integer gid = goods.getId();
        Integer mid = goods.getMid().getId();
        h_user currUser = (h_user) session.getAttribute("currUser");
        Integer uid = currUser.getId();
        LocalDateTime nowDateTime = LocalDateTime.now();
        String content = request.getParameter("content");
        //这边插入
        orderService.insertComment(mid,goodsid,uid,content,nowDateTime,h);
        orderService.updateOsByGidAndOid(goodsid,h);

        return "json:1";
    }
    public String addExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String picture="C:\\Users\\17742\\Desktop\\";
        String photo = request.getParameter("photo");
          picture+=photo;
        int table = input_table(picture);
        return "mechant/category";
    }
    public int input_table(String picture) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(picture);
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        int result = 0;
        int lastRowNum = sheet.getLastRowNum();
        for(int i  =  0;i <= lastRowNum;i++){
            XSSFRow row = sheet.getRow(i);
            if(row != null){
                List<String> list = new ArrayList<>();
                for (Cell cell : row){
                    if(cell != null){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String value = cell.getStringCellValue();
                        if(value != null && !"".equals(value)){
                            list.add(value);
                        }
                    }
                }
                if(list.size()>0){
                  //插入操作\
                    categoryService.add(list.get(0));
                }
            }
        }
        return 1;
    }
}
