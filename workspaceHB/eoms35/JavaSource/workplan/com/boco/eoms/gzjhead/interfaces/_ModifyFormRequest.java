/**
 * _ModifyFormRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

public class _ModifyFormRequest  implements java.io.Serializable {
    private java.lang.String codeA;
    private java.lang.String codeB;
    private int attNum;
    private com.boco.eoms.gzjhead.interfaces.AttachInfoListType attachInfoList;
    private com.boco.eoms.gzjhead.interfaces.NeListType relatedNEList;
    private com.boco.eoms.gzjhead.interfaces.ModifyTypeType modifyType;
    private com.boco.eoms.gzjhead.interfaces.NoteType noteModifyForm;

    public _ModifyFormRequest() {
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

    public com.boco.eoms.gzjhead.interfaces.NeListType getRelatedNEList() {
        return relatedNEList;
    }

    public void setRelatedNEList(com.boco.eoms.gzjhead.interfaces.NeListType relatedNEList) {
        this.relatedNEList = relatedNEList;
    }

    public com.boco.eoms.gzjhead.interfaces.ModifyTypeType getModifyType() {
        return modifyType;
    }

    public void setModifyType(com.boco.eoms.gzjhead.interfaces.ModifyTypeType modifyType) {
        this.modifyType = modifyType;
    }

    public com.boco.eoms.gzjhead.interfaces.NoteType getNoteModifyForm() {
        return noteModifyForm;
    }

    public void setNoteModifyForm(com.boco.eoms.gzjhead.interfaces.NoteType noteModifyForm) {
        this.noteModifyForm = noteModifyForm;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof _ModifyFormRequest)) return false;
        _ModifyFormRequest other = (_ModifyFormRequest) obj;
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
            ((this.relatedNEList==null && other.getRelatedNEList()==null) || 
             (this.relatedNEList!=null &&
              this.relatedNEList.equals(other.getRelatedNEList()))) &&
            ((this.modifyType==null && other.getModifyType()==null) || 
             (this.modifyType!=null &&
              this.modifyType.equals(other.getModifyType()))) &&
            ((this.noteModifyForm==null && other.getNoteModifyForm()==null) || 
             (this.noteModifyForm!=null &&
              this.noteModifyForm.equals(other.getNoteModifyForm())));
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
        if (getRelatedNEList() != null) {
            _hashCode += getRelatedNEList().hashCode();
        }
        if (getModifyType() != null) {
            _hashCode += getModifyType().hashCode();
        }
        if (getNoteModifyForm() != null) {
            _hashCode += getNoteModifyForm().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
