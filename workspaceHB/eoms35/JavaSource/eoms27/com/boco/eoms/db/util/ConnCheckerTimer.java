package com.boco.eoms.db.util;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 *
 * @author
 * @version 1.0
 */

import com.boco.eoms.common.log.BocoLog;

class ConnCheckerTimer extends Thread        //定时检查器
{

    private DBConnectionPool pool;
    private long m_timer = 5000;    //定时器休眠长度
    private boolean ifExit = true;

    ConnCheckerTimer(DBConnectionPool pool, long time) {
        this.pool = pool;
        this.m_timer = time;
    }

    public void run() {
        try {
            //如果有省市出现连接池的性能问题，就将以下的注释去掉，进行分析
            while (ifExit) {
                //BocoLog.debug(this,40,pool.getPoolName()+"定时器执行开始"+ifExit);
                try {
                    sleep(m_timer);
                } catch (Exception e) {
                    BocoLog.error(this, 29, pool.getPoolName() + "定时器有问题sleep", e);
                    ifExit = false;
                }
                pool.checkDBPool();
                //BocoLog.debug(this,40,pool.getPoolName()+"定时器checkDBPool完成"+ifExit);
            }
        } catch (Exception ex) {
            BocoLog.error(this, 33, pool.getPoolName() + "定时器有问题", ex);
            ifExit = false;
        }
        //BocoLog.debug(this,40,pool.getPoolName()+"定时器执行完成了！"+ifExit);
        ifExit = false;
    }

    public void exit() {
        this.ifExit = false;
    }

    public boolean getFlag() {
        return this.ifExit;
    }


}