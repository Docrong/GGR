/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis              2003-08-20             created
 */

package com.boco.eoms.security.service.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.*;

import com.boco.eoms.security.config.SystemConfig;
import com.boco.eoms.security.exception.ObjectAlreadyExistException;
import com.boco.eoms.security.exception.ObjectNotExistException;
import com.boco.eoms.security.exception.SecurityManagerDaoException;
import com.boco.eoms.security.service.dao.UserDAO;
import com.boco.eoms.security.service.model.UserDO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: all operation to user data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author weis
 * @version 1.0
 */
public class UserManager {

  private static SystemConfig sc = SystemConfig.getInstance();

  /**
   *Desc : create the corresponding DAO according to the configuration file
   * @return  UserDAO -- The data access object of user
   * @throws SecurityManagerDaoException
   */

  private static UserDAO createDao() throws SecurityManagerDaoException {
    try {
      //System.out.println(sc.userDAOClass);
      return (UserDAO) Class.forName(sc.userDAOClass).newInstance();
    }
    catch (ClassNotFoundException ex) {
      ex.printStackTrace();
      return null;
    }
    catch (IllegalAccessException ex) {
      ex.printStackTrace();
      return null;
    }
    catch (InstantiationException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  //----------------------------------------------------
  // user management
  //----------------------------------------------------

  /**
   * create user with the given User object
   * @param user - User Object
   * @return UserDO - User Object
   * @throws SecurityManagerDaoException
   * @throws ObjectAlreadyExistException
   */
  public static UserDO createUser(UserDO user) throws
      SecurityManagerDaoException, ObjectAlreadyExistException {
    UserDO ud = new UserDO();
    try {
      validation(user);
    }
    catch (Exception ex) {
      System.out.println("invalidation user!");
    }

    try {
      ud = createDao().createUser(user);
    }
    catch (ObjectAlreadyExistException ex) {
      throw new ObjectAlreadyExistException("���û�ID���Ѿ�����");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

    return ud;
  }

  /**
   * delete the user according to the given user object
   * @param user - User Object
   * @throws SecurityManagerDaoException
   * @throws ObjectNotExistException
   */
  public static void deleteUser(UserDO user) throws SecurityManagerDaoException,
      ObjectNotExistException {
    try {
      validation(user);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
//        catch(ObjectNotExistException ex)
//        {
//            throw new ObjectNotExistException();
//        }
    try {
      createDao().deleteUser(user);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("���û������ڣ�");
    }

  }

  /**
   * delete the user according to the user's name
   * @param userName - user's name
   * @throws SecurityManagerDaoException
   * @throws ObjectNotExistException
   */
  public static void deleteUser(String userId) throws
      SecurityManagerDaoException, ObjectNotExistException {
    try {
      createDao().deleteUser(userId);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("���û������ڣ�");
    }
  }

  /**
   * update the user's info according to the given user object
   * @param user - user data object
   * @throws SecurityManagerDaoException
   * @throws ObjectNotExistException
   */
  public static void updateUser(UserDO user) throws SecurityManagerDaoException,
      ObjectNotExistException {
    try {
      validation(user);
    }
    catch (SecurityManagerDaoException ex) {
      throw ex;
    }
    try {
      createDao().updateUser(user);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("���û������ڣ�");
    }

  }

  /**
   * Load all the user data and attributes according to the user's name
   * @param userName - user's name
   * @return userDO - user data object
   * @throws SecurityManagerDaoException
   * @throws ObjectNotExistException
   */
  public static UserDO lookupUser(String userId) throws
      SecurityManagerDaoException, ObjectNotExistException {
    UserDO ud = new UserDO();
    try {
      ud = createDao().lookupUser(userId);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("���û������ڣ�");
    }

    return ud;
  }

  public static boolean lookupPassword(String dn,String pwd) throws
      SecurityManagerDaoException, ObjectNotExistException {
    boolean flag = true ;
    try {
      flag = createDao().lookupUserPassword(dn,pwd) ;
    }
    catch (SecurityManagerDaoException ex) {
      flag = false;
      if(ex.getMessage().equals("�������")){
    	  throw new SecurityManagerDaoException("�������", ex);
      }else{
    	  throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
      }
    }
    catch (ObjectNotExistException ex) {
      flag = false;
      throw new ObjectNotExistException("���û����벻��ȷ��");
    }

    return flag ;
  }

  public static List lookupUsersByDeptIds(String deptIds) throws
      SecurityManagerDaoException {
    ArrayList list = new ArrayList();
    try {
      list = (ArrayList) createDao().lookupUsersByDeptIds(deptIds);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

    return list;
  }

  public static List lookupUsersByDeptIds(String deptIds,int deleted) throws
     SecurityManagerDaoException {
   ArrayList list = new ArrayList();
   try {
     list = (ArrayList) createDao().lookupUsersByDeptIds(deptIds,deleted);
   }
   catch (SecurityManagerDaoException ex) {
     throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
   }

   return list;
 }


  public static List lookupUsersByName(String userName) throws
      SecurityManagerDaoException {
    ArrayList list = new ArrayList();
    try {
      list = (ArrayList) createDao().lookupUsersByName(userName);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

    return list;
  }

  public static UserDO lookupUser(int id) throws SecurityManagerDaoException,
      ObjectNotExistException {
    UserDO ud = new UserDO();
    try {
      ud = createDao().lookupUser(id);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("���û������ڣ�");
    }

    return ud;
  }

  public static UserDO lookupUser(String userId, int deleted) throws
      SecurityManagerDaoException, ObjectNotExistException {
    UserDO ud = new UserDO();
    try {
      ud = createDao().lookupUser(userId, deleted);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("���û������ڣ�");
    }

    return ud;
  }

  /**
   * Load all the user data and attributes. On success a reference on the user
   * is returned, otherwise NULL is returned.
   *
   * @param  name  User's full name.
   * @return Return a vector of all reference on a new created User object.
   * @throws ObjectNotExistException ,SecurityManagerDaoException
   */
  public static Vector lookupUserWithUserId(String userId) throws
      SecurityManagerDaoException, ObjectNotExistException {
    Vector vct;
    try {
      vct = createDao().lookupUserWithUserId(userId);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("���û������ڣ�");
    }

    return vct;
  }



  /**
   * Load all the user data and attributes according to the user's object
   * @param user - user data object
   * @return userDO - user data object
   * @throws SecurityManagerDaoException
   * @throws ObjectNotExistException
   */
  public static UserDO lookupUser(UserDO user) throws
      SecurityManagerDaoException, ObjectNotExistException {
    UserDO ud = new UserDO();
    try {
      ud = createDao().lookupUser(user);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("���û������ڣ�");
    }
    return ud;
  }

  /**
   * get all users
   * @return vector - user's list
   * @throws SecurityManagerDaoException
   */
  public static List getUserList(int deleted) throws SecurityManagerDaoException {
    ArrayList list = new ArrayList();
    try {
      list = (ArrayList)createDao().getUserList(deleted);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

    return list;
  }

  public static List getUserListBydeptId(int deptId,int deleted) throws SecurityManagerDaoException {
   ArrayList list = new ArrayList();
   try {
     list = (ArrayList)createDao().getUserListBydeptId(deptId,deleted);
   }
   catch (SecurityManagerDaoException ex) {
     throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
   }

   return list;
 }

 public static List getRUSelByParentId(int parentId, int defnull, int undel) throws SecurityManagerDaoException {
   ArrayList list = new ArrayList();
   try {
     list = (ArrayList)createDao().getRUSelByParentId(parentId, defnull, undel);
   }
   catch (SecurityManagerDaoException ex) {
     throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
   }

   return list;
 }

     public static List getRUSelByParAndId(int parentId, int deptId, int undel) throws SecurityManagerDaoException {
       ArrayList list = new ArrayList();
       try {
         list = (ArrayList)createDao().getRUSelByParentId(parentId, deptId, undel);
       }
       catch (SecurityManagerDaoException ex) {
         throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
       }

       return list;
     }



  /**
   * get the special range users
   * @param index - the beginning of the user's list result
   * @param count - the count of the users.
   * @return vector - the result of the special range users
   * @throws SecurityManagerDaoException
   */
  public static Vector getUserList(int index, int count) throws
      SecurityManagerDaoException {
    Vector ul = new Vector();
    try {
      ul = createDao().getUserList(index, count);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

    return ul;
  }

  /**
   * get the user's count.
   * @return int - the number of the users
   * @throws SecurityManagerDaoException
   */
  public static int getUsersCount() throws SecurityManagerDaoException {
    int uc;
    try {
      uc = createDao().getUsersCount();
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

    return uc;
  }

  public static void lockUser(UserDO user) throws ObjectNotExistException,
      SecurityManagerDaoException {
    try {
      createDao().lockUser(user);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("���û������ڣ�");
    }
  }

  /**
   * add the special properties of the user,
   * @param user
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static void addPropertiesOfUser(UserDO user) throws
      ObjectNotExistException, SecurityManagerDaoException {
    createDao().addPropertiesOfUser(user);
  }

  //------------------------------------------------------
  // Relationship management
  //------------------------------------------------------
  public static void setRelationship(UserDO user, String relationship) throws
      ObjectNotExistException, ObjectAlreadyExistException,
      SecurityManagerDaoException {
    try {
      createDao().setRelationship(user, relationship);
    }

    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("��ID�Ų����ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
  }

  public static void removeRelationship(Object usr, String relationship) throws
      ObjectNotExistException, SecurityManagerDaoException {
    try {
      createDao().removeRelationship(usr, relationship);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("��ID�Ų����ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
  }

  public static Vector getPermissionList(UserDO user) throws
      SecurityManagerDaoException, ObjectNotExistException {
    Vector result = new Vector();
    try {
      result = createDao().getPermissionList(user);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("��ID�Ų����ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

    return result;
  }

  public static Vector getRangeList(UserDO user) throws
      SecurityManagerDaoException, ObjectNotExistException {
    Vector result = new Vector();
    try {
      result = createDao().getRangeList(user);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("��ID�Ų����ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

    return result;
  }

  public static String getUserRelationship(UserDO user, String permissionID) throws
      SecurityManagerDaoException {
    String result;
    try {
      result = createDao().getUserRelationship(user, permissionID);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

    return result;
  }

  public static Vector getRangeByPermissionID(UserDO user, String permissionID) throws
      SecurityManagerDaoException, ObjectNotExistException {
    Vector result = new Vector();
    try {
      result = createDao().getRangeByPermissionID(user, permissionID);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("��ID�Ų����ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

    return result;
  }

  //------------------------------------------------------
  //  role management
  //------------------------------------------------------
  /**
   * set role to user
   * @param usr user object
   * @param role role to add
   * @throws ObjectAlreadyExistException , SecurityManagerDaoException
   */
  public static void setRole(Object usr, Object role) throws
      ObjectNotExistException, ObjectAlreadyExistException,
      SecurityManagerDaoException {
    try {
      createDao().setRole(usr, role);
    }
    catch (ObjectAlreadyExistException ex) {
      throw new ObjectAlreadyExistException("��ID���Ѿ�����");
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("��ID�Ų����ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
  }

  /**
   * remove role from user
   * @param usr user object
   * @param role role to remove
   * @throws ObjectNotExistException , SecurityManagerDaoException
   */
  public static void removeRole(Object usr, Object role) throws
      ObjectNotExistException, SecurityManagerDaoException {
    try {
      createDao().removeRole(usr, role);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("��ID�Ų����ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
  }

  /**
   * get roles belong to the user
   * @param usr user object
   * @return vector containg roles assigned to the user
   * @throws SecurityManagerDaoException
   */
  public static Vector getRoleList(Object usr) throws
      SecurityManagerDaoException {
    Vector rl = new Vector();
    try {
      rl = createDao().getRoleList(usr);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

    return rl;
  }

  /**
   * clear all roles
   * @param usr user to clear roles
   * @throws SecurityManagerDaoException
   */
  public static void clearRoles(Object usr) throws SecurityManagerDaoException {
    try {
      createDao().clearRoles(usr);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

  }

  // ------------------------------------------------
  //  department management
  // ------------------------------------------------

  /**
   * get department the user belonged to
   * @param usr user to retrieve
   */
  public static Vector getDepartmentList(Object usr) throws
      SecurityManagerDaoException {
    Vector result = new Vector();
    try {
      result = createDao().getDepartmentList(usr);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

    return result;
  }

  // ------------------------------------------------
  //  dataRange management
  // ------------------------------------------------

  /**
   * Set Data Range to user
   * @param usr User Object
   * @param range DataRange Object
   * @throws ObjectNotExistException
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */

  public static void setRange(Object usr, Object range) throws
      ObjectNotExistException, ObjectAlreadyExistException,
      SecurityManagerDaoException {
    try {
      createDao().setRange(usr, range);
    }
    catch (ObjectAlreadyExistException ex) {
      throw new ObjectAlreadyExistException("��ID���Ѿ�����");
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("��ID�Ų����ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }

  }

  /**
   * Remove Data Range from user
   * @param usr User Object
   * @param range DataRange Object
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public static void removeRange(Object usr, Object range) throws
      ObjectNotExistException, SecurityManagerDaoException {
    try {
      createDao().removeRange(usr, range);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("��ID�Ų����ڣ�");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
  }

  /**
   * Get Data Range belongs to user
   * @param usr User Object
   * @return  RangeList Data Range List belongs to user
   * @throws SecurityManagerDaoException
   */
//    public static Vector getRangeList(Object usr) throws SecurityManagerDaoException
//    {
//        Vector result = new Vector();
//        try {
//            result = createDao().getRangeList(usr);
//        }
//        catch(SecurityManagerDaoException ex)
//        {
//            throw new SecurityManagerDaoException("������˴������Ժ��ڲ���",ex);
//        }
//        return result;
//    }

  /**
   *Clear Data Range belongs to user
   * @param usr User Object
   * @throws SecurityManagerDaoException
   */
//    public void clearRange(Object usr) throws SecurityManagerDaoException;

  public static Vector searchUsers(Map filter) throws
      SecurityManagerDaoException {
    Vector result = new Vector();
    try {
      result = createDao().searchUsers(filter);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
    return result;
  }

  private static void validation(UserDO user) throws
      SecurityManagerDaoException {
    if (user.getUserName() == null || user.getUserName().equals("")) {
      throw new SecurityManagerDaoException("Invalid User ID or User Name.",
                                            new
                                            Exception("Invalid User ID or User  Name."));
    }
  }

  //liangyaohan 2006-06-21
  //����û�ID��AD�в����û���Ϣ
  public static Vector searchLdapUser(String userId) throws
      SecurityManagerDaoException, ObjectNotExistException {
    Vector vUser = null;
    try {
      vUser = createDao().searchLdapUser(userId,1);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("���û������ڣ�");
    }

    return vUser;
  }

  //liangyaohan 2006-06-21
  //����û�ID����ƽ��в�ѯ
  public static Vector searchLdapUserName(String userId) throws
      SecurityManagerDaoException, ObjectNotExistException {
    Vector vUser = null;
    try {
      vUser = createDao().searchLdapUser(userId,3);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("���û������ڣ�");
    }

    return vUser;
  }

  //liangyaohan 2006-06-21
  //����û���ƽ��в�ѯ
  public static Vector searchLdapUserByName(String userId) throws
      SecurityManagerDaoException, ObjectNotExistException {
    Vector vUser = null;
    try {
      vUser = createDao().searchLdapUser(userId,2);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("������˴������Ժ��ڲ���", ex);
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("���û������ڣ�");
    }

    return vUser;
  }



  public static void main(String[] args) {
//        UserDO user = new UserDO();

//        user.setPassword("password");
//        user.setName("web60");
    //user.setProperty("LockFlag","0");
//        user.setFullName("secUser2");
//        user.setProperty("homePostalAddress","web11 home address");
//        user.setProperty("PostalCode","200433");
//        user.setProperty("mobile","139000000");
//        user.setProperty("telephoneNumber","12345678");
//        user.setProperty("mail","webtest@boco.com.cn");
//        user.setProperty("description","test update");
//        user.setProperty("LogonTime","08:30-17:30-false");
//        user.setProperty("SecurityHost","192.*.*.*");
//        user.setProperty("ExpiredTime","2004-01-01");
//        user.setProperty("LockFlag","1");
//        user.setProperty("PasswdExpiredTime","2004-01-01");
//        user.setProperty("eaRegionID","wuxi");

    //String userLT =(String) user.getProperty("eaLogonTime");
    //String id = (String)user.getUserID();
    //System.out.println("LogonTime: " + userLT);
    //System.out.println("ID: " + id);

    //user.setProperty("eaLogonTime","20030824111111");
    /*Vector roles = new Vector();
             roles.addElement("Role001");
             roles.addElement("Role002");
             roles.addElement("Role003");*/

    try {

//            UserManager.setRelationship(user,"40008-::-1::2::3::4");
//            Vector permission = UserManager.getPermissionList(user);
//            Vector range = UserManager.getRangeList(user);
//            Iterator it = range.iterator();
//            for(int i=0; i<range.size(); i++)
//            {
//                RangeDO perm = (RangeDO)it.next();
//                System.out.println(i + " = [" + perm.getRangeID() + "]" );
//            }

      //UserManager.lockUser(user);
      //UserDO user = new UserDO("test1","TestUpdateFunc123");
      /*UserDO user = new UserDO("logon");
                   user.setProperty("description","test update");
                   user.setProperty("LogonTime","08:30-17:30-false");
                   user.setProperty("SecurityHost","127.*.*.*");
                   user.setProperty("ExpiredTime","2004-01-01");
                   UserManager.updateUser(user);*/

      //UserManager.setRole("weis","Role002");
//             Vector um = UserManager.getUserList();
//            System.out.println("test for display user!");
//            Map filter = new HashMap();
//            filter.put("uid","*");
//            filter.put("regionID","*");
//            //filter.put("roleID","webRole001");
//            Vector um = UserManager.searchUsers(filter);
//
//             for(int i=0;i<um.size();i++){
//                 UserDO u = (UserDO)um.elementAt(i);
//                 System.out.println("The user's name is : "+u.getName());
//                 System.out.println("The user's full name is :" + u.getFullName());
//
//                 }
//           UserManager.createUser(user);
      //UserManager.updateUser(user);
//            UserManager.deleteUser(user);
      //UserManager.deleteUser("TestManager");

      //UserManager.addPropertiesOfUser(user);
      //UserManager.setRange(user,"testRangeManager2");
      //UserManager.removeRange(user,"testRangeManager2");

      /*Vector rangeList = UserManager.getRangeList(user);
                   for(int i=0; i<rangeList.size();i++)
                   {
          RangeDO range = (RangeDO) rangeList.elementAt(i);
          System.out.println("ID: " + range.getRangeID());
          System.out.println("Name: " + range.getName());
          System.out.println("Category: " + range.getCategory());
          System.out.println("description: " + range.getComment());
          System.out.println("----------------------");
                   } */

      //UserDO ud = UserManager.lookupUser(user.getUserID());
      //System.out.println(ud.getUserID());
      /*System.out.println(ud.getName());
                   System.out.println(ud.getFullName());
                   System.out.println(ud.getProperty("mail"));
                   //int count = UserManager.getUsersCount();
                   //System.out.println("the count of users are : " + count );*/

      /*   Vector vec = UserManager.getDepartmentList("test1");
         for(int i=0; i<vec.size();i++)
         {
             DepartmentDO department = (DepartmentDO) vec.elementAt(i);
             System.out.println("ID: " + department.getDepartmentID());
             System.out.println("Name: " + department.getName());
             System.out.println("Desc: " + department.getComments());
         }*/
      HashMap filter = new HashMap();
      filter.put("uid", "*��*");
      filter.put("regionID", "Region_2");
      //filter.put("roleID", "1");
      Vector vctU = UserManager.searchUsers(filter);
      System.out.println("[" + vctU.size() + "]");
      System.out.println("Successfully ! ! ! !");
      Vector vct = UserManager.lookupUserWithUserId("*��*");
      for (int i = 0; i < vct.size(); i++) {
        UserDO udo = (UserDO) vct.get(i);
        //System.out.println(udo.getUserId()+":" +udo.getFullName());
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      System.out.println("Failed ! ! !");
    }
  }

}
