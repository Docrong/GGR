/**
 * com.boco.eoms.commons.transaction.test.form.TstActionForm
 */
package com.boco.eoms.commons.transaction.test.form;

import org.apache.struts.action.ActionForm;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class TstActionForm extends ActionForm {

    //
    private String id;

    private String name;

    private String remark;

    private String simType;

    /**
     * default constructor
     */
    public TstActionForm() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the simType
     */
    public String getSimType() {
        return simType;
    }

    /**
     * @param simType the simType to set
     */
    public void setSimType(String simType) {
        this.simType = simType;
    }

}
