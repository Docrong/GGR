
package com.boco.eoms.sheet.itrequirement.model;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="ITRequirementMain.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="ITRequirementmain"
 */
public class ITRequirementMain extends BaseMain {

    /**
     * @dicttype
     */
    private java.lang.String mainNetSystem;

    /**
     * @texttype
     */
    private java.lang.String mainSheetID;

    /**
     * @dicttype
     */
    private java.lang.String mainUrgentDegree;

    /**
     * @texttype
     */
    private java.lang.String mainBusinessTarget;

    /**
     * @texttype
     */
    private java.lang.String mainUser;

    /**
     *
     */
    private java.util.Date mainCompleteTime;

    /**
     * @textarea
     */
    private java.lang.String mainRequirementDesc;

    /**
     * @accesstype
     */
    private java.lang.String mainRequirementDetail;

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainNetSystem() {
        return mainNetSystem;
    }

    public void setMainNetSystem(java.lang.String mainNetSystem) {
        this.mainNetSystem = mainNetSystem;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainSheetID() {
        return mainSheetID;
    }

    public void setMainSheetID(java.lang.String mainSheetID) {
        this.mainSheetID = mainSheetID;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainUrgentDegree() {
        return mainUrgentDegree;
    }

    public void setMainUrgentDegree(java.lang.String mainUrgentDegree) {
        this.mainUrgentDegree = mainUrgentDegree;
    }

    /**
     * @return
     * @hibernate.property value="300"
     * @eoms.show
     */
    public java.lang.String getMainBusinessTarget() {
        return mainBusinessTarget;
    }

    public void setMainBusinessTarget(java.lang.String mainBusinessTarget) {
        this.mainBusinessTarget = mainBusinessTarget;
    }

    /**
     * @return
     * @hibernate.property value="300"
     * @eoms.show
     */
    public java.lang.String getMainUser() {
        return mainUser;
    }

    public void setMainUser(java.lang.String mainUser) {
        this.mainUser = mainUser;
    }

    public java.util.Date getMainCompleteTime() {
        return mainCompleteTime;
    }

    public void setMainCompleteTime(java.util.Date mainCompleteTime) {
        this.mainCompleteTime = mainCompleteTime;
    }

    /**
     * @return
     * @hibernate.property value="2000"
     * @eoms.show
     */
    public java.lang.String getMainRequirementDesc() {
        return mainRequirementDesc;
    }

    public void setMainRequirementDesc(java.lang.String mainRequirementDesc) {
        this.mainRequirementDesc = mainRequirementDesc;
    }

    /**
     * @return
     * @hibernate.property value="300"
     * @eoms.show
     */
    public java.lang.String getMainRequirementDetail() {
        return mainRequirementDetail;
    }

    public void setMainRequirementDetail(java.lang.String mainRequirementDetail) {
        this.mainRequirementDetail = mainRequirementDetail;
    }

}
