/**
 * InterSwitchAlarm.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interSwitchAlarmClient;

public interface InterSwitchAlarm extends java.rmi.Remote {
    public java.lang.String isAlive() throws java.rmi.RemoteException;
    public java.lang.String newAlarm(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime,String opDetail) throws java.rmi.RemoteException;
    public java.lang.String syncAlarm(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, String opDetail) throws java.rmi.RemoteException;
}
