
package com.boco.eoms.sheet.safeaudit.model;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="SafeAuditMain.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="SafeAuditmain"
 */
public class SafeAuditMain extends BaseMain {

    /**
     * @dicttype
     */
    private java.lang.String mainSafeAuditType1;

    /**
     * @dicttype
     */
    private java.lang.String mainSafeAuditType2;

    /**
     * @dicttype
     */
    private java.lang.String mainSafeAuditType3;

    /**
     * @textarea
     */
    private java.lang.String mainSafeAuditRequest;

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainSafeAuditType1() {
        return mainSafeAuditType1;
    }

    public void setMainSafeAuditType1(java.lang.String mainSafeAuditType1) {
        this.mainSafeAuditType1 = mainSafeAuditType1;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainSafeAuditType2() {
        return mainSafeAuditType2;
    }

    public void setMainSafeAuditType2(java.lang.String mainSafeAuditType2) {
        this.mainSafeAuditType2 = mainSafeAuditType2;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getMainSafeAuditType3() {
        return mainSafeAuditType3;
    }

    public void setMainSafeAuditType3(java.lang.String mainSafeAuditType3) {
        this.mainSafeAuditType3 = mainSafeAuditType3;
    }

    /**
     * @return
     * @hibernate.property value="2000"
     * @eoms.show
     */
    public java.lang.String getMainSafeAuditRequest() {
        return mainSafeAuditRequest;
    }

    public void setMainSafeAuditRequest(java.lang.String mainSafeAuditRequest) {
        this.mainSafeAuditRequest = mainSafeAuditRequest;
    }

}
