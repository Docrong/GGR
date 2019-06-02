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

public class TawQueryForm extends BaseForm implements java.io.Serializable{
    public TawQueryForm(){
    }

    private int id;
    private String objectname="";
    private String operator="";
    private String intime="";
    private String outtime="";
    private String exceptionnote="";
    private String note="";
    private String updatetime="";
    private String productcode="";
    private String managecode="";
    private String expire="";
    private String units="";
    private String version="";
    private String swversion="";
    private String supplier="";
    private String storage="";
    private String state="";
    private String objecttype="";
    private String nettype="";
    private String necode="";
    private String madein="";
    private String serialno="";
    private String intPage="";
    private String sql="";
    private String type="";
    private int orderId;
    private String sumid="";
    private String timein="";
    private String timeend="";
    private String proposer="";
    private String contract="";
  private String loannum;
  private String repairnum;
  private String storageList;
  private String storagename;
  private String deptName;
  private String subdept;
  
  private String startintime;//��⿪ʼʱ��
  private String endintime;//������ʱ��
  private String startouttime;//��⿪ʼʱ��
  private String endouttime;//������ʱ��
  private String fixe;//�豸����
  private String company;//����˾
  
  
  public String getCompany() {
	return company;
}

public void setCompany(String company) {
	this.company = company;
}

public String getFixe() {
	return fixe;
}

public void setFixe(String fixe) {
	this.fixe = fixe;
}

public String getEndouttime() {
	return endouttime;
}

public void setEndouttime(String endouttime) {
	this.endouttime = endouttime;
}

public String getStartouttime() {
	return startouttime;
}

public void setStartouttime(String startouttime) {
	this.startouttime = startouttime;
}

public String getEndintime() {
	return endintime;
}

public void setEndintime(String endintime) {
	this.endintime = endintime;
}

public String getStartintime() {
	return startintime;
}

public void setStartintime(String startintime) {
	this.startintime = startintime;
}

public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getObjecttype(){
        return objecttype;
    }

    public void setObjecttype(String objecttype){
        this.objecttype=objecttype;
    }

    public String getVersion(){
        return version;
    }

    public void setVersion(String version){
        this.version=version;
    }

    public String getNettype(){
        return nettype;
    }

    public void setNettype(String nettype){
        this.nettype=nettype;
    }

    public String getMadein(){
        return madein;
    }

    public void setMadein(String madein){
        this.madein=madein;
    }

    public String getSupplier(){
        return supplier;
    }

    public void setSupplier(String supplier){
        this.supplier=supplier;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state=state;
    }

    public String getStorage(){
        return storage;
    }

    public void setStorage(String storage){
        this.storage=storage;
    }

    public String getNecode(){
        return necode;
    }

    public void setNecode(String necode){
        this.necode=necode;
    }

    public String getSwversion(){
        return swversion;
    }

    public void setSwversion(String swversion){
        this.swversion=swversion;
    }

    public String getObjectname(){
        return objectname;
    }

    public void setObjectname(String objectname){
        this.objectname=objectname;
    }

    public String getOperator(){
        return operator;
    }

    public void setOperator(String operator){
        this.operator=operator;
    }

    public String getIntime(){
        return intime;
    }

    public void setIntime(String intime){
        this.intime=intime;
    }

    public String getOuttime(){
        return outtime;
    }

    public void setOuttime(String outtime){
        this.outtime=outtime;
    }

    public String getExceptionnote(){
        return exceptionnote;
    }

    public void setExceptionnote(String exceptionnote){
        this.exceptionnote=exceptionnote;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note=note;
    }

    public String getUpdatetime(){
        return updatetime;
    }

    public void setUpdatetime(String updatetime){
        this.updatetime=updatetime;
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

    public String getExpire(){
        return expire;
    }

    public void setExpire(String expire){
        this.expire=expire;
    }

    public String getUnits(){
        return units;
    }

    public void setUnits(String units){
        this.units=units;
    }

    public String getSerialno(){
        return serialno;
    }

    public void setSerialno(String serialno){
        this.serialno=serialno;
    }

    public String getIntPage(){
        return intPage;
    }

    public void setIntPage(String intPage){
        this.intPage=intPage;
    }

    public String getSql(){
        return sql;
    }

    public void setSql(String sql){
        this.sql=sql;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type=type;
    }

    public int getOrderId(){
        return orderId;
    }

    public void setOrderId(int orderId){
        this.orderId=orderId;
    }

    public String getSumid(){
        return sumid;
    }

    public void setSumid(String sumid){
        this.sumid=sumid;
    }

    public String getTimein(){
        return timein;
    }

    public void setTimein(String timein){
        this.timein=timein;
    }

    public String getTimeend(){
        return timeend;
    }

    public void setTimeend(String timeend){
        this.timeend=timeend;
    }

    public String getProposer(){
        return proposer;
    }

    public void setProposer(String proposer){
        this.proposer=proposer;
    }
  public String getLoannum() {
    return loannum;
  }
  public void setLoannum(String loannum) {
    this.loannum = loannum;
  }
  public String getRepairnum() {
    return repairnum;
  }

  public String getContract() {
    return contract;
  }

  public String getStorageList() {
    return storageList;
  }

  public String getDeptName() {
    return deptName;
  }

  public String getStoragename() {
    return storagename;
  }

  public void setRepairnum(String repairnum) {
    this.repairnum = repairnum;
  }

  public void setContract(String contract) {
    this.contract = contract;
  }

  public void setStorageList(String storageList) {
    this.storageList = storageList;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public void setStoragename(String storagename) {
    this.storagename = storagename;
  }

public String getSubdept() {
	return subdept;
}

public void setSubdept(String subdept) {
	this.subdept = subdept;
}

}
