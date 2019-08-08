/**
 * FaultSheetServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.group.client.FaultSheet;

public class FaultSheetServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetService {

    // Use to get a proxy class for FaultSheet
    private final java.lang.String FaultSheet_address = "http://localhost:8080/services/FaultSheet";

    public java.lang.String getFaultSheetAddress() {
        return FaultSheet_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FaultSheetWSDDServiceName = "FaultSheet";

    public java.lang.String getFaultSheetWSDDServiceName() {
        return FaultSheetWSDDServiceName;
    }

    public void setFaultSheetWSDDServiceName(java.lang.String name) {
        FaultSheetWSDDServiceName = name;
    }

    public com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheet getFaultSheet() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FaultSheet_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFaultSheet(endpoint);
    }

    public com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheet getFaultSheet(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub _stub = new com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub(portAddress, this);
            _stub.setPortName(getFaultSheetWSDDServiceName());
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
            if (com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheet.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub _stub = new com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub(new java.net.URL(FaultSheet_address), this);
                _stub.setPortName(getFaultSheetWSDDServiceName());
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
        if ("FaultSheet".equals(inputPortName)) {
            return getFaultSheet();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://group.interfaces.eoms.boco.com", "FaultSheetService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("FaultSheet"));
        }
        return ports.iterator();
    }

}
