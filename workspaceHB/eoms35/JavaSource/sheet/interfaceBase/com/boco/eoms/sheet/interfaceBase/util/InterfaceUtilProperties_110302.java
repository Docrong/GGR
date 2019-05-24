package com.boco.eoms.sheet.interfaceBase.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.bo.ITawSystemUserBo;
import com.boco.eoms.crm.model.RecordInfo;
import com.boco.eoms.crm.util.RecordUtil;
import com.boco.eoms.sheet.base.util.SheetStaticMethod;

public class InterfaceUtilProperties_110302 {
	
	private static InterfaceUtilProperties_110302 _crmUtilProperties;
	
	public static InterfaceUtilProperties_110302 getInstance(){
		_crmUtilProperties = new InterfaceUtilProperties_110302();
		return _crmUtilProperties;
	}
	/**
	 * 将接口传来的数据与main表字段对应，并存放到map中
	 * @param interfaceMap
	 * @param filePath
	 * @param nodePath
	 * @return map 包含interfaceMap
	 * @throws Exception
	 */
	public Map getMapFromXml(Map interfaceMap,String filePath,String nodePath) throws Exception{	
		return this.getMapFromXml(interfaceMap, filePath, nodePath, true);
	}
	/**
	 * 将接口传来的数据与main表字段对应，并存放到map中
	 * @param interfaceMap
	 * @param filePath
	 * @param nodePath
	 * @param isAll	true返回的map包含interfaceMap,false返回的map中不包含interfaceMap
	 * @return
	 * @throws Exception
	 */
	public Map getMapFromXml(Map interfaceMap,String filePath,String nodePath,boolean isAll) throws Exception{	
		Map map = null;
		if(isAll)
			map = interfaceMap;
		else
			map = new HashMap();
		try{
			ITawSystemDictTypeManager dictMgr=
				 (ITawSystemDictTypeManager)ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
			ITawSystemAreaManager areaMgr = 
				(ITawSystemAreaManager)ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");

			SAXBuilder dc=new SAXBuilder();
			Document doc = dc.build(new File(filePath));
			
			Element element = doc.getRootElement();
			
			String[] paths = nodePath.split("\\.");
			for(int i=0;i<paths.length;i++){
				String nodeName = paths[i];
				element = element.getChild(nodeName);
				if(element==null){
					System.out.println(nodePath+" not find");
					throw new Exception("not found node:" + nodePath);
				}
			}
//			element = element.getChild(nodePath);
			
			List list = element.getChildren();
			for(int i=0;i<list.size();i++){
				Element node = (Element)list.get(i);
				String interfaceName = node.getAttribute("interfaceName").getValue();
				String columnName = node.getAttribute("columnName").getValue();
				System.out.println("interfaceName="+interfaceName);
				System.out.println("columnName="+columnName);
				
				String value = "";
				if(interfaceName.length()>0){
					value = StaticMethod.nullObject2String(interfaceMap.get(interfaceName)); 	
				}
				if(value.length()<=0)
					value = node.getAttribute("defauleValue").getValue();
	
				if(value.length()>0){	
					if(node.getAttribute("dictNodePath")!=null){//需要从xml文件转换数据字典
						String dictNodePath = node.getAttribute("dictNodePath").getValue();
						System.out.println("dictNodePath="+dictNodePath);
						value = this.getDictIdByInterfaceCode(dictNodePath, value);
					}else if(node.getAttribute("rootDictId")!=null){//需要从数据库转换数据字典
						System.out.println("rootDictId="+node.getAttribute("rootDictId").getValue());
						value = dictMgr.getDictIdByDictCode(node.getAttribute("rootDictId").getValue(), value);
					}else if(node.getAttribute("cityCode")!=null){
						String code = node.getAttribute("cityCode").getValue();
						System.out.println("cityCode="+code);
						System.out.println("value="+value);
						TawSystemArea area = areaMgr.getAreaByCode(value);
						if(area!=null)
							value = area.getAreaid();
						else
							System.out.println("没有找到'"+code+"'映射的地市");
					}
					if(node.getAttribute("maxLength")!=null){//最长字节
						try{
							value = SheetStaticMethod.splitString(value, Integer.valueOf(node.getAttribute("maxLength").getValue()).intValue(), "...");
						}catch(Exception e){
							System.out.println("maxLength的值非法");
						}
					}
					if(node.getAttribute("type")!=null){//转换日期格式
						String typeName = node.getAttribute("type").getValue();
						if(typeName.equalsIgnoreCase("date")){
							value = this.formateDateStr(value);
						}
					}
				}
				
				System.out.println("value="+value);
				if(value!=null && value.length()>0)
					map.put(columnName, value);
				
			}
			
			return map;
		}catch(Exception err){
			err.printStackTrace();
			throw new Exception("生成map出错："+err.getMessage());
		}
	}
	/**
	 * 生成接口的xml字符
	 * @param objectMap
	 * @param filePath
	 * @param nodePath
	 * @return
	 * @throws Exception
	 */
	public String getXmlFromMap(Map objectMap,String filePath,String nodePath) throws Exception{	
		List chNameList = new ArrayList();
		List enNameList = new ArrayList();
		List contentList = new ArrayList();
		
		return this.getXmlFromMap(objectMap, filePath, nodePath,chNameList, enNameList, contentList);
		
	}
	public String getXmlFromMap(Map objectMap,String filePath,String nodePath,List chNameList,List enNameList,List contentList) throws Exception{	
		try{
			ITawSystemDictTypeManager dictMgr=
				 (ITawSystemDictTypeManager)ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
			ITawSystemUserBo userBO = (ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
			ITawSystemAreaManager areaMgr = (ITawSystemAreaManager) ApplicationContextHolder
			.getInstance().getBean("ItawSystemAreaManager");			
			
			SAXBuilder dc=new SAXBuilder();
			Document doc = dc.build(new File(filePath));
			
			Element element = doc.getRootElement();
			element = element.getChild(nodePath);
			
			List list = element.getChildren();
			for(int i=0;i<list.size();i++){
				Element node = (Element)list.get(i);
				String interfaceCnName = node.getAttribute("interfaceCnName").getValue();
				String interfaceEnName = node.getAttribute("interfaceEnName").getValue();
				String columnName = node.getAttribute("columnName").getValue();
				
				System.out.println("interfaceCnName="+interfaceCnName);
				System.out.println("interfaceEnName="+interfaceEnName);
				System.out.println("columnName="+columnName);
				
				String value = "";
				if(columnName.length()>0){
					Object obj = objectMap.get(columnName);
					if (obj != null){
						if(obj.getClass().isArray())
							obj = ((Object[]) obj)[0];
						else if (obj instanceof Date) {
							String type = "yyyy-MM-dd HH:mm:ss";
							if(node.getAttribute("type")!=null)//转换日期格式
								type = node.getAttribute("type").getValue();
							SimpleDateFormat dateformat = new SimpleDateFormat(type);
							value = dateformat.format(obj);							
						}
					}
					if("".equals(value))
						value = StaticMethod.nullObject2String(obj); 	
				}
				if(value.length()<=0)
					value = node.getAttribute("defauleValue").getValue();
	
				if(value.length()>0){	
					if(node.getAttribute("dictNodePath")!=null){//需要从xml文件转换数据字典
						String dictNodePath = node.getAttribute("dictNodePath").getValue();
						System.out.println("dictNodePath="+dictNodePath);
						value = this.getDictIdByInterfaceCode(dictNodePath, value);
					}else if(node.getAttribute("type")!=null){
						String type = node.getAttribute("type").getValue();
						System.out.println("type="+type);
						if("dept".equalsIgnoreCase(type)){	//获取部门名称
							value = TawSystemDeptBo.getInstance().getDeptnameBydeptid(value);
						}else if("user".equalsIgnoreCase(type)){	//获取人员
							TawSystemUser user = userBO.getUserByuserid(value);
							value = user.getUsername();
						}else if("dict".equalsIgnoreCase(type)){	//获取字典
							TawSystemDictType dict = dictMgr.getDictByDictId(value);
							if(dict!=null)
								value = dict.getDictName();
						}else if("dictcode".equalsIgnoreCase(type)){	//获取字典code
							TawSystemDictType dict = dictMgr.getDictByDictId(value);
							if(dict != null)
								value = dict.getDictCode();
						}else if("areacode".equalsIgnoreCase(type)){	//获取地市code
							TawSystemArea area = areaMgr.getAreaByAreaId(value);
							if(area != null)
								value = area.getAreacode();
						}
					}
				}
				chNameList.add(interfaceCnName);
				enNameList.add(interfaceEnName);
				contentList.add(value);
			}
			
			String opDetail = RecordUtil.createOpDetailXml(chNameList, enNameList,
					contentList);
			BocoLog.info(this, nodePath+" opDetail="+opDetail);
			return opDetail;
		}catch(Exception err){
			err.printStackTrace();
			throw new Exception("生成xml出错："+err.getMessage());
		}
	}

	/**
	 * 生成接口的xml字符串
	 * @param objectMap
	 * @param childList	子表信息 <map>
	 * @param filePath
	 * @param nodePath
	 * @return
	 * @throws Exception
	 */
	public String getXmlFromMap(Map objectMap, List childList, String filePath,String nodePath) throws Exception{	
		List chNameList = new ArrayList();
		List enNameList = new ArrayList();
		List contentList = new ArrayList();
		
		return this.getXmlFromMap(objectMap, childList, filePath, nodePath,chNameList, enNameList, contentList);
		
	}
	/**
	 * 
	 * @param objectMap
	 * @param childList	子表信息 <map>
	 * @param filePath
	 * @param nodePath
	 * @param chNameList
	 * @param enNameList
	 * @param contentList
	 * @return
	 * @throws Exception
	 */
	public String getXmlFromMap(Map objectMap,List childList, String filePath,String nodePath,List chNameList,List enNameList,List contentList) throws Exception{	
		List extandList = new ArrayList();
		for(int i=0;i<chNameList.size();i++){
			RecordInfo info = new RecordInfo();
			info.setFieldChName(StaticMethod.nullObject2String(chNameList.get(i)));
			info.setFieldEnName(StaticMethod.nullObject2String(enNameList.get(i)));
			info.setFieldContent(StaticMethod.nullObject2String(contentList.get(i)));
			extandList.add(info);
		}
		return this.getXmlFromMap(objectMap, childList,filePath, nodePath, extandList);
	}
	/**
	 * 
	 * @param objectMap 主表map
	 * @param childList	子表信息 <map>
	 * @param filePath	配置文件路径
	 * @param nodePath	配置文件节点名
	 * @param extandList 扩展数据 <RecordInfo>
	 * @return
	 * @throws Exception
	 */
	public String getXmlFromMap(Map objectMap,List childList,String filePath,String nodePath,List extandList) throws Exception{		
		try{
			ITawSystemDictTypeManager dictMgr=
				 (ITawSystemDictTypeManager)ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
			ITawSystemUserBo userBO = (ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
			ITawSystemAreaManager areaMgr = (ITawSystemAreaManager) ApplicationContextHolder
			.getInstance().getBean("ItawSystemAreaManager");
			
			SAXBuilder dc=new SAXBuilder();
			Document doc = dc.build(new File(filePath));
			
			Element element = doc.getRootElement();
			element = element.getChild(nodePath);
			
			List recordInfoList = new ArrayList();
			
			if(objectMap==null)
				objectMap = new HashMap();
			if(childList==null || childList.size()==0){
				childList = new ArrayList();
				childList.add(objectMap);
			}
			List list = element.getChildren();
			for(int k=0;k<childList.size();k++){
				List fieldList = new ArrayList();
				Map childMap = (Map)childList.get(k);
				
				Map map = new HashMap();
				map.putAll(objectMap);
				map.putAll(childMap);
				
				for(int i=0;i<list.size();i++){
					Element node = (Element)list.get(i);
					String interfaceCnName = node.getAttribute("interfaceCnName").getValue();
					String interfaceEnName = node.getAttribute("interfaceEnName").getValue();
					String columnName = node.getAttribute("columnName").getValue();
					
					System.out.println("interfaceCnName="+interfaceCnName);
					System.out.println("interfaceEnName="+interfaceEnName);
					System.out.println("columnName="+columnName);
					
					String value = "";
					if(columnName.length()>0){
						Object obj = map.get(columnName);
						if (obj != null && obj.getClass().isArray()) {
							obj = ((Object[]) obj)[0];
						}
						if(obj!=null&&obj.getClass().equals("java.util.Date")){
							SimpleDateFormat dateformat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
							obj = dateformat.format(obj);
						}
						value = StaticMethod.nullObject2String(obj); 	
					}
					if(value.length()<=0)
						value = node.getAttribute("defauleValue").getValue();
		
					if(value.length()>0){	
						if(node.getAttribute("dictNodePath")!=null){//需要从xml文件转换数据字典
							String dictNodePath = node.getAttribute("dictNodePath").getValue();
							System.out.println("dictNodePath="+dictNodePath);
							value = this.getDictIdByInterfaceCode(dictNodePath, value);
						}else if(node.getAttribute("type")!=null){
							String type = node.getAttribute("type").getValue();
							System.out.println("type="+type);
							if("dept".equalsIgnoreCase(type)){	//获取部门名称
								value = TawSystemDeptBo.getInstance().getDeptnameBydeptid(value);
							}else if("user".equalsIgnoreCase(type)){	//获取人员
								TawSystemUser user = userBO.getUserByuserid(value);
								value = user.getUsername();
							}else if("dict".equalsIgnoreCase(type)){	//获取字典名称
								TawSystemDictType dict = dictMgr.getDictByDictId(value);
								if(dict != null)
									value = dict.getDictName();
							}else if("dictcode".equalsIgnoreCase(type)){	//获取字典code
								TawSystemDictType dict = dictMgr.getDictByDictId(value);
								if(dict != null)
									value = dict.getDictCode();
							}else if("areacode".equalsIgnoreCase(type)){	//获取地市code
								TawSystemArea area = areaMgr.getAreaByAreaId(value);
								if(area != null)
									value = area.getAreacode();
							}
						}
					}
					if(value.length()>0){
						RecordInfo info = new RecordInfo();
						info.setFieldChName(interfaceCnName);
						info.setFieldEnName(interfaceEnName);
						info.setFieldContent(value);
		
						fieldList.add(info);
					}
				}
				if(extandList!=null && extandList.size()>0)
					fieldList.addAll(extandList);
				recordInfoList.add(fieldList);
			}
			
			String opDetail = RecordUtil.createOpDetailXml(recordInfoList);
			BocoLog.info(this, nodePath+" opDetail="+opDetail);
			return opDetail;
		}catch(Exception err){
			err.printStackTrace();
			throw new Exception("生成xml出错："+err.getMessage());
		}
	}
	
	/**
	 * 通过字典查找对应的业务类型
	 * @param filePath
	 * @param nodePath
	 * @param dictId
	 * @return
	 * @throws Exception
	 */	 
	public String getInterfaceCodeByDictId(String filePath,String nodePath,String dictId) throws Exception{
		SAXBuilder dc=new SAXBuilder();
		Document doc = dc.build(new File(filePath));
		
		Element element = doc.getRootElement();
		element = element.getChild("dict");
		element = element.getChild(nodePath);
		
		List list = element.getChildren();
		for(int i=0;i<list.size();i++){
			Element node = (Element)list.get(i);
			String dict = node.getAttribute("dictid").getValue();
			String interfacecode = node.getAttribute("interfacecode").getValue();
			if(dict.equals(dictId))
				return interfacecode;
		}
		return "";
	}
	/**
	 * 通过字典查找对应的业务类型
	 * @param dictId
	 * @return
	 * @throws Exception
	 */
	public String getInterfaceCodeByDictId(String nodePath,String dictId) throws Exception{
		String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/crm/config/crm-config.xml");
		return this.getInterfaceCodeByDictId(filePath, nodePath, dictId);
	}
	/**
	 * 通过业务类型查找对应的字典
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String getDictIdByInterfaceCode(String filePath,String nodePath,String code) throws Exception{
		SAXBuilder dc=new SAXBuilder();
		Document doc = dc.build(new File(filePath));
		
		Element element = doc.getRootElement();
		element = element.getChild("dict");
		element = element.getChild(nodePath);
		if(element==null){
			System.out.println("dict."+nodePath+" not find");
			return code;
		}
		
		List list = element.getChildren();
		for(int i=0;i<list.size();i++){
			Element node = (Element)list.get(i);
			String dict = node.getAttribute("dictid").getValue();
			String interfacecode = node.getAttribute("interfacecode").getValue();
			if(interfacecode.equals(code))
				return dict;
		}
		return code;
	}
	/**
	 * 通过字典配置查找方法
	 * @param filePath 文件相对路径
	 * @param nodePath	节点路径,以"."分隔
	 * @param fromAttributeName 要查找的Attribute的Name
	 * @param toAttributeName 要返回的Attribute的Name
	 * @param value fromAttributeName的值
	 * @return
	 */
	public String getXmlValue(String filePath,String nodePath,String fromAttributeName,String toAttributeName,String value) throws Exception{
		System.out.println("filePath=" + filePath);
		SAXBuilder dc=new SAXBuilder();
		Document doc = dc.build(new File(filePath));
		
		Element element = doc.getRootElement();
		String[] paths = nodePath.split("\\.");
		for(int i=0;i<paths.length;i++){
			String nodeName = paths[i];
			element = element.getChild(nodeName);
			if(element==null){
				System.out.println(nodePath+" not find");
				return "";
			}
		}
		
		String result = "";
		List list = element.getChildren();
		for(int i=0;i<list.size();i++){
			Element node = (Element)list.get(i);
			String dict = node.getAttribute(toAttributeName).getValue();
			String interfacecode = node.getAttribute(fromAttributeName).getValue();
			if(interfacecode.equals(value))
				result = dict;
		}
		System.out.println("result=" + result);
		return result;
	}
	public String getDictIdByInterfaceCode(String nodePath,String code) throws Exception{
		String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/crm/config/crm-config.xml");
		return this.getDictIdByInterfaceCode(filePath, nodePath, code);
	}
	/**
	 * 获取sheetType对应工单的接口地址
	 * @param sheetType
	 * @return
	 * @throws Exception
	 */
	public String getServiceUrlBySheetType(String sheetType) throws Exception{
		String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/crm/config/crm-config.xml");
		SAXBuilder dc=new SAXBuilder();
		Document doc = dc.build(new File(filePath));
		
		Element element = doc.getRootElement();
		element = element.getChild("sheet");
		if(element==null)
			return "";
		
		List list = element.getChildren();
		String url = "";
		for(int i=0;i<list.size();i++){
			Element node = (Element)list.get(i);
			String type = node.getAttribute("sheettype").getValue();
			if(type.equals(sheetType))
				url = node.getAttribute("url").getValue();
		}
		System.out.println("serviceUrl="+url);
		return url;
	}
	/**
	 * 获取sheetType对应工单的接口客户端beanId
	 * @param sheetType
	 * @return
	 * @throws Exception
	 */
	public String getClientBeanIdBySheetType(String sheetType) throws Exception{
		String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/crm/config/crm-config.xml");
		SAXBuilder dc=new SAXBuilder();
		Document doc = dc.build(new File(filePath));
		
		Element element = doc.getRootElement();
		element = element.getChild("sheet");
		if(element==null)
			return "";
		
		List list = element.getChildren();
		String clientBeanId = "";
		for(int i=0;i<list.size();i++){
			Element node = (Element)list.get(i);
			String type = node.getAttribute("sheettype").getValue();
			if(type.equals(sheetType) && node.getAttribute("clientBeanId")!=null)
				clientBeanId = node.getAttribute("clientBeanId").getValue();
		}
		System.out.println("clientBeanId="+clientBeanId);
		return clientBeanId;
	}
	/**
	 * 接口日期格式转换成标准日期格式
	 * @param dateStr
	 * @return
	 */
	public String formateDateStr(String dateStr){
		String result = dateStr;
		int index = dateStr.indexOf("-");
		if(index<=0){
			result = dateStr.substring(0, 4) + "-" + dateStr.substring(4,6) + "-" + dateStr.substring(6);
		}
		return result;
	}
	
	public static void main(String[] args) {
		try{
			String filePath = "E:/businessDredge-crm111.xml";

			Map interfaceMap = new HashMap();
			interfaceMap.put("aa", "1");
			interfaceMap.put("bb", "2");
			
			Map map = new HashMap();
			map.put("aa", "111111");
			
			Map newmap = new HashMap();
			newmap.putAll(interfaceMap);
			newmap.putAll(map);
			System.out.println(newmap.get("aa"));
			
			SAXBuilder dc=new SAXBuilder();
			Document doc = dc.build(new File(filePath));
			
			Element element = doc.getRootElement();
			element = element.getChild("notifyWorkSheet");
			
			List list = element.getChildren();
			for(int i=0;i<list.size();i++){
				Element node = (Element)list.get(i);
				String interfaceCnName = node.getAttribute("interfaceCnName").getValue();
				String interfaceEnName = node.getAttribute("interfaceEnName").getValue();
				String columnName = node.getAttribute("columnName").getValue();
				
				System.out.println("interfaceCnName="+interfaceCnName);
				System.out.println("interfaceEnName="+interfaceEnName);
				System.out.println("columnName="+columnName);
			}
//			
//			Properties   initProp   =   new   Properties(System.getProperties());   
//            System.out.println("file.encoding:"   +   initProp.getProperty("file.encoding"));   
//            System.out.println("file.encoding:"   +   initProp.getProperty("user.language"));  
			
		}catch(Exception err){
			err.printStackTrace();
		}
	}
}
