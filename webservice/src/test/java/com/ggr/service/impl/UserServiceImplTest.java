package com.ggr.service.impl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.junit.Assert;
import org.junit.Test;

import com.ggr.model.User;

public class UserServiceImplTest {

	@Test
	public void testGetEasyEg() {
		Service service = new Service();
		try {
			Call call = (Call)service.createCall();
			//设置地址
			call.setTargetEndpointAddress("http://localhost:8080/webservice/services/UserServiceImpl?wsdl");
			//设置要执行的方法(以下两种方式都可以)
			call.setOperationName("getEasyEg");
			//设置要传入参数,如果没有要传入的参数，则不要写这个（参数名、参数类型、ParameterMode）,第一个参数啊没有具体要求
			call.addParameter("param1", org.apache.axis.Constants.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
			//设置返回的类型
			call.setReturnType(org.apache.axis.Constants.XSD_STRING);
			//调用WebService服务
			String info = "小鱼儿，你好！";
			String result = (String) call.invoke(new Object[]{info});
			System.out.println(result);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(service);
		
	}

	@Test
	public void testGetUser() {
		Service service = new Service();
		try {
			Call call = (Call)service.createCall();
			//设置地址
			call.setTargetEndpointAddress("http://localhost:8080/webservice/services/UserServiceImpl?wsdl");
			//设置要执行的方法(以下两种方式都可以)
			call.setOperationName("getUser");
			//设置要传入参数,如果没有要传入的参数，则不要写这个（参数名、参数类型、ParameterMode）,第一个参数啊没有具体要求
			call.addParameter("param1", org.apache.axis.Constants.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
			call.addParameter("param2", org.apache.axis.Constants.XSD_INT,javax.xml.rpc.ParameterMode.IN);
			//设置返回的类型
			QName qn=new QName("http://localhost:8080/webservice/services/UserServiceImpl" );
			/*
			 * 返回一个对象是call.setReturnType(qn,User.class);可以不用
			 * 但是要设置反序列化registerTypeMapping
			call.setReturnType(qn,User.class);
			 */

			call.registerTypeMapping(User.class, qn, BeanSerializerFactory.class, BeanDeserializerFactory.class);
			call.setReturnClass(User.class);
			//调用WebService服务
			String info = "testadmin！";
			User user = (User) call.invoke(new Object[]{info,123});
			System.out.println(user.getUsername());
			System.out.println(user.getAge());
			System.out.println(user.getMoney());
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetUserList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserGroup() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserMap() {
		fail("Not yet implemented");
	}

}
