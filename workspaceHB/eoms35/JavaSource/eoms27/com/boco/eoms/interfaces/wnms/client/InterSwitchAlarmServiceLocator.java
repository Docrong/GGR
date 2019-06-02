/**
 * InterSwitchAlarmServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.wnms.client;

public class InterSwitchAlarmServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.interfaces.wnms.client.InterSwitchAlarmService {

    // Use to get a proxy class for InterSwitchAlarm
    private final java.lang.String InterSwitchAlarm_address = "http://localhost:8080/services/InterSwitchAlarm";

    public java.lang.String getInterSwitchAlarmAddress() {
        return InterSwitchAlarm_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InterSwitchAlarmWSDDServiceName = "InterSwitchAlarm";

    public java.lang.String getInterSwitchAlarmWSDDServiceName() {
        return InterSwitchAlarmWSDDServiceName;
    }

    public void setInterSwitchAlarmWSDDServiceName(java.lang.String name) {
        InterSwitchAlarmWSDDServiceName = name;
    }

    public com.boco.eoms.interfaces.wnms.client.InterSwitchAlarm getInterSwitchAlarm() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InterSwitchAlarm_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInterSwitchAlarm(endpoint);
    }

    public com.boco.eoms.interfaces.wnms.client.InterSwitchAlarm getInterSwitchAlarm(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.interfaces.wnms.client.InterSwitchAlarmSoapBindingStub _stub = new com.boco.eoms.interfaces.wnms.client.InterSwitchAlarmSoapBindingStub(portAddress, this);
            _stub.setPortName(getInterSwitchAlarmWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.interfaces.wnms.client.InterSwitchAlarm.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.interfaces.wnms.client.InterSwitchAlarmSoapBindingStub _stub = new com.boco.eoms.interfaces.wnms.client.InterSwitchAlarmSoapBindingStub(new java.net.URL(InterSwitchAlarm_address), this);
                _stub.setPortName(getInterSwitchAlarmWSDDServiceName());
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
        String inputPortName = portName.getLocalPart();
        if ("InterSwitchAlarm".equals(inputPortName)) {
            return getInterSwitchAlarm();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.eoms.chinamobile.com/InterSwitchAlarm", "InterSwitchAlarmService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("InterSwitchAlarm"));
        }
        return ports.iterator();
    }

}
