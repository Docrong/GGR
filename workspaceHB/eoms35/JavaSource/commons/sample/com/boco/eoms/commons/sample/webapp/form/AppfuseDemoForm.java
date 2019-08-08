package com.boco.eoms.commons.sample.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * Generated by XDoclet/actionform. This class can be further processed with XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 *
 * @struts.form name="appfuseDemoForm"
 */
public class AppfuseDemoForm
        extends BaseForm
        implements java.io.Serializable {

    protected String columnOne;

    protected String columnTwo;

    protected String id;

    /**
     * Default empty constructor.
     */
    public AppfuseDemoForm() {
    }

    public String getColumnOne() {
        return this.columnOne;
    }

    /**
     * @struts.validator type="required"
     */

    public void setColumnOne(String columnOne) {
        this.columnOne = columnOne;
    }

    public String getColumnTwo() {
        return this.columnTwo;
    }

    /**
     * @struts.validator type="required"
     */

    public void setColumnTwo(String columnTwo) {
        this.columnTwo = columnTwo;
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

        /* To add non XDoclet-generated methods, create a file named
           xdoclet-AppfuseDemoForm.java 
           containing the additional code and place it in your metadata/web directory.
        */

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     * javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false

    }

}
