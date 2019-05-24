package com.boco.eoms.workbench.infopub.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * 
 * <p>
 * Title:信息查阅历史记录form
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:28:04 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadHistoryForm extends BaseForm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8942520311568350764L;

	protected String id;

	protected String ip;

	protected String readTime;

	protected String threadId;

	protected String userId;

	protected String historyType;

	protected String comments;

	/** Default empty constructor. */
	public ThreadHistoryForm() {
	}

	public String getId() {
		return this.id;
	}

	/**
	 */

	public void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return this.ip;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getReadTime() {
		return this.readTime;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setReadTime(String readTime) {
		this.readTime = readTime;
	}

	public String getThreadId() {
		return this.threadId;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public String getUserId() {
		return this.userId;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHistoryType() {
		return this.historyType;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setHistoryType(String historyType) {
		this.historyType = historyType;
	}

	public String getComments() {
		return this.comments;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setComments(String comments) {
		this.comments = comments;
	}

	/*
	 * To add non XDoclet-generated methods, create a file named
	 * xdoclet-ThreadHistoryForm.java containing the additional code and place
	 * it in your metadata/web directory.
	 */
	/**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// reset any boolean data types to false

	}

}
