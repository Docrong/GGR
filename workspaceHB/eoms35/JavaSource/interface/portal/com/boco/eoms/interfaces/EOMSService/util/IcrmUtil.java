package com.boco.eoms.interfaces.EOMSService.util;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.util.OpDetail;

public class IcrmUtil {
	public HashMap xmlElements(String xmlDoc, String xpath,String keyName,String valueName) throws Exception{

		HashMap map = new HashMap();
		NodeList UDSObjectList = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);		
			if(xmlDoc.indexOf("?xml")<=0)
				xmlDoc = "<?xml version=\"1.0\" encoding=\"gb2312\"?>" + xmlDoc;
			DocumentBuilder dc = dbf.newDocumentBuilder();
			Document doc = dc.parse(new InputSource(new StringReader(xmlDoc)));
			
			UDSObjectList = XPathAPI.selectNodeList(doc, xpath);
			if (UDSObjectList.getLength() > 0) {
				for(int i=0;i<UDSObjectList.getLength();i++){
					HashMap hashMap=this.getMap(xpath, map, doc,keyName,valueName);
					map.putAll(hashMap);
				}
			}
			else{
				throw new Exception("xml is wrong,not found:" + xpath);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return map;
	}

	public HashMap getMap(String xpath, HashMap map, Document doc ,String keyName,String valueName) {
		NodeList UDSObjectList = null;
		Node UDSObject = null;

		try {
			UDSObjectList = XPathAPI.selectNodeList(doc, xpath);
		} catch (TransformerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (UDSObjectList.getLength() > 0) {
			for (int i = 0; i < UDSObjectList.getLength(); i++) {
				UDSObject = UDSObjectList.item(i);
				OpDetail opDetail = new OpDetail();
				xpath = keyName;
				try {
					if (XPathAPI.selectSingleNode(UDSObject, xpath)
							.getFirstChild() != null) {
						System.out.println("cellId:"
								+ XPathAPI.selectSingleNode(UDSObject, xpath)
										.getFirstChild().getNodeValue());

						opDetail.setFieldEnName(XPathAPI.selectSingleNode(
								UDSObject, xpath).getFirstChild()
								.getNodeValue());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				xpath = valueName;
				try {
					if (XPathAPI.selectSingleNode(UDSObject, xpath)
							.getFirstChild() != null) {
						System.out.println("cellName:"
								+ XPathAPI.selectSingleNode(UDSObject, xpath)
										.getFirstChild().getNodeValue());
						opDetail.setFieldContent(XPathAPI.selectSingleNode(
								UDSObject, xpath).getFirstChild()
								.getNodeValue());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				map.put(opDetail.getFieldEnName(), opDetail.getFieldContent());
			}

		}
		return  map;
	}

	public String getDealPerformerByCityId(String cityId,String bigRoleId,Map map) throws Exception{
		if(cityId==null || cityId.equals("")){
			BocoLog.info(this, "cityId为空");
			return null;
		}
		ITawSystemAreaManager areaMgr = 
			(ITawSystemAreaManager)ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");
		
		String dictId = "";
		TawSystemArea area = areaMgr.getAreaByCode(cityId);
		if(area!=null && area.getId()!=null)
			dictId += area.getAreaid() + ",";
		else
			throw new Exception("没有找到'"+cityId+"'映射的地市");
		
		WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
		String dealperformer = "";
		
		String[] cityIds = dictId.split(",");
		for(int i=0;i<cityIds.length;i++){
			TawSystemSubRole subRole = sm.getMatchRoles("CircuitDispatchMainFlowProcess", cityIds[i], bigRoleId, map);
			if(subRole!=null && subRole.getId()!=null)
				dealperformer += subRole.getId() + ",";
			
		}
		if(dealperformer.length()>0)
			dealperformer = dealperformer.substring(0,dealperformer.length()-1);
		else
			throw new Exception("没找到对应的角色");
		BocoLog.info(this, "dealperformer="+dealperformer);
		return dealperformer;
	}
}
