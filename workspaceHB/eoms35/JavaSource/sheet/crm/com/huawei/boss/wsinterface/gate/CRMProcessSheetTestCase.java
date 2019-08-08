/**
 * CRMProcessSheetTestCase.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.huawei.boss.wsinterface.gate;

public class CRMProcessSheetTestCase extends junit.framework.TestCase {
    public CRMProcessSheetTestCase(java.lang.String name) {
        super(name);
    }

    public void test1CRMProcessSheetHttpSoap12EndpointReplyWorkSheet() throws Exception {
        com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap12BindingStub binding;
        try {
            binding = (com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap12BindingStub)
                    new com.huawei.boss.wsinterface.gate.CRMProcessSheetLocator().getCRMProcessSheetHttpSoap12Endpoint();
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
        value = binding.replyWorkSheet(new java.lang.Integer(0), new java.lang.Integer(0), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test2CRMProcessSheetHttpSoap12EndpointIsAlive() throws Exception {
        com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap12BindingStub binding;
        try {
            binding = (com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap12BindingStub)
                    new com.huawei.boss.wsinterface.gate.CRMProcessSheetLocator().getCRMProcessSheetHttpSoap12Endpoint();
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
        value = binding.isAlive(new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test3CRMProcessSheetHttpSoap12EndpointWithdrawWorkSheet() throws Exception {
        com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap12BindingStub binding;
        try {
            binding = (com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap12BindingStub)
                    new com.huawei.boss.wsinterface.gate.CRMProcessSheetLocator().getCRMProcessSheetHttpSoap12Endpoint();
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
        value = binding.withdrawWorkSheet(new java.lang.Integer(0), new java.lang.Integer(0), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test4CRMProcessSheetHttpSoap12EndpointConfirmWorkSheet() throws Exception {
        com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap12BindingStub binding;
        try {
            binding = (com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap12BindingStub)
                    new com.huawei.boss.wsinterface.gate.CRMProcessSheetLocator().getCRMProcessSheetHttpSoap12Endpoint();
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
        value = binding.confirmWorkSheet(new java.lang.Integer(0), new java.lang.Integer(0), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test5CRMProcessSheetHttpSoap12EndpointNotifyWorkSheet() throws Exception {
        com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap12BindingStub binding;
        try {
            binding = (com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap12BindingStub)
                    new com.huawei.boss.wsinterface.gate.CRMProcessSheetLocator().getCRMProcessSheetHttpSoap12Endpoint();
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
        value = binding.notifyWorkSheet(new java.lang.Integer(0), new java.lang.Integer(0), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test6CRMProcessSheetHttpSoap11EndpointReplyWorkSheet() throws Exception {
        com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap11BindingStub binding;
        try {
            binding = (com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap11BindingStub)
                    new com.huawei.boss.wsinterface.gate.CRMProcessSheetLocator().getCRMProcessSheetHttpSoap11Endpoint();
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
        value = binding.replyWorkSheet(new java.lang.Integer(0), new java.lang.Integer(0), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test7CRMProcessSheetHttpSoap11EndpointIsAlive() throws Exception {
        com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap11BindingStub binding;
        try {
            binding = (com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap11BindingStub)
                    new com.huawei.boss.wsinterface.gate.CRMProcessSheetLocator().getCRMProcessSheetHttpSoap11Endpoint();
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
        value = binding.isAlive(new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test8CRMProcessSheetHttpSoap11EndpointWithdrawWorkSheet() throws Exception {
        com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap11BindingStub binding;
        try {
            binding = (com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap11BindingStub)
                    new com.huawei.boss.wsinterface.gate.CRMProcessSheetLocator().getCRMProcessSheetHttpSoap11Endpoint();
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
        value = binding.withdrawWorkSheet(new java.lang.Integer(0), new java.lang.Integer(0), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test9CRMProcessSheetHttpSoap11EndpointConfirmWorkSheet() throws Exception {
        com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap11BindingStub binding;
        try {
            binding = (com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap11BindingStub)
                    new com.huawei.boss.wsinterface.gate.CRMProcessSheetLocator().getCRMProcessSheetHttpSoap11Endpoint();
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
        value = binding.confirmWorkSheet(new java.lang.Integer(0), new java.lang.Integer(0), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test10CRMProcessSheetHttpSoap11EndpointNotifyWorkSheet() throws Exception {
        com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap11BindingStub binding;
        try {
            binding = (com.huawei.boss.wsinterface.gate.CRMProcessSheetSoap11BindingStub)
                    new com.huawei.boss.wsinterface.gate.CRMProcessSheetLocator().getCRMProcessSheetHttpSoap11Endpoint();
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
        value = binding.notifyWorkSheet(new java.lang.Integer(0), new java.lang.Integer(0), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

}
