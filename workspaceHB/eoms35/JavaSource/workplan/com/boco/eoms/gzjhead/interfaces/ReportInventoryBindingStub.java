/**
 * ReportInventoryBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

public class ReportInventoryBindingStub extends org.apache.axis.client.Stub implements com.boco.eoms.gzjhead.interfaces.ReportInventoryPortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[1];
        org.apache.axis.description.OperationDesc oper;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ReportInventory");
        oper.addParameter(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "ReportInventoryRequest"), new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", ">ReportInventoryRequest"), com.boco.eoms.gzjhead.interfaces._ReportInventoryRequest.class, org.apache.axis.description.ParameterDesc.IN, false, false);
        oper.setReturnType(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", ">ReportInventoryResponse"));
        oper.setReturnClass(com.boco.eoms.gzjhead.interfaces._ReportInventoryResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "ReportInventoryResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "FaultDetails"),
                      "cn.com.chinaunicom.eoms.worktaskschedule.ReportExecuteService.FaultDetails",
                      new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), 
                      false
                     ));
        _operations[0] = oper;

    }

    public ReportInventoryBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ReportInventoryBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ReportInventoryBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", ">ReportInventoryRequest");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces._ReportInventoryRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "attachInfoListType");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces.AttachInfoListType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "attachInfoType");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces.AttachInfoType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", "noteType");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces.NoteType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(simplesf);
            cachedDeserFactories.add(simpledf);

            qName = new javax.xml.namespace.QName("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService", ">ReportInventoryResponse");
            cachedSerQNames.add(qName);
            cls = com.boco.eoms.gzjhead.interfaces._ReportInventoryResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

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

    public com.boco.eoms.gzjhead.interfaces._ReportInventoryResponse reportInventory(com.boco.eoms.gzjhead.interfaces._ReportInventoryRequest parameters) throws java.rmi.RemoteException, com.boco.eoms.gzjhead.interfaces.FaultDetails {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
//        _call.setSOAPActionURI("http://eoms.chinaunicom.com.cn/worktaskschedule/ReportExecuteService/ReportInventory");
        _call.setSOAPActionURI("http://10.1.45.202:7070/ScheduleService/services/ReportInventoryPort");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "ReportInventory"));

        setRequestHeaders(_call);
        setAttachments(_call);
        
        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.boco.eoms.gzjhead.interfaces._ReportInventoryResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.boco.eoms.gzjhead.interfaces._ReportInventoryResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.boco.eoms.gzjhead.interfaces._ReportInventoryResponse.class);
            }
        }
    }

}
