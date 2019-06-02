/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-18         created
 */

package com.boco.eoms.security.service.dao.ldap.factory;

import java.util.Properties;
import javax.naming.*;
import javax.naming.ldap.*;
import javax.naming.directory.*;


import com.boco.eoms.security.config.SystemConfig;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: LDAP Connection Factory </p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class LdapConnectionFactory {
  private static SystemConfig sc = SystemConfig.getInstance();
  /**
   * get the connection to the LDAP server
   * @return DirContext - the connection to the LDAP server
   */
  public synchronized static DirContext getDirContext() {
      try {
        Properties mEnv = new Properties();

        mEnv.put(Context.INITIAL_CONTEXT_FACTORY, sc.factory);
        mEnv.put(Context.PROVIDER_URL, sc.url);
        mEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
        mEnv.put(Context.SECURITY_PRINCIPAL, sc.adminUID);
        mEnv.put(Context.SECURITY_CREDENTIALS, sc.adminPWD);
        //System.out.println("chris report: ldap url="+sc.url);
        DirContext ctx = new InitialDirContext(mEnv);
     //   LdapContext ctx1 = new InitialLdapContext(mEnv,null);

        return ctx;
      }
      catch (NamingException ex) {
        System.out.println("Error when initializing connection factory!");
        ex.printStackTrace();
        return null;
      }
    }

    /**
     * only for self-testing
     * @param args - no use
     */
    public static void main (String[] args){
      System.out.println("admin:"+sc.adminUID);
      System.out.println("admin:"+sc.adminPWD);

      getDirContext();
    }
}
