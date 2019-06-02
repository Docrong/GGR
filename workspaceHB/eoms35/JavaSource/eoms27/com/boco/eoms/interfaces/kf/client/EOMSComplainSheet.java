/**
 * EOMSComplainSheet.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.kf.client;

public interface EOMSComplainSheet extends java.rmi.Remote {
    public java.lang.String isAlive(java.lang.String serSupplier) throws java.rmi.RemoteException;
    public java.lang.String cancelWorkSheet(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String sendCorp, java.lang.String sendPerson, java.lang.String sendDepart, java.lang.String sendContact, java.lang.String sendTime, java.lang.String cancelAdvice) throws java.rmi.RemoteException;
    public java.lang.String checkinWorkSheet(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callTime, java.lang.String callerPwd, java.lang.String attachRef, java.lang.String closeCorp, java.lang.String closePerson, java.lang.String closeDepart, java.lang.String closeContact, java.lang.String closeTime, int satisfaction, java.lang.String closeAdvice) throws java.rmi.RemoteException;
    public java.lang.String newWorkSheet(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String sendCorp, java.lang.String sendPerson, java.lang.String sendDepart, java.lang.String sendContact, java.lang.String sendTime, java.lang.String acceptCorp, java.lang.String coordCorp, int urgent, java.lang.String deadline, java.lang.String crmSerialNo, int complainType, int userType, java.lang.String faultTitle, java.lang.String faultOccurTime, java.lang.String customerName, java.lang.String compCalling, java.lang.String compCalled, java.lang.String compLocation, java.lang.String compDescription, java.lang.String terminal, java.lang.String recompNum) throws java.rmi.RemoteException;
    public java.lang.String suggestWorkSheet(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String sendCorp, java.lang.String sendPerson, java.lang.String sendDepart, java.lang.String sendContact, java.lang.String sendTime, java.lang.String suggestion) throws java.rmi.RemoteException;
    public java.lang.String urgeWorkSheet(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callTime, java.lang.String callerPwd, java.lang.String attachRef, java.lang.String sendCorp, java.lang.String sendPerson, java.lang.String sendDepart, java.lang.String sendContact, java.lang.String sendTime, java.lang.String urgeAdvice) throws java.rmi.RemoteException;
}
