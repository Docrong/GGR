package com.boco.eoms.extra.supplierkpi.webapp.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleManage;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.extra.supplierkpi.model.KPIShowObject;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplateAssess;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiAssessInstanceManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiDictManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInfoManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiItemManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateAssessManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateManager;
import com.boco.eoms.extra.supplierkpi.util.List2JSON;
import com.boco.eoms.extra.supplierkpi.util.SupplierkpiConstants;
import com.boco.eoms.extra.supplierkpi.util.TawSupplierkpiDictUtil;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSupplierkpiItemForm;
import com.boco.eoms.extra.supplierkpi.webapp.util.SupplierkpiAttributes;

public final class TawSupplierkpiItemAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("search");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String dictId = StaticMethod.null2String(request.getParameter("id"));
        ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
        itemMgr.removeDictAndKpiItem(dictId);

        return mapping.findForward("success");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String dictId = request.getParameter("dictId");

        String roleId = ((SupplierkpiAttributes) getBean("supplierkpiAttributes"))
                .getPgAdminRole();
        Hashtable filterHash = new Hashtable();

        // 获取专业类型
        String specialType = dictId;
        ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
        while (templateMgr.getTemplateIdBySpecialType(specialType) == null
                || "".equals(templateMgr
                .getTemplateIdBySpecialType(specialType))) {
            specialType = specialType.substring(0, specialType.length() - TawSupplierkpiDictUtil.DICTID_BETWEEN_LENGTH);
        }
        // 针对二级专业
//		String parentSpecial = specialType.substring(0,
//				specialType.length() - 2);
//		if (templateMgr.getTemplateIdBySpecialType(parentSpecial) == null
//				|| "".equals(templateMgr
//						.getTemplateIdBySpecialType(parentSpecial))) {
//			filterHash.put("class2", specialType);
//		} else {
//			// filterHash.put("class3", specialType);
//			filterHash.put("class2", parentSpecial);
//		}

        filterHash.put("class2", specialType);

        String templateId = templateMgr.getTemplateIdBySpecialType(specialType);
        TawSupplierkpiTemplate template = templateMgr
                .getTawSupplierkpiTemplate(templateId);
        String serviceType = StaticMethod
                .null2String(template.getServiceType());

        List list = RoleManage.getInstance().getSubRoles(roleId, filterHash);

        request.setAttribute("serviceType", serviceType);
        request.setAttribute("subRoles", list);
        request.setAttribute("dictId", dictId);

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String treeRootId = ((SupplierkpiAttributes) getBean("supplierkpiAttributes")).getTreeRootId();
        ITawSupplierkpiDictManager dictMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
        String dictName = request.getParameter("kpiName");
        String dictId = request.getParameter("dictId");
        String parentDictId = dictId;
        // 取得专业类型的方法修改为,从当前字典Id开始,到模型表里面查一下,查不到就把长度减两个再查
        String kpiName = dictName;
        String specialType = dictId;
        ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");

        // kpi项
        TawSupplierkpiItemForm tawSupplierkpiItemForm = (TawSupplierkpiItemForm) form;

        // 获取创建人和创建时间
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String operUser = sessionform.getUsername();
        Timestamp createTime = new Timestamp(System.currentTimeMillis());

        tawSupplierkpiItemForm.setCreator(operUser);
        tawSupplierkpiItemForm.setCreateTime(createTime.toString());

        ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
        TawSupplierkpiItem tawSupplierkpiItem = new TawSupplierkpiItem();
        tawSupplierkpiItem = (TawSupplierkpiItem) convert(tawSupplierkpiItemForm);
        tawSupplierkpiItem.setDeleted(1);
        if (tawSupplierkpiItem.getStatictsCycle() == null) {
            tawSupplierkpiItem.setStatictsCycle("");
        }

        if (tawSupplierkpiItem.getId() == null || "".equals(tawSupplierkpiItem.getId())) {
            while (templateMgr.getTemplateIdBySpecialType(specialType) == null
                    || "".equals(templateMgr.getTemplateIdBySpecialType(specialType))) {
                kpiName = dictMgr.id2Name(specialType) + "-" + kpiName;
                specialType = specialType.substring(0, specialType.length()
                        - TawSupplierkpiDictUtil.DICTID_BETWEEN_LENGTH);
            }
            tawSupplierkpiItem.setKpiName(kpiName);
            String templateId = templateMgr.getTemplateIdBySpecialType(specialType);
            tawSupplierkpiItem.setTemplateId(templateId);

            // add by liqiuye 2008-2-26
            // 判断模型审核状态,如果已审核或者isKpiChanged为1,则新增指标状态置5,否则置1
            ITawSupplierkpiTemplateAssessManager templateAssessMgr = (ITawSupplierkpiTemplateAssessManager) getBean("ItawSupplierkpiTemplateAssessManager");
            TawSupplierkpiTemplateAssess templateAssess = templateAssessMgr.getTawSupplierkpiTemplateAssessByTemplateId(templateId);
            if (templateAssess.getAssessStatus() == 3 || templateAssess.getIsKpiChanged() == 1) {
                tawSupplierkpiItem.setAssessStatus(5);
            } else {
                tawSupplierkpiItem.setAssessStatus(1);
            }

            // 字典
            TawSupplierkpiDict tawSupplierkpiDict = new TawSupplierkpiDict();
            tawSupplierkpiDict.setDictName(dictName);
            tawSupplierkpiDict.setParentDictId(parentDictId);
            tawSupplierkpiDict.setNodeType(TawSupplierkpiDictUtil.NODETYPE_ITEM);

            if (parentDictId == null || parentDictId.equals("") || parentDictId.equals(treeRootId)) {
                String firstdictid = dictMgr.getMaxDictid(treeRootId);
                tawSupplierkpiDict.setParentDictId(treeRootId);
                tawSupplierkpiDict.setLeaf("1");
                tawSupplierkpiDict.setDictId(firstdictid);
            } else {
                String newdictid = dictMgr.getMaxDictid(parentDictId);
                TawSupplierkpiDict dictType = new TawSupplierkpiDict();
                dictType = dictMgr.getDictByDictId(parentDictId);
                if (tawSupplierkpiDict.getDictId() == null || tawSupplierkpiDict.getDictId().equals("")) {
                    tawSupplierkpiDict.setDictId(newdictid);
                }
                dictType.setLeaf("0");
                tawSupplierkpiDict.setLeaf("1");
                dictMgr.saveTawSupplierkpiDict(dictType);
            }

            // 保存字典，根据返回dictId值保存kpi项,并将该KPI所属专业审核状态置1,并将isKpiChanged置1
            String info = itemMgr.saveDictAndKpiItem(tawSupplierkpiDict, tawSupplierkpiItem);
            if (!"".equals(info)) {
                request.setAttribute("failInfo", info);
                return mapping.findForward("fail");
            }
        } else {
            itemMgr.saveTawSupplierkpiItem(tawSupplierkpiItem);
            String fromTemplateView = StaticMethod.null2String(request.getParameter("fromTemplateView"));
            // 如果fromTemplateView标志为1则保存指标后返回模型送审页面
            if ("1".equals(fromTemplateView)) {
                while (templateMgr.getTemplateIdBySpecialType(specialType) == null
                        || "".equals(templateMgr.getTemplateIdBySpecialType(specialType))) {
                    specialType = specialType.substring(0, specialType.length()
                            - TawSupplierkpiDictUtil.DICTID_BETWEEN_LENGTH);
                }
                ActionRedirect redirect = new ActionRedirect(mapping.findForward("backToTemplateView"));
                redirect.addParameter("method", "view");
                redirect.addParameter("specialType", specialType);
                return redirect;
            }
        }

        return mapping.findForward("success");
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                "tawDemoMytableList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE); // ҳ��Ĳ�����,����"tawDemoMytableList"��ҳ����displayTag��id
        final int pageSize = 25; // ÿҳ��ʾ������
        final int pageIndex = GenericValidator.isBlankOrNull(request
                .getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request
                .getParameter(pageIndexName)) - 1); // ��ǰҳ��

        ITawSupplierkpiItemManager mgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
        Map map = (Map) mgr.getTawSupplierkpiItems(pageIndex, pageSize); // map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
        List list = (List) map.get("result");
        request.setAttribute(Constants.TAWSUPPLIERKPIITEM_LIST, list);
        request.setAttribute("resultSize", map.get("total"));

        return mapping.findForward("list");
    }

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response);
    }

    public ActionForward addSpecialKPI(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                "tawDemoMytableList")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE); // ҳ��Ĳ�����,����"tawDemoMytableList"��ҳ����displayTag��id
        final int pageSize = 25; // ÿҳ��ʾ������
        final int pageIndex = GenericValidator.isBlankOrNull(request
                .getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request
                .getParameter(pageIndexName)) - 1); // ��ǰҳ��

        String specialType = request.getParameter("specialType");

        // ITawSupplierkpiTemplateManager templatemgr =
        // (ITawSupplierkpiTemplateManager)
        // getBean("ItawSupplierkpiTemplateManager");
        // TawSupplierkpiTemplate tawSupplierkpiTemplate =
        // templatemgr.getTawSupplierkpiTemplate(templateId);

        ITawSupplierkpiItemManager itemmgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");

        String whereStr = " where itemtype like ('" + specialType + "%')";
        Map map = (Map) itemmgr.getTawSupplierkpiItems(pageIndex, pageSize,
                whereStr); // map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
        List list = (List) map.get("result");
        request.setAttribute(Constants.TAWSUPPLIERKPIITEM_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("serviceType", specialType.substring(0,
                specialType.length() - 2));
        request.setAttribute("specialType", specialType);

        return mapping.findForward("list");
    }

    public ActionForward addSupplierkpi(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String supplierId = request.getParameter("supplierId");
        if ((supplierId != null) && (!"".equals(supplierId))) {
            ITawSupplierkpiInfoManager mgr = (ITawSupplierkpiInfoManager) getBean("ItawSupplierkpiInfoManager");
            TawSupplierkpiInfo tawSupplierkpiInfo = mgr
                    .getTawSupplierkpiInfo(supplierId);
            request.setAttribute("supplierId", tawSupplierkpiInfo.getId());
            request.setAttribute("supplierName", tawSupplierkpiInfo
                    .getSupplierName());
        }
        return mapping.findForward("addSupplierkpi");
    }

    public void geneKPIList(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String specialType = request.getParameter("specialType");
        String supplierId = request.getParameter("supplierId");

        ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
        ITawSupplierkpiAssessInstanceManager assessInstanceMgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
        List specialKpiList = (ArrayList) itemMgr.getSpecialkpi(specialType);
        List supplierKpiList = (ArrayList) assessInstanceMgr
                .getSupplierkpi(supplierId);
        List finalList = new ArrayList();

        Iterator specialIterator = specialKpiList.iterator();

        while (specialIterator.hasNext()) {
            TawSupplierkpiItem specialKPI = (TawSupplierkpiItem) specialIterator
                    .next();
            Iterator supplierIterator = supplierKpiList.iterator();
            boolean flag = false;
            while (supplierIterator.hasNext()) {
                TawSupplierkpiItem supplierKPI = (TawSupplierkpiItem) supplierIterator
                        .next();
                if (specialKPI.getId().equals(supplierKPI.getId())) {
                    flag = true;
                }
            }
            KPIShowObject kpiShowObject = new KPIShowObject();
            kpiShowObject.setKpiId(specialKPI.getId());
            kpiShowObject.setKpiName(specialKPI.getKpiName());
            kpiShowObject.setSelected(flag);
            finalList.add(kpiShowObject);
        }
        response.setContentType("text/xml; charset=UTF-8");
        response.getWriter().print(List2JSON.transform(finalList));
    }

    /**
     * 获取某专业KPI
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void getSpecialKpis(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String specialType = request.getParameter("specialType");

        ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
        List specialKpiList = (ArrayList) itemMgr.getSpecialkpi(specialType);
        List finalList = new ArrayList();

        Iterator specialIterator = specialKpiList.iterator();

        while (specialIterator.hasNext()) {
            TawSupplierkpiItem specialKPI = (TawSupplierkpiItem) specialIterator
                    .next();
            KPIShowObject kpiShowObject = new KPIShowObject();
            kpiShowObject.setKpiId(specialKPI.getId());
            kpiShowObject.setKpiName(specialKPI.getKpiName());
            kpiShowObject.setSelected(false);
            finalList.add(kpiShowObject);
            System.out.println("kpiid========" + specialKPI.getId());
        }
        response.setContentType("text/xml; charset=UTF-8");
        response.getWriter().print(List2JSON.transform(finalList));
    }

    /**
     * 查看KPI详细信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pgAdminRole = ((SupplierkpiAttributes) getBean("supplierkpiAttributes"))
                .getPgAdminRole();
        String fuzerenRole = ((SupplierkpiAttributes) getBean("supplierkpiAttributes"))
                .getFuzerenRole();
        String dishiRole = ((SupplierkpiAttributes) getBean("supplierkpiAttributes"))
                .getDishiRole();
        String dictId = StaticMethod
                .null2String(request.getParameter("dictId"));
        ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
        TawSupplierkpiItem tawSupplierkpiItem = itemMgr
                .getItemByItemType(dictId);

        // 获取专业类型 用于传递specialType参数到页面
        String specialType = dictId;
        ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
        while (templateMgr.getTemplateIdBySpecialType(specialType) == null
                || "".equals(templateMgr
                .getTemplateIdBySpecialType(specialType))) {
            specialType = specialType.substring(0, specialType.length() - 2);
        }
        request.setAttribute("specialType", specialType);
        //--------------------

        //从页面tawSupplierkpiTemplateAssessView.jsp中传递过来，用于区分调用此方法是在已审核列表页面还是未审核列表页面
        String auditingType = request.getParameter("auditingType");
        request.setAttribute("auditingType", auditingType);
        //--------------------

        // 有可能是从送审页面链过来的已经删除的指标
        if (tawSupplierkpiItem == null) {
            String itemId = StaticMethod.null2String(request
                    .getParameter("itemId"));
            String whereStr = " from TawSupplierkpiItem where id='" + itemId
                    + "'";
            List itemList = itemMgr.getItems(whereStr);
            if (itemList.size() > 0) {
                tawSupplierkpiItem = (TawSupplierkpiItem) itemList.get(0);
            } else {
                String failInfo = "指标状态异常！";
                request.setAttribute("failInfo", failInfo);
                return mapping.findForward("fail");
            }
        }

        TawSupplierkpiItemForm tawSupplierkpiItemForm = (TawSupplierkpiItemForm) convert(tawSupplierkpiItem);

        ITawSystemSubRoleManager subRoleMgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
        TawSystemSubRole tawSystemSubRole = subRoleMgr
                .getTawSystemSubRole(tawSupplierkpiItemForm.getPreActor());
        String subRoleName = "";
        if (tawSystemSubRole == null) {
            subRoleName = "未找到指定角色";
        } else {
            subRoleName = tawSystemSubRole.getSubRoleName();
        }
        if (pgAdminRole.equals(tawSupplierkpiItemForm.getPreActor())) {
            subRoleName = "专业评估管理员";
        } else if (fuzerenRole.equals(tawSupplierkpiItemForm.getPreActor())) {
            subRoleName = "评估管理负责人";
        } else if (dishiRole.equals(tawSupplierkpiItemForm.getPreActor())) {
            subRoleName = "地市评估管理员";
        }
//		ITawSupplierkpiDictManager dictTypeMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
//		String dataSource = dictTypeMgr.getDictByDictId(
//				tawSupplierkpiItemForm.getDataSource()).getDictName();
//		String dataType = dictTypeMgr.getDictByDictId(
//				tawSupplierkpiItemForm.getDataType()).getDictName();
//		String isImpersonality = dictTypeMgr.getDictByDictId(
//				tawSupplierkpiItemForm.getIsImpersonality()).getDictName();
//		String statictsCycle = "";
//		if (tawSupplierkpiItemForm.getStatictsCycle() != null
//				&& !"".equals(tawSupplierkpiItemForm.getStatictsCycle())) {
//			statictsCycle = dictTypeMgr.getDictByDictId(
//					tawSupplierkpiItemForm.getStatictsCycle()).getDictName();
//		}
        // String writeManner =
        // dictTypeMgr.getDictNameByDictId(tawSupplierkpiItemForm.getWriteManner()).getDictName();
//		String unit = dictTypeMgr.getDictByDictId(
//				tawSupplierkpiItemForm.getUnit()).getDictName();
//		String assessMoment = dictTypeMgr.getDictByDictId(
//				tawSupplierkpiItemForm.getAssessMoment()).getDictName();

//		tawSupplierkpiItemForm.setDataSource(dataSource);
//		tawSupplierkpiItemForm.setDataType(dataType);
//		tawSupplierkpiItemForm.setIsImpersonality(isImpersonality);
//		tawSupplierkpiItemForm.setStatictsCycle(statictsCycle);
        // tawSupplierkpiItemForm.setWriteManner(writeManner);
//		tawSupplierkpiItemForm.setUnit(unit);
//		tawSupplierkpiItemForm.setAssessMoment(assessMoment);

        tawSupplierkpiItemForm.setPreActor(subRoleName);

        updateFormBean(mapping, request, tawSupplierkpiItemForm);
        // 判断此查看操作是正常查看还是从模型审核页面链接过来的查看
        String fromTemplateView = StaticMethod.null2String(request
                .getParameter("fromTemplateView"));
        if ("1".equals(fromTemplateView)) {
            request.setAttribute("fromTemplateView", "1");
        } else {
            request.setAttribute("fromTemplateView", "0");
        }

        return mapping.findForward("view");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String dictId = StaticMethod.nullObject2String(request.getParameter("dictId"));

        ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
        TawSupplierkpiItem tawSupplierkpiItem = itemMgr.getItemByItemType(dictId);
        // 被假删除的指标由于字典表中已经不存在记录，所以通过dictId取不到
        if (null == tawSupplierkpiItem) {
            String itemId = StaticMethod.nullObject2String(request.getParameter("itemId"));
            tawSupplierkpiItem = itemMgr.getTawSupplierkpiItem(itemId, SupplierkpiConstants.DELETED);
        }
        TawSupplierkpiItemForm tawSupplierkpiItemForm = (TawSupplierkpiItemForm) convert(tawSupplierkpiItem);
        updateFormBean(mapping, request, tawSupplierkpiItemForm);

        // 获取子角色列表
        String roleId = ((SupplierkpiAttributes) getBean("supplierkpiAttributes")).getPgAdminRole();
        Hashtable filterHash = new Hashtable();

        // 获取专业类型
        String specialType = dictId;
        ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
        while (templateMgr.getTemplateIdBySpecialType(specialType) == null
                || "".equals(templateMgr.getTemplateIdBySpecialType(specialType))) {
            specialType = specialType.substring(0, specialType.length() - 2);
        }
        // 针对二级专业
        String parentSpecial = specialType.substring(0, specialType.length() - 2);
        if (templateMgr.getTemplateIdBySpecialType(parentSpecial) == null
                || "".equals(templateMgr.getTemplateIdBySpecialType(parentSpecial))) {
            filterHash.put("class2", specialType);
        } else {
            // filterHash.put("class3", specialType);
            filterHash.put("class2", parentSpecial);
        }

        String templateId = templateMgr.getTemplateIdBySpecialType(specialType);
        TawSupplierkpiTemplate template = templateMgr.getTawSupplierkpiTemplate(templateId);
        String serviceType = StaticMethod.null2String(template.getServiceType());

        List list = RoleManage.getInstance().getSubRoles(roleId, filterHash);

        request.setAttribute("serviceType", serviceType);
        request.setAttribute("subRoles", list);
        request.setAttribute("dictId", dictId);
        request.setAttribute("specialType", specialType);//liujinlong加，用于update页面的返回按钮中，url传递参数

        // 判断此修改操作是正常修改还是从模型送审页面链接过来的修改
        String fromTemplateView = StaticMethod.null2String(request.getParameter("fromTemplateView"));
        if ("1".equals(fromTemplateView)) {
            request.setAttribute("fromTemplateView", "1");
        } else {
            request.setAttribute("fromTemplateView", "0");
        }
        return mapping.findForward("update");
    }
}
