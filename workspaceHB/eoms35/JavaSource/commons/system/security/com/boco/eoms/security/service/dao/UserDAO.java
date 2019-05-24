/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis              2003-08-15       created
 */

package com.boco.eoms.security.service.dao;

import java.util.Map;
import java.util.Vector;
import java.util.*;

import com.boco.eoms.security.exception.ObjectAlreadyExistException;
import com.boco.eoms.security.exception.ObjectNotExistException;
import com.boco.eoms.security.exception.SecurityManagerDaoException;
import com.boco.eoms.security.service.model.UserDO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author weis
 * @version 1.0
 */

public interface UserDAO extends BaseDAO {
     /**
     * This is the method that creates a new user in the system, with all the
     * specified attributes.
     *
     * @param  usr  user to persist
     * @throws ObjectAlreadyExistException , SecurityManagerDaoException
     */
    public UserDO createUser(UserDO usr) throws ObjectAlreadyExistException, SecurityManagerDaoException;

    /**
     * This method removes a user from the system. All the user's attributes are
     * remove, and also all the related objects belonging to the user. On success,
     * true is returned and the user parameter is not longer valid. Return false
     * on any failure.
     *
     * @param  user  reference on the user to be deleted.
     * @throws ObjectNotExistException , SecurityManagerDaoException
     */
    public void deleteUser(UserDO user) throws ObjectNotExistException, SecurityManagerDaoException;


    /**
     * remove user by the given name
     *
     * @param name name of the user
     * @throws ObjectNotExistException ,SecurityManagerDaoException
     */
    public void deleteUser(String name) throws ObjectNotExistException, SecurityManagerDaoException;


    /**
     * update user information
     *
     * @param user user to update
     * @throws ObjectNotExistException , SecurityManagerDaoException
     */
    public void updateUser(UserDO user) throws ObjectNotExistException, SecurityManagerDaoException;


    /**
     * Load all the user data and attributes. On success a reference on the user
     * is returned, otherwise NULL is returned.
     *
     * @param  name  User's identification name.
     * @return Return a reference on a new created User object.
     * @throws ObjectNotExistException ,SecurityManagerDaoException
     */
    public UserDO lookupUser(String userId)
            throws ObjectNotExistException, SecurityManagerDaoException;

    public UserDO lookupUser(int id)
            throws ObjectNotExistException, SecurityManagerDaoException;

    public UserDO lookupUser(String userId,int deleted)
            throws ObjectNotExistException, SecurityManagerDaoException;

    public List lookupUsersByName(String userName)
            throws SecurityManagerDaoException;

    public List lookupUsersByDeptIds(String deptIds)
            throws SecurityManagerDaoException;
    public List lookupUsersByDeptIds(String deptIds,int deleted)
            throws SecurityManagerDaoException;
    public List getRUSelByParentId(int parentId, int defnull, int undel)
            throws SecurityManagerDaoException;
        public List getRUSelByParAndId(int parentId, int deptId, int undel)
            throws SecurityManagerDaoException;


    /**
     * Load all the user data and attributes. On success a reference on the user
     * is returned, otherwise NULL is returned.
     *
     * @param  name  User's full name.
     * @return Return a vector of all reference on a new created User object.
     * @throws ObjectNotExistException ,SecurityManagerDaoException
     */
    public Vector lookupUserWithUserId(String userId)
	throws ObjectNotExistException, SecurityManagerDaoException;

    /**
     * Load all the user's data and attributes. On success a reference on the user
     * is returned. otherwise NULL is returned
     * @param user - User Data Object.
     * @return Return a reference on a new created User Object.
     * @throws ObjectNotExistException
     * @throws SecurityManagerDaoException
     */
    public UserDO lookupUser(UserDO user)
            throws ObjectNotExistException, SecurityManagerDaoException;




    /**
     * This method return all users' keys in the system.
     *
     * @return Return a vector of strings holding the user identification key .
     * @throws SecurityManagerDaoException
     */
    public List getUserList(int deleted) throws SecurityManagerDaoException;

    /**
     * This method return specified range of users in the system.
     *
     * @param index start index
     * @param count count
     *
     * @return Return a vector of strings holding the user identification key .
     * @throws SecurityManagerDaoException
     */
    public Vector getUserList(int index, int count) throws SecurityManagerDaoException;
  public List getUserListBydeptId(int deptId, int deleted) throws SecurityManagerDaoException;

    /**
     * Return the number of user in the system.
     *
     * @return
     *      Return the number of users in the system.
     * @throws SecurityManagerDaoException
     */
    public int getUsersCount() throws SecurityManagerDaoException;

    public void addPropertiesOfUser(UserDO user)
            throws ObjectNotExistException, SecurityManagerDaoException;

    // ------------------------------------------------
    //  role management
    // ------------------------------------------------

    /**
     * set role to user
     * @param usr user object
     * @param role role to add
     * @throws ObjectAlreadyExistException , SecurityManagerDaoException
     */
    public void setRole(Object usr, Object role)
            throws ObjectNotExistException, ObjectAlreadyExistException, SecurityManagerDaoException;


    /**
     * remove role from user
     * @param usr user object
     * @param role role to remove
     * @throws ObjectNotExistException , SecurityManagerDaoException
     */
    public void removeRole(Object usr, Object role) throws ObjectNotExistException, SecurityManagerDaoException;


    /**
     * get roles belong to the user
     * @param usr user object
     * @return vector containg roles assigned to the user
     * @throws SecurityManagerDaoException
     */
    public Vector getRoleList(Object usr) throws SecurityManagerDaoException;

    /**
     * clear all roles
     * @param usr user to clear roles
     * @throws SecurityManagerDaoException
     */
    public void clearRoles(Object usr) throws SecurityManagerDaoException;

    // ------------------------------------------------
    //  department management
    // ------------------------------------------------

    /**
     * get department the user belonged to
     * @param usr user to retrieve
     */
   public Vector getDepartmentList(Object usr) throws SecurityManagerDaoException;

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

    public void setRange(Object usr,Object range)
        throws ObjectNotExistException, ObjectAlreadyExistException, SecurityManagerDaoException;

    /**
     * Remove Data Range from user
     * @param usr User Object
     * @param range DataRange Object
     * @throws ObjectNotExistException
     * @throws SecurityManagerDaoException
     */
    public void removeRange(Object usr,Object range) throws ObjectNotExistException, SecurityManagerDaoException;

    /**
     * Get Data Range belongs to user
     * @param usr User Object
     * @return  RangeList Data Range List belongs to user
     * @throws SecurityManagerDaoException
     */
    public Vector getRangeList(Object usr) throws SecurityManagerDaoException;

    /**
     *Clear Data Range belongs to user
     * @param usr User Object
     * @throws SecurityManagerDaoException
     */
//    public void clearRange(Object usr) throws SecurityManagerDaoException;

    public Vector searchUsers(Map filter)
        throws SecurityManagerDaoException;

    public void lockUser(UserDO user)
            throws ObjectNotExistException, SecurityManagerDaoException;

    //--------------------------------------------------
    // Relationship management
    //--------------------------------------------------
    public void setRelationship(UserDO user,String relationship)
            throws ObjectNotExistException, ObjectAlreadyExistException,SecurityManagerDaoException;

    public void removeRelationship(Object usr, String relationship)
                throws ObjectNotExistException, SecurityManagerDaoException;

    public Vector getPermissionList(UserDO user)
            throws SecurityManagerDaoException,ObjectNotExistException;

    public Vector getRangeList(UserDO user)
            throws SecurityManagerDaoException,ObjectNotExistException;

    public String getUserRelationship(UserDO user,String permissionID);

    public Vector getRangeByPermissionID(UserDO user,String permissionID)
        throws SecurityManagerDaoException,ObjectNotExistException;

    public boolean lookupUserPassword(String dn,String pwd)
            throws ObjectNotExistException, SecurityManagerDaoException;
    public Vector searchLdapUser(String userId,int iType) throws ObjectNotExistException,
                SecurityManagerDaoException;

}
