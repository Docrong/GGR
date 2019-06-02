/**
 * MsgHeader.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.commons.system.user.synchronous;

public class MsgHeader  implements java.io.Serializable {
    private java.lang.String SOURCE_SYSTEM_ID;

    private java.lang.String SOURCE_SYSTEM_NAME;

    private java.lang.String TARGET_SYSTEM_ID;

    private java.lang.String TARGET_SYSTEM_NAME;

    private java.lang.String USER_ID;

    private java.lang.String USER_NAME;

    private java.util.Calendar SUBMIT_DATE;

    private java.math.BigDecimal PAGE_SIZE;

    private java.math.BigDecimal CURRENT_PAGE;

    private java.math.BigDecimal TOTAL_RECORD;

    public MsgHeader() {
    }

    public MsgHeader(
           java.lang.String SOURCE_SYSTEM_ID,
           java.lang.String SOURCE_SYSTEM_NAME,
           java.lang.String TARGET_SYSTEM_ID,
           java.lang.String TARGET_SYSTEM_NAME,
           java.lang.String USER_ID,
           java.lang.String USER_NAME,
           java.util.Calendar SUBMIT_DATE,
           java.math.BigDecimal PAGE_SIZE,
           java.math.BigDecimal CURRENT_PAGE,
           java.math.BigDecimal TOTAL_RECORD) {
           this.SOURCE_SYSTEM_ID = SOURCE_SYSTEM_ID;
           this.SOURCE_SYSTEM_NAME = SOURCE_SYSTEM_NAME;
           this.TARGET_SYSTEM_ID = TARGET_SYSTEM_ID;
           this.TARGET_SYSTEM_NAME = TARGET_SYSTEM_NAME;
           this.USER_ID = USER_ID;
           this.USER_NAME = USER_NAME;
           this.SUBMIT_DATE = SUBMIT_DATE;
           this.PAGE_SIZE = PAGE_SIZE;
           this.CURRENT_PAGE = CURRENT_PAGE;
           this.TOTAL_RECORD = TOTAL_RECORD;
    }


    /**
     * Gets the SOURCE_SYSTEM_ID value for this MsgHeader.
     * 
     * @return SOURCE_SYSTEM_ID
     */
    public java.lang.String getSOURCE_SYSTEM_ID() {
        return SOURCE_SYSTEM_ID;
    }


    /**
     * Sets the SOURCE_SYSTEM_ID value for this MsgHeader.
     * 
     * @param SOURCE_SYSTEM_ID
     */
    public void setSOURCE_SYSTEM_ID(java.lang.String SOURCE_SYSTEM_ID) {
        this.SOURCE_SYSTEM_ID = SOURCE_SYSTEM_ID;
    }


    /**
     * Gets the SOURCE_SYSTEM_NAME value for this MsgHeader.
     * 
     * @return SOURCE_SYSTEM_NAME
     */
    public java.lang.String getSOURCE_SYSTEM_NAME() {
        return SOURCE_SYSTEM_NAME;
    }


    /**
     * Sets the SOURCE_SYSTEM_NAME value for this MsgHeader.
     * 
     * @param SOURCE_SYSTEM_NAME
     */
    public void setSOURCE_SYSTEM_NAME(java.lang.String SOURCE_SYSTEM_NAME) {
        this.SOURCE_SYSTEM_NAME = SOURCE_SYSTEM_NAME;
    }


    /**
     * Gets the TARGET_SYSTEM_ID value for this MsgHeader.
     * 
     * @return TARGET_SYSTEM_ID
     */
    public java.lang.String getTARGET_SYSTEM_ID() {
        return TARGET_SYSTEM_ID;
    }


    /**
     * Sets the TARGET_SYSTEM_ID value for this MsgHeader.
     * 
     * @param TARGET_SYSTEM_ID
     */
    public void setTARGET_SYSTEM_ID(java.lang.String TARGET_SYSTEM_ID) {
        this.TARGET_SYSTEM_ID = TARGET_SYSTEM_ID;
    }


    /**
     * Gets the TARGET_SYSTEM_NAME value for this MsgHeader.
     * 
     * @return TARGET_SYSTEM_NAME
     */
    public java.lang.String getTARGET_SYSTEM_NAME() {
        return TARGET_SYSTEM_NAME;
    }


    /**
     * Sets the TARGET_SYSTEM_NAME value for this MsgHeader.
     * 
     * @param TARGET_SYSTEM_NAME
     */
    public void setTARGET_SYSTEM_NAME(java.lang.String TARGET_SYSTEM_NAME) {
        this.TARGET_SYSTEM_NAME = TARGET_SYSTEM_NAME;
    }


    /**
     * Gets the USER_ID value for this MsgHeader.
     * 
     * @return USER_ID
     */
    public java.lang.String getUSER_ID() {
        return USER_ID;
    }


    /**
     * Sets the USER_ID value for this MsgHeader.
     * 
     * @param USER_ID
     */
    public void setUSER_ID(java.lang.String USER_ID) {
        this.USER_ID = USER_ID;
    }


    /**
     * Gets the USER_NAME value for this MsgHeader.
     * 
     * @return USER_NAME
     */
    public java.lang.String getUSER_NAME() {
        return USER_NAME;
    }


    /**
     * Sets the USER_NAME value for this MsgHeader.
     * 
     * @param USER_NAME
     */
    public void setUSER_NAME(java.lang.String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }


    /**
     * Gets the SUBMIT_DATE value for this MsgHeader.
     * 
     * @return SUBMIT_DATE
     */
    public java.util.Calendar getSUBMIT_DATE() {
        return SUBMIT_DATE;
    }


    /**
     * Sets the SUBMIT_DATE value for this MsgHeader.
     * 
     * @param SUBMIT_DATE
     */
    public void setSUBMIT_DATE(java.util.Calendar SUBMIT_DATE) {
        this.SUBMIT_DATE = SUBMIT_DATE;
    }


    /**
     * Gets the PAGE_SIZE value for this MsgHeader.
     * 
     * @return PAGE_SIZE
     */
    public java.math.BigDecimal getPAGE_SIZE() {
        return PAGE_SIZE;
    }


    /**
     * Sets the PAGE_SIZE value for this MsgHeader.
     * 
     * @param PAGE_SIZE
     */
    public void setPAGE_SIZE(java.math.BigDecimal PAGE_SIZE) {
        this.PAGE_SIZE = PAGE_SIZE;
    }


    /**
     * Gets the CURRENT_PAGE value for this MsgHeader.
     * 
     * @return CURRENT_PAGE
     */
    public java.math.BigDecimal getCURRENT_PAGE() {
        return CURRENT_PAGE;
    }


    /**
     * Sets the CURRENT_PAGE value for this MsgHeader.
     * 
     * @param CURRENT_PAGE
     */
    public void setCURRENT_PAGE(java.math.BigDecimal CURRENT_PAGE) {
        this.CURRENT_PAGE = CURRENT_PAGE;
    }


    /**
     * Gets the TOTAL_RECORD value for this MsgHeader.
     * 
     * @return TOTAL_RECORD
     */
    public java.math.BigDecimal getTOTAL_RECORD() {
        return TOTAL_RECORD;
    }


    /**
     * Sets the TOTAL_RECORD value for this MsgHeader.
     * 
     * @param TOTAL_RECORD
     */
    public void setTOTAL_RECORD(java.math.BigDecimal TOTAL_RECORD) {
        this.TOTAL_RECORD = TOTAL_RECORD;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MsgHeader)) return false;
        MsgHeader other = (MsgHeader) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SOURCE_SYSTEM_ID==null && other.getSOURCE_SYSTEM_ID()==null) || 
             (this.SOURCE_SYSTEM_ID!=null &&
              this.SOURCE_SYSTEM_ID.equals(other.getSOURCE_SYSTEM_ID()))) &&
            ((this.SOURCE_SYSTEM_NAME==null && other.getSOURCE_SYSTEM_NAME()==null) || 
             (this.SOURCE_SYSTEM_NAME!=null &&
              this.SOURCE_SYSTEM_NAME.equals(other.getSOURCE_SYSTEM_NAME()))) &&
            ((this.TARGET_SYSTEM_ID==null && other.getTARGET_SYSTEM_ID()==null) || 
             (this.TARGET_SYSTEM_ID!=null &&
              this.TARGET_SYSTEM_ID.equals(other.getTARGET_SYSTEM_ID()))) &&
            ((this.TARGET_SYSTEM_NAME==null && other.getTARGET_SYSTEM_NAME()==null) || 
             (this.TARGET_SYSTEM_NAME!=null &&
              this.TARGET_SYSTEM_NAME.equals(other.getTARGET_SYSTEM_NAME()))) &&
            ((this.USER_ID==null && other.getUSER_ID()==null) || 
             (this.USER_ID!=null &&
              this.USER_ID.equals(other.getUSER_ID()))) &&
            ((this.USER_NAME==null && other.getUSER_NAME()==null) || 
             (this.USER_NAME!=null &&
              this.USER_NAME.equals(other.getUSER_NAME()))) &&
            ((this.SUBMIT_DATE==null && other.getSUBMIT_DATE()==null) || 
             (this.SUBMIT_DATE!=null &&
              this.SUBMIT_DATE.equals(other.getSUBMIT_DATE()))) &&
            ((this.PAGE_SIZE==null && other.getPAGE_SIZE()==null) || 
             (this.PAGE_SIZE!=null &&
              this.PAGE_SIZE.equals(other.getPAGE_SIZE()))) &&
            ((this.CURRENT_PAGE==null && other.getCURRENT_PAGE()==null) || 
             (this.CURRENT_PAGE!=null &&
              this.CURRENT_PAGE.equals(other.getCURRENT_PAGE()))) &&
            ((this.TOTAL_RECORD==null && other.getTOTAL_RECORD()==null) || 
             (this.TOTAL_RECORD!=null &&
              this.TOTAL_RECORD.equals(other.getTOTAL_RECORD())));
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
        if (getSOURCE_SYSTEM_ID() != null) {
            _hashCode += getSOURCE_SYSTEM_ID().hashCode();
        }
        if (getSOURCE_SYSTEM_NAME() != null) {
            _hashCode += getSOURCE_SYSTEM_NAME().hashCode();
        }
        if (getTARGET_SYSTEM_ID() != null) {
            _hashCode += getTARGET_SYSTEM_ID().hashCode();
        }
        if (getTARGET_SYSTEM_NAME() != null) {
            _hashCode += getTARGET_SYSTEM_NAME().hashCode();
        }
        if (getUSER_ID() != null) {
            _hashCode += getUSER_ID().hashCode();
        }
        if (getUSER_NAME() != null) {
            _hashCode += getUSER_NAME().hashCode();
        }
        if (getSUBMIT_DATE() != null) {
            _hashCode += getSUBMIT_DATE().hashCode();
        }
        if (getPAGE_SIZE() != null) {
            _hashCode += getPAGE_SIZE().hashCode();
        }
        if (getCURRENT_PAGE() != null) {
            _hashCode += getCURRENT_PAGE().hashCode();
        }
        if (getTOTAL_RECORD() != null) {
            _hashCode += getTOTAL_RECORD().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MsgHeader.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.vispractice.com/MsgHeader", "MsgHeader"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SOURCE_SYSTEM_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/MsgHeader", "SOURCE_SYSTEM_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SOURCE_SYSTEM_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/MsgHeader", "SOURCE_SYSTEM_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TARGET_SYSTEM_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/MsgHeader", "TARGET_SYSTEM_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TARGET_SYSTEM_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/MsgHeader", "TARGET_SYSTEM_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USER_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/MsgHeader", "USER_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USER_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/MsgHeader", "USER_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUBMIT_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/MsgHeader", "SUBMIT_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAGE_SIZE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/MsgHeader", "PAGE_SIZE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CURRENT_PAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/MsgHeader", "CURRENT_PAGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TOTAL_RECORD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/MsgHeader", "TOTAL_RECORD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
