/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis            2003-08-15         created
 * V1.0.0.0   Wang Zhuo Wei   2003-08-18         amend
 */

package com.boco.eoms.security.service.dao.ldap.factory;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.security.config.SystemConfig;
import com.boco.eoms.security.service.model.UserDO;
import com.boco.eoms.security.util.CaseInsensitiveStringSet;
import com.sun.jndi.ldap.ctl.PagedResultsControl;
import com.sun.jndi.ldap.ctl.PagedResultsResponseControl;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author weis
 * @version 1.0
 */

/**
 * Provides an easier to use interface for LDAP on top of JNDI.  The
 * JNDI interface is very generic and can be quite cumbesome to use
 * for LDAP.
 */
public class LdapOperation {
  private static SystemConfig sc = SystemConfig.getInstance();

  public LdapOperation() {
  	boolean conFlag = true;
          try {
            this.searchSubtree(SystemConfig.getInstance().userDNSuffix, "userId=test");
            //this.searchUser(SystemConfig.getInstance().userDNSuffix, "userId=test");
          }
  	catch(Exception ex){
  		conFlag = false;
  	}

  	if(!conFlag)
  	{
  		mCtxAdmin = LdapConnectionFactory.getDirContext();
  	}
  }

  /**
   * Create a new facade to a host on a given port.  This does not
   * create a connection to the host, but all future interaction
   * will be with the host.
   */
  public LdapOperation(String dn, char[] password)
      throws NamingException {
    mEnvironment.clear();
    try {
      this.authenticate(dn, password);
    }
    catch (NamingException ex) {
      System.out.println("Not login because of wrong user name or password!!!");
      throw ex;
    }
    //mCtxAdmin = LdapConnectionFactory.getDirContext();
  }

  /**
   * Description: authenticate the user information
   * @param dn - distinguish name
   * @param password - user password
   * @throws AuthenticationException
   * @throws NamingException
   */
  public void authenticate(String dn, char[] password)
      throws AuthenticationException, NamingException {
    mEnvironment.put(Context.INITIAL_CONTEXT_FACTORY, sc.factory);
    mEnvironment.put(Context.PROVIDER_URL, sc.url);
    mEnvironment.put(Context.SECURITY_AUTHENTICATION, "simple");
    mEnvironment.put(Context.SECURITY_PRINCIPAL, dn);
    mEnvironment.put(Context.SECURITY_CREDENTIALS, password);

    mContext = new InitialDirContext(mEnvironment);

    mAttributes = mContext.getAttributes(dn);
  }

  /**
   * Returns the value for a given attribute name.
   *
   * @param name Attribute name
   * @return The value of the attribute
   * @throws javax.naming.NamingException If an error occured
   */
  public String getAttribute(String name)
      throws NamingException {
    return (String) mAttributes.get(name).get();
  }

  /**
   * Returns all values of an attribute as a <code>Set</code>.  The
   * returned set is actually a
   * <code>CaseInsensitiveStringSet</code>.  Normally this will not
   * matter, but it will become apparent if comparing the whole set
   * to, say, a <code>HashSet</code> of normal <code>String</code>
   * objects.
   *
   * @param name An attribute name
   * @return The values of an attribute
   * @throws javax.naming.NamingException If an error occured
   * @see CaseInsensitiveStringSet
   */
  public Set getAllAttributeValues(String name)
      throws NamingException {
    Set values;
    NamingEnumeration valueEnumeration = null;

    values = new CaseInsensitiveStringSet();
    try {
      Attribute attribute = mAttributes.get(name);
      if (attribute != null) {
        valueEnumeration = attribute.getAll();
        while (valueEnumeration.hasMore()) {
          String value;

          value = (String) valueEnumeration.next();
          values.add(value);
        }
      }
    }
    finally {
      if (valueEnumeration != null) {
        valueEnumeration.close();
      }
    }

    return values;
  }

  /**
   * Sets the attributes to returned during the directory lookup.
   * By default, all attributes are returned.
   *
   * @param attributes Array of attributes to return.
   */
  public void setReturningAttributes(String[] attributes) {
    mControls.setReturningAttributes(attributes);
  }

  /**
   * Creates a new element.
   *
   * @param distinguishedName Full DN of the new element
   * @param attributes Attributes of the new element
   * @throws javax.naming.NamingException If element could not be added
   */
  public static void addElement(String distinguishedName, Attributes attributes)
      throws NamingException {
    DirContext subcontext;

    subcontext = mCtxAdmin.createSubcontext(distinguishedName, attributes);
    subcontext.close();
  }

  /**
   * Adds a new element with the attributes specified in a Map.
   * Each value of the map can either be a String, String[], or Set.
   * If the value is either a String[] or Set, then all entries are
   * added for that value.  Here is an example:
   * <pre>
   * Set attributes = new HashSet();
   * // Set a multi-value attribute with a String[]
   * attributes.add("objectClass", new String[] { "top", "organization" });
   * // Set a multi-value attribute with a Set
   * Set phones = new HashSet();
   * phones.add("555-1234");
   * phones.add("555-6789");
   * attributes.add("telephoneNumber", phones);
   * // Set single value attributes with a String
   * attributes.add("o", "myOrg");
   * attributes.add("description", "Sample Organization");
   * ldapOperation.addElement("o=myOrg,dc=example,dc=com", attributes);
   * </pre>
   *
   * @param dn DN to add
   * @param attributes Attributes for new element
   * @throws javax.naming.NamingException if element could not be added
   */
  public static void addElement(String dn, Map attributes)
      throws NamingException {
    Attributes jndiAttributes = new BasicAttributes();

    Iterator attributeNames = attributes.keySet().iterator();
    while (attributeNames.hasNext()) {
      String name = (String) attributeNames.next();
      Object value = attributes.get(name);
      if (value == null) {
        continue;
      }

      if (value instanceof String) {
        jndiAttributes.put(name, value);
      }
      else if (value instanceof Set) {
        Set values = (Set) value;
        BasicAttribute attribute = new BasicAttribute(name);
        Iterator i = values.iterator();
        while (i.hasNext()) {
          attribute.add(i.next());
        }
        jndiAttributes.put(attribute);
      }
      else {
        String[] values = (String[]) value;
        BasicAttribute attribute = new BasicAttribute(name);
        for (int i = 0; i < values.length; i++) {
          attribute.add(values[i]);
        }
        jndiAttributes.put(attribute);
      }
    }
    addElement(dn, jndiAttributes);
  }

  /**
   * Replace a value of an attribute with a new value.
   *
   * @param dn DN of element to modify
   * @param attributeName Attribute to modify
   * @param newValue The new value.
   * @throws javax.naming.NamingException if the attribute could not be modified
   */
  public static void modifyElementAttribute(String dn, String attributeName,
                                            String newValue)
      throws NamingException {
    BasicAttributes attributes = new BasicAttributes();
    attributes.put(attributeName, newValue);
    mCtxAdmin.modifyAttributes(dn, DirContext.REPLACE_ATTRIBUTE, attributes);
  }

  /**
   * Replace a multi-valued attribute with new values.
   *
   * @param dn DN of element to modify
   * @param attributeName Attribute to modify
   * @param newValues The new values
   * @throws javax.naming.NamingException If the attribute could not be modified
   */
  public static void modifyElementAttribute(String dn, String attributeName,
                                            Collection newValues)
      throws NamingException {
    BasicAttributes attributes = new BasicAttributes();
    BasicAttribute attribute = new BasicAttribute(attributeName);
    Iterator i = newValues.iterator();
    while (i.hasNext()) {
      attribute.add(i.next());
    }
    attributes.put(attribute);
    mCtxAdmin.modifyAttributes(dn, DirContext.REPLACE_ATTRIBUTE, attributes);
  }

  /**
   * Replace a multi-valued attribute with new values.
   *
   * @param dn DN of element to modify
   * @param attributeName Attribute to modify
   * @param newValues The new values
   * @throws javax.naming.NamingException If the attribute could not be modified
   */
  public static void modifyElementAttribute(String dn, String attributeName,
                                            String[] newValues)
      throws NamingException {
    BasicAttributes attributes = new BasicAttributes();
    BasicAttribute attribute = new BasicAttribute(attributeName);
    for (int i = 0; i < newValues.length; i++) {
      attribute.add(newValues[i]);
    }
    attributes.put(attribute);
    mCtxAdmin.modifyAttributes(dn, DirContext.REPLACE_ATTRIBUTE,
                               attributes);
  }

  /**
   * Modifies the attributes associated with a named object using
   * an ordered list of modifications.
   *
   * @param name
   *		the name of the object whose attributes will be updated
   * @param mods
   *		an ordered sequence of modifications to be performed;
   *		may not be null
   *
   * @throws	javax.naming.directory.AttributeModificationException if the modifications
   *		cannot be completed successfully
   * @throws	javax.naming.NamingException if a naming exception is encountered
   */
  public static void modifyAttributes(String name, ModificationItem[] mods)
      throws NamingException {
    mCtxAdmin.modifyAttributes(name, mods);
  }

  /**
   * Deletes an element from the directory.
   *
   * @param distinguishedName DN to delete
   * @throws javax.naming.NamingException If the element could not be deleted
   */
  public static void deleteElement(String distinguishedName)
      throws NamingException {
    mCtxAdmin.destroySubcontext(distinguishedName);
  }

  /**
   * Prepares facade for another search.  This should be called in
   * between searches to reset all state information.
   */
  public void resetSearch() {
    mControls = new SearchControls();
    mSearchBase = "";
    mResults = null;
    mCurrentResultElement = null;
    mCurrentResultAttributes = null;
  }

  /**
   * Search one level of the directory.
   *
   * @param base Base DN to search from
   * @param filter The filter to search for
   * @throws javax.naming.NamingException If an error occured
   */
  public NamingEnumeration searchOneLevel(String base, String filter)
      throws NamingException {
      mSearchBase = base;
      SearchControls mCon = new SearchControls();
    mCon.setSearchScope(SearchControls.ONELEVEL_SCOPE);
    mResults = mCtxAdmin.search(base, filter, mCon);
    return mResults;
  }

  //liangyaohan 2006-06-19
  public Vector searchAdUser(String userId,int iType)
      throws NamingException {
    Vector vUser = new Vector();
    Properties env = new Properties();
    SystemConfig sc = SystemConfig.getInstance();
    String adminName = sc.adminUID ; // "CN=baiqiang,OU=yiyang,OU=provider,OU=nms_users,DC=NMS,DC=FJ,DC=CM";
    String adminPassword = sc.adminPWD; //"Tz_345672";
    String searchBase = sc.userDNSuffix; //"OU=nms_users,DC=NMS,DC=FJ,DC=CM";
    String searchFilter = "objectClass=user";

    env.put(Context.INITIAL_CONTEXT_FACTORY, sc.factory);
    //set security credentials, note using simple cleartextauthentication
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.SECURITY_PRINCIPAL, adminName);
    env.put(Context.SECURITY_CREDENTIALS, adminPassword);
    //connect to my domain controller
    env.put(Context.PROVIDER_URL, sc.url);

    try {
      // Create the initial directory context
      LdapContext ctx = new InitialLdapContext(env, null);
      // Create the search controls
      SearchControls searchCtls = new SearchControls();
      //Specify the attributes to return
      //String returnedAtts[] = {
      //    "sn", "givenName", "mail"};
      //searchCtls.setReturningAttributes(returnedAtts);
      //Specify the search scope
      searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
      //Set the page size and initialize the cookie that we pass back in subsequent pages
      int pageSize = 500;
      byte[] cookie = null;
      //Request the paged results control
      Control[] ctls = new Control[] {new PagedResultsControl(pageSize)};
      ctx.setRequestControls(ctls);
      //initialize counter to total the results
      //int totalResults = 0;
      // Search for objects using the filter

      do {
        NamingEnumeration results = ctx.search(searchBase, searchFilter,
                                               searchCtls);
        //vResults.add(results);
        // loop through the results in each page
        while (results != null && results.hasMoreElements()) {
          SearchResult sr = (SearchResult) results.next();
          //print out the name
          UserDO user1 = createUserFromResult(sr);

          if (!user1.getDeptName().equals("") || user1.getActiveFlag() == 1)
              continue;

            String sUserId = "";
            if (iType == 1) //���û�ID��ѯ
              sUserId = user1.getUserId();
            else            //����Ʋ�ѯ
              sUserId = user1.getUserName();

            if (userId.equals(""))
            {
              vUser.add(user1);
            }
            else
            {
              if (iType == 1 || iType == 2)
              {
                if (sUserId != null && sUserId.indexOf(userId) != -1) {
                  vUser.add(user1);
                }
              }
              else  //ͬʱ���û�ID����Ʋ�ѯ
              {
                  String user_name[] = userId.split(",") ;
                  if (user1.getUserId().indexOf(user_name[0]) != -1 &&
                      user1.getUserName() != null &&
                      user1.getUserName().indexOf(user_name[1]) != -1)
                  {
                    vUser.add(user1);
                  }
              }
            }

          //System.out.println("name: " + sr.getName());
          //increment the counter
          //totalResults++;
        }

        // examine the response controls
        cookie = parseControls(ctx.getResponseControls());
        // pass the cookie back to the server for the next page
        ctx.setRequestControls(new Control[] {new
                 PagedResultsControl(pageSize, cookie,Control.CRITICAL)});

      }
      while ( (cookie != null) && (cookie.length != 0));

      ctx.close();
      //System.out.println("Total entries: " + totalResults);
    }

    catch (NamingException e) {
      System.err.println("Paged Search failed." + e);
    }
    catch (java.io.IOException e) {
      System.err.println("Paged Search failed." + e);
    }

    return vUser;


  }
  
  static byte[] parseControls(Control[] controls) throws NamingException {

    byte[] cookie = null;

    if (controls != null) {

      for (int i = 0; i < controls.length; i++) {

        if (controls[i] instanceof PagedResultsResponseControl) {

          PagedResultsResponseControl prrc =
              (PagedResultsResponseControl) controls[i];

          cookie = prrc.getCookie();

          //System.out.println(">>Next Page \n");

        }

      }

    }

    return (cookie == null) ? new byte[0] : cookie;

  }


  public static String getName(SearchResult sr,String name)
      throws NamingException {
    Attribute attribute = sr.getAttributes().get(name);
    if (attribute == null) {
      return null;
    }

    return (String) attribute.get();
  }

  public static Object getObject(SearchResult sr,String name)
      throws NamingException {
    Attribute attribute = sr.getAttributes().get(name);
    if (attribute == null) {
      return null;
    }

    return attribute.get();
  }

  public static UserDO createUserFromResult(SearchResult sr) throws
      NamingException {
    UserDO usr = new UserDO(getName(sr,"sAMAccountName"),
                            getName(sr,"cn"));
    usr.setUserId(getName(sr,"sAMAccountName"));
    usr.setNotes(getName(sr,"distinguishedName")) ;

    Object pwdObj = getObject(sr,"password");

    if (pwdObj != null) {
      if (pwdObj instanceof byte[])
        usr.setPassword(new String( (byte[]) pwdObj));
      else if (pwdObj instanceof String)
        usr.setPassword( (String) pwdObj);
    }
    else
      usr.setPassword("");

    pwdObj = getObject(sr,"telephoneNumber");
    if (pwdObj != null) {
      if (pwdObj instanceof byte[])
        usr.setMobile(new String( (byte[]) pwdObj));
      else if (pwdObj instanceof String)
        usr.setMobile( (String) pwdObj);
    }
    else
      usr.setMobile("");

    usr.setUserName(getName(sr,"displayName"));

    //liangyaohan 2006-06-22
    //�Բ��Ż����û������ж�
    //System.out.print(ldap.getResultAttribute("memberof") + " , " );
    //System.out.println(ldap.getResultAttribute("member") );
    String strDeptName = StaticMethod.null2String(getName(sr,"member"));
    usr.setDeptName(strDeptName);

    usr.setDeptId(new Integer(StaticMethod.null2String(getName(sr,
        "deptId"), "0")).intValue());
    usr.setId(new Integer(StaticMethod.null2String(getName(sr,"id"),
        "0")).intValue());
    usr.setEmail(StaticMethod.null2String(getName(sr,"email"),""));
    usr.setPhone(StaticMethod.null2String(getName(sr,"phone"),""));
    usr.setFax(StaticMethod.null2String(getName(sr,"fax"),""));
    usr.setId(new Integer(StaticMethod.null2String(getName(sr,"id"),
        "0")).intValue());
    usr.setDeleted(new Integer(StaticMethod.null2String(getName(sr,
        "deleted"), "0")).intValue());
    usr.setOrderId(new Integer(StaticMethod.null2String(getName(sr,
        "orderId"), "0")).intValue());
    usr.setAccessTimeFlag(new Integer(StaticMethod.null2String(getName(sr,"accessTimeFlag"), "0")).
                          intValue());
    usr.setAccessIpFlag(new Integer(StaticMethod.null2String(getName(sr,"accessIpFlag"), "0")).
                        intValue());
    //AccountDisabled
    int accountControl = new Integer(StaticMethod.null2String(getName(sr,"userAccountControl"), "0")).intValue();
    if (accountControl == 514)
      usr.setActiveFlag(1);
    else
      usr.setActiveFlag(0);
    //usr.setActiveFlag(new Integer(StaticMethod.null2String(ldap.
    //    getResultAttribute("AccountDisabled"), "0")).
    //                  intValue());
    usr.setUserDegree(new Integer(StaticMethod.null2String(getName(sr,"userDegree"), "0")).
                      intValue());
    usr.setAccessTimeBe(StaticMethod.null2String(getName(sr,"accessTimeBe"),""));
    usr.setAccessTimeEn(StaticMethod.null2String(getName(sr,"accessTimeEn"),""));
    usr.setAccessIp(StaticMethod.null2String(getName(sr,"accessIp"),""));

    return usr;

  }

  //liangyaohan 2006-06-21
  public NamingEnumeration searchAllUser(String base, String filter)
      throws NamingException {
    mSearchBase = base;
    SearchControls mCon = new SearchControls();
    mCon.setSearchScope(SearchControls.SUBTREE_SCOPE);
    NamingEnumeration mResults1 = null;
    mResults1 = mCtxAdmin.search(base, filter, mCon);
    vResults.add(mResults1);
    return mResults1;

  }

  /**
   * Recursively search a subtree of the directory.
   *
   * @param base Base DN to search from
   * @param filter The filter to search for
   * @throws javax.naming.NamingException If an error occured
   */
  public NamingEnumeration searchSubtree(String base, String filter)
      throws NamingException {
      mSearchBase = base;
      SearchControls mCon = new SearchControls();
    mCon.setSearchScope(SearchControls.SUBTREE_SCOPE);
    mResults = mCtxAdmin.search(base, filter, mCon);

    /*try {
      int pageSize = 10;
      byte[] cookie = null;
      //Request the paged results control
      Control[] ctls = new Control[] {
          new PagedResultsControl(pageSize, Control.CRITICAL)};

      //mCtxAdmin.setRequestControls(ctls);
      //initialize counter to total the results
      int totalResults = 0;
      // Search for objects using the filter

      do {
        NamingEnumeration results = mCtxAdmin.search(base, filter, mCon);
        // loop through the results in each page
        while (results != null && results.hasMoreElements()) {
          SearchResult sr = (SearchResult) results.next();
          //print out the name
          System.out.println("name: " + sr.getName());
          //increment the counter
          totalResults++;
        }

        // examine the response controls
        //cookie = parseControls(mCtxAdmin.getResponseControls());
        // pass the cookie back to the server for the next page
        //mCtxAdmin.setRequestControls(new Control[] {new
        //                             PagedResultsControl(pageSize, cookie,
        //    Control.CRITICAL)});
      }
      while ( (cookie != null) && (cookie.length != 0));
    }
    catch (NamingException e) {
      System.err.println("Paged Search failed." + e);
    }
    catch (java.io.IOException e) {
      System.err.println("Paged Search failed." + e);
    }
*/
    return mResults;
  }

  /**
   * Advances to the next result of the search, if there is one.
   *
   * @return <code>true</code> if there are more elements, or
   * <code>false</code> if there are no more.
   * @throws javax.naming.NamingException If an error occured
   */
  public boolean nextResult()
      throws NamingException {
    boolean hasMore;

    if (mResults == null) {
      return false;
    }

    hasMore = mResults.hasMore();
    if (hasMore) {
      mCurrentResultElement = (SearchResult) mResults.next();
      mCurrentResultAttributes = mCurrentResultElement.getAttributes();
    }

    return hasMore;
  }

  /**
   * Gets the value of a single-valued attribute for the current
   * result element.
   *
   * @param name Attribute name
   * @return The value of this attribute
   * @throws javax.naming.NamingException If an error occured
   */
  public String getResultAttribute(String name)
      throws NamingException {
    Attribute attribute = mCurrentResultAttributes.get(name);
    if (attribute == null) {
      return null;
    }

    return (String) attribute.get();
  }

  /**
   * Gets the value of a single-valued attribute for the current
   * result element.
   *
   * @param name Attribute name
   * @return The value of this attribute
   * @throws javax.naming.NamingException If an error occured
   */
  public Object getResultAttributeObject(String name)
      throws NamingException {
    Attribute attribute = mCurrentResultAttributes.get(name);
    if (attribute == null) {
      return null;
    }

    return attribute.get();
  }

  /**
   * Gets the attributes for the current result element.
   *
   * @return All attributes for the current result element.
   * @throws javax.naming.NamingException If an error occured
   */
  public NamingEnumeration getAllResultAttributes()
      throws NamingException {
    return mCurrentResultAttributes.getAll();
  }

  /**
   * Gets the name (DN) of the current result attribute.
   *
   * @return DN of the current result attribute.
   * @throws javax.naming.NamingException If an error occured
   */
  public String getResultName()
      throws NamingException {
    String relativeDn;

    relativeDn = mCurrentResultElement.getName();
    if (relativeDn.equals("")) {
      return mSearchBase;
    }
    else {
      return relativeDn + "," + mSearchBase;
    }
  }

  /**
   * Gets all values of a multi-valued attribute for the current
   * result element.  The returned set is actually a
   * <code>CaseInsensitiveStringSet</code>.  Normally this will not
   * matter, but it will become apparent if comparing the whole set
   * to, say, a <code>HashSet</code> of normal <code>String</code>
   * objects.
   *
   * @param name Attribute name
   * @return The values of this attribute
   * @throws javax.naming.NamingException If an error occured
   * @see CaseInsensitiveStringSet
   */
  public Set getAllResultAttributeValues(String name)
      throws NamingException {
    Set values;
    NamingEnumeration valueEnumeration = null;

    values = new CaseInsensitiveStringSet();
    try {
      Attribute attribute = mCurrentResultAttributes.get(name);
      if (attribute != null) {
        valueEnumeration = attribute.getAll();
        while (valueEnumeration.hasMore()) {
          String value;

          value = (String) valueEnumeration.next();
          values.add(value);
        }
      }
    }
    finally {
      if (valueEnumeration != null) {
        valueEnumeration.close();
      }
    }

    return values;
  }

  /**
   * get the bind dir context
   */
  public DirContext getDirContext() {
      return mCtxAdmin;
      //return mContext;
  }

  /**
   * Releases any resources used by this facade.  This should be
   * called on every instance to avoid resource leakage in a finally
   * block.
   */
  public void close() {
    try {
      if (mContext != null) {
        resetSearch();
        mContext.close();
        mContext = null;
        mEnvironment.clear();
      }
    }
    catch (NamingException e) {
      e.printStackTrace();
    }
  }

  /**
   * Description: get the administrative context of LDAP server
   * @return DirContext - the context of the administrator
   */
  public static DirContext getAdminCtx() {
    return mCtxAdmin;
  }

  //liangyaohan 2006-06-21
  public Vector getSearchResult()
  {
    return vResults;
  }

  //liangyaohan 2006-06-21
  //���ldap�ĵ�i����
  public boolean nextResult(int index)
      throws NamingException {
    boolean hasMore;

    if (vResults.size() == 0 || index >= vResults.size())
    {
      return false;
    }
    else
    {
      mResults = (NamingEnumeration)vResults.get(index) ;
    }

    if (mResults == null) {
      return false;
    }

    hasMore = mResults.hasMoreElements();
    if (hasMore) {
      mCurrentResultElement = (SearchResult) mResults.next();
      mCurrentResultAttributes = mCurrentResultElement.getAttributes();
    }

    return hasMore;
  }


  /*Only for self-testing*/
  public static void main(String[] args) {
    try {
      LdapOperation a = new LdapOperation();

      NamingEnumeration ne = a.searchSubtree("OU=������,OU=nms_users,DC=NMS,DC=FJ,DC=CM","ou=*");
      while (ne.hasMore()){
        System.out.println(ne.next());
      }
    }
    catch (NamingException ex) {
      ex.printStackTrace();
    }
  }

  /** The environment to use for this context. */
  private Hashtable mEnvironment = new Hashtable();
  /** The context (connection) for this facade. */
  private DirContext mContext = null;
  private static DirContext mCtxAdmin = LdapConnectionFactory.getDirContext();
  /** The attributes of the bound element. */
  private Attributes mAttributes = null;

  /** Controls to use for searches. */
  private SearchControls mControls = new SearchControls();
  /** The results of the last search. */
  private NamingEnumeration mResults = null;
  private Vector vResults = new Vector();
  /** The element of the current search result. */
  private SearchResult mCurrentResultElement = null;
  /** The attributes of the current search result. */
  private Attributes mCurrentResultAttributes = null;
  /** The base of the previous search. */
  private String mSearchBase = null;
  
  public Vector searchAdDept(String userId, int iType) throws NamingException {
		Vector vUser = new Vector();
		Properties env = new Properties();
		SystemConfig sc = SystemConfig.getInstance();
		String adminName = sc.adminUID; // "CN=baiqiang,OU=yiyang,OU=provider,OU=nms_users,DC=NMS,DC=FJ,DC=CM";
		String adminPassword = sc.adminPWD; //"Tz_345672";
		String searchBase = "OU=nms_users,DC=NMS,DC=FJ,DC=CM";
		String searchFilter = "objectClass=user";

		env.put(Context.INITIAL_CONTEXT_FACTORY, sc.factory);
		//set security credentials, note using simple cleartextauthentication
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, adminName);
		env.put(Context.SECURITY_CREDENTIALS, adminPassword);
		//connect to my domain controller
		env.put(Context.PROVIDER_URL, sc.url);

		try {
			// Create the initial directory context
			LdapContext ctx = new InitialLdapContext(env, null);
			// Create the search controls
			SearchControls searchCtls = new SearchControls();
			//Specify the attributes to return
			//String returnedAtts[] = {
			//    "sn", "givenName", "mail"};
			//searchCtls.setReturningAttributes(returnedAtts);
			//Specify the search scope
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			//Set the page size and initialize the cookie that we pass back in subsequent pages
			int pageSize = 500;
			byte[] cookie = null;
			//Request the paged results control
			Control[] ctls = new Control[] { new PagedResultsControl(pageSize) };
			ctx.setRequestControls(ctls);
			//initialize counter to total the results
			//int totalResults = 0;
			// Search for objects using the filter

			do {
				NamingEnumeration results = ctx.search(searchBase,
						searchFilter, searchCtls);
				//vResults.add(results);
				// loop through the results in each page
				while (results != null && results.hasMoreElements()) {
					SearchResult sr = (SearchResult) results.next();
					//print out the name
					UserDO user1 = createUserFromResult(sr);

					if (!user1.getDeptName().equals("")
							|| user1.getActiveFlag() == 1)
						continue;

					String sUserId = "";
					if (iType == 1) //���û�ID��ѯ
						sUserId = user1.getUserId();
					else
						//����Ʋ�ѯ
						sUserId = user1.getUserName();

					if (userId.equals("")) {
						vUser.add(user1);
					} else {
						if (iType == 1 || iType == 2) {
							if (sUserId != null
									&& sUserId.indexOf(userId) != -1) {
								vUser.add(user1);
							}
						} else //ͬʱ���û�ID����Ʋ�ѯ
						{
							String user_name[] = userId.split(",");
							if (user1.getUserId().indexOf(user_name[0]) != -1
									&& user1.getUserName() != null
									&& user1.getUserName()
											.indexOf(user_name[1]) != -1) {
								vUser.add(user1);
							}
						}
					}

					//System.out.println("name: " + sr.getName());
					//increment the counter
					//totalResults++;
				}

				// examine the response controls
				cookie = parseControls(ctx.getResponseControls());
				// pass the cookie back to the server for the next page
				ctx.setRequestControls(new Control[] { new PagedResultsControl(
						pageSize, cookie, Control.CRITICAL) });

			} while ((cookie != null) && (cookie.length != 0));

			ctx.close();
			//System.out.println("Total entries: " + totalResults);
		}

		catch (NamingException e) {
			System.err.println("Paged Search failed." + e);
		} catch (java.io.IOException e) {
			System.err.println("Paged Search failed." + e);
		}

		return vUser;

	}
  
}
