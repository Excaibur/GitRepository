package com.eshop.dao;

import com.eshop.model.MemberBean;
import com.eshop.util.ConnBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;

    public MemberBean defLogin(String name, String pwd){
        connection= ConnBD.getConnection();
        MemberBean mBean=null;
        try {
            sql="SELECT m.MEMID, m.MEMLOGIN, m.MEMPASS, m.MEMNAME, m.MEMPHONE, m.MEMADDRESS, m.MEMZIP, m.EMAIL, lv.LVNAME,m.LVID,m.LOGINNUM" +
                    " FROM T_MEMBER m,T_MEMLEVEL lv WHERE m.LVID=lv.LVID AND m.MEMLOGIN=? AND m.MEMPASS=?";
            ps=connection.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,pwd);
            rs=ps.executeQuery();
            if (rs.next()){
                int id=rs.getInt("MEMID");
                int num=rs.getInt("LOGINNUM")+1;
                int i=new MemberDao().updateLogin(id,num);
                if (i>0){
                    mBean=new MemberBean();
                    mBean.setMemid(rs.getInt("MEMID"));
                    mBean.setMemlogin(rs.getString("MEMLOGIN"));
                    mBean.setMempass(rs.getString("MEMPASS"));
                    mBean.setMemname(rs.getString("MEMNAME"));
                    mBean.setMemphone(rs.getString("MEMPHONE"));
                    mBean.setMemaddress(rs.getString("MEMADDRESS"));
                    mBean.setMemzip(rs.getString("MEMZIP"));
                    mBean.setEmail(rs.getString("EMAIL"));
                    mBean.setLvname(rs.getString("LVNAME"));
                    mBean.setLvid(rs.getInt("LVID"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return mBean;
    }

    public int adminLogin(String name, String pwd) {
        connection= ConnBD.getConnection();
        int status=5;
        try {
            sql="SELECT MSTATUS FROM T_MANGER WHERE MUSER=? AND MPASSWORD=?";
            ps=connection.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,pwd);
            rs=ps.executeQuery();
            if (rs.next()){
                status=rs.getInt("mstatus");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return status;
    }
}
