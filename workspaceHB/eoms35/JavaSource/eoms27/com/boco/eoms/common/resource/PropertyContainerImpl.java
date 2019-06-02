/**
 *  The contents of this file are subject to the Mozilla Public
 *  License Version 1.1 (the "License"); you may not use this file
 *  except in compliance with the License. You may obtain a copy of
 *  the License at http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an "AS
 *  IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 *  implied. See the License for the specific language governing
 *  rights and limitations under the License.
 *
 *  The Original Code is nmstoolkit library.
 *
 *  The Initial Owner of the Original Code is
 *  Paul Monday
 *
 *  Portions created by Power Of Two S.R.L. are
 *  Copyright (C) Power Of Two S.R.L.
 *  All Rights Reserved.
 *
 * Contributor(s):
 */

package com.boco.eoms.common.resource;

import org.apache.log4j.Category;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Properties;


/**
 *  Description of the Class
 *
 * @author     Paul Monday
 **    23 aprile 2002
 */
public abstract class PropertyContainerImpl
        implements PropertyContainer, Serializable {
    /** Log4J category. */
    protected Category cat = Category.getInstance(this.getClass());

    /**  properties table */
    protected Properties properties = new Properties();


    /**  Constructor for the PropertyContainerImpl object */
    public PropertyContainerImpl() {
    }


    /**
     * Add a property associated with a token name.
     * <br>
     * If the token already exists, the value will be replaced.
     * If the token does not exist, it will be added with the value.
     *
     * @param  value  is an object that cannot be null
     * @param  token  is a key that can be used to retrieve the value
     */
    public void addProperty(Object value, String token) {
        if (value == null || token == null)
            return;

        if (properties.containsKey(token))
            properties.remove(token);

        properties.put(token, value);
    }


    /**
     *  Retrieve a value by a particular token.
     *
     * @param  token  is a key that can be used to retrieve the value
     * @return        String is the value associated with the token.  It
     *                will not be null
     */
    public String getProperty(String token) {
        return (token != null) ? properties.getProperty(token) : null;
    }


    /**
     *  Retrieve a value by a particular token.
     *
     * @param  token  is a key that can be used to retrieve the value
     * @return        Object is the value associated with the token.  It
     *                will not be null
     */
    public Object getObject(String token) {
        return (token != null) ? properties.get(token) : null;
    }


    /**
     * Retrieve all property keys currently in use.
     *
     * @return    String[] is an array of all valid token names
     */
    public String[] getPropertyKeys() {
        String keys[] = null;

        synchronized (properties) {
            int s = properties.size();
            keys = new String[s];
            Enumeration e = properties.keys();
            int i = 0;

            while (e.hasMoreElements()) {
                keys[i] = (String) e.nextElement();
                i++;
            }
        }

        return keys;
    }


    /**
     *  Remove a value associated with a particular token.
     *
     * @param  token  is a key associated with a value that was added
     */
    public void removeProperty(String token) {
        if (token == null)
            return;

        properties.remove(token);
    }
}
