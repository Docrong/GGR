
package com.boco.eoms.sheet.safeaudit.webapp.action;

import java.util.ArrayList;
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
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;

public class SafeAuditMethod extends BaseSheet {
    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getInterfaceObjMap(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        HashMap hashMap = new HashMap();
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
        System.out.println("operateName is -----------------------" + operatName);
        try {
            if (operatName.equals("forceHold")) {
                HashMap map = new HashMap();
                String sheetKey = StaticMethod.nullObject2String(request.getParameter("id"));
                if (sheetKey.equals("")) {
                    sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
                }
                BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                map.put("main", main);
                map.put("link", getLinkService().getLinkObject());
                map.put("operate", getPageColumnName());
                hashMap.put("selfSheet", map);
            } else if (taskName.equals("")) {
                //新增工单
                HashMap sheetMap = new HashMap();
                sheetMap.put("main", getMainService().getMainObject().getClass().newInstance());
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);
            } else if (taskName.equals("DraftHumTask")) {
                //草稿状态
                try {
                    HashMap sheetMap = new HashMap();
                    String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
                    System.out.println("task is=============" + sheetKey);
                    if (sheetKey.equals("")) {
                        sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
                    }
                    BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                    System.out.println("main is=============" + main);
                    sheetMap.put("main", main);
                    sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                    sheetMap.put("operate", getPageColumnName());
                    hashMap.put("selfSheet", sheetMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (taskName.equals("HoldHumTask")) {
                //待归档
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
            } else if (taskName.equals("cc") || taskName.equals("advice") || taskName.equals("reply")) {
                HashMap sheetMap = new HashMap();
                String ifNeedMain = StaticMethod.nullObject2String(request
                        .getParameter("ifNeedMain"));
                if (ifNeedMain.equals("true")) {
                    String sheetKey = StaticMethod.nullObject2String(request
                            .getParameter("mainId"));
                    if (sheetKey == null || sheetKey.equals("")) {
                        sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
                    }
                    System.out.println("=======sheetKey========" + sheetKey);
                    BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
                    sheetMap.put("main", main);
                }
                sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
                sheetMap.put("operate", getPageColumnName());
                hashMap.put("selfSheet", sheetMap);
            } else {
                System.out.println("其他人工处理.......");
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
        String roleId = StaticMethod.nullObject2String(request
                .getParameter("roleId"));
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        String phaseId = StaticMethod.nullObject2String(request
                .getParameter("phaseId"));
        HashMap sheetMap = this.getFlowEngineMap();
        if (taskName.equals("reply") || taskName.equals("advice")) {
            Map link = (HashMap) sheetMap.get("link");
            link.put("id", "");
            sheetMap.put("link", link);
        }

        this.setFlowEngineMap(sheetMap);
    }

    public Map getParameterMap() {
        // TODO Auto-generated method stub
        return this.getParameterMap();
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


    public void showForceHoldPage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showForceHoldPage(mapping, form, request, response);
        //需要回调外部流程
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
        }
    }


    public void showInputDealPage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputDealPage(mapping, form, request, response);

        // add by yangyankuang
        String operateRoleId = StaticMethod.nullObject2String(request
                .getParameter("operateRoleId"));

        TawSystemSessionForm sessionform = (TawSystemSessionForm) request
                .getSession().getAttribute("sessionform");
        ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemSubRoleManager");
        List subRoleList = new ArrayList();
        int listLength = subRoleList.size();
        long roleId = 0;
        System.out.println("===operateRoleId====" + operateRoleId);

        TawSystemSubRole subrole = mgr.getTawSystemSubRole(operateRoleId);

        request.setAttribute("operateRoleId", operateRoleId);
        if (subrole != null) {
            request.setAttribute("roleId", subrole.getRoleId() + "");
        }
        request.setAttribute("operateDeptId", sessionform.getDeptid());

        /*
         * add by panlong
         */
        // 取上条TASK
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("activeTemplateId"));
        if (taskName.equals("")) {
            taskName = StaticMethod.nullObject2String(request
                    .getParameter("taskName"));
        }

        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));
        if (taskName.equals("EvaluateMaterialHumTask") || taskName.equals("EditReportHumTask") || taskName.equals("ApprovingHumTask")) {
            super.setParentTaskOperateWhenRejct(request);
        }
		/*if(taskName.equals("HoldHumTask")){
			String  sheetKey  =  StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			ITawSheetRelationManager  rmgr  =  (ITawSheetRelationManager)  ApplicationContextHolder.getInstance().getBean("ITawSheetRelationManager");
			List  relationAllList=rmgr.getAllSheetByParentIdAndPhaseId(sheetKey,taskName);
			if(relationAllList  !=  null){
			for(int  i=0;i <relationAllList.size();i++){
			TawSheetRelation  relation=(TawSheetRelation)relationAllList.get(i);
			int  state=relation.getInvokeState();
			if(state==Constants.INVOKE_STATE_RUNNING){
			request.setAttribute("ifInvoke",  "no");
			break;
			}
			request.setAttribute("ifInvoke",  "yes");    
			}
			}else{
			request.setAttribute("ifInvoke",  "no");
			}
			}
			*/

    }

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
        if (taskName.equals("HoldHumTask") && operateType.equals("18")) {
            //查询工单互调表
            ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
                    .getInstance().getBean("ITawSheetRelationManager");
            List relationAllList = rmgr.getAllSheetByParentIdAndPhaseId(sheetKey, taskName);
            //如果已经调用了其他工单则继续执行父类的performPreCommit方法,否则返回status为-1
            if (relationAllList != null && relationAllList.size() > 0) {
                try {
                    super.performPreCommit(mapping, form, request, response);
                } catch (RuntimeException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                JSONArray data = new JSONArray();
                JSONObject o = new JSONObject();
                o.put("text", "请调用安全问题处理流程！");
                data.put(o);
                JSONObject jsonRoot = new JSONObject();
                jsonRoot.put("data", data);
                jsonRoot.put("status", "2");
                JSONUtil.print(response, jsonRoot.toString());
            }
        } else {
            try {
                super.performPreCommit(mapping, form, request, response);
            } catch (RuntimeException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
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
