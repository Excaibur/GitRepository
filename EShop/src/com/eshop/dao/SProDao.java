package com.eshop.dao;

import com.eshop.model.ProductBean;
import com.eshop.util.ConnBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SProDao {
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;
    private int num;
    private int pagesize=5;
    private List<ProductBean> list;

    public int countPage(){
        connection= ConnBD.getConnection();
        int count=0;
        try {
            sql="SELECT COUNT(*) FROM T_PRODUCT WHERE SPRICE != 0.0";
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
            sql="SELECT PROID,PRONAME,PROMODEL,PROPIC,PRICE FROM (SELECT p.*,rownum rn FROM T_PRODUCT p WHERE p.SPRICE != 0.0) WHERE rn>? AND rn<=?";
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
}
