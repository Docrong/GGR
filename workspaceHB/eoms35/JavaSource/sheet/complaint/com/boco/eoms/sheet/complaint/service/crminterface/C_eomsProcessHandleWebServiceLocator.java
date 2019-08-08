/**
 * C_eomsProcessHandleWebServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.boco.eoms.sheet.complaint.service.crminterface;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.util.xml.XmlManage;

public class C_eomsProcessHandleWebServiceLocator extends org.apache.axis.client.Service implements C_eomsProcessHandleWebService {

    public C_eomsProcessHandleWebServiceLocator() {
    }


    public C_eomsProcessHandleWebServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public C_eomsProcessHandleWebServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for c_eomsProcessHandleWebServiceHttpPort
    private java.lang.String c_eomsProcessHandleWebServiceHttpPort_address = StaticMethod.nullObject2String
            (XmlManage.getFile("/config/complaint-util.xml").getProperty("wsdlurl"));

    public java.lang.String getc_eomsProcessHandleWebServiceHttpPortAddress() {
        return c_eomsProcessHandleWebServiceHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String c_eomsProcessHandleWebServiceHttpPortWSDDServiceName = "c_eomsProcessHandleWebServiceHttpPort";

    public java.lang.String getc_eomsProcessHandleWebServiceHttpPortWSDDServiceName() {
        return c_eomsProcessHandleWebServiceHttpPortWSDDServiceName;
    }

    public void setc_eomsProcessHandleWebServiceHttpPortWSDDServiceName(java.lang.String name) {
        c_eomsProcessHandleWebServiceHttpPortWSDDServiceName = name;
    }

    public C_eomsProcessHandleWebServicePortType getc_eomsProcessHandleWebServiceHttpPort() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(c_eomsProcessHandleWebServiceHttpPort_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getc_eomsProcessHandleWebServiceHttpPort(endpoint);
    }

    public C_eomsProcessHandleWebServicePortType getc_eomsProcessHandleWebServiceHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            C_eomsProcessHandleWebServiceHttpBindingStub _stub = new C_eomsProcessHandleWebServiceHttpBindingStub(portAddress, this);
            _stub.setPortName(getc_eomsProcessHandleWebServiceHttpPortWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setc_eomsProcessHandleWebServiceHttpPortEndpointAddress(java.lang.String address) {
        c_eomsProcessHandleWebServiceHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (C_eomsProcessHandleWebServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                C_eomsProcessHandleWebServiceHttpBindingStub _stub = new C_eomsProcessHandleWebServiceHttpBindingStub(new java.net.URL(c_eomsProcessHandleWebServiceHttpPort_address), this);
                _stub.setPortName(getc_eomsProcessHandleWebServiceHttpPortWSDDServiceName());
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
        if ("c_eomsProcessHandleWebServiceHttpPort".equals(inputPortName)) {
            return getc_eomsProcessHandleWebServiceHttpPort();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.eoms.customization.csp.huawei.com", "c_eomsProcessHandleWebService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.eoms.customization.csp.huawei.com", "c_eomsProcessHandleWebServiceHttpPort"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("c_eomsProcessHandleWebServiceHttpPort".equals(portName)) {
            setc_eomsProcessHandleWebServiceHttpPortEndpointAddress(address);
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
