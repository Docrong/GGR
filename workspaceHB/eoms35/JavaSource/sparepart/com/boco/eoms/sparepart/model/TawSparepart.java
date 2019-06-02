package com.boco.eoms.sparepart.model;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawSparepart
{
    private int id;
    private int objecttype;
    private int version;
    private int nettype;
    private int subnettype;
    private int madein;
    private int supplier;
    private int state;//11:库中可用;12:库中待修;13:维修出库;14:借出;15:报废;16:扩容;19:调拨
    private int storageid;
    private int necode;
    private int swversion;
    private String objectname;
    private String operator;
    private String intime;
    private String outtime;
    private String exceptionnote;
    private String note;
    private String updatetime;
    private String productcode;
    private String managecode;
    private String expire;
    private String units;
    private String proform;
    private String position;
    private String type;
    private String serialno;
    private String cname;
    private String repairnum;
    private String loannum;
    private String money;
    private String contract;
    private String sheetid;
    
    private String proposer;//申请人
    private String company;//所属公司
    private String objtype; //设备型号
    private String repair_endtime;//保修到期时间
    private String repairtime;//保修时间
    private String fixe;//设备厂商
    private int checksum;
    private int parttype;
    
    
  private String orgSerialNo;
  public TawSparepart()
    {
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public int getObjecttype()
    {
        return objecttype;
    }
    public void setObjecttype(int objecttype)
    {
        this.objecttype = objecttype;
    }
    public int getVersion()
    {
        return version;
    }
    public void setVersion(int version)
    {
        this.version = version;
    }
    public int getNettype()
    {
        return nettype;
    }
    public void setNettype(int nettype)
    {
        this.nettype = nettype;
    }
    public int getMadein()
    {
        return madein;
    }
    public void setMadein(int madein)
    {
        this.madein = madein;
    }
    public int getSupplier()
    {
        return supplier;
    }
    public void setSupplier(int supplier)
    {
        this.supplier = supplier;
    }
    public int getState()
    {
        return state;
    }
    public void setState(int state)
    {
        this.state = state;
    }
    public int getStorageid()
    {
        return storageid;
    }
    public void setStorageid(int storageid)
    {
        this.storageid = storageid;
    }
    public int getNecode()
    {
        return necode;
    }
    public void setNecode(int necode)
    {
        this.necode = necode;
    }
    public int getSwversion()
    {
        return swversion;
    }
    public void setSwversion(int swversion)
    {
        this.swversion = swversion;
    }
    public String getObjectname()
    {
        return objectname;
    }
    public void setObjectname(String objectname)
    {
        this.objectname = objectname;
    }
    public String getOperator()
    {
        return operator;
    }
    public void setOperator(String operator)
    {
        this.operator = operator;
    }
    public String getIntime()
    {
        return intime;
    }
    public void setIntime(String intime)
    {
        this.intime = intime;
    }
    public String getOuttime()
    {
        return outtime;
    }
    public void setOuttime(String outtime)
    {
        this.outtime = outtime;
    }
    public String getExceptionnote()
    {
        return exceptionnote;
    }
    public void setExceptionnote(String exceptionnote)
    {
        this.exceptionnote = exceptionnote;
    }
    public String getNote()
    {
        return note;
    }
    public void setNote(String note)
    {
        this.note = note;
    }
    public String getUpdatetime()
    {
        return updatetime;
    }
    public void setUpdatetime(String updatetime)
    {
        this.updatetime = updatetime;
    }
    public String getProductcode()
    {
        return productcode;
    }
    public void setProductcode(String productcode)
    {
        this.productcode = productcode;
    }
    public String getManagecode()
    {
        return managecode;
    }
    public void setManagecode(String managecode)
    {
        this.managecode = managecode;
    }
    public String getExpire()
    {
        return expire;
    }
    public void setExpire(String expire)
    {
        this.expire = expire;
    }
    public String getUnits()
    {
        return units;
    }
    public void setUnits(String units)
    {
        this.units = units;
    }
  public String getProform() {
    return proform;
  }
  public void setProform(String proform) {
    this.proform = proform;
  }
  public String getPosition() {
    return position;
  }
  public void setPosition(String position) {
    this.position = position;
  }
  public String getType() {
    return type;
  }

  public String getSerialno() {
    return serialno;
  }

  public String getCname() {
    return cname;
  }

  public String getRepairnum() {
    return repairnum;
  }

  public String getLoannum() {
    return loannum;
  }

  public String getMoney() {
    return money;
  }

  public String getContract() {
    return contract;
  }

  public String getSheetid() {
    return sheetid;
  }

  public String getOrgSerialNo() {
    return orgSerialNo;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setSerialno(String serialno) {
    this.serialno = serialno;
  }

  public void setCname(String cname) {
    this.cname = cname;
  }

  public void setRepairnum(String repairnum) {
    this.repairnum = repairnum;
  }

  public void setLoannum(String loannum) {
    this.loannum = loannum;
  }

  public void setMoney(String money) {
    this.money = money;
  }

  public void setContract(String contract) {
    this.contract = contract;
  }

  public void setSheetid(String sheetid) {
    this.sheetid = sheetid;
  }

  public void setOrgSerialNo(String orgSerialNo) {
    this.orgSerialNo = orgSerialNo;
  }
public int getSubnettype() {
	return subnettype;
}
public void setSubnettype(int subnettype) {
	this.subnettype = subnettype;
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}
public String getFixe() {
	return fixe;
}
public void setFixe(String fixe) {
	this.fixe = fixe;
}
public String getObjtype() {
	return objtype;
}
public void setObjtype(String objtype) {
	this.objtype = objtype;
}

public String getProposer() {
	return proposer;
}
public void setProposer(String proposer) {
	this.proposer = proposer;
}
public String getRepair_endtime() {
	return repair_endtime;
}
public void setRepair_endtime(String repair_endtime) {
	this.repair_endtime = repair_endtime;
}
public String getRepairtime() {
	return repairtime;
}
public void setRepairtime(String repairtime) {
	this.repairtime = repairtime;
}
public int getChecksum() {
	return checksum;
}
public void setChecksum(int checksum) {
	this.checksum = checksum;
}

public int getParttype() {
	return parttype;
}
public void setParttype(int parttype) {
	this.parttype = parttype;
}

}
