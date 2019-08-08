package com.boco.eoms.knowledge.client;

public class KnowledgeSendServiceProxy implements com.boco.eoms.knowledge.client.KnowledgeSendService {
    private boolean _useJNDI = true;
    private String _endpoint = null;
    private com.boco.eoms.knowledge.client.KnowledgeSendService __knowledgeSendService = null;

    public KnowledgeSendServiceProxy() {
        _initKnowledgeSendServiceProxy();
    }

    private void _initKnowledgeSendServiceProxy() {

        if (_useJNDI) {
            try {
                javax.naming.InitialContext ctx = new javax.naming.InitialContext();
                __knowledgeSendService = ((com.boco.eoms.knowledge.client.KnowledgeSendServiceService) ctx.lookup("java:comp/env/service/KnowledgeSendServiceService")).getKnowledgeSendService();
            } catch (javax.naming.NamingException namingException) {
            } catch (javax.xml.rpc.ServiceException serviceException) {
            }
        }
        if (__knowledgeSendService == null) {
            try {
                __knowledgeSendService = (new com.boco.eoms.knowledge.client.KnowledgeSendServiceServiceLocator()).getKnowledgeSendService();

            } catch (javax.xml.rpc.ServiceException serviceException) {
            }
        }
        if (__knowledgeSendService != null) {
            if (_endpoint != null)
                ((javax.xml.rpc.Stub) __knowledgeSendService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
            else
                _endpoint = (String) ((javax.xml.rpc.Stub) __knowledgeSendService)._getProperty("javax.xml.rpc.service.endpoint.address");
        }

    }


    public void useJNDI(boolean useJNDI) {
        _useJNDI = useJNDI;
        __knowledgeSendService = null;

    }

    public String getEndpoint() {
        return _endpoint;
    }

    public void setEndpoint(String endpoint) {
        _endpoint = endpoint;
        if (__knowledgeSendService != null)
            ((javax.xml.rpc.Stub) __knowledgeSendService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

    }

    public com.boco.eoms.knowledge.client.KnowledgeSendService getKnowledgeSendService() {
        if (__knowledgeSendService == null)
            _initKnowledgeSendServiceProxy();
        return __knowledgeSendService;
    }

    public void main(java.lang.String[] args) throws java.rmi.RemoteException {
        if (__knowledgeSendService == null)
            _initKnowledgeSendServiceProxy();
        __knowledgeSendService.main(args);
    }

    public java.lang.String saveXmlValue(java.lang.String xmlString) throws java.rmi.RemoteException {
        if (__knowledgeSendService == null)
            _initKnowledgeSendServiceProxy();
        return __knowledgeSendService.saveXmlValue(xmlString);
    }

    public java.lang.String searchXmlValue(java.lang.String xmlString) throws java.rmi.RemoteException {
        if (__knowledgeSendService == null)
            _initKnowledgeSendServiceProxy();
        return __knowledgeSendService.searchXmlValue(xmlString);
    }


}