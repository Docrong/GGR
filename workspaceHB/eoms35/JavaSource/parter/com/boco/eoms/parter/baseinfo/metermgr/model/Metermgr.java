package com.boco.eoms.parter.baseinfo.metermgr.model;

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
 * @hibernate.class table="pnr_metermgr"
 */
public class Metermgr extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String maintenUnitId ;               	//代维公司
	private String meterName;				//仪表名称
	private String meterSort;				//仪表使用分类（字典）
	public String meterSortName;				//仪表使用分类（字典）
	private String meterNumber;				//仪表编号
	private String useSummarize;				//用途概述
	
		
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
	/**
	 * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */

	public String getMaintenUnitId() {
		return maintenUnitId;
	}

	public void setMaintenUnitId(String maintenUnitId) {
		this.maintenUnitId = maintenUnitId;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */

	public String getMeterName() {
		return meterName;
	}

	public void setMeterName(String meterName) {
		this.meterName = meterName;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */

	public String getMeterSort() {
		return meterSort;
	}

	public void setMeterSort(String meterSort) {
		this.meterSort = meterSort;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */

	public String getMeterNumber() {
		return meterNumber;
	}

	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 */

	public String getUseSummarize() {
		return useSummarize;
	}

	public void setUseSummarize(String useSummarize) {
		this.useSummarize = useSummarize;
	}
	public String getMeterSortName() {
		meterSortName = DictMgrLocator.getId2NameService().id2Name(
				this.meterSort, "ItawSystemDictTypeDao");
		return meterSortName;
	}

	public void setMeterSortName(String meterSortName) {
		this.meterSortName = meterSortName;
	}
		
}
