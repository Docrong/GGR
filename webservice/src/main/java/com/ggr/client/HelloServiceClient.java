package com.ggr.client;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

public class HelloServiceClient {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Service service = new Service();
        try {
            Call call = (Call) service.createCall();
            //���õ�ַ
            call.setTargetEndpointAddress("http://localhost:8080/webservice/services/HelloServiceImpl?wsdl");
            //����Ҫִ�еķ���(�������ַ�ʽ������)
            call.setOperationName("sayHello");
//				call.setOperationName(new QName("http://impl.service.ggr.com","sayHello"));
            //����Ҫ�������,���û��Ҫ����Ĳ�������Ҫд��������������������͡�ParameterMode��,��һ��������û�о���Ҫ��
            call.addParameter("param1", org.apache.axis.Constants.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
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

    }
}
