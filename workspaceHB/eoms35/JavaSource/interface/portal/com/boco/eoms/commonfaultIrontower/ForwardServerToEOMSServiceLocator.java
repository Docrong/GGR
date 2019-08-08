/**
 * ForwardServerToEOMSServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.commonfaultIrontower;

public class ForwardServerToEOMSServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.commonfaultIrontower.ForwardServerToEOMSService {

    public ForwardServerToEOMSServiceLocator() {
    }


    public ForwardServerToEOMSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ForwardServerToEOMSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TaskToEOMS
    private java.lang.String TaskToEOMS_address = "http://10.30.227.15:8080/TowerKafkaProject/services/TaskToEOMS";

    public java.lang.String getTaskToEOMSAddress() {
        return TaskToEOMS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TaskToEOMSWSDDServiceName = "TaskToEOMS";

    public java.lang.String getTaskToEOMSWSDDServiceName() {
        return TaskToEOMSWSDDServiceName;
    }

    public void setTaskToEOMSWSDDServiceName(java.lang.String name) {
        TaskToEOMSWSDDServiceName = name;
    }

    public com.boco.eoms.commonfaultIrontower.ForwardServerToEOMS getTaskToEOMS() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TaskToEOMS_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTaskToEOMS(endpoint);
    }

    public com.boco.eoms.commonfaultIrontower.ForwardServerToEOMS getTaskToEOMS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.commonfaultIrontower.TaskToEOMSSoapBindingStub _stub = new com.boco.eoms.commonfaultIrontower.TaskToEOMSSoapBindingStub(portAddress, this);
            _stub.setPortName(getTaskToEOMSWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTaskToEOMSEndpointAddress(java.lang.String address) {
        TaskToEOMS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.commonfaultIrontower.ForwardServerToEOMS.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.commonfaultIrontower.TaskToEOMSSoapBindingStub _stub = new com.boco.eoms.commonfaultIrontower.TaskToEOMSSoapBindingStub(new java.net.URL(TaskToEOMS_address), this);
                _stub.setPortName(getTaskToEOMSWSDDServiceName());
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
        if ("TaskToEOMS".equals(inputPortName)) {
            return getTaskToEOMS();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://10.30.227.15:8080/TowerKafkaProject/services/TaskToEOMS", "ForwardServerToEOMSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://10.30.227.15:8080/TowerKafkaProject/services/TaskToEOMS", "TaskToEOMS"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("TaskToEOMS".equals(portName)) {
            setTaskToEOMSEndpointAddress(address);
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
