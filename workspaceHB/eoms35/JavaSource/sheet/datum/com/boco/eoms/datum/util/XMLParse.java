package com.boco.eoms.datum.util;
/**
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 *
 * @author Administrator
 */

import java.util.Hashtable;
import java.util.Map;

import com.boco.eoms.common.util.PropertyFile;
import com.boco.eoms.common.util.StaticObject;

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

    private void init() {
        PropertyFile prop = PropertyFile.getInstance();

        String name = "OKB.config";
        String[] tt = prop.getChildrenProperties(name);
        for (int i = 0; i < tt.length; i++) {
            String typevalue = tt[i];
            String key1 = name + ".fromOkbToSys." + typevalue;
            String key2 = name + ".fromSysToOkb." + typevalue;
            String nowname = name + "." + tt[i];
            String[] yy = prop.getChildrenProperties(nowname);
            Hashtable map1 = new Hashtable();
            Hashtable map2 = new Hashtable();
            for (int j = 0; j < yy.length; j++) {
                String okbname = prop.getAttribute(nowname, j, "okbname");
                String sysname = prop.getAttribute(nowname, j, "sysname");
                map1.put(okbname, sysname);
                map2.put(sysname, okbname);
            }
            obj.putObject(key1, map1);
            obj.putObject(key2, map2);
        }
    }

    public String getOkbIdFromSysId(String type, String key) {
        String ret = "";
        ret = obj.getRecordFromObj("OKB.config.fromSysToOkb." + type, key).toString();
        return ret;
    }

    public Map getSysIdMap(String type) {
        Map ret = (Map) obj.getObject("OKB.config.fromSysToOkb." + type);
        return ret;
    }

    public Map getOkbIdMap(String type) {
        Map ret = (Map) obj.getObject("OKB.config.fromOkbToSys." + type);
        return ret;
    }

    public String getSysIdFromOkbId(String type, String key) {
        String ret = "";
        ret = obj.getRecordFromObj("OKB.config.fromOkbToSys." + type, key).toString();
        return ret;
    }

}
