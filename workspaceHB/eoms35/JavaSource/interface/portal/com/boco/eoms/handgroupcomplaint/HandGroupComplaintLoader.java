package com.boco.eoms.handgroupcomplaint;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.crm.bo.CrmLoader;

public class HandGroupComplaintLoader {
	
	/**
	 * 创建opdetail
	 * @param chNameList
	 * @param enNameList
	 * @param contentList
	 * @return
	 */
	public static String createOpDetailXml(List chNameList,List enNameList,List contentList,List analysisList){
		Document dom4jDoc = DocumentHelper.createDocument();		
		Element opDetailElement = dom4jDoc.addElement("opDetail");
		Element recordInfo = opDetailElement.addElement("recordInfo");
		for(int i=0;i<chNameList.size();i++){
			HandGroupComplaintLoader.addContentXML(recordInfo,StaticMethod.nullObject2String(chNameList.get(i)),StaticMethod.nullObject2String(enNameList.get(i)),contentList.get(i),analysisList);
		}
		String xml = dom4jDoc.asXML();
		String opt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		int index = xml.indexOf(opt);
		if(index>=0){
			xml = xml.substring(index+opt.length());
		}
		return xml;
	}
	private static  void addContentXML(Element recordInfo, String cnname, String name,
			Object object,List analysisList) {
		Element filedInfo =recordInfo.addElement("fieldInfo");
		Element fieldCnName = filedInfo.addElement("fieldChName");
		fieldCnName.setText(cnname);
		Element fieldEnName = filedInfo.addElement("fieldEnName");
		fieldEnName.setText(name);
		Element fieldContent = filedInfo.addElement("fieldContent");
		if(object==null){
			fieldContent.setText("");
		}else{
			if(analysisList.contains(name)){
				fieldContent.addCDATA(object.toString());
			}else{
				fieldContent.setText(object.toString());
			}
			
		}
	}
}
