/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.netchange.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * @author panlong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NetChangeLink extends BaseLink {

	private String linkProjectExplain;
	private Date linkEndDate;
	private String linkProjectKey;
	private String linkRiskEvaluate;
	private String linkOperationConstrue;
	private String linkAuditingResult;
	private String linkAuditingIdea;
	private String linkPermitResult;
	private String linkPermitIdea;
	private Integer linkIfStartCircuit;
	private Integer linkIfStartSoft;
	private Integer linkIfStartData;
	private String linkColligateExcuteResult;
	private String linkExcuteResult;
	private String linkIfAccordingProject;
	private String linkIfExcuteSuccess;
	private String linkTestSummarize;
	private String linkWorkPlan;
	private String linkIsToMaintenance;	
	private String linkMaintenanceDes;
	private Date linkExecuteEndDate;
	private String linkFailedReason;
	
	public Date getLinkEndDate() {
		return linkEndDate;
	}
	public void setLinkEndDate(Date linkEndDate) {
		this.linkEndDate = linkEndDate;
	}
	public String getLinkProjectKey() {
		return linkProjectKey;
	}
	public void setLinkProjectKey(String linkProjectKey) {
		this.linkProjectKey = linkProjectKey;
	}
	public String getLinkRiskEvaluate() {
		return linkRiskEvaluate;
	}
	public void setLinkRiskEvaluate(String linkRiskEvaluate) {
		this.linkRiskEvaluate = linkRiskEvaluate;
	}
	public String getLinkAuditingIdea() {
		return linkAuditingIdea;
	}
	public void setLinkAuditingIdea(String linkAuditingIdea) {
		this.linkAuditingIdea = linkAuditingIdea;
	}
	public String getLinkAuditingResult() {
		return linkAuditingResult;
	}
	public void setLinkAuditingResult(String linkAuditingResult) {
		this.linkAuditingResult = linkAuditingResult;
	}
	public String getLinkColligateExcuteResult() {
		return linkColligateExcuteResult;
	}
	public void setLinkColligateExcuteResult(String linkColligateExcuteResult) {
		this.linkColligateExcuteResult = linkColligateExcuteResult;
	}

	public String getLinkExcuteResult() {
		return linkExcuteResult;
	}
	public void setLinkExcuteResult(String linkExcuteResult) {
		this.linkExcuteResult = linkExcuteResult;
	}
	public String getLinkIfAccordingProject() {
		return linkIfAccordingProject;
	}
	public void setLinkIfAccordingProject(String linkIfAccordingProject) {
		this.linkIfAccordingProject = linkIfAccordingProject;
	}
	public String getLinkIfExcuteSuccess() {
		return linkIfExcuteSuccess;
	}
	public void setLinkIfExcuteSuccess(String linkIfExcuteSuccess) {
		this.linkIfExcuteSuccess = linkIfExcuteSuccess;
	}
	public String getLinkIsToMaintenance() {
		return linkIsToMaintenance;
	}
	public void setLinkIsToMaintenance(String linkIsToMaintenance) {
		this.linkIsToMaintenance = linkIsToMaintenance;
	}
	public String getLinkMaintenanceDes() {
		return linkMaintenanceDes;
	}
	public void setLinkMaintenanceDes(String linkMaintenanceDes) {
		this.linkMaintenanceDes = linkMaintenanceDes;
	}
	public String getLinkOperationConstrue() {
		return linkOperationConstrue;
	}
	public void setLinkOperationConstrue(String linkOperationConstrue) {
		this.linkOperationConstrue = linkOperationConstrue;
	}
	public String getLinkPermitIdea() {
		return linkPermitIdea;
	}
	public void setLinkPermitIdea(String linkPermitIdea) {
		this.linkPermitIdea = linkPermitIdea;
	}
	public String getLinkPermitResult() {
		return linkPermitResult;
	}
	public void setLinkPermitResult(String linkPermitResult) {
		this.linkPermitResult = linkPermitResult;
	}
	public String getLinkProjectExplain() {
		return linkProjectExplain;
	}
	public void setLinkProjectExplain(String linkProjectExplain) {
		this.linkProjectExplain = linkProjectExplain;
	}
	public String getLinkTestSummarize() {
		return linkTestSummarize;
	}
	public void setLinkTestSummarize(String linkTestSummarize) {
		this.linkTestSummarize = linkTestSummarize;
	}
	public String getLinkWorkPlan() {
		return linkWorkPlan;
	}
	public void setLinkWorkPlan(String linkWorkPlan) {
		this.linkWorkPlan = linkWorkPlan;
	}
	/**
	 * @return the linkIfStartCircuit
	 */
	public Integer getLinkIfStartCircuit() {
		return linkIfStartCircuit;
	}
	/**
	 * @param linkIfStartCircuit the linkIfStartCircuit to set
	 */
	public void setLinkIfStartCircuit(Integer linkIfStartCircuit) {
		this.linkIfStartCircuit = linkIfStartCircuit;
	}
	/**
	 * @return the linkIfStartData
	 */
	public Integer getLinkIfStartData() {
		return linkIfStartData;
	}
	/**
	 * @param linkIfStartData the linkIfStartData to set
	 */
	public void setLinkIfStartData(Integer linkIfStartData) {
		this.linkIfStartData = linkIfStartData;
	}
	/**
	 * @return the linkIfStartSoft
	 */
	public Integer getLinkIfStartSoft() {
		return linkIfStartSoft;
	}
	/**
	 * @param linkIfStartSoft the linkIfStartSoft to set
	 */
	public void setLinkIfStartSoft(Integer linkIfStartSoft) {
		this.linkIfStartSoft = linkIfStartSoft;
	}
	public Date getLinkExecuteEndDate() {
		return linkExecuteEndDate;
	}
	public void setLinkExecuteEndDate(Date linkExecuteEndDate) {
		this.linkExecuteEndDate = linkExecuteEndDate;
	}
	public String getLinkFailedReason() {
		return linkFailedReason;
	}
	public void setLinkFailedReason(String linkFailedReason) {
		this.linkFailedReason = linkFailedReason;
	}
}
