package com.eshop.dao;

import com.eshop.model.LevelWordBean;
import com.eshop.util.ConnBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LevelWordDao {
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;
    private int pagesize=10;
    private List<LevelWordBean> list;

    public int countPage(){
        connection= ConnBD.getConnection();
        int count=0;
        try {
            sql="SELECT COUNT(*) FROM T_WORD";
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

    public int addLevelWord(LevelWordBean lwBean) {
        connection= ConnBD.getConnection();
        int i=0;
        try {
            sql="INSERT INTO T_WORD(WID,WTITLE,WCONTENT,WDATE,MEMID,ISCONTENT) VALUES (WORD_SEQ.nextval,?,?,?,?,?)";
            ps=connection.prepareStatement(sql);
            ps.setString(1,lwBean.getWtitle());
            ps.setString(2,lwBean.getWcontent());
            ps.setDate(3,new Date(lwBean.getWdate().getTime()));
            ps.setInt(4,lwBean.getMemid());
            ps.setInt(5,lwBean.getIscontent());
            i=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return i;
    }

    public List<LevelWordBean> queryAll(int page) {
        connection= ConnBD.getConnection();
        list=new ArrayList<>();
        try {
            sql="SELECT WID,WTITLE,WCONTENT,WDATE,WMCONTENT,MEMLOGIN,ISCONTENT FROM (SELECT w.*,m.MEMLOGIN,rownum rn FROM T_WORD w,T_MEMBER m WHERE w.MEMID=m.MEMID) WHERE rn>? AND rn<=?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,(page-1)*pagesize);
            ps.setInt(2,page*pagesize);
            rs=ps.executeQuery();
            while (rs.next()){
                LevelWordBean lwordBean=new LevelWordBean();
                lwordBean.setWid(rs.getInt("WID"));
                lwordBean.setWtitle(rs.getString("WTITLE"));
                lwordBean.setWcontent(rs.getString("WCONTENT"));
                lwordBean.setWdate(rs.getDate("WDATE"));
                lwordBean.setWmcontent(rs.getString("WMCONTENT"));
                lwordBean.setMemlogin(rs.getString("MEMLOGIN"));
                lwordBean.setIscontent(rs.getInt("ISCONTENT"));
                list.add(lwordBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return list;
    }

    public LevelWordBean queryById(int id) {
        connection= ConnBD.getConnection();
        LevelWordBean lwBean=new LevelWordBean();
        try {
            sql="SELECT WID,WTITLE,WCONTENT,WMCONTENT,MEMLOGIN,ISCONTENT FROM (SELECT w.*,m.MEMLOGIN,rownum rn FROM T_WORD w,T_MEMBER m) WHERE WID=?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            if (rs.next()){
                lwBean.setWid(rs.getInt("WID"));
                lwBean.setWtitle(rs.getString("WTITLE"));
                lwBean.setWcontent(rs.getString("WCONTENT"));
                lwBean.setWmcontent(rs.getString("WMCONTENT"));
                lwBean.setMemlogin(rs.getString("MEMLOGIN"));
                lwBean.setIscontent(rs.getInt("ISCONTENT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return lwBean;
    }

    public int delById(int id) {
        connection= ConnBD.getConnection();
        int i=0;
        try {
            sql="DELETE FROM T_WORD WHERE WID=?";
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

    public int uploadById(int id, String answerContent) {
        connection= ConnBD.getConnection();
        int i=0;
        try {
            sql="UPDATE T_WORD SET WMCONTENT=?,ISCONTENT=? WHERE WID=?";
            ps=connection.prepareStatement(sql);
            ps.setString(1,answerContent);
            ps.setInt(2,1);
            ps.setInt(3,id);
            i=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return i;
    }
}
