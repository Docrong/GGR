/**
 * SheetStateSync3Locator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf130745.06 v111407041203
 */

package com.chinamobile.eoms.service;

public class SheetStateSync3Locator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, com.chinamobile.eoms.service.SheetStateSync3 {

    public SheetStateSync3Locator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://service.eoms.chinamobile.com/SheetStateSync",
           "SheetStateSync3"));

        context.setLocatorName("com.chinamobile.eoms.service.SheetStateSync3Locator");
    }

    public SheetStateSync3Locator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("com.chinamobile.eoms.service.SheetStateSync3Locator");
    }

    // 用于获取 sheetStateSync3Soap 的代理类
    private final java.lang.String sheetStateSync3Soap_address = "http://localhost/SheetStateSync/SheetStateSync3.asmx";

    public java.lang.String getSheetStateSync3SoapAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return sheetStateSync3Soap_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("SheetStateSync3Soap");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return sheetStateSync3Soap_address;
        }
    }

    private java.lang.String sheetStateSync3SoapPortName = "SheetStateSync3Soap";

    // The WSDD port name defaults to the port name.
    private java.lang.String sheetStateSync3SoapWSDDPortName = "SheetStateSync3Soap";

    public java.lang.String getSheetStateSync3SoapWSDDPortName() {
        return sheetStateSync3SoapWSDDPortName;
    }

    public void setSheetStateSync3SoapWSDDPortName(java.lang.String name) {
        sheetStateSync3SoapWSDDPortName = name;
    }

    public com.chinamobile.eoms.service.SheetStateSync3Soap getSheetStateSync3Soap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getSheetStateSync3SoapAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // 不太可能，因为 URL 已在 WSDL2Java 中得到验证
        }
        return getSheetStateSync3Soap(endpoint);
    }

    public com.chinamobile.eoms.service.SheetStateSync3Soap getSheetStateSync3Soap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        com.chinamobile.eoms.service.SheetStateSync3Soap _stub =
            (com.chinamobile.eoms.service.SheetStateSync3Soap) getStub(
                sheetStateSync3SoapPortName,
                (String) getPort2NamespaceMap().get(sheetStateSync3SoapPortName),
                com.chinamobile.eoms.service.SheetStateSync3Soap.class,
                "com.chinamobile.eoms.service.SheetStateSync3SoapStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(sheetStateSync3SoapWSDDPortName);
        }
        return _stub;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.chinamobile.eoms.service.SheetStateSync3Soap.class.isAssignableFrom(serviceEndpointInterface)) {
                return getSheetStateSync3Soap();
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("WSWS3273E: 错误：接口没有存根实现：  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        String inputPortName = portName.getLocalPart();
        if ("SheetStateSync3Soap".equals(inputPortName)) {
            return getSheetStateSync3Soap();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        sheetStateSync3SoapWSDDPortName = prefix + "/" + sheetStateSync3SoapPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "SheetStateSync3");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "SheetStateSync3Soap",
               "http://schemas.xmlsoap.org/wsdl/soap/");
        }
        return port2NamespaceMap;
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            String serviceNamespace = getServiceName().getNamespaceURI();
            for (java.util.Iterator i = getPort2NamespaceMap().keySet().iterator(); i.hasNext(); ) {
                ports.add(
                    com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                        serviceNamespace,
                        (String) i.next()));
            }
        }
        return ports.iterator();
    }

    public javax.xml.rpc.Call[] getCalls(javax.xml.namespace.QName portName) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: 错误：portName 不应为 null。");
        }
        if  (portName.getLocalPart().equals("SheetStateSync3Soap")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "isAlive", "null"),
                createCall(portName, "syncSheetState", "null"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: 错误：portName 不应为 null。");
        }
    }
}
