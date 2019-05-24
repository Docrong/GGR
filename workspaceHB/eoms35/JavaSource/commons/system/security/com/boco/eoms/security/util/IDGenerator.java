/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-10-10         Created
 */


package com.boco.eoms.security.util;

/**
 * <p>Title: Authentication and Authorization System via LDAP</p>
 * <p>Description: Authentication and Authorization System via LDAP</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import com.boco.eoms.security.service.dao.ldap.factory.LdapOperation;

public class IDGenerator {

  public static String ctxDN = "ou=IDCount,dc=boco,dc=com";

  /**
   *
   * @return The next available role ID
   */
  public static String getRoleID() {
    long id = 0L;
    try {
      id = 0;

      LdapOperation ldap = getLdap();
      String[] attrs = {"eaIDCount"};
      ldap.setReturningAttributes(attrs);
      ldap.searchSubtree(ctxDN, "cn=eaRoleIDCount");
      if (ldap.nextResult()) {
        id = new Long(ldap.getResultAttribute("eaIDCount")).longValue();
      }

      ModificationItem[] mods = new ModificationItem[1];

      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("eaIDCount", new Long(id + 1).toString()));

      LdapOperation.modifyAttributes("cn=eaRoleIDCount," + ctxDN, mods);
    }
    catch (NamingException ex) {
      ex.printStackTrace();
      id = 0;
    }

    return "Role_" + Long.toString(id);
  }
  /**
   *
   * @return The next available region ID
   */
  public static String getRegionID() {
    long id = 0;

    try {
      id = 0;

      LdapOperation ldap = getLdap();
      String[] attrs = {"eaIDCount"};
      ldap.setReturningAttributes(attrs);
      ldap.searchSubtree(ctxDN, "cn=eaRegionIDCount");
      if (ldap.nextResult()) {
        id = new Long(ldap.getResultAttribute("eaIDCount")).longValue();
      }

      ModificationItem[] mods = new ModificationItem[1];

      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("eaIDCount", new Long(id + 1).toString()));

      LdapOperation.modifyAttributes("cn=eaRegionIDCount," + ctxDN, mods);
    }
    catch (NamingException ex) {
      id = 0;
    }

    return "Region_" + Long.toString(id);
  }

  /**
   *
   * @return The next available department ID
   */

  public static String getDeptID() {
    long id = 0;

    try {
      id = 0;

      LdapOperation ldap = getLdap();
      String[] attrs = {"eaIDCount"};
      ldap.setReturningAttributes(attrs);
      ldap.searchSubtree(ctxDN, "cn=eaDeptIDCount");
      if (ldap.nextResult()) {
        id = new Long(ldap.getResultAttribute("eaIDCount")).longValue();
      }

      ModificationItem[] mods = new ModificationItem[1];

      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("eaIDCount", new Long(id + 1).toString()));

      LdapOperation.modifyAttributes("cn=eaDeptIDCount," + ctxDN, mods);
    }
    catch (NamingException ex) {
      id = 0;
    }

    return "Department_" + Long.toString(id);
  }

  /**
   * Get the connection instance of LDAP
   * @return LdapOperation
   * @throws NamingException
   */
  public static LdapOperation getLdap()
      throws NamingException {
    LdapOperation ldap = new LdapOperation();
    return ldap;
  }

  public static void main(String[] args){
    System.out.println("Role ID:"+getRoleID());
    System.out.println("Dept ID:"+getDeptID());
    System.out.println("Region ID:"+getRegionID());
  }
}