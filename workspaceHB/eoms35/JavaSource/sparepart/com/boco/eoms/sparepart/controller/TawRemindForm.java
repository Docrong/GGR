package com.boco.eoms.sparepart.controller;


import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawRemindForm extends BaseForm implements java.io.Serializable{
    private String department;
    private String necode;
    private String objectname;
    private String storageid;
    private String upperlimit;
    private String lowerlimit;
    private int type;
    private String limitdate;
    public TawRemindForm(){
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getNecode() {
        return necode;
    }
    public void setNecode(String necode) {
        this.necode = necode;
    }
    public String getObjectname() {
        return objectname;
    }
    public void setObjectname(String objectname) {
        this.objectname = objectname;
    }
    public String getStorageid() {
        return storageid;
    }
    public void setStorageid(String storageid) {
        this.storageid = storageid;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getUpperlimit() {
        return upperlimit;
    }
    public void setUpperlimit(String upperlimit) {
        this.upperlimit = upperlimit;
    }
    public String getLowerlimit() {
        return lowerlimit;
    }
    public void setLowerlimit(String lowerlimit) {
        this.lowerlimit = lowerlimit;
    }
    public String getLimitdate() {
        return limitdate;
    }
    public void setLimitdate(String limitdate) {
        this.limitdate = limitdate;
    }

}