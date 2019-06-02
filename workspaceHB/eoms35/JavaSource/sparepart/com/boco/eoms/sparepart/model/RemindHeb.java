package com.boco.eoms.sparepart.model;
import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

public class RemindHeb extends BaseObject {
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
    
	private String storageid;
	
    public String getStorageid()
    {
        return storageid;
    }
    public void setStorageid(String storageid)
    {
        this.storageid = storageid;
    }
    
	private String object;
	
    public String getObject()
    {
        return object;
    }
    public void setObject(String object)
    {
        this.object = object;
    }
    
	private Integer type;
	
    public Integer getType()
    {
        return type;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }
    
	private String upperlimit;
	
    public String getUpperlimit()
    {
        return upperlimit;
    }
    public void setUpperlimit(String upperlimit)
    {
        this.upperlimit = upperlimit;
    }
    
	private String lowerlimit;
	
    public String getLowerlimit()
    {
        return lowerlimit;
    }
    public void setLowerlimit(String lowerlimit)
    {
        this.lowerlimit = lowerlimit;
    }
    
	private Date limitdate;
	
    public Date getLimitdate()
    {
        return limitdate;
    }
    public void setLimitdate(Date limitdate)
    {
        this.limitdate = limitdate;
    }
    
	private String sendmsg;
	
    public String getSendmsg()
    {
        return sendmsg;
    }
    public void setSendmsg(String sendmsg)
    {
        this.sendmsg = sendmsg;
    }
    
	private String note;
	
    public String getNote()
    {
        return note;
    }
    public void setNote(String note)
    {
        this.note = note;
    }
    
    public boolean equals(Object o) {
		if (o instanceof RemindHeb) {
			RemindHeb remindHeb = (RemindHeb) o;
			if (this.id != null || this.id.equals(remindHeb.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
