/**
 * InterSwitchAlarmService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.wnms.client;

public interface InterSwitchAlarmService extends javax.xml.rpc.Service {
    public java.lang.String getInterSwitchAlarmAddress();

    public com.boco.eoms.interfaces.wnms.client.InterSwitchAlarm getInterSwitchAlarm() throws javax.xml.rpc.ServiceException;

    public com.boco.eoms.interfaces.wnms.client.InterSwitchAlarm getInterSwitchAlarm(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
