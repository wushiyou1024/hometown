package com.atguigu.home.dao.Impl;

import com.atguigu.home.dao.GoodsDAO;
import com.atguigu.home.pojo.h_goods;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bless_Wu
 * @Description
 * @create 2022-06-20 16:34
 */
public class GoodsDAOImpl extends BaseDAO<h_goods> implements GoodsDAO {
    @Override
    public Long getCountsMe(String first, String ending, Integer id) {
        String sql = "select count(*) from h_goods where mid=? ";
        StringBuilder sb = new StringBuilder();
        //条件们
        List params = new ArrayList();
        //判断参数是否有值
        params.add(id);
        if (first!=null && !first.equals("") && !ending.equals("") && ending!=null ){
            int e = Integer.parseInt(ending);
            int f = Integer.parseInt(first);
            sb.append(" and price >= ? and price <= ?");
            params.add(f);
            params.add(e);
        }else if (first!=null && !first.equals("")){
            int f = Integer.parseInt(first);
            sb.append(" and price >= ? ");
            params.add(f);
        }else  if (!ending.equals("") && ending!=null){
            int e = Integer.parseInt(ending);
            sb.append(" and price <= ? ");
            params.add(e);
        }

        sql += sb.toString();
        return getValue(sql,params.toArray());
    }
    @Override
    public List<h_goods> getGoodsMe(String first, String ending, Integer pageNo, Integer id) {
        String sql = "SELECT * FROM  h_goods where mid=? ";
        StringBuilder sb = new StringBuilder();
        //条件们
        List params = new ArrayList();
        //判断参数是否有值
        params.add(id);
        if (first!=null && !first.equals("")){
            int f = Integer.parseInt(first);
            sb.append("and price >= ? ");
            params.add(f);
        }
        if (!ending.equals("") && ending!=null){
            int e = Integer.parseInt(ending);
            sb.append("and price <= ? ");
            params.add(e);
        }

        if ((first==null || first.equals("")) && (ending.equals("") || ending==null)){
            int f = Integer.parseInt(first);
            int e = Integer.parseInt(ending);
            sb.append("and price >= ? and price <=? ");
            params.add(f);
            params.add(e);
        }

        //分页
        sb.append(" limit ?,10");
        sql += sb.toString();
        params.add((pageNo-1)*10);
        return executeQuery(sql,params.toArray());
    }
    @Override
    public List<h_goods> getGoodsList(String keyword, Integer pageNo) {
        return executeQuery("SELECT * FROM  h_goods  where title like ? or detail_content like ? LIMIT ?,10","%"+keyword+"%","%"+keyword+"%",(pageNo-1)*10);
    }

    @Override
    public Long getCount(String keyword) {
        String sql = "select count(*)  from h_goods where title like ? or detail_content like ?";
        return getValue(sql,"%"+keyword+"%","%"+keyword+"%");

    }

    @Override
    public List<h_goods> getGoodsListById(String keyword, Integer pageNo, Integer id) {
        return executeQuery("SELECT * FROM  h_goods  where mid=? and categoryid like ? LIMIT ?,10",id,"%"+keyword+"%",(pageNo-1)*10);

    }

    @Override
    public Long getCountByid(String keyword, Integer id) {
        String sql = "select count(*)  from h_goods where mid=? and categoryid like ? ";
        return getValue(sql,id,"%"+keyword+"%");

    }

    @Override
    public void addGoods(h_goods goods) {
        executeUpdate("insert into h_goods values(0,?,?,?,?,?,?,?)",goods.getPrice(),goods.getImg(),goods.getTitle(),goods.getDetail_content(),goods.getCategoryid().getId(),goods.getSum(),goods.getMerchant().getId());
    }

    @Override
    public void deleteById(Integer id) {
        executeUpdate("delete from h_goods where id=?",id);
    }

    @Override
    public h_goods getGoods(Integer id) {
        return load("select * from h_goods where id=?",id);
    }

    @Override
    public void updateGoods(h_goods goods) {
        executeUpdate("update h_goods set price=?,img=?,title=?,detail_content=?,categoryid=?,sum=? where id=?",goods.getPrice(),goods.getImg(),goods.getTitle(),goods.getDetail_content(),goods.getCategoryid().getId(),goods.getSum(),goods.getId());
    }

    @Override
    public Long getSavedCountById(Integer id) {
        return getValue("select sum(buyCount) from h_order_item where gid in (SELECT id FROM h_goods  where mid=? )",id);
    }

    @Override
    public List<h_goods> getGoods(String first, String ending, Integer pageNo) {

        String sql = "SELECT * FROM  h_goods where price>0 ";
        StringBuilder sb = new StringBuilder();
        //条件们
        List params = new ArrayList();
        //判断参数是否有值

        if (first!=null && !first.equals("")){
            int f = Integer.parseInt(first);
            sb.append("and price >= ? ");
            params.add(f);
        }
        if (!ending.equals("") && ending!=null){
            int e = Integer.parseInt(ending);
            sb.append("and price <= ? ");
            params.add(e);
        }

        if ((first==null || first.equals("")) && (ending.equals("") || ending==null)){
            int f = Integer.parseInt(first);
            int e = Integer.parseInt(ending);
            sb.append("and price >= ? and price <=? ");
            params.add(f);
            params.add(e);
        }

        //分页
        sb.append(" limit ?,10");
        sql += sb.toString();
        params.add((pageNo-1)*10);
        return executeQuery(sql,params.toArray());
    }


    @Override
    public Long getCounts(String first, String ending) {
        String sql = "select count(*) from h_goods where ";
        StringBuilder sb = new StringBuilder();
        //条件们
        List params = new ArrayList();
        //判断参数是否有值

        if (first!=null && !first.equals("") && !ending.equals("") && ending!=null ){
            int e = Integer.parseInt(ending);
            int f = Integer.parseInt(first);
            sb.append("price >= ? and price <= ?");
            params.add(f);
            params.add(e);
        }else if (first!=null && !first.equals("")){
            int f = Integer.parseInt(first);
            sb.append("price >= ? ");
            params.add(f);
        }else  if (!ending.equals("") && ending!=null){
            int e = Integer.parseInt(ending);
            sb.append("price <= ? ");
            params.add(e);
        }

        sql += sb.toString();
        return getValue(sql,params.toArray());
    }



    @Override
    public h_goods getGoodsByid(int id)
    {
        return load("select * from h_goods where id=?",id);
    }

    @Override
    public Long getMeCount(String keyword,Integer mid) {
        String sql = "select count(*)  from h_goods where mid=? and( title like ? or detail_content like ?)";
        return getValue(sql,mid,"%"+keyword+"%","%"+keyword+"%");
    }

    @Override
    public List<h_goods> UserGetGoodsListById(String keyword, Integer pageNo, Integer id) {
        return executeQuery("SELECT * FROM  h_goods  where mid=? and (title like ? or detail_content like ?) LIMIT ?,10",id,"%"+keyword+"%","%"+keyword+"%",(pageNo-1)*10);

    }

    @Override
    public List<h_goods> getGoodsListBySid(String id,String keyword, Integer pageNo) {
        return executeQuery("select * from h_goods where categoryid = (select id from h_category where id = ?) and (title like ? or detail_content like ?) LIMIT ?,10",id,"%"+keyword+"%","%"+keyword+"%",(pageNo-1)*10);
    }
    @Override
    public Long getCountBySid(String id,String keyword) {
        String sql = "select count(*) from h_goods where categoryid = (select id from h_category where id = ?) and( title like ? or detail_content like ?)";
        return getValue(sql,id,"%"+keyword+"%","%"+keyword+"%");

    }
    @Override
    public List<h_goods> getGoods(String sid,String first, String ending, Integer pageNo) {

        String sql = "SELECT * FROM  h_goods where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //条件们
        List params = new ArrayList();
        int s = 0;
        //判断参数是否有值
        if (!sid.equals("")){
            s = Integer.parseInt(sid);
            sb.append(" and categoryid = (select id from h_category where id = ?)");
            params.add(s);

        }
        if (!first.equals("")){
            int f = Integer.parseInt(first);
            sb.append(" and price >= ? ");
            params.add(f);
        }
        if (!ending.equals("")){
            int e = Integer.parseInt(ending);
            sb.append("and price <= ? ");
            params.add(e);
        }

        if ( first.equals("") && ending.equals("") ){
            if (!sid.equals("")){
                sb.append("and categoryid = (select id from h_category where id = ?)");
                params.add(s);
            }

        }

        //分页
        sb.append(" limit ?,10");
        sql = sb.toString();
        params.add((pageNo-1)*10);
        return executeQuery(sql,params.toArray());
    }


    @Override
    public Long getCounts(String sid,String first, String ending) {
        String sql = "select count(*) from h_goods where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //条件们
        List params = new ArrayList();
        int s = 0;
        //判断参数是否有值
        if ( !sid.equals("")){
            s = Integer.parseInt(sid);
            sb.append(" and categoryid = (select id from h_category where id = ?)");
            params.add(s);

        }
        if ( !first.equals("")){
            int f = Integer.parseInt(first);
            sb.append(" and price >= ? ");
            params.add(f);
        }
        if (!ending.equals("") ){
            int e = Integer.parseInt(ending);
            sb.append("and price <= ? ");
            params.add(e);
        }


        if ( first.equals("") && ending.equals("") ){
            if (!sid.equals("")){
                sb.append("and categoryid = (select id from h_category where id = ?)");
                params.add(s);
            }
        }
        sql = sb.toString();
        return getValue(sql,params.toArray());
    }

}
