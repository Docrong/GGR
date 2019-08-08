/**
 * CRMProcessSheetLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.huawei.boss.wsinterface.gate;

public class CRMProcessSheetLocator extends org.apache.axis.client.Service implements com.huawei.boss.wsinterface.gate.CRMProcessSheet {

    // Use to get a proxy class for CRMProcessSheetHttpSoap12Endpoint
    private final java.lang.String CRMProcessSheetHttpSoap12Endpoint_address = "http://10.25.5.144:9006/interface/services/CRMProcessSheet.CRMProcessSheetHttpSoap12Endpoint";

    public java.lang.String getCRMProcessSheetHttpSoap12EndpointAddress() {
        return CRMProcessSheetHttpSoap12Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CRMProcessSheetHttpSoap12EndpointWSDDServiceName = "CRMProcessSheetHttpSoap12Endpoint";

    public java.lang.String getCRMProcessSheetHttpSoap12EndpointWSDDServiceName() {
        return CRMProcessSheetHttpSoap12EndpointWSDDServiceName;
    }

    public void setCRMProcessSheetHttpSoap12EndpointWSDDServiceName(java.lang.String name) {
        CRMProcessSheetHttpSoap12EndpointWSDDServiceName = name;
    }

    public com.huawei.boss.wsinterface.gate.CRMProcessSheetPortType getCRMProcessSheetHttpSoap12Endpoint() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CRMProcessSheetHttpSoap12Endpoint_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCRMProcessSheetHttpSoap12Endpoint(endpoint);
    }

    public com.huawei.boss.wsinterface.gate.CRMProcessSheetPortType getCRMProcessSheetHttpSoap12Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap12BindingStub _stub = new com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap12BindingStub(portAddress, this);
            _stub.setPortName(getCRMProcessSheetHttpSoap12EndpointWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }


    // Use to get a proxy class for CRMProcessSheetHttpSoap11Endpoint
    private final java.lang.String CRMProcessSheetHttpSoap11Endpoint_address = "http://10.25.5.144:9006/interface/services/CRMProcessSheet.CRMProcessSheetHttpSoap11Endpoint";

    public java.lang.String getCRMProcessSheetHttpSoap11EndpointAddress() {
        return CRMProcessSheetHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CRMProcessSheetHttpSoap11EndpointWSDDServiceName = "CRMProcessSheetHttpSoap11Endpoint";

    public java.lang.String getCRMProcessSheetHttpSoap11EndpointWSDDServiceName() {
        return CRMProcessSheetHttpSoap11EndpointWSDDServiceName;
    }

    public void setCRMProcessSheetHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        CRMProcessSheetHttpSoap11EndpointWSDDServiceName = name;
    }

    public com.huawei.boss.wsinterface.gate.CRMProcessSheetPortType getCRMProcessSheetHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CRMProcessSheetHttpSoap11Endpoint_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCRMProcessSheetHttpSoap11Endpoint(endpoint);
    }

    public com.huawei.boss.wsinterface.gate.CRMProcessSheetPortType getCRMProcessSheetHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap11BindingStub _stub = new com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap11BindingStub(portAddress, this);
            _stub.setPortName(getCRMProcessSheetHttpSoap11EndpointWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.huawei.boss.wsinterface.gate.CRMProcessSheetPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap12BindingStub _stub = new com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap12BindingStub(new java.net.URL(CRMProcessSheetHttpSoap12Endpoint_address), this);
                _stub.setPortName(getCRMProcessSheetHttpSoap12EndpointWSDDServiceName());
                return _stub;
            }
            if (com.huawei.boss.wsinterface.gate.CRMProcessSheetPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap11BindingStub _stub = new com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap11BindingStub(new java.net.URL(CRMProcessSheetHttpSoap11Endpoint_address), this);
                _stub.setPortName(getCRMProcessSheetHttpSoap11EndpointWSDDServiceName());
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
        if ("CRMProcessSheetHttpSoap12Endpoint".equals(inputPortName)) {
            return getCRMProcessSheetHttpSoap12Endpoint();
        } else if ("CRMProcessSheetHttpSoap11Endpoint".equals(inputPortName)) {
            return getCRMProcessSheetHttpSoap11Endpoint();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://gate.wsinterface.boss.huawei.com", "CRMProcessSheet");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("CRMProcessSheetHttpSoap12Endpoint"));
            ports.add(new javax.xml.namespace.QName("CRMProcessSheetHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

}
