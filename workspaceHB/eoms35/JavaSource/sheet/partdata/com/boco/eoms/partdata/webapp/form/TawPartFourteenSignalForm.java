package com.boco.eoms.partdata.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:14位信令点
 * </p>
 * <p>
 * Description:14位信令点范围
 * </p>
 * <p>
 * Tue Jul 06 14:18:23 CST 2010
 * </p>
 * 
 * @moudle.getAuthor() Josh
 * @moudle.getVersion() 3.5
 * 
 */
public class TawPartFourteenSignalForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	


	/**
	 *
	 * 主键字段
	 *
	 */
	private java.lang.String id;
   
	public void setId(java.lang.String id){
		this.id= id;       
	}
   
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *
	 * userid
	 *
	 */
	private java.lang.String userid;
   
	public void setUserid(java.lang.String userid){
		this.userid= userid;       
	}
   
	public java.lang.String getUserid(){
		return this.userid;
	}

	/**
	 *
	 * 更新时间
	 *
	 */
	private java.lang.String updatedate;
   
	public void setUpdatedate(java.lang.String updatedate){
		this.updatedate= updatedate;       
	}
   
	public java.lang.String getUpdatedate(){
		return this.updatedate;
	}

	/**
	 *
	 * signalnum
	 *
	 */
	private java.lang.String signalnum;
   
	public void setSignalnum(java.lang.String signalnum){
		this.signalnum= signalnum;       
	}
   
	public java.lang.String getSignalnum(){
		return this.signalnum;
	}

	/**
	 *
	 * lacvalue
	 *
	 */
	private java.lang.String lacvalue;
   
	public void setLacvalue(java.lang.String lacvalue){
		this.lacvalue= lacvalue;       
	}
   
	public java.lang.String getLacvalue(){
		return this.lacvalue;
	}


}