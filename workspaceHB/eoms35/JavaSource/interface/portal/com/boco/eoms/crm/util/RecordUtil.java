package com.boco.eoms.crm.util;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.crm.model.RecordInfo;

public class RecordUtil {
	public static String createOpDetailXml(List chNameList, List enNameList, List contentList)
    {
        Document dom4jDoc = DocumentHelper.createDocument();
        Element opDetailElement = dom4jDoc.addElement("opDetail");
        Element recordInfo = opDetailElement.addElement("recordInfo");
        for(int i = 0; i < chNameList.size(); i++)
            addContentXML(recordInfo, StaticMethod.nullObject2String(chNameList.get(i)), StaticMethod.nullObject2String(enNameList.get(i)), contentList.get(i));

        String xml = dom4jDoc.asXML();
        String opt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        int index = xml.indexOf(opt);
        if(index >= 0)
            xml = xml.substring(index + opt.length());
        return xml;
    }
    public static String createOpDetailXml(List recordInfoList){
		Document dom4jDoc = DocumentHelper.createDocument();		
		Element opDetailElement = dom4jDoc.addElement("opDetail");
		
		for(int i=0;i<recordInfoList.size();i++){
			Element recordInfo = opDetailElement.addElement("recordInfo");
			List fieldList = (List)recordInfoList.get(i);
			for(int j=0;j<fieldList.size();j++){
				RecordInfo info = (RecordInfo)fieldList.get(j);
				RecordUtil.addContentXML(recordInfo,info.getFieldChName(),info.getFieldEnName(),info.getFieldContent());
			}			
		}
		
		String xml = dom4jDoc.asXML();
		String opt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		int index = xml.indexOf(opt);
		if(index>=0){
			xml = xml.substring(index+opt.length());
		}
		return xml;
	}

    private static void addContentXML(Element recordInfo, String cnname, String name, Object object)
    {
        Element filedInfo = recordInfo.addElement("fieldInfo");
        Element fieldCnName = filedInfo.addElement("fieldChName");
        fieldCnName.setText(cnname);
        Element fieldEnName = filedInfo.addElement("fieldEnName");
        fieldEnName.setText(name);
        Element fieldContent = filedInfo.addElement("fieldContent");
        if(object == null)
            fieldContent.setText("");
        else
            fieldContent.setText(object.toString());
    }

    public static String createAttachRefXml(String attachIds)
    {
        System.out.println("attachIds=" + attachIds);
        if(attachIds == null || attachIds.equals(""))
            return "";
        attachIds = attachIds.replaceAll("'", "");
        Document dom4jDoc = DocumentHelper.createDocument();
        Element opDetailElement = dom4jDoc.addElement("attachRef");
        ITawCommonsAccessoriesManager mgr = (ITawCommonsAccessoriesManager)ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
        String uploadType = XmlManage.getFile("/com/boco/eoms/crm/config/crm-config.xml").getProperty("uploadType");
        if(uploadType == null || uploadType.endsWith(""))
            uploadType = "http";
        String attacArray[] = attachIds.split(",");
        for(int i = 0; i < attacArray.length; i++)
        {
            String id = attacArray[i];
            TawCommonsAccessories tawCommonsAccessories = mgr.getSystemToOther(id, uploadType);
            if(tawCommonsAccessories != null && tawCommonsAccessories.getAccessoriesPath() != null)
            {
                Element recordInfo = opDetailElement.addElement("attachInfo");
                Element attachName = recordInfo.addElement("attachName");
                attachName.setText(tawCommonsAccessories.getAccessoriesCnName());
                Element attachURL = recordInfo.addElement("attachURL");
                attachURL.setText(tawCommonsAccessories.getAccessoriesPath());
                Element attachLength = recordInfo.addElement("attachLength");
                attachLength.setText(String.valueOf(tawCommonsAccessories.getAccessoriesSize()));
            }
        }

        String xml = dom4jDoc.asXML();
        String opt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        int index = xml.indexOf(opt);
        if(index >= 0)
            xml = xml.substring(index + opt.length());
        System.out.println("attach=" + xml);
        return xml;
    }
}
