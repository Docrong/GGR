/**
 * FaultSheetServiceTestCase.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.group.client.FaultSheet;

public class FaultSheetServiceTestCase extends junit.framework.TestCase {
    public FaultSheetServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void test1FaultSheetIsAlive() throws Exception {
        com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub)
                    new com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetServiceLocator().getFaultSheet();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.isAlive(new java.lang.String());
        // TBD - validate results
    }

    public void test2FaultSheetCancelWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub)
                    new com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetServiceLocator().getFaultSheet();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
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

    public void test3FaultSheetCheckinWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub)
                    new com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetServiceLocator().getFaultSheet();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
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

    public void test4FaultSheetConfirmWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub)
                    new com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetServiceLocator().getFaultSheet();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.confirmWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), 0, new java.lang.String());
        // TBD - validate results
    }

    public void test5FaultSheetNewWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub)
                    new com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetServiceLocator().getFaultSheet();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.newWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), 0, new java.lang.String(), 0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test6FaultSheetNotifyWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub)
                    new com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetServiceLocator().getFaultSheet();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.notifyWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test7FaultSheetRenewWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub)
                    new com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetServiceLocator().getFaultSheet();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.renewWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), 0, new java.lang.String(), new java.lang.String(), 0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test8FaultSheetReplyWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub)
                    new com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetServiceLocator().getFaultSheet();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.replyWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), 0, 0, new java.lang.String());
        // TBD - validate results
    }

    public void test9FaultSheetSuggestWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub)
                    new com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetServiceLocator().getFaultSheet();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.suggestWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test10FaultSheetSyncAlarmWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub)
                    new com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetServiceLocator().getFaultSheet();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.syncAlarmWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test11FaultSheetUrgeWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub)
                    new com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetServiceLocator().getFaultSheet();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.urgeWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test12FaultSheetWithdrawWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetSoapBindingStub)
                    new com.boco.eoms.interfaces.group.client.FaultSheet.FaultSheetServiceLocator().getFaultSheet();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.withdrawWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

}
