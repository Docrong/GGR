package com.boco.eoms.extra.supplierkpi.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="User.java.html"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_supplierkpi_relation"
 */
public class TawSupplierkpiRelation extends BaseObject {

    private String id;
    private String kpiItemId;
    private String assessInstanceId;

    /**
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="false"
     */
    public String getAssessInstanceId() {
        return assessInstanceId;
    }

    public void setAssessInstanceId(String assessInstanceId) {
        this.assessInstanceId = assessInstanceId;
    }

    /**
     * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="false"
     */
    public String getKpiItemId() {
        return kpiItemId;
    }

    public void setKpiItemId(String kpiItemId) {
        this.kpiItemId = kpiItemId;
    }

    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
}
