/**
 * CRMProcessSheetServiceInformation.java
 * <p>
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf170819.19 v52708210711
 */

package com.boco.eoms.crm.client;

public class CRMProcessSheetServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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
        inner0.put("confirmWorkSheet", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc confirmWorkSheet0Op = _confirmWorkSheet0Op();
        list0.add(confirmWorkSheet0Op);

        java.util.List list1 = new java.util.ArrayList();
        inner0.put("isAlive", list1);

        com.ibm.ws.webservices.engine.description.OperationDesc isAlive1Op = _isAlive1Op();
        list1.add(isAlive1Op);

        java.util.List list2 = new java.util.ArrayList();
        inner0.put("notifyWorkSheet", list2);

        com.ibm.ws.webservices.engine.description.OperationDesc notifyWorkSheet2Op = _notifyWorkSheet2Op();
        list2.add(notifyWorkSheet2Op);

        java.util.List list3 = new java.util.ArrayList();
        inner0.put("replyWorkSheet", list3);

        com.ibm.ws.webservices.engine.description.OperationDesc replyWorkSheet3Op = _replyWorkSheet3Op();
        list3.add(replyWorkSheet3Op);

        java.util.List list4 = new java.util.ArrayList();
        inner0.put("withdrawWorkSheet", list4);

        com.ibm.ws.webservices.engine.description.OperationDesc withdrawWorkSheet4Op = _withdrawWorkSheet4Op();
        list4.add(withdrawWorkSheet4Op);

        operationDescriptions.put("CRMProcessSheet", inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _confirmWorkSheet0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc confirmWorkSheet0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "sheetType"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serviceType"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serialNo"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "attachRef"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opPerson"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opCorp"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opDepart"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opContact"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params0[0].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params0[0].setOption("partName", "int");
        _params0[1].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params0[1].setOption("partName", "int");
        _params0[2].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[2].setOption("partName", "string");
        _params0[3].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[3].setOption("partName", "string");
        _params0[4].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[4].setOption("partName", "string");
        _params0[5].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[5].setOption("partName", "string");
        _params0[6].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[6].setOption("partName", "string");
        _params0[7].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[7].setOption("partName", "string");
        _params0[8].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[8].setOption("partName", "string");
        _params0[9].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[9].setOption("partName", "string");
        _params0[10].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[10].setOption("partName", "string");
        _params0[11].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[11].setOption("partName", "string");
        _params0[12].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[12].setOption("partName", "string");
        _params0[13].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[13].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "confirmWorkSheetReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc0.setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc0.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        confirmWorkSheet0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("confirmWorkSheet", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://gpbs.webservice.linkage.com", "confirmWorkSheet"), _params0, _returnDesc0, _faults0, null);
        confirmWorkSheet0Op.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "confirmWorkSheetRequest"));
        confirmWorkSheet0Op.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheetService"));
        confirmWorkSheet0Op.setOption("inoutOrderingReq", "false");
        confirmWorkSheet0Op.setOption("inputName", "confirmWorkSheetRequest");
        confirmWorkSheet0Op.setOption("inputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        confirmWorkSheet0Op.setOption("ResponseNamespace", "http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        confirmWorkSheet0Op.setOption("buildNum", "cf170819.19");
        confirmWorkSheet0Op.setOption("outputName", "confirmWorkSheetResponse");
        confirmWorkSheet0Op.setOption("targetNamespace", "http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        confirmWorkSheet0Op.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "confirmWorkSheetResponse"));
        confirmWorkSheet0Op.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheet"));
        confirmWorkSheet0Op.setOption("outputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        confirmWorkSheet0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return confirmWorkSheet0Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _isAlive1Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc isAlive1Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params0[0].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[0].setOption("partName", "string");
        _params0[1].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[1].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "isAliveReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc0.setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc0.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        isAlive1Op = new com.ibm.ws.webservices.engine.description.OperationDesc("isAlive", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://gpbs.webservice.linkage.com", "isAlive"), _params0, _returnDesc0, _faults0, null);
        isAlive1Op.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "isAliveRequest"));
        isAlive1Op.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheetService"));
        isAlive1Op.setOption("inoutOrderingReq", "false");
        isAlive1Op.setOption("inputName", "isAliveRequest");
        isAlive1Op.setOption("inputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        isAlive1Op.setOption("ResponseNamespace", "http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        isAlive1Op.setOption("buildNum", "cf170819.19");
        isAlive1Op.setOption("outputName", "isAliveResponse");
        isAlive1Op.setOption("targetNamespace", "http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        isAlive1Op.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "isAliveResponse"));
        isAlive1Op.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheet"));
        isAlive1Op.setOption("outputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        isAlive1Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return isAlive1Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _notifyWorkSheet2Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc notifyWorkSheet2Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "sheetType"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serviceType"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serialNo"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "attachRef"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opPerson"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opCorp"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opDepart"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opContact"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params0[0].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params0[0].setOption("partName", "int");
        _params0[1].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params0[1].setOption("partName", "int");
        _params0[2].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[2].setOption("partName", "string");
        _params0[3].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[3].setOption("partName", "string");
        _params0[4].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[4].setOption("partName", "string");
        _params0[5].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[5].setOption("partName", "string");
        _params0[6].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[6].setOption("partName", "string");
        _params0[7].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[7].setOption("partName", "string");
        _params0[8].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[8].setOption("partName", "string");
        _params0[9].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[9].setOption("partName", "string");
        _params0[10].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[10].setOption("partName", "string");
        _params0[11].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[11].setOption("partName", "string");
        _params0[12].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[12].setOption("partName", "string");
        _params0[13].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[13].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "notifyWorkSheetReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc0.setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc0.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        notifyWorkSheet2Op = new com.ibm.ws.webservices.engine.description.OperationDesc("notifyWorkSheet", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://gpbs.webservice.linkage.com", "notifyWorkSheet"), _params0, _returnDesc0, _faults0, null);
        notifyWorkSheet2Op.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "notifyWorkSheetRequest"));
        notifyWorkSheet2Op.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheetService"));
        notifyWorkSheet2Op.setOption("inoutOrderingReq", "false");
        notifyWorkSheet2Op.setOption("inputName", "notifyWorkSheetRequest");
        notifyWorkSheet2Op.setOption("inputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        notifyWorkSheet2Op.setOption("ResponseNamespace", "http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        notifyWorkSheet2Op.setOption("buildNum", "cf170819.19");
        notifyWorkSheet2Op.setOption("outputName", "notifyWorkSheetResponse");
        notifyWorkSheet2Op.setOption("targetNamespace", "http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        notifyWorkSheet2Op.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "notifyWorkSheetResponse"));
        notifyWorkSheet2Op.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheet"));
        notifyWorkSheet2Op.setOption("outputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        notifyWorkSheet2Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return notifyWorkSheet2Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _replyWorkSheet3Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc replyWorkSheet3Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "sheetType"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serviceType"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serialNo"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "attachRef"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opPerson"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opCorp"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opDepart"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opContact"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params0[0].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params0[0].setOption("partName", "int");
        _params0[1].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params0[1].setOption("partName", "int");
        _params0[2].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[2].setOption("partName", "string");
        _params0[3].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[3].setOption("partName", "string");
        _params0[4].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[4].setOption("partName", "string");
        _params0[5].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[5].setOption("partName", "string");
        _params0[6].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[6].setOption("partName", "string");
        _params0[7].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[7].setOption("partName", "string");
        _params0[8].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[8].setOption("partName", "string");
        _params0[9].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[9].setOption("partName", "string");
        _params0[10].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[10].setOption("partName", "string");
        _params0[11].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[11].setOption("partName", "string");
        _params0[12].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[12].setOption("partName", "string");
        _params0[13].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[13].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "replyWorkSheetReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc0.setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc0.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        replyWorkSheet3Op = new com.ibm.ws.webservices.engine.description.OperationDesc("replyWorkSheet", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://gpbs.webservice.linkage.com", "replyWorkSheet"), _params0, _returnDesc0, _faults0, null);
        replyWorkSheet3Op.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "replyWorkSheetRequest"));
        replyWorkSheet3Op.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheetService"));
        replyWorkSheet3Op.setOption("inoutOrderingReq", "false");
        replyWorkSheet3Op.setOption("inputName", "replyWorkSheetRequest");
        replyWorkSheet3Op.setOption("inputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        replyWorkSheet3Op.setOption("ResponseNamespace", "http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        replyWorkSheet3Op.setOption("buildNum", "cf170819.19");
        replyWorkSheet3Op.setOption("outputName", "replyWorkSheetResponse");
        replyWorkSheet3Op.setOption("targetNamespace", "http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        replyWorkSheet3Op.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "replyWorkSheetResponse"));
        replyWorkSheet3Op.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheet"));
        replyWorkSheet3Op.setOption("outputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        replyWorkSheet3Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return replyWorkSheet3Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _withdrawWorkSheet4Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc withdrawWorkSheet4Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "sheetType"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serviceType"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serialNo"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "attachRef"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opPerson"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opCorp"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opDepart"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opContact"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params0[0].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params0[0].setOption("partName", "int");
        _params0[1].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params0[1].setOption("partName", "int");
        _params0[2].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[2].setOption("partName", "string");
        _params0[3].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[3].setOption("partName", "string");
        _params0[4].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[4].setOption("partName", "string");
        _params0[5].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[5].setOption("partName", "string");
        _params0[6].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[6].setOption("partName", "string");
        _params0[7].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[7].setOption("partName", "string");
        _params0[8].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[8].setOption("partName", "string");
        _params0[9].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[9].setOption("partName", "string");
        _params0[10].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[10].setOption("partName", "string");
        _params0[11].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[11].setOption("partName", "string");
        _params0[12].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[12].setOption("partName", "string");
        _params0[13].setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[13].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "withdrawWorkSheetReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc0.setOption("partQNameString", "{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc0.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        withdrawWorkSheet4Op = new com.ibm.ws.webservices.engine.description.OperationDesc("withdrawWorkSheet", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://gpbs.webservice.linkage.com", "withdrawWorkSheet"), _params0, _returnDesc0, _faults0, null);
        withdrawWorkSheet4Op.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "withdrawWorkSheetRequest"));
        withdrawWorkSheet4Op.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheetService"));
        withdrawWorkSheet4Op.setOption("inoutOrderingReq", "false");
        withdrawWorkSheet4Op.setOption("inputName", "withdrawWorkSheetRequest");
        withdrawWorkSheet4Op.setOption("inputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        withdrawWorkSheet4Op.setOption("ResponseNamespace", "http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        withdrawWorkSheet4Op.setOption("buildNum", "cf170819.19");
        withdrawWorkSheet4Op.setOption("outputName", "withdrawWorkSheetResponse");
        withdrawWorkSheet4Op.setOption("targetNamespace", "http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        withdrawWorkSheet4Op.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "withdrawWorkSheetResponse"));
        withdrawWorkSheet4Op.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheet"));
        withdrawWorkSheet4Op.setOption("outputEncodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        withdrawWorkSheet4Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return withdrawWorkSheet4Op;

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
