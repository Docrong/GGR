package com.boco.eoms.sparepart.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.sparepart.model.SparepartHeb;


public class SparepartHeb extends BaseObject {

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

	private Integer madein;

	public Integer getMadein()
    {
        return madein;
    }
    public void setMadein(Integer madein)
    {
        this.madein = madein;
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

	private Date outtime;

	public Date getOuttime()
    {
        return outtime;
    }
    public void setOuttime(Date outtime)
    {
        this.outtime = outtime;
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

	private String exceptionnote;

	public String getExceptionnote()
    {
        return exceptionnote;
    }
    public void setState(String exceptionnote)
    {
        this.exceptionnote = exceptionnote;
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

	private String contract;

	public String getContract()
    {
        return contract;
    }
    public void setContract(String contract)
    {
        this.contract = contract;
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

	private Date updatetime;

	public Date getUpdatetime()
    {
        return updatetime;
    }
    public void setUpdatetime(Date updatetime)
    {
        this.updatetime = updatetime;
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

	private Date expire;

	public Date getExpire()
    {
        return expire;
    }
    public void setExpire(Date expire)
    {
        this.expire = expire;
    }

	private String units;

	public String getUnits()
    {
        return units;
    }
    public void setUnits(String units)
    {
        this.units = units;
    }

	private Integer proform;

	public Integer getProform()
    {
        return proform;
    }
    public void setProform(Integer proform)
    {
        this.proform = proform;
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

	private Integer loannum;

	public Integer getLoannum()
    {
        return loannum;
    }
    public void setLoannum(Integer loannum)
    {
        this.loannum = loannum;
    }

	private Integer repairnum;

	public Integer getRepairnum()
    {
        return repairnum;
    }
    public void setRepairnum(Integer repairnum)
    {
        this.repairnum = repairnum;
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

	private String sheetid;

	public String getSheetid()
    {
        return sheetid;
    }
    public void setSheetid(String sheetid)
    {
        this.sheetid = sheetid;
    }

	private Integer warrantyFlag;

	public Integer getWarrantyFlag()
    {
        return warrantyFlag;
    }
    public void setWarrantyFlag(Integer warrantyFlag)
    {
        this.warrantyFlag = warrantyFlag;
    }

	private Integer stopproductFlag;

	public Integer getStopproductFlag()
    {
        return stopproductFlag;
    }
    public void setStopproductFlag(Integer stopproductFlag)
    {
        this.stopproductFlag = stopproductFlag;
    }

	private Integer sparepartFlag;

	public Integer getSparepartFlag()
    {
        return sparepartFlag;
    }
    public void setSparepartFlag(Integer sparepartFlag)
    {
        this.sparepartFlag = sparepartFlag;
    }

	private Integer borrowState;

	public Integer getBorrowState()
    {
        return borrowState;
    }
    public void setBorrowState(Integer borrowState)
    {
        this.borrowState = borrowState;
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

	private String accessory;

	public String getAccessory()
    {
        return accessory;
    }
    public void setAccessory(String accessory)
    {
        this.accessory = accessory;
    }

	private String thefile;

	public String getThefile()
    {
        return thefile;
    }
    public void setThefile(String thefile)
    {
        this.thefile = thefile;
    }

	private Integer subdept;

	public Integer getSubdept()
    {
        return subdept;
    }
    public void setSubdept(Integer subdept)
    {
        this.subdept = subdept;
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

	private String company;

	public String getCompany()
    {
        return company;
    }
    public void setCompany(String company)
    {
        this.company = company;
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

	private String repairEndtime;

	public String getRepairEndtime()
    {
        return repairEndtime;
    }
    public void setRepairEndtime(String repairEndtime)
    {
        this.repairEndtime = repairEndtime;
    }

	private String repairtime;

	public String getRepairtime()
    {
        return repairtime;
    }
    public void setRepairtime(String repairtime)
    {
        this.repairtime = repairtime;
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

	private String deleted;

	public String getDeleted()
    {
        return deleted;
    }
    public void setDeleted(String deleted)
    {
        this.deleted = deleted;
    }

	private Integer parttype;

	public Integer getParttype()
    {
        return parttype;
    }
    public void setParttype(Integer parttype)
    {
        this.parttype = parttype;
    }

	private Integer checksum;

	public Integer getChecksum()
    {
        return checksum;
    }
    public void setChecksum(Integer checksum)
    {
        this.checksum = checksum;
    }

	private String describe;

	public String getDescribe()
    {
        return describe;
    }
    public void setChecksum(String describe)
    {
        this.describe = describe;
    }

    public boolean equals(Object o) {
		if (o instanceof SparepartHeb) {
			SparepartHeb sparepartHeb = (SparepartHeb) o;
			if (this.id != null || this.id.equals(sparepartHeb.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
