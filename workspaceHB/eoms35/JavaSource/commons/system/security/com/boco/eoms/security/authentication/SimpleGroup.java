/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 */

package com.boco.eoms.security.authentication;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class SimpleGroup
    extends SimplePrincipal
    implements Group {

  private HashMap memberList;

  public SimpleGroup(String s) {
    super(s);
    memberList = new HashMap(3);
  }

  public boolean addMember(Principal principal) {
    boolean flag = memberList.containsKey(principal.toString());

    if (!flag) {
      memberList.put(principal.toString(), principal);
    }
    return!flag;
  }

  public boolean removeMember(Principal principal) {
    if (memberList.remove(principal) != null) {
      return true;
    }
    else {
      return false;
    }
  }

  public boolean isMember(Principal principal) {
    boolean flag = memberList.containsKey(principal.toString());

    if (!flag) {
      Collection collection = memberList.values();
      for (Iterator iterator = collection.iterator(); !flag && iterator.hasNext(); ) {
        Object obj = iterator.next();
        if (obj instanceof Group) {
          Group group = (Group) obj;
          flag = group.isMember(principal);
        }
      }
    }
    return flag;

  }

  public boolean isMember(String s) {
    boolean flag = memberList.containsKey(s);
    return flag;
  }

  public Enumeration members() {
    return Collections.enumeration(memberList.values());
  }

  public int size() {
    return memberList.size();
  }

  public String toString() {
    Enumeration enumeration = members();
    StringBuffer stringbuffer = new StringBuffer("-- OBJECT Dump:\n");
    stringbuffer.append("  Name : ").append(getName()).append("\n");
    if (enumeration.hasMoreElements()) {
      stringbuffer.append("  - Members :\n");
    }
    String sTemp;
    for (; enumeration.hasMoreElements(); stringbuffer.append("\t\t").append(sTemp).append("\n")) {
      Object obj = enumeration.nextElement();
      sTemp = obj.toString();
    }

    stringbuffer.append("\n");
    return stringbuffer.toString();
  }
}
