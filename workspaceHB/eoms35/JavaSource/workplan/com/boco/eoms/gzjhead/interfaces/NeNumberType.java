/**
 * NeNumberType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

public class NeNumberType  implements java.io.Serializable, org.apache.axis.encoding.SimpleType {
    private java.lang.String value;

    public NeNumberType() {
    }

    // Simple Types must have a String constructor
    public NeNumberType(java.lang.String value) {
        this.value = new java.lang.String(value);
    }

    // Simple Types must have a toString for serializing the value
    public java.lang.String toString() {
        return value == null ? null : value.toString();
    }

    public java.lang.String getValue() {
        return value;
    }

    public void setValue(java.lang.String value) {
        this.value = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NeNumberType)) return false;
        NeNumberType other = (NeNumberType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue())));
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
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
