package com.boco.eoms.sheet.offlineData.model;

import com.boco.eoms.sheet.base.model.BaseLink;

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
 
 public class OfflineDataLink extends BaseLink {
	 /**
		 * @textarea
		 */
	     private java.lang.String isConfirmation;
	     private java.lang.String linkAuditResult;
	    /**
		 * @textarea
		 */
	     private java.lang.String indeedDept;

		public java.lang.String getIndeedDept() {
			return indeedDept;
		}

		public void setIndeedDept(java.lang.String indeedDept) {
			this.indeedDept = indeedDept;
		}

		public java.lang.String getIsConfirmation() {
			return isConfirmation;
		}

		public void setIsConfirmation(java.lang.String isConfirmation) {
			this.isConfirmation = isConfirmation;
		}

		public java.lang.String getLinkAuditResult() {
			return linkAuditResult;
		}

		public void setLinkAuditResult(java.lang.String linkAuditResult) {
			this.linkAuditResult = linkAuditResult;
		}


 }