
package com.boco.eoms.message.interfacesclient;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import javax.xml.namespace.QName;

import org.codehaus.xfire.XFireRuntimeException;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Endpoint;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.soap.AbstractSoapBinding;
import org.codehaus.xfire.transport.TransportManager;

public class UserServiceClient {

    private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
    private HashMap endpoints = new HashMap();
    private Service service0;

    public UserServiceClient() {
        create0();
        Endpoint UserServicePortTypeLocalEndpointEP = service0.addEndpoint(new QName("http://10.32.2.136:8085/UserService/services/userservice", "UserServicePortTypeLocalEndpoint"), new QName("http://10.32.2.136:8085/UserService/services/userservice", "UserServicePortTypeLocalBinding"), "xfire.local://UserService");
        endpoints.put(new QName("http://10.32.2.136:8085/UserService/services/userservice", "UserServicePortTypeLocalEndpoint"), UserServicePortTypeLocalEndpointEP);
        Endpoint UserServiceHttpPortEP = service0.addEndpoint(new QName("http://10.32.2.136:8085/UserService/services/userservice", "UserServiceHttpPort"), new QName("http://10.32.2.136:8085/UserService/services/userservice", "UserServiceHttpBinding"), "http://10.32.2.136:8085/UserService/services/UserService");
        endpoints.put(new QName("http://10.32.2.136:8085/UserService/services/userservice", "UserServiceHttpPort"), UserServiceHttpPortEP);
    }

    public Object getEndpoint(Endpoint endpoint) {
        try {
            return proxyFactory.create((endpoint).getBinding(), (endpoint).getUrl());
        } catch (MalformedURLException e) {
            throw new XFireRuntimeException("Invalid URL", e);
        }
    }

    public Object getEndpoint(QName name) {
        Endpoint endpoint = ((Endpoint) endpoints.get((name)));
        if ((endpoint) == null) {
            throw new IllegalStateException("No such endpoint!");
        }
        return getEndpoint((endpoint));
    }

    public Collection getEndpoints() {
        return endpoints.values();
    }

    private void create0() {
        TransportManager tm = (org.codehaus.xfire.XFireFactory.newInstance().getXFire().getTransportManager());
        HashMap props = new HashMap();
        //props.put("annotations.allow.interface", true);
        AnnotationServiceFactory asf = new AnnotationServiceFactory(new Jsr181WebAnnotations(), tm, new AegisBindingProvider(new JaxbTypeRegistry()));
        asf.setBindingCreationEnabled(false);
        service0 = asf.create((com.boco.eoms.message.interfacesclient.UserServicePortType.class), props);
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://10.32.2.136:8085/UserService/services/userservice", "UserServiceHttpBinding"), "http://schemas.xmlsoap.org/soap/http");
        }
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://10.32.2.136:8085/UserService/services/userservice", "UserServicePortTypeLocalBinding"), "urn:xfire:transport:local");
        }
    }

    public UserServicePortType getUserServicePortTypeLocalEndpoint() {
        return ((UserServicePortType) (this).getEndpoint(new QName("http://10.32.2.136:8085/UserService/services/userservice", "UserServicePortTypeLocalEndpoint")));
    }

    public UserServicePortType getUserServicePortTypeLocalEndpoint(String url) {
        UserServicePortType var = getUserServicePortTypeLocalEndpoint();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public UserServicePortType getUserServiceHttpPort() {
        return ((UserServicePortType) (this).getEndpoint(new QName("http://10.32.2.136:8085/UserService/services/userservice", "UserServiceHttpPort")));
    }

    public UserServicePortType getUserServiceHttpPort(String url) {
        UserServicePortType var = getUserServiceHttpPort();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public static void main(String[] args) {


        UserServiceClient client = new UserServiceClient();

        //create a default service endpoint
        UserServicePortType service = client.getUserServiceHttpPort();

        //TODO: Add custom client code here
        //
        //service.yourServiceOperationHere();

        System.out.println("test client completed");
        System.exit(0);
    }

}
