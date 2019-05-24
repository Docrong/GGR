/**
 * InterSwitchEomsIP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.workplan.InterSwitchEomsIPWebServices;

public interface InterSwitchEomsIP extends java.rmi.Remote {
    public void main(java.lang.String[] argv) throws java.rmi.RemoteException;
    public org.w3c.dom.Document taskInfo(java.lang.String netID) throws java.rmi.RemoteException;
    public org.w3c.dom.Document netInfo() throws java.rmi.RemoteException;
    public org.w3c.dom.Document performResultInfo(java.lang.String taskID, java.lang.String netID, java.lang.String performTime) throws java.rmi.RemoteException;
}
