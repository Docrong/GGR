/**
 * TSfaultInfo_yiyangLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.groupcomplantts;

import com.boco.eoms.commons.util.xml.XmlManage;

public class TSfaultInfo_yiyangLocator extends org.apache.axis.client.Service implements com.boco.eoms.groupcomplantts.TSfaultInfo_yiyang {

    public TSfaultInfo_yiyangLocator() {
    }


    public TSfaultInfo_yiyangLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TSfaultInfo_yiyangLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TSfaultInfo_yiyangSoap
    private java.lang.String TSfaultInfo_yiyangSoap_address = XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.url");


    public java.lang.String getTSfaultInfo_yiyangSoapAddress() {
        return TSfaultInfo_yiyangSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TSfaultInfo_yiyangSoapWSDDServiceName = "TSfaultInfo_yiyangSoap";

    public java.lang.String getTSfaultInfo_yiyangSoapWSDDServiceName() {
        return TSfaultInfo_yiyangSoapWSDDServiceName;
    }

    public void setTSfaultInfo_yiyangSoapWSDDServiceName(java.lang.String name) {
        TSfaultInfo_yiyangSoapWSDDServiceName = name;
    }

    public com.boco.eoms.groupcomplantts.TSfaultInfo_yiyangSoap_PortType getTSfaultInfo_yiyangSoap() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TSfaultInfo_yiyangSoap_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTSfaultInfo_yiyangSoap(endpoint);
    }

    public com.boco.eoms.groupcomplantts.TSfaultInfo_yiyangSoap_PortType getTSfaultInfo_yiyangSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.groupcomplantts.TSfaultInfo_yiyangSoap_BindingStub _stub = new com.boco.eoms.groupcomplantts.TSfaultInfo_yiyangSoap_BindingStub(portAddress, this);
            _stub.setPortName(getTSfaultInfo_yiyangSoapWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTSfaultInfo_yiyangSoapEndpointAddress(java.lang.String address) {
        TSfaultInfo_yiyangSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.groupcomplantts.TSfaultInfo_yiyangSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.groupcomplantts.TSfaultInfo_yiyangSoap_BindingStub _stub = new com.boco.eoms.groupcomplantts.TSfaultInfo_yiyangSoap_BindingStub(new java.net.URL(TSfaultInfo_yiyangSoap_address), this);
                _stub.setPortName(getTSfaultInfo_yiyangSoapWSDDServiceName());
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
        if ("TSfaultInfo_yiyangSoap".equals(inputPortName)) {
            return getTSfaultInfo_yiyangSoap();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "TSfaultInfo_yiyang");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "TSfaultInfo_yiyangSoap"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("TSfaultInfo_yiyangSoap".equals(portName)) {
            setTSfaultInfo_yiyangSoapEndpointAddress(address);
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
