package com.boco.eoms.commons.system.priv.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

public class TawSystemPrivRegion extends BaseObject implements Serializable {

	// 主键ID
	private Integer id;

	// userid 或者 roleid
	private String objectid;

	// 菜单方案ID
	private String menuid;

	// 域ID 机房ID或者部门ID
	private String regionid;

	// 域类型 机房域或部门域
	private String isapp;

	// 叶子节点标识
	private String leaf;

	// 是否参加排班标识0为参加 1为不参加
	private String flag;

	// 域中包含的URL 操作定义
	private String url;

	// 角色或者用户标识
	private String objecttype;

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

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getRegionid() {
		return regionid;
	}

	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}

	public String getIsapp() {
		return isapp;
	}

	public void setIsapp(String isapp) {
		this.isapp = isapp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return Returns the objecttype.
	 */
	public String getObjecttype() {
		return objecttype;
	}

	/**
	 * @param objecttype
	 *            The objecttype to set.
	 */
	public void setObjecttype(String objecttype) {
		this.objecttype = objecttype;
	}
}
