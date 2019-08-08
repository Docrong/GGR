/**
 * KnowledgeSendServiceSoapBindingStub.java
 * <p>
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf170819.19 v52708210711
 */

package com.boco.eoms.knowledge.client;

public class KnowledgeSendServiceSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements com.boco.eoms.knowledge.client.KnowledgeSendService {
    public KnowledgeSendServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        } else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        initTypeMapping();
        super.cachedEndpoint = endpointURL;
        super.connection = ((com.ibm.ws.webservices.engine.client.Service) super.service).getConnection(endpointURL);
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[3];
    }

    private void initTypeMapping() {
        javax.xml.rpc.encoding.TypeMapping tm = super.getTypeMapping(com.ibm.ws.webservices.engine.Constants.URI_SOAP11_ENC);
        java.lang.Class javaType = null;
        javax.xml.namespace.QName xmlType = null;
        javax.xml.namespace.QName compQName = null;
        javax.xml.namespace.QName compTypeQName = null;
        com.ibm.ws.webservices.engine.encoding.SerializerFactory sf = null;
        com.ibm.ws.webservices.engine.encoding.DeserializerFactory df = null;
        javaType = java.lang.String[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "ArrayOf_soapenc_string");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, null, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, null, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _mainOperation0 = null;

    private static com.ibm.ws.webservices.engine.description.OperationDesc _getmainOperation0() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "args"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "ArrayOf_soapenc_string"), java.lang.String[].class, false, false, false, false, true, false),
        };
        _params0[0].setOption("partQNameString", "{http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService}ArrayOf_soapenc_string");
        _params0[0].setOption("partName", "ArrayOf_soapenc_string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(null, com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://websphere.ibm.com/webservices/", "Void"), void.class, true, false, false, false, true, true);
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        _mainOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("main", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://webservice.interfaces.km.eoms.boco.com", "main"), _params0, _returnDesc0, _faults0, "");
        _mainOperation0.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "mainRequest"));
        _mainOperation0.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "KnowledgeSendServiceService"));
        _mainOperation0.setOption("inoutOrderingReq", "false");
        _mainOperation0.setOption("inputName", "mainRequest");
        _mainOperation0.setOption("inputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        _mainOperation0.setOption("ResponseNamespace", "http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService");
        _mainOperation0.setOption("buildNum", "cf170819.19");
        _mainOperation0.setOption("outputName", "mainResponse");
        _mainOperation0.setOption("targetNamespace", "http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService");
        _mainOperation0.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "mainResponse"));
        _mainOperation0.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "KnowledgeSendService"));
        _mainOperation0.setOption("outputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        _mainOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.ENCODED);
        _mainOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return _mainOperation0;

    }

    private int _mainIndex0 = 0;

    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getmainInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_mainIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(KnowledgeSendServiceSoapBindingStub._mainOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            super.primeMessageContext(mc);
            super.messageContexts[_mainIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        } catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public void main(java.lang.String[] args) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        try {
            _getmainInvoke0(new java.lang.Object[]{args}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        }
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _saveXmlValueOperation1 = null;

    private static com.ibm.ws.webservices.engine.description.OperationDesc _getsaveXmlValueOperation1() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params1 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "xmlString"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params1[0].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params1[0].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc1 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "saveXmlValueReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc1.setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc1.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults1 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        _saveXmlValueOperation1 = new com.ibm.ws.webservices.engine.description.OperationDesc("saveXmlValue", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://webservice.interfaces.km.eoms.boco.com", "saveXmlValue"), _params1, _returnDesc1, _faults1, "");
        _saveXmlValueOperation1.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "saveXmlValueRequest"));
        _saveXmlValueOperation1.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "KnowledgeSendServiceService"));
        _saveXmlValueOperation1.setOption("inoutOrderingReq", "false");
        _saveXmlValueOperation1.setOption("inputName", "saveXmlValueRequest");
        _saveXmlValueOperation1.setOption("inputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        _saveXmlValueOperation1.setOption("ResponseNamespace", "http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService");
        _saveXmlValueOperation1.setOption("buildNum", "cf170819.19");
        _saveXmlValueOperation1.setOption("outputName", "saveXmlValueResponse");
        _saveXmlValueOperation1.setOption("targetNamespace", "http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService");
        _saveXmlValueOperation1.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "saveXmlValueResponse"));
        _saveXmlValueOperation1.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "KnowledgeSendService"));
        _saveXmlValueOperation1.setOption("outputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        _saveXmlValueOperation1.setUse(com.ibm.ws.webservices.engine.enumtype.Use.ENCODED);
        _saveXmlValueOperation1.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return _saveXmlValueOperation1;

    }

    private int _saveXmlValueIndex1 = 1;

    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getsaveXmlValueInvoke1(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_saveXmlValueIndex1];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(KnowledgeSendServiceSoapBindingStub._saveXmlValueOperation1);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            super.primeMessageContext(mc);
            super.messageContexts[_saveXmlValueIndex1] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        } catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String saveXmlValue(java.lang.String xmlString) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getsaveXmlValueInvoke1(new java.lang.Object[]{xmlString}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        }
        try {
            return (java.lang.String) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (java.lang.String) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), java.lang.String.class);
        }
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _searchXmlValueOperation2 = null;

    private static com.ibm.ws.webservices.engine.description.OperationDesc _getsearchXmlValueOperation2() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params2 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "xmlString"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params2[0].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params2[0].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc2 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "searchXmlValueReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc2.setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc2.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults2 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        _searchXmlValueOperation2 = new com.ibm.ws.webservices.engine.description.OperationDesc("searchXmlValue", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://webservice.interfaces.km.eoms.boco.com", "searchXmlValue"), _params2, _returnDesc2, _faults2, "");
        _searchXmlValueOperation2.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "searchXmlValueRequest"));
        _searchXmlValueOperation2.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "KnowledgeSendServiceService"));
        _searchXmlValueOperation2.setOption("inoutOrderingReq", "false");
        _searchXmlValueOperation2.setOption("inputName", "searchXmlValueRequest");
        _searchXmlValueOperation2.setOption("inputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        _searchXmlValueOperation2.setOption("ResponseNamespace", "http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService");
        _searchXmlValueOperation2.setOption("buildNum", "cf170819.19");
        _searchXmlValueOperation2.setOption("outputName", "searchXmlValueResponse");
        _searchXmlValueOperation2.setOption("targetNamespace", "http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService");
        _searchXmlValueOperation2.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "searchXmlValueResponse"));
        _searchXmlValueOperation2.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "KnowledgeSendService"));
        _searchXmlValueOperation2.setOption("outputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        _searchXmlValueOperation2.setUse(com.ibm.ws.webservices.engine.enumtype.Use.ENCODED);
        _searchXmlValueOperation2.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return _searchXmlValueOperation2;

    }

    private int _searchXmlValueIndex2 = 2;

    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getsearchXmlValueInvoke2(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_searchXmlValueIndex2];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(KnowledgeSendServiceSoapBindingStub._searchXmlValueOperation2);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            super.primeMessageContext(mc);
            super.messageContexts[_searchXmlValueIndex2] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        } catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String searchXmlValue(java.lang.String xmlString) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getsearchXmlValueInvoke2(new java.lang.Object[]{xmlString}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        }
        try {
            return (java.lang.String) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (java.lang.String) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), java.lang.String.class);
        }
    }

    private static void _staticInit() {
        _searchXmlValueOperation2 = _getsearchXmlValueOperation2();
        _saveXmlValueOperation1 = _getsaveXmlValueOperation1();
        _mainOperation0 = _getmainOperation0();
    }

    static {
        _staticInit();
    }
}
