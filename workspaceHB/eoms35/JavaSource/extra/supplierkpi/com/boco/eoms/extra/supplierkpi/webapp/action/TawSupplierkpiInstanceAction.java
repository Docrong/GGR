package com.boco.eoms.extra.supplierkpi.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.Constants;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiAssessInstance;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstanceAss;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiRelation;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiDictManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInstanceAssManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInstanceManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateManager;
import com.boco.eoms.extra.supplierkpi.util.SuppStaticVariable;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSupplierkpiInstanceForm;
import com.boco.eoms.extra.supplierkpi.webapp.util.SupplierkpiAttributes;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;

public final class TawSupplierkpiInstanceAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TawSupplierkpiInstanceForm tawSupplierkpiInstanceForm = (TawSupplierkpiInstanceForm) form;
        ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");

        String specialType = "";
        String id = tawSupplierkpiInstanceForm.getId();
        TawSupplierkpiInstance tawSupplierkpiInstance = mgr
                .getTawSupplierkpiInstance(id);
        specialType = tawSupplierkpiInstance.getSpecialType();
        return searchView(mapping, form, request, response, specialType);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionMessages messages = new ActionMessages();
        TawSupplierkpiInstanceForm tawSupplierkpiInstanceForm = (TawSupplierkpiInstanceForm) form;

        ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
        mgr.removeTawSupplierkpiInstance(tawSupplierkpiInstanceForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                "tawSupplierkpiInstance.deleted"));

        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSupplierkpiInstanceForm tawSupplierkpiInstanceForm = (TawSupplierkpiInstanceForm) form;

        if (tawSupplierkpiInstanceForm.getId() != null) {
            ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
            TawSupplierkpiInstance tawSupplierkpiInstance = mgr
                    .getTawSupplierkpiInstance(tawSupplierkpiInstanceForm
                            .getId());
            tawSupplierkpiInstanceForm = (TawSupplierkpiInstanceForm) convert(tawSupplierkpiInstance);
            updateFormBean(mapping, request, tawSupplierkpiInstanceForm);
        }

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionMessages messages = new ActionMessages();
        TawSupplierkpiInstanceForm tawSupplierkpiInstanceForm = (TawSupplierkpiInstanceForm) form;
        ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");

        String[] updateId = StaticMethod.TokenizerString2(tawSupplierkpiInstanceForm.getUpdateid(), "@@");
        String[] examineContentJs = StaticMethod.TokenizerString2(tawSupplierkpiInstanceForm.getExamineContentJs(), "@@");
        String[] memoJs = StaticMethod.TokenizerString2(tawSupplierkpiInstanceForm.getMemoJs(), "@@");

        for (int i = 0; i < updateId.length; i++) {
            TawSupplierkpiInstance tawSupplierkpiInstance = mgr
                    .getTawSupplierkpiInstance(updateId[i]);
            if (!examineContentJs[i].trim().equals("")) {
                tawSupplierkpiInstance.setFillFlag(1);
                tawSupplierkpiInstance.setExamineContent(URLDecoder.decode(examineContentJs[i].trim(), "UTF-8"));
                tawSupplierkpiInstance.setMemo(URLDecoder.decode(memoJs[i].trim(), "UTF-8"));
                tawSupplierkpiInstance.setFirstFillTime(StaticMethod.getTimestamp(StaticMethod.getCurrentDateTime()));
                mgr.saveTawSupplierkpiInstance(tawSupplierkpiInstance);
                messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                        "tawSupplierkpiInstance.updated"));
                saveMessages(request, messages);
            }
            /**
             else if(!examineContentJs[i].trim().equals("") || !memoJs[i].trim().equals("")){

             tawSupplierkpiInstance.setExamineContent(examineContentJs[i].trim());
             tawSupplierkpiInstance.setMemo(memoJs[i].trim());
             tawSupplierkpiInstance.setFirstFillTime(StaticMethod.getTimestamp(StaticMethod.getCurrentDateTime()));
             mgr.saveTawSupplierkpiInstance(tawSupplierkpiInstance);
             messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
             "tawSupplierkpiInstance.updated"));
             saveMessages(request, messages);
             }
             */
        }
        PrintWriter out = response.getWriter();
        out.print("true");
        return null;
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionMessages messages = new ActionMessages();
        TawSupplierkpiInstanceForm tawSupplierkpiInstanceForm = (TawSupplierkpiInstanceForm) form;
        ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");

        String specialType = "";
        String id = tawSupplierkpiInstanceForm.getId();
        TawSupplierkpiInstance tawSupplierkpiInstance = mgr
                .getTawSupplierkpiInstance(id);
        specialType = tawSupplierkpiInstance.getSpecialType();
        tawSupplierkpiInstance.setExamineContent(tawSupplierkpiInstanceForm
                .getExamineContent());
        tawSupplierkpiInstance.setMemo(tawSupplierkpiInstanceForm.getMemo());
        tawSupplierkpiInstance.setFillFlag(1);
        mgr.saveTawSupplierkpiInstance(tawSupplierkpiInstance);
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                "tawSupplierkpiInstance.updated"));
        saveMessages(request, messages);

        return searchView(mapping, form, request, response, specialType);

    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response,
                                String specialType) throws Exception {
        String specialType1 = (String) (request.getParameter("id"));
        ITawSupplierkpiInstanceAssManager instanceAssMgr = (ITawSupplierkpiInstanceAssManager) getBean("ItawSupplierkpiInstanceAssManager");
        TawSupplierkpiInstanceAss instanceAss = instanceAssMgr.getTawSupplierkpiInstanceAssBySpecialType(specialType1);
        if (instanceAss.getAssessStatus() == 3) {
            String failInfo = "对不起，该专业填写的评估数据已经通过审核，本月不能继续填写！";
            request.setAttribute("failInfo", failInfo);
            return mapping.findForward("fail");
        }
        String currentTime = StaticMethod.getCurrentDateTime();
        int date = Integer.parseInt(currentTime.substring(8, 10));
        String py = SuppStaticVariable.getLocalString(date, 0);
        String year = py.substring(0, 4);
        String fy = py.substring(5, 7);
        String timeLatitude = "";
        String timeLatitude1 = "";
        if ("03".equals(fy)) {
            timeLatitude = "one";
        } else if ("06".equals(fy)) {
            timeLatitude = "two";
        } else if ("09".equals(fy)) {
            timeLatitude = "three";
        } else if ("12".equals(fy)) {
            timeLatitude = "four";
            timeLatitude1 = "year";
        }

        String whereHql = "";

        if (specialType1 != null && !specialType1.equals("-1")) {
            whereHql = " where fillFlag = 0 and specialType ='" + specialType1
                    + "'" + " and (timeLatitude='" + fy + "' or timeLatitude='"
                    + timeLatitude + "' or timeLatitude='" + timeLatitude1
                    + "')" + " and year='" + year + "'";
        } else {
            whereHql = " where fillFlag = 0 and specialType ='" + specialType
                    + "'" + " and (timeLatitude='" + fy + "' or timeLatitude='"
                    + timeLatitude + "' or timeLatitude='" + timeLatitude1
                    + "')" + " and year='" + year + "'";
        }

        // 用于页面点击厂商或者服务类型时,按其中一个字段排序
        String orderType = "desc";
        String manufacturerId = (String) request.getParameter("manufacturerId");
        String itemType = (String) request.getParameter("itemType");
        String orderHql = "";
        if (manufacturerId != null && !manufacturerId.equals("")) {
            orderHql = " order by manufacturerId " + manufacturerId;
            if (manufacturerId.equals("desc")) {
                orderType = "asc";
            } else {
                orderType = "desc";
            }
        } else if (itemType != null && !itemType.equals("")) {
            orderHql = " order by itemType " + itemType;
            if (itemType.equals("desc")) {
                orderType = "asc";
            } else {
                orderType = "desc";
            }
        }
        Map fillMap = (HashMap) querySupplierkpiInstances(request, whereHql,
                orderHql);
        List fillList = (List) fillMap.get("result");

        ArrayList itemTypes = (ArrayList) fillMap.get("itemTypes");
        ArrayList manufacturers = (ArrayList) fillMap.get("manufacturers");

        TawSupplierkpiInstance instanceForm = new TawSupplierkpiInstance();
        if (fillList.size() > 0 && fillList != null) {
            instanceForm = (TawSupplierkpiInstance) fillList.get(0);
            String serviceTy = instanceForm.getServiceType();
            String specialTy = instanceForm.getSpecialType();
            String statictsCy = instanceForm.getStatictsCycle();
            String timeLati = instanceForm.getTimeLatitude();
            String _year = instanceForm.getYear();
            request.setAttribute("serviceTy", serviceTy);
            request.setAttribute("specialTy", specialTy);
            request.setAttribute("statictsCy", statictsCy);
            request.setAttribute("timeLati", timeLati);
            request.setAttribute("_year", _year);

            request.setAttribute("itemTypes", itemTypes);
            request.setAttribute("manufacturers", manufacturers);
            request.setAttribute(Constants.TAWSUPPLIERKPIINSTANCE_LIST,
                    fillList);
            request.setAttribute("resultSize", fillMap.get("total"));
            request.setAttribute("TREEID2", specialType);
            request.setAttribute("TREEID1", specialType1);
            request.setAttribute("orderType", orderType);

            return mapping.findForward("list");
        } else {
            return mapping.findForward("finish");
        }
    }

    public ActionForward searchView(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String specialType = (String) (request.getParameter("id"));
        return searchView(mapping, form, request, response, specialType);

    }

    public ActionForward searchView(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response, String specialType)
            throws Exception {

        String queryYear = (String) (request.getParameter("year"));
        String queryMonth = (String) (request.getParameter("month"));
        String fy = "";
        String year = "";
        String timeLatitude = "";
        String timeLatitude1 = "";
        if (queryYear != null && !"".equals(queryYear) && queryMonth != null && !"".equals(queryMonth)) {
            fy = queryMonth;
            year = queryYear;
        } else {
            String currentTime = StaticMethod.getCurrentDateTime();
            int date = Integer.parseInt(currentTime.substring(8, 10));
            String py = SuppStaticVariable.getLocalString(date, 0);
            year = py.substring(0, 4);
            fy = py.substring(5, 7);
        }
        if ("03".equals(fy)) {
            timeLatitude = "one";
        } else if ("06".equals(fy)) {
            timeLatitude = "two";
        } else if ("09".equals(fy)) {
            timeLatitude = "three";
        } else if ("12".equals(fy)) {
            timeLatitude = "four";
            timeLatitude1 = "year";
        }
        String viewHql = "";
        if (specialType != null && !specialType.equals("-1")) {
            viewHql = " where fillFlag != 0 and specialType ='" + specialType + "'" +
                    " and (timeLatitude='" + fy + "' or timeLatitude='" + timeLatitude + "' or timeLatitude='" + timeLatitude1 + "')" +
                    " and year='" + year + "'";
        }
        Map viewMap = (HashMap) querySupplierkpiInstances(request, viewHql, "");
        List viewList = (List) viewMap.get("result");

        TawSupplierkpiInstance instanceForm = new TawSupplierkpiInstance();
        if (viewList.size() > 0) {
            instanceForm = (TawSupplierkpiInstance) viewList.get(0);
            String serviceTy = instanceForm.getServiceType();
            String specialTy = instanceForm.getSpecialType();
            String statictsCy = instanceForm.getStatictsCycle();
            String timeLati = instanceForm.getTimeLatitude();
            String _year = instanceForm.getYear();
            request.setAttribute("serviceTy", serviceTy);
            request.setAttribute("specialTy", specialTy);
            request.setAttribute("statictsCy", statictsCy);
            request.setAttribute("timeLati", timeLati);
            request.setAttribute("_year", _year);

            request.setAttribute("viewList", viewList);
            request.setAttribute("viewSize", viewMap.get("total"));
            request.setAttribute("pageNum", viewMap.get("pageNum"));
            return mapping.findForward("filled");
        } else {
            return mapping.findForward("failer");
        }

    }

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response, null);
    }

    /**
     * 根据父节点的id得到所有子节点的JSON数据
     */
    public ActionForward getNodes(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String treeRootId = ((SupplierkpiAttributes) getBean("supplierkpiAttributes")).getTreeRootId();
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");

        // 根据用户ID返回该用户所隶属的所有子角色
        TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
        List userRoleList = rolebo.getRoleByuserid(sessionform
                .getUserid(), RoleConstants.ROLETYPE_SUBROLE);
        String querystr = "";
        String whereHql = "";
        for (int i = 0; i < userRoleList.size(); i++) {
            TawSystemSubRole userRole = (TawSystemSubRole) userRoleList.get(i);
            querystr += userRole.getId() + "','";
        }
        if (querystr.length() > 0) {
            querystr = querystr.substring(0, querystr.length() - 3);
        }
        whereHql = " where fillRole in ('" + querystr + "')"; // 最后需要加上月份,季度,年度条件,以防查出来非本月的内容.

        ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
        Map fillMap = (Map) mgr.getTawSupplierkpiInstances(0, 10, whereHql, whereHql); // 未填写完成的
        List fillList = (List) fillMap.get("result");
        if (fillList.size() > 0 && fillList != null) {
            ITawSupplierkpiInstanceManager instancemgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
            ITawSupplierkpiDictManager dictmgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");

            String currentTime = StaticMethod.getCurrentDateTime();
            int date = Integer.parseInt(currentTime.substring(8, 10));
            String py = SuppStaticVariable.getLocalString(date, 0);
            String year = py.substring(0, 4);
            String fy = py.substring(5, 7);
            String timeLatitude = "";
            String timeLatitude1 = "";
            if ("03".equals(fy)) {
                timeLatitude = "one";
            } else if ("06".equals(fy)) {
                timeLatitude = "two";
            } else if ("09".equals(fy)) {
                timeLatitude = "three";
            } else if ("12".equals(fy)) {
                timeLatitude = "four";
                timeLatitude1 = "year";
            }
            ArrayList dictList = new ArrayList();
            ArrayList list = new ArrayList();
            JSONArray jsonRoot = new JSONArray();
            HashMap dictMap = new HashMap();
            String nodeId = request.getParameter("node");

            String hql = "select distinct(specialType) from TawSupplierkpiInstance where (timeLatitude='"
                    + fy
                    + "'"
                    + " or timeLatitude='"
                    + timeLatitude
                    + "' or timeLatitude='"
                    + timeLatitude1
                    + "') and year='"
                    + year + "' and fillRole in ('" + querystr + "')";

            //填写数据已经通过审核的专业不允许再填写,屏蔽树图节点 edit by liqiuye 2008-3-26
            //hql = hql + " and specialType not in(select distinct(specialType) from TawSupplierkpiInstanceAss where assessStatus=3)";

            dictList = (ArrayList) instancemgr.getTawSupplierkpiInstances(hql);

            if (dictList.size() <= 0) {
                JSONArray json = new JSONArray();
                JSONObject item = new JSONObject();
                item.put("text", "对不起,目前没有需要您填写的内容!");
                item.put("leaf", true);
                item.put("allowExtra1", false);
                item.put("allowExtra2", false);
                item.put("allowEdit", false);
                json.put(item);
                response.setContentType("text/xml;charset=UTF-8");
                response.getWriter().print(json.toString());
                return null;
            }

            for (int i = 0; i < dictList.size(); i++) {
                String special = dictList.get(i).toString();
                while (special.length() >= 3) {
                    if (dictMap.get(special) != null
                            && !dictMap.get(special).equals("")) {

                    } else {
                        dictMap.put(special, special);
                    }
                    special = special.substring(0, special.length() - 2);
                }
            }
            Set set = dictMap.keySet();
            for (Iterator dictIt = set.iterator(); dictIt.hasNext(); ) {
                String dictId = (String) dictIt.next();
                list = dictmgr.getDictSonsByDictId(nodeId);
                for (Iterator rowIt = list.iterator(); rowIt.hasNext(); ) {
                    TawSupplierkpiDict _objDictType = (TawSupplierkpiDict) rowIt
                            .next();
                    if (dictId.equals(_objDictType.getDictId())) {

                        JSONObject jitem = new JSONObject();
                        jitem.put("id", _objDictType.getDictId());
                        jitem.put("parentDictId", _objDictType.getParentDictId());
                        jitem.put("text", _objDictType.getDictName());
                        if (treeRootId.equals(_objDictType.getParentDictId())) {
                            jitem.put("allowExtra1", false);
                            jitem.put("allowExtra2", false);
                            jitem.put("allowEdit", false);
                        } else {
                            int flag = 0;
                            ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
                            ITawSupplierkpiDictManager dictTypeMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
                            List sonList = dictTypeMgr.getDictSonsByDictId(_objDictType.getDictId());
                            for (int i = 0; i < sonList.size(); i++) {
                                TawSupplierkpiDict tawSystemDictType = (TawSupplierkpiDict) sonList.get(i);
                                String tempDictId = tawSystemDictType.getDictId();
                                String tempId = templateMgr.getTemplateIdBySpecialType(tempDictId);
                                if ((tempId != null) && (!"".equals(tempId))) {
                                    flag = 1;
                                    break;
                                }
                            }
                            if (0 == flag) {
                                jitem.put("allowExtra1", true);
                                jitem.put("allowExtra2", true);
                                jitem.put("allowEdit", true);
                            } else {
                                jitem.put("allowExtra1", false);
                                jitem.put("allowExtra2", false);
                                jitem.put("allowEdit", false);
                            }
                        }
                        for (Iterator leafIt = dictList.iterator(); leafIt
                                .hasNext(); ) {
                            String leaf = leafIt.next().toString();
                            if (leaf.equals(_objDictType.getDictId())) {
                                jitem.put("leaf", true);
                            }
                        }
                        jsonRoot.put(jitem);
                    }
                }
            }
            response.setContentType("text/xml;charset=UTF-8");
            response.getWriter().print(jsonRoot.toString());
            return null;
        } else {
            JSONArray jsonRoot = new JSONArray();
            JSONObject jitem = new JSONObject();
            jitem.put("text", "对不起,目前没有需要您填写的内容!");
            jitem.put("leaf", true);
            jitem.put("allowExtra1", false);
            jitem.put("allowExtra2", false);
            jitem.put("allowEdit", false);
            jsonRoot.put(jitem);
            response.setContentType("text/xml;charset=UTF-8");
            response.getWriter().print(jsonRoot.toString());
            return null;
        }
    }

    public Map querySupplierkpiInstances(HttpServletRequest request, String hql, String orderStr) {

        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession
                ().getAttribute("sessionform");
        TawSystemUserRoleBo rolebo = TawSystemUserRoleBo.getInstance();
        List userRoleList = rolebo.getRoleByuserid(sessionform.getUserid(), RoleConstants.ROLETYPE_SUBROLE);
        Map map = new HashMap();
        String querystr = "";

        for (int i = 0; i < userRoleList.size(); i++) {
            TawSystemSubRole userRole = (TawSystemSubRole) userRoleList.get(i);
            querystr += userRole.getId() + "','";
        }
        //如果满足查询条件,则查出所有的填定实例.否转到提示页面
        if (querystr.length() > 0) {
            querystr = querystr.substring(0, querystr.length() - 3);
            hql = hql + " and fillRole in ('" + querystr + "')";
            String orderHql = hql + orderStr;
            String pageIndexName = new org.displaytag.util.ParamEncoder(
                    "tawSupplierkpiInstance")
                    .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
            final int pageSize = 100;
            final int pageIndex = GenericValidator.isBlankOrNull(request
                    .getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request
                    .getParameter(pageIndexName)) - 1);
            String pageNum = String.valueOf(pageIndex);
            ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
            map = (Map) mgr.getTawSupplierkpiInstances(pageIndex, pageSize, orderHql, hql);
            ArrayList itemTypes = (ArrayList) mgr.getItemType(hql);
            ArrayList manufacturers = (ArrayList) mgr.getManufacturerName(hql);
            map.put("itemTypes", itemTypes);
            map.put("manufacturers", manufacturers);
            map.put("pageNum", pageNum);
        }
        return map;
    }

    //追加售前售中的各项指标
    public ActionForward addSell(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSupplierkpiInstanceForm tawSupplierkpiInstanceForm = (TawSupplierkpiInstanceForm) form;
        String serviceType = tawSupplierkpiInstanceForm.getServiceType();
        String specialType = tawSupplierkpiInstanceForm.getSpecialType();

        ITawSupplierkpiInstanceAssManager instanceAssMgr = (ITawSupplierkpiInstanceAssManager) getBean("ItawSupplierkpiInstanceAssManager");
        TawSupplierkpiInstanceAss instanceAss = instanceAssMgr.getTawSupplierkpiInstanceAssBySpecialType(specialType);
        if (instanceAss.getAssessStatus() == 3) {
            String failInfo = "对不起，该专业填写的评估数据已经通过审核，本月不能继续填写！";
            request.setAttribute("failInfo", failInfo);
            return mapping.findForward("fail");
        }

        String hql = " from TawSupplierkpiAssessInstance assessInstance,TawSupplierkpiRelation relation,TawSupplierkpiItem item" +
                " where assessInstance.id=relation.assessInstanceId and relation.kpiItemId=item.id" +
                " and item.assessMoment!='106010603' and assessInstance.specialType='" + specialType + "'";
        ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
        ArrayList list = (ArrayList) mgr.getTawSupplierkpiInstances(hql);

        //若没有售前售中指标,则给出提示
        if (list.size() <= 0) {
            String failInfo = "对不起，该专业目前没有供应商定制售前/售中指标！";
            request.setAttribute("failInfo", failInfo);
            return mapping.findForward("fail");
        }

        ArrayList viewList = new ArrayList();
        TawSupplierkpiAssessInstance assessInstance = new TawSupplierkpiAssessInstance();
        TawSupplierkpiRelation relation = new TawSupplierkpiRelation();
        TawSupplierkpiItem kpiItem = new TawSupplierkpiItem();

        for (int i = 0; i < list.size(); i++) {
            String manufacturerName = null;
            TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();
            assessInstance = (TawSupplierkpiAssessInstance) ((Object[]) list.get(i))[0];
            relation = (TawSupplierkpiRelation) ((Object[]) list.get(i))[1];
            kpiItem = (TawSupplierkpiItem) ((Object[]) list.get(i))[2];
            //取出对应的厂商名称
            if (!assessInstance.getSupplierId().equals("") && assessInstance.getSupplierId() != null) {
                manufacturerName = mgr.getTawSupplierNameById(assessInstance.getSupplierId());
            }
            tawSupplierkpiInstance.setKpiItemId(StaticMethod.null2String(relation.getKpiItemId()));
            tawSupplierkpiInstance.setManufacturerId(StaticMethod.null2String(assessInstance.getSupplierId()));
            tawSupplierkpiInstance.setManufacturerName(StaticMethod.null2String(manufacturerName));
            tawSupplierkpiInstance.setServiceType(StaticMethod.null2String(assessInstance.getServiceType()));
            tawSupplierkpiInstance.setSpecialType(StaticMethod.null2String(assessInstance.getSpecialType()));
            tawSupplierkpiInstance.setKpiName(StaticMethod.null2String(kpiItem.getKpiName()));
            tawSupplierkpiInstance.setAssessId(StaticMethod.null2String(relation.getAssessInstanceId()));
            tawSupplierkpiInstance.setItemType(StaticMethod.null2String(kpiItem.getItemType()));
            tawSupplierkpiInstance.setAssessContent(StaticMethod.null2String(kpiItem.getAssessContent()));
            tawSupplierkpiInstance.setAssessNote(StaticMethod.null2String(kpiItem.getAssessNote()));
            tawSupplierkpiInstance.setUnit(StaticMethod.null2String(kpiItem.getUnit()));
            tawSupplierkpiInstance.setIsImpersonality(StaticMethod.null2String(kpiItem.getIsImpersonality()));
            tawSupplierkpiInstance.setAssessMoment(StaticMethod.null2String(kpiItem.getAssessMoment()));
            viewList.add(tawSupplierkpiInstance);
        }
        request.setAttribute("serviceType", serviceType);
        request.setAttribute("specialType", specialType);
        request.setAttribute("viewList", viewList);
        return mapping.findForward("noSell");
    }

    //保存售前售中的各项指标
    public ActionForward saveSell(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String currentTime = StaticMethod.getCurrentDateTime();
        int date = Integer.parseInt(currentTime.substring(8, 10));
        String py = SuppStaticVariable.getLocalString(date, 0);
        String year = py.substring(0, 4);
        String fy = py.substring(5, 7);
        String fillStratTime = StaticMethod.getLocalString();
        String fillEndTime = StaticMethod.getLocalString(7, 0);

        TawSupplierkpiInstanceForm tawSupplierkpiInstanceForm = (TawSupplierkpiInstanceForm) form;
        ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
        ITawSystemSubRoleManager subRole = (ITawSystemSubRoleManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemSubRoleManager");
        String[] temp = StaticMethod.TokenizerString2(
                tawSupplierkpiInstanceForm.getKpiItemId(), "@@");
        String specialType = tawSupplierkpiInstanceForm.getSpecialType();
        for (int k = 0; k < temp.length; k++) {
            String[] kpiItemIds = StaticMethod.TokenizerString2(temp[k], ",");
            String kpiItemId = kpiItemIds[0].trim();
            String manufacturerId = kpiItemIds[1].trim();

            String hql = " from TawSupplierkpiAssessInstance assessInstance,TawSupplierkpiRelation relation,TawSupplierkpiItem item"
                    + " where assessInstance.id=relation.assessInstanceId and relation.kpiItemId=item.id"
                    + " and item.assessMoment!='106010603' and assessInstance.specialType='"
                    + specialType
                    + "' and item.id = '"
                    + kpiItemId
                    + "' and assessInstance.supplierId = '"
                    + manufacturerId
                    + "'";

            ArrayList list = (ArrayList) mgr.getTawSupplierkpiInstances(hql);
            TawSupplierkpiAssessInstance assessInstance = new TawSupplierkpiAssessInstance();
            TawSupplierkpiRelation relation = new TawSupplierkpiRelation();
            TawSupplierkpiItem kpiItem = new TawSupplierkpiItem();
            TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();
            TawSystemSubRole tawSystemSubRole = new TawSystemSubRole();
            for (int i = 0; i < list.size(); i++) {
                List roleList = new ArrayList();
                String manufacturerName = null;
                assessInstance = (TawSupplierkpiAssessInstance) ((Object[]) list
                        .get(i))[0];
                relation = (TawSupplierkpiRelation) ((Object[]) list.get(i))[1];
                kpiItem = (TawSupplierkpiItem) ((Object[]) list.get(i))[2];
                // 取出对应的厂商名称
                if (!assessInstance.getSupplierId().equals("")
                        && assessInstance.getSupplierId() != null) {
                    manufacturerName = mgr
                            .getTawSupplierNameById(assessInstance
                                    .getSupplierId());
                }
                tawSupplierkpiInstance.setKpiItemId(StaticMethod
                        .null2String(relation.getKpiItemId()));
                tawSupplierkpiInstance.setManufacturerId(StaticMethod
                        .null2String(assessInstance.getSupplierId()));
                tawSupplierkpiInstance.setManufacturerName(StaticMethod
                        .null2String(manufacturerName));
                tawSupplierkpiInstance.setServiceType(StaticMethod
                        .null2String(assessInstance.getServiceType()));
                tawSupplierkpiInstance.setSpecialType(StaticMethod
                        .null2String(assessInstance.getSpecialType()));
                tawSupplierkpiInstance.setKpiName(StaticMethod
                        .null2String(kpiItem.getKpiName()));
                tawSupplierkpiInstance.setAssessId(StaticMethod
                        .null2String(relation.getAssessInstanceId()));
                tawSupplierkpiInstance.setItemType(StaticMethod
                        .null2String(kpiItem.getItemType()));
                tawSupplierkpiInstance.setAssessContent(StaticMethod
                        .null2String(kpiItem.getAssessContent()));
                tawSupplierkpiInstance.setAssessNote(StaticMethod
                        .null2String(kpiItem.getAssessNote()));
                tawSupplierkpiInstance.setDataSource(StaticMethod
                        .null2String(kpiItem.getDataSource()));
                tawSupplierkpiInstance.setDataType(StaticMethod
                        .null2String(kpiItem.getDataType()));
                tawSupplierkpiInstance.setUnit(StaticMethod.null2String(kpiItem
                        .getUnit()));
                tawSupplierkpiInstance.setFillStratTime(StaticMethod
                        .getTimestamp(fillStratTime));
                tawSupplierkpiInstance.setFillEndTime(StaticMethod
                        .getTimestamp(fillEndTime));
//				tawSupplierkpiInstance.setStatictsCycle(StaticMethod
//						.null2String(kpiItem.getStatictsCycle()));
                tawSupplierkpiInstance.setStatictsCycle("106010301");
                tawSupplierkpiInstance.setIsImpersonality(StaticMethod
                        .null2String(kpiItem.getIsImpersonality()));
                tawSupplierkpiInstance.setAssessMoment(StaticMethod
                        .null2String(kpiItem.getAssessMoment()));
                tawSupplierkpiInstance.setTimeLatitude(fy);
                tawSupplierkpiInstance.setYear(year);

                // 如果预定执行者为大角色，则取出其下面的所有子所角，每个子角色分配一份
                if (kpiItem.getPreActor() != null
                        && !kpiItem.getPreActor().equals("")
                        && kpiItem.getPreActor().length() < 32) {
                    roleList = subRole.getTawSystemSubRoles(Long
                            .parseLong(kpiItem.getPreActor()));
                    for (int l = 0; l < roleList.size(); l++) {
                        tawSystemSubRole = (TawSystemSubRole) roleList.get(l);
                        tawSupplierkpiInstance.setFillRole(StaticMethod
                                .null2String(tawSystemSubRole.getId()));
                        mgr.makeTawSupplierkpiInstance(tawSupplierkpiInstance);
                    }
                } else {// 如果预定义执行者为小角色时，则直接给小角色分配指标
                    tawSupplierkpiInstance.setFillRole(StaticMethod
                            .null2String(kpiItem.getPreActor()));
                    mgr.makeTawSupplierkpiInstance(tawSupplierkpiInstance);
                }
            }
        }
        return search(mapping, form, request, response, specialType);
    }

}
