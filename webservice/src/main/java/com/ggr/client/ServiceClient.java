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
				//���õ�ַ
				call.setTargetEndpointAddress("http://localhost:8080/webservice/services/HelloServiceImpl?wsdl");
				//����Ҫִ�еķ���(�������ַ�ʽ������)
//				call.setOperationName("sayHello");
				call.setOperationName(new QName("http://impl.service.ggr.com","sayHello"));
				//����Ҫ�������,���û��Ҫ����Ĳ�������Ҫд��������������������͡�ParameterMode��
				call.addParameter("info", org.apache.axis.Constants.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
				//���÷��ص�����
				call.setReturnType(org.apache.axis.Constants.XSD_STRING);
				//����WebService����
				String info = "С�������ã�";
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
