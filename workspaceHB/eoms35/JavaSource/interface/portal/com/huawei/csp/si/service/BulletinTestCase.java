/**
 * BulletinTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.huawei.csp.si.service;

public class BulletinTestCase extends junit.framework.TestCase {
    public BulletinTestCase(java.lang.String name) {
        super(name);
    }
    public void test1BulletinHttpPortNewBulletin() throws Exception {
        com.huawei.csp.si.service.BulletinHttpBindingStub binding;
        try {
            binding = (com.huawei.csp.si.service.BulletinHttpBindingStub)
                          new com.huawei.csp.si.service.BulletinLocator().getBulletinHttpPort();
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
        value = binding.newBulletin(new java.lang.Integer(0), new java.lang.Integer(0), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test2BulletinHttpPortConfirmBulletin() throws Exception {
        com.huawei.csp.si.service.BulletinHttpBindingStub binding;
        try {
            binding = (com.huawei.csp.si.service.BulletinHttpBindingStub)
                          new com.huawei.csp.si.service.BulletinLocator().getBulletinHttpPort();
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
        value = binding.confirmBulletin(new java.lang.Integer(0), new java.lang.Integer(0), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

}
