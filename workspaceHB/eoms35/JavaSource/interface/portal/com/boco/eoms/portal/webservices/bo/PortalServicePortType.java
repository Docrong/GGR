/**
 * PortalServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.portal.webservices.bo;

public interface PortalServicePortType extends java.rmi.Remote {
    public java.lang.String[] getPortalRoleList() throws java.rmi.RemoteException;
    public boolean saveOrUpdatePortalUser(org.light.portal.user.entity.PortalUser in0, java.lang.String in1) throws java.rmi.RemoteException;
    public boolean delPortalUser(java.lang.String in0) throws java.rmi.RemoteException;
}
