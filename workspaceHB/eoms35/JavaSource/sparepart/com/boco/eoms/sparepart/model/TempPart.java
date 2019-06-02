package com.boco.eoms.sparepart.model;

import com.boco.eoms.base.model.BaseObject;

public class TempPart extends BaseObject {
	private static final long serialVersionUID = 5751736560951652677L;

	private String id;
	
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    
	private String userId;
	
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
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
    
    public boolean equals(Object o) {
		if (o instanceof TempPart) {
			TempPart tempPart = (TempPart) o;
			if (this.id != null || this.id.equals(tempPart.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
