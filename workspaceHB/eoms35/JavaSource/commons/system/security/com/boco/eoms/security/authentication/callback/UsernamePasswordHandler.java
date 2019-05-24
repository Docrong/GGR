/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 */

package com.boco.eoms.security.authentication.callback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: CallbackHandle using Username and Password to do authentication</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class UsernamePasswordHandler
    implements CallbackHandler {

  /**
   * Variables which store the user name and password.
   */
  private transient String userName;
  private transient char[] password;

  public UsernamePasswordHandler(String name, char[] pwd) {
    this.userName = name;
    this.password = pwd;
  }

  /**
   * Description: Transport the user information to LoginModule.
   * @param Callback acallback[]
   * @throws UnsupportedCallbackException
   */
  public void handle(Callback acallback[]) throws UnsupportedCallbackException {
    System.out.println("Now in handle() of UsernamePasswordHandler");

    for (int i = 0; i < acallback.length; i++) {
      //when NameCallback for user name
      if (acallback[i] instanceof NameCallback) {
        NameCallback namecallback = (NameCallback) acallback[i];
        namecallback.setName(userName);
      }
      //when PasswordCallback for user password
      else
      if (acallback[i] instanceof PasswordCallback) {
        PasswordCallback passwordcallback = (PasswordCallback) acallback[i];
        System.out.println("Before setPassword="+password.toString());
        passwordcallback.setPassword(password);
      }
      //when exception situation
      else {
        throw new UnsupportedCallbackException(acallback[i], "Unrecognized Callback");
      }
    }
  }
}
