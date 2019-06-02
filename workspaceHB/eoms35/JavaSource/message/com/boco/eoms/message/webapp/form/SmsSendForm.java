package com.boco.eoms.message.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * Generated by XDoclet/actionform. This class can be further processed with
 * XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 * 
 * @struts.form name="SmsContentTemplateForm"
 */
public class SmsSendForm extends BaseForm implements java.io.Serializable {
	
	protected String id;
	
	protected String mobiles;
	
	protected String users;
	
	protected String remark;
	
	protected String content;
	
	protected String contentRemark;
	
	protected  boolean isSendVoice;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getIsSendVoice() {
		return isSendVoice;
	}

	public void setIsSendVoice(boolean isSendVoice) {
		this.isSendVoice = isSendVoice;
	}

	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public String getContentRemark() {
		return contentRemark;
	}




	public void setContentRemark(String contentRemark) {
		this.contentRemark = contentRemark;
	}




	public String getMobiles() {
		return mobiles;
	}




	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}




	public String getRemark() {
		return remark;
	}




	public void setRemark(String remark) {
		this.remark = remark;
	}




	public String getUsers() {
		return users;
	}




	public void setUsers(String users) {
		this.users = users;
	}




	/*
	 * To add non XDoclet-generated methods, create a file named
	 * xdoclet-SmsLogForm.java containing the additional code and place it in
	 * your metadata/web directory.
	 */
	/**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// reset any boolean data types to false

	}

}
