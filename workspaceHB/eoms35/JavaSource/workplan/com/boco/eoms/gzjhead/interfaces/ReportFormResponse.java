/**
 * ReportFormResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

public class ReportFormResponse  implements java.io.Serializable {
    private java.lang.String resultReportForm;

    public ReportFormResponse() {
    }

    public java.lang.String getResultReportForm() {
        return resultReportForm;
    }

    public void setResultReportForm(java.lang.String resultReportForm) {
        this.resultReportForm = resultReportForm;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportFormResponse)) return false;
        ReportFormResponse other = (ReportFormResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultReportForm==null && other.getResultReportForm()==null) || 
             (this.resultReportForm!=null &&
              this.resultReportForm.equals(other.getResultReportForm())));
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
        if (getResultReportForm() != null) {
            _hashCode += getResultReportForm().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
