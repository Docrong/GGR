
package com.boco.eoms.extra.supplierkpi.webapp.action;

import java.sql.Timestamp;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiAssessInstance;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplateAssess;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiAssessInstanceManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateAssessManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateManager;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSupplierkpiAssessInstanceForm;

public final class TawSupplierkpiAssessInstanceAction extends BaseAction {
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
            throws Exception {
        return mapping.findForward("search");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
            throws Exception {

        ActionMessages messages = new ActionMessages();
        TawSupplierkpiAssessInstanceForm tawSupplierkpiAssessInstanceForm = (TawSupplierkpiAssessInstanceForm) form;

        // Exceptions are caught by ActionExceptionHandler
        ITawSupplierkpiAssessInstanceManager mgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
        mgr.removeTawSupplierkpiAssessInstance(tawSupplierkpiAssessInstanceForm.getId());

        messages.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("tawSupplierkpiAssessInstance.deleted"));

        // save messages in session, so they'll survive the redirect
        saveMessages(request.getSession(), messages);

        return mapping.findForward("search");
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
            throws Exception {
        String specialType = StaticMethod.null2String(request.getParameter("specialType"));
        ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
        String templateId = StaticMethod.null2String(templateMgr.getTemplateIdBySpecialType(specialType));
        ITawSupplierkpiTemplateAssessManager templateAssessMgr = (ITawSupplierkpiTemplateAssessManager) getBean("ItawSupplierkpiTemplateAssessManager");
        TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess = templateAssessMgr.getTawSupplierkpiTemplateAssessByTemplateId(templateId);
        if ((tawSupplierkpiTemplateAssess.getId() == null) || ("".equals(tawSupplierkpiTemplateAssess.getId()))) {
            return null;
        }
        TawSupplierkpiAssessInstanceForm tawSupplierkpiAssessInstanceForm = (TawSupplierkpiAssessInstanceForm) form;

        // if an id is passed in, look up the user - otherwise
        // don't do anything - user is doing an add
        if (tawSupplierkpiAssessInstanceForm.getId() != null) {
            ITawSupplierkpiAssessInstanceManager mgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
            TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance = mgr.getTawSupplierkpiAssessInstance(tawSupplierkpiAssessInstanceForm.getId());
            tawSupplierkpiAssessInstanceForm = (TawSupplierkpiAssessInstanceForm) convert(tawSupplierkpiAssessInstance);
            updateFormBean(mapping, request, tawSupplierkpiAssessInstanceForm);
        }
        request.setAttribute("specialType", specialType);
        return mapping.findForward("edit");
    }

    /**
     * 批量保存供应商KPI
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
            throws Exception {

        //从表单获取供应商Id,服务类型,专业类型
        TawSupplierkpiAssessInstanceForm tawSupplierkpiAssessInstanceForm = (TawSupplierkpiAssessInstanceForm) form;
        String specialType = StaticMethod.null2String(tawSupplierkpiAssessInstanceForm.getSpecialType());

        //从session获取创建人和创建时间
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String creator = StaticMethod.null2String(sessionform.getUsername());
        Timestamp createTime = new Timestamp(System.currentTimeMillis());

        //从专业模型获取审核状态
        ITawSupplierkpiTemplateManager templatemgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
        String templateId = templatemgr.getTemplateIdBySpecialType(specialType);
        TawSupplierkpiTemplate tawSupplierkpiTemplate = templatemgr.getTawSupplierkpiTemplate(templateId);
        int assessStatus = tawSupplierkpiTemplate.getAssessStatus();
        String serviceType = StaticMethod.null2String(tawSupplierkpiTemplate.getServiceType());
        //String assessLevel;

        //获取选中的KPI项,如果checkbox一个也没选,后面只保存assessInstance表，不保存relation表
        String[] kpiIds = request.getParameterValues("checkbox");
        int size = 0;
        if (kpiIds != null) {
            size = kpiIds.length;
        }

        //获取选中的供应商
        String[] supplierIds = request.getParameterValues("supplier");
        int num = 0;
        //批量
        if (supplierIds != null) {
            num = supplierIds.length;

            for (int i = 0; i < num; i++) {
                String supplierId = supplierIds[i];
                //set考核实例各属性
                TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance = new TawSupplierkpiAssessInstance();
                tawSupplierkpiAssessInstance.setSupplierId(supplierId);
                tawSupplierkpiAssessInstance.setServiceType(serviceType);
                tawSupplierkpiAssessInstance.setSpecialType(specialType);
                tawSupplierkpiAssessInstance.setCreator(creator);
                tawSupplierkpiAssessInstance.setCreateTime(createTime);
                tawSupplierkpiAssessInstance.setAssessStatus(assessStatus);
                //保存考核实例和关系表
                ITawSupplierkpiAssessInstanceManager assessInstancemgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
                assessInstancemgr.saveTawSupplierkpiAssessInstanceAndRelations(tawSupplierkpiAssessInstance, kpiIds, size);
            }
        }
        //非批量
        else {
            String supplierId = request.getParameter("supplierId");
            //set考核实例各属性
            TawSupplierkpiAssessInstance tawSupplierkpiAssessInstance = new TawSupplierkpiAssessInstance();
            tawSupplierkpiAssessInstance.setSupplierId(supplierId);
            tawSupplierkpiAssessInstance.setServiceType(serviceType);
            tawSupplierkpiAssessInstance.setSpecialType(specialType);
            tawSupplierkpiAssessInstance.setCreator(creator);
            tawSupplierkpiAssessInstance.setCreateTime(createTime);
            tawSupplierkpiAssessInstance.setAssessStatus(assessStatus);
            //保存考核实例和关系表
            ITawSupplierkpiAssessInstanceManager assessInstancemgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
            assessInstancemgr.saveTawSupplierkpiAssessInstanceAndRelations(tawSupplierkpiAssessInstance, kpiIds, size);

            assessInstancemgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
            List list = assessInstancemgr.getCustomSuppliers(specialType);
            ListIterator it = list.listIterator();
            request.setAttribute("specialType", specialType);
            request.setAttribute("serviceType", serviceType);
            request.setAttribute("it", it);
            return mapping.findForward("viewCustomSuppliers");
        }

        return mapping.findForward("success");
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder("tawDemoMytableList").encodeParameterName(
                org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);   // ҳ��Ĳ�����,����"tawDemoMytableList"��ҳ����displayTag��id
        final int pageSize = 25;   //ÿҳ��ʾ������
        final int pageIndex = GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);  //��ǰҳ��

        ITawSupplierkpiAssessInstanceManager mgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
        Map map = (Map) mgr.getTawSupplierkpiAssessInstances(pageIndex, pageSize);    //map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
        List list = (List) map.get("result");
        request.setAttribute(Constants.TAWSUPPLIERKPIASSESSINSTANCE_LIST, list);
        request.setAttribute("resultSize", map.get("total"));

        return mapping.findForward("list");
    }

    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response);
    }

    public ActionForward viewCustomSuppliers(ActionMapping mapping, ActionForm form,
                                             HttpServletRequest request,
                                             HttpServletResponse response)
            throws Exception {
        ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
        String specialType = StaticMethod.null2String(request.getParameter("specialType"));
        String templateId = StaticMethod.null2String(templateMgr.getTemplateIdBySpecialType(specialType));
        TawSupplierkpiTemplate template = templateMgr.getTawSupplierkpiTemplate(templateId);
        String serviceType = StaticMethod.null2String(template.getServiceType());
        ITawSupplierkpiAssessInstanceManager assessInstancemgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
        List list = assessInstancemgr.getCustomSuppliers(specialType);
        ListIterator it = list.listIterator();
        request.setAttribute("specialType", specialType);
        request.setAttribute("serviceType", serviceType);
        request.setAttribute("it", it);
        return mapping.findForward("viewCustomSuppliers");
    }
}
