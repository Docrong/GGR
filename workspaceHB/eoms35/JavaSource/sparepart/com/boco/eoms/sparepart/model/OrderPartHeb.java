package com.boco.eoms.sparepart.model;

import com.boco.eoms.base.model.BaseObject;

public class OrderPartHeb extends BaseObject {
	private static final long serialVersionUID = 5751736560951652677L;

	/**
	 * 主键
	 */
	private String id;
	
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }

	private String orderId;
	
    public String getOrderId()
    {
        return orderId;
    }
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

	private String sparepartId;
	
    public String getSparepartId()
    {
        return sparepartId;
    }
    public void setSparepartId(String sparepartId)
    {
        this.sparepartId = sparepartId;
    }

	private String mangle;
	
    public String getMangle()
    {
        return mangle;
    }
    public void setMangle(String mangle)
    {
        this.mangle = mangle;
    }

	private String charge;
	
    public String getCharge()
    {
        return charge;
    }
    public void setCharge(String charge)
    {
        this.charge = charge;
    }

	private Integer state;
	
    public Integer getState()
    {
        return state;
    }
    public void setState(Integer state)
    {
        this.state = state;
    }

	private String orgSerialNo;
	
    public String getOrgSerialNo()
    {
        return orgSerialNo;
    }
    public void setOrgSerialNo(String orgSerialNo)
    {
        this.orgSerialNo = orgSerialNo;
    }

	private String newSerialNo;
	
    public String getNewSerialNo()
    {
        return newSerialNo;
    }
    public void setNewSerialNo(String newSerialNo)
    {
        this.newSerialNo = newSerialNo;
    }

    public boolean equals(Object o) {
		if (o instanceof OrderPartHeb) {
			OrderPartHeb orderPartHeb = (OrderPartHeb) o;
			if (this.id != null || this.id.equals(orderPartHeb.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
