package com.boco.eoms.businessupport.interfaces.client;

import java.net.URL;
import java.util.List;
import java.util.Map;

import com.boco.eoms.businessupport.interfaces.util.DataSourceUtil;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.restar.fulfillReady.manager.ResManagerImplServiceLocator;
import com.boco.restar.fulfillReady.manager.ResManagerImplServiceSoapBindingStub;

public class ResManagerLoader {
	/**
	 * 连接资源服务
	 * @return
	 * @throws Exception
	 */
	public static ResManagerImplServiceSoapBindingStub loadService() throws Exception{
		String urlStr = XmlManage.getFile("/com/boco/eoms/businessupport/config/resourceInterface_util.xml").getProperty("base.ServiceUrl");
		BocoLog.info(ResManagerLoader.class, "resService:" + urlStr);
		
        URL url = new URL(urlStr);
		return (ResManagerImplServiceSoapBindingStub)(new ResManagerImplServiceLocator()).getResManagerImplPort(url);
	}
	/**
	 * 将接口的返回值转换成map：rtCode是否成功，1成功，0失败；rtMessage：失败信息
	 * @param xml
	 * @return
	 */
	public static Map transactionXml(String xml){
		return DataSourceUtil.buildMultiRecordsByXml(xml);
	}
	public static String getIrmsResult(Map resultMap) throws Exception{
		List list = (List)resultMap.get("commInfo");
		if(list!=null && list.size()>0){
			Map map = (Map)list.get(0);
			if(map.get("rtCode").toString().equals("1"))
				return "1";
			throw new Exception(map.get("rtMessage").toString());
		}else
		    throw new Exception("资源返回结果错误");
	}
	/**
	 * 资源数据初始化接口
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static String createProService(String xml) throws Exception{
		String result = ResManagerLoader.loadService().createProService(xml);
		BocoLog.info(ResManagerLoader.class, "createProService return:"+result);
		
		Map resultMap = ResManagerLoader.transactionXml(result);
		
		return ResManagerLoader.getIrmsResult(resultMap);

	}
	/**
	 * 资源实占接口
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static String occupyServiceRes(String xml) throws Exception{
		String result = ResManagerLoader.loadService().occupyServiceRes(xml);
		BocoLog.info(ResManagerLoader.class, "occupyServiceRes return:"+result);
		
		Map resultMap = ResManagerLoader.transactionXml(result);
		
		return ResManagerLoader.getIrmsResult(resultMap);
	}
	/**
	 * 资源取消接口
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static String undoServiceRes(String xml) throws Exception{
		String result = ResManagerLoader.loadService().undoServiceRes(xml);
		BocoLog.info(ResManagerLoader.class, "undoServiceRes return:"+result);
		
		Map resultMap = ResManagerLoader.transactionXml(result);
		
		return ResManagerLoader.getIrmsResult(resultMap);
	}
	/**
	 * 资源回滚接口
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static String undoTaskRes(String xml) throws Exception{
		String result = ResManagerLoader.loadService().undoTaskRes(xml);
		BocoLog.info(ResManagerLoader.class, "undoTaskRes return:"+result);
		
		Map resultMap = ResManagerLoader.transactionXml(result);
		
		return ResManagerLoader.getIrmsResult(resultMap);
	}
	/**
	 * 资源校验接口
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static String preOccupyResFinish(String xml) throws Exception{
		String result = ResManagerLoader.loadService().preOccupyResFinish(xml);
		BocoLog.info(ResManagerLoader.class, "preOccupyResFinish return:"+result);
		
		Map resultMap = ResManagerLoader.transactionXml(result);
		
		return ResManagerLoader.getIrmsResult(resultMap);
	}

}
