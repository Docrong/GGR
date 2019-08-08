package com.ggr.hoo.client;

import com.ggr.hoo.entity.User;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * * <b>function:</b>axis WebService传递复杂对象，客户端 * @author hoojo * @createDate Dec
 * 16, 2010 10:32:57 PM * @file UserServiceClient.java * @package com.hoo.client
 * * @project AxisWebService * @blog http://blog.csdn.net/IBM_hoojo * @email
 * hoojo_@126.com * @version 1.0
 */
@SuppressWarnings("unchecked")
public class UserServiceClient {
    private static final String url = "http://localhost:9080/eoms35/services/SuperviseTaskInterface?wsdl";
    private static Service service = new Service();

    public static User getUser(String name) throws ServiceException, RemoteException {
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(url);
        QName qn = new QName("urn:User", "User"); // call.registerTypeMapping(User.class, qn, new
        // BeanSerializerFactory(User.class, qn), new
        // BeanDeserializerFactory(User.class, qn));
        // call.registerTypeMapping(User.class, qn,
        // BeanSerializerFactory.class, BeanDeserializerFactory.class);
        // call.setOperationName("getUserByName");
        /*
         * * 这里客户端和服务器端共用一个User，在实际开发中 * 客户端和服务器端在不同的机器上，所以User对象可能不一样 *
         * 需要我们根据WebService的wsdl分析对象的属性
         */
        call.addParameter("name", XMLType.XSD_STRING, ParameterMode.IN);
        call.setReturnClass(User.class);
        User user = (User) call.invoke(new Object[]{"jackson"});
        return user;
    }

    public static void setUser(User user) throws ServiceException, RemoteException {
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(url);
//这里的QName的ns和wsdd文件中的对应 
        QName qn = new QName("urn:User", "User");
//这里是将对象序列化和反序列化的配置 
        call.registerTypeMapping(User.class, qn, new BeanSerializerFactory(User.class, qn),
                new BeanDeserializerFactory(User.class, qn));
        call.setOperationName("setUser");
//设置参数类型 
        call.addParameter("user", qn, ParameterMode.IN);
        call.invoke(new Object[]{user});
    }

    public static List<User> getUsers(int i) throws ServiceException, RemoteException {
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(url); // 这里的QName的ns和wsdd文件中的对应
        QName qn = new QName("urn:User", "User");
//这里是将对象序列化和反序列化的配置 
        call.registerTypeMapping(User.class, qn, new BeanSerializerFactory(User.class, qn),
                new BeanDeserializerFactory(User.class, qn));
        call.setOperationName("getUsers");
        call.addParameter("i", XMLType.XSD_INT, ParameterMode.IN);
        call.setReturnClass(List.class);
        List<User> users = (List<User>) call.invoke(new Object[]{i});
        return users;
    }

    public static void setUsers(List<User> users) throws ServiceException, RemoteException {
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(url); // 这里的QName的ns和wsdd文件中的对应
        QName qn = new QName("urn:User", "User"); // 这里是将对象序列化和反序列化的配置
        call.registerTypeMapping(User.class, qn, new BeanSerializerFactory(User.class, qn),
                new BeanDeserializerFactory(User.class, qn));
        call.setOperationName("setUsers");
        call.addParameter("users", XMLType.XSD_ANYTYPE, ParameterMode.IN);
        call.invoke(new Object[]{users});
    }

    public static void setUserMap(Map<String, User> maps) throws ServiceException, RemoteException {
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(url);
        // 这里的QName的ns和wsdd文件中的对应 QName qn = new QName("urn:User", "User");
        // //这里是将对象序列化和反序列化的配置 call.registerTypeMapping(User.class, qn, new
        // BeanSerializerFactory(User.class, qn), new
        // BeanDeserializerFactory(User.class, qn));
        // call.setOperationName("setUserMap"); call.addParameter("maps",
        // XMLType.XSD_ANYTYPE, ParameterMode.IN); call.invoke(new Object[] { maps
        // }); } public static Map<String, User> getUserMap() throws
        // RemoteException, ServiceException { Call call = (Call)
        // service.createCall(); call.setTargetEndpointAddress(url);
        // //这里的QName的ns和wsdd文件中的对应 QName qn = new QName("urn:User", "User");
        // //这里是将对象序列化和反序列化的配置 call.registerTypeMapping(User.class, qn, new
        // BeanSerializerFactory(User.class, qn), new
        // BeanDeserializerFactory(User.class, qn));
        // call.setOperationName("getUserMap"); //call.addParameter("null",
        // XMLType.XSD_ANYTYPE, ParameterMode.IN); call.setReturnClass(Map.class);
        // Map<String, User> maps = (Map<String, User>) call.invoke(new Object[]{});
        // return maps; } public static User[] getUserArray(int i) throws
        // ServiceException, RemoteException { Call call = (Call)
        // service.createCall(); call.setTargetEndpointAddress(url);
        // //这里的QName的ns和wsdd文件中的对应 QName qn = new QName("urn:User", "User");
        // //这里是将对象序列化和反序列化的配置 call.registerTypeMapping(User.class, qn, new
        // BeanSerializerFactory(User.class, qn), new
        // BeanDeserializerFactory(User.class, qn));
        // call.setOperationName("getUserArray"); call.addParameter("i",
        // XMLType.XSD_INT, ParameterMode.IN); call.setReturnClass(User[].class);
        // User[] users = (User[]) call.invoke(new Object[] { i }); return users; }
        // public static void setUserArray(User[] users) throws RemoteException,
        // ServiceException { Call call = (Call) service.createCall();
        // call.setTargetEndpointAddress(url); //这里的QName的ns和wsdd文件中的对应 QName qn =
        // new QName("urn:User", "User"); //这里是将对象序列化和反序列化的配置
        // call.registerTypeMapping(User.class, qn, new
        // BeanSerializerFactory(User.class, qn), new
        // BeanDeserializerFactory(User.class, qn));
        // call.setOperationName("setUserArray"); call.addParameter("users",
        // XMLType.XSD_ANYTYPE, ParameterMode.IN); call.invoke(new Object[] { users
        // }); } @SuppressWarnings("deprecation") public static void main(String[]
        // args) throws RemoteException, ServiceException { User user = new User();
        // user.setId(new Date().getSeconds()); user.setName("tom");
        // user.setAddress("china"); user.setEmail("tom@hoo.com");
        // System.out.println("============getUser=============");
        // System.out.println(getUser("jack"));
        // System.out.println("============setUser=============");
        // setUser(user);//看服务器端控制台 System.out.println("============getUsers
        // List============="); List<User> users = getUsers(3); for (User u : users)
        // { System.out.println(u); } System.out.println("============setUsers
        // List============="); setUsers(users);
        // System.out.println("============getUserMap============="); Map<String,
        // User> map = getUserMap(); System.out.println(map);
        // System.out.println("============setUserMap=============");
        // setUserMap(map);
        // System.out.println("============getUserArray============="); User[] arr =
        // getUserArray(3);
        // System.out.println("============setUserArray=============");
        // setUserArray(arr); } }
    }
}
