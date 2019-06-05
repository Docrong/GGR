package com.webservice;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

public class Client {

	/*
	 * test supervisetaskBoardDetail3
	 */
	public static void main(String[] args) throws Exception {
		supervisetaskBoardDetail3();

	}
	
	public static void supervisetaskBoardDetail3() throws Exception {
		String str1 = "JX-051-190513-24071";
		String str2 = "commonfault";
		String endpoint = "http://localhost:9080/eoms35/services/SuperviseTaskInterface?wsdl";
		System.out.println("SuperviseTaskManagerImpl,webservice接口,服务端地址====" + endpoint);
		URL url = new URL(endpoint);

		String targetNamespace = "http://api/";
		String operationName = "supervisetaskBoardDetail3";

		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(url);

//		参数1：wsdl文件中的targetNamespace，参数2：  WSDL里面描述的接口名称(要调用的方法)
		call.setOperationName(new javax.xml.namespace.QName(targetNamespace, operationName));

		call.addParameter("sheetid", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("workflowType", XMLType.XSD_STRING, ParameterMode.IN);

//		 设置被调用方法的返回值类型
		call.setReturnType(XMLType.XSD_STRING);
		try {
			String result = (String) call.invoke(new Object[] { str1, str2 });
			System.out.println(result);
		} catch (Exception e) {
			System.out.println("SupervisetaskError");
			e.printStackTrace();
		}
	}
}
