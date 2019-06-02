package com.boco.eoms.workplan.util;

/**
 * <p>
 * Title: import模板
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
  
 * </p>
 * 
 * @author 龚玉峰
 * @version 1.0
 * 
 */
public class FMImportModel {

	private String sysTypeId;   // 系统类型
	
	private String netTypeid;   // 网元类型
	
	private String modelNames;  // 模板名称 / 作业计划名称
	
	private String cycle;       // 周期
	
	private String remark; //  执行帮助
	
	private String executeNames;  // 执行项名称
	
	private String format;    //默认填写
	
	private String remark1;
	
	private String remark2;
	

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getExecuteNames() {
		return executeNames;
	}

	public void setExecuteNames(String executeNames) {
		this.executeNames = executeNames;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getModelNames() {
		return modelNames;
	}

	public void setModelNames(String modelNames) {
		this.modelNames = modelNames;
	}

	public String getNetTypeid() {
		return netTypeid;
	}

	public void setNetTypeid(String netTypeid) {
		this.netTypeid = netTypeid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSysTypeId() {
		return sysTypeId;
	}

	public void setSysTypeId(String sysTypeId) {
		this.sysTypeId = sysTypeId;
	}
	
	
	
	
}
