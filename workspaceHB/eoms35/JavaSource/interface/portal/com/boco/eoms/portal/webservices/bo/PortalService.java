/**
 * PortalService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.portal.webservices.bo;

public interface PortalService extends javax.xml.rpc.Service {
    public java.lang.String getPortalServiceHttpPortAddress();

    public com.boco.eoms.portal.webservices.bo.PortalServicePortType getPortalServiceHttpPort() throws javax.xml.rpc.ServiceException;

    public com.boco.eoms.portal.webservices.bo.PortalServicePortType getPortalServiceHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
