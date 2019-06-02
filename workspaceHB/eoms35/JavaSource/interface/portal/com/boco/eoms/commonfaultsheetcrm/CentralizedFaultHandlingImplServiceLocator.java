/**
 * CentralizedFaultHandlingImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.commonfaultsheetcrm;

public class CentralizedFaultHandlingImplServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.commonfaultsheetcrm.CentralizedFaultHandlingImplService {

    public CentralizedFaultHandlingImplServiceLocator() {
    }


    public CentralizedFaultHandlingImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CentralizedFaultHandlingImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CentralizedFaultHandlingImplPort
    private java.lang.String CentralizedFaultHandlingImplPort_address = "http://10.30.172.40:58999/CentralizedFaultHandlingService";

    public java.lang.String getCentralizedFaultHandlingImplPortAddress() {
        return CentralizedFaultHandlingImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CentralizedFaultHandlingImplPortWSDDServiceName = "CentralizedFaultHandlingImplPort";

    public java.lang.String getCentralizedFaultHandlingImplPortWSDDServiceName() {
        return CentralizedFaultHandlingImplPortWSDDServiceName;
    }

    public void setCentralizedFaultHandlingImplPortWSDDServiceName(java.lang.String name) {
        CentralizedFaultHandlingImplPortWSDDServiceName = name;
    }

    public com.boco.eoms.commonfaultsheetcrm.CentralizedFaultHandlingImpl getCentralizedFaultHandlingImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CentralizedFaultHandlingImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCentralizedFaultHandlingImplPort(endpoint);
    }

    public com.boco.eoms.commonfaultsheetcrm.CentralizedFaultHandlingImpl getCentralizedFaultHandlingImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.commonfaultsheetcrm.CentralizedFaultHandlingImplPortBindingStub _stub = new com.boco.eoms.commonfaultsheetcrm.CentralizedFaultHandlingImplPortBindingStub(portAddress, this);
            _stub.setPortName(getCentralizedFaultHandlingImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCentralizedFaultHandlingImplPortEndpointAddress(java.lang.String address) {
        CentralizedFaultHandlingImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.commonfaultsheetcrm.CentralizedFaultHandlingImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.commonfaultsheetcrm.CentralizedFaultHandlingImplPortBindingStub _stub = new com.boco.eoms.commonfaultsheetcrm.CentralizedFaultHandlingImplPortBindingStub(new java.net.URL(CentralizedFaultHandlingImplPort_address), this);
                _stub.setPortName(getCentralizedFaultHandlingImplPortWSDDServiceName());
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
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CentralizedFaultHandlingImplPort".equals(inputPortName)) {
            return getCentralizedFaultHandlingImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://services.boco.com/", "CentralizedFaultHandlingImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://services.boco.com/", "CentralizedFaultHandlingImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CentralizedFaultHandlingImplPort".equals(portName)) {
            setCentralizedFaultHandlingImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
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
