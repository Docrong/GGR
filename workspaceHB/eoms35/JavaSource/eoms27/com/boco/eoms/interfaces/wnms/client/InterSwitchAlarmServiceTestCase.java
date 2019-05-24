/**
 * InterSwitchAlarmServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.wnms.client;

public class InterSwitchAlarmServiceTestCase extends junit.framework.TestCase {
    public InterSwitchAlarmServiceTestCase(java.lang.String name) {
        super(name);
    }
    public void test1InterSwitchAlarmIsAlive() throws Exception {
        com.boco.eoms.interfaces.wnms.client.InterSwitchAlarmSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.wnms.client.InterSwitchAlarmSoapBindingStub)
                          new com.boco.eoms.interfaces.wnms.client.InterSwitchAlarmServiceLocator().getInterSwitchAlarm();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.isAlive();
        // TBD - validate results
    }

    public void test2InterSwitchAlarmNewAlarm() throws Exception {
        com.boco.eoms.interfaces.wnms.client.InterSwitchAlarmSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.wnms.client.InterSwitchAlarmSoapBindingStub)
                          new com.boco.eoms.interfaces.wnms.client.InterSwitchAlarmServiceLocator().getInterSwitchAlarm();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.newAlarm(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test3InterSwitchAlarmSyncAlarm() throws Exception {
        com.boco.eoms.interfaces.wnms.client.InterSwitchAlarmSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.wnms.client.InterSwitchAlarmSoapBindingStub)
                          new com.boco.eoms.interfaces.wnms.client.InterSwitchAlarmServiceLocator().getInterSwitchAlarm();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.syncAlarm(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), 0, new java.lang.String());
        // TBD - validate results
    }

}
