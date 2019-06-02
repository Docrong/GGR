/**
 * GroupFaultServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.boco.eoms.sheet.commonfault.interfaces;

public class GroupFaultServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.sheet.commonfault.interfaces.GroupFaultService {

    public GroupFaultServiceLocator() {
    }


    public GroupFaultServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GroupFaultServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for GroupFault
    private java.lang.String GroupFault_address = "http://10.25.88.18:8010/usi-fccos-ws/services/GroupFault";

    public java.lang.String getGroupFaultAddress() {
        return GroupFault_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String GroupFaultWSDDServiceName = "GroupFault";

    public java.lang.String getGroupFaultWSDDServiceName() {
        return GroupFaultWSDDServiceName;
    }

    public void setGroupFaultWSDDServiceName(java.lang.String name) {
        GroupFaultWSDDServiceName = name;
    }

    public com.boco.eoms.sheet.commonfault.interfaces.GroupFault_PortType getGroupFault() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(GroupFault_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getGroupFault(endpoint);
    }

    public com.boco.eoms.sheet.commonfault.interfaces.GroupFault_PortType getGroupFault(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.sheet.commonfault.interfaces.GroupFaultSoapBindingStub _stub = new com.boco.eoms.sheet.commonfault.interfaces.GroupFaultSoapBindingStub(portAddress, this);
            _stub.setPortName(getGroupFaultWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setGroupFaultEndpointAddress(java.lang.String address) {
        GroupFault_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.sheet.commonfault.interfaces.GroupFault_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.sheet.commonfault.interfaces.GroupFaultSoapBindingStub _stub = new com.boco.eoms.sheet.commonfault.interfaces.GroupFaultSoapBindingStub(new java.net.URL(GroupFault_address), this);
                _stub.setPortName(getGroupFaultWSDDServiceName());
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
        if ("GroupFault".equals(inputPortName)) {
            return getGroupFault();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://10.25.88.18:8010/usi-fccos-ws/services/GroupFault", "GroupFaultService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://10.25.88.18:8010/usi-fccos-ws/services/GroupFault", "GroupFault"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("GroupFault".equals(portName)) {
            setGroupFaultEndpointAddress(address);
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
