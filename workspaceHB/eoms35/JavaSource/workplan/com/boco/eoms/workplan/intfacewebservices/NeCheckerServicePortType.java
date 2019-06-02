/**
 * NeCheckerServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.workplan.intfacewebservices;

public interface NeCheckerServicePortType extends java.rmi.Remote {
    public java.lang.String getPerformTaskResultInfo(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException;
    public java.lang.String getCmdTaskInfo(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public java.lang.String getPerformCmdResultInfo(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3) throws java.rmi.RemoteException;
    public java.lang.String getTaskInfo(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public java.lang.String getNetInfo() throws java.rmi.RemoteException;
    public java.lang.String getTaskCmdResultInfo(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3) throws java.rmi.RemoteException;
}
