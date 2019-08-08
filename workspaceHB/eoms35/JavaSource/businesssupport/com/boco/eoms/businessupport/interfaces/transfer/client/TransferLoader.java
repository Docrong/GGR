package com.boco.eoms.businessupport.interfaces.transfer.client;

import java.net.URL;

import webattemp.AttempXIrmsServiceServiceLocator;
import webattemp.AttempXIrmsServiceSoapBindingStub;

import com.boco.eoms.commons.util.xml.XmlManage;

public class TransferLoader {
    private static AttempXIrmsServiceSoapBindingStub load() throws Exception {
        String getTransferStr = XmlManage.getFile("/com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml").getProperty("ServiceUrl");
        System.out.println("transferService:" + getTransferStr);
        URL transferUrl = new URL(getTransferStr);
        AttempXIrmsServiceSoapBindingStub binding = (AttempXIrmsServiceSoapBindingStub) (new AttempXIrmsServiceServiceLocator().getAttempXIrmsService(transferUrl));
        return binding;
    }

    public static String irmsInitTraphInfos(String userName, String callerPwd, String xml) {
        String result = "";
        try {
            result = TransferLoader.load().irmsInitTraphInfos("", "EOMS", xml, userName, callerPwd);
        } catch (Exception err) {
            err.printStackTrace();
            result = "irmsInitTraphInfos err:" + err.getMessage();
        }
        System.out.println("irmsInitTraphInfos return:" + result);
        return result;
    }

    public static String irmsGetTraphInfos(String userName, String callerPwd, String xml) {
        String result = "";
        try {
            System.out.println("用户名:" + userName);
            result = TransferLoader.load().irmsGetTraphInfos(xml, userName, callerPwd);
        } catch (Exception err) {
            System.out.println("");
            err.printStackTrace();
        }
        System.out.println("irmsGetTraphInfos return:" + result);
        return result;
    }

    public static String irmsAttempArchive(String userName, String callerPwd, String xml) {
        String result = "";
        try {
            result = TransferLoader.load().irmsAttempArchive(xml, userName, callerPwd);
        } catch (Exception err) {
            System.out.println("");
            err.printStackTrace();
        }
        System.out.println("irmsAttempArchive return:" + result);
        return result;
    }
}
