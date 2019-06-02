package com.boco.eoms.commons.db.bocopool;

/**
 * Title: Description: Copyright: Copyright (c) 2002 Company:
 * 
 * @author
 * @version 1.0
 */

// eoms class
import com.boco.eoms.commons.loging.BocoLog;

public class ConnCheckerTimer extends Thread { // 定时检查器��ʱ�����

    private DBConnectionPool pool;

    private long m_timer = 5000; // 定时器休眠时间��ʱ�����߳���

    private boolean ifExit = true;

    public ConnCheckerTimer(DBConnectionPool pool, long time) {
        this.pool = pool;
        this.m_timer = time;
    }

    public void run() {
        try {
            while (ifExit) {
                BocoLog.debug(this, pool.getPoolName() + "定时器开始执行" + ifExit);

                try {
                    sleep(m_timer);
                }
                catch (Exception e) {
                    BocoLog.error(this, pool.getPoolName() + "定时器执行休眠出错: "
                            + e.getMessage());
                    ifExit = false;
                }
                pool.checkDBPool();
                BocoLog.debug(this, pool.getPoolName()
                        + "定时器调用连接池的checkDBPool()完成" + ifExit);
            }
        }
        catch (Exception ex) {
            BocoLog.error(this, pool.getPoolName() + "定时器有误: " + ex.getMessage());
            ifExit = false;
        }
        ifExit = false;
    }

    public void exit() {
        this.ifExit = false;
    }

    public boolean getFlag() {
        return this.ifExit;
    }

}