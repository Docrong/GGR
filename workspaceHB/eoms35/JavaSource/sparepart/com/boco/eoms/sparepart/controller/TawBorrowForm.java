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

public class TawBorrowForm extends BaseForm implements java.io.Serializable{

    private String id;
    private String cname;
    private String ename;
    private String note;
    private int strutsAction;
    private String parentId;
    private String net;
    private String orderId;
    private String orderType;
    private String nettype="";
    private String necode="";
    private String subdept="";
    private String objectname="";
    private String department;
    private String sql;
    private String storage;
    private String storageid="";
    private String productcode;
    private String managecode;
    private String serialno;
    private String operator;
    private String position;
    private String objecttype;
    private String supplier="";
    private String intime="";
    private String timestart="";
    private String timeend="";
    private String necname;
    private String objecttypename;
    private String suppliername;
    private String contract;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id=id;
    }

    public String getStorageid(){
    return storageid;
  }

  public void setStorageid(String storageid){
    this.storageid=storageid;
  }

  public String getStorage(){
    return storage;
  }

  public void setStorage(String storage){
    this.storage=storage;
  }

    public String getCname(){
        return cname;
    }

    public void setCname(String cname){
        this.cname=cname;
    }

    public String getNecname(){
        return necname;
    }

    public void setNecname(String necname){
        this.necname=necname;
    }


    public String getProductcode(){
        return productcode;
    }

    public void setProductcode(String productcode){
        this.productcode=productcode;
    }

    public String getManagecode(){
        return managecode;
    }

    public void setManagecode(String managecode){
        this.managecode=managecode;
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

    public String getEname() {
        return ename;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getSerialno(){
        return serialno;
    }

    public void setSerialno(String serialno){
        this.serialno=serialno;
    }

    public String getOperator(){
        return operator;
    }

    public void setOperator(String operator){
        this.operator=operator;
    }

    public String getPosition() {
    return position;
  }
  public void setPosition(String position) {
    this.position = position;
  }

  public String getObjecttype(){
        return objecttype;
    }

    public void setObjecttype(String objecttype){
        this.objecttype=objecttype;
    }

    public String getSupplier(){
        return supplier;
    }

    public void setSupplier(String supplier){
        this.supplier=supplier;
    }

    public String getIntime(){
        return intime;
    }

    public void setIntime(String intime){
        this.intime=intime;
    }

    public String getTimestart(){
      return timestart;
    }

    public void setTimestart(String timestart){
      this.timestart=timestart;
    }

    public String getTimeend(){
      return timeend;
    }

    public void setTimeend(String timeend){
      this.timeend=timeend;
    }

    public String getObjecttypename(){
            return objecttypename;
    }

    public void setObjecttypename(String objecttypename){
            this.objecttypename=objecttypename;
    }

    public String getSuppliername(){
            return suppliername;
    }

  public String getContract() {
    return contract;
  }

  public void setSuppliername(String suppliername){
            this.suppliername=suppliername;
    }

  public void setContract(String contract) {
    this.contract = contract;
  }

public String getSubdept() {
	return subdept;
}

public void setSubDept(String subdept) {
	this.subdept = subdept;
}
}
