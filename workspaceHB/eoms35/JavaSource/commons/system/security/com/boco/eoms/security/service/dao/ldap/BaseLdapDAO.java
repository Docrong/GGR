/**
 * Copyright (c) 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis               2003-08-18                  created
 */

package com.boco.eoms.security.service.dao.ldap;

import javax.naming.NamingException;

import com.boco.eoms.security.service.dao.BaseDAO;
import com.boco.eoms.security.service.dao.ldap.factory.LdapOperation;


/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author weis
 * @version 1.0
 */
public abstract class BaseLdapDAO implements BaseDAO {

    /** constructor */
    public BaseLdapDAO() {}


    /**
     * @return initialized LdapOperation object
     * @throws javax.naming.NamingException
     */
    public LdapOperation getLdap() throws NamingException {
        LdapOperation ldap = new LdapOperation();
        return ldap;
    }

    /**
     * Closes an LDAP facade if it is not null.  This is handy in
     * finally blocks.
     *
     * @param ldap LDAP facade to close.
     */
    public void closeLdap(LdapOperation ldap) {

        if (ldap != null) {
            ldap.close();
        }
    }
}