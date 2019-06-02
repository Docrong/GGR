/**
 * EomsAuthenticationServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.eomsAuthenticationClient;

public class EomsAuthenticationServiceTestCase extends junit.framework.TestCase {
    public EomsAuthenticationServiceTestCase(java.lang.String name) {
        super(name);
    }
    /**
	 * 
	 * 测试用例 test1EomsAuthenticationIsAlive 测试 EomsAuthentication服务的isAlive（）
	 * 
	 */
    public void test1EomsAuthenticationIsAlive() throws Exception {
       EomsAuthenticationSoapBindingStub binding;
        try {
            binding = (EomsAuthenticationSoapBindingStub)
                          new EomsAuthenticationServiceLocator().getEomsAuthentication();
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
        value = binding.isAlive();
        // TBD - validate results
    }
    /**
	 * 
	 * 测试用例 test2EomsAuthenticationEomsAuthentication 测试 EomsAuthentication服务的eomsAuthentication（）
	 * 
	 */
    public void test2EomsAuthenticationEomsAuthentication() throws Exception {
       EomsAuthenticationSoapBindingStub binding;
        try {
            binding = (EomsAuthenticationSoapBindingStub)
                          new EomsAuthenticationServiceLocator().getEomsAuthentication();
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
        value = binding.eomsAuthentication(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

}
