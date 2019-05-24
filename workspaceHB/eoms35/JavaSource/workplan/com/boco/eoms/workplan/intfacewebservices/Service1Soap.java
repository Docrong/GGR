/**
 * Service1Soap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2beta3 Aug 01, 2004 (05:59:22 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.workplan.intfacewebservices;

public interface Service1Soap extends java.rmi.Remote {
	public org.w3c.dom.Document getNetInfo() throws java.rmi.RemoteException;

	public org.w3c.dom.Document getCmdTaskInfo(java.lang.String netID,
			java.lang.String userID) throws java.rmi.RemoteException;

	public org.w3c.dom.Document getTaskInfo(java.lang.String netID,
			java.lang.String userID) throws java.rmi.RemoteException;

	public org.w3c.dom.Document getPerformCmdResultInfo(java.lang.String cmdID,
			java.lang.String netID, java.lang.String startTime,
			java.lang.String endTime) throws java.rmi.RemoteException;

	public org.w3c.dom.Document getPerformTaskResultInfo(java.lang.String taskID,
			java.lang.String startTime, java.lang.String endTime)
			throws java.rmi.RemoteException;

	public org.w3c.dom.Document getTaskCmdResultInfo(java.lang.String taskID,
			java.lang.String netID, java.lang.String startTime,
			java.lang.String endTime) throws java.rmi.RemoteException;

}
