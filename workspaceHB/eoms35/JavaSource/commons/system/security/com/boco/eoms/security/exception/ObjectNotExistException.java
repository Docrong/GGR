/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 */

package com.boco.eoms.security.exception;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The Exception of Object Not Exist</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class ObjectNotExistException
    extends Exception {
  public ObjectNotExistException() {
    super();
  }

  public ObjectNotExistException(String desc) {
    super(desc);
  }
}
