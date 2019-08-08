/**
 * SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv_ServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.commons.system.user.synchronous;

public class SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv_ServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.commons.system.user.synchronous.SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv_Service {

    public SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv_ServiceLocator() {
    }


    public SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPort
    private java.lang.String SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPort_address = "http://localhost:8080/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv";

    public java.lang.String getSB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPortAddress() {
        return SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPortWSDDServiceName = "SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPort";

    public java.lang.String getSB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPortWSDDServiceName() {
        return SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPortWSDDServiceName;
    }

    public void setSB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPortWSDDServiceName(java.lang.String name) {
        SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPortWSDDServiceName = name;
    }

    public com.boco.eoms.commons.system.user.synchronous.SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv_PortType getSB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPort() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPort_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPort(endpoint);
    }

    public com.boco.eoms.commons.system.user.synchronous.SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv_PortType getSB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.commons.system.user.synchronous.SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvBindingStub _stub = new com.boco.eoms.commons.system.user.synchronous.SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvBindingStub(portAddress, this);
            _stub.setPortName(getSB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPortWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPortEndpointAddress(java.lang.String address) {
        SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.commons.system.user.synchronous.SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.commons.system.user.synchronous.SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvBindingStub _stub = new com.boco.eoms.commons.system.user.synchronous.SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvBindingStub(new java.net.URL(SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPort_address), this);
                _stub.setPortName(getSB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPortWSDDServiceName());
                return _stub;
            }
        } catch (java.lang.Throwable t) {
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
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPort".equals(inputPortName)) {
            return getSB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPort();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.vispractice.com/SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrv", "SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPort"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("SB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPort".equals(portName)) {
            setSB_HR_HRMIS_InquiryHRNETEmployeeInfoSrvPortEndpointAddress(address);
        } else { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
