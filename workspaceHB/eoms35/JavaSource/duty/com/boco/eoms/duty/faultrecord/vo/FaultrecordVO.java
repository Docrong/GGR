package com.boco.eoms.duty.faultrecord.vo;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;

/**
 * <p>Title: ���ϼ�¼</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author zhangxiaobo
 * @version 1.0
 */

public class FaultrecordVO {

    public FaultrecordVO() {
    }
    
	ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
	.getInstance().getBean("itawSystemUserManager");
	ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) ApplicationContextHolder
	.getInstance().getBean("ItawSystemDeptManager");
    
    /** ������� */
    private String id;
    
    /** ������ */
    private String userId;
    
    private String userName;
    
    /** �������� */
    private String deptId;
    
    private String deptName;
    
    /** ����ʱ�� */
    private String insertTime;

    /** ɾ���� */
    private int delFlag;

    /** ���� */
    private String accessories;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        this.userName = userMgr.getUserByuserid(this.userId).getUsername();
        return userName;
    }

    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        this.deptName = deptMgr.getDeptinfobydeptid(this.deptId,"0").getDeptName();
        return deptName;
    }

    public String getInsertTime() {
        return this.insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public int getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public String getAccessories() {
        return this.accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }


  /*****************************************************************
   * �����Ǹ��?���е�����
   *****************************************************************/


    /** �澯ʱ�� */
    private String startTime;
    
    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
  

    /** ��Ԫ��� */
    private String networkName;
    
    public String getNetworkName() {
        return this.networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }
  

    /** �豸���� */
    private int devVendor;
    
    public int getDevVendor() {
        return this.devVendor;
    }

    public void setDevVendor(int devVendor) {
        this.devVendor = devVendor;
    }
  
    private String devVendorName = "";

    public String getDevVendorName() {
		try {
			devVendorName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-plancontent",
									"devVendor"), Integer.toString(this.getDevVendor()));
		} catch (DictServiceException e) {
			devVendorName = Util.idNoName();
		}
        return this.devVendorName;
    }

    /** �豸���� */
    private int devicetype;
    
    public int getDevicetype() {
        return this.devicetype;
    }

    public void setDevicetype(int devicetype) {
        this.devicetype = devicetype;
    }
  
    private String devicetypeName = "";

    public String getDevicetypeName() {
		try {
			devicetypeName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-plancontent",
									"devicetype"), Integer.toString(this.getDevicetype()));
		} catch (DictServiceException e) {
			devicetypeName = Util.idNoName();
		}
        return this.devicetypeName;
    }

    /** ���ϵ�Ԫ���� */
    private int faultUnitLevel;
    
    public int getFaultUnitLevel() {
        return this.faultUnitLevel;
    }

    public void setFaultUnitLevel(int faultUnitLevel) {
        this.faultUnitLevel = faultUnitLevel;
    }
  
    private String faultUnitLevelName = "";

    public String getFaultUnitLevelName() {
		try {
			faultUnitLevelName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-plancontent",
									"faultUnitLevel"), Integer.toString(this.getFaultUnitLevel()));
		} catch (DictServiceException e) {
			faultUnitLevelName = Util.idNoName();
		}
        return this.faultUnitLevelName;
    }

    /** ���ϼ��� */
    private int faultLevel;
    
    public int getFaultLevel() {
        return this.faultLevel;
    }

    public void setFaultLevel(int faultLevel) {
        this.faultLevel = faultLevel;
    }
  
    private String faultLevelName = "";

    public String getFaultLevelName() {
		try {
			faultLevelName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-plancontent",
									"faultLevel"), Integer.toString(this.getFaultLevel()));
		} catch (DictServiceException e) {
			faultLevelName = Util.idNoName();
		}
        return this.faultLevelName;
    }

    /** �������� */
    private String faultContent;
    
    public String getFaultContent() {
        return this.faultContent;
    }

    public void setFaultContent(String faultContent) {
        this.faultContent = faultContent;
    }
  

    /** ���ϴ������ */
    private String faultResult;
    
    public String getFaultResult() {
        return this.faultResult;
    }

    public void setFaultResult(String faultResult) {
        this.faultResult = faultResult;
    }
  

    /** �����걨�� */
    private String declareUser;
    
    public String getDeclareUser() {
        return this.declareUser;
    }

    public void setDeclareUser(String declareUser) {
        this.declareUser = declareUser;
    }
  

    /** �걨ʱ�� */
    private String declareTime;
    
    public String getDeclareTime() {
        return this.declareTime;
    }

    public void setDeclareTime(String declareTime) {
        this.declareTime = declareTime;
    }
  

    /** ���ϴ����� */
    private String dealUser;
    
    public String getDealUser() {
        return this.dealUser;
    }

    public void setDealUser(String dealUser) {
        this.dealUser = dealUser;
    }
  

    /** ����ʱ�� */
    private String dealTime;
    
    public String getDealTime() {
        return this.dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }
  

    /** ���ϻָ� */
    private String endTime;
    
    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
  

    /** ������ */
    private int problemSolveInfo;
    
    public int getProblemSolveInfo() {
        return this.problemSolveInfo;
    }

    public void setProblemSolveInfo(int problemSolveInfo) {
        this.problemSolveInfo = problemSolveInfo;
    }
  
    private String problemSolveInfoName = "";

    public String getProblemSolveInfoName() {
		try {
			problemSolveInfoName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-plancontent",
									"problemSolveInfo"), Integer.toString(this.getProblemSolveInfo()));
		} catch (DictServiceException e) {
			problemSolveInfoName = Util.idNoName();
		}
        return this.problemSolveInfoName;
    }

    /** ������ʱ */
    private String totalTime;
    
    public String getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }
  

    /** ҵ���ж� */
    private String operHaltTime;
    
    public String getOperHaltTime() {
        return this.operHaltTime;
    }

    public void setOperHaltTime(String operHaltTime) {
        this.operHaltTime = operHaltTime;
    }
  

    /** ��ע */
    private String remark;
    
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
  
}