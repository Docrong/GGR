/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 * V1.0.0.0     weis            2003-08-20                  modify
 */

package com.boco.common.security.service.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of User Data Object </p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 *
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class UserDO
        implements Principal, Serializable, Comparable {

    private String name;
    private String userID;
    private String fullName;
    private String password;
    private String legalHost;
    private String logonTime;
    private String teleNumber;
    private String mobNumber;
    private String email;
    private String postalCode;
    private String address;
    private String comment;
    private Date registerTime;

    //user properties
    private HashMap m_props;

    public UserDO() {
        m_props = new HashMap();
    }

    public UserDO(String name, String fullName) {
        this.name = name;
        this.fullName = fullName;
        this.userID = name;
        m_props = new HashMap();
    }

    public UserDO(String name) {
        this.name = name;
        this.userID = name;
        this.fullName = "";
        m_props = new HashMap();
    }

    /**
     * Return the unique String identifier of this user.
     *
     * @return the unique String identifier of this user.
     */
    public String getUserID() {
        //return userID;
        return getName();
    }

    /**
     * Set the user"s identifier.
     * @param userID
     */
  /*    public void setUserID(String userID) {
          this.userID = name;
      }*/

    /**
     * Return the full user username.
     *
     * @return Return the username.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Set the user's full name.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Set the user's full name.
     *
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Return the user's password.
     *
     * @return Return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user's password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public int hashCode() {
        return this.userID.hashCode();
    }

    public boolean equals(Object obj) {
        UserDO rdo = (UserDO) obj;
        if (rdo.hashCode() == this.hashCode()) {
            return true;
        } else {
            return false;
        }
    }

    public Map getProperties() {
        return m_props;
    }

    /**
     * Retrieve the requested user property.
     *
     * @param key Property's name.
     * @return Return the property's value of the specified key, or null if the
     * property does not exist.
     */
    public Object getProperty(String key) {
        return m_props.get(key);
    }

    /**
     * Remove the specified property from the properties list.
     *
     * @param key Property's name.
     */
    public void removeProperty(String key) {
        m_props.remove(key);
    }

    /**
     * Add (or update if not already in the property list) a property key-value
     * pair in the user's properties list.
     *
     * @param key   Property's name.
     * @param value Property's value.
     */
    public void setProperty(String key, Object value) {
        m_props.put(key, value);
    }

    public String toString() {
        return "User ID: " + this.name + "\n" +
                "Full Name: " + this.fullName + "\n" +
                "Description: " + this.comment + "\n" +
                "Legal Host: " + getProperty("SecurityHost") + "\n" +
                "Register Time: " + getProperty("ExpiredTime") + "\n" +
                "Logon Time: " + getProperty("LogonTime") + "\n" +
                "E-Mail: " + getProperty("mail") + "\n" +
                "Mobile Number: " + getProperty("mobile") + "\n" +
                "Tel Number: " + getProperty("telephoneNumber") + "\n" +
                "Postal Code: " + getProperty("PostalCOde") + "\n" +
                "Address: " + getProperty("homePostalAddress");

    }

    public int compareTo(Object o) {
        UserDO u = (UserDO) o;
        return this.getName().compareTo(u.getName());
    }
}
