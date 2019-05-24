/**
 * SheetStateSync3SoapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.alarm.service.sheetStateSync;

public class SheetStateSync3SoapServiceLocator extends org.apache.axis.client.Service implements com.boco.alarm.service.sheetStateSync.SheetStateSync3SoapService {

    public SheetStateSync3SoapServiceLocator() {
    }


    public SheetStateSync3SoapServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SheetStateSync3SoapServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SheetStateSync3SoapPort
    private java.lang.String SheetStateSync3SoapPort_address = "http://10.30.198.164:9000/SheetStateSync";

    public java.lang.String getSheetStateSync3SoapPortAddress() {
        return SheetStateSync3SoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SheetStateSync3SoapPortWSDDServiceName = "SheetStateSync3SoapPort";

    public java.lang.String getSheetStateSync3SoapPortWSDDServiceName() {
        return SheetStateSync3SoapPortWSDDServiceName;
    }

    public void setSheetStateSync3SoapPortWSDDServiceName(java.lang.String name) {
        SheetStateSync3SoapPortWSDDServiceName = name;
    }

    public com.boco.alarm.service.sheetStateSync.SheetStateSync3Soap getSheetStateSync3SoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SheetStateSync3SoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSheetStateSync3SoapPort(endpoint);
    }

    public com.boco.alarm.service.sheetStateSync.SheetStateSync3Soap getSheetStateSync3SoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.alarm.service.sheetStateSync.SheetStateSync3SoapServiceSoapBindingStub _stub = new com.boco.alarm.service.sheetStateSync.SheetStateSync3SoapServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSheetStateSync3SoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSheetStateSync3SoapPortEndpointAddress(java.lang.String address) {
        SheetStateSync3SoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.alarm.service.sheetStateSync.SheetStateSync3Soap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.alarm.service.sheetStateSync.SheetStateSync3SoapServiceSoapBindingStub _stub = new com.boco.alarm.service.sheetStateSync.SheetStateSync3SoapServiceSoapBindingStub(new java.net.URL(SheetStateSync3SoapPort_address), this);
                _stub.setPortName(getSheetStateSync3SoapPortWSDDServiceName());
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
        if ("SheetStateSync3SoapPort".equals(inputPortName)) {
            return getSheetStateSync3SoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.eoms.chinamobile.com/SheetStateSync", "SheetStateSync3SoapService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.eoms.chinamobile.com/SheetStateSync", "SheetStateSync3SoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SheetStateSync3SoapPort".equals(portName)) {
            setSheetStateSync3SoapPortEndpointAddress(address);
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
