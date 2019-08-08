/**
 * IRMSServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf170819.19 v52708210711
 */

package com.boco.eoms.interfaces.EOMSService.client;

public class IRMSServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, com.boco.eoms.interfaces.EOMSService.client.IRMSService {

    public IRMSServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms",
                "IRMSService"));

        context.setLocatorName("com.boco.eoms.interfaces.EOMSService.client.IRMSServiceLocator");
    }

    public IRMSServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("com.boco.eoms.interfaces.EOMSService.client.IRMSServiceLocator");
    }

    // ���ڻ�ȡ IRMSService �Ĵ�����
    private final java.lang.String IRMSService_address = "http://localhost:19090/transnms/services/IRMSService";

    public java.lang.String getIRMSServiceAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return IRMSService_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("IRMSService");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        } else {
            return IRMSService_address;
        }
    }

    private java.lang.String IRMSServicePortName = "IRMSService";

    // The WSDD port name defaults to the port name.
    private java.lang.String IRMSServiceWSDDPortName = "IRMSService";

    public java.lang.String getIRMSServiceWSDDPortName() {
        return IRMSServiceWSDDPortName;
    }

    public void setIRMSServiceWSDDPortName(java.lang.String name) {
        IRMSServiceWSDDPortName = name;
    }

    public com.boco.eoms.interfaces.EOMSService.client.IAttempXToEoms getIRMSService() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getIRMSServiceAddress());
        } catch (java.net.MalformedURLException e) {
            return null; // ��̫���ܣ���Ϊ URL ���� WSDL2Java �еõ���֤
        }
        return getIRMSService(endpoint);
    }

    public com.boco.eoms.interfaces.EOMSService.client.IAttempXToEoms getIRMSService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        com.boco.eoms.interfaces.EOMSService.client.IAttempXToEoms _stub =
                (com.boco.eoms.interfaces.EOMSService.client.IAttempXToEoms) getStub(
                        IRMSServicePortName,
                        (String) getPort2NamespaceMap().get(IRMSServicePortName),
                        com.boco.eoms.interfaces.EOMSService.client.IAttempXToEoms.class,
                        "com.boco.eoms.interfaces.EOMSService.client.IRMSServiceSoapBindingStub",
                        portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(IRMSServiceWSDDPortName);
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
            if (com.boco.eoms.interfaces.EOMSService.client.IAttempXToEoms.class.isAssignableFrom(serviceEndpointInterface)) {
                return getIRMSService();
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
        if ("IRMSService".equals(inputPortName)) {
            return getIRMSService();
        } else {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        IRMSServiceWSDDPortName = prefix + "/" + IRMSServicePortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IRMSService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
                    "IRMSService",
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
        if (portName.getLocalPart().equals("IRMSService")) {
            return new javax.xml.rpc.Call[]{
                    createCall(portName, "submitReplySheet", "submitReplySheetRequest"),
                    createCall(portName, "putBusinessData", "putBusinessDataRequest"),
                    createCall(portName, "deleteSheet", "deleteSheetRequest"),
                    createCall(portName, "setCheck", "setCheckRequest"),
                    createCall(portName, "getDeptIds", "getDeptIdsRequest"),
                    createCall(portName, "getExcelData", "getExcelDataRequest"),
            };
        } else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: ����portName ��ӦΪ null��");
        }
    }
}
