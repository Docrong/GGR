package com.ggr.client;

import com.alibaba.fastjson.JSONObject;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class Test2 {
	public static void main(String[] args) {
        Service service = new Service();
        try {
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress("http://localhost:9080/eoms35/services/SuperviseTaskInterface?wsdl");
            call.setOperationName("countDataAppend");
            call.addParameter("str", org.apache.axis.Constants.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
            call.setReturnType(org.apache.axis.Constants.XSD_STRING);

            JSONObject json = new JSONObject();
            String s=new String();
            json.put("first_result", "2019-11-26");
            json.put("ne_label", "adasfasd22313");
            json.put("dellisttype", "不处理");
            json.put("abnormal_cycle", "-44");
            json.put("abnormal_frequency", "3");
            json.put("abnormal_frequency_add", "3");
            json.put("sheet_no", "JX-666-191204-66666");
            json.put("operate_userid", "admin");
            System.out.println("输入:"+json.toString());
            String result = (String) call.invoke(new Object[]{json.toString()});
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
}
