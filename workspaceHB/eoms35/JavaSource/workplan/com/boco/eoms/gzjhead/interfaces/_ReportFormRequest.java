/**
 * _ReportFormRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

public class _ReportFormRequest  implements java.io.Serializable {
    private java.lang.String codeA;
    private java.lang.String codeB;
    private int attNum;
    private com.boco.eoms.gzjhead.interfaces.AttachInfoListType attachInfoList;
    private com.boco.eoms.gzjhead.interfaces.ReportTypeType reportType;
    private com.boco.eoms.gzjhead.interfaces.NoteType noteReportForm;

    public _ReportFormRequest() {
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

    public int getAttNum() {
        return attNum;
    }

    public void setAttNum(int attNum) {
        this.attNum = attNum;
    }

    public com.boco.eoms.gzjhead.interfaces.AttachInfoListType getAttachInfoList() {
        return attachInfoList;
    }

    public void setAttachInfoList(com.boco.eoms.gzjhead.interfaces.AttachInfoListType attachInfoList) {
        this.attachInfoList = attachInfoList;
    }

    public com.boco.eoms.gzjhead.interfaces.ReportTypeType getReportType() {
        return reportType;
    }

    public void setReportType(com.boco.eoms.gzjhead.interfaces.ReportTypeType reportType) {
        this.reportType = reportType;
    }

    public com.boco.eoms.gzjhead.interfaces.NoteType getNoteReportForm() {
        return noteReportForm;
    }

    public void setNoteReportForm(com.boco.eoms.gzjhead.interfaces.NoteType noteReportForm) {
        this.noteReportForm = noteReportForm;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof _ReportFormRequest)) return false;
        _ReportFormRequest other = (_ReportFormRequest) obj;
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
              this.codeB.equals(other.getCodeB()))) &&
            this.attNum == other.getAttNum() &&
            ((this.attachInfoList==null && other.getAttachInfoList()==null) || 
             (this.attachInfoList!=null &&
              this.attachInfoList.equals(other.getAttachInfoList()))) &&
            ((this.reportType==null && other.getReportType()==null) || 
             (this.reportType!=null &&
              this.reportType.equals(other.getReportType()))) &&
            ((this.noteReportForm==null && other.getNoteReportForm()==null) || 
             (this.noteReportForm!=null &&
              this.noteReportForm.equals(other.getNoteReportForm())));
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
        _hashCode += getAttNum();
        if (getAttachInfoList() != null) {
            _hashCode += getAttachInfoList().hashCode();
        }
        if (getReportType() != null) {
            _hashCode += getReportType().hashCode();
        }
        if (getNoteReportForm() != null) {
            _hashCode += getNoteReportForm().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
