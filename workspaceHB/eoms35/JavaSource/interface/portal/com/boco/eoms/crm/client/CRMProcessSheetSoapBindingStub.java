/**
 * CRMProcessSheetSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf170819.19 v52708210711
 */

package com.boco.eoms.crm.client;

public class CRMProcessSheetSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements com.boco.eoms.crm.client.CRMProcessSheet {
    public CRMProcessSheetSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
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
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[5];
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

    private static com.ibm.ws.webservices.engine.description.OperationDesc _isAliveOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getisAliveOperation0() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[0].setOption("partName","string");
        _params0[1].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params0[1].setOption("partName","string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "isAliveReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc0.setOption("partName","string");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _isAliveOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("isAlive", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://gpbs.webservice.linkage.com", "isAlive"), _params0, _returnDesc0, _faults0, "");
        _isAliveOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "isAliveRequest"));
        _isAliveOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheetService"));
        _isAliveOperation0.setOption("inoutOrderingReq","false");
        _isAliveOperation0.setOption("inputName","isAliveRequest");
        _isAliveOperation0.setOption("inputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _isAliveOperation0.setOption("ResponseNamespace","http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        _isAliveOperation0.setOption("buildNum","cf170819.19");
        _isAliveOperation0.setOption("outputName","isAliveResponse");
        _isAliveOperation0.setOption("targetNamespace","http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        _isAliveOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "isAliveResponse"));
        _isAliveOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheet"));
        _isAliveOperation0.setOption("outputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _isAliveOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.ENCODED);
        _isAliveOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return _isAliveOperation0;

    }

    private int _isAliveIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getisAliveInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_isAliveIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(CRMProcessSheetSoapBindingStub._isAliveOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            super.primeMessageContext(mc);
            super.messageContexts[_isAliveIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String isAlive(java.lang.String serSupplier, java.lang.String callTime) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getisAliveInvoke0(new java.lang.Object[] {serSupplier, callTime}).invoke();

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

    private static com.ibm.ws.webservices.engine.description.OperationDesc _confirmWorkSheetOperation1 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getconfirmWorkSheetOperation1() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params1 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
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
        _params1[0].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params1[0].setOption("partName","int");
        _params1[1].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params1[1].setOption("partName","int");
        _params1[2].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params1[2].setOption("partName","string");
        _params1[3].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params1[3].setOption("partName","string");
        _params1[4].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params1[4].setOption("partName","string");
        _params1[5].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params1[5].setOption("partName","string");
        _params1[6].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params1[6].setOption("partName","string");
        _params1[7].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params1[7].setOption("partName","string");
        _params1[8].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params1[8].setOption("partName","string");
        _params1[9].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params1[9].setOption("partName","string");
        _params1[10].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params1[10].setOption("partName","string");
        _params1[11].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params1[11].setOption("partName","string");
        _params1[12].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params1[12].setOption("partName","string");
        _params1[13].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params1[13].setOption("partName","string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc1 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "confirmWorkSheetReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false); 
        _returnDesc1.setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc1.setOption("partName","string");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults1 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _confirmWorkSheetOperation1 = new com.ibm.ws.webservices.engine.description.OperationDesc("confirmWorkSheet", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://gpbs.webservice.linkage.com", "confirmWorkSheet"), _params1, _returnDesc1, _faults1, "");
        _confirmWorkSheetOperation1.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "confirmWorkSheetRequest"));
        _confirmWorkSheetOperation1.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheetService"));
        _confirmWorkSheetOperation1.setOption("inoutOrderingReq","false");
        _confirmWorkSheetOperation1.setOption("inputName","confirmWorkSheetRequest");
        _confirmWorkSheetOperation1.setOption("inputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _confirmWorkSheetOperation1.setOption("ResponseNamespace","http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        _confirmWorkSheetOperation1.setOption("buildNum","cf170819.19");
        _confirmWorkSheetOperation1.setOption("outputName","confirmWorkSheetResponse");
        _confirmWorkSheetOperation1.setOption("targetNamespace","http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        _confirmWorkSheetOperation1.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "confirmWorkSheetResponse"));
        _confirmWorkSheetOperation1.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheet"));
        _confirmWorkSheetOperation1.setOption("outputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _confirmWorkSheetOperation1.setUse(com.ibm.ws.webservices.engine.enumtype.Use.ENCODED);
        _confirmWorkSheetOperation1.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return _confirmWorkSheetOperation1;

    }

    private int _confirmWorkSheetIndex1 = 1;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getconfirmWorkSheetInvoke1(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_confirmWorkSheetIndex1];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(CRMProcessSheetSoapBindingStub._confirmWorkSheetOperation1);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            super.primeMessageContext(mc);
            super.messageContexts[_confirmWorkSheetIndex1] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String confirmWorkSheet(java.lang.Integer sheetType, java.lang.Integer serviceType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getconfirmWorkSheetInvoke1(new java.lang.Object[] {sheetType, serviceType, serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail}).invoke();

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

    private static com.ibm.ws.webservices.engine.description.OperationDesc _notifyWorkSheetOperation2 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getnotifyWorkSheetOperation2() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params2 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
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
        _params2[0].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params2[0].setOption("partName","int");
        _params2[1].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params2[1].setOption("partName","int");
        _params2[2].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params2[2].setOption("partName","string");
        _params2[3].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params2[3].setOption("partName","string");
        _params2[4].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params2[4].setOption("partName","string");
        _params2[5].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params2[5].setOption("partName","string");
        _params2[6].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params2[6].setOption("partName","string");
        _params2[7].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params2[7].setOption("partName","string");
        _params2[8].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params2[8].setOption("partName","string");
        _params2[9].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params2[9].setOption("partName","string");
        _params2[10].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params2[10].setOption("partName","string");
        _params2[11].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params2[11].setOption("partName","string");
        _params2[12].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params2[12].setOption("partName","string");
        _params2[13].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params2[13].setOption("partName","string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc2 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "notifyWorkSheetReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false); 
        _returnDesc2.setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc2.setOption("partName","string");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults2 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _notifyWorkSheetOperation2 = new com.ibm.ws.webservices.engine.description.OperationDesc("notifyWorkSheet", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://gpbs.webservice.linkage.com", "notifyWorkSheet"), _params2, _returnDesc2, _faults2, "");
        _notifyWorkSheetOperation2.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "notifyWorkSheetRequest"));
        _notifyWorkSheetOperation2.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheetService"));
        _notifyWorkSheetOperation2.setOption("inoutOrderingReq","false");
        _notifyWorkSheetOperation2.setOption("inputName","notifyWorkSheetRequest");
        _notifyWorkSheetOperation2.setOption("inputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _notifyWorkSheetOperation2.setOption("ResponseNamespace","http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        _notifyWorkSheetOperation2.setOption("buildNum","cf170819.19");
        _notifyWorkSheetOperation2.setOption("outputName","notifyWorkSheetResponse");
        _notifyWorkSheetOperation2.setOption("targetNamespace","http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        _notifyWorkSheetOperation2.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "notifyWorkSheetResponse"));
        _notifyWorkSheetOperation2.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheet"));
        _notifyWorkSheetOperation2.setOption("outputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _notifyWorkSheetOperation2.setUse(com.ibm.ws.webservices.engine.enumtype.Use.ENCODED);
        _notifyWorkSheetOperation2.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return _notifyWorkSheetOperation2;

    }

    private int _notifyWorkSheetIndex2 = 2;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getnotifyWorkSheetInvoke2(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_notifyWorkSheetIndex2];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(CRMProcessSheetSoapBindingStub._notifyWorkSheetOperation2);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            super.primeMessageContext(mc);
            super.messageContexts[_notifyWorkSheetIndex2] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String notifyWorkSheet(java.lang.Integer sheetType, java.lang.Integer serviceType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getnotifyWorkSheetInvoke2(new java.lang.Object[] {sheetType, serviceType, serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail}).invoke();

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

    private static com.ibm.ws.webservices.engine.description.OperationDesc _replyWorkSheetOperation3 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getreplyWorkSheetOperation3() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params3 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
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
        _params3[0].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params3[0].setOption("partName","int");
        _params3[1].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params3[1].setOption("partName","int");
        _params3[2].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params3[2].setOption("partName","string");
        _params3[3].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params3[3].setOption("partName","string");
        _params3[4].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params3[4].setOption("partName","string");
        _params3[5].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params3[5].setOption("partName","string");
        _params3[6].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params3[6].setOption("partName","string");
        _params3[7].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params3[7].setOption("partName","string");
        _params3[8].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params3[8].setOption("partName","string");
        _params3[9].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params3[9].setOption("partName","string");
        _params3[10].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params3[10].setOption("partName","string");
        _params3[11].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params3[11].setOption("partName","string");
        _params3[12].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params3[12].setOption("partName","string");
        _params3[13].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params3[13].setOption("partName","string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc3 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "replyWorkSheetReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false); 
        _returnDesc3.setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc3.setOption("partName","string");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults3 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _replyWorkSheetOperation3 = new com.ibm.ws.webservices.engine.description.OperationDesc("replyWorkSheet", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://gpbs.webservice.linkage.com", "replyWorkSheet"), _params3, _returnDesc3, _faults3, "");
        _replyWorkSheetOperation3.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "replyWorkSheetRequest"));
        _replyWorkSheetOperation3.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheetService"));
        _replyWorkSheetOperation3.setOption("inoutOrderingReq","false");
        _replyWorkSheetOperation3.setOption("inputName","replyWorkSheetRequest");
        _replyWorkSheetOperation3.setOption("inputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _replyWorkSheetOperation3.setOption("ResponseNamespace","http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        _replyWorkSheetOperation3.setOption("buildNum","cf170819.19");
        _replyWorkSheetOperation3.setOption("outputName","replyWorkSheetResponse");
        _replyWorkSheetOperation3.setOption("targetNamespace","http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        _replyWorkSheetOperation3.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "replyWorkSheetResponse"));
        _replyWorkSheetOperation3.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheet"));
        _replyWorkSheetOperation3.setOption("outputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _replyWorkSheetOperation3.setUse(com.ibm.ws.webservices.engine.enumtype.Use.ENCODED);
        _replyWorkSheetOperation3.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return _replyWorkSheetOperation3;

    }

    private int _replyWorkSheetIndex3 = 3;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getreplyWorkSheetInvoke3(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_replyWorkSheetIndex3];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(CRMProcessSheetSoapBindingStub._replyWorkSheetOperation3);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            
            super.primeMessageContext(mc);
            super.messageContexts[_replyWorkSheetIndex3] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String replyWorkSheet(java.lang.Integer sheetType, java.lang.Integer serviceType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getreplyWorkSheetInvoke3(new java.lang.Object[] {sheetType, serviceType, serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail}).invoke();

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

    private static com.ibm.ws.webservices.engine.description.OperationDesc _withdrawWorkSheetOperation4 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getwithdrawWorkSheetOperation4() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params4 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
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
        _params4[0].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params4[0].setOption("partName","int");
        _params4[1].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}int");
        _params4[1].setOption("partName","int");
        _params4[2].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params4[2].setOption("partName","string");
        _params4[3].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params4[3].setOption("partName","string");
        _params4[4].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params4[4].setOption("partName","string");
        _params4[5].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params4[5].setOption("partName","string");
        _params4[6].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params4[6].setOption("partName","string");
        _params4[7].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params4[7].setOption("partName","string");
        _params4[8].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params4[8].setOption("partName","string");
        _params4[9].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params4[9].setOption("partName","string");
        _params4[10].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params4[10].setOption("partName","string");
        _params4[11].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params4[11].setOption("partName","string");
        _params4[12].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params4[12].setOption("partName","string");
        _params4[13].setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _params4[13].setOption("partName","string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc4 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "withdrawWorkSheetReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, true, false, false, false, true, false); 
        _returnDesc4.setOption("partQNameString","{http://schemas.xmlsoap.org/soap/encoding/}string");
        _returnDesc4.setOption("partName","string");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults4 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _withdrawWorkSheetOperation4 = new com.ibm.ws.webservices.engine.description.OperationDesc("withdrawWorkSheet", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://gpbs.webservice.linkage.com", "withdrawWorkSheet"), _params4, _returnDesc4, _faults4, "");
        _withdrawWorkSheetOperation4.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "withdrawWorkSheetRequest"));
        _withdrawWorkSheetOperation4.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheetService"));
        _withdrawWorkSheetOperation4.setOption("inoutOrderingReq","false");
        _withdrawWorkSheetOperation4.setOption("inputName","withdrawWorkSheetRequest");
        _withdrawWorkSheetOperation4.setOption("inputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _withdrawWorkSheetOperation4.setOption("ResponseNamespace","http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        _withdrawWorkSheetOperation4.setOption("buildNum","cf170819.19");
        _withdrawWorkSheetOperation4.setOption("outputName","withdrawWorkSheetResponse");
        _withdrawWorkSheetOperation4.setOption("targetNamespace","http://client.crm.eoms.boco.com/services/CRMProcessSheet");
        _withdrawWorkSheetOperation4.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "withdrawWorkSheetResponse"));
        _withdrawWorkSheetOperation4.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://client.crm.eoms.boco.com/services/CRMProcessSheet", "CRMProcessSheet"));
        _withdrawWorkSheetOperation4.setOption("outputEncodingStyle","http://schemas.xmlsoap.org/soap/encoding/");
        _withdrawWorkSheetOperation4.setUse(com.ibm.ws.webservices.engine.enumtype.Use.ENCODED);
        _withdrawWorkSheetOperation4.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.RPC);
        return _withdrawWorkSheetOperation4;

    }

    private int _withdrawWorkSheetIndex4 = 4;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getwithdrawWorkSheetInvoke4(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_withdrawWorkSheetIndex4];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(CRMProcessSheetSoapBindingStub._withdrawWorkSheetOperation4);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            super.primeMessageContext(mc);
            super.messageContexts[_withdrawWorkSheetIndex4] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String withdrawWorkSheet(java.lang.Integer sheetType, java.lang.Integer serviceType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getwithdrawWorkSheetInvoke4(new java.lang.Object[] {sheetType, serviceType, serialNo, serSupplier, serCaller, callerPwd, callTime, attachRef, opPerson, opCorp, opDepart, opContact, opTime, opDetail}).invoke();

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
        _confirmWorkSheetOperation1 = _getconfirmWorkSheetOperation1();
        _replyWorkSheetOperation3 = _getreplyWorkSheetOperation3();
        _notifyWorkSheetOperation2 = _getnotifyWorkSheetOperation2();
        _isAliveOperation0 = _getisAliveOperation0();
        _withdrawWorkSheetOperation4 = _getwithdrawWorkSheetOperation4();
    }

    static {
       _staticInit();
    }
}
