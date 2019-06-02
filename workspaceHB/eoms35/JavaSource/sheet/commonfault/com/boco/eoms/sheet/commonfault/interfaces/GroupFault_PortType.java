/**
 * GroupFault_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.boco.eoms.sheet.commonfault.interfaces;

public interface GroupFault_PortType extends java.rmi.Remote {
    public java.lang.String getBasicInfo(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException;
    public java.lang.String getStageFeedback(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException;
    public java.lang.String getCoveryInfo(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException;
}
