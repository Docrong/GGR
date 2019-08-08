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
 * <a href="User.java.html"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_supplierkpi_assessinstance"
 */
public class TawSupplierkpiAssessInstance extends BaseObject {

    private String id;
    private String templateName;
    private String supplierId;
    private String serviceType;
    private String specialType;
    private String creator;
    private Timestamp createTime;
    private int assessStatus;
    private String assessLevel;
    private String timeLatitude;
    private Timestamp assessTime;
    private String realAssessor;
    private String assessAttitude;
    private String subRole;

    private String id2specialType;
    private String id2serviceType;

    public String getId2specialType() throws DictDAOException {
        TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
                .getInstance().getBean("ItawSystemDictTypeDao");
        return dao.id2Name(this.getSpecialType());
    }

    public String getId2serviceType() throws DictDAOException {
        TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
                .getInstance().getBean("ItawSystemDictTypeDao");
        return dao.id2Name(this.getServiceType());
    }

    /**
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="false"
     */
    public String getAssessLevel() {
        return assessLevel;
    }

    public void setAssessLevel(String assessLevel) {
        this.assessLevel = assessLevel;
    }

    /**
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="false"
     */
    public int getAssessStatus() {
        return assessStatus;
    }

    public void setAssessStatus(int assessStatus) {
        this.assessStatus = assessStatus;
    }

    /**
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="false"
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    /**
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="false"
     */
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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
     * @hibernate.property length="32" not-null="false" unique="false"
     */
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="false"
     */
    public String getSpecialType() {
        return specialType;
    }

    public void setSpecialType(String specialType) {
        this.specialType = specialType;
    }

    /**
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="false"
     */
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="false"
     */
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getAssessAttitude() {
        return assessAttitude;
    }

    public void setAssessAttitude(String assessAttitude) {
        this.assessAttitude = assessAttitude;
    }

    public Timestamp getAssessTime() {
        return assessTime;
    }

    public void setAssessTime(Timestamp assessTime) {
        this.assessTime = assessTime;
    }

    public String getRealAssessor() {
        return realAssessor;
    }

    public void setRealAssessor(String realAssessor) {
        this.realAssessor = realAssessor;
    }

    public String getSubRole() {
        return subRole;
    }

    public void setSubRole(String subRole) {
        this.subRole = subRole;
    }

    public String getTimeLatitude() {
        return timeLatitude;
    }

    public void setTimeLatitude(String timeLatitude) {
        this.timeLatitude = timeLatitude;
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

}
