package com.boco.eoms.interSwitchAlarmClient;

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

public class InterSwitchAlarmServiceClient {

	private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
	private HashMap endpoints = new HashMap();
	private Service service0;
	private String netUrl = "http://localhost:8085/eoms/services/InterSwitchAlarm";

	public InterSwitchAlarmServiceClient() {
		create0();

		Endpoint InterSwitchAlarmLocalEndpointEP = service0.addEndpoint(
				new QName(netUrl, "InterSwitchAlarmLocalEndpoint"), new QName(
						netUrl, "InterSwitchAlarmLocalBinding"),
				"xfire.local://InterSwitchAlarmService");
		endpoints.put(new QName(netUrl, "InterSwitchAlarmLocalEndpoint"),
				InterSwitchAlarmLocalEndpointEP);
		Endpoint InterSwitchAlarmEP = service0.addEndpoint(new QName(netUrl,
				"InterSwitchAlarm"), new QName(netUrl,
				"InterSwitchAlarmSoapBinding"), netUrl);
		endpoints
				.put(new QName(netUrl, "InterSwitchAlarm"), InterSwitchAlarmEP);
	}

	public Object getEndpoint(Endpoint endpoint) {
		try {
			return proxyFactory.create((endpoint).getBinding(), (endpoint)
					.getUrl());
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
		TransportManager tm = (org.codehaus.xfire.XFireFactory.newInstance()
				.getXFire().getTransportManager());
		HashMap props = new HashMap();
//		props.put("annotations.allow.interface", true);
		AnnotationServiceFactory asf = new AnnotationServiceFactory(
				new Jsr181WebAnnotations(), tm, new AegisBindingProvider(
						new JaxbTypeRegistry()));
		asf.setBindingCreationEnabled(false);
		service0 = asf.create(
				(com.boco.eoms.interSwitchAlarmClient.InterSwitchAlarm.class),
				props);
		{
			AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0,
					new QName(netUrl, "InterSwitchAlarmLocalBinding"),
					"urn:xfire:transport:local");
		}
		{
			AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0,
					new QName(netUrl, "InterSwitchAlarmSoapBinding"),
					"http://schemas.xmlsoap.org/soap/http");
		}
	}

	public InterSwitchAlarm getInterSwitchAlarmLocalEndpoint() {
		return ((InterSwitchAlarm) (this).getEndpoint(new QName(netUrl,
				"InterSwitchAlarmLocalEndpoint")));
	}

	public InterSwitchAlarm getInterSwitchAlarmLocalEndpoint(String url) {
		InterSwitchAlarm var = getInterSwitchAlarmLocalEndpoint();
		org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
		return var;
	}

	public InterSwitchAlarm getInterSwitchAlarm() {
		return ((InterSwitchAlarm) (this).getEndpoint(new QName(netUrl,
				"InterSwitchAlarm")));
	}

	public InterSwitchAlarm getInterSwitchAlarm(String url) {
		InterSwitchAlarm var = getInterSwitchAlarm();
		org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
		return var;
	}

	public static void main(String[] args) {

		InterSwitchAlarmServiceClient client = new InterSwitchAlarmServiceClient();

		//create a default service endpoint
		try {
		InterSwitchAlarm service = client.getInterSwitchAlarm();
		service.isAlive();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//TODO: Add custom client code here
		//
		//service.yourServiceOperationHere();

		System.out.println("test client completed");
		System.exit(0);
	}

}
