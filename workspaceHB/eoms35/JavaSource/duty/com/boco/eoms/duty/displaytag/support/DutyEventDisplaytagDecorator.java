package com.boco.eoms.duty.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.duty.model.TawRmDutyEvent;

public class DutyEventDisplaytagDecorator extends TableDecorator {

	public String getInputuser() {
		ITawSystemUserManager userManager = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");
		TawRmDutyEvent tawRmDutyEvent = (TawRmDutyEvent) getCurrentRowObject();
		String userName = "";
		userName = userManager.getUserByuserid(tawRmDutyEvent.getInputuser())
				.getUsername();
		return userName;
	}

	public String getFlag() {
		TawRmDutyEvent tawRmDutyEvent = (TawRmDutyEvent) getCurrentRowObject();
		String flagName = "";
		String flag = StaticMethod.null2String(tawRmDutyEvent.getFlag());
		if (flag.equals("1"))
			flagName = "★";
		if (flag.equals("2"))
			flagName = "★★";
		if (flag.equals("3"))
			flagName = "★★★";
		if (flag.equals("4"))
			flagName = "★★★★";
		if (flag.equals("5"))
			flagName = "★★★★★";
		return flagName;

	}

	public String getFaultType() {
		String faulttypeName = "";
		TawRmDutyEvent tawRmDutyEvent = (TawRmDutyEvent) getCurrentRowObject();
		String faulttype = StaticMethod.null2String(tawRmDutyEvent.getFaultType());
		if (faulttype.equals("1"))
			faulttypeName = "故障(传输)";
		if (faulttype.equals("2"))
			faulttypeName = "故障(移动)";
		if (faulttype.equals("3"))
			faulttypeName = "故障(数固)";
		return faulttypeName;
	}

	public String getComplateFlag() {
		TawRmDutyEvent tawRmDutyEvent = (TawRmDutyEvent) getCurrentRowObject();
		String complateFlag = StaticMethod.null2String(tawRmDutyEvent.getComplateFlag());
		String complateFlagName = "";
		if (complateFlag.equals("1"))
			complateFlagName = "完成";
		if (complateFlag.equals("2"))
			complateFlagName = "未完成";
		return complateFlagName;
	}
	public String getPubstatus() {
		TawRmDutyEvent tawRmDutyEvent = (TawRmDutyEvent) getCurrentRowObject();
		String Pubstatus = StaticMethod.null2String(tawRmDutyEvent.getPubstatus());
		String PubstatusName = "";
		if (Pubstatus.equals("0"))
			PubstatusName = "未发布";
		if (Pubstatus.equals("1"))
			PubstatusName = "已发布";
		if (Pubstatus.equals("2"))
			PubstatusName = "不可发布";
		return PubstatusName;
	}
}
