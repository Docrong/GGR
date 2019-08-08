/**
 * Service_ServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.boco.eoms.sheet.commonfault.interfaces;

public class Service_ServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.sheet.commonfault.interfaces.Service_Service {

    public Service_ServiceLocator() {
    }


    public Service_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Service_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for JSONServicePort
    private java.lang.String JSONServicePort_address = "http://10.30.174.15:8001/cxfivrcj/webservice/cj";

    public java.lang.String getJSONServicePortAddress() {
        return JSONServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String JSONServicePortWSDDServiceName = "JSONServicePort";

    public java.lang.String getJSONServicePortWSDDServiceName() {
        return JSONServicePortWSDDServiceName;
    }

    public void setJSONServicePortWSDDServiceName(java.lang.String name) {
        JSONServicePortWSDDServiceName = name;
    }

    public com.boco.eoms.sheet.commonfault.interfaces.Service_PortType getJSONServicePort() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(JSONServicePort_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getJSONServicePort(endpoint);
    }

    public com.boco.eoms.sheet.commonfault.interfaces.Service_PortType getJSONServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.sheet.commonfault.interfaces.ServiceSoapBindingStub _stub = new com.boco.eoms.sheet.commonfault.interfaces.ServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getJSONServicePortWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setJSONServicePortEndpointAddress(java.lang.String address) {
        JSONServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.sheet.commonfault.interfaces.Service_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.sheet.commonfault.interfaces.ServiceSoapBindingStub _stub = new com.boco.eoms.sheet.commonfault.interfaces.ServiceSoapBindingStub(new java.net.URL(JSONServicePort_address), this);
                _stub.setPortName(getJSONServicePortWSDDServiceName());
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
        if ("JSONServicePort".equals(inputPortName)) {
            return getJSONServicePort();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ivrcj.zysoft.com/", "Service");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ivrcj.zysoft.com/", "JSONServicePort"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("JSONServicePort".equals(portName)) {
            setJSONServicePortEndpointAddress(address);
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
