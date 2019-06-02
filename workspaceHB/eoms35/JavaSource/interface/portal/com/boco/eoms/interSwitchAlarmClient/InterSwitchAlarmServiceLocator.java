/**
 * InterSwitchAlarmServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interSwitchAlarmClient;

import java.net.URL;

import javax.xml.rpc.ServiceException;

public class InterSwitchAlarmServiceLocator extends org.apache.axis.client.Service implements InterSwitchAlarmService {

    // Use to get a proxy class for InterSwitchAlarm
    private final java.lang.String InterSwitchAlarm_address = "http://localhost:8085/eoms/services/InterSwitchAlarm";

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

    public InterSwitchAlarm getInterSwitchAlarm() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InterSwitchAlarm_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInterSwitchAlarm(endpoint);
    }

    public InterSwitchAlarm getInterSwitchAlarm(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
           InterSwitchAlarmSoapBindingStub _stub = new InterSwitchAlarmSoapBindingStub(portAddress, this);
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
            if (InterSwitchAlarm.class.isAssignableFrom(serviceEndpointInterface)) {
                InterSwitchAlarmSoapBindingStub _stub = new InterSwitchAlarmSoapBindingStub(new java.net.URL(InterSwitchAlarm_address), this);
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
        return new javax.xml.namespace.QName("http://localhost:8085/eoms/services/InterSwitchAlarm", "InterSwitchAlarmService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("InterSwitchAlarm"));
        }
        return ports.iterator();
    }

//	public InterSwitchAlarm getInterSwitchAlarm() throws ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public InterSwitchAlarm getInterSwitchAlarm(URL portAddress)
//			throws ServiceException {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
