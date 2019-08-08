/**
 * IRMSServiceInformation.java
 * <p>
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf170819.19 v52708210711
 */

package com.boco.eoms.interfaces.EOMSService.client;

public class IRMSServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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
        inner0.put("deleteSheet", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc deleteSheet0Op = _deleteSheet0Op();
        list0.add(deleteSheet0Op);

        java.util.List list1 = new java.util.ArrayList();
        inner0.put("getDeptIds", list1);

        com.ibm.ws.webservices.engine.description.OperationDesc getDeptIds1Op = _getDeptIds1Op();
        list1.add(getDeptIds1Op);

        java.util.List list2 = new java.util.ArrayList();
        inner0.put("getExcelData", list2);

        com.ibm.ws.webservices.engine.description.OperationDesc getExcelData2Op = _getExcelData2Op();
        list2.add(getExcelData2Op);

        java.util.List list3 = new java.util.ArrayList();
        inner0.put("putBusinessData", list3);

        com.ibm.ws.webservices.engine.description.OperationDesc putBusinessData3Op = _putBusinessData3Op();
        list3.add(putBusinessData3Op);

        java.util.List list4 = new java.util.ArrayList();
        inner0.put("setCheck", list4);

        com.ibm.ws.webservices.engine.description.OperationDesc setCheck4Op = _setCheck4Op();
        list4.add(setCheck4Op);

        java.util.List list5 = new java.util.ArrayList();
        inner0.put("submitReplySheet", list5);

        com.ibm.ws.webservices.engine.description.OperationDesc submitReplySheet5Op = _submitReplySheet5Op();
        list5.add(submitReplySheet5Op);

        operationDescriptions.put("IRMSService", inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _deleteSheet0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc deleteSheet0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params0[0].setOption("inputPosition", "0");
        _params0[0].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("partName", "string");
        _params0[1].setOption("inputPosition", "1");
        _params0[1].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[1].setOption("partName", "string");
        _params0[2].setOption("inputPosition", "2");
        _params0[2].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[2].setOption("partName", "string");
        _params0[3].setOption("inputPosition", "3");
        _params0[3].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[3].setOption("partName", "string");
        _params0[4].setOption("inputPosition", "4");
        _params0[4].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[4].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "deleteSheetReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc0.setOption("outputPosition", "0");
        _returnDesc0.setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        deleteSheet0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("deleteSheet", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "deleteSheet"), _params0, _returnDesc0, _faults0, null);
        deleteSheet0Op.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "deleteSheetRequest"));
        deleteSheet0Op.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IRMSService"));
        deleteSheet0Op.setOption("inputName", "deleteSheetRequest");
        deleteSheet0Op.setOption("ResponseNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        deleteSheet0Op.setOption("buildNum", "cf170819.19");
        deleteSheet0Op.setOption("outputName", "deleteSheetResponse");
        deleteSheet0Op.setOption("ResponseLocalPart", "deleteSheetResponse");
        deleteSheet0Op.setOption("targetNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        deleteSheet0Op.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "deleteSheetResponse"));
        deleteSheet0Op.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IAttempXToEoms"));
        deleteSheet0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return deleteSheet0Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _getDeptIds1Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc getDeptIds1Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params0[0].setOption("inputPosition", "0");
        _params0[0].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("partName", "string");
        _params0[1].setOption("inputPosition", "1");
        _params0[1].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[1].setOption("partName", "string");
        _params0[2].setOption("inputPosition", "2");
        _params0[2].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[2].setOption("partName", "string");
        _params0[3].setOption("inputPosition", "3");
        _params0[3].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[3].setOption("partName", "string");
        _params0[4].setOption("inputPosition", "4");
        _params0[4].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[4].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getDeptIdsReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc0.setOption("outputPosition", "0");
        _returnDesc0.setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        getDeptIds1Op = new com.ibm.ws.webservices.engine.description.OperationDesc("getDeptIds", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getDeptIds"), _params0, _returnDesc0, _faults0, null);
        getDeptIds1Op.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getDeptIdsRequest"));
        getDeptIds1Op.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IRMSService"));
        getDeptIds1Op.setOption("inputName", "getDeptIdsRequest");
        getDeptIds1Op.setOption("ResponseNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        getDeptIds1Op.setOption("buildNum", "cf170819.19");
        getDeptIds1Op.setOption("outputName", "getDeptIdsResponse");
        getDeptIds1Op.setOption("ResponseLocalPart", "getDeptIdsResponse");
        getDeptIds1Op.setOption("targetNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        getDeptIds1Op.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getDeptIdsResponse"));
        getDeptIds1Op.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IAttempXToEoms"));
        getDeptIds1Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return getDeptIds1Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _getExcelData2Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc getExcelData2Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params0[0].setOption("inputPosition", "0");
        _params0[0].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("partName", "string");
        _params0[1].setOption("inputPosition", "1");
        _params0[1].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[1].setOption("partName", "string");
        _params0[2].setOption("inputPosition", "2");
        _params0[2].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[2].setOption("partName", "string");
        _params0[3].setOption("inputPosition", "3");
        _params0[3].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[3].setOption("partName", "string");
        _params0[4].setOption("inputPosition", "4");
        _params0[4].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[4].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getExcelDataReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc0.setOption("outputPosition", "0");
        _returnDesc0.setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        getExcelData2Op = new com.ibm.ws.webservices.engine.description.OperationDesc("getExcelData", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getExcelData"), _params0, _returnDesc0, _faults0, null);
        getExcelData2Op.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getExcelDataRequest"));
        getExcelData2Op.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IRMSService"));
        getExcelData2Op.setOption("inputName", "getExcelDataRequest");
        getExcelData2Op.setOption("ResponseNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        getExcelData2Op.setOption("buildNum", "cf170819.19");
        getExcelData2Op.setOption("outputName", "getExcelDataResponse");
        getExcelData2Op.setOption("ResponseLocalPart", "getExcelDataResponse");
        getExcelData2Op.setOption("targetNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        getExcelData2Op.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getExcelDataResponse"));
        getExcelData2Op.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IAttempXToEoms"));
        getExcelData2Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return getExcelData2Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _putBusinessData3Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc putBusinessData3Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params0[0].setOption("inputPosition", "0");
        _params0[0].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("partName", "string");
        _params0[1].setOption("inputPosition", "1");
        _params0[1].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[1].setOption("partName", "string");
        _params0[2].setOption("inputPosition", "2");
        _params0[2].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[2].setOption("partName", "string");
        _params0[3].setOption("inputPosition", "3");
        _params0[3].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[3].setOption("partName", "string");
        _params0[4].setOption("inputPosition", "4");
        _params0[4].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[4].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "putBusinessDataReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc0.setOption("outputPosition", "0");
        _returnDesc0.setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        putBusinessData3Op = new com.ibm.ws.webservices.engine.description.OperationDesc("putBusinessData", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "putBusinessData"), _params0, _returnDesc0, _faults0, null);
        putBusinessData3Op.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "putBusinessDataRequest"));
        putBusinessData3Op.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IRMSService"));
        putBusinessData3Op.setOption("inputName", "putBusinessDataRequest");
        putBusinessData3Op.setOption("ResponseNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        putBusinessData3Op.setOption("buildNum", "cf170819.19");
        putBusinessData3Op.setOption("outputName", "putBusinessDataResponse");
        putBusinessData3Op.setOption("ResponseLocalPart", "putBusinessDataResponse");
        putBusinessData3Op.setOption("targetNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        putBusinessData3Op.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "putBusinessDataResponse"));
        putBusinessData3Op.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IAttempXToEoms"));
        putBusinessData3Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return putBusinessData3Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _setCheck4Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc setCheck4Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params0[0].setOption("inputPosition", "0");
        _params0[0].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("partName", "string");
        _params0[1].setOption("inputPosition", "1");
        _params0[1].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[1].setOption("partName", "string");
        _params0[2].setOption("inputPosition", "2");
        _params0[2].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[2].setOption("partName", "string");
        _params0[3].setOption("inputPosition", "3");
        _params0[3].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[3].setOption("partName", "string");
        _params0[4].setOption("inputPosition", "4");
        _params0[4].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[4].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "setCheckReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc0.setOption("outputPosition", "0");
        _returnDesc0.setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        setCheck4Op = new com.ibm.ws.webservices.engine.description.OperationDesc("setCheck", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "setCheck"), _params0, _returnDesc0, _faults0, null);
        setCheck4Op.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "setCheckRequest"));
        setCheck4Op.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IRMSService"));
        setCheck4Op.setOption("inputName", "setCheckRequest");
        setCheck4Op.setOption("ResponseNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        setCheck4Op.setOption("buildNum", "cf170819.19");
        setCheck4Op.setOption("outputName", "setCheckResponse");
        setCheck4Op.setOption("ResponseLocalPart", "setCheckResponse");
        setCheck4Op.setOption("targetNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        setCheck4Op.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "setCheckResponse"));
        setCheck4Op.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IAttempXToEoms"));
        setCheck4Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return setCheck4Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _submitReplySheet5Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc submitReplySheet5Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params0[0].setOption("inputPosition", "0");
        _params0[0].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("partName", "string");
        _params0[1].setOption("inputPosition", "1");
        _params0[1].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[1].setOption("partName", "string");
        _params0[2].setOption("inputPosition", "2");
        _params0[2].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[2].setOption("partName", "string");
        _params0[3].setOption("inputPosition", "3");
        _params0[3].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[3].setOption("partName", "string");
        _params0[4].setOption("inputPosition", "4");
        _params0[4].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params0[4].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "submitReplySheetReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc0.setOption("outputPosition", "0");
        _returnDesc0.setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        submitReplySheet5Op = new com.ibm.ws.webservices.engine.description.OperationDesc("submitReplySheet", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "submitReplySheet"), _params0, _returnDesc0, _faults0, null);
        submitReplySheet5Op.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "submitReplySheetRequest"));
        submitReplySheet5Op.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IRMSService"));
        submitReplySheet5Op.setOption("inputName", "submitReplySheetRequest");
        submitReplySheet5Op.setOption("ResponseNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        submitReplySheet5Op.setOption("buildNum", "cf170819.19");
        submitReplySheet5Op.setOption("outputName", "submitReplySheetResponse");
        submitReplySheet5Op.setOption("ResponseLocalPart", "submitReplySheetResponse");
        submitReplySheet5Op.setOption("targetNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        submitReplySheet5Op.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "submitReplySheetResponse"));
        submitReplySheet5Op.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IAttempXToEoms"));
        submitReplySheet5Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return submitReplySheet5Op;

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
