/**
 * KnowledgeSendServiceServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf170819.19 v52708210711
 */

package com.boco.eoms.knowledge.client;

public class KnowledgeSendServiceServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, com.boco.eoms.knowledge.client.KnowledgeSendServiceService {

    public KnowledgeSendServiceServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                "http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService",
                "KnowledgeSendServiceService"));

        context.setLocatorName("com.boco.eoms.knowledge.client.KnowledgeSendServiceServiceLocator");
    }

    public KnowledgeSendServiceServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("com.boco.eoms.knowledge.client.KnowledgeSendServiceServiceLocator");
    }

    // 用于获取 knowledgeSendService 的代理类
    private final java.lang.String knowledgeSendService_address = "http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService";

    public java.lang.String getKnowledgeSendServiceAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return knowledgeSendService_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("KnowledgeSendService");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        } else {
            return knowledgeSendService_address;
        }
    }

    private java.lang.String knowledgeSendServicePortName = "KnowledgeSendService";

    // The WSDD port name defaults to the port name.
    private java.lang.String knowledgeSendServiceWSDDPortName = "KnowledgeSendService";

    public java.lang.String getKnowledgeSendServiceWSDDPortName() {
        return knowledgeSendServiceWSDDPortName;
    }

    public void setKnowledgeSendServiceWSDDPortName(java.lang.String name) {
        knowledgeSendServiceWSDDPortName = name;
    }

    public com.boco.eoms.knowledge.client.KnowledgeSendService getKnowledgeSendService() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getKnowledgeSendServiceAddress());
        } catch (java.net.MalformedURLException e) {
            return null; // 不太可能，因为 URL 已在 WSDL2Java 中得到验证
        }
        return getKnowledgeSendService(endpoint);
    }

    public com.boco.eoms.knowledge.client.KnowledgeSendService getKnowledgeSendService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        com.boco.eoms.knowledge.client.KnowledgeSendService _stub =
                (com.boco.eoms.knowledge.client.KnowledgeSendService) getStub(
                        knowledgeSendServicePortName,
                        (String) getPort2NamespaceMap().get(knowledgeSendServicePortName),
                        com.boco.eoms.knowledge.client.KnowledgeSendService.class,
                        "com.boco.eoms.knowledge.client.KnowledgeSendServiceSoapBindingStub",
                        portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(knowledgeSendServiceWSDDPortName);
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
            if (com.boco.eoms.knowledge.client.KnowledgeSendService.class.isAssignableFrom(serviceEndpointInterface)) {
                return getKnowledgeSendService();
            }
        } catch (java.lang.Throwable t) {
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
        if ("KnowledgeSendService".equals(inputPortName)) {
            return getKnowledgeSendService();
        } else {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        knowledgeSendServiceWSDDPortName = prefix + "/" + knowledgeSendServicePortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "KnowledgeSendServiceService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
                    "KnowledgeSendService",
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
        if (portName.getLocalPart().equals("KnowledgeSendService")) {
            return new javax.xml.rpc.Call[]{
                    createCall(portName, "main", "mainRequest"),
                    createCall(portName, "saveXmlValue", "saveXmlValueRequest"),
                    createCall(portName, "searchXmlValue", "searchXmlValueRequest"),
            };
        } else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: 错误：portName 不应为 null。");
        }
    }
}
