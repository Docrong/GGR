package com.boco.eoms.commons.system.priv.a_fsh;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.exolab.castor.xml.Unmarshaller;

import com.boco.eoms.commons.system.priv.a_fsh.ParseXMLException;

/**
 * castor ����xml��Ҳ��Ĭ��ʵ����
 * 
 * @author leo
 * 
 */
public class CastorParseXML implements ParseXML {

	/**
	 * @see com.boco.eoms.codegen.ParseXML#xml2object(Class, String)
	 */
	public Object xml2object(Class clz, String xmlPath)
			throws ParseXMLException {
		System.out.println("xmlPath===>"+xmlPath);
		Unmarshaller un = new Unmarshaller(clz);

		un.setValidation(false);
		Object obj = null;
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(new FileInputStream(xmlPath));
			obj = un.unmarshal(isr);
		} catch (Exception e) {
			
			throw new ParseXMLException(e);
		}
		finally{
			try {
				isr.close();
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return obj;
	}
}
