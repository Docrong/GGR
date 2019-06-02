package com.boco.eoms.km.core.hibernate.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.boco.eoms.km.core.hibernate.Query;
import com.boco.eoms.km.core.hibernate.ScrollMode;
import com.boco.eoms.km.core.hibernate.dialect.Dialect;
import com.boco.eoms.km.core.hibernate.engine.RowSelection;

public class AbstractQueryImpl implements Query {
	
	private static final Log log = LogFactory.getLog( AbstractQueryImpl.class );

	private final String queryString;
	private RowSelection selection;
	final Connection conn = null;
	
	private List values = new ArrayList(4);
	private List types = new ArrayList(4);

	public AbstractQueryImpl(String queryString) {
		this.queryString = queryString;
		this.selection = new RowSelection();
	}

	public Query setFirstResult(int firstResult) {
		selection.setFirstRow( new Integer(firstResult) );
		return this;
	}

	public Query setMaxResults(int maxResults) {
		selection.setMaxRows( new Integer(maxResults) );
		return this;
	}

	public Query setParameterList(List values, List types) {
		this.values = values;
		this.types = types;
		return this;
	}

	public List list() throws SQLException {
		final int maxRows = hasMaxRows( selection ) ? selection.getMaxRows().intValue() : Integer.MAX_VALUE;
		final PreparedStatement st = this.prepareQueryStatement();
		final ResultSet rs = getResultSet(st);
		final List results = new ArrayList();

		try {
			if ( log.isTraceEnabled() ) log.trace( "processing result set" );
			int count;

			for ( count = 0; count < maxRows && rs.next(); count++ ) {
				if ( log.isTraceEnabled() ) log.debug("result set row: " + count);
				Object result = null;
				results.add( result );				
			}

			if ( log.isTraceEnabled() ) {
				log.trace( "done processing result set (" + count + " rows)" );
			}
		}
		finally {
			System.out.println("list()");
		}

		return results;
	}

	private static boolean hasMaxRows(RowSelection selection) {
		return selection != null && selection.getMaxRows() != null;
	}

	/**
	 * Obtain a <tt>PreparedStatement</tt> with all parameters pre-bound.
	 * Bind JDBC-style <tt>?</tt> parameters, named parameters, and
	 * limit parameters.
	 */
	protected final PreparedStatement prepareQueryStatement() throws SQLException {
		String sql = null;
		final Dialect dialect = Dialect.getDialect();
		boolean useLimit = useLimit( selection, dialect );
		boolean hasFirstRow = getFirstRow( selection ) > 0;
		boolean useOffset = hasFirstRow && useLimit && dialect.supportsLimitOffset();
		ScrollMode scrollMode = ScrollMode.SCROLL_INSENSITIVE;
		boolean scroll = true;

		if ( useLimit ) {
			sql = dialect.getLimitString( 
					sql.trim(),
					useOffset ? getFirstRow(selection) : 0, 
					getMaxOrLimit(selection, dialect) 
				);
		}

		PreparedStatement st = prepareQueryStatement( sql, scroll, scrollMode );

		try {
			if ( !useLimit ) setMaxRows( st, selection );

			if ( selection != null ) {
				if ( selection.getTimeout() != null ) {
					// 将驱动程序等待 Statement 对象执行的秒数设置为给定秒数。
					st.setQueryTimeout( selection.getTimeout().intValue() );
				}
				if ( selection.getFetchSize() != null ) {
					// 为 JDBC 驱动程序提供关于需要更多行时应该从数据库获取的行数的提示。
					st.setFetchSize( selection.getFetchSize().intValue() );
				}
			}
		}
		catch ( SQLException sqle ) {
			throw sqle;
		}

		return st;
	}

	/**
	 * Should we pre-process the SQL string, adding a dialect-specific
	 * LIMIT clause.
	 */
	private static boolean useLimit(final RowSelection selection, final Dialect dialect) {
		return dialect.supportsLimit() && hasMaxRows( selection );
	}

	private static int getFirstRow(RowSelection selection) {
		if ( selection == null || selection.getFirstRow() == null ) {
			return 0;
		}
		else {
			return selection.getFirstRow().intValue();
		}
	}

	/**
	 * Some dialect-specific LIMIT clauses require the maximium last row number,
	 * others require the maximum returned row count.
	 */
	private static int getMaxOrLimit(final RowSelection selection, final Dialect dialect) {
		final int firstRow = getFirstRow( selection );
		final int lastRow = selection.getMaxRows().intValue();
		if ( dialect.useMaxForLimit() ) {
			return lastRow + firstRow;
		}
		else {
			return lastRow;
		}
	}

	public PreparedStatement prepareQueryStatement(String sql, boolean scrollable, ScrollMode scrollMode) throws SQLException{
		PreparedStatement ps = getPreparedStatement(this.conn, sql, scrollable, scrollMode );
		return ps;
	}

	private PreparedStatement getPreparedStatement(final Connection conn, final String sql, final boolean scrollable, final ScrollMode scrollMode) throws SQLException {
		log.trace("preparing statement");

		PreparedStatement result;
		if (scrollable) {
			result = conn.prepareStatement( sql, scrollMode.toResultSetType(), ResultSet.CONCUR_READ_ONLY );
		}
		else {
			result = conn.prepareStatement(sql);
		}

		return result;
	}

	/**
	 * Use JDBC API to limit the number of rows returned by the SQL query if necessary
	 * 将任何 ResultSet 对象都可以包含的最大行数限制设置为给定数。如果超过了该限制，则安静地撤消多出的行。
	 */
	private void setMaxRows(final PreparedStatement st, final RowSelection selection) throws SQLException {
		if ( hasMaxRows( selection ) ) {
			st.setMaxRows( selection.getMaxRows().intValue() + getFirstRow( selection ) );
		}
	}


	/**
	 * Fetch a <tt>PreparedStatement</tt>, call <tt>setMaxRows</tt> and then execute it,
	 * advance to the first result and return an SQL <tt>ResultSet</tt>
	 */
	protected final ResultSet getResultSet(final PreparedStatement st) throws SQLException {	
		ResultSet rs = null;
		try {
			final Dialect dialect = Dialect.getDialect();

			if ( !dialect.supportsLimitOffset() || !useLimit( selection, dialect ) ) {
				advance( rs, selection );
			}

			return rs;
		}
		catch ( SQLException sqle ) {
			throw sqle;
		}
	}

	/**
	 * Advance the cursor to the first required row of the <tt>ResultSet</tt>
	 */
	private void advance(final ResultSet rs, final RowSelection selection) throws SQLException {
		final int firstRow = getFirstRow( selection );
		if ( firstRow != 0 ) {
			final Dialect dialect = Dialect.getDialect();
			if ( dialect.isScrollableResultSetsEnabled() ) {
				// we can go straight to the first required row
				rs.absolute( firstRow );
			}
			else {
				// we need to step through the rows one row at a time (slow)
				for ( int m = 0; m < firstRow; m++ ) rs.next();
			}
		}
	}
}
