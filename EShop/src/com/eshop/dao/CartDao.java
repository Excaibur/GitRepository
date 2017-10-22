package com.eshop.dao;

import com.eshop.model.CartBean;
import com.eshop.model.ProductBean;
import com.eshop.util.ConnBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;
    private List<CartBean> list;

    public List<CartBean> queryAll(int mid) {
        connection = ConnBD.getConnection();
        list = new ArrayList<>();
        try {
            sql = "SELECT CID,MEMID,PRONAME,PRONUM,PRICE,SPRICE,MONEY,ISORDER,PROID,PRECENT FROM T_CART WHERE MEMID=? AND ISORDER=0";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, mid);
            rs = ps.executeQuery();
            while (rs.next()) {
                CartBean cBean = new CartBean();
                cBean.setCid(rs.getInt("CID"));
                cBean.setMemid(rs.getInt("MEMID"));
                cBean.setProname(rs.getString("PRONAME"));
                cBean.setPronum(rs.getInt("PRONUM"));
                cBean.setPrice(rs.getDouble("PRICE"));
                cBean.setSprice(rs.getDouble("SPRICE"));
                cBean.setMoney(rs.getDouble("MONEY"));
                cBean.setIsorder(rs.getInt("ISORDER"));
                cBean.setProid(rs.getInt("PROID"));
                cBean.setPrecent(rs.getDouble("PRECENT"));
                list.add(cBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnBD.closeConn(rs, ps, connection);
        }
        return list;
    }

    public int delById(int id) {
        connection = ConnBD.getConnection();
        int i = 0;
        try {
            sql = "DELETE FROM T_CART WHERE CID=? AND ISORDER=0";
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

    public int addCart(int mid, ProductBean pBean, double precent) {
        connection = ConnBD.getConnection();
        int i = 0;
        try {
            sql = "INSERT INTO T_CART VALUES (CART_SEQ.nextval,?,?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, mid);
            ps.setString(2, pBean.getProname());
            ps.setInt(3, 1);
            ps.setDouble(4, pBean.getPrice());
            ps.setDouble(5, pBean.getSprice());
            if (pBean.getSprice() != 0.0) {
                ps.setDouble(6, pBean.getSprice() * precent);
            } else {
                ps.setDouble(6, pBean.getPrice() * precent);
            }
            ps.setInt(7, 0);
            ps.setInt(8, pBean.getProid());
            ps.setDouble(9, precent);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnBD.closeConn(rs, ps, connection);
        }
        return i;
    }

    public CartBean queryById(int mid, int proid) {
        connection = ConnBD.getConnection();
        int num = 0;
        CartBean cartBean = new CartBean();
        try {
            sql = "SELECT CID,MEMID,PRONUM,PRICE,SPRICE,MONEY,PROID,PRECENT FROM T_CART WHERE MEMID=? AND PROID=? AND ISORDER=0";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, mid);
            ps.setInt(2, proid);
            rs = ps.executeQuery();
            if (rs.next()) {
                cartBean.setCid(rs.getInt("CID"));
                cartBean.setMemid(rs.getInt("MEMID"));
                cartBean.setPronum(rs.getInt("PRONUM"));
                cartBean.setProid(rs.getInt("PROID"));
                cartBean.setPrice(rs.getDouble("PRICE"));
                cartBean.setSprice(rs.getDouble("SPRICE"));
                cartBean.setMoney(rs.getDouble("MONEY"));
                cartBean.setPrecent(rs.getDouble("PRECENT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnBD.closeConn(rs, ps, connection);
        }
        return cartBean;
    }

    public int update(int mid, int proid, int num, double precent) {
        connection = ConnBD.getConnection();
        int i = 0;
        try {
            sql = "SELECT PRICE,SPRICE FROM T_CART WHERE MEMID=? AND PROID=? AND ISORDER=0";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, mid);
            ps.setInt(2, proid);
            rs = ps.executeQuery();
            if (rs.next()) {
                sql = "UPDATE T_CART SET PRONUM=?,MONEY=? WHERE MEMID=? AND PROID=?";
                ps = connection.prepareStatement(sql);
                ps.setInt(1, num);
                if (rs.getDouble("SPRICE") != 0.0) {
                    ps.setDouble(2, rs.getDouble("SPRICE") * precent * (num));
                } else {
                    ps.setDouble(2, rs.getDouble("PRICE") * precent * (num));
                }
                ps.setInt(3, mid);
                ps.setInt(4, proid);
                i = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnBD.closeConn(rs, ps, connection);
        }
        return i;
    }

    public double queryLv(int mid) {
        connection = ConnBD.getConnection();
        double num = 0;
        try {
            sql = "SELECT lv.LVPERCENT FROM (SELECT LVID FROM T_MEMBER WHERE MEMID=?) m,T_MEMLEVEL lv WHERE m.LVID=lv.LVID";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, mid);
            rs = ps.executeQuery();
            if (rs.next()) {
                num = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnBD.closeConn(rs, ps, connection);
        }
        return num;
    }

    public int delAll(int mid) {
        connection = ConnBD.getConnection();
        int i = 0;
        try {
            sql = "DELETE FROM T_CART WHERE MEMID=? AND ISORDER=0";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, mid);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnBD.closeConn(rs, ps, connection);
        }
        return i;
    }

    public double totalMoney(List<CartBean> list) {
        double money = 0;
        for (CartBean cb :
                list) {
            money += cb.getMoney();
        }
        return money;
    }

    public int updateStatus(int memid) {
        connection = ConnBD.getConnection();
        int i = 0;
        try {
            sql = "UPDATE T_CART SET ISORDER=? WHERE MEMID=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2, memid);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnBD.closeConn(rs, ps, connection);
        }
        return i;
    }
}
