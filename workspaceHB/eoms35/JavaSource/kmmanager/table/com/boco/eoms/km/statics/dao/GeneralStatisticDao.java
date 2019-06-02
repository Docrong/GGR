package com.boco.eoms.km.statics.dao;

import java.util.Date;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;

public interface GeneralStatisticDao  extends Dao{

	/**
	 * 知识贡献统计（部门）- Informix 数据库
	 * @param startDate 统计开始时间
	 * @param endDate 统计结束时间
	 * @return map中total为条数,result(list) curPage页的记录
	 */
    public Map getGeneralStatistics(final Date startDate, final Date endDate);
	/**
	 * 知识贡献统计（部门）- Oracle 数据库

	 * @param startDate 统计开始时间
	 * @param endDate 统计结束时间
	 * @return map中total为条数,result(list) curPage页的记录
	 */

    public Map getGeneralStatistics(final String startDate, final String endDate);

}
