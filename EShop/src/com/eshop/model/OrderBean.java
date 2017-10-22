package com.eshop.model;

import java.util.Date;
import java.util.List;

public class OrderBean {
    private long orderid;
    private double ordermoney;
    private Date orderdate;
    private int memid;
    private String ordername;
    private String orderphone;
    private String orderzip;
    private String orderaddress;
    private int orderstatus;
    private String lvname;
    private double precent;
    private List<OrderInfoBean> infoList;

    public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public double getOrdermoney() {
        return ordermoney;
    }

    public void setOrdermoney(double ordermoney) {
        this.ordermoney = ordermoney;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public int getMemid() {
        return memid;
    }

    public void setMemid(int memid) {
        this.memid = memid;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public String getOrderphone() {
        return orderphone;
    }

    public void setOrderphone(String orderphone) {
        this.orderphone = orderphone;
    }

    public String getOrderzip() {
        return orderzip;
    }

    public void setOrderzip(String orderzip) {
        this.orderzip = orderzip;
    }

    public String getOrderaddress() {
        return orderaddress;
    }

    public void setOrderaddress(String orderaddress) {
        this.orderaddress = orderaddress;
    }

    public int getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(int orderstatus) {
        this.orderstatus = orderstatus;
    }

    public List<OrderInfoBean> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<OrderInfoBean> infoList) {
        this.infoList = infoList;
    }

    public double getPrecent() {
        return precent;
    }

    public void setPrecent(double precent) {
        this.precent = precent;
    }

    public String getLvname() {
        return lvname;
    }

    public void setLvname(String lvname) {
        this.lvname = lvname;
    }
}
