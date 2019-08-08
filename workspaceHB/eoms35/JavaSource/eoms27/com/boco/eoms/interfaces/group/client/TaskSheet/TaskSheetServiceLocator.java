/**
 * TaskSheetServiceLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.group.client.TaskSheet;

public class TaskSheetServiceLocator extends org.apache.axis.client.Service implements com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetService {

    // Use to get a proxy class for TaskSheet
    private final java.lang.String TaskSheet_address = "http://localhost:8080/services/TaskSheet";

    public java.lang.String getTaskSheetAddress() {
        return TaskSheet_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TaskSheetWSDDServiceName = "TaskSheet";

    public java.lang.String getTaskSheetWSDDServiceName() {
        return TaskSheetWSDDServiceName;
    }

    public void setTaskSheetWSDDServiceName(java.lang.String name) {
        TaskSheetWSDDServiceName = name;
    }

    public com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheet getTaskSheet() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TaskSheet_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTaskSheet(endpoint);
    }

    public com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheet getTaskSheet(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetSoapBindingStub _stub = new com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetSoapBindingStub(portAddress, this);
            _stub.setPortName(getTaskSheetWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheet.class.isAssignableFrom(serviceEndpointInterface)) {
                com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetSoapBindingStub _stub = new com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetSoapBindingStub(new java.net.URL(TaskSheet_address), this);
                _stub.setPortName(getTaskSheetWSDDServiceName());
                return _stub;
            }
        } catch (java.lang.Throwable t) {
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
        String inputPortName = portName.getLocalPart();
        if ("TaskSheet".equals(inputPortName)) {
            return getTaskSheet();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://TaskSheet.client.group.interfaces.eoms.boco.com", "TaskSheetService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("TaskSheet"));
        }
        return ports.iterator();
    }

}
