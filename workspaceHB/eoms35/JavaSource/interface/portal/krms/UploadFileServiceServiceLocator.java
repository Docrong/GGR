/**
 * UploadFileServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf130745.06 v111407041203
 */

package krms;

public class UploadFileServiceServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, krms.UploadFileServiceService {

    public UploadFileServiceServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://krms/services/uploadFileService",
           "UploadFileServiceService"));

        context.setLocatorName("krms.UploadFileServiceServiceLocator");
    }

    public UploadFileServiceServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("krms.UploadFileServiceServiceLocator");
    }

    // 用于获取 uploadFileService 的代理类
    private final java.lang.String uploadFileService_address = "http://krms/services/uploadFileService";

    public java.lang.String getUploadFileServiceAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return uploadFileService_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("uploadFileService");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return uploadFileService_address;
        }
    }

    private java.lang.String uploadFileServicePortName = "uploadFileService";

    // The WSDD port name defaults to the port name.
    private java.lang.String uploadFileServiceWSDDPortName = "uploadFileService";

    public java.lang.String getUploadFileServiceWSDDPortName() {
        return uploadFileServiceWSDDPortName;
    }

    public void setUploadFileServiceWSDDPortName(java.lang.String name) {
        uploadFileServiceWSDDPortName = name;
    }

    public krms.UploadFileService getUploadFileService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getUploadFileServiceAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // 不太可能，因为 URL 已在 WSDL2Java 中得到验证
        }
        return getUploadFileService(endpoint);
    }

    public krms.UploadFileService getUploadFileService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        krms.UploadFileService _stub =
            (krms.UploadFileService) getStub(
                uploadFileServicePortName,
                (String) getPort2NamespaceMap().get(uploadFileServicePortName),
                krms.UploadFileService.class,
                "krms.UploadFileServiceSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(uploadFileServiceWSDDPortName);
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
            if (krms.UploadFileService.class.isAssignableFrom(serviceEndpointInterface)) {
                return getUploadFileService();
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
        if ("uploadFileService".equals(inputPortName)) {
            return getUploadFileService();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        uploadFileServiceWSDDPortName = prefix + "/" + uploadFileServicePortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/uploadFileService", "UploadFileServiceService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "uploadFileService",
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
        if  (portName.getLocalPart().equals("uploadFileService")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "uploadByWebservice", "uploadByWebserviceRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: 错误：portName 不应为 null。");
        }
    }
}
