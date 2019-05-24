/**
 * KnowledgeServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf170819.19 v52708210711
 */

package krms;

public class KnowledgeServiceSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements krms.AddKnowledgeService {
    public KnowledgeServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        initTypeMapping();
        super.cachedEndpoint = endpointURL;
        super.connection = ((com.ibm.ws.webservices.engine.client.Service) super.service).getConnection(endpointURL);
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[2];
    }

    private void initTypeMapping() {
        javax.xml.rpc.encoding.TypeMapping tm = super.getTypeMapping(com.ibm.ws.webservices.engine.Constants.URI_SOAP11_ENC);
        java.lang.Class javaType = null;
        javax.xml.namespace.QName xmlType = null;
        javax.xml.namespace.QName compQName = null;
        javax.xml.namespace.QName compTypeQName = null;
        com.ibm.ws.webservices.engine.encoding.SerializerFactory sf = null;
        com.ibm.ws.webservices.engine.encoding.DeserializerFactory df = null;
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _getKnowledgeBySheetIdsOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getgetKnowledgeBySheetIdsOperation0() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "xmlStr"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[0].setOption("partName","string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "getKnowledgeBySheetIdsReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc0.setOption("partName","string");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _getKnowledgeBySheetIdsOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("getKnowledgeBySheetIds", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://wservice.externalinterface.subassembly.krm.boco.com", "getKnowledgeBySheetIds"), _params0, _returnDesc0, _faults0, "");
        _getKnowledgeBySheetIdsOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "getKnowledgeBySheetIdsRequest"));
        _getKnowledgeBySheetIdsOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "AddKnowledgeServiceService"));
        _getKnowledgeBySheetIdsOperation0.setOption("inoutOrderingReq","false");
        _getKnowledgeBySheetIdsOperation0.setOption("inputName","getKnowledgeBySheetIdsRequest");
        _getKnowledgeBySheetIdsOperation0.setOption("inputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _getKnowledgeBySheetIdsOperation0.setOption("ResponseNamespace","http://krms/services/knowledgeService");
        _getKnowledgeBySheetIdsOperation0.setOption("buildNum","cf170819.19");
        _getKnowledgeBySheetIdsOperation0.setOption("outputName","getKnowledgeBySheetIdsResponse");
        _getKnowledgeBySheetIdsOperation0.setOption("targetNamespace","http://krms/services/knowledgeService");
        _getKnowledgeBySheetIdsOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "getKnowledgeBySheetIdsResponse"));
        _getKnowledgeBySheetIdsOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "AddKnowledgeService"));
        _getKnowledgeBySheetIdsOperation0.setOption("outputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _getKnowledgeBySheetIdsOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.ENCODED);
        _getKnowledgeBySheetIdsOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return _getKnowledgeBySheetIdsOperation0;

    }

    private int _getKnowledgeBySheetIdsIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getgetKnowledgeBySheetIdsInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_getKnowledgeBySheetIdsIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(KnowledgeServiceSoapBindingStub._getKnowledgeBySheetIdsOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            super.primeMessageContext(mc);
            super.messageContexts[_getKnowledgeBySheetIdsIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String getKnowledgeBySheetIds(java.lang.String xmlStr) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getgetKnowledgeBySheetIdsInvoke0(new java.lang.Object[] {xmlStr}).invoke();

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

    private static com.ibm.ws.webservices.engine.description.OperationDesc _saveXmlValueOperation1 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getsaveXmlValueOperation1() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params1 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "xmlStr"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        _params1[0].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params1[0].setOption("partName","string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc1 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "saveXmlValueReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false); 
        _returnDesc1.setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc1.setOption("partName","string");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults1 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _saveXmlValueOperation1 = new com.ibm.ws.webservices.engine.description.OperationDesc("saveXmlValue", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://wservice.externalinterface.subassembly.krm.boco.com", "saveXmlValue"), _params1, _returnDesc1, _faults1, "");
        _saveXmlValueOperation1.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "saveXmlValueRequest"));
        _saveXmlValueOperation1.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "AddKnowledgeServiceService"));
        _saveXmlValueOperation1.setOption("inoutOrderingReq","false");
        _saveXmlValueOperation1.setOption("inputName","saveXmlValueRequest");
        _saveXmlValueOperation1.setOption("inputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _saveXmlValueOperation1.setOption("ResponseNamespace","http://krms/services/knowledgeService");
        _saveXmlValueOperation1.setOption("buildNum","cf170819.19");
        _saveXmlValueOperation1.setOption("outputName","saveXmlValueResponse");
        _saveXmlValueOperation1.setOption("targetNamespace","http://krms/services/knowledgeService");
        _saveXmlValueOperation1.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "saveXmlValueResponse"));
        _saveXmlValueOperation1.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "AddKnowledgeService"));
        _saveXmlValueOperation1.setOption("outputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _saveXmlValueOperation1.setUse(com.ibm.ws.webservices.engine.enumtype.Use.ENCODED);
        _saveXmlValueOperation1.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return _saveXmlValueOperation1;

    }

    private int _saveXmlValueIndex1 = 1;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getsaveXmlValueInvoke1(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_saveXmlValueIndex1];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(KnowledgeServiceSoapBindingStub._saveXmlValueOperation1);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            super.primeMessageContext(mc);
            super.messageContexts[_saveXmlValueIndex1] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String saveXmlValue(java.lang.String xmlStr) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getsaveXmlValueInvoke1(new java.lang.Object[] {xmlStr}).invoke();

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
        _getKnowledgeBySheetIdsOperation0 = _getgetKnowledgeBySheetIdsOperation0();
        _saveXmlValueOperation1 = _getsaveXmlValueOperation1();
    }

    static {
       _staticInit();
    }
}
