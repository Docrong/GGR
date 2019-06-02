/**
 * InterSwitchAlarm.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.wnms.client;

public interface InterSwitchAlarm extends java.rmi.Remote {
    public java.lang.String isAlive() throws java.rmi.RemoteException;
    public java.lang.String newAlarm(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callTime, java.lang.String alarmId, java.lang.String oriAlarmId, java.lang.String alarmTitle, java.lang.String alarmCreateTime, java.lang.String neType, java.lang.String neName, java.lang.String alarmLevel, java.lang.String alarmType, java.lang.String alarmRedefLevel, java.lang.String alarmRedefType, java.lang.String alarmLocation, java.lang.String alarmDetail, java.lang.String alarmPropose, java.lang.String alarmRegion) throws java.rmi.RemoteException;
    public java.lang.String syncAlarm(java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callTime, java.lang.String alarmId, int alarmStatus, java.lang.String statusTime) throws java.rmi.RemoteException;
}
