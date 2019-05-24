package com.boco.eoms.message.model;

import java.util.List;

import com.boco.eoms.base.model.BaseObject;
/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="mms_content"
 */
public class MmsContent extends BaseObject {
	
	private String id;
	
	private String monitorId;
	
	private String contentId;
	
	private String content;
	
	private String contentType;
	
	private String position;
	
	private String deleted;
	
	private MmsContent mmsContent;
	
	private List subContents;
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="true" unique="true"
	 * @eoms.show
	 * @eoms.cn name="彩信轮询表ID"
	 * @return
	 */
	public String getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="1500" not-null="true" unique="true"
	 * @eoms.show
	 * @eoms.cn name="发送内容"
	 * @return
	 */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="5" not-null="false" unique="false"
	 * @eoms.show
	 * @eoms.cn name="删除标志"
	 * @return
	 */
	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * 
	 * @return
	 */
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	
	
	public List getSubContents() {
		return subContents;
	}

	public void setSubContents(List subContents) {
		this.subContents = subContents;
	}

	public void addMmsContent(MmsContent mmsContent) {
		subContents.add(mmsContent);
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
