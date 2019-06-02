package com.boco.eoms.commons.statistic.base.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.boco.eoms.commons.statistic.base.config.model.KpiConfig;
import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.dao.IStatJdbcDAO;
import com.boco.eoms.commons.statistic.base.dao.impl.StatJdbcDAOImpl;
import com.boco.eoms.commons.statistic.base.reference.ApplicationContextHolder;
import com.boco.eoms.commons.statistic.base.reference.ParseXmlService;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;
import com.boco.eoms.commons.statistic.base.reference.UUIDHexGenerator;

public class QuerySql {

	/**
	 * 
	 * @param kpiName
	 * @param param (request.getParameterMap()页面中的参数)
	 * @param condSql
	 * @param kpiConfigURL
	 * @param statID
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public static List getListKpiResult(KpiDefine kpiDefine, Map param,String statID,IStatJdbcDAO sjdi ) throws Exception
	{
		Map sqlParams = ((StatJdbcDAOImpl)sjdi).getCondSqlByMap(kpiDefine, param,statID);
		
		return ((StatJdbcDAOImpl)sjdi).getListKpiResult(kpiDefine, "",sqlParams);
		
	}
	
		private static KpiConfig getKpiConfig(String kpiConfigUrl)
		{
	//		StatConfigManagerImpl scmi = new StatConfigManagerImpl();
	//		KpiConfig kpiConfig= null;
	//		try {
	//			kpiConfig = scmi.getKpiConfig(kpiConfigUrl);
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
			
			KpiConfig kpiConfig = null;
			try {
				kpiConfig = (KpiConfig) ParseXmlService.create().xml2object(
						KpiConfig.class, StaticMethod.getFilePathForUrl(kpiConfigUrl));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return kpiConfig;
		}
	
		private static KpiDefine getConfigByKpiDefineName(KpiConfig kpiConfig, String kpiDefineName)
		{
			KpiDefine kpiDefine = null;
			try {
				kpiDefine = kpiConfig.getConfigByKpiDefineName(kpiDefineName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return kpiDefine;
		}
	
		//对外接口
		//获取KpiDefine结果集数据结构
		private static KpiDefine getConfigByKpiDefineName(String kpiConfigUrl,String kpiDefineName)
		{
			return getConfigByKpiDefineName(getKpiConfig(kpiConfigUrl),kpiDefineName);
		}
		
		private static List querySql(HttpServletRequest request)throws Exception
	    {
	    	String kpiConfigUrl = "classpath:config/statistic-config-query-commonfault_T_resolve_KPI4.xml";
			String kpiDefineName = "commonfault_T_resolve_byuser";
			KpiDefine kpiDefine = getConfigByKpiDefineName(kpiConfigUrl, kpiDefineName);
			
			StatJdbcDAOImpl sjdi = (StatJdbcDAOImpl)ApplicationContextHolder.getInstance().getBean("statBaseJdbcDAO");

			Map actionMap = StatUtil.ParameterMapToUtilMap(request.getParameterMap());
			//根据type修改 beginTime 和 endTime
			actionMap = StatUtil.modActionMap(actionMap);
			
//			String statID =java.util.UUID.randomUUID().toString();//1.5jdk
			UUIDHexGenerator uu = UUIDHexGenerator.getInstance();
			String statID = uu.getID();
			
			List rsList = getListKpiResult(kpiDefine, actionMap, 
					statID,sjdi);
			//打印返回的List
			StatUtil.printRsList(rsList);
			
			return rsList;
	    }
	   
	    public static KpiDefine getKpiDefine(String kpiConfigUrl,String kpiDefineName)
	    {
	    	return getConfigByKpiDefineName(kpiConfigUrl, kpiDefineName);
	    }
	    
	    public static List getDataList(Map paramsMap,KpiDefine kpiDefine) throws Exception
	    {
	    	StatJdbcDAOImpl sjdi = (StatJdbcDAOImpl)ApplicationContextHolder.getInstance().getBean("statBaseJdbcDAO");

//	    	String statID =java.util.UUID.randomUUID().toString();//1.5jdk
			UUIDHexGenerator uu = UUIDHexGenerator.getInstance();
			String statID = uu.getID();
			
			List rsList = getListKpiResult(kpiDefine, paramsMap,statID,sjdi);
			
			return rsList;
	    }
}
