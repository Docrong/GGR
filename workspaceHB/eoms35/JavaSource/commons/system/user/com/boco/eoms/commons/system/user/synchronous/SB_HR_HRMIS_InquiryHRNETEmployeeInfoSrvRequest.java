/**
 * SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.commons.system.user.synchronous;

public class SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest  implements java.io.Serializable {
    private com.boco.eoms.commons.system.user.synchronous.MsgHeader msgHeader;

    private java.lang.String POS_NAME;

    private java.lang.String ATTRIBUTE1;

    private java.lang.String ATTRIBUTE2;

    private java.lang.String ATTRIBUTE3;

    private java.lang.String ATTRIBUTE4;

    private java.lang.String ATTRIBUTE5;

    public SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest() {
    }

    public SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest(
           com.boco.eoms.commons.system.user.synchronous.MsgHeader msgHeader,
           java.lang.String POS_NAME,
           java.lang.String ATTRIBUTE1,
           java.lang.String ATTRIBUTE2,
           java.lang.String ATTRIBUTE3,
           java.lang.String ATTRIBUTE4,
           java.lang.String ATTRIBUTE5) {
           this.msgHeader = msgHeader;
           this.POS_NAME = POS_NAME;
           this.ATTRIBUTE1 = ATTRIBUTE1;
           this.ATTRIBUTE2 = ATTRIBUTE2;
           this.ATTRIBUTE3 = ATTRIBUTE3;
           this.ATTRIBUTE4 = ATTRIBUTE4;
           this.ATTRIBUTE5 = ATTRIBUTE5;
    }


    /**
     * Gets the msgHeader value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.
     * 
     * @return msgHeader
     */
    public com.boco.eoms.commons.system.user.synchronous.MsgHeader getMsgHeader() {
        return msgHeader;
    }


    /**
     * Sets the msgHeader value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.
     * 
     * @param msgHeader
     */
    public void setMsgHeader(com.boco.eoms.commons.system.user.synchronous.MsgHeader msgHeader) {
        this.msgHeader = msgHeader;
    }


    /**
     * Gets the POS_NAME value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.
     * 
     * @return POS_NAME
     */
    public java.lang.String getPOS_NAME() {
        return POS_NAME;
    }


    /**
     * Sets the POS_NAME value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.
     * 
     * @param POS_NAME
     */
    public void setPOS_NAME(java.lang.String POS_NAME) {
        this.POS_NAME = POS_NAME;
    }


    /**
     * Gets the ATTRIBUTE1 value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.
     * 
     * @return ATTRIBUTE1
     */
    public java.lang.String getATTRIBUTE1() {
        return ATTRIBUTE1;
    }


    /**
     * Sets the ATTRIBUTE1 value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.
     * 
     * @param ATTRIBUTE1
     */
    public void setATTRIBUTE1(java.lang.String ATTRIBUTE1) {
        this.ATTRIBUTE1 = ATTRIBUTE1;
    }


    /**
     * Gets the ATTRIBUTE2 value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.
     * 
     * @return ATTRIBUTE2
     */
    public java.lang.String getATTRIBUTE2() {
        return ATTRIBUTE2;
    }


    /**
     * Sets the ATTRIBUTE2 value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.
     * 
     * @param ATTRIBUTE2
     */
    public void setATTRIBUTE2(java.lang.String ATTRIBUTE2) {
        this.ATTRIBUTE2 = ATTRIBUTE2;
    }


    /**
     * Gets the ATTRIBUTE3 value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.
     * 
     * @return ATTRIBUTE3
     */
    public java.lang.String getATTRIBUTE3() {
        return ATTRIBUTE3;
    }


    /**
     * Sets the ATTRIBUTE3 value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.
     * 
     * @param ATTRIBUTE3
     */
    public void setATTRIBUTE3(java.lang.String ATTRIBUTE3) {
        this.ATTRIBUTE3 = ATTRIBUTE3;
    }


    /**
     * Gets the ATTRIBUTE4 value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.
     * 
     * @return ATTRIBUTE4
     */
    public java.lang.String getATTRIBUTE4() {
        return ATTRIBUTE4;
    }


    /**
     * Sets the ATTRIBUTE4 value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.
     * 
     * @param ATTRIBUTE4
     */
    public void setATTRIBUTE4(java.lang.String ATTRIBUTE4) {
        this.ATTRIBUTE4 = ATTRIBUTE4;
    }


    /**
     * Gets the ATTRIBUTE5 value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.
     * 
     * @return ATTRIBUTE5
     */
    public java.lang.String getATTRIBUTE5() {
        return ATTRIBUTE5;
    }


    /**
     * Sets the ATTRIBUTE5 value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.
     * 
     * @param ATTRIBUTE5
     */
    public void setATTRIBUTE5(java.lang.String ATTRIBUTE5) {
        this.ATTRIBUTE5 = ATTRIBUTE5;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest)) return false;
        SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest other = (SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.msgHeader==null && other.getMsgHeader()==null) || 
             (this.msgHeader!=null &&
              this.msgHeader.equals(other.getMsgHeader()))) &&
            ((this.POS_NAME==null && other.getPOS_NAME()==null) || 
             (this.POS_NAME!=null &&
              this.POS_NAME.equals(other.getPOS_NAME()))) &&
            ((this.ATTRIBUTE1==null && other.getATTRIBUTE1()==null) || 
             (this.ATTRIBUTE1!=null &&
              this.ATTRIBUTE1.equals(other.getATTRIBUTE1()))) &&
            ((this.ATTRIBUTE2==null && other.getATTRIBUTE2()==null) || 
             (this.ATTRIBUTE2!=null &&
              this.ATTRIBUTE2.equals(other.getATTRIBUTE2()))) &&
            ((this.ATTRIBUTE3==null && other.getATTRIBUTE3()==null) || 
             (this.ATTRIBUTE3!=null &&
              this.ATTRIBUTE3.equals(other.getATTRIBUTE3()))) &&
            ((this.ATTRIBUTE4==null && other.getATTRIBUTE4()==null) || 
             (this.ATTRIBUTE4!=null &&
              this.ATTRIBUTE4.equals(other.getATTRIBUTE4()))) &&
            ((this.ATTRIBUTE5==null && other.getATTRIBUTE5()==null) || 
             (this.ATTRIBUTE5!=null &&
              this.ATTRIBUTE5.equals(other.getATTRIBUTE5())));
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
        if (getMsgHeader() != null) {
            _hashCode += getMsgHeader().hashCode();
        }
        if (getPOS_NAME() != null) {
            _hashCode += getPOS_NAME().hashCode();
        }
        if (getATTRIBUTE1() != null) {
            _hashCode += getATTRIBUTE1().hashCode();
        }
        if (getATTRIBUTE2() != null) {
            _hashCode += getATTRIBUTE2().hashCode();
        }
        if (getATTRIBUTE3() != null) {
            _hashCode += getATTRIBUTE3().hashCode();
        }
        if (getATTRIBUTE4() != null) {
            _hashCode += getATTRIBUTE4().hashCode();
        }
        if (getATTRIBUTE5() != null) {
            _hashCode += getATTRIBUTE5().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "MsgHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.vispractice.com/MsgHeader", "MsgHeader"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POS_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "POS_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "ATTRIBUTE1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "ATTRIBUTE2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "ATTRIBUTE3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "ATTRIBUTE4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ATTRIBUTE5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "ATTRIBUTE5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
