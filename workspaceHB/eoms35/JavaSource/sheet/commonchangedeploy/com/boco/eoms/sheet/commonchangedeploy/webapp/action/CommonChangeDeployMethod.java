package com.boco.eoms.sheet.commonchangedeploy.webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;

/**
 * <p>
 * Title:通用变更配置工单
 * </p>
 * <p>
 * Description:通用变更配置工单
 * </p>
 * <p>
 * Fri Jun 12 09:08:01 CST 2009
 * </p>
 *
 * @author yangwei
 * @version 3.5
 */

public class CommonChangeDeployMethod extends BaseSheet {

    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        HashMap hashMap = new HashMap();
        String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
        String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
        BocoLog.debug(this, "operateName is -----------------------" + operatName);

        try {
            //强制归档
            if (operatName.equals("forceHold")) {
                try {
                    HashMap map = new HashMap();
                    String sheetKey = StaticMethod.nullObject2String(request.getParameter("id"));
                    if (sheetKey == null || sheetKey.equals("")) {
                        sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
                    }

                    BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                    map.put("main", main);
                    map.put("link", getLinkService().getLinkObject().getClass().newInstance());
                    map.put("operate", getPageColumnName());

                    hashMap.put("selfSheet", map);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //新建环节
            } else if (taskName.equals("")) {
                HashMap sheetMap = new HashMap();

                String sheetKey = StaticMethod.nullObject2String(request.getParameter("id"));
                if (sheetKey == null || sheetKey.equals("")) {
                    sheetMap.put("main", (BaseMain) getMainService().getMainObject().getClass().newInstance());
                } else {
                    BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                    sheetMap.put("main", main);
                }
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());

                hashMap.put("selfSheet", sheetMap);

                //待审批
            } else if (taskName.equals("AuditTask")) {
                String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
                if (sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
                }

                HashMap sheetMap = new HashMap();
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                sheetMap.put("main", main);
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());

                hashMap.put("selfSheet", sheetMap);

                //排程中
            } else if (taskName.equals("PlanTask")) {
                String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
                if (sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
                }

                HashMap sheetMap = new HashMap();
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                sheetMap.put("main", main);
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());

                hashMap.put("selfSheet", sheetMap);

                //实施中
            } else if (taskName.equals("ImplementTask")) {
                String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
                if (sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
                }

                HashMap sheetMap = new HashMap();
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                sheetMap.put("main", main);
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());

                hashMap.put("selfSheet", sheetMap);

                //归档
            } else if (taskName.equals("HoldTask")) {
                String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
                if (sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
                }

                HashMap sheetMap = new HashMap();
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                sheetMap.put("main", main);
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());

                hashMap.put("selfSheet", sheetMap);

                //其它
            } else {
                String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
                if (sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
                }

                HashMap sheetMap = new HashMap();
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                sheetMap.put("main", main);
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }


    public void dealFlowEngineMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        super.dealFlowEngineMap(mapping, form, request, response);
        String roleId = StaticMethod.nullObject2String(request.getParameter("roleId"));
        String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        HashMap sheetMap = this.getFlowEngineMap();
        if (taskName.equals("reply") || taskName.equals("advice")) {
            Map link = (HashMap) sheetMap.get("link");
            link.put("id", "");
            sheetMap.put("link", link);

        }

        //     如果是审批不通过则将驳回次数加1
        if (taskName.equals("AuditSummaryTask") && operateType.equals("952")) {
            Map main = (HashMap) sheetMap.get("main");
            Integer mainRejectTimes = (Integer) main.get("mainRejectTimes");
            mainRejectTimes = new Integer(mainRejectTimes.intValue() + 1);
            main.put("mainRejectTimes", mainRejectTimes);
            sheetMap.put("main", main);
            this.setFlowEngineMap(sheetMap);
        }

        Map operate = (HashMap) sheetMap.get("operate");
        String[] dealperformers = (StaticMethod.nullObject2String(operate.get("dealPerformer"))).split(",");
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
            this.setFlowEngineMap(sheetMap);

            //设置新增时候一派多的聚合点
            operate.put("extendKey2", "HoldTask");

        }
    }


    /**
     * 设置需要从流程引擎中取的派往对象，包括派发，抄送，送审
     */
    public Map getProcessOjbectAttribute() {
        Map attributeMap = new HashMap();
        attributeMap.put("dealPerformer", "dealPerformer");
        attributeMap.put("copyPerformer", "copyPerformer");
        attributeMap.put("audit", "audit");
        attributeMap.put("objectName", "operate");
        return attributeMap;
    }

    /**
     * 设置main表保存的派往对角
     */
    public Map getMainSendObject() {
        Map attributeMap = new HashMap();
        attributeMap.put("sendObject", "sendObject");
        return attributeMap;
    }

    /**
     * 设置link表保存的派往对象
     */
    public Map getLinkSendObject() {
        Map attributeMap = new HashMap();
        attributeMap.put("sendObject", "sendObject");
        return attributeMap;
    }

    /**
     * 设置main和link保存附件字段属性
     */
    public Map getAttachmentAttributeOfOjbect() {
        Map objectMap = new HashMap();

        List mainAttachmentAttributes = new ArrayList();
        mainAttachmentAttributes.add("mainProjectAccessories");

        List linkAttachmentAttributes = new ArrayList();
        linkAttachmentAttributes.add("linkExecuteAccessories");
        linkAttachmentAttributes.add("linkTestReport");

        objectMap.put("mainObject", mainAttachmentAttributes);
        objectMap.put("linkObject", linkAttachmentAttributes);
        return objectMap;
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
    public void showForceHoldPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.showForceHoldPage(mapping, form, request, response);

        //需要回调外部流程
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
        BaseMain main = this.getMainService().getSingleMainPO(sheetKey);

        String parentSheetName = main.getParentSheetName();
        String parentSheetKey = main.getParentSheetId();

        if (parentSheetName != null && !parentSheetName.equals("")) {
            IMainService parentMainService = (IMainService) ApplicationContextHolder.getInstance().getBean(parentSheetName);
            BaseMain parentMain = parentMainService.getSingleMainPO(parentSheetKey);
            String parentPhaseName = main.getParentPhaseName();

            if (parentPhaseName.indexOf("@") != -1) {
                request.setAttribute("parentPiid", parentPhaseName.substring(parentPhaseName.indexOf("@") + 1));
                BocoLog.debug(this, "回调：parentProcessId：" + parentPhaseName.substring(parentPhaseName.indexOf("@") + 1));
            } else {
                request.setAttribute("parentPiid", parentMain.getPiid());
            }
            request.setAttribute("parentMain", parentMain);
            request.setAttribute("parentProcessName", parentSheetName);
        }
    }

}