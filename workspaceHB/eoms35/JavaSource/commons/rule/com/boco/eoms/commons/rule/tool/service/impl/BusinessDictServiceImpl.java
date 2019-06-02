package com.boco.eoms.commons.rule.tool.service.impl;

import java.util.List;

import com.boco.eoms.commons.rule.tool.dao.IBusinessDictJdbcDao;
import com.boco.eoms.commons.rule.tool.exception.RuleToolJdbcException;
import com.boco.eoms.commons.rule.tool.service.IBusinessDictService;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * May 10, 2007 2:00:16 PM
 * </p>
 * 
 * @author ��
 * @version 1.0
 *  
 */
public class BusinessDictServiceImpl implements IBusinessDictService {

	private IBusinessDictJdbcDao businessDictJdbcDao;

	public void setBusinessDictJdbcDao(IBusinessDictJdbcDao businessDictJdbcDao) {
		this.businessDictJdbcDao = businessDictJdbcDao;
	}

	public List findBusinessDictByModuleId(String sql, Object typeId)
			throws RuleToolJdbcException {
		String sqlStr = "";
		// 判断类型ID类型，若为整型、长整型则sql不加单引号''
		if (typeId instanceof java.lang.Integer
				|| typeId instanceof java.lang.Long) {
			sqlStr = sql + "" + typeId;
		}
		// 若类型ID为字符串，则sql加入单引号
		else if (typeId instanceof java.lang.String) {
			sqlStr = sql + "'" + typeId + "'";
		}
		return this.businessDictJdbcDao.findBusinessDict(sqlStr);
	}

	public List findBusinessDictForModule(String sql)
			throws RuleToolJdbcException {
		return this.businessDictJdbcDao.findBusinessDict(sql);
	}

}
