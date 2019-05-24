//---------------------------------------------------------
// Application: E_OMS
// Author     : Jerry
// File       : DAOCacheManager.java
//
// Copyright 2003 boco
// Generated at Tue Mar 25 17:18:02 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.common.dao;

import java.util.*;
import java.text.*;
import org.apache.commons.logging.*;
import com.boco.eoms.common.util.*;

public class DAOCacheManager {
  private static Log log = LogFactory.getFactory().getInstance("DAOCacheManager");
  private static CacheManager cacheManager = new CacheManager(CacheManager.MIX);

  public static Object getCache(String identifier) {
    if (log.isInfoEnabled()) {
      Object[] o = {new Long(cacheManager.getHitCount()), new Long(cacheManager.getMissCount())};
      log.info(MessageFormat.format(" [DAOCacheManager] getCache: {0} hits, {1} misses", o));
    }
    return cacheManager.getCache(identifier);
  }

  public static void putCache(Object object, String id, int minutesToLive) {
    if (log.isInfoEnabled()) {
      log.info(" [DAOCacheManager] putCache");
    }
    cacheManager.putCache(object, id, minutesToLive);
  }

  public static void invalidate(String id) {
    cacheManager.invalidate(id);
  }

}