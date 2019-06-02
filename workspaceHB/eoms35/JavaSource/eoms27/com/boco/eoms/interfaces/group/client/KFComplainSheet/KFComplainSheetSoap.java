/**
 * KFComplainSheetSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.group.client.KFComplainSheet;

public interface KFComplainSheetSoap extends java.rmi.Remote {
    public java.lang.String isAlive(java.lang.String serSupplier) throws java.rmi.RemoteException;
    public java.lang.String confirmWorkSheet(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String replyCorp, java.lang.String replyPerson, java.lang.String replyDepart, java.lang.String replyContact, java.lang.String replyTime, int urgent, java.lang.String deadline) throws java.rmi.RemoteException;
    public java.lang.String notifyWorkSheet(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String replyCorp, java.lang.String replyPerson, java.lang.String replyDepart, java.lang.String replyContact, java.lang.String replyTime, java.lang.String stepResult) throws java.rmi.RemoteException;
    public java.lang.String replyWorkSheet(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callTime, java.lang.String callerPwd, java.lang.String attachRef, java.lang.String replyCorp, java.lang.String replyPerson, java.lang.String replyDepart, java.lang.String replyContact, java.lang.String replyTime, java.lang.String recoverTime, java.lang.String outOfServiceTime, int networkType, int serviceType, java.lang.String endResult, java.lang.String records) throws java.rmi.RemoteException;
    public java.lang.String withdrawWorkSheet(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callTime, java.lang.String callerPwd, java.lang.String attachRef, java.lang.String replyCorp, java.lang.String replyPerson, java.lang.String replyDepart, java.lang.String replyContact, java.lang.String replyTime, java.lang.String withdrawReason) throws java.rmi.RemoteException;
}
