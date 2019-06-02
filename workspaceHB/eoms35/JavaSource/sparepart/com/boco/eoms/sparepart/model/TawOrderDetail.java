package com.boco.eoms.sparepart.model;

public class TawOrderDetail {
  private String proposer;
  private String propDept;
  private String startdate;
  private String orderId;
  private String sparepartId;
  private String objectname;
  private String serialno;
  private String supplier;
  private int orderType;
  private String orderName;
  private String orderPartState;
  private String orderPartStateName;
  private String charge;
  private String orgSerialNo;
  private String newSerialNo;
  private int storageid;
  private String storageName;
  private String operater;
  private String propTel;
  private String sheetid;
  private String managecode;
  public String getCharge() {
    return charge;
  }

  public String getOrderPartStateName() {
    return orderPartStateName;
  }

  public String getOrderPartState() {
    return orderPartState;
  }

  public String getOrderName() {
    return orderName;
  }

  public int getOrderType() {
    return orderType;
  }

  public String getSupplier() {
    return supplier;
  }

  public String getSerialno() {
    return serialno;
  }

  public String getObjectname() {
    return objectname;
  }

  public String getSparepartId() {
    return sparepartId;
  }

  public String getOrderId() {
    return orderId;
  }

  public String getStartdate() {
    return startdate;
  }



  public void setProposer(String proposer) {
    this.proposer = proposer;
  }

  public void setCharge(String charge) {
    this.charge = charge;
  }

  public void setOrderPartStateName(String orderPartStateName) {
    this.orderPartStateName = orderPartStateName;
  }

  public void setOrderPartState(String orderPartState) {
    this.orderPartState = orderPartState;
  }

  public void setOrderName(String orderName) {
    this.orderName = orderName;
  }

  public void setOrderType(int orderType) {
    this.orderType = orderType;
  }

  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }

  public void setSerialno(String serialno) {
    this.serialno = serialno;
  }

  public void setObjectname(String objectname) {
    this.objectname = objectname;
  }

  public void setSparepartId(String sparepartId) {
    this.sparepartId = sparepartId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public void setStartdate(String startdate) {
    this.startdate = startdate;
  }



  public void setNewSerialNo(String newSerialNo) {
    this.newSerialNo = newSerialNo;
  }

  public void setOrgSerialNo(String orgSerialNo) {
    this.orgSerialNo = orgSerialNo;
  }

  public void setStorageName(String storageName) {
    this.storageName = storageName;
  }



  public void setPropTel(String propTel) {
    this.propTel = propTel;
  }

  public void setOperater(String operater) {
    this.operater = operater;
  }

  public void setStorageid(int storageid) {
    this.storageid = storageid;
  }

  public void setPropDept(String propDept) {
    this.propDept = propDept;
  }

  public void setSheetid(String sheetid) {
    this.sheetid = sheetid;
  }

  public String getProposer() {
    return proposer;
  }

  public String getNewSerialNo() {
    return newSerialNo;
  }

  public String getOrgSerialNo() {
    return orgSerialNo;
  }

  public String getStorageName() {
    return storageName;
  }



  public String getPropTel() {
    return propTel;
  }

  public String getOperater() {
    return operater;
  }

  public int getStorageid() {
    return storageid;
  }

  public String getPropDept() {
    return propDept;
  }

  public String getSheetid() {
    return sheetid;
  }

  public TawOrderDetail() {
  }

public String getManagecode() {
	return managecode;
}

public void setManagecode(String managecode) {
	this.managecode = managecode;
}
}
