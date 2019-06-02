package com.boco.eoms.km.train.mgr.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.km.statics.model.PersonalStatistic;
import com.boco.eoms.km.statics.util.StatisticMethod;
import com.boco.eoms.km.train.model.TrainAllStatistic;
import com.boco.eoms.km.train.model.TrainDeptStatistic;
import com.boco.eoms.km.train.model.TrainPersonalStatistic;
import com.boco.eoms.km.train.model.TrainRecord;
import com.boco.eoms.km.train.model.TrainSpecialtyStatistic;
import com.boco.eoms.km.train.mgr.TrainRecordMgr;
import com.boco.eoms.km.train.dao.TrainRecordDao;

/**
 * <p>
 * Title:培训需求
 * </p>
 * <p>
 * Description:培训需求信息
 * </p>
 * <p>
 * Wed Jul 01 16:11:13 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class TrainRecordMgrImpl implements TrainRecordMgr {
 
	private TrainRecordDao  trainRecordDao;
 	
	public TrainRecordDao getTrainRecordDao() {
		return this.trainRecordDao;
	}
 	
	public void setTrainRecordDao(TrainRecordDao trainRecordDao) {
		this.trainRecordDao = trainRecordDao;
	}
 	
    public List getTrainRecords() {
    	return trainRecordDao.getTrainRecords();
    }
    
    public TrainRecord getTrainRecord(final String id) {
    	return trainRecordDao.getTrainRecord(id);
    }
    
    public void saveTrainRecord(TrainRecord trainRecord) {
    	trainRecordDao.saveTrainRecord(trainRecord);
    }
    
    public void removeTrainRecord(final String id) {
    	trainRecordDao.removeTrainRecord(id);
    }
    
    /**
	 * 根据培训时间查询
	 * @param trainBeginTime
	 * @param trianEndTime
	 * @return
	 */
	public List getTrainRecords(final Date trainBeginTime,final Date trianEndTime){
//		List list = trainRecordDao.getTrainRecords(trainBeginTime, trianEndTime);
//		List list_result = new ArrayList();
//		for(int i=0;i<list.size();i++){
//			Object [] objs = (Object[])list.get(i);
//			TrainRecord trainRecord = new TrainRecord();
//			trainRecord.setId(StatisticMethod.objectToString(objs[0]));
//			trainRecord.setTrainName(StatisticMethod.objectToString(objs[1]));
//			trainRecord.setTrainUser(StatisticMethod.objectToString(objs[2]));
//			trainRecord.setTrainDept(StatisticMethod.objectToString(objs[3]));
//			trainRecord.setTrainAddress(StatisticMethod.objectToString(objs[4]));
//			trainRecord.setTrainDate(StatisticMethod.stringToDate(objs[5].toString(), "yyyy-MM-dd"));
//			trainRecord.setTrainTime(StatisticMethod.objectToString(objs[6]));
//			trainRecord.setTrainVender(StatisticMethod.objectToString(objs[7]));
//			trainRecord.setTrainType(StatisticMethod.objectToString(objs[8]));
//			trainRecord.setTrainCenter(StatisticMethod.objectToString(objs[9]));
//			trainRecord.setTrainUnit(StatisticMethod.objectToString(objs[10]));
//			list_result.add(trainRecord);
//		}
//		return list_result;
		return trainRecordDao.getTrainRecords(trainBeginTime, trianEndTime);
	}
    
	
    public Map getTrainRecords(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return trainRecordDao.getTrainRecords(curPage, pageSize, whereStr);
	}
	
    
    /**
	 * 根据条件分页查询个人培训记录
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @return 返回个人培训记录
	 */
	public Map getPersonalStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate){
		
		Map map = null;
		
		if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
			Date newStartDate = StatisticMethod.stringToDate( startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
			Date newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
			map = this.trainRecordDao.getPersonalStatistics(curPage, pageSize, newStartDate, newEndDate);
		}
		else if("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())){
			String newStartDate = startDate + " 00:00:00";
			String newEndDate = endDate + " 23:59:59";
			map = this.trainRecordDao.getPersonalStatistics(curPage, pageSize, newStartDate, newEndDate);
		} 

		List list = (List)map.get("result");
		List list_result = new ArrayList();
		int length = list.size();
		for (int i = 0; i < length; i++){
			Object [] objs = (Object[])list.get(i);
			TrainPersonalStatistic trainPersonalStatistic = new TrainPersonalStatistic();
			trainPersonalStatistic.setTrainUser(StatisticMethod.objectToString(objs[0])); //用户姓名
			//trainPersonalStatistic.setTrainDept(StatisticMethod.objectToString(objs[1])); //用户部门
			trainPersonalStatistic.setTrainCount(StatisticMethod.objectToInteger(objs[1])); //培训次数
			trainPersonalStatistic.setTimeCount(StatisticMethod.objectToInteger(objs[2])); //培训天数
			list_result.add(trainPersonalStatistic);
		}
		map.put("result", list_result);
		return map;
	}
	
	/**
	 * oracle
	 * 根据专业统计 培训天数和次数
	 * @param curPage
	 * @param pageSize
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Map getSpecialitylStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate){
		Map map = null;
		
		if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
			Date newStartDate = StatisticMethod.stringToDate( startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
			Date newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
			map = this.trainRecordDao.getSpecialitylStatistics(curPage, pageSize, newStartDate, newEndDate);
		}
		else if("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())){
			String newStartDate = startDate + " 00:00:00";
			String newEndDate = endDate + " 23:59:59";
			map = this.trainRecordDao.getSpecialitylStatistics(curPage, pageSize, newStartDate, newEndDate);
		} 

		List list = (List)map.get("result");
		List list_result = new ArrayList();
		int length = list.size();
		for (int i = 0; i < length; i++){
			Object [] objs = (Object[])list.get(i);
			TrainSpecialtyStatistic trainSpecialtyStatistic = new TrainSpecialtyStatistic();
			trainSpecialtyStatistic.setTrainSpeciality(StatisticMethod.objectToString(objs[0])); //用户姓名
			trainSpecialtyStatistic.setTrainCount(StatisticMethod.objectToInteger(objs[1])); //培训次数
			trainSpecialtyStatistic.setTimeCount(StatisticMethod.objectToInteger(objs[2])); //培训天数
			list_result.add(trainSpecialtyStatistic);
		}
		map.put("result", list_result);
		return map;
	}
	
	/**
	 * oracle
	 * 根据部门统计 培训天数和次数
	 * @param curPage
	 * @param pageSize
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Map getDeptStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate){
		Map map = null;
		
		if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
			Date newStartDate = StatisticMethod.stringToDate( startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
			Date newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
			map = this.trainRecordDao.getDeptStatistics(curPage, pageSize, newStartDate, newEndDate);
		}
		else if("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())){
			String newStartDate = startDate + " 00:00:00";
			String newEndDate = endDate + " 23:59:59";
			map = this.trainRecordDao.getDeptStatistics(curPage, pageSize, newStartDate, newEndDate);
		} 

		List list = (List)map.get("result");
		List list_result = new ArrayList();
		int length = list.size();
		for (int i = 0; i < length; i++){
			Object [] objs = (Object[])list.get(i);
			TrainDeptStatistic trainDeptStatistic = new TrainDeptStatistic();
			trainDeptStatistic.setTrainDept(StatisticMethod.objectToString(objs[0])); //部门
			trainDeptStatistic.setTrainCount(StatisticMethod.objectToInteger(objs[1])); //培训次数
			trainDeptStatistic.setTimeCount(StatisticMethod.objectToInteger(objs[2])); //培训天数
			list_result.add(trainDeptStatistic);
		}
		map.put("result", list_result);
		return map;
	}
	
	/**
	 * oracle
	 * 根据培训名字统计 培训天数和次数
	 * @param curPage
	 * @param pageSize
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Map getAllStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate){
		Map map = null;
		
		if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
			Date newStartDate = StatisticMethod.stringToDate( startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
			Date newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
			map = this.trainRecordDao.getDeptStatistics(curPage, pageSize, newStartDate, newEndDate);
		}
		else if("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())){
			String newStartDate = startDate + " 00:00:00";
			String newEndDate = endDate + " 23:59:59";
			map = this.trainRecordDao.getAllStatistics(curPage, pageSize, newStartDate, newEndDate);
		} 

		List list = (List)map.get("result");
		List list_result = new ArrayList();
		int length = list.size();
		for (int i = 0; i < length; i++){
			Object [] objs = (Object[])list.get(i);
			TrainAllStatistic trainAllStatistic = new TrainAllStatistic();
			trainAllStatistic.setTrainName(StatisticMethod.objectToString(objs[0])); //培训名称
			trainAllStatistic.setTrainCount(StatisticMethod.objectToInteger(objs[1])); //培训次数
			trainAllStatistic.setTimeCount(StatisticMethod.objectToInteger(objs[2])); //培训天数
			list_result.add(trainAllStatistic);
		}
		map.put("result", list_result);
		return map;
	}
}