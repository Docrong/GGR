/**
 * IRMSServiceSoapBindingStub.java
 * <p>
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf170819.19 v52708210711
 */

package com.boco.eoms.interfaces.EOMSService.client;

public class IRMSServiceSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements com.boco.eoms.interfaces.EOMSService.client.IAttempXToEoms {
    public IRMSServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        } else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        initTypeMapping();
        super.cachedEndpoint = endpointURL;
        super.connection = ((com.ibm.ws.webservices.engine.client.Service) super.service).getConnection(endpointURL);
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[6];
    }

    private void initTypeMapping() {
        javax.xml.rpc.encoding.TypeMapping tm = super.getTypeMapping(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
        java.lang.Class javaType = null;
        javax.xml.namespace.QName xmlType = null;
        javax.xml.namespace.QName compQName = null;
        javax.xml.namespace.QName compTypeQName = null;
        com.ibm.ws.webservices.engine.encoding.SerializerFactory sf = null;
        com.ibm.ws.webservices.engine.encoding.DeserializerFactory df = null;
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _submitReplySheetOperation0 = null;

    private static com.ibm.ws.webservices.engine.description.OperationDesc _getsubmitReplySheetOperation0() {
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
        _submitReplySheetOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("submitReplySheet", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "submitReplySheet"), _params0, _returnDesc0, _faults0, "");
        _submitReplySheetOperation0.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "submitReplySheetRequest"));
        _submitReplySheetOperation0.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IRMSService"));
        _submitReplySheetOperation0.setOption("inputName", "submitReplySheetRequest");
        _submitReplySheetOperation0.setOption("ResponseNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        _submitReplySheetOperation0.setOption("buildNum", "cf170819.19");
        _submitReplySheetOperation0.setOption("outputName", "submitReplySheetResponse");
        _submitReplySheetOperation0.setOption("ResponseLocalPart", "submitReplySheetResponse");
        _submitReplySheetOperation0.setOption("targetNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        _submitReplySheetOperation0.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "submitReplySheetResponse"));
        _submitReplySheetOperation0.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IAttempXToEoms"));
        _submitReplySheetOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _submitReplySheetOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _submitReplySheetOperation0;

    }

    private int _submitReplySheetIndex0 = 0;

    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getsubmitReplySheetInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_submitReplySheetIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(IRMSServiceSoapBindingStub._submitReplySheetOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_submitReplySheetIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        } catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String submitReplySheet(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getsubmitReplySheetInvoke0(new java.lang.Object[]{serSupplier, serCaller, callerPwd, callTime, opDetail}).invoke();

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

    private static com.ibm.ws.webservices.engine.description.OperationDesc _putBusinessDataOperation1 = null;

    private static com.ibm.ws.webservices.engine.description.OperationDesc _getputBusinessDataOperation1() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params1 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params1[0].setOption("inputPosition", "0");
        _params1[0].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params1[0].setOption("partName", "string");
        _params1[1].setOption("inputPosition", "1");
        _params1[1].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params1[1].setOption("partName", "string");
        _params1[2].setOption("inputPosition", "2");
        _params1[2].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params1[2].setOption("partName", "string");
        _params1[3].setOption("inputPosition", "3");
        _params1[3].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params1[3].setOption("partName", "string");
        _params1[4].setOption("inputPosition", "4");
        _params1[4].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params1[4].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc1 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "putBusinessDataReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc1.setOption("outputPosition", "0");
        _returnDesc1.setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc1.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults1 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        _putBusinessDataOperation1 = new com.ibm.ws.webservices.engine.description.OperationDesc("putBusinessData", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "putBusinessData"), _params1, _returnDesc1, _faults1, "");
        _putBusinessDataOperation1.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "putBusinessDataRequest"));
        _putBusinessDataOperation1.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IRMSService"));
        _putBusinessDataOperation1.setOption("inputName", "putBusinessDataRequest");
        _putBusinessDataOperation1.setOption("ResponseNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        _putBusinessDataOperation1.setOption("buildNum", "cf170819.19");
        _putBusinessDataOperation1.setOption("outputName", "putBusinessDataResponse");
        _putBusinessDataOperation1.setOption("ResponseLocalPart", "putBusinessDataResponse");
        _putBusinessDataOperation1.setOption("targetNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        _putBusinessDataOperation1.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "putBusinessDataResponse"));
        _putBusinessDataOperation1.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IAttempXToEoms"));
        _putBusinessDataOperation1.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _putBusinessDataOperation1.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _putBusinessDataOperation1;

    }

    private int _putBusinessDataIndex1 = 1;

    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getputBusinessDataInvoke1(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_putBusinessDataIndex1];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(IRMSServiceSoapBindingStub._putBusinessDataOperation1);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_putBusinessDataIndex1] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        } catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String putBusinessData(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getputBusinessDataInvoke1(new java.lang.Object[]{serSupplier, serCaller, callerPwd, callTime, opDetail}).invoke();

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

    private static com.ibm.ws.webservices.engine.description.OperationDesc _deleteSheetOperation2 = null;

    private static com.ibm.ws.webservices.engine.description.OperationDesc _getdeleteSheetOperation2() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params2 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params2[0].setOption("inputPosition", "0");
        _params2[0].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params2[0].setOption("partName", "string");
        _params2[1].setOption("inputPosition", "1");
        _params2[1].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params2[1].setOption("partName", "string");
        _params2[2].setOption("inputPosition", "2");
        _params2[2].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params2[2].setOption("partName", "string");
        _params2[3].setOption("inputPosition", "3");
        _params2[3].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params2[3].setOption("partName", "string");
        _params2[4].setOption("inputPosition", "4");
        _params2[4].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params2[4].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc2 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "deleteSheetReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc2.setOption("outputPosition", "0");
        _returnDesc2.setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc2.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults2 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        _deleteSheetOperation2 = new com.ibm.ws.webservices.engine.description.OperationDesc("deleteSheet", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "deleteSheet"), _params2, _returnDesc2, _faults2, "");
        _deleteSheetOperation2.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "deleteSheetRequest"));
        _deleteSheetOperation2.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IRMSService"));
        _deleteSheetOperation2.setOption("inputName", "deleteSheetRequest");
        _deleteSheetOperation2.setOption("ResponseNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        _deleteSheetOperation2.setOption("buildNum", "cf170819.19");
        _deleteSheetOperation2.setOption("outputName", "deleteSheetResponse");
        _deleteSheetOperation2.setOption("ResponseLocalPart", "deleteSheetResponse");
        _deleteSheetOperation2.setOption("targetNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        _deleteSheetOperation2.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "deleteSheetResponse"));
        _deleteSheetOperation2.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IAttempXToEoms"));
        _deleteSheetOperation2.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _deleteSheetOperation2.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _deleteSheetOperation2;

    }

    private int _deleteSheetIndex2 = 2;

    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getdeleteSheetInvoke2(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_deleteSheetIndex2];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(IRMSServiceSoapBindingStub._deleteSheetOperation2);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_deleteSheetIndex2] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        } catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String deleteSheet(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getdeleteSheetInvoke2(new java.lang.Object[]{serSupplier, serCaller, callerPwd, callTime, opDetail}).invoke();

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

    private static com.ibm.ws.webservices.engine.description.OperationDesc _setCheckOperation3 = null;

    private static com.ibm.ws.webservices.engine.description.OperationDesc _getsetCheckOperation3() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params3 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params3[0].setOption("inputPosition", "0");
        _params3[0].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params3[0].setOption("partName", "string");
        _params3[1].setOption("inputPosition", "1");
        _params3[1].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params3[1].setOption("partName", "string");
        _params3[2].setOption("inputPosition", "2");
        _params3[2].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params3[2].setOption("partName", "string");
        _params3[3].setOption("inputPosition", "3");
        _params3[3].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params3[3].setOption("partName", "string");
        _params3[4].setOption("inputPosition", "4");
        _params3[4].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params3[4].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc3 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "setCheckReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc3.setOption("outputPosition", "0");
        _returnDesc3.setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc3.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults3 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        _setCheckOperation3 = new com.ibm.ws.webservices.engine.description.OperationDesc("setCheck", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "setCheck"), _params3, _returnDesc3, _faults3, "");
        _setCheckOperation3.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "setCheckRequest"));
        _setCheckOperation3.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IRMSService"));
        _setCheckOperation3.setOption("inputName", "setCheckRequest");
        _setCheckOperation3.setOption("ResponseNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        _setCheckOperation3.setOption("buildNum", "cf170819.19");
        _setCheckOperation3.setOption("outputName", "setCheckResponse");
        _setCheckOperation3.setOption("ResponseLocalPart", "setCheckResponse");
        _setCheckOperation3.setOption("targetNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        _setCheckOperation3.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "setCheckResponse"));
        _setCheckOperation3.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IAttempXToEoms"));
        _setCheckOperation3.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _setCheckOperation3.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _setCheckOperation3;

    }

    private int _setCheckIndex3 = 3;

    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getsetCheckInvoke3(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_setCheckIndex3];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(IRMSServiceSoapBindingStub._setCheckOperation3);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_setCheckIndex3] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        } catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String setCheck(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getsetCheckInvoke3(new java.lang.Object[]{serSupplier, serCaller, callerPwd, callTime, opDetail}).invoke();

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

    private static com.ibm.ws.webservices.engine.description.OperationDesc _getDeptIdsOperation4 = null;

    private static com.ibm.ws.webservices.engine.description.OperationDesc _getgetDeptIdsOperation4() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params4 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params4[0].setOption("inputPosition", "0");
        _params4[0].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params4[0].setOption("partName", "string");
        _params4[1].setOption("inputPosition", "1");
        _params4[1].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params4[1].setOption("partName", "string");
        _params4[2].setOption("inputPosition", "2");
        _params4[2].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params4[2].setOption("partName", "string");
        _params4[3].setOption("inputPosition", "3");
        _params4[3].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params4[3].setOption("partName", "string");
        _params4[4].setOption("inputPosition", "4");
        _params4[4].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params4[4].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc4 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getDeptIdsReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc4.setOption("outputPosition", "0");
        _returnDesc4.setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc4.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults4 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        _getDeptIdsOperation4 = new com.ibm.ws.webservices.engine.description.OperationDesc("getDeptIds", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getDeptIds"), _params4, _returnDesc4, _faults4, "");
        _getDeptIdsOperation4.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getDeptIdsRequest"));
        _getDeptIdsOperation4.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IRMSService"));
        _getDeptIdsOperation4.setOption("inputName", "getDeptIdsRequest");
        _getDeptIdsOperation4.setOption("ResponseNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        _getDeptIdsOperation4.setOption("buildNum", "cf170819.19");
        _getDeptIdsOperation4.setOption("outputName", "getDeptIdsResponse");
        _getDeptIdsOperation4.setOption("ResponseLocalPart", "getDeptIdsResponse");
        _getDeptIdsOperation4.setOption("targetNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        _getDeptIdsOperation4.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getDeptIdsResponse"));
        _getDeptIdsOperation4.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IAttempXToEoms"));
        _getDeptIdsOperation4.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _getDeptIdsOperation4.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _getDeptIdsOperation4;

    }

    private int _getDeptIdsIndex4 = 4;

    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getgetDeptIdsInvoke4(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_getDeptIdsIndex4];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(IRMSServiceSoapBindingStub._getDeptIdsOperation4);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_getDeptIdsIndex4] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        } catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String getDeptIds(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getgetDeptIdsInvoke4(new java.lang.Object[]{serSupplier, serCaller, callerPwd, callTime, opDetail}).invoke();

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

    private static com.ibm.ws.webservices.engine.description.OperationDesc _getExcelDataOperation5 = null;

    private static com.ibm.ws.webservices.engine.description.OperationDesc _getgetExcelDataOperation5() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[] _params5 = new com.ibm.ws.webservices.engine.description.ParameterDesc[]{
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
                new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false),
        };
        _params5[0].setOption("inputPosition", "0");
        _params5[0].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params5[0].setOption("partName", "string");
        _params5[1].setOption("inputPosition", "1");
        _params5[1].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params5[1].setOption("partName", "string");
        _params5[2].setOption("inputPosition", "2");
        _params5[2].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params5[2].setOption("partName", "string");
        _params5[3].setOption("inputPosition", "3");
        _params5[3].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params5[3].setOption("partName", "string");
        _params5[4].setOption("inputPosition", "4");
        _params5[4].setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _params5[4].setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.ParameterDesc _returnDesc5 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getExcelDataReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false);
        _returnDesc5.setOption("outputPosition", "0");
        _returnDesc5.setOption("partQNameString", "{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc5.setOption("partName", "string");
        com.ibm.ws.webservices.engine.description.FaultDesc[] _faults5 = new com.ibm.ws.webservices.engine.description.FaultDesc[]{
        };
        _getExcelDataOperation5 = new com.ibm.ws.webservices.engine.description.OperationDesc("getExcelData", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getExcelData"), _params5, _returnDesc5, _faults5, "");
        _getExcelDataOperation5.setOption("inputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getExcelDataRequest"));
        _getExcelDataOperation5.setOption("ServiceQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IRMSService"));
        _getExcelDataOperation5.setOption("inputName", "getExcelDataRequest");
        _getExcelDataOperation5.setOption("ResponseNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        _getExcelDataOperation5.setOption("buildNum", "cf170819.19");
        _getExcelDataOperation5.setOption("outputName", "getExcelDataResponse");
        _getExcelDataOperation5.setOption("ResponseLocalPart", "getExcelDataResponse");
        _getExcelDataOperation5.setOption("targetNamespace", "http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms");
        _getExcelDataOperation5.setOption("outputMessageQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "getExcelDataResponse"));
        _getExcelDataOperation5.setOption("portTypeQName", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.EOMSService.interfaces.eoms.boco.com/AttempXToEoms", "IAttempXToEoms"));
        _getExcelDataOperation5.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _getExcelDataOperation5.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _getExcelDataOperation5;

    }

    private int _getExcelDataIndex5 = 5;

    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getgetExcelDataInvoke5(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_getExcelDataIndex5];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(IRMSServiceSoapBindingStub._getExcelDataOperation5);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_getExcelDataIndex5] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        } catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String getExcelData(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getgetExcelDataInvoke5(new java.lang.Object[]{serSupplier, serCaller, callerPwd, callTime, opDetail}).invoke();

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
        _submitReplySheetOperation0 = _getsubmitReplySheetOperation0();
        _getExcelDataOperation5 = _getgetExcelDataOperation5();
        _setCheckOperation3 = _getsetCheckOperation3();
        _putBusinessDataOperation1 = _getputBusinessDataOperation1();
        _getDeptIdsOperation4 = _getgetDeptIdsOperation4();
        _deleteSheetOperation2 = _getdeleteSheetOperation2();
    }

    static {
        _staticInit();
    }
}
