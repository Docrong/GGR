/**
 * 
 */
package com.boco.eoms.commons.db.test;

// eoms classes
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.db.test.util.ConnectionPoolCheck;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class PoolMainTest extends Thread {

    private int _iUserCounts;

    private long _lHoldConnTime;

    private long _lInervalTime;

    /**
     * 
     * @param int _iUserCounts, Thread counts.
     * @prama long _lHoldConnTime, Use connection for how long time.
     * @param long _lInervalTime, How long time to create one thread.
     */
    public static void main(String[] args) {

        PoolMainTest _objPoolMain = null;

        if (args.length == 3) {
            _objPoolMain = new PoolMainTest();
            _objPoolMain._iUserCounts = (new Integer(args[0])).intValue();
            _objPoolMain._lHoldConnTime = (new Long(args[1])).longValue();
            _objPoolMain._lInervalTime = (new Long(args[2])).longValue();
            _objPoolMain.exec();
        }
        else {
            BocoLog.info(null, "Input parameter numbers error, should be three!");
        }

    }

    private void exec() {
        ConnectionPoolCheck _obj = new ConnectionPoolCheck(null, 2000);
        _obj.start();

        int iCount = 0;
        while (iCount < _iUserCounts) {
            BocoPoolTest _objBocoPool1 = new BocoPoolTest(_lHoldConnTime);
            _objBocoPool1.start();

            waitfor(_lInervalTime);
            iCount++;
        }
    }

    public void waitfor(long _ltime) {
        try {
            sleep(_ltime);
        }
        catch (InterruptedException e) {
            BocoLog.error(this, "Thread: [" + this.toString() + "], error message: ["
                    + e.getMessage() + "]");
        }
    }
}
