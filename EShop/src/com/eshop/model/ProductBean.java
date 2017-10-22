package com.eshop.model;

import java.util.Date;

public class ProductBean {
    private int proid;
    private String proname;
    private int typeid;
    private String typename;
    private String promodel;
    private String propic;
    private double price;
    private double sprice;
    private String prodesc;
    private String profactory;
    private Date facdate;
    private Date regdate;

    public int getProid() {
        return proid;
    }

    public void setProid(int proid) {
        this.proid = proid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getPromodel() {
        return promodel;
    }

    public void setPromodel(String promodel) {
        this.promodel = promodel;
    }

    public String getPropic() {
        return propic;
    }

    public void setPropic(String propic) {
        this.propic = propic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSprice() {
        return sprice;
    }

    public void setSprice(double sprice) {
        this.sprice = sprice;
    }

    public String getProdesc() {
        return prodesc;
    }

    public void setProdesc(String prodesc) {
        this.prodesc = prodesc;
    }

    public String getProfactory() {
        return profactory;
    }

    public void setProfactory(String profactory) {
        this.profactory = profactory;
    }

    public Date getFacdate() {
        return facdate;
    }

    public void setFacdate(Date facdate) {
        this.facdate = facdate;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
}
