package com.boco.eoms.commons.cache.event;

import org.apache.log4j.Logger;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 4, 2007 10:10:36 AM
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public class EHCacheEventListener implements CacheEventListener {


    Logger logger = Logger.getLogger(this.getClass());

    /**
     * Give the replicator a chance to cleanup and free resources when no longer
     * needed
     */
    public void dispose() {
        logger.debug("dispose");
    }

    /**
     * Called immediately after an element is <i>found</i> to be expired. The
     * {@link net.sf.ehcache.Cache#remove(Object)} method will block until this
     * method returns. <p/> As the {@link Element} has been expired, only what
     * was the key of the element is known. <p/> Elements are checked for expiry
     * in ehcache at the following times:
     * <ul>
     * <li>When a get request is made
     * <li>When an element is spooled to the diskStore in accordance with a
     * MemoryStore eviction policy
     * <li>In the DiskStore when the expiry thread runs, which by default is
     * {@link net.sf.ehcache.Cache#DEFAULT_EXPIRY_THREAD_INTERVAL_SECONDS}
     * </ul>
     * If an element is found to be expired, it is deleted and this method is
     * notified.
     *
     * @param cache   the cache emitting the notification
     * @param element the element that has just expired <p/> Deadlock Warning:
     *                expiry will often come from the <code>DiskStore</code>
     *                expiry thread. It holds a lock to the DiskStorea the time the
     *                notification is sent. If the implementation of this method
     *                calls into a synchronized <code>Cache</code> method and that
     *                subsequently calls into DiskStore a deadlock will result.
     *                Accordingly implementers of this method should not call back
     *                into Cache.
     */
    public void notifyElementExpired(Ehcache arg0, Element arg1) {
        logger.debug("notifyElementExpired");

    }

    /**
     * Called immediately after an element has been put into the cache. The
     * {@link net.sf.ehcache.Cache#put(net.sf.ehcache.Element)} method will
     * block until this method returns. <p/> Implementers may wish to have
     * access to the Element's fields, including value, so the element is
     * provided. Implementers should be careful not to modify the element. The
     * effect of any modifications is undefined.
     *
     * @param cache   the cache emitting the notification
     * @param element the element which was just put into the cache.
     */
    public void notifyElementPut(Ehcache arg0, Element arg1)
            throws CacheException {
        logger.debug("notifyElementPut");

    }

    /**
     * Called immediately after an element has been removed. The remove method
     * will block until this method returns. <p/> Ehcache does not chech for
     * <p/> As the {@link net.sf.ehcache.Element} has been removed, only what
     * was the key of the element is known. <p/>
     *
     * @param cache   the cache emitting the notification
     * @param element just deleted
     */
    public void notifyElementRemoved(Ehcache arg0, Element arg1)
            throws CacheException {
        logger.debug("notifyElementRemoved");

    }

    /**
     * Called immediately after an element has been put into the cache and the
     * element already existed in the cache. This is thus an update. <p/> The
     * {@link net.sf.ehcache.Cache#put(net.sf.ehcache.Element)} method will
     * block until this method returns. <p/> Implementers may wish to have
     * access to the Element's fields, including value, so the element is
     * provided. Implementers should be careful not to modify the element. The
     * effect of any modifications is undefined.
     *
     * @param cache   the cache emitting the notification
     * @param element the element which was just put into the cache.
     */

    public void notifyElementUpdated(Ehcache arg0, Element arg1)
            throws CacheException {
        logger.debug("notifyElementUpdated");

    }

    /**
     * Creates a clone of this listener. This method will only be called by
     * ehcache before a cache is initialized. <p/> This may not be possible for
     * listeners after they have been initialized. Implementations should throw
     * CloneNotSupportedException if they do not support clone.
     *
     * @return a clone
     * @throws CloneNotSupportedException if the listener could not be cloned.
     */


    public Object clone() throws CloneNotSupportedException {
        logger.debug("clone");
        // TODO Auto-generated method stub
        return super.clone();
    }

}
