package com.boco.eoms.sheet.base.flowchar;

/**
 * @see Node结点
 * @author zhangxb
 * @version 1.0
 * @since 2008-09-09
 */

public class Node {
	private String code; // 层次编码
	private String id; // 主键
	private String currentLink;
	private String parentId; // 上级
	private String parentLink; //上级的操作
	private String name; // 名称
	private int status;//节点的状态
	private int subSize; // 子节点个数

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getSubSize() {
		return subSize;
	}

	public void setSubSize(int subSize) {
		this.subSize = subSize;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getParentLink() {
		return parentLink;
	}

	public void setParentLink(String parentLink) {
		this.parentLink = parentLink;
	}

	public String getCurrentLink() {
		return currentLink;
	}

	public void setCurrentLink(String currentLink) {
		this.currentLink = currentLink;
	}
}
