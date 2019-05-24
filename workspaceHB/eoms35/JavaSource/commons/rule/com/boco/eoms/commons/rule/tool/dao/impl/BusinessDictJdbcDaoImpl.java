package com.boco.eoms.commons.rule.tool.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultReader;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.boco.eoms.commons.rule.tool.dao.IBusinessDictJdbcDao;
import com.boco.eoms.commons.rule.tool.exception.RuleToolJdbcException;
import com.boco.eoms.commons.rule.tool.model.LabelBean;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * May 10, 2007 11:02:56 AM
 * </p>
 * 
 * @author ��
 * @version 1.0
 *  
 */
public class BusinessDictJdbcDaoImpl extends JdbcDaoSupport implements
		IBusinessDictJdbcDao {

	public List findBusinessDict(String sql) throws RuleToolJdbcException {

		List list = getJdbcTemplate().query(sql,
				new RowMapperResultReader(new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						LabelBean labelBean = new LabelBean();
						labelBean.setName(rs.getString("name"));
						labelBean.setValue(rs.getObject("id"));
						return labelBean;
					}
				}));

		return list;
	}

}
