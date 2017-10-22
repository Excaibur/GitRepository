package com.eshop.dao;

import com.eshop.util.ConnBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckDao {
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;

    public boolean chkRegName(String name){
        connection= ConnBD.getConnection();
        boolean flag=true;
        try {
            sql="SELECT * FROM T_MEMBER WHERE MEMLOGIN=?";
            ps=connection.prepareStatement(sql);
            ps.setString(1,name);
            rs=ps.executeQuery();
            if (rs.next()){
                flag=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return flag;
    }

    public boolean chkUserName(String name) {
        connection= ConnBD.getConnection();
        boolean flag=true;
        try {
            sql="SELECT * FROM T_MANGER WHERE MUSER=?";
            ps=connection.prepareStatement(sql);
            ps.setString(1,name);
            rs=ps.executeQuery();
            if (rs.next()){
                flag=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return flag;
    }

    public boolean chkCateName(String name) {
        connection= ConnBD.getConnection();
        boolean flag=true;
        try {
            sql="SELECT * FROM T_PROTYPE WHERE TYPENAME=?";
            ps=connection.prepareStatement(sql);
            ps.setString(1,name);
            rs=ps.executeQuery();
            if (rs.next()){
                flag=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return flag;
    }
}
