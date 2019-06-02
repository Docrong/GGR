package com.boco.eoms.interfaces.province.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xpath.XPathAPI;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.util.AttachRef;


/**
 * 集团EOMS系统和省EOMS系统互联接口工具类
 * @author qinmin
 *
 */
public class JTSheetInterfaceUtils {
	/**
	 * 构建opdetail字符串
	 * @param chNameList
	 * @param enNameList
	 * @param contentList
	 * @return
	 */
	public static String createOpDetailXml(Map resultMap,Map cnNameMap){
		if(resultMap!=null&&!resultMap.isEmpty()){
			Document document = new Document();
			Element opDetailElement = new Element("opDetail");
			document.addContent(opDetailElement);
			Element recordInfo = new Element("recordInfo");
			opDetailElement.addContent(recordInfo);
			Iterator keyIterator=resultMap.keySet().iterator();
			while(keyIterator.hasNext()){
			  String name=StaticMethod.nullObject2String(keyIterator.next());
			  String cnName=StaticMethod.nullObject2String(cnNameMap.get(name));
			  String value=StaticMethod.nullObject2String(resultMap.get(name));
			  addContentXML(recordInfo,cnName,name,value);
			}
			
			 Format format = Format.getCompactFormat();
		     format.setEncoding("UTF-8");// 设置xml文件的字符为UTF-8，解决中文问题
		     XMLOutputter xmlout = new XMLOutputter(format);
		     ByteArrayOutputStream bo = new ByteArrayOutputStream();
		     try{
		    	 xmlout.output(document, bo);
		     }catch(Exception e){
		    	 return "";
		     }
		     return bo.toString().trim();
		}else {
			return "";
		}	
	}

	private static void addContentXML(Element recordInfo, String cnname, String name, Object object) {
		Element filedInfo = new Element("fieldInfo");
		recordInfo.addContent(filedInfo);
		Element fieldCnName = new Element("fieldChName");
		fieldCnName.setText(cnname);
		filedInfo.addContent(fieldCnName);
		Element fieldEnName = new Element("fieldEnName");
		fieldEnName.setText(name);
		filedInfo.addContent(fieldEnName);
		Element fieldContent = new Element("fieldContent");
		if (object == null)
			fieldContent.setText("");
		else
			fieldContent.setText(object.toString());
		filedInfo.addContent(fieldContent);
	}
	
	/**
     * 检查接口传递值是否满足长度等限制
     * @param interfaceInfoList
     * @return
     */
    public static String checkLength(List interfaceInfoList){
       String checkResult="";
       //获取配置的接口字段限制条件
       try{
    	String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/interfaces/province/config/JISheetService-util.xml");
    	SAXBuilder dc = new SAXBuilder();
		Document doc = dc.build(new File(filePath));
		Element element = doc.getRootElement();
		Element interfaceInfoCheckElement=element.getChild("interfaceInfoCheck");
		List list = interfaceInfoCheckElement.getChildren();
		HashMap ifAllowNullMap=new HashMap();
		HashMap interfaceLengthMap=new HashMap();
		HashMap operateTypeMap=new HashMap();
		for (int i = 0; list!=null&&!list.isEmpty()&&i < list.size(); i++){
			Element node = (Element)list.get(i);
			String interfaceName=StaticMethod.nullObject2String(node.getAttribute("interfaceName").getValue());
			ifAllowNullMap.put(interfaceName, node.getAttribute("ifAllowNull").getValue());
			interfaceLengthMap.put(interfaceName, node.getAttribute("interfaceLength").getValue());
			operateTypeMap.put(interfaceName, node.getAttribute("operateType").getValue());
		}
		if(interfaceLengthMap.size()>0){
		  for(int i=0;interfaceInfoList.size()>0&&i<interfaceInfoList.size();i++){
		     Map interfaceInfoMap=(Map)interfaceInfoList.get(i);
		     Iterator iterator=interfaceInfoMap.keySet().iterator();
		     while(iterator.hasNext()){
		    	String interfaceNameTemp=StaticMethod.nullObject2String(iterator.next());
		    	String ifAllowNull=StaticMethod.nullObject2String(ifAllowNullMap.get(interfaceNameTemp));
		    	int interfinterfaceLength=StaticMethod.nullObject2int(interfaceLengthMap.get(interfaceNameTemp));
		    	String operateType=StaticMethod.nullObject2String(operateTypeMap.get(interfaceNameTemp));
		    	String interfaceContent=StaticMethod.nullObject2String(interfaceInfoMap.get(interfaceNameTemp));
                if("false".equals(ifAllowNull)&&"".equals(interfaceContent)){//不允许为空
                   checkResult=checkResult+"第"+i+"条接口信息的"+interfaceNameTemp+"字段值不能为空;";                                    
    	        }else if(interfinterfaceLength>0){//若是String类型，判断字段长度是否在规定字数内
                   String content=StaticMethod.nullObject2String(interfaceContent);
                   int contentLength=content.length();
                   if(contentLength>interfinterfaceLength&&"".equals(operateType)){
                      checkResult=checkResult+"第"+i+"条接口信息的"+interfaceNameTemp+"字段值超过了规定长度;";
                    }else if(contentLength>interfinterfaceLength&&"".equals(operateType)){
                      interfaceInfoMap.put(interfaceNameTemp,content.substring(0,interfinterfaceLength));
                    }
                  }
		    	}
		      }  
		  }
    	}catch(Exception e){
    		e.printStackTrace();
    	}    
    	return checkResult;
    }
    /**
     * 检查建单用户的合法性
     * @param groupUserId 集团EOMS用户ID
     * @return
     */
    public static String checkUserInfo(String groupUserId){
        String checkResult="";
        //获取配置的集团用户和省用户之间的映射关系
        try{
     	 String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/interfaces/province/config/JISheetService-util.xml");
     	 SAXBuilder dc = new SAXBuilder();
 		 Document doc = dc.build(new File(filePath));
 		 Element element = doc.getRootElement();
 		 Element interfaceInfoCheckElement=element.getChild("userInfo");
 		 List list = interfaceInfoCheckElement.getChildren();
 		 for (int i = 0; list!=null&&!list.isEmpty()&&i < list.size(); i++){
 			Element node = (Element)list.get(i);
			String groupUser=StaticMethod.nullObject2String(node.getAttribute("groupUser").getValue());
			if(groupUserId.equals(groupUser)){
				checkResult=StaticMethod.nullObject2String(node.getAttribute("provinceUser").getValue());
				break;
			}
 		 }
        }catch(Exception e){
    		e.printStackTrace();
    	} 
        return checkResult;
    }
    
    /**
     * 网络分类转换
     * @param interfaceInfoList
     * @return
     */
    public static void netTypeConvert(Map map){
      //先将集团传递过来的网络分类映射为EOMS系统中对应的第三级分类的dictCode
      String netType = StaticMethod.nullObject2String(map.get("netType"));
      String provinceDictCode="";
      try{
    	 String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/interfaces/province/config/JISheetService-util.xml");
         SAXBuilder dc = new SAXBuilder();
	     Document doc = dc.build(new File(filePath));
	     Element element = doc.getRootElement();
	     Element interfaceInfoCheckElement=element.getChild("netTypeInfo");
	     List list = interfaceInfoCheckElement.getChildren();
	     for (int i = 0; list!=null&&!list.isEmpty()&&i < list.size(); i++){
		  Element node = (Element)list.get(i);
		  String groupNetType=StaticMethod.nullObject2String(node.getAttribute("groupDictName").getValue());
			if(netType.equals(groupNetType)){
				provinceDictCode=StaticMethod.nullObject2String(node.getAttribute("provinceDictCode").getValue());
				break;
			}
		 }
       }catch(Exception e){
   		e.printStackTrace();
   	   } 
       if(provinceDictCode!=null&&!"".equals(provinceDictCode)){
    	   //把所需的字典信息全部查询出来
    	   Map dictCodeMap=new HashMap();//存放字典ID与字典code映射关系
    	   Map dictParentIdMap=new HashMap();//存放字典ID与父字典ID映射关系
    	   try{
    		 IDownLoadSheetAccessoriesService sqlMgr=
    			(IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
    		 String netTypeDictSql=StaticMethod.nullObject2String(
    	    	XmlManage.getFile("/com/boco/eoms/interfaces/province/config/JISheetService-util.xml").getProperty("netTypeDictSql"));
    		 List netTypeDictList=sqlMgr.getSheetAccessoriesList(netTypeDictSql);
    		 for(int i=0;netTypeDictList!=null&&netTypeDictList.size()>0&&i<netTypeDictList.size();i++){
    			Map dictMap=(Map)netTypeDictList.get(i);
    			String dictId=StaticMethod.nullObject2String(dictMap.get("dictId"));
    			String dictCode=StaticMethod.nullObject2String(dictMap.get("dictCode"));
    			String parentDictId=StaticMethod.nullObject2String(dictMap.get("parentDictId"));
    			dictCodeMap.put(dictCode, dictId);
    			dictParentIdMap.put(dictId, parentDictId);
    		  }
    		}catch(Exception e){
    			e.printStackTrace();
    		} 
    	   //根据dictCode找到对应的网络三级分类
    	   String mainNetSortThree=StaticMethod.nullObject2String(dictCodeMap.get(provinceDictCode));
    	   if(!"".equals(mainNetSortThree)){
   			map.put("mainNetSort3", mainNetSortThree);
   			String mainNetSortTwo =StaticMethod.nullObject2String(dictParentIdMap.get(mainNetSortThree));
   			map.put("mainNetSort2", mainNetSortTwo);
   			String mainNetSortOne =StaticMethod.nullObject2String(dictParentIdMap.get(mainNetSortTwo));
   			map.put("mainNetSort1", mainNetSortOne);
   		   }
        }  
    }
    
    public static List getAttachRefFromXml(String xmlDoc)
	{
		System.out.println("xmlDoc=="+xmlDoc);
		if (xmlDoc == null || xmlDoc.length() == 0)
			return null;
		List resultlist = new ArrayList();
		NodeList UDSObjectList = null;
		Node UDSObject = null;
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			if (xmlDoc.indexOf("?xml") <= 0) 
				xmlDoc = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" + xmlDoc;
			DocumentBuilder dc = dbf.newDocumentBuilder();
			org.w3c.dom.Document doc = dc.parse(new InputSource(new StringReader(xmlDoc)));
			String xpath = "//attachRef/attachInfo";
			UDSObjectList = XPathAPI.selectNodeList(doc, xpath);
			if (UDSObjectList.getLength() > 0)
			{
				for (int i = 0; i < UDSObjectList.getLength(); i++)
				{
					UDSObject = UDSObjectList.item(i);
					AttachRef attach = new AttachRef();
					xpath = "attachName";
					attach.setAttachName(XPathAPI.selectSingleNode(UDSObject, xpath).getFirstChild().getNodeValue());
					xpath = "attachURL";
					attach.setAttachURL(XPathAPI.selectSingleNode(UDSObject, xpath).getFirstChild().getNodeValue());
					xpath = "attachLength";
					Node node=XPathAPI.selectSingleNode(UDSObject, xpath).getFirstChild();
					if(node!=null){
						attach.setAttachLength(XPathAPI.selectSingleNode(UDSObject, xpath).getFirstChild().getNodeValue());
					}				
					resultlist.add(attach);
				}

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return resultlist;
	}
}
