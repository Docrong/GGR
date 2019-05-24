package com.boco.eoms.parter.baseinfo.carmgr.model;

import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="User.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="pnr_carmgr"
 */
public class CarMgr extends BaseObject{
	private String id;
	private String carName;			//车辆名称
	private String carTypes;		//车辆类型
	protected String carTypesName;
	private String carNum;			//车牌号
	private String parterCor;		//代维公司
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCarTypes() {
		return carTypes;
	}
	public void setCarTypes(String carTypes) {
		this.carTypes = carTypes;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */
	public String getParterCor() {
		return parterCor;
	}
	public void setParterCor(String parterCor) {
		this.parterCor = parterCor;
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
	public String getCarTypesName() {
		carTypesName = DictMgrLocator.getId2NameService().id2Name(
				this.carTypes, "ItawSystemDictTypeDao");
		return carTypesName;
	}
	public void setCarTypesName(String carTypesName) {
		this.carTypesName = carTypesName;
	}
}
