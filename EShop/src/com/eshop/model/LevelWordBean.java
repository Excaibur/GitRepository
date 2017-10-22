package com.eshop.model;

import java.util.Date;

public class LevelWordBean {
    private int wid;
    private String wtitle;
    private String wcontent;
    private int memid;
    private Date wdate;
    private String wmcontent;
    private String memlogin;
    private int iscontent;

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public String getWtitle() {
        return wtitle;
    }

    public void setWtitle(String wtitle) {
        this.wtitle = wtitle;
    }

    public String getWcontent() {
        return wcontent;
    }

    public void setWcontent(String wcontent) {
        this.wcontent = wcontent;
    }

    public int getMemid() {
        return memid;
    }

    public void setMemid(int memid) {
        this.memid = memid;
    }

    public Date getWdate() {
        return wdate;
    }

    public void setWdate(Date wdate) {
        this.wdate = wdate;
    }

    public String getWmcontent() {
        return wmcontent;
    }

    public void setWmcontent(String wmcontent) {
        this.wmcontent = wmcontent;
    }

    public int getIscontent() {
        return iscontent;
    }

    public void setIscontent(int iscontent) {
        this.iscontent = iscontent;
    }

    public String getMemlogin() {
        return memlogin;
    }

    public void setMemlogin(String memlogin) {
        this.memlogin = memlogin;
    }
}
