package com.boco.eoms.db.util;

import java.util.ArrayList;
import java.util.TimerTask;

import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.PropertyFile;
import com.boco.eoms.common.util.StaticMethod;

public class ConnCheckerScheduler extends TimerTask {
    private PropertyFile prop = PropertyFile.getInstance();

    ArrayList poolnames;

    public ConnCheckerScheduler() {
        String poolname = prop.getProperty("PoolNames", "BocoInformix");
        poolnames = StaticMethod.TokenizerString(poolname, " ");
        BocoLog.info(this, 18, "启动检查数据库池的定时检查器的定时器");
    }

    public void run() {
        try {
            for (int i = 0; i < poolnames.size(); i++) {
                com.boco.eoms.commons.db.bocopool.DBConnectionPool pool = ConnectionPool
                        .getInstance().getPool(
                                StaticMethod
                                        .nullObject2String(poolnames.get(i)));
                // if (!pool.getTimerExitFlag()) {
                pool.resetCheckTimer();
                BocoLog.debug(this, 25, poolnames.get(i) + "重置定时器ok");
                // }
                BocoLog.debug(this, 30, "检查" + poolnames.get(i)
                        + "数据库的定时检查器运行正常！");
            }
        } catch (Exception e) {
            BocoLog.error(this, 31, "检查数据库池的定时检查器运行报错！", e);
        }
    }

}