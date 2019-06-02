package com.boco.eoms.sheet.modifytime.model;


/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="ModifyTime.java.do"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * @version
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="ModifyTime"
 */
public class ModifyTime{

	private String id;

	private String beginTime;

	private String endTime;

	private String type;

	private String specialtyOne;
	
	private String specialtyTwo;
	
	private String specialtyThree;

	private String local;

	private String metNet;

	private String functionary;

	private String title;
		
	private String introduction;

	private String remarks;	

	private String deleted;

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
	 * @eoms.cn name="变更开始时间"
	 * @return
	 */
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="变更结束时间"
	 * @return
	 */
	public String getEndTime() {
		return endTime;
	}
 
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="负责人"
	 * @return
	 */
	public String getFunctionary() {
		return functionary;
	}
 
	public void setFunctionary(String functionary) {
		this.functionary = functionary;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="255"
	 * @eoms.show
	 * @eoms.cn name="简介"
	 * @return
	 */
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="地域"
	 * @return
	 */
	public String getLocal() {
		return local;
	}
 
	public void setLocal(String local) {
		this.local = local;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="网元"
	 * @return
	 */
	public String getMetNet() {
		return metNet;
	}
 
	public void setMetNet(String metNet) {
		this.metNet = metNet;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="专业"
	 * @return
	 */
	public String getSpecialtyOne() {
		return specialtyOne;
	}

	public void setSpecialtyOne(String specialtyOne) {
		this.specialtyOne = specialtyOne;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="专业1"
	 * @return
	 */
	public String getSpecialtyTwo() {
		return specialtyTwo;
	}

	public void setSpecialtyTwo(String specialtyTwo) {
		this.specialtyTwo = specialtyTwo;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="专业2"
	 * @return
	 */
	public String getSpecialtyThree() {
		return specialtyThree;
	}

	public void setSpecialtyThree(String specialtyThree) {
		this.specialtyThree = specialtyThree;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="变更主题"
	 * @return
	 */
	public String getTitle() {
		return title;
	}
 
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="50" not-null="false" unique="true"
	 * @eoms.show
	 * @eoms.cn name="变更类别"
	 * @return
	 */
	public String getType() {
		return type;
	}
 
	public void setType(String type) {
		this.type = type;
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

	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="5" not-null="false" unique="false"
	 * @eoms.show
	 * @eoms.cn name="删除标志"
	 * @return
	 */

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		deleted = deleted;
	}
}
