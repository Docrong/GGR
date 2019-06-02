package com.boco.eoms.commons.statistic.base.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.boco.eoms.commons.statistic.base.config.model.FieldDefine;
import com.boco.eoms.commons.statistic.base.config.model.KpiDefine;
import com.boco.eoms.commons.statistic.base.config.model.QueryDefine;
import com.boco.eoms.commons.statistic.base.dao.IStatDetailDAO;
import com.boco.eoms.commons.statistic.base.reference.StaticMethod;

public abstract class DetailDAOBaseImpl extends JdbcDaoSupport implements IStatDetailDAO {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	protected void populate(Object bean, ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();
		int ncolumns = metaData.getColumnCount();

		HashMap properties = new HashMap();
		String sTemp = "";

		// Scroll to next record and pump into hashmap
		for (int i = 1; i <= ncolumns; i++) {
			 //System.out.println(metaData.getColumnName(i) +":"+
			 //metaData.getColumnTypeName(i));
			if (metaData.getColumnTypeName(i).toLowerCase().equals("date")
					|| metaData.getColumnTypeName(i).toLowerCase().equals(
							"datetime")
					|| metaData.getColumnTypeName(i).toLowerCase().equals(
							"datetime year to second")
					|| metaData.getColumnTypeName(i).toLowerCase().equals(
							"number")) {
				sTemp = StaticMethod.null2String(rs.getString(i)).replaceAll(
						"\\.0", "");
			} else {
				sTemp = StaticMethod.null2String(rs.getString(i)).trim();
			}
			properties.put(metaData.getColumnName(i).toLowerCase(), sTemp);
		}
		// Set the corresponding properties of our bean
		try {
			BeanUtils.populate(bean, properties);
		} catch (InvocationTargetException ite) {
			throw new SQLException("BeanUtils.populate threw " + ite.toString());
		} catch (IllegalAccessException iae) {
			throw new SQLException("BeanUtils.populate threw " + iae.toString());
		}
	}

	/**
	 * 样例
	 * 根据特殊情况,处理详细列表的VO, 比如某个字段需要复杂运算出来
	 * @param sqlParams 统计首页输入的参数
	 * @param summaryParams 具体一行数据的 汇总字段数值.
	          比如按部门统计,那么查看一行某一个字段的详细列表时,需要传入这行的部门值
	 * @return
	 */
	public List extendDealVOList(List list,KpiDefine kpiDefine,QueryDefine queryDefine,
			FieldDefine fieldDefine,Map sqlParams,Map summaryParams){
//  做一些特殊处理		
		return list;
	}
}
