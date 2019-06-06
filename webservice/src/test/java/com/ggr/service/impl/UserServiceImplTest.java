package com.ggr.service.impl;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.junit.Assert;
import org.junit.Test;

public class UserServiceImplTest {

	@Test
	public void testGetEasyEg() {
		Service service = new Service();
		try {
			Call call = (Call)service.createCall();
			//���õ�ַ
			call.setTargetEndpointAddress("http://localhost:8080/webservice/services/UserServiceImpl?wsdl");
			//����Ҫִ�еķ���(�������ַ�ʽ������)
			call.setOperationName("getEasyEg");
			//����Ҫ�������,���û��Ҫ����Ĳ�������Ҫд��������������������͡�ParameterMode��,��һ��������û�о���Ҫ��
			call.addParameter("param1", org.apache.axis.Constants.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
			//���÷��ص�����
			call.setReturnType(org.apache.axis.Constants.XSD_STRING);
			//����WebService����
			String info = "С�������ã�";
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
		fail("Not yet implemented");
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
