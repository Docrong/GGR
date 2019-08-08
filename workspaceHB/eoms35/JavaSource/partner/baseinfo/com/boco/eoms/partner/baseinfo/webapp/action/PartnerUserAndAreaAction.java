package com.boco.eoms.partner.baseinfo.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.partner.baseinfo.mgr.PartnerUserAndAreaMgr;
import com.boco.eoms.partner.baseinfo.model.AreaDeptTree;
import com.boco.eoms.partner.baseinfo.model.PartnerUserAndArea;
import com.boco.eoms.partner.baseinfo.util.PartnerUserAndAreaConstants;
import com.boco.eoms.partner.baseinfo.webapp.form.PartnerDeptForm;
import com.boco.eoms.partner.baseinfo.webapp.form.PartnerUserAndAreaForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:��Ա�������
 * </p>
 * <p>
 * Description:��Ա�������
 * </p>
 * <p>
 * Tue Mar 10 16:24:32 CST 2009
 * </p>
 *
 * @moudle.getAuthor() liujinlong
 * @moudle.getVersion() 3.5
 */
public final class PartnerUserAndAreaAction extends BaseAction {

    /**
     * δָ������ʱĬ�ϵ��õķ���
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response);
    }

    /**
     * ������Ա�������
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("edit");
    }

    /**
     * �޸���Ա�������
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartnerUserAndAreaMgr partnerUserAndAreaMgr = (PartnerUserAndAreaMgr) getBean("partnerUserAndAreaMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        PartnerUserAndArea partnerUserAndArea = partnerUserAndAreaMgr.getPartnerUserAndArea(id);
        PartnerUserAndAreaForm partnerUserAndAreaForm = (PartnerUserAndAreaForm) convert(partnerUserAndArea);
        updateFormBean(mapping, request, partnerUserAndAreaForm);
        return mapping.findForward("edit");
    }

    /**
     * ������Ա�������
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartnerUserAndAreaMgr partnerUserAndAreaMgr = (PartnerUserAndAreaMgr) getBean("partnerUserAndAreaMgr");
        PartnerUserAndAreaForm partnerUserAndAreaForm = (PartnerUserAndAreaForm) form;
        boolean isNew = (null == partnerUserAndAreaForm.getId() || "".equals(partnerUserAndAreaForm.getId()));
//		PartnerUserAndArea partnerUserAndArea = (PartnerUserAndArea) convert(partnerUserAndAreaForm);
        if (isNew) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            PartnerUserAndArea partnerUserAndArea = (PartnerUserAndArea) convert(partnerUserAndAreaForm);
            if (partnerUserAndAreaMgr.isunique(partnerUserAndArea.getUserId()).booleanValue()) {
                partnerUserAndAreaMgr.savePartnerUserAndArea(partnerUserAndArea);
            } else {
                updateFormBean(mapping, request, partnerUserAndAreaForm);
                request.setAttribute("fallure", " 用户ID已经存在");
                return mapping.findForward("edit");
            }
        } else {
            PartnerUserAndArea area = partnerUserAndAreaMgr.getPartnerUserAndArea(partnerUserAndAreaForm.getId());
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

            if (area.getUserId().equals(partnerUserAndAreaForm.getUserId())) {
                area.setAreaNames(partnerUserAndAreaForm.getAreaNames());
                area.setAreaType(partnerUserAndAreaForm.getAreaType());
                area.setName(partnerUserAndAreaForm.getName());
                partnerUserAndAreaMgr.savePartnerUserAndArea(area);
            } else {
                if (partnerUserAndAreaMgr.isunique(partnerUserAndAreaForm.getUserId()).booleanValue()) {
                    area.setAreaNames(partnerUserAndAreaForm.getAreaNames());
                    area.setAreaType(partnerUserAndAreaForm.getAreaType());
                    area.setName(partnerUserAndAreaForm.getName());
                    area.setUserId(partnerUserAndAreaForm.getUserId());
                    partnerUserAndAreaMgr.savePartnerUserAndArea(area);


                } else {
                    updateFormBean(mapping, request, partnerUserAndAreaForm);
                    request.setAttribute("fallure", " 用户ID已经存在");
                    return mapping.findForward("edit");
                }
            }

        }
        return search(mapping, partnerUserAndAreaForm, request, response);
    }

    /**
     * ɾ����Ա�������
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward remove(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PartnerUserAndAreaMgr partnerUserAndAreaMgr = (PartnerUserAndAreaMgr) getBean("partnerUserAndAreaMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        if (!id.equals("")) {
            partnerUserAndAreaMgr.removePartnerUserAndArea(id);
        }
        String[] ids = request.getParameterValues("checkbox11");
        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                partnerUserAndAreaMgr.removePartnerUserAndArea(ids[i]);
            }
        }
        return search(mapping, form, request, response);
    }

    /**
     * ��ҳ��ʾ��Ա��������б�
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                PartnerUserAndAreaConstants.PARTNERUSERANDAREA_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        PartnerUserAndAreaMgr partnerUserAndAreaMgr = (PartnerUserAndAreaMgr) getBean("partnerUserAndAreaMgr");
        String whereStr = " where 1=1";
        //组装查询条件
        if (request.getParameter("nameSearch") != null && !request.getParameter("nameSearch").equals("")) {
            whereStr += " and name like '%" + request.getParameter("nameSearch") + "%'";
        }
        if (request.getParameter("userIdSearch") != null && !request.getParameter("userIdSearch").equals("")) {
            whereStr += " and userId like '%" + request.getParameter("userIdSearch") + "%'";
        }
        Map map = (Map) partnerUserAndAreaMgr.getPartnerUserAndAreas(pageIndex, pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(PartnerUserAndAreaConstants.PARTNERUSERANDAREA_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }
}