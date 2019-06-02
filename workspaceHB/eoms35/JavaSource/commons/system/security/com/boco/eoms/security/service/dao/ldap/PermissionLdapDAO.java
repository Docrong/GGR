/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis              2003-08-21             created
 */

package com.boco.eoms.security.service.dao.ldap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
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
import com.boco.eoms.security.service.dao.PermissionDAO;
import com.boco.eoms.security.service.dao.ldap.factory.LdapOperation;
import com.boco.eoms.security.service.model.PermissionDO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: the permission Ldap Data access object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author weis
 * @version 1.0
 */

public class PermissionLdapDAO
    extends BaseLdapDAO
    implements PermissionDAO {

  private static SystemConfig sc = SystemConfig.getInstance();

  protected static String PERMISSION_OBJECT_CLASS = "ecPermission";
  protected static String CATEGORY_OBJECT_CLASS = "organizationalUnit";

  /**
   * constructor
   */
  public PermissionLdapDAO() {}

  /**
   * permission class directory context
   * @return  directory context
   */
  public String getPermissionDirectoryContext() {
    return sc.permissionCtxDN;
  }

  /**
   * get the permission DN in Ldap directory
   * @param id
   * @param category
   * @return  DN
   */
  protected String getPermissionDN(String id, String category) {
    if (category == null || category.length() == 0) {
      return "eaPermissionID=" + id + "," + getPermissionDirectoryContext();
    }
    else {
      return "eaPermissionID=" + id + "," + "ou=" + category + "," + getPermissionDirectoryContext();
    }
  }

  /**
   * Search the permission dn of a permission object
   *
   * @param ldap ldap operation object
   * @param permission  permission object
   *
   * @return dn of the permission object, null if the permission object can't be found.
   */
  protected String searchPermissionDN(LdapOperation ldap, Object permission)
      throws NamingException, ObjectNotExistException {

    String permissionDN;
    String id;

    if (permission instanceof PermissionDO) {
      id = ( (PermissionDO) permission).getPermissionID();
    }
    else if (permission instanceof String) {
      id = (String) permission;
    }
    else {
      throw new IllegalArgumentException("bad role parameter");
    }

    String permissonCtxDN = getPermissionDirectoryContext();
    String filter = "(&(objectClass=ecPermission)(eaPermissionID=" + id + "))";

    String[] attrs = {"cn", "description", "businessCategory", "eaPermissionID", "eaRoleDN"};
    ldap.setReturningAttributes(attrs);

    ldap.searchSubtree(permissonCtxDN, filter);
    if (ldap.nextResult()) {
      permissionDN = ldap.getResultName();
    }
    else {
      throw new ObjectNotExistException("permission  " + permission + " not exist.");
    }

    ldap.resetSearch();

    return permissionDN;
  }

  /**
   * Helper function to create permission object from ldap search result
   *
   * @param ldap
   * @return
   */
  public static PermissionDO createPermissionFromLdapResult(LdapOperation ldap) {
    PermissionDO pm = null;

    try {
      String name = ldap.getResultAttribute("cn");
      String desc = ldap.getResultAttribute("description");
      String cat = ldap.getResultAttribute("businessCategory");
      String id = ldap.getResultAttribute("eaPermissionID");
      pm = new PermissionDO(id, name, cat, desc);
    }
    catch (NamingException e) {
      pm = null;
    }

    return pm;
  }

  public PermissionDO createPermission(PermissionDO pdo)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {
    String permissionID = pdo.getPermissionID();
    String name = pdo.getName();
    String category = pdo.getCategory();
    String description = pdo.getComment();
    return createPermission(permissionID, name, category, description);
  }

  /**
   * create the permission
   * @param id    permission ID
   * @param name  permission name
   * @param category  the category of the permission
   * @param desc  the description of the permission
   * @return
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public PermissionDO createPermission(String id, String name, String category, String desc)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;

    //validate data
    if (category == null) {
      category = "";
    }
    if (desc == null) {
      desc = "";

    }
    if (id == null && id.length() == 0) {
      throw new IllegalArgumentException("Id can't be null");
    }

    try {
      ldap = getLdap();
      String dn;

      String permissionCtxDN = getPermissionDirectoryContext();
      String filter = "(&(objectClass=ecPermission)(eaPermissionID=" + id + "))";
      ldap.searchSubtree(permissionCtxDN, filter);
      if (ldap.nextResult()) {
        throw new ObjectAlreadyExistException("the permission " + id + " has already exist.");
      }

      //create the permission object
      //Map attributes = new HashMap();
      Attributes attributes = new BasicAttributes(true);
      Attribute attr = new BasicAttribute("objectClass");
      attr.add("top");
      attr.add(PERMISSION_OBJECT_CLASS);

      attributes.put(attr);
      attributes.put("cn", name);
      attributes.put("description", desc);
      attributes.put("eaPermissionID", id);
      attributes.put("businessCategory", category);

      //create the category first
      createCategory(ldap, category, "category");

      dn = getPermissionDN(id, category);
      LdapOperation.addElement(dn, attributes);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("can not create permission object : " + name, e);
    }
    finally {
      closeLdap(ldap);
    }
    PermissionDO perm = new PermissionDO(id, name, category, desc);
    return perm;
  }

  /**
   * create permission category
   *
   * @param name category name
   * @param desc category description
   */
  public void createCategory(String name, String desc)
      throws SecurityManagerDaoException {

    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      createCategory(ldap, name, desc);
    }
    catch (NamingException e) {

      throw new SecurityManagerDaoException("Can not create permission category object : " + name, e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * create the permission category
   * @param ldap  LdapOperation Object
   * @param name category name
   * @param desc  description the category
   * @throws NamingException
   */
  protected void createCategory(LdapOperation ldap, String name, String desc)
      throws SecurityManagerDaoException {
    try {
      String dn;
      String permissionCtxDn = getPermissionDirectoryContext();

      //try to find the permission category has been existed
      String filter = "(&(objectClass=organizationalUnit)(ou=" + name + "))";
      ldap.searchOneLevel(permissionCtxDn, filter);
      if (ldap.nextResult()) {
        return;
      }

      Attributes attributes = new BasicAttributes(true);
      Attribute attr = new BasicAttribute("objectClass");
      attr.add("top");
      attr.add(CATEGORY_OBJECT_CLASS);

      attributes.put(attr);

      attributes.put("ou", name);
      attributes.put("description", desc);

      dn = "ou=" + name + "," + getPermissionDirectoryContext();
//    System.out.println("The DN : " + dn);
      LdapOperation.addElement(dn, attributes);
    }
    catch (NamingException ex) {
      throw new SecurityManagerDaoException(ex);
    }

  }

  /**
   * delete permission
   *
   * @param pid permisson id
   */
  public void deletePermission(String pid)
      throws ObjectNotExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;
    String dn;

    try {
      ldap = getLdap();
      dn = searchPermissionDN(ldap, pid);
      System.out.println("The DN is1: " + dn);
      dn = dn.replaceAll("\"","");
      dn = dn.replaceAll("/","\\\\/");
      System.out.println("The DN is2: " + dn);
      if (dn == null) {
        System.out.println("This permission can not be found, the operation ignore!");
        return;
      }

      LdapOperation.deleteElement(dn);
    }
    catch (NamingException e) {
      System.out.println("Can not delete permission object!");
      throw new SecurityManagerDaoException("Can not delete permission object : " + pid, e);

    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * lookup a permission object
   * @param pid
   * @return
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public PermissionDO lookupPermission(String pid)
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;
    PermissionDO result = null;

    try {
      ldap = getLdap();

      String permissionCtxDN = getPermissionDirectoryContext();
      String filter = "(&(objectClass=ecPermission)(eaPermissionID=" + pid + "))";

      String[] attrs = {"cn", "description", "businessCategory", "eaPermissionID"};
      ldap.setReturningAttributes(attrs);

      ldap.searchSubtree(permissionCtxDN, filter);

      if (ldap.nextResult()) {
        result = createPermissionFromLdapResult(ldap);
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Failed to find the permission object ", e);
    }
    finally {
      closeLdap(ldap);
    }

    return result;
  }

  public Vector getAllPermissions()
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;
    Vector vec = new Vector();

    try {
      ldap = getLdap();
      String permissionCtxDN = getPermissionDirectoryContext();

      String[] attrs = {"cn", "description", "businessCategory", "eaPermissionID"};
      ldap.setReturningAttributes(attrs);

      ldap.searchSubtree(permissionCtxDN, "objectClass=ecPermission");

      while (ldap.nextResult()) {
        PermissionDO perm = createPermissionFromLdapResult(ldap);
        if (perm != null) {
          vec.add(perm);
        }
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Failed to list permissions", e);
    }
    finally {
      closeLdap(ldap);
    }

    return vec;
  }

  /**
   * get roles to which the permission is assigned
   * @param ldap  LdapOperation object
   * @param permid    permission id
   * @return vec  Vector of Roles
   * @throws NamingException
   */
  protected Vector getAssignedRoles(LdapOperation ldap, String permid)
      throws NamingException {
    Vector vec = new Vector();
    String permissiondn = null;

    //get the permission object
    String searchDN = getPermissionDirectoryContext();
    String searchFilter = "eaPermissionID=" + permid;
    ldap.searchSubtree(searchDN, searchFilter);

    Set setDN = null;

    if (ldap.nextResult()) {
      setDN = ldap.getAllResultAttributeValues("eaRoleDN");
      permissiondn = ldap.getResultName();
    }

    ldap.resetSearch();
    if (setDN != null) {
      Iterator it = setDN.iterator();
      String attrs[] = {"cn"};
      while (it.hasNext()) {
        String roledn = (String) it.next();
        //System.out.println(roledn);
        //System.out.println("-------");
        try {
          ldap.getDirContext().getAttributes(roledn, attrs);
          vec.add(roledn);
        }
        catch (NamingException e) {
          //the entry is invalid, try to remove the entry
          // the entry is invalid , try remove the entry
          BasicAttribute attr = new BasicAttribute("eaRoleDN", roledn);
          ModificationItem mod = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, attr);
          ModificationItem[] mods = {mod};
          LdapOperation.modifyAttributes(permissiondn, mods);
        }
      }
    }

    return vec;
  }

  /**
   * get roles assigned to a permisson
   *
   * @param permid permission name
   * @return vector of roles dn assigned to the permisson
   */
  public Vector getAssignedRoles(String permid)
      throws ObjectNotExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;
    Vector vec = null;

    try {
      ldap = getLdap();
      vec = getAssignedRoles(ldap, permid);

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not get role list of permission" + permid, e);
    }
    finally {
      closeLdap(ldap);
    }

    return vec;
  }

  /**
   * assign role aci
   */
  public void assignRolePermission(String permid, Vector roles)
      throws ObjectNotExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;
    String pdn = "";

    try {
      ldap = getLdap();

      pdn = searchPermissionDN(ldap, permid);
      if (pdn == null) {
        System.out.println("The permission object can't be found!");
        return;
      }

      Vector vec = getAssignedRoles(ldap, permid); // get all assigned groups
      Vector dns = new Vector(vec); // assigned dns
      Vector mods = new Vector(); // modification items

      for (int j = 0; j < roles.size(); j++) {
        String role = (String) roles.elementAt(j);
        String roledn;

        try {
          roledn = RoleLdapDAO.lookupRoleDN(ldap, role);
        }
        catch (Exception e) {
          System.out.println("Can't find the role dn , ignore the operation.");
          continue;
        }

        // check if the role has been assigned
        boolean bFound = false;
        for (int k = 0; k < vec.size(); k++) {
          String dn = (String) vec.elementAt(k);
          if (dn.equalsIgnoreCase(roledn)) {
            bFound = true;
            break;
          }
        }

        // use eaRoleDN to store the role information
        if (!bFound) {
          dns.add(roledn);
          BasicAttribute attr = new BasicAttribute("eaRoleDN", roledn);
          ModificationItem mod = new ModificationItem(DirContext.ADD_ATTRIBUTE, attr);
          mods.add(mod);
        }
      }

      ModificationItem[] mod1 = new ModificationItem[1];
      LdapOperation.modifyAttributes(pdn, (ModificationItem[]) mods.toArray(mod1));

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not set role of permission : " + pdn, e);

    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * remove role aci
   */
  public void removeRolePermission(String permid, Vector roles)
      throws ObjectNotExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;
    String pdn = "";

    try {
      ldap = getLdap();

      pdn = searchPermissionDN(ldap, permid);
      if (pdn == null) {
        System.out.println("The permission object can't be found!");
        return;
      }

      Vector vec = getAssignedRoles(ldap, permid); // get all assigned groups
      Vector mods = new Vector(); // modification items

      //System.out.println("test");

      for (int j = 0; j < roles.size(); j++) {
        String role = (String) roles.elementAt(j);
        String roledn;
        //System.out.println(role);
        try {

          roledn = RoleLdapDAO.lookupRoleDN(ldap, role);
        }
        catch (Exception e) {
          System.out.println("Can't find the role dn, ignore the operation");
          continue;
        }

        // check if the grpdn has been assigned
        boolean bFound = false;
        for (int k = 0; k < vec.size(); k++) {
          String dn = (String) vec.elementAt(k);
          //System.out.println("dn: " + dn);
          //System.out.println("roledn: " + roledn);
          if (dn.equalsIgnoreCase(roledn)) {
            bFound = true;
            vec.remove(k); // remove the item
            break;
          }
        }

        // use eaRoleDN to store the role information
        if (bFound) {
          BasicAttribute attr = new BasicAttribute("eaRoleDN", roledn);
          ModificationItem mod = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, attr);
          mods.add(mod);
        }
      }

      ModificationItem[] mod1 = new ModificationItem[1];
      LdapOperation.modifyAttributes(pdn, (ModificationItem[]) mods.toArray(mod1));

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not set role of permission : " + pdn, e);

    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * update a permission object
   */
  public void updatePermission(PermissionDO perm)
      throws ObjectNotExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String pdn = searchPermissionDN(ldap, perm.getPermissionID());
      //System.out.println(pdn);
      if (pdn == null) {
        System.out.println("The permission object can't be found!");
        return;
      }

//            ldap.modifyElementAttribute(pdn, "cn", perm.getName());
//            ldap.modifyElementAttribute(pdn, "description", perm.getComment());
//            ldap.modifyElementAttribute(pdn, "ou",perm.getCategory());
      this.deletePermission(perm.getPermissionID());
      this.createPermission(perm);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not update permission object : " + perm, e);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("Can not update permission object : " + perm, ex);
    }
    catch (ObjectAlreadyExistException ex) {
      throw new SecurityManagerDaoException("Can not update permission object : " + perm, ex);
    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * get permissions assigned to a role object
   * @param role role to retrieve
   */
  public Vector getRolePermissions(String role)
      throws ObjectNotExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;
    Vector vec = new Vector();

    try {
      ldap = getLdap();

      String rdn;
      try {
        rdn = RoleLdapDAO.lookupRoleDN(ldap, role);
      }
      catch (Exception e) {
        System.out.println("Can't find the group dn , ignore the operation.");

        throw new ObjectNotExistException("The role '" + role + "' not exist.");
      }

      String permissonCtxDN = getPermissionDirectoryContext();

      String[] attrs = {"cn", "description", "businessCategory", "eaPermissionID"};
      ldap.setReturningAttributes(attrs);

      // make the query filter
      String filter = "(&(objectClass=ecPermission)(eaRoleDN=" + rdn + "))";
      ldap.searchSubtree(permissonCtxDN, filter);

      while (ldap.nextResult()) {

        PermissionDO pm = createPermissionFromLdapResult(ldap);
        if (pm != null) {
          vec.add(pm);
        }
      }

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Failed to list permissions", e);
    }
    finally {
      closeLdap(ldap);
    }

    return vec;
  }

  public Vector getRangeByCategory(Object category)
      throws ObjectNotExistException, SecurityManagerDaoException {
    String categoryID;
    if (category instanceof PermissionDO) {
      categoryID = ( (PermissionDO) category).getPermissionID();
    }
    else if (category instanceof String) {
      categoryID = (String) category;
    }
    else {
      throw new IllegalArgumentException("bad user parameter");
    }

    LdapOperation ldap = null;
    Vector vec = new Vector();

    try {
      ldap = getLdap();
      String rangeCtxDN = getPermissionDirectoryContext();

      String[] attrs = {"cn", "description", "businessCategory", "eaPermissionID"};
      ldap.setReturningAttributes(attrs);

      String filter = "(&(objectClass=ecPermission)(businessCategory=" + categoryID + "))";
      //System.out.println(filter);
      ldap.searchSubtree(rangeCtxDN, filter);

      while (ldap.nextResult()) {
        //System.out.println("result!");
        PermissionDO range = createPermissionFromLdapResult(ldap);
        if (range != null) {
          vec.add(range);
        }
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Failed to list ranges", e);
    }
    finally {
      closeLdap(ldap);
    }

    return vec;
  }

  public Vector getAllCategory()
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;
    Vector vec = new Vector();

    try {
      ldap = getLdap();
      String permissionCtxDN = getPermissionDirectoryContext();
      String[] attrs = {"ou", "description"};
      ldap.setReturningAttributes(attrs);

      ldap.searchSubtree(permissionCtxDN, "objectClass=organizationalUnit");

      while (ldap.nextResult()) {
        if (ldap.getResultAttribute("ou").equals("Permissions")) {
          continue;
        }
        Map category = new HashMap();

        category.put("categoryName", ldap.getResultAttribute("ou"));
        category.put("description", ldap.getResultAttribute("description"));
        if (category != null) {
          vec.add(category);
        }
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Failed to list category", e);
    }
    finally {
      closeLdap(ldap);
    }

    return vec;
  }

}
