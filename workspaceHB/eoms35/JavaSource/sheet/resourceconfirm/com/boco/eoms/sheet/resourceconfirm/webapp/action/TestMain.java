package com.boco.eoms.sheet.resourceconfirm.webapp.action;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

public class TestMain {
    public static void main(String[] args) throws FileNotFoundException, Exception {
        String nodeName = "notifyWorkSheet";
        Map map = new HashMap();
        String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/resourceConfirm-crm.xml"), nodeName);
        System.out.println("opDetail:" + opDetail);
    }
}
