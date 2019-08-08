package com.boco.eoms.sheet.commonfault.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;

/**
 * 工具类
 *
 * @author weichao
 * add by weichao 20150518
 */
public class GeneralUtil {

    /**
     * 将带逗号的字符串 转换成 List集合
     *
     * @param ids
     * @return
     */
    public static List convertString2List(String ids) {
        List list = new ArrayList();
        if (ids.contains(",")) {
            String[] idds = ids.split(",");
            for (int i = 0; i < idds.length; i++) {
                list.add(idds[i]);
            }
        } else {
            list.add(ids);
        }
        return list;
    }


    /**
     * 弹出前台的提示信息
     *
     * @param response
     * @param text     提示内容
     * @param type     0-成功  2-异常
     * @throws Exception
     */
    public static void sendAlert(HttpServletResponse response, String text, String type) throws Exception {
        if ("0".equals(type)) {
            JSONArray data = new JSONArray();
            JSONObject jsonRoot = new JSONObject();
            jsonRoot.put("data", data);
            jsonRoot.put("status", "0");
            JSONUtil.print(response, jsonRoot.toString());
        } else if ("2".equals(type)) {
            JSONArray data = new JSONArray();
            JSONObject o = new JSONObject();
            o.put("text", text);
            data.put(o);
            JSONObject jsonRoot = new JSONObject();
            jsonRoot.put("data", data);
            jsonRoot.put("status", type);
            JSONUtil.print(response, jsonRoot.toString());
        }

    }

    public static Integer getLevel(String mainFaultResponseLevel) {
        String key = "batchDeal.level.t" + mainFaultResponseLevel;
        return StaticMethod.nullObject2Integer(XmlManage.getFile("/config/commonfault-util.xml").getProperty(key));
    }

}
