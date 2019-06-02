/**
 * TaskSheetServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.group.client.TaskSheet;

public class TaskSheetServiceTestCase extends junit.framework.TestCase {
    public TaskSheetServiceTestCase(java.lang.String name) {
        super(name);
    }
    public void test1TaskSheetIsAlive() throws Exception {
        com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetSoapBindingStub)
                          new com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetServiceLocator().getTaskSheet();
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
        binding.isAlive(new java.lang.String());
        // TBD - validate results
    }

    public void test2TaskSheetCancelWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetSoapBindingStub)
                          new com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetServiceLocator().getTaskSheet();
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
        value = binding.cancelWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test3TaskSheetCheckinWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetSoapBindingStub)
                          new com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetServiceLocator().getTaskSheet();
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
        value = binding.checkinWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), 0, new java.lang.String());
        // TBD - validate results
    }

    public void test4TaskSheetNewWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetSoapBindingStub)
                          new com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetServiceLocator().getTaskSheet();
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
        value = binding.newWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), 0, new java.lang.String(), 0, new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test5TaskSheetRenewWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetSoapBindingStub)
                          new com.boco.eoms.interfaces.group.client.TaskSheet.TaskSheetServiceLocator().getTaskSheet();
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
        value = binding.renewWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), 0, new java.lang.String(), 0, new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

}
