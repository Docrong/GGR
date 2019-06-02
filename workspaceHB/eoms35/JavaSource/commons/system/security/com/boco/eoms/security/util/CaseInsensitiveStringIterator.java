/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   weis              2003-08-15       created
 */

package com.boco.eoms.security.util;

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
 * A wrapper around an iterator of <code>CaseInsensitiveString</code>
 * objects that converts the objects back to normal
 * <code>String</code> objects as the items are retrieved.  This
 * iterator will not work unless every item is a
 * <code>CaseInsensitiveString</code>
 *
 * @see com.boco.common.security.lang.CaseInsensitiveString
 * @see String
 */
public class CaseInsensitiveStringIterator implements Iterator {
    /**
     * Creates a new iterator from an existing iterator.
     *
     * @param iterator Iterator to wrap.
     */
    public CaseInsensitiveStringIterator(Iterator iterator) {
        mIterator = iterator;
    }

    /**
     * Checks if there are more items in this iterator.
     *
     * @return True if there are more items.
     */
    public boolean hasNext() {
        return mIterator.hasNext();
    }

    /**
     * Returns the current item, as a <code>String</code>.  The item
     * is really a <code>CaseInsensitiveString</code> object, but gets
     * converted automatically.
     *
     * @return The current item, as a <code>String</code>.
     */
    public Object next() {
        CaseInsensitiveString ciString;
        ciString = (CaseInsensitiveString) mIterator.next();
        return ciString.toString();
    }

    /**
     * Removes the current item.
     */
    public void remove() {
        mIterator.remove();
    }

    /** The wrapped iterator. */
    private Iterator mIterator;
}
