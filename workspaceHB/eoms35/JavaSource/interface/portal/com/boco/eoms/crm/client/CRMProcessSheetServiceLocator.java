/**
 * CRMProcessSheetServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf170819.19 v52708210711
 */

package com.boco.eoms.crm.client;

public class CRMProcessSheetServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, com.boco.eoms.crm.client.CRMProcessSheetService {

    public CRMProcessSheetServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                "http://client.crm.eoms.boco.com/services/CRMProcessSheet",
                "CRMProcessSheetService"));

        context.setLocatorName("com.boco.eoms.crm.client.CRMProcessSheetServiceLocator");
    }

    public CRMProcessSheetServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("com.boco.eoms.crm.client.CRMProcessSheetServiceLocator");
    }

    // ���ڻ�ȡ CRMProcessSheet �Ĵ�����
    private final java.lang.String CRMProcessSheet_address = "http://client.crm.eoms.boco.com/services/CRMProcessSheet";

    public java.lang.String getCRMProcessSheetAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return CRMProcessSheet_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("CRMProcessSheet");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        } else {
            return CRMProcessSheet_address;
        }
    }

    private java.lang.String CRMProcessSheetPortName = "CRMProcessSheet";

    // The WSDD port name defaults to the port name.
    private java.lang.String CRMProcessSheetWSDDPortName = "CRMProcessSheet";

    public java.lang.String getCRMProcessSheetWSDDPortName() {
        return CRMProcessSheetWSDDPortName;
    }

    public void setCRMProcessSheetWSDDPortName(java.lang.String name) {
        CRMProcessSheetWSDDPortName = name;
    }

    public com.boco.eoms.crm.client.CRMProcessSheet getCRMProcessSheet() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getCRMProcessSheetAddress());
        } catch (java.net.MalformedURLException e) {
            return null; // ��̫���ܣ���Ϊ URL ���� WSDL2Java �еõ���֤
        }
        return getCRMProcessSheet(endpoint);
    }

    public com.boco.eoms.crm.client.CRMProcessSheet getCRMProcessSheet(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        com.boco.eoms.crm.client.CRMProcessSheet _stub =
                (com.boco.eoms.crm.client.CRMProcessSheet) getStub(
                        CRMProcessSheetPortName,
                        (String) getPort2NamespaceMap().get(CRMProcessSheetPortName),
                        com.boco.eoms.crm.client.CRMProcessSheet.class,
                        "com.boco.eoms.crm.client.CRMProcessSheetSoapBindingStub",
                        portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(CRMProcessSheetWSDDPortName);
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
            if (com.boco.eoms.crm.client.CRMProcessSheet.class.isAssignableFrom(serviceEndpointInterface)) {
                return getCRMProcessSheet();
            }
        } catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("WSWS3273E: ���󣺽ӿ�û�д��ʵ�֣�  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        String inputPortName = portName.getLocalPart();
        if ("CRMProcessSheet".equals(inputPortName)) {
            return getCRMProcessSheet();
        } else {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        CRMProcessSheetWSDDPortName = prefix + "/" + CRMProcessSheetPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheetService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
                    "CRMProcessSheet",
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
            throw new javax.xml.rpc.ServiceException("WSWS3062E: ����portName ��ӦΪ null��");
        }
        if (portName.getLocalPart().equals("CRMProcessSheet")) {
            return new javax.xml.rpc.Call[]{
                    createCall(portName, "isAlive", "isAliveRequest"),
                    createCall(portName, "confirmWorkSheet", "confirmWorkSheetRequest"),
                    createCall(portName, "notifyWorkSheet", "notifyWorkSheetRequest"),
                    createCall(portName, "replyWorkSheet", "replyWorkSheetRequest"),
                    createCall(portName, "withdrawWorkSheet", "withdrawWorkSheetRequest"),
            };
        } else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: ����portName ��ӦΪ null��");
        }
    }
}
