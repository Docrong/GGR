
package com.boco.eoms.sheet.arithmeticmodify.model;


import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseLink;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="arithmeticmodifyLink.java.html"> <i>View Source </i> </a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="arithmeticmodifylink"
 */
public class ArithmeticModifyLink extends BaseLink
{
            
	private String linkYuLiuA;	        
	private String linkYuLiuB;	    
	private String linkYuLiuC;
	private String linkYuLiuD;	        
	private String linkYuLiuE;	    

	 //审批结果
	 private String linkPermitResult;
	 //审批意见
	 private String linkPermitOpinion;
	 //需求确认结果
	 private String linkRequireConfirm;
	 //测试开始时间
	 private Date linkTestStartTime;
	 //测试结束时间
	 private Date linkTestEndTime;
	 //测试结果
	 private String linkTestResult;
	 //验证报告结果
	 private String linkYZReportResult;
	 //检查报告结果
	 private String linkJCReportResult;
	 //指标检查结果
	 private String linkTargetCheckResult;
	 //相关OA号或算法审批工单号
	 private String linkSheetNumber;
	 //部署结果报告
	 private String linkBSResultReport;
	 //算法发布时间
	 private Date linkPulishTime;
	
	public String getLinkYuLiuA() {
		return linkYuLiuA;
	}
	public void setLinkYuLiuA(String linkYuLiuA) {
		this.linkYuLiuA = linkYuLiuA;
	}
	public String getLinkYuLiuB() {
		return linkYuLiuB;
	}
	public void setLinkYuLiuB(String linkYuLiuB) {
		this.linkYuLiuB = linkYuLiuB;
	}
	public String getLinkYuLiuC() {
		return linkYuLiuC;
	}
	public void setLinkYuLiuC(String linkYuLiuC) {
		this.linkYuLiuC = linkYuLiuC;
	}
	public String getLinkYuLiuD() {
		return linkYuLiuD;
	}
	public void setLinkYuLiuD(String linkYuLiuD) {
		this.linkYuLiuD = linkYuLiuD;
	}
	public String getLinkYuLiuE() {
		return linkYuLiuE;
	}
	public void setLinkYuLiuE(String linkYuLiuE) {
		this.linkYuLiuE = linkYuLiuE;
	}
	public String getLinkBSResultReport() {
		return linkBSResultReport;
	}
	public void setLinkBSResultReport(String linkBSResultReport) {
		this.linkBSResultReport = linkBSResultReport;
	}
	public String getLinkJCReportResult() {
		return linkJCReportResult;
	}
	public void setLinkJCReportResult(String linkJCReportResult) {
		this.linkJCReportResult = linkJCReportResult;
	}
	public String getLinkPermitOpinion() {
		return linkPermitOpinion;
	}
	public void setLinkPermitOpinion(String linkPermitOpinion) {
		this.linkPermitOpinion = linkPermitOpinion;
	}
	public String getLinkPermitResult() {
		return linkPermitResult;
	}
	public void setLinkPermitResult(String linkPermitResult) {
		this.linkPermitResult = linkPermitResult;
	}
	public Date getLinkPulishTime() {
		return linkPulishTime;
	}
	public void setLinkPulishTime(Date linkPulishTime) {
		this.linkPulishTime = linkPulishTime;
	}
	public String getLinkRequireConfirm() {
		return linkRequireConfirm;
	}
	public void setLinkRequireConfirm(String linkRequireConfirm) {
		this.linkRequireConfirm = linkRequireConfirm;
	}
	public String getLinkSheetNumber() {
		return linkSheetNumber;
	}
	public void setLinkSheetNumber(String linkSheetNumber) {
		this.linkSheetNumber = linkSheetNumber;
	}
	public String getLinkTargetCheckResult() {
		return linkTargetCheckResult;
	}
	public void setLinkTargetCheckResult(String linkTargetCheckResult) {
		this.linkTargetCheckResult = linkTargetCheckResult;
	}
	public Date getLinkTestEndTime() {
		return linkTestEndTime;
	}
	public void setLinkTestEndTime(Date linkTestEndTime) {
		this.linkTestEndTime = linkTestEndTime;
	}
	public String getLinkTestResult() {
		return linkTestResult;
	}
	public void setLinkTestResult(String linkTestResult) {
		this.linkTestResult = linkTestResult;
	}
	public Date getLinkTestStartTime() {
		return linkTestStartTime;
	}
	public void setLinkTestStartTime(Date linkTestStartTime) {
		this.linkTestStartTime = linkTestStartTime;
	}
	public String getLinkYZReportResult() {
		return linkYZReportResult;
	}
	public void setLinkYZReportResult(String linkYZReportResult) {
		this.linkYZReportResult = linkYZReportResult;
	}
	
}
