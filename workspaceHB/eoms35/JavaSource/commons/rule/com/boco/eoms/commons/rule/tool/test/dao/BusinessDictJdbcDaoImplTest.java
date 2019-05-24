package com.boco.eoms.commons.rule.tool.test.dao;

import java.util.List;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.rule.tool.dao.IBusinessDictJdbcDao;
import com.boco.eoms.commons.rule.tool.exception.RuleToolJdbcException;

/**
 * 
 * <p>
 * Title:业务字典dao测试类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 14, 2007 4:35:44 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class BusinessDictJdbcDaoImplTest extends ConsoleTestCase {


	public void testFindBusinessDict() {
		IBusinessDictJdbcDao dao = (IBusinessDictJdbcDao) this
				.getBean("businessDictJdbcDao");
		List list = null;
		try {
			list = dao
					.findBusinessDict("select t.dictid as id,t.dictname as name from taw_system_dicttype t");
		} catch (RuleToolJdbcException e) {
			fail(e.getMessage());
		}
		assertNotNull(list);

		assertFalse(list.isEmpty());

	}

}
