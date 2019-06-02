/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis              2003-08-15       created
 */

package com.boco.eoms.security.service.dao.ldap;

import java.io.IOException;
import java.util.*;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import com.boco.eoms.security.config.SystemConfig;
import com.boco.eoms.security.crypto.Base64Encoder;
import com.boco.eoms.security.exception.ObjectAlreadyExistException;
import com.boco.eoms.security.exception.ObjectNotExistException;
import com.boco.eoms.security.exception.SecurityManagerDaoException;
import com.boco.eoms.security.service.dao.UserDAO;
import com.boco.eoms.security.service.dao.ldap.factory.LdapOperation;
import com.boco.eoms.security.service.manager.PermissionManager;
import com.boco.eoms.security.service.manager.RangeManager;
import com.boco.eoms.security.service.model.DeptDO;
import com.boco.eoms.security.service.model.PermissionDO;
import com.boco.eoms.security.service.model.RangeDO;
import com.boco.eoms.security.service.model.RoleDO;
import com.boco.eoms.security.service.model.UserDO;
import com.boco.eoms.common.util.*;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author weis
 * @version 1.0
 */

public class UserLdapDAO
    extends BaseLdapDAO
    implements UserDAO {

  private static SystemConfig sc = SystemConfig.getInstance();

  protected static String[] USER_OBJECT_CLASS = {
      "organizationalPerson", "user"};


  public UserLdapDAO() {}

  /**
   * user class directory context -- Suffix
   */
  public static String getUserDNSuffix() {
    return sc.userDNSuffix;
  }

  /**
   *user class directory context -- preffix
   */
  public static String getUserDNPreffix() {
    return sc.userDNPrefix;
  }

  public static String[] getUserObjectClass() {
    return USER_OBJECT_CLASS;
  }

  public String getPermissionDirectoryContext() {
    return sc.permissionCtxDN;
  }

  public static String getRangeDirectoryContext() {
    return sc.rangeCtxDN;
  }

  public static final String calcUserDN(String usrId) {

    StringBuffer usrdn = new StringBuffer(getUserDNPreffix());
    usrdn.append(usrId).append(",").append(getUserDNSuffix());

    return usrdn.toString();
  }

  public UserDO createUser(UserDO user) throws ObjectAlreadyExistException,
      SecurityManagerDaoException {

    LdapOperation ldap = null;
    //Note: the userName must equals to userID, In Ldap, userName is equals to uid, ("sn")
    //So , to be sure that userName equals to userID, let uer's input user's name,
    //the input user's name equals to userName, here set userID = userName.
    // add by song :just Test for the problem.
    String userId = user.getUserId();
    try {
      ldap = getLdap();

      //check if the user has already exist.

      String userCtxDN = getUserDNSuffix();
      ldap.searchSubtree(userCtxDN, "CN=" + userId);

      if (ldap.nextResult())
        throw new ObjectAlreadyExistException("The user " + user +
                                              "has already exist.");

      //create the domain
      Attributes attributes = new BasicAttributes(true);
      Attribute attr = new BasicAttribute("objectClass");
      //attr.add("top");
      for (int i = 0; i < USER_OBJECT_CLASS.length; i++) {
        attr.add(USER_OBJECT_CLASS[i]);
      }

      attributes.put(attr);

      attributes.put("cn",userId);
      /*
      attributes.put("userId", userId);
      attributes.put("userName", user.getUserName());
      attributes.put("password", user.getPassword());
      attributes.put("deptId", String.valueOf(user.getDeptId()));
      attributes.put("id", String.valueOf(StaticMethod.getSingleId()));
      attributes.put("orderId", String.valueOf(user.getOrderId()));
      attributes.put("deleted", String.valueOf(StaticVariable.UNDELETED));
      attributes.put("accessIpFlag", String.valueOf(user.getAccessIpFlag()));
      attributes.put("activeFlag", String.valueOf(user.getActiveFlag()));
      attributes.put("accessTimeFlag", String.valueOf(user.getAccessTimeFlag()));
      attributes.put("userDegree", String.valueOf(user.getUserDegree()));

      if (user.getEmail() != null && !user.getEmail().equals("") )
        attributes.put("email", user.getEmail());
      if (user.getMobile() != null && !user.getMobile().equals(""))
        attributes.put("mobile", user.getMobile());
      if (user.getPhone() != null && !user.getPhone().equals("") )
        attributes.put("phone", user.getPhone());
      if ( user.getFax() != null &&!user.getFax().equals(""))
        attributes.put("fax", user.getFax());
      if (user.getNotes() != null && !user.getNotes().equals(""))
        attributes.put("notes", user.getNotes());
      if (user.getAccessTimeBe() != null && !user.getAccessTimeBe().equals(""))
        attributes.put("accessTimeBe", user.getAccessTimeBe());
      if ( user.getAccessTimeEn() != null &&!user.getAccessTimeEn().equals(""))
        attributes.put("accessTimeEn", user.getAccessTimeEn());
      if (user.getAccessIp() != null && !user.getAccessIp().equals(""))
        attributes.put("accessIp", user.getAccessIp());
*/

      String userDN = "CN=" + userId + "," + getUserDNSuffix();

      LdapOperation.addElement(userDN, attributes);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Count not create user : " + user,
                                            e);
    }

    finally {
      closeLdap(ldap);
    }
    return user;
  }

  /**
   * routine to get user name
   */
  protected String getUserName(Object user) {
    // get the user's name
    String username;
    if (user instanceof UserDO)
      username = ( (UserDO) user).getUserName();
    else if (user instanceof String)
      username = (String) user;
    else
      throw new IllegalArgumentException("bad user parameter");

    return username;
  }

  /**
   * This method removes a user from the system. All the user's attributes are
       * remove, and also all the related objects belonging to the user. On success,
   * true is returned and the user parameter is not longer valid. Return false
   * on any failure.
   *
   * @param  user  reference on the user to be deleted.
   * @throws ObjectNotExistException , SecurityManagerDaoException
   */
  public void deleteUser(UserDO user) throws ObjectNotExistException,
      SecurityManagerDaoException {
    //System.out.println(user.getUserName());
    deleteUser(user.getUserId());
  }

  /**
   * remove user by the given name
   *
   * @param name name of the user
   * @throws ObjectNotExistException ,SecurityManagerDaoException
   */
  public void deleteUser(String userId) throws ObjectNotExistException,
      SecurityManagerDaoException {

    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String usrdn = calcUserDN(userId);
      LdapOperation.deleteElement(usrdn);

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not delete user : " + userId,
                                            e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * update user information
   *
   *   cn - common name (full name)
   *   sn - surname (required by ldap class person) , keep it same as uid
   *   userPassword - user password , defined in class inetOrgPerson , empty password should allowed
   *   mail - mail address (support multiple value)
   *   telephoneNumber - telephone number (support multiple value)
   *
   * @param user user to update
   * @throws ObjectNotExistException , SecurityManagerDaoException
   */
  public void updateUser(UserDO user) throws ObjectNotExistException,
      SecurityManagerDaoException {

    LdapOperation ldap = null;
    UserDO oldUser = null;

    try {
      ldap = getLdap();

      String userDN = calcUserDN(user.getUserId());

      // check if the user exist
      oldUser = lookupUser(user.getUserId());

      if (oldUser == null)
        throw new ObjectNotExistException(user + " not exist in directory.");

      // compare and modify attributes
      Vector vec = new Vector();

      // check if the object has required schema
      String[] attids = {
          "objectClass"};

      // System.out.println(userDN);

      Attributes attrs = ldap.getDirContext().getAttributes(userDN, attids);
      NamingEnumeration valueEnum = attrs.get("objectClass").getAll();
      boolean bfound = false;
      while (valueEnum.hasMore()) {
        String value;
        value = (String) valueEnum.next();
        if (value.equalsIgnoreCase("User"))
          bfound = true;
      }

      if (!bfound)
        vec.add(new ModificationItem(DirContext.ADD_ATTRIBUTE,
                                     new BasicAttribute("objectClass", "User")));

      if (!oldUser.getUserId().equals(user.getUserId()))
        vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("cn", user.getUserId())));

      if (user.getPassword() != null && !user.getPassword().equals("")) {
        String newpassword = Base64Encoder.encode(user.getPassword());
        vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("userPassword",
            newpassword)));
      }

      if (user.getUserName() != null&&!user.getUserName().equals(""))
        vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("userName",
            user.getUserName())));

      if (user.getPhone() != null&&!user.getPhone().equals(""))
        vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("phone", user.getPhone())));

      if (user.getEmail() != null&&!user.getEmail().equals(""))
        vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("email",
            (String) user.getEmail())));

      if (user.getMobile() != null&&!user.getMobile().equals(""))
        vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("mobile",
            (String) user.getMobile())));

      if (user.getFax() != null&&!user.getFax().equals(""))
        vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("fax",
            (String) user.getFax())));

      if (user.getNotes() != null&&!user.getNotes().equals(""))
        vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("notes",
            (String) user.getNotes())));

      vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                   new BasicAttribute("userDegree",
          String.valueOf(user.getUserDegree()))));

      vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                   new BasicAttribute("orderId",
          String.valueOf(user.getOrderId()))));

      if (user.getAccessTimeBe() != null&&!user.getAccessTimeBe().equals(""))
        vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("accessTimeBe",
            (String) user.getAccessTimeBe())));

      if (user.getAccessTimeEn() != null&&!user.getAccessTimeEn().equals(""))
        vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("accessTimeEn",
            (String) user.getAccessTimeEn())));

      vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                   new BasicAttribute("accessTimeFlag",
          String.valueOf(user.getAccessTimeFlag()))));

      if (user.getAccessIp() != null&&!user.getAccessIp().equals(""))
        vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("accessIp",
            (String) user.getAccessIp())));

      vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                   new BasicAttribute("accessIpFlag",
          String.valueOf(user.getAccessIpFlag()))));

      vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                   new BasicAttribute("activeFlag",
          String.valueOf(user.getActiveFlag()))));

      vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                   new BasicAttribute("deptId",
          String.valueOf(user.getDeptId()))));

      vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                  new BasicAttribute("deleted",
         String.valueOf(user.getDeleted()))));


      ModificationItem[] mods = new ModificationItem[1];
      LdapOperation.modifyAttributes(userDN,
                                     (ModificationItem[]) vec.toArray(mods));

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Count not create user : " + user,
                                            e);
    }
    catch (IOException e) {
      throw new SecurityManagerDaoException("Count not create user : " + user,
                                            e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  public void addPropertiesOfUser(UserDO user) throws ObjectNotExistException,
      SecurityManagerDaoException {
    LdapOperation ldap = null;
    UserDO oldUser = null;
    try {
      ldap = getLdap();

      String userDN = calcUserDN(user.getUserName());

      // check if the user exist
      oldUser = lookupUser(user.getUserName());

      if (oldUser == null)
        throw new ObjectNotExistException(user + " not exist in directory.");

      // compare and modify attributes
      Vector vec = new Vector();

      //There are mutli-values of mail, to add
      if (user.getProperty("mail") != null)
        vec.add(new ModificationItem(DirContext.ADD_ATTRIBUTE,
                                     new BasicAttribute("mail",
            (String) user.getProperty("mail"))));

        /*if(user.getProperty("SecurityHost")!=null)
                     {
            vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
             new BasicAttribute("eaLogonTime", (String)user.getProperty("LogonTime"))));
                     } */

      if (user.getProperty("LogonTime") != null)

        // {
        vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("eaLogonTime",
            (String) user.getProperty("LogonTime"))));

      ModificationItem[] mods = new ModificationItem[1];
      LdapOperation.modifyAttributes(userDN,
                                     (ModificationItem[]) vec.toArray(mods));
      //}

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Count not create user : " + user,
                                            e);
    }
//        catch(IOException e)
//        {
//            System.out.println("IO exception");
//            e.printStackTrace();
//        }
//        catch (Exception e) {
//
//            throw new SecurityManagerDaoException("Can not update user : " + user, e);
//
//        }
    finally {
      closeLdap(ldap);
    }

  }

  /**
   * create user object from ldap search result
   */
  public static UserDO createUserFromResult(LdapOperation ldap) throws
      NamingException {
    UserDO usr = new UserDO(ldap.getResultAttribute("sAMAccountName"),
                            ldap.getResultAttribute("cn"));
    usr.setUserId(ldap.getResultAttribute("sAMAccountName"));
    usr.setNotes(ldap.getResultAttribute("distinguishedName")) ;

    Object pwdObj = ldap.getResultAttributeObject("password");

    if (pwdObj != null) {
      if (pwdObj instanceof byte[])
        usr.setPassword(new String( (byte[]) pwdObj));
      else if (pwdObj instanceof String)
        usr.setPassword( (String) pwdObj);
    }
    else
      usr.setPassword("");

    pwdObj = ldap.getResultAttribute("telephoneNumber");
    if (pwdObj != null) {
      if (pwdObj instanceof byte[])
        usr.setMobile(new String( (byte[]) pwdObj));
      else if (pwdObj instanceof String)
        usr.setMobile( (String) pwdObj);
    }
    else
      usr.setMobile("");

    usr.setUserName(ldap.getResultAttribute("displayName"));

    //liangyaohan 2006-06-22
    //�Բ��Ż����û������ж�
    //System.out.print(ldap.getResultAttribute("memberof") + " , " );
    //System.out.println(ldap.getResultAttribute("member") );
    String strDeptName = StaticMethod.null2String(ldap.getResultAttribute("member"));
    usr.setDeptName(strDeptName);

    usr.setDeptId(new Integer(StaticMethod.null2String(ldap.getResultAttribute(
        "deptId"), "0")).intValue());
    usr.setId(new Integer(StaticMethod.null2String(ldap.getResultAttribute("id"),
        "0")).intValue());
    usr.setEmail(StaticMethod.null2String(ldap.getResultAttribute("email"),""));
    usr.setPhone(StaticMethod.null2String(ldap.getResultAttribute("phone"),""));
    usr.setFax(StaticMethod.null2String(ldap.getResultAttribute("fax"),""));
    usr.setId(new Integer(StaticMethod.null2String(ldap.getResultAttribute("id"),
        "0")).intValue());
    usr.setDeleted(new Integer(StaticMethod.null2String(ldap.getResultAttribute(
        "deleted"), "0")).intValue());
    usr.setOrderId(new Integer(StaticMethod.null2String(ldap.getResultAttribute(
        "orderId"), "0")).intValue());
    usr.setAccessTimeFlag(new Integer(StaticMethod.null2String(ldap.
        getResultAttribute("accessTimeFlag"), "0")).
                          intValue());
    usr.setAccessIpFlag(new Integer(StaticMethod.null2String(ldap.
        getResultAttribute("accessIpFlag"), "0")).
                        intValue());
    //AccountDisabled
    int accountControl = new Integer(StaticMethod.null2String(ldap.
        getResultAttribute("userAccountControl"), "0")).intValue();
    if (accountControl == 514)
      usr.setActiveFlag(1);
    else
      usr.setActiveFlag(0);
    //usr.setActiveFlag(new Integer(StaticMethod.null2String(ldap.
    //    getResultAttribute("AccountDisabled"), "0")).
    //                  intValue());
    usr.setUserDegree(new Integer(StaticMethod.null2String(ldap.
        getResultAttribute("userDegree"), "0")).
                      intValue());
    usr.setAccessTimeBe(StaticMethod.null2String(ldap.getResultAttribute("accessTimeBe"),""));
    usr.setAccessTimeEn(StaticMethod.null2String(ldap.getResultAttribute("accessTimeEn"),""));
    usr.setAccessIp(StaticMethod.null2String(ldap.getResultAttribute("accessIp"),""));

    return usr;

  }

  /**
   * create user object from ldap search result
   */
  public static UserDO createUserFromAttrs(Attributes attrs) throws
      NamingException {
    UserDO usr = new UserDO( (String) attrs.get("uid").get(),
                            (String) attrs.get("cn").get());

    if (attrs.get("userPassword") != null) {
      Object pwdObj = attrs.get("userPassword").get();

      if (pwdObj != null) {
        if (pwdObj instanceof byte[])
          usr.setPassword(new String( (byte[]) pwdObj));
        else if (pwdObj instanceof String)
          usr.setPassword( (String) pwdObj);
      }
      else
        usr.setPassword("");
    }

    if (attrs.get("LogonTime") != null)
      usr.setProperty("LogonTime", attrs.get("LogonTime").get());

    if (attrs.get("eaRegionID") != null)
      usr.setProperty("eaRegionID", attrs.get("eaRegionID").get());

    if (attrs.get("ExpiredTime") != null)
      usr.setProperty("ExpiredTime", attrs.get("ExpiredTime").get());

    if (attrs.get("SecurityHost") != null)
      usr.setProperty("SecurityHost", attrs.get("SecurityHost").get());

    if (attrs.get("PostalCode") != null)
      usr.setProperty("PostalCode", attrs.get("PostalCode").get());

    if (attrs.get("mail") != null)
      usr.setProperty("mail", attrs.get("mail").get());

    if (attrs.get("telephoneNumber") != null)
      usr.setProperty("telephoneNumber", attrs.get("telephoneNumber").get());

    if (attrs.get("mobile") != null)
      usr.setProperty("mobile", attrs.get("mobile").get());

    if (attrs.get("homePostalAddress") != null)
      usr.setProperty("homePostalAddress", attrs.get("homePostalAddress").get());

    if (attrs.get("description") != null)
      usr.setProperty("description", attrs.get("description").get());

    if (attrs.get("departmentnumber") != null)
      usr.setProperty("departmentnumber", attrs.get("departmentnumber").get());

    return usr;

  }

  /**
   * Load all the user data and attributes. On success a reference on the user
   * is returned, otherwise NULL is returned.
   *
   * @param  name  User's identification name.
   * @return Return a reference on a new created User object.
   * @throws SecurityManagerDaoException
   */

  //liangyaohan 2006-06-19
  /*
  public UserDO lookupUser(String userId) throws SecurityManagerDaoException {

    LdapOperation ldap = null;
    UserDO user = null;

    try {
      ldap = getLdap();

      String userCtxDN = getUserDNSuffix();
      ldap.searchSubtree(userCtxDN, getUserDNPreffix() + userId);

      if (ldap.nextResult()) {
        user = createUserFromResult(ldap);
      }

    }
    catch (Exception e) {
      throw new SecurityManagerDaoException("Can not find user : " + userId, e);

    }
    finally {
      closeLdap(ldap);
    }

    return user;
  }*/
  public UserDO lookupUser(String userId) throws SecurityManagerDaoException {

    LdapOperation ldap = null;
    UserDO user = null;
    Vector vUser = new Vector();

    try {
      ldap = getLdap();

      //String userCtxDN = getUserDNSuffix();
      //ldap.searchSubtree(userCtxDN, getUserDNPreffix() + userId);
      vUser = ldap.searchAdUser(userId,1) ;

      if (vUser.size() > 0) {
        user = (UserDO)vUser.get(0);
      }

    }
    catch (Exception e) {
      throw new SecurityManagerDaoException("Can not find user : " + userId, e);

    }
    finally {
      closeLdap(ldap);
    }

    return user;
  }

  public UserDO lookupUser(int id) throws SecurityManagerDaoException {

    LdapOperation ldap = null;
    UserDO user = null;

    try {
      ldap = getLdap();

      String userCtxDN = getUserDNSuffix();
      ldap.searchSubtree(userCtxDN, "id=" + id);

      if (ldap.nextResult()) {
        user = createUserFromResult(ldap);
      }

    }
    catch (Exception e) {
      throw new SecurityManagerDaoException("Can not find user : " + id, e);

    }
    finally {
      closeLdap(ldap);
    }

    return user;
  }

  public UserDO lookupUser(String userId, int deleted) throws
      ObjectNotExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;
    UserDO user = null;

    try {
      ldap = getLdap();

      String userCtxDN = getUserDNSuffix();
      ldap.searchSubtree(userCtxDN,
                         " (&(" + getUserDNPreffix() + "=" + userId +
                         ")(deleted=" +
                         deleted + "))");

      if (ldap.nextResult()) {
        user = createUserFromResult(ldap);
      }

    }
    catch (Exception e) {
      throw new SecurityManagerDaoException("Can not find user : " + userId, e);

    }
    finally {
      closeLdap(ldap);
    }

    return user;

  }

  /**
   * Load all the user data and attributes. On success a reference on the user
   * is returned, otherwise NULL is returned.
   *
   * @param  name  User's full name.
   * @return Return a vector of all reference on a new created User object.
   * @throws ObjectNotExistException ,SecurityManagerDaoException
   */
  public Vector lookupUserWithUserId(String userId) throws
      SecurityManagerDaoException {

    LdapOperation ldap = null;
    UserDO user = null;
    Vector vct = new Vector();

    try {
      ldap = getLdap();

      String userCtxDN = getUserDNSuffix();
      ldap.searchSubtree(userCtxDN, "cn=" + userId);

      while (ldap.nextResult()) {
        user = createUserFromResult(ldap);
        vct.add(user);
      }

    }
    catch (Exception e) {
      throw new SecurityManagerDaoException("Can not find user id: " + userId,
                                            e);

    }
    finally {
      closeLdap(ldap);
    }

    return vct;
  }

  public List lookupUsersByDeptIds(String deptIds) throws
      SecurityManagerDaoException {

    LdapOperation ldap = null;
    UserDO user = null;
    ArrayList list = new ArrayList();
    String filter = "";
    String dept[] = deptIds.split(",");
    for (int i = 0; i < dept.length; i++) {
      filter += "(deptId=" + dept[i] + ")";
    }
    filter = "(|" + filter + ")";
    try {
      ldap = getLdap();

      String userCtxDN = getUserDNSuffix();
      ldap.searchSubtree(userCtxDN, filter);

      while (ldap.nextResult()) {
        user = createUserFromResult(ldap);
        list.add(user);
      }

    }
    catch (Exception e) {
      e.printStackTrace();
      throw new SecurityManagerDaoException("Can not find user: " + deptIds,
                                            e);

    }
    finally {
      closeLdap(ldap);
    }

    return list;
  }

  public List lookupUsersByDeptIds(String deptIds, int deleted) throws
      SecurityManagerDaoException {

    LdapOperation ldap = null;
    UserDO user = null;
    ArrayList list = new ArrayList();
    String filter = "";
    String dept[] = deptIds.split(",");
    for (int i = 0; i < dept.length; i++) {
      filter += "(deptId=" + dept[i] + ")";
    }
    filter = "(&(|" + filter + ")(deleted=" + deleted + "))";
    try {
      ldap = getLdap();

      String userCtxDN = getUserDNSuffix();
      ldap.searchSubtree(userCtxDN, filter);

      while (ldap.nextResult()) {
        user = createUserFromResult(ldap);
        list.add(user);
      }

    }
    catch (Exception e) {
      e.printStackTrace();
      throw new SecurityManagerDaoException("Can not find user: " + deptIds,
                                            e);

    }
    finally {
      closeLdap(ldap);
    }

    return list;
  }

  public List lookupUsersByName(String userName) throws
      SecurityManagerDaoException {

    LdapOperation ldap = null;
    UserDO user = null;
    ArrayList list = new ArrayList();

    try {
      ldap = getLdap();

      String userCtxDN = getUserDNSuffix();
      ldap.searchSubtree(userCtxDN, "userName=*" + userName + "*");

      while (ldap.nextResult()) {
        user = createUserFromResult(ldap);
        list.add(user);
      }

    }
    catch (Exception e) {
      e.printStackTrace();
      throw new SecurityManagerDaoException("Can not find user: " + userName,
                                            e);

    }
    finally {
      closeLdap(ldap);
    }

    return list;
  }

  public UserDO lookupUser(UserDO user) throws SecurityManagerDaoException {
    String userId = user.getUserId();
    return lookupUser(userId);
  }

  /**
   * This method return all users' keys in the system.
   *
   * @return Return a vector of strings holding the user identification key .
   * @throws SecurityManagerDaoException
   */
  public List getUserList(int deleted) throws SecurityManagerDaoException {

    LdapOperation ldap = null;
    ArrayList list = new ArrayList();
    //System.out.println("this is a test for manager!");

    try {
      ldap = getLdap();

      String userCtxDN = getUserDNSuffix();
      ldap.searchSubtree(userCtxDN, "deleted=" + deleted);

      while (ldap.nextResult()) {
        UserDO usr = createUserFromResult(ldap);
        list.add(usr);
      }

    }
    catch (Exception e) {
      throw new SecurityManagerDaoException("Failed to count users", e);
    }
    finally {
      closeLdap(ldap);
    }

    return list;
  }

  public List getRUSelByParentId(int parentId, int defnull, int undel) throws
      SecurityManagerDaoException {
    LdapOperation ldap = null;
    ArrayList list = new ArrayList();

    try {
      ldap = getLdap();

      String userCtxDN = getUserDNSuffix();
      ldap.searchSubtree(userCtxDN,
                         "(&(deleted=" + undel + ")(|(deptId=" + parentId +
                         ")(deptId=" + defnull + ")(deptId=null))");

      while (ldap.nextResult()) {
        UserDO usr = createUserFromResult(ldap);
        list.add(usr);
      }

    }
    catch (Exception e) {
      throw new SecurityManagerDaoException("Failed to count users", e);
    }
    finally {
      closeLdap(ldap);
    }

    return list;

  }

  public List getRUSelByParAndId(int parentId, int deptId, int undel) throws
      SecurityManagerDaoException {
    LdapOperation ldap = null;
    ArrayList list = new ArrayList();

    try {
      ldap = getLdap();

      String userCtxDN = getUserDNSuffix();
      ldap.searchSubtree(userCtxDN,
                         "(&(deleted=" + undel + ")(|(deptId=" + parentId +
                         ")(deptId=" + deptId + ")(deptId=" + parentId + "))");

      while (ldap.nextResult()) {
        UserDO usr = createUserFromResult(ldap);
        list.add(usr);
      }

    }
    catch (Exception e) {
      throw new SecurityManagerDaoException("Failed to count users", e);
    }
    finally {
      closeLdap(ldap);
    }

    return list;

  }

  public List getUserListBydeptId(int deptId, int deleted) throws
      SecurityManagerDaoException {

    LdapOperation ldap = null;
    ArrayList list = new ArrayList();
    //System.out.println("this is a test for manager!");

    try {
      ldap = getLdap();

      String userCtxDN = getUserDNSuffix();
      ldap.searchSubtree(userCtxDN,
                         "(&(deleted=" + deleted + ")(deptId=" + deptId + "))");

      while (ldap.nextResult()) {
        UserDO usr = createUserFromResult(ldap);
        list.add(usr);
      }

    }
    catch (Exception e) {
      throw new SecurityManagerDaoException("Failed to count users", e);
    }
    finally {
      closeLdap(ldap);
    }

    return list;
  }

  /**
   * This method return specified range of users in the system.
   *
   * @param index start index
   * @param count count
   *
   * @return Return a vector of strings holding the user identification key .
   * @throws SecurityManagerDaoException
   */
  public Vector getUserList(int index, int count) throws
      SecurityManagerDaoException {

    LdapOperation ldap = null;
    Vector vec = new Vector();

    try {
      ldap = getLdap();

      String userCtxDN = getUserDNSuffix();
      ldap.searchSubtree(userCtxDN, "objectclass=inetOrgPerson");

      int nIdx = 0;
      int nCnt = 0;

      while (ldap.nextResult()) {
        nIdx++;
        if (nIdx >= index) {
          UserDO usr = createUserFromResult(ldap);
          vec.add(usr);

          nCnt++;
          if (nCnt >= count)
            break;
        }
      }

    }
    catch (Exception e) {
      throw new SecurityManagerDaoException("Failed to count users", e);
    }
    finally {
      closeLdap(ldap);
    }

    return vec;
  }

  /**
   * Return the number of user in the system.
   *
   * @return
   *      Return the number of users in the system.
   * @throws SecurityManagerDaoException
   */
  public int getUsersCount() throws SecurityManagerDaoException {

    LdapOperation ldap = null;
    int nCount = 0;

    try {
      ldap = getLdap();

      String userCtxDN = getUserDNSuffix();
      ldap.searchSubtree(userCtxDN, "objectclass=inetOrgPerson");

      while (ldap.nextResult()) {
        nCount++;
      }

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Failed to count users", e);
    }
    finally {
      closeLdap(ldap);
    }

    return nCount;
  }




  //---------------------------------------------------------------
  // Relationship Management
  //---------------------------------------------------------------

  /**
   * remove one of the user's relationship table
   * @param usr
   * @param relationship - the String of the relationship
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public void removeRelationship(Object usr, String relationship) throws
      ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;

    String usrname = getUserName(usr);
    String usrdn = calcUserDN(usrname);

    try {
      ldap = getLdap();

      // check if the relationship exist in the user
      String searchDN = getUserDNSuffix();
      String searchFilter = "(&(" + getUserDNPreffix() + usrname +
          ")(eaRelationship=" + relationship + "))";

      ldap.searchOneLevel(searchDN, searchFilter);
      if (ldap.nextResult()) {
//                    throw new ObjectNotExistException(relationship + " not exist in this user");

        BasicAttribute attr = new BasicAttribute("eaRelationship", relationship);
        ModificationItem mod = new ModificationItem(DirContext.REMOVE_ATTRIBUTE,
            attr);
        ModificationItem[] mods = {
            mod};
        LdapOperation.modifyAttributes(usrdn, mods);
      }

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException(
          "Can not remove the relationship of the user : " + usrdn, e);

    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * You can use this function to create and update the relation. if the relation is already exist in the table, remove is first and add the new one.
   * in relation, the permissionID is equal to the primary key, one permissionID has only one relationship item.
   * @param user
   * @param relationship
   * @throws SecurityManagerDaoException
   * @throws ObjectAlreadyExistException
   */
  public void setRelationship(UserDO user, String relationship) throws
      SecurityManagerDaoException, ObjectNotExistException,
      ObjectAlreadyExistException {
    LdapOperation ldap = null;
    String username = user.getUserName();
    String usrdn = calcUserDN(username);

    try {
      ldap = getLdap();

      int pos = relationship.indexOf("-::-");
      String newPermID = relationship.substring(0, pos);
      Vector oldPerm = getPermissionList(user);
      Iterator it = oldPerm.iterator();
      for (int i = 0; i < oldPerm.size(); i++) {
        PermissionDO pdo = (PermissionDO) it.next();
        if (newPermID.equals(pdo.getPermissionID())) {
          String removeRelationship = getUserRelationship(user, newPermID);
          removeRelationship(user, removeRelationship);
        }
      }

      BasicAttribute attr = new BasicAttribute("eaRelationship", relationship);
      ModificationItem mod = new ModificationItem(DirContext.ADD_ATTRIBUTE,
                                                  attr);
      ModificationItem[] mods = {
          mod};
      LdapOperation.modifyAttributes(usrdn, mods);

    }
    catch (NamingException ex) {
      throw new SecurityManagerDaoException(
          "Can not remove the relationship of the user : " + usrdn, ex);
    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * get the relationship String from the PermissionID that the user's have.
   * The relationship like: permissionID-::-DataRange1::DataRange2
   * @param user
   * @param permissionID
   * @return
   */
  public String getUserRelationship(UserDO user, String permissionID) {
    String relationship = new String();

    LdapOperation ldap = null;
    String username = user.getUserName();
//        String usrdn = calcUserDN(username);

    try {
      ldap = getLdap();

      String searchDN = getUserDNSuffix();
      String searchFilter = getUserDNPreffix() + username;

      String[] attr = {
          "eaRelationship"};
      ldap.setReturningAttributes(attr);
      ldap.searchOneLevel(searchDN, searchFilter);

      Set setRelationshipList = null;
      if (ldap.nextResult()) {
        setRelationshipList = ldap.getAllResultAttributeValues("eaRelationship");
      }
      ldap.resetSearch();

      if (setRelationshipList != null) {
        Iterator it = setRelationshipList.iterator();

        while (it.hasNext()) {
          String rs = (String) it.next();
          int pos = rs.indexOf("-::-");
          String pid = rs.substring(0, pos);
          if (permissionID.equals(pid)) {
            relationship = rs;
          }
        }
      }

    }
    catch (NamingException ex) {
      ex.printStackTrace();
    }

    return relationship;
  }

  /**
   * get the DataRange set through the permissionID which the you have.
   * the RangeDO are in the set.
   * @param user
   * @param permissionID
   * @return
   * @throws SecurityManagerDaoException
   * @throws ObjectNotExistException
   */
  public Vector getRangeByPermissionID(UserDO user, String permissionID) throws
      SecurityManagerDaoException, ObjectNotExistException {
    LdapOperation ldap = null;

    Set vec = new HashSet();

    try {
      ldap = getLdap();

      String relationship = getUserRelationship(user, permissionID);
      int position = relationship.indexOf("-::-");
      String range = relationship.substring(position + 4, relationship.length());
      String[] rangeID = range.split("::");

      for (int i = 0; i < rangeID.length; i++) {
        try {
          String rangeCtxDN = getRangeDirectoryContext();
          String filter = "(&(objectClass=ecDataRange)(eaDataRangeID=" +
              rangeID[i] + "))";

          ldap.searchSubtree(rangeCtxDN, filter);
          if (ldap.nextResult()) {
            RangeDO rangedo = RangeManager.lookupRange(rangeID[i]);
            vec.add(rangedo);
          }
          else {
//                            DataRangeID.addElement(rangeID[i]);
          }
        }
        catch (NamingException e1) {
          System.out.println("Something is wrong ");
          e1.printStackTrace();
        }
      }
    }
    catch (NamingException e) {

      throw new SecurityManagerDaoException("Can not get relationship list of " +
                                            user, e);

    }
    finally {
      closeLdap(ldap);
    }

    Vector dataRange = new Vector();
    Iterator it = vec.iterator();
    for (int i = 0; i < vec.size(); i++) {
      RangeDO rangedo = (RangeDO) it.next();
      dataRange.addElement(rangedo);
    }

    return dataRange;
  }

  //---------------------------------------------------------------
  //  Role Management
  //---------------------------------------------------------------

  /**
   * set role to group
   * @param usr user object
   * @param role role to add
   * @throws ObjectAlreadyExistException , SecurityManagerDaoException
   */
  public void setRole(Object usr, Object role) throws ObjectNotExistException,
      ObjectAlreadyExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;
    String usrname = getUserName(usr);
    String usrdn = calcUserDN(usrname);

    String roledn;

    try {
      ldap = getLdap();

      roledn = RoleLdapDAO.lookupRoleDN(ldap, role);

      // check if the role has alreay exist in the group
      String searchDN = getUserDNSuffix();
      String searchFilter = "(&(" + getUserDNPreffix() + usrname +
          ")(eaRoleDN=" + roledn + "))";
      ldap.searchOneLevel(searchDN, searchFilter);
      if (ldap.nextResult())
        throw new ObjectAlreadyExistException(roledn +
                                              " already exist in this user");

      ldap.resetSearch();

      // iplanet use nsRoleDN attibute to store role info
      BasicAttribute attr = new BasicAttribute("eaRoleDN", roledn);
      ModificationItem mod = new ModificationItem(DirContext.ADD_ATTRIBUTE,
                                                  attr);
      ModificationItem[] mods = {
          mod};
      LdapOperation.modifyAttributes(usrdn, mods);

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not set role of group : " +
                                            usrdn, e);

    }
    finally {
      closeLdap(ldap);
    }

  }

  /**
   * remove role from group
   * @param usr user object
   * @param role role to remove
   * @throws ObjectNotExistException , SecurityManagerDaoException
   */
  public void removeRole(Object usr, Object role) throws
      ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;

    String usrname = getUserName(usr);
    String usrdn = calcUserDN(usrname);

    String roledn;

    try {
      ldap = getLdap();

      roledn = RoleLdapDAO.lookupRoleDN(ldap, role);

      // check if the role exist in the user
      String searchDN = getUserDNSuffix();
      String searchFilter = "(&(" + getUserDNPreffix() + usrname +
          ")(eaRoleDN=" + roledn + "))";

      ldap.searchOneLevel(searchDN, searchFilter);
      if (!ldap.nextResult())
        throw new ObjectNotExistException(roledn + " not exist in this user");

      BasicAttribute attr = new BasicAttribute("eaRoleDN", roledn);
      ModificationItem mod = new ModificationItem(DirContext.REMOVE_ATTRIBUTE,
                                                  attr);
      ModificationItem[] mods = {
          mod};
      LdapOperation.modifyAttributes(usrdn, mods);

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not set role of user : " +
                                            usrdn, e);

    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * get roles belong to the group
   * @param usr user object
   * @return vector containg roles assigned to the user
   * @throws SecurityManagerDaoException
   */
  public Vector getRoleList(Object usr) throws SecurityManagerDaoException {

    LdapOperation ldap = null;

    String usrname = getUserName(usr);
    String usrdn = calcUserDN(usrname);

    Vector vec = new Vector();

    try {
      ldap = getLdap();

      // check if the usr has alreay exist in the group
      String searchDN = getUserDNSuffix();
      String searchFilter = getUserDNPreffix() + usrname;

      String[] attr = {
          "eaRoleDN"};
      ldap.setReturningAttributes(attr);
      ldap.searchOneLevel(searchDN, searchFilter);

      Set setRoleDN = null;

      if (ldap.nextResult()) {
        setRoleDN = ldap.getAllResultAttributeValues("eaRoleDN");
      }

      ldap.resetSearch();
      if (setRoleDN != null) {

        Iterator it = setRoleDN.iterator();
        String attrs[] = {
            "cn", "description"};

        while (it.hasNext()) {
          String rdn = (String) it.next();
          try {
            Attributes atts = ldap.getDirContext().getAttributes(rdn, attrs);
            RoleDO role = RoleLdapDAO.createRoleFromAttributes(atts);
            if (role != null)
              vec.add(role);

          }
          catch (NamingException e) {
            try {
              BasicAttribute ba = new BasicAttribute("eaRoleDN", rdn);
              ModificationItem mod = new ModificationItem(DirContext.
                  REMOVE_ATTRIBUTE, ba);
              ModificationItem[] mods = {
                  mod};
              LdapOperation.modifyAttributes(usrdn, mods);

            }
            catch (NamingException e1) {
            }
          }
          ldap.resetSearch();
        }
      }

    }
    catch (NamingException e) {

      throw new SecurityManagerDaoException("Can not get role list of " + usr,
                                            e);

    }
    finally {
      closeLdap(ldap);
    }

    return vec;
  }

  /**
   * clear all roles
   * @param usr user to clear roles
   * @throws SecurityManagerDaoException
   */
  public void clearRoles(Object usr) throws SecurityManagerDaoException {
    LdapOperation ldap = null;

    String usrname = getUserName(usr);
    String usrdn = calcUserDN(usrname);

    try {
      ldap = getLdap();
      LdapOperation.modifyElementAttribute(usrdn, "eaRoleDN", (String)null);

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can clear user.", e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  //------------------------------------------------------------------------------
  // Data Range Management
  //------------------------------------------------------------------------------

  /**
   * set user's Data Range.
   * @param user - user object
   * @param range - DataRange Object
   * @throws ObjectNotExistException
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public void setRange(Object user, Object range) throws
      ObjectNotExistException, ObjectAlreadyExistException,
      SecurityManagerDaoException {
    LdapOperation ldap = null;
    String userName = getUserName(user);
    String userDN = calcUserDN(userName);

    String rangeDN;
    try {
      ldap = getLdap();
      rangeDN = RangeLdapDAO.searchRangeDN(ldap, range);

      //check if the Range exist in this user
      String searchDN = getUserDNSuffix();
      String searchFilter = "(&(" + getUserDNPreffix() + userName +
          ")(eaDataRangeDN=" + rangeDN + "))";
      ldap.searchOneLevel(searchDN, searchFilter);
      if (ldap.nextResult())
        throw new ObjectAlreadyExistException(rangeDN +
                                              " already exist in this user");
      ldap.resetSearch();

      //Add the range to the user
      BasicAttribute attr = new BasicAttribute("eaDataRangeDN", rangeDN);
      ModificationItem mod = new ModificationItem(DirContext.ADD_ATTRIBUTE,
                                                  attr);
      ModificationItem[] mods = {
          mod};
      LdapOperation.modifyAttributes(userDN, mods);
    }
    catch (NamingException ex) {
      throw new SecurityManagerDaoException("Can not set Range of user : " +
                                            userDN, ex);
    }
  }

  /**
   * remove range from a given user
   * @param user - user object
   * @param range - data range object
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public void removeRange(Object user, Object range) throws
      ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;
    String userName = getUserName(user);
    String userDN = calcUserDN(userName);

    String rangeDN;
    try {
      ldap = getLdap();
      rangeDN = RangeLdapDAO.searchRangeDN(ldap, range);

      //check if the Range exist in this user
      String searchDN = getUserDNSuffix();
      String searchFilter = "(&(" + getUserDNPreffix() + userName +
          ")(eaDataRangeDN=" + rangeDN + "))";
      ldap.searchOneLevel(searchDN, searchFilter);
      if (!ldap.nextResult())
        throw new ObjectNotExistException(rangeDN +
                                          " does not exist in this user");
      ldap.resetSearch();

      //Add the range to the user
      BasicAttribute attr = new BasicAttribute("eaDataRangeDN", rangeDN);
      ModificationItem mod = new ModificationItem(DirContext.REMOVE_ATTRIBUTE,
                                                  attr);
      ModificationItem[] mods = {
          mod};
      LdapOperation.modifyAttributes(userDN, mods);
    }
    catch (NamingException ex) {
      throw new SecurityManagerDaoException("Can not remove Range of user : " +
                                            userDN, ex);
    }
  }

  public Vector getRangeList(Object user) throws SecurityManagerDaoException {
    LdapOperation ldap = null;

    String usrname = getUserName(user);
    String usrdn = calcUserDN(usrname);

    Vector vec = new Vector();

    try {
      ldap = getLdap();

      String searchDN = getUserDNSuffix();
      String searchFilter = getUserDNPreffix() + usrname;

      String[] attr = {
          "eaDataRangeDN"};
      ldap.setReturningAttributes(attr);
      ldap.searchOneLevel(searchDN, searchFilter);

      Set setRangeDN = null;
      if (ldap.nextResult()) {
        setRangeDN = ldap.getAllResultAttributeValues("eaDataRangeDN");
      }
      ldap.resetSearch();

      if (setRangeDN != null) {
        Iterator it = setRangeDN.iterator();
        String[] attrs = {
            "eaDataRangeID", "cn", "businessCategory", "description"};

        while (it.hasNext()) {
          String rangeDN = (String) it.next();
          try {
            Attributes atts = ldap.getDirContext().getAttributes(rangeDN, attrs);
            RangeDO range = RangeLdapDAO.createRangeFromAttributes(atts);
            if (range != null)
              vec.add(range);
          }
          catch (NamingException ex) {
            try {
              BasicAttribute ba = new BasicAttribute("eaDataRangeID", rangeDN);
              ModificationItem mod = new ModificationItem(DirContext.
                  REMOVE_ATTRIBUTE, ba);
              ModificationItem[] mods = {
                  mod};
              LdapOperation.modifyAttributes(usrdn, mods);
            }
            catch (NamingException e) {
              System.out.println("Can't remove range of user!");
            }
          }
          ldap.resetSearch();
        }
      }
    }
    catch (NamingException ne) {
      throw new SecurityManagerDaoException("Can not get role list of " + user,
                                            ne);
    }
    finally {
      closeLdap(ldap);
    }
    return vec;
  }

  /**
   * get groups the user belonged to
   * @param usr user to retrieve
   */
  public Vector getDepartmentList(Object usr) throws
      SecurityManagerDaoException {

    LdapOperation ldap = null;
    Vector vec = new Vector();
    String usrname = getUserName(usr);
    String usrdn = calcUserDN(usrname); // get the user dn

    try {
      ldap = getLdap();
      String groupCtxDN = sc.groupCtxDN;

      // construct the filter
      StringBuffer filter = new StringBuffer("(&(uniquemember=");
      //filter.append(usrdn).append(")(objectClass=groupOfUniqueNames))");
      filter.append(usrdn).append(")(objectClass=ecDepartment))");

      ldap.searchSubtree(groupCtxDN, filter.toString());

      while (ldap.nextResult()) {
        DeptDO dpt = DeptLdapDAO.createDepartmentFromLdapResult(
            ldap);
        if (dpt != null)
          vec.add(dpt);
      }
    }
    catch (Exception e) {
      throw new SecurityManagerDaoException("Failed to count users", e);
    }
    finally {
      closeLdap(ldap);
    }

    return vec;

  }

  public Vector searchUsers(Map filter) throws SecurityManagerDaoException {
    Vector result = new Vector();
    LdapOperation ldap = null;
    try {
      ldap = getLdap();
      String s = (String) filter.get("uid");
      String s1 = (String) filter.get("name");
      String s2 = (String) filter.get("regionID");
      String s3 = (String) filter.get("roleID");
      StringBuffer stringbuffer = new StringBuffer("(&(objectClass=ecUser)");
      if (s != null)
        stringbuffer.append("(uid=").append(s).append(")");
      if (s1 != null)
        stringbuffer.append("(cn=").append(s1).append(")");
      if (s2 != null)
        stringbuffer.append("(eaRegionID=").append(s2).append(")");
      Object obj = null;
      if (s3 != null && !s3.equalsIgnoreCase("*")) {
        String s4 = RoleLdapDAO.lookupRoleDN(ldap, s3);
        if (s4 != null)
          stringbuffer.append("(eaRoleDN=").append(s4).append(")");
      }
      stringbuffer.append(")");
      String s5 = stringbuffer.toString();
      //String s6 = SecurityManagerDaoFactor.getProperty("principalDNSuffix");
      String s6 = getUserDNSuffix();

      System.out.println("The filter is: " + s5);
      //ldap.searchOneLevel(s6, s5);
      ldap.searchSubtree(s6, s5);
      //UserDO user;
      while (ldap.nextResult()) {
        UserDO usr = createUserFromResult(ldap);
        result.add(usr);
      }
    }
    catch (Exception exception) {
      throw new SecurityManagerDaoException("Failed to count users", exception);
    }
    finally {
      closeLdap(ldap);
    }
    return result;
  }

  public void lockUser(UserDO user) throws ObjectNotExistException,
      SecurityManagerDaoException {
    String userId = user.getUserId();

    LdapOperation ldap = null;
    UserDO oldUser = null;

    try {
      ldap = getLdap();

      String userDN = calcUserDN(userId);
//            System.out.println(userDN);
//            System.out.println("---------");

      // check if the user exist
      oldUser = lookupUser(userId);

      if (oldUser == null)
        throw new ObjectNotExistException(user + " not exist in directory.");

      // compare and modify attributes
      Vector vec = new Vector();

      // check if the object has required schema
      String[] attids = {
          "objectClass"};

      // System.out.println(userDN);

      Attributes attrs = ldap.getDirContext().getAttributes(userDN, attids);
      NamingEnumeration valueEnum = attrs.get("objectClass").getAll();
      boolean bfound = false;
      while (valueEnum.hasMore()) {
        String value;
        value = (String) valueEnum.next();
        if (value.equalsIgnoreCase("ecUser"))
          bfound = true;
      }

      if (!bfound)
        vec.add(new ModificationItem(DirContext.ADD_ATTRIBUTE,
                                     new BasicAttribute("objectClass", "ecUser")));

//            if (user.getProperty("PasswdExpiredTime") != null)
//                            vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
//                                    new BasicAttribute("eaPasswdExpiredTime", (String)user.getProperty("PasswdExpiredTime"))));

//            System.out.println("The lock flage is: " + user.getProperty("LockFlag"));

      if (user.getProperty("LockFlag") != null)
        vec.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("eaLockFlag",
            (String) user.getProperty("LockFlag"))));

      ModificationItem[] mods = new ModificationItem[1];
      LdapOperation.modifyAttributes(userDN,
                                     (ModificationItem[]) vec.toArray(mods));

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Count not create user : " + user,
                                            e);
    }
    finally {
      closeLdap(ldap);
    }

  }

  /**
   * Get the relationship list of the user
   * @param user
   * @return
   * @throws SecurityManagerDaoException
   */

  public Vector getPermissionList(UserDO user) throws
      SecurityManagerDaoException, ObjectNotExistException {

    LdapOperation ldap = null;

    String usrname = user.getUserName();
    String usrdn = calcUserDN(usrname);

    Vector vec = new Vector();

    try {
      ldap = getLdap();

      // check if the usr has alreay exist in the group
      String searchDN = getUserDNSuffix();
      String searchFilter = getUserDNPreffix() + usrname;

      String[] attr = {
          "eaRelationship"};
      ldap.setReturningAttributes(attr);
      ldap.searchOneLevel(searchDN, searchFilter);

      Set setRelationshipList = null;
      if (ldap.nextResult()) {
        setRelationshipList = ldap.getAllResultAttributeValues("eaRelationship");
      }

      ldap.resetSearch();

      if (setRelationshipList != null) {
        Iterator it = setRelationshipList.iterator();
        String attrs[] = {
            "eaPermissionID"};
        while (it.hasNext()) {
          String relationship = (String) it.next();
          int position = relationship.indexOf("-::-");
          String permissionID = relationship.substring(0, position);

          try {
            String permissionCtxDN = getPermissionDirectoryContext();
            String filter = "(&(objectClass=ecPermission)(eaPermissionID=" +
                permissionID + "))";
            ldap.searchSubtree(permissionCtxDN, filter);
            if (ldap.nextResult()) {
              PermissionDO pdo = PermissionManager.lookupPermission(
                  permissionID);
              vec.addElement(pdo);
            }
            else {
              BasicAttribute ba = new BasicAttribute("eaRelationship",
                  relationship);
              ModificationItem mod = new ModificationItem(DirContext.
                  REMOVE_ATTRIBUTE, ba);
              ModificationItem[] mods = {
                  mod};
              LdapOperation.modifyAttributes(usrdn, mods);
            }
          }
          catch (NamingException e1) {
            System.out.println("Something is wrong ");
            e1.printStackTrace();
          }
        }
      }
    }
    catch (NamingException e) {

      throw new SecurityManagerDaoException("Can not get relationship list of " +
                                            user, e);

    }
    finally {
      closeLdap(ldap);
    }

    return vec;
  }

  public Vector getRangeList(UserDO user) throws SecurityManagerDaoException,
      ObjectNotExistException {

    LdapOperation ldap = null;

    String usrname = user.getUserName();
    String usrdn = calcUserDN(usrname);

//            Vector vec = new Vector();
    Set vec = new HashSet();

//        System.out.println("here is in range list [" + usrdn +"]");

    try {
      ldap = getLdap();

      // check if the usr has alreay exist in the group
      String searchDN = getUserDNSuffix();
      String searchFilter = getUserDNPreffix() + usrname;

      String[] attr = {
          "eaRelationship"};
      ldap.setReturningAttributes(attr);
      ldap.searchOneLevel(searchDN, searchFilter);

      Set setRelationshipList = new HashSet();
      if (ldap.nextResult()) {
        setRelationshipList = ldap.getAllResultAttributeValues("eaRelationship");
      }

      ldap.resetSearch();

      if (setRelationshipList != null) {
        Iterator it = setRelationshipList.iterator();
        String attrs[] = {
            "eaDataRangeID"};
        Vector DataRangeID = new Vector();

        while (it.hasNext()) {
          String relationship = (String) it.next();
          int position = relationship.indexOf("-::-");
          String range = relationship.substring(position + 4,
                                                relationship.length());
          String[] rangeID = range.split("::");

          for (int i = 0; i < rangeID.length; i++) {
            try {
              String rangeCtxDN = getRangeDirectoryContext();
              String filter = "(&(objectClass=ecDataRange)(eaDataRangeID=" +
                  rangeID[i] + "))";

//                                System.out.println("------" + filter + "------------");
              ldap.searchSubtree(rangeCtxDN, filter);
              if (ldap.nextResult()) {
//                                DataRangeID.addElement(rangeID[i]);
                RangeDO rangedo = RangeManager.lookupRange(rangeID[i]);
//                                PermissionDO pdo= PermissionManager.lookupPermission(rangeID);
                vec.add(rangedo);
              }
              else {
                DataRangeID.addElement(rangeID[i]);
//                                BasicAttribute ba = new BasicAttribute("eaRelationship",relationship);
//                                ModificationItem mod = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, ba);
//                                ModificationItem[] mods = {mod};
//                                ldap.modifyAttributes(usrdn, mods);
              }
            }
            catch (NamingException e1) {
              System.out.println("Something is wrong ");
              e1.printStackTrace();
            }
          }
        }
      }
    }
    catch (NamingException e) {

      throw new SecurityManagerDaoException("Can not get relationship list of " +
                                            user, e);

    }
    finally {
      closeLdap(ldap);
    }

    Vector dataRange = new Vector();
    Iterator it = vec.iterator();
    for (int i = 0; i < vec.size(); i++) {
      RangeDO rangedo = (RangeDO) it.next();
      dataRange.addElement(rangedo);
    }

    return dataRange;
  }

  //liangyaohan 2006-06-19
  public boolean lookupUserPassword(String dn,String pwd)
            throws SecurityManagerDaoException
  {
	//modify by laiping for validate user Password  
    boolean flag =true;
    LdapOperation ldap = null;
    try
    {
      ldap = new LdapOperation(dn, pwd.toCharArray());
    }
    catch(Exception e)
    {
      flag = false;
      if(e.getMessage().startsWith("[LDAP: error code 49 - 80090308: LdapErr: DSID-0C090334, comment: AcceptSecurityContext error, data 773, vece")){
    	  throw new SecurityManagerDaoException("�������", e);  
      }else{
    	  throw new SecurityManagerDaoException(dn + " password error !", e);  
      }
    }
    finally {
      closeLdap(ldap);
    }


    return flag;
  }

  //liangyaohan 2006-06-21
  //����û�ID�Ͳ�ѯ���ͣ���AD�в����û���Ϣ
  public Vector searchLdapUser(String userId,int iType) throws SecurityManagerDaoException {

    LdapOperation ldap = null;
    Vector vUser = new Vector();
    int i = 0;
    try {
      ldap = getLdap();

      vUser = ldap.searchAdUser(userId,iType) ;

    }
    catch (Exception e) {
      System.out.println(i);
      throw new SecurityManagerDaoException("Can not find user : " + userId, e);

    }
    finally {
      closeLdap(ldap);
    }

    return vUser;
  }

  public void test()
  {
    LdapOperation ldap = null;
    try {
      ldap = getLdap();


        try {
          String rangeCtxDN = "OU=nms_users,DC=NMS,DC=FJ,DC=CM";
          String filter = "objectClass=user";

          ldap.searchSubtree(rangeCtxDN, filter);
          while (ldap.nextResult()) {
            UserDO usr = createUserFromResult(ldap);
            System.out.println(usr.getNotes()) ;
          }
        }
        catch (NamingException e1) {
          System.out.println("Something is wrong ");
          e1.printStackTrace();
        }

    }
    catch (NamingException e) {

    }
    finally {
      closeLdap(ldap);
    }

  }
}
