/**
 * EOMSComplainSheetServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.kf.client;

public class EOMSComplainSheetServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.interfaces.kf.client.EOMSComplainSheetService {

    // Use to get a proxy class for EOMSComplainSheet
    private final java.lang.String EOMSComplainSheet_address = "http://localhost:8080/EOMS_J2EE/services/EOMSComplainSheet";

    public java.lang.String getEOMSComplainSheetAddress() {
        return EOMSComplainSheet_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EOMSComplainSheetWSDDServiceName = "EOMSComplainSheet";

    public java.lang.String getEOMSComplainSheetWSDDServiceName() {
        return EOMSComplainSheetWSDDServiceName;
    }

    public void setEOMSComplainSheetWSDDServiceName(java.lang.String name) {
        EOMSComplainSheetWSDDServiceName = name;
    }

    public com.boco.eoms.interfaces.kf.client.EOMSComplainSheet getEOMSComplainSheet() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EOMSComplainSheet_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEOMSComplainSheet(endpoint);
    }

    public com.boco.eoms.interfaces.kf.client.EOMSComplainSheet getEOMSComplainSheet(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub _stub = new com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub(portAddress, this);
            _stub.setPortName(getEOMSComplainSheetWSDDServiceName());
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
            if (com.boco.eoms.interfaces.kf.client.EOMSComplainSheet.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub _stub = new com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub(new java.net.URL(EOMSComplainSheet_address), this);
                _stub.setPortName(getEOMSComplainSheetWSDDServiceName());
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
        if ("EOMSComplainSheet".equals(inputPortName)) {
            return getEOMSComplainSheet();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://kf.interfaces.eoms.boco.com", "EOMSComplainSheetService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("EOMSComplainSheet"));
        }
        return ports.iterator();
    }

}
