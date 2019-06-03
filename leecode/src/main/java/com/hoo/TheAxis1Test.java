package com.hoo;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.junit.Test;

import com.hoo.entity.TheUser;

 
public class TheAxis1Test {
	
		private static final String url="http://localhost:8080/webservice/services/TheUserWsddAction";
		
		/**
		 * 最简单的，入参为一个 string ， 反参为一个 string
		 * @throws ServiceException
		 * @throws RemoteException
		 */
		@Test
		//@Ignore
		public void testgetEasyEg() throws ServiceException, RemoteException{
			//获取Service 对象-创建服务  
			Service service=new Service();
			
			//通过 Service 对象获取远程调用对象Call-创建调用句柄  
	        Call call=(Call) service.createCall();  
	        
	        //设置远程调用桩-设置请求地址  
	        call.setTargetEndpointAddress(url);  
	        
	      //设置远程操作方法名  
	        /** 
	        * 设置调用的方法和方法的命名空间； 
	        * 因为这里是手动发布到webroot目录下的，所以命名空间和请求地址一致 
	        * 当然null也可以，因为本身它就没有设置命名空间，一般方法的命名空间是 
	        * 包名倒写组成，如com.hoo.service,ns=http://service.hoo.com 
	        *  getSayHello 是要调用的方法名     
	        */  
	       call.setOperationName(new QName(null,"getEasyEg"));  
	       
	     //设置参数，执行远程方法  
	      String result=(String)call.invoke(new Object[]{"Jecket"});
	      System.out.println(result);
		}
		
		/**
		 * 入参为 String,int
		 * 反参为 TheUser
		 * @throws ServiceException 
		 * @throws RemoteException 
		 */
		/**
		 * @throws ServiceException
		 * @throws RemoteException
		 */
		/**
		 * @throws ServiceException
		 * @throws RemoteException
		 */
		@Test
		//@Ignore
		public void testgetTheUser() throws ServiceException, RemoteException{
			//获取Service 对象-创建服务  
			Service service=new Service();
			
			//通过 Service 对象获取远程调用对象Call-创建调用句柄  
	        Call call=(Call) service.createCall();  
			
	        //设置远程调用桩-设置请求地址  
	        call.setTargetEndpointAddress(url);  
	        
	        //这里的QName的ns和wsdd文件中的对应
	        QName qn = new QName("urn:TheUser", "TheUser");
	        
	        //这里是将对象序列化和反序列化的配置
	        call.registerTypeMapping(com.hoo.entity.TheUser.class, qn, BeanSerializerFactory.class, BeanDeserializerFactory.class);
 
	        //设置要调用的方法的名字
	        call.setOperationName("getTheUser");
	        
	        /*
	         * 这里客户端和服务器端共用一个User，在实际开发中
	         * 客户端和服务器端在不同的机器上，所以User对象可能不一样
	         * 需要我们根据WebService的wsdl分析对象的属性
	         */
	        call.addParameter("username", XMLType.XSD_STRING, ParameterMode.IN);
	        call.addParameter("age", XMLType.XSD_INTEGER, ParameterMode.IN);
	        call.setReturnClass(TheUser.class);
	        TheUser tuser = (TheUser) call.invoke(new Object[] {"Jecket",20});
	        System.out.println("你获取了tuser ,名字是："+tuser.getUsername()+" 年龄是 "+ tuser.getAge()+" 其他信息 "+tuser.getClass());
	       
	        
		}
		
		/**
		 * 入参为  String , int
		 * 反参为 List<TheUser>
		 * @throws ServiceException
		 * @throws RemoteException
		 * 
		 */
		@Test
		//@Ignore
		public void testgetTheUserList() throws ServiceException, RemoteException{
			//获取Service 对象-创建服务  
			Service service=new Service();
			
			//通过 Service 对象获取远程调用对象Call-创建调用句柄  
	        Call call=(Call) service.createCall();  
			
	        //设置远程调用桩-设置请求地址  
	        call.setTargetEndpointAddress(url);  
	        
	        //这里的QName的ns和wsdd文件中的对应
	        QName qn = new QName("urn:TheUser", "TheUser");
	        
	        //这里是将对象序列化和反序列化的配置
	        call.registerTypeMapping(TheUser.class, qn, BeanSerializerFactory.class, BeanDeserializerFactory.class);
 
	        //设置要调用的方法的名字
	        call.setOperationName("getTheUserList");
	        
	        
	        /*
	         * 这里客户端和服务器端共用一个User，在实际开发中
	         * 客户端和服务器端在不同的机器上，所以User对象可能不一样
	         * 需要我们根据WebService的wsdl分析对象的属性
	         */
	        call.addParameter("username", XMLType.XSD_STRING, ParameterMode.IN);
	        call.addParameter("age", XMLType.XSD_INTEGER, ParameterMode.IN);
	        
	        //设置返回值属性
	        call.setReturnClass(List.class);
	        List<TheUser> tus = (List<TheUser>) call.invoke(new Object[] {"Jecket",20});
	        
	        for(TheUser tu: tus){
	            System.out.println("调用 getTheUserList() 你获取了tu ,名字是："+tu.getUsername()+" 年龄是 "+ tu.getAge()+" 其他信息 "+tu.getClass());
	        }
	        
		}
		/**
		 * 入参为：String , int
		 * 反参为：数组 
		 * @throws ServiceException 
		 * @throws RemoteException 
		 */
		/**
		 * @throws ServiceException
		 * @throws RemoteException
		 */
		@Test
		//@Ignore
		public void testgetTheUserGroup() throws ServiceException, RemoteException{
			//获取Service 对象-创建服务  
			Service service=new Service();
			
			//通过 Service 对象获取远程调用对象Call-创建调用句柄  
	        Call call=(Call) service.createCall();  
			
	        //设置远程调用桩-设置请求地址  
	        call.setTargetEndpointAddress(url);  
	        
	        //这里的QName的ns和wsdd文件中的对应
	        QName qn = new QName("urn:TheUser", "TheUser");
	        
	        //这里是将对象序列化和反序列化的配置
	        call.registerTypeMapping(TheUser.class, qn, BeanSerializerFactory.class, BeanDeserializerFactory.class);
	       
	        //设置要调用的方法的名字
	        call.setOperationName("getTheUserGroup");
	        
	        /*
	         * 这里客户端和服务器端共用一个User，在实际开发中
	         * 客户端和服务器端在不同的机器上，所以User对象可能不一样
	         * 需要我们根据WebService的wsdl分析对象的属性
	         */
	        call.addParameter("username", XMLType.XSD_STRING, ParameterMode.IN);
	        call.addParameter("age", XMLType.XSD_INTEGER, ParameterMode.IN);
	        
	        //设置返回值属性
	        call.setReturnClass(TheUser[].class);
	        
	        TheUser[] tu=(TheUser[]) call.invoke(new Object[] {"Jecket",20});
	        
	        for(TheUser tus:tu){
	        	System.out.println("调用了getTheUserGroup() 方法 ，年龄："+tus.getAge()+"  姓名："+tus.getUsername()+" 所属的类："+tus.getClass());
	        }
	        
	        //理解数据的类型
	        /*String[] a={"1","2"};
	        for(String b:a){
	        	System.out.println(b);
	        }*/
		}
		
		@Test
		//@Ignore
		public void testgetTheUserMap() throws ServiceException, RemoteException{
			//获取Service 对象-创建服务  
			Service service=new Service();
			
			//通过 Service 对象获取远程调用对象Call-创建调用句柄  
	        Call call=(Call) service.createCall();  
			
	        //设置远程调用桩-设置请求地址  
	        call.setTargetEndpointAddress(url);  
	        
	        //这里的QName的ns和wsdd文件中的对应
	        QName qn = new QName("urn:TheUser", "TheUser");
	        
	        //这里是将对象序列化和反序列化的配置
	        call.registerTypeMapping(TheUser.class, qn, BeanSerializerFactory.class, BeanDeserializerFactory.class);
	       
	        //设置要调用的方法的名字
	        call.setOperationName("getTheUserMap");
	        
	        //设置返回值属性
	        call.setReturnClass(Map.class);
	        
	        Map<String, TheUser> maps = (Map<String, TheUser>) call.invoke(new Object[]{});
	       /* 
	        TheUser ta=(TheUser)maps.get("tusera");
	        TheUser tb=(TheUser)maps.get("tuserb");
	        System.out.println("调用了getTheUserMap() 方法 ，年龄："+ta.getAge()+"  姓名："+ta.getUsername()+" 所属的类："+ta.getClass());
	        System.out.println("调用了getTheUserMap() 方法 ，年龄："+tb.getAge()+"  姓名："+tb.getUsername()+" 所属的类："+tb.getClass());
	       */ 
	       
	        //遍历  Map 的方法
	        Iterator it=maps.keySet().iterator();
	        while(it.hasNext()){
	        	TheUser ta=maps.get(it.next());
	        	System.out.println("调用了getTheUserMap() 方法 ，年龄："+ta.getAge()+"  姓名："+ta.getUsername()+" 所属的类："+ta.getClass());
	        }
	        
		}
		
		/**
		 * 使用webservice客户端进行调用
		 * 返回的对象都是 object
		 * @throws ServiceException 
		 * @throws RemoteException 
		 */
		@Test
		//@Ignore
		public void testhere() throws ServiceException, RemoteException{
			
		}
	
 
}
