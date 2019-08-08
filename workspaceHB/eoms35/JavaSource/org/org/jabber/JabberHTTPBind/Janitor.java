package org.jabber.JabberHTTPBind;

import java.io.PrintStream;
import java.util.Enumeration;

public class Janitor
        implements Runnable {
    public static final int SLEEPMILLIS = 1000;
    private boolean keep_running = true;

    public void run() {
        while (this.keep_running) {
            for (Enumeration e = Session.getSessions(); e.hasMoreElements(); ) {
                Session sess = (Session) e.nextElement();

                if (System.currentTimeMillis() - sess.getLastActive() > 60000L) {
                    System.err.println("Session timed out: " + sess.getSID());
                    sess.terminate();
                }
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public void stop() {
        this.keep_running = false;
    }
}