package com.boco.eoms.sheet.nbproducts.model;

import com.boco.eoms.sheet.base.model.BaseObject;
/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="NBProducts.java.do"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * @version
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="NBProducts"
 */
public class NBProducts extends BaseObject {
	
	private String id;

	private String nbPName;

	private String businessDept;

	private String businessSort;

	private String recorder;

	private String recordTime;

	private String recorderDept;

	private String recorderContact;

	private String keyword;

	private String nbPdescription;

	private String remarks;

	private String node;

	private Integer deleted;
	
	private String procode;
	
	private String code;
	
	private String sheetName;
	
	private String businessSortTwo;
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="工单名称"
	 * @return
	 */

	

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="产品编号"
	 * @return
	 */


	public String getProcode() {
		return procode;
	}

	public void setProcode(String procode) {
		this.procode = procode;
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
	 * @eoms.show
	 * @eoms.cn name="业务部门"
	 * @return
	 */
	public String getBusinessDept() {
		return businessDept;
	}

	public void setBusinessDept(String businessDept) {
		this.businessDept = businessDept;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="业务分类"
	 * @return
	 */
	public String getBusinessSort() {
		return businessSort;
	}

	public void setBusinessSort(String businessSort) {
		this.businessSort = businessSort;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="记录人"
	 * @return
	 */
	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="记录时间"
	 * @return
	 */
	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="记录人部门"
	 * @return
	 */
	public String getRecorderDept() {
		return recorderDept;
	}

	public void setRecorderDept(String recorderDept) {
		this.recorderDept = recorderDept;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="记录人联系方式"
	 * @return
	 */
	public String getRecorderContact() {
		return recorderContact;
	}

	public void setRecorderContact(String recorderContact) {
		this.recorderContact = recorderContact;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false"
	 * @eoms.show
	 * @eoms.cn name="关键字"
	 * @return
	 */
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}




	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @eoms.cn name="备注"
	 * @return
	 */
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @eoms.cn name="附件"
	 * @return
	 */
	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public String toString() {
		return null;
	}
	

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="产品名称"
	 * @return
	 */
	public String getNbPName() {
		return nbPName;
	}

	public void setNbPName(String nbPName) {
		this.nbPName = nbPName;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="200" not-null="false"
	 * @eoms.show
	 * @eoms.cn name="新产品描述"
	 * @return
	 */
	public String getNbPdescription() {
		return nbPdescription;
	}

	public void setNbPdescription(String nbPdescription) {
		this.nbPdescription = nbPdescription;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBusinessSortTwo() {
		return businessSortTwo;
	}

	public void setBusinessSortTwo(String businessSortTwo) {
		this.businessSortTwo = businessSortTwo;
	}



}
