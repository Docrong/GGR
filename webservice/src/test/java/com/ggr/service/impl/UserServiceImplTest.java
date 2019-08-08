package com.ggr.service.impl;

import com.ggr.model.User;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

import static org.junit.Assert.fail;

public class UserServiceImplTest {

    @Test
    public void testGetEasyEg() {
        Service service = new Service();
        try {
            Call call = (Call) service.createCall();
            //���õ�ַ
            call.setTargetEndpointAddress("http://localhost:8080/webservice/services/UserServiceImpl?wsdl");
            //����Ҫִ�еķ���(�������ַ�ʽ������)
            call.setOperationName("getEasyEg");
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
        Assert.assertNotNull(service);

    }

    @Test
    public void testGetUser() {
        Service service = new Service();
        try {
            Call call = (Call) service.createCall();
            //���õ�ַ
            call.setTargetEndpointAddress("http://localhost:8080/webservice/services/UserServiceImpl?wsdl");
            //����Ҫִ�еķ���(�������ַ�ʽ������)
            call.setOperationName("getUser");
            //����Ҫ�������,���û��Ҫ����Ĳ�������Ҫд��������������������͡�ParameterMode��,��һ��������û�о���Ҫ��
            call.addParameter("param1", org.apache.axis.Constants.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.addParameter("param2", org.apache.axis.Constants.XSD_INT, javax.xml.rpc.ParameterMode.IN);
            //���÷��ص�����
            QName qn = new QName("http://localhost:8080/webservice/services/UserServiceImpl");
			/*
			 * ����һ��������call.setReturnType(qn,User.class);���Բ���
			 * ����Ҫ���÷����л�registerTypeMapping
			call.setReturnType(qn,User.class);
			 */

            call.registerTypeMapping(User.class, qn, BeanSerializerFactory.class, BeanDeserializerFactory.class);
            call.setReturnClass(User.class);
            //����WebService����
            String info = "testadmin��";
            User user = (User) call.invoke(new Object[]{info, 123});
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
