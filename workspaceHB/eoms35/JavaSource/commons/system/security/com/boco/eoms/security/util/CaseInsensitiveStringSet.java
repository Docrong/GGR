/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis              2003-08-15       created
 */

package com.boco.eoms.security.util;

import java.util.HashSet;
import java.util.Iterator;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author weis
 * @version 1.0
 */

/**
 * A hash set containing strings, ignoring the case, but preserving
 * the case, of strings as they are added.  Thus the following code is
 * valid:
 *
 * <pre>
 *     Set set = new CaseInsensitiveSet();
 *     set.add("NewSet");
 *     set.add("newSet") == false;
 *
 *     set.contains("NewSet") == true;
 *     set.contains("newset") == true;
 *     set.contains("NEWSET") == true;
 * </pre>
 */
public class CaseInsensitiveStringSet extends HashSet {
    /**
     * Adds a string to this set.
     *
     * @param object Object to add.
     * @return False if this object has already been added.
     */
    public boolean add(Object object) {
        String string = (String) object;
        return super.add(new CaseInsensitiveString(string));
    }

    /**
     * Returns true if set contains the string, ignoring the case of
     * the target string.
     *
     * @param object String to check for
     * @return True if the set contais the string
     */
    public boolean contains(Object object) {
        String string = (String) object;
        return super.contains(new CaseInsensitiveString(string));
    }

    /**
     * Removes a string from the set, ignore the case.
     *
     * @param object String to remove
     * @return True if the remove succeeded, otherwise false.
     */
    public boolean remove(Object object) {
        String string = (String) object;
        return super.remove(new CaseInsensitiveString(string));
    }

    /**
     * Returns an iterator over the strings in this set.  Each string
     * preserves the case as when they were inserted.
     *
     * @return An iterator for this set.
     */
    public Iterator iterator() {
        return new CaseInsensitiveStringIterator(super.iterator());
    }
}
