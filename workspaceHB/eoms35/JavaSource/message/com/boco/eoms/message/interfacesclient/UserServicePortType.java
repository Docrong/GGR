
package com.boco.eoms.message.interfacesclient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

//@WebService(name = "UserServicePortType", targetNamespace = "http://10.32.2.136:8085/UserService/services/userservice")
//@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface UserServicePortType {

	//@WebMethod(operationName = "getMobilePair", action = "")
	//@WebResult(name = "out", targetNamespace = "http://10.32.2.136:8085/UserService/services/userservice")
	public String getMobilePair(
			//@WebParam(name = "in0", targetNamespace = "http://10.32.2.136:8085/UserService/services/userservice")
			String in0,
			//@WebParam(name = "in1", targetNamespace = "http://10.32.2.136:8085/UserService/services/userservice")
			String in1);

}
