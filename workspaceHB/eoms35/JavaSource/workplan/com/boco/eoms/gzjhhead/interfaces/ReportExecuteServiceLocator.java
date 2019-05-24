/**
 * ReportExecuteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhhead.interfaces;

public class ReportExecuteServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.gzjhhead.interfaces.ReportExecuteService {

    private String reportPort="http://10.1.32.105:8080/EOMS_J2EE/";
//	private String reportPort="http://10.1.34.243:8090/EOMS_J2EE/";
    
	// Use to get a proxy class for ReportInventoryPort
    private final java.lang.String ReportInventoryPort_address = reportPort+"/services/ReportInventoryPort";

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

    public com.boco.eoms.gzjhhead.interfaces.ReportInventoryPortType getReportInventoryPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ReportInventoryPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getReportInventoryPort(endpoint);
    }

    public com.boco.eoms.gzjhhead.interfaces.ReportInventoryPortType getReportInventoryPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.gzjhhead.interfaces.ReportInventoryBindingStub _stub = new com.boco.eoms.gzjhhead.interfaces.ReportInventoryBindingStub(portAddress, this);
            _stub.setPortName(getReportInventoryPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }


    // Use to get a proxy class for ReportExecutePort
    private final java.lang.String ReportExecutePort_address = reportPort+"/services/ReportExecutePort";

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

    public com.boco.eoms.gzjhhead.interfaces.ReportExecutePortType getReportExecutePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ReportExecutePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getReportExecutePort(endpoint);
    }

    public com.boco.eoms.gzjhhead.interfaces.ReportExecutePortType getReportExecutePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub _stub = new com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub(portAddress, this);
            _stub.setPortName(getReportExecutePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }


    // Use to get a proxy class for AuxiliaryPort
    private final java.lang.String AuxiliaryPort_address = reportPort+"/services/AuxiliaryPort";

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

    public com.boco.eoms.gzjhhead.interfaces.AuxiliaryPortType getAuxiliaryPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AuxiliaryPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAuxiliaryPort(endpoint);
    }

    public com.boco.eoms.gzjhhead.interfaces.AuxiliaryPortType getAuxiliaryPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.gzjhhead.interfaces.AuxiliaryBindingStub _stub = new com.boco.eoms.gzjhhead.interfaces.AuxiliaryBindingStub(portAddress, this);
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
            if (com.boco.eoms.gzjhhead.interfaces.ReportInventoryPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.gzjhhead.interfaces.ReportInventoryBindingStub _stub = new com.boco.eoms.gzjhhead.interfaces.ReportInventoryBindingStub(new java.net.URL(ReportInventoryPort_address), this);
                _stub.setPortName(getReportInventoryPortWSDDServiceName());
                return _stub;
            }
            if (com.boco.eoms.gzjhhead.interfaces.ReportExecutePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub _stub = new com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub(new java.net.URL(ReportExecutePort_address), this);
                _stub.setPortName(getReportExecutePortWSDDServiceName());
                return _stub;
            }
            if (com.boco.eoms.gzjhhead.interfaces.AuxiliaryPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.gzjhhead.interfaces.AuxiliaryBindingStub _stub = new com.boco.eoms.gzjhhead.interfaces.AuxiliaryBindingStub(new java.net.URL(AuxiliaryPort_address), this);
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
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
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
