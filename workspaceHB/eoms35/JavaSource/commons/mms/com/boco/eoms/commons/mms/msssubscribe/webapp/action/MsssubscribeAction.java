package com.boco.eoms.commons.mms.msssubscribe.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.mms.mmsreporttemplate.mgr.MmsreportTemplateMgr;
import com.boco.eoms.commons.mms.mmsreporttemplate.model.MmsreportTemplate;
import com.boco.eoms.commons.mms.msssubscribe.mgr.MsssubscribeMgr;
import com.boco.eoms.commons.mms.msssubscribe.model.Msssubscribe;
import com.boco.eoms.commons.mms.msssubscribe.util.MsssubscribeConstants;
import com.boco.eoms.commons.mms.msssubscribe.webapp.form.MsssubscribeForm;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:彩信报订阅
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Fri Feb 20 11:32:20 CST 2009
 * </p>
 *
 * @moudle.getAuthor() 李志刚
 * @moudle.getVersion() 3.5
 */
public final class MsssubscribeAction extends BaseAction {

    /**
     * 未指定方法时默认调用的方法
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
     * 新增彩信报订阅
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
        MmsreportTemplateMgr mmsreportTemplateMgr = (MmsreportTemplateMgr) getBean("mmsreportTemplateMgr");
        List mmsreportTemplateList = null;
        mmsreportTemplateList = mmsreportTemplateMgr.getMmsreportTemplates();
        request.setAttribute("mmsreportTemplateList", mmsreportTemplateList);

        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String userid = sessionform.getUserid();
        request.setAttribute("userid", userid);

        return mapping.findForward("add");
    }

    /**
     * 查看彩信报订阅
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
        MsssubscribeMgr msssubscribeMgr = (MsssubscribeMgr) getBean("msssubscribeMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        Msssubscribe msssubscribe = msssubscribeMgr.getMsssubscribe(id);
        MsssubscribeForm msssubscribeForm = (MsssubscribeForm) convert(msssubscribe);
        updateFormBean(mapping, request, msssubscribeForm);
        return mapping.findForward("edit");
    }

    /**
     * 修改彩信报订阅
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward modify(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        MsssubscribeMgr msssubscribeMgr = (MsssubscribeMgr) getBean("msssubscribeMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        Msssubscribe msssubscribe = msssubscribeMgr.getMsssubscribe(id);
        MsssubscribeForm msssubscribeForm = (MsssubscribeForm) convert(msssubscribe);
        updateFormBean(mapping, request, msssubscribeForm);
        return mapping.findForward("modify");
    }

    /**
     * 彩信报查询页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showPage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
        String userid = sessionform.getUserid();
        request.setAttribute("userid", userid);
        return mapping.findForward("showPage");
    }

    /**
     * 保存彩信报订阅
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
        MsssubscribeMgr msssubscribeMgr = (MsssubscribeMgr) getBean("msssubscribeMgr");
        MsssubscribeForm msssubscribeForm = (MsssubscribeForm) form;

        MmsreportTemplateMgr mmsreportTemplateMgr = (MmsreportTemplateMgr) getBean("mmsreportTemplateMgr");
        String id = StaticMethod.null2String(msssubscribeForm.getMmsreport_templateId());
        MmsreportTemplate mmsreportTemplate = mmsreportTemplateMgr.getMmsreportTemplate(id);

        msssubscribeForm.setCreatePerson(this.getUser(request).getUserid());
        msssubscribeForm.setCreatTime(StaticMethod.getCurrentDateTime());
        msssubscribeForm.setMmreportName(mmsreportTemplate.getMmsName());
        boolean isNew = (null == msssubscribeForm.getId() || "".equals(msssubscribeForm.getId()));
        Msssubscribe msssubscribe = (Msssubscribe) convert(msssubscribeForm);
        if (isNew) {
            msssubscribeMgr.saveMsssubscribe(msssubscribe);
        } else {
            msssubscribeMgr.saveMsssubscribe(msssubscribe);
        }
        return mapping.findForward("success_msssubscribe");//search(mapping, msssubscribeForm, request, response);
    }

    /**
     * 删除彩信报订阅
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
        MsssubscribeMgr msssubscribeMgr = (MsssubscribeMgr) getBean("msssubscribeMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        msssubscribeMgr.removeMsssubscribe(id);
        return mapping.findForward("success");//search(mapping, form, request, response);
    }

    /**
     * 分页显示彩信报订阅列表
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
                MsssubscribeConstants.MSSSUBSCRIBE_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        MsssubscribeMgr msssubscribeMgr = (MsssubscribeMgr) getBean("msssubscribeMgr");
        /**
         * 查询条件
         *
         */
        String createPerson = request.getParameter("createPerson");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");

//		查询条件
        String where = " where 1=1 ";

        if ((!"".equalsIgnoreCase(beginTime) && beginTime != null)
                && (!"".equalsIgnoreCase(endTime) && endTime != null)) {
            where += " and msssubscribe.receiveTime>='" + beginTime + "' and msssubscribe.receiveTime<='" + endTime + "'";
        }
        if (!"".equalsIgnoreCase(createPerson) && createPerson != null) {
            where += " and msssubscribe.createPerson like '%" + createPerson + "%'";
        }
        Map map = (Map) msssubscribeMgr.getMsssubscribes(pageIndex, pageSize, where);
        List list = (List) map.get("result");
        request.setAttribute(MsssubscribeConstants.MSSSUBSCRIBE_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }
}