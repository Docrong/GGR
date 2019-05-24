/**
 * AttachInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

public class AttachInfoType  implements java.io.Serializable {
    private java.lang.String attachName;
    private int attachLength;
    private java.lang.String attachURL;

    public AttachInfoType() {
    }

    public java.lang.String getAttachName() {
        return attachName;
    }

    public void setAttachName(java.lang.String attachName) {
        this.attachName = attachName;
    }

    public int getAttachLength() {
        return attachLength;
    }

    public void setAttachLength(int attachLength) {
        this.attachLength = attachLength;
    }

    public java.lang.String getAttachURL() {
        return attachURL;
    }

    public void setAttachURL(java.lang.String attachURL) {
        this.attachURL = attachURL;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AttachInfoType)) return false;
        AttachInfoType other = (AttachInfoType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.attachName==null && other.getAttachName()==null) || 
             (this.attachName!=null &&
              this.attachName.equals(other.getAttachName()))) &&
            this.attachLength == other.getAttachLength() &&
            ((this.attachURL==null && other.getAttachURL()==null) || 
             (this.attachURL!=null &&
              this.attachURL.equals(other.getAttachURL())));
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
        if (getAttachName() != null) {
            _hashCode += getAttachName().hashCode();
        }
        _hashCode += getAttachLength();
        if (getAttachURL() != null) {
            _hashCode += getAttachURL().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
