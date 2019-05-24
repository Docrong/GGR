/*
 * Created on 2007-11-22
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.limit.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;

import com.boco.eoms.commons.fileconfig.exception.ParseXMLException;
import com.boco.eoms.sheet.base.util.flowdefine.xml.StaticMethod;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TimeMapSchema {
	private static TimeMappings nodes = null;
	private static TimeMapSchema timeMapSchema= null;
	
	public static TimeMapSchema getInstance() throws Exception{
		
		if(timeMapSchema==null||nodes==null){
			timeMapSchema = new TimeMapSchema();
			
		}
		loadXml();
		return timeMapSchema;
	}

	private static void loadXml() throws Exception{
		try {
			//用于读取xml文件
			FileReader in = null;
			// 返回obj,调用者需强制转型
			Object obj = null;
			try {
				// 缓存
				// ClassDescriptorResolver cdr = new ClassDescriptorResovlerImpl();

				Unmarshaller un = new Unmarshaller(TimeMappings.class);
				Mapping map = new Mapping();
				map.loadMapping(StaticMethod.getFileUrl("classpath:com/boco/eoms/sheet/limit/util/TimeMapping.xml"));
				un.setMapping(map);
				// 设置不验证xml以加快速度
				un.setValidation(true);
				// Unmarshaller un = new Unmarshaller(cls);
				// un.setResolver(cdr);

				// 读取xml文件
				// in = new FileReader(finalPath);

				InputStreamReader isr = new InputStreamReader(StaticMethod.getFileInputStream("classpath:config/sheet-sheetLimit-mapping.xml"),"utf-8");
				System.out.println("encoding======"+isr.getEncoding());
				System.out.println("isr.toString()="+isr.toString());
				
				obj = un.unmarshal(isr);

			} catch (Exception e) {
				e.printStackTrace();
				throw new ParseXMLException(e);
			} finally {
				if (in != null) {
					try {
						// 关闭
						in.close();
					} catch (IOException e) {
						throw new ParseXMLException(e);
					}
				}
			}
			nodes = (TimeMappings)obj;
			//nodes = (TimeMappings) ParseXmlService
			//		.create()
			//		.xml2object(TimeMappings.class,"classpath:com/boco/eoms/sheet/overtimetip/util/TimeMapping.xml", "classpath:config/sheet-overtime-mapping.xml",false);
			
		} catch (ParseXMLException e) {
			e.printStackTrace();
			throw new Exception("读取配置文件'config/sheet-sheetLimit-mapping.xml'出错");
		}
	}

	/**
	 * 根据模块，获取TimeFilter数组
	 * @param modelName
	 * @return
	 */
	public List getTimeFilterListByFlow(String flowName){
		if(nodes!=null){
			List roleList = nodes.getTimeModel();
			for (int i=0;i<roleList.size();i++) {
				TimeModel rm = (TimeModel)roleList.get(i);
				if(rm.getFlowName().equals(flowName))
					return rm.getTimeFilter();
			}
		}
		return null;
	}
	/**
	 * 根据模块，获取ExceptTask
	 * @param flowName
	 * @return String
	 */
	public String getExceptTaskByFlow(String flowName){
		if(nodes!=null){
			List roleList = nodes.getTimeModel();
			for (int i=0;i<roleList.size();i++) {
				TimeModel rm = (TimeModel)roleList.get(i);
				if(rm.getFlowName().equals(flowName)){
					ExceptTask exceptTask = null;
					try{
					exceptTask = rm.getExceptTask();
					}catch(Exception e){}
					if(exceptTask!=null){
						return exceptTask.getTaskName();
					}
				}
			}
		}
		return null;
	}
	/**
	 * 根据taskFlowName，获取flowName
	 * @param taskFlowName
	 * @return
	 */
	public String getFlowNameByTaskFlowName(String taskFlowName){
		if(nodes!=null){
			List roleList = nodes.getTimeModel();
			for (int i=0;i<roleList.size();i++) {
				TimeModel rm = (TimeModel)roleList.get(i);
				if(rm.getTaskFlowName().indexOf(taskFlowName)!=-1)
					return rm.getFlowName();
			}
		}
		return null;
	}
	/**
	 * 根据flowName和businessName，获取TimeFilter
	 * @param modelName
	 * @return
	 */
	public TimeFilter getTimeFilterBybusinessName(String flowName,String businessName){
		if(nodes!=null){
			List timeList = nodes.getTimeModel();
			for (int i=0;i<timeList.size();i++) {
				TimeModel rm = (TimeModel)timeList.get(i);
				if(rm.getFlowName().equals(flowName)){
					List filterList = rm.getTimeFilter();
					for(int j=0;j<filterList.size();j++){
						TimeFilter filter = (TimeFilter)filterList.get(j);
						if(filter.getBusinessName().equals(businessName)){
							return filter;
						}
					}
				}
			}
		}
		return null;
	}
	/**
	 * 根据flowName和sub，获取TimeFilter
	 * @param modelName
	 * @return
	 */
	public TimeFilter getTimeFilterBySub(String flowName,String sub){
		if(nodes!=null){
			List timeList = nodes.getTimeModel();
			for (int i=0;i<timeList.size();i++) {
				TimeModel rm = (TimeModel)timeList.get(i);
				if(rm.getFlowName().equals(flowName)){
					List filterList = rm.getTimeFilter();
					for(int j=0;j<filterList.size();j++){
						TimeFilter filter = (TimeFilter)filterList.get(j);
						if(filter.getSub().equals(sub)){
							return filter;
						}
					}
				}
			}
		}
		return null;
	}
	/**
	 * 获取工单各个区分度对应的HTML控件ID
	 * @param flowId 流程名
	 * @param sheetId 工单名
	 * @return <String>
	 */
	public HashMap getCnNamesBySheet(String sheetId){
		 HashMap resultMap = new HashMap();
		
		if(nodes!=null){
			boolean isfind = false;
			List roleList = nodes.getTimeModel();
			for (int i=0;i<roleList.size();i++) {
				TimeModel rm = (TimeModel)roleList.get(i);
				List roleFilters = rm.getTimeFilter();
				for(int j=0;roleFilters!=null && j<roleFilters.size();j++){
					TimeFilter filter = (TimeFilter)roleFilters.get(j);
					List sheetInfos = filter.getSheetInfo();
					if(sheetInfos==null)
						continue;
					for(int k=0;k<sheetInfos.size();k++){
						SheetInfo si = (SheetInfo)sheetInfos.get(k);
						if(si.getName().equals(sheetId)){
							resultMap.put(filter.getBusinessName(),si.getCnName());
							isfind = true;
							break;
						}
						
					}
				}
				if(isfind)
					break;
			}
		}
		return resultMap;
	}
	public List getModelLists(){
		List modelList = null;
		if(nodes!=null){
			modelList = nodes.getTimeModel();
		}
		
		return modelList;
	}
	/**
	 * 根绝模块编号获取DICTID
	 * @param flowName 流程名
	 * @param businessName 业务字段名称
	 * @return
	 */
	public String getDictIdByModelId(String flowName,String businessName){
		if(nodes!=null){
			List roleList = nodes.getTimeModel();
			for (int i=0;i<roleList.size();i++) {
				TimeModel rm = (TimeModel)roleList.get(i);
				if(rm.getFlowName().equals(flowName)){
					List rfList = rm.getTimeFilter();
					for(int j=0;j<rfList.size();j++){
						TimeFilter rf = (TimeFilter)rfList.get(j);
						if(rf.getBusinessName().equals(businessName))
							return rf.getDictId();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取工单对应的流程ID
	 * @param sheetId 工单名
	 * @return <String>
	 */
	public String getModelIdBySheet(String sheetId){
		
		if(nodes!=null){
			boolean isfind = false;
			List roleList = nodes.getTimeModel();
			for (int i=0;i<roleList.size();i++) {
				TimeModel rm = (TimeModel)roleList.get(i);
				List roleFilters = rm.getTimeFilter();
				for(int j=0;j<roleFilters.size();j++){
					TimeFilter filter = (TimeFilter)roleFilters.get(j);
					List sheetInfos = filter.getSheetInfo();
					if(sheetInfos==null)
						continue;
					for(int k=0;k<sheetInfos.size();k++){
						SheetInfo si = (SheetInfo)sheetInfos.get(k);
						if(si.getName().equals(sheetId)){
							return rm.getFlowName();
						}
						
					}
				}
			}
		}
		return null;
	}
	/**
	 * 根据flowName，获取TimeWorkTime
	 * @param modelName
	 * @return
	 */
	public TimeWorktime getTimeWorkTime(String flowName){
		if(nodes!=null){
			List timeList = nodes.getTimeModel();
			for (int i=0;i<timeList.size();i++) {
				TimeModel rm = (TimeModel)timeList.get(i);
				if(rm.getFlowName().equals(flowName)||rm.getTaskFlowName().indexOf(flowName)!=-1){
					TimeWorktime timeWorktime = rm.getTimeWorktime();
					return timeWorktime;
				}
			}
		}
		return null;
	}
}
