/**
 * ReportExecuteServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.gzjhhead.interfaces;

public class ReportExecuteServiceTestCase extends junit.framework.TestCase {
    public ReportExecuteServiceTestCase(java.lang.String name) {
        super(name);
    }
    public void test1ReportInventoryPortReportInventory() throws Exception {
        com.boco.eoms.gzjhhead.interfaces.ReportInventoryBindingStub binding;
        try {
            binding = (com.boco.eoms.gzjhhead.interfaces.ReportInventoryBindingStub)
                          new com.boco.eoms.gzjhhead.interfaces.ReportExecuteServiceLocator().getReportInventoryPort();
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
        try {
            com.boco.eoms.gzjhhead.interfaces._ReportInventoryResponse value = null;
            value = binding.reportInventory(new com.boco.eoms.gzjhhead.interfaces._ReportInventoryRequest());
        }
        catch (com.boco.eoms.gzjhhead.interfaces.FaultDetails e1) {
            throw new junit.framework.AssertionFailedError("ReportInventoryFault Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test2ReportExecutePortReportForm() throws Exception {
        com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub binding;
        try {
            binding = (com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub)
                          new com.boco.eoms.gzjhhead.interfaces.ReportExecuteServiceLocator().getReportExecutePort();
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
        try {
            com.boco.eoms.gzjhhead.interfaces._ReportFormResponse value = null;
            value = binding.reportForm(new com.boco.eoms.gzjhhead.interfaces._ReportFormRequest());
        }
        catch (com.boco.eoms.gzjhhead.interfaces.FaultDetails e1) {
            throw new junit.framework.AssertionFailedError("ReportFormFault Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test3ReportExecutePortModifyForm() throws Exception {
        com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub binding;
        try {
            binding = (com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub)
                          new com.boco.eoms.gzjhhead.interfaces.ReportExecuteServiceLocator().getReportExecutePort();
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
        try {
            com.boco.eoms.gzjhhead.interfaces._ModifyFormResponse value = null;
            value = binding.modifyForm(new com.boco.eoms.gzjhhead.interfaces._ModifyFormRequest());
        }
        catch (com.boco.eoms.gzjhhead.interfaces.FaultDetails e1) {
            throw new junit.framework.AssertionFailedError("ModifyFormFault Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test4AuxiliaryPortIsAlive() throws Exception {
        com.boco.eoms.gzjhhead.interfaces.AuxiliaryBindingStub binding;
        try {
            binding = (com.boco.eoms.gzjhhead.interfaces.AuxiliaryBindingStub)
                          new com.boco.eoms.gzjhhead.interfaces.ReportExecuteServiceLocator().getAuxiliaryPort();
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
        try {
            com.boco.eoms.gzjhhead.interfaces._IsAliveResponse value = null;
            value = binding.isAlive(new com.boco.eoms.gzjhhead.interfaces._IsAliveRequest());
        }
        catch (com.boco.eoms.gzjhhead.interfaces.FaultDetails e1) {
            throw new junit.framework.AssertionFailedError("IsAliveFault Exception caught: " + e1);
        }
            // TBD - validate results
    }

}
