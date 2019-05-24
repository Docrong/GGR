/**
 * SheetStateSync3SoapStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf130745.06 v111407041203
 */

package com.chinamobile.eoms.service;

public class SheetStateSync3SoapStub extends com.ibm.ws.webservices.engine.client.Stub implements com.chinamobile.eoms.service.SheetStateSync3Soap {
    public SheetStateSync3SoapStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
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
        javax.xml.rpc.encoding.TypeMapping tm = super.getTypeMapping(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
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
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "isAliveResult"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, true, true, false); 
        _returnDesc0.setOption("outputPosition","0");
        _returnDesc0.setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("partName","string");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _isAliveOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("isAlive", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "isAlive"), _params0, _returnDesc0, _faults0, "http://service.eoms.chinamobile.com/SheetStateSync/isAlive");
        _isAliveOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "isAliveSoapIn"));
        _isAliveOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "SheetStateSync3"));
        _isAliveOperation0.setOption("ResponseNamespace","http://service.eoms.chinamobile.com/SheetStateSync");
        _isAliveOperation0.setOption("buildNum","cf130745.06");
        _isAliveOperation0.setOption("ResponseLocalPart","isAliveResponse");
        _isAliveOperation0.setOption("targetNamespace","http://service.eoms.chinamobile.com/SheetStateSync");
        _isAliveOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "isAliveSoapOut"));
        _isAliveOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "SheetStateSync3Soap"));
        _isAliveOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _isAliveOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _isAliveOperation0;

    }

    private int _isAliveIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getisAliveInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_isAliveIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(SheetStateSync3SoapStub._isAliveOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("http://service.eoms.chinamobile.com/SheetStateSync/isAlive");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.ws.webservices.engine.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            mc.setProperty(com.ibm.ws.webservices.engine.WebServicesEngine.PROP_DOMULTIREFS, Boolean.FALSE);
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

    public java.lang.String isAlive() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getisAliveInvoke0(new java.lang.Object[] {}).invoke();

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

    private static com.ibm.ws.webservices.engine.description.OperationDesc _syncSheetStateOperation1 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getsyncSheetStateOperation1() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params1 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "serSupplier"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "serCaller"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "callerPwd"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "callTime"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "opDetail"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
          };
        _params1[0].setOption("inputPosition","0");
        _params1[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params1[0].setOption("partName","string");
        _params1[1].setOption("inputPosition","1");
        _params1[1].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params1[1].setOption("partName","string");
        _params1[2].setOption("inputPosition","2");
        _params1[2].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params1[2].setOption("partName","string");
        _params1[3].setOption("inputPosition","3");
        _params1[3].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params1[3].setOption("partName","string");
        _params1[4].setOption("inputPosition","4");
        _params1[4].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params1[4].setOption("partName","string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc1 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "syncSheetStateResult"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, true, true, false); 
        _returnDesc1.setOption("outputPosition","0");
        _returnDesc1.setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc1.setOption("partName","string");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults1 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _syncSheetStateOperation1 = new com.ibm.ws.webservices.engine.description.OperationDesc("syncSheetState", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "syncSheetState"), _params1, _returnDesc1, _faults1, "http://service.eoms.chinamobile.com/SheetStateSync/syncSheetState");
        _syncSheetStateOperation1.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "syncSheetStateSoapIn"));
        _syncSheetStateOperation1.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "SheetStateSync3"));
        _syncSheetStateOperation1.setOption("ResponseNamespace","http://service.eoms.chinamobile.com/SheetStateSync");
        _syncSheetStateOperation1.setOption("buildNum","cf130745.06");
        _syncSheetStateOperation1.setOption("ResponseLocalPart","syncSheetStateResponse");
        _syncSheetStateOperation1.setOption("targetNamespace","http://service.eoms.chinamobile.com/SheetStateSync");
        _syncSheetStateOperation1.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "syncSheetStateSoapOut"));
        _syncSheetStateOperation1.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.eoms.chinamobile.com/SheetStateSync", "SheetStateSync3Soap"));
        _syncSheetStateOperation1.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _syncSheetStateOperation1.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _syncSheetStateOperation1;

    }

    private int _syncSheetStateIndex1 = 1;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getsyncSheetStateInvoke1(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_syncSheetStateIndex1];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(SheetStateSync3SoapStub._syncSheetStateOperation1);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("http://service.eoms.chinamobile.com/SheetStateSync/syncSheetState");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.ws.webservices.engine.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            mc.setProperty(com.ibm.ws.webservices.engine.WebServicesEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_syncSheetStateIndex1] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String syncSheetState(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getsyncSheetStateInvoke1(new java.lang.Object[] {serSupplier, serCaller, callerPwd, callTime, opDetail}).invoke();

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
        _isAliveOperation0 = _getisAliveOperation0();
        _syncSheetStateOperation1 = _getsyncSheetStateOperation1();
    }

    static {
       _staticInit();
    }
}
