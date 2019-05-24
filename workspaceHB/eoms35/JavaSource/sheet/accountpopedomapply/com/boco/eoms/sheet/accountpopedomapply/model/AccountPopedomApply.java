package com.boco.eoms.sheet.accountpopedomapply.model;

public class AccountPopedomApply {
	
	
	/**
	* @header value="账号权限申请流程" 
	* @generatortype
	* @phaseid draft="DraftHumTask" hold="HoldHumTask"
	* @flowid value="074" 
	* @sheetid value="01" 
	*/ 
	private String id;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="true"
	 * @eoms.cn value="网络分类(一级)*"
	 * @main
	 * @dicttype
	 */
	private String netType1;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="true"
	 * @eoms.cn value="网络分类(二级)*"
	 * @main
	 * @dicttype
	 */
	private String netType2;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="true"
	 * @eoms.cn value="网络分类(三级)*"
	 * @main
	 * @dicttype
	 */
	private String netType3;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="帐号权限生效时间"
	 * @main
	 * @date
	 */
	private String becomeTime;
	
	/**
	 * @filedlength length="50"
	 * @allowBlank value="false"
	 * @eoms.cn value="帐号权限失效时间"
	 * @main
	 * @date
	 */
	private String abateTime;
	
	/**
	 * @filedlength length="100"
	 * @allowBlank value="false"
	 * @eoms.cn value="所属系统"
	 * @main
	 * @texttype
	 */
	private String system;
	
	/**
	 * @filedlength length="2000"
	 * @allowBlank value="false"
	 * @eoms.cn value="申请说明"
	 * @main
	 * @textarea
	 */
	private String applyExplain;
	
	
	
	
	/**
	 * @filedlength length="2000"
	 * @allowBlank value="false"
	 * @eoms.cn value="审核意见"
	 * @link
	 * @textarea
	 */
	private String applyAttitude;
	
	/**
	 * @filedlength length="2000"
	 * @allowBlank value="false"
	 * @eoms.cn value="审核结果"
	 * @link
	 * @textarea
	 */
	private String applyResult;
	
	/**
	 * @filedlength length="2000"
	 * @allowBlank value="false"
	 * @eoms.cn value="执行结果"
	 * @link
	 * @textarea
	 */
	private String deResult;

}
