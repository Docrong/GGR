/**
 * InterSwitchAlarmService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interSwitchAlarmClient;

public interface InterSwitchAlarmService extends javax.xml.rpc.Service {
    public java.lang.String getInterSwitchAlarmAddress();

    public InterSwitchAlarm getInterSwitchAlarm() throws javax.xml.rpc.ServiceException;

    public InterSwitchAlarm getInterSwitchAlarm(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
