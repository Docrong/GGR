package com.boco.eoms.sheet.commonfaultLocal.webapp.action;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.commonfault.webapp.action.CommonFaultAction;;

/**
 * Title:集中化故障工单(各省个性化Action类）
 * Description:各省个性化功能Action层方法均放置在此类中。
 * Wed Sep 11 11:34:50 GMT 2013
 *
 * @author qinmin
 * @version 1.0
 */

public class CentralCommonfaultLocalAction extends CommonFaultAction {

    public ActionForward interfaceForBaiKe(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String token = StaticMethod.null2String(request.getParameter("token"));
        String province = XmlManage.getFile("/config/alarmEncyclopediasConfig.xml").getProperty("connparam.province");//从配置文件中读取省份
        String systemName = XmlManage.getFile("/config/alarmEncyclopediasConfig.xml").getProperty("connparam.systemName");//从配置文件中读取系统名
        String stringType = XmlManage.getFile("/config/alarmEncyclopediasConfig.xml").getProperty("connparam.stringType");//从配置文件中读取字符串格式
        String urlTitle = XmlManage.getFile("/config/alarmEncyclopediasConfig.xml").getProperty("connparam.urlTitle");//从配置文件中读取字符串格式
        String urlStr = "";
        if ("xml".equals(stringType)) {
            urlStr = urlTitle + province + "/" + systemName + ".xml";
        } else {
            urlStr = urlTitle + province + "/" + systemName + ".json";
        }

        System.out.println("请求地址为:" + urlStr + "?token=" + token);

        URL url = new URL(urlStr);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        httpConn.setRequestMethod("POST");
        httpConn.setUseCaches(false);
        httpConn.setInstanceFollowRedirects(true);
        //charset=utf-8 解决了编码问题
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        //httpConn.setReadTimeout(20 * 1000); // 缓存的最长时间
        //httpConn.setConnectTimeout(DEFAULT_SOCKET_TIMEOUT);
        System.setProperty("sun.net.client.defaultConnectTimeout", "60000");
        System.setProperty("sun.net.client.defaultReadTimeout", "30000");

        String content = "token=" + token;
        httpConn.connect();
        DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
        dos.writeBytes(content);
        if (null != dos) {
            dos.flush();
        }
        if (null != dos) {
            dos.close();
        }
        InputStream inStream = httpConn.getInputStream();
        if (null == inStream) {
            httpConn.disconnect();
            //return "httpRequestPost连接失败,false";
        }
        //判断连接是否成功
        StringBuilder returnbuilder = new StringBuilder();
        if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));

            String readLine = "";
            while ((readLine = bufferReader.readLine()) != null) {
                returnbuilder.append(readLine);
            }
            if (null != inStream) {
                inStream.close();
            }
            if (null != bufferReader) {
                bufferReader.close();
            }
            if (null != httpConn) {
                httpConn.disconnect();
            }
            //return returnbuilder.toString();
        } else {
            if (null != httpConn) {
                httpConn.disconnect();
            }
            //return "httpRequestPost连接失败,false";
        }

        LinkedHashMap map = new LinkedHashMap();
        if ("xml".equals(stringType)) {
            System.out.print("++++returnbuilder.toString()++++++++" + returnbuilder.toString());
            String xml = returnbuilder.toString();
            xml = xml.substring(0, xml.indexOf("</wikiBean>") + 11);
            map = xmlElements(xml);
        } else {
            System.out.print("++++returnbuilder.toString()++++++++" + returnbuilder.toString());
            String xml = returnbuilder.toString();
            xml = xml.substring(0, xml.indexOf("</wikiBean>") + 11);
            map = jsonElements(xml);
        }

        request.setAttribute("id", map.get("id"));
        request.setAttribute("title", map.get("title"));
        request.setAttribute("alarmId", map.get("alarmId"));
        request.setAttribute("alarmSpecialty", map.get("alarmSpecialty"));
        request.setAttribute("equipmentFactory", map.get("equipmentFactory"));
        request.setAttribute("equipmentType", map.get("equipmentType"));
        request.setAttribute("alarmName", map.get("alarmName"));
        request.setAttribute("alarmNameCn", map.get("alarmNameCn"));
        request.setAttribute("alarmLevel", map.get("alarmLevel"));
        request.setAttribute("alarmExplanation", map.get("alarmExplanation"));
        request.setAttribute("busiEffect", map.get("busiEffect"));
        request.setAttribute("handOpinion", map.get("handOpinion"));
        return mapping.findForward("baikeInterface");
    }

    /**
     * 解析XML字符串到map
     */
    public static LinkedHashMap xmlElements(String xmlDoc) {
        LinkedHashMap map = new LinkedHashMap();
        //创建一个新的字符串
        StringReader read = new StringReader(xmlDoc);
        //创建新的输入源SAX 解析器将使用InputSource对象来确定如何读取XML输入
        InputSource source = new InputSource(read);
        //创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        try {
            //通过输入源构造一个Document
            Document doc = sb.build(source);
            //取得根元素
            Element root = doc.getRootElement();
            System.out.println(root);
            if (root.getContentSize() > 0) {
                //得到根元素所有子元素的集合
                //Element secondTag = root.getChild("wikiBean");
                //System.out.println(secondTag);
                //List secondList = secondTag.getChildren();
                //获得XML中的命名空间（XML中未定义可不写）
                Namespace ns = root.getNamespace();
                //Element et = null;
                //for(int i = 0;i<secondList.size();i++){
                //et = (Element)secondList.get(i);//循环一次得到子元素
                map.put("id", root.getChild("id", ns).getText());
                map.put("title", root.getChild("title", ns).getText());
                map.put("alarmId", root.getChild("alarmId", ns).getText());
                map.put("alarmSpecialty", root.getChild("alarmSpecialty", ns).getText());
                map.put("equipmentFactory", root.getChild("equipmentFactory", ns).getText());
                map.put("equipmentType", root.getChild("equipmentType", ns).getText());
                map.put("alarmName", root.getChild("alarmName", ns).getText());
                map.put("alarmNameCn", root.getChild("alarmNameCn", ns).getText());
                map.put("alarmLevel", root.getChild("alarmLevel", ns).getText());
                map.put("alarmExplanation", root.getChild("alarmExplanation", ns).getText());
                map.put("busiEffect", root.getChild("busiEffect", ns).getText());
                map.put("handOpinion", root.getChild("handOpinion", ns).getText());
                //System.out.println(et.getChild("fieldEnName", ns).getText()+"---"+et.getChild("fieldContent", ns).getText());
                //}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 解析json字符串到map
     */
    public static LinkedHashMap jsonElements(String jsonDoc) {
        String sJson = "[{'gwcxxid':'1','spsl':'2'},{'gwcxxid':'1','spsl':'2'},{'gwcxxid':'3','spsl':'4'}]";
        JSONArray jsonArray = new JSONArray(jsonDoc);
        JSONObject jsonObj = jsonArray.getJSONObject(0);
        JSONObject jsonElements = jsonObj.getJSONObject("wikiBean");

        LinkedHashMap map = new LinkedHashMap();
        map.put("id", jsonElements.get("id"));
        map.put("title", jsonElements.get("title"));
        map.put("alarmId", jsonElements.get("alarmId"));
        map.put("alarmSpecialty", jsonElements.get("alarmSpecialty"));
        map.put("equipmentFactory", jsonElements.get("equipmentFactory"));
        map.put("equipmentType", jsonElements.get("equipmentType"));
        map.put("alarmName", jsonElements.get("alarmName"));
        map.put("alarmNameCn", jsonElements.get("alarmNameCn"));
        map.put("alarmLevel", jsonElements.get("alarmLevel"));
        map.put("alarmExplanation", jsonElements.get("alarmExplanation"));
        map.put("busiEffect", jsonElements.get("busiEffect"));
        map.put("handOpinion", jsonElements.get("handOpinion"));

        return map;
    }

}
 



