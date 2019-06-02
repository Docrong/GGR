/**
 * InterSwitchAlarmServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interSwitchAlarmClient;

import com.boco.eoms.commons.rule.tool.exception.RuleToolXMLException;

public class InterSwitchAlarmServiceTestCase extends junit.framework.TestCase {
    public InterSwitchAlarmServiceTestCase(java.lang.String name) {
        super(name);
    }
	/**
	 * 
	 * 测试用例 test1InterSwitchAlarmIsAlive 测试 InterSwitchAlarm服务的newAlarm（）
	 * 
	 */
    public void test1InterSwitchAlarmIsAlive() throws Exception {
        InterSwitchAlarmSoapBindingStub binding;
        try {
            binding = (InterSwitchAlarmSoapBindingStub)
                          new InterSwitchAlarmServiceLocator().getInterSwitchAlarm();
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
    /**
	 * 
	 *测试用例 test3InterSwitchAlarmIsAlive 测试 InterSwitchAlarm服务的newAlarm（）
	 * @throws Exception
	 */
    public void test2InterSwitchAlarmNewAlarm() throws Exception {
        InterSwitchAlarmSoapBindingStub binding;
        try {
            binding = (InterSwitchAlarmSoapBindingStub)
                          new InterSwitchAlarmServiceLocator().getInterSwitchAlarm();
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
        value = binding.newAlarm(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }
    /**
	 * 
	 * 测试用例 test3InterSwitchAlarmSyncAlarm
	 * @throws Exception
	 */
    public void test3InterSwitchAlarmSyncAlarm() throws Exception {
        InterSwitchAlarmSoapBindingStub binding;
        try {
            binding = (InterSwitchAlarmSoapBindingStub)
                          new InterSwitchAlarmServiceLocator().getInterSwitchAlarm();
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
        value = binding.syncAlarm(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(),new java.lang.String());
        // TBD - validate results
    }

}
