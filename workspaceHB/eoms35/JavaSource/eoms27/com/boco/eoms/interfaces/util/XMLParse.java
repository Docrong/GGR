package com.boco.eoms.interfaces.util;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import java.util.Hashtable;
import com.boco.eoms.common.util.PropertyFile;
import com.boco.eoms.common.util.StaticObject;
import com.boco.eoms.common.util.StaticMethod;

public class XMLParse {

  private StaticObject obj = StaticObject.getInstance();
  private PropertyFile prop = PropertyFile.getInstance();

  private static XMLParse xmlParse = null;

  public static XMLParse getInstance() {
    if (xmlParse == null) {
      xmlParse = new XMLParse();
    }
    return xmlParse;
  }

  private XMLParse() {
    init();
  }

  private void init(){
    PropertyFile prop = PropertyFile.getInstance();

    String name = "Interface.map";
    String[] tt = prop.getChildrenProperties(name);
    for (int i = 0; i < tt.length; i++) {
      String typevalue = tt[i].substring(5);
      String key1 = name+".fromInterfaceToEoms."+typevalue;
      String key2 = name+".fromEomsToInterface."+typevalue;
      String nowname = name+"."+tt[i];
      String[] yy = prop.getChildrenProperties(nowname);
      Hashtable map1 = new Hashtable();
      Hashtable map2 = new Hashtable();
      for (int j=0;j<yy.length;j++) {
        String interface_id = prop.getAttribute(nowname, j, "interface_id");
        String eoms_id = prop.getAttribute(nowname, j, "eoms_id");
        map1.put(interface_id,eoms_id);
        map2.put(eoms_id,interface_id);
      }
      obj.putObject(key1,map1);
      obj.putObject(key2,map2);
    }

  }

  public String getEomsIdFromInterfaceId(String type, String key) {
    String ret="";
    try{
      ret = obj.getRecordFromObj("Interface.map.fromInterfaceToEoms." + type,
                                 key).toString();
      System.out.println("ret="+ret);
    }catch(Exception e){
      ret = "4008";
    }
    return ret;
  }
  public String getInterfaceIdFromEomsId(String type, String key) {
    return StaticMethod.nullObject2String(obj.getRecordFromObj(
        "Interface.map.fromEomsToInterface." + type, key));
  }

}
