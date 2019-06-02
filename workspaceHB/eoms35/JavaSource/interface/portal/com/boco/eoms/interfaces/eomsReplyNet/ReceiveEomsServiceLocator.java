/**
 * ReceiveEomsServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.eomsReplyNet;

public class ReceiveEomsServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.interfaces.eomsReplyNet.ReceiveEomsService {

    public ReceiveEomsServiceLocator() {
    }


    public ReceiveEomsServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ReceiveEomsServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ReceiveEomsServiceSoap
    private java.lang.String ReceiveEomsServiceSoap_address = "http://10.30.244.32:8011/ReceiveEomsService.asmx";

    public java.lang.String getReceiveEomsServiceSoapAddress() {
        return ReceiveEomsServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ReceiveEomsServiceSoapWSDDServiceName = "ReceiveEomsServiceSoap";

    public java.lang.String getReceiveEomsServiceSoapWSDDServiceName() {
        return ReceiveEomsServiceSoapWSDDServiceName;
    }

    public void setReceiveEomsServiceSoapWSDDServiceName(java.lang.String name) {
        ReceiveEomsServiceSoapWSDDServiceName = name;
    }

    public com.boco.eoms.interfaces.eomsReplyNet.ReceiveEomsServiceSoap_PortType getReceiveEomsServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ReceiveEomsServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getReceiveEomsServiceSoap(endpoint);
    }

    public com.boco.eoms.interfaces.eomsReplyNet.ReceiveEomsServiceSoap_PortType getReceiveEomsServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.interfaces.eomsReplyNet.ReceiveEomsServiceSoap_BindingStub _stub = new com.boco.eoms.interfaces.eomsReplyNet.ReceiveEomsServiceSoap_BindingStub(portAddress, this);
            _stub.setPortName(getReceiveEomsServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setReceiveEomsServiceSoapEndpointAddress(java.lang.String address) {
        ReceiveEomsServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.interfaces.eomsReplyNet.ReceiveEomsServiceSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.interfaces.eomsReplyNet.ReceiveEomsServiceSoap_BindingStub _stub = new com.boco.eoms.interfaces.eomsReplyNet.ReceiveEomsServiceSoap_BindingStub(new java.net.URL(ReceiveEomsServiceSoap_address), this);
                _stub.setPortName(getReceiveEomsServiceSoapWSDDServiceName());
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
        if ("ReceiveEomsServiceSoap".equals(inputPortName)) {
            return getReceiveEomsServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ReceiveEomsService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ReceiveEomsServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ReceiveEomsServiceSoap".equals(portName)) {
            setReceiveEomsServiceSoapEndpointAddress(address);
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
