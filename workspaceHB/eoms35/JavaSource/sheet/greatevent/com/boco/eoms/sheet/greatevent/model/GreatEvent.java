package com.boco.eoms.sheet.greatevent.model;

public class GreatEvent {
	
	
	/**
	* @header value="重大事件保障流程"
	* @generatortype
	* @phaseid draft="DraftTask" hold="HoldTask"
	* @flowid value="062"
	* @sheetid value="01" 
	*/ 
	private String id;
	
	/**
	 * @filedlength  value="50"
	 * @texttype
	 * @alowBlank value="false"
	 * @eoms.cn value="备注"
	 * @main
	 */
	
	private String mainNetSortOne;
	
	/**
	 * @filedlength  value="50"
	 * @texttype
	 * @alowBlank value="false"
	 * @eoms.cn value="备注"
	 * @main
	 */
	
	private String mainNetSortTwo;
	
	/**
	 * @filedlength  value="50"
	 * @texttype
	 * @alowBlank value="false"
	 * @eoms.cn value="备注"
	 * @main
	 */
	
	private String mainNetSortThree;
	
	/**
   * @date
	 * @alowBlank value="false"
	 * @eoms.cn value="事件开始时间"
	 * @main
	 */
	
	private String mainEventStartTime;
	
	/**
	 * @date
	 * @alowBlank value="false"
	 * @eoms.cn value="事件结束时间"
	 * @main
	 */
	
	private String mainEventEndTime;
	
	/**
	 * @filedlength  value="500"
	 * @textarea
	 * @alowBlank value="true"	
	 * @eoms.cn value="事件描述"
	 * @main
	 */
	
	private String mainEventDesc;
	
	/**
	 * @filedlength  value="100"  此属性对应数据库的大小
	 * @textarea
	 * @alowBlank value="false"
	 * @eoms.cn value="初步处理结果"
	 * @main
	 */
	
	private String mainInitDealResult;
	
	/**
	 * @filedlength  value="500"
	 * @textarea
	 * @alowBlank value="false"
	 * @eoms.cn value="事件处理建议"
	 * @main
	 */
	
	private String mainEventDealAdvice;
	
	/**
	 * @filedlength  value="50"
	 * @texttype
	 * @alowBlank value="false"
	 * @eoms.cn value="审批对象"
	 * @link
	 */
	
	private String linkPermisObject;
	
	/**
	 * @filedlength  value="50"
	 * @texttype
	 * @alowBlank value="false"
	 * @eoms.cn value="资源准备结果"
	 * @link
	 */
	
	private String linkResReadyResult;
	
	/**
	 * @filedlength  value="300"
	 * @accesstype
	 * @alowBlank value="false" 
	 * @eoms.cn value="重大活动保障方案"
	 * @link
	 */
	
	private String linkGreatSecurityProgram;
	
	/**
	 * @filedlength  value="30"
	 * @dicttype
	 * @alowBlank value="false"
	 * @eoms.cn value="审核意见"
	 * @link
	 */
	
	private String linkAuditAdvice;
	
	/**
	 * @filedlength  value="500"
	 * @textarea
	 * @alowBlank value="false"
	 * @eoms.cn value="审核结果"
	 * @link
	 */
	
	private String linkAuditResult;
	
	/**
	 * @filedlength  value="30"
	 * @dicttype value=""
	 * @alowBlank value="false"
	 * @eoms.cn value="审批意见"
	 * @link
	 */
	
	private String linkPermisAdvice;
	
	/**
	 * @filedlength  value="500"
	 * @textarea
	 * @alowBlank value="false"
	 * @eoms.cn value="审批结果"
	 * @link
	 */
	
	private String linkPermisResult;
	
	/**
	 * @filedlength  value="500"
	 * @textarea
	 * @alowBlank value="false"
	 * @index value="rescheck_str"  
	 * @eoms.cn value="现场实施总结"
	 * @link
	 */
	
	private String linkSencePerformSummary;
	
	/**
	 * @filedlength  value="300"
	 * @accesstype
	 * @alowBlank value="false"
	 * @eoms.cn value="现场实施报告"
	 * @link
	 */
	
	private String linkSencePerformReport;
	
	/**
	 * @filedlength  value="30"
	 * @dicttype value=""
	 * @alowBlank value="false"
	 * @eoms.cn value="是否启动变更流程"
	 * @link
	 */
	
	private String linkIfStartChangeProcess;
	
	/**
	 * @filedlength  value="300"
	 * @accesstype
	 * @alowBlank value="false"
	 * @eoms.cn value="现场保障总结"
	 * @link
	 */
	
	private String linkSenceSecuritySummary;
	
	/**
	 * @filedlength  value="300"
	 * @accesstype
	 * @alowBlank value="false"
	 * @eoms.cn value="现场保障报告"
	 * @link
	 */
	
	private String linkSenceSecurityReport;
	
	/**
	 * @filedlength  value="300"
	 * @accesstype
	 * @eoms.cn value="后评估报告"
	 * @link
	 */
	
	private String linkAssessReport;
	
	/**
	 * @filedlength  value="500"
	 * @textarea
	 * @alowBlank value="false"
	 * @eoms.cn value="修订预案说明"
	 * @link
	 */
	
	private String linkAmendPlanHelp;
	
	/**
	 * @filedlength  value="300"
	 * @accesstype
	 * @alowBlank value="false"
	 * @eoms.cn value="应急预案"
	 * @link
	 */
	
	private String linkEmergencyPlan;

}
