/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 */

package com.boco.eoms.security.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The Exception of Security Error</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class SecurityManagerDaoException
    extends Exception {
  /**
   * the root exception of SecurityManagerDaoException
   */
  private Throwable rootCause;

  public SecurityManagerDaoException(String desc, Throwable throwable) {
    super(desc);
    this.rootCause = throwable;
  }

  public SecurityManagerDaoException(Throwable throwable) {
    super();
    this.rootCause = throwable;
  }

  /**
   * get the root exception of SecurityManagerDaoException
   * @return Throwable
   */
  public Throwable getRootCause() {
    return this.rootCause;
  }

  /**
   * print the trace information of exception
   */
  public void printStackTrace() {
    if (this.rootCause != null) {
      this.rootCause.printStackTrace();
    }
  }

  /**
   * print the trace information of exception
   * @param printwriter - output device
   */
  public void printStackTrace(PrintWriter printwriter) {
    if (this.rootCause != null) {
      this.rootCause.printStackTrace(printwriter);
    }
  }

  /**
   * print the trace information of exception
   * @param printstream - output device
   */
  public void printStackTrace(PrintStream printstream) {
    if (this.rootCause != null) {
      this.rootCause.printStackTrace(printstream);
    }
  }
}
