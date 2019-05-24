package com.boco.eoms.sheet.businessplan.model;

public class BusinessPlan {

	 /**
	* @header value="新业务设计流程"
	* @generatortype
	* @phaseid draft="draft" hold="hold"
	* @flowid value="021"
	* @sheetid value="021" 
	* 
	*/ 
	private String id;
	/**
	 * @filedlength  value="50"  
	 * @texttype
	 * @alowBlank value="false"
	 * @eoms.cn value="业务类别"
	 * @main
	 */
	private String mainProductType;
	/**
	 * @filedlength  value="50" 
	 * @dicttype value="102001"
	 * @alowBlank value="false"
	 * @eoms.cn value="新产品名称"
	 * @main
	 */
	private String mainProductName;
	/**
	 * @filedlength  value="50"  
	 * @texttype
	 * @alowBlank value="false"
	 * @eoms.cn value="新产品编号"
	 * @main
	 */
	private String mainProductCode;
	/**
	 * @filedlength value="50"
     * @alowBlank value="false"
	 * @dicttype value="102002"
	 * @eoms.cn value="需求类别"
	 * @main
	 */
	private String mainReqType;
	/**
	 * @filedlength  value="50"  
	 * @textarea
	 * @alowBlank value="false"
	 * @eoms.cn value="技术规范说明"
	 * @main
	 */
	private String mainTecDesc;
	/**
	 * @filedlength  value="50"
	 * @accesstype
	 * @alowBlank value="false" 
	 * @eoms.cn value="技术规范"
	 * @main
	 */
	private String mainStandard;
	/**
	 * @filedlength  value="50"  
	 * @texttype
	 * @alowBlank value="false"
	 * @eoms.cn value="审批对象"
	 * @link
	 */	
	private String linkAuditPer;
	/**
	 * @filedlength  value="50"  
	 * @textarea
	 * @alowBlank value="false"
	 * @eoms.cn value="可行性分析概述"
	 * @link
	 */	
	private String linkAnalyse;	
	/**
	 * @filedlength  value="50"
	 * @accesstype
	 * @alowBlank value="false" 
	 * @eoms.cn value="可行性报告"
	 * @link
	 */
	private String linkreport;	
	/**
	 * @filedlength value="50"
     * @alowBlank value="true"
	 * @dicttype value="102003"
	 * @eoms.cn value="是否可行"
	 * @link
	 */
	private String linkIsKx;
	/**
	 * @filedlength value="50"
     * @alowBlank value="true"
	 * @dicttype value="102004"
	 * @eoms.cn value="审批结果"
	 * @link
	 */
	private String linkAuditResult;
	/**
	 * @filedlength  value="50"  
	 * @textarea
	 * @alowBlank value="false"
	 * @eoms.cn value="审批意见"
	 * @link
	 */	
	private String linkAuditDesc;	
	/**
	 * @filedlength  value="50"  
	 * @textarea
	 * @alowBlank value="false"
	 * @eoms.cn value="技术规范评审意见"
	 * @link
	 */	
	private String linkOpion;
	/**
	 * @filedlength  value="50"
	 * @accesstype
	 * @alowBlank value="false" 
	 * @eoms.cn value="技术规范评审报告"
	 * @link
	 */
	private String linkOpionReport;	
	
	

}
