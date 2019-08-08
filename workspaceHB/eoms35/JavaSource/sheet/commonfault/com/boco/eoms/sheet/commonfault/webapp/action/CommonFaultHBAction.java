// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonFaultHBAction.java

package com.boco.eoms.sheet.commonfault.webapp.action;


import org.apache.commons.validator.GenericValidator;

import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commonfaultIrontower.CommonfaultIrontower;
import com.boco.eoms.commons.accessories.service.ITawCommonsAccessoriesManager;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.statistic.base.util.ExcelConverterUtil;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.commonfault.model.CommonFaultLink;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.model.CommonFaultViSheetInfo;
import com.boco.eoms.sheet.commonfault.model.Filedowninfo;
import com.boco.eoms.sheet.commonfault.qo.ICommonFaultQo;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultHBManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultLinkManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultTaskManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultViSheetInfoManager;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;
import com.boco.eoms.sheet.sheetdelete.model.WfSheetDeleteInfo;
import com.boco.eoms.sheet.sheetdelete.service.IWfSheetDeleteInfoManager;
import com.boco.eoms.interfaces.EOMSPreService.IPreprocessResultReviewImplPortBindingStub;
import com.boco.eoms.interfaces.EOMSPreService.IPreprocessResultReviewImplServiceLocator;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;
import com.boco.eoms.sheet.commonfault.util.GeneralUtil;
import com.boco.eoms.sheet.commonfaultpack.service.ICommonFaultPackMainManager;
import com.boco.eoms.util.InterfaceUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.displaytag.util.ParamEncoder;

// Referenced classes of package com.boco.eoms.sheet.commonfault.webapp.action:
//			CommonFaultAction, CommonFaultSheetMethodHB

public class CommonFaultHBAction extends CommonFaultAction {

    public CommonFaultHBAction() {
    }

    public ActionForward showQueryPageHB(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String beanName = mapping.getAttribute();
        IBaseSheet baseSheet = (IBaseSheet) getBean(beanName);
        baseSheet.showQueryPage(mapping, form, request, response);
        return mapping.findForward("queryhb");
    }

    public ActionForward performQueryHB(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CommonFaultSheetMethodHB baseSheet = new CommonFaultSheetMethodHB();
        baseSheet.performQueryHB(mapping, form, request, response);
        return mapping.findForward("querylisthb");
    }

    public ActionForward repealHB(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String oldsql = request.getParameter("oldsql");
        request.setAttribute("oldsql", oldsql);
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        request.setAttribute("Userid", sessionform.getUserid());
        request.setAttribute("Deptid", sessionform.getDeptid());
        request.setAttribute("ContactMobile", sessionform.getContactMobile());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date()).toString();
        request.setAttribute("LocalTime", time);
        return mapping.findForward("repealhb");
    }

    public ActionForward referHB(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println(new Date());
        String ipaddress = request.getRemoteAddr();
        String oldsql = request.getParameter("oldsql");
        request.setAttribute("oldsql", oldsql);
        String excelid = request.getParameter("excelid");
        String sheetkeys[] = request.getParameter("sheetkeys").split(",");
        ICommonFaultHBManager commonfaultHBManager = (ICommonFaultHBManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultHBManager");
        commonfaultHBManager.updateObject(sheetkeys);
        System.out.println(new Date());
        String Userid = request.getParameter("Userid");
        String Deptid = request.getParameter("Deptid");
        String operaterContact = request.getParameter("operaterContact");
        String LocalTime = request.getParameter("LocalTime");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date createTime = df.parse(LocalTime);
        String cancelReason = request.getParameter("cancelReason");
        IWfSheetDeleteInfoManager wfSheetDeleteInfoManager = (IWfSheetDeleteInfoManager) ApplicationContextHolder.getInstance().getBean("iWfSheetDeleteInfoManager");
        for (int i = 0; i < sheetkeys.length; i++) {
            WfSheetDeleteInfo wfSheetDeleteInfo = new WfSheetDeleteInfo();
            wfSheetDeleteInfo.setUserid(Userid);
            wfSheetDeleteInfo.setDepid(Deptid);
            wfSheetDeleteInfo.setCreateTime(createTime);
            wfSheetDeleteInfo.setIsSended("0");
            wfSheetDeleteInfo.setRemark(cancelReason);
            System.out.println(sheetkeys[i]);
            String values[] = sheetkeys[i].split("&");
            wfSheetDeleteInfo.setPiid(values[0]);
            wfSheetDeleteInfo.setSheetKey(values[1]);
            wfSheetDeleteInfo.setIpaddress(ipaddress);
            wfSheetDeleteInfoManager.saveOrUpdateWfSheetDeleteInfo(wfSheetDeleteInfo);
        }

        System.out.println(new Date());
        if (excelid != null && !"".equals(excelid) && !"null".equals(excelid)) {
            commonfaultHBManager.deleteFordel(sheetkeys);
            System.out.println(new Date());
            return performQueryHB(mapping, form, request, response);
        } else {
            return mapping.findForward("successhb");
        }
    }

    public ActionForward showTabundo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("tab");
    }

    public ActionForward showListAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CommonFaultSheetMethodHB baseSheet = new CommonFaultSheetMethodHB();
        baseSheet.showListUndoAll(mapping, form, request, response);
        return mapping.findForward("listall");
    }
	
/*	public ActionForward showListAllPerformace(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	CommonFaultSheetMethodHB baseSheet = new CommonFaultSheetMethodHB();
//	baseSheet.showListUndoAllPerformace(mapping, form, request, response);
	return mapping.findForward("listall");
}*/

    public ActionForward getAlarmPreResult(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String strSheetID = StaticMethod.null2String(request.getParameter("mainAlarmId"));
        System.out.println("lizhi:strSheetID=" + strSheetID);
        try {
            IPreprocessResultReviewImplServiceLocator service = new IPreprocessResultReviewImplServiceLocator();
            IPreprocessResultReviewImplPortBindingStub binding = (IPreprocessResultReviewImplPortBindingStub) service.getIPreprocessResultReviewImplPort();
            String flags = binding.getPreprocessResult(strSheetID);
            System.out.println("lizhi:flag=" + flags);
            InterfaceUtil interfaceUtil = new InterfaceUtil();
            HashMap map = new HashMap();
            map = interfaceUtil.xmlElements(flags, map, "FieldContent");
            String flag = (String) map.get("pretResult");
            JSONArray json = new JSONArray();
            JSONObject jitem = new JSONObject();
            jitem.put("flag", flag);
            json.put(jitem);
            response.setContentType("text/xml;charset=UTF-8");
            response.getWriter().print(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
/*
public ActionForward getCheckDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
throws Exception
{
	String strSheetID = StaticMethod.null2String(request.getParameter("sheetId"));
	String sql = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty(
	"sql.checkDetail"));
	sql = ExcelConverterUtil.replaceAll(sql, "@id@", strSheetID );
	System.out.println("-----checkDetail------"+sql);
    IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
    List finishList = services.getSheetAccessoriesList(sql);
	request.setAttribute("taskList", finishList);
	request.setAttribute("total", "3");
	request.setAttribute("pageSize", "1");
	return mapping.findForward("checkDetailList");
}*/

    public ActionForward performShowSheetInfoBySheet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sheetId = StaticMethod.nullObject2String(request.getParameter("sheetId"));
        ICommonFaultMainManager commonfaultMainManager = (ICommonFaultMainManager) getBean("iCommonFaultMainManager");
        com.boco.eoms.sheet.base.model.BaseMain main = commonfaultMainManager.getMainBySheetId(sheetId);
        request.setAttribute("sheetMain", main);
        return mapping.findForward("maindetail");
    }

    public void getBysheetId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CommonFaultSheetMethodHB baseSheet = new CommonFaultSheetMethodHB();
        System.out.println("sheetId:----------" + request.getParameter("sheetId"));
        List list = baseSheet.showListUndoAllBysheetId(mapping, form, request, response, request.getParameter("sheetId"));
        System.out.println("getBysheetId:-------------aaaa");
        Map taskMap = (Map) list.get(0);
        System.out.println("getBysheetId:-------------bbbb");
        String sheetKey = StaticMethod.nullObject2String(taskMap.get("sheetKey")).trim();
        String taskName = StaticMethod.nullObject2String(taskMap.get("taskName")).trim();
        String id = StaticMethod.nullObject2String(taskMap.get("id")).trim();
        String operateRoleId = StaticMethod.nullObject2String(taskMap.get("operateRoleId")).trim();
        String TKID = StaticMethod.nullObject2String(taskMap.get("id")).trim();
        String processId = StaticMethod.nullObject2String(taskMap.get("processId")).trim();
        String taskStatus = StaticMethod.nullObject2String(taskMap.get("taskStatus")).trim();
        String preLinkId = StaticMethod.nullObject2String(taskMap.get("preLinkId")).trim();
        String sheetId = StaticMethod.nullObject2String(taskMap.get("sheetId")).trim();
        String returnUrl = request.getParameter("url") + "/sheet/commonfault/commonfault.do?method=showDetailPage&sheetKey=" + sheetKey + "&taskId=" + id + "&taskName=" + taskName + "&operateRoleId=" + operateRoleId + "&TKID=" + TKID + "&piid=" + processId + "&taskStatus=" + taskStatus + "&preLinkId=" + preLinkId;
        System.out.println("returnUrl:-----------" + returnUrl);
        response.setContentType("text/xml");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(returnUrl);
    }

    public ActionForward getReply(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sheetKey = StaticMethod.null2String(request.getParameter("sheetKey"));
        System.out.println("lizhi:sheetKey=" + sheetKey);
        CommonFaultSheetMethodHB baseSheet = new CommonFaultSheetMethodHB();
        String reply = baseSheet.getReply(mapping, form, request, response);
        System.out.println("lizhi:reply=" + reply);
        JSONArray json = new JSONArray();
        JSONObject jitem = new JSONObject();
        jitem.put("reply", reply);
        json.put(jitem);
        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(json.toString());
        return null;
    }

    /**
     * add by weichao 20150513 显示待批量接单的工单列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showBatchAcceptSheetList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                                  HttpServletResponse response) throws Exception {
        String psize = StaticMethod.nullObject2String(request.getParameter("psize"));
        if (!"".equals(psize)) {
            if ("f".equals(psize)) {
                psize = "50";
            } else if ("h".equals(psize)) {
                psize = "100";
            } else {
                psize = "15";
            }
        } else {
            psize = "15";
        }

        String hql = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty(
                "batchDeal.batachClaimSQL"));
        String listSize = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty(
                "batchDeal.batachClaimSize"));
        // 获取每页显示条数
        Integer pageSize = Integer.valueOf(psize);
        // 当前页数
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : (Integer
                .parseInt(request.getParameter(pageIndexName)) - 1));
        String exportType = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
        if (!exportType.equals("")) {
            pageSize = new Integer(-1);
        }
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        String deptId = sessionform.getDeptid();
        hql = ExcelConverterUtil.replaceAll(hql, "$userId$", userId);
        hql = ExcelConverterUtil.replaceAll(hql, "$deptId$", deptId);
        ICommonFaultTaskManager taskService = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean(
                "iCommonFaultTaskManager");
        HashMap taskListOvertimeMap = taskService.getTaskListByCondition(hql, pageIndex, pageSize);

        Integer total = ((Integer) taskListOvertimeMap.get("taskTotal"));
        List result = (List) taskListOvertimeMap.get("taskList");
        request.setAttribute("taskList", result);
        request.setAttribute("total", total);
        request.setAttribute("listSize", listSize);
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("batchAcceptlist");
    }

    /**
     * add by weichao 20150518 将所勾选的工单执行 接单处理 操作
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void performBatchClaimSheet(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        BocoLog.info(this, "***开始批量接单start claim sheet***");
        String ids = request.getParameter("ids");
        ids = ids.substring(0, ids.lastIndexOf(","));
        BocoLog.info(this, "批量接单的工单Id(BatchClaimMainId)==" + ids);
        ICommonFaultMainManager mainMgr = (ICommonFaultMainManager) getBean("iCommonFaultMainManager");
        ICommonFaultLinkManager linkMgr = (ICommonFaultLinkManager) getBean("iCommonFaultLinkManager");
        ICommonFaultTaskManager taskMgr = (ICommonFaultTaskManager) getBean("iCommonFaultTaskManager");
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        String deptId = sessionform.getDeptid();
        String mobile = sessionform.getContactMobile();
        try {
            List idsList = GeneralUtil.convertString2List(ids);
            Date now = new Date();
            Calendar ca = Calendar.getInstance();
            int year = ca.get(Calendar.YEAR);//获取年份
            int month = ca.get(Calendar.MONTH);//获取月份
            int day = ca.get(Calendar.DATE);//获取日
            IBusinessFlowService businessFlowService = (IBusinessFlowService) getBean("iCommonFaultFlowManager");
            HashMap sessionMap = new HashMap();
            sessionMap.put("userId", userId);
            sessionMap.put("password", "111");
            WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
            for (int i = 0; i < idsList.size(); i++) {
                HashMap parameters = new HashMap();
                String id = StaticMethod.nullObject2String(idsList.get(i));
                CommonFaultMain baseMain = (CommonFaultMain) mainMgr.getSingleMainPO(id);
                CommonFaultLink newLink = (CommonFaultLink) linkMgr.getLinkObject().getClass().newInstance();
                String condition = " sheetKey = '" + baseMain.getId() + "' and taskName not in('cc','reply','advice')" +
                        "  and taskStatus='2' and (subTaskFlag is null or subTaskFlag = 'false' ) ";//找出待办的task记录
                List baseTasks = taskMgr.getTasksByCondition(condition);
                CommonFaultTask baseTask = null;
                if (baseTasks != null && baseTasks.size() > 0) {
                    baseTask = (CommonFaultTask) baseTasks.get(0);
                } else {
                    continue;
                }
                baseTask.setTaskStatus("8");
                baseTask.setTaskOwner(userId);

                //构造基础的link信息
                newLink.setMainId(baseMain.getId());
                newLink.setOperateType(Integer.valueOf("61"));
                newLink.setOperateTime(now);
                newLink.setNodeAcceptLimit(baseTask.getAcceptTimeLimit());
                newLink.setNodeCompleteLimit(baseTask.getCompleteTimeLimit());
                newLink.setOperateUserId(userId);
                newLink.setPreLinkId(baseTask.getPreLinkId());
                newLink.setPiid(baseMain.getPiid());
                newLink.setAiid(baseTask.getId());
                newLink.setActiveTemplateId(baseTask.getTaskName());
                newLink.setOperateRoleId(baseTask.getOperateRoleId());
                newLink.setToOrgRoleId(baseTask.getOperateRoleId());//派往对象
                newLink.setToOrgType(Integer.valueOf(0));
                newLink.setCompleteFlag(Integer.valueOf(0));
                newLink.setCorrelationKey(baseMain.getCorrelationKey());
                newLink.setOperaterContact(mobile);
                newLink.setOperateDeptId(deptId);
                newLink.setOperateYear(year);
                newLink.setOperateMonth(month);
                newLink.setOperateDay(day);

                Map linkMap = SheetBeanUtils.bean2Map(newLink);
                Map mainMap = SheetBeanUtils.bean2Map(baseMain);

                HashMap columnMap = new HashMap();
                columnMap.put("selfSheet", this.setNewInterfacePara());

                parameters.putAll(mainMap);
                parameters.putAll(linkMap);
                this.setBaseMap(parameters);

                HashMap WpsMap = sm.prepareMap(parameters, columnMap);
                businessFlowService.claimTask(baseTask.getId(), WpsMap, sessionMap);
            }
            GeneralUtil.sendAlert(response, "批量接单成功！", "0");
        } catch (Exception e) {
            e.printStackTrace();
            GeneralUtil.sendAlert(response, "批量接单失败，请联系管理员！", "2");
        }
    }

    public Map setBaseMap(Map map) {
        try {
            ICommonFaultMainManager mainMgr = (ICommonFaultMainManager) getBean("iCommonFaultMainManager");
            ICommonFaultLinkManager linkMgr = (ICommonFaultLinkManager) getBean("iCommonFaultLinkManager");
            map.put("beanId", new String[]{"iCommonFaultMainManager"});
            BocoLog.info(this, "mainClassName=" + mainMgr.getMainObject().getClass().getName());
            BocoLog.info(this, "linkClassName=" + linkMgr.getLinkObject().getClass().getName());
            map.put("mainClassName", new String[]{mainMgr.getMainObject().getClass().getName()});
            map.put("linkClassName", new String[]{linkMgr.getLinkObject().getClass().getName()});
        } catch (Exception err) {
            err.printStackTrace();
        }
        return map;
    }

    public Map setNewInterfacePara() throws Exception {
        ICommonFaultMainManager mainMgr = (ICommonFaultMainManager) getBean("iCommonFaultMainManager");
        ICommonFaultLinkManager linkMgr = (ICommonFaultLinkManager) getBean("iCommonFaultLinkManager");
        HashMap sheetMap = new HashMap();
        sheetMap.put("main", mainMgr.getMainObject().getClass().newInstance());
        sheetMap.put("link", linkMgr.getLinkObject().getClass().newInstance());
        sheetMap.put("operate", Constants.pageColumnName);
        return sheetMap;
    }


    /**
     * add by weichao 20150527 显示合并工单列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showCombineSheetList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
        String psize = StaticMethod.nullObject2String(request.getParameter("psize"));
        String tt = StaticMethod.nullObject2String(request.getParameter("tt"));
        String taskName = "2".equals(tt) ? "SecondExcuteHumTask" : "FirstExcuteHumTask";
        if (!"".equals(psize)) {
            if ("f".equals(psize)) {
                psize = "50";
            } else if ("h".equals(psize)) {
                psize = "100";
            } else {
                psize = "15";
            }
        } else {
            psize = "15";
        }
        String keyHql = "batchDeal.T" + tt + "CombineSQL";
        String keyListsize = "batchDeal.T" + tt + "CombineSQLSize";
        String hql = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty(keyHql));
        String listSize = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty(keyListsize));
        // 获取每页显示条数
        Integer pageSize = Integer.valueOf(psize);
        // 当前页数
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : (Integer
                .parseInt(request.getParameter(pageIndexName)) - 1));
        String exportType = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
        if (!exportType.equals("")) {
            pageSize = new Integer(-1);
        }
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        String deptId = sessionform.getDeptid();
        hql = ExcelConverterUtil.replaceAll(hql, "$userId$", userId);
        hql = ExcelConverterUtil.replaceAll(hql, "$deptId$", deptId);
        ICommonFaultTaskManager taskService = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean(
                "iCommonFaultTaskManager");
        ICommonFaultPackMainManager packMainSerive = (ICommonFaultPackMainManager) ApplicationContextHolder.getInstance().getBean(
                "iCommonFaultPackMainManager");
        HashMap taskListOvertimeMap = taskService.getTaskListByCondition(hql, pageIndex, pageSize);

        Integer total = ((Integer) taskListOvertimeMap.get("taskTotal"));
        int t = total.intValue();
        List result = (List) taskListOvertimeMap.get("taskList");
        if (null != result && taskName.equals("SecondExcuteHumTask")) {
            for (int i = 0; i < result.size(); i++) {
                CommonFaultMain main = (CommonFaultMain) result.get(i);
                if ("1030101".equals(main.getMainIsPack())) {//如果是打包工单则检查打包的工单是否有告警清除时间
                    boolean re = packMainSerive.isAllHaveClearTime(main.getId());
                    if (re) {
                        result.remove(main);
                        t--;
                    }
                }
            }
        }
        request.setAttribute("taskList", result);
        request.setAttribute("total", total);
        request.setAttribute("listSize", listSize);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("taskName", taskName);
        request.setAttribute("tt", tt);
        return mapping.findForward("combinelist");
    }

    /**
     * add by weichao 20150518 将所勾选的工单执行 合并 操作
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward performCombineSheet(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        try {

            ICommonFaultMainManager mainMgr = (ICommonFaultMainManager) getBean("iCommonFaultMainManager");
            ICommonFaultViSheetInfoManager infoMgr = (ICommonFaultViSheetInfoManager) getBean("iCommonFaultViSheetInfoManager");
            ICommonFaultTaskManager taskMgr = (ICommonFaultTaskManager) getBean("iCommonFaultTaskManager");
            String mainId = StaticMethod.nullObject2String(request.getParameter("mains"));// 已勾选主单的id
            String viss = StaticMethod.nullObject2String(request.getParameter("viss"));// 已勾选副单的id
            String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));//合并工单的环节
            viss = viss.substring(0, viss.lastIndexOf(","));
            BocoLog.info(this, "要合并的主单信息main sheet id===" + mainId);
            BocoLog.info(this, "要合并的副单信息viss sheet ids===" + viss);
            //1、更新主单标题
            CommonFaultMain main = (CommonFaultMain) mainMgr.loadSinglePO(mainId);
            String title = main.getTitle();
            if (!title.contains("【合（主）】")) {
                title = "【合（主）】 " + title;
                main.setTitle(title);//保存
            }
            main.setMainIfCombine("combine");
            mainMgr.saveOrUpdateMain(main);
            //2、查找当前步骤的task对象
            String condition = " sheetKey = '" + mainId + "'" +
                    " and taskName='" + taskName + "' and taskStatus='8' and (subTaskFlag is null or subTaskFlag = 'false' )";
            List baseTasks = taskMgr.getTasksByCondition(condition);
            CommonFaultTask baseTask = (CommonFaultTask) baseTasks.get(0);
            Date mainTaskSheetAccept = baseTask.getAcceptTimeLimit();
            Date mainTaskSheetComplete = baseTask.getCompleteTimeLimit();
            baseTask.setTitle(title);
            taskMgr.addTask(baseTask);

            Date mainMainSheetAccept = main.getSheetAcceptLimit();
            Date mainMainSheetComplete = main.getSheetCompleteLimit();
            //3、for循环更新子单的main表 标题、受理时限、完成时限、以及task环节的受理时限、完成时限
            List list = GeneralUtil.convertString2List(viss);
            for (int i = 0; i < list.size(); i++) {
                String visId = StaticMethod.nullObject2String(list.get(i));
                CommonFaultMain vism = (CommonFaultMain) mainMgr.loadSinglePO(visId);
                String vistitle = vism.getTitle();
                vistitle = "【合（副）】 " + vistitle;
                vism.setTitle(vistitle);
                vism.setSheetAcceptLimit(mainMainSheetAccept);
                vism.setSheetCompleteLimit(mainMainSheetComplete);
                vism.setMainIfCombine(mainId);

                //to-do 保存
                String conditionVis = " sheetKey = '" + visId + "'" +
                        " and taskName='" + taskName + "' and taskStatus='8' and (subTaskFlag is null or subTaskFlag = 'false' )";
                List bs = taskMgr.getTasksByCondition(conditionVis);
                CommonFaultTask visTask = null;
                if (null != bs && bs.size() > 0) {
                    visTask = (CommonFaultTask) bs.get(0);
                } else {
                    BocoLog.info(this, "副单没有符合条件的task记录==" + vism.getSheetId());
                    continue;
                }
                visTask.setAcceptTimeLimit(mainTaskSheetAccept);
                visTask.setCompleteTimeLimit(mainTaskSheetComplete);
                visTask.setTaskStatus("9");//随便改个数字，使之不出现在待办工单列表

                //to-do 保存
                CommonFaultViSheetInfo info = new CommonFaultViSheetInfo();
                info.setId(UUIDHexGenerator.getInstance().getID());
                info.setMainId(mainId);
                info.setVisId(visId);
                info.setDeleted(Integer.valueOf(0));
                info.setTaskId(visTask.getId());
                info.setMainSheetAcceptLimit(mainMainSheetAccept);
                info.setMainSheetCompleteLimit(mainMainSheetComplete);
                info.setTaskSheetAcceptLimit(mainTaskSheetAccept);
                info.setTaskSheetCompleteLimit(mainTaskSheetComplete);
                //to-do 保存
                mainMgr.saveOrUpdateMain(vism);
                infoMgr.saveOrUpdate(info);
                taskMgr.addTask(visTask);
            }
            return mapping.findForward("success");

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("合并工单失败，请联系管理员！");
        }
    }

    /**
     * 显示副单列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showVisList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        ICommonFaultMainManager mainMgr = (ICommonFaultMainManager) getBean("iCommonFaultMainManager");
        String id = request.getParameter("id");
        CommonFaultMain main = (CommonFaultMain) mainMgr.loadSinglePO(id);
        String combineStatus = main.getMainIfCombine();
        if (null != combineStatus && "combine".equals(combineStatus)) {
            Integer pageSize = Integer.valueOf("15");
            // 当前页数
            String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                    .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
            final Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : (Integer
                    .parseInt(request.getParameter(pageIndexName)) - 1));
            String exportType = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("taskList")
                    .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
            if (!exportType.equals("")) {
                pageSize = new Integer(-1);
            }
            String sql = "from CommonFaultMain where mainIfCombine='" + id + "'";

            Map map = mainMgr.getQueryListByCondition(sql, pageIndex, pageSize);
            List list = (List) map.get("sheetList");
            Integer total = StaticMethod.nullObject2Integer(map.get("sheetTotal"));
            request.setAttribute("taskList", list);
            request.setAttribute("total", total);
            request.setAttribute("pageSize", pageSize);
        } else {
            request.setAttribute("total", Integer.valueOf(0));
        }

        return mapping.findForward("vislist");
    }

    /**
     * 撤销已合并的副单 add by weichao 20150602
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void cancelViSheet(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        String mid = request.getParameter("m");
        String vid = request.getParameter("v");
        ICommonFaultMainManager mainMgr = (ICommonFaultMainManager) getBean("iCommonFaultMainManager");
        ICommonFaultViSheetInfoManager infoMgr = (ICommonFaultViSheetInfoManager) getBean("iCommonFaultViSheetInfoManager");
        ICommonFaultTaskManager taskMgr = (ICommonFaultTaskManager) getBean("iCommonFaultTaskManager");
        try {
            CommonFaultViSheetInfo info = infoMgr.getCommonFaultViSheetInfoByVisId(mid, vid);
            BocoLog.info(this, "cancel sheet info==" + info.getId());
            JSONArray data = new JSONArray();
            JSONObject o = new JSONObject();
            JSONObject jsonRoot = new JSONObject();
            if (null != info) {
                CommonFaultMain vmain = (CommonFaultMain) mainMgr.loadSinglePO(vid);
                CommonFaultMain main = (CommonFaultMain) mainMgr.loadSinglePO(mid);
                if (null != main.getMainIfReply() && !"".equals(main.getMainIfReply())) {
                    o.put("text", "主单已回单，副单不能撤单！");
                    jsonRoot.put("status", "2");
                } else {
                    BocoLog.info(this, "cancel sheet's sheetId==" + vmain.getSheetId());
                    info.setDeleted(Integer.valueOf(1));
                    infoMgr.saveOrUpdate(info);
                    String title = vmain.getTitle();
                    BocoLog.info(this, "title from cancelViSheet before replace==" + title);
                    title = title.replace("【合（副）】 ", "");
                    BocoLog.info(this, "title from cancelViSheet after replace==" + title);
                    vmain.setTitle(title);
                    vmain.setSheetAcceptLimit(info.getMainSheetAcceptLimit());
                    vmain.setSheetCompleteLimit(info.getMainSheetCompleteLimit());
                    vmain.setMainIfCombine("");

                    ITask task = taskMgr.getSinglePO(info.getTaskId());
                    task.setTaskStatus("8");
                    task.setAcceptTimeLimit(info.getTaskSheetAcceptLimit());
                    task.setCompleteTimeLimit(info.getTaskSheetCompleteLimit());

                    CommonFaultViSheetInfo infoM = infoMgr.getCommonFaultViSheetInfoBymainId(mid);
                    if (null == infoM) {
                        String tt = main.getTitle();
                        BocoLog.info(this, "tt from cancelViSheet before replace==" + tt);
                        tt = tt.replace("【合（主）】 ", "");
                        BocoLog.info(this, "tt from cancelViSheet after replace==" + tt);
                        main.setMainIfCombine("");
                        main.setTitle(tt);//保存
                        mainMgr.saveOrUpdateMain(main);
                    }
                    mainMgr.saveOrUpdateMain(vmain);

                    taskMgr.addTask(task);

                    o.put("text", "撤单成功！");
                    jsonRoot.put("status", "0");
                }
            } else {
                o.put("text", "未找到副单信息！");
                jsonRoot.put("status", "2");
            }
            data.put(o);
            jsonRoot.put("data", data);
            JSONUtil.print(response, jsonRoot.toString());
        } catch (Exception e) {
            e.printStackTrace();
            BocoLog.info(this, "cancel sheet failed");
            JSONArray data = new JSONArray();
            JSONObject o = new JSONObject();
            JSONObject jsonRoot = new JSONObject();
            o.put("text", "撤单失败，请联系管理员！");
            jsonRoot.put("status", "2");
            data.put(o);
            jsonRoot.put("data", data);
            JSONUtil.print(response, jsonRoot.toString());
        }
    }

    /**
     * add by liuyonggang 201700601 显示不同情况下的待办列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showListEeveundo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {


        // 获取每页显示条数
        Integer pageSize = Integer.valueOf("15");
        // 当前页数
        String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : (Integer
                .parseInt(request.getParameter(pageIndexName)) - 1));
        String exportType = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("taskList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
        if (!exportType.equals("")) {
            pageSize = new Integer(-1);
        }
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String userId = sessionform.getUserid();
        String deptId = sessionform.getDeptid();
        ICommonFaultTaskManager taskService = (ICommonFaultTaskManager) ApplicationContextHolder.getInstance().getBean(
                "iCommonFaultTaskManager");

        //判断待办列表在不同情况下的标志
        String undoFlag = StaticMethod.null2String(request.getParameter("undoFlag"));
        System.out.println("showListEeveundo=undoFlag=" + undoFlag);

        String hql = "";
        //T1处理 (新建派发)new、待归档hold、T1处理 (T2驳回)t2reject 、阶段回复reply、跨地市驳回cityreject
        if ("new".equals(undoFlag)) {
            String newSql = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-sql.xml").getProperty("newSql"));
            hql = newSql.replace("$userId$", userId).replace("$deptId$", deptId);
        } else if ("hold".equals(undoFlag)) {
            String holdSql = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-sql.xml").getProperty("hold"));
            hql = holdSql.replace("$userId$", userId).replace("$deptId$", deptId);
        } else if ("t2reject".equals(undoFlag)) {
            String T2Sql = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-sql.xml").getProperty("T2Sql"));
            hql = T2Sql.replace("$userId$", userId).replace("$deptId$", deptId);
        } else if ("reply".equals(undoFlag)) {
            String replySql = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-sql.xml").getProperty("reply"));
            hql = replySql.replace("$userId$", userId).replace("$deptId$", deptId);
        } else if ("cityreject".equals(undoFlag)) {
            String replySql = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-sql.xml").getProperty("cityreject"));
            hql = replySql.replace("$userId$", userId).replace("$deptId$", deptId);
        }

        HashMap taskListMap = taskService.getEeveundo(hql, pageIndex, pageSize);

        Integer total = ((Integer) taskListMap.get("taskTotal"));
        List resultTem = (List) taskListMap.get("taskList");
        List taskList = new ArrayList();

        String[] elementKey = new String[]{"id", "taskName", "taskDisplayName", "createTime", "taskStatus", "processId", "sheetKey", "sheetId", "title", "acceptTimeLimit", "completeTimeLimit", "operateRoleId", "taskOwner", "preLinkId", "flowName", "currentLinkId", "subTaskFlag", "operateType", "parentTaskId", "ifWaitForSubTask", "subTaskDealFalg", "createYear", "createMonth", "createDay", "sendTime", "predealRoleId", "predealDept", "predealUserId", "sendRejectFlag", "revert", "perAllocatedUser", "mainAlarmSolveDate", "mainCheckStatus", "mainNetSortTwo", "mainNetSortThree", "mainFaultResponseLevel", "mainNetSortOne", "mainNetName"};
        for (int i = 0; i < resultTem.size(); i++) {
            Object[] tmpObjArr = (Object[]) resultTem.get(i);
            Map tmptaskMap = new HashMap();
            for (int j = 0; j < elementKey.length; j++) {
                tmptaskMap.put(elementKey[j], tmpObjArr[j]);
            }
            taskList.add(tmptaskMap);
        }

        request.setAttribute("taskList", taskList);
        request.setAttribute("total", total);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("undoFlag", undoFlag);

        return mapping.findForward("eveundolist");
    }

    /**
     * add by liuyonggang 201800418 工单互调汇总 点击sheetId得到页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showListTowner(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        String sheetKey = StaticMethod.null2String(request.getParameter("sheetKey"));
        IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        String townerSql = "SELECT * FROM commonfault_towner WHERE sheetKey = '" + sheetKey + "'";
        List townerList = services.getSheetAccessoriesList(townerSql);
        request.setAttribute("townerList", townerList);
        return mapping.findForward("townerpage");
    }

    public ActionForward performDealByToner(ActionMapping mapping, ActionForm form,
                                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("====input Map===");
        Map map = request.getParameterMap();
        //遍历 map
        Map valueMap = new HashMap();
        Iterator entries = map.entrySet().iterator();
        System.out.println("===Maplgy==" + entries.hasNext());
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            String value = (String) ((String[]) entry.getValue())[0];
            System.out.println("key==liu==" + key + "=====value=" + value);
            valueMap.put(key, value);
        }


        String sheetKey = StaticMethod.nullObject2String(valueMap.get("sheetKey"));
        String TASKID = StaticMethod.nullObject2String(valueMap.get("TASKID"));
        String TASK_STATUS = StaticMethod.nullObject2String(valueMap.get("TASK_STATUS"));
        String TASK_DEAL_DESC = StaticMethod.nullObject2String(valueMap.get("TASK_DEAL_DESC"));
        String TASK_PERSON = StaticMethod.nullObject2String(valueMap.get("TASK_PERSON"));
        String PERSON_TELPHONE = StaticMethod.nullObject2String(valueMap.get("PERSON_TELPHONE"));
        String TASK_TIME = StaticMethod.nullObject2String(valueMap.get("TASK_TIME"));
        String ELECTRIC_CURRENT = StaticMethod.nullObject2String(valueMap.get("ELECTRIC_CURRENT"));
        String TOWNER_DEVICE_ID = StaticMethod.nullObject2String(valueMap.get("TOWNER_DEVICE_ID"));
        String STATION_CODE = StaticMethod.nullObject2String(valueMap.get("STATION_CODE"));
        String STATION_NAME = StaticMethod.nullObject2String(valueMap.get("STATION_NAME"));
        String IS_BUY_ELETRIC = StaticMethod.nullObject2String(valueMap.get("IS_BUY_ELETRIC"));
        String IS_GENELEC_BILL = StaticMethod.nullObject2String(valueMap.get("IS_GENELEC_BILL"));
        String ELETRIC_BEGINTIME = StaticMethod.nullObject2String(valueMap.get("ELETRIC_BEGINTIME"));
        String ELETRIC_ENDTIME = StaticMethod.nullObject2String(valueMap.get("ELETRIC_ENDTIME"));
        String ACCEPT_TIME = StaticMethod.nullObject2String(valueMap.get("ACCEPT_TIME"));
        String CITY_NAME = StaticMethod.nullObject2String(valueMap.get("CITY_NAME"));
        String COUNTRY_NAME = StaticMethod.nullObject2String(valueMap.get("COUNTRY_NAME"));
        String OPERATORS_SHARE = StaticMethod.nullObject2String(valueMap.get("OPERATORS_SHARE"));
        String STATION_LEVEL = StaticMethod.nullObject2String(valueMap.get("STATION_LEVEL"));
        String IS_RELIEF = StaticMethod.nullObject2String(valueMap.get("IS_RELIEF"));
        String FTP_PATH = StaticMethod.nullObject2String(valueMap.get("FTP_PATH"));
        String CAUSE_TYPE = StaticMethod.nullObject2String(valueMap.get("CAUSE_TYPE"));
        String FAULT_TYPE = StaticMethod.nullObject2String(valueMap.get("FAULT_TYPE"));
        String IS_DERATE = StaticMethod.nullObject2String(valueMap.get("IS_DERATE"));
        String DERATE_MESSAGE = StaticMethod.nullObject2String(valueMap.get("DERATE_MESSAGE"));
        String DERATE_MINUTE = StaticMethod.nullObject2String(valueMap.get("DERATE_MINUTE"));
        String DEALSTEP = StaticMethod.nullObject2String(valueMap.get("DEALSTEP"));
        String CLEARTIME = StaticMethod.nullObject2String(valueMap.get("CLEARTIME"));
        String FUALTREASON = StaticMethod.nullObject2String(valueMap.get("FUALTREASON"));

        System.out.println("sheetKey=irontoer==" + sheetKey);

        IDownLoadSheetAccessoriesService sheetServices = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        String inSql = "INSERT INTO commonfault_towner VALUES ('" + TASKID + "','" + TASK_STATUS + "'," +
                "'" + TASK_DEAL_DESC + "'," + "'" + TASK_PERSON + "'," +
                "'" + PERSON_TELPHONE + "'," + "'" + TASK_TIME + "'," +
                "'" + ELECTRIC_CURRENT + "'," + "'" + TOWNER_DEVICE_ID + "'," +
                "'" + STATION_CODE + "'," + "'" + STATION_NAME + "'," +
                "'" + IS_BUY_ELETRIC + "'," + "'" + IS_GENELEC_BILL + "'," +
                "'" + ELETRIC_BEGINTIME + "'," + "'" + ELETRIC_ENDTIME + "'," +
                "'" + ACCEPT_TIME + "'," + "'" + CITY_NAME + "'," +
                "'" + COUNTRY_NAME + "'," + "'" + OPERATORS_SHARE + "'," +
                "'" + STATION_LEVEL + "'," + "'" + IS_RELIEF + "'," +
                "'" + FTP_PATH + "'," + "'" + CAUSE_TYPE + "'," +
                "'" + FAULT_TYPE + "'," + "'" + IS_DERATE + "'," +
                "'" + DERATE_MESSAGE + "'," + "'" + DERATE_MINUTE + "'," +
                "'" + DEALSTEP + "'," + "'" + CLEARTIME + "'," +
                "'" + FUALTREASON + "'," + "'" + sheetKey + "'" +
                ")";
        sheetServices.executeProcedure(inSql);
        request.setAttribute("isTrowerSend", "isTrowerSend");
        return super.performDeal(mapping, form, request, response);
    }

    public ActionForward performDealBackByToner(ActionMapping mapping, ActionForm form,
                                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map map = request.getParameterMap();
        //遍历 map
        Map valueMap = new HashMap();
        Iterator entries = map.entrySet().iterator();
        System.out.println("===Maplgy==" + entries.hasNext());
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            String value = (String) ((String[]) entry.getValue())[0];
            System.out.println("key==liu==" + key + "=====value=" + value);
            valueMap.put(key, value);
        }
        CommonfaultIrontower tower = new CommonfaultIrontower();
        String returnStr = tower.rejectSheet(valueMap);
        System.out.println("tietabohui=" + returnStr);
        return null;
    }

//铁塔接单 performc

    public ActionForward performClaimTaskByToner(ActionMapping mapping, ActionForm form,
                                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map map = request.getParameterMap();
        //遍历 map
        Map valueMap = new HashMap();
        Iterator entries = map.entrySet().iterator();
        System.out.println("===Maplgy==" + entries.hasNext());
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            String key = (String) entry.getKey();
            String value = (String) ((String[]) entry.getValue())[0];
            System.out.println("key==liu==" + key + "=====value=" + value);
            valueMap.put(key, value);
        }

        String sheetId = StaticMethod.nullObject2String(valueMap.get("sheetId"));
        String sheetKey = StaticMethod.nullObject2String(valueMap.get("mainId"));
        IDownLoadSheetAccessoriesService sheetServices = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");


        //调用接口成功后，在 工单互调汇总 表中添加数据
        String inSql = "INSERT INTO sheet_tool_relation VALUES ('" + sheetKey + "','','" + sheetId + "','" + sheetKey + "1','CommonFaultMainFlowProcess','" + sheetId + "','','',SYSDATE,'ComplaintProcess','','','','3','irontowner') ";
        try {
            sheetServices.executeProcedure(inSql);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("err==townerSend====sheetServices.executeProcedure===");
            e.printStackTrace();
        }
        return super.performClaimTask(mapping, form, request, response);
    }


//public ActionForward showQueryPageHBTo7Day(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//		HttpServletResponse response) throws Exception {
//	String fileName = StaticMethod.nullObject2String(request
//			.getParameter("filename"), "");
//	request.setAttribute("fileName", fileName);
//	request.setAttribute("searchInit", "SearchInit");
//	return mapping.findForward("queryhbTo7Day");
//}
//public ActionForward performQueryHBTo7Day(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//		HttpServletResponse response) throws Exception {
//	System.out.println("&&&&进入performQueryHBTo7Day方法&&&&");
//	System.out.println(request.getParameter("sendTimeStartDate"));
//	
//	Map actionMap = request.getParameterMap();
//
//	Iterator names = actionMap.keySet().iterator(); 
//	System.out.println("actionMap中size=             "+actionMap.size());
//    while(names.hasNext()) 
//    {
//        String name = (String)names.next();
//        System.out.println("actionMap中name="+name);
//        }
//
//	List result = null;
//	Integer pageSize = new Integer(100);
//	String pageIndexName = (new ParamEncoder("taskList")).encodeParameterName("p");
//	Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
//	String aSql[] = {
//		""
//	};
//	int aTotal[] = new int[1];
//	String queryType = StaticMethod.nullObject2String(request.getParameter("queryType"));
//	ICommonFaultMainManager mainService = (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
//	ICommonFaultQo workSheetQO = (ICommonFaultQo)mainService.getWorkSheetQO();
//	String oldsql = workSheetQO.getClauseSql(actionMap);
//	oldsql = oldsql.replace("order by", "and commonFaultMain.status = '0' order by");
//	aSql[0] = oldsql;
//	String sql = request.getParameter("oldsql");
//	if (sql != null && !"".equals(sql))
//	{
//		oldsql = sql;
//		aSql[0] = sql;
//	}
//	if (request.getParameter("excelid") == null || "null".equals(request.getParameter("excelid")))
//		result = mainService.getQueryResult(aSql, actionMap, pageIndex, new Integer(pageSize.intValue()), aTotal, queryType);
//	else
//	if (request.getParameter("excelid") != null && !"".equals(request.getParameter("excelid")))
//	{
//		String excelsql = "select distinct commonfaul0_.* from commonfault_main commonfaul0_, commonfault_importfordel t where commonfaul0_.templateFlag <> 1 and (commonfaul0_.title is not null) and commonfaul0_.sheetid = t.sheetid and commonfaul0_.deleted <> '1' and commonfaul0_.status = '0' and t.excelid = '" + request.getParameter("excelid") + "' order by commonfaul0_.sendTime desc";
//		ICommonFaultHBManager commonfaultHBManager = (ICommonFaultHBManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultHBManager");
//		result = commonfaultHBManager.selectObject(excelsql, pageIndex, new Integer(pageSize.intValue()));
//		String countexcelsql = "select count(*) from (select distinct commonfaul0_.* from commonfault_main commonfaul0_, commonfault_importfordel t where commonfaul0_.templateFlag <> 1 and (commonfaul0_.title is not null) and commonfaul0_.sheetid = t.sheetid and commonfaul0_.deleted <> '1' and commonfaul0_.status = '0' and t.excelid = '" + request.getParameter("excelid") + "' order by commonfaul0_.sendTime desc)";
//		aTotal[0] = commonfaultHBManager.selectCount(countexcelsql);
//	}
//	Integer total = new Integer(aTotal[0]);
//	if (queryType != null && queryType.equals("number"))
//		request.setAttribute("recordTotal", total);
//	request.setAttribute("oldsql", oldsql);
//	request.setAttribute("taskList", result);
//	request.setAttribute("total", total);
//	request.setAttribute("pageSize", pageSize);
//	return mapping.findForward("querylisthb");
//}
//
//public ActionForward showQueryPageHBTo3Month(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//		HttpServletResponse response) throws Exception {
//	System.out.println("进入showQueryPageHBTo3Month方法");
//	String fileName = StaticMethod.nullObject2String(request
//			.getParameter("filename"), "");
//	request.setAttribute("fileName", fileName);
//	request.setAttribute("searchInit", "SearchInit");
//	System.out.println("进入页面");
//	return mapping.findForward("queryhbTo3Month");
//}
//
//public ActionForward performQueryHBTo3Month(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//		HttpServletResponse response) throws Exception {
//	System.out.println("进入showQueryPageHBTo3Month方法");
//	String fileName = StaticMethod.nullObject2String(request
//			.getParameter("filename"), "");
//	request.setAttribute("fileName", fileName);
//	request.setAttribute("searchInit", "SearchInit");
//	System.out.println("进入页面");
//	return mapping.findForward("queryhbTo3Month");
//}
//
///**
// * 文件导出
// * 
// * @param mapping
// * @param form
// * @param request
// * @param response
// * @return
// * @throws Exception
// * @author 
// */
//public ActionForward download(ActionMapping mapping, ActionForm form,
//		HttpServletRequest request, HttpServletResponse response)
//		throws Exception {
//	if (log.isDebugEnabled()) {
//		log.debug("Entering 'search' method");
//	}
//
//	try {
//		String id = StaticMethod.nullObject2String(request
//				.getParameter("id"));
//		String beginyear = StaticMethod.nullObject2String(request
//				.getParameter("beginyear"));
//		String beginmonth = StaticMethod.nullObject2String(request
//				.getParameter("beginmonth"));
//		ITawCommonsAccessoriesManager dao1 = (ITawCommonsAccessoriesManager)getBean("ItawCommonsAccessoriesManager");
//		List list1 = dao1.getTawCommonsAccessoriesByMonth(beginyear, beginmonth);
//		if (list1.size()>0) {
//			Filedowninfo f = (Filedowninfo)list1.get(0);
//			String fileCnName = f.getFilename();
//			String fileName = f.getFilename();
//			// String path = accessories.getAccessoriesPath();
//			
//			
//			
//			
//
//			String rootFilePath = f.getFilepath();
//			String path = rootFilePath + fileName;
//			// 若文件系统中的文件不存在则给出提示
//			File file = new File(path);
//			if (!file.exists()) {
//
//			}
//			else {
//				request.setAttribute("noteInfo", "");
//			}
//			InputStream inStream = new FileInputStream(path);
//			//
//			// fileCnName = URLEncoder.encode(fileCnName, "UTF-8");
//			response.reset();
//			response.setContentType("application/x-msdownload");
//			response.setCharacterEncoding("GB2312");
//
//			fileCnName = new String(fileCnName.getBytes("gbk"), "iso8859-1");
//			response.addHeader("Content-Disposition",
//					"attachment;filename=" + fileCnName);
//			// // 文件的存放路径
//			/*
//			 * javax.servlet.RequestDispatcher dispatcher = request
//			 * .getRequestDispatcher(path); if (dispatcher != null) {
//			 * dispatcher.forward(request, response); } 
//			 */
//			// 循环取出流中的数据
//			byte[] b = new byte[128];
//			int len;
//			while ((len = inStream.read(b)) > 0)
//				response.getOutputStream().write(b, 0, len);
//			inStream.close();
//		}
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	System.out.println("###############noteInfo");
//	request.setAttribute("noteInfo", "文件系统中的文件不存在，可能已经被删除！");
//	String fileIdList = StaticMethod.nullObject2String(request
//			.getParameter("filelist"));
//	request.setAttribute("fileIdlist", fileIdList);
//	request.setAttribute("file", "file");
//	System.out.println("##################进入VIEW");
//	return mapping.findForward("view");
//
//}


    public ActionForward getMainAlarmIdIsTure(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String mainAlarmId = StaticMethod.nullObject2String(request.getParameter("mainAlarmId"));
        System.out.println("lyg:mainAlarmId=" + mainAlarmId);
        try {
            String flag = "1";//表示没有找到
            IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
            String sql = "select * from FaultAvoidTime_alarmId where alarmid = '" + mainAlarmId + "'";
            List list = (List) sqlMgr.getSheetAccessoriesList(sql);

            if (list != null && list.size() > 0) {
                flag = "0";
            }
            System.out.println("lizhi:flag=" + flag);
            JSONArray json = new JSONArray();
            JSONObject jitem = new JSONObject();
            jitem.put("flag", flag);
            json.put(jitem);
            response.setContentType("text/xml;charset=UTF-8");
            response.getWriter().print(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据故障地市选择该地市下的班组
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward seleBanz(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IDownLoadSheetAccessoriesService sheetServices = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        String operateRoleId = StaticMethod.nullObject2String(request.getParameter("operateRoleId"));
        String subRoleName = StaticMethod.nullObject2String(request.getParameter("subRoleName"));

        String banzSql = "SELECT * FROM taw_system_sub_role WHERE roleid = '8005106' AND deleted = '0' AND deptid in (SELECT deptid FROM taw_system_sub_role WHERE ID = '" + operateRoleId + "')  ";
        if (!"".equals(subRoleName)) {
            banzSql = banzSql + " and subrolename like '%" + subRoleName + "%' ";
        }
        banzSql = banzSql + " ORDER BY deptid";
        List banzList = sheetServices.getSheetAccessoriesList(banzSql);
        if (banzList != null && banzList.size() > 0) {
            request.setAttribute("banzList", banzList);
        }
        request.setAttribute("subRoleName", subRoleName);
        request.setAttribute("operateRoleId", operateRoleId);
        return mapping.findForward("showbanzlist");
    }


    /**
     * 选择其他班组
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward sendotherbanz(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String dealPerformer = StaticMethod.nullObject2String(request.getParameter("dealPerformer"));
        String linkSendRes = StaticMethod.nullObject2String(request.getParameter("linkSendRes"));
        String linkSendOtherName = StaticMethod.nullObject2String(request.getParameter("linkSendOtherName"));
        String linkSendOtherTel = StaticMethod.nullObject2String(request.getParameter("linkSendOtherTel"));
        String operateRoleId = StaticMethod.nullObject2String(request.getParameter("operateRoleId"));
        String sendObject = "";
        if (!"".equals(dealPerformer)) {
            sendObject = "[{id:'" + dealPerformer + "',nodeType:'subrole',categoryId:'dealPerformer'}]";
        }
        System.out.println(sendObject);
        request.setAttribute("sendObject", sendObject);
        request.setAttribute("linkSendRes", linkSendRes);
        request.setAttribute("linkSendOtherName", linkSendOtherName);
        request.setAttribute("linkSendOtherTel", linkSendOtherTel);

        request.setAttribute("operateRoleId", operateRoleId);

        return mapping.findForward("showotherbanz");
    }

}