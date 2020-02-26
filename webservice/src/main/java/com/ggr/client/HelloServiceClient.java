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
            //??????
            call.setTargetEndpointAddress("http://localhost:9080/eoms35/services/SuperviseTaskInterface?wsdl");
            call.setOperationName("countDataAppend");
//				call.setOperationName(new QName("http://impl.service.ggr.com","sayHello"));
            //????????????,????????????????????д??????????????????????ParameterMode??,?????????????о??????
            call.addParameter("str", org.apache.axis.Constants.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            //???÷????????
            call.setReturnType(org.apache.axis.Constants.XSD_STRING);
            //????WebService????
            String info = "";
            String result = (String) call.invoke(new Object[]{info});
            System.out.println(result);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
