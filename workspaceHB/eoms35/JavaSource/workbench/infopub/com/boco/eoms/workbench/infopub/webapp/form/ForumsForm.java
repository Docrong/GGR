package com.boco.eoms.workbench.infopub.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * 
 * <p>
 * Title:信息发布版块信息form
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 22, 2008 1:46:41 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ForumsForm extends BaseForm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8350892380048821442L;

	protected String createrId;
	
	/**
	 * 移动到主题
	 */
	protected String toForumsId;

	protected String createTime;

	protected String description;

	protected String id;

	protected String title;

	protected String parentId;

	/**
	 * 删除标记
	 */
	protected String isDel;

	/**
	 * 是否为叶子节点
	 */
	protected String isLeaf;

	/** Default empty constructor. */
	public ForumsForm() {
	}

	public String getCreaterId() {
		return this.createrId;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return this.id;
	}

	/**
	 */

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getParentId() {
		return this.parentId;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/*
	 * To add non XDoclet-generated methods, create a file named
	 * xdoclet-ForumsForm.java containing the additional code and place it in
	 * your metadata/web directory.
	 */
	/**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// reset any boolean data types to false

	}

	/**
	 * @return the isLeaf
	 */
	public String getIsLeaf() {
		return isLeaf;
	}

	/**
	 * @param isLeaf
	 *            the isLeaf to set
	 */
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * @return the isDel
	 */
	public String getIsDel() {
		return isDel;
	}

	/**
	 * @param isDel
	 *            the isDel to set
	 */
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	/**
	 * @return the toForumsId
	 */
	public String getToForumsId() {
		return toForumsId;
	}

	/**
	 * @param toForumsId the toForumsId to set
	 */
	public void setToForumsId(String toForumsId) {
		this.toForumsId = toForumsId;
	}

}
