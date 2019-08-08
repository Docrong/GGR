package com.boco.eoms.workbench.contact.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.*;
import org.apache.struts.upload.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * Generated by XDoclet/actionform. This class can be further processed with XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 *
 * @author 龚玉峰
 * @version 3.5.1
 * @struts.form name="tawWorkbenchContactForm"
 * <p>
 * Title:个人通讯录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 22, 2008 15:59:30 AM
 * </p>
 */
public class TawWorkbenchContactForm
        extends BaseForm
        implements java.io.Serializable {

    protected String address;

    protected String contactName;
    protected String oldContactName;

    protected String deleted;

    protected String deptId;

    protected String deptName;

    protected String email;

    protected String groupId;

    protected String id;

    protected String position;

    protected String tele;

    protected String userId;

    private FormFile thisFile;

    public FormFile getThisFile() {
        return thisFile;
    }

    public void setThisFile(FormFile thisFile) {
        this.thisFile = thisFile;
    }

    /**
     * Default empty constructor.
     */
    public TawWorkbenchContactForm() {
    }

    public String getAddress() {
        return this.address;
    }

    /**
     *
     */

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return this.contactName;
    }

    /**
     *
     */

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getDeleted() {
        return this.deleted;
    }

    /**
     *
     */

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getDeptId() {
        return this.deptId;
    }

    /**
     *
     */

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return this.deptName;
    }

    /**
     *
     */

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEmail() {
        return this.email;
    }

    /**
     *
     */

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupId() {
        return this.groupId;
    }

    /**
     *
     */

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public String getPosition() {
        return this.position;
    }

    /**
     *
     */

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTele() {
        return this.tele;
    }

    /**
     *
     */

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getUserId() {
        return this.userId;
    }

    /**
     *
     */

    public void setUserId(String userId) {
        this.userId = userId;
    }

        /* To add non XDoclet-generated methods, create a file named
           xdoclet-TawWorkbenchContactForm.java 
           containing the additional code and place it in your metadata/web directory.
        */

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     * javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false

    }

    public String getOldContactName() {
        return oldContactName;
    }

    public void setOldContactName(String oldContactName) {
        this.oldContactName = oldContactName;
    }

}
