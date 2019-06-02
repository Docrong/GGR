// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AutoDealSOPVO.java

package com.boco.eoms.sheetflowline.vo;

import java.util.Date;

public class AutoDealSOPVO
{

	private String id;
	private String alarmId;
	private String alarmTitle;
	private String autoDealTask;
	private String autoDealMode;
	private String autoRule;
	private String operateUserId;
	private String faultDealResult;
	private String ifBigFault;
	private String faultReasonSort;
	private String faultReasonSubsection;
	private String ifCarryNetChange;
	private String ifLastPlan;
	private String ifAddCaseDataBase;
	private Date faultAvoidTime;
	private Date operRenewTime;
	private String affectTimeLength;
	private String faultReasonInfo;
	private String dealStep;
	private String remark;
	private Date faultdealTime;
	private String faultTreatment;
	private String tranferObject;
	private String ifMutualCommunication;
	private String ifSafe;
	private String ifGreatFault;
	private String faultFirstDealDesc;
	private String tranferReason;
	private String createUser;
	private Date createTime;
	private String special;
	private String vendor;
	private String equipmentType;
	private String sheetCount;

	public AutoDealSOPVO()
	{
	}

	public String getAffectTimeLength()
	{
		return affectTimeLength;
	}

	public void setAffectTimeLength(String affectTimeLength)
	{
		this.affectTimeLength = affectTimeLength;
	}

	public String getAlarmId()
	{
		return alarmId;
	}

	public void setAlarmId(String alarmId)
	{
		this.alarmId = alarmId;
	}

	public String getAlarmTitle()
	{
		return alarmTitle;
	}

	public void setAlarmTitle(String alarmTitle)
	{
		this.alarmTitle = alarmTitle;
	}

	public String getAutoDealMode()
	{
		return autoDealMode;
	}

	public void setAutoDealMode(String autoDealMode)
	{
		this.autoDealMode = autoDealMode;
	}

	public String getAutoDealTask()
	{
		return autoDealTask;
	}

	public void setAutoDealTask(String autoDealTask)
	{
		this.autoDealTask = autoDealTask;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public String getCreateUser()
	{
		return createUser;
	}

	public void setCreateUser(String createUser)
	{
		this.createUser = createUser;
	}

	public String getDealStep()
	{
		return dealStep;
	}

	public void setDealStep(String dealStep)
	{
		this.dealStep = dealStep;
	}

	public Date getFaultAvoidTime()
	{
		return faultAvoidTime;
	}

	public void setFaultAvoidTime(Date faultAvoidTime)
	{
		this.faultAvoidTime = faultAvoidTime;
	}

	public String getFaultDealResult()
	{
		return faultDealResult;
	}

	public void setFaultDealResult(String faultDealResult)
	{
		this.faultDealResult = faultDealResult;
	}

	public String getFaultFirstDealDesc()
	{
		return faultFirstDealDesc;
	}

	public void setFaultFirstDealDesc(String faultFirstDealDesc)
	{
		this.faultFirstDealDesc = faultFirstDealDesc;
	}

	public String getFaultReasonSort()
	{
		return faultReasonSort;
	}

	public void setFaultReasonSort(String faultReasonSort)
	{
		this.faultReasonSort = faultReasonSort;
	}

	public String getFaultReasonSubsection()
	{
		return faultReasonSubsection;
	}

	public void setFaultReasonSubsection(String faultReasonSubsection)
	{
		this.faultReasonSubsection = faultReasonSubsection;
	}

	public String getFaultReasonInfo()
	{
		return faultReasonInfo;
	}

	public void setFaultReasonInfo(String faultReasonInfo)
	{
		this.faultReasonInfo = faultReasonInfo;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getIfAddCaseDataBase()
	{
		return ifAddCaseDataBase;
	}

	public void setIfAddCaseDataBase(String ifAddCaseDataBase)
	{
		this.ifAddCaseDataBase = ifAddCaseDataBase;
	}

	public String getIfBigFault()
	{
		return ifBigFault;
	}

	public void setIfBigFault(String ifBigFault)
	{
		this.ifBigFault = ifBigFault;
	}

	public String getIfCarryNetChange()
	{
		return ifCarryNetChange;
	}

	public void setIfCarryNetChange(String ifCarryNetChange)
	{
		this.ifCarryNetChange = ifCarryNetChange;
	}

	public String getIfLastPlan()
	{
		return ifLastPlan;
	}

	public void setIfLastPlan(String ifLastPlan)
	{
		this.ifLastPlan = ifLastPlan;
	}

	public String getOperateUserId()
	{
		return operateUserId;
	}

	public void setOperateUserId(String operateUserId)
	{
		this.operateUserId = operateUserId;
	}

	public Date getOperRenewTime()
	{
		return operRenewTime;
	}

	public void setOperRenewTime(Date operRenewTime)
	{
		this.operRenewTime = operRenewTime;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getTranferObject()
	{
		return tranferObject;
	}

	public void setTranferObject(String tranferObject)
	{
		this.tranferObject = tranferObject;
	}

	public String getTranferReason()
	{
		return tranferReason;
	}

	public void setTranferReason(String tranferReason)
	{
		this.tranferReason = tranferReason;
	}

	public String getAutoRule()
	{
		return autoRule;
	}

	public void setAutoRule(String autoRule)
	{
		this.autoRule = autoRule;
	}

	public String getIfGreatFault()
	{
		return ifGreatFault;
	}

	public void setIfGreatFault(String ifGreatFault)
	{
		this.ifGreatFault = ifGreatFault;
	}

	public String getIfMutualCommunication()
	{
		return ifMutualCommunication;
	}

	public void setIfMutualCommunication(String ifMutualCommunication)
	{
		this.ifMutualCommunication = ifMutualCommunication;
	}

	public String getIfSafe()
	{
		return ifSafe;
	}

	public void setIfSafe(String ifSafe)
	{
		this.ifSafe = ifSafe;
	}

	public Date getFaultdealTime()
	{
		return faultdealTime;
	}

	public void setFaultdealTime(Date faultdealTime)
	{
		this.faultdealTime = faultdealTime;
	}

	public String getFaultTreatment()
	{
		return faultTreatment;
	}

	public void setFaultTreatment(String faultTreatment)
	{
		this.faultTreatment = faultTreatment;
	}

	public String getEquipmentType()
	{
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType)
	{
		this.equipmentType = equipmentType;
	}

	public String getSpecial()
	{
		return special;
	}

	public void setSpecial(String special)
	{
		this.special = special;
	}

	public String getVendor()
	{
		return vendor;
	}

	public void setVendor(String vendor)
	{
		this.vendor = vendor;
	}

	public String getSheetCount()
	{
		return sheetCount;
	}

	public void setSheetCount(String sheetCount)
	{
		this.sheetCount = sheetCount;
	}
}
