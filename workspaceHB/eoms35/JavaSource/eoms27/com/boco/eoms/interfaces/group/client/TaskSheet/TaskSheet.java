/**
 * TaskSheet.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.group.client.TaskSheet;

public interface TaskSheet extends java.rmi.Remote {
    public void isAlive(java.lang.String serSupplier) throws java.rmi.RemoteException;
    public java.lang.String cancelWorkSheet(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callTime, java.lang.String callerPwd, java.lang.String attachRef, java.lang.String sendCorp, java.lang.String sendPerson, java.lang.String sendDepart, java.lang.String sendContact, java.lang.String sendTime, java.lang.String cancelAdvice) throws java.rmi.RemoteException;
    public java.lang.String checkinWorkSheet(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callTime, java.lang.String callerPwd, java.lang.String attachRef, java.lang.String closeCorp, java.lang.String closePerson, java.lang.String closeDepart, java.lang.String closeContact, java.lang.String closeTime, int satisfaction, java.lang.String closeAdvice) throws java.rmi.RemoteException;
    public java.lang.String confirmWorkSheet(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callTime, java.lang.String callerPwd, java.lang.String attachRef, java.lang.String replyCorp, java.lang.String replyPerson, java.lang.String replyDepart, java.lang.String replyContact, java.lang.String replyTime, int urgent, java.lang.String deadline) throws java.rmi.RemoteException;
    public java.lang.String newWorkSheet(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callTime, java.lang.String callerPwd, java.lang.String attachRef, java.lang.String sendCorp, java.lang.String sendPerson, java.lang.String sendDepart, java.lang.String sendContact, java.lang.String sendTime, java.lang.String acceptCorp, java.lang.String coordCorp, int urgent, java.lang.String deadline, int assigType, java.lang.String assigTitle, java.lang.String assigContent) throws java.rmi.RemoteException;
    public java.lang.String renewWorkSheet(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callTime, java.lang.String callerPwd, java.lang.String attachRef, java.lang.String sendCorp, java.lang.String sendPerson, java.lang.String sendDepart, java.lang.String sendContact, java.lang.String sendTime, java.lang.String acceptCorp, java.lang.String coordCorp, java.lang.String resendReason, int urgent, java.lang.String deadline, int assigType, java.lang.String assigTitle, java.lang.String assigContent) throws java.rmi.RemoteException;
    public java.lang.String replyWorkSheet(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callTime, java.lang.String callerPwd, java.lang.String attachRef, java.lang.String replyCorp, java.lang.String replyPerson, java.lang.String replyDepart, java.lang.String replyContact, java.lang.String replyTime, java.lang.String endResult) throws java.rmi.RemoteException;
    public java.lang.String newWorkSheetEDIS(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callTime, java.lang.String callerPwd, java.lang.String attachRef, java.lang.String sendCorp, java.lang.String sendPerson, java.lang.String sendContact, java.lang.String sendTime, java.lang.String acceptCorp, int urgent, java.lang.String deadline, int assigType, java.lang.String assigTitle, java.lang.String assigContent) throws java.rmi.RemoteException;
}
