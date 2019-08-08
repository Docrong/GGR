package com.boco.eoms.commons.message.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * Generated by XDoclet/actionform. This class can be further processed with
 * XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 *
 * @struts.form name="tawCommonMessageAddServiceForm"
 */
public class TawCommonMessageAddServiceForm extends BaseForm implements
        java.io.Serializable {

    private static final long serialVersionUID = 1L;

    protected String issendimediat;

    protected String issendnight;

    protected String messagetype;

    protected String modelid;

    protected String modelname;

    protected String operid;

    protected String opername;

    protected String remark;

    protected String urgency;

    protected String id;

    protected String modeloperid;

    protected String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getModeloperid() {
        return modeloperid;
    }

    public void setModeloperid(String modeloperid) {
        this.modeloperid = modeloperid;
    }

    /**
     * Default empty constructor.
     */
    public TawCommonMessageAddServiceForm() {
    }

    public String getIssendimediat() {
        return this.issendimediat;
    }

    /**
     *
     */

    public void setIssendimediat(String issendimediat) {
        this.issendimediat = issendimediat;
    }

    public String getIssendnight() {
        return this.issendnight;
    }

    /**
     *
     */

    public void setIssendnight(String issendnight) {
        this.issendnight = issendnight;
    }

    public String getMessagetype() {
        return this.messagetype;
    }

    /**
     *
     */

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    public String getModelid() {
        return this.modelid;
    }

    /**
     *
     */

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    public String getModelname() {
        return this.modelname;
    }

    /**
     *
     */

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getOperid() {
        return this.operid;
    }

    /**
     *
     */

    public void setOperid(String operid) {
        this.operid = operid;
    }

    public String getOpername() {
        return this.opername;
    }

    /**
     *
     */

    public void setOpername(String opername) {
        this.opername = opername;
    }

    public String getRemark() {
        return this.remark;
    }

    /**
     *
     */

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrgency() {
        return this.urgency;
    }

    /**
     *
     */

    public void setUrgency(String urgency) {
        this.urgency = urgency;
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

    /*
     * To add non XDoclet-generated methods, create a file named
     * xdoclet-TawCommonMessageAddServiceForm.java containing the additional
     * code and place it in your metadata/web directory.
     */

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     * javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false

    }

}
