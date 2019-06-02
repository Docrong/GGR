/**
 * SynchroniseUserServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf130745.06 v111407041203
 */

package krms;

public class SynchroniseUserServiceServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, krms.SynchroniseUserServiceService {

    public SynchroniseUserServiceServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://krms/services/synUserService",
           "SynchroniseUserServiceService"));

        context.setLocatorName("krms.SynchroniseUserServiceServiceLocator");
    }

    public SynchroniseUserServiceServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("krms.SynchroniseUserServiceServiceLocator");
    }

    // 用于获取 synUserService 的代理类
    private final java.lang.String synUserService_address = "http://krms/services/synUserService";

    public java.lang.String getSynUserServiceAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return synUserService_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("synUserService");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return synUserService_address;
        }
    }

    private java.lang.String synUserServicePortName = "synUserService";

    // The WSDD port name defaults to the port name.
    private java.lang.String synUserServiceWSDDPortName = "synUserService";

    public java.lang.String getSynUserServiceWSDDPortName() {
        return synUserServiceWSDDPortName;
    }

    public void setSynUserServiceWSDDPortName(java.lang.String name) {
        synUserServiceWSDDPortName = name;
    }

    public krms.SynchroniseUserService getSynUserService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getSynUserServiceAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // 不太可能，因为 URL 已在 WSDL2Java 中得到验证
        }
        return getSynUserService(endpoint);
    }

    public krms.SynchroniseUserService getSynUserService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        krms.SynchroniseUserService _stub =
            (krms.SynchroniseUserService) getStub(
                synUserServicePortName,
                (String) getPort2NamespaceMap().get(synUserServicePortName),
                krms.SynchroniseUserService.class,
                "krms.SynUserServiceSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(synUserServiceWSDDPortName);
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
            if (krms.SynchroniseUserService.class.isAssignableFrom(serviceEndpointInterface)) {
                return getSynUserService();
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
        if ("synUserService".equals(inputPortName)) {
            return getSynUserService();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        synUserServiceWSDDPortName = prefix + "/" + synUserServicePortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/synUserService", "SynchroniseUserServiceService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "synUserService",
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
        if  (portName.getLocalPart().equals("synUserService")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "synchroniseUser", "synchroniseUserRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: 错误：portName 不应为 null。");
        }
    }
}
