package com.boco.eoms.knowledge.util;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.fileconfig.exception.ParseXMLException;
import com.boco.eoms.commons.fileconfig.service.impl.ParseXmlService;

public class SheetMapingSchema {
	private static SheetMappings nodes = null;
	private static SheetMapingSchema sheetMapingSchema= null;
	
	public static SheetMapingSchema getInstance() throws Exception{
		if(sheetMapingSchema==null){
			sheetMapingSchema = new SheetMapingSchema();
			try{
				loadXml();
			}catch(Exception e){
				throw e;
			}
		}
		return sheetMapingSchema;
	}
	
	private static void loadXml() throws Exception{
		try {
			nodes = (SheetMappings) ParseXmlService
					.create()
					.xml2object(SheetMappings.class, StaticMethod.getFilePathForUrl("classpath:config/knowledge-service.xml"));

		} catch (ParseXMLException e) {
			e.printStackTrace();
			throw new Exception("读取配置文件'knowledge-service.xml'出错");
		}
	}
	/**
	 * 
	 * @param tableid 工单知识库名称
	 * @return
	 */
	public String getBeanId(String tableid){
		if(nodes!=null){
			SheetType[] sheetTypeList = nodes.getSheetType();
			for (int i=0;i<sheetTypeList.length;i++) {
				SheetType st = (SheetType)sheetTypeList[i];
				if(st.getTableid().equals(tableid))
					return st.getBeanid();
			}
		}
		return "";
	}
}
