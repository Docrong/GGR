package com.boco.eoms.km.core.hibernate;

import java.sql.SQLException;
import java.util.List;

public interface Query {

    /**
     * Set the first row to retrieve. If not set, rows will be
     * retrieved beginnning from row <tt>0</tt>.
     *
     * @param firstResult a row number, numbered from <tt>0</tt>
     */
    public Query setFirstResult(int firstResult);

    /**
     * Set the maximum number of rows to retrieve. If not set,
     * there is no limit to the number of rows retrieved.
     *
     * @param maxResults the maximum number of rows
     */
    public Query setMaxResults(int maxResults);

    public Query setParameterList(List values, List types);

    /**
     * Return the query results as a <tt>List</tt>. If the query contains
     * multiple results pre row, the results are returned in an instance
     * of <tt>Object[]</tt>.
     *
     * @return the result list
     * @throws HibernateException
     */
    public List list() throws SQLException;


}
