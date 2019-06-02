package com.boco.eoms.sheet.mofficedata.service.client;

/**
 * DATAProcessSheetTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */



public class DATAProcessSheetTestCase extends junit.framework.TestCase {
    public DATAProcessSheetTestCase(java.lang.String name) {
        super(name);
    }

    public void testEomsDataImportImplPortWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new DATAProcessSheetLocator().getEomsDataImportImplPortAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new DATAProcessSheetLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1EomsDataImportImplPortNewWorkSheet() throws Exception {
        DATAProcessSheetSoapBindingStub binding;
        try {
            binding = (DATAProcessSheetSoapBindingStub)
                          new DATAProcessSheetLocator().getEomsDataImportImplPort();
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
        value = binding.newWorkSheet(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test2EomsDataImportImplPortGetBusiTypeFilePath() throws Exception {
        DATAProcessSheetSoapBindingStub binding;
        try {
            binding = (DATAProcessSheetSoapBindingStub)
                          new DATAProcessSheetLocator().getEomsDataImportImplPort();
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
        value = binding.getBusiTypeFilePath(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test3EomsDataImportImplPortGetTemplatePath() throws Exception {
        DATAProcessSheetSoapBindingStub binding;
        try {
            binding = (DATAProcessSheetSoapBindingStub)
                          new DATAProcessSheetLocator().getEomsDataImportImplPort();
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
        value = binding.getTemplatePath(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

}
