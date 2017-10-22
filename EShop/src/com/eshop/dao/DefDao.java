package com.eshop.dao;

import com.eshop.model.ProductBean;
import com.eshop.util.ConnBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefDao {
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;
    private int num;
    private int pagesize=6;

    /**
     * 查询所有商品数量
     * @return
     */
    public int countPage(){
        connection= ConnBD.getConnection();
        int count=0;
        try {
            sql="SELECT COUNT(*) FROM T_PRODUCT";
            ps=connection.prepareStatement(sql);
            rs=ps.executeQuery();
            if (rs.next()){
                count=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return count;
    }

    /**
     * 查询特价商品数量
     * @return
     */
    public int countSproPage(){
        connection= ConnBD.getConnection();
        int count=0;
        try {
            sql="SELECT COUNT(*) FROM T_PRODUCT WHERE SPRICE!=0.0";
            ps=connection.prepareStatement(sql);
            rs=ps.executeQuery();
            if (rs.next()){
                count=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return count;
    }

    /**
     * 模糊查询商品数量
     * @return
     */
    public int countLikePage(String name, int type){
        connection= ConnBD.getConnection();
        int count=0;
        try {
            sql="SELECT COUNT(*) FROM T_PRODUCT WHERE PRONAME LIKE ? AND TYPEID LIKE ?";
            ps=connection.prepareStatement(sql);
            ps.setString(1,"%"+name+"%");
            if (type==0){
                ps.setString(2,"%%");
            }else {
                ps.setString(2,"%"+type+"%");
            }
            rs=ps.executeQuery();
            if (rs.next()){
                count=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return count;
    }

    public int pageLikeNum(String name, int type){
        int count = countLikePage(name,type);
        int num=0;
        if (count%pagesize==0){
            num=count/pagesize;
        }else {
            num=(count/pagesize)+1;
        }
        return num;
    }

    public int pageNum(String temp){
        int count=0;
        if (temp.equals("pro")) {
            count = countPage();
        }else if (temp.equals("spro")){
            count = countSproPage();
        }
        int num=0;
        if (count%pagesize==0){
            num=count/pagesize;
        }else {
            num=(count/pagesize)+1;
        }
        return num;
    }

    /**
     * 特价商品
     * @param curPage
     * @return
     */
    public List<ProductBean> querySpro(int curPage){
        connection= ConnBD.getConnection();
        ArrayList<ProductBean> list=new ArrayList<>();
        try {
            sql="SELECT PROID,PRONAME,PROPIC,PRICE,SPRICE,PROFACTORY FROM (SELECT p.*,rownum rn FROM T_PRODUCT p WHERE SPRICE != 0.0 ORDER BY FACDATE DESC) WHERE rn>? AND rn<=? ";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,(curPage-1)*pagesize);
            ps.setInt(2,curPage*pagesize);
            rs=ps.executeQuery();
            while (rs.next()){
                ProductBean proBean=new ProductBean();
                proBean.setProid(rs.getInt("PROID"));
                proBean.setProname(rs.getString("PRONAME"));
                proBean.setPropic(rs.getString("PROPIC"));
                proBean.setPrice(rs.getDouble("PRICE"));
                proBean.setSprice(rs.getDouble("SPRICE"));
                proBean.setProfactory(rs.getString("PROFACTORY"));
                list.add(proBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return list;
    }

    /**
     * 最新商品
     * @param curPage
     * @return
     */
    public List<ProductBean> queryPro(int curPage){
        connection= ConnBD.getConnection();
        ArrayList<ProductBean> list=new ArrayList<>();
        try {
            sql="SELECT PROID,PRONAME,PROPIC,PRICE,SPRICE,PROFACTORY FROM (SELECT p.*,rownum rn FROM T_PRODUCT p) WHERE rn>? AND rn<=? ORDER BY FACDATE ";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,(curPage-1)*pagesize);
            ps.setInt(2,curPage*pagesize);
            rs=ps.executeQuery();
            while (rs.next()){
                ProductBean proBean=new ProductBean();
                proBean.setProid(rs.getInt("PROID"));
                proBean.setProname(rs.getString("PRONAME"));
                proBean.setPropic(rs.getString("PROPIC"));
                proBean.setPrice(rs.getDouble("PRICE"));
                proBean.setSprice(rs.getDouble("SPRICE"));
                proBean.setProfactory(rs.getString("PROFACTORY"));
                list.add(proBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return list;
    }

    public List<ProductBean> queryLike(int curPage,String name, int type) {
        connection= ConnBD.getConnection();
        ArrayList<ProductBean> list=new ArrayList<>();
        try {
            sql="SELECT PROID,PRONAME,PROPIC,PRICE,PRODESC,PROFACTORY FROM (SELECT p.*,rownum rn FROM T_PRODUCT p WHERE p.PRONAME LIKE ? AND TYPEID LIKE ?) WHERE rn>? AND rn<=? ORDER BY FACDATE DESC ";
            ps=connection.prepareStatement(sql);
            ps.setString(1,"%"+name+"%");
            if (type==0){
                ps.setString(2,"%%");
            }else {
                ps.setString(2,"%"+type+"%");
            }
            ps.setInt(3,(curPage-1)*pagesize);
            ps.setInt(4,curPage*pagesize);
            rs=ps.executeQuery();
            while (rs.next()){
                ProductBean proBean=new ProductBean();
                proBean.setProid(rs.getInt("PROID"));
                proBean.setProname(rs.getString("PRONAME"));
                proBean.setPropic(rs.getString("PROPIC"));
                proBean.setPrice(rs.getDouble("PRICE"));
                proBean.setProdesc(rs.getString("PRODESC"));
                proBean.setProfactory(rs.getString("PROFACTORY"));
                list.add(proBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return list;
    }
}
