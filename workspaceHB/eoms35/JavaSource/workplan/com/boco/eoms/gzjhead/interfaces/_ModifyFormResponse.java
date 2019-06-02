/**
 * _ModifyFormResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

public class _ModifyFormResponse  implements java.io.Serializable {
    private java.lang.String resultModifyForm;

    public _ModifyFormResponse() {
    }

    public java.lang.String getResultModifyForm() {
        return resultModifyForm;
    }

    public void setResultModifyForm(java.lang.String resultModifyForm) {
        this.resultModifyForm = resultModifyForm;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof _ModifyFormResponse)) return false;
        _ModifyFormResponse other = (_ModifyFormResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultModifyForm==null && other.getResultModifyForm()==null) || 
             (this.resultModifyForm!=null &&
              this.resultModifyForm.equals(other.getResultModifyForm())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getResultModifyForm() != null) {
            _hashCode += getResultModifyForm().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
