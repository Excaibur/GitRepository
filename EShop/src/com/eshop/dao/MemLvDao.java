package com.eshop.dao;

import com.eshop.model.MemLvBean;
import com.eshop.util.ConnBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemLvDao {
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;
    private List<MemLvBean> list;

    public List<MemLvBean> queryAll(){
        connection= ConnBD.getConnection();
        list=new ArrayList<>();
        try {
            sql="SELECT LVID,LVNAME,LVPERCENT FROM T_MEMLEVEL";
            ps=connection.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                MemLvBean lvBean=new MemLvBean();
                lvBean.setLvid(rs.getInt("LVID"));
                lvBean.setLvname(rs.getString("LVNAME"));
                lvBean.setLvpercent(rs.getDouble("LVPERCENT"));
                list.add(lvBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return list;
    }

    public MemLvBean queryById(int id){
        connection= ConnBD.getConnection();
        MemLvBean lvBean=new MemLvBean();
        try {
            sql="SELECT LVID,LVNAME,LVPERCENT FROM T_MEMLEVEL WHERE LVID=?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            if (rs.next()){
                lvBean.setLvid(rs.getInt("LVID"));
                lvBean.setLvname(rs.getString("LVNAME"));
                lvBean.setLvpercent(rs.getDouble("LVPERCENT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return lvBean;
    }

    public MemLvBean queryByMemId(int id){
        connection= ConnBD.getConnection();
        MemLvBean lvBean=new MemLvBean();
        try {
            sql="SELECT LVNAME,LVPERCENT FROM T_MEMLEVEL lv WHERE lv.LVID=(SELECT LVID FROM T_MEMBER WHERE MEMID=?)";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            if (rs.next()){
                lvBean.setLvname(rs.getString("LVNAME"));
                lvBean.setLvpercent(rs.getDouble("LVPERCENT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return lvBean;
    }
}
