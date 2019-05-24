/**
 * Service_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.boco.eoms.sheet.commonfault.interfaces;

public interface Service_PortType extends java.rmi.Remote {
    public boolean insertEnd(int arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public boolean insert(java.lang.String json) throws java.rmi.RemoteException;
}
