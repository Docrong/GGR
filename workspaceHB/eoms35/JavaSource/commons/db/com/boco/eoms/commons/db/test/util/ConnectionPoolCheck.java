/**
 * 
 */
package com.boco.eoms.commons.db.test.util;

/**
 * @author Sandy.wei
 * @version 3.5
 */
import java.util.Iterator;

// eoms classes
import com.boco.eoms.commons.db.bocopool.ConnectionPool;
import com.boco.eoms.commons.db.bocopool.BocoConnection;
import com.boco.eoms.commons.db.bocopool.DBConnectionPool;
import com.boco.eoms.commons.loging.BocoLog;

public class ConnectionPoolCheck extends Thread {

    private DBConnectionPool pool;

    private long m_timer = 2000;

    public ConnectionPoolCheck(DBConnectionPool pool, long time) {
        this.pool = pool;
        this.m_timer = time;
    }

    public void run() {
        try {
            while (true) {
                try {
                    sleep(m_timer);
                }
                catch (Exception e) {
                }
                displayAll();
            }
        }
        catch (Exception ex) {
        }
    }

    private void displayAll() {
        int iIndex = 1;
        int iCount = 0;

        Iterator _objConn = null;
        if (pool == null) {

            _objConn = ConnectionPool.getInstance().getPool()
                    .getConnectionIterator();
        }
        else {
            _objConn = pool.getConnectionIterator();
        }

        while (_objConn.hasNext()) {
            iCount++;
            BocoConnection _objBocoConn = (BocoConnection) _objConn.next();
            if (!_objBocoConn.isUse()) {
                BocoLog.debug(this, "Index[" + iIndex + "]"
                        + _objBocoConn.toString() + " not be using!");
            }
            else {
                BocoLog.debug(this, "Index[" + iIndex + "]"
                        + _objBocoConn.toString() + " be using!");
            }

            printConnectionProperties(_objBocoConn);
            iIndex++;
        }

        BocoLog.debug(this, "There [" + iCount
                + "] connection in current pool!");
    }

    private void printConnectionProperties(BocoConnection _objBocoConn) {
        long _lTimestamp = _objBocoConn.getTimeStamp();
        int _iUseCount = _objBocoConn.getConnectionUseCount();
        boolean _bIS = _objBocoConn.isFlagUseLongActiveTime();
        BocoLog.debug(this, "TimeStamp: [" + _lTimestamp
                + "], ConnectionUseCount: [" + _iUseCount
                + "], FlagUseLongActiveTime: [" + _bIS + "]");
    }
}