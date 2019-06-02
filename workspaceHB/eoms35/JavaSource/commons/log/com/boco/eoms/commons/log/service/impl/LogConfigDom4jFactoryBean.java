/**
 * 
 */
package com.boco.eoms.commons.log.service.impl;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.log.exceptions.DocumentCreateException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 11, 2008 10:54:42 AM
 * </p>
 * 
 * @author 王蓓颖
 * @version 1.0
 * 
 */
public class LogConfigDom4jFactoryBean {
	
    /**
     * 缓存document
     */
    private Map cache;

    /**
     * xml注册在其中,如key=major,value=classpath:com/boco/eoms/sample.xml
     */
    private Properties register;

    /**
     * @return the register
     */
    public Properties getRegister() {
        return register;
    }

    /**
     * @param register
     *            the register to set
     */
    public void setRegister(Properties register) {
        this.register = register;
    }

    private SAXReader saxReader;

    public void setSaxReader(SAXReader saxReader) {
        this.saxReader = saxReader;
    }

    public Document getDocument(String key) throws DocumentCreateException {
        //xml地址
        String xmlPath = null;
        try {
            if (cache.containsKey(key)) {
                return (Document) cache.get(key);
            } else {
                //在xml注册器中查找key对应的xml地址
                xmlPath = (String) this.register.get(key);
                // TODO 可能使用Url方式读取
                Document doc = saxReader
                        .read(StaticMethod.getFileUrl(xmlPath));
                cache.put(key, doc);
                return doc;
            }

        } catch (Exception e) {
            throw new DocumentCreateException("初使化" + xmlPath + "文件不成功\n"
                    + e.getMessage());
        }
    }

	/**
	 * 
	 */
	public LogConfigDom4jFactoryBean() {
		super();
		cache = new HashMap();
	}
	
	/**
	 * @param register
	 * @param saxReader
	 */
	public LogConfigDom4jFactoryBean(Properties register, SAXReader saxReader) {
		super();
		cache = new HashMap();
		this.register = register;
		this.saxReader = saxReader;
		try {
			init();
		} catch (Exception e) {
            e.printStackTrace();
		}
	}

	private void init() throws DocumentCreateException{
		Set ketSet = register.keySet();
		String xmlPath = null;
		String key = null;
		for(Iterator it=ketSet.iterator();it.hasNext();) {
			try {
				key = (String)it.next();
//			在xml注册器中查找key对应的xml地址
				xmlPath = (String) this.register.get(key);
				// TODO 可能使用Url方式读取
				Document doc = saxReader
				        .read(StaticMethod.getFileUrl(xmlPath));
				cache.put(key, doc);
			} catch (Exception e) {
	            throw new DocumentCreateException("初使化" + xmlPath + "文件不成功\n"
	                    + e.getMessage());
			}
		}
	}
}
