/**
 * AinetWebServerLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.interfaces;

public class AinetWebServerLocator extends org.apache.axis.client.Service implements com.interfaces.AinetWebServer {

    public AinetWebServerLocator() {
    }


    public AinetWebServerLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AinetWebServerLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AinetWebServerHttpPort
    private java.lang.String AinetWebServerHttpPort_address = "http://10.30.174.16:8089/AiNetEmos/services/AinetWebServer";

    public java.lang.String getAinetWebServerHttpPortAddress() {
        return AinetWebServerHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AinetWebServerHttpPortWSDDServiceName = "AinetWebServerHttpPort";

    public java.lang.String getAinetWebServerHttpPortWSDDServiceName() {
        return AinetWebServerHttpPortWSDDServiceName;
    }

    public void setAinetWebServerHttpPortWSDDServiceName(java.lang.String name) {
        AinetWebServerHttpPortWSDDServiceName = name;
    }

    public com.interfaces.AinetWebServerPortType getAinetWebServerHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AinetWebServerHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAinetWebServerHttpPort(endpoint);
    }

    public com.interfaces.AinetWebServerPortType getAinetWebServerHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.interfaces.AinetWebServerHttpBindingStub _stub = new com.interfaces.AinetWebServerHttpBindingStub(portAddress, this);
            _stub.setPortName(getAinetWebServerHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAinetWebServerHttpPortEndpointAddress(java.lang.String address) {
        AinetWebServerHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.interfaces.AinetWebServerPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.interfaces.AinetWebServerHttpBindingStub _stub = new com.interfaces.AinetWebServerHttpBindingStub(new java.net.URL(AinetWebServerHttpPort_address), this);
                _stub.setPortName(getAinetWebServerHttpPortWSDDServiceName());
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
        if ("AinetWebServerHttpPort".equals(inputPortName)) {
            return getAinetWebServerHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.ultrapower.com", "AinetWebServer");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.ultrapower.com", "AinetWebServerHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AinetWebServerHttpPort".equals(portName)) {
            setAinetWebServerHttpPortEndpointAddress(address);
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
