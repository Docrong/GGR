package com.boco.eoms.base.util;

import java.util.Hashtable;

/**
 * <p>Title: nmmis</p>
 * <p>Description: table and applet</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: boco</p>
 * @author dudajiang
 * @version 1.0
 */


public class StaticObject {

  private Hashtable objs = new Hashtable();
  private static StaticObject instance = new StaticObject();

  /** private constructor */
  private StaticObject() {
  }

  public static StaticObject getInstance() {
    return instance;
  }


  public Object getObject(String key) {
    return objs.get(key);
  }

  public void putObject(String key, Object obj) {
    objs.put(key, obj);
  }

  public void removeObject(String key) {
      objs.remove(key) ;
  }

  public Object getRecordFromObj (String HashKey, String RecKey) {
    Hashtable tmp = (Hashtable)getObject(HashKey);
    if (tmp == null) {
      tmp = new Hashtable();
      putObject(HashKey, tmp);
    }
    return tmp.get(RecKey);
  }

  public void putRecordToObj (String HashKey, String RecKey, Object obj) {
    Hashtable tmp = (Hashtable)getObject(HashKey);
    if (tmp == null) {
      tmp = new Hashtable();
    }
    tmp.put(RecKey, obj);
    putObject(HashKey, tmp);
  }

  public void removeRecordFromObj (String HashKey, String RecKey) {
    Hashtable tmp = (Hashtable)getObject(HashKey);
    if (tmp == null) {
      tmp = new Hashtable();
      putObject(HashKey, tmp);
    }
    else {
      tmp.remove(RecKey);
      putObject(HashKey, tmp);
    }
  }

  public void clearRecord()  {
    objs.clear();
  }
}
