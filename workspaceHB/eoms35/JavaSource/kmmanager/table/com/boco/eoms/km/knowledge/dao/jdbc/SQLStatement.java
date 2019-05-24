package com.boco.eoms.km.knowledge.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.AssertionFailure;

import com.boco.eoms.km.core.hibernate.dialect.Dialect;
import com.boco.eoms.km.core.hibernate.engine.RowSelection;
import com.boco.eoms.km.knowledge.model.KmContents;

public class SQLStatement {

	private RowSelection selection = new RowSelection();

	private String sql = null;
	private List value = new ArrayList();
	private List type = new ArrayList();

	private PreparedStatement pstm = null;
	private ResultSet rest = null;

	public SQLStatement(final String sql, List value, List type)
			throws SQLException {
		this.sql = sql;
		this.value.addAll(value);
		this.type.addAll(type);
	}

	public void setFirstRow(Integer firstRow) {
		selection.setFirstRow(firstRow);
	}

	public void setMaxRows(Integer maxRows) {
		selection.setMaxRows(maxRows);
	}

	public int update(Connection conn) throws SQLException {
		pstm = conn.prepareStatement(sql);
		this.setValueList();
		int count = this.pstm.executeUpdate();
		pstm.close();
		return count;
	}

	public Map executeQuery(Connection conn)
			throws SQLException {
		Map rowMap = new HashMap();
		pstm = conn.prepareStatement(sql);
		this.setValueList();
		this.rest = this.pstm.executeQuery();
		if (rest.next()) {
			ResultSetMetaData rsd = rest.getMetaData();
			SQLResult sqlResult = new SQLResult(conn, rest, rsd);
			for (int i = 1; i <= rsd.getColumnCount(); i++) {
				String value = sqlResult.getObjectFormResult(i);
				String key = rsd.getColumnName(i).toUpperCase();
				rowMap.put(key, value);
			}
		}
		rest.close();
		pstm.close();
		return rowMap;
	}

	public int count(Connection conn) throws SQLException {
		int result = 0;
		pstm = conn.prepareStatement(sql);
		this.setValueList();
		this.rest = this.pstm.executeQuery();
		if (rest.next()) {
			result = rest.getInt(1);
		}
		rest.close();
		pstm.close();
		return result;
	}

	public List list(Connection conn) throws SQLException {

		final Dialect dialect = Dialect.getDialect();
		boolean useLimit = useLimit(selection, dialect);
		boolean hasFirstRow = getFirstRow(selection) > 0;
		boolean useOffset = hasFirstRow && useLimit && dialect.supportsLimitOffset();
		boolean scrollable = selection.getFirstRow().intValue() > 0 ? true : false;
		final int maxRows = hasMaxRows( selection ) ? selection.getMaxRows().intValue() : Integer.MAX_VALUE;
				
		if (useLimit) {
			sql = dialect.getLimitString(sql.trim(), 
					useOffset ? getFirstRow(selection) : 0, 
							getMaxOrLimit(selection, dialect));
		}

		if (scrollable) {
			this.pstm = conn.prepareStatement(this.sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
		} else {
			this.pstm = conn.prepareStatement(this.sql);
		}

		if (useLimit && dialect.addLimitParameters()) {
			bindLimitParameters(dialect);
		}

		this.setValueList();

		if (!useLimit)
			setMaxRows(this.selection);

		this.rest = this.pstm.executeQuery();

		if ( !dialect.supportsLimitOffset() || !useLimit( selection, dialect ) ) {
			advance(dialect);
		}

		final List results = new ArrayList();

		for (int count = 0; count < maxRows && rest.next(); count++) {
			KmContents kmContents = new KmContents();
			kmContents.setId(rest.getString("ID"));
			kmContents.setTableId(rest.getString("TABLE_ID"));
			kmContents.setThemeId(rest.getString("THEME_ID"));
			kmContents.setCreateUser(rest.getString("CREATE_USER"));
			kmContents.setCreateDept(rest.getString("CREATE_DEPT"));
			kmContents.setCreateTime(rest.getTimestamp("CREATE_TIME"));
			kmContents.setContentTitle(rest.getString("CONTENT_TITLE"));
			kmContents.setContentKeys(rest.getString("CONTENT_KEYS"));
			kmContents.setContentStatus(rest.getString("CONTENT_STATUS"));
			kmContents.setAuditFlag(rest.getString("AUDIT_FLAG"));
			kmContents.setRolestrFlag(rest.getString("ROLESTR_FLAG"));
			kmContents.setLevelFlag(rest.getString("LEVEL_FLAG"));
			kmContents.setIsBest(rest.getString("IS_BEST"));
			kmContents.setIsPublic(rest.getString("IS_PUBLIC"));
			kmContents.setGradeOne(new Integer(rest.getInt("GRADE_ONE")));
			kmContents.setGradeTwo(new Integer(rest.getInt("GRADE_TWO")));
			kmContents.setGradeThree(new Integer(rest.getInt("GRADE_THREE")));
			kmContents.setGradeFour(new Integer(rest.getInt("GRADE_FOUR")));
			kmContents.setGradeFive(new Integer(rest.getInt("GRADE_FIVE")));
			kmContents.setReadCount(new Integer(rest.getInt("READ_COUNT")));
			kmContents.setUseCount(new Integer(rest.getInt("USE_COUNT")));
			kmContents.setModifyCount(new Integer(rest.getInt("MODIFY_COUNT")));
			results.add(kmContents);
		}

		rest.close();
		pstm.close();
		return results;
	}

	public List listAllHistory(Connection conn) throws SQLException {
		
		final List results = new ArrayList();
		
		this.pstm = conn.prepareStatement(this.sql);
		this.setValueList();
		this.rest = this.pstm.executeQuery();

		while (rest.next()) {
			KmContents kmContents = new KmContents();
			kmContents.setId(rest.getString("ID"));
			kmContents.setTableId(rest.getString("TABLE_ID"));
			kmContents.setThemeId(rest.getString("THEME_ID"));
			
			String MODIFY_USER = rest.getString("MODIFY_USER");
			if( MODIFY_USER == null || MODIFY_USER.equals("")){
				kmContents.setModifyUser(rest.getString("CREATE_USER"));
				kmContents.setModifyDept(rest.getString("CREATE_DEPT"));
				kmContents.setModifyTime(rest.getTimestamp("CREATE_TIME"));				
			}
			else{
				kmContents.setModifyUser(rest.getString("MODIFY_USER"));
				kmContents.setModifyDept(rest.getString("MODIFY_DEPT"));
				kmContents.setModifyTime(rest.getTimestamp("MODIFY_TIME"));				
			}
			kmContents.setVersion(new Integer(rest.getInt("VERSION")));	
			results.add(kmContents);
		}

		rest.close();
		pstm.close();
		return results;
	}

	private void setValueList() throws SQLException {
		if (value.size() != type.size()) {
			throw new SQLException("参数列表与数据类型数组长度不符");
		}
		int length = value.size();
		for (int i = 0; i < length; i++) {
			int index = i + 1;
			Object obj = value.get(i);
			Integer sqlType = (Integer)type.get(i);
			setObject(index, obj, sqlType);
		}
	}

	private void setObject(int index, Object obj, Integer sqlType)
			throws SQLException {
		if (obj == null) {
			this.pstm.setNull(index, sqlType.intValue());
		} else if (obj instanceof java.sql.Clob) {
			java.sql.Clob clob = (java.sql.Clob) obj;
			this.pstm.setCharacterStream(index, clob.getCharacterStream(), (int) clob.length());
		} else if (obj instanceof java.sql.Blob) {
			java.sql.Blob blob = (java.sql.Blob) obj;
			this.pstm.setBinaryStream(index, blob.getBinaryStream(), (int) blob.length());
		} else {
			this.pstm.setObject(index, obj, sqlType.intValue());
		}
	}

	public void close() throws SQLException {
		if (this.pstm != null) {
			this.pstm.close();
			this.pstm = null;
		}
	}

	private void bindLimitParameters() {
		if (true) {
			this.value.add(new Integer(1));
			this.type.add(new Integer(Types.INTEGER));
		}
	}

	/**
	 * Bind parameters needed by the dialect-specific LIMIT clause
	 */
	private void bindLimitParameters(Dialect dialect) throws SQLException {
		if (!hasMaxRows(this.selection)) {
			throw new AssertionFailure("no max results set");
		}
		int firstRow = getFirstRow(selection);
		
		int lastRow = getMaxOrLimit(selection, dialect);
		
		this.value.add(new Integer(lastRow));
		this.type.add(new Integer(Types.INTEGER));
		
		boolean hasFirstRow = firstRow > 0 && dialect.supportsLimitOffset();
		if (hasFirstRow) {
			this.value.add(new Integer(firstRow));
			this.type.add(new Integer(Types.INTEGER));
		}
	}

	private static boolean useLimit(final RowSelection selection, final Dialect dialect) {
		return dialect.supportsLimit() && hasMaxRows(selection);
	}

	private static boolean hasMaxRows(RowSelection selection) {
		return selection != null && selection.getMaxRows() != null;
	}

	private static int getFirstRow(RowSelection selection) {
		if (selection == null || selection.getFirstRow() == null) {
			return 0;
		} else {
			return selection.getFirstRow().intValue();
		}
	}

	private static int getMaxOrLimit(final RowSelection selection,
			final Dialect dialect) {
		final int firstRow = getFirstRow(selection);
		final int lastRow = selection.getMaxRows().intValue();
		if (dialect.useMaxForLimit()) {
			return lastRow + firstRow;
		} else {
			return lastRow;
		}
	}

	private void setMaxRows(final RowSelection selection) throws SQLException {
		if (hasMaxRows(selection)) {
			this.pstm.setMaxRows(selection.getMaxRows().intValue() + getFirstRow(selection));
		}
	}

	private void advance(final Dialect dialect) throws SQLException {
		final int firstRow = getFirstRow(this.selection);
		if (firstRow != 0) {
			if (dialect.isScrollableResultSetsEnabled()) {
				// we can go straight to the first required row
				this.rest.absolute(firstRow);
			} else {
				// we need to step through the rows one row at a time (slow)
				for (int m = 0; m < firstRow; m++)
					rest.next();
			}
		}
	}
}
