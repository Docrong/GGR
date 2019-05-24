package com.boco.eoms.commons.rule.tool.service;

import java.util.List;

import com.boco.eoms.commons.rule.tool.exception.RuleToolJdbcException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * May 10, 2007 1:59:55 PM
 * </p>
 * 
 * @author ��
 * @version 1.0
 * 
 */
public interface IBusinessDictService {
	/**
	 * ȡ�����ֵ�moudle
	 * 
	 * @param sql
	 *            sql���
	 * @return
	 * @throws RuleToolJdbcException
	 */
	public List findBusinessDictForModule(String sql)
			throws RuleToolJdbcException;

	/**
	 * ȡmoudle�ڵ��ֵ�id��name
	 * 
	 * @param sql
	 *            sql���
	 * @param moduleId
	 *            ģ��id
	 * @return
	 * @throws RuleToolJdbcException
	 */
	public List findBusinessDictByModuleId(String sql, Object typeId)
			throws RuleToolJdbcException;
}
