package com.boco.eoms.commons.sheet.expert.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.sheet.expert.model.TawSheetExpert;
import com.boco.eoms.commons.sheet.expert.service.ITawSheetExpertManager;
import com.boco.eoms.commons.sheet.special.model.TawSheetSpecial;
import com.boco.eoms.commons.sheet.special.service.ITawSheetSpecialManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.bo.ITawSystemUserBo;
import com.boco.eoms.commons.ui.util.UIConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StaticMethod {
    /**
     * 专家树图
     *
     * @param speid
     * @param chkType
     * @return
     */
    public static JSONArray getExpertTree(String speid, String chkType) {
        JSONArray json = new JSONArray();
        ITawSheetSpecialManager mgr = (ITawSheetSpecialManager) ApplicationContextHolder
                .getInstance().getBean("ItawSheetSpecialManager");
        ArrayList list = new ArrayList();
        try {
            list = (ArrayList) mgr.getSonspecialByspecialId(speid);
            for (Iterator iter = list.iterator(); iter.hasNext(); ) {
                TawSheetSpecial special = (TawSheetSpecial) iter.next();
                JSONObject jitem = new JSONObject();
                jitem.put("id", special.getSpeid());
                jitem.put("text", special.getSpecialname());

                jitem.put(UIConstants.JSON_NODETYPE, "special");
                jitem.put("iconCls", "special");
                jitem.put("allowChild", true);
                jitem.put("allowDelete", true);
                if ("special".equalsIgnoreCase(chkType)) {
                    jitem.put("checked", false);
                }
                json.put(jitem);
            }
            ITawSheetExpertManager mgr1 = (ITawSheetExpertManager) ApplicationContextHolder
                    .getInstance().getBean("ItawSheetExpertManager");
            List userlist = mgr1.getExpertsBySpecialId(speid);
            TawSheetExpert tawExpert = new TawSheetExpert();
            ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
                    .getInstance().getBean("iTawSystemUserBo");
            for (int n = 0; n < userlist.size(); n++) {

                tawExpert = (TawSheetExpert) userlist.get(n);
                TawSystemUser sysuser = sysuserbo.getUserByuserid(tawExpert.getExpertName());
                JSONObject jitem = new JSONObject();
                jitem.put("id", tawExpert.getId());
                jitem.put("text", sysuser.getUsername());

                jitem.put(UIConstants.JSON_NODETYPE, "user");
                jitem.put("iconCls", "user");
                jitem.put("leaf", 1);
                jitem.put("allowChild", false);
                jitem.put("allowDelete", true);
                jitem.put("allowEdit", true);

                String expertStatus = tawExpert.getExpertState();
                if (expertStatus == null || expertStatus.equals("2")) {
                    expertStatus = "在线";
                } else if (expertStatus.equals("1")) {
                    expertStatus = "忙碌";
                } else {
                    expertStatus = "离线";
                }
                String post = tawExpert.getPostName();
                if (post == null || post.equals("1")) {
                    post = "技术支援岗";
                } else if (post.equals("2")) {
                    post = "技术支援专家";
                } else {
                    post = "技术支援调度员";
                }
                String etype = tawExpert.getExpertType();
                if (etype == null || etype.equals("0")) {
                    etype = "虚拟";
                } else if (etype.equals("1")) {
                    etype = "兼职";

                } else {
                    etype = "专职";
                }
                jitem.put("qtip", "所在部门:" + sysuser.getDeptname()
                        + "<br \\/>所在岗位:" + post
                        + "<br \\/>专家类型:" + etype
                        + "<br \\/>专家状态:" + expertStatus
                        + "<br \\/>状态开始时间:" + tawExpert.getStateStartTime()
                        + "<br \\/>状态开始时间:" + tawExpert.getStateEndTime());
                jitem.put("qtipTitle", sysuser.getUsername());
                jitem.put("leaf", true);
                json.put(jitem);

            }
        } catch (Exception ex) {
            BocoLog.error("", "生成专业树图时报错：" + ex);
        }
        return json;
    }
}
