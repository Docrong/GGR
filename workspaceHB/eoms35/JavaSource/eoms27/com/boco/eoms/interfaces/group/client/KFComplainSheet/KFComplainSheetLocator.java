/**
 * KFComplainSheetLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.group.client.KFComplainSheet;

public class KFComplainSheetLocator extends org.apache.axis.client.Service implements com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheet {

    // Use to get a proxy class for KFComplainSheetSoap
    private final java.lang.String KFComplainSheetSoap_address = "http://135.11.50.55/adminservice/KFComplainSheet.asmx";

    public java.lang.String getKFComplainSheetSoapAddress() {
        return KFComplainSheetSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String KFComplainSheetSoapWSDDServiceName = "KFComplainSheetSoap";

    public java.lang.String getKFComplainSheetSoapWSDDServiceName() {
        return KFComplainSheetSoapWSDDServiceName;
    }

    public void setKFComplainSheetSoapWSDDServiceName(java.lang.String name) {
        KFComplainSheetSoapWSDDServiceName = name;
    }

    public com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoap getKFComplainSheetSoap() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(KFComplainSheetSoap_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getKFComplainSheetSoap(endpoint);
    }

    public com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoap getKFComplainSheetSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoapStub _stub = new com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoapStub(portAddress, this);
            _stub.setPortName(getKFComplainSheetSoapWSDDServiceName());
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
            if (com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoapStub _stub = new com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoapStub(new java.net.URL(KFComplainSheetSoap_address), this);
                _stub.setPortName(getKFComplainSheetSoapWSDDServiceName());
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
        if ("KFComplainSheetSoap".equals(inputPortName)) {
            return getKFComplainSheetSoap();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "KFComplainSheet");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("KFComplainSheetSoap"));
        }
        return ports.iterator();
    }

}
