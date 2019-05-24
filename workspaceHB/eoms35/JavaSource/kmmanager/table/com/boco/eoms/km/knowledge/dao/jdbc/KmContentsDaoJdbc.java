package com.boco.eoms.km.knowledge.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;

import com.boco.eoms.base.dao.jdbc.BaseDaoJdbc;

public class KmContentsDaoJdbc extends BaseDaoJdbc {
	
	/**
	 * 保存知识内容
	 * 
	 * @param sql
	 * @param valueList
	 * @param dataTypeList
	 * @return
	 */
	public Integer insert(final String sql, final List valueList, final List dataTypeList) {
		ConnectionCallback callback = new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException, DataAccessException {
				SQLStatement statement = new SQLStatement(sql, valueList, dataTypeList);
				int count = statement.update(conn);
				statement.close();
				return new Integer(count);
			}
		};
		return (Integer) this.getJdbcTemplate().execute(callback);
	}

	/**
	 * 删除知识内容
	 * 
	 * @param sql
	 * @param valueList
	 * @param dataTypeList
	 * @return
	 */
	public Integer deleted(final String sql, final List valueList, final List dataTypeList) {
		ConnectionCallback callback = new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException, DataAccessException {
				SQLStatement statement = new SQLStatement(sql, valueList, dataTypeList);
				int count = statement.update(conn);
				statement.close();
				return new Integer(count);
			}
		};
		return (Integer) this.getJdbcTemplate().execute(callback);
	}

	/**
	 * 修改知识内容
	 * 
	 * @param sql
	 * @param valueList
	 * @param dataTypeList
	 * @return
	 */
	public Integer update(final String sql, final List valueList, final List dataTypeList) {
		ConnectionCallback callback = new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException, DataAccessException {
				SQLStatement statement = new SQLStatement(sql, valueList, dataTypeList);
				int count = statement.update(conn);
				statement.close();
				return new Integer(count);
			}
		};
		return (Integer) this.getJdbcTemplate().execute(callback);
	}

	/**
	 * 查看知识内容
	 * 
	 * @param sql
	 * @param valueList
	 * @param dataTypeList
	 * @return
	 */
	public Map view(final String sql, final List valueList, final List dataTypeList) {
		ConnectionCallback callback = new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException, DataAccessException {
				SQLStatement statement = new SQLStatement(sql, valueList, dataTypeList);
				Map rowMap = statement.executeQuery(conn);
				if(rowMap == null){
					return new HashMap();
				}
				return rowMap;
			}
		};
		return (Map) this.getJdbcTemplate().execute(callback);
	}

	// 分页查询知识条目列表
	public List list(final String sql, final List valueList, final List dataTypeList, final Integer curPage, final Integer pageSize) {
		ConnectionCallback callback = new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,DataAccessException {
				SQLStatement statement = new SQLStatement(sql, valueList, dataTypeList);				
				statement.setFirstRow(new Integer(pageSize.intValue() * curPage.intValue()));
				statement.setMaxRows(pageSize);
				List results = statement.list(conn);
				if(results == null){
					return new ArrayList();
				}
				return results;
			}
		};
		return (List) this.getJdbcTemplate().execute(callback);
	}

	
	// 分页查询知识条目列表
	public List listLib(final String sql, final List valueList, final List dataTypeList, final Integer curPage, final Integer pageSize, String startDate, String endDate) {
		ConnectionCallback callback = new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,DataAccessException {
				SQLStatement statement = new SQLStatement(sql, valueList, dataTypeList);				
				statement.setFirstRow(new Integer(pageSize.intValue() * curPage.intValue()));
				statement.setMaxRows(pageSize);
				List results = statement.list(conn);
				if(results == null){
					return new ArrayList();
				}
				return results;
			}
		};
		return (List) this.getJdbcTemplate().execute(callback);
	}

	
	
	public Integer count(final String sql, final List valueList, final List dataTypeList) {
		ConnectionCallback callback = new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException, DataAccessException {
				SQLStatement statement = new SQLStatement(sql, valueList, dataTypeList);
				int count = statement.count(conn);
				return new Integer(count);
			}
		};
		return (Integer) this.getJdbcTemplate().execute(callback);
	}
	
	/**
	 * 查询知识内容版本表
	 * 
	 * @param sql
	 * @param valueList
	 * @param dataTypeList
	 * @return
	 */
	public List listAllHistory(final String sql, final List valueList, final List dataTypeList) {
		ConnectionCallback callback = new ConnectionCallback() {
			public Object doInConnection(Connection conn) throws SQLException,DataAccessException {
				SQLStatement statement = new SQLStatement(sql, valueList, dataTypeList);
				List results = statement.listAllHistory(conn);
				if(results == null){
					return new ArrayList();
				}
				return results;
			}
		};
		return (List) this.getJdbcTemplate().execute(callback);
	}
}
