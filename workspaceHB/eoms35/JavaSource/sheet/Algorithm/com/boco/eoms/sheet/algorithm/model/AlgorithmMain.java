package com.boco.eoms.sheet.algorithm.model;

import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * <p>
 * Title:码号资源申请工单
 * </p>
 * <p>
 * Description:码号资源申请工单
 * </p>
 * <p>
 * Mon Sep 27 18:07:01 CST 2010
 * </p>
 * 
 * @author liuyang
 * @version 3.5
 * 
 */
 
 public class AlgorithmMain extends BaseMain {
	 /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainNetSort1;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainNetSort2;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainNetSort3;

    /**
	 *
	 * @dicttype
	 */
     private java.lang.String mainTaskType;

    /**
	 *
	 * @textarea
	 */
     private java.lang.String mainTaskDescription;

    /**
	 *
	 * @textarea
	 */
     private java.lang.String mainRemark;
     
     
     private java.lang.String sheettype;
     
     private java.lang.String netName;	
     private java.lang.String netNo;
     private java.lang.String netText;	

	public java.lang.String getSheettype() {
		return sheettype;
	}

	public void setSheettype(java.lang.String sheettype) {
		this.sheettype = sheettype;
	}

	public java.lang.String getMainNetSort1() {
		return mainNetSort1;
	}

	public void setMainNetSort1(java.lang.String mainNetSort1) {
		this.mainNetSort1 = mainNetSort1;
	}

	public java.lang.String getMainNetSort2() {
		return mainNetSort2;
	}

	public void setMainNetSort2(java.lang.String mainNetSort2) {
		this.mainNetSort2 = mainNetSort2;
	}

	public java.lang.String getMainNetSort3() {
		return mainNetSort3;
	}

	public void setMainNetSort3(java.lang.String mainNetSort3) {
		this.mainNetSort3 = mainNetSort3;
	}

	public java.lang.String getMainRemark() {
		return mainRemark;
	}

	public void setMainRemark(java.lang.String mainRemark) {
		this.mainRemark = mainRemark;
	}

	public java.lang.String getMainTaskDescription() {
		return mainTaskDescription;
	}

	public void setMainTaskDescription(java.lang.String mainTaskDescription) {
		this.mainTaskDescription = mainTaskDescription;
	}

	public java.lang.String getMainTaskType() {
		return mainTaskType;
	}

	public void setMainTaskType(java.lang.String mainTaskType) {
		this.mainTaskType = mainTaskType;
	}

	public java.lang.String getNetName() {
		return netName;
	}

	public void setNetName(java.lang.String netName) {
		this.netName = netName;
	}

	public java.lang.String getNetNo() {
		return netNo;
	}

	public void setNetNo(java.lang.String netNo) {
		this.netNo = netNo;
	}

	public java.lang.String getNetText() {
		return netText;
	}

	public void setNetText(java.lang.String netText) {
		this.netText = netText;
	}

	 
 }