/**
 * KnowledgeSendServiceServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf170819.19 v52708210711
 */

package com.boco.eoms.knowledge.client;

public class KnowledgeSendServiceServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

    private static java.util.Map operationDescriptions;
    private static java.util.Map typeMappings;

    static {
         initOperationDescriptions();
         initTypeMappings();
    }

    private static void initOperationDescriptions() { 
        operationDescriptions = new java.util.HashMap();

        java.util.Map inner0 = new java.util.HashMap();

        java.util.List list0 = new java.util.ArrayList();
        inner0.put("main", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc main0Op = _main0Op();
        list0.add(main0Op);

        java.util.List list1 = new java.util.ArrayList();
        inner0.put("saveXmlValue", list1);

        com.ibm.ws.webservices.engine.description.OperationDesc saveXmlValue1Op = _saveXmlValue1Op();
        list1.add(saveXmlValue1Op);

        java.util.List list2 = new java.util.ArrayList();
        inner0.put("searchXmlValue", list2);

        com.ibm.ws.webservices.engine.description.OperationDesc searchXmlValue2Op = _searchXmlValue2Op();
        list2.add(searchXmlValue2Op);

        operationDescriptions.put("KnowledgeSendService",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _main0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc main0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "args"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "ArrayOf_soapenc_string"), java.lang.String[].class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partQNameString","{http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService}ArrayOf_soapenc_string");
        _params0[0].setOption("partName","ArrayOf_soapenc_string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(null, com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://websphere.ibm.com/webservices/", "Void"), void.class, true, false, false, false, true, true); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        main0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("main", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://webservice.interfaces.km.eoms.boco.com", "main"), _params0, _returnDesc0, _faults0, null);
        main0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "mainRequest"));
        main0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "KnowledgeSendServiceService"));
        main0Op.setOption("inoutOrderingReq","false");
        main0Op.setOption("inputName","mainRequest");
        main0Op.setOption("inputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        main0Op.setOption("ResponseNamespace","http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService");
        main0Op.setOption("buildNum","cf170819.19");
        main0Op.setOption("outputName","mainResponse");
        main0Op.setOption("targetNamespace","http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService");
        main0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "mainResponse"));
        main0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "KnowledgeSendService"));
        main0Op.setOption("outputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        main0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return main0Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _saveXmlValue1Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc saveXmlValue1Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "xmlString"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[0].setOption("partName","string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "saveXmlValueReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc0.setOption("partName","string");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        saveXmlValue1Op = new com.ibm.ws.webservices.engine.description.OperationDesc("saveXmlValue", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://webservice.interfaces.km.eoms.boco.com", "saveXmlValue"), _params0, _returnDesc0, _faults0, null);
        saveXmlValue1Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "saveXmlValueRequest"));
        saveXmlValue1Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "KnowledgeSendServiceService"));
        saveXmlValue1Op.setOption("inoutOrderingReq","false");
        saveXmlValue1Op.setOption("inputName","saveXmlValueRequest");
        saveXmlValue1Op.setOption("inputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        saveXmlValue1Op.setOption("ResponseNamespace","http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService");
        saveXmlValue1Op.setOption("buildNum","cf170819.19");
        saveXmlValue1Op.setOption("outputName","saveXmlValueResponse");
        saveXmlValue1Op.setOption("targetNamespace","http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService");
        saveXmlValue1Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "saveXmlValueResponse"));
        saveXmlValue1Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "KnowledgeSendService"));
        saveXmlValue1Op.setOption("outputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        saveXmlValue1Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return saveXmlValue1Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _searchXmlValue2Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc searchXmlValue2Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "xmlString"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[0].setOption("partName","string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "searchXmlValueReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc0.setOption("partName","string");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        searchXmlValue2Op = new com.ibm.ws.webservices.engine.description.OperationDesc("searchXmlValue", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://webservice.interfaces.km.eoms.boco.com", "searchXmlValue"), _params0, _returnDesc0, _faults0, null);
        searchXmlValue2Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "searchXmlValueRequest"));
        searchXmlValue2Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "KnowledgeSendServiceService"));
        searchXmlValue2Op.setOption("inoutOrderingReq","false");
        searchXmlValue2Op.setOption("inputName","searchXmlValueRequest");
        searchXmlValue2Op.setOption("inputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        searchXmlValue2Op.setOption("ResponseNamespace","http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService");
        searchXmlValue2Op.setOption("buildNum","cf170819.19");
        searchXmlValue2Op.setOption("outputName","searchXmlValueResponse");
        searchXmlValue2Op.setOption("targetNamespace","http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService");
        searchXmlValue2Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "searchXmlValueResponse"));
        searchXmlValue2Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "KnowledgeSendService"));
        searchXmlValue2Op.setOption("outputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        searchXmlValue2Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return searchXmlValue2Op;

    }


    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.knowledge.eoms.boco.com/eomsMain/services/KnowledgeSendService", "ArrayOf_soapenc_string"),
                         java.lang.String[].class);

        typeMappings = java.util.Collections.unmodifiableMap(typeMappings);
    }

    public java.util.Map getTypeMappings() {
        return typeMappings;
    }

    public Class getJavaType(javax.xml.namespace.QName xmlName) {
        return (Class) typeMappings.get(xmlName);
    }

    public java.util.Map getOperationDescriptions(String portName) {
        return (java.util.Map) operationDescriptions.get(portName);
    }

    public java.util.List getOperationDescriptions(String portName, String operationName) {
        java.util.Map map = (java.util.Map) operationDescriptions.get(portName);
        if (map != null) {
            return (java.util.List) map.get(operationName);
        }
        return null;
    }

}
