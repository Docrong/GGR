/**
 * AddKnowledgeServiceServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf170819.19 v52708210711
 */

package krms;

public class AddKnowledgeServiceServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, krms.AddKnowledgeServiceService {

    public AddKnowledgeServiceServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                "http://krms/services/knowledgeService",
                "AddKnowledgeServiceService"));

        context.setLocatorName("krms.AddKnowledgeServiceServiceLocator");
    }

    public AddKnowledgeServiceServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("krms.AddKnowledgeServiceServiceLocator");
    }

    // ���ڻ�ȡ knowledgeService �Ĵ�����
    private final java.lang.String knowledgeService_address = "http://krms/services/knowledgeService";

    public java.lang.String getKnowledgeServiceAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return knowledgeService_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("knowledgeService");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        } else {
            return knowledgeService_address;
        }
    }

    private java.lang.String knowledgeServicePortName = "knowledgeService";

    // The WSDD port name defaults to the port name.
    private java.lang.String knowledgeServiceWSDDPortName = "knowledgeService";

    public java.lang.String getKnowledgeServiceWSDDPortName() {
        return knowledgeServiceWSDDPortName;
    }

    public void setKnowledgeServiceWSDDPortName(java.lang.String name) {
        knowledgeServiceWSDDPortName = name;
    }

    public krms.AddKnowledgeService getKnowledgeService() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getKnowledgeServiceAddress());
        } catch (java.net.MalformedURLException e) {
            return null; // ��̫���ܣ���Ϊ URL ���� WSDL2Java �еõ���֤
        }
        return getKnowledgeService(endpoint);
    }

    public krms.AddKnowledgeService getKnowledgeService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        krms.AddKnowledgeService _stub =
                (krms.AddKnowledgeService) getStub(
                        knowledgeServicePortName,
                        (String) getPort2NamespaceMap().get(knowledgeServicePortName),
                        krms.AddKnowledgeService.class,
                        "krms.KnowledgeServiceSoapBindingStub",
                        portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(knowledgeServiceWSDDPortName);
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
            if (krms.AddKnowledgeService.class.isAssignableFrom(serviceEndpointInterface)) {
                return getKnowledgeService();
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
        if ("knowledgeService".equals(inputPortName)) {
            return getKnowledgeService();
        } else {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        knowledgeServiceWSDDPortName = prefix + "/" + knowledgeServicePortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "AddKnowledgeServiceService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
                    "knowledgeService",
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
        if (portName.getLocalPart().equals("knowledgeService")) {
            return new javax.xml.rpc.Call[]{
                    createCall(portName, "getKnowledgeBySheetIds", "getKnowledgeBySheetIdsRequest"),
                    createCall(portName, "saveXmlValue", "saveXmlValueRequest"),
            };
        } else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: ����portName ��ӦΪ null��");
        }
    }
}
