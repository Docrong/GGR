/**
 * InterSwitchEomsIPServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.workplan.InterSwitchEomsIPWebServices;

public class InterSwitchEomsIPServiceTestCase extends junit.framework.TestCase {
    public InterSwitchEomsIPServiceTestCase(java.lang.String name) {
        super(name);
    }
    public void test1InterSwitchEomsIPMain() throws Exception {
        com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPSoapBindingStub)
                          new com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPServiceLocator().getInterSwitchEomsIP();
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
        binding.main(new java.lang.String[0]);
        // TBD - validate results
    }

    public void test2InterSwitchEomsIPNetInfo() throws Exception {
        com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPSoapBindingStub)
                          new com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPServiceLocator().getInterSwitchEomsIP();
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
        org.w3c.dom.Document value = null;
        value = binding.netInfo();
        // TBD - validate results
    }

    public void test3InterSwitchEomsIPPerformResultInfo() throws Exception {
        com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPSoapBindingStub)
                          new com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPServiceLocator().getInterSwitchEomsIP();
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
        org.w3c.dom.Document value = null;
        value = binding.performResultInfo(new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test4InterSwitchEomsIPTaskInfo() throws Exception {
        com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPSoapBindingStub)
                          new com.boco.eoms.workplan.InterSwitchEomsIPWebServices.InterSwitchEomsIPServiceLocator().getInterSwitchEomsIP();
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
        org.w3c.dom.Document value = null;
        value = binding.taskInfo(new java.lang.String());
        // TBD - validate results
    }

}
