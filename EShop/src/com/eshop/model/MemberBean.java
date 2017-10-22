package com.eshop.model;

import java.util.Date;

public class MemberBean {
    private int memid;
    private String memlogin;
    private String mempass;
    private String memname;
    private String memphone;
    private String memaddress;
    private String memzip;
    private String email;
    private int lvid;
    private Date regdate;
    private int loginnum;
    private Date logindate;
    private String lvname;

    public int getMemid() {
        return memid;
    }

    public void setMemid(int memid) {
        this.memid = memid;
    }

    public String getMemlogin() {
        return memlogin;
    }

    public void setMemlogin(String memlogin) {
        this.memlogin = memlogin;
    }

    public String getMempass() {
        return mempass;
    }

    public void setMempass(String mempass) {
        this.mempass = mempass;
    }

    public String getMemname() {
        return memname;
    }

    public void setMemname(String memname) {
        this.memname = memname;
    }

    public String getMemphone() {
        return memphone;
    }

    public void setMemphone(String memphone) {
        this.memphone = memphone;
    }

    public String getMemaddress() {
        return memaddress;
    }

    public void setMemaddress(String memaddress) {
        this.memaddress = memaddress;
    }

    public String getMemzip() {
        return memzip;
    }

    public void setMemzip(String memzip) {
        this.memzip = memzip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLvid() {
        return lvid;
    }

    public void setLvid(int lvid) {
        this.lvid = lvid;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public int getLoginnum() {
        return loginnum;
    }

    public void setLoginnum(int loginnum) {
        this.loginnum = loginnum;
    }

    public Date getLogindate() {
        return logindate;
    }

    public void setLogindate(Date logindate) {
        this.logindate = logindate;
    }

    public String getLvname() {
        return lvname;
    }

    public void setLvname(String lvname) {
        this.lvname = lvname;
    }
}
