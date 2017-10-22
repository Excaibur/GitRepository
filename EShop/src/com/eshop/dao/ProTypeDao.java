package com.eshop.dao;

import com.eshop.model.ProTypeBean;
import com.eshop.util.ConnBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProTypeDao {
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;
    private int pagesize = 5;
    private List<ProTypeBean> list;

    public int countPage() {
        connection = ConnBD.getConnection();
        int count = 0;
        try {
            sql = "SELECT COUNT(*) FROM T_PROTYPE";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnBD.closeConn(rs, ps, connection);
        }
        return count;
    }

    public int pageNum() {
        int count = countPage();
        int num = 0;
        if (count % pagesize == 0) {
            num = count / pagesize;
        } else {
            num = (count / pagesize) + 1;
        }
        return num;
    }

    public List<ProTypeBean> queryAll(int curPage) {
        connection = ConnBD.getConnection();
        list = new ArrayList<>();
        try {
            sql = "SELECT TYPEID,TYPENAME,TYPEDESC FROM (SELECT pt.*,rownum rn FROM T_PROTYPE pt) WHERE rn>? AND rn<=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (curPage - 1) * pagesize);
            ps.setInt(2, curPage * pagesize);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProTypeBean typeBean = new ProTypeBean();
                typeBean.setTypeid(rs.getInt("TYPEID"));
                typeBean.setTypename(rs.getString("TYPENAME"));
                typeBean.setTypedesc(rs.getString("TYPEDESC"));
                list.add(typeBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnBD.closeConn(rs, ps, connection);
        }
        return list;
    }

    public ProTypeBean queryById(int id) {
        connection = ConnBD.getConnection();
        ProTypeBean typeBean = new ProTypeBean();
        try {
            sql = "SELECT TYPEID,TYPENAME,TYPEDESC FROM T_PROTYPE WHERE TYPEID=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                typeBean.setTypeid(rs.getInt("TYPEID"));
                typeBean.setTypename(rs.getString("TYPENAME"));
                typeBean.setTypedesc(rs.getString("TYPEDESC"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnBD.closeConn(rs, ps, connection);
        }
        return typeBean;
    }

    public int delById(int id) {
        connection = ConnBD.getConnection();
        int i = 0;
        try {
            sql = "DELETE FROM T_PRODUCT WHERE TYPEID=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            i = ps.executeUpdate();
            sql = "DELETE FROM T_PROTYPE WHERE TYPEID=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnBD.closeConn(rs, ps, connection);
        }
        return i;
    }

    public int addUser(ProTypeBean typeBean) {
        connection = ConnBD.getConnection();
        int i = 0;
        try {
            sql = "INSERT INTO T_PROTYPE VALUES (MANAGE_SEQ.nextval,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, typeBean.getTypename());
            ps.setString(2, typeBean.getTypedesc());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnBD.closeConn(rs, ps, connection);
        }
        return i;
    }

    public int update(ProTypeBean typeBean) {
        connection = ConnBD.getConnection();
        int i = 0;
        try {
            sql = "UPDATE T_PROTYPE SET TYPENAME=?,TYPEDESC=? WHERE TYPEID=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, typeBean.getTypename());
            ps.setString(2, typeBean.getTypedesc());
            ps.setInt(3, typeBean.getTypeid());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnBD.closeConn(rs, ps, connection);
        }
        return i;
    }

    public List<ProTypeBean> query() {
        connection = ConnBD.getConnection();
        list = new ArrayList<>();
        try {
            sql = "SELECT TYPEID,TYPENAME FROM T_PROTYPE";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProTypeBean typeBean = new ProTypeBean();
                typeBean.setTypeid(rs.getInt("TYPEID"));
                typeBean.setTypename(rs.getString("TYPENAME"));
                list.add(typeBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnBD.closeConn(rs, ps, connection);
        }
        return list;
    }
}
