/**
 * BulletinSoap.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.group.client.Bulletin;

public interface BulletinSoap extends java.rmi.Remote {
    public java.lang.String newBulletin(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String title, java.lang.String severity, java.lang.String type, java.lang.String content, java.lang.String keyTime, java.lang.String sendCorp, java.lang.String sendPerson, java.lang.String sendDepart, java.lang.String sendContact, java.lang.String sendTime, java.lang.String bulScope, java.lang.String bulAudit) throws java.rmi.RemoteException;

    public java.lang.String confirmBulletin(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String replyCorp, java.lang.String replyPerson, java.lang.String replyDepart, java.lang.String replyContact, java.lang.String replyTime) throws java.rmi.RemoteException;

    public java.lang.String modifyBulletin(int sheetType, java.lang.String serialNo, java.lang.String serSupplier, java.lang.String serCaller, java.lang.String callerPwd, java.lang.String callTime, java.lang.String attachRef, java.lang.String sendCorp, java.lang.String sendPerson, java.lang.String sendDepart, java.lang.String sendContact, java.lang.String sendTime, java.lang.String bulAudit) throws java.rmi.RemoteException;
}
