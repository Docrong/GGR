// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonFaultMain.java

package com.boco.eoms.sheet.commonfault.model;

import com.boco.eoms.sheet.base.model.BaseMain;
import java.util.Date;

public class CommonFaultMain extends BaseMain
{

	private String mainApplySheetId;
	private String mainIfUrgentFault;
	private String mainUrgentFaultLog;
	private String mainIfSafe;
	private String mainIfMutualCommunication;
	private String mainFaultResponseLevel;
	private String mainAlarmNum;
	private String mainAlarmState;
	private String mainAlarmDesc;
	private Date mainAlarmSolveDate;
	private String mainNetSortOne;
	private String mainNetSortTwo;
	private String mainNetSortThree;
	private String mainEquipmentFactory;
	private String mainEquipmentName;
	private String mainEquipmentModel;
	private Date mainFaultGenerantTime;
	private String mainFaultGenerantPriv;
	private String mainFaultGenerantCity;
	private String mainIfAffectOperation;
	private String mainAffectArea;
	private Date mainAffectStartTime;
	private String mainFaultDiscoverableMode;
	private String mainFaultFirstDealDesc;
	private String mainFaultDesc;
	private String mainSendMode;
	private String mainAlarmId;
	private String mainAlarmSource;
	private String mainAlarmLogicSort;
	private String mainAlarmLogicSortSub;
	private String mainFaultSpecialty;
	private String mainEquipmentType;
	private String mainNetName;
	private Date mainCompleteLimitT1;
	private Date mainCompleteLimitT2;
	private Date mainCompleteLimitT3;
	private Integer mainRejectCount;
	private String mainDelayFlag;
	private Integer mainIfRecord;
	private String mainIfCheck;
	private String mainCheckResult;
	private String mainCheckIdea;
	private String mainIfAlarmSend;
	private String mainAlarmLevel;
	private String mainPretreatment;
	private String linkIfMutualCommunication;
	private String linkIfSafe;
	private String linkFaultDesc;
	private String linkFaultFirstDealDesc;
	private String linkIfGreatFault;
	private String firstNodeAccessories;
	private String mainIsPack;
	private String ifAgent;
	private int reportFlag;
	private String revert;
	private Date mainDetectFaultTime;
	private String perAllocatedUser;
	private String mainNeId;
	private String mainIfCenterMonitor;
	private String mainSendSystem;
	private String intelligentPreSolve;
	private String mainManualPreSolve;
	private String mainCheckStatus;
	private Date mainCheckTime;
	private Date mainApplyCheckTime;
	private String mainT2ApplyCheckTime;
	private String mainCheckRejected;
	private String mainCheckDealer;
	private String mainT2Applicant;
	private String mainT1ReplyTime;
	private String mainApplyReason;
	private int checkCount;
	private String qcRemark;
	private int qcMark;
	private String mainPretResult;
	private String mainPretResultOne;
	private String mainPretResultTwo;
	private String mainPretResultThree;
	private String mainPretResultFour;
	private String mainIfReply;
	private String mainIfCombine;
	private String netOptimization;
	private String rejectAccessories;
	private String applyAccessories;
	private String mainInterfaceCaller;
	private String mainInterfaceCallerGZ;
	
	private String equipmentName;
	
	private String mainT2RejectionNum;
	
	private String mainTowerId;
	
	private String mainPretResultId;
	
	private String mainTownerName;//站址名称
	private String mainTownerDeviceId;//铁塔站址资源编码
	private String mainTownerRoomId;//铁塔机房资源编码
	private String mainTownerFlag;//铁塔标记是否隐藏待办
	private String mainIFTowner;//标记是否为铁塔工单
	
	private String mainLocatinf;//定位信息
	
	private String mainCitySubrole;//移动、铁塔驳回
	private String mainReserved1;//告警区县
	private String mainReserved2;
	private String mainReserved3;
	private String mainReserved4;
	private String mainReserved5;
	
	private String mainOtherSubrole;//其它地市班组
	private String mainThisSubrole;//当前地市班组
	private String mainIsOther;//标记是否是其他班组
	
	public CommonFaultMain()
	{
	}

	public String getQcRemark()
	{
		return qcRemark;
	}

	public String getMainCheckDealer()
	{
		return mainCheckDealer;
	}

	public void setMainCheckDealer(String mainCheckDealer)
	{
		this.mainCheckDealer = mainCheckDealer;
	}

	public String getMainT2ApplyCheckTime()
	{
		return mainT2ApplyCheckTime;
	}

	public void setMainT2ApplyCheckTime(String mainT2ApplyCheckTime)
	{
		this.mainT2ApplyCheckTime = mainT2ApplyCheckTime;
	}

	public String getMainT1ReplyTime()
	{
		return mainT1ReplyTime;
	}

	public void setMainT1ReplyTime(String mainT1ReplyTime)
	{
		this.mainT1ReplyTime = mainT1ReplyTime;
	}

	public String getMainT2Applicant()
	{
		return mainT2Applicant;
	}

	public void setMainT2Applicant(String mainT2Applicant)
	{
		this.mainT2Applicant = mainT2Applicant;
	}

	public void setQcRemark(String qcRemark)
	{
		this.qcRemark = qcRemark;
	}

	public String getNetOptimization()
	{
		return netOptimization;
	}

	public String getMainIfCombine()
	{
		return mainIfCombine;
	}

	public void setMainIfCombine(String mainIfCombine)
	{
		this.mainIfCombine = mainIfCombine;
	}

	public String getMainIfReply()
	{
		return mainIfReply;
	}

	public void setMainIfReply(String mainIfReply)
	{
		this.mainIfReply = mainIfReply;
	}

	public void setNetOptimization(String netOptimization)
	{
		this.netOptimization = netOptimization;
	}

	public int getQcMark()
	{
		return qcMark;
	}

	public void setQcMark(int qcMark)
	{
		this.qcMark = qcMark;
	}

	public String getMainCheckStatus()
	{
		return mainCheckStatus;
	}

	public void setMainCheckStatus(String mainCheckStatus)
	{
		this.mainCheckStatus = mainCheckStatus;
	}

	public Date getMainCheckTime()
	{
		return mainCheckTime;
	}

	public void setMainCheckTime(Date mainCheckTime)
	{
		this.mainCheckTime = mainCheckTime;
	}

	public String getLinkIfGreatFault()
	{
		return linkIfGreatFault;
	}

	public void setLinkIfGreatFault(String linkIfGreatFault)
	{
		this.linkIfGreatFault = linkIfGreatFault;
	}

	public String getMainIfAlarmSend()
	{
		return mainIfAlarmSend;
	}

	public void setMainIfAlarmSend(String mainIfAlarmSend)
	{
		this.mainIfAlarmSend = mainIfAlarmSend;
	}

	public String getMainCheckIdea()
	{
		return mainCheckIdea;
	}

	public void setMainCheckIdea(String mainCheckIdea)
	{
		this.mainCheckIdea = mainCheckIdea;
	}

	public String getMainCheckResult()
	{
		return mainCheckResult;
	}

	public void setMainCheckResult(String mainCheckResult)
	{
		this.mainCheckResult = mainCheckResult;
	}

	public String getMainIfCheck()
	{
		return mainIfCheck;
	}

	public void setMainIfCheck(String mainIfCheck)
	{
		this.mainIfCheck = mainIfCheck;
	}

	public Integer getMainIfRecord()
	{
		return mainIfRecord;
	}

	public void setMainIfRecord(Integer mainIfRecord)
	{
		this.mainIfRecord = mainIfRecord;
	}

	public String getMainDelayFlag()
	{
		return mainDelayFlag;
	}

	public void setMainDelayFlag(String mainDelayFlag)
	{
		this.mainDelayFlag = mainDelayFlag;
	}

	public Integer getMainRejectCount()
	{
		return mainRejectCount;
	}

	public void setMainRejectCount(Integer mainRejectCount)
	{
		this.mainRejectCount = mainRejectCount;
	}

	public String getMainAffectArea()
	{
		return mainAffectArea;
	}

	public void setMainAffectArea(String mainAffectArea)
	{
		this.mainAffectArea = mainAffectArea;
	}

	public Date getMainAffectStartTime()
	{
		return mainAffectStartTime;
	}

	public void setMainAffectStartTime(Date mainAffectStartTime)
	{
		this.mainAffectStartTime = mainAffectStartTime;
	}

	public String getMainAlarmDesc()
	{
		return mainAlarmDesc;
	}

	public void setMainAlarmDesc(String mainAlarmDesc)
	{
		this.mainAlarmDesc = mainAlarmDesc;
	}

	public String getMainAlarmNum()
	{
		return mainAlarmNum;
	}

	public void setMainAlarmNum(String mainAlarmNum)
	{
		this.mainAlarmNum = mainAlarmNum;
	}

	public Date getMainAlarmSolveDate()
	{
		return mainAlarmSolveDate;
	}

	public void setMainAlarmSolveDate(Date mainAlarmSolveDate)
	{
		this.mainAlarmSolveDate = mainAlarmSolveDate;
	}

	public String getMainAlarmState()
	{
		return mainAlarmState;
	}

	public void setMainAlarmState(String mainAlarmState)
	{
		this.mainAlarmState = mainAlarmState;
	}

	public String getMainApplySheetId()
	{
		return mainApplySheetId;
	}

	public void setMainApplySheetId(String mainApplySheetId)
	{
		this.mainApplySheetId = mainApplySheetId;
	}

	public String getMainEquipmentFactory()
	{
		return mainEquipmentFactory;
	}

	public void setMainEquipmentFactory(String mainEquipmentFactory)
	{
		this.mainEquipmentFactory = mainEquipmentFactory;
	}

	public String getMainEquipmentModel()
	{
		return mainEquipmentModel;
	}

	public void setMainEquipmentModel(String mainEquipmentModel)
	{
		this.mainEquipmentModel = mainEquipmentModel;
	}

	public String getMainEquipmentName()
	{
		return mainEquipmentName;
	}

	public void setMainEquipmentName(String mainEquipmentName)
	{
		this.mainEquipmentName = mainEquipmentName;
	}

	public String getMainFaultDesc()
	{
		return mainFaultDesc;
	}

	public void setMainFaultDesc(String mainFaultDesc)
	{
		this.mainFaultDesc = mainFaultDesc;
	}

	public String getMainFaultDiscoverableMode()
	{
		return mainFaultDiscoverableMode;
	}

	public void setMainFaultDiscoverableMode(String mainFaultDiscoverableMode)
	{
		this.mainFaultDiscoverableMode = mainFaultDiscoverableMode;
	}

	public String getMainFaultFirstDealDesc()
	{
		return mainFaultFirstDealDesc;
	}

	public void setMainFaultFirstDealDesc(String mainFaultFirstDealDesc)
	{
		this.mainFaultFirstDealDesc = mainFaultFirstDealDesc;
	}

	public String getMainFaultGenerantCity()
	{
		return mainFaultGenerantCity;
	}

	public void setMainFaultGenerantCity(String mainFaultGenerantCity)
	{
		this.mainFaultGenerantCity = mainFaultGenerantCity;
	}

	public String getMainFaultGenerantPriv()
	{
		return mainFaultGenerantPriv;
	}

	public void setMainFaultGenerantPriv(String mainFaultGenerantPriv)
	{
		this.mainFaultGenerantPriv = mainFaultGenerantPriv;
	}

	public Date getMainFaultGenerantTime()
	{
		return mainFaultGenerantTime;
	}

	public void setMainFaultGenerantTime(Date mainFaultGenerantTime)
	{
		this.mainFaultGenerantTime = mainFaultGenerantTime;
	}

	public String getMainFaultResponseLevel()
	{
		return mainFaultResponseLevel;
	}

	public void setMainFaultResponseLevel(String mainFaultResponseLevel)
	{
		this.mainFaultResponseLevel = mainFaultResponseLevel;
	}

	public String getMainIfAffectOperation()
	{
		return mainIfAffectOperation;
	}

	public void setMainIfAffectOperation(String mainIfAffectOperation)
	{
		this.mainIfAffectOperation = mainIfAffectOperation;
	}

	public String getMainIfMutualCommunication()
	{
		return mainIfMutualCommunication;
	}

	public void setMainIfMutualCommunication(String mainIfMutualCommunication)
	{
		this.mainIfMutualCommunication = mainIfMutualCommunication;
	}

	public String getMainIfSafe()
	{
		return mainIfSafe;
	}

	public void setMainIfSafe(String mainIfSafe)
	{
		this.mainIfSafe = mainIfSafe;
	}

	public String getMainIfUrgentFault()
	{
		return mainIfUrgentFault;
	}

	public void setMainIfUrgentFault(String mainIfUrgentFault)
	{
		this.mainIfUrgentFault = mainIfUrgentFault;
	}

	public String getMainNetSortOne()
	{
		return mainNetSortOne;
	}

	public void setMainNetSortOne(String mainNetSortOne)
	{
		this.mainNetSortOne = mainNetSortOne;
	}

	public String getMainNetSortThree()
	{
		return mainNetSortThree;
	}

	public void setMainNetSortThree(String mainNetSortThree)
	{
		this.mainNetSortThree = mainNetSortThree;
	}

	public String getMainNetSortTwo()
	{
		return mainNetSortTwo;
	}

	public void setMainNetSortTwo(String mainNetSortTwo)
	{
		this.mainNetSortTwo = mainNetSortTwo;
	}

	public String getMainUrgentFaultLog()
	{
		return mainUrgentFaultLog;
	}

	public void setMainUrgentFaultLog(String mainUrgentFaultLog)
	{
		this.mainUrgentFaultLog = mainUrgentFaultLog;
	}

	public String getMainSendMode()
	{
		return mainSendMode;
	}

	public void setMainSendMode(String mainSendMode)
	{
		this.mainSendMode = mainSendMode;
	}

	public String getMainAlarmId()
	{
		return mainAlarmId;
	}

	public void setMainAlarmId(String mainAlarmId)
	{
		this.mainAlarmId = mainAlarmId;
	}

	public String getMainAlarmLogicSort()
	{
		return mainAlarmLogicSort;
	}

	public void setMainAlarmLogicSort(String mainAlarmLogicSort)
	{
		this.mainAlarmLogicSort = mainAlarmLogicSort;
	}

	public String getMainAlarmLogicSortSub()
	{
		return mainAlarmLogicSortSub;
	}

	public void setMainAlarmLogicSortSub(String mainAlarmLogicSortSub)
	{
		this.mainAlarmLogicSortSub = mainAlarmLogicSortSub;
	}

	public String getMainAlarmSource()
	{
		return mainAlarmSource;
	}

	public void setMainAlarmSource(String mainAlarmSource)
	{
		this.mainAlarmSource = mainAlarmSource;
	}

	public Date getMainCompleteLimitT1()
	{
		return mainCompleteLimitT1;
	}

	public void setMainCompleteLimitT1(Date mainCompleteLimitT1)
	{
		this.mainCompleteLimitT1 = mainCompleteLimitT1;
	}

	public Date getMainCompleteLimitT2()
	{
		return mainCompleteLimitT2;
	}

	public void setMainCompleteLimitT2(Date mainCompleteLimitT2)
	{
		this.mainCompleteLimitT2 = mainCompleteLimitT2;
	}

	public Date getMainCompleteLimitT3()
	{
		return mainCompleteLimitT3;
	}

	public void setMainCompleteLimitT3(Date mainCompleteLimitT3)
	{
		this.mainCompleteLimitT3 = mainCompleteLimitT3;
	}

	public String getMainEquipmentType()
	{
		return mainEquipmentType;
	}

	public void setMainEquipmentType(String mainEquipmentType)
	{
		this.mainEquipmentType = mainEquipmentType;
	}

	public String getMainFaultSpecialty()
	{
		return mainFaultSpecialty;
	}

	public void setMainFaultSpecialty(String mainFaultSpecialty)
	{
		this.mainFaultSpecialty = mainFaultSpecialty;
	}

	public String getMainNetName()
	{
		return mainNetName;
	}

	public void setMainNetName(String mainNetName)
	{
		this.mainNetName = mainNetName;
	}

	public String getMainPretreatment()
	{
		return mainPretreatment;
	}

	public void setMainPretreatment(String mainPretreatment)
	{
		this.mainPretreatment = mainPretreatment;
	}

	public String getFirstNodeAccessories()
	{
		return firstNodeAccessories;
	}

	public void setFirstNodeAccessories(String firstNodeAccessories)
	{
		this.firstNodeAccessories = firstNodeAccessories;
	}

	public String getLinkFaultDesc()
	{
		return linkFaultDesc;
	}

	public void setLinkFaultDesc(String linkFaultDesc)
	{
		this.linkFaultDesc = linkFaultDesc;
	}

	public String getLinkFaultFirstDealDesc()
	{
		return linkFaultFirstDealDesc;
	}

	public void setLinkFaultFirstDealDesc(String linkFaultFirstDealDesc)
	{
		this.linkFaultFirstDealDesc = linkFaultFirstDealDesc;
	}

	public String getLinkIfMutualCommunication()
	{
		return linkIfMutualCommunication;
	}

	public void setLinkIfMutualCommunication(String linkIfMutualCommunication)
	{
		this.linkIfMutualCommunication = linkIfMutualCommunication;
	}

	public String getLinkIfSafe()
	{
		return linkIfSafe;
	}

	public void setLinkIfSafe(String linkIfSafe)
	{
		this.linkIfSafe = linkIfSafe;
	}

	public String getMainAlarmLevel()
	{
		return mainAlarmLevel;
	}

	public void setMainAlarmLevel(String mainAlarmLevel)
	{
		this.mainAlarmLevel = mainAlarmLevel;
	}

	public int getReportFlag()
	{
		return reportFlag;
	}

	public void setReportFlag(int reportFlag)
	{
		this.reportFlag = reportFlag;
	}

	public String getMainIsPack()
	{
		return mainIsPack;
	}

	public void setMainIsPack(String mainIsPack)
	{
		this.mainIsPack = mainIsPack;
	}

	public String getIfAgent()
	{
		return ifAgent;
	}

	public void setIfAgent(String ifAgent)
	{
		this.ifAgent = ifAgent;
	}

	public String getRevert()
	{
		return revert;
	}

	public void setRevert(String revert)
	{
		this.revert = revert;
	}

	public Date getMainDetectFaultTime()
	{
		return mainDetectFaultTime;
	}

	public void setMainDetectFaultTime(Date mainDetectFaultTime)
	{
		this.mainDetectFaultTime = mainDetectFaultTime;
	}

	public String getPerAllocatedUser()
	{
		return perAllocatedUser;
	}

	public void setPerAllocatedUser(String perAllocatedUser)
	{
		this.perAllocatedUser = perAllocatedUser;
	}

	public String getMainIfCenterMonitor()
	{
		return mainIfCenterMonitor;
	}

	public void setMainIfCenterMonitor(String mainIfCenterMonitor)
	{
		this.mainIfCenterMonitor = mainIfCenterMonitor;
	}

	public String getMainNeId()
	{
		return mainNeId;
	}

	public void setMainNeId(String mainNeId)
	{
		this.mainNeId = mainNeId;
	}

	public String getMainSendSystem()
	{
		return mainSendSystem;
	}

	public void setMainSendSystem(String mainSendSystem)
	{
		this.mainSendSystem = mainSendSystem;
	}

	public String getMainPretResult()
	{
		return mainPretResult;
	}

	public void setMainPretResult(String mainPretResult)
	{
		this.mainPretResult = mainPretResult;
	}

	public String getMainPretResultFour()
	{
		return mainPretResultFour;
	}

	public void setMainPretResultFour(String mainPretResultFour)
	{
		this.mainPretResultFour = mainPretResultFour;
	}

	public String getMainPretResultOne()
	{
		return mainPretResultOne;
	}

	public void setMainPretResultOne(String mainPretResultOne)
	{
		this.mainPretResultOne = mainPretResultOne;
	}

	public String getMainPretResultThree()
	{
		return mainPretResultThree;
	}

	public void setMainPretResultThree(String mainPretResultThree)
	{
		this.mainPretResultThree = mainPretResultThree;
	}

	public String getMainPretResultTwo()
	{
		return mainPretResultTwo;
	}

	public void setMainPretResultTwo(String mainPretResultTwo)
	{
		this.mainPretResultTwo = mainPretResultTwo;
	}

	public String getMainCheckRejected()
	{
		return mainCheckRejected;
	}

	public void setMainCheckRejected(String mainCheckRejected)
	{
		this.mainCheckRejected = mainCheckRejected;
	}

	public int getCheckCount()
	{
		return checkCount;
	}

	public void setCheckCount(int checkCount)
	{
		this.checkCount = checkCount;
	}

	public Date getMainApplyCheckTime()
	{
		return mainApplyCheckTime;
	}

	public void setMainApplyCheckTime(Date mainApplyCheckTime)
	{
		this.mainApplyCheckTime = mainApplyCheckTime;
	}

	public String getIntelligentPreSolve()
	{
		return intelligentPreSolve;
	}

	public void setIntelligentPreSolve(String intelligentPreSolve)
	{
		this.intelligentPreSolve = intelligentPreSolve;
	}

	public String getMainManualPreSolve()
	{
		return mainManualPreSolve;
	}

	public void setMainManualPreSolve(String mainManualPreSolve)
	{
		this.mainManualPreSolve = mainManualPreSolve;
	}

	public String getApplyAccessories()
	{
		return applyAccessories;
	}

	public void setApplyAccessories(String applyAccessories)
	{
		this.applyAccessories = applyAccessories;
	}

	public String getRejectAccessories()
	{
		return rejectAccessories;
	}

	public void setRejectAccessories(String rejectAccessories)
	{
		this.rejectAccessories = rejectAccessories;
	}

	public String getMainApplyReason()
	{
		return mainApplyReason;
	}

	public void setMainApplyReason(String mainApplyReason)
	{
		this.mainApplyReason = mainApplyReason;
	}

	public String getMainInterfaceCaller() {
		return mainInterfaceCaller;
	}

	public void setMainInterfaceCaller(String mainInterfaceCaller) {
		this.mainInterfaceCaller = mainInterfaceCaller;
	}

	public String getMainInterfaceCallerGZ() {
		return mainInterfaceCallerGZ;
	}

	public void setMainInterfaceCallerGZ(String mainInterfaceCallerGZ) {
		this.mainInterfaceCallerGZ = mainInterfaceCallerGZ;
	}

	public String getMainT2RejectionNum() {
		return mainT2RejectionNum;
	}

	public void setMainT2RejectionNum(String mainT2RejectionNum) {
		this.mainT2RejectionNum = mainT2RejectionNum;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getMainTowerId() {
		return mainTowerId;
	}

	public void setMainTowerId(String mainTowerId) {
		this.mainTowerId = mainTowerId;
	}

	public String getMainPretResultId() {
		return mainPretResultId;
	}

	public void setMainPretResultId(String mainPretResultId) {
		this.mainPretResultId = mainPretResultId;
	}

	public String getMainIFTowner() {
		return mainIFTowner;
	}

	public void setMainIFTowner(String mainIFTowner) {
		this.mainIFTowner = mainIFTowner;
	}

	public String getMainTownerDeviceId() {
		return mainTownerDeviceId;
	}

	public void setMainTownerDeviceId(String mainTownerDeviceId) {
		this.mainTownerDeviceId = mainTownerDeviceId;
	}

	public String getMainTownerFlag() {
		return mainTownerFlag;
	}

	public void setMainTownerFlag(String mainTownerFlag) {
		this.mainTownerFlag = mainTownerFlag;
	}

	public String getMainTownerName() {
		return mainTownerName;
	}

	public void setMainTownerName(String mainTownerName) {
		this.mainTownerName = mainTownerName;
	}

	public String getMainTownerRoomId() {
		return mainTownerRoomId;
	}

	public void setMainTownerRoomId(String mainTownerRoomId) {
		this.mainTownerRoomId = mainTownerRoomId;
	}

	public String getMainLocatinf() {
		return mainLocatinf;
	}

	public void setMainLocatinf(String mainLocatinf) {
		this.mainLocatinf = mainLocatinf;
	}

	public String getMainCitySubrole() {
		return mainCitySubrole;
	}

	public void setMainCitySubrole(String mainCitySubrole) {
		this.mainCitySubrole = mainCitySubrole;
	}

	public String getMainReserved1() {
		return mainReserved1;
	}

	public void setMainReserved1(String mainReserved1) {
		this.mainReserved1 = mainReserved1;
	}

	public String getMainReserved2() {
		return mainReserved2;
	}

	public void setMainReserved2(String mainReserved2) {
		this.mainReserved2 = mainReserved2;
	}

	public String getMainReserved3() {
		return mainReserved3;
	}

	public void setMainReserved3(String mainReserved3) {
		this.mainReserved3 = mainReserved3;
	}

	public String getMainReserved4() {
		return mainReserved4;
	}

	public void setMainReserved4(String mainReserved4) {
		this.mainReserved4 = mainReserved4;
	}

	public String getMainReserved5() {
		return mainReserved5;
	}

	public void setMainReserved5(String mainReserved5) {
		this.mainReserved5 = mainReserved5;
	}

	public String getMainIsOther() {
		return mainIsOther;
	}

	public void setMainIsOther(String mainIsOther) {
		this.mainIsOther = mainIsOther;
	}

	public String getMainOtherSubrole() {
		return mainOtherSubrole;
	}

	public void setMainOtherSubrole(String mainOtherSubrole) {
		this.mainOtherSubrole = mainOtherSubrole;
	}

	public String getMainThisSubrole() {
		return mainThisSubrole;
	}

	public void setMainThisSubrole(String mainThisSubrole) {
		this.mainThisSubrole = mainThisSubrole;
	}
}
