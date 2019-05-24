package com.boco.eoms.sparepart.controller;

import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawSparepartForm extends BaseForm implements java.io.Serializable{

    private String id;
    private String objectname;
    private String operator;
    private String intime;
    private String outtime;
    private String exceptionnote;
    private String note;
    private String updatetime;
    private String productcode;
    private String managecode;
    private String expire;
    private String units;
    private String version;
    private String swversion;
    private String supplier;
    private String storageid;
    private String state;
    private String objecttype;
    private String nettype;
    private String subDept;
    private String necode;
    private String madein;
    private String serialno;
    private String intPage;
    private String sql;
    private String type;
    private String sumid;
    private String timein;
    private String timeend;
    private String userId;
    private String money;
    private String cname;
    private int strutsAction;
    private String parentId;
    private String net;
    private String ename;
    private String contract;
    private String orderId;
    private String orderType;
    private String department;
    private String charge;
    private String position;
    private String proform;
    private String badserialno;
    private int loannum;
    private int repairnum;
    private String sheetid;
    private String warrantyFlag;
    private String stopproductFlag;
  private String accessory;
  private FormFile theFile;
  
  
  private String proposer;//申请人
  private String company;//所属公司
  private String objtype; //设备型号
  private String repair_endtime;//保修到期时间
  private String repairtime;//保修时间
  private String fixe;//设备厂商
  private String deleted;//删除标志
  private int partType;//物资类型
  private String describe;//物资类型
  
  

  

public int getPartType() {
	return partType;
}

public void setPartType(int partType) {
	this.partType = partType;
}

public String getDeleted() {
	return deleted;
}

public void setDeleted(String deleted) {
	this.deleted = deleted;
}

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

public String getObjtype() {
	return objtype;
}

public void setObjtype(String objtype) {
	this.objtype = objtype;
}

public String getProposer() {
	return proposer;
}

public void setProposer(String proposer) {
	this.proposer = proposer;
}

public String getRepair_endtime() {
	return repair_endtime;
}

public void setRepair_endtime(String repair_endtime) {
	this.repair_endtime = repair_endtime;
}

public String getRepairtime() {
	return repairtime;
}

public void setRepairtime(String repairtime) {
	this.repairtime = repairtime;
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

    public String getId(){
        return id;
    }

    public void setId(String id){
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

    public String getStorageid(){
        return storageid;
    }

    public void setStorageid(String storageid){
        this.storageid=storageid;
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

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getEname() {
        return ename;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }
    public String getContract() {
        return contract;
    }
    public void setContract(String contract) {
        this.contract = contract;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getCharge() {
        return charge;
    }
    public void setCharge(String charge) {
        this.charge = charge;
    }
  public String getPosition() {
    return position;
  }
  public void setPosition(String position) {
    this.position = position;
  }
  public String getProform() {
    return proform;
  }
  public void setProform(String proform) {
    this.proform = proform;
  }
  public String getBadserialno() {
    return badserialno;
  }
  public void setBadserialno(String badserialno) {
    this.badserialno = badserialno;
  }
  public int getLoannum() {
    return loannum;
  }
  public void setLoannum(int loannum) {
    this.loannum = loannum;
  }
  public int getRepairnum() {
    return repairnum;
  }

  public String getMoney() {
    return money;
  }

  public String getSheetid() {
    return sheetid;
  }

  public void setRepairnum(int repairnum) {
    this.repairnum = repairnum;
  }

  public void setMoney(String money) {
    this.money = money;
  }

  public void setSheetid(String sheetid) {
    this.sheetid = sheetid;
  }

  public String getWarrantyFlag() {
    return warrantyFlag;
  }

  public void setWarrantyFlag(String warrantyFlag) {
    this.warrantyFlag = warrantyFlag;
  }

  public String getStopproductFlag() {
    return stopproductFlag;
  }

  public String getAccessory() {
    return accessory;
  }

  public FormFile getTheFile() {
    return theFile;
  }

  public void setStopproductFlag(String stopproductFlag) {
    this.stopproductFlag = stopproductFlag;
  }

  public void setAccessory(String accessory) {
    this.accessory = accessory;
  }

  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }

public String getSubDept() {
	return subDept;
}

public void setSubDept(String subDept) {
	this.subDept = subDept;
}

public String getDescribe() {
	return describe;
}

public void setDescribe(String describe) {
	this.describe = describe;
}

 
 

}
