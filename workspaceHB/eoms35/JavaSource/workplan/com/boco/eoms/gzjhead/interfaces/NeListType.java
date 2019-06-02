/**
 * NeListType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

public class NeListType  implements java.io.Serializable {
    private com.boco.eoms.gzjhead.interfaces.NeNumberType[] neNumber;

    public NeListType() {
    }

    public com.boco.eoms.gzjhead.interfaces.NeNumberType[] getNeNumber() {
        return neNumber;
    }

    public void setNeNumber(com.boco.eoms.gzjhead.interfaces.NeNumberType[] neNumber) {
        this.neNumber = neNumber;
    }

    public com.boco.eoms.gzjhead.interfaces.NeNumberType getNeNumber(int i) {
        return neNumber[i];
    }

    public void setNeNumber(int i, com.boco.eoms.gzjhead.interfaces.NeNumberType value) {
        this.neNumber[i] = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NeListType)) return false;
        NeListType other = (NeListType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.neNumber==null && other.getNeNumber()==null) || 
             (this.neNumber!=null &&
              java.util.Arrays.equals(this.neNumber, other.getNeNumber())));
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
        if (getNeNumber() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNeNumber());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNeNumber(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
