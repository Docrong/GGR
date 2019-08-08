/**
 * ForwardServerToTowerServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.commonfaultIrontower;

import com.boco.eoms.commons.util.xml.XmlManage;

public class ForwardServerToTowerServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.commonfaultIrontower.ForwardServerToTowerService {

    public ForwardServerToTowerServiceLocator() {
    }


    public ForwardServerToTowerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ForwardServerToTowerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    private java.lang.String ipPort = XmlManage.getFile("/config/commonfaulttowner-util.xml").getProperty("operate.ipPort");
    // Use to get a proxy class for TaskToTower
    private java.lang.String TaskToTower_address = "http://" + ipPort + "/TowerKafkaProject/services/TaskToTower";

    public java.lang.String getTaskToTowerAddress() {
        return TaskToTower_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TaskToTowerWSDDServiceName = "TaskToTower";

    public java.lang.String getTaskToTowerWSDDServiceName() {
        return TaskToTowerWSDDServiceName;
    }

    public void setTaskToTowerWSDDServiceName(java.lang.String name) {
        TaskToTowerWSDDServiceName = name;
    }

    public com.boco.eoms.commonfaultIrontower.ForwardServerToTower getTaskToTower() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TaskToTower_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTaskToTower(endpoint);
    }

    public com.boco.eoms.commonfaultIrontower.ForwardServerToTower getTaskToTower(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.commonfaultIrontower.TaskToTowerSoapBindingStub _stub = new com.boco.eoms.commonfaultIrontower.TaskToTowerSoapBindingStub(portAddress, this);
            _stub.setPortName(getTaskToTowerWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTaskToTowerEndpointAddress(java.lang.String address) {
        TaskToTower_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.commonfaultIrontower.ForwardServerToTower.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.commonfaultIrontower.TaskToTowerSoapBindingStub _stub = new com.boco.eoms.commonfaultIrontower.TaskToTowerSoapBindingStub(new java.net.URL(TaskToTower_address), this);
                _stub.setPortName(getTaskToTowerWSDDServiceName());
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
        if ("TaskToTower".equals(inputPortName)) {
            return getTaskToTower();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://10.30.227.15:8080/TowerKafkaProject/services/TaskToTower", "ForwardServerToTowerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://10.30.227.15:8080/TowerKafkaProject/services/TaskToTower", "TaskToTower"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("TaskToTower".equals(portName)) {
            setTaskToTowerEndpointAddress(address);
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
