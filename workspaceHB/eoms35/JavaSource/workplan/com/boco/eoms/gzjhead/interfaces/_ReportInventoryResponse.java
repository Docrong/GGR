/**
 * _ReportInventoryResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

public class _ReportInventoryResponse  implements java.io.Serializable {
    private java.lang.String resultReportInventory;

    public _ReportInventoryResponse() {
    }

    public java.lang.String getResultReportInventory() {
        return resultReportInventory;
    }

    public void setResultReportInventory(java.lang.String resultReportInventory) {
        this.resultReportInventory = resultReportInventory;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof _ReportInventoryResponse)) return false;
        _ReportInventoryResponse other = (_ReportInventoryResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultReportInventory==null && other.getResultReportInventory()==null) || 
             (this.resultReportInventory!=null &&
              this.resultReportInventory.equals(other.getResultReportInventory())));
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
        if (getResultReportInventory() != null) {
            _hashCode += getResultReportInventory().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
