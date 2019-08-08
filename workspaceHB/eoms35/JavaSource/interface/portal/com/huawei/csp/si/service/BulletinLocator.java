/**
 * BulletinLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.huawei.csp.si.service;

import com.boco.eoms.util.InterfaceUtilVariable;
import com.boco.eoms.util.BulletinMgrLocator;

public class BulletinLocator extends org.apache.axis.client.Service implements com.huawei.csp.si.service.Bulletin {

    // Use to get a proxy class for BulletinHttpPort

    private final java.lang.String BulletinHttpPort_address = BulletinMgrLocator.getAttributes().getBulletinHttpPortAddress();

    public java.lang.String getBulletinHttpPortAddress() {
        return BulletinHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BulletinHttpPortWSDDServiceName = "BulletinHttpPort";

    public java.lang.String getBulletinHttpPortWSDDServiceName() {
        return BulletinHttpPortWSDDServiceName;
    }

    public void setBulletinHttpPortWSDDServiceName(java.lang.String name) {
        BulletinHttpPortWSDDServiceName = name;
    }

    public com.huawei.csp.si.service.BulletinPortType getBulletinHttpPort() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BulletinHttpPort_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBulletinHttpPort(endpoint);
    }

    public com.huawei.csp.si.service.BulletinPortType getBulletinHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.huawei.csp.si.service.BulletinHttpBindingStub _stub = new com.huawei.csp.si.service.BulletinHttpBindingStub(portAddress, this);
            _stub.setPortName(getBulletinHttpPortWSDDServiceName());
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
            if (com.huawei.csp.si.service.BulletinPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.huawei.csp.si.service.BulletinHttpBindingStub _stub = new com.huawei.csp.si.service.BulletinHttpBindingStub(new java.net.URL(BulletinHttpPort_address), this);
                _stub.setPortName(getBulletinHttpPortWSDDServiceName());
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
        if ("BulletinHttpPort".equals(inputPortName)) {
            return getBulletinHttpPort();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.si.csp.huawei.com", "Bulletin");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("BulletinHttpPort"));
        }
        return ports.iterator();
    }

}
