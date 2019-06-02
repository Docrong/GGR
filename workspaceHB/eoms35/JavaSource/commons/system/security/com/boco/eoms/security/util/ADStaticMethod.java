/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-18         created
 */

package com.boco.eoms.security.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import com.boco.eoms.security.config.SystemConfig;
import com.boco.eoms.security.exception.ObjectAlreadyExistException;
import com.boco.eoms.security.exception.ObjectNotExistException;
import com.boco.eoms.security.exception.SecurityManagerDaoException;

import com.boco.eoms.security.service.dao.ldap.factory.LdapOperation;



import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.security.config.SystemConfig;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: Load system configuration into memory </p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class ADStaticMethod {
  public static SystemConfig sc = SystemConfig.getInstance();
 public static String[] USER_OBJECT_CLASS = {
    "organizationalPerson", "user"};
 public static String[] DEPT_OBJECT_CLASS = {
     "dept"};

 public ADStaticMethod() {}

 public static String getUserDNSuffix() {
   return sc.userDNSuffix;
 }
 public static String getUserDNPreffix() {
  return sc.userDNPrefix;
}


 /**
  * create user object from ldap search result
  */
 public static TawSystemUser createUserFromResult(LdapOperation ldap) throws
     NamingException {
	 TawSystemUser usr = new TawSystemUser();
   usr.setUserid(ldap.getResultAttribute("cn"));

   Object pwdObj = ldap.getResultAttributeObject("password");

   if (pwdObj != null) {
     if (pwdObj instanceof byte[])
       usr.setPassword(new String( (byte[]) pwdObj));
     else if (pwdObj instanceof String)
       usr.setPassword( (String) pwdObj);
   }
   else
     usr.setPassword("");

   usr.setUsername(ldap.getResultAttribute("userName"));
   usr.setDeptid(StaticMethod.null2String(ldap.getResultAttribute(
       "deptId"), "0"));
   usr.setId(StaticMethod.null2String(ldap.getResultAttribute("id"),
       "0"));
   usr.setEmail(StaticMethod.null2String(ldap.getResultAttribute("email"),""));
   usr.setMobile(StaticMethod.null2String(ldap.getResultAttribute("mobile"),""));
   usr.setPhone(StaticMethod.null2String(ldap.getResultAttribute("phone"),""));
   usr.setFax(StaticMethod.null2String(ldap.getResultAttribute("fax"),""));
   usr.setId(StaticMethod.null2String(ldap.getResultAttribute("id"),
       "0"));
   usr.setDeleted(StaticMethod.null2String(ldap.getResultAttribute(
       "deleted"), "0"));
//   usr.setOrderId(StaticMethod.null2String(ldap.getResultAttribute(
//       "orderId"), "0"));
//   usr.setAccessTimeFlag(new Integer(StaticMethod.null2String(ldap.
//       getResultAttribute("accessTimeFlag"), "0")).
//                         intValue());
//   usr.setAccessIpFlag(new Integer(StaticMethod.null2String(ldap.
//       getResultAttribute("accessIpFlag"), "0")).
//                       intValue());
//   usr.setActiveFlag(new Integer(StaticMethod.null2String(ldap.
//       getResultAttribute("activeFlag"), "0")).
//                     intValue());
   usr.setUserdegree(StaticMethod.null2String(ldap.
       getResultAttribute("userDegree"), "0"));
//   usr.setAccessTimeBe(StaticMethod.null2String(ldap.getResultAttribute("accessTimeBe"),""));
//   usr.setAccessTimeEn(StaticMethod.null2String(ldap.getResultAttribute("accessTimeEn"),""));
//   usr.setAccessIp(StaticMethod.null2String(ldap.getResultAttribute("accessIp"),""));

   return usr;

 }


 public static String getDeptClassDirectoryContext() {
    return sc.groupCtxDN;
  }

  public static TawSystemDept createDeptFromResult(LdapOperation ldap) {
	  TawSystemDept dept = null;

    try {
      dept = new TawSystemDept();

      dept.setDeptName(StaticMethod.null2String(ldap.getResultAttribute(
          "deptName"), ""));
      dept.setParentDeptid(StaticMethod.null2String(ldap.
          getResultAttribute("parentDept"), "0"));
      dept.setId(new Integer(StaticMethod.null2String(ldap.getResultAttribute("ids"), "")));
//      dept.setManager(StaticMethod.null2String(ldap.getResultAttribute("master"),
//                                               ""));
//      dept.setTempManager(StaticMethod.null2String(ldap.getResultAttribute(
//          "tempMaster"), ""));
      dept.setDeptType(StaticMethod.null2String(ldap.getResultAttribute(
          "deptType"), ""));
//      dept.setPhone(StaticMethod.null2String(ldap.getResultAttribute("phone"),
//                                             ""));
//      dept.setMobile(StaticMethod.null2String(ldap.getResultAttribute("mobile"),
//                                              ""));
//      dept.setFax(StaticMethod.null2String(ldap.getResultAttribute("fax"), ""));
      dept.setDeleted(StaticMethod.null2String(ldap.
          getResultAttribute("deleted"), "0"));
      dept.setRemark(StaticMethod.null2String(ldap.getResultAttribute("notes")));
      dept.setOrdercode(new Integer(StaticMethod.null2String(ldap.
          getResultAttribute("orderCode"), "0")));
      dept.setRegionflag(new Integer(StaticMethod.null2String(ldap.
          getResultAttribute("reginoFlag"), "0")));
      dept.setDeptId(StaticMethod.null2String(ldap.
          getResultAttribute("deptId"), "0"));
//      dept.setStopTime(StaticMethod.null2String(ldap.getResultAttribute(
//          "stopTime"), ""));

    }
    catch (NamingException e) {
      e.printStackTrace();
    }
    return dept;
  }
  public static final String calcUserDN(String usrId) {

   StringBuffer usrdn = new StringBuffer(getUserDNPreffix());
   usrdn.append(usrId).append(",").append(getUserDNSuffix());

   return usrdn.toString();
 }

}