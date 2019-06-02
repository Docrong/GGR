package com.boco.eoms.km.statics.mgr.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.km.statics.dao.PersonalApplyStatisticDao;
import com.boco.eoms.km.statics.mgr.PersonalApplyStatisticMgr;
import com.boco.eoms.km.statics.model.PersonalApplyStatistic;
import com.boco.eoms.km.statics.util.StatisticMethod;

/**
 * <p>
 * Title:知识申请人本期（周、月、季、年）申请知识情况统计表
 * </p>
 * <p>
 * Description:知识申请人本期（周、月、季、年）申请知识情况统计表
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
public class PersonalApplyStatisticMgrImpl implements PersonalApplyStatisticMgr {
 
	//调用知识操作日统计表接口,读取所需数据
	private PersonalApplyStatisticDao personalApplyStatisticDao;

	public PersonalApplyStatisticDao getPersonalApplyStatisticDao() {
		return personalApplyStatisticDao;
	}

	public void setPersonalApplyStatisticDao(
			PersonalApplyStatisticDao personalApplyStatisticDao) {
		this.personalApplyStatisticDao = personalApplyStatisticDao;
	}

	
	/**
	 * 根据条件分页查询知识申请人本期（周、月、季、年）申请知识情况统计表
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param period 查询范围(year,month,season,week)
	 * @return 返回知识申请人本期（周、月、季、年）申请知识情况统计表的分页列表
	 */
	public Map getPersonalApplyStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate){

		Map map = null;
		
		if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
			Date newStartDate = StatisticMethod.stringToDate( startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
			Date newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
			map = this.personalApplyStatisticDao.getPersonalApplyStatistics(curPage, pageSize, newStartDate, newEndDate);
		}
		else if("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())){
			String newStartDate = startDate + " 00:00:00";
			String newEndDate = endDate + " 23:59:59";
			map = this.personalApplyStatisticDao.getPersonalApplyStatistics(curPage, pageSize, newStartDate, newEndDate);
		} 
 
		//遍历取回数据集合,封装以List<PersonalApplyStatistic>中
		List list = (List)map.get("result");
		List list_result = new ArrayList();
		for (int i = 0; i < list.size(); i++){
			Object [] objs = (Object[])list.get(i);	    	
			PersonalApplyStatistic applyStatistic = new PersonalApplyStatistic();
			applyStatistic.setUserName(StatisticMethod.objectToString(objs[0]));
			applyStatistic.setUserDept(StatisticMethod.objectToString(objs[1]));
			applyStatistic.setApplyCount(StatisticMethod.objectToInteger(objs[2])); //申请次数
			list_result.add(applyStatistic);
			
		}
		map.put("result", list_result);
		
		 //返回结果
		return map;
	}

	/**
	 * 根据条件分页查询部门知识贡献统计
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @return 返回人员知识贡献统计
	 */
	public Map getDeptApplyStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate){

		Map map = null;
		if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
			Date newStartDate = StatisticMethod.stringToDate( startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
			Date newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");		
			map = this.personalApplyStatisticDao.getDeptApplyStatistics(curPage, pageSize, newStartDate, newEndDate);
		}
		else if("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())){
			String newStartDate = startDate + " 00:00:00";
			String newEndDate = endDate + " 23:59:59";			
			map = this.personalApplyStatisticDao.getDeptApplyStatistics(curPage, pageSize, newStartDate, newEndDate);
		}
 
		//遍历取回数据集合,封装以List<PersonalApplyStatistic>中
		List list = (List)map.get("result");
		List list_result = new ArrayList();
		for (int i = 0; i < list.size(); i++){
			Object [] objs = (Object[])list.get(i);
			PersonalApplyStatistic useStatistic = new PersonalApplyStatistic();
			useStatistic.setUserDept(StatisticMethod.objectToString(objs[0])); //用户部门
			useStatistic.setApplyCount(StatisticMethod.objectToInteger(objs[1])); //申请次数
			list_result.add(useStatistic);
			
		}
		map.put("result", list_result);
		
		 //返回结果
		return map;
	}
}