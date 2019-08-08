/**
 * AddKnowledgeServiceServiceInformation.java
 * <p>
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf170819.19 v52708210711
 */

package krms;

public class AddKnowledgeServiceServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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
        inner0.put("getKnowledgeBySheetIds", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc getKnowledgeBySheetIds0Op = _getKnowledgeBySheetIds0Op();
        list0.add(getKnowledgeBySheetIds0Op);

        java.util.List list1 = new java.util.ArrayList();
        inner0.put("saveXmlValue", list1);

        com.ibm.ws.webservices.engine.description.OperationDesc saveXmlValue1Op = _saveXmlValue1Op();
        list1.add(saveXmlValue1Op);

        operationDescriptions.put("knowledgeService", inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _getKnowledgeBySheetIds0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc getKnowledgeBySheetIds0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "xmlStr"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params0[0].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[0].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "getKnowledgeBySheetIdsReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc0.setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc0.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        getKnowledgeBySheetIds0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("getKnowledgeBySheetIds", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://wservice.externalinterface.subassembly.krm.boco.com", "getKnowledgeBySheetIds"), _params0, _returnDesc0, _faults0, null);
        getKnowledgeBySheetIds0Op.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "getKnowledgeBySheetIdsRequest"));
        getKnowledgeBySheetIds0Op.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "AddKnowledgeServiceService"));
        getKnowledgeBySheetIds0Op.setOption("inoutOrderingReq", "false");
        getKnowledgeBySheetIds0Op.setOption("inputName", "getKnowledgeBySheetIdsRequest");
        getKnowledgeBySheetIds0Op.setOption("inputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        getKnowledgeBySheetIds0Op.setOption("ResponseNamespace", "http://krms/services/knowledgeService");
        getKnowledgeBySheetIds0Op.setOption("buildNum", "cf170819.19");
        getKnowledgeBySheetIds0Op.setOption("outputName", "getKnowledgeBySheetIdsResponse");
        getKnowledgeBySheetIds0Op.setOption("targetNamespace", "http://krms/services/knowledgeService");
        getKnowledgeBySheetIds0Op.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "getKnowledgeBySheetIdsResponse"));
        getKnowledgeBySheetIds0Op.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "AddKnowledgeService"));
        getKnowledgeBySheetIds0Op.setOption("outputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        getKnowledgeBySheetIds0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return getKnowledgeBySheetIds0Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _saveXmlValue1Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc saveXmlValue1Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "xmlStr"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params0[0].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[0].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "saveXmlValueReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc0.setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc0.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        saveXmlValue1Op = new com.ibm.ws.webservices.engine.description.OperationDesc("saveXmlValue", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://wservice.externalinterface.subassembly.krm.boco.com", "saveXmlValue"), _params0, _returnDesc0, _faults0, null);
        saveXmlValue1Op.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "saveXmlValueRequest"));
        saveXmlValue1Op.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "AddKnowledgeServiceService"));
        saveXmlValue1Op.setOption("inoutOrderingReq", "false");
        saveXmlValue1Op.setOption("inputName", "saveXmlValueRequest");
        saveXmlValue1Op.setOption("inputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        saveXmlValue1Op.setOption("ResponseNamespace", "http://krms/services/knowledgeService");
        saveXmlValue1Op.setOption("buildNum", "cf170819.19");
        saveXmlValue1Op.setOption("outputName", "saveXmlValueResponse");
        saveXmlValue1Op.setOption("targetNamespace", "http://krms/services/knowledgeService");
        saveXmlValue1Op.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "saveXmlValueResponse"));
        saveXmlValue1Op.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://krms/services/knowledgeService", "AddKnowledgeService"));
        saveXmlValue1Op.setOption("outputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        saveXmlValue1Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return saveXmlValue1Op;

    }


    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
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
