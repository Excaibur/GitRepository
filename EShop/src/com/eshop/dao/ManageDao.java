package com.eshop.dao;

import com.eshop.model.ManageBean;
import com.eshop.util.ConnBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageDao {
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;
    private int pagesize=5;
    private List<ManageBean> list;

    public int countPage(){
        connection= ConnBD.getConnection();
        int count=0;
        try {
            sql="SELECT COUNT(*) FROM T_MANGER";
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

    public List<ManageBean> queryAll(int curPage){
        connection= ConnBD.getConnection();
        list=new ArrayList<>();
        try {
            sql="SELECT MID,MUSER,MPASSWORD,MNAME,MSTATUS FROM (SELECT m.*,rownum rn FROM T_MANGER m) WHERE rn>? AND rn<=?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,(curPage-1)*pagesize);
            ps.setInt(2,curPage*pagesize);
            rs=ps.executeQuery();
            while (rs.next()){
                ManageBean mBean=new ManageBean();
                mBean.setMid(rs.getInt("mid"));
                mBean.setMuser(rs.getString("muser"));
                mBean.setMpassword(rs.getString("MPASSWORD"));
                mBean.setMname(rs.getString("MNAME"));
                mBean.setMstatus(rs.getInt("MSTATUS"));
                list.add(mBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return list;
    }

    public ManageBean queryById(int id) {
        connection= ConnBD.getConnection();
        ManageBean mBean=new ManageBean();
        try {
            sql="SELECT MID,MUSER,MPASSWORD,MNAME,MSTATUS FROM T_MANGER WHERE MID=?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            if (rs.next()){
                mBean.setMid(rs.getInt("mid"));
                mBean.setMuser(rs.getString("muser"));
                mBean.setMpassword(rs.getString("MPASSWORD"));
                mBean.setMname(rs.getString("MNAME"));
                mBean.setMstatus(rs.getInt("MSTATUS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return mBean;
    }

    public int delById(int id) {
        connection=ConnBD.getConnection();
        int i=0;
        try {
            sql="DELETE FROM T_MANGER WHERE MID=?";
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

    public int addUser(ManageBean mBean) {
        connection=ConnBD.getConnection();
        int i=0;
        try {
            sql="INSERT INTO T_MANGER VALUES (MANAGE_SEQ.nextval,?,?,?,?)";
            ps=connection.prepareStatement(sql);
            ps.setString(1,mBean.getMuser());
            ps.setString(2,mBean.getMpassword());
            ps.setString(3,mBean.getMname());
            ps.setInt(4,mBean.getMstatus());
            i=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return i;
    }

    public int update(ManageBean mBean) {
        connection=ConnBD.getConnection();
        int i=0;
        try {
            sql="UPDATE T_MANGER SET MUSER=?,MPASSWORD=?,MNAME=?,MSTATUS=? WHERE MID=?";
            ps=connection.prepareStatement(sql);
            ps.setString(1,mBean.getMuser());
            ps.setString(2,mBean.getMpassword());
            ps.setString(3,mBean.getMname());
            ps.setInt(4,mBean.getMstatus());
            ps.setInt(5,mBean.getMid());
            i=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return i;
    }
}
