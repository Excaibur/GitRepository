package com.eshop.dao;

import com.eshop.model.ProductBean;
import com.eshop.util.ConnBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;
    private int num;
    private int pagesize=5;
    private List<ProductBean> list;

    public int addProduct(ProductBean proBean) {
        connection= ConnBD.getConnection();
        try {
            java.util.Date date=new java.util.Date();
            sql="INSERT INTO T_PRODUCT(PROID,TYPEID,PRONAME,PROMODEL,PROPIC,PRICE,SPRICE,PRODESC,PROFACTORY,FACDATE,REGDATE) VALUES " +
                    "(PRODUCT_SEQ.nextval,?,?,?,?,?,?,?,?,?,?)";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,proBean.getTypeid());
            ps.setString(2,proBean.getProname());
            ps.setString(3,proBean.getPromodel());
            ps.setString(4,proBean.getPropic());
            ps.setDouble(5,proBean.getPrice());
            ps.setDouble(6,proBean.getSprice());
            ps.setString(7,proBean.getProdesc());
            ps.setString(8,proBean.getProfactory());
            ps.setDate(9,new Date(proBean.getFacdate().getTime()));
            ps.setDate(10,new Date(date.getTime()));
            num=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return num;
    }

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

    public int pageNum(){
        int count=countPage();
        int num=0;
        if (count%pagesize==0){
            num=count/pagesize;
        }else {
            num=(count/pagesize)+1;
        }
        return num;
    }

    public List<ProductBean> queryAll(int curPage){
        connection= ConnBD.getConnection();
        list=new ArrayList<>();
        try {
            sql="SELECT PROID,PRONAME,PROMODEL,PROPIC,PRICE FROM (SELECT p.*,rownum rn FROM T_PRODUCT p) WHERE rn>? AND rn<=?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,(curPage-1)*pagesize);
            ps.setInt(2,curPage*pagesize);
            rs=ps.executeQuery();
            while (rs.next()){
                ProductBean proBean=new ProductBean();
                proBean.setProid(rs.getInt("PROID"));
                proBean.setProname(rs.getString("PRONAME"));
                proBean.setPromodel(rs.getString("PROMODEL"));
                proBean.setPropic(rs.getString("PROPIC"));
                proBean.setPrice(rs.getDouble("PRICE"));
                list.add(proBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return list;
    }

    public ProductBean queryById(int id) {
        connection= ConnBD.getConnection();
        ProductBean proBean=new ProductBean();
        try {
            sql="SELECT PROID,p.TYPEID,t.TYPENAME,PRONAME,PROMODEL,PROPIC,PRICE,SPRICE,PRODESC,PROFACTORY,FACDATE,REGDATE " +
                    "FROM T_PRODUCT p,T_PROTYPE t WHERE p.TYPEID=t.TYPEID AND PROID=?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            if (rs.next()){
                proBean.setProid(rs.getInt("PROID"));
                proBean.setTypeid(rs.getInt("TYPEID"));
                proBean.setTypename(rs.getString("TYPENAME"));
                proBean.setProname(rs.getString("PRONAME"));
                proBean.setPromodel(rs.getString("PROMODEL"));
                proBean.setPrice(rs.getDouble("PRICE"));
                proBean.setSprice(rs.getDouble("SPRICE"));
                proBean.setPropic(rs.getString("PROPIC"));
                proBean.setProdesc(rs.getString("PRODESC"));
                proBean.setProfactory(rs.getString("PROFACTORY"));
                proBean.setFacdate(rs.getDate("FACDATE"));
                proBean.setRegdate(rs.getDate("REGDATE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return proBean;
    }

    public int delById(int id) {
        connection=ConnBD.getConnection();
        int i=0;
        try {
            sql="DELETE FROM T_PRODUCT WHERE PROID=?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            i=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return i;
    }

    public int update(ProductBean proBean) {
        connection=ConnBD.getConnection();
        int i=0;
        try {
            sql="UPDATE T_PRODUCT SET TYPEID=?,PRONAME=?,PROMODEL=?,PROPIC=?,PRICE=?,SPRICE=?,PRODESC=?,PROFACTORY=?,FACDATE=? WHERE PROID=?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,proBean.getTypeid());
            ps.setString(2,proBean.getProname());
            ps.setString(3,proBean.getPromodel());
            ps.setString(4,proBean.getPropic());
            ps.setDouble(5,proBean.getPrice());
            ps.setDouble(6,proBean.getSprice());
            ps.setString(7,proBean.getProdesc());
            ps.setString(8,proBean.getProfactory());
            ps.setDate(9,new Date(proBean.getFacdate().getTime()));
            ps.setInt(10,proBean.getProid());
            i=ps.executeUpdate();
            if (i>0){
//                删除原上传图片
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return i;
    }
}
