
package com.boco.eoms.sheet.accountpopedomapply.model;

import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.BaseSubMain;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="AccountPopedomApplyMain.java.html"> <i>View Source </i> </a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="AccountPopedomApplymain"
 */
public class AccountPopedomApplyMain extends BaseMain {

    /**
     * @dicttype
     */
    private java.lang.String netType1;

    /**
     * @dicttype
     */
    private java.lang.String netType2;

    /**
     * @dicttype
     */
    private java.lang.String netType3;

    /**
     *
     */
    private java.util.Date becomeTime;

    /**
     *
     */
    private java.util.Date abateTime;

    /**
     * @texttype
     */
    private java.lang.String system;

    /**
     * @textarea
     */
    private java.lang.String applyExplain;

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getNetType1() {
        return netType1;
    }

    public void setNetType1(java.lang.String netType1) {
        this.netType1 = netType1;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getNetType2() {
        return netType2;
    }

    public void setNetType2(java.lang.String netType2) {
        this.netType2 = netType2;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.lang.String getNetType3() {
        return netType3;
    }

    public void setNetType3(java.lang.String netType3) {
        this.netType3 = netType3;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.util.Date getBecomeTime() {
        return becomeTime;
    }

    public void setBecomeTime(java.util.Date becomeTime) {
        this.becomeTime = becomeTime;
    }

    /**
     * @return
     * @hibernate.property value="50"
     * @eoms.show
     */
    public java.util.Date getAbateTime() {
        return abateTime;
    }

    public void setAbateTime(java.util.Date abateTime) {
        this.abateTime = abateTime;
    }

    /**
     * @return
     * @hibernate.property value="100"
     * @eoms.show
     */
    public java.lang.String getSystem() {
        return system;
    }

    public void setSystem(java.lang.String system) {
        this.system = system;
    }

    /**
     * @return
     * @hibernate.property value="2000"
     * @eoms.show
     */
    public java.lang.String getApplyExplain() {
        return applyExplain;
    }

    public void setApplyExplain(java.lang.String applyExplain) {
        this.applyExplain = applyExplain;
    }

}
