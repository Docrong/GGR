/**
 * IPreprocessResultReviewImplServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.EOMSPreService;

public class IPreprocessResultReviewImplServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.interfaces.EOMSPreService.IPreprocessResultReviewImplService {

    public IPreprocessResultReviewImplServiceLocator() {
    }


    public IPreprocessResultReviewImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IPreprocessResultReviewImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IPreprocessResultReviewImplPort
    private java.lang.String IPreprocessResultReviewImplPort_address = "http://10.25.116.75:8088/PreprocessResultReviewServer/IPreprocessResultReviewImplPort";

    public java.lang.String getIPreprocessResultReviewImplPortAddress() {
        return IPreprocessResultReviewImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IPreprocessResultReviewImplPortWSDDServiceName = "IPreprocessResultReviewImplPort";

    public java.lang.String getIPreprocessResultReviewImplPortWSDDServiceName() {
        return IPreprocessResultReviewImplPortWSDDServiceName;
    }

    public void setIPreprocessResultReviewImplPortWSDDServiceName(java.lang.String name) {
        IPreprocessResultReviewImplPortWSDDServiceName = name;
    }

    public com.boco.eoms.interfaces.EOMSPreService.IPreprocessResultReviewImplDelegate getIPreprocessResultReviewImplPort() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IPreprocessResultReviewImplPort_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIPreprocessResultReviewImplPort(endpoint);
    }

    public com.boco.eoms.interfaces.EOMSPreService.IPreprocessResultReviewImplDelegate getIPreprocessResultReviewImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.interfaces.EOMSPreService.IPreprocessResultReviewImplPortBindingStub _stub = new com.boco.eoms.interfaces.EOMSPreService.IPreprocessResultReviewImplPortBindingStub(portAddress, this);
            _stub.setPortName(getIPreprocessResultReviewImplPortWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIPreprocessResultReviewImplPortEndpointAddress(java.lang.String address) {
        IPreprocessResultReviewImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.interfaces.EOMSPreService.IPreprocessResultReviewImplDelegate.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.interfaces.EOMSPreService.IPreprocessResultReviewImplPortBindingStub _stub = new com.boco.eoms.interfaces.EOMSPreService.IPreprocessResultReviewImplPortBindingStub(new java.net.URL(IPreprocessResultReviewImplPort_address), this);
                _stub.setPortName(getIPreprocessResultReviewImplPortWSDDServiceName());
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
        if ("IPreprocessResultReviewImplPort".equals(inputPortName)) {
            return getIPreprocessResultReviewImplPort();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.prrs.hb.boco.com/", "IPreprocessResultReviewImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.prrs.hb.boco.com/", "IPreprocessResultReviewImplPort"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("IPreprocessResultReviewImplPort".equals(portName)) {
            setIPreprocessResultReviewImplPortEndpointAddress(address);
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
