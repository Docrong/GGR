/**
 * EOMSComplainSheetServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.kf.client;

public class EOMSComplainSheetServiceTestCase extends junit.framework.TestCase {
    public EOMSComplainSheetServiceTestCase(java.lang.String name) {
        super(name);
    }
    public void test1EOMSComplainSheetIsAlive() throws Exception {
        com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub)
                          new com.boco.eoms.interfaces.kf.client.EOMSComplainSheetServiceLocator().getEOMSComplainSheet();
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
        value = binding.isAlive(new java.lang.String());
        // TBD - validate results
    }

    public void test2EOMSComplainSheetCancelWorkSheet() throws Exception {
        com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub)
                          new com.boco.eoms.interfaces.kf.client.EOMSComplainSheetServiceLocator().getEOMSComplainSheet();
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

    public void test3EOMSComplainSheetCheckinWorkSheet() throws Exception {
        com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub)
                          new com.boco.eoms.interfaces.kf.client.EOMSComplainSheetServiceLocator().getEOMSComplainSheet();
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

    public void test4EOMSComplainSheetNewWorkSheet() throws Exception {
        com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub)
                          new com.boco.eoms.interfaces.kf.client.EOMSComplainSheetServiceLocator().getEOMSComplainSheet();
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
        value = binding.newWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), 0, new java.lang.String(), new java.lang.String(), 0, 0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test5EOMSComplainSheetSuggestWorkSheet() throws Exception {
        com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub)
                          new com.boco.eoms.interfaces.kf.client.EOMSComplainSheetServiceLocator().getEOMSComplainSheet();
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
        value = binding.suggestWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test6EOMSComplainSheetUrgeWorkSheet() throws Exception {
        com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub binding;
        try {
            binding = (com.boco.eoms.interfaces.kf.client.EOMSComplainSheetSoapBindingStub)
                          new com.boco.eoms.interfaces.kf.client.EOMSComplainSheetServiceLocator().getEOMSComplainSheet();
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
        value = binding.urgeWorkSheet(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

}
