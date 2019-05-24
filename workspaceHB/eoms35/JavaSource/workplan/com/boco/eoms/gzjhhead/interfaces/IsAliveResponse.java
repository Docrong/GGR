/**
 * IsAliveResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhhead.interfaces;

public class IsAliveResponse  implements java.io.Serializable {
    private java.lang.String resultIsAlive;

    public IsAliveResponse() {
    }

    public java.lang.String getResultIsAlive() {
        return resultIsAlive;
    }

    public void setResultIsAlive(java.lang.String resultIsAlive) {
        this.resultIsAlive = resultIsAlive;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IsAliveResponse)) return false;
        IsAliveResponse other = (IsAliveResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultIsAlive==null && other.getResultIsAlive()==null) || 
             (this.resultIsAlive!=null &&
              this.resultIsAlive.equals(other.getResultIsAlive())));
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
        if (getResultIsAlive() != null) {
            _hashCode += getResultIsAlive().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
