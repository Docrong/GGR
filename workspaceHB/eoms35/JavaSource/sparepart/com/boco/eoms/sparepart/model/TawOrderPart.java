package com.boco.eoms.sparepart.model;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawOrderPart {
    private int id;
    private int orderId;
    private String sparepartId;
    private int state;
    private String orgSerialNo;
    private String newSerialNo;
    
  public TawOrderPart() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public String getSparepartId() {
        return sparepartId;
    }

  public String getNewSerialNo() {
    return newSerialNo;
  }

  public String getOrgSerialNo() {
    return orgSerialNo;
  }

  public void setSparepartId(String sparepartId) {
        this.sparepartId = sparepartId;
    }

  public void setNewSerialNo(String newSerialNo) {
    this.newSerialNo = newSerialNo;
  }

  public void setOrgSerialNo(String orgSerialNo) {
    this.orgSerialNo = orgSerialNo;
  }
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}

}
