/**
 * ReportExecuteBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

public class ReportExecuteBindingStub extends org.apache.axis.client.Stub implements com.boco.eoms.gzjhead.interfaces.ReportExecutePortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[2];
        org.apache.axis.description.OperationDesc oper;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ReportForm");
        oper.addParameter(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "ReportFormRequest"), new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", ">ReportFormRequest"), com.boco.eoms.gzjhead.interfaces._ReportFormRequest.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", ">ReportFormResponse"));
        oper.setReturnClass(com.boco.eoms.gzjhead.interfaces._ReportFormResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "ReportFormResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "FaultDetails"),
                      "cn.com.chinaunicom.eoms.worktaskschedule.ReportExecuteService.FaultDetails",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), 
                      false
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ModifyForm");
        oper.addParameter(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "ModifyFormRequest"), new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", ">ModifyFormRequest"), com.boco.eoms.gzjhead.interfaces._ModifyFormRequest.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", ">ModifyFormResponse"));
        oper.setReturnClass(com.boco.eoms.gzjhead.interfaces._ModifyFormResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "ModifyFormResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "FaultDetails"),
                      "cn.com.chinaunicom.eoms.worktaskschedule.ReportExecuteService.FaultDetails",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), 
                      false
                     ));
        _operations[1] = oper;

    }

    public ReportExecuteBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ReportExecuteBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ReportExecuteBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", ">ReportFormRequest");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces._ReportFormRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "neNumberType");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces.NeNumberType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(simplesf);
            cachedDeserFactories.add(simpledf);

            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", ">ModifyFormRequest");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces._ModifyFormRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "attachInfoType");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces.AttachInfoType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "neListType");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces.NeListType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "reportTypeType");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces.ReportTypeType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(simplesf);
            cachedDeserFactories.add(simpledf);

            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "modifyTypeType");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces.ModifyTypeType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(simplesf);
            cachedDeserFactories.add(simpledf);

            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "attachInfoListType");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces.AttachInfoListType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", ">ModifyFormResponse");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces._ModifyFormResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", ">ReportFormResponse");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces._ReportFormResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "noteType");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces.NoteType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(simplesf);
            cachedDeserFactories.add(simpledf);

    }

    private org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call =
                    (org.apache.axis.client.Call) super.service.createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                        java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                        _call.registerTypeMapping(cls, qName, sf, df, false);
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", t);
        }
    }

    public com.boco.eoms.gzjhead.interfaces._ReportFormResponse reportForm(com.boco.eoms.gzjhead.interfaces._ReportFormRequest parameters) throws java.rmi.RemoteException, com.boco.eoms.gzjhead.interfaces.FaultDetails {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService/ReportForm");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ReportForm"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.boco.eoms.gzjhead.interfaces._ReportFormResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.boco.eoms.gzjhead.interfaces._ReportFormResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.boco.eoms.gzjhead.interfaces._ReportFormResponse.class);
            }
        }
    }

    public com.boco.eoms.gzjhead.interfaces._ModifyFormResponse modifyForm(com.boco.eoms.gzjhead.interfaces._ModifyFormRequest parameters) throws java.rmi.RemoteException, com.boco.eoms.gzjhead.interfaces.FaultDetails {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService/ModifyForm");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ModifyForm"));

        setRequestHeaders(_call);
        setAttachments(_call);
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.boco.eoms.gzjhead.interfaces._ModifyFormResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (_ModifyFormResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.boco.eoms.gzjhead.interfaces._ModifyFormResponse.class);
            }
        }
    }

}
