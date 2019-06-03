package com.hoo;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.junit.Test;

import com.hoo.entity.SimpleObject;

public class TestClient {

	public static void main(String[] args) throws RemoteException, ServiceException, MalformedURLException {
//		testgetEasyEg();
//		testobject();
//		test3();
		test6();
	}
	public static void testgetEasyEg() throws ServiceException, RemoteException {
		// 获取Service 对象-创建服务
		Service service = new Service();
		String url = "http://localhost:9080/eoms35/services/SuperviseTaskInterface?wsdl";
		// 通过 Service 对象获取远程调用对象Call-创建调用句柄
		Call call = (Call) service.createCall();

		// 设置远程调用桩-设置请求地址
		call.setTargetEndpointAddress(url);

		// 设置远程操作方法名
		/**
		 * 设置调用的方法和方法的命名空间； 因为这里是手动发布到webroot目录下的，所以命名空间和请求地址一致
		 * 当然null也可以，因为本身它就没有设置命名空间，一般方法的命名空间是
		 * 包名倒写组成，如com.hoo.service,ns=http://service.hoo.com getSayHello 是要调用的方法名
		 */
		call.setOperationName(new QName(null, "teststring"));

		// 设置参数，执行远程方法
		String result = (String) call.invoke(new Object[] { "Jecket" });
		System.out.println(result);
	}

	public static void testobject() throws ServiceException, RemoteException {
		Service service = new Service();
		String url = "http://localhost:9080/eoms35/services/SuperviseTaskInterface?wsdl";
		Call call = (Call) service.createCall();

		// 设置远程调用桩-设置请求地址

		// 这里的QName的ns和wsdd文件中的对应
		QName qn = new QName("urn:ApiResult", "ApiResult");

		// 这里是将对象序列化和反序列化的配置
		call.registerTypeMapping(ApiResult.class, qn, BeanSerializerFactory.class, BeanDeserializerFactory.class);

		String targetNamespace = "http://api/";
		String operationName = "testapi";
		call.setTargetEndpointAddress(url);
		call.setOperationName(new javax.xml.namespace.QName(targetNamespace, operationName));
		call.addParameter("id", XMLType.XSD_STRING, ParameterMode.IN);
		call.setReturnClass(ApiResult.class);
		ApiResult tuser = (ApiResult) call.invoke(new Object[] { "Jecket" });
		System.out.println(tuser.getOk());

	}


	public static void test3() throws RemoteException, ServiceException {
		String url = "http://localhost:9080/eoms35/services/SuperviseTaskInterface?wsdl";
		Service service = new Service();
		 Call call = (Call) service.createCall();
		 call.setTargetEndpointAddress(url);
		 call.setOperationName(new QName(url, "testapi"));//Login为要调用的方法名 

		 call.addParameter("id", XMLType.XSD_STRING, ParameterMode.IN);
		// hoope.views.api.login_service.LoginService为 wsdl中 targetNamespace 的值 以下相同
//		call.addParameter(new QName("http://localhost:9080/eoms35/services/SuperviseTaskInterface",
//		 "username"),XMLType.SOAP_STRING,ParameterMode.IN);//这里的username为传入参数的变量名 字  
//		call.addParameter(new QName("hoope.views.api.login_service.LoginService",
//		 "pwd"),XMLType.SOAP_STRING,ParameterMode.IN);//这里的units为传入参数的变量名字  
//		call.addParameter(new QName("hoope.views.api.login_service.LoginService",
//		 "code"),XMLType.SOAP_STRING,ParameterMode.IN);//这里的code为传入参数的变量名字  
		
		call.setReturnType(new QName("http://localhost:9080/eoms35/services/SuperviseTaskInterface",
		 "response"), ApiResult.class);//ApiResult这里是重点，返回时主要在这配置，ApiResult为返回的对象 

		// 注册映射关系
		 QName XljgInfo = new QName("http://localhost:9080/eoms35/services/SuperviseTaskInterface", "ApiResult");//此处的 LoginInfoDict为WSDL文件中complexType name的属性值 

		 //这里注册映射关系，对自定义的类进行序列化与反序列化。

		  call.registerTypeMapping(ApiResult.class, XljgInfo,
				  new BeanSerializerFactory(ApiResult.class, XljgInfo),
				  new BeanDeserializerFactory(ApiResult.class, XljgInfo));//ApiResult.class同上，
		  ApiResult itv =(ApiResult)
				  call.invoke(new Object[] {"12"});//接口接收的参数
	}
	
	public static void test4() throws ServiceException, RemoteException, MalformedURLException{
		//获取Service 对象-创建服务  
		Service service = new Service();
		Call call = (Call) service.createCall();
		String url = "http://localhost:9080/eoms35/services/SuperviseTaskInterface?wsdl";

        
	    //设置远程调用桩-设置请求地址  
        call.setTargetEndpointAddress(url);  
        
        //这里的QName的ns和wsdd文件中的对应
        QName qn = new QName("urn:ApiResult", "ApiResult");
        
        //这里是将对象序列化和反序列化的配置
//        call.registerTypeMapping(ApiResult.class, qn,
//        		BeanSerializerFactory.class,
//        		BeanDeserializerFactory.class);
        
        call.registerTypeMapping(ApiResult.class, qn,
                
        		new BeanSerializerFactory(ApiResult.class, qn),
        		        
        		new BeanDeserializerFactory(ApiResult.class, qn));

        //设置要调用的方法的名字
        call.setOperationName("testapi");
        
        /*
         * 这里客户端和服务器端共用一个User，在实际开发中
         * 客户端和服务器端在不同的机器上，所以User对象可能不一样
         * 需要我们根据WebService的wsdl分析对象的属性
         */
        call.addParameter("id", XMLType.XSD_STRING, ParameterMode.IN);
//        call.addParameter("age", XMLType.XSD_INTEGER, ParameterMode.IN);
        call.setReturnClass(ApiResult.class);
        ApiResult tuser = (ApiResult) call.invoke(new Object[] {"Jecket"});

	}

public static void test5() {
	 String wsdlUrl = 	   "http://localhost:9080/eoms35/services/SuperviseTaskInterface?wsdl";
     String nameSpaceUri = "http://localhost:9080/eoms35/services/SuperviseTaskInterface";
     try {
         Service service = new Service();
         Call call = null;
         call = (Call) service.createCall();
         QName qn = new QName("urn:ApiResult", "ApiResult");
         call.registerTypeMapping(ApiResult.class, qn,
         new BeanSerializerFactory(ApiResult.class, qn),
         new BeanDeserializerFactory(ApiResult.class, qn));
         System.out.println(">>>createSimpleObject");
         QName getObjectQn = new QName(nameSpaceUri, "testapi");
         call.setOperationName(getObjectQn);
         call.setTargetEndpointAddress(new java.net.URL(wsdlUrl));
         ApiResult rtnSO = (ApiResult) call.invoke(new Object[]
         { "123"});
     } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     } 
}

public static void test6() {
    
        // TODO Auto-generated method stub
        String wsdlUrl = "http://localhost:9080/eoms35/services/SimpleObjectService?wsdl";
        String nameSpaceUri = "http://localhost:9080/eoms35/services/SimpleObjectService";
        try {
            Service service = new Service();
            Call call = null;
            call = (Call) service.createCall();
            QName qn = new QName("urn:SimpleObjectService", "SimpleObject");
            call.registerTypeMapping(SimpleObject.class, qn,
            new BeanSerializerFactory(SimpleObject.class, qn),
            new BeanDeserializerFactory(SimpleObject.class, qn));
            System.out.println(">>>createSimpleObject");
            QName getObjectQn = new QName("http://api/", "createSimpleObject");
            call.setOperationName(getObjectQn);
            call.setTargetEndpointAddress(new java.net.URL(wsdlUrl));
            SimpleObject rtnSO = (SimpleObject) call.invoke(new Object[]
            { new Integer(1), new Float(0.1f), "liu2" });
            System.out.println("return object " + rtnSO.getString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }

       public static void test7() {
    	   String targetendpoint="http://localhost:9080/eoms35/services/SimpleObjectService?wsdl";
       }

}
