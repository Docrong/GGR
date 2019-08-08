/**
 * KFComplainSheetTestCase.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.group.client.KFComplainSheet;

public class KFComplainSheetTestCase extends junit.framework.TestCase {
    public KFComplainSheetTestCase(java.lang.String name) {
        super(name);
    }

    public void test1KFComplainSheetSoapIsAlive() throws Exception {
        com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoapStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoapStub)
                    new com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetLocator().getKFComplainSheetSoap();
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

    public void test2KFComplainSheetSoapConfirmWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoapStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoapStub)
                    new com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetLocator().getKFComplainSheetSoap();
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

    public void test3KFComplainSheetSoapNotifyWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoapStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoapStub)
                    new com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetLocator().getKFComplainSheetSoap();
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

    public void test4KFComplainSheetSoapReplyWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoapStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoapStub)
                    new com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetLocator().getKFComplainSheetSoap();
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
        value = binding.replyWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), 0, 0, new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test5KFComplainSheetSoapWithdrawWorkSheet() throws Exception {
        com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoapStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetSoapStub)
                    new com.boco.eoms.interfaces.group.client.KFComplainSheet.KFComplainSheetLocator().getKFComplainSheetSoap();
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
