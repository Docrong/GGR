/**
 * SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.commons.system.user.synchronous;

public class SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse  implements java.io.Serializable {
    private java.lang.String SERVICE_FLAG;

    private java.lang.String SERVICE_MESSAGE;

    private java.lang.String INSTANCE_ID;

    private java.math.BigDecimal TOTAL_RECORD;

    private java.math.BigDecimal TOTAL_PAGE;

    private java.math.BigDecimal PAGE_SIZE;

    private java.math.BigDecimal CURRENT_PAGE;

    private com.boco.eoms.commons.system.user.synchronous.OutputItem[] outputCollection;

    public SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse() {
    }

    public SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse(
           java.lang.String SERVICE_FLAG,
           java.lang.String SERVICE_MESSAGE,
           java.lang.String INSTANCE_ID,
           java.math.BigDecimal TOTAL_RECORD,
           java.math.BigDecimal TOTAL_PAGE,
           java.math.BigDecimal PAGE_SIZE,
           java.math.BigDecimal CURRENT_PAGE,
           com.boco.eoms.commons.system.user.synchronous.OutputItem[] outputCollection) {
           this.SERVICE_FLAG = SERVICE_FLAG;
           this.SERVICE_MESSAGE = SERVICE_MESSAGE;
           this.INSTANCE_ID = INSTANCE_ID;
           this.TOTAL_RECORD = TOTAL_RECORD;
           this.TOTAL_PAGE = TOTAL_PAGE;
           this.PAGE_SIZE = PAGE_SIZE;
           this.CURRENT_PAGE = CURRENT_PAGE;
           this.outputCollection = outputCollection;
    }


    /**
     * Gets the SERVICE_FLAG value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @return SERVICE_FLAG
     */
    public java.lang.String getSERVICE_FLAG() {
        return SERVICE_FLAG;
    }


    /**
     * Sets the SERVICE_FLAG value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @param SERVICE_FLAG
     */
    public void setSERVICE_FLAG(java.lang.String SERVICE_FLAG) {
        this.SERVICE_FLAG = SERVICE_FLAG;
    }


    /**
     * Gets the SERVICE_MESSAGE value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @return SERVICE_MESSAGE
     */
    public java.lang.String getSERVICE_MESSAGE() {
        return SERVICE_MESSAGE;
    }


    /**
     * Sets the SERVICE_MESSAGE value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @param SERVICE_MESSAGE
     */
    public void setSERVICE_MESSAGE(java.lang.String SERVICE_MESSAGE) {
        this.SERVICE_MESSAGE = SERVICE_MESSAGE;
    }


    /**
     * Gets the INSTANCE_ID value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @return INSTANCE_ID
     */
    public java.lang.String getINSTANCE_ID() {
        return INSTANCE_ID;
    }


    /**
     * Sets the INSTANCE_ID value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @param INSTANCE_ID
     */
    public void setINSTANCE_ID(java.lang.String INSTANCE_ID) {
        this.INSTANCE_ID = INSTANCE_ID;
    }


    /**
     * Gets the TOTAL_RECORD value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @return TOTAL_RECORD
     */
    public java.math.BigDecimal getTOTAL_RECORD() {
        return TOTAL_RECORD;
    }


    /**
     * Sets the TOTAL_RECORD value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @param TOTAL_RECORD
     */
    public void setTOTAL_RECORD(java.math.BigDecimal TOTAL_RECORD) {
        this.TOTAL_RECORD = TOTAL_RECORD;
    }


    /**
     * Gets the TOTAL_PAGE value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @return TOTAL_PAGE
     */
    public java.math.BigDecimal getTOTAL_PAGE() {
        return TOTAL_PAGE;
    }


    /**
     * Sets the TOTAL_PAGE value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @param TOTAL_PAGE
     */
    public void setTOTAL_PAGE(java.math.BigDecimal TOTAL_PAGE) {
        this.TOTAL_PAGE = TOTAL_PAGE;
    }


    /**
     * Gets the PAGE_SIZE value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @return PAGE_SIZE
     */
    public java.math.BigDecimal getPAGE_SIZE() {
        return PAGE_SIZE;
    }


    /**
     * Sets the PAGE_SIZE value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @param PAGE_SIZE
     */
    public void setPAGE_SIZE(java.math.BigDecimal PAGE_SIZE) {
        this.PAGE_SIZE = PAGE_SIZE;
    }


    /**
     * Gets the CURRENT_PAGE value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @return CURRENT_PAGE
     */
    public java.math.BigDecimal getCURRENT_PAGE() {
        return CURRENT_PAGE;
    }


    /**
     * Sets the CURRENT_PAGE value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @param CURRENT_PAGE
     */
    public void setCURRENT_PAGE(java.math.BigDecimal CURRENT_PAGE) {
        this.CURRENT_PAGE = CURRENT_PAGE;
    }


    /**
     * Gets the outputCollection value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @return outputCollection
     */
    public com.boco.eoms.commons.system.user.synchronous.OutputItem[] getOutputCollection() {
        return outputCollection;
    }


    /**
     * Sets the outputCollection value for this SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.
     * 
     * @param outputCollection
     */
    public void setOutputCollection(com.boco.eoms.commons.system.user.synchronous.OutputItem[] outputCollection) {
        this.outputCollection = outputCollection;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse)) return false;
        SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse other = (SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SERVICE_FLAG==null && other.getSERVICE_FLAG()==null) || 
             (this.SERVICE_FLAG!=null &&
              this.SERVICE_FLAG.equals(other.getSERVICE_FLAG()))) &&
            ((this.SERVICE_MESSAGE==null && other.getSERVICE_MESSAGE()==null) || 
             (this.SERVICE_MESSAGE!=null &&
              this.SERVICE_MESSAGE.equals(other.getSERVICE_MESSAGE()))) &&
            ((this.INSTANCE_ID==null && other.getINSTANCE_ID()==null) || 
             (this.INSTANCE_ID!=null &&
              this.INSTANCE_ID.equals(other.getINSTANCE_ID()))) &&
            ((this.TOTAL_RECORD==null && other.getTOTAL_RECORD()==null) || 
             (this.TOTAL_RECORD!=null &&
              this.TOTAL_RECORD.equals(other.getTOTAL_RECORD()))) &&
            ((this.TOTAL_PAGE==null && other.getTOTAL_PAGE()==null) || 
             (this.TOTAL_PAGE!=null &&
              this.TOTAL_PAGE.equals(other.getTOTAL_PAGE()))) &&
            ((this.PAGE_SIZE==null && other.getPAGE_SIZE()==null) || 
             (this.PAGE_SIZE!=null &&
              this.PAGE_SIZE.equals(other.getPAGE_SIZE()))) &&
            ((this.CURRENT_PAGE==null && other.getCURRENT_PAGE()==null) || 
             (this.CURRENT_PAGE!=null &&
              this.CURRENT_PAGE.equals(other.getCURRENT_PAGE()))) &&
            ((this.outputCollection==null && other.getOutputCollection()==null) || 
             (this.outputCollection!=null &&
              java.util.Arrays.equals(this.outputCollection, other.getOutputCollection())));
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
        if (getSERVICE_FLAG() != null) {
            _hashCode += getSERVICE_FLAG().hashCode();
        }
        if (getSERVICE_MESSAGE() != null) {
            _hashCode += getSERVICE_MESSAGE().hashCode();
        }
        if (getINSTANCE_ID() != null) {
            _hashCode += getINSTANCE_ID().hashCode();
        }
        if (getTOTAL_RECORD() != null) {
            _hashCode += getTOTAL_RECORD().hashCode();
        }
        if (getTOTAL_PAGE() != null) {
            _hashCode += getTOTAL_PAGE().hashCode();
        }
        if (getPAGE_SIZE() != null) {
            _hashCode += getPAGE_SIZE().hashCode();
        }
        if (getCURRENT_PAGE() != null) {
            _hashCode += getCURRENT_PAGE().hashCode();
        }
        if (getOutputCollection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOutputCollection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOutputCollection(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SERVICE_FLAG");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "SERVICE_FLAG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SERVICE_MESSAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "SERVICE_MESSAGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INSTANCE_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "INSTANCE_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TOTAL_RECORD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "TOTAL_RECORD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TOTAL_PAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "TOTAL_PAGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAGE_SIZE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "PAGE_SIZE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CURRENT_PAGE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "CURRENT_PAGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outputCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "OutputCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "OutputItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "OutputItem"));
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
