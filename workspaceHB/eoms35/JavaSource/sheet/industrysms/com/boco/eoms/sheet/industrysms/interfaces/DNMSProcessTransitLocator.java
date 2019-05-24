/**
 * DNMSProcessTransitLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.sheet.industrysms.interfaces;

public class DNMSProcessTransitLocator extends org.apache.axis.client.Service implements com.boco.eoms.sheet.industrysms.interfaces.DNMSProcessTransit {

    public DNMSProcessTransitLocator() {
    }


    public DNMSProcessTransitLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DNMSProcessTransitLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DNMSProcessTransitHttpPort
    private java.lang.String DNMSProcessTransitHttpPort_address = "http://10.221.18.76:8080/EmosTransit/DNMSProcessTransit.ws";

    public java.lang.String getDNMSProcessTransitHttpPortAddress() {
        return DNMSProcessTransitHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DNMSProcessTransitHttpPortWSDDServiceName = "DNMSProcessTransitHttpPort";

    public java.lang.String getDNMSProcessTransitHttpPortWSDDServiceName() {
        return DNMSProcessTransitHttpPortWSDDServiceName;
    }

    public void setDNMSProcessTransitHttpPortWSDDServiceName(java.lang.String name) {
        DNMSProcessTransitHttpPortWSDDServiceName = name;
    }

    public com.boco.eoms.sheet.industrysms.interfaces.DNMSProcessTransitPortType getDNMSProcessTransitHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DNMSProcessTransitHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDNMSProcessTransitHttpPort(endpoint);
    }

    public com.boco.eoms.sheet.industrysms.interfaces.DNMSProcessTransitPortType getDNMSProcessTransitHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.sheet.industrysms.interfaces.DNMSProcessTransitHttpBindingStub _stub = new com.boco.eoms.sheet.industrysms.interfaces.DNMSProcessTransitHttpBindingStub(portAddress, this);
            _stub.setPortName(getDNMSProcessTransitHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDNMSProcessTransitHttpPortEndpointAddress(java.lang.String address) {
        DNMSProcessTransitHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.sheet.industrysms.interfaces.DNMSProcessTransitPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.sheet.industrysms.interfaces.DNMSProcessTransitHttpBindingStub _stub = new com.boco.eoms.sheet.industrysms.interfaces.DNMSProcessTransitHttpBindingStub(new java.net.URL(DNMSProcessTransitHttpPort_address), this);
                _stub.setPortName(getDNMSProcessTransitHttpPortWSDDServiceName());
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
        if ("DNMSProcessTransitHttpPort".equals(inputPortName)) {
            return getDNMSProcessTransitHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://emosTransit.mas.eastcom.com", "DNMSProcessTransit");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://emosTransit.mas.eastcom.com", "DNMSProcessTransitHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DNMSProcessTransitHttpPort".equals(portName)) {
            setDNMSProcessTransitHttpPortEndpointAddress(address);
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
