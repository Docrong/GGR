package com.boco.eoms.common.util;

/**
 * <p>Title:  eoms 系统的log日志类</p>
 * <p>Description: 通过PropertyFile类读取日志的相关信息（如日志文件的路径等信息）</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: boco</p>
 * @author boco mis
 * @update by dudajiang 2003-4-9
 * @version 1.0
 */

import java.io.*;
import java.util.*;
import java.text.*;

public class LogMan {

    private static String MAINLOG = "exec" ;
    private static LogMan instance = null;
    private String LOG_ROOT = null;
    private static FileWriter log = null;
    private static SimpleDateFormat SDF = null;
    private PropertyFile prop = PropertyFile.getInstance() ;
    private static Calendar lastday = null ;
    private static String prep = null ;


    public static synchronized LogMan getInstance() {
      if (instance == null) {
        instance = new LogMan(MAINLOG);
      }
      return instance;
    }

    public static synchronized LogMan getInstance(String pre) {
      if (instance == null) {
        instance = new LogMan(pre);
      }
      return instance ;
    }

    private LogMan(String prep) {

        this.prep = prep ;
        LOG_ROOT = prop.getProperty("LOGFILE_ROOT") ;
        //FileManage.createDir(LOG_ROOT) ;
        SDF = new SimpleDateFormat(prop.getProperty("LOG_FORMAT"), Locale.TRADITIONAL_CHINESE);
        log = getFileWriter(prep);
    }

    private FileWriter getFileWriter(String prep) {
      try {

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        String LOGFILE = LOG_ROOT +File.separator+prep + "_" + add0(today.get(Calendar.YEAR), 4) +
                         add0(today.get(Calendar.MONTH) + 1, 2) +
                         add0(today.get(Calendar.DAY_OF_MONTH), 2) + ".log";

        lastday = today;

        FileWriter tmplog = new FileWriter(LOGFILE , true);
        return tmplog;

      } catch (Exception e) {
        return null;
      }

    }

    public synchronized void writeLog(Object obj)   {

        writeLog("NunClass" , obj)  ;
    }

    public synchronized void writeLog(String className, Object obj)  {

  	String debugOn = prop.getProperty( "DEBUG_MODE");
        try {
          java.util.Date now = Calendar.getInstance().getTime();

          Calendar today = Calendar.getInstance();
          today.set(Calendar.HOUR, 0);
          today.set(Calendar.MINUTE, 0);
          today.set(Calendar.SECOND, 0);
          today.set(Calendar.MILLISECOND, 0);

          if (lastday == null || !today.equals(lastday)) log = getFileWriter(prep) ;

          if (obj instanceof Exception) {
              log.write(SDF.format(now) + " " + className + " - ");
              ((Exception)obj).printStackTrace(new PrintWriter(log));
              log.write("\n");
          }
          else if (debugOn.trim().toLowerCase().equals("true")) {
              log.write(SDF.format(now) + " " + className + " - ");
              log.write(obj.toString());
              log.write("\n");
          }
        }
        catch (Exception ex) {}
        finally { try { log.flush(); } catch (Exception exx) {} }
      }
      private static String add0(int v, int l) {
        long lv = (long)Math.pow(10, l);
        return String.valueOf(lv + v).substring(1);
      }

}