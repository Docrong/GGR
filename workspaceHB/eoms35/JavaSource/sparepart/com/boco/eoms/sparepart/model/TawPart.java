package com.boco.eoms.sparepart.model;

import java.sql.SQLException;
import java.util.Map;

import com.boco.eoms.sparepart.dao.TawPartDAO;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawPart{
    private int id;
    private String objectname;
    private String operator;
    private String intime;
    private String note;
    private String necode;
    private String nettype;
    private String subdept;
    private String objecttype;
    private String state;
    private String storage;
    private String supplier;
    private String version;
    private String serialno;
    private String sum;
    private String userId;
    private String managecode;
    private String productcode;
    private String units;
    private String contract;
    private int outSum;
    private int serSum;
    private String outPercent;
    private String servicePercent;
    private String charge;
    private String supplierid;
    private String hwversion;
    private String etime;
  private String position;
  private String proform;
  private String updatetime;
  private int loannum;
  private int repairnum;
  private String money;
  private String sheetid;
  private int proformFlag;
  private int warrantyFlag;
  private String warrantyName;
  private int stopproductFlag;
  private String stopproductName;
  private int deptId;
  private String deptName;
  private int borrowState;
  private String borrowStateName;
  private int nettypeid;
  private int subdeptid;
  private int necodeid;
  private int stateid;
  private int storageid;
  private long objecttypeid;
  private String orgSerialNo;
  private String outtime;
  private String accessory;
  private String thefile;
  private String proposer;//入库申请人
  private String company;//所属公司
  private String companyid;//所属公司  id
  private String objtype;//设备类型
  private String repair_endtime;//保修到期时间
  private String repairtime;//保修期
  private String fixe;//设备厂商
  private String fixeid;//设备厂商  id
  private int checksum;
  private int parttype;
  private String partclass;
  private String describe;//主要功能描述
  private TawPartDAO dao=new TawPartDAO();
  public String getCompanyid() {
	return companyid;
}

public void setCompanyid(String companyid) {

	this.companyid = companyid;
}

public String getFixeid() {
	return fixeid;
}

public void setFixeid(String fixeid) {
	this.fixeid = fixeid;
}

public String getCompany() {
	if(company==null || company.equals("")){
		Map map=dao.getCname(450);
		String value=(String) map.get(this.getCompanyid());
		return value;
	}else{
	return company;
	}
}

public void setCompany(String company) {
	this.company = company;
}

public String getFixe() {
	if(fixe==null || company.equals("")){
		Map map=dao.getCname(460);//设备厂商
		String value=(String) map.get(this.getCompanyid());
		return value;
	}else{
	return fixe;
	}
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

public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getObjecttype(){
        return objecttype;
    }

    public void setObjecttype(String objecttype){
        this.objecttype=objecttype;
    }

    public String getVersion(){
        return version;
    }

    public void setVersion(String version){
        this.version=version;
    }

    public String getNettype(){
        return nettype;
    }

    public void setNettype(String nettype){
        this.nettype=nettype;
    }

    public String getSupplier(){
    	if(supplier==null || supplier.equals("")){
    		Map map=dao.getCname(6);//设备厂商
    		String value=(String) map.get(this.getCompanyid());
    		return value;
    	}else{
    	return supplier;
    	}
    }

    public void setSupplier(String supplier){
        this.supplier=supplier;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state=state;
    }

    public String getStorage(){
        return storage;
    }

    public void setStorage(String storage){
        this.storage=storage;
    }

    public String getNecode(){
        return necode;
    }

    public void setNecode(String necode){
        this.necode=necode;
    }

    public String getObjectname(){
        return objectname;
    }

    public void setObjectname(String objectname){
        this.objectname=objectname;
    }

    public String getOperator(){
        return operator;
    }

    public void setOperator(String operator){
        this.operator=operator;
    }

    public String getIntime(){
        return intime;
    }

    public void setIntime(String intime){
        this.intime=intime.substring(0,10);
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note=note;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){

        this.userId=userId;
    }

    public String getSerialno(){
        return serialno;
    }

    public void setSerialno(String serialno){
        this.serialno=serialno;
    }

    public String getSum(){
        return sum;
    }

    public void setSum(String sum){
        this.sum=sum;
    }

    public String getManagecode(){
        return managecode;
    }

    public void setManagecode(String managecode){
        this.managecode=managecode;
    }

    public String getProductcode(){
        return productcode;
    }

    public void setProductcode(String productcode){
        this.productcode=productcode;
    }

    public String getUnits(){
        return units;
    }

    public void setUnits(String units){
        this.units=units;
    }

    public String getContract(){
        return contract;
    }

    public void setContract(String contract){
        this.contract=contract;
    }

    public int getOutSum(){
        return outSum;
    }

    public void setOutSum(int outSum){
        this.outSum=outSum;
    }

    public int getSerSum(){
        return serSum;
    }

    public void setSerSum(int serSum){
        this.serSum=serSum;
    }

    public String getOutPercent(){

        if(getOutSum()>0){
            String a=getSum();
            String b=Integer.toString(getOutSum());
            double aa=Double.parseDouble(b)/Double.parseDouble(a);
            outPercent=Double.toString(aa*100.0);
            if(outPercent.length()>5){
                outPercent=outPercent.substring(0,5);
            }
        }
        else{
            outPercent="0";
        }
        return outPercent+"%";
    }

    public void setOutPercent(String outPercent){
        this.outPercent=outPercent;
    }

    public String getServicePercent(){
        if(getSerSum()>0){
            String a=getSum();
            String b=Integer.toString(getSerSum());
            double aa=Double.parseDouble(b)/Double.parseDouble(a);
            servicePercent=Double.toString(aa*100.0);
            if(servicePercent.length()>5){
                servicePercent=servicePercent.substring(0,5);
            }
        }
        else{
            servicePercent="0";
        }
        return servicePercent+"%";
    }

    public void setServicePercent(String servicePercent){
        this.servicePercent=servicePercent;
    }

    public String getCharge(){
        return charge;
    }

    public void setCharge(String charge){
        this.charge=charge;
    }

    public String getSupplierid(){
        return supplierid;
    }

    public void setSupplierid(String supplierid){
        this.supplierid=supplierid;
    }

    public String getHwversion(){
        return hwversion;
    }

    public void setHwversion(String hwversion){
        this.hwversion=hwversion;
    }

    public String getEtime(){
        return etime;
    }

    public void setEtime(String etime){
        this.etime=getStrTime(etime);
    }

    public String getStrTime(String datetime){
        String strTime="";
        
        if(datetime!=""){
        	int n=datetime.indexOf("/");
        	if(n>0){
            String[] str=datetime.split("/");
            //int s=datetime.indexOf("/");
            //int e=datetime.lastIndexOf("/");
            // String a=datetime.substring(e+1,e+5);
            // String b=datetime.substring(s+1,s+3);
            // String c=datetime.substring(0,s);
            //System.out.println(a+"-"+b+"-"+c);
            strTime="20"+str[2]+"-"+str[0]+"-"+str[1];
        	}
        }
        return strTime;
    }
  public String getPosition() {
    return position;
  }
  public void setPosition(String position) {
    this.position = position;
  }
  public String getProform() {
    return proform;
  }
  public void setProform(String proform) {
    this.proform = proform;
  }
  public String getUpdatetime() {
    return updatetime;
  }
  public void setUpdatetime(String updatetime) {
    this.updatetime = updatetime;
  }
  public int getLoannum() {
    return loannum;
  }
  public void setLoannum(int loannum) {
    this.loannum = loannum;
  }
  public int getRepairnum() {
    return repairnum;
  }

  public String getMoney() {
    return money;
  }

  public String getSheetid() {
    return sheetid;
  }

  public int getProformFlag() {
    return proformFlag;
  }

  public void setRepairnum(int repairnum) {
    this.repairnum = repairnum;
  }

  public void setMoney(String money) {
    this.money = money;
  }

  public void setSheetid(String sheetid) {
    this.sheetid = sheetid;
  }

  public void setProformFlag(int proformFlag) {
    this.proformFlag = proformFlag;
  }

  public int getWarrantyFlag() {
    return warrantyFlag;
  }

  public void setWarrantyFlag(int warrantyFlag) {
    this.warrantyFlag = warrantyFlag;
  }

  public String getWarrantyName() {
    return warrantyName;
  }

  public void setWarrantyName(String warrantyName) {
    this.warrantyName = warrantyName;
  }

  public int getStopproductFlag() {
    return stopproductFlag;
  }

  public void setStopproductFlag(int stopproductFlag) {
    this.stopproductFlag = stopproductFlag;
  }

  public String getStopproductName() {
    return stopproductName;
  }

  public void setStopproductName(String stopproductName) {
    this.stopproductName = stopproductName;
  }

  public int getDeptId() {
    return deptId;
  }

  public void setDeptId(int deptId) {
    this.deptId = deptId;
  }

  public String getDeptName() {
    return deptName;
  }

  public int getBorrowState() {
    return borrowState;
  }

  public String getBorrowStateName() {
    return borrowStateName;
  }

  public int getNecodeid() {
    return necodeid;
  }

  public int getNettypeid() {
    return nettypeid;
  }

  public int getStateid() {
    return stateid;
  }

  public long getObjecttypeid() {
    return objecttypeid;
  }

  public int getStorageid() {
    return storageid;
  }

  public String getOrgSerialNo() {
    return orgSerialNo;
  }

  public String getOuttime() {
    return outtime;
  }

  public String getAccessory() {
    return accessory;
  }

  public String getThefile() {
    return thefile;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public void setBorrowState(int borrowState) {
    this.borrowState = borrowState;
  }

  public void setBorrowStateName(String borrowStateName) {
    this.borrowStateName = borrowStateName;
  }

  public void setNecodeid(int necodeid) {
    this.necodeid = necodeid;
  }

  public void setNettypeid(int nettypeid) {
    this.nettypeid = nettypeid;
  }

  public void setStateid(int stateid) {
    this.stateid = stateid;
  }

  public void setObjecttypeid(long objecttypeid) {
    this.objecttypeid = objecttypeid;
  }

  public void setStorageid(int storageid) {
    this.storageid = storageid;
  }

  public void setOrgSerialNo(String orgSerialNo) {
    this.orgSerialNo = orgSerialNo;
  }

  public void setOuttime(String outtime) {
    this.outtime = outtime;
  }

  public void setAccessory(String accessory) {
    this.accessory = accessory;
  }

  public void setThefile(String thefile) {
    this.thefile = thefile;
  }

public String getSubdept() {
	return subdept;
}

public void setSubdept(String subDept) {
	this.subdept = subDept;
}

public int getSubdeptid() {
	return subdeptid;
}

public void setSubdeptid(int subDeptid) {
	this.subdeptid = subdeptid;
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

public String getPartclass() {
	return partclass;
}

public void setPartclass(String partclass) {
	this.partclass = partclass;
}

public String getDescribe() {
	return describe;
}

public void setDescribe(String describe) {
	this.describe = describe;
}



}
