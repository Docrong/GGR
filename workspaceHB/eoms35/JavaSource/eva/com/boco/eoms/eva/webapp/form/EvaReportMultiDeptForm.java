package com.boco.eoms.eva.webapp.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.form.BaseForm;

public class EvaReportMultiDeptForm extends BaseForm implements
		java.io.Serializable {

	
	protected String areaId;//地市ID
	protected String deptId;//厂商ID
	protected String totalScore;//指标总得分
	protected List kpiScoreList; //指标分数LIST
	
	
	public String getAreaId() {
		return areaId;
	}


	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}


	public String getDeptId() {
		return deptId;
	}


	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}


	public List getKpiScoreList() {
		return kpiScoreList;
	}


	public void setKpiScoreList(List kpiScoreList) {
		this.kpiScoreList = kpiScoreList;
	}


	public String getTotalScore() {
		return totalScore;
	}


	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}


	/**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// reset any boolean data types to false

	}


}
