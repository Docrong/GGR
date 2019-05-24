package com.boco.eoms.sparepart.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.sparepart.model.BorrowHeb;

public class BorrowHeb extends BaseObject {
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
    
	private String objecttype;
	
    public String getObjecttype()
    {
        return objecttype;
    }
    public void setObjecttype(String objecttype)
    {
        this.objecttype = objecttype;
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
    
	private String objectname;
	
    public String getObjectname()
    {
        return objectname;
    }
    public void setObjectname(String objectname)
    {
        this.objectname = objectname;
    }
    
	private String nettype;
	
    public String getNettype()
    {
        return nettype;
    }
    public void setNettype(String nettype)
    {
        this.nettype = nettype;
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
    
	private String supplier;
	
    public String getSupplier()
    {
        return supplier;
    }
    public void setSupplier(String supplier)
    {
        this.supplier = supplier;
    }
    
	private String operator;
	
    public String getOperator()
    {
        return operator;
    }
    public void setOperator(String operator)
    {
        this.operator = operator;
    }
    
	private Date intime;
	
    public Date getIntime()
    {
        return intime;
    }
    public void setIntime(Date intime)
    {
        this.intime = intime;
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
    
	private String note;
	
    public String getNote()
    {
        return note;
    }
    public void setNote(String note)
    {
        this.note = note;
    }
    
	private Integer necode;
	
    public Integer getNecode()
    {
        return necode;
    }
    public void setNecode(Integer necode)
    {
        this.necode = necode;
    }
    
	private String swversion;
	
    public String getSwversion()
    {
        return swversion;
    }
    public void setSwversion(String swversion)
    {
        this.swversion = swversion;
    }
    
	private String hwversion;
	
    public String getHwversion()
    {
        return hwversion;
    }
    public void setHwversion(String hwversion)
    {
        this.hwversion = hwversion;
    }
    
	private String productcode;
	
    public String getProductcode()
    {
        return productcode;
    }
    public void setProductcode(String productcode)
    {
        this.productcode = productcode;
    }
    
	private String managecode;
	
    public String getManagecode()
    {
        return managecode;
    }
    public void setManagecode(String managecode)
    {
        this.managecode = managecode;
    }
    
	private String position;
	
    public String getPosition()
    {
        return position;
    }
    public void setPosition(String position)
    {
        this.position = position;
    }
    
	private String storage;
	
    public String getStorage()
    {
        return storage;
    }
    public void setStorage(String storage)
    {
        this.storage = storage;
    }
    
	private String cname;
	
    public String getCname()
    {
        return cname;
    }
    public void setCname(String cname)
    {
        this.cname = cname;
    }
    
	private String necname;
	
    public String getNecname()
    {
        return necname;
    }
    public void setNecname(String necname)
    {
        this.necname = necname;
    }
    
	private String suppliername;
	
    public String getSuppliername()
    {
        return suppliername;
    }
    public void setSuppliername(String suppliername)
    {
        this.suppliername = suppliername;
    }
    
	private String contract;
	
    public String getContract()
    {
        return contract;
    }
    public void setContract(String contract)
    {
        this.contract = contract;
    }
    
	private String money;
	
    public String getMoney()
    {
        return money;
    }
    public void setMoney(String money)
    {
        this.money = money;
    }
    
	private String deptId;
	
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    
	private String objecttypename;
	
    public String getObjecttypename()
    {
        return objecttypename;
    }
    public void setObjecttypename(String objecttypename)
    {
        this.objecttypename = objecttypename;
    }
    
    public boolean equals(Object o) {
		if (o instanceof BorrowHeb) {
			BorrowHeb borrowHeb = (BorrowHeb) o;
			if (this.id != null || this.id.equals(borrowHeb.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
