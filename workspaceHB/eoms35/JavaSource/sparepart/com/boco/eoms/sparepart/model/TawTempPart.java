package com.boco.eoms.sparepart.model;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawTempPart {
    private String userId;
    private String orderId;
    private String sparepartId;
    private int id;
  private String newSerialNo;
  private String orgSerialNo;
  public TawTempPart() {
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getSparepartId() {
        return sparepartId;
    }
    public void setSparepartId(String sparepartId) {
        this.sparepartId = sparepartId;
    }
    public int getId() {
        return id;
    }

  public String getOrgSerialNo() {
    return orgSerialNo;
  }

  public String getNewSerialNo() {
    return newSerialNo;
  }

  public void setId(int id) {
        this.id = id;
    }

  public void setOrgSerialNo(String orgSerialNo) {
    this.orgSerialNo = orgSerialNo;
  }

  public void setNewSerialNo(String newSerialNo) {
    this.newSerialNo = newSerialNo;
  }

}
