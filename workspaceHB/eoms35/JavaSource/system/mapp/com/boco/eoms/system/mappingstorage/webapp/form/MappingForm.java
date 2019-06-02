package com.boco.eoms.system.mappingstorage.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

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
 * @moudle.getAuthor() sam
 * @moudle.getVersion() 1.0
 * 
 */
public class MappingForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
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
	
	/*
	 * new_table
	 */
	private String new_table;
	public void setNew_table(String new_table){
		this.new_table=new_table;
	}
	public String getNew_table(){
		return new_table;
	}

	/**
	 *
	 * mapping_id
	 *
	 */
	
	
	private String deleted="0";

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
	
	


}