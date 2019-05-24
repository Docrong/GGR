/**
 * _IsAliveRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

public class _IsAliveRequest  implements java.io.Serializable {
    private java.lang.String codeA;
    private java.lang.String codeB;

    public _IsAliveRequest() {
    }

    public java.lang.String getCodeA() {
        return codeA;
    }

    public void setCodeA(java.lang.String codeA) {
        this.codeA = codeA;
    }

    public java.lang.String getCodeB() {
        return codeB;
    }

    public void setCodeB(java.lang.String codeB) {
        this.codeB = codeB;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof _IsAliveRequest)) return false;
        _IsAliveRequest other = (_IsAliveRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codeA==null && other.getCodeA()==null) || 
             (this.codeA!=null &&
              this.codeA.equals(other.getCodeA()))) &&
            ((this.codeB==null && other.getCodeB()==null) || 
             (this.codeB!=null &&
              this.codeB.equals(other.getCodeB())));
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
        if (getCodeA() != null) {
            _hashCode += getCodeA().hashCode();
        }
        if (getCodeB() != null) {
            _hashCode += getCodeB().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
