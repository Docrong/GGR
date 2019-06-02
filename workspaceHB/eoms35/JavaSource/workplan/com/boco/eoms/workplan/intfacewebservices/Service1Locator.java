/**
 * Service1Locator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2beta3 Aug 01, 2004 (05:59:22 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.workplan.intfacewebservices;

import com.boco.eoms.workplan.util.WorkplanMgrLocator;

public class Service1Locator
    extends org.apache.axis.client.Service implements com.boco.eoms.workplan.
    intfacewebservices.Service1 {

	   public Service1Locator() {
	    }


	    public Service1Locator(org.apache.axis.EngineConfiguration config) {
	        super(config);
	    }

	    public Service1Locator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
	        super(wsdlLoc, sName);
	    }

	    // Use to get a proxy class for Service1Soap12
	    private java.lang.String Service1Soap12_address = "http://10.101.16.87:8088/Service1.asmx";

	    public java.lang.String getService1Soap12Address() {
	        return Service1Soap12_address;
	    }

	    // The WSDD service name defaults to the port name.
	    private java.lang.String Service1Soap12WSDDServiceName = "Service1Soap12";

	    public java.lang.String getService1Soap12WSDDServiceName() {
	        return Service1Soap12WSDDServiceName;
	    }

	    public void setService1Soap12WSDDServiceName(java.lang.String name) {
	        Service1Soap12WSDDServiceName = name;
	    }

	    public com.boco.eoms.workplan.intfacewebservices.Service1Soap getService1Soap12() throws javax.xml.rpc.ServiceException {
	       java.net.URL endpoint;
	        try {
	            endpoint = new java.net.URL(Service1Soap12_address);
	        }
	        catch (java.net.MalformedURLException e) {
	            throw new javax.xml.rpc.ServiceException(e);
	        }
	        return getService1Soap12(endpoint);
	    }

	    public com.boco.eoms.workplan.intfacewebservices.Service1Soap getService1Soap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
	        try {
	            com.boco.eoms.workplan.intfacewebservices.Service1Soap12Stub _stub = new com.boco.eoms.workplan.intfacewebservices.Service1Soap12Stub(portAddress, this);
	            _stub.setPortName(getService1Soap12WSDDServiceName());
	            return _stub;
	        }
	        catch (org.apache.axis.AxisFault e) {
	            return null;
	        }
	    }

	    public void setService1Soap12EndpointAddress(java.lang.String address) {
	        Service1Soap12_address = address;
	    }


	    // Use to get a proxy class for Service1Soap
	    private java.lang.String Service1Soap_address = WorkplanMgrLocator.getAttributes().getIntfaceUrl();

	    public java.lang.String getService1SoapAddress() {
	        return Service1Soap_address;
	    }

	    // The WSDD service name defaults to the port name.
	    private java.lang.String Service1SoapWSDDServiceName = "Service1Soap";

	    public java.lang.String getService1SoapWSDDServiceName() {
	        return Service1SoapWSDDServiceName;
	    }

	    public void setService1SoapWSDDServiceName(java.lang.String name) {
	        Service1SoapWSDDServiceName = name;
	    }

	    public com.boco.eoms.workplan.intfacewebservices.Service1Soap getService1Soap() throws javax.xml.rpc.ServiceException {
	       java.net.URL endpoint;
	       System.out.println("Service1Soap_address:"+Service1Soap_address);
	        try {
	            endpoint = new java.net.URL(Service1Soap_address);
	        }
	        catch (java.net.MalformedURLException e) {
	            throw new javax.xml.rpc.ServiceException(e);
	        }
	        return getService1Soap(endpoint);
	    }

	    public com.boco.eoms.workplan.intfacewebservices.Service1Soap getService1Soap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
	        try {
	            com.boco.eoms.workplan.intfacewebservices.Service1SoapStub _stub = new com.boco.eoms.workplan.intfacewebservices.Service1SoapStub(portAddress, this);
	            _stub.setPortName(getService1SoapWSDDServiceName());
	            return _stub;
	        }
	        catch (org.apache.axis.AxisFault e) {
	            return null;
	        }
	    }

	    public void setService1SoapEndpointAddress(java.lang.String address) {
	        Service1Soap_address = address;
	    }

	    /**
	     * For the given interface, get the stub implementation.
	     * If this service has no port for the given interface,
	     * then ServiceException is thrown.
	     * This service has multiple ports for a given interface;
	     * the proxy implementation returned may be indeterminate.
	     */
	    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
	        try {
	            if (com.boco.eoms.workplan.intfacewebservices.Service1Soap.class.isAssignableFrom(serviceEndpointInterface)) {
	            	com.boco.eoms.workplan.intfacewebservices.Service1Soap12Stub _stub = new com.boco.eoms.workplan.intfacewebservices.Service1Soap12Stub(new java.net.URL(Service1Soap12_address), this);
	                _stub.setPortName(getService1Soap12WSDDServiceName());
	                return _stub;
	            }
	            if (com.boco.eoms.workplan.intfacewebservices.Service1Soap.class.isAssignableFrom(serviceEndpointInterface)) {
	            	com.boco.eoms.workplan.intfacewebservices.Service1SoapStub _stub = new com.boco.eoms.workplan.intfacewebservices.Service1SoapStub(new java.net.URL(Service1Soap_address), this);
	                _stub.setPortName(getService1SoapWSDDServiceName());
	                return _stub;
	            }
	        }
	        catch (java.lang.Throwable t) {
	            throw new javax.xml.rpc.ServiceException(t);
	        }
	        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
	    }

	    /**
	     * For the given interface, get the stub implementation.
	     * If this service has no port for the given interface,
	     * then ServiceException is thrown.
	     */
	    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
	        if (portName == null) {
	            return getPort(serviceEndpointInterface);
	        }
	        java.lang.String inputPortName = portName.getLocalPart();
	        if ("Service1Soap12".equals(inputPortName)) {
	            return getService1Soap12();
	        }
	        else if ("Service1Soap".equals(inputPortName)) {
	            return getService1Soap();
	        }
	        else  {
	            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
	            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
	            return _stub;
	        }
	    }

	    public javax.xml.namespace.QName getServiceName() {
	        return new javax.xml.namespace.QName("http://tempuri.org/", "Service1");
	    }

	    private java.util.HashSet ports = null;

	    public java.util.Iterator getPorts() {
	        if (ports == null) {
	            ports = new java.util.HashSet();
	            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "Service1Soap12"));
	            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "Service1Soap"));
	        }
	        return ports.iterator();
	    }

	    /**
	    * Set the endpoint address for the specified port name.
	    */
	    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
	        
	if ("Service1Soap12".equals(portName)) {
	            setService1Soap12EndpointAddress(address);
	        }
	        else 
	if ("Service1Soap".equals(portName)) {
	            setService1SoapEndpointAddress(address);
	        }
	        else 
	{ // Unknown Port Name
	            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
	        }
	    }

	    /**
	    * Set the endpoint address for the specified port name.
	    */
	    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
	        setEndpointAddress(portName.getLocalPart(), address);
	    }

}
