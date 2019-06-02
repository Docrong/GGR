package com.boco.eoms.sparepart.model;

import com.boco.eoms.base.model.BaseObject;

public class StorageHeb extends BaseObject {
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

	private String storagename;
	
    public String getStoragename()
    {
        return storagename;
    }
    public void setStoragename(String storagename)
    {
        this.storagename = storagename;
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

	private String deptName;
	
    public String getDeptName()
    {
        return deptName;
    }
    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
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
