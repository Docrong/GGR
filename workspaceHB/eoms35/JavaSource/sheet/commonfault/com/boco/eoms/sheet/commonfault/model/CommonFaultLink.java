// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonFaultLink.java

package com.boco.eoms.sheet.commonfault.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import java.util.Date;

public class CommonFaultLink extends BaseLink
{

	private String linkFaultResponseLevel;
	private String linkUrgentFaultLog;
	private String linkIfMutualCommunication;
	private String linkIfSafe;
	private String linkIfExcuteNetChange;
	private String linkFaultDealResult;
	private Date linkFaultAvoidTime;
	private Date linkOperRenewTime;
	private String linkAffectTimeLength;
	private String linkFaultReason;
	private String linkDealStep;
	private String linkUntreadReason;
	private String linkIfUrgentFault;
	private String linkTransmitReason;
	private String linkFaultFirstDealDesc;
	private String linkFaultDesc;
	private String linkFaultReasonSort;
	private String linkFaultReasonSubsection;
	private String linkIfFinallySolveProject;
	private String linkIfAddCaseDataBase;
	private String linkTransmitContent;
	private String linkExamineContent;
	private String linkIfDeferResolve;
	private Date faultdealTime;
	private String faultdealdesc;
	private String linkDealdesc;
	private String selectStep;
	private String faultTreatment;
	private String linkFaultDealInfo;
	private String linkUntreadIdea;
	private String linkIfGreatFault;
	private String linkBackCase;
	private String faultClassification;
	private String recoveryUnit;
	private String linkIfMobile;
	private String linkFaultReasonSubsectionTwo;
	private String linkIfConfirmPreSolve;
	private String confirmReason;
    private String manualPreSolve;
    private String faultReason;
    
    private String linkFalg;
    
    
    private String linkCiytSubrole;//工单驳回时t1派t2的班组
    private String linkReserved1;
    private String linkReserved2;//智能质检 是 否
    private String linkReserved3;//智能质检结果  合格  不合格
    private String linkReserved4;//智能质检意见
    private String linkReserved5;
    
    private String linkSendOtherName;//已联系待转派单位班组维护人员姓名
    private String linkSendOtherTel;//已联系待转派单位班组维护人员电话
    private String linkSendRes;//转派理由
    
    private String linkIsPass;//T1是否同意
    private String linkT1NoRes;//T1是不同意理由
    

	public CommonFaultLink()
	{
	}

	public String getLinkAffectTimeLength()
	{
		return linkAffectTimeLength;
	}

	public void setLinkAffectTimeLength(String linkAffectTimeLength)
	{
		this.linkAffectTimeLength = linkAffectTimeLength;
	}

	public String getLinkDealStep()
	{
		return linkDealStep;
	}

	public String getFaultReason() {
		return faultReason;
	}

	public void setFaultReason(String faultReason) {
		this.faultReason = faultReason;
	}

	public void setLinkDealStep(String linkDealStep)
	{
		this.linkDealStep = linkDealStep;
	}

	public Date getLinkFaultAvoidTime()
	{
		return linkFaultAvoidTime;
	}

	public void setLinkFaultAvoidTime(Date linkFaultAvoidTime)
	{
		this.linkFaultAvoidTime = linkFaultAvoidTime;
	}

	public String getLinkFaultDealResult()
	{
		return linkFaultDealResult;
	}

	public void setLinkFaultDealResult(String linkFaultDealResult)
	{
		this.linkFaultDealResult = linkFaultDealResult;
	}

	public String getLinkFaultReason()
	{
		return linkFaultReason;
	}

	public void setLinkFaultReason(String linkFaultReason)
	{
		this.linkFaultReason = linkFaultReason;
	}

	public String getLinkFaultResponseLevel()
	{
		return linkFaultResponseLevel;
	}

	public void setLinkFaultResponseLevel(String linkFaultResponseLevel)
	{
		this.linkFaultResponseLevel = linkFaultResponseLevel;
	}

	public String getLinkIfExcuteNetChange()
	{
		return linkIfExcuteNetChange;
	}

	public void setLinkIfExcuteNetChange(String linkIfExcuteNetChange)
	{
		this.linkIfExcuteNetChange = linkIfExcuteNetChange;
	}

	public String getLinkIfGreatFault()
	{
		return linkIfGreatFault;
	}

	public void setLinkIfGreatFault(String linkIfGreatFault)
	{
		this.linkIfGreatFault = linkIfGreatFault;
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

	public Date getLinkOperRenewTime()
	{
		return linkOperRenewTime;
	}

	public void setLinkOperRenewTime(Date linkOperRenewTime)
	{
		this.linkOperRenewTime = linkOperRenewTime;
	}

	public String getLinkUntreadReason()
	{
		return linkUntreadReason;
	}

	public void setLinkUntreadReason(String linkUntreadReason)
	{
		this.linkUntreadReason = linkUntreadReason;
	}

	public String getLinkUrgentFaultLog()
	{
		return linkUrgentFaultLog;
	}

	public void setLinkUrgentFaultLog(String linkUrgentFaultLog)
	{
		this.linkUrgentFaultLog = linkUrgentFaultLog;
	}

	public String getLinkIfUrgentFault()
	{
		return linkIfUrgentFault;
	}

	public void setLinkIfUrgentFault(String linkIfUrgentFault)
	{
		this.linkIfUrgentFault = linkIfUrgentFault;
	}

	public String getLinkTransmitReason()
	{
		return linkTransmitReason;
	}

	public void setLinkTransmitReason(String linkTransmitReason)
	{
		this.linkTransmitReason = linkTransmitReason;
	}

	public String getLinkExamineContent()
	{
		return linkExamineContent;
	}

	public void setLinkExamineContent(String linkExamineContent)
	{
		this.linkExamineContent = linkExamineContent;
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

	public String getLinkFaultReasonSort()
	{
		return linkFaultReasonSort;
	}

	public void setLinkFaultReasonSort(String linkFaultReasonSort)
	{
		this.linkFaultReasonSort = linkFaultReasonSort;
	}

	public String getLinkFaultReasonSubsection()
	{
		return linkFaultReasonSubsection;
	}

	public void setLinkFaultReasonSubsection(String linkFaultReasonSubsection)
	{
		this.linkFaultReasonSubsection = linkFaultReasonSubsection;
	}

	public String getLinkIfAddCaseDataBase()
	{
		return linkIfAddCaseDataBase;
	}

	public void setLinkIfAddCaseDataBase(String linkIfAddCaseDataBase)
	{
		this.linkIfAddCaseDataBase = linkIfAddCaseDataBase;
	}

	public String getLinkIfDeferResolve()
	{
		return linkIfDeferResolve;
	}

	public void setLinkIfDeferResolve(String linkIfDeferResolve)
	{
		this.linkIfDeferResolve = linkIfDeferResolve;
	}

	public String getLinkIfFinallySolveProject()
	{
		return linkIfFinallySolveProject;
	}

	public void setLinkIfFinallySolveProject(String linkIfFinallySolveProject)
	{
		this.linkIfFinallySolveProject = linkIfFinallySolveProject;
	}

	public String getLinkTransmitContent()
	{
		return linkTransmitContent;
	}

	public void setLinkTransmitContent(String linkTransmitContent)
	{
		this.linkTransmitContent = linkTransmitContent;
	}

	public String getLinkFaultDealInfo()
	{
		return linkFaultDealInfo;
	}

	public void setLinkFaultDealInfo(String linkFaultDealInfo)
	{
		this.linkFaultDealInfo = linkFaultDealInfo;
	}

	public String getLinkUntreadIdea()
	{
		return linkUntreadIdea;
	}

	public void setLinkUntreadIdea(String linkUntreadIdea)
	{
		this.linkUntreadIdea = linkUntreadIdea;
	}

	public String getFaultdealdesc()
	{
		return faultdealdesc;
	}

	public void setFaultdealdesc(String faultdealdesc)
	{
		this.faultdealdesc = faultdealdesc;
	}

	public Date getFaultdealTime()
	{
		return faultdealTime;
	}

	public void setFaultdealTime(Date faultdealTime)
	{
		this.faultdealTime = faultdealTime;
	}

	public String getLinkDealdesc()
	{
		return linkDealdesc;
	}

	public void setLinkDealdesc(String linkDealdesc)
	{
		this.linkDealdesc = linkDealdesc;
	}

	public String getSelectStep()
	{
		return selectStep;
	}

	public void setSelectStep(String selectStep)
	{
		this.selectStep = selectStep;
	}

	public String getFaultTreatment()
	{
		return faultTreatment;
	}

	public void setFaultTreatment(String faultTreatment)
	{
		this.faultTreatment = faultTreatment;
	}

	public String getLinkBackCase()
	{
		return linkBackCase;
	}

	public void setLinkBackCase(String linkBackCase)
	{
		this.linkBackCase = linkBackCase;
	}

	public String getFaultClassification() {
		return faultClassification;
	}

	public void setFaultClassification(String faultClassification) {
		this.faultClassification = faultClassification;
	}

	public String getRecoveryUnit() {
		return recoveryUnit;
	}

	public void setRecoveryUnit(String recoveryUnit) {
		this.recoveryUnit = recoveryUnit;
	}
	
	public String getLinkIfMobile()
	{
		return linkIfMobile;
	}

	public void setLinkIfMobile(String linkIfMobile)
	{
		this.linkIfMobile = linkIfMobile;
	}

	public String getLinkFaultReasonSubsectionTwo() {
		return linkFaultReasonSubsectionTwo;
	}

	public void setLinkFaultReasonSubsectionTwo(String linkFaultReasonSubsectionTwo) {
		this.linkFaultReasonSubsectionTwo = linkFaultReasonSubsectionTwo;
	}

	public String getConfirmReason() {
		return confirmReason;
	}

	public void setConfirmReason(String confirmReason) {
		this.confirmReason = confirmReason;
	}

	public String getLinkIfConfirmPreSolve() {
		return linkIfConfirmPreSolve;
	}

	public void setLinkIfConfirmPreSolve(String linkIfConfirmPreSolve) {
		this.linkIfConfirmPreSolve = linkIfConfirmPreSolve;
	}

	public String getManualPreSolve() {
		return manualPreSolve;
	}

	public void setManualPreSolve(String manualPreSolve) {
		this.manualPreSolve = manualPreSolve;
	}

	public String getLinkFalg() {
		return linkFalg;
	}

	public void setLinkFalg(String linkFalg) {
		this.linkFalg = linkFalg;
	}

	public String getLinkCiytSubrole() {
		return linkCiytSubrole;
	}

	public void setLinkCiytSubrole(String linkCiytSubrole) {
		this.linkCiytSubrole = linkCiytSubrole;
	}

	public String getLinkReserved1() {
		return linkReserved1;
	}

	public void setLinkReserved1(String linkReserved1) {
		this.linkReserved1 = linkReserved1;
	}

	public String getLinkReserved2() {
		return linkReserved2;
	}

	public void setLinkReserved2(String linkReserved2) {
		this.linkReserved2 = linkReserved2;
	}

	public String getLinkReserved3() {
		return linkReserved3;
	}

	public void setLinkReserved3(String linkReserved3) {
		this.linkReserved3 = linkReserved3;
	}

	public String getLinkReserved4() {
		return linkReserved4;
	}

	public void setLinkReserved4(String linkReserved4) {
		this.linkReserved4 = linkReserved4;
	}

	public String getLinkReserved5() {
		return linkReserved5;
	}

	public void setLinkReserved5(String linkReserved5) {
		this.linkReserved5 = linkReserved5;
	}

	public String getLinkIsPass() {
		return linkIsPass;
	}

	public void setLinkIsPass(String linkIsPass) {
		this.linkIsPass = linkIsPass;
	}

	public String getLinkSendOtherName() {
		return linkSendOtherName;
	}

	public void setLinkSendOtherName(String linkSendOtherName) {
		this.linkSendOtherName = linkSendOtherName;
	}

	public String getLinkSendOtherTel() {
		return linkSendOtherTel;
	}

	public void setLinkSendOtherTel(String linkSendOtherTel) {
		this.linkSendOtherTel = linkSendOtherTel;
	}

	public String getLinkSendRes() {
		return linkSendRes;
	}

	public void setLinkSendRes(String linkSendRes) {
		this.linkSendRes = linkSendRes;
	}

	public String getLinkT1NoRes() {
		return linkT1NoRes;
	}

	public void setLinkT1NoRes(String linkT1NoRes) {
		this.linkT1NoRes = linkT1NoRes;
	}


}
