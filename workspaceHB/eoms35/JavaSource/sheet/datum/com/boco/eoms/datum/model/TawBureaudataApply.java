package com.boco.eoms.datum.model;

import java.io.Serializable;

public class TawBureaudataApply implements Serializable {


    private java.lang.String id;

    private java.lang.String city;
    private java.lang.String worktype;
    private java.lang.String year;
    private java.lang.String flowid;

    public java.lang.String getCity() {
        return city;
    }

    public void setCity(java.lang.String city) {
        this.city = city;
    }

    public java.lang.String getFlowid() {
        return flowid;
    }

    public void setFlowid(java.lang.String flowid) {
        this.flowid = flowid;
    }

    public java.lang.String getWorktype() {
        return worktype;
    }

    public void setWorktype(java.lang.String worktype) {
        this.worktype = worktype;
    }

    public java.lang.String getYear() {
        return year;
    }

    public void setYear(java.lang.String year) {
        this.year = year;
    }

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }


}
