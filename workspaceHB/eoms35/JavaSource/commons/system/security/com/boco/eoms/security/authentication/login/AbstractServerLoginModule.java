/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 */

package com.boco.eoms.security.authentication.login;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The abstract login module </p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public abstract class AbstractServerLoginModule
    implements LoginModule {

  protected Subject subject;
  protected CallbackHandler callbackHandler;
  protected Map sharedState;
  protected Map options;

  protected String name = null;
  protected char[] passwd = null;

  public AbstractServerLoginModule() {}

  /**
   * Description: initialize the class members for login and commit
   * @param subject
   * @param callbackHandler
   * @param sharedState
   * @param options
   */
  public void initialize(Subject subject, CallbackHandler callbackHandler,
                         Map sharedState, Map options) {
    System.out.println("initialize() in AbstractServerLoginModule");
    this.subject = subject;
    this.callbackHandler = callbackHandler;
    this.sharedState = sharedState;
    this.options = options;
  }

  /**
   * Description: check the user name and password with the information on LDAP server
   * @return true - if login successfully
   * @throws LoginException
   */
  public boolean login() throws LoginException {

    System.out.println("login() in AbstractServerLoginModule");

    Callback[] aCallback = new Callback[2];
    aCallback[0] = new NameCallback("User Name: ");
    aCallback[1] = new PasswordCallback("Password: ", false);

    try {
      callbackHandler.handle(aCallback);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    //only to check user name and password is not empty
    name = ( (NameCallback) aCallback[0]).getName();
    passwd = ( (PasswordCallback) aCallback[1]).getPassword();

    System.out.println("AbstractServerLoginModule-Username:" + name);
    System.out.println("AbstractServerLoginModule-Password:" + passwd.toString());

    if (name != null && passwd != null) {
      return true;
    }
    else {
      throw new LoginException("can't get user information from context,please check!");
    }
  }
  /**
   * commit the user login information
   * @return true - if commit successfully
   * @throws LoginException
   */
  public boolean commit() throws LoginException {
    System.out.println("commit() in AbstractServerLoginModule");
    return true;
  }
  /**
   * abort the user login information
   * @return true - if abort successfully
   * @throws LoginException
   */
  public boolean abort() throws LoginException {
    System.out.println("abort() in AbstractServerLoginModule");
    return true;
  }
  /**
   * logout from system
   * @return true - if logout successfully
   * @throws LoginException
   */
  public boolean logout() throws LoginException {
    System.out.println("logout() in AbstractServerLoginModule");
    return true;
  }

  /*implement by subclass to return different types of authorization*/
  protected abstract void getAuthorization();
}
