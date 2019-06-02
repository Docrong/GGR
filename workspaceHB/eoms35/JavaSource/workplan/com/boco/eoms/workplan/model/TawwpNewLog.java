package com.boco.eoms.workplan.model;

import java.io.Serializable;

import com.boco.eoms.common.oo.DataObject;

/**
 * @hibernate.class table="TAW_WP_LOG"
 */

public class TawwpNewLog implements Serializable, DataObject {

	private String id = null; // ��ʶ

	private java.util.Date createTime = null; // ִ��ʱ��

	private String model = null; //

	private String resultState = null; // ִ�н��

	private String logType = null; // ִ������

	private String message = null;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public TawwpNewLog() {
	}

	/**
	 * @hibernate.id column="ID" length="32" unsaved-value="null"
	 *               generator-class="uuid.hex"
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="LOGTYPE" length=1" not-null="true"
	 *                     update="false"
	 */
	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the resultState
	 */
	public String getResultState() {
		return resultState;
	}

	/**
	 * @param resultState
	 *            the resultState to set
	 */
	public void setResultState(String resultState) {
		this.resultState = resultState;
	}

	/**
	 * @return the createTime
	 */
	public java.util.Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

}
