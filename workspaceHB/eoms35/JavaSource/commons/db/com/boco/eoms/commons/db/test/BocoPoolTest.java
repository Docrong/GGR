/**
 * 
 */
package com.boco.eoms.commons.db.test;

// eoms library
import com.boco.eoms.commons.db.bocopool.BocoConnection;
import com.boco.eoms.commons.db.bocopool.ConnectionPool;
import com.boco.eoms.commons.db.bocopool.DBConnectionPool;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class BocoPoolTest extends Thread {

    DBConnectionPool _objConnPool = null;

    BocoConnection _objConn = null;

    long _lWaitTimetoRelease;

    /**
     * @param waitTimetoRelease
     */
    public BocoPoolTest(long waitTimetoRelease) {
        super();
        _lWaitTimetoRelease = waitTimetoRelease;
    }

    /**
     * @return the _lWaitTimetoRelease
     */
    public long get_lWaitTimetoRelease() {
        return _lWaitTimetoRelease;
    }

    /**
     * @param waitTimetoRelease
     *            the _lWaitTimetoRelease to set
     */
    public void set_lWaitTimetoRelease(long waitTimetoRelease) {
        _lWaitTimetoRelease = waitTimetoRelease;
    }

    private void getOneConnection() {
        _objConnPool = ConnectionPool.getInstance().getPool();
        _objConn = _objConnPool.getConnection();
        BocoLog.debug(this, "Thread: [" + this.toString() + "], get Connection: ["
                    + _objConn.toString() + "]");
    }

    private void releaseConnection() {
        _objConn.close();

        BocoLog.debug(this, "Thread: [" + this.toString() + "], Close Connection: ["
                    + _objConn.toString() + "]");

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Thread#run()
     */
    public void run() {
        getOneConnection();

        try {
            sleep(_lWaitTimetoRelease);
        }
        catch (InterruptedException e) {
            BocoLog.error(this, "Thread: [" + this.toString() + "], error message: ["
                    + e.getMessage() + "]");
        }

        releaseConnection();
    }

}
