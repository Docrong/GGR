package com.boco.eoms.knowledge.util;

import java.net.URL;

import krms.AddKnowledgeServiceServiceLocator;
import krms.KnowledgeServiceSoapBindingStub;
import krms.SynchroniseUserServiceServiceLocator;
import krms.SynUserServiceSoapBindingStub;
import krms.UploadFileServiceServiceLocator;
import krms.UploadFileServiceSoapBindingStub;

import com.boco.eoms.commons.util.xml.XmlManage;

/**
 * @author IBM
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class KnowledgeClient {

    public static KnowledgeServiceSoapBindingStub loadAddKnowledgeService() throws Exception {
        String getIrmsStr = XmlManage.getFile("/config/knowledge-config.xml").getProperty("addKnowledgeService");
        System.out.println("addKnowledgeService:" + getIrmsStr);
        URL alarmCreateUrl = new URL(getIrmsStr);
        KnowledgeServiceSoapBindingStub binding = (KnowledgeServiceSoapBindingStub) (new AddKnowledgeServiceServiceLocator().getKnowledgeService(alarmCreateUrl));
        return binding;
    }

    public static SynUserServiceSoapBindingStub loadSynUserService() throws Exception {
        String getIrmsStr = XmlManage.getFile("/config/knowledge-config.xml").getProperty("synUserService");
        System.out.println("synUserService:" + getIrmsStr);
        URL alarmCreateUrl = new URL(getIrmsStr);
        SynUserServiceSoapBindingStub binding = (SynUserServiceSoapBindingStub) (new SynchroniseUserServiceServiceLocator().getSynUserService(alarmCreateUrl));
        return binding;
    }

    public static UploadFileServiceSoapBindingStub loadUploadFileService() throws Exception {
        String getIrmsStr = XmlManage.getFile("/config/knowledge-config.xml").getProperty("uploadFileService");
        System.out.println("uploadFileService:" + getIrmsStr);
        URL alarmCreateUrl = new URL(getIrmsStr);
        UploadFileServiceSoapBindingStub binding = (UploadFileServiceSoapBindingStub) (new UploadFileServiceServiceLocator().getUploadFileService(alarmCreateUrl));
        return binding;
    }
}
