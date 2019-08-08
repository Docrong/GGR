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
 * <a href="TawSupplierkpiTemplate.java.html"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_supplierkpi_template"
 */
public class TawSupplierkpiTemplate extends BaseObject {

    private String id;

    private String assessInstanceId;

    private String templateName;

    private String serviceType;

    private String specialType;

    private String creator;

    private Timestamp createTime;

    private int assessStatus;

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
     * @hibernate.property length="32" not-null="false" unique="true"
     */
    public int getAssessStatus() {
        return assessStatus;
    }

    public void setAssessStatus(int assessStatus) {
        this.assessStatus = assessStatus;
    }

    /**
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="true"
     */
    public String getAssessInstanceId() {
        return assessInstanceId;
    }

    public void setAssessInstanceId(String assessInstanceId) {
        this.assessInstanceId = assessInstanceId;
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
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="true"
     */
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="true"
     */
    public String getSpecialType() {
        return specialType;
    }

    public void setSpecialType(String specialType) {
        this.specialType = specialType;
    }

    public boolean equals(Object o) {
        if (!(o instanceof TawSupplierkpiTemplate)) {
            return false;
        }
        TawSupplierkpiTemplate template = (TawSupplierkpiTemplate) o;
        if (template.getId().equals(getId())) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.getId().hashCode();
    }

    public String toString() {
        return this.getId();
    }
}
