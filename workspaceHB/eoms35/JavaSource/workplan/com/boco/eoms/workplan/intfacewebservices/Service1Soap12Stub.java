package com.boco.eoms.workplan.intfacewebservices;

import org.w3c.dom.Document;
import javax.xml.parsers.*;
import javax.swing.text.*;
import org.xml.sax.*;
import java.io.*;
 


public class Service1Soap12Stub extends org.apache.axis.client.Stub implements com.boco.eoms.workplan.intfacewebservices.Service1Soap {
	   private java.util.Vector cachedSerClasses = new java.util.Vector();
	    private java.util.Vector cachedSerQNames = new java.util.Vector();
	    private java.util.Vector cachedSerFactories = new java.util.Vector();
	    private java.util.Vector cachedDeserFactories = new java.util.Vector();

	    static org.apache.axis.description.OperationDesc [] _operations;

	    static {
	        _operations = new org.apache.axis.description.OperationDesc[6];
	        _initOperationDesc1();
	    }

	    private static void _initOperationDesc1(){
	        org.apache.axis.description.OperationDesc oper;
	        org.apache.axis.description.ParameterDesc param;
	        oper = new org.apache.axis.description.OperationDesc();
	        oper.setName("GetNetInfo");
	        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
	        oper.setReturnClass(java.lang.String.class);
	        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetNetInfoResult"));
	        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
	        oper.setUse(org.apache.axis.constants.Use.LITERAL);
	        _operations[0] = oper;

	        oper = new org.apache.axis.description.OperationDesc();
	        oper.setName("GetCmdTaskInfo");
	        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "netID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
	        oper.addParameter(param);
	        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
	        oper.addParameter(param);
	        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
	        oper.setReturnClass(java.lang.String.class);
	        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetCmdTaskInfoResult"));
	        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
	        oper.setUse(org.apache.axis.constants.Use.LITERAL);
	        _operations[1] = oper;

	        oper = new org.apache.axis.description.OperationDesc();
	        oper.setName("GetTaskInfo");
	        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "netID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
	        oper.addParameter(param);
	        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
	        oper.addParameter(param);
	        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
	        oper.setReturnClass(java.lang.String.class);
	        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetTaskInfoResult"));
	        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
	        oper.setUse(org.apache.axis.constants.Use.LITERAL);
	        _operations[2] = oper;

	        oper = new org.apache.axis.description.OperationDesc();
	        oper.setName("GetPerformCmdResultInfo");
	        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "cmdID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
	        oper.addParameter(param);
	        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "netID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
	        oper.addParameter(param);
	        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "startTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
	        oper.addParameter(param);
	        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "endTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
	        oper.addParameter(param);
	        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
	        oper.setReturnClass(java.lang.String.class);
	        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetPerformCmdResultInfoResult"));
	        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
	        oper.setUse(org.apache.axis.constants.Use.LITERAL);
	        _operations[3] = oper;

	        oper = new org.apache.axis.description.OperationDesc();
	        oper.setName("GetPerformTaskResultInfo");
	        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "taskID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
	        oper.addParameter(param);
	        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "startTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
	        oper.addParameter(param);
	        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "endTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
	        oper.addParameter(param);
	        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
	        oper.setReturnClass(java.lang.String.class);
	        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetPerformTaskResultInfoResult"));
	        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
	        oper.setUse(org.apache.axis.constants.Use.LITERAL);
	        _operations[4] = oper;

	        oper = new org.apache.axis.description.OperationDesc();
	        oper.setName("GetTaskCmdResultInfo");
	        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "taskID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
	        oper.addParameter(param);
	        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "netID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
	        oper.addParameter(param);
	        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "startTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
	        oper.addParameter(param);
	        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "endTime"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
	        oper.addParameter(param);
	        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
	        oper.setReturnClass(java.lang.String.class);
	        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "GetTaskCmdResultInfoResult"));
	        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
	        oper.setUse(org.apache.axis.constants.Use.LITERAL);
	        _operations[5] = oper;

	    }

	    public Service1Soap12Stub() throws org.apache.axis.AxisFault {
	         this(null);
	    }

	    public Service1Soap12Stub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
	         this(service);
	         super.cachedEndpoint = endpointURL;
	    }

	    public Service1Soap12Stub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
	        if (service == null) {
	            super.service = new org.apache.axis.client.Service();
	        } else {
	            super.service = service;
	        }
	        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.1");
	    }

	    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
	        try {
	            org.apache.axis.client.Call _call = super._createCall();
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
	            return _call;
	        }
	        catch (java.lang.Throwable _t) {
	            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
	        }
	    }

	    public org.w3c.dom.Document getNetInfo() throws java.rmi.RemoteException {
	        if (super.cachedEndpoint == null) {
	            throw new org.apache.axis.NoEndPointException();
	        }
	        org.apache.axis.client.Call _call = createCall();
	        _call.setOperation(_operations[0]);
	        _call.setUseSOAPAction(true);
	        _call.setSOAPActionURI("http://tempuri.org/GetNetInfo");
	        _call.setEncodingStyle(null);
	        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
	        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
	        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
	        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "GetNetInfo"));

	        setRequestHeaders(_call);
	        setAttachments(_call);
	 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

	        if (_resp instanceof java.rmi.RemoteException) {
	            throw (java.rmi.RemoteException)_resp;
	        }
	        else {
	            extractAttachments(_call);
	            if ("".equals((String) _resp)) {
	    	    	return parseXMLDocument(new String(
	    	          	"<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>"  
	    	          	+"<UDSObjectList><UDSObject>"
	    				+"<Attributes><executeResult>return null!</executeResult>" 
	    				+"</Attributes></UDSObject></UDSObjectList>"));
	            }
	    		try
	    		{return (org.w3c.dom.Document) org.apache.axis.utils.JavaUtils
	    						.convert(parseXMLDocument((String) _resp),
	    								org.w3c.dom.Document.class);
	    		}catch(RuntimeException e)
	    		{
	    		return parseXMLDocument(new String(
	    	          	"<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>"  
	    	          	+"<UDSObjectList><UDSObject>"
	    				+"<Attributes><executeResult>return null!</executeResult>" 
	    				+"</Attributes></UDSObject></UDSObjectList>"));}
	    			 
	    		}
	            
	            
	            
	            
	            
	            
	            /*
	            try {
	                return (java.lang.String) _resp;
	            } catch (java.lang.Exception _exception) {
	                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
	            }*/
	  } catch (org.apache.axis.AxisFault axisFaultException) {
	  throw axisFaultException;
	}
	    }

	    public org.w3c.dom.Document getCmdTaskInfo(java.lang.String netID, java.lang.String userID) throws java.rmi.RemoteException {
	        if (super.cachedEndpoint == null) {
	            throw new org.apache.axis.NoEndPointException();
	        }
	        org.apache.axis.client.Call _call = createCall();
	        _call.setOperation(_operations[1]);
	        _call.setUseSOAPAction(true);
	        _call.setSOAPActionURI("http://tempuri.org/GetCmdTaskInfo");
	        _call.setEncodingStyle(null);
	        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
	        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
	        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
	        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "GetCmdTaskInfo"));

	        setRequestHeaders(_call);
	        setAttachments(_call);
	 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {netID, userID});

	        if (_resp instanceof java.rmi.RemoteException) {
	            throw (java.rmi.RemoteException)_resp;
	        }
	        else {
	            extractAttachments(_call);
	            if ("".equals((String) _resp)) {
	    	    	return parseXMLDocument(new String(
	    	          	"<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>"  
	    	          	+"<UDSObjectList><UDSObject>"
	    				+"<Attributes><executeResult>return null!</executeResult>" 
	    				+"</Attributes></UDSObject></UDSObjectList>"));
	            }
	    		try
	    		{return (org.w3c.dom.Document) org.apache.axis.utils.JavaUtils
	    						.convert(parseXMLDocument((String) _resp),
	    								org.w3c.dom.Document.class);
	    		}catch(RuntimeException e)
	    		{
	    		return parseXMLDocument(new String(
	    	          	"<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>"  
	    	          	+"<UDSObjectList><UDSObject>"
	    				+"<Attributes><executeResult>return null!</executeResult>" 
	    				+"</Attributes></UDSObject></UDSObjectList>"));}
	    			 
	    		}
	        
	  } catch (org.apache.axis.AxisFault axisFaultException) {
	  throw axisFaultException;
	}
	    }

	    public org.w3c.dom.Document getTaskInfo(java.lang.String netID, java.lang.String userID) throws java.rmi.RemoteException {
	        if (super.cachedEndpoint == null) {
	            throw new org.apache.axis.NoEndPointException();
	        }
	        org.apache.axis.client.Call _call = createCall();
	        _call.setOperation(_operations[2]);
	        _call.setUseSOAPAction(true);
	        _call.setSOAPActionURI("http://tempuri.org/GetTaskInfo");
	        _call.setEncodingStyle(null);
	        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
	        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
	        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
	        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "GetTaskInfo"));

	        setRequestHeaders(_call);
	        setAttachments(_call);
	 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {netID, userID});

	        if (_resp instanceof java.rmi.RemoteException) {
	            throw (java.rmi.RemoteException)_resp;
	        }
	        else {
	            extractAttachments(_call);
	            if ("".equals((String) _resp)) {
	    	    	return parseXMLDocument(new String(
	    	          	"<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>"  
	    	          	+"<UDSObjectList><UDSObject>"
	    				+"<Attributes><executeResult>return null!</executeResult>" 
	    				+"</Attributes></UDSObject></UDSObjectList>"));
	            }
	    		try
	    		{return (org.w3c.dom.Document) org.apache.axis.utils.JavaUtils
	    						.convert(parseXMLDocument((String) _resp),
	    								org.w3c.dom.Document.class);
	    		}catch(RuntimeException e)
	    		{
	    		return parseXMLDocument(new String(
	    	          	"<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>"  
	    	          	+"<UDSObjectList><UDSObject>"
	    				+"<Attributes><executeResult>return null!</executeResult>" 
	    				+"</Attributes></UDSObject></UDSObjectList>"));}
	        }
	  } catch (org.apache.axis.AxisFault axisFaultException) {
	  throw axisFaultException;
	}
	    }

	    public org.w3c.dom.Document getPerformCmdResultInfo(java.lang.String cmdID, java.lang.String netID, java.lang.String startTime, java.lang.String endTime) throws java.rmi.RemoteException {
	        if (super.cachedEndpoint == null) {
	            throw new org.apache.axis.NoEndPointException();
	        }
	        org.apache.axis.client.Call _call = createCall();
	        _call.setOperation(_operations[3]);
	        _call.setUseSOAPAction(true);
	        _call.setSOAPActionURI("http://tempuri.org/GetPerformCmdResultInfo");
	        _call.setEncodingStyle(null);
	        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
	        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
	        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
	        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "GetPerformCmdResultInfo"));

	        setRequestHeaders(_call);
	        setAttachments(_call);
	 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cmdID, netID, startTime, endTime});

	        if (_resp instanceof java.rmi.RemoteException) {
	            throw (java.rmi.RemoteException)_resp;
	        }
	        else {
	            extractAttachments(_call);
	            if ("".equals((String) _resp)) {
	    	    	return parseXMLDocument(new String(
	    	          	"<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>"  
	    	          	+"<UDSObjectList><UDSObject>"
	    				+"<Attributes><executeResult>return null!</executeResult>" 
	    				+"</Attributes></UDSObject></UDSObjectList>"));
	            }
	    		try
	    		{return (org.w3c.dom.Document) org.apache.axis.utils.JavaUtils
	    						.convert(parseXMLDocument((String) _resp),
	    								org.w3c.dom.Document.class);
	    		}catch(RuntimeException e)
	    		{
	    		return parseXMLDocument(new String(
	    	          	"<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>"  
	    	          	+"<UDSObjectList><UDSObject>"
	    				+"<Attributes><executeResult>return null!</executeResult>" 
	    				+"</Attributes></UDSObject></UDSObjectList>"));}
	    			 
	    		}
	  } catch (org.apache.axis.AxisFault axisFaultException) {
	  throw axisFaultException;
	}
	    }

	    public org.w3c.dom.Document getPerformTaskResultInfo(java.lang.String taskID, java.lang.String startTime, java.lang.String endTime) throws java.rmi.RemoteException {
	        if (super.cachedEndpoint == null) {
	            throw new org.apache.axis.NoEndPointException();
	        }
	        org.apache.axis.client.Call _call = createCall();
	        _call.setOperation(_operations[4]);
	        _call.setUseSOAPAction(true);
	        _call.setSOAPActionURI("http://tempuri.org/GetPerformTaskResultInfo");
	        _call.setEncodingStyle(null);
	        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
	        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
	        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
	        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "GetPerformTaskResultInfo"));

	        setRequestHeaders(_call);
	        setAttachments(_call);
	 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {taskID, startTime, endTime});

	        if (_resp instanceof java.rmi.RemoteException) {
	            throw (java.rmi.RemoteException)_resp;
	        }
	        else {
	            extractAttachments(_call);
	            if ("".equals((String) _resp)) {
	    	    	return parseXMLDocument(new String(
	    	          	"<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>"  
	    	          	+"<UDSObjectList><UDSObject>"
	    				+"<Attributes><executeResult>return null!</executeResult>" 
	    				+"</Attributes></UDSObject></UDSObjectList>"));
	            }
	    		try
	    		{return (org.w3c.dom.Document) org.apache.axis.utils.JavaUtils
	    						.convert(parseXMLDocument((String) _resp),
	    								org.w3c.dom.Document.class);
	    		}catch(RuntimeException e)
	    		{
	    		return parseXMLDocument(new String(
	    	          	"<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>"  
	    	          	+"<UDSObjectList><UDSObject>"
	    				+"<Attributes><executeResult>return null!</executeResult>" 
	    				+"</Attributes></UDSObject></UDSObjectList>"));}
	    			 
	    		}
	  } catch (org.apache.axis.AxisFault axisFaultException) {
	  throw axisFaultException;
	}
	    }

	    public org.w3c.dom.Document getTaskCmdResultInfo(java.lang.String taskID, java.lang.String netID, java.lang.String startTime, java.lang.String endTime) throws java.rmi.RemoteException {
	        if (super.cachedEndpoint == null) {
	            throw new org.apache.axis.NoEndPointException();
	        }
	        org.apache.axis.client.Call _call = createCall();
	        _call.setOperation(_operations[5]);
	        _call.setUseSOAPAction(true);
	        _call.setSOAPActionURI("http://tempuri.org/GetTaskCmdResultInfo");
	        _call.setEncodingStyle(null);
	        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
	        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
	        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
	        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "GetTaskCmdResultInfo"));

	        setRequestHeaders(_call);
	        setAttachments(_call);
	 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {taskID, netID, startTime, endTime});

	        if (_resp instanceof java.rmi.RemoteException) {
	            throw (java.rmi.RemoteException)_resp;
	        }
	        else {
	            extractAttachments(_call);
	            if ("".equals((String) _resp)) {
	    	    	return parseXMLDocument(new String(
	    	          	"<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>"  
	    	          	+"<UDSObjectList><UDSObject>"
	    				+"<Attributes><executeResult>return null!</executeResult>" 
	    				+"</Attributes></UDSObject></UDSObjectList>"));
	            }
	    		try
	    		{return (org.w3c.dom.Document) org.apache.axis.utils.JavaUtils
	    						.convert(parseXMLDocument((String) _resp),
	    								org.w3c.dom.Document.class);
	    		}catch(RuntimeException e)
	    		{
	    		return parseXMLDocument(new String(
	    	          	"<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\"?>"  
	    	          	+"<UDSObjectList><UDSObject>"
	    				+"<Attributes><executeResult>return null!</executeResult>" 
	    				+"</Attributes></UDSObject></UDSObjectList>"));}
	    			 
	    		}
	  } catch (org.apache.axis.AxisFault axisFaultException) {
	  throw axisFaultException;
	}
	    }
		/**
		 * �������һ��XML Stringת����һ��org.w3c.dom.Document���󷵻ء�
		 * 
		 * @param xmlString
		 *            һ����XML�淶���ַ��
		 * @return a Document
		 */
		public static Document parseXMLDocument(String xmlString) {
			if (xmlString == null) {
				throw new IllegalArgumentException();
			}
			try {
				return newDocumentBuilder().parse(
						new InputSource(new StringReader(xmlString)));
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		/**
		 * ��ʼ��һ��DocumentBuilder
		 * 
		 * @return a DocumentBuilder
		 * @throws ParserConfigurationException
		 */
		public static DocumentBuilder newDocumentBuilder()
				throws ParserConfigurationException {
			return newDocumentBuilderFactory().newDocumentBuilder();
		}

		/**
		 * ��ʼ��һ��DocumentBuilderFactory
		 * 
		 * @return a DocumentBuilderFactory
		 */
		public static DocumentBuilderFactory newDocumentBuilderFactory() {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			return dbf;




		}
	
}
