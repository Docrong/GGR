/**
 * 
 */
package com.boco.eoms.commons.log.test.service;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.log.service.impl.LogConfigDom4jFactoryBean;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 11, 2008 2:44:23 PM
 * </p>
 * 
 * @author 王蓓颖
 * @version 1.0
 * 
 */
public class LogConfigDom4jFactoryBeanTest extends ConsoleTestCase {
	
	LogConfigDom4jFactoryBean logConfigDom4jFactoryBean = null;
	/**
	 * @see com.boco.eoms.base.test.console.ConsoleTestCase#setUp()
	 */
	protected void onSetUpInTransaction() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		logConfigDom4jFactoryBean = (LogConfigDom4jFactoryBean)getBean("logConfigDom4jFactoryBean");
	}


	
	public void testRead() {
		try{
			Document doc = logConfigDom4jFactoryBean.getDocument("aa1");
			Element element = (Element)doc.selectSingleNode("//log-configs/log-config");
			System.out.println(element.attributeValue("operId"));
			System.out.println(element.attributeValue("operName"));
			System.out.println(element.attributeValue("operDesc"));
			System.out.println(element.getPath());
			element = (Element)doc.selectSingleNode("//log-configs");
			System.out.println(element.attributeValue("modelId"));
			System.out.println(element.attributeValue("modelName"));
			System.out.println(element.getPath());
		} catch (Exception e) {
			e.printStackTrace();
			this.fail();
		}
	}
	
	public void testReadDuplicate() {
		try{
			Document doc = logConfigDom4jFactoryBean.getDocument("aa1");
			
			List list= doc.selectNodes("//log-configs/log-config/*");
			for(Iterator it=list.iterator();it.hasNext();){
				Element element = (Element)it.next();
				System.out.println(element.attributeValue("operId"));
				System.out.println(element.attributeValue("operName"));
				System.out.println(element.attributeValue("operDesc"));
			}
			System.out.println("------------");
			list= doc.selectNodes("//log-config");
			for(Iterator it=list.iterator();it.hasNext();){
				Element element = (Element)it.next();
				System.out.println(element.attributeValue("operId"));
				System.out.println(element.attributeValue("operName"));
				System.out.println(element.attributeValue("operDesc"));
			}
			
			//element = (Element)doc.selectSingleNode("//log-configs");
			//System.out.println(element.attributeValue("modelId"));
			//System.out.println(element.attributeValue("modelName"));
		} catch (Exception e) {
			e.printStackTrace();
			this.fail();
		}
	}
}
