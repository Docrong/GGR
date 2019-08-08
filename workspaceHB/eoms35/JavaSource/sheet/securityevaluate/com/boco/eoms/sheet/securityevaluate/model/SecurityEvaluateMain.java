
package com.boco.eoms.sheet.securityevaluate.model;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="SecurityEvaluateMain.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="SecurityEvaluatemain"
 */
public class SecurityEvaluateMain extends BaseMain {

    /**
     * @texttype
     */
    private java.lang.String mainNetSortOne;

    /**
     * @texttype
     */
    private java.lang.String mainNetSortTwo;

    /**
     * @texttype
     */
    private java.lang.String mainNetSortThree;

    /**
     * @texttype
     */
    private java.util.Date mainCompleteTime;

    /**
     * @accesstype
     */
    private java.lang.String mainSafetyStatement;

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainNetSortOne() {
        return mainNetSortOne;
    }

    public void setMainNetSortOne(java.lang.String mainNetSortOne) {
        this.mainNetSortOne = mainNetSortOne;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainNetSortTwo() {
        return mainNetSortTwo;
    }

    public void setMainNetSortTwo(java.lang.String mainNetSortTwo) {
        this.mainNetSortTwo = mainNetSortTwo;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainNetSortThree() {
        return mainNetSortThree;
    }

    public void setMainNetSortThree(java.lang.String mainNetSortThree) {
        this.mainNetSortThree = mainNetSortThree;
    }

    /**
     * @hibernate.property value="35"
     * @eoms.show
     * @return
     */


    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainSafetyStatement() {
        return mainSafetyStatement;
    }

    public void setMainSafetyStatement(java.lang.String mainSafetyStatement) {
        this.mainSafetyStatement = mainSafetyStatement;
    }

    public java.util.Date getMainCompleteTime() {
        return mainCompleteTime;
    }

    public void setMainCompleteTime(java.util.Date mainCompleteTime) {
        this.mainCompleteTime = mainCompleteTime;
    }

}
