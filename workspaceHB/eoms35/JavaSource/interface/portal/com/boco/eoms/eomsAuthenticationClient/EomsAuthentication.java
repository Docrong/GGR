/**
 * EomsAuthentication.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.eomsAuthenticationClient;

public interface EomsAuthentication extends java.rmi.Remote {
    public java.lang.String isAlive() throws java.rmi.RemoteException;
    public java.lang.String eomsAuthentication(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String opDetail) throws java.rmi.RemoteException;
}
