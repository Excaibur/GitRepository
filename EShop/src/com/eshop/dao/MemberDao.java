package com.eshop.dao;

import com.eshop.model.MemberBean;
import com.eshop.util.ConnBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;
    private int num;
    private int pagesize=5;
    private List<MemberBean> list;

    public int addMember(MemberBean mBean) {
        connection= ConnBD.getConnection();
        try {
            sql="INSERT INTO T_MEMBER(MEMID, MEMLOGIN, MEMPASS, MEMNAME, MEMPHONE, MEMADDRESS, MEMZIP, EMAIL, LVID, REGDATE) VALUES " +
                    "(MEMBER_SEQ.nextval,?,?,?,?,?,?,?,?,?)";
            ps=connection.prepareStatement(sql);
            ps.setString(1,mBean.getMemlogin());
            ps.setString(2,mBean.getMempass());
            ps.setString(3,mBean.getMemname());
            ps.setString(4,mBean.getMemphone());
            ps.setString(5,mBean.getMemaddress());
            ps.setString(6,mBean.getMemzip());
            ps.setString(7,mBean.getEmail());
            ps.setInt(8,mBean.getLvid());
            ps.setDate(9,new Date(mBean.getRegdate().getTime()));
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
            sql="SELECT COUNT(*) FROM T_MEMBER";
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

    public List<MemberBean> queryAll(int curPage){
        connection= ConnBD.getConnection();
        list=new ArrayList<>();
        try {
            sql="SELECT MEMID, MEMLOGIN, MEMNAME, MEMPHONE,LVNAME, REGDATE FROM (SELECT m.*,lv.LVNAME,rownum rn FROM T_MEMBER m,T_MEMLEVEL lv WHERE m.LVID=lv.LVID) WHERE rn>? AND rn<=?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,(curPage-1)*pagesize);
            ps.setInt(2,curPage*pagesize);
            rs=ps.executeQuery();
            while (rs.next()){
                MemberBean mBean=new MemberBean();
                mBean.setMemid(rs.getInt("MEMID"));
                mBean.setMemlogin(rs.getString("MEMLOGIN"));
                mBean.setMemname(rs.getString("MEMNAME"));
                mBean.setMemphone(rs.getString("MEMPHONE"));
                mBean.setLvname(rs.getString("LVNAME"));
                mBean.setRegdate(rs.getDate("REGDATE"));
                list.add(mBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return list;
    }

    public MemberBean queryById(int id) {
        connection= ConnBD.getConnection();
        MemberBean mBean=new MemberBean();
        try {
            sql="SELECT MEMID, MEMLOGIN, MEMPASS, MEMNAME, MEMPHONE, MEMADDRESS, MEMZIP, EMAIL, LVNAME, REGDATE,LOGINNUM,LOGINDATE " +
                    "FROM T_MEMBER m,T_MEMLEVEL lv WHERE MEMID=? AND m.LVID=lv.LVID";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            if (rs.next()){
                mBean.setMemid(rs.getInt("MEMID"));
                mBean.setMemlogin(rs.getString("MEMLOGIN"));
                mBean.setMempass(rs.getString("MEMPASS"));
                mBean.setMemname(rs.getString("MEMNAME"));
                mBean.setMemphone(rs.getString("MEMPHONE"));
                mBean.setMemaddress(rs.getString("MEMADDRESS"));
                mBean.setMemzip(rs.getString("MEMZIP"));
                mBean.setEmail(rs.getString("EMAIL"));
                mBean.setLvname(rs.getString("LVNAME"));
                mBean.setRegdate(rs.getDate("REGDATE"));
                mBean.setLoginnum(rs.getInt("LOGINNUM"));
                mBean.setLogindate(rs.getDate("LOGINDATE"));
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
            sql="DELETE FROM T_MEMBER WHERE MEMID=?";
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

    public int update(MemberBean mBean) {
        connection=ConnBD.getConnection();
        int i=0;
        try {
            sql="UPDATE T_MEMBER SET MEMLOGIN=?,MEMPASS=?,MEMNAME=?,MEMPHONE=?,MEMADDRESS=?,MEMZIP=?,EMAIL=? WHERE MEMID=?";
            ps=connection.prepareStatement(sql);
            ps.setString(1,mBean.getMemlogin());
            ps.setString(2,mBean.getMempass());
            ps.setString(3,mBean.getMemname());
            ps.setString(4,mBean.getMemphone());
            ps.setString(5,mBean.getMemaddress());
            ps.setString(6,mBean.getMemzip());
            ps.setString(7,mBean.getEmail());
            ps.setInt(8,mBean.getMemid());
            i=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return i;
    }

    public int updateLogin(int id,int num) {
        connection=ConnBD.getConnection();
        java.util.Date date=new java.util.Date();
        int i=0;
        try {
            sql="UPDATE T_MEMBER SET LOGINNUM=?,LOGINDATE=? WHERE MEMID=?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,num);
            ps.setDate(2,new Date(date.getTime()));
            ps.setInt(3,id);
            i=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return i;
    }

    public int updateLv(MemberBean mBean) {
        connection=ConnBD.getConnection();
        int i=0;
        try {
            sql="UPDATE T_MEMBER SET LVID=? WHERE MEMID=?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,mBean.getLvid());
            ps.setInt(2,mBean.getMemid());
            i=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return i;
    }
}
