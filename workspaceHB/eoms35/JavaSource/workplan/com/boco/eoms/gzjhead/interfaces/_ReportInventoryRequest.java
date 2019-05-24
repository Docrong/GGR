/**
 * _ReportInventoryRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

public class _ReportInventoryRequest  implements java.io.Serializable {
    private java.lang.String codeA;
    private java.lang.String codeB;
    private int attNum;
    private com.boco.eoms.gzjhead.interfaces.AttachInfoListType attachInfoList;
    private com.boco.eoms.gzjhead.interfaces.NoteType noteReportInventory;

    public _ReportInventoryRequest() {
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

    public com.boco.eoms.gzjhead.interfaces.NoteType getNoteReportInventory() {
        return noteReportInventory;
    }

    public void setNoteReportInventory(com.boco.eoms.gzjhead.interfaces.NoteType noteReportInventory) {
        this.noteReportInventory = noteReportInventory;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof _ReportInventoryRequest)) return false;
        _ReportInventoryRequest other = (_ReportInventoryRequest) obj;
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
            ((this.noteReportInventory==null && other.getNoteReportInventory()==null) || 
             (this.noteReportInventory!=null &&
              this.noteReportInventory.equals(other.getNoteReportInventory())));
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
        if (getNoteReportInventory() != null) {
            _hashCode += getNoteReportInventory().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
