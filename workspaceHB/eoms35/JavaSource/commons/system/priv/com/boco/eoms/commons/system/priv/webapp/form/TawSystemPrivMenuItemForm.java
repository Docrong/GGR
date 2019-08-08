package com.boco.eoms.commons.system.priv.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * Generated by XDoclet/actionform. This class can be further processed with
 * XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 *
 * @struts.form name="tawSystemPrivMenuItemForm"
 */
public class TawSystemPrivMenuItemForm extends BaseForm implements
        java.io.Serializable {

    protected String id;

    protected String menuid;

    protected String code;

    protected String itemName;

    protected String parentcode;

    protected String isApp;

    protected String isLeaf;

    protected String isHide;

    protected String remark;

    /**
     * Default empty constructor.
     */
    public TawSystemPrivMenuItemForm() {
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

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getCode() {
        return this.code;
    }

    /**
     * @struts.validator type="required"
     */

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentcode() {
        return this.parentcode;
    }

    /**
     * @struts.validator type="required"
     */

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public String getIsApp() {
        return this.isApp;
    }

    /**
     *
     */

    public void setIsApp(String isApp) {
        this.isApp = isApp;
    }

    public String getIsLeaf() {
        return this.isLeaf;
    }

    /**
     *
     */

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getIsHide() {
        return this.isHide;
    }

    /**
     *
     */

    public void setIsHide(String isHide) {
        this.isHide = isHide;
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

    /*
     * To add non XDoclet-generated methods, create a file named
     * xdoclet-TawSystemPrivMenuItemForm.java containing the additional code and
     * place it in your metadata/web directory.
     */

    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     * javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
