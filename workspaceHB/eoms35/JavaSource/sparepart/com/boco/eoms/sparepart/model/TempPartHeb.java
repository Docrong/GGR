package com.boco.eoms.sparepart.model;

import com.boco.eoms.base.model.BaseObject;

public class TempPartHeb extends BaseObject {
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
		if (o instanceof TempPartHeb) {
			TempPartHeb tempPartHeb = (TempPartHeb) o;
			if (this.id != null || this.id.equals(tempPartHeb.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
