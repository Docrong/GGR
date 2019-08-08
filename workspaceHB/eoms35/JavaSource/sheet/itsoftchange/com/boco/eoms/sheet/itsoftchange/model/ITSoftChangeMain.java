
package com.boco.eoms.sheet.itsoftchange.model;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="ITSoftChangeMain.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="ITSoftChangemain"
 */
public class ITSoftChangeMain extends BaseMain {

    /**
     * @texttype
     */
    private java.lang.String mainITRequirementID;

    /**
     * @dicttype
     */
    private java.lang.String mainIsReferCost;

    /**
     * @dicttype
     */
    private java.lang.String mainNetSystem;

    /**
     * @textarea
     */
    private java.lang.String mainChangeDesc;

    /**
     *
     */
    private java.util.Date mainCompleteTime;

    /**
     * @texttype
     */
    private java.lang.String mainApplyer;

    /**
     * @accesstype
     */
    private java.lang.String mainChangeDetail;

    /**
     * KPI新增字段
     */
    private Integer mainRejectTimes;

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainITRequirementID() {
        return mainITRequirementID;
    }

    public void setMainITRequirementID(java.lang.String mainITRequirementID) {
        this.mainITRequirementID = mainITRequirementID;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainIsReferCost() {
        return mainIsReferCost;
    }

    public void setMainIsReferCost(java.lang.String mainIsReferCost) {
        this.mainIsReferCost = mainIsReferCost;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainNetSystem() {
        return mainNetSystem;
    }

    public java.util.Date getMainCompleteTime() {
        return mainCompleteTime;
    }

    public void setMainCompleteTime(java.util.Date mainCompleteTime) {
        this.mainCompleteTime = mainCompleteTime;
    }

    public void setMainNetSystem(java.lang.String mainNetSystem) {
        this.mainNetSystem = mainNetSystem;
    }

    /**
     * @return
     * @hibernate.property value="500"
     * @eoms.show
     */
    public java.lang.String getMainChangeDesc() {
        return mainChangeDesc;
    }

    public void setMainChangeDesc(java.lang.String mainChangeDesc) {
        this.mainChangeDesc = mainChangeDesc;
    }


    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainApplyer() {
        return mainApplyer;
    }

    public void setMainApplyer(java.lang.String mainApplyer) {
        this.mainApplyer = mainApplyer;
    }

    /**
     * @return
     * @hibernate.property value="300"
     * @eoms.show
     */
    public java.lang.String getMainChangeDetail() {
        return mainChangeDetail;
    }

    public void setMainChangeDetail(java.lang.String mainChangeDetail) {
        this.mainChangeDetail = mainChangeDetail;
    }

    public Integer getMainRejectTimes() {
        return mainRejectTimes;
    }

    public void setMainRejectTimes(Integer mainRejectTimes) {
        this.mainRejectTimes = mainRejectTimes;
    }

}
