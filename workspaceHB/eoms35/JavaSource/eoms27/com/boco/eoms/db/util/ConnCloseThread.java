package com.boco.eoms.db.util;

import java.util.Vector;
import com.boco.eoms.common.log.BocoLog;
public class ConnCloseThread extends Thread {

  private BocoConnection conn = null;
  private Vector closeConnections ;

  public ConnCloseThread(BocoConnection con,Vector closeConns) {
    this.conn = con;
    this.closeConnections = closeConns;
  }

  public void run() {
    closeConnections.add(conn);
    //如果有省市出现连接池的性能问题，就将以下的注释去掉，进行分析
    //BocoLog.trace(this,13,"关闭连接"+conn.getTimeStamp()+"开始");
    conn.release();
    //BocoLog.trace(this,15,"关闭连接"+conn.getTimeStamp()+"结束");
    closeConnections.remove(conn);
  }
}