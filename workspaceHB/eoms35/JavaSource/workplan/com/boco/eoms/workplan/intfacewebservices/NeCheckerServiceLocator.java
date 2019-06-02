/**
 * NeCheckerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.workplan.intfacewebservices;

import com.boco.eoms.workplan.util.WorkplanMgrLocator;

public class NeCheckerServiceLocator extends org.apache.axis.client.Service implements NeCheckerService {

    public NeCheckerServiceLocator() {
    }


    public NeCheckerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public NeCheckerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for NeCheckerServiceHttpPort
    private java.lang.String NeCheckerServiceHttpPort_address =WorkplanMgrLocator.getAttributes().getIntfaceUrl();

    public java.lang.String getNeCheckerServiceHttpPortAddress() {
        return NeCheckerServiceHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NeCheckerServiceHttpPortWSDDServiceName = "NeCheckerServiceHttpPort";

    public java.lang.String getNeCheckerServiceHttpPortWSDDServiceName() {
        return NeCheckerServiceHttpPortWSDDServiceName;
    }

    public void setNeCheckerServiceHttpPortWSDDServiceName(java.lang.String name) {
        NeCheckerServiceHttpPortWSDDServiceName = name;
    }

    public NeCheckerServicePortType getNeCheckerServiceHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NeCheckerServiceHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNeCheckerServiceHttpPort(endpoint);
    }

    public NeCheckerServicePortType getNeCheckerServiceHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            NeCheckerServiceHttpBindingStub _stub = new NeCheckerServiceHttpBindingStub(portAddress, this);
            _stub.setPortName(getNeCheckerServiceHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNeCheckerServiceHttpPortEndpointAddress(java.lang.String address) {
        NeCheckerServiceHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (NeCheckerServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                NeCheckerServiceHttpBindingStub _stub = new NeCheckerServiceHttpBindingStub(new java.net.URL(NeCheckerServiceHttpPort_address), this);
                _stub.setPortName(getNeCheckerServiceHttpPortWSDDServiceName());
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
        if ("NeCheckerServiceHttpPort".equals(inputPortName)) {
            return getNeCheckerServiceHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.nechecker.boco.com", "NeCheckerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.nechecker.boco.com", "NeCheckerServiceHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("NeCheckerServiceHttpPort".equals(portName)) {
            setNeCheckerServiceHttpPortEndpointAddress(address);
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
