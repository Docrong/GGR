
package com.boco.eoms.sheet.securitydeal.model;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="SecurityDealMain.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="SecurityDealmain"
 */
public class SecurityDealMain extends BaseMain {

    /**
     * @dicttype
     */
    private java.lang.String mainNetSortOne;

    /**
     *
     */
    private java.lang.String mainNetSortTwo;

    /**
     *
     */
    private java.lang.String mainNetSortThree;

    /**
     * @textarea
     */
    private java.lang.String mainSecurityDealRequire;

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
     * @return
     * @hibernate.property value="2000"
     * @eoms.show
     */
    public java.lang.String getMainSecurityDealRequire() {
        return mainSecurityDealRequire;
    }

    public void setMainSecurityDealRequire(java.lang.String mainSecurityDealRequire) {
        this.mainSecurityDealRequire = mainSecurityDealRequire;
    }

}
