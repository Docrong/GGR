/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-18         created
 */

package com.boco.eoms.security.service.dao.ldap;

import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
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
import com.boco.eoms.security.service.dao.DeptDAO;
import com.boco.eoms.security.service.dao.ldap.factory.LdapOperation;
import com.boco.eoms.security.service.manager.RangeManager;
import com.boco.eoms.security.service.manager.RegionManager;
import com.boco.eoms.security.service.manager.RoleManager;
import com.boco.eoms.security.service.manager.UserManager;
import com.boco.eoms.security.service.model.DeptDO;
import com.boco.eoms.security.service.model.RangeDO;
import com.boco.eoms.security.service.model.RegionDO;
import com.boco.eoms.security.service.model.RoleDO;
import com.boco.eoms.security.service.model.UserDO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class DeptLdapDAO
    extends BaseLdapDAO
    implements DeptDAO {

  protected static String DEPT_OBJECT_CLASS_1 = "groupOfUniqueNames";
  protected static String DEPT_OBJECT_CLASS_2 = "ecDepartment";
  protected static String DEPT_OBJECT_CLASS_3 = "vtgroup-10";
  Vector vecAllUsers = new Vector();

  private static SystemConfig sc = SystemConfig.getInstance();

  public DeptLdapDAO() {}

  /**
   * region class directory context
   * @return
   */
  public static String getDeptClassDirectoryContext() {
    return sc.groupCtxDN;
  }

  /**
   * get region object class
   */
  protected String getDeptObjectClass1() {
    return DEPT_OBJECT_CLASS_1;
  }

  protected String getDeptObjectClass2() {
    return DEPT_OBJECT_CLASS_2;
  }

  protected String getDeptObjectClass3() {
    return DEPT_OBJECT_CLASS_3;
  }

//   --------------------------------------------------------------------------------------------------------------------------------
  //lookup the dn of a department , note that department id is not allowed to be duplicated
  public String lookupDepartmentDN(LdapOperation ldap, Object department)
      throws NamingException, ObjectNotExistException {

    String dptdn; // department dn
    String dptid; // department id

    if (department instanceof DeptDO) {
      dptid = ( (DeptDO) department).getDepartmentID();
    }
    else if (department instanceof String) {
      dptid = (String) department;
    }
    else {
      throw new IllegalArgumentException("bad department parameter");
    }

    String filter = "(&(objectClass=" + getDeptObjectClass2() + ")(cn=" + dptid + "))";
    ldap.searchSubtree(getDeptClassDirectoryContext(), filter);

    if (ldap.nextResult()) {
      dptdn = ldap.getResultName();
    }
    else {
      throw new ObjectNotExistException("department " + dptid + " not exist.");
    }

    ldap.resetSearch();

    return dptdn;
  }

  /**
   * get the DN of a department object
   *
   * @param ldap ldap object
   * @param dpt department object
   */
  protected String getDepartmentDN(LdapOperation ldap, DeptDO dpt, RegionDO region)
      throws NamingException, ObjectNotExistException {

    // check if the department has parent region
    // if so , get the parent region dn first
//        String rgnid = dpt.getRegionID();
    String rgnid = region.getRegionID();
    String pdn = null;
    if (rgnid != null && rgnid.length() > 0) {
      pdn = RegionLdapDAO.lookupRegionDN(ldap, rgnid);
    }
    else {
      // check if the department has parent department object
      // if so , get the parent department dn first
      String pid = dpt.getDepartmentParentID();
      if (pid != null && pid.trim().length() > 0) {
        // get parent dn first
        pdn = lookupDepartmentDN(ldap, pid);
      }
    }

    // construct the department dn
    StringBuffer buf = new StringBuffer("cn=");
    buf.append(dpt.getDepartmentID()).append(",");

    if (pdn == null) {
      buf.append(getDeptClassDirectoryContext());
    }
    else {
      buf.append(pdn);

    }
    return buf.toString();
  }

//    ------------------------------------------------------------------------------------------------------------------------------------------

  /**
   * Desc :
   * @param department
   * @param region
   * @return
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public DeptDO createDepartment(DeptDO department)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;

    try {
      ldap = getLdap();


// ----------------------------------------------------------------------------------------------------------------

   //   String deptCtxDN = getDeptClassDirectoryContext();

      String deptDN = null;

        deptDN = "cn=" + department.getDepartmentID() + "," + getDeptClassDirectoryContext();

      Attributes attributes = new BasicAttributes(true);

      Attribute attr = new BasicAttribute("objectClass");
      attr.add("top");
      attr.add(DEPT_OBJECT_CLASS_1);
      attr.add(DEPT_OBJECT_CLASS_2);

      attributes.put("cn", department.getDepartmentID());
      attributes.put("description", department.getComments());
      attributes.put("telephoneNumber", department.getTeleNumber());
      attributes.put("vtgid", department.getDepartmentID());
      LdapOperation.addElement(deptDN, attributes);
    }
    catch (NamingException e) {
     e.printStackTrace() ;
    }
    finally {
      closeLdap(ldap);
    }
    return department;
  }

   public DeptDO createDepartment(DeptDO department, RegionDO region)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;

    try {
      ldap = getLdap();

// ------------------------------------------------------------------------------------------------------------
      String dptdn;
      // check if the department has already exist
      boolean bFound = false;
      try {
        dptdn = lookupDepartmentDN(ldap, department);
        bFound = true;
      }
      catch (Exception e) {
        // not found is expected
        bFound = false;
      }
      if (bFound) {
        throw new ObjectAlreadyExistException("The department " + department + " has already exist.");
      }
// ----------------------------------------------------------------------------------------------------------------

      String deptCtxDN = getDeptClassDirectoryContext();

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
//        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
//        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

//      if (ldap.nextResult()) {
//        throw new ObjectAlreadyExistException("The department " + department + " has already exist.");
//      }

      // Create the domain
      Attributes attributes = new BasicAttributes(true);

      Attribute attr = new BasicAttribute("objectClass");
      attr.add("top");
      attr.add(DEPT_OBJECT_CLASS_1);
      attr.add(DEPT_OBJECT_CLASS_2);
      attr.add(DEPT_OBJECT_CLASS_3);

      Attribute attrRole = new BasicAttribute("eaRoleDN");
      Iterator itRole = department.getRoleList().iterator();
      boolean bRole = false;
      while (itRole.hasNext()) {
        attrRole.add( (String) itRole.next());
        bRole = true;
      }

      Attribute attrUser = new BasicAttribute("eaDataRangeDN");
      Iterator itRange = department.getRangeList().iterator();
      boolean bUser = false;
      while (itRange.hasNext()) {
        attrRole.add( (String) itRange.next());
        bUser = true;
      }

      Attribute attrRange = new BasicAttribute("uniqueMember");
      Iterator itUser = department.getUserList().iterator();
      boolean bRange = false;
      while (itUser.hasNext()) {
        attrRole.add( (String) itUser.next());
        bRange = true;
      }

      attributes.put(attr);
      if (bRole) {
        attributes.put(attrRole);
      }
      if (bUser) {
        attributes.put(attrUser);
      }
      if (bRange) {
        attributes.put(attrRange);

      }
      attributes.put("cn", department.getDepartmentID());
      attributes.put("description", department.getComments());
      attributes.put("telephoneNumber", department.getTeleNumber());
      attributes.put("mail", department.getEmail());
      attributes.put("businessCategory", department.getSpecialFlag());
      attributes.put("ou", department.getRegionName());
      attributes.put("o", department.getName());
//      attributes.put("eaRegionID", department.getRegionID());
      attributes.put("eaRegionID", region.getRegionID());
      attributes.put("eaDeptParentID", department.getDepartmentParentID());

      //add by jerry
      attributes.put("vtgid", department.getDepartmentID());

//        deptDN = getDepartmentDN(ldap, department,region);
      LdapOperation.addElement(deptDN, attributes);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not create region : " + region, e);
    }
    finally {
      closeLdap(ldap);
    }
    return department;
  }


  /**
   * Desc :
   * @param department
   * @param region
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public void deleteDepartment(DeptDO department, RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String dn = constructDN(region);
      String deptDN = null;
      String regionDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        regionDN = "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        regionDN = "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      LdapOperation.deleteElement(deptDN);

      region = RegionManager.lookupRegion(region);
      Vector vec = RegionManager.getDepartmentList(region);

      Iterator it = vec.iterator();

      while (it.hasNext()) {
        DeptDO ddo = (DeptDO) it.next();

        if (ddo.getDepartmentParentID() != null
            && ddo.getDepartmentParentID().equals(department.getDepartmentID())) {
          LdapOperation.deleteElement("cn=" + ddo.getDepartmentID() + "," + regionDN);
        }
      }

    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not create region : " + region, e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * Desc :
   * @param department
   * @param region
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public void updateDepartment(DeptDO department, RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      //modify the attributes
      ModificationItem[] mods = new ModificationItem[9];

      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("cn", department.getDepartmentID()));
      mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("telephoneNumber", department.getTeleNumber()));
      mods[2] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("description", department.getComments()));
      mods[3] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("mail", department.getEmail()));
      mods[4] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("businessCategory", department.getSpecialFlag()));
      mods[5] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("ou", department.getRegionName()));
      mods[6] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("o", department.getName()));
      mods[7] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("eaRegionID", department.getRegionID()));
      mods[8] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("eaDeptParentID", department.getDepartmentParentID()));

      LdapOperation.modifyAttributes(deptDN, mods);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not create region : " + region, e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * Desc : look for the desired department.
   * @param department
   * @param region
   * @return
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public DeptDO lookupDepartment(DeptDO department, RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;
    DeptDO retDept = null;

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String dn = constructDN(region);
      String deptDN = null;

      String[] attrs = {
                       "eaRoleDN", "eaDataRangeDN", "uniqueMember",
                       "cn", "description", "telephoneNumber", "mail",
                       "businessCategory", "ou", "o", "eaRegionID", "eaDeptParentID"
      };

      ldap.setReturningAttributes(attrs);

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      ldap.searchSubtree(deptDN, "cn=" + department.getDepartmentID());

      if (ldap.nextResult()) {
        retDept = createDepartmentFromLdapResult(ldap);
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not create region : " + region, e);
    }
    finally {
      closeLdap(ldap);
    }
    return retDept;
  }


  public DeptDO lookupDepartment(int deptID)  //add by shenwei 2004-02-04
  throws ObjectNotExistException, SecurityManagerDaoException {
  	LdapOperation ldap = null;
  	DeptDO retDept = null;

  	try {
  		ldap = getLdap();

  		String deptCtxDN = getDeptClassDirectoryContext();

  	//	String dn = constructDN(region);
  		String deptDN = null;

  		String[] attrs = {
  				"eaRoleDN", "eaDataRangeDN", "uniqueMember",
				"cn", "description", "telephoneNumber", "mail",
				"businessCategory", "ou", "o", "eaRegionID", "eaDeptParentID"
  		};

  		ldap.setReturningAttributes(attrs);

//  		if (dn.length() > 0) {
//  			ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
//  			deptDN = "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
//  		}
//  		else {
//  			ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
//  			deptDN = "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
//  		}

  		//deptDN = "ou=Region_2,ou=Region_0"  + "," + getDeptClassDirectoryContext();
                deptDN = "ou=1"  + "," + getDeptClassDirectoryContext();
  		System.out.println("deptDN=["+deptDN+"]");
  		System.out.println("dept ID=["+deptID+"]");
  		ldap.searchSubtree(deptDN, "cn=" + deptID);


  		if (ldap.nextResult()) {
  			retDept = createDepartmentFromLdapResult(ldap);
  		}
  	}
  	catch (NamingException e) {
  		throw new SecurityManagerDaoException("Can not create region : " + deptID, e);
  	}
  	finally {
  		closeLdap(ldap);
  	}
  	return retDept;
  }

  /**
   * @todo tomorrow
   * @return
   * @throws SecurityManagerDaoException
   */
  public static Vector getDepartmentList()
      throws SecurityManagerDaoException {
    return new Vector();
  }

// ------------------------------------------------
//  User management
// ------------------------------------------------
  /**
   * Desc : add specified user to the department
   * @param department
   * @param region
   * @param user
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public void addUser(DeptDO department, RegionDO region, UserDO user)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      //modify the attributes
      Attribute attr = new BasicAttribute("uniqueMember");
      DeptDO dept = null;
      try {
        dept = this.lookupDepartment(department, region);
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
      Vector deptVec = dept.getUserList();

      Iterator it = deptVec.iterator();
      while (it.hasNext()) {
        UserDO udoTmp = (UserDO) it.next();
        if (udoTmp != null) {
          attr.add("uid=" + udoTmp.getUserId() + "," + sc.userDNSuffix);
        }
      }

      attr.add("uid=" + user.getUserId() + "," + sc.userDNSuffix);

      ModificationItem[] mods = new ModificationItem[1];
      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);

      LdapOperation.modifyAttributes(deptDN, mods);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can't clear role of department " + department.getDepartmentID(), e);
    }
    finally {
      closeLdap(ldap);
    }

  }

  /**
   * Desc : remove the specified user from the department
   * @param department
   * @param region
   * @param user
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public void removeUser(DeptDO department, RegionDO region, UserDO user)
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      //modify the attributes
      Attribute attr = new BasicAttribute("uniqueMember");
      DeptDO dept = this.lookupDepartment(department, region);
      Vector deptVec = dept.getUserList();

      Iterator it = deptVec.iterator();
      while (it.hasNext()) {
        UserDO udoTmp = (UserDO) it.next();
        if (udoTmp != null) {
          String userId = udoTmp.getUserId();
          if (!userId.equalsIgnoreCase(user.getUserId())) {
            attr.add("uid=" + userId + "," + sc.userDNSuffix);
          }
        }
      }

      ModificationItem[] mods = new ModificationItem[1];
      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);

      LdapOperation.modifyAttributes(deptDN, mods);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can't clear role of department " + department.getDepartmentID(), e);
    }
    finally {
      closeLdap(ldap);
    }

  }

  /**
   * Desc : get all users in the department
   * @param department
   * @param region
   * @return
   * @throws SecurityManagerDaoException
   */
  public Vector getUserList(DeptDO department, RegionDO region)
      throws SecurityManagerDaoException {
    LdapOperation ldap = null;
    Vector retVec = new Vector();

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String[] attrs = {"uniqueMember"};
      ldap.setReturningAttributes(attrs);

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      ldap.searchSubtree(deptDN, "cn=" + department.getDepartmentID());

      if (ldap.nextResult()) {
        Set s = ldap.getAllResultAttributeValues("uniqueMember");
        Iterator it = s.iterator();
        while (it.hasNext()) {
          try {
            StringBuffer sb = new StringBuffer( (String) it.next());
            int begin = sb.indexOf("=");
            int end = sb.indexOf(",");
            retVec.add(UserManager.lookupUser(sb.substring(begin + 1, end).toString()));
          }
          catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can't clear role of department " + department.getDepartmentID(), e);
    }
    finally {
      closeLdap(ldap);
    }
    return retVec;
  }

  /**
   * Desc : get all users in the department
   * @param department
   * @param region
   * @return
   * @throws SecurityManagerDaoException
   * @author shenwei
   */
  public Vector getUserList(String deptID)//add by shenwei 2004-02-04参数deptID可以是多个部门，如deptID="1,2,3"
  throws SecurityManagerDaoException {
  	LdapOperation ldap = null;
  	Vector retVec = new Vector();
	String noUserDept="";
  	try {
  		ldap = getLdap();

  		String deptCtxDN = getDeptClassDirectoryContext();

  		String[] attrs = {"uniqueMember"};
  		ldap.setReturningAttributes(attrs);
  		String deptDN = null;

  		deptDN = "ou=Region_2,ou=Region_0"  + "," + getDeptClassDirectoryContext();
  		String aDeptID[] = deptID.split(",");
  		for (int k=0; k<aDeptID.length;k++){
  			try{
				if (aDeptID[k].indexOf("Department_")<0)
					aDeptID[k] = "Department_" +aDeptID[k];
    			ldap.searchSubtree(deptDN, "cn=" + aDeptID[k]);
	  			if (ldap.nextResult()) {
	  				Set s = ldap.getAllResultAttributeValues("uniqueMember");
	  				Iterator it = s.iterator();
	  				while (it.hasNext()) {
	  					try {
	  						StringBuffer sb = new StringBuffer( (String) it.next());
	  						int begin = sb.indexOf("=");
	  						int end = sb.indexOf(",");
	  						retVec.add(UserManager.lookupUser(sb.substring(begin + 1, end).toString()));
	  					}
	  					catch (Exception ex) {
	  						ex.printStackTrace();
	  					}
	  				}
	  			}
  			}catch (NamingException e) {
  				 if (noUserDept.equals(""))
					noUserDept = aDeptID[k];
				else
					noUserDept +="," +aDeptID[k];
  			}
  		}

  	}
  	catch (NamingException e) {
  		throw new SecurityManagerDaoException("Can't clear role of department " + deptID+"["+noUserDept+"]", e);
  	}
  	finally {
  		closeLdap(ldap);
  	}
  	return retVec;
  }

  /**
   * Desc : get all users in the department and sub department
   * @param department
   * @param region
   * @return
   * @throws SecurityManagerDaoException
   * @author shenwei
   */
  public Vector getAllUserList(String deptID)//add by shenwei 2004-02-04参数deptID只能是一个部门，如deptID="1"
  throws SecurityManagerDaoException {
  	LdapOperation ldap = null;
	String noUserDept="";
  	try {
  		ldap = getLdap();

  		String deptCtxDN = getDeptClassDirectoryContext();

  		String[] attrs = {"uniqueMember"};
  		ldap.setReturningAttributes(attrs);
  		String deptDN = null;

  		deptDN = "ou=Region_2,ou=Region_0"  + "," + getDeptClassDirectoryContext();
  			try{
				if (deptID.indexOf("Department_")<0)
					deptID = "Department_" +deptID;
    			ldap.searchSubtree(deptDN, "cn=" + deptID);
	  			if (ldap.nextResult()) {
	  				Set s = ldap.getAllResultAttributeValues("uniqueMember");
	  				Iterator it = s.iterator();
	  				while (it.hasNext()) {
	  					try {
	  						StringBuffer sb = new StringBuffer( (String) it.next());
	  						int begin = sb.indexOf("=");
	  						int end = sb.indexOf(",");
	  						vecAllUsers.add(UserManager.lookupUser(sb.substring(begin + 1, end).toString()));
	  					}
	  					catch (Exception ex) {
	  						ex.printStackTrace();
	  					}
	  				}
	  			}
  			}catch (NamingException e) {
  				 if (noUserDept.equals(""))
					noUserDept = deptID;
				else
					noUserDept +="," +deptID;
  			}

  			Vector vct =null;
			try{
				vct =this.getSubDepartment(deptID);
			}catch(Exception e){

			}
			if (vct.size()>0){
				for(int m=0;m<vct.size();m++){
					DeptDO retDept = (DeptDO)vct.get(m);
					getAllUserList(retDept.getDepartmentID());
				}
			}


  	}
  	catch (NamingException e) {
  		throw new SecurityManagerDaoException("Can't clear role of department " + deptID+"["+noUserDept+"]", e);
  	}
  	finally {
  		closeLdap(ldap);
  	}
  	return vecAllUsers;
  }

  /**
   * Desc : remove all the users from the department
   * @param department
   * @param region
   * @throws SecurityManagerDaoException
   */
  public void clearUsers(DeptDO department, RegionDO region)
      throws SecurityManagerDaoException {
    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      //modify the attributes
      Attribute attr = new BasicAttribute("uniqueMember");

      ModificationItem[] mods = new ModificationItem[1];
      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);

      LdapOperation.modifyAttributes(deptDN, mods);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can't clear role of department " + department.getDepartmentID(), e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  public boolean isMemberOf(DeptDO department, RegionDO region, UserDO user)
      throws SecurityManagerDaoException {
    LdapOperation ldap = null;
    Vector retVec = new Vector();

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String[] attrs = {"uniqueMember"};
      ldap.setReturningAttributes(attrs);

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      ldap.searchSubtree(deptDN, "cn=" + department.getDepartmentID());

      if (ldap.nextResult()) {
        Set s = ldap.getAllAttributeValues("uniqueMember");
        Iterator it = s.iterator();
        String userDN = "uid=" + user.getUserId() + "," + sc.userDNSuffix;
        while (it.hasNext()) {
          if (userDN.equalsIgnoreCase( (String) it.next())) {
            return true;
          }
        }
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can't clear role of department " + department.getDepartmentID(), e);
    }
    finally {
      closeLdap(ldap);
    }
    return false;
  }

// ------------------------------------------------
//  Role management
// ------------------------------------------------

  /**
   * Desc : add the specified role to the department
   * @param department
   * @param region
   * @param role
   * @throws ObjectNotExistException
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public void setRole(DeptDO department, RegionDO region, RoleDO role)
      throws ObjectNotExistException, ObjectAlreadyExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }


      //modify the attributes
      Attribute attr = new BasicAttribute("eaRoleDN");
      DeptDO dept = this.lookupDepartment(department, region);
      Vector deptVec = dept.getRoleList();

      Iterator it = deptVec.iterator();
      while (it.hasNext()) {
        RoleDO rdoTmp = (RoleDO) it.next();
        if (rdoTmp != null) {
          attr.add("cn=" + rdoTmp.getRoleID() + "," + sc.rolesCtxDN);
        }
      }

      attr.add("cn=" + role.getRoleID() + "," + sc.rolesCtxDN);

      ModificationItem[] mods = new ModificationItem[1];
      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);

      LdapOperation.modifyAttributes(deptDN, mods);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can't clear role of department " + department.getDepartmentID(), e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * Desc : remove the specified role from department
   * @param department
   * @param region
   * @param role
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public void removeRole(DeptDO department, RegionDO region, RoleDO role)
      throws ObjectNotExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      //modify the attributes
      Attribute attr = new BasicAttribute("eaRoleDN");
      DeptDO dept = this.lookupDepartment(department, region);
      Vector deptVec = dept.getRoleList();

      Iterator it = deptVec.iterator();
      while (it.hasNext()) {
        RoleDO roleTmp = (RoleDO) it.next();
        if (roleTmp != null) {
          String roleId = roleTmp.getRoleID();
          if (!roleId.equalsIgnoreCase(role.getRoleID())) {
            attr.add("cn=" + roleId + "," + sc.rolesCtxDN);
          }
        }
      }

      ModificationItem[] mods = new ModificationItem[1];
      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);

      LdapOperation.modifyAttributes(deptDN, mods);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can't clear role of department " + department.getDepartmentID(), e);
    }
    finally {
      closeLdap(ldap);
    }
  }


  public void clearupRoles(DeptDO department, RegionDO region, Set all, Vector trash)
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      //modify the attributes
      Attribute attr = new BasicAttribute("eaRoleDN");
      all.removeAll(trash);

      Iterator it = all.iterator();
      while (it.hasNext()){
        attr.add(it.next());
      }

      ModificationItem[] mods = new ModificationItem[1];
      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);

      LdapOperation.modifyAttributes(deptDN, mods);
    }
    catch (Exception e) {
      throw new SecurityManagerDaoException("Can't clear role of department " + department.getDepartmentID(), e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  /**
   * Desc : get all roles assigned to the department
   * @param department
   * @param region
   * @return
   * @throws SecurityManagerDaoException
   */
  public Vector getRoleList(DeptDO department, RegionDO region)
      throws SecurityManagerDaoException {
    LdapOperation ldap = null;
    Vector retVec = new Vector();

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String[] attrs = {"eaRoleDN"};
      ldap.setReturningAttributes(attrs);

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      ldap.searchSubtree(deptDN, "cn=" + department.getDepartmentID());

      if (ldap.nextResult()) {
        Set s = ldap.getAllAttributeValues("eaRoleDN");
        Iterator it = s.iterator();
        while (it.hasNext()) {
          try {
            StringBuffer sb = new StringBuffer( (String) it.next());
            int begin = sb.indexOf("=");
            int end = sb.indexOf(",");

            retVec.add(RoleManager.lookupRole(sb.substring(begin + 1, end).toString()));
          }
          catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can't clear role of department " + department.getDepartmentID(), e);
    }
    finally {
      closeLdap(ldap);
    }
    return retVec;
  }

  /**
   * Desc : remove all roles assigned to the department
   * @param department
   * @param region
   * @throws SecurityManagerDaoException
   */
  public void clearRoles(DeptDO department, RegionDO region)
      throws SecurityManagerDaoException {
    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      //modify the attributes
      Attribute attr = new BasicAttribute("eaRoleDN");

      ModificationItem[] mods = new ModificationItem[1];
      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);

      LdapOperation.modifyAttributes(deptDN, mods);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can't clear role of department " + department.getDepartmentID(), e);
    }
    finally {
      closeLdap(ldap);
    }
  }

// ------------------------------------------------
//  DataRange management
// ------------------------------------------------

  /**
   * Desc : add the specified data range to the department
   * @param department
   * @param region
   * @param range
   * @throws ObjectNotExistException
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public void setRange(DeptDO department, RegionDO region, RangeDO range)
      throws ObjectNotExistException, ObjectAlreadyExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      //modify the attributes
      Attribute attr = new BasicAttribute("eaDataRangeDN");
      DeptDO dept = this.lookupDepartment(department, region);
      Vector deptVec = dept.getRangeList();

      Iterator it = deptVec.iterator();
      while (it.hasNext()) {
        RangeDO rdoTmp = (RangeDO) it.next();
        if (rdoTmp != null) {
          attr.add("eadatarangeid=" + rdoTmp.getRangeID() + ",ou=" + rdoTmp.getCategory() + "," + sc.rangeCtxDN);
        }
      }

      attr.add("eadatarangeid=" + range.getRangeID() + ",ou=" + range.getCategory() + "," + sc.rangeCtxDN);

      ModificationItem[] mods = new ModificationItem[1];
      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);

      LdapOperation.modifyAttributes(deptDN, mods);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can't set range of department " + department.getDepartmentID(), e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  public void removeRange(DeptDO department, RegionDO region, RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      //modify the attributes
      Attribute attr = new BasicAttribute("eaDataRangeDN");
      DeptDO dept = this.lookupDepartment(department, region);
      Vector deptVec = dept.getRangeList();

      Iterator it = deptVec.iterator();
      while (it.hasNext()) {
        RangeDO rangeTmp = (RangeDO) it.next();
        if (rangeTmp != null) {
          String rangeId = rangeTmp.getRangeID();
          if (!rangeId.equals(range.getRangeID())) {
            attr.add("eadatarangeid=" + rangeId + ",ou=" + rangeTmp.getCategory() + "," + sc.rangeCtxDN);
          }
        }
      }

      ModificationItem[] mods = new ModificationItem[1];
      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);

      LdapOperation.modifyAttributes(deptDN, mods);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can't remove range of department " + department.getDepartmentID(), e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  public Vector getRangeList(DeptDO department, RegionDO region)
      throws SecurityManagerDaoException {
    LdapOperation ldap = null;
    Vector retVec = new Vector();

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String[] attrs = {"eaDataRangeDN"};
      ldap.setReturningAttributes(attrs);

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      ldap.searchSubtree(deptDN, "cn=" + department.getDepartmentID());

      if (ldap.nextResult()) {
        Set s = ldap.getAllAttributeValues("eaDataRangeDN");
        Iterator it = s.iterator();
        while (it.hasNext()) {
          try {
            StringBuffer sb = new StringBuffer( (String) it.next());
            int begin = sb.indexOf("=");
            int end = sb.indexOf(",");

            retVec.add(RangeManager.lookupRange(sb.substring(begin + 1, end).toString()));
          }
          catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can't clear role of department " + department.getDepartmentID(), e);
    }
    finally {
      closeLdap(ldap);
    }
    return retVec;

  }

  public void clearRange(DeptDO department, RegionDO region)
      throws SecurityManagerDaoException {
    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      String dn = constructDN(region);
      String deptDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + department.getDepartmentID());
        deptDN = "cn=" + department.getDepartmentID() + "," + "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      //modify the attributes
      Attribute attr = new BasicAttribute("eaDataRangeDN");

      ModificationItem[] mods = new ModificationItem[1];
      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);

      LdapOperation.modifyAttributes(deptDN, mods);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can't clear range of department " + department.getDepartmentID(), e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  public static DeptDO createDepartmentFromLdapResult(LdapOperation ldap) {
    DeptDO dept = null;

    try {
      dept = new DeptDO();

      dept.setComments(ldap.getResultAttribute("description"));
      dept.setDepartmentID(ldap.getResultAttribute("cn"));
      dept.setTeleNumber(ldap.getResultAttribute("telephoneNumber"));
      dept.setEmail(ldap.getResultAttribute("mail"));
      dept.setSpecialFlag(ldap.getResultAttribute("businessCategory"));
      dept.setRegionID(ldap.getResultAttribute("eaRegionID"));
      dept.setRegionName(ldap.getResultAttribute("ou"));
      dept.setName(ldap.getResultAttribute("o"));
      dept.setDepartmentParentID(ldap.getResultAttribute("eaDeptParentID"));
      dept.setNotes(ldap.getResultAttribute("notes"));
      Set neRole = ldap.getAllResultAttributeValues("eaRoleDN");
      Vector trash = new Vector();
      Vector vRole = new Vector();
      Iterator itRole = neRole.iterator();
      while (itRole.hasNext()) {
        String roleDN = (String) itRole.next();

        StringTokenizer stComma = new StringTokenizer(roleDN, ",");
        StringTokenizer stEquals = new StringTokenizer(stComma.nextToken(), "=");
        stEquals.nextToken();

        RoleDO rdo = null;
        RoleDO rrdo = new RoleDO();
        String parsedRoleID = stEquals.nextToken();
        rrdo.setRoleID(parsedRoleID);

        try {
          rdo = RoleManager.lookupRole(parsedRoleID);
        }
        catch (SecurityManagerDaoException ex) {
          rdo = null;
        }
        catch (ObjectNotExistException ex) {
          trash.add(rrdo);
          rdo = null;
        }
        vRole.add(rdo);
      }

      DeptLdapDAO dao = new DeptLdapDAO();
      RegionDO tmpRDO = new RegionDO();
      tmpRDO.setRegionID(dept.getRegionID());

      try {
        tmpRDO = RegionManager.lookupRegion(tmpRDO);
      }
      catch (Exception ex) {
      }

      Set neRole1 = ldap.getAllResultAttributeValues("eaRoleDN");

      for (int jj = 0; jj < trash.size(); jj++) {
        RoleDO tmpDO = (RoleDO) trash.elementAt(jj);
        try {
          dao.clearupRoles(dept,tmpRDO,neRole1,trash);
        }
        catch (Exception ex3) {
        }
      }

      dept.setRoleList(vRole);

      Set neRange = ldap.getAllResultAttributeValues("eaDataRangeDN");

      Vector vRange = new Vector();
      Iterator itRange = neRange.iterator();
      while (itRange.hasNext()) {
        String rangeDN = (String) itRange.next();

        StringTokenizer stComma = new StringTokenizer(rangeDN, ",");
        StringTokenizer stEquals = new StringTokenizer(stComma.nextToken(), "=");
        stEquals.nextToken();

        RangeDO rado = null;

        try {
          rado = RangeManager.lookupRange(stEquals.nextToken());
        }
        catch (SecurityManagerDaoException ex1) {
          rado = null;
        }
        catch (ObjectNotExistException ex1) {
          rado = null;
        }
        vRange.add(rado);
      }
      dept.setRangeList(vRange);

      Set neUser = ldap.getAllResultAttributeValues("uniqueMember");
      Vector vUser = new Vector();
      Iterator itUser = neUser.iterator();
      while (itUser.hasNext()) {
        String userDN = (String) itUser.next();

        StringTokenizer stComma = new StringTokenizer(userDN, ",");
        StringTokenizer stEquals = new StringTokenizer(stComma.nextToken(), "=");
        stEquals.nextToken();

        UserDO udo = null;

        try {
          udo = UserManager.lookupUser(stEquals.nextToken());
        }
        catch (ObjectNotExistException ex2) {
          udo = null;
        }
        catch (SecurityManagerDaoException ex2) {
          udo = null;
        }

        vUser.add(udo);
      }
      dept.setUserList(vUser);
    }
    catch (NamingException e) {
      dept = null;
    }
    return dept;
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

      String deptCtxDN = getDeptClassDirectoryContext();

      String[] attrs = {"cn"};
      ldap.setReturningAttributes(attrs);

      ldap.searchSubtree(deptCtxDN, "objectClass=" + getDeptObjectClass2());

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
   * Desc : construct the hierarchy DN of region data object
   * @param region
   * @return
   */
  private String constructDN(RegionDO region) {

    RegionDO newRegion = null;
    Iterator it = region.getParentHierarchy().iterator();
    StringBuffer sb = new StringBuffer();

    if (it.hasNext()) {
      String regionId = (String) it.next();
      sb.append("ou=" + regionId + ",");

      if (regionId != null && !regionId.equalsIgnoreCase("groups")) {
        RegionDO rdo = new RegionDO();
        rdo.setRegionID(regionId);
        try {
          newRegion = RegionManager.lookupRegion(rdo);
        }
        catch (SecurityManagerDaoException ex) {}
        catch (ObjectNotExistException ex) {}

        if (newRegion != null) {
          sb.append(constructDN(newRegion));
        }
      }
    }

    if (sb.length() > 1) {
      if (sb.charAt(sb.length() - 1) == ',') {
        sb.deleteCharAt(sb.length() - 1);

      }
    }
    return sb.toString();
  }

  /**
   *
   * @param deptID
   * @param up if get the parent department
   * @return All ranges of the department(parent)
   * @throws SecurityManagerDaoException
   * @throws ObjectNotExistException
   */
  public Vector getRolesOfDepartment(String deptID, String regionID, boolean up)
      throws SecurityManagerDaoException, ObjectNotExistException {
    Vector result = new Vector();
    try {
      RegionDO tmpRDO = new RegionDO();
      DeptDO tmpDDO = new DeptDO();

      tmpRDO.setRegionID(regionID);
      tmpDDO.setDepartmentID(deptID);

      tmpRDO = RegionManager.lookupRegion(tmpRDO);
      tmpDDO = this.lookupDepartment(tmpDDO, tmpRDO);

      if (up) {
        if (tmpDDO.getDepartmentParentID() == null
            || tmpDDO.getDepartmentParentID().equals("")
            || tmpDDO.getDepartmentParentID().equals("*")) {
          result = RoleManager.getRoleList();
        }
        else {
          DeptDO tmp = new DeptDO();
          tmp.setDepartmentID(tmpDDO.getDepartmentParentID());
          tmp = this.lookupDepartment(tmp, tmpRDO);
          result = tmp.getRoleList();
        }
      }
      else {
        result = tmpDDO.getRoleList();
      }
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Failed to retrieve department information");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("Failed to retrieve roles of department", ex);
    }
    return result;

  }

  /**
   *
   * @param deptID
   * @param up if get the parent department
   * @return All ranges of the department(parent)
   * @throws SecurityManagerDaoException
   * @throws ObjectNotExistException
   */
  public Vector getRangesOfDepartment(String deptID, String regionID, boolean up)
      throws SecurityManagerDaoException, ObjectNotExistException {
    Vector result = new Vector();
    try {
      RegionDO tmpRDO = new RegionDO();
      DeptDO tmpDDO = new DeptDO();

      tmpRDO.setRegionID(regionID);
      tmpDDO.setDepartmentID(deptID);

      tmpRDO = RegionManager.lookupRegion(tmpRDO);
      tmpDDO = this.lookupDepartment(tmpDDO, tmpRDO);

      if (up) {
        if (tmpDDO.getDepartmentParentID() == null
            || tmpDDO.getDepartmentParentID().equals("")
            || tmpDDO.getDepartmentParentID().equals("*")) {

            Vector allRanges = tmpRDO.getRangeList();
/*          RangeDO currRDO = RangeManager.lookupRange(tmpRDO.getRegionID());
          Vector allSubRegion = RegionManager.getSubRegions(tmpRDO);
          Vector allCorresRange = new Vector();

          for (int q = 0; q < allSubRegion.size(); q++) {
            RegionDO qRDO = (RegionDO) allSubRegion.elementAt(q);
            RangeDO qqRDO = RangeManager.lookupRange(qRDO.getRegionID());
            allCorresRange.add(qqRDO);

          }

          Vector allRanges = RangeManager.getAllRanges();
          Vector allRemovedRaanges = new Vector();
          for (int i = 0; i < allRanges.size(); i++) {
            RangeDO circleRDO = (RangeDO) allRanges.elementAt(i);

            if (currRDO.getCategory().equals(circleRDO.getCategory())) {
              allRemovedRaanges.add(circleRDO);
            }
          }
          allRanges.removeAll(allRemovedRaanges);
          allRanges.addAll(allCorresRange);*/

          result = allRanges;
        }

        else {
          DeptDO tmp = new DeptDO();
          tmp.setDepartmentID(tmpDDO.getDepartmentParentID());
          tmp = this.lookupDepartment(tmp, tmpRDO);
          result = tmp.getRangeList();
        }
      }
      else {
        result = tmpDDO.getRangeList();
      }
    }

    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Failed to retrieve department information");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("Failed to retrieve ranges of department", ex);
    }
    return result;

  }

  public Vector getSubDepartment(String deptID, String regionID)
      throws SecurityManagerDaoException, ObjectNotExistException {
    Vector result = new Vector();
    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();

      RegionDO region = new RegionDO();
      region.setRegionID(regionID);
      region = RegionManager.lookupRegion(region);

      String dn = constructDN(region);
      String deptDN = null;

      String[] attrs = {
                       "eaRoleDN", "eaDataRangeDN", "uniqueMember",
                       "cn", "description", "telephoneNumber", "mail",
                       "businessCategory", "ou", "o", "eaRegionID", "eaDeptParentID"
      };

      ldap.setReturningAttributes(attrs);

      if (dn.length() > 0) {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + dn + "," + deptCtxDN, "cn=" + deptID);
        deptDN = "ou=" + region.getRegionID() + "," + dn + "," + getDeptClassDirectoryContext();
      }
      else {
        ldap.searchSubtree("ou=" + region.getRegionID() + "," + deptCtxDN, "ou=" + deptID);
        deptDN = "ou=" + region.getRegionID() + "," + getDeptClassDirectoryContext();
      }

      ldap.searchSubtree(deptDN, "eaDeptParentID=" + deptID);

      while (ldap.nextResult()) {
        DeptDO retDept = createDepartmentFromLdapResult(ldap);
        result.add(retDept);
      }
    }
    catch (ObjectNotExistException ex) {
      throw new ObjectNotExistException("Failed to retrieve department information");
    }
    catch (SecurityManagerDaoException ex) {
      throw new SecurityManagerDaoException("Failed to retrieve ranges of department", ex);
    } catch (NamingException e) {
		e.printStackTrace();
	}
    return result;

  }


  public Vector getSubDepartment(String deptID)
  throws SecurityManagerDaoException, ObjectNotExistException //add by shenwei 2004-02-04
  {
    Vector result = new Vector();
    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String deptCtxDN = getDeptClassDirectoryContext();



      String deptDN = null;

      String[] attrs = {
                       "eaRoleDN", "eaDataRangeDN", "uniqueMember",
                       "cn", "description", "telephoneNumber", "mail",
                       "businessCategory", "ou", "o", "eaRegionID", "eaDeptParentID"
      };

      ldap.setReturningAttributes(attrs);

	  deptDN = "ou=Region_2,ou=Region_0"  + "," + getDeptClassDirectoryContext();
      ldap.searchSubtree(deptDN, "eaDeptParentID=" + deptID);

      while (ldap.nextResult()) {
        DeptDO retDept = createDepartmentFromLdapResult(ldap);
        result.add(retDept);
      }
    } catch(Exception e) {
    	e.printStackTrace();
    }
    return result;

  }

public Vector getSubDepartment() throws SecurityManagerDaoException, ObjectNotExistException{
    Vector result = new Vector();
    LdapOperation ldap = null;

    try {
      ldap = getLdap();
      String deptDN = null;

      String[] attrs = {
                       "eaRoleDN", "eaDataRangeDN", "uniqueMember",
                       "cn", "description", "telephoneNumber", "mail",
                       "businessCategory", "ou", "o", "eaRegionID", "eaDeptParentID"
      };

      ldap.setReturningAttributes(attrs);

	  deptDN = "OU=部门树,OU=nms_users,DC=NMS,DC=FJ,DC=CM";
      ldap.searchSubtree(deptDN, "objectClass=organizationalUnit");

      while (ldap.nextResult()) {
        DeptDO retDept = createDepartmentFromLdapResult(ldap);
        result.add(retDept);
      }
    } catch(Exception e) {
    	e.printStackTrace();
    }
    return result;
}
}
