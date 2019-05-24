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

public class TawClassMsgForm extends BaseForm implements java.io.Serializable{

    private String id;
    private String cname;
    private String note;
    private int strutsAction;
    private String parentId;
    private String net;
    private String orderId;
    private String orderType;
    private String nettype;
    private String necode;
    private String objectname;
    private String department;
    private String sql;
    private String deleted;
    private String contract;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getCname(){
        return cname;
    }

    public void setCname(String cname){
        this.cname=cname;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note=note;
    }

    public int getStrutsAction(){
        return strutsAction;
    }

    public void setStrutsAction(int strutsAction){
        this.strutsAction=strutsAction;
    }

    public String getParentId(){
        return parentId;
    }

    public void setParentId(String parentId){
        this.parentId=parentId;
    }

    public String getNet(){
        return net;
    }

    public void setNet(String net){
        this.net=net;
    }

    public String getOrderId(){
        return orderId;
    }

    public void setOrderId(String orderId){
        this.orderId=orderId;
    }

    public String getOrderType(){
        return orderType;
    }

    public void setOrderType(String orderType){
        this.orderType=orderType;
    }
    public String getNettype() {
        return nettype;
    }
    public void setNettype(String nettype) {
        this.nettype = nettype;
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
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getSql() {
        return sql;
    }
    public void setSql(String sql) {
        this.sql = sql;
    }
    public String getDeleted() {
        return deleted;
    }

  public String getContract() {
    return contract;
  }

  public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

  public void setContract(String contract) {
    this.contract = contract;
  }
}
