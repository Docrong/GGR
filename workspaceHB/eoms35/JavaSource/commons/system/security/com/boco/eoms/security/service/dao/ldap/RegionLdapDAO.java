/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-20         created
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
import com.boco.eoms.security.service.dao.RegionDAO;
import com.boco.eoms.security.service.dao.ldap.factory.LdapOperation;
import com.boco.eoms.security.service.manager.RangeManager;
import com.boco.eoms.security.service.manager.RegionManager;
import com.boco.eoms.security.service.model.DeptDO;
import com.boco.eoms.security.service.model.RangeDO;
import com.boco.eoms.security.service.model.RegionDO;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class RegionLdapDAO
    extends BaseLdapDAO
    implements RegionDAO {

  //protected static String REGION_OBJECT_CLASS = "organizationalunit";
  protected static String REGION_OBJECT_CLASS = "ecregion";

  private static SystemConfig sc = SystemConfig.getInstance();

  public RegionLdapDAO() {}

  /**
   * region class directory context
   * @return
   */
  public static String getRegionClassDirectoryContext() {
    return sc.groupCtxDN;
  }

  /**
   * get region object class
   */
  protected static String getRegionObjectClass() {
    return REGION_OBJECT_CLASS;
  }

  public static String lookupRegionDN(LdapOperation ldap, Object region)
      throws NamingException, ObjectNotExistException {

    String rgndn;
    // get the region dn
    String rgnid;

    if (region instanceof RegionDO) {
      rgnid = ( (RegionDO) region).getRegionID();
    }
    else if (region instanceof String) {
      rgnid = (String) region;
    }
    else {
      throw new IllegalArgumentException("bad region parameter");
    }

    String filter = "(&(objectClass=" + getRegionObjectClass() + ")(ou=" + rgnid + "))";
    ldap.searchSubtree(getRegionClassDirectoryContext(), filter);

    if (ldap.nextResult()) {
      rgndn = ldap.getResultName();
    }
    else {
      throw new ObjectNotExistException("region " + rgnid + " not exist.");
    }

    ldap.resetSearch();

    return rgndn;
  }

  /**
   * Desc : create the region
   * @param region - the region information which will be created
   * @return RegionDO - the created region data object
   * @throws ObjectAlreadyExistException
   * @throws SecurityManagerDaoException
   */
  public RegionDO createRegion(RegionDO region)
      throws ObjectAlreadyExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;

    if (region.getRegionID() == null || region.getRegionID().length() == 0) {
      throw new IllegalArgumentException("Region id can't be null");
    }

    try {
      ldap = getLdap();
//====================================================================
//to check if the region has already exist!!!
      String rdn;
      boolean bFound = false;
      try {
        rdn = lookupRegionDN(ldap, region);
        bFound = true;
      }
      catch (Exception ex) {
        //not found is expected.
        bFound = false;
      }

      if (bFound) {
        throw new ObjectAlreadyExistException("The Region " + region + " has already exist.");
      }
//================================================================

      // check if the region has already exist
      String regionCtxDN = getRegionClassDirectoryContext();

      String dn = constructDN(region);
      String regionDN = null;

      if (dn.length() > 0) {
        ldap.searchSubtree(regionCtxDN, "ou=" + region.getRegionID() + "," + dn);
        regionDN = "ou=" + region.getRegionID() + "," + dn + "," + getRegionClassDirectoryContext();
      }
      else {
        ldap.searchSubtree(regionCtxDN, "ou=" + region.getRegionID());
        regionDN = "ou=" + region.getRegionID() + "," + getRegionClassDirectoryContext();
      }

      if (ldap.nextResult()) {
        throw new ObjectAlreadyExistException("The region " + region + " has already exist.");
      }

      // Create the domain
      Attributes attributes = new BasicAttributes(true);

      Attribute attr = new BasicAttribute("objectClass");
      attr.add("top");
      attr.add("organizationalunit");
      attr.add(REGION_OBJECT_CLASS);

      attributes.put(attr);
      attributes.put("ou", region.getRegionID());
      attributes.put("l", region.getName());
      attributes.put("description", region.getComment().trim());

      LdapOperation.addElement(regionDN, attributes);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not create region : " + region, e);
    }
    finally {
      closeLdap(ldap);
    }
    return region;
  }

  /**
   * Desc : delete the region
   * @param region - the region information which will be deleted
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public void deleteRegion(RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;

    String dn = constructDN(region);

    String regionDN = "";

    if (dn.length() > 0) {
      regionDN = "ou=" + region.getRegionID() + "," + constructDN(region) + "," + getRegionClassDirectoryContext();
    }
    else {
      regionDN = "ou=" + region.getRegionID() + "," + getRegionClassDirectoryContext();
    }

    try {
      ldap = getLdap();
      LdapOperation.deleteElement(regionDN);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not delete region : " + regionDN, e);
    }
    finally {
      closeLdap(ldap);
    }

  }

  /**
   * Desc : update the region
   * @param region - the new region information which will be updated
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public void updateRegion(RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String dn = constructDN(region);
      String regionDN = "";

      if (dn.length() > 0) {
        regionDN = "ou=" + region.getRegionID() + "," + dn + "," + getRegionClassDirectoryContext();
      }
      else {
        regionDN = "ou=" + region.getRegionID() + "," + getRegionClassDirectoryContext();
      }

      ModificationItem[] mods = new ModificationItem[2];

      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("description", region.getComment()));

      mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
                                     new BasicAttribute("l", region.getName()));

      LdapOperation.modifyAttributes(regionDN, mods);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not update role : " + region, e);
    }
    finally {
      closeLdap(ldap);
    }

  }

  /**
   * Desc :
   * @param RegionDO - the region you want to search
   * @return RegionDO - the return region data object
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public RegionDO lookupRegion(RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException {

    LdapOperation ldap = null;
    RegionDO retRegion = null;

    try {
      ldap = getLdap();

      String[] attrs = {"cn", "description", "ou", "eaDataRangeDN"};
      ldap.setReturningAttributes(attrs);

      String regionCtxDN = getRegionClassDirectoryContext();
      ldap.searchSubtree(regionCtxDN, "ou=" + region.getRegionID());

      if (ldap.nextResult()) {
        retRegion = createRegionFromLdapResult(ldap);
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not find region : " + region.getRegionID(), e);
    }
    finally {
      closeLdap(ldap);
    }
    return retRegion;
  }

  public static RegionDO createRegionFromLdapResult(LdapOperation ldap) {
    RegionDO region = null;

    try {
      String regionId = ldap.getResultAttribute("ou");
      String regionName = ldap.getResultAttribute("l");
      String notes=ldap.getResultAttribute("distinguishedName");
      if (regionName == null) {
        regionName = regionId;
      }
      String regionDesc = ldap.getResultAttribute("description");
      Set rangeSet = ldap.getAllResultAttributeValues("eaDataRangeDN");
     
      String entrydn = ldap.getResultName();

      //analyst the dn returned
      String parentID = "";
      String parentName = "";

      StringTokenizer tok = new StringTokenizer(entrydn, ",");
      if (tok.hasMoreTokens()) {
        tok.nextToken();
        if (tok.hasMoreTokens()) {
          parentID = tok.nextToken();
        }
      }
      StringTokenizer tok2 = new StringTokenizer(parentID, "=");

      if (tok2.hasMoreTokens()) {
        tok2.nextToken();
        if (tok2.hasMoreTokens()) {
          parentName = tok2.nextToken();
        }
      }

      Vector aV = new Vector();
      Iterator it = rangeSet.iterator();
      while (it.hasNext()) {
        String dn = (String) it.next();
        StringTokenizer st1 = new StringTokenizer(dn, ",");
        StringTokenizer st2 = new StringTokenizer(st1.nextToken(), "=");
        st2.nextToken();
        String rid = st2.nextToken();

        try {
          RangeDO rdo = RangeManager.lookupRange(rid);
          aV.add(rdo);
        }
        catch (SecurityManagerDaoException ex) {
          //nothing to do
        }
        catch (ObjectNotExistException ex) {
          //nothing to do
        }
      }

      region = new RegionDO();
      region.setRegionID(regionId);
      region.setName(regionName);
      region.setComment(regionDesc);
      region.setRangeList(aV);
      region.setNotes(notes);

      if (!parentName.equalsIgnoreCase("groups")) {
        region.addParentHierarchy(parentName);
      }
    }
    catch (NamingException e) {
      region = null;
    }
    return region;
  }

  /**
   * Desc :
   * @param s
   * @return
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public RegionDO getParentRegion(RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;
    RegionDO retRegion = null;

    try {
      ldap = getLdap();

      String[] attrs = {"cn", "description", "ou","eaDataRangeDN"};
      ldap.setReturningAttributes(attrs);

      region = RegionManager.lookupRegion(region);

      String dn = constructDN(region);
      if (dn.length() > 0){
        dn += "," + getRegionClassDirectoryContext();
      }
      else{
        dn = getRegionClassDirectoryContext();
      }

      StringTokenizer st = new StringTokenizer(dn,",");

      String part2 = st.nextToken();
      String part1 = st.nextToken("");
      part1 = part1.replaceFirst(",","");

      ldap.searchSubtree(part1, part2);

      if (ldap.nextResult()) {
        retRegion = createRegionFromLdapResult(ldap);
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not find region : " + region.getRegionID(), e);
    }
    finally {
      closeLdap(ldap);
    }
    return retRegion;
  }

  /**
   * Desc :
   * @param s
   * @return
   * @throws ObjectNotExistException
   * @throws SecurityManagerDaoException
   */
  public Vector getSubRegions(RegionDO region)
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;
    RegionDO retRegion = null;
    Vector retVector = new Vector();

    try {
      ldap = getLdap();

      String[] attrs = {"cn", "description", "ou","eaDataRangeDN"};
      ldap.setReturningAttributes(attrs);
      String regionCtxDN = getRegionClassDirectoryContext();

      region = RegionManager.lookupRegion(region);
      String dn = constructDN(region);

      if (dn.length() > 0) {
        if (!region.getRegionID().equalsIgnoreCase("groups")) {
          ldap.searchOneLevel("ou=" + region.getRegionID() + "," + dn + "," + regionCtxDN, "objectclass=ecregion");
        }
      }
      else {
        if (!region.getRegionID().equalsIgnoreCase("groups")) {
          ldap.searchOneLevel("ou=" + region.getRegionID() + "," + regionCtxDN, "objectclass=ecregion");
        }
      }

      while (ldap.nextResult()) {
        retRegion = createRegionFromLdapResult(ldap);
        if (retRegion != null && !retRegion.getRegionID().equals(region.getRegionID())) {
          retRegion = RegionManager.lookupRegion(retRegion);
          retVector.add(retRegion);
        }
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not find region : " + region.getRegionID(), e);
    }
    finally {
      closeLdap(ldap);
    }
    return retVector;
  }

  /**
   * Desc :
   * @param obj
   * @return
   * @throws SecurityManagerDaoException
   */
  public Vector getDepartmentList(RegionDO region)
      throws SecurityManagerDaoException {
    LdapOperation ldap = null;
    DeptDO retDepartment = null;
    Vector retVector = new Vector();

    try {
      ldap = getLdap();

      String[] attrs = {"cn", "description", "ou","eaDataRangeDN"};
      ldap.setReturningAttributes(attrs);
      String regionCtxDN = getRegionClassDirectoryContext();

      String dn = constructDN(region);

      if (dn.length() > 0) {
        System.out.println("1. ou=" + region.getRegionID() + "," + dn);
        ldap.searchOneLevel("ou=" + region.getRegionID() + "," + dn + "," + regionCtxDN, "cn=*");

      }
      else {
        System.out.println("2. ou=" + region.getRegionID());
        ldap.searchOneLevel("ou=" + region.getRegionID() + "," + regionCtxDN, "cn=*");
      }

      while (ldap.nextResult()) {
        retDepartment = DeptLdapDAO.createDepartmentFromLdapResult(ldap);
        if (retDepartment != null) {
          retVector.add(retDepartment);
        }
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can not find region : " + region.getRegionID(), e);
    }
    finally {
      closeLdap(ldap);
    }
    return retVector;
  }

  /**
   * Desc :
   * @return
   * @throws SecurityManagerDaoException
   */
  public Vector getRegionList()
      throws SecurityManagerDaoException {
    LdapOperation ldap = null;
    RegionDO retRegion = null;
    Vector retVector = new Vector();

    try {
      ldap = getLdap();

      String[] attrs = {"cn", "description", "ou","eaDataRangeDN"};
      ldap.setReturningAttributes(attrs);
      String regionCtxDN = getRegionClassDirectoryContext();
      //ldap.searchSubtree(regionCtxDN, "ou=*");
      ldap.searchSubtree(regionCtxDN, "objectclass=ecRegion");

      while (ldap.nextResult()) {
        retRegion = createRegionFromLdapResult(ldap);
        retVector.add(retRegion);
      }
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Get Region List Failed!!", e);
    }
    finally {
      closeLdap(ldap);
    }
    return retVector;
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

      String regionCtxDN = getRegionClassDirectoryContext();

      String[] attrs = {"ou"};
      ldap.setReturningAttributes(attrs);

      ldap.searchSubtree(regionCtxDN, "objectClass=" + getRegionObjectClass());

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
  public String constructDN(RegionDO region) {

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
          newRegion = this.lookupRegion(rdo);
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

  public void addRange(RegionDO region, RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String regionCtxDN = getRegionClassDirectoryContext();

      region = RegionManager.lookupRegion(region);

      String dn = constructDN(region);

      if (dn.length() > 0) {
        dn = "ou=" + region.getRegionID() + "," + dn + "," + regionCtxDN;
      }
      else {
        dn = "ou=" + region.getRegionID() + "," + regionCtxDN;
      }

      //modify the attributes
      Attribute attr = new BasicAttribute("eaDataRangeDN");
      Vector regionVec = region.getRangeList();

      Iterator it = regionVec.iterator();
      while (it.hasNext()) {
        RangeDO rdoTmp = (RangeDO) it.next();
        if (rdoTmp != null) {
          attr.add("eadatarangeid=" + rdoTmp.getRangeID() + ",ou=" + rdoTmp.getCategory() + "," + sc.rangeCtxDN);
        }
      }

      attr.add("eadatarangeid=" + range.getRangeID() + ",ou=" + range.getCategory() + "," + sc.rangeCtxDN);

      ModificationItem[] mods = new ModificationItem[1];
      mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);
      System.out.println(regionVec);

      LdapOperation.modifyAttributes(dn, mods);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can't set range of region " + region.getRegionID(), e);
    }
    finally {
      closeLdap(ldap);
    }
  }

  public void removeRange(RegionDO region, RangeDO range)
      throws ObjectNotExistException, SecurityManagerDaoException {
    LdapOperation ldap = null;

    try {
      ldap = getLdap();

      String regionCtxDN = getRegionClassDirectoryContext();

      region = RegionManager.lookupRegion(region);

      Vector rangeVec = region.getRangeList();
      String dn = constructDN(region);

      if (dn.length() > 0) {
        dn = "ou=" + region.getRegionID() + "," + dn + "," + regionCtxDN;
      }
      else {
        dn = "ou=" + region.getRegionID() + "," + regionCtxDN;
      }

      //modify the attributes
      Attribute attr = new BasicAttribute("eaDataRangeDN");

      Iterator it = rangeVec.iterator();
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

      LdapOperation.modifyAttributes(dn, mods);
    }
    catch (NamingException e) {
      throw new SecurityManagerDaoException("Can't remove range of region " + region.getRegionID(), e);
    }
    finally {
      closeLdap(ldap);
    }
  }
}
