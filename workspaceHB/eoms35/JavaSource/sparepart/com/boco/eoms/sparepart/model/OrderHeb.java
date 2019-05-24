package com.boco.eoms.sparepart.model;
import java.util.Date;

import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.sparepart.model.OrderHeb;

public class OrderHeb extends BaseObject {
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

	private String operater;
	
    public String getOperater()
    {
        return operater;
    }
    public void setOperater(String operater)
    {
        this.operater = operater;
    }

	private String proposer;
	
    public String getProposer()
    {
        return proposer;
    }
    public void setProposer(String proposer)
    {
        this.proposer = proposer;
    }

	private String propDept;
	
    public String getPropDept()
    {
        return propDept;
    }
    public void setPropDept(String propDept)
    {
        this.propDept = propDept;
    }

	private String propTel;
	
    public String getPropTel()
    {
        return propTel;
    }
    public void setPropTel(String propTel)
    {
        this.propTel = propTel;
    }

	private Date stratdate;
	
    public Date getStratdate()
    {
        return stratdate;
    }
    public void setStratdate(Date stratdate)
    {
        this.stratdate = stratdate;
    }

	private Date overdate;
	
    public Date getOverdate()
    {
        return overdate;
    }
    public void setOverdate(Date overdate)
    {
        this.overdate = overdate;
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

	private Integer type;
	
    public Integer getType()
    {
        return type;
    }
    public void setType(Integer type)
    {
        this.type = type;
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

	private String accessory;
	
    public String getAccessory()
    {
        return accessory;
    }
    public void setAccessory(String accessory)
    {
        this.accessory = accessory;
    }

	private String sheetid;
	
    public String getSheetid()
    {
        return sheetid;
    }
    public void setSheetid(String sheetid)
    {
        this.sheetid = sheetid;
    }

	private String overgay;
	
    public String getOvergay()
    {
        return overgay;
    }
    public void setOvergay(String overgay)
    {
        this.overgay = overgay;
    }

	private String reason;
	
    public String getReason()
    {
        return reason;
    }
    public void setReason(String reason)
    {
        this.reason = reason;
    }

	private String station;
	
    public String getStation()
    {
        return station;
    }
    public void setStation(String station)
    {
        this.station = station;
    }

	private String fixe;
	
    public String getFixe()
    {
        return fixe;
    }
    public void setFixe(String fixe)
    {
        this.fixe = fixe;
    }

	private String version;
	
    public String getVersion()
    {
        return version;
    }
    public void setVersion(String version)
    {
        this.version = version;
    }

	private String serialno;
	
    public String getSerialno()
    {
        return serialno;
    }
    public void setSerialno(String serialno)
    {
        this.serialno = serialno;
    }

	private String objtype;
	
    public String getObjtype()
    {
        return objtype;
    }
    public void setObjtype(String objtype)
    {
        this.objtype = objtype;
    }

	private String ename;
	
    public String getEname()
    {
        return ename;
    }
    public void setEname(String ename)
    {
        this.ename = ename;
    }

	private String advices;
	
    public String getAdvices()
    {
        return advices;
    }
    public void setAdvices(String advices)
    {
        this.advices = advices;
    }

    public boolean equals(Object o) {
		if (o instanceof OrderHeb) {
			OrderHeb orderHeb = (OrderHeb) o;
			if (this.id != null || this.id.equals(orderHeb.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
