package com.eshop.dao;

import com.eshop.model.CartBean;
import com.eshop.model.MemberBean;
import com.eshop.model.OrderBean;
import com.eshop.model.OrderInfoBean;
import com.eshop.util.ConnBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class OrderDao {
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;
    private String sql;
    private List<CartBean> cart;
    private List<OrderBean> order;
    private int pagesize=5;

    /**
     * 产生随机唯一订单号码
     *
     * @return
     */
    public long getOrderId(MemberBean memberBean) {
        String datestr = ((Long) new Date().getTime()).toString();
        int index = datestr.length();
        Long date = Long.parseLong(datestr.substring(index - 6, index)) * 10000;
        Long rand = (new Random().nextInt(10000) * 1000000000000l);
        Long oid = rand + date + memberBean.getMemid();
        return oid;
    }

    public double addOrder(OrderBean orderBean, List<CartBean> cartList) {
        connection = ConnBD.getConnection();
        int i = 0;
        double total = 0;
        try {
            for (CartBean cb :
                    cartList) {
                sql = "INSERT INTO T_ORDERINFO VALUES (?,?,?,?,?,?)";
                ps = connection.prepareStatement(sql);
                ps.setLong(1, orderBean.getOrderid());
                ps.setString(2, cb.getProname());
                ps.setInt(3, cb.getPronum());
                ps.setDouble(4, cb.getPrice());
                ps.setDouble(5, cb.getSprice());
                ps.setDouble(6, cb.getMoney());
                ps.executeUpdate();
                total += cb.getMoney();
            }
            sql = "INSERT INTO T_ORDER VALUES (?,?,?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, orderBean.getOrderid());
            ps.setDouble(2, total);
            ps.setDate(3, new java.sql.Date(orderBean.getOrderdate().getTime()));
            ps.setInt(4, orderBean.getMemid());
            ps.setString(5, orderBean.getOrdername());
            ps.setString(6, orderBean.getOrderphone());
            ps.setString(7, orderBean.getOrderzip());
            ps.setString(8, orderBean.getOrderaddress());
            ps.setInt(9, orderBean.getOrderstatus());
            i = ps.executeUpdate();
            if (i>0){
                i=new CartDao().updateStatus(orderBean.getMemid());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnBD.closeConn(rs, ps, connection);
        }
        if (i>0){
            return total;
        }else {
            return i;
        }
    }

    public List<OrderBean> getOrderById(int id){
        connection=ConnBD.getConnection();
        order=new ArrayList<>();
        try {
            sql="SELECT ORDERID,ORDERMONEY,ORDERDATE,ORDERSTATUS FROM T_ORDER WHERE MEMID=?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while (rs.next()){
                OrderBean oBean=new OrderBean();
                oBean.setOrderid(rs.getLong("ORDERID"));
                oBean.setOrdermoney(rs.getDouble("ORDERMONEY"));
                oBean.setOrderdate(rs.getDate("ORDERDATE"));
                oBean.setOrderstatus(rs.getInt("ORDERSTATUS"));
                order.add(oBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return order;
    }

    public OrderBean getOrderInfoById(long oid) {
        connection=ConnBD.getConnection();
        OrderBean oBean=new OrderBean();
        try {
            sql="SELECT ORDERID,MEMID,ORDERMONEY,ORDERDATE,ORDERNAME,ORDERPHONE,ORDERZIP,ORDERADDRESS FROM T_ORDER WHERE ORDERID=?";
            ps=connection.prepareStatement(sql);
            ps.setLong(1,oid);
            rs=ps.executeQuery();
            if (rs.next()){
                oBean.setOrderid(rs.getLong("ORDERID"));
                oBean.setMemid(rs.getInt("MEMID"));
                oBean.setOrdermoney(rs.getDouble("ORDERMONEY"));
                oBean.setOrderdate(rs.getDate("ORDERDATE"));
                oBean.setOrdername(rs.getString("ORDERNAME"));
                oBean.setOrderphone(rs.getString("ORDERPHONE"));
                oBean.setOrderzip(rs.getString("ORDERZIP"));
                oBean.setOrderaddress(rs.getString("ORDERADDRESS"));
                List<OrderInfoBean> infolist=new ArrayList<>();
                sql="SELECT ORDERID,PRONAME,PRONUM,PRICE,SPRICE,MONEY FROM T_ORDERINFO WHERE ORDERID=?";
                ps=connection.prepareStatement(sql);
                ps.setLong(1,oid);
                rs=ps.executeQuery();
                while (rs.next()){
                    OrderInfoBean infoBean=new OrderInfoBean();
                    infoBean.setOrderid(rs.getLong("ORDERID"));
                    infoBean.setProname(rs.getString("PRONAME"));
                    infoBean.setPronum(rs.getInt("PRONUM"));
                    infoBean.setPrice(rs.getDouble("PRICE"));
                    infoBean.setSprice(rs.getDouble("SPRICE"));
                    infoBean.setMoney(rs.getDouble("MONEY"));
                    infolist.add(infoBean);
                }
                oBean.setInfoList(infolist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return oBean;
    }

    public int delOrderById(Long oid) {
        connection=ConnBD.getConnection();
        int i=0;
        try {
            sql="DELETE FROM T_ORDERINFO WHERE ORDERID=?";
            ps=connection.prepareStatement(sql);
            ps.setLong(1,oid);
            i=ps.executeUpdate();
            if (i>0){
                sql="DELETE FROM T_ORDER WHERE ORDERID=? AND ORDERSTATUS=3";
                ps=connection.prepareStatement(sql);
                ps.setLong(1,oid);
                i=ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return i;
    }

    public int countPage(){
        connection= ConnBD.getConnection();
        int count=0;
        try {
            sql="SELECT COUNT(*) FROM T_ORDER";
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

    public List<OrderBean> getOrderAll(int page) {
        connection= ConnBD.getConnection();
        order=new ArrayList<>();
        try {
            sql="SELECT ORDERID,ORDERMONEY,ORDERDATE,ORDERSTATUS FROM (SELECT o.*,rownum rn FROM T_ORDER o) WHERE rn>? AND rn<=?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1,(page-1)*pagesize);
            ps.setInt(2,page*pagesize);
            rs=ps.executeQuery();
            while (rs.next()){
                OrderBean oBean=new OrderBean();
                oBean.setOrderid(rs.getLong("ORDERID"));
                oBean.setOrdermoney(rs.getDouble("ORDERMONEY"));
                oBean.setOrderdate(rs.getDate("ORDERDATE"));
                oBean.setOrderstatus(rs.getInt("ORDERSTATUS"));
                order.add(oBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return order;
    }

    public int updateOrderStatus(Long oid) {
        connection= ConnBD.getConnection();
        int i=0;
        try {
            sql="SELECT ORDERSTATUS FROM T_ORDER WHERE ORDERID=?";
            ps=connection.prepareStatement(sql);
            ps.setLong(1,oid);
            rs=ps.executeQuery();
            if (rs.next()){
                i=rs.getInt(1);
                if (i<3){
                    sql="UPDATE T_ORDER SET ORDERSTATUS=? WHERE ORDERID=?";
                    ps=connection.prepareStatement(sql);
                    ps.setInt(1,i+1);
                    ps.setLong(2,oid);
                    i=ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnBD.closeConn(rs,ps,connection);
        }
        return i;
    }
}
