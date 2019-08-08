package com.boco.eoms.sheet.complaint.service.bo;

import java.net.URL;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.interfaces.*;

public class AiNetClient {

    public static void main(String args[]) {
        try {
            String ag = (String) args[0];
            AinetWebServerLocator locator = new AinetWebServerLocator();
            locator.setAinetWebServerHttpPortEndpointAddress("http://10.30.174.16:8089/AiNetEmos/services/AinetWebServer");
            AinetWebServerPortType binding = (AinetWebServerPortType) locator.getAinetWebServerHttpPort();
            String ret = binding.eomsGetTestTaskResult(ag);
            System.out.println(ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAiNetResult(String args) {
        String ag = args;
        String result = "";
        try {

            AinetWebServerLocator locator = new AinetWebServerLocator();
            locator.setAinetWebServerHttpPortEndpointAddress("http://10.30.174.16:8089/AiNetEmos/services/AinetWebServer");
            AinetWebServerPortType binding = (AinetWebServerPortType) locator.getAinetWebServerHttpPort();
            String ret = binding.eomsGetTestTaskResult(ag);
            System.out.println("--爱网络测试结果 --" + ret);
            result = ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
