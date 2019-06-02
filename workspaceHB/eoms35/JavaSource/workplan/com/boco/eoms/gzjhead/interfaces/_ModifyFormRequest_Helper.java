/**
 * _ModifyFormRequest_Helper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

public class _ModifyFormRequest_Helper {
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(_ModifyFormRequest.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", ">ModifyFormRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "codeA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codeB");
        elemField.setXmlName(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "codeB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "attNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachInfoList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "attachInfoList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "attachInfoListType"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relatedNEList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "relatedNEList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "neListType"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifyType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "modifyType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "modifyTypeType"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noteModifyForm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "noteModifyForm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "noteType"));
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
