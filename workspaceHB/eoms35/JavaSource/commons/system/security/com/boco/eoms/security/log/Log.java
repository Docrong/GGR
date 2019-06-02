/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-25         created
 */

package com.boco.eoms.security.log;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class Log {
  private static Log log = null;
  private static Connection con = null;

  private final String sql =
    " INSERT INTO TAI_OLOGREC "+
    " (OLOG_NUM,OLOG_TYPE,OLOG_CLASS,OLOG_ID,MACHINE_NAME,MACHINE_IP,APPLICATION_ID,USER_ID,REC_DTIME,MEMO)"+
    " VALUES(informix.Q_TAI_OLOGREC.NEXTVAL,1,5,?,?,?,6100,?,SYSDATE,?)";


  private Log() {
    /*
    try {
      SAXBuilder sax = new SAXBuilder();
      Document doc = sax.build(new File("../conf/LogConfig.xml"));
//      Document doc = dom.build(new File("D:/A_Working/LDAP_project/project/conf/LogConfig.xml"));

      List ll = doc.getRootElement().getChild("log").getChildren();
      Iterator it = ll.iterator();

      String driver = "";String url = "";String user = "";String password = "";

      while (it.hasNext())
      {
        Element ele = (Element)it.next();
        if (ele.getName().equalsIgnoreCase("connection-url")) url = ele.getTextTrim();
        if (ele.getName().equalsIgnoreCase("driver-class")) driver = ele.getTextTrim();
        if (ele.getName().equalsIgnoreCase("user-name")) user = ele.getTextTrim();
        if (ele.getName().equalsIgnoreCase("password")) password = ele.getTextTrim();
      }
System.out.println("current drive<" +driver +">");
      if (driver.equals("") || url.equals("") || user.equals("") || password.equals(""))
      {
      	System.out.println("drive<" +driver +">, " +"url<" +url +">, "+"user<" +user +">, "+"password<" +password +">");
        throw new Exception("Config File Format Error." );

      }

      Class.forName(driver);
      con = DriverManager.getConnection(url,user,password);
    }
    catch (Exception ex)
    {
      System.out.println("Log Functionality Initialization Failed:"+ex.getMessage());
    }
*/
  }

  public synchronized static Log getInstance() {
    if (log == null) {
      log = new Log();
    }
    return log;
  }

  public synchronized void writeLog(String ologID, HttpServletRequest req, String user, String memo) {
    /*
    PreparedStatement st = null;
    try
    {
      st = con.prepareStatement(sql);
      st.setLong(1,Long.parseLong(ologID));
      st.setString(2,req.getRemoteHost());
      st.setString(3,req.getRemoteAddr());
      st.setLong(4,-1);
      st.setString(5,memo);
      int row = st.executeUpdate();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      System.out.println("Log create failed......"+e.getMessage());
    }
        */
  }

  public static void main(String[] args) {
    Log a = Log.getInstance();
  }
}