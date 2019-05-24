package com.boco.eoms.sheet.safeaudit.model;

public class SafeAudit {
	
	/**
	* @header value="安全审计流程" 
	* @generatortype
	* @phaseid draft="DraftHumTask" hold="HoldingHumTask"
	* @flowid value="072" 
	* @sheetid value="01" 
	*/ 
	private String id;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="网络分类（一级）"
	 * @main
	 * @dicttype
	 */
	private String mainSafeAuditType1;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="网络分类（二级)"
	 * @main
	 * @dicttype
	 */
	private String mainSafeAuditType2;
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="网络分类（三级）"
	 * @main
	 * @dicttype
	 */
	private String mainSafeAuditType3;
	/**
	 * @filedlength length="2000"
	 * @allowBlank value="true"
	 * @eoms.cn value="安全审计要求"
	 * @main
	 * @textarea
	 */
	private String mainSafeAuditRequest;
	/**
	 * @filedlength length="2000"
	 * @allowBlank value="false"
	 * @eoms.cn value="安全现状描述"
	 * @link
	 * @textarea
	 */
	private String linkPresentDescribe;
	/**
	 * @filedlength length="2000"
	 * @allowBlank value="false"
	 * @eoms.cn value="安全现状报告"
	 * @link
	 * @textarea
	 */
	private String linkPresentReport;
	/**
	 * @filedlength length="200"
	 * @allowBlank value="false"
	 * @eoms.cn value="审核对象"
	 * @link
	 * @texttype
	 */
	private String linkCheckObject;
	/**
	 * @filedlength length="300"
	 * @allowBlank value="false"
	 * @eoms.cn value="审计报告"
	 * @link
	 * @accesstype
	 */
	private String linkAuditReport;
	/**
	 * @filedlength length="5"
	 * @allowBlank value="false"
	 * @eoms.cn value="审批意见"
	 * @link
	 * @dicttype
	 */
	private String linkCheckIdeas;
	/**
	 * @filedlength length="300"
	 * @allowBlank value="false"
	 * @eoms.cn value="审批结果"
	 * @link
	 * @textarea
	 */
	private String linkCheckResult;
	/**
	 * @filedlength length="3000"
	 * @allowBlank value="false"
	 * @eoms.cn value="退回原因"
	 * @link
	 * @textarea
	 */
	private String linkUntreadReason;
}
