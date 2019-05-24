package com.boco.eoms.commons.system.dict.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.exceptions.DocumentCreateException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 23, 2007 5:23:47 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DictXMLDom4jDocumentFactory {

    /**
     * 构造方法初始化缓存map
     */
    public DictXMLDom4jDocumentFactory() {
        cache = new HashMap();
    }

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
        String xmlPath = "classpath:config/"+key+".xml";
        try {
            if (cache.containsKey(key)) {
                return (Document) cache.get(key);
            } else {
                //在xml注册器中查找key对应的xml地址
                xmlPath = (String) this.register.get(key);
                // TODO 可能使用Url方式读取
                Document doc = saxReader
                        .read(StaticMethod.getFileUrl(xmlPath));
                //入cache
                cache.put(key, doc);
                return doc;
            }

        } catch (Exception e) {
            throw new DocumentCreateException("初使化" + xmlPath + "文件不成功\n"
                    + e.getMessage());
        }
    }
}
