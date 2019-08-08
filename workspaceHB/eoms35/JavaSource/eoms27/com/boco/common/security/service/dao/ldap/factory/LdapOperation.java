/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis            2003-08-15         created
 * V1.0.0.0   Wang Zhuo Wei   2003-08-18         amend
 */

package com.boco.common.security.service.dao.ldap.factory;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

import com.boco.common.security.config.SystemConfig;
import com.boco.common.security.util.CaseInsensitiveStringSet;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 *
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
            this.searchSubtree(SystemConfig.getInstance().userDNSuffix, "uid=test");
        } catch (Exception ex) {
            conFlag = false;
        }

        if (!conFlag) {
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
        } catch (NamingException ex) {
            System.out.println("Not login because of wrong user name or password!!!");
            throw ex;
        }
        //mCtxAdmin = LdapConnectionFactory.getDirContext();
    }

    /**
     * Description: authenticate the user information
     *
     * @param dn       - distinguish name
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
        } finally {
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
     * @param attributes        Attributes of the new element
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
     * @param dn         DN to add
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
            } else if (value instanceof Set) {
                Set values = (Set) value;
                BasicAttribute attribute = new BasicAttribute(name);
                Iterator i = values.iterator();
                while (i.hasNext()) {
                    attribute.add(i.next());
                }
                jndiAttributes.put(attribute);
            } else {
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
     * @param dn            DN of element to modify
     * @param attributeName Attribute to modify
     * @param newValue      The new value.
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
     * @param dn            DN of element to modify
     * @param attributeName Attribute to modify
     * @param newValues     The new values
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
     * @param dn            DN of element to modify
     * @param attributeName Attribute to modify
     * @param newValues     The new values
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
     * @param name the name of the object whose attributes will be updated
     * @param mods an ordered sequence of modifications to be performed;
     *             may not be null
     * @throws javax.naming.directory.AttributeModificationException if the modifications
     *                                                               cannot be completed successfully
     * @throws javax.naming.NamingException                          if a naming exception is encountered
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
     * @param base   Base DN to search from
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

    /**
     * Recursively search a subtree of the directory.
     *
     * @param base   Base DN to search from
     * @param filter The filter to search for
     * @throws javax.naming.NamingException If an error occured
     */
    public NamingEnumeration searchSubtree(String base, String filter)
            throws NamingException {
        mSearchBase = base;
        SearchControls mCon = new SearchControls();
        mCon.setSearchScope(SearchControls.SUBTREE_SCOPE);
        mResults = mCtxAdmin.search(base, filter, mCon);

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
        } else {
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
        } finally {
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
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Description: get the administrative context of LDAP server
     *
     * @return DirContext - the context of the administrator
     */
    public static DirContext getAdminCtx() {
        return mCtxAdmin;
    }

    /*Only for self-testing*/
    public static void main(String[] args) {
        try {
            LdapOperation a = new LdapOperation();
            NamingEnumeration ne = a.searchSubtree("ou=groups, dc=boco,dc=com", "ou=*");
            while (ne.hasMore()) {
                System.out.println(ne.next());
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The environment to use for this context.
     */
    private Hashtable mEnvironment = new Hashtable();
    /**
     * The context (connection) for this facade.
     */
    private DirContext mContext = null;
    private static DirContext mCtxAdmin = LdapConnectionFactory.getDirContext();
    /**
     * The attributes of the bound element.
     */
    private Attributes mAttributes = null;

    /**
     * Controls to use for searches.
     */
    private SearchControls mControls = new SearchControls();
    /**
     * The results of the last search.
     */
    private NamingEnumeration mResults = null;
    /**
     * The element of the current search result.
     */
    private SearchResult mCurrentResultElement = null;
    /**
     * The attributes of the current search result.
     */
    private Attributes mCurrentResultAttributes = null;
    /**
     * The base of the previous search.
     */
    private String mSearchBase = null;
}