package com.boco.eoms.extra.supplierkpi.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.system.dict.dao.hibernate.TawSystemDictTypeDaoHibernate;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;

/**
 * Generated by XDoclet/actionform. This class can be further processed with XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 *
 * @struts.form name="tawSupplierkpiTemplateForm"
 */
public class TawSupplierkpiTemplateForm
        extends BaseForm
        implements java.io.Serializable {

    protected int assessStatus;

    protected String assessInstanceId;

    protected String createTime;

    protected String creator;

    protected String id;

    protected String templateName;

    protected String serviceType;

    protected String specialType;

    protected String id2specialType;
    protected String id2serviceType;

    protected String servicedictid;
    protected String specialdictid;

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
     * Default empty constructor.
     */
    public TawSupplierkpiTemplateForm() {
    }

    public int getAssessStatus() {
        return this.assessStatus;
    }

    /**
     * @struts.validator type="required"
     */

    public void setAssessStatus(int assessStatus) {
        this.assessStatus = assessStatus;
    }

    public String getAssessInstanceId() {
        return this.assessInstanceId;
    }

    /**
     * @struts.validator type="required"
     */

    public void setAssessInstanceId(String assessInstanceId) {
        this.assessInstanceId = assessInstanceId;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    /**
     * @struts.validator type="required"
     */

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return this.creator;
    }

    /**
     * @struts.validator type="required"
     */

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getId() {
        return this.id;
    }

    /**
     *
     */

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    /**
     * @struts.validator type="required"
     */

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getServiceType() {
        return this.serviceType;
    }

    /**
     * @struts.validator type="required"
     */

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getSpecialType() {
        return this.specialType;
    }

    /**
     * @struts.validator type="required"
     */

    public void setSpecialType(String specialType) {
        this.specialType = specialType;
    }

        /* To add non XDoclet-generated methods, create a file named
           xdoclet-TawSupplierkpiTemplateForm.java 
           containing the additional code and place it in your metadata/web directory.
        */

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     * javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false

    }

    /**
     * @return Returns the servicedictid.
     */
    public String getServicedictid() {
        return servicedictid;
    }

    /**
     * @param servicedictid The servicedictid to set.
     */
    public void setServicedictid(String servicedictid) {
        this.servicedictid = servicedictid;
    }

    public String getSpecialdictid() {
        return specialdictid;
    }

    public void setSpecialdictid(String specialdictid) {
        this.specialdictid = specialdictid;
    }

    /**
     * @param id2serviceType The id2serviceType to set.
     */
    public void setId2serviceType(String id2serviceType) {
        this.id2serviceType = id2serviceType;
    }

    /**
     * @param id2specialType The id2specialType to set.
     */
    public void setId2specialType(String id2specialType) {
        this.id2specialType = id2specialType;
    }
}
