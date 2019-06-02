package com.boco.eoms.system.mappingstorage.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:存储映射
 * </p>
 * <p>
 * Description:存储映射
 * </p>
 * <p>
 * Wed Apr 08 09:10:47 CST 2009
 * </p>
 * 
 * @author sam
 * @version 1.0
 * 
 */
public class Mapping extends BaseObject {

	/**
	 * 锟斤拷锟�
	 */
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * app_code
	 *
	 */
	private String app_code;
   
	public void setApp_code(String app_code){
		this.app_code= app_code;       
	}
   
	public String getApp_code(){
		return this.app_code;
	}

	/**
	 *
	 * app_name
	 *
	 */
	private String app_name;
   
	public void setApp_name(String app_name){
		this.app_name= app_name;       
	}
   
	public String getApp_name(){
		return this.app_name;
	}

	/**
	 *
	 * mapping_table
	 *
	 */
	

	/**
	 *
	 * context
	 *
	 */
	private String context;
   
	public void setContext(String context){
		this.context= context;       
	}
   
	public String getContext(){
		return this.context;
	}

	/**
	 *
	 * mapping_id
	 *
	 */
	
	private String new_table;
	public void setNew_table(String new_table){
		this.new_table=new_table;
	}
	public String getNew_table(){
		return new_table;
	}
	
	private String deleted;
	
	


	

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	private String beanid;
	

	public String getBeanid() {
		return beanid;
	}

	public void setBeanid(String beanid) {
		this.beanid = beanid;
	}

	public boolean equals(Object o) {
		if( o instanceof Mapping ) {
			Mapping mapping=(Mapping)o;
			if (this.id != null || this.id.equals(mapping.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}