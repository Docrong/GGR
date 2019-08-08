/**
 * ReplaySheetWorkLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.sheet.commonfault.interfaces;

public class ReplaySheetWorkLocator extends org.apache.axis.client.Service implements com.boco.eoms.sheet.commonfault.interfaces.ReplaySheetWork {

    public ReplaySheetWorkLocator() {
    }


    public ReplaySheetWorkLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ReplaySheetWorkLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ReplaySheetWorkSoap
    private java.lang.String ReplaySheetWorkSoap_address = "http://10.30.244.32:3000/Plugins/HuBei.CentralizeAnalysis/WebService/ReplaySheetWork.asmx";

    public java.lang.String getReplaySheetWorkSoapAddress() {
        return ReplaySheetWorkSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ReplaySheetWorkSoapWSDDServiceName = "ReplaySheetWorkSoap";

    public java.lang.String getReplaySheetWorkSoapWSDDServiceName() {
        return ReplaySheetWorkSoapWSDDServiceName;
    }

    public void setReplaySheetWorkSoapWSDDServiceName(java.lang.String name) {
        ReplaySheetWorkSoapWSDDServiceName = name;
    }

    public com.boco.eoms.sheet.commonfault.interfaces.ReplaySheetWorkSoap_PortType getReplaySheetWorkSoap() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ReplaySheetWorkSoap_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getReplaySheetWorkSoap(endpoint);
    }

    public com.boco.eoms.sheet.commonfault.interfaces.ReplaySheetWorkSoap_PortType getReplaySheetWorkSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.sheet.commonfault.interfaces.ReplaySheetWorkSoap_BindingStub _stub = new com.boco.eoms.sheet.commonfault.interfaces.ReplaySheetWorkSoap_BindingStub(portAddress, this);
            _stub.setPortName(getReplaySheetWorkSoapWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setReplaySheetWorkSoapEndpointAddress(java.lang.String address) {
        ReplaySheetWorkSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.sheet.commonfault.interfaces.ReplaySheetWorkSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.sheet.commonfault.interfaces.ReplaySheetWorkSoap_BindingStub _stub = new com.boco.eoms.sheet.commonfault.interfaces.ReplaySheetWorkSoap_BindingStub(new java.net.URL(ReplaySheetWorkSoap_address), this);
                _stub.setPortName(getReplaySheetWorkSoapWSDDServiceName());
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
        if ("ReplaySheetWorkSoap".equals(inputPortName)) {
            return getReplaySheetWorkSoap();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ReplaySheetWork");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ReplaySheetWorkSoap"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("ReplaySheetWorkSoap".equals(portName)) {
            setReplaySheetWorkSoapEndpointAddress(address);
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
