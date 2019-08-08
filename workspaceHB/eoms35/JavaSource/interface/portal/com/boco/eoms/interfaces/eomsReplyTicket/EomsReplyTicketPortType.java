/**
 * EomsReplyTicketPortType.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.eomsReplyTicket;

public interface EomsReplyTicketPortType extends java.rmi.Remote {
    public com.boco.eoms.interfaces.eomsReplyTicket.EomsReply replyEomsTicket(java.lang.String in0) throws java.rmi.RemoteException;

    public int receiveEomsTicket(com.boco.eoms.interfaces.eomsReplyTicket.EomsTicket in0) throws java.rmi.RemoteException;
}
