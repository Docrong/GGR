package com.boco.eoms.km.knowledge.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:知识管理
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 13:30:17 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() eoms
 * @moudle.getVersion() 1.0
 * 
 */
public class KmContentsDictForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
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
	 * 知识ID
	 *
	 */
	private java.lang.String contentId;
   
	public void setContentId(java.lang.String contentId){
		this.contentId= contentId;       
	}
   
	public java.lang.String getContentId(){
		return this.contentId;
	}

	/**
	 *
	 * 字典ID
	 *
	 */
	private java.lang.String dictId;
   
	public void setDictId(java.lang.String dictId){
		this.dictId= dictId;       
	}
   
	public java.lang.String getDictId(){
		return this.dictId;
	}

	/**
	 *
	 * 字典类型
	 *
	 */
	private java.lang.String dictType;
   
	public void setDictType(java.lang.String dictType){
		this.dictType= dictType;       
	}
   
	public java.lang.String getDictType(){
		return this.dictType;
	}

}