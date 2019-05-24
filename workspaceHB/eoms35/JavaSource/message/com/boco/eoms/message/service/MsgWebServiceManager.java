package com.boco.eoms.message.service;

import java.net.URL;

import java.util.Vector;

import java.util.Arrays;

 

import org.apache.soap.Constants;

import org.apache.soap.SOAPException;

import org.apache.soap.Envelope;

import org.apache.soap.Fault;

import org.apache.soap.rpc.Call;

import org.apache.soap.rpc.Response;

import org.apache.soap.rpc.Parameter;
public interface MsgWebServiceManager {
	public String regetData(String serviceId);	
}
