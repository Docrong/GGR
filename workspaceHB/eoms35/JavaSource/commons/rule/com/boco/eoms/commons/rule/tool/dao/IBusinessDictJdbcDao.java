package com.boco.eoms.commons.rule.tool.dao;

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
 * May 10, 2007 10:27:18 AM
 * </p>
 * 
 * @author ��
 * @version 1.0
 *  
 */
public interface IBusinessDictJdbcDao {

	/**
	 * ȡ�����ֵ�moudle
	 * 
	 * @param sql
	 * @return
	 * @throws RuleToolJdbcException
	 */
	public List findBusinessDict(String sql) throws RuleToolJdbcException;

}
