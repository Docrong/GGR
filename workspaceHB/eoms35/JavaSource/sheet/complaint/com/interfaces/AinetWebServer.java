/**
 * AinetWebServer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.interfaces;

public interface AinetWebServer extends javax.xml.rpc.Service {
    public java.lang.String getAinetWebServerHttpPortAddress();

    public com.interfaces.AinetWebServerPortType getAinetWebServerHttpPort() throws javax.xml.rpc.ServiceException;

    public com.interfaces.AinetWebServerPortType getAinetWebServerHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
