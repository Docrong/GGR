///**
// * ReportExecutePortTestCase.java
// *
// * This file was auto-generated from WSDL
// * by the Apache Axis WSDL2Java emitter.
// */
//
//package com.boco.eoms.gzjhhead.interfaces;
//
//public class ReportExecutePortTestCase extends junit.framework.TestCase {
//    public ReportExecutePortTestCase(java.lang.String name) {
//        super(name);
//    }
//    public void test1ReportExecutePortIsAlive() throws Exception {
//        com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub binding;
//        try {
//            binding = (com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub)
//                          new com.boco.eoms.gzjhhead.interfaces.ReportExecutePortLocator().getReportExecutePort();
//        }
//        catch (javax.xml.rpc.ServiceException jre) {
//            if(jre.getLinkedCause()!=null)
//                jre.getLinkedCause().printStackTrace();
//            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
//        }
//        assertNotNull("binding is null", binding);
//
//        // Time out after a minute
//        binding.setTimeout(60000);
//
//        // Test operation
//        try {
//            com.boco.eoms.gzjhhead.interfaces.IsAliveResponse value = null;
//            value = binding.isAlive(new com.boco.eoms.gzjhhead.interfaces.IsAliveRequest());
//        }
//        catch (com.boco.eoms.gzjhhead.interfaces.FaultDetails e1) {
//            throw new junit.framework.AssertionFailedError("IsAliveFault Exception caught: " + e1);
//        }
//            // TBD - validate results
//    }
//
//    public void test2ReportExecutePortReportForm() throws Exception {
//        com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub binding;
//        try {
//            binding = (com.boco.eoms.gzjhhead.interfaces.ReportExecuteBindingStub)
//                          new com.boco.eoms.gzjhhead.interfaces.ReportExecutePortLocator().getReportExecutePort();
//        }
//        catch (javax.xml.rpc.ServiceException jre) {
//            if(jre.getLinkedCause()!=null)
//                jre.getLinkedCause().printStackTrace();
//            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
//        }
//        assertNotNull("binding is null", binding);
//
//        // Time out after a minute
//        binding.setTimeout(60000);
//
//        // Test operation
//        try {
//            com.boco.eoms.gzjhhead.interfaces.ReportFormResponse value = null;
//            value = binding.reportForm(new com.boco.eoms.gzjhhead.interfaces.ReportFormRequest());
//        }
//        catch (com.boco.eoms.gzjhhead.interfaces.FaultDetails e1) {
//            throw new junit.framework.AssertionFailedError("ReportFormFault Exception caught: " + e1);
//        }
//            // TBD - validate results
//    }
//
//}
