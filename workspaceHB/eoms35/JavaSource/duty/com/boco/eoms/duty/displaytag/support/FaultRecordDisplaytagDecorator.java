package com.boco.eoms.duty.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.duty.faultrecord.model.Faultrecord;

public class FaultRecordDisplaytagDecorator extends TableDecorator {

	public String getUserId(){
		ITawSystemUserManager userManager=(ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		Faultrecord faultrecord=(Faultrecord)getCurrentRowObject();
		String userName="";
		userName=userManager.getUserByuserid(faultrecord.getUserId()).getUsername();
		return userName;
	}

	public String getDeptId() {
		ITawSystemDeptManager deptManager=(ITawSystemDeptManager)ApplicationContextHolder.getInstance().getBean("ItawSystemDeptManager");
		Faultrecord faultrecord=(Faultrecord)getCurrentRowObject();
		String deptName="";
		deptName=deptManager.getDeptinfobydeptid(faultrecord.getDeptId(),"0").getDeptName();
		return deptName;
	}

	/**
	 * 设备厂商
	 * @return
	 */
	public String getDevVendor(){
		Faultrecord faultrecord = (Faultrecord) getCurrentRowObject();
		String devVendorName = "";
		try {
			devVendorName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-plancontent",
									"devVendor"), Integer.toString(faultrecord.getDevVendor()));
		} catch (DictServiceException e) {
			devVendorName = Util.idNoName();
		}
		return devVendorName;
	}

	/**
	 * 设备类型
	 * @return
	 */
	public String getDevicetype(){
		Faultrecord faultrecord = (Faultrecord) getCurrentRowObject();
		String devicetypeName = "";
		try {
			devicetypeName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-plancontent",
									"devicetype"), Integer.toString(faultrecord.getDevicetype()));
		} catch (DictServiceException e) {
			devicetypeName = Util.idNoName();
		}
		return devicetypeName;
	}

	/**
	 * 故障单元级别
	 * @return
	 */
	public String getFaultUnitLevel(){
		Faultrecord faultrecord = (Faultrecord) getCurrentRowObject();
		String faultUnitLevelName = "";
		try {
			faultUnitLevelName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-plancontent",
									"faultUnitLevel"), Integer.toString(faultrecord.getFaultUnitLevel()));
		} catch (DictServiceException e) {
			faultUnitLevelName = Util.idNoName();
		}
		return faultUnitLevelName;
	}

	/**
	 * 故障级别
	 * @return
	 */
	public String getFaultLevel(){
		Faultrecord faultrecord = (Faultrecord) getCurrentRowObject();
		String faultLevelName = "";
		try {
			faultLevelName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-plancontent",
									"faultLevel"), Integer.toString(faultrecord.getFaultLevel()));
		} catch (DictServiceException e) {
			faultLevelName = Util.idNoName();
		}
		return faultLevelName;
	}

	/**
	 * 解决类别
	 * @return
	 */
	public String getProblemSolveInfo(){
		Faultrecord faultrecord = (Faultrecord) getCurrentRowObject();
		String problemSolveInfoName = "";
		try {
			problemSolveInfoName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-plancontent",
									"problemSolveInfo"), Integer.toString(faultrecord.getProblemSolveInfo()));
		} catch (DictServiceException e) {
			problemSolveInfoName = Util.idNoName();
		}
		return problemSolveInfoName;
	}
}
