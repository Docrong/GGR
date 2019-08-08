/**
 * BulletinLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.group.client.Bulletin;

public class BulletinLocator extends org.apache.axis.client.Service implements com.boco.eoms.interfaces.group.client.Bulletin.Bulletin {

    // Use to get a proxy class for BulletinSoap
    private final java.lang.String BulletinSoap_address = "http://135.11.50.55/adminservice/Bulletin.asmx";

    public java.lang.String getBulletinSoapAddress() {
        return BulletinSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BulletinSoapWSDDServiceName = "BulletinSoap";

    public java.lang.String getBulletinSoapWSDDServiceName() {
        return BulletinSoapWSDDServiceName;
    }

    public void setBulletinSoapWSDDServiceName(java.lang.String name) {
        BulletinSoapWSDDServiceName = name;
    }

    public com.boco.eoms.interfaces.group.client.Bulletin.BulletinSoap getBulletinSoap() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BulletinSoap_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBulletinSoap(endpoint);
    }

    public com.boco.eoms.interfaces.group.client.Bulletin.BulletinSoap getBulletinSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.interfaces.group.client.Bulletin.BulletinSoapStub _stub = new com.boco.eoms.interfaces.group.client.Bulletin.BulletinSoapStub(portAddress, this);
            _stub.setPortName(getBulletinSoapWSDDServiceName());
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
            if (com.boco.eoms.interfaces.group.client.Bulletin.BulletinSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.interfaces.group.client.Bulletin.BulletinSoapStub _stub = new com.boco.eoms.interfaces.group.client.Bulletin.BulletinSoapStub(new java.net.URL(BulletinSoap_address), this);
                _stub.setPortName(getBulletinSoapWSDDServiceName());
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
        if ("BulletinSoap".equals(inputPortName)) {
            return getBulletinSoap();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "Bulletin");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("BulletinSoap"));
        }
        return ports.iterator();
    }

}
