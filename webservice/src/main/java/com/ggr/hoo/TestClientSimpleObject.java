package com.ggr.hoo;

import com.ggr.hoo.entity.SimpleObject;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import javax.xml.namespace.QName;

public class TestClientSimpleObject {

    public static void main(String[] args) {

        // TODO Auto-generated method stub
        String wsdlUrl = "http://localhost:9080/eoms35/services/SimpleObjectService?wsdl";
        try {
            Service service = new Service();
            Call call = null;
            call = (Call) service.createCall();
            QName qn = new QName("urn:SimpleObjectService", "SimpleObject");
            call.registerTypeMapping(SimpleObject.class, qn, new BeanSerializerFactory(SimpleObject.class, qn),
                    new BeanDeserializerFactory(SimpleObject.class, qn));
            System.out.println(">>>createSimpleObject");
            QName getObjectQn = new QName("http://api/", "createSimpleObject");
            call.setOperationName(getObjectQn);
            call.setTargetEndpointAddress(new java.net.URL(wsdlUrl));
            SimpleObject rtnSO = (SimpleObject) call.invoke(new Object[]{new Integer(1), new Float(0.1f), "liu2"});
            System.out.println("return object " + rtnSO.getString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
