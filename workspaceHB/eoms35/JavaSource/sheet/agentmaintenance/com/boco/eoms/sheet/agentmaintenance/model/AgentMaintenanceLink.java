// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AgentMaintenanceLink.java

package com.boco.eoms.sheet.agentmaintenance.model;

import com.boco.eoms.sheet.base.model.BaseLink;
import java.util.Date;

public class AgentMaintenanceLink extends BaseLink
{

	private String linkFaultResponseLevel;
	private String linkFaultUrgentLog;
	private String linkFaultIfMutualCommunication;
	private String linkFaultIfSafe;
	private String linkFaultIfExcuteNetChange;
	private String linkFaultDealResult;
	private Date linkFaultAvoidTime;
	private Date linkFaultOperRenewTime;
	private String linkFaultAffectTimeLength;
	private String linkFaultReason;
	private String linkFaultDealStep;
	private String linkFaultUntreadReason;
	private String linkFaultIfUrgentFault;
	private String linkFaultTransmitReason;
	private String linkFaultFirstDealDesc;
	private String linkFaultDesc;
	private String linkFaultReasonSort;
	private String linkFaultReasonSubsection;
	private String linkFaultIfFinallySolveProject;
	private String linkFaultIfAddCaseDataBase;
	private String linkFaultTransmitContent;
	private String linkFaultExamineContent;
	private String linkFaultIfDeferResolve;
	private String linkFaultDealInfo;
	private String linkFaultUntreadIdea;
	private String linkFaultIfGreatFault;
	private Date commonFaultdealTime;
	private String commonFaultTreatment;
	private String commonFaultdealdesc;
	private String commonLinkDealdesc;
	private String selectStep;
	private String linkTaskAuditResult;
	private String linkTaskAuditIdea;
	private String linkTaskComplete;
	private String linkComUntreadReason;
	private String linkComTransmitReason;
	private String linkComIfInvokeChange;
	private Date linkComFaultStartTime;
	private Date linkComFaultEndTime;
	private String linkComFaultGenerantPlace;
	private String linkComPlaceDesc;
	private String linkComndeptContact;
	private String linkComndeptContactPhone;
	private String linkComdealResult;
	private String linkComdealDesc;
	private Date linkComissueEliminatTime;
	private String linkComissueEliminatReason;
	private String linkComCheckResult;
	private String linkComCheckIdea;
	private String linkComTransmitContent;
	private String linkComExamineContent;
	private String linkComIfDeferResolve;
	private String linkComIfInvokeCaseDatabase;
	private String linkComChangeSheetId;
	private String linkComisSubTransmit;
	private String linkComparLinkId;
	private String linkComisReplied;
	private String linkComcompProp;
	private String linkComisDeferReploy;
	private String linkComReplyPerson;
	private String linkComReplayPhone;
	private String linkComLocalDealDesc;
	private String linkComBusinessType;
	private String linkComFaultType;
	private String linkComReasonType;
	private String linkComIsReciveFaultId;
	private String linkComReciveFaultId;
	private String linkComIsSolve;
	private Date linkComSolveDate;
	private String linkComPlanSolveType;
	private Date linkComPlanSolveDate;
	private String linkComIsUserConfirm;
	private String linkComNoConfirmReason;
	private String linkComIsRepeatCom;
	private String linkComRepeatType;
	private String linkComIsUserStatisfied;
	private String linkComUserNoStatisfied;
	private String linkComIsContactUser;
	private String linkComContactDate;
	private String linkComContactship;
	private String linkComContactUser;
	private String linkComContactPhone;
	private String linkComNoContactReason;
	private String linkNoAuditReason;
	private String linkRevertReason;

	public AgentMaintenanceLink()
	{
	}

	public String getLinkComBusinessType()
	{
		return linkComBusinessType;
	}

	public void setLinkComBusinessType(String linkComBusinessType)
	{
		this.linkComBusinessType = linkComBusinessType;
	}

	public String getLinkComChangeSheetId()
	{
		return linkComChangeSheetId;
	}

	public void setLinkComChangeSheetId(String linkComChangeSheetId)
	{
		this.linkComChangeSheetId = linkComChangeSheetId;
	}

	public String getLinkComCheckIdea()
	{
		return linkComCheckIdea;
	}

	public void setLinkComCheckIdea(String linkComCheckIdea)
	{
		this.linkComCheckIdea = linkComCheckIdea;
	}

	public String getLinkComCheckResult()
	{
		return linkComCheckResult;
	}

	public void setLinkComCheckResult(String linkComCheckResult)
	{
		this.linkComCheckResult = linkComCheckResult;
	}

	public String getLinkComcompProp()
	{
		return linkComcompProp;
	}

	public void setLinkComcompProp(String linkComcompProp)
	{
		this.linkComcompProp = linkComcompProp;
	}

	public String getLinkComContactDate()
	{
		return linkComContactDate;
	}

	public void setLinkComContactDate(String linkComContactDate)
	{
		this.linkComContactDate = linkComContactDate;
	}

	public String getLinkComContactPhone()
	{
		return linkComContactPhone;
	}

	public void setLinkComContactPhone(String linkComContactPhone)
	{
		this.linkComContactPhone = linkComContactPhone;
	}

	public String getLinkComContactship()
	{
		return linkComContactship;
	}

	public void setLinkComContactship(String linkComContactship)
	{
		this.linkComContactship = linkComContactship;
	}

	public String getLinkComContactUser()
	{
		return linkComContactUser;
	}

	public void setLinkComContactUser(String linkComContactUser)
	{
		this.linkComContactUser = linkComContactUser;
	}

	public String getLinkComdealDesc()
	{
		return linkComdealDesc;
	}

	public void setLinkComdealDesc(String linkComdealDesc)
	{
		this.linkComdealDesc = linkComdealDesc;
	}

	public String getLinkComLocalDealDesc()
	{
		return linkComLocalDealDesc;
	}

	public void setLinkComLocalDealDesc(String linkComLocalDealDesc)
	{
		this.linkComLocalDealDesc = linkComLocalDealDesc;
	}

	public String getLinkComdealResult()
	{
		return linkComdealResult;
	}

	public void setLinkComdealResult(String linkComdealResult)
	{
		this.linkComdealResult = linkComdealResult;
	}

	public String getLinkComExamineContent()
	{
		return linkComExamineContent;
	}

	public void setLinkComExamineContent(String linkComExamineContent)
	{
		this.linkComExamineContent = linkComExamineContent;
	}

	public Date getLinkComFaultEndTime()
	{
		return linkComFaultEndTime;
	}

	public void setLinkComFaultEndTime(Date linkComFaultEndTime)
	{
		this.linkComFaultEndTime = linkComFaultEndTime;
	}

	public String getLinkComFaultGenerantPlace()
	{
		return linkComFaultGenerantPlace;
	}

	public void setLinkComFaultGenerantPlace(String linkComFaultGenerantPlace)
	{
		this.linkComFaultGenerantPlace = linkComFaultGenerantPlace;
	}

	public Date getLinkComFaultStartTime()
	{
		return linkComFaultStartTime;
	}

	public void setLinkComFaultStartTime(Date linkComFaultStartTime)
	{
		this.linkComFaultStartTime = linkComFaultStartTime;
	}

	public String getLinkComFaultType()
	{
		return linkComFaultType;
	}

	public void setLinkComFaultType(String linkComFaultType)
	{
		this.linkComFaultType = linkComFaultType;
	}

	public String getLinkComIfDeferResolve()
	{
		return linkComIfDeferResolve;
	}

	public void setLinkComIfDeferResolve(String linkComIfDeferResolve)
	{
		this.linkComIfDeferResolve = linkComIfDeferResolve;
	}

	public String getLinkComIfInvokeCaseDatabase()
	{
		return linkComIfInvokeCaseDatabase;
	}

	public void setLinkComIfInvokeCaseDatabase(String linkComIfInvokeCaseDatabase)
	{
		this.linkComIfInvokeCaseDatabase = linkComIfInvokeCaseDatabase;
	}

	public String getLinkComIfInvokeChange()
	{
		return linkComIfInvokeChange;
	}

	public void setLinkComIfInvokeChange(String linkComIfInvokeChange)
	{
		this.linkComIfInvokeChange = linkComIfInvokeChange;
	}

	public String getLinkComIsContactUser()
	{
		return linkComIsContactUser;
	}

	public void setLinkComIsContactUser(String linkComIsContactUser)
	{
		this.linkComIsContactUser = linkComIsContactUser;
	}

	public String getLinkComisDeferReploy()
	{
		return linkComisDeferReploy;
	}

	public void setLinkComisDeferReploy(String linkComisDeferReploy)
	{
		this.linkComisDeferReploy = linkComisDeferReploy;
	}

	public String getLinkComIsReciveFaultId()
	{
		return linkComIsReciveFaultId;
	}

	public void setLinkComIsReciveFaultId(String linkComIsReciveFaultId)
	{
		this.linkComIsReciveFaultId = linkComIsReciveFaultId;
	}

	public String getLinkComIsRepeatCom()
	{
		return linkComIsRepeatCom;
	}

	public void setLinkComIsRepeatCom(String linkComIsRepeatCom)
	{
		this.linkComIsRepeatCom = linkComIsRepeatCom;
	}

	public String getLinkComisReplied()
	{
		return linkComisReplied;
	}

	public void setLinkComisReplied(String linkComisReplied)
	{
		this.linkComisReplied = linkComisReplied;
	}

	public String getLinkComIsSolve()
	{
		return linkComIsSolve;
	}

	public void setLinkComIsSolve(String linkComIsSolve)
	{
		this.linkComIsSolve = linkComIsSolve;
	}

	public String getLinkComisSubTransmit()
	{
		return linkComisSubTransmit;
	}

	public void setLinkComisSubTransmit(String linkComisSubTransmit)
	{
		this.linkComisSubTransmit = linkComisSubTransmit;
	}

	public String getLinkComissueEliminatReason()
	{
		return linkComissueEliminatReason;
	}

	public void setLinkComissueEliminatReason(String linkComissueEliminatReason)
	{
		this.linkComissueEliminatReason = linkComissueEliminatReason;
	}

	public Date getLinkComissueEliminatTime()
	{
		return linkComissueEliminatTime;
	}

	public void setLinkComissueEliminatTime(Date linkComissueEliminatTime)
	{
		this.linkComissueEliminatTime = linkComissueEliminatTime;
	}

	public String getLinkComIsUserConfirm()
	{
		return linkComIsUserConfirm;
	}

	public void setLinkComIsUserConfirm(String linkComIsUserConfirm)
	{
		this.linkComIsUserConfirm = linkComIsUserConfirm;
	}

	public String getLinkComIsUserStatisfied()
	{
		return linkComIsUserStatisfied;
	}

	public void setLinkComIsUserStatisfied(String linkComIsUserStatisfied)
	{
		this.linkComIsUserStatisfied = linkComIsUserStatisfied;
	}

	public String getLinkComndeptContact()
	{
		return linkComndeptContact;
	}

	public void setLinkComndeptContact(String linkComndeptContact)
	{
		this.linkComndeptContact = linkComndeptContact;
	}

	public String getLinkComndeptContactPhone()
	{
		return linkComndeptContactPhone;
	}

	public void setLinkComndeptContactPhone(String linkComndeptContactPhone)
	{
		this.linkComndeptContactPhone = linkComndeptContactPhone;
	}

	public String getLinkComNoConfirmReason()
	{
		return linkComNoConfirmReason;
	}

	public void setLinkComNoConfirmReason(String linkComNoConfirmReason)
	{
		this.linkComNoConfirmReason = linkComNoConfirmReason;
	}

	public String getLinkComNoContactReason()
	{
		return linkComNoContactReason;
	}

	public void setLinkComNoContactReason(String linkComNoContactReason)
	{
		this.linkComNoContactReason = linkComNoContactReason;
	}

	public String getLinkComparLinkId()
	{
		return linkComparLinkId;
	}

	public void setLinkComparLinkId(String linkComparLinkId)
	{
		this.linkComparLinkId = linkComparLinkId;
	}

	public String getLinkComPlaceDesc()
	{
		return linkComPlaceDesc;
	}

	public void setLinkComPlaceDesc(String linkComPlaceDesc)
	{
		this.linkComPlaceDesc = linkComPlaceDesc;
	}

	public Date getLinkComPlanSolveDate()
	{
		return linkComPlanSolveDate;
	}

	public void setLinkComPlanSolveDate(Date linkComPlanSolveDate)
	{
		this.linkComPlanSolveDate = linkComPlanSolveDate;
	}

	public String getLinkComPlanSolveType()
	{
		return linkComPlanSolveType;
	}

	public void setLinkComPlanSolveType(String linkComPlanSolveType)
	{
		this.linkComPlanSolveType = linkComPlanSolveType;
	}

	public String getLinkComReasonType()
	{
		return linkComReasonType;
	}

	public void setLinkComReasonType(String linkComReasonType)
	{
		this.linkComReasonType = linkComReasonType;
	}

	public String getLinkComReciveFaultId()
	{
		return linkComReciveFaultId;
	}

	public void setLinkComReciveFaultId(String linkComReciveFaultId)
	{
		this.linkComReciveFaultId = linkComReciveFaultId;
	}

	public String getLinkComRepeatType()
	{
		return linkComRepeatType;
	}

	public void setLinkComRepeatType(String linkComRepeatType)
	{
		this.linkComRepeatType = linkComRepeatType;
	}

	public String getLinkComReplayPhone()
	{
		return linkComReplayPhone;
	}

	public void setLinkComReplayPhone(String linkComReplayPhone)
	{
		this.linkComReplayPhone = linkComReplayPhone;
	}

	public String getLinkComReplyPerson()
	{
		return linkComReplyPerson;
	}

	public void setLinkComReplyPerson(String linkComReplyPerson)
	{
		this.linkComReplyPerson = linkComReplyPerson;
	}

	public Date getLinkComSolveDate()
	{
		return linkComSolveDate;
	}

	public void setLinkComSolveDate(Date linkComSolveDate)
	{
		this.linkComSolveDate = linkComSolveDate;
	}

	public String getLinkComTransmitContent()
	{
		return linkComTransmitContent;
	}

	public void setLinkComTransmitContent(String linkComTransmitContent)
	{
		this.linkComTransmitContent = linkComTransmitContent;
	}

	public String getLinkComTransmitReason()
	{
		return linkComTransmitReason;
	}

	public void setLinkComTransmitReason(String linkComTransmitReason)
	{
		this.linkComTransmitReason = linkComTransmitReason;
	}

	public String getLinkComUntreadReason()
	{
		return linkComUntreadReason;
	}

	public void setLinkComUntreadReason(String linkComUntreadReason)
	{
		this.linkComUntreadReason = linkComUntreadReason;
	}

	public String getLinkComUserNoStatisfied()
	{
		return linkComUserNoStatisfied;
	}

	public void setLinkComUserNoStatisfied(String linkComUserNoStatisfied)
	{
		this.linkComUserNoStatisfied = linkComUserNoStatisfied;
	}

	public String getLinkFaultAffectTimeLength()
	{
		return linkFaultAffectTimeLength;
	}

	public void setLinkFaultAffectTimeLength(String linkFaultAffectTimeLength)
	{
		this.linkFaultAffectTimeLength = linkFaultAffectTimeLength;
	}

	public Date getLinkFaultAvoidTime()
	{
		return linkFaultAvoidTime;
	}

	public void setLinkFaultAvoidTime(Date linkFaultAvoidTime)
	{
		this.linkFaultAvoidTime = linkFaultAvoidTime;
	}

	public String getLinkFaultDealInfo()
	{
		return linkFaultDealInfo;
	}

	public void setLinkFaultDealInfo(String linkFaultDealInfo)
	{
		this.linkFaultDealInfo = linkFaultDealInfo;
	}

	public String getLinkFaultDealResult()
	{
		return linkFaultDealResult;
	}

	public void setLinkFaultDealResult(String linkFaultDealResult)
	{
		this.linkFaultDealResult = linkFaultDealResult;
	}

	public String getLinkFaultDealStep()
	{
		return linkFaultDealStep;
	}

	public void setLinkFaultDealStep(String linkFaultDealStep)
	{
		this.linkFaultDealStep = linkFaultDealStep;
	}

	public String getLinkFaultDesc()
	{
		return linkFaultDesc;
	}

	public void setLinkFaultDesc(String linkFaultDesc)
	{
		this.linkFaultDesc = linkFaultDesc;
	}

	public String getLinkFaultExamineContent()
	{
		return linkFaultExamineContent;
	}

	public void setLinkFaultExamineContent(String linkFaultExamineContent)
	{
		this.linkFaultExamineContent = linkFaultExamineContent;
	}

	public String getLinkFaultFirstDealDesc()
	{
		return linkFaultFirstDealDesc;
	}

	public void setLinkFaultFirstDealDesc(String linkFaultFirstDealDesc)
	{
		this.linkFaultFirstDealDesc = linkFaultFirstDealDesc;
	}

	public String getLinkFaultIfAddCaseDataBase()
	{
		return linkFaultIfAddCaseDataBase;
	}

	public void setLinkFaultIfAddCaseDataBase(String linkFaultIfAddCaseDataBase)
	{
		this.linkFaultIfAddCaseDataBase = linkFaultIfAddCaseDataBase;
	}

	public String getLinkFaultIfDeferResolve()
	{
		return linkFaultIfDeferResolve;
	}

	public void setLinkFaultIfDeferResolve(String linkFaultIfDeferResolve)
	{
		this.linkFaultIfDeferResolve = linkFaultIfDeferResolve;
	}

	public String getLinkFaultIfExcuteNetChange()
	{
		return linkFaultIfExcuteNetChange;
	}

	public void setLinkFaultIfExcuteNetChange(String linkFaultIfExcuteNetChange)
	{
		this.linkFaultIfExcuteNetChange = linkFaultIfExcuteNetChange;
	}

	public String getLinkFaultIfFinallySolveProject()
	{
		return linkFaultIfFinallySolveProject;
	}

	public void setLinkFaultIfFinallySolveProject(String linkFaultIfFinallySolveProject)
	{
		this.linkFaultIfFinallySolveProject = linkFaultIfFinallySolveProject;
	}

	public String getLinkFaultIfGreatFault()
	{
		return linkFaultIfGreatFault;
	}

	public void setLinkFaultIfGreatFault(String linkFaultIfGreatFault)
	{
		this.linkFaultIfGreatFault = linkFaultIfGreatFault;
	}

	public String getLinkFaultIfMutualCommunication()
	{
		return linkFaultIfMutualCommunication;
	}

	public void setLinkFaultIfMutualCommunication(String linkFaultIfMutualCommunication)
	{
		this.linkFaultIfMutualCommunication = linkFaultIfMutualCommunication;
	}

	public String getLinkFaultIfSafe()
	{
		return linkFaultIfSafe;
	}

	public void setLinkFaultIfSafe(String linkFaultIfSafe)
	{
		this.linkFaultIfSafe = linkFaultIfSafe;
	}

	public String getLinkFaultIfUrgentFault()
	{
		return linkFaultIfUrgentFault;
	}

	public void setLinkFaultIfUrgentFault(String linkFaultIfUrgentFault)
	{
		this.linkFaultIfUrgentFault = linkFaultIfUrgentFault;
	}

	public Date getLinkFaultOperRenewTime()
	{
		return linkFaultOperRenewTime;
	}

	public void setLinkFaultOperRenewTime(Date linkFaultOperRenewTime)
	{
		this.linkFaultOperRenewTime = linkFaultOperRenewTime;
	}

	public String getLinkFaultReason()
	{
		return linkFaultReason;
	}

	public void setLinkFaultReason(String linkFaultReason)
	{
		this.linkFaultReason = linkFaultReason;
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

	public String getLinkFaultResponseLevel()
	{
		return linkFaultResponseLevel;
	}

	public void setLinkFaultResponseLevel(String linkFaultResponseLevel)
	{
		this.linkFaultResponseLevel = linkFaultResponseLevel;
	}

	public String getLinkFaultTransmitContent()
	{
		return linkFaultTransmitContent;
	}

	public void setLinkFaultTransmitContent(String linkFaultTransmitContent)
	{
		this.linkFaultTransmitContent = linkFaultTransmitContent;
	}

	public String getLinkFaultTransmitReason()
	{
		return linkFaultTransmitReason;
	}

	public void setLinkFaultTransmitReason(String linkFaultTransmitReason)
	{
		this.linkFaultTransmitReason = linkFaultTransmitReason;
	}

	public String getLinkFaultUntreadIdea()
	{
		return linkFaultUntreadIdea;
	}

	public void setLinkFaultUntreadIdea(String linkFaultUntreadIdea)
	{
		this.linkFaultUntreadIdea = linkFaultUntreadIdea;
	}

	public String getLinkFaultUntreadReason()
	{
		return linkFaultUntreadReason;
	}

	public void setLinkFaultUntreadReason(String linkFaultUntreadReason)
	{
		this.linkFaultUntreadReason = linkFaultUntreadReason;
	}

	public String getLinkFaultUrgentLog()
	{
		return linkFaultUrgentLog;
	}

	public void setLinkFaultUrgentLog(String linkFaultUrgentLog)
	{
		this.linkFaultUrgentLog = linkFaultUrgentLog;
	}

	public String getLinkTaskAuditIdea()
	{
		return linkTaskAuditIdea;
	}

	public void setLinkTaskAuditIdea(String linkTaskAuditIdea)
	{
		this.linkTaskAuditIdea = linkTaskAuditIdea;
	}

	public String getLinkTaskAuditResult()
	{
		return linkTaskAuditResult;
	}

	public void setLinkTaskAuditResult(String linkTaskAuditResult)
	{
		this.linkTaskAuditResult = linkTaskAuditResult;
	}

	public String getLinkTaskComplete()
	{
		return linkTaskComplete;
	}

	public void setLinkTaskComplete(String linkTaskComplete)
	{
		this.linkTaskComplete = linkTaskComplete;
	}

	public String getCommonFaultdealdesc()
	{
		return commonFaultdealdesc;
	}

	public void setCommonFaultdealdesc(String commonFaultdealdesc)
	{
		this.commonFaultdealdesc = commonFaultdealdesc;
	}

	public Date getCommonFaultdealTime()
	{
		return commonFaultdealTime;
	}

	public void setCommonFaultdealTime(Date commonFaultdealTime)
	{
		this.commonFaultdealTime = commonFaultdealTime;
	}

	public String getCommonFaultTreatment()
	{
		return commonFaultTreatment;
	}

	public void setCommonFaultTreatment(String commonFaultTreatment)
	{
		this.commonFaultTreatment = commonFaultTreatment;
	}

	public String getCommonLinkDealdesc()
	{
		return commonLinkDealdesc;
	}

	public void setCommonLinkDealdesc(String commonLinkDealdesc)
	{
		this.commonLinkDealdesc = commonLinkDealdesc;
	}

	public String getSelectStep()
	{
		return selectStep;
	}

	public void setSelectStep(String selectStep)
	{
		this.selectStep = selectStep;
	}

	public String getLinkNoAuditReason()
	{
		return linkNoAuditReason;
	}

	public void setLinkNoAuditReason(String linkNoAuditReason)
	{
		this.linkNoAuditReason = linkNoAuditReason;
	}

	public String getLinkRevertReason() {
		return linkRevertReason;
	}

	public void setLinkRevertReason(String linkRevertReason) {
		this.linkRevertReason = linkRevertReason;
	}

	public Object getYuliu1() {
		// TODO 自动生成方法存根
		return null;
	}
}
