package com.boco.eoms.workbench.infopub.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:信息发布版块
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 21, 2008 3:31:03 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */

public class Forums extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8767832611703970873L;

	/**
	 * 主键
	 * 
	 * 
	 */
	private String id;

	/**
	 * 版块名称
	 * 
	 * 
	 */
	private String title;

	/**
	 * 版块描述
	 * 
	 * 
	 */
	private String description;

	/**
	 * 父级版块
	 * 
	 * 
	 */
	private String parentId;

	/**
	 * 创建人id
	 * 
	 * 
	 */
	private String createrId;

	/**
	 * 创建时间
	 * 
	 */
	private Date createTime;

	/**
	 * 是否为叶子节点
	 */
	private String isLeaf;

	/**
	 * 是否为删除版块
	 */
	private String isDel;
	
	/**
	 * @return the createrId
	 */
	public String getCreaterId() {
		return createrId;
	}

	/**
	 * @param createrId
	 *            the createrId to set
	 */
	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the description
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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

	/**
	 * @return the parentId *
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof Forums) {
			Forums forums = (Forums) o;
			if (this.id != null || this.id.equals(forums.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
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

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
