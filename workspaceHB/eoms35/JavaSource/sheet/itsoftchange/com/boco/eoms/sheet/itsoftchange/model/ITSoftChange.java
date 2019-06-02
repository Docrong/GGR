package com.boco.eoms.sheet.itsoftchange.model;

public class ITSoftChange {
	/**
	* @header value="IT需求开发流程" 
	* @generatortype
	* @phaseid draft="DraftTask" hold="HoldTask"
	* @flowid value="092" 
	* @sheetid value="01" 
	*/ 
	private String id;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="true"
	 * @eoms.cn value="相关IT需求申请工单号"
	 * @main
	 * @texttype
	 */
	private String mainITRequirementID;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="是否涉及开发费用"
	 * @main
	 * @dicttype
	 */
	private String mainIsReferCost;	
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="网管支撑系统"
	 * @main
	 * @dicttype
	 */
	private String mainNetSystem;

	/**
	 * @filedlength length="500"
	 * @allowBlank value="false"
	 * @eoms.cn value="变更概述"
	 * @main
	 * @textarea
	 */
	private String mainChangeDesc;	
	

	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="变更完成时限"
	 * @main
	 * @date
	 */
	private String mainCompleteTime;
	/**
	 * @filedlength length="50"
	 * @allowBlank value="true"
	 * @eoms.cn value="需求申请人（测试人）"
	 * @main
	 * @texttype
	 */
	private String mainApplyer;
	
	/**
	 * @filedlength length="300"
	 * @allowBlank value="false"
	 * @eoms.cn value="IT变更详细说明"
	 * @main
	 * @accesstype
	 */
	private String mainChangeDetail;

	
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="开发人员及联系方式"
	 * @link
	 * @texttype
	 */
	private String linkDevContacter;	
	/**
	 * @filedlength length="500"
	 * @allowBlank value="false"
	 * @eoms.cn value="完成说明"
	 * @link
	 * @textarea
	 */
	private String linkCompleteDesc;		
	
	/**
	 * @filedlength length="300"
	 * @allowBlank value="false"
	 * @eoms.cn value="测试指南"
	 * @link
	 * @accesstype
	 */
	private String linkTestGuide;
	
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="测试结果"
	 * @link
	 * @dicttype
	 */
	private String linkTestResult;
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="测试满意度"
	 * @link
	 * @dicttype
	 */
	private String linkTestSatisfaction;
		
	/**
	 * @filedlength length="500"
	 * @allowBlank value="false"
	 * @eoms.cn value="测试说明"
	 * @link
	 * @textarea
	 */
	private String linkTestDesc;	
	
	/**
	 * @filedlength length="300"
	 * @allowBlank value="false"
	 * @eoms.cn value="测试附件"
	 * @link
	 * @accesstype
	 */
	private String linkTestNote;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="完成割接上线时间"
	 * @link
	 * @date
	 */
	private String linkOnlineTime;	
	
	
	
	/**
	 * @filedlength length="500"
	 * @allowBlank value="false"
	 * @eoms.cn value="上线说明"
	 * @link
	 * @textarea
	 */
	private String linkOnlineDesc;	
	
	
	
	/**
	 * @filedlength length="300"
	 * @allowBlank value="true"
	 * @eoms.cn value="用户手册变更说明"
	 * @link
	 * @accesstype
	 */
	private String linkUserNoteDesc;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="审核结果"
	 * @link
	 * @dicttype
	 */
	private String linkAuditResult;
	/**
	 * @filedlength length="50"
	 * @allowBlank value="true"
	 * @eoms.cn value="需求分析工作量（人天）"
	 * @link
	 * @texttype
	 */
	private String linkAnalysisCount;
	/**
	 * @filedlength length="50"
	 * @allowBlank value="true"
	 * @eoms.cn value="现场实施与测试工作量（人天）"
	 * @link
	 * @texttype
	 */
	private String linkTestCount;
	/**
	 * @filedlength length="50"
	 * @allowBlank value="true"
	 * @eoms.cn value="研发工作量（人天）"
	 * @link
	 * @texttype
	 */
	private String linkDevCount;
	
}
