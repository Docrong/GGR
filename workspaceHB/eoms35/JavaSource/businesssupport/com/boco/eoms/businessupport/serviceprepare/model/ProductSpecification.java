package com.boco.eoms.businessupport.serviceprepare.model;

import java.io.Serializable;
/**
 * 产品规格表
 * 
 * @author yangliangliang
 *
 */
public class ProductSpecification implements Serializable{
	/**
	 * 主键
	 */
	private String id;
    /**
     * 服务规格名称
     */    
    private String name;	
    /**
     * 服务规格说明
     */    
    private String comment;		
    /**
     * 编码
     */    
    private String code;
	/**
	 * 删除标示
	 */
	private String deleted;
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}			
	
}