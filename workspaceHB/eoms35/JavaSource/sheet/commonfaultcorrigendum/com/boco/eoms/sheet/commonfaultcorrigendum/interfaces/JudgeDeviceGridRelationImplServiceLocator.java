/**
 * JudgeDeviceGridRelationImplServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.sheet.commonfaultcorrigendum.interfaces;

public class JudgeDeviceGridRelationImplServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.sheet.commonfaultcorrigendum.interfaces.JudgeDeviceGridRelationImplService {

    public JudgeDeviceGridRelationImplServiceLocator() {
    }


    public JudgeDeviceGridRelationImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public JudgeDeviceGridRelationImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for JudgeDeviceGridRelationService
    private java.lang.String JudgeDeviceGridRelationService_address = "http://10.30.173.173:7087/search/services/JudgeDeviceGridRelationService";

    public java.lang.String getJudgeDeviceGridRelationServiceAddress() {
        return JudgeDeviceGridRelationService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String JudgeDeviceGridRelationServiceWSDDServiceName = "JudgeDeviceGridRelationService";

    public java.lang.String getJudgeDeviceGridRelationServiceWSDDServiceName() {
        return JudgeDeviceGridRelationServiceWSDDServiceName;
    }

    public void setJudgeDeviceGridRelationServiceWSDDServiceName(java.lang.String name) {
        JudgeDeviceGridRelationServiceWSDDServiceName = name;
    }

    public com.boco.eoms.sheet.commonfaultcorrigendum.interfaces.JudgeDeviceGridRelationImpl getJudgeDeviceGridRelationService() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(JudgeDeviceGridRelationService_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getJudgeDeviceGridRelationService(endpoint);
    }

    public com.boco.eoms.sheet.commonfaultcorrigendum.interfaces.JudgeDeviceGridRelationImpl getJudgeDeviceGridRelationService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.sheet.commonfaultcorrigendum.interfaces.JudgeDeviceGridRelationServiceSoapBindingStub _stub = new com.boco.eoms.sheet.commonfaultcorrigendum.interfaces.JudgeDeviceGridRelationServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getJudgeDeviceGridRelationServiceWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setJudgeDeviceGridRelationServiceEndpointAddress(java.lang.String address) {
        JudgeDeviceGridRelationService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.sheet.commonfaultcorrigendum.interfaces.JudgeDeviceGridRelationImpl.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.sheet.commonfaultcorrigendum.interfaces.JudgeDeviceGridRelationServiceSoapBindingStub _stub = new com.boco.eoms.sheet.commonfaultcorrigendum.interfaces.JudgeDeviceGridRelationServiceSoapBindingStub(new java.net.URL(JudgeDeviceGridRelationService_address), this);
                _stub.setPortName(getJudgeDeviceGridRelationServiceWSDDServiceName());
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
        if ("JudgeDeviceGridRelationService".equals(inputPortName)) {
            return getJudgeDeviceGridRelationService();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://emos.server.ws.boco.com", "JudgeDeviceGridRelationImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://emos.server.ws.boco.com", "JudgeDeviceGridRelationService"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("JudgeDeviceGridRelationService".equals(portName)) {
            setJudgeDeviceGridRelationServiceEndpointAddress(address);
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
