/**
 * ReportExecuteService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhead.interfaces;

public interface ReportExecuteService extends javax.xml.rpc.Service {
    public java.lang.String getReportInventoryPortAddress();

    public com.boco.eoms.gzjhead.interfaces.ReportInventoryPortType getReportInventoryPort() throws javax.xml.rpc.ServiceException;

    public com.boco.eoms.gzjhead.interfaces.ReportInventoryPortType getReportInventoryPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getReportExecutePortAddress();

    public com.boco.eoms.gzjhead.interfaces.ReportExecutePortType getReportExecutePort() throws javax.xml.rpc.ServiceException;

    public com.boco.eoms.gzjhead.interfaces.ReportExecutePortType getReportExecutePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getAuxiliaryPortAddress();

    public com.boco.eoms.gzjhead.interfaces.AuxiliaryPortType getAuxiliaryPort() throws javax.xml.rpc.ServiceException;

    public com.boco.eoms.gzjhead.interfaces.AuxiliaryPortType getAuxiliaryPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
