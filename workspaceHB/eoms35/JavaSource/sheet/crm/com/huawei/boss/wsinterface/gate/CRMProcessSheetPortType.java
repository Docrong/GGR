/**
 * CRMProcessSheetPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.huawei.boss.wsinterface.gate;

public interface CRMProcessSheetPortType extends java.rmi.Remote {
    public java.lang.String replyWorkSheet(java.lang.Integer sheetType, java.lang.Integer serviceType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException;
    public java.lang.String isAlive(java.lang.String serSupplier, java.lang.String callTime) throws java.rmi.RemoteException;
    public java.lang.String withdrawWorkSheet(java.lang.Integer sheetType, java.lang.Integer serviceType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException;
    public java.lang.String confirmWorkSheet(java.lang.Integer sheetType, java.lang.Integer serviceType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException;
    public java.lang.String notifyWorkSheet(java.lang.Integer sheetType, java.lang.Integer serviceType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException;
}
