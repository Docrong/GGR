package com.hoo;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

public class SimpleObjectClientTest {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		test5();
//		test6();
	}


public static void test5() {
	String wsdlUrl = "http://localhost:9080/eoms35/services/SuperviseTaskInterface?wsdl";
	try {
        Service service = new Service();
        Call call = null;
        call = (Call) service.createCall();
        QName qn = new QName("urn:SuperviseTaskInterface", "ApiResult");
        call.registerTypeMapping(ApiResult.class, qn,
        new BeanSerializerFactory(ApiResult.class, qn),
        new BeanDeserializerFactory(ApiResult.class, qn));
        System.out.println(">>>createApiResult");
        QName getObjectQn = new QName("http://api/", "testapi");
        call.setOperationName(getObjectQn);
        call.setTargetEndpointAddress(new java.net.URL(wsdlUrl));
        ApiResult rtnSO = (ApiResult) call.invoke(new Object[]{ "liu2" });
        System.out.println(call.invoke(new Object[]{ "liu2" }));
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } 
}



      

}
