package com.boco.eoms.commons.statistic.base.statinterface;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.statistic.base.config.model.KpiConfig;
import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.dao.impl.StatJdbcDAOImpl;
import com.boco.eoms.commons.statistic.base.reference.ParseXmlService;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;

public class StatDAO {

	private static KpiDefine getKpiDefine(String kpiConfigUrl,String kpiDefineName) throws FileNotFoundException, Exception
	{
		KpiConfig kpiConfig = (KpiConfig) ParseXmlService.create().xml2object(
				KpiConfig.class, StaticMethod.getFilePathForUrl(kpiConfigUrl));
		
		return kpiConfig.getConfigByKpiDefineName(kpiDefineName);
	}
	
	private static List getDataList (Map paramsMap,KpiDefine kpiDefine) throws Exception
	{
		StatJdbcDAOImpl sjdi = new StatJdbcDAOImpl();
		return sjdi.getListKpiResult(kpiDefine, "",paramsMap);
	}
	
	/**
	 * 
	 * @param paramsMap map的结构{beginTime=”2008-9-7”,endTime=” 2008-4-19”……等其他的参数}。
	 * @param kpiConfigUrl 算法配置文件路径，web中格式为classpath:config/文件名，文件存放路径：“web\WEB-INF\classes\config ”
	 * @param kpiDefineName 该字段是标识需要使用的算法名称。对应算法配置<kpi-define>节点下的<name>节点
	 * @return List
	 * List：结果集，结构是一个list中存放这多个map.
		例如：
		List
		{
			{s1=a,f1=12,f2=4}，
			{s1=b,f1=15,f2=5},
		{s1=c,f1=16,f2=7},
		}
		是list结果集中存放这3个map
	 * @throws Exception  
	 * 
	 */
	public static List getDataList (Map paramsMap,String kpiConfigUrl,String kpiDefineName) throws Exception
	{
		return getDataList(paramsMap,getKpiDefine(kpiConfigUrl,kpiDefineName));
	}
	
	
	/**
	 * @param args
	 * @throws Exception 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, Exception 
	{
		String path = "classpath:config/statistic/commonfault-config/oracle/statistic-config-query-commonfault_T_resolve_KPI5_oracle.xml";
		String name = "commonfault_T_resolve_byuser";
		KpiDefine kpiDefine = getKpiDefine(path,name);
		System.out.print(kpiDefine);
		
//		endTime=2008-11-30 23:59:59, beginTime=2008-11-01 00:00:00, 
//		areaid=""
//		mainNetSortOne=101010401
//		userByDeptid=""
		
		Map map = new HashMap();
		map.put("beginTime", "2008-11-01 00:00:00");
		map.put("endTime", "2008-11-30 23:59:59");
		map.put("areaid", "");
		map.put("userByDeptid", "");
		map.put("mainNetSortOne", "101010401");;
		
		
		List list = getDataList(map,kpiDefine);
		System.out.print(list);
//		[{f2=0, f5=0, summaryKey=gzjdr10101040122, s2=101010401, f1=1, f4=1, s1=gzjdr, s3=22, f3=1},
//		{f2=0, f5=0, summaryKey=new110101040121, s2=101010401, f1=1, f4=1, s1=new1, s3=21, f3=1}, 
//		{f2=1, f5=1, summaryKey=gzcl110101040121, s2=101010401, f1=1, f4=0, s1=gzcl1, s3=21, f3=0}, 
//		{f2=1, f5=1, summaryKey=gzcl110101040122, s2=101010401, f1=1, f4=0, s1=gzcl1, s3=22, f3=0}]
	}
}
