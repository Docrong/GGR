/*
 * Created on 2008-1-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.statistic.base.mgr.impl;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.boco.eoms.commons.statistic.base.config.IStatisticTool;
import com.boco.eoms.commons.statistic.base.config.excel.Sheet;
import com.boco.eoms.commons.statistic.base.config.model.ConditionParam;
import com.boco.eoms.commons.statistic.base.config.model.DynamicRowParam;
import com.boco.eoms.commons.statistic.base.config.model.FieldDefine;
import com.boco.eoms.commons.statistic.base.config.model.KpiConfig;
import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.config.model.QueryDefine;
import com.boco.eoms.commons.statistic.base.config.model.SummaryDefine;
import com.boco.eoms.commons.statistic.base.dao.ICustomStatHibernateDAO;
import com.boco.eoms.commons.statistic.base.dao.IStatDetailDAO;
import com.boco.eoms.commons.statistic.base.dao.IStatJdbcDAO;
import com.boco.eoms.commons.statistic.base.mgr.IStatConfigManager;
import com.boco.eoms.commons.statistic.base.mgr.IStatManager;
import com.boco.eoms.commons.statistic.base.model.StatisticFileInfo;
import com.boco.eoms.commons.statistic.base.reference.ApplicationContextHolder;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil;
import com.boco.eoms.commons.statistic.base.util.SqlParamCache;
import com.boco.eoms.commons.statistic.base.util.StatUtil;

/**
 * @author liuxy
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class StatManagerImpl implements IStatManager {
	Logger logger = Logger.getLogger(StatManagerImpl.class);

	private IStatJdbcDAO statJdbcDAO;

	private IStatDetailDAO statDetailDAO;
	
	private IStatConfigManager statConfigManager;
	
	private ExcelConverter statExcelConverter;
	
	private ExportExcel statExportExcel;

	private String statDetailVOName;
	
	private ICustomStatHibernateDAO customStatHibernateDAO;
	
	private IStatisticTool statisticTool;

	/*

	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.statistic.base.mgr.IStatManager#getKpiResult(java.lang.String,
	 *      java.util.Map)
	 */
/*	public Map getKpiResult(String kpiName, Map param) throws Exception {
//		String beginTime = StaticMethod.nullObject2String(((Object[]) param
//				.get("beginTime"))[0]);
//		String endTime = StaticMethod.nullObject2String(((Object[]) param
//				.get("endTime"))[0]);
		Map mapResult = null;
		try {
			KpiDefine kpiDefine = statConfigManager.getKpiConfig()
					.getConfigByKpiDefineName(kpiName);
//			mapResult = kpiJdbcDAO.getKpiResult(kpiDefine, beginTime, endTime, param);
			mapResult =statJdbcDAO.getKpiResult(kpiDefine, param);
			
			return mapResult;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new Exception("统计查询出错!"+e);
		}
	}*/



/*	protected String getSqlAddSummaryData(String sql, KpiDefine kpiDefine,
			String summaryData) throws Exception {
		
			if(summaryData != null && !summaryData.equals("")) {
		
			String[] summaryDatas = summaryData.split(",");
			for (int iSummary = 0; iSummary < kpiDefine.getSummaryDefines().length; iSummary++) {
				SummaryDefine summaryDefine = kpiDefine.getSummaryDefines()[iSummary];

				if (sql.indexOf("where") > 0)
					sql += " and ";
				else
					sql += " where ";
				sql += summaryDefine.getMappingProperty() + "="
						+ summaryDatas[iSummary];
			}
			return sql;
		} else
			throw new Exception("获取查询工单列表SQL时,拼接汇总字段条件时错误!");
		
	}*/



	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.statistic.base.mgr.IStatManager#getListCountSqlByName(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
/*	public String getListCountSqlByName(String kpiDefineName,
			String queryDefineName, String fieldAsName, String summaryData)
			throws Exception {
		try {
			KpiConfig kpiconfig = statConfigManager.getKpiConfig();
			KpiDefine kpidefine = kpiconfig.getConfigByKpiDefineName(kpiDefineName);
			QueryDefine queryDefine = kpidefine.getQueryDefineByName(queryDefineName);
			FieldDefine fieldDefine = queryDefine.getFieldDefineByAsName(fieldAsName);
			String sql = fieldDefine.getListCountHsql();

			if (sql == null || sql.equals(""))

				throw new Exception("读取" + kpiDefineName + "-" + queryDefineName + "-"
						+ fieldAsName + "中<list-count-sql>错误!");
			else
				return sql;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new Exception("读取" + kpiDefineName + "-" + queryDefineName + "-"
					+ fieldAsName + "中<list-count-hsql>错误!");
		}

	}
*/

	/**
	 * 根据字典字段生成查询sql配置
	 * 
	 * @param QueryDefines
	 * @param dictionaryByName
	 * @return
	 * @throws Exception 
	 */
	private QueryDefine[] handleDictionary(QueryDefine[] QueryDefines ,Map parmMap) throws Exception
	{
		List reList = new ArrayList();
		
		for(int queryDefine=0;queryDefine<QueryDefines.length;queryDefine++)
		{
			reList.add(QueryDefines[queryDefine]);
			String dictName = QueryDefines[queryDefine].getStatSqlDicts();
			if(dictName== null || dictName.equalsIgnoreCase(""))
			{
				continue;
			}
			List dictionaryList = statisticTool.getDictionaryList(dictName);
			if(dictionaryList == null)
			{
				throw new Exception("请查是否正确配置字典(需要实现IStatisticTool接口)");
			}
			
			Map dictionaryMap = this.getDictionaryMap(dictionaryList,dictName);
			if(parmMap != null)
			{
				parmMap.putAll(dictionaryMap);
			}
			System.out.println("parmMap is "+ parmMap);
			QueryDefine clone = (QueryDefine)StatUtil.CloneObject(QueryDefines[queryDefine]);
			reList.remove(queryDefine);
			Iterator iterator = dictionaryMap.keySet().iterator();
			int count = 1;
			while(iterator.hasNext())
			{
				//需要进行复制的配置
				String key = String.valueOf(iterator.next());
				String name = QueryDefines[queryDefine].getName() + count;
				clone.setName(name);
				
				String StatSqlDicts = QueryDefines[queryDefine].getStatSqlDicts().replaceAll(dictName, key);
				clone.setStatSqlDicts(StatSqlDicts);
				String whereSting = QueryDefines[queryDefine].getWhere().replaceAll(dictName, key);
				clone.setWhere(whereSting);
				
				FieldDefine[] fieldDefines = clone.getFieldDefines();
				for(int j=0;j<fieldDefines.length;j++)
				{
					fieldDefines[j].setId(key);
					//fieldDefines[j].setStatSqlDicts(key);
					String listHsql = QueryDefines[queryDefine].getFieldDefines()[j].getListHsql().replaceAll(dictName, key);
					fieldDefines[j].setListHsql(listHsql);
				}
				
				reList.add(StatUtil.CloneObject(clone));
				count++;
			}
		}
		
		Object[] object = reList.toArray();
		
		QueryDefine[] re = new QueryDefine[object.length];
		for(int i=0;i<object.length;i++)
		{
			re[i] = (QueryDefine)object[i];
		}
		return re;
	}
	
	/**
	 * 根据字典字段生成查询sql配置
	 * 
	 * @param QueryDefines
	 * @param dictionaryByName
	 * @return
	 * @throws Exception 
	 */
	private QueryDefine[] handleDynamicColum(QueryDefine[] QueryDefines, String[] selectColumIds) throws Exception
	{
		if(QueryDefines == null || QueryDefines.length==0 ||selectColumIds == null || selectColumIds.length==0)
		{
			return QueryDefines;
		}
		
		List reList = new ArrayList();
		for(int queryDefine=0;queryDefine<QueryDefines.length;queryDefine++)
		{
			QueryDefine qd = QueryDefines[queryDefine];
			for(int i=0;i<selectColumIds.length;i++)
			{
				String dcid = qd.getDynamicColumId();
				String scid = selectColumIds[i];
				if(dcid == null || scid.equalsIgnoreCase(dcid))
				{
					reList.add(qd);
					break;
				}
			}
		}
		
		Object[] object = reList.toArray();
		QueryDefine[] re = new QueryDefine[object.length];
		for(int i=0;i<object.length;i++)
		{
			re[i] = (QueryDefine)object[i];
		}
		return re;
	}
	
	
	public List getListKpiResult(String kpiName, Map param,String[] condSql,String kpiConfigURL,String statID) throws Exception {
//		System.out.println("kpiName is "+kpiName + "," +"kpiConfigURL is " + kpiConfigURL + "," + "statID is " + statID
//				+"," + "param is " + param +"," + "condSql size is " + condSql);
		
		List listResult = null;
		try {
			KpiConfig kpiConfig = statConfigManager.getKpiConfig(kpiConfigURL);
			KpiDefine kpiDefine = kpiConfig.getConfigByKpiDefineName(kpiName);
			
			//处理安字典统计的情况，修改KpiDefine为符合字典统计的情况 
			//动态列的情况，对算法配置进行修改
			//kpiDefine.setQueryDefines(handleDictionary(kpiDefine.getQueryDefines(),param));
			String[] dyColumSelectids = param.get("dynamiccolumparam")==null?null:String.valueOf(param.get("dynamiccolumparam")).split(",");
			kpiDefine.setQueryDefines(handleDynamicColum(kpiDefine.getQueryDefines(),dyColumSelectids));
			
			if(kpiDefine.getDynamicRowParams() == null)
			{
				listResult = statJdbcDAO.getListKpiResult(kpiDefine, param,condSql,statID);
			}
			else //modify by lizhenyou 2009-03-23
			{
				listResult = new ArrayList();
				DynamicRowParam[] dynamicparams = kpiDefine.getDynamicRowParams();
				if(dynamicparams.length == 1)
				{
					String pageName = dynamicparams[0].getPageName();
					String pageValue = String.valueOf(param.get(pageName));
					String[] pvs = pageValue.split(",");
					
					ConditionParam cp = new ConditionParam(pageName);
					kpiDefine.addCp(cp);
					for(int i=0;i<pvs.length;i++)
					{
						param.put(pageName, pvs[i]);//修改参数map
						List list = statJdbcDAO.getListKpiResult(kpiDefine, param,condSql,statID);
						if(list != null)
						{
							for(int l=0;l<list.size();l++)
							{
								//listResult = listResult==null?new ArrayList():listResult;
								listResult.add(list.get(l));
							}
						}
					}
				}
				else if(dynamicparams.length == 2)
				{
					throw new Exception("不支持2级以上的动态行功能");
				}
				else
				{
					throw new Exception("不支持2级以上的动态行功能");
				}
			}
			
			return listResult;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new Exception("统计查询出错!"+e);
		}
	}
	public List getListKpiResult(String kpiName, Map param,String statID) throws Exception {
		List listResult = null;
		try {
			KpiDefine kpiDefine = statConfigManager.getKpiConfig()
					.getConfigByKpiDefineName(kpiName);
			listResult = statJdbcDAO.getListKpiResult(kpiDefine, param,new String[1],statID);
			
			return listResult;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new Exception("统计查询出错!"+e);
		}
	}
	
	/*public OutputStream getHtmlKpiStream(Excel excelConfig, int sheepIndex  ,List reList,String dataUrl) throws Exception
	{
		return statExcelConverter.parseExcel(excelConfig, sheepIndex ,reList, dataUrl);
	}*/
	public OutputStream getHtmlKpiStream(Sheet reportData, List reList,Object info,String dataUrl,KpiDefine kpiDefine) throws Exception
	{
		return statExcelConverter.parseSheet2Html(reportData, reList,info, dataUrl,kpiDefine);
	}
	
	public String getHtmlKpiString(Sheet reportData, List reList, Object info, String dataUrl, KpiDefine kpiDefine) throws Exception
	{
		OutputStream htmlStream = getHtmlKpiStream(reportData, reList, info, dataUrl, kpiDefine);
		return ExcelConverterUtil.OutputStreamToString(htmlStream);
	}
	
	public OutputStream exportExcelKpiStream(String inFilePath,String sourceSheetName, List rslist, Map map,KpiDefine kpiDefine) throws Exception
	{
		return statExportExcel.ResultExportExcel(inFilePath, sourceSheetName, rslist, map,kpiDefine);
	}
	
	public OutputStream exportExcelKpiStream(String inFilePath,String sourceSheetName, List rslist, Map map,KpiDefine kpiDefine,String[] dyColumSelectids,int sheetIndex) throws Exception
	{
		return statExportExcel.ResultExportExcel(inFilePath, sourceSheetName, rslist, map, kpiDefine, dyColumSelectids, sheetIndex);
	}

	private String getSummaryCond(SummaryDefine[] defines,Map actionMap){

			String condSql = "";
			for (int i = 0; i < defines.length; i++) {
				SummaryDefine define = defines[i];
				String data = String.valueOf(actionMap.get(define.getId()));
				if(i>0)condSql+=" and ";
				condSql+=define.getColumn()+"="+data;
			}
			return condSql;

	}

	public List getDetailList(final int[] total,int pageIndex, int pageSize, Map actionMap ,String excelConfigURL,String statID) {
		String sheetIndex = String.valueOf(actionMap.get("reportIndex"));
		String fieldId =  String.valueOf(actionMap.get("fieldId"));
		/*try {
			sheetIndex = new String( sheetIndex.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		try {
			Sheet sheet = statConfigManager.getExcelConfig(excelConfigURL).getSheetByIndex(sheetIndex);
			KpiDefine kpiDefine = statConfigManager.getKpiConfig(sheet.getQueryFileName()).getConfigByKpiDefineName(sheet.getQueryName());
			Map sqlParams = SqlParamCache.Instance().getParams(statID);
			//内存中没有statID，这种情况属于定制统计
			if(sqlParams == null)
			{
				String queryString = "from StatisticFileInfo where detailId='" + statID + "'";
				List list = customStatHibernateDAO.getAlreadySatatistFile(queryString);
				StatisticFileInfo statisticFileInfo = (StatisticFileInfo)list.get(0);
				sqlParams = statisticFileInfo.getCustomDetail(statisticFileInfo.getCustomDetail());
				SqlParamCache.Instance().putParams(statID, sqlParams);
			}
			//add by lizhenyou
			String[] dyColumSelectids = sqlParams.get("dynamiccolumparam")==null?null:String.valueOf(sqlParams.get("dynamiccolumparam")).split(",");
			kpiDefine.setQueryDefines(handleDynamicColum(kpiDefine.getQueryDefines(),dyColumSelectids));
			//add end
			FieldDefine fieldDefine = kpiDefine.getFieldDefineByID(fieldId);
			
			
/*			String summarysql = getSummaryCond(kpiDefine.getSummaryDefines(),actionMap);
			
			if(summarysql!=null && !summarysql.equals("")){
			StringBuffer s  = new StringBuffer(queryStr).insert(
					queryStr.indexOf("where")+5," "+summarysql+" and ");
			queryStr = s.toString();
			}*/
		
		
			/* 改变参数 设置方式
		String condsql = String.valueOf(actionMap.get("condsql"));
			if(!condsql.equals("")){
				StringBuffer s  = new StringBuffer(queryStr).insert(
						queryStr.indexOf("where")+5," "+condsql+" and ");
				queryStr = s.toString();
				
			}
*/
			String countSqlStr="";
			
			String listCount = fieldDefine.getListCount();
			
			if(listCount==null || "".equalsIgnoreCase(listCount))
			{
				countSqlStr = fieldDefine.getListHsql();
			}
			else
			{
				countSqlStr = listCount;
			}
			
			sqlParams.putAll(actionMap);
			countSqlStr = StatUtil.getRepString(countSqlStr, sqlParams, "@");
			
			if (total[0] < 0) {
				String totalSQL = null;
				if(listCount != null && !"".equalsIgnoreCase(listCount))
				{
					totalSQL = countSqlStr;
				}
				else
				{
					int fromindex = countSqlStr.indexOf("from");
					int toindex = countSqlStr.indexOf("order by ");
					
					if (toindex==-1)
						totalSQL = fieldDefine.getListCountSelectSql() + ""
							+ countSqlStr.substring(fromindex);
					else
						totalSQL = fieldDefine.getListCountSelectSql() + ""
							+ countSqlStr.substring(fromindex,toindex);
				}
				
				this.logger.info("\n显示Detail fieldDefine id : " + fieldDefine.getId());
				this.logger.info("\n总数totalSQL: " + totalSQL);
				total[0]=	statJdbcDAO.queryForInt(totalSQL);
			}
			
			//增加判断是否使用默认查询统计结果详细列表的DAO接口 
//			if(statDetailDAO == null)
//			{
//				statDetailDAO = (IStatDetailDAO)ApplicationContextHolder.getInstance().getBean("defaultDetailDAO");
//				this.logger.info("\n使用默认查询统计结果详细列表的DAO接口 defaultDetailDAO： " + statDetailDAO.getClass());
//			}
			//为了方便2次开发人员，根据applcationContext-all.xml中配置的方言而定
			//hql方言 取值范围org.hibernate.dialect.InformixDialect和org.hibernate.dialect.OracleDialect
			String hQLDialect = com.boco.eoms.base.util.ApplicationContextHolder.getInstance().getHQLDialect().trim();
			if("org.hibernate.dialect.InformixDialect".equalsIgnoreCase(hQLDialect))
			{
				//Informix
				statDetailDAO = (IStatDetailDAO)ApplicationContextHolder.getInstance().getBean("statDetailDAOInformix");
			}
			else if("org.hibernate.dialect.OracleDialect".equalsIgnoreCase(hQLDialect))
			{
				//Oracle
				statDetailDAO = (IStatDetailDAO)ApplicationContextHolder.getInstance().getBean("statDetailDAOOracle");
			}
			else
			{
				//默认的方言
				statDetailDAO = (IStatDetailDAO)ApplicationContextHolder.getInstance().getBean("defaultDetailDAO");
			}
			this.logger.info("\n使用默认查询统计结果详细列表的DAO接口 defaultDetailDAO： " + statDetailDAO.getClass());
			
			String statDetailVO = kpiDefine.getStatDetailVo();
			String detailQueryStr = StatUtil.getRepString(fieldDefine.getListHsql(), sqlParams, "@");
			this.logger.info("\n显示Detail sql : " + detailQueryStr);
			List list= statDetailDAO.getListDetail(detailQueryStr, statDetailVO, pageIndex, pageSize);
			
			if (!fieldDefine.getListVoMethod().equals("")) {
				Class c = statDetailDAO.getClass();
				Class[] types = new Class[6];
				types[0] = List.class;
				types[1] = KpiDefine.class;
				types[2] = QueryDefine.class;
				types[3] = FieldDefine.class;
				types[4] = Map.class;
				types[5] = Map.class;

				Method m = c.getMethod(fieldDefine.getListVoMethod(), types);
				Object[] args = new Object[6];
				args[0] = list;
				args[1] = kpiDefine;
				args[2] = kpiDefine.getQueryDefineByFieldID(fieldId);;
				args[3] = fieldDefine;
				args[4] = sqlParams;
				args[5] = actionMap;
				return (List) m.invoke(statDetailDAO, args);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return null;
	}
	
	public Map getDictionaryMap(List list,String id) 
	{
		Map map = new HashMap();
		
		for(int i=0;i<list.size();i++)
		{
			map.put(id + list.get(i), list.get(i));
		}
		
		return map;
	}
	
	private Map setDynamicColumParamInMap(String[] values,String id) 
	{
		Map map = new HashMap();
		
		for(int i=0;i<values.length;i++)
		{
			map.put(id + (i+1), values[i]);
		}
		
		return map;
	}
	
	public int getCustomTotalCount(final String queryStr) throws HibernateException
	{
		return customStatHibernateDAO.getCustomTotalCount(queryStr);
	}
	
	public List getDetailCustomStatisticList(final String queryStr ,final int total ,  final int pageSize , final int pageIndex)
	{
		return customStatHibernateDAO.getDetailCustomStatisticList(queryStr, total, pageSize, pageIndex);
	}
	
	public int getStatistiTotalCount(final String queryStr) throws HibernateException
	{
		return customStatHibernateDAO.getStatistiTotalCount(queryStr);
	}

	public List getDetailStatisticFileList(final String queryStr ,int total , int pageSize , int pageIndex)
	{
		return customStatHibernateDAO.getDetailStatisticFileList(queryStr, total, pageSize, pageIndex);
	}
	
	public boolean saveStatisticInfo(StatisticFileInfo statisticFileInfo)
	{
		return customStatHibernateDAO.saveStatisticInfo(statisticFileInfo);
	}
	
	public List getAlreadyCustomStatisticFilterList(Calendar currentCanlender,String queryStr)
	{
		return customStatHibernateDAO.getAlreadyCustomStatisticFilterList(currentCanlender,queryStr);
	}
	
	/**
	 * 删除定制信息
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteCustomStatistic(String id)
	{
		return customStatHibernateDAO.deleteCustomStatistic(id);
	}

	public Integer getTotalCount(Map actionMap) {
		return null;
	}
	
	public ExportExcel getStatExportExcel() {
		return statExportExcel;
	}
	
	public void setStatExportExcel(ExportExcel statExportExcel) {
		this.statExportExcel = statExportExcel;
	}
	public IStatDetailDAO getStatDetailDAO() {
		return statDetailDAO;
	}
	public void setStatDetailDAO(IStatDetailDAO statDetailDAO) {
		this.statDetailDAO = statDetailDAO;
	}
	public String getStatDetailVOName() {
		return statDetailVOName;
	}
	public void setStatDetailVOName(String statDetailVOName) {
		this.statDetailVOName = statDetailVOName;
	}
	
	public ICustomStatHibernateDAO getCustomStatHibernateDAO() {
		return customStatHibernateDAO;
	}
	
	public void setCustomStatHibernateDAO(
			ICustomStatHibernateDAO customStatHibernateDAO) {
		this.customStatHibernateDAO = customStatHibernateDAO;
	}
	
	public IStatisticTool getStatisticTool() {
		return statisticTool;
	}

	public void setStatisticTool(IStatisticTool statisticTool) {
		this.statisticTool = statisticTool;
	}
	

	public IStatJdbcDAO getStatJdbcDAO() {
		return statJdbcDAO;
	}

	public void setStatJdbcDAO(IStatJdbcDAO statJdbcDAO) {
		this.statJdbcDAO = statJdbcDAO;
	}

	public IStatConfigManager getStatConfigManager() {
		return statConfigManager;
	}

	public void setStatConfigManager(IStatConfigManager statConfigManager) {
		this.statConfigManager = statConfigManager;
	}

	public ExcelConverter getStatExcelConverter() {
		return statExcelConverter;
	}

	public void setStatExcelConverter(ExcelConverter statExcelConverter) {
		this.statExcelConverter = statExcelConverter;
	}

}
