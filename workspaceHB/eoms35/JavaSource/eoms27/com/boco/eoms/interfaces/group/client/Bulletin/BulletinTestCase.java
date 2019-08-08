/**
 * BulletinTestCase.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.interfaces.group.client.Bulletin;

public class BulletinTestCase extends junit.framework.TestCase {
    public BulletinTestCase(java.lang.String name) {
        super(name);
    }

    public void test1BulletinSoapNewBulletin() throws Exception {
        com.boco.eoms.interfaces.group.client.Bulletin.BulletinSoapStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.Bulletin.BulletinSoapStub)
                    new com.boco.eoms.interfaces.group.client.Bulletin.BulletinLocator().getBulletinSoap();
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
        value = binding.newBulletin(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test2BulletinSoapConfirmBulletin() throws Exception {
        com.boco.eoms.interfaces.group.client.Bulletin.BulletinSoapStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.Bulletin.BulletinSoapStub)
                    new com.boco.eoms.interfaces.group.client.Bulletin.BulletinLocator().getBulletinSoap();
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
        value = binding.confirmBulletin(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test3BulletinSoapModifyBulletin() throws Exception {
        com.boco.eoms.interfaces.group.client.Bulletin.BulletinSoapStub binding;
        try {
            binding = (com.boco.eoms.interfaces.group.client.Bulletin.BulletinSoapStub)
                    new com.boco.eoms.interfaces.group.client.Bulletin.BulletinLocator().getBulletinSoap();
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
        value = binding.modifyBulletin(0, new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

}
