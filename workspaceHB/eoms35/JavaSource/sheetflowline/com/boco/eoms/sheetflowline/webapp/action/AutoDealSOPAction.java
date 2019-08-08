// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AutoDealSOPAction.java

package com.boco.eoms.sheetflowline.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultAutoManager;
import com.boco.eoms.sheetflowline.mgr.IAutoDealSOPMgr;
import com.boco.eoms.sheetflowline.model.AutoDealSOP;
import com.boco.eoms.sheetflowline.vo.AutoDealSOPVO;
import com.ibm.icu.text.SimpleDateFormat;

import java.util.*;
import javax.servlet.http.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.*;
import org.displaytag.util.ParamEncoder;
import org.jdom.Element;

public class AutoDealSOPAction extends BaseAction {

    public AutoDealSOPAction() {
    }

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String mainAlarmIds = "";
        String where = "";
        boolean isAlarmid = true;
        Map condition = new HashMap();
        List batchmains = (List) request.getAttribute("mains");
        ICommonFaultAutoManager autoservice = (ICommonFaultAutoManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
        if (batchmains != null) {
            for (int i = 0; i < batchmains.size(); i++) {
                CommonFaultMain commonfaultMain = (CommonFaultMain) batchmains.get(i);
                if (mainAlarmIds.equals(""))
                    mainAlarmIds = "'" + commonfaultMain.getMainAlarmId() + "'";
                else if (mainAlarmIds.indexOf(commonfaultMain.getMainAlarmId()) == -1) {
                    isAlarmid = false;
                    mainAlarmIds = mainAlarmIds + ",'" + commonfaultMain.getMainAlarmId() + "'";
                }
            }

            where = " where remark1 in(" + mainAlarmIds + ")";
        }
        condition.put("where", where);
        int aTotal[] = new int[1];
        List steplist = autoservice.getObjectsByCondtion(new Integer(0), new Integer(-1), aTotal, condition, "record");
        request.setAttribute("steplist", steplist);
        return mapping.findForward("add");
    }

    public ActionForward nextSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String alarmId = StaticMethod.nullObject2String(request.getParameter("alarmId"));
        String alarmTitle = StaticMethod.nullObject2String(request.getParameter("alarmTitle"));
        String autoDealTask = StaticMethod.nullObject2String(request.getParameter("autoDealTask"));
        String autoDealMode = StaticMethod.nullObject2String(request.getParameter("autoDealMode"));
        String autoRule = StaticMethod.nullObject2String(request.getParameter("autoRule"));
        String special = StaticMethod.nullObject2String(request.getParameter("special"));
        String vendor = StaticMethod.nullObject2String(request.getParameter("vendor"));
        String equipmentType = StaticMethod.nullObject2String(request.getParameter("equipmentType"));
        request.setAttribute("alarmId", alarmId);
        request.setAttribute("alarmTitle", alarmTitle);
        request.setAttribute("autoDealTask", autoDealTask);
        request.setAttribute("autoDealMode", autoDealMode);
        request.setAttribute("autoRule", autoRule);
        request.setAttribute("special", special);
        request.setAttribute("vendor", vendor);
        request.setAttribute("equipmentType", equipmentType);
        String findForward = "";
        if ("1".equals(autoDealMode))
            findForward = "tranferadd";
        else
            findForward = "holdadd";
        return mapping.findForward(findForward);
    }

    public ActionForward saveObject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm TawSystemSessionForm = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String userId = TawSystemSessionForm.getUserid();
        Map map = request.getParameterMap();
        AutoDealSOP object = new AutoDealSOP();
        SheetBeanUtils.populateMap2Bean(object, map);
        object.setCreateTime(new Date());
        object.setCreateUser(userId);
        IAutoDealSOPMgr mgr = (IAutoDealSOPMgr) getBean("autoDealSopMgr");
        mgr.saveObject(object);
        return mapping.findForward("success");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String ids = StaticMethod.nullObject2String(request.getParameter("ids"));
        String inids = "";
        IAutoDealSOPMgr mgr = (IAutoDealSOPMgr) getBean("autoDealSopMgr");
        if (!"".equals(ids)) {
            String idArray[] = ids.split(",");
            for (int i = 0; i < idArray.length; i++) {
                String id = idArray[i];
                inids = "'" + id + "'," + inids;
            }

            inids = inids.substring(0, inids.length() - 1);
            String sql = "delete from AutoDealSOP where id in (" + inids + ")";
            mgr.executeHsql(sql);
        }
        return mapping.findForward("success");
    }

    public ActionForward showList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
        if (!exportType.equals(""))
            pageSize = new Integer(-1);
        IAutoDealSOPMgr mgr = (IAutoDealSOPMgr) getBean("autoDealSopMgr");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(2, -1);
        Date lastMonth = c.getTime();
        String lastMonths = sdf.format(lastMonth);
        lastMonths = lastMonths.substring(0, 10) + " 00:00:00";
        String currentTime = StaticMethod.getCurrentDateTime();
        Map map = mgr.listSOP(pageIndex, pageSize, lastMonths, currentTime);
        int total = ((Integer) map.get("taskTotal")).intValue();
        List taskOvertimeList = (List) map.get("taskList");
        List list = new ArrayList();
        if (taskOvertimeList != null && taskOvertimeList.size() > 0) {
            for (int i = 0; i < taskOvertimeList.size(); i++) {
                Object o[] = (Object[]) taskOvertimeList.get(i);
                AutoDealSOP sopObject = (AutoDealSOP) o[1];
                int count = StaticMethod.nullObject2int(o[0]);
                AutoDealSOPVO vo = new AutoDealSOPVO();
                MyBeanUtils.copyPropertiesFromDBToPage(vo, sopObject);
                vo.setSheetCount((new StringBuffer(String.valueOf(count))).toString());
                list.add(vo);
            }

        }
        request.setAttribute("taskList", list);
        request.setAttribute("total", new Integer(total));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("lastMonths", lastMonths);
        request.setAttribute("currentTime", currentTime);
        return mapping.findForward("list");
    }

    public ActionForward queryList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
        if (!exportType.equals(""))
            pageSize = new Integer(-1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(2, -1);
        Date lastMonth = c.getTime();
        String lastMonths = sdf.format(lastMonth);
        lastMonths = lastMonths.substring(0, 10) + " 00:00:00";
        String currentTime = StaticMethod.getCurrentDateTime();
        Map map = request.getParameterMap();
        AutoDealSOP object = new AutoDealSOP();
        SheetBeanUtils.populateMap2Bean(object, map);
        Map paramMap = SheetBeanUtils.bean2Map(object);
        IAutoDealSOPMgr mgr = (IAutoDealSOPMgr) getBean("autoDealSopMgr");
        Map returnmap = mgr.listSOP(paramMap, pageIndex, pageSize, lastMonths, currentTime);
        List taskOvertimeList = (List) returnmap.get("taskList");
        List list = new ArrayList();
        if (taskOvertimeList != null && taskOvertimeList.size() > 0) {
            for (int i = 0; i < taskOvertimeList.size(); i++) {
                Object o[] = (Object[]) taskOvertimeList.get(i);
                AutoDealSOP sopObject = (AutoDealSOP) o[1];
                int count = StaticMethod.nullObject2int(o[0]);
                AutoDealSOPVO vo = new AutoDealSOPVO();
                MyBeanUtils.copyPropertiesFromDBToPage(vo, sopObject);
                vo.setSheetCount((new StringBuffer(String.valueOf(count))).toString());
                list.add(vo);
            }

        }
        Integer taskTotal = (Integer) returnmap.get("taskTotal");
        request.setAttribute("taskList", list);
        request.setAttribute("total", taskTotal);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("object", object);
        request.setAttribute("lastMonths", lastMonths);
        request.setAttribute("currentTime", currentTime);
        return mapping.findForward("list");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = StaticMethod.nullObject2String(request.getParameter("id"));
        IAutoDealSOPMgr mgr = (IAutoDealSOPMgr) getBean("autoDealSopMgr");
        ITawSystemUserManager userManager = (ITawSystemUserManager) getBean("itawSystemUserManager");
        ITawSystemSubRoleManager subRoleMgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
        AutoDealSOP object = mgr.getSOP(id);
        TawSystemUser user = userManager.getUserByuserid(object.getOperateUserId());
        String userName = "";
        if (user != null)
            userName = user.getUsername();
        JSONArray jsonRoot = new JSONArray();
        JSONObject jitem = new JSONObject();
        jitem.put("id", object.getCreateUser());
        jitem.put("name", userName);
        jsonRoot.put(jitem);
        String jsonString = jsonRoot.toString();
        if (object.getTranferObject() != null) {
            TawSystemSubRole t2subrole = subRoleMgr.getTawSystemSubRole(object.getTranferObject());
            JSONArray jsonRoot1 = new JSONArray();
            JSONObject jitem1 = new JSONObject();
            jitem1.put("id", object.getTranferObject());
            jitem1.put("name", t2subrole.getSubRoleName());
            jsonRoot1.put(jitem1);
            String jsonString1 = jsonRoot1.toString();
            request.setAttribute("jsonString1", jsonString1);
        }
        request.setAttribute("jsonString", jsonString);
        request.setAttribute("alarmId", object.getAlarmId());
        request.setAttribute("alarmTitle", object.getAlarmTitle());
        request.setAttribute("autoDealTask", object.getAutoDealTask());
        request.setAttribute("autoDealMode", object.getAutoDealMode());
        request.setAttribute("autoRule", object.getAutoRule());
        request.setAttribute("object", object);
        String findForward = "";
        if ("1".equals(object.getAutoDealMode()))
            findForward = "tranferadd";
        else
            findForward = "holdadd";
        return mapping.findForward(findForward);
    }

    public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("query");
    }

    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String alarmId = request.getParameter("alarmId");
            String autoDealTask = request.getParameter("autoDealTask");
            IAutoDealSOPMgr mgr = (IAutoDealSOPMgr) getBean("autoDealSopMgr");
            Map conditionMap = new HashMap();
            conditionMap.put("autoDealTask", autoDealTask);
            conditionMap.put("alarmId", alarmId);
            List list = mgr.searchSOP(conditionMap);
            if (list != null && list.size() > 0) {
                JSONObject jitem = new JSONObject();
                jitem.put("text", "该环节的告警ID已经存在");
                JSONUtil.print(response, jitem.toString());
            } else {
                JSONUtil.print(response, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            JSONArray jsonRoot = new JSONArray();
            String special = "";
            String vendor = "";
            String etype = "";
            String alarmId = StaticMethod.nullObject2String(request.getParameter("alarmId"));
            if (!"".equals(alarmId)) {
                String specialcode = alarmId.substring(0, 1);
                String vendorcode = alarmId.substring(1, 3);
                String etypecode = alarmId.substring(3, 6);
                Element root = XmlManage.getFile("/com/boco/eoms/sheetflowline/config/sheetflowline-util.xml").getElement();
                Map specialMap = getParameterByConfig(root, "special");
                Map vendorMap = getParameterByConfig(root, "vendor");
                special = StaticMethod.nullObject2String(specialMap.get(specialcode));
                vendor = StaticMethod.nullObject2String(vendorMap.get(vendorcode));
                if ("0".equals(specialcode) || "1".equals(specialcode) || "2".equals(specialcode)) {
                    Map equipmenttypeMap = getParameterByConfig(root, "equipmenttype");
                    etype = StaticMethod.nullObject2String(equipmenttypeMap.get(etypecode));
                } else if ("6".equals(specialcode)) {
                    Map specialetypeMap = getParameterByConfig(root, "specialetype");
                    etype = StaticMethod.nullObject2String(specialetypeMap.get(etypecode));
                }
            }
            JSONObject jitem1 = new JSONObject();
            jitem1.put("id", "special");
            jitem1.put("text", special);
            jsonRoot.put(jitem1);
            JSONObject jitem2 = new JSONObject();
            jitem2.put("id", "vendor");
            jitem2.put("text", vendor);
            jsonRoot.put(jitem2);
            JSONObject jitem3 = new JSONObject();
            jitem3.put("id", "equipmentType");
            jitem3.put("text", etype);
            jsonRoot.put(jitem3);
            JSONUtil.print(response, jsonRoot.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map getParameterByConfig(Element root, String element) {
        Element inspection = root.getChild(element);
        List childOneList = inspection.getChildren();
        Map map = new HashMap();
        Element childOne = null;
        if (childOneList != null && childOneList.size() > 0) {
            for (int i = 0; i < childOneList.size(); i++) {
                childOne = (Element) childOneList.get(i);
                String code = childOne.getAttributeValue("code");
                String url = childOne.getAttributeValue("value");
                map.put(code, url);
            }

        }
        return map;
    }

    public ActionForward getSheetListByRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
        Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        String exportType = StaticMethod.null2String(request.getParameter((new ParamEncoder("taskList")).encodeParameterName("e")));
        if (!exportType.equals(""))
            pageSize = new Integer(-1);
        String ruleId = StaticMethod.nullObject2String(request.getParameter("id"));
        String lastMonths = StaticMethod.nullObject2String(request.getParameter("lastMonths"));
        String currentTime = StaticMethod.nullObject2String(request.getParameter("currentTime"));
        IAutoDealSOPMgr mgr = (IAutoDealSOPMgr) getBean("autoDealSopMgr");
        Map returnmap = mgr.listSopSheet(ruleId, pageIndex, pageSize, lastMonths, currentTime);
        List taskList = (List) returnmap.get("taskList");
        Integer taskTotal = (Integer) returnmap.get("taskTotal");
        request.setAttribute("taskList", taskList);
        request.setAttribute("total", taskTotal);
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("soplist");
    }
}
