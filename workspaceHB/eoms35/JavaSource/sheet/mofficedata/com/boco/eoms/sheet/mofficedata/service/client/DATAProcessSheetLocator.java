package com.boco.eoms.sheet.mofficedata.service.client;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.util.xml.XmlManage;

/**
 * DATAProcessSheetLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */


public class DATAProcessSheetLocator extends org.apache.axis.client.Service implements DATAProcessSheet {

    public DATAProcessSheetLocator() {
    }


    public DATAProcessSheetLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DATAProcessSheetLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EomsDataImportImplPort
    private java.lang.String EomsDataImportImplPort_address = StaticMethod.nullObject2String(XmlManage.getFile("/config/mofficedata-util.xml").getProperty("interfaces.url"));

    public java.lang.String getEomsDataImportImplPortAddress() {
        return EomsDataImportImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EomsDataImportImplPortWSDDServiceName = "EomsDataImportImplPort";

    public java.lang.String getEomsDataImportImplPortWSDDServiceName() {
        return EomsDataImportImplPortWSDDServiceName;
    }

    public void setEomsDataImportImplPortWSDDServiceName(java.lang.String name) {
        EomsDataImportImplPortWSDDServiceName = name;
    }

    public IEomsDataImport getEomsDataImportImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EomsDataImportImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEomsDataImportImplPort(endpoint);
    }

    public IEomsDataImport getEomsDataImportImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            DATAProcessSheetSoapBindingStub _stub = new DATAProcessSheetSoapBindingStub(portAddress, this);
            _stub.setPortName(getEomsDataImportImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEomsDataImportImplPortEndpointAddress(java.lang.String address) {
        EomsDataImportImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (IEomsDataImport.class.isAssignableFrom(serviceEndpointInterface)) {
                DATAProcessSheetSoapBindingStub _stub = new DATAProcessSheetSoapBindingStub(new java.net.URL(EomsDataImportImplPort_address), this);
                _stub.setPortName(getEomsDataImportImplPortWSDDServiceName());
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
        java.lang.String inputPortName = portName.getLocalPart();
        if ("EomsDataImportImplPort".equals(inputPortName)) {
            return getEomsDataImportImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://common.webservice.boco.com/", "DATAProcessSheet");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://common.webservice.boco.com/", "EomsDataImportImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EomsDataImportImplPort".equals(portName)) {
            setEomsDataImportImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
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
