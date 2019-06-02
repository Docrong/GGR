/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis              2003-08-15       created
 */

package com.boco.eoms.security.service.dao;

import java.util.Vector;

import com.boco.eoms.security.exception.ObjectAlreadyExistException;
import com.boco.eoms.security.exception.ObjectNotExistException;
import com.boco.eoms.security.exception.SecurityManagerDaoException;
import com.boco.eoms.security.service.model.PermissionDO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author weis
 * @version 1.0
 */
public interface PermissionDAO {
    /**
     * create permission object
     *
     * @param id permission id , should take the format of [category].[name]
     * @param name permission name
     * @param cat permission category
     * @param desc permission description
     */
    public PermissionDO createPermission(String id, String name, String cat, String desc)
            throws ObjectAlreadyExistException, SecurityManagerDaoException;

    /**
     * create the permission with a permission object
     * @param permission    permission object
     * @return
     * @throws ObjectAlreadyExistException
     * @throws SecurityManagerDaoException
     */
    public PermissionDO createPermission(PermissionDO permission)
            throws ObjectAlreadyExistException, SecurityManagerDaoException;
    /**
     * create permission category
     *
     * @param name category name
     * @param desc category description
     */
    public void createCategory(String name, String desc) throws SecurityManagerDaoException;

    /**
     * delete permission
     *
     * @param name permisson name
     */
    public void deletePermission(String name)
            throws ObjectNotExistException, SecurityManagerDaoException;

    /**
     * update a permission object
     */
    public void updatePermission(PermissionDO perm)
            throws ObjectNotExistException, SecurityManagerDaoException;

    /**
     * lookup a permission object
     */
    public PermissionDO lookupPermission(String name)
            throws ObjectNotExistException, SecurityManagerDaoException;


    /**
     * get all permissons
     *
     * @return all permisson object
     */
    public Vector getAllPermissions()
            throws ObjectNotExistException, SecurityManagerDaoException;


    // -------------------------------------------------------------
    //  Role permission management
    // -------------------------------------------------------------

    /**
     * assign role permission
     */
    public void assignRolePermission(String perm, Vector roles)
            throws ObjectNotExistException, SecurityManagerDaoException;


    /**
     * remove permission assigned to role object
     */
   public void removeRolePermission(String perm, Vector roles)
            throws ObjectNotExistException, SecurityManagerDaoException;

    /**
     * get roles assigned to a permisson
     *
     * @param perm permission name
     * @return vector of roles assigned to the permisson
     */
    public Vector getAssignedRoles(String perm)
            throws ObjectNotExistException, SecurityManagerDaoException;

    /**
     * get permissions assigned to a role object
     * @param role role to retrieve
     */
    public Vector getRolePermissions(String role)
            throws ObjectNotExistException, SecurityManagerDaoException;

    public Vector getRangeByCategory(Object category)
             throws ObjectNotExistException, SecurityManagerDaoException;

    public Vector getAllCategory()
             throws ObjectNotExistException, SecurityManagerDaoException;
}
