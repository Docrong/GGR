package com.boco.eoms.sheet.securityevaluate.model;

public class SecurityEvaluate {

	
	/**
	* @header value="安全评估流程" 
	* @generatortype
	* @phaseid draft="DraftTask" hold="HoldTask"
	* @flowid value="071" 
	* @sheetid value="01" 
	*/ 
	private String id;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="网络分类一"
	 * @main
	 * @texttype
	 */
	private String mainNetSortOne;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="网络分类二"
	 * @main
	 * @texttype
	 */
	private String mainNetSortTwo;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="网络分类三"
	 * @main
	 * @texttype
	 */
	private String mainNetSortThree;
	
	/**
	 * @filedlength length="35"
	 * @allowBlank value="false"
	 * @eoms.cn value="评估完成时限"
	 * @main
	 * @texttype
	 */
	private String mainCompleteTime;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="true"
	 * @eoms.cn value="安全任务说明"
	 * @main
	 * @accesstype
	 */
	private String mainSafetyStatement;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="审核对象"
	 * @link
	 * @texttype
	 */
	private String linkAuditObject;
	
	/**
	 * @filedlength length="2000"
	 * @allowBlank value="false"
	 * @eoms.cn value="分析结果"
	 * @link
	 * @textarea
	 */
	private String linkAnalysisResult;
	
	/**
	 * @filedlength length="255"
	 * @allowBlank value="true"
	 * @eoms.cn value="分析报告"
	 * @link
	 * @accesstype
	 */
	private String linkAnalysisReport;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="审核意见"
	 * @link
	 * @texttype
	 */
	private String linkAuditOpinion;
	
	/**
	 * @filedlength length="2000"
	 * @allowBlank value="false"
	 * @eoms.cn value="审核结果"
	 * @link
	 * @texttype
	 */
	private String linkAuditResult;
	
	/**
	 * @filedlength length="10"
	 * @allowBlank value="true"
	 * @eoms.cn value="是否启动安全问题处理流程"
	 * @link
	 * @texttype
	 */
	private String linkIsStartProcess;
	
	
}
