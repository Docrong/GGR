/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.circuitdispatch.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * @author jialei
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CircuitDispatchLink extends BaseLink {
	private Date linkExecuteEndDate;
	private String linkProgrammeExplain;
	private String linkProgrammeKey;
	private String linkRiskEvaluate;
	private String linkOperationConstrue;
	private String linkProgrammeNo;
	private Integer linkPermitResult;
	private String linkPermitIdea;
	private String linkExcutePrincipal;
	private String linkContact;
	private Date linkPlanStartDate;
	private Date linkPlanEndDate;
	private String linkNetAffectArea;
	private Integer linkIfAffectOperation;
	private String linkAffectSituation;
	private String linkAffectNetManage;
	private String linkReferOperateDept;
	private Integer linkIfNotify;
	private Integer linkExcuteResult;
	private Integer linkIsAccordanceProgramme;
	private String linkExcuteExplain;
	private String linkAffectOperationExplain;
	private Integer linkTestResult;
	private String linkAlarmRecord;
	private String linkReportAbnormityExplain;
	private String linkExcuteConstrue;
	private Integer linkIfUpdatePlan;
	private String linkPlanUpdateIdea;
	private Integer linkCircuitUpdate;
	private String linkWorkPlan;
	private Integer linkIsToMaintenance;
	private String linkMaintenanceDes;
	private String linkFailedReason;
	private String linkInvolvedProvince;
	private String linkInvolvedCity;
	private String linkRejectReason;
	private String linkCopyPerformer;
	/**
	 * @return the linkInvolvedCity
	 */
	public String getLinkInvolvedCity() {
		return linkInvolvedCity;
	}
	/**
	 * @param linkInvolvedCity the linkInvolvedCity to set
	 */
	public void setLinkInvolvedCity(String linkInvolvedCity) {
		this.linkInvolvedCity = linkInvolvedCity;
	}
	/**
	 * @return the linkInvolvedProvince
	 */
	public String getLinkInvolvedProvince() {
		return linkInvolvedProvince;
	}
	/**
	 * @param linkInvolvedProvince the linkInvolvedProvince to set
	 */
	public void setLinkInvolvedProvince(String linkInvolvedProvince) {
		this.linkInvolvedProvince = linkInvolvedProvince;
	}
	/**
	 * @return the linkAffectNetManage
	 */
	public String getLinkAffectNetManage() {
		return linkAffectNetManage;
	}
	/**
	 * @param linkAffectNetManage the linkAffectNetManage to set
	 */
	public void setLinkAffectNetManage(String linkAffectNetManage) {
		this.linkAffectNetManage = linkAffectNetManage;
	}
	/**
	 * @return the linkAffectOperationExplain
	 */
	public String getLinkAffectOperationExplain() {
		return linkAffectOperationExplain;
	}
	/**
	 * @param linkAffectOperationExplain the linkAffectOperationExplain to set
	 */
	public void setLinkAffectOperationExplain(String linkAffectOperationExplain) {
		this.linkAffectOperationExplain = linkAffectOperationExplain;
	}
	/**
	 * @return the linkAffectSituation
	 */
	public String getLinkAffectSituation() {
		return linkAffectSituation;
	}
	/**
	 * @param linkAffectSituation the linkAffectSituation to set
	 */
	public void setLinkAffectSituation(String linkAffectSituation) {
		this.linkAffectSituation = linkAffectSituation;
	}
	/**
	 * @return the linkAlarmRecord
	 */
	public String getLinkAlarmRecord() {
		return linkAlarmRecord;
	}
	/**
	 * @param linkAlarmRecord the linkAlarmRecord to set
	 */
	public void setLinkAlarmRecord(String linkAlarmRecord) {
		this.linkAlarmRecord = linkAlarmRecord;
	}
	/**
	 * @return the linkCircuitUpdate
	 */
	public Integer getLinkCircuitUpdate() {
		return linkCircuitUpdate;
	}
	/**
	 * @param linkCircuitUpdate the linkCircuitUpdate to set
	 */
	public void setLinkCircuitUpdate(Integer linkCircuitUpdate) {
		this.linkCircuitUpdate = linkCircuitUpdate;
	}
	/**
	 * @return the linkContact
	 */
	public String getLinkContact() {
		return linkContact;
	}
	/**
	 * @param linkContact the linkContact to set
	 */
	public void setLinkContact(String linkContact) {
		this.linkContact = linkContact;
	}
	/**
	 * @return the linkExcuteConstrue
	 */
	public String getLinkExcuteConstrue() {
		return linkExcuteConstrue;
	}
	/**
	 * @param linkExcuteConstrue the linkExcuteConstrue to set
	 */
	public void setLinkExcuteConstrue(String linkExcuteConstrue) {
		this.linkExcuteConstrue = linkExcuteConstrue;
	}
	/**
	 * @return the linkExcuteExplain
	 */
	public String getLinkExcuteExplain() {
		return linkExcuteExplain;
	}
	/**
	 * @param linkExcuteExplain the linkExcuteExplain to set
	 */
	public void setLinkExcuteExplain(String linkExcuteExplain) {
		this.linkExcuteExplain = linkExcuteExplain;
	}
	/**
	 * @return the linkExcutePrincipal
	 */
	public String getLinkExcutePrincipal() {
		return linkExcutePrincipal;
	}
	/**
	 * @param linkExcutePrincipal the linkExcutePrincipal to set
	 */
	public void setLinkExcutePrincipal(String linkExcutePrincipal) {
		this.linkExcutePrincipal = linkExcutePrincipal;
	}
	/**
	 * @return the linkExcuteResult
	 */
	public Integer getLinkExcuteResult() {
		return linkExcuteResult;
	}
	/**
	 * @param linkExcuteResult the linkExcuteResult to set
	 */
	public void setLinkExcuteResult(Integer linkExcuteResult) {
		this.linkExcuteResult = linkExcuteResult;
	}
	/**
	 * @return the linkExecuteEndDate
	 */
	public Date getLinkExecuteEndDate() {
		return linkExecuteEndDate;
	}
	/**
	 * @param linkExecuteEndDate the linkExecuteEndDate to set
	 */
	public void setLinkExecuteEndDate(Date linkExecuteEndDate) {
		this.linkExecuteEndDate = linkExecuteEndDate;
	}
	/**
	 * @return the linkIfAffectOperation
	 */
	public Integer getLinkIfAffectOperation() {
		return linkIfAffectOperation;
	}
	/**
	 * @param linkIfAffectOperation the linkIfAffectOperation to set
	 */
	public void setLinkIfAffectOperation(Integer linkIfAffectOperation) {
		this.linkIfAffectOperation = linkIfAffectOperation;
	}
	/**
	 * @return the linkIfNotify
	 */
	public Integer getLinkIfNotify() {
		return linkIfNotify;
	}
	/**
	 * @param linkIfNotify the linkIfNotify to set
	 */
	public void setLinkIfNotify(Integer linkIfNotify) {
		this.linkIfNotify = linkIfNotify;
	}
	/**
	 * @return the linkIfUpdatePlan
	 */
	public Integer getLinkIfUpdatePlan() {
		return linkIfUpdatePlan;
	}
	/**
	 * @param linkIfUpdatePlan the linkIfUpdatePlan to set
	 */
	public void setLinkIfUpdatePlan(Integer linkIfUpdatePlan) {
		this.linkIfUpdatePlan = linkIfUpdatePlan;
	}
	/**
	 * @return the linkIsAccordanceProgramme
	 */
	public Integer getLinkIsAccordanceProgramme() {
		return linkIsAccordanceProgramme;
	}
	/**
	 * @param linkIsAccordanceProgramme the linkIsAccordanceProgramme to set
	 */
	public void setLinkIsAccordanceProgramme(Integer linkIsAccordanceProgramme) {
		this.linkIsAccordanceProgramme = linkIsAccordanceProgramme;
	}
	/**
	 * @return the linkIsToMaintenance
	 */
	public Integer getLinkIsToMaintenance() {
		return linkIsToMaintenance;
	}
	/**
	 * @param linkIsToMaintenance the linkIsToMaintenance to set
	 */
	public void setLinkIsToMaintenance(Integer linkIsToMaintenance) {
		this.linkIsToMaintenance = linkIsToMaintenance;
	}
	/**
	 * @return the linkMaintenanceDes
	 */
	public String getLinkMaintenanceDes() {
		return linkMaintenanceDes;
	}
	/**
	 * @param linkMaintenanceDes the linkMaintenanceDes to set
	 */
	public void setLinkMaintenanceDes(String linkMaintenanceDes) {
		this.linkMaintenanceDes = linkMaintenanceDes;
	}
	/**
	 * @return the linkNetAffectArea
	 */
	public String getLinkNetAffectArea() {
		return linkNetAffectArea;
	}
	/**
	 * @param linkNetAffectArea the linkNetAffectArea to set
	 */
	public void setLinkNetAffectArea(String linkNetAffectArea) {
		this.linkNetAffectArea = linkNetAffectArea;
	}
	/**
	 * @return the linkOperationConstrue
	 */
	public String getLinkOperationConstrue() {
		return linkOperationConstrue;
	}
	/**
	 * @param linkOperationConstrue the linkOperationConstrue to set
	 */
	public void setLinkOperationConstrue(String linkOperationConstrue) {
		this.linkOperationConstrue = linkOperationConstrue;
	}
	/**
	 * @return the linkPermitIdea
	 */
	public String getLinkPermitIdea() {
		return linkPermitIdea;
	}
	/**
	 * @param linkPermitIdea the linkPermitIdea to set
	 */
	public void setLinkPermitIdea(String linkPermitIdea) {
		this.linkPermitIdea = linkPermitIdea;
	}
	/**
	 * @return the linkPermitResult
	 */
	public Integer getLinkPermitResult() {
		return linkPermitResult;
	}
	/**
	 * @param linkPermitResult the linkPermitResult to set
	 */
	public void setLinkPermitResult(Integer linkPermitResult) {
		this.linkPermitResult = linkPermitResult;
	}
	/**
	 * @return the linkPlanEndDate
	 */
	public Date getLinkPlanEndDate() {
		return linkPlanEndDate;
	}
	/**
	 * @param linkPlanEndDate the linkPlanEndDate to set
	 */
	public void setLinkPlanEndDate(Date linkPlanEndDate) {
		this.linkPlanEndDate = linkPlanEndDate;
	}
	/**
	 * @return the linkPlanStartDate
	 */
	public Date getLinkPlanStartDate() {
		return linkPlanStartDate;
	}
	/**
	 * @param linkPlanStartDate the linkPlanStartDate to set
	 */
	public void setLinkPlanStartDate(Date linkPlanStartDate) {
		this.linkPlanStartDate = linkPlanStartDate;
	}
	/**
	 * @return the linkPlanUpdateIdea
	 */
	public String getLinkPlanUpdateIdea() {
		return linkPlanUpdateIdea;
	}
	/**
	 * @param linkPlanUpdateIdea the linkPlanUpdateIdea to set
	 */
	public void setLinkPlanUpdateIdea(String linkPlanUpdateIdea) {
		this.linkPlanUpdateIdea = linkPlanUpdateIdea;
	}
	/**
	 * @return the linkProgrammeExplain
	 */
	public String getLinkProgrammeExplain() {
		return linkProgrammeExplain;
	}
	/**
	 * @param linkProgrammeExplain the linkProgrammeExplain to set
	 */
	public void setLinkProgrammeExplain(String linkProgrammeExplain) {
		this.linkProgrammeExplain = linkProgrammeExplain;
	}
	/**
	 * @return the linkProgrammeKey
	 */
	public String getLinkProgrammeKey() {
		return linkProgrammeKey;
	}
	/**
	 * @param linkProgrammeKey the linkProgrammeKey to set
	 */
	public void setLinkProgrammeKey(String linkProgrammeKey) {
		this.linkProgrammeKey = linkProgrammeKey;
	}
	/**
	 * @return the linkProgrammeNo
	 */
	public String getLinkProgrammeNo() {
		return linkProgrammeNo;
	}
	/**
	 * @param linkProgrammeNo the linkProgrammeNo to set
	 */
	public void setLinkProgrammeNo(String linkProgrammeNo) {
		this.linkProgrammeNo = linkProgrammeNo;
	}
	/**
	 * @return the linkReferOperateDept
	 */
	public String getLinkReferOperateDept() {
		return linkReferOperateDept;
	}
	/**
	 * @param linkReferOperateDept the linkReferOperateDept to set
	 */
	public void setLinkReferOperateDept(String linkReferOperateDept) {
		this.linkReferOperateDept = linkReferOperateDept;
	}
	/**
	 * @return the linkReportAbnormityExplain
	 */
	public String getLinkReportAbnormityExplain() {
		return linkReportAbnormityExplain;
	}
	/**
	 * @param linkReportAbnormityExplain the linkReportAbnormityExplain to set
	 */
	public void setLinkReportAbnormityExplain(String linkReportAbnormityExplain) {
		this.linkReportAbnormityExplain = linkReportAbnormityExplain;
	}
	/**
	 * @return the linkRiskEvaluate
	 */
	public String getLinkRiskEvaluate() {
		return linkRiskEvaluate;
	}
	/**
	 * @param linkRiskEvaluate the linkRiskEvaluate to set
	 */
	public void setLinkRiskEvaluate(String linkRiskEvaluate) {
		this.linkRiskEvaluate = linkRiskEvaluate;
	}
	/**
	 * @return the linkTestResult
	 */
	public Integer getLinkTestResult() {
		return linkTestResult;
	}
	/**
	 * @param linkTestResult the linkTestResult to set
	 */
	public void setLinkTestResult(Integer linkTestResult) {
		this.linkTestResult = linkTestResult;
	}
	/**
	 * @return the linkWorkPlan
	 */
	public String getLinkWorkPlan() {
		return linkWorkPlan;
	}
	/**
	 * @param linkWorkPlan the linkWorkPlan to set
	 */
	public void setLinkWorkPlan(String linkWorkPlan) {
		this.linkWorkPlan = linkWorkPlan;
	}
	public String getLinkFailedReason() {
		return linkFailedReason;
	}
	public void setLinkFailedReason(String linkFailedReason) {
		this.linkFailedReason = linkFailedReason;
	}
	public String getLinkRejectReason() {
		return linkRejectReason;
	}
	public void setLinkRejectReason(String linkRejectReason) {
		this.linkRejectReason = linkRejectReason;
	}
	public String getLinkCopyPerformer() {
		return linkCopyPerformer;
	}
	public void setLinkCopyPerformer(String linkCopyPerformer) {
		this.linkCopyPerformer = linkCopyPerformer;
	}

}
