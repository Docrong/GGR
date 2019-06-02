/**
 * EMOSAutomSearchServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.sheet.groupcomplaint.qo;

public class EMOSAutomSearchServiceImplServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.sheet.groupcomplaint.qo.EMOSAutomSearchServiceImplService {

    public EMOSAutomSearchServiceImplServiceLocator() {
    }


    public EMOSAutomSearchServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EMOSAutomSearchServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EMOSAutomSearchServiceImplPort
    private java.lang.String EMOSAutomSearchServiceImplPort_address = "http://10.30.172.40:8899/services/EMOSAutomSearchService";

    public java.lang.String getEMOSAutomSearchServiceImplPortAddress() {
        return EMOSAutomSearchServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EMOSAutomSearchServiceImplPortWSDDServiceName = "EMOSAutomSearchServiceImplPort";

    public java.lang.String getEMOSAutomSearchServiceImplPortWSDDServiceName() {
        return EMOSAutomSearchServiceImplPortWSDDServiceName;
    }

    public void setEMOSAutomSearchServiceImplPortWSDDServiceName(java.lang.String name) {
        EMOSAutomSearchServiceImplPortWSDDServiceName = name;
    }

    public com.boco.eoms.sheet.groupcomplaint.qo.EMOSAutomSearchServiceImpl getEMOSAutomSearchServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EMOSAutomSearchServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEMOSAutomSearchServiceImplPort(endpoint);
    }

    public com.boco.eoms.sheet.groupcomplaint.qo.EMOSAutomSearchServiceImpl getEMOSAutomSearchServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.sheet.groupcomplaint.qo.EMOSAutomSearchServiceImplPortBindingStub _stub = new com.boco.eoms.sheet.groupcomplaint.qo.EMOSAutomSearchServiceImplPortBindingStub(portAddress, this);
            _stub.setPortName(getEMOSAutomSearchServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEMOSAutomSearchServiceImplPortEndpointAddress(java.lang.String address) {
        EMOSAutomSearchServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.sheet.groupcomplaint.qo.EMOSAutomSearchServiceImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.sheet.groupcomplaint.qo.EMOSAutomSearchServiceImplPortBindingStub _stub = new com.boco.eoms.sheet.groupcomplaint.qo.EMOSAutomSearchServiceImplPortBindingStub(new java.net.URL(EMOSAutomSearchServiceImplPort_address), this);
                _stub.setPortName(getEMOSAutomSearchServiceImplPortWSDDServiceName());
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
        if ("EMOSAutomSearchServiceImplPort".equals(inputPortName)) {
            return getEMOSAutomSearchServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.boco.com/", "EMOSAutomSearchServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.boco.com/", "EMOSAutomSearchServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EMOSAutomSearchServiceImplPort".equals(portName)) {
            setEMOSAutomSearchServiceImplPortEndpointAddress(address);
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
