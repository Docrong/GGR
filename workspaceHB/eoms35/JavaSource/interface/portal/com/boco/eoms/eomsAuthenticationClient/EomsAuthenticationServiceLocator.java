/**
 * EomsAuthenticationServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.eomsAuthenticationClient;

import java.net.URL;

import javax.xml.rpc.ServiceException;

public class EomsAuthenticationServiceLocator extends org.apache.axis.client.Service implements EomsAuthenticationService {

    // Use to get a proxy class for EomsAuthentication
    private final java.lang.String EomsAuthentication_address = "http://localhost:8085/eoms/services/EomsAuthentication";

    public java.lang.String getEomsAuthenticationAddress() {
        return EomsAuthentication_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EomsAuthenticationWSDDServiceName = "EomsAuthentication";

    public java.lang.String getEomsAuthenticationWSDDServiceName() {
        return EomsAuthenticationWSDDServiceName;
    }

    public void setEomsAuthenticationWSDDServiceName(java.lang.String name) {
        EomsAuthenticationWSDDServiceName = name;
    }

    public EomsAuthentication getEomsAuthentication() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EomsAuthentication_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEomsAuthentication(endpoint);
    }

    public EomsAuthentication getEomsAuthentication(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            EomsAuthenticationSoapBindingStub _stub = new EomsAuthenticationSoapBindingStub(portAddress, this);
            _stub.setPortName(getEomsAuthenticationWSDDServiceName());
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
            if (EomsAuthentication.class.isAssignableFrom(serviceEndpointInterface)) {
                EomsAuthenticationSoapBindingStub _stub = new EomsAuthenticationSoapBindingStub(new java.net.URL(EomsAuthentication_address), this);
                _stub.setPortName(getEomsAuthenticationWSDDServiceName());
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
        if ("EomsAuthentication".equals(inputPortName)) {
            return getEomsAuthentication();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:8085/eoms/services/EomsAuthentication", "EomsAuthenticationService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("EomsAuthentication"));
        }
        return ports.iterator();
    }



}
