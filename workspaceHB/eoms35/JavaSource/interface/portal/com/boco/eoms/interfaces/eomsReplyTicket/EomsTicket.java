/**
 * EomsTicket.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.eomsReplyTicket;

public class EomsTicket  implements java.io.Serializable {
    private java.lang.String compContent;

    private java.lang.String compTime;

    private java.lang.String eomsSeq;

    private java.lang.String place;

    private java.lang.String serviceName;

    private java.lang.String ticketSeq;

    private java.lang.String userCity;

    private java.lang.String userPhone;

    private java.lang.String userProv;

    private java.lang.String workerPhone;

    public EomsTicket() {
    }

    public EomsTicket(
           java.lang.String compContent,
           java.lang.String compTime,
           java.lang.String eomsSeq,
           java.lang.String place,
           java.lang.String serviceName,
           java.lang.String ticketSeq,
           java.lang.String userCity,
           java.lang.String userPhone,
           java.lang.String userProv,
           java.lang.String workerPhone) {
           this.compContent = compContent;
           this.compTime = compTime;
           this.eomsSeq = eomsSeq;
           this.place = place;
           this.serviceName = serviceName;
           this.ticketSeq = ticketSeq;
           this.userCity = userCity;
           this.userPhone = userPhone;
           this.userProv = userProv;
           this.workerPhone = workerPhone;
    }


    /**
     * Gets the compContent value for this EomsTicket.
     * 
     * @return compContent
     */
    public java.lang.String getCompContent() {
        return compContent;
    }


    /**
     * Sets the compContent value for this EomsTicket.
     * 
     * @param compContent
     */
    public void setCompContent(java.lang.String compContent) {
        this.compContent = compContent;
    }


    /**
     * Gets the compTime value for this EomsTicket.
     * 
     * @return compTime
     */
    public java.lang.String getCompTime() {
        return compTime;
    }


    /**
     * Sets the compTime value for this EomsTicket.
     * 
     * @param compTime
     */
    public void setCompTime(java.lang.String compTime) {
        this.compTime = compTime;
    }


    /**
     * Gets the eomsSeq value for this EomsTicket.
     * 
     * @return eomsSeq
     */
    public java.lang.String getEomsSeq() {
        return eomsSeq;
    }


    /**
     * Sets the eomsSeq value for this EomsTicket.
     * 
     * @param eomsSeq
     */
    public void setEomsSeq(java.lang.String eomsSeq) {
        this.eomsSeq = eomsSeq;
    }


    /**
     * Gets the place value for this EomsTicket.
     * 
     * @return place
     */
    public java.lang.String getPlace() {
        return place;
    }


    /**
     * Sets the place value for this EomsTicket.
     * 
     * @param place
     */
    public void setPlace(java.lang.String place) {
        this.place = place;
    }


    /**
     * Gets the serviceName value for this EomsTicket.
     * 
     * @return serviceName
     */
    public java.lang.String getServiceName() {
        return serviceName;
    }


    /**
     * Sets the serviceName value for this EomsTicket.
     * 
     * @param serviceName
     */
    public void setServiceName(java.lang.String serviceName) {
        this.serviceName = serviceName;
    }


    /**
     * Gets the ticketSeq value for this EomsTicket.
     * 
     * @return ticketSeq
     */
    public java.lang.String getTicketSeq() {
        return ticketSeq;
    }


    /**
     * Sets the ticketSeq value for this EomsTicket.
     * 
     * @param ticketSeq
     */
    public void setTicketSeq(java.lang.String ticketSeq) {
        this.ticketSeq = ticketSeq;
    }


    /**
     * Gets the userCity value for this EomsTicket.
     * 
     * @return userCity
     */
    public java.lang.String getUserCity() {
        return userCity;
    }


    /**
     * Sets the userCity value for this EomsTicket.
     * 
     * @param userCity
     */
    public void setUserCity(java.lang.String userCity) {
        this.userCity = userCity;
    }


    /**
     * Gets the userPhone value for this EomsTicket.
     * 
     * @return userPhone
     */
    public java.lang.String getUserPhone() {
        return userPhone;
    }


    /**
     * Sets the userPhone value for this EomsTicket.
     * 
     * @param userPhone
     */
    public void setUserPhone(java.lang.String userPhone) {
        this.userPhone = userPhone;
    }


    /**
     * Gets the userProv value for this EomsTicket.
     * 
     * @return userProv
     */
    public java.lang.String getUserProv() {
        return userProv;
    }


    /**
     * Sets the userProv value for this EomsTicket.
     * 
     * @param userProv
     */
    public void setUserProv(java.lang.String userProv) {
        this.userProv = userProv;
    }


    /**
     * Gets the workerPhone value for this EomsTicket.
     * 
     * @return workerPhone
     */
    public java.lang.String getWorkerPhone() {
        return workerPhone;
    }


    /**
     * Sets the workerPhone value for this EomsTicket.
     * 
     * @param workerPhone
     */
    public void setWorkerPhone(java.lang.String workerPhone) {
        this.workerPhone = workerPhone;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EomsTicket)) return false;
        EomsTicket other = (EomsTicket) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.compContent==null && other.getCompContent()==null) || 
             (this.compContent!=null &&
              this.compContent.equals(other.getCompContent()))) &&
            ((this.compTime==null && other.getCompTime()==null) || 
             (this.compTime!=null &&
              this.compTime.equals(other.getCompTime()))) &&
            ((this.eomsSeq==null && other.getEomsSeq()==null) || 
             (this.eomsSeq!=null &&
              this.eomsSeq.equals(other.getEomsSeq()))) &&
            ((this.place==null && other.getPlace()==null) || 
             (this.place!=null &&
              this.place.equals(other.getPlace()))) &&
            ((this.serviceName==null && other.getServiceName()==null) || 
             (this.serviceName!=null &&
              this.serviceName.equals(other.getServiceName()))) &&
            ((this.ticketSeq==null && other.getTicketSeq()==null) || 
             (this.ticketSeq!=null &&
              this.ticketSeq.equals(other.getTicketSeq()))) &&
            ((this.userCity==null && other.getUserCity()==null) || 
             (this.userCity!=null &&
              this.userCity.equals(other.getUserCity()))) &&
            ((this.userPhone==null && other.getUserPhone()==null) || 
             (this.userPhone!=null &&
              this.userPhone.equals(other.getUserPhone()))) &&
            ((this.userProv==null && other.getUserProv()==null) || 
             (this.userProv!=null &&
              this.userProv.equals(other.getUserProv()))) &&
            ((this.workerPhone==null && other.getWorkerPhone()==null) || 
             (this.workerPhone!=null &&
              this.workerPhone.equals(other.getWorkerPhone())));
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
        if (getCompContent() != null) {
            _hashCode += getCompContent().hashCode();
        }
        if (getCompTime() != null) {
            _hashCode += getCompTime().hashCode();
        }
        if (getEomsSeq() != null) {
            _hashCode += getEomsSeq().hashCode();
        }
        if (getPlace() != null) {
            _hashCode += getPlace().hashCode();
        }
        if (getServiceName() != null) {
            _hashCode += getServiceName().hashCode();
        }
        if (getTicketSeq() != null) {
            _hashCode += getTicketSeq().hashCode();
        }
        if (getUserCity() != null) {
            _hashCode += getUserCity().hashCode();
        }
        if (getUserPhone() != null) {
            _hashCode += getUserPhone().hashCode();
        }
        if (getUserProv() != null) {
            _hashCode += getUserProv().hashCode();
        }
        if (getWorkerPhone() != null) {
            _hashCode += getWorkerPhone().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EomsTicket.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.jcss.hy.wri.com", "EomsTicket"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("compContent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.jcss.hy.wri.com", "compContent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("compTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.jcss.hy.wri.com", "compTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eomsSeq");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.jcss.hy.wri.com", "eomsSeq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("place");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.jcss.hy.wri.com", "place"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.jcss.hy.wri.com", "serviceName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ticketSeq");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.jcss.hy.wri.com", "ticketSeq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userCity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.jcss.hy.wri.com", "userCity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userPhone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.jcss.hy.wri.com", "userPhone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userProv");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.jcss.hy.wri.com", "userProv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workerPhone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://vo.jcss.hy.wri.com", "workerPhone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
