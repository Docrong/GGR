/*
 * Created on 2008-1-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.kpi.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ConvertUtil;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.fileconfig.service.impl.ParseXmlService;
import com.boco.eoms.sheet.base.kpi.dao.IBaseKPIDAO;
import com.boco.eoms.sheet.base.kpi.service.IBaseKPIManager;
import com.boco.eoms.sheet.base.kpi.util.CollectColumn;
import com.boco.eoms.sheet.base.kpi.util.CollectColumnQuery;
import com.boco.eoms.sheet.base.kpi.util.CollectInfo;
import com.boco.eoms.sheet.base.model.BaseCollect;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseKPIManagerImpl extends BaseManager implements IBaseKPIManager{
	private IBaseKPIDAO dao;
	protected BaseLink link;
	protected BaseMain main;
	protected BaseCollect collectPojo;
	protected String xmlKey;
	
	/**
	 * @return Returns the dao.
	 */
	public IBaseKPIDAO getDao() {
		return dao;
	}
	/**
	 * @param dao The dao to set.
	 */
	public void setDao(IBaseKPIDAO dao) {
		this.dao = dao;
	}
	public void setLink(BaseLink link){
		dao.setLink(link);
	}
	public BaseLink getLink(){
		return dao.getLink();
	}
	public void setMain(BaseMain main){
		dao.setMain(main);
	}
	public BaseMain getMain(){
		return dao.getMain();
	}
	public void setCollectPojo(BaseCollect collectPojo){
		dao.setCollectPojo(collectPojo);
	}
	public BaseCollect getCollectPojo(){
		return dao.getCollectPojo();
	}
	
	public List getBaseData(String startDate){
		return dao.getBaseData(startDate);
	}
	public List getCollects(){
		return dao.getCollects();
	}
	public void saveOrUpdateAll(List list){
		dao.saveOrUpdateAll(list);
	}
	public void deleteData(String startDate){
		dao.deleteData(startDate);
	}
	public String getMainName(){
		return dao.getMainName();
	}
	public String getLinkName(){
		return dao.getLinkName();
	}
	public String getCollectName(){
		return dao.getCollectName();
	}
	/**
	 * 采集基础数据，更新从startTime开始到目前的所有变动的数据
	 * @param startTime
	 */
	public void run(String startTime) throws Exception{
		startTime = "";
		CollectColumn[] baseColumns = this.getBaseColumnByKey();
		CollectColumn[] columns = this.getColumnByKey();
		
		List dataList = this.getBaseData(startTime);
		List collectList = new ArrayList();
		
		try{
			for(int i=0;i<dataList.size();i++)
			{
				Object[] o = (Object[])dataList.get(i);
				BaseMain main = (BaseMain)(o[2]);
				BaseLink link = (BaseLink)(o[0]);
				BaseLink preLink = (BaseLink)(o[1]);
				
				BaseCollect collect = this.createNewCollect(main,link,preLink);
				
				for(int j=0;j<baseColumns.length;j++){
					CollectColumn column = baseColumns[j];
					String value = CollectColumnQuery.getInstance().getValue(column,this.getMainName(),this.getLinkName(),main.getId());					
					StaticMethod.invokeStringMethod(collect,column.getColumnName(),value);
				}
				
				if(columns!=null){
					for(int j=0;j<columns.length;j++){
						CollectColumn column = columns[j];
						String value = CollectColumnQuery.getInstance().getValue(column,this.getMainName(),this.getLinkName(),main.getId());
						StaticMethod.invokeStringMethod(collect,column.getColumnName(),value);
					}
				}
				collectList.add(collect);
				
			}
			this.deleteData(startTime);
			this.saveOrUpdateAll(collectList);
		}catch(Exception e){
			throw e;
		}
	}
	private CollectInfo loadXML(String key) throws Exception{
		return (CollectInfo)ParseXmlService.create().xml2objectWithKey(CollectInfo.class,key);
	}
	private CollectColumn[] getBaseColumnByKey() throws Exception{
		CollectInfo baseInfo = this.loadXML("BaseKPI");
		return baseInfo.getColumns();
	}
	private CollectColumn[] getColumnByKey() throws Exception{
		if(this.getXmlKey()==null || this.getXmlKey().equals(""))
			return null;
		CollectInfo baseInfo = this.loadXML(this.getXmlKey());
		return baseInfo.getColumns();
	}
	/**
	 * @return Returns the xmlKey.
	 */
	public String getXmlKey() {
		return xmlKey;
	}
	/**
	 * @param xmlKey The xmlKey to set.
	 */
	public void setXmlKey(String xmlKey) {
		this.xmlKey = xmlKey;
	}
	
	/**
	 * 创建一条新的基础数据
	 * @param main
	 * @param link
	 * @param preLink
	 * @return
	 * @throws Exception
	 */
	private BaseCollect createNewCollect(BaseMain main,BaseLink link,BaseLink preLink) throws Exception{
		try{
			BaseCollect collect = (BaseCollect)Class.forName(this.getCollectPojo().getClass().getName()).newInstance();
			collect = (BaseCollect)ConvertUtil.populateObject(collect,main);
			collect = (BaseCollect)ConvertUtil.populateObject(collect,link);
			
			Calendar c = Calendar.getInstance();	
			if(link.getAcceptTime()!=null && preLink.getAcceptTime()!=null){
				c.setTime(link.getAcceptTime());
				long a1 = c.getTimeInMillis();				
				c.setTime(preLink.getAcceptTime());
				long a2 = c.getTimeInMillis();				
				collect.setAcceptSecond(String.valueOf(a1-a2));
			}
			
			if(link.getCompleteTime()!=null && preLink.getCompleteTime()!=null){
				c.setTime(link.getCompleteTime());
				long c1 = c.getTimeInMillis();				
				c.setTime(preLink.getCompleteTime());
				long c2 = c.getTimeInMillis();				
				collect.setCompleteSecond(String.valueOf(c1-c2));
			}
			
			if(link.getOperateTime()!=null && link.getCompleteTime()!=null){
				if(link.getOperateTime().compareTo(link.getCompleteTime())>0)
					collect.setCompleteFlag(new Integer(0));	//不及时
				else
					collect.setCompleteFlag(new Integer(1));	//及时
			}
			
			collect.setId(null);
			
			return putCollectDate(collect);
		}catch(Exception e){
			throw e;
		}
	}
	/**
	 * 如果有特殊算法，在子类中重写此方法
	 * @param collect
	 * @return
	 */
	protected BaseCollect putCollectDate(BaseCollect collect){
		return collect;
	}
}
