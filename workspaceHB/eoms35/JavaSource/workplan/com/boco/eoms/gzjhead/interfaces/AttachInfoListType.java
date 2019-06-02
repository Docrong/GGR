/**
 * AttachInfoListType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

public class AttachInfoListType  implements java.io.Serializable {
    private com.boco.eoms.gzjhead.interfaces.AttachInfoType[] attachInfo;

    public AttachInfoListType() {
    }

    public com.boco.eoms.gzjhead.interfaces.AttachInfoType[] getAttachInfo() {
        return attachInfo;
    }

    public void setAttachInfo(com.boco.eoms.gzjhead.interfaces.AttachInfoType[] attachInfo) {
        this.attachInfo = attachInfo;
    }

    public com.boco.eoms.gzjhead.interfaces.AttachInfoType getAttachInfo(int i) {
        return attachInfo[i];
    }

    public void setAttachInfo(int i, com.boco.eoms.gzjhead.interfaces.AttachInfoType value) {
        this.attachInfo[i] = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AttachInfoListType)) return false;
        AttachInfoListType other = (AttachInfoListType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.attachInfo==null && other.getAttachInfo()==null) || 
             (this.attachInfo!=null &&
              java.util.Arrays.equals(this.attachInfo, other.getAttachInfo())));
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
        if (getAttachInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttachInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttachInfo(), i);
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
