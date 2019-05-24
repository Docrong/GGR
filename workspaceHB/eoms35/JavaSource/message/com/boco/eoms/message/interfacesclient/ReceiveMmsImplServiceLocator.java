/**
 * ReceiveMmsImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.message.interfacesclient;

public class ReceiveMmsImplServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.message.interfacesclient.ReceiveMmsImplService {

    public ReceiveMmsImplServiceLocator() {
    }


    public ReceiveMmsImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ReceiveMmsImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for receiveMms
    private java.lang.String receiveMms_address = "http://10.194.2.23:8180/MMS_SEND/services/receiveMms";

    public java.lang.String getreceiveMmsAddress() {
        return receiveMms_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String receiveMmsWSDDServiceName = "receiveMms";

    public java.lang.String getreceiveMmsWSDDServiceName() {
        return receiveMmsWSDDServiceName;
    }

    public void setreceiveMmsWSDDServiceName(java.lang.String name) {
        receiveMmsWSDDServiceName = name;
    }

    public com.boco.eoms.message.interfacesclient.ReceiveMmsImpl getreceiveMms() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(receiveMms_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getreceiveMms(endpoint);
    }

    public com.boco.eoms.message.interfacesclient.ReceiveMmsImpl getreceiveMms(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
        	com.boco.eoms.message.interfacesclient.ReceiveMmsSoapBindingStub _stub = new com.boco.eoms.message.interfacesclient.ReceiveMmsSoapBindingStub(portAddress, this);
            _stub.setPortName(getreceiveMmsWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setreceiveMmsEndpointAddress(java.lang.String address) {
        receiveMms_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.message.interfacesclient.ReceiveMmsImpl.class.isAssignableFrom(serviceEndpointInterface)) {
            	com.boco.eoms.message.interfacesclient.ReceiveMmsSoapBindingStub _stub = new com.boco.eoms.message.interfacesclient.ReceiveMmsSoapBindingStub(new java.net.URL(receiveMms_address), this);
                _stub.setPortName(getreceiveMmsWSDDServiceName());
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
        if ("receiveMms".equals(inputPortName)) {
            return getreceiveMms();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://10.194.2.23:8180/MMS_SEND/services/receiveMms", "receiveMmsImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://10.194.2.23:8180/MMS_SEND/services/receiveMms", "receiveMms"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("receiveMms".equals(portName)) {
            setreceiveMmsEndpointAddress(address);
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
