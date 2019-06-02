package com.boco.eoms.commons.accessories.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.accessories.dao.jdbc.TawCommonsAccessoriesDaoJdbc;
import com.boco.eoms.commons.accessories.exception.AccessoriesConfigException;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessoriesConfig;
import com.boco.eoms.commons.accessories.model.TawCommonsApplication;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:附件配置管理service实现类
 * </p>
 * <p>
 * Apr 10, 2007 11:00:28 AM
 * </p>
 * 
 * @author 秦敏
 * @version 1.0
 *  
 */
public class TawCommonsAccessoriesConfigManagerByXMLImpl extends BaseManager {
    private String configFilePath;

    private TawCommonsAccessoriesDaoJdbc daoJdbc;

    public String getConfigFilePath() {
        return configFilePath;
    }

    public void setConfigFilePath(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    public TawCommonsAccessoriesDaoJdbc getDaoJdbc() {
        return daoJdbc;
    }

    public void setDaoJdbc(TawCommonsAccessoriesDaoJdbc daoJdbc) {
        this.daoJdbc = daoJdbc;
    }

    /**
     * 获取配置信息
     * 
     * @author 秦敏
     * @return
     * @throws Exception
     */
    public List readConfigInfo() throws AccessoriesConfigException {
        List configInfoList = new ArrayList();
        try {
            String filePath = StaticMethod.getFilePathForUrl(configFilePath);
            File file = new File(filePath);
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(file);
            List childElements = document.getRootElement().getChildren();
            for (int i = 0; i < childElements.size(); i++) {
                TawCommonsAccessoriesConfig config = new TawCommonsAccessoriesConfig();
                Element tempElement = (Element) childElements.get(i);
                config.setAppCode(tempElement.getChild("appCode").getValue());
                config.setAppName(tempElement.getChild("appName").getValue());
                config.setMaxSize(Integer.valueOf(tempElement.getChild(
                        "maxSize").getValue()));
                config.setPath(tempElement.getChild("path").getValue());
                config.setAllowFileType(tempElement.getChild("allowFileType")
                        .getValue());
                configInfoList.add(config);
            }
        } catch (IOException e) {
            BocoLog.error(this, "IO错误：读取配置文件错误");
            throw new AccessoriesConfigException("读取配置文件错误");
        } catch (JDOMException e) {
            BocoLog.error(this, "JDOM解析配置文件错误");
            throw new AccessoriesConfigException("读取配置文件错误");
        }
        return configInfoList;
    }

    /**
     * 保存配置信息（xml文件）
     * 
     * @author 秦敏
     * @param configObject
     *            配置信息
     */
    public void saveinfoToxml(TawCommonsAccessoriesConfig config)
            throws AccessoriesConfigException {
        Document document;
        String filePath;
        try {
            filePath = StaticMethod.getFilePathForUrl(configFilePath);
        } catch (FileNotFoundException e1) {
            throw new AccessoriesConfigException(configFilePath
                    + "is not found");
        }
        try {
            File file = new File(filePath);
            SAXBuilder builder = new SAXBuilder();
            document = builder.build(file);
        } catch (IOException e) {
            BocoLog.error(this, "IO错误：读取配置文件错误");
            throw new AccessoriesConfigException("保存配置文件错误");
        } catch (JDOMException e) {
            BocoLog.error(this, "JDOM解析配置文件错误");
            throw new AccessoriesConfigException("保存配置文件错误");
        }
        Element rootElement = document.getRootElement();
        List childElements = document.getRootElement().getChildren();
        String allowFileType = config.getAllowFileType();
        String appCode = config.getAppCode();
        String appName = getAppNameByAppcode(appCode);
        String maxSize = String.valueOf(config.getMaxSize());
        String path = config.getPath();

        for (int i = 0; i < childElements.size(); i++) {
            Element element = (Element) childElements.get(i);
            Element childElement = element.getChild("appCode");
            String tempAppCode = childElement.getValue();
            if (tempAppCode != null && appCode != null
                    && tempAppCode.equals(appCode)) {
                rootElement.removeContent(element);
                break;
            }
        }

        Element newElement = new Element("application");
        Element newChildElement = new Element("allowFileType");
        newChildElement.setText(allowFileType);
        newElement.addContent(newChildElement);

        newChildElement = new Element("appCode");
        newChildElement.setText(appCode);
        newElement.addContent(newChildElement);

        newChildElement = new Element("appName");
        newChildElement.setText(appName);
        newElement.addContent(newChildElement);

        newChildElement = new Element("maxSize");
        newChildElement.setText(maxSize);
        newElement.addContent(newChildElement);

        newChildElement = new Element("path");
        newChildElement.setText(path);

        newElement.addContent(newChildElement);

        rootElement.addContent(newElement);

        Format format = Format.getPrettyFormat();
        format.setEncoding("GB2312");
        XMLOutputter outp = new XMLOutputter(format);
        try {
            FileOutputStream fo = new FileOutputStream(filePath);
            outp.output(document, fo);
        } catch (FileNotFoundException e) {
            BocoLog.error(this, "保存文件路径错误");
            throw new AccessoriesConfigException("保存配置文件错误");
        } catch (IOException e) {
            BocoLog.error(this, "IO错误：保存配置文件错误");
            throw new AccessoriesConfigException("保存配置文件错误");
        }

    }

    /**
     * 查询配置信息
     * 
     * @author
     * @param appCode
     *            应用模板ID
     *  
     */
    public TawCommonsAccessoriesConfig getTawCommonsAccessoriesConfig(
            String appCode) throws AccessoriesConfigException {
        TawCommonsAccessoriesConfig config = null;
        try {
            String filePath = StaticMethod.getFilePathForUrl(configFilePath);

            File file = new File(filePath);
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(file);
            List childElements = document.getRootElement().getChildren();
            for (int i = 0; i < childElements.size(); i++) {
                Element element = (Element) childElements.get(i);
                Element childElement = element.getChild("appCode");
                String tempAppCode = childElement.getValue();
                if (tempAppCode != null && appCode != null
                        && tempAppCode.equals(appCode)) {
                    config = new TawCommonsAccessoriesConfig();
                    config.setAppCode(element.getChild("appCode").getValue());
                    config.setAppName(element.getChild("appName").getValue());
                    config.setMaxSize(Integer.valueOf(element.getChild(
                            "maxSize").getValue()));
                    config.setPath(element.getChild("path").getValue());
                    config.setAllowFileType(element.getChild("allowFileType")
                            .getValue());
                    break;
                }
            }
        } catch (IOException e) {
            BocoLog.error(this, "IO错误：读取配置文件错误");
            throw new AccessoriesConfigException("读取配置文件错误");
        } catch (JDOMException e) {
            BocoLog.error(this, "JDOM解析配置文件错误");
            throw new AccessoriesConfigException("读取配置文件错误");
        }

        return config;
    }

    /**
     * 删除配置信息
     * 
     * @author 秦敏
     * @param appCode
     *            应用模板ID
     *  
     */
    public void removeTawCommonsAccessoriesConfig(String appCode)
            throws AccessoriesConfigException {
        String filePath = "";
        try {
            filePath = StaticMethod.getFilePathForUrl(configFilePath);
        } catch (FileNotFoundException e1) {
            throw new AccessoriesConfigException(configFilePath
                    + "is not found");
        }
        Document document = null;
        try {
            File file = new File(filePath);
            SAXBuilder builder = new SAXBuilder();
            document = builder.build(file);
        } catch (IOException e) {
            BocoLog.error(this, "IO错误：读取配置文件错误");
            throw new AccessoriesConfigException("保存配置文件错误");
        } catch (JDOMException e) {
            BocoLog.error(this, "JDOM解析配置文件错误");
            throw new AccessoriesConfigException("保存配置文件错误");
        }

        Element rootElement = document.getRootElement();
        List childElements = document.getRootElement().getChildren();

        for (int i = 0; i < childElements.size(); i++) {
            Element element = (Element) childElements.get(i);
            Element childElement = element.getChild("appCode");
            String tempAppCode = childElement.getValue();
            if (tempAppCode != null && appCode != null
                    && tempAppCode.equals(appCode)) {
                rootElement.removeContent(element);
                break;
            }
        }
        Format format = Format.getPrettyFormat();
        format.setEncoding("GB2312");
        XMLOutputter outp = new XMLOutputter(format);
        try {
            FileOutputStream fo = new FileOutputStream(filePath);
            outp.output(document, fo);

        } catch (FileNotFoundException e) {
            BocoLog.error(this, "保存文件路径错误");
            throw new AccessoriesConfigException("保存配置文件错误");
        } catch (IOException e) {
            BocoLog.error(this, "IO错误：保存配置文件错误");
            throw new AccessoriesConfigException("读取配置文件错误");
        }

    }

    /**
     * 根据应用模块ID号查询模块名称
     * 
     * @param appCode
     * @return
     * @author 秦敏
     */
    private String getAppNameByAppcode(String appCode) {
        String appName = "";
        appName = daoJdbc.getApplicatioNameById(Integer.parseInt(appCode));
        return appName;
    }

    /**
     * 获取应用模块信息
     * 
     * @return
     * @author 秦敏
     */
    public List getApplicationInfo() {
        List applicationTag = new ArrayList();
        List applicationInfo = daoJdbc.getTawSystemApplications();
        for (int i = 0; i < applicationInfo.size(); i++) {
            TawCommonsApplication application = (TawCommonsApplication) applicationInfo
                    .get(i);
            applicationTag.add(new org.apache.struts.util.LabelValueBean(
                    application.getAppName(), String.valueOf(application
                            .getAppId())));
        }
        return applicationTag;
    }
}
