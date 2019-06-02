package com.boco.eoms.sheet.emergencydrill.model;

import java.util.Date;

public class EmergencyDrill {
	/**
	* @header value="应急预案演练流程" 
	* @generatortype
	* @phaseid draft="DraftTask" hold="HoldTask"
	* @flowid value="061" 
	* @sheetid value="01" 
	*/ 
	private String id;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="应急分类一"
	 * @main
	 * @subdict
	 */
	private String mainEmergencySortOne;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="应急分类二"
	 * @main
	 * @subdict
	 */
	private String mainEmergencySortTwo;
	
	/**
	 * @filedlength length="35"
	 * @allowBlank value="false"
	 * @eoms.cn value="完成时限"
	 * @main
	 * @texttype
	 */
	private Date mainCompleteTime;
	
	/**
	 * @filedlength length="500"
	 * @allowBlank value="false"
	 * @eoms.cn value="备注"
	 * @main
	 * @texttype
	 */
	private String mainRemarks;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="应急演练计划"
	 * @main
	 * @accesstype
	 */
	private String mainEmergencyPlan;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="审核对象"
	 * @link
	 * @texttype
	 */
	private String linkAuditObject;
	
	/**
	 * @filedlength length="500"
	 * @allowBlank value="false"
	 * @eoms.cn value="应急演练方案说明"
	 * @link
	 * @textarea
	 */
	private String linkEmergencyDrillNote;
	
	/**
	 * @filedlength length="255"
	 * @allowBlank value="true"
	 * @eoms.cn value="应急演练方案"
	 * @link
	 * @accesstype
	 */
	private String linkEmergencyDrill;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="审核意见"
	 * @link
	 * @dicttype
	 */
	private String linkAuditOpinion;
	
	/**
	 * @filedlength length="500"
	 * @allowBlank value="false"
	 * @eoms.cn value="审核结果"
	 * @link
	 * @texttype
	 */
	private String linkAuditResult;
	
	/**
	 * @filedlength length="500"
	 * @allowBlank value="true"
	 * @eoms.cn value="实施演练结果"
	 * @link
	 * @texttype
	 */
	private String linkImplementResult;
	/**
	 * @filedlength length="255"
	 * @allowBlank value="false"
	 * @eoms.cn value="演练报告"
	 * @link
	 * @accesstype
	 */
	private String linkDrillReport;
	/**
	 * @filedlength length="500"
	 * @allowBlank value="false"
	 * @eoms.cn value="实施演练总结"
	 * @link
	 * @texttype
	 */
	private String linkImplementSummary;
	/**
	 * @filedlength length="255"
	 * @allowBlank value="false"
	 * @eoms.cn value="演练总结报告"
	 * @link
	 * @accesstype
	 */
	private String linkDrillSummaryReport;
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="是否修改预案"
	 * @link
	 * @dicttype
	 */
	private String linkIsAmendmentDrill;
	/**
	 * @filedlength length="500"
	 * @allowBlank value="false"
	 * @eoms.cn value="修订预案说明"
	 * @link
	 * @texttype
	 */
	private String linkAmendmentDrillNote;
	/**
	 * @filedlength length="255"
	 * @allowBlank value="false"
	 * @eoms.cn value="应急预案"
	 * @link
	 * @accesstype
	 */
	private String linkEmergencyPreparedness;
	
	
}
