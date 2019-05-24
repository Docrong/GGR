/**
 * 
 */
package com.boco.eoms.commons.db.test;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.db.bocopool.BocoConnection;
import com.boco.eoms.commons.db.bocopool.ConnectionPool;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class ConnectionPoolTest extends ConsoleTestCase {
    ConnectionPool _objConnPool = null;

    /* (non-Javadoc)
     * @see com.boco.eoms.base.test.console.ConsoleTestCase#setUp()
     */
    protected void onSetUpInTransaction() throws Exception {
        _objConnPool = ConnectionPool.getInstance();
        super.setUp();
    }

    /**
     * 测试非等待方式获取数据库连接
     */
    public void testGetConnection1() throws Exception {
        BocoConnection _objConn = _objConnPool.getConnection();
        int _iTotalCounts = _objConnPool.getPool().getConnectionSize();
        int _iUsableCounts = _objConnPool.getPool().getUsableConnSize();
        assertNotNull(_objConn);
        assertEquals(_iTotalCounts, _iUsableCounts+1);

        _objConn.close();
    }

    /**
     * 测试等待方式获取数据库连接
     */
    public void testGetConnection2() throws Exception {
        int _iWaitTime = 1;
        BocoConnection _objConn = _objConnPool.getConnection(_iWaitTime);
        int _iTotalCounts = _objConnPool.getPool().getConnectionSize();
        int _iUsableCounts = _objConnPool.getPool().getUsableConnSize();
        assertNotNull(_objConn);
        assertEquals(_iTotalCounts, _iUsableCounts+1);

        _objConn.close();
    }

    /**
     * 测试关闭采用非等待方式获取的数据库连接
     */
    public void testCloseConnection1() throws Exception {
        BocoConnection _objConn = _objConnPool.getConnection();
        int _iTotalCounts = _objConnPool.getPool().getConnectionSize();
        int _iUsableCounts1 = _objConnPool.getPool().getUsableConnSize();
        assertEquals(_iTotalCounts, _iUsableCounts1+1);

        _objConn.close();
        int _iUsableCounts2 = _objConnPool.getPool().getUsableConnSize();
        assertEquals(_iTotalCounts, _iUsableCounts2);
    }

    /**
     * 测试关闭采用等待方式获取的数据库连接
     */
    public void testCloseConnection2() throws Exception {
        int _iWaitTime = 1;
        BocoConnection _objConn = _objConnPool.getConnection(_iWaitTime);
        int _iTotalCounts = _objConnPool.getPool().getConnectionSize();
        int _iUsableCounts1 = _objConnPool.getPool().getUsableConnSize();
        assertEquals(_iTotalCounts, _iUsableCounts1+1);

        _objConn.close();
        int _iUsableCounts2 = _objConnPool.getPool().getUsableConnSize();
        assertEquals(_iTotalCounts, _iUsableCounts2);
    }

}
