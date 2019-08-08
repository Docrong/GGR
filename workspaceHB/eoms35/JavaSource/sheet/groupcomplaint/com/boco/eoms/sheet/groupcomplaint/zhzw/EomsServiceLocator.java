/**
 * EomsServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.sheet.groupcomplaint.zhzw;

import com.boco.eoms.commons.util.xml.XmlManage;

public class EomsServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.sheet.groupcomplaint.zhzw.EomsService {

    public EomsServiceLocator() {
    }


    public EomsServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EomsServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Eoms2IomServicePort
    private java.lang.String Eoms2IomServicePort_address = XmlManage.getFile("/config/GroupComplaintZHZWService.xml").getProperty("interfaceType.url");

    public java.lang.String getEoms2IomServicePortAddress() {
        return Eoms2IomServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Eoms2IomServicePortWSDDServiceName = "Eoms2IomServicePort";

    public java.lang.String getEoms2IomServicePortWSDDServiceName() {
        return Eoms2IomServicePortWSDDServiceName;
    }

    public void setEoms2IomServicePortWSDDServiceName(java.lang.String name) {
        Eoms2IomServicePortWSDDServiceName = name;
    }

    public com.boco.eoms.sheet.groupcomplaint.zhzw.Eoms2IomService getEoms2IomServicePort() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Eoms2IomServicePort_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEoms2IomServicePort(endpoint);
    }

    public com.boco.eoms.sheet.groupcomplaint.zhzw.Eoms2IomService getEoms2IomServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.sheet.groupcomplaint.zhzw.EomsServiceSoapBindingStub _stub = new com.boco.eoms.sheet.groupcomplaint.zhzw.EomsServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getEoms2IomServicePortWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEoms2IomServicePortEndpointAddress(java.lang.String address) {
        Eoms2IomServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.sheet.groupcomplaint.zhzw.Eoms2IomService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.sheet.groupcomplaint.zhzw.EomsServiceSoapBindingStub _stub = new com.boco.eoms.sheet.groupcomplaint.zhzw.EomsServiceSoapBindingStub(new java.net.URL(Eoms2IomServicePort_address), this);
                _stub.setPortName(getEoms2IomServicePortWSDDServiceName());
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
        if ("Eoms2IomServicePort".equals(inputPortName)) {
            return getEoms2IomServicePort();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.web.eoms.iwhalecloud.com", "EomsService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.web.eoms.iwhalecloud.com", "Eoms2IomServicePort"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("Eoms2IomServicePort".equals(portName)) {
            setEoms2IomServicePortEndpointAddress(address);
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
