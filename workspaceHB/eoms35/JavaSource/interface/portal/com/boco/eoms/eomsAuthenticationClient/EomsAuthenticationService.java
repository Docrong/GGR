/**
 * EomsAuthenticationService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.boco.eoms.eomsAuthenticationClient;

public interface EomsAuthenticationService extends javax.xml.rpc.Service {
    public java.lang.String getEomsAuthenticationAddress();

    public  EomsAuthentication getEomsAuthentication() throws javax.xml.rpc.ServiceException;

    public EomsAuthentication getEomsAuthentication(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
