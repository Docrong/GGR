/**
 * Eoms2IomService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.widencomplaintSheet;

public interface Eoms2IomService extends java.rmi.Remote {
    public java.lang.String checkinWorkSheet(java.lang.String sheetType, java.lang.String serviceType, java.lang.String serialNo, java.lang.String eomsSheetid, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException;
    public java.lang.String remindWorkSheet(java.lang.String sheetType, java.lang.String serviceType, java.lang.String serialNo, java.lang.String eomsSheetid, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException;
    public java.lang.String renewWorkSheet(java.lang.String sheetType, java.lang.String serviceType, java.lang.String serialNo, java.lang.String eomsSheetid, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException;
    public java.lang.String untreadWorkSheet(java.lang.String sheetType, java.lang.String serviceType, java.lang.String serialNo, java.lang.String eomsSheetid, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException;
    public java.lang.String newWorkSheet(java.lang.String sheetType, java.lang.String serviceType, java.lang.String serialNo, java.lang.String eomsSheetid, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String opPerson, java.lang.String opCorp, java.lang.String opDepart, java.lang.String opContact, java.lang.String opTime, java.lang.String opDetail) throws java.rmi.RemoteException;
}
