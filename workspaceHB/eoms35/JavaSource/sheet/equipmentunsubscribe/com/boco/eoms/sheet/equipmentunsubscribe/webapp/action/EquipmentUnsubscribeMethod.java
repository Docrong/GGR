package com.boco.eoms.sheet.equipmentunsubscribe.webapp.action;

import java.util.ArrayList;
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
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.equipmentinstallation.model.EquipmentInstallationLink;
import com.boco.eoms.sheet.equipmentunsubscribe.model.EquipmentUnsubscribeLink;
import com.boco.eoms.sheet.equipmentunsubscribe.service.IEquipmentUnsubscribeLinkManager;

/**
 * <p>
 * Title:设备退订流程
 * </p>
 * <p>
 * Description:设备退订流程
 * </p>
 * <p>
 * Tue Oct 09 14:24:25 GMT+08:00 2018
 * </p>
 *
 * @author lyg
 * @version 3.6
 */

public class EquipmentUnsubscribeMethod extends BaseSheet {

    public String getPageColumnName() {

        return super.getPageColumnName() + "gatherPerformer@java.lang.String,gatherPerformerLeader@java.lang.String,"
                + "gatherPerformerType@java.lang.String,gatherPhaseId@java.lang.String,";

    }

    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HashMap hashMap = new HashMap();

        HashMap sheetMap = new HashMap();
        String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
        BaseMain main = (BaseMain) this.getMainService().getMainObject().getClass().newInstance();
        if (!sheetKey.equals("")) {
            sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));

        }
        if (!sheetKey.equals("")) {
            main = this.getMainService().getSingleMainPO(sheetKey);
        }
        sheetMap.put("main", main);
        sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
        sheetMap.put("operate", getPageColumnName());
        hashMap.put("selfSheet", sheetMap);

        return hashMap;
    }

    /**
     * 提交流程引擎前作最后一次参数处理
     */
    public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.dealFlowEngineMap(mapping, form, request, response);

        String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
        //String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        //String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
        HashMap sheetMap = this.getFlowEngineMap();

        //Map main = (HashMap) sheetMap.get("main");
        Map operate = (HashMap) sheetMap.get("operate");
        Map link = (HashMap) sheetMap.get("link");

        String[] dealperformers = (StaticMethod.nullObject2String(operate.get("dealPerformer"))).split(",");

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

        sheetMap.put("link", link);
        sheetMap.put("operate", operate);

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
        mainAttachmentAttributes.add("mainSignPhoto");

        List linkAttachmentAttributes = new ArrayList();
        linkAttachmentAttributes.add("nodeAccessories");

        linkAttachmentAttributes.add("linkScenePhoto");
        linkAttachmentAttributes.add("linkPutPhoto");
        linkAttachmentAttributes.add("linkReturnPhoto");

        objectMap.put("mainObject", mainAttachmentAttributes);
        objectMap.put("linkObject", linkAttachmentAttributes);
        return objectMap;
    }

    /**
     * 进入处理环节
     */
    public void showInputDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.showInputDealPage(mapping, form, request, response);
        String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"));
        //驳回上一级（不是移交的任务），需要取出上一级的角色和phaseId
        if (operateType.equals("4")) {
            BaseLink prelink = this.getLinkService().getSingleLinkPO(preLinkId);
            if (prelink != null) {
                request.setAttribute("fOperateroleid", prelink.getOperateRoleId());
                request.setAttribute("fPreTaskName", prelink.getActiveTemplateId());
            }
        }
        //如果是移交后的任务被驳回
        if (operateType.equals("4")) {
            String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
            IEquipmentUnsubscribeLinkManager service = (IEquipmentUnsubscribeLinkManager) ApplicationContextHolder.getInstance().getBean("iEquipmentUnsubscribeLinkManager");
            String taskId = StaticMethod.nullObject2String(request.getParameter("taskId"));
            String condition = " mainId='" + sheetKey + "' and operateType=8 and aiid='" + taskId + "' order by operateTime desc";
            List linkList = service.getLinksBycondition(condition);
            if (linkList != null && linkList.size() > 0) {
                BaseLink prelink = (BaseLink) linkList.get(0);
                request.setAttribute("fOperateroleid", prelink.getOperateRoleId());
                request.setAttribute("fPreTaskName", prelink.getActiveTemplateId());
            }
        }

    }

    public void showInputNewSheetPage(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
        String sql = "select areaid keyId,areaname keyName from taw_system_area where parentareaid='18' AND areaid NOT IN ('1817','1818') order by areaid";
        List cityList = sqlMgr.getSheetAccessoriesList(sql);
        request.setAttribute("cityList", cityList);
        super.showInputNewSheetPage(mapping, form, request, response);
    }


    public void showSheetDealList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		/*String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"), "");	
		String ifWaitForSubTask = StaticMethod.nullObject2String(request
				.getParameter("ifWaitForSubTask"), "");			
		if (!sheetKey.equals("")) {
			BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
			List list = getLinkService().getLinksByMainId(sheetKey);
			request.setAttribute("HISTORY", list);
			request.setAttribute("taskName", taskName);
			request.setAttribute("ifWaitForSubTask", ifWaitForSubTask);
			request.setAttribute("sheetMain", main);
		}
		BaseLink link = this.getLinkService().getLinkObject();
		request.setAttribute("linkClassName", link);*/

        String sheetKey = StaticMethod.nullObject2String(request
                .getParameter("sheetKey"), "");
        String taskName = StaticMethod.nullObject2String(request
                .getParameter("taskName"), "");
        String ifWaitForSubTask = StaticMethod.nullObject2String(request
                .getParameter("ifWaitForSubTask"), "");
        if (!sheetKey.equals("")) {
            BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
            List list = getLinkService().getLinksByMainId(sheetKey);

            String orderByDetp = StaticMethod.nullObject2String(request
                    .getParameter("orderByDetp"), "");
            if (orderByDetp.equals("true")) {
                //历史列表按部门排列
                Map deptIdMap = new HashMap();
                List deptIdarray = new ArrayList();
                Map numberMap = new HashMap();
                int i = 0;
                //循环把部门的ID归类
                for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                    EquipmentInstallationLink baseLink = (EquipmentInstallationLink) iterator.next();
                    i++;
                    numberMap.put(baseLink.getId(), new Integer(i));

                    if (deptIdMap.get(baseLink.getOperateDeptId()) == null) {
                        List links = new ArrayList();
                        links.add(baseLink);
                        deptIdarray.add(baseLink.getOperateDeptId());
                        deptIdMap.put(baseLink.getOperateDeptId(), links);
                    } else {
                        List links = (List) deptIdMap.get(baseLink.getOperateDeptId());
                        links.add(baseLink);
                    }

                }

                request.setAttribute("numberMap", numberMap);
                request.setAttribute("deptIdtable", deptIdarray);
                request.setAttribute("deptIdMap", deptIdMap);
            }
            Map mapacc = new HashMap();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    EquipmentUnsubscribeLink baseLink = (EquipmentUnsubscribeLink) list.get(i);
//					将附件中文名称循环
//					linkEquipmentAgree,linkUserReceipt,linkInformation,linkScenePhoto,linkChargePhoto
                    String linkScenePhoto = baseLink.getLinkScenePhoto();
                    String scenePhoto = getAccessoriesCNName(linkScenePhoto);
                    mapacc.put(linkScenePhoto, scenePhoto);

                    String linkPutPhoto = baseLink.getLinkPutPhoto();
                    String putPhoto = getAccessoriesCNName(linkPutPhoto);
                    mapacc.put(linkPutPhoto, putPhoto);

                    String linkReturnPhoto = baseLink.getLinkReturnPhoto();
                    String returnPhoto = getAccessoriesCNName(linkReturnPhoto);
                    mapacc.put(linkReturnPhoto, returnPhoto);


                }
            }
            request.setAttribute("mapacc", mapacc);
            request.setAttribute("HISTORY", list);
            request.setAttribute("taskName", taskName);
            request.setAttribute("ifWaitForSubTask", ifWaitForSubTask);
            request.setAttribute("sheetMain", main);
        }
        BaseLink link = this.getLinkService().getLinkObject();
        request.setAttribute("linkClassName", link);
    }

    /**
     * 根据附件id得到附件的中文名称与id
     *
     * @param accessId
     * @return 殷毅婷.txt#297e5899667a7dff01667fe531160069$网优告警ID.xls#297e5899667a7dff01667fe5592f006a
     * @throws Exception
     */
    public String getAccessoriesCNName(String accessId) throws Exception {
        String accessoriesCNNameAndId = "";
        if (accessId != null && !"".equals(accessId)) {
            List attachments = this.getMainService().getAllAttachmentsBySheet(accessId);
            if (attachments != null && attachments.size() > 0) {
                for (int i = 0; i < attachments.size(); i++) {
                    TawCommonsAccessories tawCommonsAccessories = (TawCommonsAccessories) attachments.get(i);
                    String accessoriesCNName = tawCommonsAccessories.getAccessoriesCnName();
                    String id = tawCommonsAccessories.getId();
                    String temStr = accessoriesCNName + "#" + id;
                    accessoriesCNNameAndId = accessoriesCNNameAndId + "&" + temStr;
                    System.out.println("accessoriesCNNameAndId1==" + accessoriesCNNameAndId);
                }
                if (!"".equals(accessoriesCNNameAndId)) {
                    accessoriesCNNameAndId = accessoriesCNNameAndId.substring(1, accessoriesCNNameAndId.length());
                    System.out.println("accessoriesCNNameAndId2==" + accessoriesCNNameAndId);
                }
            }
        }
        return accessoriesCNNameAndId;
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
        String operateType = StaticMethod.nullObject2String(request
                .getParameter("operateType"));

        if (operateType.equals("0") || operateType.equals("3") || operateType.equals("54")) {
            String text = "";
            String status = "";
            String mainMINIID = StaticMethod.nullObject2String(request.getParameter("mainMINIID"));
            IDownLoadSheetAccessoriesService sqlMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
            String sql = "SELECT * FROM equipmentinstallation_main WHERE sheetid = '" + mainMINIID + "' AND deleted = '0'";
            List sqlList = sqlMgr.getSheetAccessoriesList(sql);
            if (sqlList == null || sqlList.size() == 0) {
                text = "在安装流程中找到所填写的 MINI设备安装ID ，请确认后重新输入！";
                status = "2";
            }

            JSONArray data = new JSONArray();
            JSONObject o = new JSONObject();
            o.put("text", text);
            data.put(o);
            JSONObject jsonRoot = new JSONObject();
            jsonRoot.put("data", data);
            jsonRoot.put("status", status);
            JSONUtil.print(response, jsonRoot.toString());
        } else {
            super.performPreCommit(mapping, form, request, response);
        }


    }

}