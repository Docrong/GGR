/**
 * GroupFaultService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.commonfaultfccos;

public interface GroupFaultService extends javax.xml.rpc.Service {
    public java.lang.String getGroupFaultAddress();

    public com.boco.eoms.commonfaultfccos.GroupFault_PortType getGroupFault() throws javax.xml.rpc.ServiceException;

    public com.boco.eoms.commonfaultfccos.GroupFault_PortType getGroupFault(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
