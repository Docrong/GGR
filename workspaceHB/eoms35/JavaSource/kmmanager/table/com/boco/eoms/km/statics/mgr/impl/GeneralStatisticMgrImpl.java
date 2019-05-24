package com.boco.eoms.km.statics.mgr.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.km.statics.dao.GeneralStatisticDao;
import com.boco.eoms.km.statics.mgr.GeneralStatisticMgr;
import com.boco.eoms.km.statics.model.GeneralStatistic;
import com.boco.eoms.km.statics.model.PersonalStatistic;
import com.boco.eoms.km.statics.util.StatisticMethod;

/**
 * <p>
 * Title:知识库按知识库统计
 * </p>
 * <p>
 * Description:知识库按知识库统计
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() daizhigang
 * @moudle.getVersion() 0.1
 * 
 */
public class GeneralStatisticMgrImpl  implements GeneralStatisticMgr {
	
	private GeneralStatisticDao generalStatisticDao;

	public GeneralStatisticDao getGeneralStatisticDao() {
		return generalStatisticDao;
	}

	public void setGeneralStatisticDao(GeneralStatisticDao generalStatisticDao) {
		this.generalStatisticDao = generalStatisticDao;
	}
	/**
	 * 根据条件分页查询部门知识贡献统计
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @return 返回人员知识贡献统计
	 */
	public Map getGeneralStatistics(final String startDate, final String endDate){

		Map map = null;		
		if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
			Date newStartDate = StatisticMethod.stringToDate( startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
			Date newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");		
			map = this.generalStatisticDao.getGeneralStatistics(newStartDate, newEndDate);
		}
		else if("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())){
			String newStartDate = startDate + " 00:00:00";
			String newEndDate = endDate + " 23:59:59";			
			map = this.generalStatisticDao.getGeneralStatistics(newStartDate, newEndDate);
		}

		List list = (List)map.get("result");
		List list_result = new ArrayList();
		int length = list.size();
		for (int i = 0; i < length; i++){
			Object [] objs = (Object[])list.get(i);
			GeneralStatistic generalStatistic = new GeneralStatistic();
			generalStatistic.setTableId(StatisticMethod.objectToString(objs[0])); //经验库ID
			generalStatistic.setIsBest(StatisticMethod.objectToInteger(objs[1])); //推荐知识数
			generalStatistic.setIsPublic(StatisticMethod.objectToInteger(objs[2])); //公开知识数
			generalStatistic.setGradeOne(StatisticMethod.objectToInteger(objs[3])); //一星知识数
			generalStatistic.setGradeTwo(StatisticMethod.objectToInteger(objs[4])); //二星知识数
			generalStatistic.setGradeThree(StatisticMethod.objectToInteger(objs[5])); //三星知识数
			generalStatistic.setGradeFour(StatisticMethod.objectToInteger(objs[6])); //四星知识数
			generalStatistic.setGradeFive(StatisticMethod.objectToInteger(objs[7])); //五星知识数
			generalStatistic.setReadCount(StatisticMethod.objectToInteger(objs[8])); //阅读的次数
			generalStatistic.setUseCount(StatisticMethod.objectToInteger(objs[9])); //引用的次数
			generalStatistic.setModifyCount(StatisticMethod.objectToInteger(objs[10])); //引用的次数
			list_result.add(generalStatistic);
		}
		map.put("result", list_result);
		return map;
	}
}
