/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.netchange.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.netchange.model.NetChangeLink;
import com.boco.eoms.sheet.netchange.task.impl.NetChangeTask;
import com.boco.eoms.sheet.tool.access.model.TawSheetAccess;
import com.boco.eoms.sheet.tool.access.service.ITawSheetAccessManager;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;


/**
 * @author TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class NetChangeSheetMethod extends BaseSheet {

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getInterfaceObjMap(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub

        HashMap hashMap = new HashMap();
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        if (taskName.equals("ProjectCreateTask")) {
            String mainIsNeedDesign = StaticMethod.nullObject2String(request.getParameter("mainIfRequireProject"));
            if (mainIsNeedDesign.equals("1030101")) {
                taskName = StaticMethod.nullObject2String(request.getParameter("trueActiveTemplateId"));
            }
        }
        String operatName = StaticMethod.nullObject2String(request
                .getParameter("operateName"));
        System.out.println("operateName is -----------------------"
                + operatName);
        try {
            if (operatName.equals("forceHold")) {
                HashMap map = new HashMap();
                String sheetKey = StaticMethod.nullObject2String(request
                        .getParameter("id"));
                if (sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request
                            .getParameter("sheetKey"));
                }
                //BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                map.put("main", main);
                map.put("link", getLinkService().getLinkObject().getClass().newInstance());
                map.put("operate", getPageColumnName());
                hashMap.put("selfSheet", map);
            } else if (taskName.equals("")) {
                // 新增工单
                HashMap sheetMap = new HashMap();
                sheetMap.put("main", getMainService().getMainObject().getClass().newInstance());
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);
            } else if (taskName.equals("DraftTask") || taskName.equals("RejectTask")) {
                // 草稿状态
                HashMap sheetMap = new HashMap();
                String sheetKey = StaticMethod.nullObject2String(request
                        .getParameter("mainId"));
                if (sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request
                            .getParameter("sheetKey"));
                }
                //BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                sheetMap.put("main", main);
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);
            } else if (taskName.equals("advice") || taskName.equals("reply")) {
                HashMap sheetMap = new HashMap();
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);
            } else if (taskName.equals("ProjectCreateTask") ||
                    taskName.equals("AuditTask") ||
                    taskName.equals("PermitTask") ||
                    taskName.equals("ExecuteTask") ||
                    taskName.equals("TestTask") ||
                    taskName.equals("HoldTask")) {
                // 待归档
                String sheetKey = StaticMethod.nullObject2String(request
                        .getParameter("mainId"));
                if (sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request
                            .getParameter("sheetKey"));
                }
                // System.out.println("==$$$$==sheetKey==="+sheetKey);
                HashMap sheetMap = new HashMap();
                //BaseMain main = this.getMainService().getMainDAO().loadSinglePO(sheetKey, this.getMainService().getMainObject());
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                sheetMap.put("main", main);
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);
            } else {
                // 其他人工处理
                HashMap sheetMap = new HashMap();
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.dealFlowEngineMap(mapping, form, request, response);
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        if (taskName.equals("ProjectCreateTask")) {
            String mainIsNeedDesign = StaticMethod.nullObject2String(request.getParameter("mainIfRequireProject"));
            if (mainIsNeedDesign.equals("1030101")) {
                taskName = StaticMethod.nullObject2String(request.getParameter("trueActiveTemplateId"));
            }
        }
        String phaseId = StaticMethod.nullObject2String(request
                .getParameter("phaseId"));
        HashMap sheetMap = this.getFlowEngineMap();
        //阶段回复
        if (taskName.equals("reply") || taskName.equals("advice")) {
            Map link = (HashMap) sheetMap.get("link");
            link.put("id", "");
            sheetMap.put("link", link);
        }
        //如果是驳回则将驳回次数加1
        if (taskName.equals("PermitTask") && (phaseId.equals("DraftTask") || phaseId.equals("ProjectCreateTask"))) {
            Map main = (HashMap) sheetMap.get("main");
            Integer mainRejectTimes = (Integer) main.get("mainRejectTimes");
            mainRejectTimes = new Integer(mainRejectTimes.intValue() + 1);
            main.put("mainRejectTimes", mainRejectTimes);
            sheetMap.put("main", main);
            this.setFlowEngineMap(sheetMap);
        }
        //将实施完成时限数据保存到main的mainExecuteEndDate中
        if (taskName.equals("ProjectCreateTask")) {
            Map main = (HashMap) sheetMap.get("main");
            Map link = (HashMap) sheetMap.get("link");
            main.put("mainExecuteEndDate", link.get("linkExecuteEndDate"));
            sheetMap.put("main", main);
            this.setFlowEngineMap(sheetMap);
        }
        //自动派单
        String mainIsNeedDesign = StaticMethod.nullObject2String(request.getParameter("mainIfRequireProject"));
        BocoLog.info(this, "====makeStep1==== taskName:" + taskName);
        BocoLog.info(this, "====makeStep1==== mainIsNeedDesign:" + mainIsNeedDesign);
        if ((taskName.equals("") || taskName.equals("DraftTask") || taskName.equals("RejectTask")) && mainIsNeedDesign.equals("1030101") && phaseId.equals("AuditTask")) {
            HashMap link = (HashMap) sheetMap.get("link");
            if (taskName.equals("")) {
                link.put("operateType", new Integer(0));
            } else if (taskName.equals("DraftTask")) {
                link.put("operateType", new Integer(3));
            } else if (taskName.equals("RejectTask")) {
                link.put("operateType", new Integer(54));
            }
            link.put("activeTemplateId", taskName);
            sheetMap.put("link", link);
            sheetMap = makeStep1(sheetMap);
        }
    }

    public void newPerformAdd(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.newPerformAdd(mapping, form, request, response);

        //如果是报备则需要保存一条Main记录
        String mainChangeSource = StaticMethod.nullObject2String(request
                .getParameter("mainChangeSource"));
        try {
            if (mainChangeSource.equals("101090101") || mainChangeSource.equals("101090102")) {
                BaseMain main = (BaseMain) this.getMainService().getMainObject().getClass().newInstance();
                Map map = this.getFlowEngineMap();
                Map mainMap = (Map) map.get("main");
                Iterator names = mainMap.keySet().iterator();
                while (names.hasNext()) {
                    String name = (String) names.next();
                    Object value = mainMap.get(name);
                    if (value != null) {
                        Class clazz = value.getClass();
                        String className = clazz.getName();
                        if (className.equalsIgnoreCase("java.util.Date")) {
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String datestr = df.format((Date) value);
                            mainMap.put(name, datestr);
                        }
                    }
                }
                SheetBeanUtils.populateMap2Bean(main, mainMap);
                this.getMainService().addMain(main);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInputDealPage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputDealPage(mapping, form, request, response);
        //////////驳回////////////
        // 取上条TASK
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        if (taskName.equals("")) {
            taskName = StaticMethod.nullObject2String(request
                    .getParameter("taskName"));
        }
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        //碰到需要驳回操作的节点时，去取上一个Task记录
        if (taskName.equals("ProjectCreateTask") || taskName.equals("AuditTask") || taskName.equals("PermitTask") || taskName.equals("PlanTask") || taskName.equals("ExecuteTask") || taskName.equals("TestTask") || taskName.equals("HoldTask")) {
            //下面这个方法写在base类中，会取出4个值，传递到inputdealpage页面中
            super.setParentTaskOperateWhenRejct(request);
        }
        if (taskName.equals("PermitTask")) {
            setParentTaskOperateWhenRejct("", request);
        }
        //////////驳回////////////

        //需要调用外部流程
        if (taskName.equals("ExecuteTask")) {
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
                    .getInstance().getBean("ITawSheetRelationManager");
            List relationAllList = rmgr.getAllSheetByParentIdAndPhaseId(sheetKey, taskName);
            if (relationAllList != null) {
                for (int i = 0; i < relationAllList.size(); i++) {
                    TawSheetRelation relation = (TawSheetRelation) relationAllList.get(i);
                    int state = relation.getInvokeState();
                    if (state == Constants.INVOKE_STATE_RUNNING) {
                        request.setAttribute("ifInvoke", "no");
                        break;
                    }
                    request.setAttribute("ifInvoke", "yes");
                }
            } else {
                request.setAttribute("ifInvoke", "no");
            }
        }
        if (taskName.equals("HoldTask")) {
            String sheetKey = StaticMethod.nullObject2String(request
                    .getParameter("sheetKey"), "");
            BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
            String parentSheetName = main.getParentSheetName();
            String parentSheetKey = main.getParentSheetId();
            if (parentSheetName != null && !parentSheetName.equals("")) {
                IMainService parentMainService = (IMainService) ApplicationContextHolder
                        .getInstance().getBean(parentSheetName);
                BaseMain parentMain = parentMainService.getSingleMainPO(parentSheetKey);

                String parentPhaseName = main.getParentPhaseName();

                if (parentPhaseName.indexOf("@") != -1) {
                    request.setAttribute("parentPiid", parentPhaseName.substring(parentPhaseName.indexOf("@") + 1));
                    System.out.println("回调：parentProcessId：" + parentPhaseName.substring(parentPhaseName.indexOf("@") + 1));
                } else {
                    request.setAttribute("parentPiid", parentMain.getPiid());
                }

                request.setAttribute("parentMain", parentMain);
                request.setAttribute("parentProcessName", parentSheetName);
                System.out.println("netchang 执行了回调 ===========");
            }
        }
    }

    /**
     * ADD jialei 当驳回的时候查询之前特定的任务的执行者对象
     * 从request中通过request.getAttribute(fOperateroleid/ftaskOwner/fOperateroleidType)
     * 取得上一条任务的Operateroleid taskOwner OperateroleidType
     *
     * @param request
     * @throws Exception
     */
    public void setParentTaskOperateWhenRejct(String taskName, HttpServletRequest request)
            throws Exception {
        String prelinkid = StaticMethod.nullObject2String(request
                .getParameter("preLinkId"));
        BaseLink preLink = (BaseLink) this.getLinkService()
                .getSingleLinkPO(prelinkid);
        String fOperateroleid = "";
        String ftaskOwner = "";
        String fOperateroleidType = "";
        String fPreTaskName = "";
        if (preLink != null) {
            // 不是流程第一个操作步骤
            String parentTaskId = StaticMethod.nullObject2String(preLink
                    .getAiid());
            if (!parentTaskId.equals("")) {
                ITask task = this.getTaskService().getSinglePO(parentTaskId);
                fOperateroleid = task.getOperateRoleId();
                ftaskOwner = task.getTaskOwner();
                fOperateroleidType = task.getOperateType();
                fPreTaskName = task.getTaskName();
            } else {
                String sheetKey = preLink.getMainId();
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                fOperateroleid = main.getSendRoleId();
                ftaskOwner = main.getSendUserId();
                fOperateroleidType = UIConstants.NODETYPE_SUBROLE;
            }
        }
        request.setAttribute("fOperateroleid", fOperateroleid);
        request.setAttribute("ftaskOwner", ftaskOwner);
        request.setAttribute("fOperateroleidType", fOperateroleidType);
        request.setAttribute("fPreTaskName", fPreTaskName);

        //得到特定的任务的执行者对象
        if (taskName != null) {
            while (!taskName.equals(StaticMethod.nullObject2String(fPreTaskName))) {
                prelinkid = preLink.getPreLinkId();
                preLink = (BaseLink) this.getLinkService().getSingleLinkPO(prelinkid);
                fOperateroleid = "";
                ftaskOwner = "";
                fOperateroleidType = "";
                fPreTaskName = "";
                if (preLink != null) {
                    // 不是流程第一个操作步骤
                    String parentTaskId = StaticMethod.nullObject2String(preLink.getAiid());
                    if (!parentTaskId.equals("")) {
                        ITask task = this.getTaskService().getSinglePO(parentTaskId);
                        fOperateroleid = task.getOperateRoleId();
                        ftaskOwner = task.getTaskOwner();
                        fOperateroleidType = task.getOperateType();
                        fPreTaskName = task.getTaskName();
                    } else {
                        String sheetKey = preLink.getMainId();
                        BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                        fOperateroleid = main.getSendRoleId();
                        ftaskOwner = main.getSendUserId();
                        fOperateroleidType = UIConstants.NODETYPE_SUBROLE;
                        fPreTaskName = "";
                    }
                }
                request.setAttribute(fPreTaskName + "Operateroleid", fOperateroleid);
                request.setAttribute(fPreTaskName + "TaskOwner", ftaskOwner);
                request.setAttribute(fPreTaskName + "OperateroleidType", fOperateroleidType);
                if (fPreTaskName.equals("")) {
                    break;
                }
            }
        }
    }

    public Map getProcessOjbectAttribute() {
        Map attributeMap = new HashMap();
        attributeMap.put("dealPerformer", "dealPerformer");
        attributeMap.put("copyPerformer", "copyPerformer");
        attributeMap.put("auditPerformer", "auditPerformer");
        attributeMap.put("subAuditPerformer", "subAuditPerformer");
        attributeMap.put("objectName", "operate");
        return attributeMap;
    }


    public Map getAttachmentAttributeOfOjbect() {
        Map objectMap = new HashMap();

        List mainAttachmentAttributes = new ArrayList();
        mainAttachmentAttributes.add("sheetAccessories");

        List linkAttachmentAttributes = new ArrayList();
        linkAttachmentAttributes.add("nodeAccessories");

        objectMap.put("mainObject", mainAttachmentAttributes);
        objectMap.put("linkObject", linkAttachmentAttributes);
        return objectMap;
    }

    /**
     * 工单提交预处理
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public void performPreCommit(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        if (taskName.equals("")) {
            taskName = StaticMethod.nullObject2String(request
                    .getParameter("taskName"));
        }
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        if (taskName.equals("ExecuteTask") && operateType.equals("118")) {
            //查询工单互调表
            ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
                    .getInstance().getBean("ITawSheetRelationManager");
            List relationAllList = rmgr.getRunningSheetByParentIdAndPhaseId(sheetKey, taskName);
            //如果已经调用了其他工单则继续执行父类的performPreCommit方法,否则返回status为-1
            if (relationAllList != null && relationAllList.size() > 0) {
                super.performPreCommit(mapping, form, request, response);
            } else {
                JSONArray data = new JSONArray();
                JSONObject o = new JSONObject();
                o.put("text", "请调用其他流程！");
                data.put(o);
                JSONObject jsonRoot = new JSONObject();
                jsonRoot.put("data", data);
                jsonRoot.put("status", "2");
                JSONUtil.print(response, jsonRoot.toString());
            }
        } else {
            super.performPreCommit(mapping, form, request, response);
        }
    }

    /**
     * 工单强制归档、作废页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void showForceHoldPage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showForceHoldPage(mapping, form, request, response);
        String sheetKey = StaticMethod.nullObject2String(request
                .getParameter("sheetKey"), "");
        //已经调用了其他流程
        //查询工单互调表
        ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
                .getInstance().getBean("ITawSheetRelationManager");
        List relationAllList = rmgr.getRunningSheetByParentId(sheetKey);
        if (relationAllList != null && relationAllList.size() > 0) {
            request.setAttribute("invoke", "true");
        } else {
            request.setAttribute("invoke", "false");
        }


        //需要回调外部流程
        BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
        String parentSheetName = main.getParentSheetName();
        String parentSheetKey = main.getParentSheetId();
        if (parentSheetName != null && !parentSheetName.equals("")) {
            IMainService parentMainService = (IMainService) ApplicationContextHolder
                    .getInstance().getBean(parentSheetName);
            BaseMain parentMain = parentMainService.getSingleMainPO(parentSheetKey);
            String parentPhaseName = main.getParentPhaseName();

            if (parentPhaseName.indexOf("@") != -1) {
                request.setAttribute("parentPiid", parentPhaseName.substring(parentPhaseName.indexOf("@") + 1));
                System.out.println("回调：parentProcessId：" + parentPhaseName.substring(parentPhaseName.indexOf("@") + 1));
            } else {
                request.setAttribute("parentPiid", parentMain.getPiid());
            }
            request.setAttribute("parentMain", parentMain);
            request.setAttribute("parentProcessName", parentSheetName);
        }
    }

    /**
     * 由于operateType为111被审批使用，所以这里将performIsInvokeProcess覆盖
     */
    public boolean performIsInvokeProcess(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response) {
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        System.out.println("opetateType===========" + operateType);
        if (taskName.equals("")) {
            taskName = StaticMethod.nullObject2String(request
                    .getParameter("taskName"));
        }
        //查询工单互调表
        ITawSheetRelationManager mgr = (ITawSheetRelationManager) ApplicationContextHolder.getInstance().getBean("ITawSheetRelationManager");
        List relationAllList = mgr.getRunningSheetByParentIdAndPhaseId(sheetKey, taskName);
        if (relationAllList != null && relationAllList.size() > 0 && !operateType.equals("113")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 自动派单生成T1数据
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public HashMap makeStep1(HashMap sheetMap) throws Exception {
        BocoLog.info(this, "====makeT1====");
        Map mainMap = (HashMap) sheetMap.get("main");
        Map tmpLinkMap = (HashMap) sheetMap.get("link");
        Map operateMap = (HashMap) sheetMap.get("operate");

        if (operateMap.get("phaseId").equals("AuditTask")) {
            Date nowDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nowDate);
            sheetMap.put("operate", operateMap);

            Map linkMap = new HashMap();
            linkMap.putAll(tmpLinkMap);

            Iterator names = linkMap.keySet().iterator();
            while (names.hasNext()) {
                String name = (String) names.next();
                Object value = linkMap.get(name);
                if (value != null) {
                    Class clazz = value.getClass();
                    String className = clazz.getName();
                    if (className.equalsIgnoreCase("java.util.Date")) {
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String datestr = df.format((Date) value);
                        linkMap.put(name, datestr);
                    }
                }
            }

            String tkid = "_AI:" + UUIDHexGenerator.getInstance().getID();

            //生成新增工单的link记录
            NetChangeLink link1 = (NetChangeLink) this.getLinkService()
                    .getLinkObject().getClass().newInstance();
            SheetBeanUtils.populateMap2Bean(link1, linkMap);
            link1.setId(UUIDHexGenerator.getInstance().getID());
            link1.setMainId((String) mainMap.get("id"));
            link1.setOperateTime(nowDate);
            link1.setToOrgRoleId((String) mainMap.get("sendRoleId"));

            String activeTemplateId = (String) linkMap.get("activeTemplateId");
            if (activeTemplateId.equals("DraftTask")) {
                link1.setOperateType(new Integer(3));
                link1.setActiveTemplateId(activeTemplateId);
            } else if (activeTemplateId.equals("RejectTask")) {
                link1.setOperateType(new Integer(54));
                link1.setActiveTemplateId(activeTemplateId);
            } else {
                link1.setOperateType(new Integer(0));
                link1.setActiveTemplateId("");
            }
            link1.setAiid("");
            link1.setPreLinkId("");
            link1.setNodeAccessories("");
            this.getLinkService().addLink(link1);

            //生成T1处理的确认受理的link记录
            NetChangeLink link2 = (NetChangeLink) this.getLinkService()
                    .getLinkObject().getClass().newInstance();
            SheetBeanUtils.populateMap2Bean(link2, linkMap);
            link2.setId(UUIDHexGenerator.getInstance().getID());
            link2.setOperateType(new Integer("61"));
            link2.setActiveTemplateId("ProjectCreateTask");
            link2.setOperateTime(new Date(nowDate.getTime() + 1000));
            link2.setMainId((String) mainMap.get("id"));
            link2.setToOrgRoleId((String) mainMap.get("sendRoleId"));
            link2.setPreLinkId(link1.getId());
            link2.setAiid(tkid);
            link2.setNodeAccessories("");
            this.getLinkService().addLink(link2);

            //生成T1处理的Task记录
            NetChangeTask task = (NetChangeTask) this.getTaskService()
                    .getTaskModelObject();

            task.setId(tkid);
            task.setCreateTime(nowDate);
            task.setTaskStatus(Constants.TASK_STATUS_FINISHED);
            task.setProcessId((String) mainMap.get("piid"));
            task.setSheetKey((String) mainMap.get("id"));
            task.setSheetId((String) mainMap.get("sheetId"));
            task.setTitle((String) mainMap.get("title"));
            task.setAcceptTimeLimit(link1.getNodeAcceptLimit());
            task.setCompleteTimeLimit(link1.getNodeCompleteLimit());
            task.setPreLinkId(link1.getId());
            task.setCurrentLinkId((String) linkMap.get("id"));
            task.setIfWaitForSubTask("false");
            task.setCreateDay(calendar.get(calendar.DATE));
            task.setCreateMonth(calendar.get(calendar.MONTH) + 1);
            task.setCreateYear(calendar.get(calendar.YEAR));
            task.setTaskDisplayName("方案制定中");
            task.setTaskName("ProjectCreateTask");
            task.setOperateRoleId(link1.getOperateRoleId());
            task.setTaskOwner(link1.getOperateUserId());
            task.setOperateType("subrole");
            task.setFlowName("NetChangeMainProcess");
            task.setParentTaskId(task.getId());
            task.setSendTime((Date) mainMap.get("sendTime"));
            this.getTaskService().addTask(task);

            //生成Step1处理的link记录
            tmpLinkMap.put("operateType", "110");
            tmpLinkMap.put("mainId", (String) mainMap.get("id"));
            tmpLinkMap.put("preLinkId", link1.getId());
            tmpLinkMap.put("aiid", task.getId());
            tmpLinkMap.put("operateTime", new Date(nowDate.getTime() + 2000));
            tmpLinkMap.put("nodeCompleteLimit", mainMap.get("sheetCompleteLimit"));
            tmpLinkMap.put("activeTemplateId", "ProjectCreateTask");
            tmpLinkMap.put("nodeAccessories", mainMap.get("firstNodeAccessories"));
            sheetMap.put("link", tmpLinkMap);
        }
        return sheetMap;
    }

    /**
     * 工单的初始化页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void showInputNewSheetPage(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputNewSheetPage(mapping, form, request, response);
        //增加T1处理的附件模板
        String processName = this.getMainService().getFlowTemplateName();
        ITawSheetAccessManager mgr = (ITawSheetAccessManager) ApplicationContextHolder
                .getInstance().getBean("ItawSheetAccessManager");
        TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processName, "ProjectCreateTask");
        request.setAttribute("tawSheetAccess1", access);
    }

    public void showInputTemplateSheetPage(ActionMapping mapping,
                                           ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        super.showInputTemplateSheetPage(mapping, form, request, response);
        //增加T1处理的模板
        String processName = this.getMainService().getFlowTemplateName();
        ITawSheetAccessManager mgr = (ITawSheetAccessManager) ApplicationContextHolder
                .getInstance().getBean("ItawSheetAccessManager");
        TawSheetAccess access = mgr.getAccessByPronameAndTaskname(processName, "ProjectCreateTask");
        request.setAttribute("tawSheetAccess1", access);

    }

    public String getSheetAttachCode() {
        // TODO Auto-generated method stub
        return null;
    }

    public Map initMap(Map map, List attach, String type) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}
