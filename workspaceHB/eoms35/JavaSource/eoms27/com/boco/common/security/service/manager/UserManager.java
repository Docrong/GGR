/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis              2003-08-20             created
 */

package com.boco.common.security.service.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.boco.common.security.config.SystemConfig;
import com.boco.common.security.exception.ObjectAlreadyExistException;
import com.boco.common.security.exception.ObjectNotExistException;
import com.boco.common.security.exception.SecurityManagerDaoException;
import com.boco.common.security.service.dao.UserDAO;
import com.boco.common.security.service.model.UserDO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: all operation to user data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 *
 * @author weis
 * @version 1.0
 */
public class UserManager {

    private static SystemConfig sc = SystemConfig.getInstance();

    /**
     * Desc : create the corresponding DAO according to the configuration file
     *
     * @return UserDAO -- The data access object of user
     * @throws SecurityManagerDaoException
     */

    private static UserDAO createDao() throws SecurityManagerDaoException {
        try {
            //System.out.println(sc.userDAOClass);
            return (UserDAO) Class.forName(sc.userDAOClass).newInstance();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            return null;
        } catch (InstantiationException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    //----------------------------------------------------
    // user management
    //----------------------------------------------------

    /**
     * create user with the given User object
     *
     * @param user - User Object
     * @return UserDO - User Object
     * @throws SecurityManagerDaoException
     * @throws ObjectAlreadyExistException
     */
    public static UserDO createUser(UserDO user)
            throws SecurityManagerDaoException, ObjectAlreadyExistException {
        UserDO ud = new UserDO();
        try {
            validation(user);
        } catch (Exception ex) {
            System.out.println("invalidation user!");
        }

        try {
            ud = createDao().createUser(user);
        } catch (ObjectAlreadyExistException ex) {
            throw new ObjectAlreadyExistException("此用户ID号已经存在");
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }

        return ud;
    }

    /**
     * delete the user according to the given user object
     *
     * @param user - User Object
     * @throws SecurityManagerDaoException
     * @throws ObjectNotExistException
     */
    public static void deleteUser(UserDO user)
            throws SecurityManagerDaoException, ObjectNotExistException {
        try {
            validation(user);
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }
//        catch(ObjectNotExistException ex)
//        {
//            throw new ObjectNotExistException();
//        }
        try {
            createDao().deleteUser(user);
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("该用户不存在！");
        }

    }

    /**
     * delete the user according to the user's name
     *
     * @param userName - user's name
     * @throws SecurityManagerDaoException
     * @throws ObjectNotExistException
     */
    public static void deleteUser(String userName)
            throws SecurityManagerDaoException, ObjectNotExistException {
        try {
            createDao().deleteUser(userName);
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("该用户不存在！");
        }
    }

    /**
     * update the user's info according to the given user object
     *
     * @param user - user data object
     * @throws SecurityManagerDaoException
     * @throws ObjectNotExistException
     */
    public static void updateUser(UserDO user)
            throws SecurityManagerDaoException, ObjectNotExistException {
        try {
            validation(user);
        } catch (SecurityManagerDaoException ex) {
            throw ex;
        }
        try {
            createDao().updateUser(user);
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("该用户不存在！");
        }

    }

    /**
     * Load all the user data and attributes according to the user's name
     *
     * @param userName - user's name
     * @return userDO - user data object
     * @throws SecurityManagerDaoException
     * @throws ObjectNotExistException
     */
    public static UserDO lookupUser(String userName)
            throws SecurityManagerDaoException, ObjectNotExistException {
        UserDO ud = new UserDO();
        try {
            ud = createDao().lookupUser(userName);
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("该用户不存在！");
        }

        return ud;
    }

    /**
     * Load all the user data and attributes. On success a reference on the user
     * is returned, otherwise NULL is returned.
     *
     * @param name User's full name.
     * @return Return a vector of all reference on a new created User object.
     * @throws ObjectNotExistException ,SecurityManagerDaoException
     */
    public static Vector lookupUserWithUserName(String name)
            throws SecurityManagerDaoException, ObjectNotExistException {
        Vector vct;
        try {
            vct = createDao().lookupUserWithUserName(name);
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("该用户不存在！");
        }

        return vct;
    }

    /**
     * Load all the user data and attributes according to the user's object
     *
     * @param user - user data object
     * @return userDO - user data object
     * @throws SecurityManagerDaoException
     * @throws ObjectNotExistException
     */
    public static UserDO lookupUser(UserDO user)
            throws SecurityManagerDaoException, ObjectNotExistException {
        UserDO ud = new UserDO();
        try {
            ud = createDao().lookupUser(user);
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("该用户不存在！");
        }
        return ud;
    }

    /**
     * get all users
     *
     * @return vector - user's list
     * @throws SecurityManagerDaoException
     */
    public static Vector getUserList() throws SecurityManagerDaoException {
        Vector ul = new Vector();
        try {
            ul = createDao().getUserList();
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }

        return ul;
    }

    /**
     * get the special range users
     *
     * @param index - the beginning of the user's list result
     * @param count - the count of the users.
     * @return vector - the result of the special range users
     * @throws SecurityManagerDaoException
     */
    public static Vector getUserList(int index, int count) throws SecurityManagerDaoException {
        Vector ul = new Vector();
        try {
            ul = createDao().getUserList(index, count);
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }

        return ul;
    }

    /**
     * get the user's count.
     *
     * @return int - the number of the users
     * @throws SecurityManagerDaoException
     */
    public static int getUsersCount() throws SecurityManagerDaoException {
        int uc;
        try {
            uc = createDao().getUsersCount();
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }

        return uc;
    }

    public static void lockUser(UserDO user)
            throws ObjectNotExistException, SecurityManagerDaoException {
        try {
            createDao().lockUser(user);
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("该用户不存在！");
        }
    }

    /**
     * add the special properties of the user,
     *
     * @param user
     * @throws ObjectNotExistException
     * @throws SecurityManagerDaoException
     */
    public static void addPropertiesOfUser(UserDO user)
            throws ObjectNotExistException, SecurityManagerDaoException {
        createDao().addPropertiesOfUser(user);
    }

    //------------------------------------------------------
    // Relationship management
    //------------------------------------------------------
    public static void setRelationship(UserDO user, String relationship)
            throws ObjectNotExistException, ObjectAlreadyExistException, SecurityManagerDaoException {
        try {
            createDao().setRelationship(user, relationship);
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("此ID号不存在！");
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }
    }

    public static void removeRelationship(Object usr, String relationship)
            throws ObjectNotExistException, SecurityManagerDaoException {
        try {
            createDao().removeRelationship(usr, relationship);
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("此ID号不存在！");
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }
    }

    public static Vector getPermissionList(UserDO user)
            throws SecurityManagerDaoException, ObjectNotExistException {
        Vector result = new Vector();
        try {
            result = createDao().getPermissionList(user);
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("此ID号不存在！");
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }

        return result;
    }

    public static Vector getRangeList(UserDO user)
            throws SecurityManagerDaoException, ObjectNotExistException {
        Vector result = new Vector();
        try {
            result = createDao().getRangeList(user);
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("此ID号不存在！");
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }

        return result;
    }

    public static String getUserRelationship(UserDO user, String permissionID)
            throws SecurityManagerDaoException {
        String result;
        try {
            result = createDao().getUserRelationship(user, permissionID);
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }

        return result;
    }

    public static Vector getRangeByPermissionID(UserDO user, String permissionID)
            throws SecurityManagerDaoException, ObjectNotExistException {
        Vector result = new Vector();
        try {
            result = createDao().getRangeByPermissionID(user, permissionID);
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("此ID号不存在！");
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }

        return result;
    }


    //------------------------------------------------------
    //  role management
    //------------------------------------------------------

    /**
     * set role to user
     *
     * @param usr  user object
     * @param role role to add
     * @throws ObjectAlreadyExistException , SecurityManagerDaoException
     */
    public static void setRole(Object usr, Object role)
            throws ObjectNotExistException, ObjectAlreadyExistException, SecurityManagerDaoException {
        try {
            createDao().setRole(usr, role);
        } catch (ObjectAlreadyExistException ex) {
            throw new ObjectAlreadyExistException("此ID号已经存在");
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("此ID号不存在！");
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }
    }


    /**
     * remove role from user
     *
     * @param usr  user object
     * @param role role to remove
     * @throws ObjectNotExistException , SecurityManagerDaoException
     */
    public static void removeRole(Object usr, Object role)
            throws ObjectNotExistException, SecurityManagerDaoException {
        try {
            createDao().removeRole(usr, role);
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("此ID号不存在！");
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }
    }


    /**
     * get roles belong to the user
     *
     * @param usr user object
     * @return vector containg roles assigned to the user
     * @throws SecurityManagerDaoException
     */
    public static Vector getRoleList(Object usr) throws SecurityManagerDaoException {
        Vector rl = new Vector();
        try {
            rl = createDao().getRoleList(usr);
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }

        return rl;
    }

    /**
     * clear all roles
     *
     * @param usr user to clear roles
     * @throws SecurityManagerDaoException
     */
    public static void clearRoles(Object usr) throws SecurityManagerDaoException {
        try {
            createDao().clearRoles(usr);
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }

    }

    // ------------------------------------------------
    //  department management
    // ------------------------------------------------

    /**
     * get department the user belonged to
     *
     * @param usr user to retrieve
     */
    public static Vector getDepartmentList(Object usr) throws SecurityManagerDaoException {
        Vector result = new Vector();
        try {
            result = createDao().getDepartmentList(usr);
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }

        return result;
    }

    // ------------------------------------------------
    //  dataRange management
    // ------------------------------------------------

    /**
     * Set Data Range to user
     *
     * @param usr   User Object
     * @param range DataRange Object
     * @throws ObjectNotExistException
     * @throws ObjectAlreadyExistException
     * @throws SecurityManagerDaoException
     */

    public static void setRange(Object usr, Object range)
            throws ObjectNotExistException, ObjectAlreadyExistException, SecurityManagerDaoException {
        try {
            createDao().setRange(usr, range);
        } catch (ObjectAlreadyExistException ex) {
            throw new ObjectAlreadyExistException("此ID号已经存在");
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("此ID号不存在！");
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }

    }

    /**
     * Remove Data Range from user
     *
     * @param usr   User Object
     * @param range DataRange Object
     * @throws ObjectNotExistException
     * @throws SecurityManagerDaoException
     */
    public static void removeRange(Object usr, Object range)
            throws ObjectNotExistException, SecurityManagerDaoException {
        try {
            createDao().removeRange(usr, range);
        } catch (ObjectNotExistException ex) {
            throw new ObjectNotExistException("此ID号不存在！");
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }
    }

    /**
     * Get Data Range belongs to user
     * @param usr User Object
     * @return RangeList Data Range List belongs to user
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
//            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！",ex);
//        }
//        return result;
//    }

    /**
     * Clear Data Range belongs to user
     *
     * @param usr User Object
     * @throws SecurityManagerDaoException
     */
//    public void clearRange(Object usr) throws SecurityManagerDaoException;
    public static Vector searchUsers(Map filter)
            throws SecurityManagerDaoException {
        Vector result = new Vector();
        try {
            result = createDao().searchUsers(filter);
        } catch (SecurityManagerDaoException ex) {
            throw new SecurityManagerDaoException("服务器端错误，请稍后在操作！", ex);
        }
        return result;
    }

    private static void validation(UserDO user)
            throws SecurityManagerDaoException {
        if (user.getName() == null || user.getName().equals("")) {
            throw new SecurityManagerDaoException("Invalid User ID or User Name.", new Exception("Invalid User ID or User  Name."));
        }
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
            filter.put("uid", "*陈*");
            filter.put("regionID", "Region_2");
            //filter.put("roleID", "1");
            Vector vctU = UserManager.searchUsers(filter);
            System.out.println("[" + vctU.size() + "]");
            System.out.println("Successfully ! ! ! !");
            Vector vct = UserManager.lookupUserWithUserName("*陈*");
            for (int i = 0; i < vct.size(); i++) {
                UserDO udo = (UserDO) vct.get(i);
                System.out.println(udo.getUserID() + ":" + udo.getFullName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed ! ! !");
        }
    }


}
