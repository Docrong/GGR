/**
 * PortalServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.portal.webservices.bo;

public class PortalServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.portal.webservices.bo.PortalService {

    public PortalServiceLocator() {
    }


    public PortalServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PortalServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PortalServiceHttpPort
    private java.lang.String PortalServiceHttpPort_address = "http://10.149.6.4:8080/portalTest/services/PortalService";

    public java.lang.String getPortalServiceHttpPortAddress() {
        return PortalServiceHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PortalServiceHttpPortWSDDServiceName = "PortalServiceHttpPort";

    public java.lang.String getPortalServiceHttpPortWSDDServiceName() {
        return PortalServiceHttpPortWSDDServiceName;
    }

    public void setPortalServiceHttpPortWSDDServiceName(java.lang.String name) {
        PortalServiceHttpPortWSDDServiceName = name;
    }

    public com.boco.eoms.portal.webservices.bo.PortalServicePortType getPortalServiceHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PortalServiceHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPortalServiceHttpPort(endpoint);
    }

    public com.boco.eoms.portal.webservices.bo.PortalServicePortType getPortalServiceHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.portal.webservices.bo.PortalServiceHttpBindingStub _stub = new com.boco.eoms.portal.webservices.bo.PortalServiceHttpBindingStub(portAddress, this);
            _stub.setPortName(getPortalServiceHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPortalServiceHttpPortEndpointAddress(java.lang.String address) {
        PortalServiceHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.portal.webservices.bo.PortalServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.portal.webservices.bo.PortalServiceHttpBindingStub _stub = new com.boco.eoms.portal.webservices.bo.PortalServiceHttpBindingStub(new java.net.URL(PortalServiceHttpPort_address), this);
                _stub.setPortName(getPortalServiceHttpPortWSDDServiceName());
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
        if ("PortalServiceHttpPort".equals(inputPortName)) {
            return getPortalServiceHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://bo.webservices.portal.eoms.boco.com", "PortalService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://bo.webservices.portal.eoms.boco.com", "PortalServiceHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PortalServiceHttpPort".equals(portName)) {
            setPortalServiceHttpPortEndpointAddress(address);
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
