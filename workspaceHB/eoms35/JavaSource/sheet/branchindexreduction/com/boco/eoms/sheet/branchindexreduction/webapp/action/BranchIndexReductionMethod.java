package com.boco.eoms.sheet.branchindexreduction.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.common.resource.DateUtil;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.prm.service.impl.Pojo2PojoServiceImpl;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.model.TawSystemWorkflow;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.branchindexreduction.job.AutoFinishSchedule;
import com.boco.eoms.sheet.branchindexreduction.job.AutoTrailAndAgainToSchedule;
import com.boco.eoms.sheet.branchindexreduction.job.DateIfEqualShow;
import com.boco.eoms.sheet.branchindexreduction.job.TimeAndDateDeial;
import com.boco.eoms.sheet.branchindexreduction.model.BranchIndexReductionMain;
import com.boco.eoms.sheet.branchindexreduction.model.SubtractTable;
import com.boco.eoms.sheet.branchindexreduction.service.IBranchIndexReductionLinkManager;
import com.boco.eoms.sheet.branchindexreduction.service.ISubtractTableMgr;
import com.boco.eoms.sheet.daiweiindexreduction.model.DaiSubtractTable;
import com.boco.eoms.sheet.daiweiindexreduction.service.IDaiSubtractTableMgr;

/**
 * <p>
 * Title:分公司指标核减流程
 * </p>
 * <p>
 * Description:分公司指标核减流程
 * </p>
 * <p>
 * Fri Jul 28 15:47:20 CST 2017
 * </p>
 *
 * @author wangmingming
 * @version 1.0
 */

public class BranchIndexReductionMethod extends BaseSheet {

    public String getPageColumnName() {

        return super.getPageColumnName()
                + "gatherPerformer@java.lang.String,gatherPerformerLeader@java.lang.String,"
                + "gatherPerformerType@java.lang.String,gatherPhaseId@java.lang.String,";

    }

    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HashMap hashMap = new HashMap();

        HashMap sheetMap = new HashMap();
        String sheetKey = StaticMethod.nullObject2String(request
                .getParameter("mainId"));
        BaseMain main = (BaseMain) this.getMainService().getMainObject()
                .getClass().newInstance();
        if (!sheetKey.equals("")) {
            sheetKey = StaticMethod.nullObject2String(request
                    .getParameter("sheetKey"));

        }
        if (!sheetKey.equals("")) {
            main = this.getMainService().getSingleMainPO(sheetKey);
        }
        sheetMap.put("main", main);
        sheetMap.put("link", getLinkService().getLinkObject().getClass()
                .newInstance());
        sheetMap.put("operate", getPageColumnName());
        hashMap.put("selfSheet", sheetMap);

        return hashMap;
    }

    /**
     * 提交流程引擎前作最后一次参数处理
     */

    public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.dealFlowEngineMap(mapping, form, request, response);

        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@operateType" + operateType);
        // String phaseId =
        // StaticMethod.nullObject2String(request.getParameter("phaseId"));
        HashMap sheetMap = this.getFlowEngineMap();

        // Map main = (HashMap) sheetMap.get("main");
        Map operate = (HashMap) sheetMap.get("operate");
        Map main = (HashMap) sheetMap.get("main");
        Map link = (HashMap) sheetMap.get("link");

        String[] dealperformers = (StaticMethod.nullObject2String(operate
                .get("dealPerformer"))).split(",");

        if (taskName.equals("reply") || taskName.equals("advice")) {
            link.put("id", "");
        }

        if (dealperformers.length >= 1) {

            String corrKey = "";
            String tmp = "";
            for (int i = 0; i < dealperformers.length; i++) {
                tmp = UUIDHexGenerator.getInstance().getID();
                if (dealperformers.length == 1) {
                    corrKey = tmp;
                } else {
                    if (corrKey.equals("")) {
                        corrKey = tmp;
                    } else {
                        corrKey = corrKey + "," + tmp;
                    }

                }
            }
            System.out.println("corrKey" + corrKey);
            operate.put("extendKey1", corrKey);

        }
        if ("0".equals(operateType) || "".equals(operateType) || "".equals(operateType)) {/*新建 草稿派发 重派*/
            link.put("nodeCompleteLimit", main.get("mainProcessTime"));
        }

        // add by wmm 2017.08.10
        if ("TrialTask".equals(taskName) && "102".equals(operateType)) { // 新建工单 第一次初审提交 处理数据
            main.put("mainReserveC", "1");
            /* 核减内容表id */
            String infoId = StaticMethod.nullObject2String(request
                    .getParameter("infoId"));
            /* 核减理由是否成立 */
            String reserveC = StaticMethod.nullObject2String(request
                    .getParameter("infoReserveC"));
            /* 说明 */
            String reserveD = StaticMethod.nullObject2String(request
                    .getParameter("infoReserveD"));
            ISubtractTableMgr subtractTableMgr = (ISubtractTableMgr) ApplicationContextHolder
                    .getInstance().getBean("subtractTableMgr");
            if (!"".equals(infoId) && !"".equals(reserveC)
                    && !"".equals(reserveD)) {
                String[] infoIds = infoId.split(",");
                String[] reserveCs = reserveC.split(",");
                String[] reserveDs = reserveD.split(",");
                for (int i = 0; i < reserveCs.length; i++) { // 遍历核减理由是否成立的集合
                    SubtractTable subtractTable = (SubtractTable) subtractTableMgr
                            .getSubtractTable(infoIds[i].trim());
                    subtractTable.setReserveC(reserveCs[i]);

                    if ("1030102".equals(reserveCs[i].trim())) {// 否
                        subtractTable.setFirstRejection(reserveDs[i].trim());
                        subtractTable.setReserveD(reserveDs[i].trim());

                    }

                    if ("1030101".equals(reserveCs[i].trim())) { // 是
                        subtractTable.setReserveD(reserveDs[i].trim());

                    }

                    subtractTableMgr.saveSubtractTable(subtractTable);
                }
            }
        }

        if ("TrialTask".equals(taskName) && "101".equals(operateType)) { // 重派工单 第二次初审提交  处理数据
            /* 是否重新派发，1为是 */
            String isResend = StaticMethod.nullObject2String(main
                    .get("mainReserveC"));
            /* 核减内容表id */
            String infoId = StaticMethod.nullObject2String(request
                    .getParameter("infoId"));
            /* 核减理由是否成立 */
            String reserveC = StaticMethod.nullObject2String(request
                    .getParameter("infoReserveC"));
            /* 说明 */
            String reserveD = StaticMethod.nullObject2String(request
                    .getParameter("infoReserveD"));
            ISubtractTableMgr subtractTableMgr = (ISubtractTableMgr) ApplicationContextHolder
                    .getInstance().getBean("subtractTableMgr");

            if (!"".equals(infoId) && !"".equals(reserveC)
                    && !"".equals(reserveD)) {
                String[] infoIds = infoId.split(",");
                String[] reserveCs = reserveC.split(",");
                String[] reserveDs = reserveD.split(",");
                for (int i = 0; i < reserveCs.length; i++) { // 遍历核减理由是否成立的集合
                    SubtractTable subtractTable = (SubtractTable) subtractTableMgr
                            .getSubtractTable(infoIds[i].trim());
                    subtractTable.setReserveC(reserveCs[i]);
                    if ("1030101".equals(reserveCs[i].trim())) { // 是
                        subtractTable.setReserveC("1030101"); // 否改为是
                        subtractTable.setReserveE("101410203"); // 核减状态  核减通过
                        subtractTable.setReserveD(reserveDs[i].trim());
                    }
                    if ("1030102".equals(reserveCs[i].trim())) {// 否
                        subtractTable.setReserveE("101410204"); // 核减状态  核减未通过
                        subtractTable.setSecondRejection(reserveDs[i].trim());
                        subtractTable.setReserveD(reserveDs[i].trim());
                    }
                    subtractTableMgr.saveSubtractTable(subtractTable);
                }
            }
        }


        sheetMap.put("link", link);
        sheetMap.put("operate", operate);
        sheetMap.put("main", main);
        this.setFlowEngineMap(sheetMap);
    }

    /**
     * 设置需要从流程引擎中取的派往对象，包括派发，抄送，送审
     */
    public Map getProcessOjbectAttribute() {
        Map attributeMap = new HashMap();
        attributeMap.put("dealPerformer", "dealPerformer");
        attributeMap.put("copyPerformer", "copyPerformer");
        attributeMap.put("auditPerformer", "auditPerformer");
        attributeMap.put("subAuditPerformer", "subAuditPerformer");
        attributeMap.put("objectName", "operate");
        return attributeMap;
    }

    public Map getParameterMap() {
        Map attributeMap = new HashMap();
        return attributeMap;
    }

    /**
     * 设置main和link保存附件字段属性
     */
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
     * 进入处理环节
     */
    public void showInputDealPage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputDealPage(mapping, form, request, response);
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        String preLinkId = StaticMethod.nullObject2String(request
                .getParameter("preLinkId"));
        // 驳回上一级（不是移交的任务），需要取出上一级的角色和phaseId
        if (operateType.equals("4")) {
            BaseLink prelink = this.getLinkService().getSingleLinkPO(preLinkId);
            if (prelink != null) {
                request.setAttribute("fOperateroleid", prelink
                        .getOperateRoleId());
                request.setAttribute("fPreTaskName", prelink
                        .getActiveTemplateId());
            }
        }
        // 如果是移交后的任务被驳回
        if (operateType.equals("4")) {
            String sheetKey = StaticMethod.nullObject2String(request
                    .getParameter("sheetKey"));
            IBranchIndexReductionLinkManager service = (IBranchIndexReductionLinkManager) ApplicationContextHolder
                    .getInstance().getBean("iBranchIndexReductionLinkManager");
            String taskId = StaticMethod.nullObject2String(request
                    .getParameter("taskId"));
            String condition = " mainId='" + sheetKey
                    + "' and operateType=8 and aiid='" + taskId
                    + "' order by operateTime desc";
            List linkList = service.getLinksBycondition(condition);
            if (linkList != null && linkList.size() > 0) {
                BaseLink prelink = (BaseLink) linkList.get(0);
                request.setAttribute("fOperateroleid", prelink
                        .getOperateRoleId());
                request.setAttribute("fPreTaskName", prelink
                        .getActiveTemplateId());
            }
        }

    }

    /**
     * 工单的初始化页面 本流程新增调用
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String showNewSheetPage(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String actionForward = "new";
        //判断当前用户是否具有建单者权限

//		request.setAttribute("city", StaticMethod.nullObject2String(request.getParameter("city")));
        request.setAttribute("subtractName", StaticMethod.nullObject2String(request.getParameter("subtractName")));
        request.setAttribute("subtractMajor", StaticMethod.nullObject2String(request.getParameter("subtractMajor")));
        request.setAttribute("applyTime", StaticMethod.nullObject2String(request.getParameter("applyTime")));
        request.setAttribute("userId", StaticMethod.nullObject2String(request.getParameter("userId")));

        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        ITawSystemSubRoleManager roleManager = (ITawSystemSubRoleManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemSubRoleManager");
        String processName = this.getMainService().getFlowTemplateName();
        ITawSystemWorkflowManager wfManager = (ITawSystemWorkflowManager) ApplicationContextHolder
                .getInstance().getBean("ITawSystemWorkflowManager");
        TawSystemWorkflow wf = wfManager.getTawSystemWorkflowByName(processName);
        String roleId = StaticMethod.nullObject2String(wf.getRoleId());
        if (!roleId.equals("")) {
            List list = roleManager.getSubRoles(sessionform.getUserid(), roleId);  // 通过session获取  操作人roleId
            if (list == null || list.size() == 0) {
                actionForward = "nopriv";
                return actionForward;
            }
        }

        /*核减指标允许的时间*/
        DateIfEqualShow autoShow = new DateIfEqualShow(); // 核减允许的时间类

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-01 00:00:00");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        Calendar currcal = Calendar.getInstance();
        Date d = sdf1.parse(sdf.format(new Date())); // 当月25号
        currcal.setTime(d);


        Calendar nowcal = Calendar.getInstance();
        nowcal.setTime(new Date());

        // 需要核减工单 新建权限校验语句
        //nowcal.setTime(sdf1.parse("2017-08-28 00:00:00"));
        int jj_n = 0;
        if (DateIfEqualShow.checkHoliday(currcal)) {
            jj_n = 1;
        }
        long now = nowcal.getTimeInMillis();/*当前时间*/
        long currStart = currcal.getTimeInMillis();/*当月25号*/
        long currEnd = DateIfEqualShow.addDate01ByHour(d, 2 + jj_n);
        System.out.println("==========当前时间 currEnd 为==========" + currEnd);
        System.out.println("==========当前时间 currStart 为==========" + currStart);
        String sendSheet = XmlManage.getFile("/config/demandmanage-util.xml").getProperty("sendSheet");
        ;

        if (!"true".equals(sendSheet) && (now <= currStart || now >= currEnd)) {
            request.setAttribute("result", "当前日期不在派单时间段内，不允许派单!");
            actionForward = "unWork";
            return actionForward;  // 无权限页面
        }
//			else if (autoShow.checkHoliday(nowcal)){
//				request.setAttribute("result", "当前日期不是工作日，不允许派单!");
//				actionForward="unWork";
//				return actionForward;  // 无权限页面
//			}


        String parentCorrelation = StaticMethod.nullObject2String(request
                .getParameter("parentCorrelation"), "");
        String parentSheetId = StaticMethod.nullObject2String(request
                .getParameter("parentSheetId"), "");
        String parentSheetName = StaticMethod.nullObject2String(request
                .getParameter("parentSheetName"), "");
        String parentPhaseName = StaticMethod.nullObject2String(request
                .getParameter("parentPhaseName"), "");
        String invokeMode = StaticMethod.nullObject2String(request
                .getParameter("invokeMode"), "");

        //核减bylyg start
        String newFlag = StaticMethod.nullObject2String(request
                .getParameter("newFlag"), "");
        request.setAttribute("newFlag", newFlag);
        //核减bylyg start

        request.setAttribute("parentCorrelation", parentCorrelation);
        request.setAttribute("parentSheetId", parentSheetId);
        request.setAttribute("parentSheetName", parentSheetName);
        request.setAttribute("parentPhaseName", parentPhaseName);
        request.setAttribute("invokeMode", invokeMode);
        return actionForward;
    }

    // 测试日期
//	public static void main(String[] args) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-25 00:00:00");
//		String nowtime =(new SimpleDateFormat("yyyy-MM-25 00:00:00")).format(new Date()); // 当前时间
//		System.out.println("当月25号"+nowtime);
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date());
//		cal.add(Calendar.MONTH, -1);
//		System.out.println("上月25号"+sdf.format(cal.getTime()));
//	}

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
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        // MainForm mainform = this.getMainService().getMainForm();
        BranchIndexReductionMain mainObject = (BranchIndexReductionMain) this.getMainService().getMainObject()
                .getClass().newInstance();
        // BaseMain mainObject = (BaseMain) Class.forName(
        // this.getMainService().getMainObject().getClass().getName()).newInstance();
        String sendUserName = "";
        String sendDeptName = "";
        String sendRoleName = "";
        if (sessionform != null) {
            mainObject.setSendUserId(sessionform.getUserid());
            mainObject.setSendDeptId(sessionform.getDeptid());
            mainObject.setSendRoleId(StaticMethod.nullObject2String(sessionform
                    .getRoleid()));
            mainObject.setSendOrgType(UIConstants.NODETYPE_SUBROLE);
            mainObject.setSendContact(sessionform.getContactMobile());
            sendUserName = sessionform.getUsername();
            sendDeptName = sessionform.getDeptname();
            sendRoleName = sessionform.getRolename();
        }
        String acceptLimit = StaticMethod.getLocalString(1);
        String completeLimit = StaticMethod.getLocalString(3);
        mainObject.setSheetAcceptLimit(SheetUtils.stringToDate(acceptLimit));
        mainObject.setSheetCompleteLimit(SheetUtils.stringToDate(completeLimit));
        mainObject.setSendTime(StaticMethod.getLocalTime());

        String sheetPageName = StaticMethod.nullObject2String(request
                .getParameter("sheetPageName"), "");
        if (!sheetPageName.equals("") && sheetPageName.indexOf(".") == -1) {
            sheetPageName = sheetPageName + ".";
        }
        String parentCorrelation = StaticMethod.nullObject2String(request
                .getParameter("parentCorrelation"), "");
        String parentSheetId = StaticMethod.nullObject2String(request
                .getParameter("parentSheetId"), "");
        String parentSheetName = StaticMethod.nullObject2String(request
                .getParameter("parentSheetName"), "");
        String parentPhaseName = StaticMethod.nullObject2String(request
                .getParameter("parentPhaseName"), "");
        String invokeMode = StaticMethod.nullObject2String(request
                .getParameter("invokeMode"), "");

        TimeAndDateDeial timeDate = new TimeAndDateDeial();
        /*判断给定受理时间 处理时间 初审结束时间*/
        mainObject.setSheetAcceptLimit(timeDate.addDateByHour(4));
        mainObject.setSheetCompleteLimit(timeDate.addDateByHour(6));
        mainObject.setMainProcessTime(timeDate.addDateByHour(4));
        System.out.println("==============>getMainProcessTime" + mainObject.getMainProcessTime());

        mainObject.setParentCorrelation(parentCorrelation);
        mainObject.setParentSheetId(parentSheetId);
        mainObject.setParentSheetName(parentSheetName);
        mainObject.setParentPhaseName(parentPhaseName);
        mainObject.setInvokeMode(invokeMode);

        /*获取核减报表传来的数据*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String city = StaticMethod.nullObject2String(request.getParameter("city"));
        String subtractName = StaticMethod.nullObject2String(request.getParameter("subtractName"));
        String subtractMajor = StaticMethod.nullObject2String(request.getParameter("subtractMajor"));
        String applyTime = StaticMethod.nullObject2String(request.getParameter("applyTime"));
        if (!"".equals(applyTime)) {
            mainObject.setMainSubtractTime(sdf.parse(applyTime)); /*核减时间*/
        } else {
            mainObject.setMainSubtractTime(new Date()); /*核减时间 接口没传值取当前时间*/
        }

        mainObject.setMainSubtractIndexName(subtractName);
        mainObject.setMainSubtractProfessional(subtractMajor);

        String invokerId = StaticMethod.nullObject2String(request.getParameter("invokerId"), "");

        if (!invokerId.equals("")) {
            String invokerObject = StaticMethod.nullObject2String(request
                    .getParameter("invokerObject"), "");
            IBaseSheet basesheet = (IBaseSheet) ApplicationContextHolder
                    .getInstance().getBean(invokerObject);
            BaseMain baseMain = basesheet.getMainService().getSingleMainPO(
                    invokerId);
            if (baseMain != null) {
                Pojo2PojoServiceImpl p2pService = (Pojo2PojoServiceImpl) ApplicationContextHolder
                        .getInstance().getBean("p2pService");
                p2pService.p2p(baseMain, mainObject);
            }
        }
        if (!parentSheetId.equals("") && !parentSheetName.equals("")) {
            IMainService parentMainSerivce = (IMainService) ApplicationContextHolder
                    .getInstance().getBean(parentSheetName);
            BaseMain parentMain = parentMainSerivce.getSingleMainPO(parentSheetId);
            String tmpparentSheetId = parentMain.getSheetId();

            ITawSystemWorkflowManager mgr = (ITawSystemWorkflowManager) ApplicationContextHolder
                    .getInstance().getBean("ITawSystemWorkflowManager");
            TawSystemWorkflow workflow = mgr
                    .getTawSystemWorkflowByBeanId(parentSheetName);
            String parentProcessName = workflow.getName();

            request.setAttribute("parentSheetId", tmpparentSheetId);
            request.setAttribute("parentProcessName", parentProcessName);

            System.out.println("parentSheetId=" + parentSheetId
                    + "parentProcessName=" + parentProcessName);
        }

//		核减bylyg start
        String newFlag = StaticMethod.nullObject2String(request
                .getParameter("newFlag"), "");
        request.setAttribute("newFlag", newFlag);
        IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        String sql = "select areaid keyId,areaname keyName from taw_system_area where parentareaid='18' AND areaid NOT IN ('1817','1818') order by areaid";
        List cityList = sqlMgr.getSheetAccessoriesList(sql);
        request.setAttribute("cityList", cityList);

        //核减bylyg start

        request.setAttribute("sendUserName", sendUserName);
        request.setAttribute("sendDeptName", sendDeptName);
        request.setAttribute("sendRoleName", sendRoleName);
        request.setAttribute("sheetMain", mainObject);
        request.setAttribute("sheetPageName", sheetPageName);
        request.setAttribute("status", Constants.SHEET_RUN);
        request.setAttribute("sendOrgType", UIConstants.NODETYPE_SUBROLE);
        request.setAttribute("methodBeanId", mapping.getAttribute());

    }


    public void performAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.performAdd(mapping, form, request, response);
        HashMap sheetMap = this.getFlowEngineMap();

        Map main = (HashMap) sheetMap.get("main");
        ISubtractTableMgr subMgr = (ISubtractTableMgr) ApplicationContextHolder
                .getInstance().getBean("subtractTableMgr");
        //派单成功标志ReserveF 为 “1”    "0".equals(operateType) ||  "3".equals(operateType)
        if (!"".equals(main.get("sheetId"))) {
            String condition = " where reserveB = '" + StaticMethod.nullObject2String(main.get("mainReserveA")) + "' ";
            System.out.println("condition:" + condition);
            List vulInfoList = subMgr.getSubtractTablesByCondition(condition);
            SubtractTable vulInfo = null;
            for (int i = 0; vulInfoList != null && i < vulInfoList.size(); i++) {
                vulInfo = (SubtractTable) vulInfoList.get(i);
                vulInfo.setReserveF("1");
                System.out.println("@@gengxinshuju");
                subMgr.saveSubtractTable(vulInfo);
            }
        }
    }

}