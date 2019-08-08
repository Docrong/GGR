package com.boco.eoms.extra.supplierkpi.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="TawSupplierkpiInfo.java.html"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_supplierkpi_info"
 */
public class TawSupplierkpiInfo extends BaseObject {

    private String id;
    private String supplierName;
    private String supplierContact;
    private String supplierLinkman;

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
     * @hibernate.property length="32" not-null="false" unique="true"
     */
    public String getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }

    /**
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="true"
     */
    public String getSupplierLinkman() {
        return supplierLinkman;
    }

    public void setSupplierLinkman(String supplierLinkman) {
        this.supplierLinkman = supplierLinkman;
    }

    /**
     * @struts.validator type="required"
     * @hibernate.property length="32" not-null="false" unique="true"
     */
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public boolean equals(Object o) {
        // TODO 自动生成方法存根
        return false;
    }

    public int hashCode() {
        // TODO 自动生成方法存根
        return 0;
    }

    public String toString() {
        // TODO 自动生成方法存根
        return null;
    }
}
