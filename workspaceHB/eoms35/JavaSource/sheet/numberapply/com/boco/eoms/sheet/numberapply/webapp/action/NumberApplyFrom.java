package com.boco.eoms.sheet.numberapply.webapp.action;

import org.apache.struts.upload.FormFile;

import com.boco.eoms.sheet.base.webapp.form.MultiPageForm;

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
 
 public class NumberApplyFrom extends MultiPageForm  {
	 
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FormFile theFile;

	public FormFile getTheFile() {
		return theFile;
	}

	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}

	
 	
 	
 }
 



