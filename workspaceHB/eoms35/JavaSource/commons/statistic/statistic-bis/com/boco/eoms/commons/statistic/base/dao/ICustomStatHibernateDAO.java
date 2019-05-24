package com.boco.eoms.commons.statistic.base.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.commons.statistic.base.model.StatisticFileInfo;

/**
 * 订制统计Hibernate接口
 * 
 * @author lizhenyou
 *
 */
public interface ICustomStatHibernateDAO {
	
	/**
	 * 得到表的记录条数
	 * 
	 * @param queryStr
	 * @return
	 * @throws HibernateException
	 */
	public int getCustomTotalCount(final String queryStr) throws HibernateException;
	
	/**
	 * 查询已经订制的统计
	 * @param currentCanlender
	 * @param queryStr
	 * @return
	 */
	public List getAlreadyCustomStatisticFilterList(Calendar currentCanlender,String queryStr);
	
	/**
	 * 获得订制统计的详细信息（分页）
	 * 
	 * @return
	 */
	public List getDetailCustomStatisticList(final String queryStr ,final int total ,  final int pageSize , final int pageIndex);
	
	/**
	 * 保存统计（文件与数据库的关系）
	 * 
	 * @return
	 */
	public boolean saveStatisticInfo(StatisticFileInfo statisticFileInfo);
	
	/**
	 * 获得已经统计过的所有信息
	 * 
	 * @return
	 */
	public List getAlreadystatisticInfoList();
	
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
	 * 
	 * @param id
	 * @return
	 */
	public List getAlreadySatatistFile(String quary);
	
	/**
	 * 删除定制信息
	 * @param id
	 * @return
	 */
	public boolean deleteCustomStatistic(String id);
	
}
