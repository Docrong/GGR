/**
 * InterSwitchEomsIPServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.workplan.InterSwitchEomsIPWebServices;

public class InterSwitchEomsIPServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPService {

    // Use to get a proxy class for InterSwitchEomsIP
    private final java.lang.String InterSwitchEomsIP_address = "http://211.137.82.175:8080/eoms/services/InterSwitchEomsIP";

    public java.lang.String getInterSwitchEomsIPAddress() {
        return InterSwitchEomsIP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InterSwitchEomsIPWSDDServiceName = "InterSwitchEomsIP";

    public java.lang.String getInterSwitchEomsIPWSDDServiceName() {
        return InterSwitchEomsIPWSDDServiceName;
    }

    public void setInterSwitchEomsIPWSDDServiceName(java.lang.String name) {
        InterSwitchEomsIPWSDDServiceName = name;
    }

    public com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIP getInterSwitchEomsIP() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InterSwitchEomsIP_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInterSwitchEomsIP(endpoint);
    }

    public com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIP getInterSwitchEomsIP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPSoapBindingStub _stub = new com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPSoapBindingStub(portAddress, this);
            _stub.setPortName(getInterSwitchEomsIPWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
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
            if (com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIP.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPSoapBindingStub _stub = new com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPSoapBindingStub(new java.net.URL(InterSwitchEomsIP_address), this);
                _stub.setPortName(getInterSwitchEomsIPWSDDServiceName());
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
        String inputPortName = portName.getLocalPart();
        if ("InterSwitchEomsIP".equals(inputPortName)) {
            return getInterSwitchEomsIP();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://211.137.82.175:8080/eoms/services/InterSwitchEomsIP", "InterSwitchEomsIPService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("InterSwitchEomsIP"));
        }
        return ports.iterator();
    }

}
