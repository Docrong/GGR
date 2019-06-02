/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-18         created
 */

package com.boco.eoms.security.authentication.login;

import java.util.Map;

import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

import com.boco.eoms.security.authentication.SimpleGroup;
import com.boco.eoms.security.config.SystemConfig;
import com.boco.eoms.security.service.dao.ldap.factory.LdapOperation;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class LdapLoginModule
    extends AbstractServerLoginModule {

  SimpleGroup roles = null;
  SimpleGroup departments = null;
  SimpleGroup permissions = null;
  SimpleGroup regions = null;
  SimpleGroup ranges = null;

  SystemConfig sc = SystemConfig.getInstance();
  public LdapLoginModule() {
    roles = new SimpleGroup("roles");
    departments = new SimpleGroup("departments");
    permissions = new SimpleGroup("permissions");
    ranges = new SimpleGroup("ranges");
    regions = new SimpleGroup("regions");

  }

  /**
   * Description: initialize the class members for login and commit
   * @param subject -
   * @param callbackHandler -
   * @param sharedState -
   * @param options -
   */
  public void initialize(Subject subject, CallbackHandler callbackhandler, Map sharedState, Map options) {
    super.initialize(subject, callbackhandler, sharedState, options);
    System.out.println("initialize() in LdapLoginModule");
  }

  /**
   * Description: check the user name and password with the information on LDAP server
   * @return true - if login successfully
   * @throws LoginException
   */
  public boolean login()
      throws LoginException {
    System.out.println("login() in LdapLoginModule");

    if (super.login()) {
      System.out.println("LdapLoginModule-Username:" + name);
      System.out.println("LdapLoginModule-Password:" + passwd.toString());

      if (this.authenticate()) {
        return true;
      }
      else {
        return false;
      }
    }
    else {
      return false;
    }
  }

  /**
   * Description: Authenticate user name and password according to information on LDAP
   * @return true - if authentication ends successfully
   */
  private boolean authenticate() {
    System.out.println("LdapLoginModule-authenticate() Started...");

    try {
      System.out.println("LdapLoginModule-authenticate() User[" + name + "] is trying to login...");
      LdapOperation op = new LdapOperation(sc.userDNPrefix + name + "," + sc.userDNSuffix, passwd);
      System.out.println("LdapLoginModule-authenticate() successfully");

      return true;
    }
    catch (NamingException nex) {
      nex.printStackTrace();
      return false;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
  }

  /**
   * get the user authorization from LDAP server
   */
  protected void getAuthorization() {
  }
}
