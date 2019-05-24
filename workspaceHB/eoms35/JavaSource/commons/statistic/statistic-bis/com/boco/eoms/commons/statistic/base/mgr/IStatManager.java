/*
 * Created on 2008-1-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.statistic.base.mgr;

import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.commons.statistic.base.config.IStatisticTool;
import com.boco.eoms.commons.statistic.base.config.excel.Sheet;
import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.dao.ICustomStatHibernateDAO;
import com.boco.eoms.commons.statistic.base.dao.IStatDetailDAO;
import com.boco.eoms.commons.statistic.base.dao.IStatJdbcDAO;
import com.boco.eoms.commons.statistic.base.excelutil.mgr.impl.DyTableInfo;
import com.boco.eoms.commons.statistic.base.mgr.impl.ExcelConverter;
import com.boco.eoms.commons.statistic.base.model.StatisticFileInfo;


/**
 * @author liuxy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IStatManager {
	public IStatJdbcDAO getStatJdbcDAO();
	public IStatDetailDAO getStatDetailDAO(); 
	public void setStatJdbcDAO(IStatJdbcDAO statJdbcDAO);
	public void setStatDetailDAO(IStatDetailDAO statDetailDAO);

	//add by lizhenyou
	public ICustomStatHibernateDAO getCustomStatHibernateDAO();
	public void setCustomStatHibernateDAO(ICustomStatHibernateDAO customStatHibernateDAO);
	public IStatisticTool getStatisticTool();
	public void setStatisticTool(IStatisticTool statisticTool) ;
	public Map getDictionaryMap(List list,String id);
	
	public String getStatDetailVOName();
	public void setStatDetailVOName(String statDetailVOName);
	
	public void setStatExcelConverter(ExcelConverter excelConverter);
	public ExcelConverter getStatExcelConverter();
	
	public IStatConfigManager getStatConfigManager();
	
	public void setStatConfigManager(IStatConfigManager statConfigManager);
	
	/**
	 * @deprecated
	 */
	public List getListKpiResult(String KpiName, Map param,String statID) throws Exception;
	public List getListKpiResult(String kpiName, Map param,String[] condSql,String kpiConfigURL,String statID) throws Exception;

	/**
	 * 
	 * @param KpiName
	 * @param param
	 * @return
	 * @throws Exception
	 */
//	public Map getKpiResult(String KpiName, Map param) throws Exception;
	
//	public List getKpiResult(Map param);
	/**
	 * (non-Javadoc)
	 * 根据查询条件得到工单列表
	 * @throws SheetException
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#getHolds()
	 */
//	public List getMainList(final Integer curPage, final Integer pageSize,final String queryStr) throws SheetException, SheetException;
	
	/**
	 * (non-Javadoc)
	 * 根据查询条件得到工单总数
	 * @see com.boco.eoms.sheet.base.dao.IMainDAO#getHoldsCount()
	 */
//	public Integer getTotalCount(final String queryStr) throws SheetException;
	
	
	/**
	 * 通过名称返回查询列表需要的sql语句	
	 * @param summaryData
	 * @throws Exception
	 */
//	   public String getlistSqlByName(String kpiDefineName,String queryDefineName,String asName, String summaryData) throws Exception;
	/**
	 * @param kpiDefineName
	 * @param queryDefineName
	 * @param fieldAsName
	 * @param summaryData
	 * @return
	 * @throws Exception
	 */
//	public String getListCountSqlByName(String kpiDefineName, String queryDefineName, String fieldAsName, String summaryData) throws Exception;
	/**
	 * @param kpiDefineName
	 * @param queryDefineName
	 * @param fieldAsName
	 * @param summaryData
	 * @param isPercentCount
	 * @param condition
	 * @return
	 * @throws Exception
	 */
//	public Integer getTotalCount(String kpiDefineName, String queryDefineName, String fieldAsName, String summaryData, String isPercentCount, String condition) throws Exception;
	/**
	 * @param pageIndex
	 * @param integer
	 * @param kpiDefineName
	 * @param queryDefineName
	 * @param fieldAsName
	 * @param summaryData
	 * @param isPercentCount
	 * @param condition
	 * @return
	 * @throws Exception
	 */
//	public List getMainList(Integer pageIndex, Integer integer, String kpiDefineName, String queryDefineName, String fieldAsName, String summaryData, String isPercentCount, String condition) throws Exception;
	  
	
	/**
	 * 获得统计的HtmlStream
	 * @param excelConfig
	 * @param sheepIndex
	 * @param mappingPath
	 * @param reList
	 * @return
	 * @throws Exception
	 */
	//public OutputStream getHtmlKpiStream(Excel excelConfig, int sheepIndex  ,List reList) throws Exception;
	
	public OutputStream getHtmlKpiStream(Sheet reportData, List reList, Object info, String dataUrl, KpiDefine kpiDefine) throws Exception;
	
	public String getHtmlKpiString(Sheet reportData, List reList, Object info, String dataUrl, KpiDefine kpiDefine) throws Exception;
	
	/**
	 * 导出统计的Excel
	 * @param inFilePath
	 * @param sourceSheetName
	 * @param rslist
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public OutputStream exportExcelKpiStream(String inFilePath,String sourceSheetName, List rslist, Map map,KpiDefine kpiDefine) throws Exception;
	
	/**
	 * 导出统计的Excel
	 * @param inFilePath
	 * @param sourceSheetName
	 * @param rslist
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public OutputStream exportExcelKpiStream(String inFilePath,String sourceSheetName, List rslist, Map map,KpiDefine kpiDefine,String[] dyColumSelectids,int sheetIndex) throws Exception;
	
	public Integer getTotalCount(Map actionMap);
	public List getDetailList(final int[] total, int pageIndex, int pageSize, Map actionMap ,String excelConfigURL,String statID);

	//************************订制统计DAO**********************************
	/**
	 * 获得订制统计的详细信息（分页）
	 * 
	 * @return
	 */
	public List getDetailCustomStatisticList(final String queryStr ,final int total ,  final int pageSize , final int pageIndex);
	
	
	/**
	 * 获得订制信息的总数
	 * 
	 * @return
	 */
	public int getCustomTotalCount(final String queryStr) throws HibernateException;
	
	/**
	 * 获得已经统计的总数
	 * @param queryStr
	 * @return
	 * @throws HibernateException
	 */
	public int getStatistiTotalCount(final String queryStr) throws HibernateException;
	
	/**
	 * 获得统计出来的文件信息（分页）
	 * 
	 * @param statisticInfoList
	 * @param total
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public List getDetailStatisticFileList(final String queryStr ,int total , int pageSize , int pageIndex);
	
	/**
	 * 保存统计（文件与数据库的关系）
	 * 
	 * @return
	 */
	public boolean saveStatisticInfo(StatisticFileInfo statisticFileInfo);
	
	/**
	 * 查询已经订制的统计
	 * @param currentCanlender
	 * @param queryStr
	 * @return
	 */
	public List getAlreadyCustomStatisticFilterList(Calendar currentCanlender,String queryStr);
	
	/**
	 * 删除定制信息
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteCustomStatistic(String id);
}
