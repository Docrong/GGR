package com.boco.eoms.commons.db.bocopool;

// java standard library
import java.util.Vector;

public class ConnCloseThread extends Thread {

    private BocoConnection conn = null;

    private Vector closeConnections;

    public ConnCloseThread(BocoConnection con, Vector closeConns) {
        this.conn = con;
        this.closeConnections = closeConns;
    }

    public void run() {
        closeConnections.add(conn);
        conn.release();
        closeConnections.remove(conn);
    }
}