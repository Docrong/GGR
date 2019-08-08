package com.boco.eoms.sheet.businessimplementsms.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.boco.eoms.businessupport.order.model.OrderSheet;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.serviceprepare.mgr.ServicePrepareMgr;
import com.boco.eoms.businessupport.serviceprepare.model.ProcessTasks;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.businessimplementsms.model.BusinessImplementSmsMain;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;

public class BusinessImplementSmsMethod extends BaseSheet {
    public String getPageColumnName() {

        return super.getPageColumnName()
                + ",toMorePhaseId@java.lang.String,supplier1Performer@java.lang.String,supplier1PerformerLeader@java.lang.String,"
                + "supplier1PerformerType@java.lang.String,supplier2Performer@java.lang.String,supplier2PerformerLeader@java.lang.String,supplier2PerformerType@java.lang.String,"
                + "gatherPhaseId@java.lang.String,gatherDealPerformer@java.lang.String,gatherDealPerformerLeader@java.lang.String,gatherDealPerformerType@java.lang.String,";
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getInterfaceObjMap(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
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

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#dealFlowEngineMap(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.dealFlowEngineMap(mapping, form, request, response);
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        String phaseId = StaticMethod.nullObject2String(request
                .getParameter("phaseId"));
        HashMap sheetMap = this.getFlowEngineMap();

        Map mainMap = (HashMap) sheetMap.get("main");
        Map linkMap = (HashMap) sheetMap.get("link");
        Map operate = (HashMap) sheetMap.get("operate");

        String[] dealperformers = (StaticMethod.nullObject2String(operate
                .get("dealPerformer"))).split(",");
        String[] toMorePhaseIds = (StaticMethod.nullObject2String(operate
                .get("toMorePhaseId"))).split(",");

        if (taskName.equals("reply") || taskName.equals("advice")) {
            Map link = (HashMap) sheetMap.get("link");
            link.put("id", "");
            sheetMap.put("link", link);
        }

        if (dealperformers.length > 1) {

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

        if (operateType.equals("0")) {
            String orderId = StaticMethod.nullObject2String(request.getParameter("orderId"));
            if (orderId.length() > 0) {

                OrderSheet order = new OrderSheet();
                SheetBeanUtils.populateMap2Bean(order, request.getParameterMap());
                order.setId(orderId);
                order.setOrderBuisnessType(StaticMethod.nullObject2String(mainMap.get("mainSpecifyType")));
                order.setUrgentDegree(StaticMethod.nullObject2String(mainMap.get("mainArgument")));
                order.setCreatTime(new Date());
                order.setOrderType("1040104");

                IOrderSheetManager mgr = (IOrderSheetManager) ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
                mgr.saveOrUpdate(order);

                mainMap.put("orderSheetId", orderId);
            }
        }

        if (toMorePhaseIds.length > 0) {

            String tmp = "";
            for (int i = 0; i < toMorePhaseIds.length; i++) {
                tmp = UUIDHexGenerator.getInstance().getID();
                if (toMorePhaseIds[i].equals("NetMakeTask")) {
                    operate.put("supplier1CorrKey", tmp);
                } else if (toMorePhaseIds[i].equals("SupportMakeTask")) {
                    operate.put("supplier2CorrKey", tmp);
                }
                BocoLog.info(this, "=======tmp=CorrKey==" + tmp);
            }
        }
        if (toMorePhaseIds.length > 0) {

            String tmp = "";
            for (int i = 0; i < toMorePhaseIds.length; i++) {
                tmp = UUIDHexGenerator.getInstance().getID();
                if (toMorePhaseIds[i].equals("NetMakeTask")) {
                    operate.put("supplier1CorrKey", tmp);
                } else if (toMorePhaseIds[i].equals("SupportMakeTask")) {
                    operate.put("supplier2CorrKey", tmp);
                }

                System.out.println("=======tmp=CorrKey==" + tmp);
            }
        }

        System.out.println("main=" + mainMap);
        if (mainMap != null && StaticMethod.nullObject2String(mainMap.get("mainSendSheetModule")).equalsIgnoreCase("1")) {
            String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessImplementSms-crm.xml").getProperty("base.SendImmediately"));
            if (!sendImmediately.equalsIgnoreCase("true")) {
                if (taskName.equals("AuditTask") && operateType.equals("4")) {//驳回
                    operate.put("interfaceType", "withdrawWorkSheet");
                    operate.put("methodType", "withdrawWorkSheet");
                } else if (operateType.equals("9")) {//阶段回复
                    operate.put("interfaceType", "notifyWorkSheet");
                    operate.put("methodType", "notifyWorkSheet");
                } else if (taskName.equals("AuditTask") && operateType.equals("61")) {// 受理
                    operate.put("interfaceType", "confirmWorkSheet");
                    operate.put("methodType", "confirmWorkSheet");
                } else if (operateType.equals("213")) {// 回复
                    operate.put("interfaceType", "replyWorkSheet");
                    operate.put("methodType", "replyWorkSheet");
                }
            }
        }

        sheetMap.put("link", linkMap);
        sheetMap.put("main", mainMap);
        sheetMap.put("operate", operate);
        this.setFlowEngineMap(sheetMap);

    }

    public Map getProcessOjbectAttribute() {
        Map attributeMap = new HashMap();
        attributeMap.put("dealPerformer", "dealPerformer");
        attributeMap.put("copyPerformer", "copyPerformer");
        attributeMap.put("auditPerformer", "auditPerformer");
        attributeMap.put("objectName", "operate");
        return attributeMap;
    }

    public Map getParameterMap() {
        // TODO Auto-generated method stub
        return this.getParameterMap();
    }

    public Map getAttachmentAttributeOfOjbect() {
        Map objectMap = new HashMap();

        List mainAttachmentAttributes = new ArrayList();
        mainAttachmentAttributes.add("sheetAccessories");

        List linkAttachmentAttributes = new ArrayList();
        linkAttachmentAttributes.add("nodeAccessories");
        //linkAttachmentAttributes.add("linkProgramTopology");
        objectMap.put("mainObject", mainAttachmentAttributes);
        objectMap.put("linkObject", linkAttachmentAttributes);
        return objectMap;
    }

    public void showInputDealPage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputDealPage(mapping, form, request, response);

        // 需要调用外部流程
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("taskName"));
        if (taskName.equals("TranferTask")) {
            String sheetKey = StaticMethod.nullObject2String(request
                    .getParameter("sheetKey"));
            ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
                    .getInstance().getBean("ITawSheetRelationManager");
            List relationAllList = rmgr.getAllSheetByParentIdAndPhaseId(
                    sheetKey, taskName);
            if (relationAllList != null) {
                for (int i = 0; i < relationAllList.size(); i++) {
                    TawSheetRelation relation = (TawSheetRelation) relationAllList
                            .get(i);
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

        if (taskName.equals("TranferTask") && operateType.equals("111")) {
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

    public String getProcessTemplateName() {
        // TODO Auto-generated method stub
        return "BusinessImplementSmsProcess";
    }

    public String getSheetAttachCode() {
        // TODO Auto-generated method stub
        return "businessimplementsms";
    }

    public Map initMap(Map map, List attach, String type) throws Exception {
        return null;
    }


    /*
     * 获取与工单相关的定单信息，呈现在工单的detail页面上 modify by shichuangke
     */
    public void showDetailPage(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        super.showDetailPage(mapping, form, request, response);
        String preLinkId = StaticMethod.nullObject2String(request
                .getParameter("preLinkId"), "");
        if (!preLinkId.equals("")) {
            request.setAttribute("preLink", this.getLinkService()
                    .getSingleLinkPO(preLinkId));
        }
        String sheetKey = StaticMethod.nullObject2String(request
                .getParameter("sheetKey"), "");
        if (!sheetKey.equals("")) {
            // 根据sheetKey得到工单main对象，取出ordersheetid，查找定单信息
            BusinessImplementSmsMain BusinessImplmentSmsMain = (BusinessImplementSmsMain) this
                    .getMainService().getSingleMainPO(sheetKey);
            String orderSheetId = BusinessImplmentSmsMain.getOrderSheetId();
            if (orderSheetId != null && !orderSheetId.equals("")) {
                IOrderSheetManager iOrderSheetManager = (IOrderSheetManager) ApplicationContextHolder
                        .getInstance().getBean("IOrderSheetManager");
                OrderSheet orderSheet = iOrderSheetManager
                        .getOrderSheet(orderSheetId);
                request.setAttribute("orderSheet", orderSheet);
            }
        }

    }

    /*
     * 获取与工单相关的定单信息，呈现在工单的detail页面上 modify by shichuangke
     */
    public void showMainDetailPage(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO Auto-generated method stub
        super.showMainDetailPage(mapping, form, request, response);
        String preLinkId = StaticMethod.nullObject2String(request
                .getParameter("preLinkId"), "");
        if (!preLinkId.equals("")) {
            request.setAttribute("preLink", this.getLinkService()
                    .getSingleLinkPO(preLinkId));
        }
        String sheetKey = StaticMethod.nullObject2String(request
                .getParameter("sheetKey"), "");
        if (!sheetKey.equals("")) {
            // 根据sheetKey得到工单main对象，取出ordersheetid，查找定单信息
            BusinessImplementSmsMain BusinessImplmentSmsMain = (BusinessImplementSmsMain) this
                    .getMainService().getSingleMainPO(sheetKey);
            String orderSheetId = BusinessImplmentSmsMain.getOrderSheetId();
            if (orderSheetId != null && !orderSheetId.equals("")) {
                IOrderSheetManager iOrderSheetManager = (IOrderSheetManager) ApplicationContextHolder
                        .getInstance().getBean("IOrderSheetManager");
                OrderSheet orderSheet = iOrderSheetManager
                        .getOrderSheet(orderSheetId);
                request.setAttribute("orderSheet", orderSheet);
                request.setAttribute("orderSheetId", orderSheetId);
            }
        }
    }

}
