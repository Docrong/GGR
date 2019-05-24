package com.boco.eoms.sheet.offlineData.model;

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
 
 public class OfflineDataMain extends BaseMain {
	 /**
	 *
	 * @dicttype
	 */

    /**
	 *
	 * @textarea
	 */
     private java.lang.String mainRemark;
     
     
     private java.lang.String offlineProgram;
     
     private java.lang.String offlineNum;	
     private java.lang.String offlineType;



	public java.lang.String getMainRemark() {
		return mainRemark;
	}

	public void setMainRemark(java.lang.String mainRemark) {
		this.mainRemark = mainRemark;
	}


	public java.lang.String getOfflineNum() {
		return offlineNum;
	}

	public void setOfflineNum(java.lang.String offlineNum) {
		this.offlineNum = offlineNum;
	}

	public java.lang.String getOfflineProgram() {
		return offlineProgram;
	}

	public void setOfflineProgram(java.lang.String offlineProgram) {
		this.offlineProgram = offlineProgram;
	}

	public java.lang.String getOfflineType() {
		return offlineType;
	}

	public void setOfflineType(java.lang.String offlineType) {
		this.offlineType = offlineType;
	}
	 
 }