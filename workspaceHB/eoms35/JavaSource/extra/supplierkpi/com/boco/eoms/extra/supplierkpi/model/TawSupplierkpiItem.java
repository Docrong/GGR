package com.boco.eoms.extra.supplierkpi.model;

import java.sql.Timestamp;

import com.boco.eoms.base.model.BaseObject;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.dao.hibernate.TawSystemDictTypeDaoHibernate;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawSupplierkpiItem.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_supplierkpi_item"
 */
public class TawSupplierkpiItem extends BaseObject{

	private String id;
	private String templateId;
	private String itemType;
	private String assessContent;
	private String assessNote;
	private String dataSource;
	private String dataType;
	private String unit;
	private String creator;
	private Timestamp createTime;
	private String statictsCycle;
	private String dataSourceImplManner;
	private String isImpersonality;
	private String writeManner;
	private String preActor;
	private String isAssess;
	private String assessRole;
	private String planGeneCycle;
	private String kpiName;
	private String assessMoment;
	
	
	private String id2dataSource;
	private String id2dataType;
	private String id2statictsCycle;
	private String id2writeManner;
	private String id2unit;
	private String id2isImpersonality;
	private String id2itemType;
    
	private int assessStatus;
	private String dictId;
	private int deleted;
	
    public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public int getAssessStatus() {
		return assessStatus;
	}
	public void setAssessStatus(int assessStatus) {
		this.assessStatus = assessStatus;
	}
	public String getId2dataSource() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeDao");
		return dao.id2Name(this.getDataSource());
	}
    public String getId2dataType() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeDao");
		return dao.id2Name(this.getDataType());
	}
    public String getId2statictsCycle() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeDao");
		return dao.id2Name(this.getStatictsCycle());
	}
    public String getId2writeManner() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeDao");
		return dao.id2Name(this.getWriteManner());
	}
    public String getId2unit() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeDao");
		return dao.id2Name(this.getUnit());
	}
    public String getId2isImpersonality() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeDao");
		return dao.id2Name(this.getIsImpersonality());
	}
    public String getId2itemType() throws DictDAOException {
		TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDictTypeDao");
		return dao.id2Name(this.getItemType());
	}
	
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="500" not-null="false" unique="true"
	 */
	public String getAssessContent() {
		return assessContent;
	}
	public void setAssessContent(String assessContent) {
		this.assessContent = assessContent;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="500" not-null="false" unique="true"
	 */
	public String getAssessNote() {
		return assessNote;
	}
	public void setAssessNote(String assessNote) {
		this.assessNote = assessNote;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public String getDataSourceImplManner() {
		return dataSourceImplManner;
	}
	public void setDataSourceImplManner(String dataSourceImplManner) {
		this.dataSourceImplManner = dataSourceImplManner;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
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
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public String getIsImpersonality() {
		return isImpersonality;
	}
	public void setIsImpersonality(String isImpersonality) {
		this.isImpersonality = isImpersonality;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public String getPlanGeneCycle() {
		return planGeneCycle;
	}
	public void setPlanGeneCycle(String planGeneCycle) {
		this.planGeneCycle = planGeneCycle;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public String getPreActor() {
		return preActor;
	}
	public void setPreActor(String preActor) {
		this.preActor = preActor;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public String getStatictsCycle() {
		return statictsCycle;
	}
	public void setStatictsCycle(String statictsCycle) {
		this.statictsCycle = statictsCycle;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public String getWriteManner() {
		return writeManner;
	}
	public void setWriteManner(String writeManner) {
		this.writeManner = writeManner;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public String getAssessRole() {
		return assessRole;
	}
	public void setAssessRole(String assessRole) {
		this.assessRole = assessRole;
	}
	/**
	 * @struts.validator type="required"
	 * @hibernate.property length="32" not-null="false" unique="true"
	 */
	public String getIsAssess() {
		return isAssess;
	}
	public void setIsAssess(String isAssess) {
		this.isAssess = isAssess;
	}
	
	public String getKpiName() {
		return kpiName;
	}
	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}

	public String getAssessMoment() {
		return assessMoment;
	}
	public void setAssessMoment(String assessMoment) {
		this.assessMoment = assessMoment;
	}
	public boolean equals(Object o) {
		// TODO 自动生成方法存根
		return false;
	}

	public int hashCode() {
		// TODO 自动生成方法存根
		return 0;
	}

	public String toString() {
		// TODO 自动生成方法存根
		return null;
	}
	
}
