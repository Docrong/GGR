package com.boco.eoms.message.util;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.boco.eoms.message.model.MmsContent;

public class MmsUtil {

	public static List MmsContentUtil(String xmlString) {
		List list = new ArrayList();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xmlString);
		} catch (DocumentException e2) {
			e2.printStackTrace();
		}
		Element root = doc.getRootElement();
		AccessoriesUrls au = new AccessoriesUrls();

		List list1 = root.elements("MmsContent");

		for (int i = 0; i < list1.size(); i++) {
			Element emmsContent = (Element) list1.get(i);
			Element eContent = emmsContent.element("Content");
			String content = eContent.getText();
			Element ePosition = emmsContent.element("postion");
			String position = ePosition.getText();
			Element eContent_Type = emmsContent.element("Content_Type");
			String contentType = eContent_Type.getText();
			MmsContent mmsContent = new MmsContent();
			if (contentType.equals(MsgConstants.MMS_TYPE_TEXT))
				mmsContent.setContent(content);
			else {
				String url = au.getAccessoryUrl(content);
				mmsContent.setContent(url);
			}
			mmsContent.setPosition(position);
			mmsContent.setContentType(contentType);
			list.add(mmsContent);

		}
		return list;

	}

}
