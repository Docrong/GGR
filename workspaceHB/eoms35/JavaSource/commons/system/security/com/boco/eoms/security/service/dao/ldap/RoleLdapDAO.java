/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis            2003-08-15         created
 * V1.0.0.0   Wang Zhuo Wei   2003-08-19         amend
 */

package com.boco.eoms.security.service.dao.ldap;

import java.util.Vector;

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
import com.boco.eoms.security.service.dao.RoleDAO;
import com.boco.eoms.security.service.dao.ldap.factory.LdapOperation;
import com.boco.eoms.security.service.model.RoleDO;

/**
 * Ldap user dao implementation . Role dir context is configed in in the config
 * item roleCtxDN . Role use objectclass of organizationalRole , I will consider
 * use nsRole instead of organizationalRole to take advantage of role managed
 * acl.
 *
 * @author <a href="sangwei@boco.com.cn">weis</a>
 */
public class RoleLdapDAO
    extends BaseLdapDAO
    implements RoleDAO {

  protected static String ROLE_OBJECT_CLASS = "organizationalRole";

  private static SystemConfig sc = SystemConfig.getInstance();


  public RoleLdapDAO() {}

  /**
   * role class directory context
   * @return
   */
  public static String getRoleClassDirectoryContext() {
    return sc.rolesCtxDN;
  }

  /**
   * get role object class
   */
  protected String getRoleObjectClass() {
    return ROLE_OBJECT_CLASS;
  }

  /**
   * create a role with given name
   * @param role name of the role
   */
  public RoleDO createRole(RoleDO role)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      // check if the role has already exist
      String roleCtxDN = getRoleClassDirectoryContext();
      ldap.searchSubtree(roleCtxDN, "cn=" + role.getRoleID());

      if (ldap.nextResult()) {
        throw new ObjectAlreadyExistException("The role " + role + "has already exist.");
      }

      // Create the domain
      Attributes attributes = new BasicAttributes(true);

      Attribute attr = new BasicAttribute("objectClass");
      attr.add("top");
      attr.add(ROLE_OBJECT_CLASS);

      attributes.put(attr);
      attributes.put("cn", role.getRoleID());
      attributes.put("ou", role.getName());
      attributes.put("description", role.getComment().trim());

      String roleDN = "cn="+role.getRoleID()+","+getRoleClassDirectoryContext();
      LdapOperation.addElement(roleDN, attributes);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Count not create role : " + role, e);
    }
    finally {
      closeLdap(ldap);
    }

    return role;
  }

  /**
   * delete the role with the name
   * @param role name of the role
   */
  public void deleteRole(RoleDO role)
      throws ObjectNotExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;
    String roleDN = "cn="+role.getRoleID()+","+getRoleClassDirectoryContext();

    try {
      ldap = getLdap();
      LdapOperation.deleteElement(roleDN);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not delete role : " + roleDN, e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * update the role
   * @param role role to update
   */
  public void updateRole(RoleDO role)
      throws ObjectNotExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String roleDN = "cn="+role.getRoleID()+","+getRoleClassDirectoryContext();
      ModificationItem[] mods = new ModificationItem[2];

      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                   new BasicAttribute("description", role.getComment()));

      mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                   new BasicAttribute("ou", role.getName()));

      LdapOperation.modifyAttributes(roleDN, mods);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not update role : " + role, e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * retrieve the role with the given name
   */
  public RoleDO lookupRole(String name)
      throws ObjectNotExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;
    RoleDO role = null;

    try {
      ldap = getLdap();

      String[] attrs = {"cn", "description", "ou"};
      ldap.setReturningAttributes(attrs);
      String roleCtxDN = getRoleClassDirectoryContext();
      ldap.searchSubtree(roleCtxDN, "cn=" + name);

      if (ldap.nextResult()) {
        role = createRoleFromLdapResult(ldap);
      }
      else{
        throw new ObjectNotExistException("Can't find the specified ROLE.");
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not find role : " + name, e);
    }
    finally {
      closeLdap(ldap);
    }

    return role;
  }

  public static RoleDO createRoleFromLdapResult(LdapOperation ldap) {
    RoleDO role = null;

    try {
      String roleId = ldap.getResultAttribute("cn");
      String roleName = ldap.getResultAttribute("ou");
      if (roleName == null) {
        roleName = roleId;
      }
      String roleDesc = ldap.getResultAttribute("description");

      role = new RoleDO(roleId, roleName, roleDesc);
    }
    catch (NamingException e) {

      role = null;
    }

    return role;
  }

  public static RoleDO createRoleFromAttributes(Attributes attrs) {
    RoleDO role = null;

    try {
      String id = (String) attrs.get("cn").get();

      String name = null;
      if (attrs.get("ou") != null) {
        name = (String) attrs.get("ou").get();
      }
      else {
        name = id;

      }
      String desc = null;
      if (attrs.get("description") != null) {
        desc = (String) attrs.get("description").get();

      }
      role = new RoleDO(id, name, desc);

    }
    catch (NamingException e) {

      role = null;
    }

    return role;
  }

  /**
   * @return total number of roles
   */
  public int getCount()
      throws SecurityManagerDaoException {

    LdapOperation ldap = null;
    int nCount = 0;

    try {
      ldap = getLdap();

      String roleCtxDN = getRoleClassDirectoryContext();

      String[] attrs = {"cn"};
      ldap.setReturningAttributes(attrs);

      ldap.searchSubtree(roleCtxDN, "objectClass=" + getRoleObjectClass());

      while (ldap.nextResult()) {
        nCount++;
      }

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Failed to count roles", e);
    }
    finally {
      closeLdap(ldap);
    }

    return nCount;
  }

  /**
   * @return all the role names
   */
  public Vector getRoleNames()
      throws SecurityManagerDaoException {
    LdapOperation ldap = null;
    Vector vec = new Vector();

    try {
      ldap = getLdap();

      String roleCtxDN = getRoleClassDirectoryContext();

      String[] attrs = {"cn","ou","description"};
      ldap.setReturningAttributes(attrs);

      ldap.searchSubtree(roleCtxDN, "objectClass=" + getRoleObjectClass());

      while (ldap.nextResult()) {
        RoleDO retRole = createRoleFromLdapResult(ldap);
        vec.add(retRole);
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Failed to count roles", e);
    }
    finally {
      closeLdap(ldap);
    }

    return vec;
  }

  /**
   * @param index start index
   * @param nCount count of names
   * @return a range of role names
   */
  public Vector getRoleNames(int index, int nCount)
      throws SecurityManagerDaoException {

    LdapOperation ldap = null;
    Vector vec = new Vector();

    int start = 0;
    int nCnt = 0;

    try {
      ldap = getLdap();

      String roleCtxDN = getRoleClassDirectoryContext();

      // limit search attributes
      String[] attrs = {"cn"};
      ldap.setReturningAttributes(attrs);

      ldap.searchSubtree(roleCtxDN, "objectClass=" + getRoleObjectClass());

      // loop
      while (ldap.nextResult()) {
        start++;

        if (start >= index) {
          vec.add(ldap.getResultAttribute("cn"));
          nCnt++;

          // check if count limit is reached
          if (nCnt == nCount) {
            break;
          }
        }
      }

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Failed to get roles", e);
    }
    finally {
      closeLdap(ldap);
    }

    return vec;
  }

  /**
   * return all the roles
   */
  public Vector getRoles()
      throws SecurityManagerDaoException {

    LdapOperation ldap = null;
    Vector vec = new Vector();

    try {
      ldap = getLdap();

      String roleCtxDN = getRoleClassDirectoryContext();

      String[] attrs = {"cn", "description", "ou"};
      ldap.setReturningAttributes(attrs);

      ldap.searchSubtree(roleCtxDN, "objectClass=" + getRoleObjectClass());

      while (ldap.nextResult()) {
        RoleDO role = createRoleFromLdapResult(ldap);

        if (role != null) {
          vec.add(role);
        }
      }

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Failed to get roles", e);
    }
    finally {
      closeLdap(ldap);
    }

    return vec;
  }

    /**
     * lookup role dn , this method will seach the sub tree and find the result.
     *
     * @param ldap ldap facade object
     * @param role , may be a Role object or role name
     * @return the dn of the role
     * @throws NamingException , ObjectNotExistException
     */
    public static String lookupRoleDN(LdapOperation ldap, Object role) throws NamingException, ObjectNotExistException {
        String roledn;
        // get the role dn
        String rolename;
        if (role instanceof RoleDO)
            rolename = ((RoleDO) role).getName();
        else if (role instanceof String)
            rolename = (String) role;
        else
            throw new IllegalArgumentException("bad role parameter");

        ldap.searchSubtree(getRoleClassDirectoryContext(), "cn=" + rolename);
        if (ldap.nextResult())
            roledn = ldap.getResultName();
        else
            throw new ObjectNotExistException("role " + rolename + " not exist.");

        ldap.resetSearch();

        return roledn;
    }

    /**
     * Constructs the DN of a domain.
     *
     * @param rolename role name
     * @return DN for this role
     */
    public static final String calcRoleDN(String rolename) {
        StringBuffer domainDn = new StringBuffer();
        domainDn.append("cn=").append(rolename).append(",").append(getRoleClassDirectoryContext());

        return domainDn.toString();
    }


}
