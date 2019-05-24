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
                          

/**
 *  PropertyContainer Interface
 *
 * @author     Paul Monday
 * @created    23 aprile 2002
 */
public interface PropertyContainer {
    /**
     *  Add a property associated with a token name.
     *  <br>
     *  If the token already exists, the value will be replaced.
     *  If the token does not exist, it will be added with the value.
     *
     * @param  value  is an object that cannot be null
     * @param  token  is a key that can be used to retrieve the value
     */
    public void addProperty(Object value, String token);


    /**
     *  Retrieve a value by a particular token.
     *
     * @param  token  is a key that can be used to retrieve the value
     * @return        String is the value associated with the token.  It
     *                will not be null
     */
    public String getProperty(String token);


    /**
     *  Retrieve a value by a particular token.
     *
     * @param  token  is a key that can be used to retrieve the value
     * @return        Object is the value associated with the token.  It
     *                will not be null
     */
    public Object getObject(String token);


    /**
     *  Retrieve all property keys currently in use.
     *
     * @return    String[] is an array of all valid token names
     */
    public String[] getPropertyKeys();


    /**
     *  Remove a value associated with a particular token.
     *
     * @param  token  is a key associated with a value that was added
     */
    public void removeProperty(String token);
}
