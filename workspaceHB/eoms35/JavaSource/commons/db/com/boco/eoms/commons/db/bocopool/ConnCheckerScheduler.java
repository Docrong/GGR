package com.boco.eoms.commons.db.bocopool;

// java standard library
import java.util.TimerTask;
import java.util.ArrayList;

// eoms classes
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.db.util.PropertyFile;
import com.boco.eoms.commons.loging.BocoLog;

public class ConnCheckerScheduler extends TimerTask {

    private PropertyFile prop = PropertyFile.getInstance();

    ArrayList poolnames;

    public ConnCheckerScheduler() {
        String poolname = prop.getProperty("PoolNames", "BocoInformix");
        poolnames = StaticMethod.TokenizerString(poolname, " ");
        BocoLog.info(this, "启动定时数据库连接池检查器的定时器");
    }

    public void run() {
        try {
            for (int i = 0; i < poolnames.size(); i++) {
                DBConnectionPool pool = ConnectionPool.getInstance().getPool(
                        StaticMethod.nullObject2String(poolnames.get(i)));
                pool.resetCheckTimer();
                BocoLog.info(this, poolnames.get(i) + "重置定时器成功");
                BocoLog.info(this, "检查" + poolnames.get(i) + "定时数据库连接池运行正常");
            }
        }
        catch (Exception e) {
            BocoLog.error(this, "定时数据库连接池检查器运行错误: " + e.getMessage());
        }
    }

}