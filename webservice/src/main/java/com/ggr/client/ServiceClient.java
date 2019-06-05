package com.ggr.client;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class ServiceClient {

	public static void main(String[] args) {
			// TODO Auto-generated method stub
			Service service = new Service();
			try {
				Call call = (Call)service.createCall();
				//设置地址
				call.setTargetEndpointAddress("http://localhost:8080/webservice/services/HelloServiceImpl?wsdl");
				//设置要执行的方法(以下两种方式都可以)
//				call.setOperationName("sayHello");
				call.setOperationName(new QName("http://impl.service.ggr.com","sayHello"));
				//设置要传入参数,如果没有要传入的参数，则不要写这个（参数名、参数类型、ParameterMode）
				call.addParameter("info", org.apache.axis.Constants.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
				//设置返回的类型
				call.setReturnType(org.apache.axis.Constants.XSD_STRING);
				//调用WebService服务
				String info = "小鱼儿，你好！";
				String result = (String) call.invoke(new Object[]{info});
				System.out.println(result);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}
