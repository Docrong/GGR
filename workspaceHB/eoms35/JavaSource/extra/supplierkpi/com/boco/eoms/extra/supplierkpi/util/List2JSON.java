package com.boco.eoms.extra.supplierkpi.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.extra.supplierkpi.model.KPIShowObject;

public class List2JSON {

    public static String transform(List list) {
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject check = new JSONObject();
        for (int i = 0; i < list.size(); i++) {
            KPIShowObject obj = (KPIShowObject) list.get(i);
            check = new JSONObject();
            check.put("kpiId", obj.getKpiId());
            check.put("kpiName", obj.getKpiName());
            check.put("check", obj.getSelected());
            check.put("serviceType", obj.getServiceType());
            check.put("specialType", obj.getSpecialType());
            jsonArray.put(check);
        }
        jsonObj.put("KPIObjects", jsonArray);
        jsonObj.put("size", list.size());
        return jsonObj.toString();
    }
}
