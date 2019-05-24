/**
 * ReportExecuteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

import javax.xml.rpc.ServiceException;

public class ReportExecuteServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.gzjhead.interfaces.ReportExecuteService {

    // Use to get a proxy class for ReportInventoryPort
    private final java.lang.String ReportInventoryPort_address = "http://10.1.45.202:7070/ScheduleService/services/ReportInventoryPort";

    public java.lang.String getReportInventoryPortAddress() {
        return ReportInventoryPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ReportInventoryPortWSDDServiceName = "ReportInventoryPort";

    public java.lang.String getReportInventoryPortWSDDServiceName() {
        return ReportInventoryPortWSDDServiceName;
    }

    public void setReportInventoryPortWSDDServiceName(java.lang.String name) {
        ReportInventoryPortWSDDServiceName = name;
    }

    public com.boco.eoms.gzjhead.interfaces.ReportInventoryPortType getReportInventoryPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ReportInventoryPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getReportInventoryPort(endpoint);
    }

    public com.boco.eoms.gzjhead.interfaces.ReportInventoryPortType getReportInventoryPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.gzjhead.interfaces.ReportInventoryBindingStub _stub = new com.boco.eoms.gzjhead.interfaces.ReportInventoryBindingStub(portAddress, this);
            _stub.setPortName(getReportInventoryPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }


    // Use to get a proxy class for ReportExecutePort
    private final java.lang.String ReportExecutePort_address = "http://10.1.45.202:7070/ScheduleService/services/ReportExecutePort";

    public java.lang.String getReportExecutePortAddress() {
        return ReportExecutePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ReportExecutePortWSDDServiceName = "ReportExecutePort";

    public java.lang.String getReportExecutePortWSDDServiceName() {
        return ReportExecutePortWSDDServiceName;
    }

    public void setReportExecutePortWSDDServiceName(java.lang.String name) {
        ReportExecutePortWSDDServiceName = name;
    }

    public com.boco.eoms.gzjhead.interfaces.ReportExecutePortType getReportExecutePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ReportExecutePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getReportExecutePort(endpoint);
    }

    public com.boco.eoms.gzjhead.interfaces.ReportExecutePortType getReportExecutePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.gzjhead.interfaces.ReportExecuteBindingStub _stub = new com.boco.eoms.gzjhead.interfaces.ReportExecuteBindingStub(portAddress, this);
            _stub.setPortName(getReportExecutePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }


    // Use to get a proxy class for AuxiliaryPort
    private final java.lang.String AuxiliaryPort_address = "http://localhost:9999/AssignScheduleService";

    public java.lang.String getAuxiliaryPortAddress() {
        return AuxiliaryPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AuxiliaryPortWSDDServiceName = "AuxiliaryPort";

    public java.lang.String getAuxiliaryPortWSDDServiceName() {
        return AuxiliaryPortWSDDServiceName;
    }

    public void setAuxiliaryPortWSDDServiceName(java.lang.String name) {
        AuxiliaryPortWSDDServiceName = name;
    }

    public com.boco.eoms.gzjhead.interfaces.AuxiliaryPortType getAuxiliaryPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AuxiliaryPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAuxiliaryPort(endpoint);
    }

    public com.boco.eoms.gzjhead.interfaces.AuxiliaryPortType getAuxiliaryPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.gzjhead.interfaces.AuxiliaryBindingStub _stub = new com.boco.eoms.gzjhead.interfaces.AuxiliaryBindingStub(portAddress, this);
            _stub.setPortName(getAuxiliaryPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.gzjhead.interfaces.ReportInventoryPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.gzjhead.interfaces.ReportInventoryBindingStub _stub = new com.boco.eoms.gzjhead.interfaces.ReportInventoryBindingStub(new java.net.URL(ReportInventoryPort_address), this);
                _stub.setPortName(getReportInventoryPortWSDDServiceName());
                return _stub;
            }
            if (com.boco.eoms.gzjhead.interfaces.ReportExecutePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.gzjhead.interfaces.ReportExecuteBindingStub _stub = new com.boco.eoms.gzjhead.interfaces.ReportExecuteBindingStub(new java.net.URL(ReportExecutePort_address), this);
                _stub.setPortName(getReportExecutePortWSDDServiceName());
                return _stub;
            }
            if (com.boco.eoms.gzjhead.interfaces.AuxiliaryPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.gzjhead.interfaces.AuxiliaryBindingStub _stub = new com.boco.eoms.gzjhead.interfaces.AuxiliaryBindingStub(new java.net.URL(AuxiliaryPort_address), this);
                _stub.setPortName(getAuxiliaryPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("ReportInventoryPort".equals(inputPortName)) {
            return getReportInventoryPort();
        }
        else if ("ReportExecutePort".equals(inputPortName)) {
            return getReportExecutePort();
        }
        else if ("AuxiliaryPort".equals(inputPortName)) {
            return getAuxiliaryPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "ReportExecuteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("ReportInventoryPort"));
            ports.add(new javax.xml.namespace.QName("ReportExecutePort"));
            ports.add(new javax.xml.namespace.QName("AuxiliaryPort"));
        }
        return ports.iterator();
    }

}
