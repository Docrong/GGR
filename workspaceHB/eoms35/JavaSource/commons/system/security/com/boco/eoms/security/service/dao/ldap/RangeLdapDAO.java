/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis              2003-08-22              created
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
import com.boco.eoms.security.service.dao.RangeDAO;
import com.boco.eoms.security.service.dao.ldap.factory.LdapOperation;
import com.boco.eoms.security.service.manager.RangeManager;
import com.boco.eoms.security.service.model.RangeDO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The Object about Data Range Management</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author weis
 * @version 1.0
 */
public class RangeLdapDAO
    extends BaseLdapDAO
    implements RangeDAO {
  private static SystemConfig sc = SystemConfig.getInstance();

  protected static String RANGE_OBJECT_CLASS = "ecDataRange";
  protected static String CATEGORY_OBJECT_CLASS = "organizationalUnit";

  /**
   * constructor
   */
  public RangeLdapDAO() {}

  /**
   * Data range class directory context
   * @return  directory context
   */
  public static String getRangeDirectoryContext() {
    return sc.rangeCtxDN;
  }

  /**
   * get the data range DN in Ldap directory
   * @param id
   * @param category
   * @return  DN
   */
  protected String getRangeDN(String id, String category) {
    if (category == null || category.length() == 0) {
      return "eaDataRangeID=" + id + "," + getRangeDirectoryContext();
    }
    else {
      return "eaDataRangeID=" + id + "," + "ou=" + category + "," + getRangeDirectoryContext();
    }
  }

  /**
   * Search the range dn of a range object
   *
   * @param ldap ldap operation object
   * @param range  range object
   *
   * @return dn of the range object, null if the range object can't be found.
   */
  public static String searchRangeDN(LdapOperation ldap, Object range)
      throws NamingException, ObjectNotExistException {

    String rangeDN;
    String id;
    if (range instanceof RangeDO) {
      id = ( (RangeDO) range).getRangeID();
    }
    else if (range instanceof String) {
      id = (String) range;
    }
    else {
      throw new IllegalArgumentException("bad role parameter");
    }

    String RangeCtxDN = getRangeDirectoryContext();
    //String RangeCtxDN = sc.rangeCtxDN;
    String filter = "(&(objectClass=ecDataRange)(eaDataRangeID=" + id + "))";

    String[] attrs = {"cn", "description", "businessCategory", "eaDataRangeID"};
    ldap.setReturningAttributes(attrs);

    ldap.searchSubtree(RangeCtxDN, filter);
    if (ldap.nextResult()) {
      rangeDN = ldap.getResultName();
    }
    else {
      throw new ObjectNotExistException("range " + range + " not exist.");
    }

    ldap.resetSearch();

    return rangeDN;
  }

  /**
   * Helper function to create data range object from ldap search result
   *
   * @param ldap
   * @return
   */
  public static RangeDO createRangeFromLdapResult(LdapOperation ldap) {
    RangeDO range = null;

    try {
      String name = ldap.getResultAttribute("cn");
      String cat = ldap.getResultAttribute("businessCategory");
      String id = ldap.getResultAttribute("eaDataRangeID");
      range = new RangeDO(id, name, cat);

      range.setComment(ldap.getResultAttribute("description"));
      range.setParentID(ldap.getResultAttribute("eaParentID"));

      Object oa = ldap.getResultAttributeObject("eaCommonAttribute");
      if (oa != null) {
        range.setProperty("eaCommonAttribute", oa);

      }
      oa = ldap.getResultAttributeObject("eaCommonRegion");
      if (oa != null) {
        range.setProperty("eaCommonRegion", oa);

      }
      oa = ldap.getResultAttributeObject("eaCommonFunction");
      if (oa != null) {
        range.setProperty("eaCommonFunction", oa);

      }
      oa = ldap.getResultAttributeObject("eaCommonType");
      if (oa != null) {
        range.setProperty("eaCommonType", oa);

      }
      oa = ldap.getResultAttributeObject("eaCommonOwner");
      if (oa != null) {
        range.setProperty("eaCommonOwner", oa);

      }
      oa = ldap.getResultAttributeObject("eaCommonSystem");
      if (oa != null) {
        range.setProperty("eaCommonSystem", oa);

      }
    }
    catch (NamingException e) {
      range = null;
    }

    return range;
  }

  public static RangeDO createRangeFromAttributes(Attributes attrs) {
    RangeDO range = null;
    try {
      String cat = (String) attrs.get("businessCategory").get();
      String id = (String) attrs.get("eaDataRangeID").get();
      String name = (String) attrs.get("cn").get();
      range = new RangeDO(id, name, cat);

      if (attrs.get("description") != null) {

        //range.setProperty("comment",attrs.get("description").get());
        range.setComment( (String) attrs.get("description").get());

      }
      if (attrs.get("eaCommonAttribute") != null) {
        range.setProperty("eaCommonAttribute", attrs.get("eaCommonAttribute").get());

      }
      if (attrs.get("eaCommonRegion") != null) {
        range.setProperty("eaCommonRegion", attrs.get("eaCommonRegion").get());

      }
      if (attrs.get("eaCommonFunction") != null) {
        range.setProperty("eaCommonFunction", attrs.get("eaCommonFunction").get());

      }
      if (attrs.get("eaCommonType") != null) {
        range.setProperty("eaCommonType", attrs.get("eaCommonType").get());

      }
      if (attrs.get("eaCommonOwner") != null) {
        range.setProperty("eaCommonOwner", attrs.get("eaCommonOwner").get());

      }
      if (attrs.get("eaCommonSystem") != null) {
        range.setProperty("eaCommonSystem", attrs.get("eaCommonSystem").get());

      }

    }
    catch (NamingException ex) {
      range = null;
    }
    return range;
  }

  /**
   * create Data Range according to RangeDO
   * @param rangedo - Range Data object
   * @return  rangedo - Range Data object
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public RangeDO createRange(RangeDO rangedo)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {
    String RangeID = rangedo.getRangeID();
    String RangeName = rangedo.getName();
    String category = rangedo.getCategory();
    String desc = rangedo.getComment();
    String parentID = rangedo.getParentID();

    return createRange(RangeID, RangeName, category, desc, parentID);
  }

  /**
   * create Data Range according to the given data.
   * @param id - Range ID
   * @param name - Data Range Name
   * @param category - Data Range's category
   * @param desc - Data Range's description
   * @return
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public RangeDO createRange(String id, String name, String category, String desc, String parentID)
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

      String permissionCtxDN = getRangeDirectoryContext();
      String filter = "(&(objectClass=ecDataRange)(eaDataRangeID=" + id + "))";
      ldap.searchSubtree(permissionCtxDN, filter);
      if (ldap.nextResult()) {
        throw new ObjectAlreadyExistException("the range " + id + " has already exist.");
      }

      //create the permission object
      //Map attributes = new HashMap();
      Attributes attributes = new BasicAttributes(true);
      Attribute attr = new BasicAttribute("objectClass");
      attr.add("top");
      attr.add(RANGE_OBJECT_CLASS);

      attributes.put(attr);
      attributes.put("cn", name);
      attributes.put("description", desc);
      attributes.put("eaDataRangeID", id);
      attributes.put("businessCategory", category);
      attributes.put("eaParentID", parentID);

      //create the category first
      createCategory(ldap, category, "category");

      dn = getRangeDN(id, category);
      LdapOperation.addElement(dn, attributes);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("can not create permission object : " + name, e);
    }
    finally {
      closeLdap(ldap);
    }
    RangeDO range = new RangeDO(id, name, category);
    return range;
  }

  public void createCategory(String name, String desc)
      throws SecurityManagerDaoException {

    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      createCategory(ldap, name, desc);
    }
    catch (NamingException e) {

      throw new SecurityManagerDaoException("Can not create range category object : " + name, e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  protected void createCategory(LdapOperation ldap, String name, String desc)
      throws NamingException {
    String dn;
    String rangeCtxDn = getRangeDirectoryContext();

    //try to find the permission category has been existed
    String filter = "(&(objectClass=organizationalUnit)(ou=" + name + "))";
    ldap.searchOneLevel(rangeCtxDn, filter);
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

    dn = "ou=" + name + "," + getRangeDirectoryContext();
    LdapOperation.addElement(dn, attributes);

  }

  public void deleteRange(RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException {
    String rangeID = range.getRangeID();
    deleteRange(rangeID);
  }

  public void deleteRange(String pid)
      throws ObjectNotExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;
    String dn;

    try {
      ldap = getLdap();
      dn = searchRangeDN(ldap, pid);

      if (dn == null) {
        return;
      }

      LdapOperation.deleteElement(dn);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not delete range object : " + pid, e);

    }
    finally {
      closeLdap(ldap);
    }
  }

  public void updateRange(RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String pdn = searchRangeDN(ldap, range.getRangeID());

      if (pdn == null) {
        return;
      }

//            ldap.modifyElementAttribute(pdn, "cn", range.getName());
//            ldap.modifyElementAttribute(pdn, "description", range.getComment());
//            ldap.modifyElementAttribute(pdn, "ou", range.getCategory());
      this.deleteRange(range.getRangeID());

      this.createRange(range);

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not update permission object : " + range, e);
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("Can not update permission object : " + range, ex);
    }
    catch (ObjectAlreadyExistException ex) {
      throw new SecurityManagerDaoException("Can not update permission object : " + range, ex);
    }

    finally {
      closeLdap(ldap);
    }
  }

  public RangeDO lookupRange(String pid)
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;
    RangeDO result = null;

    try {
      ldap = getLdap();

      String rangeCtxDN = getRangeDirectoryContext();
      String filter = "(&(objectClass=ecDataRange)(eaDataRangeID=" + pid + "))";

      String[] attrs = {"cn", "description", "businessCategory", "eaDataRangeID", "eaParentID"};
      ldap.setReturningAttributes(attrs);

      ldap.searchSubtree(rangeCtxDN, filter);

      if (ldap.nextResult()) {
        result = createRangeFromLdapResult(ldap);
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Failed to find the range object ", e);
    }
    finally {
      closeLdap(ldap);
    }

    return result;
  }

  public Vector getAllRange()
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;
    Vector vec = new Vector();

    try {
      ldap = getLdap();
      String rangeCtxDN = getRangeDirectoryContext();

      String[] attrs = {"cn", "description", "businessCategory","eaParentID", "eaDataRangeID"};
      ldap.setReturningAttributes(attrs);

      ldap.searchSubtree(rangeCtxDN, "objectClass=ecDataRange");

      while (ldap.nextResult()) {
        RangeDO range = createRangeFromLdapResult(ldap);
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

  public Vector getRangeByCategory(Object category)
      throws ObjectNotExistException, SecurityManagerDaoException {
    String categoryID;
    if (category instanceof RangeDO) {
      categoryID = ( (RangeDO) category).getRangeID();
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
      String rangeCtxDN = getRangeDirectoryContext();

      String[] attrs = {"cn", "description", "businessCategory","eaParentID", "eaDataRangeID"};
      ldap.setReturningAttributes(attrs);

      String filter = "(&(objectClass=ecDataRange)(businessCategory=" + categoryID + "))";
      ldap.searchSubtree(rangeCtxDN, filter);

      while (ldap.nextResult()) {
        RangeDO range = createRangeFromLdapResult(ldap);
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
      String rangeCtxDN = getRangeDirectoryContext();
      String[] attrs = {"ou", "description"};
      ldap.setReturningAttributes(attrs);

      ldap.searchSubtree(rangeCtxDN, "objectClass=organizationalUnit");

      while (ldap.nextResult()) {
        if (ldap.getResultAttribute("ou").equals("DataRanges")) {
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
      throw new SecurityManagerDaoException("Failed to list ranges", e);
    }
    finally {
      closeLdap(ldap);
    }

    return vec;
  }

  /**
   *
   * @param rangeID
   * @param roles
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */

  public void assignRoleRange(String rangeID, Vector roles)
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;
    String pdn = "";

    try {
      ldap = getLdap();

      pdn = searchRangeDN(ldap, rangeID);
      if (pdn == null) {
        return;
      }

      Vector vec = getAssignedRoles(ldap, rangeID); // get all assigned groups
      Vector dns = new Vector(vec); // assigned dns
      Vector mods = new Vector(); // modification items

      for (int j = 0; j < roles.size(); j++) {
        String role = (String) roles.elementAt(j);
        String roledn;

        try {
          roledn = RoleLdapDAO.lookupRoleDN(ldap, role);
        }
        catch (Exception e) {
          e.printStackTrace();
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
      throw new SecurityManagerDaoException("Can not set role of range : " + pdn, e);

    }
    finally {
      closeLdap(ldap);
    }

  }

  /**
   *
   * @param rangeID
   * @param roles
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public void removeRoleRange(String rangeID, Vector roles)
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;
    String pdn = "";

    try {
      ldap = getLdap();

      pdn = searchRangeDN(ldap, rangeID);
      if (pdn == null) {
        return;
      }

      Vector vec = getAssignedRoles(ldap, rangeID); // get all assigned groups
      Vector mods = new Vector(); // modification items

      for (int j = 0; j < roles.size(); j++) {
        String role = (String) roles.elementAt(j);
        String roledn;

        try {

          roledn = RoleLdapDAO.lookupRoleDN(ldap, role);
        }
        catch (Exception e) {
          e.printStackTrace();
          continue;
        }

        // check if the grpdn has been assigned
        boolean bFound = false;
        for (int k = 0; k < vec.size(); k++) {
          String dn = (String) vec.elementAt(k);

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
      throw new SecurityManagerDaoException("Can not set role of range : " + pdn, e);

    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * get permissions assigned to a role object
   * @param role role to retrieve
   */
  public Vector getRoleRanges(String role)
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
        throw new ObjectNotExistException("The role '" + role + "' not exist.");
      }

      String permissonCtxDN = getRangeDirectoryContext();

      String[] attrs = {"cn", "description", "businessCategory", "eaPermissionID"};
      ldap.setReturningAttributes(attrs);

      // make the query filter
      String filter = "(&(objectClass=ecDataRange)(eaRoleDN=" + rdn + "))";
      ldap.searchSubtree(permissonCtxDN, filter);

      while (ldap.nextResult()) {

        RangeDO pm = createRangeFromLdapResult(ldap);
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

  /**
   *
   * @param ldap
   * @param rangeID
   * @return
   * @throws NamingException
   */
  protected Vector getAssignedRoles(LdapOperation ldap, String rangeID)
      throws NamingException {
    Vector vec = new Vector();
    String rangedn = null;

    //get the range object
    String searchDN = getRangeDirectoryContext();
    String searchFilter = "eaDataRangeID=" + rangeID;
    ldap.searchSubtree(searchDN, searchFilter);

    Set setDN = null;

    if (ldap.nextResult()) {
      setDN = ldap.getAllResultAttributeValues("eaRoleDN");
      rangedn = ldap.getResultName();
    }

    ldap.resetSearch();
    if (setDN != null) {
      Iterator it = setDN.iterator();
      String attrs[] = {"cn"};
      while (it.hasNext()) {
        String roledn = (String) it.next();

        try {
          ldap.getDirContext().getAttributes(roledn, attrs);
          vec.add(roledn);
        }
        catch (NamingException e) {
          //the entry is invalid, try to remove the entry
          //the entry is invalid , try remove the entry
          BasicAttribute attr = new BasicAttribute("eaRoleDN", roledn);
          ModificationItem mod = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, attr);
          ModificationItem[] mods = {mod};
          LdapOperation.modifyAttributes(rangedn, mods);
        }
      }
    }

    return vec;
  }

  public boolean isSubRange(RangeDO p, RangeDO c)
      throws ObjectNotExistException, SecurityManagerDaoException {
    p = RangeManager.lookupRange(p.getRangeID());
    c = RangeManager.lookupRange(c.getRangeID());

    if (p.getRangeID().equals(c.getParentID()))
      return true;
    else
      return false;
  }

  public Vector removeSubRangesInVector(RangeDO p, Vector v)
      throws ObjectNotExistException, SecurityManagerDaoException {
    Vector ret = new Vector();

    Iterator it = v.iterator();
    while (it.hasNext()){
      RangeDO rdo = (RangeDO)it.next();
      rdo = RangeManager.lookupRange(rdo.getRangeID());

      if (rdo.getParentID()==null || !rdo.getParentID().equals(p.getRangeID())){
        ret.add(rdo);
      }
    }
    return ret;
  }

  public Vector getSubRanges(RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException {
    Vector ret = new Vector();
    Vector tmpRDO = getAllRange();

    Iterator it = tmpRDO.iterator();

    while (it.hasNext()){
      RangeDO rdo = (RangeDO)it.next();

      if (rdo.getParentID()!=null && rdo.getParentID().equals(range.getRangeID())){
        ret.add(rdo);
      }
    }
    return ret;
  }

  public RangeDO getParentRange(RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException {
    range = RangeManager.lookupRange(range.getRangeID());

    if (range.getParentID() == null || range.getParentID().equals(""))
      throw new ObjectNotExistException("No parent range exist...");


    return RangeManager.lookupRange(range.getParentID());
  }
}
