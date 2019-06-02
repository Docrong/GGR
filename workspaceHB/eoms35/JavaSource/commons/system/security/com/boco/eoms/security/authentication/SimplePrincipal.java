/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 */

package com.boco.eoms.security.authentication;

import java.io.Serializable;
import java.security.Principal;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: Simple Principal </p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class SimplePrincipal
    implements Principal, Serializable {

  String name = null;
  String dispalyName = null;

  public SimplePrincipal(String newName) {
    this.name = newName;
    this.dispalyName = newName;
  }

  public SimplePrincipal(String newName, String newDisplayName) {
    this.name = newName;
    this.dispalyName = newDisplayName;
  }

  public boolean equals(Object obj) {
    if (! (obj instanceof Principal)) {
      return false;
    }

    String newName = ( (Principal) obj).getName();
    boolean flag = false;
    if (name == null) {
      flag = (newName == null);
    }
    else {
      flag = name.equals(newName);
    }
    return flag;

  }

  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }

  public String toString() {
    return name;
  }

  public String getName() {
    return name;
  }

  public String getDisplayName() {
    return dispalyName;
  }
}
