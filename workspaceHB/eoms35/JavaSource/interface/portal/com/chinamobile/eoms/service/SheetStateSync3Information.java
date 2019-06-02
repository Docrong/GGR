/**
 * SheetStateSync3Information.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf130745.06 v111407041203
 */

package com.chinamobile.eoms.service;

public class SheetStateSync3Information implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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
        inner0.put("isAlive", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc isAlive0Op = _isAlive0Op();
        list0.add(isAlive0Op);

        java.util.List list1 = new java.util.ArrayList();
        inner0.put("syncSheetState", list1);

        com.ibm.ws.webservices.engine.description.OperationDesc syncSheetState1Op = _syncSheetState1Op();
        list1.add(syncSheetState1Op);

        operationDescriptions.put("SheetStateSync3Soap",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _isAlive0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc isAlive0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "isAliveResult"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, true, true, false); 
        _returnDesc0.setOption("outputPosition","0");
        _returnDesc0.setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("partName","string");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        isAlive0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("isAlive", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "isAlive"), _params0, _returnDesc0, _faults0, null);
        isAlive0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "isAliveSoapIn"));
        isAlive0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "SheetStateSync3"));
        isAlive0Op.setOption("ResponseNamespace","http://service.eoms.chinamobile.com/SheetStateSync");
        isAlive0Op.setOption("buildNum","cf130745.06");
        isAlive0Op.setOption("ResponseLocalPart","isAliveResponse");
        isAlive0Op.setOption("targetNamespace","http://service.eoms.chinamobile.com/SheetStateSync");
        isAlive0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "isAliveSoapOut"));
        isAlive0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "SheetStateSync3Soap"));
        isAlive0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return isAlive0Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _syncSheetState1Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc syncSheetState1Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
          };
        _params0[0].setOption("inputPosition","0");
        _params0[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("partName","string");
        _params0[1].setOption("inputPosition","1");
        _params0[1].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[1].setOption("partName","string");
        _params0[2].setOption("inputPosition","2");
        _params0[2].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[2].setOption("partName","string");
        _params0[3].setOption("inputPosition","3");
        _params0[3].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[3].setOption("partName","string");
        _params0[4].setOption("inputPosition","4");
        _params0[4].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[4].setOption("partName","string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "syncSheetStateResult"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, true, true, false); 
        _returnDesc0.setOption("outputPosition","0");
        _returnDesc0.setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("partName","string");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        syncSheetState1Op = new com.ibm.ws.webservices.engine.description.OperationDesc("syncSheetState", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "syncSheetState"), _params0, _returnDesc0, _faults0, null);
        syncSheetState1Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "syncSheetStateSoapIn"));
        syncSheetState1Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "SheetStateSync3"));
        syncSheetState1Op.setOption("ResponseNamespace","http://service.eoms.chinamobile.com/SheetStateSync");
        syncSheetState1Op.setOption("buildNum","cf130745.06");
        syncSheetState1Op.setOption("ResponseLocalPart","syncSheetStateResponse");
        syncSheetState1Op.setOption("targetNamespace","http://service.eoms.chinamobile.com/SheetStateSync");
        syncSheetState1Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "syncSheetStateSoapOut"));
        syncSheetState1Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "SheetStateSync3Soap"));
        syncSheetState1Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return syncSheetState1Op;

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
