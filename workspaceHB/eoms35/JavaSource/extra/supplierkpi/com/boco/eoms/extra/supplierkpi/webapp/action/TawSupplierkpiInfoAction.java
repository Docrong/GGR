package com.boco.eoms.extra.supplierkpi.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dict.dao.hibernate.TawSystemDictTypeDaoHibernate;
import com.boco.eoms.extra.supplierkpi.model.KPIShowObject;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiAssessInstanceManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInfoManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateManager;
import com.boco.eoms.extra.supplierkpi.util.List2JSON;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSupplierkpiInfoForm;
import com.boco.eoms.extra.supplierkpi.webapp.util.SupplierkpiAttributes;

public final class TawSupplierkpiInfoAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("search");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionMessages messages = new ActionMessages();
        TawSupplierkpiInfoForm tawSupplierkpiInfoForm = (TawSupplierkpiInfoForm) form;

        // Exceptions are caught by ActionExceptionHandler
        ITawSupplierkpiInfoManager mgr = (ITawSupplierkpiInfoManager) getBean("ItawSupplierkpiInfoManager");
        mgr.removeTawSupplierkpiInfo(tawSupplierkpiInfoForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                "tawSupplierkpiInfo.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("success");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSupplierkpiInfoForm tawSupplierkpiInfoForm = (TawSupplierkpiInfoForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (tawSupplierkpiInfoForm.getId() != null) {
            ITawSupplierkpiInfoManager mgr = (ITawSupplierkpiInfoManager) getBean("ItawSupplierkpiInfoManager");
            TawSupplierkpiInfo tawSupplierkpiInfo = mgr
                    .getTawSupplierkpiInfo(tawSupplierkpiInfoForm.getId());
            tawSupplierkpiInfoForm = (TawSupplierkpiInfoForm) convert(tawSupplierkpiInfo);
            updateFormBean(mapping, request, tawSupplierkpiInfoForm);
        }

        return mapping.findForward("edit");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Extract attributes and parameters we will need
        ActionMessages messages = new ActionMessages();
        TawSupplierkpiInfoForm tawSupplierkpiInfoForm = (TawSupplierkpiInfoForm) form;
        boolean isNew = ("".equals(tawSupplierkpiInfoForm.getId()) || tawSupplierkpiInfoForm
                .getId() == null);

        ITawSupplierkpiInfoManager mgr = (ITawSupplierkpiInfoManager) getBean("ItawSupplierkpiInfoManager");
        TawSupplierkpiInfo tawSupplierkpiInfo = (TawSupplierkpiInfo) convert(tawSupplierkpiInfoForm);
        mgr.saveTawSupplierkpiInfo(tawSupplierkpiInfo);

        // add success messages
        if (isNew) {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "tawSupplierkpiInfo.added"));

            // save messages in session to survive a redirect
            saveMessages(request.getSession(), messages);

            return mapping.findForward("success");
        } else {
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                    "tawSupplierkpiInfo.updated"));
            saveMessages(request, messages);

            return mapping.findForward("success");
        }
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

        ITawSupplierkpiInfoManager mgr = (ITawSupplierkpiInfoManager) getBean("ItawSupplierkpiInfoManager");
        Map map = (Map) mgr.getTawSupplierkpiInfos(pageIndex, pageSize); // map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
        List list = (List) map.get("result");
        request.setAttribute(Constants.TAWSUPPLIERKPIINFO_LIST, list);
        request.setAttribute("resultSize", map.get("total"));

        return mapping.findForward("list");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        TawSupplierkpiInfoForm tawSupplierkpiInfoForm = (TawSupplierkpiInfoForm) form;
        if (tawSupplierkpiInfoForm.getId() != null) {
            ITawSupplierkpiInfoManager mgr = (ITawSupplierkpiInfoManager) getBean("ItawSupplierkpiInfoManager");
            TawSupplierkpiInfo tawSupplierkpiInfo = mgr
                    .getTawSupplierkpiInfo(tawSupplierkpiInfoForm.getId());
            tawSupplierkpiInfoForm = (TawSupplierkpiInfoForm) convert(tawSupplierkpiInfo);
            updateFormBean(mapping, request, tawSupplierkpiInfoForm);
        }
        return mapping.findForward("view");
    }

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response);
    }

    /**
     * 生成服务商树，仅一层 liqiuye 071119
     */
    public String getNodes(ActionMapping mapping, ActionForm form,
                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String treeRootId = ((SupplierkpiAttributes) ApplicationContextHolder
                .getInstance().getBean("supplierkpiAttributes"))
                .getTreeRootId();
        ArrayList list = new ArrayList();
        ITawSupplierkpiInfoManager mgr = (ITawSupplierkpiInfoManager) getBean("ItawSupplierkpiInfoManager");
        list = (ArrayList) mgr.getTawSupplierkpiInfos(new TawSupplierkpiInfo());
        JSONArray jsonRoot = new JSONArray();
        for (Iterator rowIt = list.iterator(); rowIt.hasNext(); ) {
            TawSupplierkpiInfo _objSupplierInfo = (TawSupplierkpiInfo) rowIt
                    .next();

            JSONObject jitem = new JSONObject();
            jitem.put("id", _objSupplierInfo.getId());
            jitem.put("dictId", _objSupplierInfo.getId());
            jitem.put("parentDictId", treeRootId);
            jitem.put("text", _objSupplierInfo.getSupplierName());
            jitem.put("allowChild", true);
            jitem.put("allowEdit", true);
            jitem.put("allowDelete", true);
            jitem.put("allowview", true);
            jitem.put("allowkpi", true);
            jitem.put("qtip", "联系人:" + _objSupplierInfo.getSupplierLinkman()
                    + "<br \\/>联系方式:" + _objSupplierInfo.getSupplierContact());
            jitem.put("qtipTitle", _objSupplierInfo.getSupplierName());

            jitem.put("leaf", true);
            jsonRoot.put(jitem);
        }

        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().print(jsonRoot.toString());
        return null;
    }

    /**
     * 获得某供应商所有KPI
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void getSupplierKpis(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String supplierId = StaticMethod.null2String(request
                .getParameter("supplierId"));
        TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
                .getInstance().getBean("ItawSystemDictTypeDao");
        ITawSupplierkpiAssessInstanceManager assessInstanceMgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
        List supplierKpiList = (ArrayList) assessInstanceMgr
                .getSupplierkpi(supplierId);
        List finalList = new ArrayList();
        Iterator supplierIterator = supplierKpiList.iterator();
        while (supplierIterator.hasNext()) {
            TawSupplierkpiItem supplierKPI = (TawSupplierkpiItem) supplierIterator
                    .next();
            KPIShowObject kpiShowObject = new KPIShowObject();
            kpiShowObject.setKpiId(supplierKPI.getId());
            kpiShowObject.setKpiName(supplierKPI.getKpiName());
            kpiShowObject.setSelected(false);
            // 获取服务类型和专业类型
            ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
            TawSupplierkpiTemplate template = templateMgr
                    .getTawSupplierkpiTemplate(supplierKPI.getTemplateId());
            kpiShowObject
                    .setServiceType(dao.id2Name(template.getServiceType()));
            kpiShowObject
                    .setSpecialType(dao.id2Name(template.getSpecialType()));
            finalList.add(kpiShowObject);
        }
        response.setContentType("text/xml; charset=UTF-8");
        response.getWriter().print(List2JSON.transform(finalList));
    }

    /**
     * 某供应商定制所有KPI列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward supplierkpiList(ActionMapping mapping,
                                         ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {

        String supplierId = StaticMethod
                .null2String(request.getParameter("id"));
        if ((supplierId != null) && (!"".equals(supplierId))) {
            if ("-1".equals(supplierId)) {
                return null;
            }
            // 获取kpi列表,组织数据
            ITawSupplierkpiAssessInstanceManager assessInstanceMgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
            List supplierKpiList = (ArrayList) assessInstanceMgr
                    .getSupplierkpi(supplierId);
            List specialList = new ArrayList();
            List kpiList = new ArrayList();
            Iterator supplierIterator = supplierKpiList.iterator();
            while (supplierIterator.hasNext()) {
                TawSupplierkpiItem supplierKPI = (TawSupplierkpiItem) supplierIterator
                        .next();
                KPIShowObject kpiShowObject = new KPIShowObject();
                kpiShowObject.setKpiId(supplierKPI.getId());
                kpiShowObject.setKpiName(supplierKPI.getKpiName());
                // 获取服务类型和专业类型
                ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
                TawSupplierkpiTemplate template = templateMgr
                        .getTawSupplierkpiTemplate(supplierKPI.getTemplateId());
                kpiShowObject.setServiceType(template.getServiceType());
                kpiShowObject.setSpecialType(template.getSpecialType());
                kpiList.add(kpiShowObject);
            }

            List tempList = assessInstanceMgr
                    .getSpecialsBySupplierId(supplierId);
            Iterator specialIterator = tempList.iterator();
            while (specialIterator.hasNext()) {
                String specialType = specialIterator.next().toString();
                KPIShowObject kpiShowObject = new KPIShowObject();
                // 获取服务类型
                ITawSupplierkpiTemplateManager tempMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
                String templateId = tempMgr
                        .getTemplateIdBySpecialType(specialType);
                TawSupplierkpiTemplate template = tempMgr
                        .getTawSupplierkpiTemplate(templateId);
                kpiShowObject.setServiceType(template.getServiceType());
                kpiShowObject.setSpecialType(template.getSpecialType());
                specialList.add(kpiShowObject);
            }

            TawSupplierkpiInfoForm tawSupplierkpiInfoForm = (TawSupplierkpiInfoForm) form;
            ITawSupplierkpiInfoManager mgr = (ITawSupplierkpiInfoManager) getBean("ItawSupplierkpiInfoManager");
            TawSupplierkpiInfo tawSupplierkpiInfo = mgr
                    .getTawSupplierkpiInfo(supplierId);
            tawSupplierkpiInfoForm = (TawSupplierkpiInfoForm) convert(tawSupplierkpiInfo);
            updateFormBean(mapping, request, tawSupplierkpiInfoForm);
            request.setAttribute("supplierId", supplierId);
            request.setAttribute("specialList", specialList);
            request.setAttribute("kpiList", kpiList);

            return mapping.findForward("supplierkpiList");
        } else {
            String failInfo = "未发现此供应商！";
            request.setAttribute("failInfo", failInfo);
            return mapping.findForward("fail");
        }

    }

    /**
     * 某供应商定制某一专业的所有KPI
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward customSpecialKPIs(ActionMapping mapping,
                                           ActionForm form, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        String supplierId = StaticMethod
                .null2String(request.getParameter("id"));
        String specialType = StaticMethod.null2String(request
                .getParameter("specialType"));
        String serviceType = StaticMethod.null2String(request
                .getParameter("serviceType"));

        if ((supplierId != null) && (!"".equals(supplierId))) {
            TawSupplierkpiInfoForm tawSupplierkpiInfoForm = (TawSupplierkpiInfoForm) form;
            ITawSupplierkpiInfoManager mgr = (ITawSupplierkpiInfoManager) getBean("ItawSupplierkpiInfoManager");
            TawSupplierkpiInfo tawSupplierkpiInfo = mgr
                    .getTawSupplierkpiInfo(supplierId);
            tawSupplierkpiInfoForm = (TawSupplierkpiInfoForm) convert(tawSupplierkpiInfo);
            updateFormBean(mapping, request, tawSupplierkpiInfoForm);
            request.setAttribute("specialType", specialType);
            request.setAttribute("serviceType", serviceType);
            return mapping.findForward("customSpecialKPIs");
        } else {
            String failInfo = "未发现此供应商！";
            request.setAttribute("failInfo", failInfo);
            return mapping.findForward("fail");
        }

    }

    /**
     * 删除某供应商部分KPI
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void delKPIs(ActionMapping mapping, ActionForm form,
                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 获取供应商Id
        String supplierId = StaticMethod.null2String(request
                .getParameter("supplierId"));
        // 获取选中的KPI项
        String checkValues = request.getParameter("checkValues");
        String[] kpiIds = checkValues.split(":");
        int size = 0;
        if (kpiIds != null) {
            size = kpiIds.length;
        }

        ITawSupplierkpiAssessInstanceManager mgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
        mgr.removeRelationBySupplierIdAndKpiIds(supplierId, kpiIds, size);
        getSupplierKpis(mapping, form, request, response);
    }

    /**
     * 获得所有供应商
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @throws Exception
     */
    public void getSuppliers(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ITawSupplierkpiInfoManager infoMgr = (ITawSupplierkpiInfoManager) getBean("ItawSupplierkpiInfoManager");
        List supplierList = (ArrayList) infoMgr
                .getTawSupplierkpiInfos(new TawSupplierkpiInfo());
        List finalList = new ArrayList();
        Iterator supplierIterator = supplierList.iterator();
        while (supplierIterator.hasNext()) {
            TawSupplierkpiInfo supplier = (TawSupplierkpiInfo) supplierIterator
                    .next();
            KPIShowObject kpiShowObject = new KPIShowObject();
            kpiShowObject.setKpiId(supplier.getId());
            kpiShowObject.setKpiName(supplier.getSupplierName());
            kpiShowObject.setSelected(false);
            finalList.add(kpiShowObject);
        }
        response.setContentType("text/xml; charset=UTF-8");
        response.getWriter().print(List2JSON.transform(finalList));
    }
}
