package com.boco.eoms.km.expert.webapp.action;

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
import com.boco.eoms.km.expert.mgr.KmExpertGroupMgr;
import com.boco.eoms.km.expert.mgr.KmExpertTrainMgr;
import com.boco.eoms.km.expert.model.KmExpertGroup;
import com.boco.eoms.km.expert.util.KmExpertGroupConstants;
import com.boco.eoms.km.expert.webapp.form.KmExpertGroupForm;

public class KmExpertGroupAction extends BaseAction {

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
     * 新增团队基本信息
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
     * 修改团队基本信息
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
        String id = StaticMethod.null2String(request.getParameter("id"));

        KmExpertGroupMgr kmExpertGroupMgr = (KmExpertGroupMgr) getBean("kmExpertGroupMgr");
        KmExpertGroup kmExpertGroup = kmExpertGroupMgr.getKmExpertGroupAndGroupMember(id);

        KmExpertGroupForm kmExpertGroupForm = (KmExpertGroupForm) convert(kmExpertGroup);
        updateFormBean(mapping, request, kmExpertGroupForm);

        return mapping.findForward("edit");
    }

    /**
     * 查看团队基本信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward detail(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = StaticMethod.null2String(request.getParameter("id"));

        KmExpertGroupMgr kmExpertGroupMgr = (KmExpertGroupMgr) getBean("kmExpertGroupMgr");
        KmExpertGroup kmExpertGroup = kmExpertGroupMgr.getKmExpertGroupAndGroupMember(id);

        KmExpertGroupForm kmExpertGroupForm = (KmExpertGroupForm) convert(kmExpertGroup);
        updateFormBean(mapping, request, kmExpertGroupForm);

        return mapping.findForward("detail");
    }

    /**
     * 保存基本信息
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
        KmExpertGroupForm kmExpertGroupForm = (KmExpertGroupForm) form;

        KmExpertGroup kmExpertGroup = (KmExpertGroup) convert(kmExpertGroupForm);
        kmExpertGroup.setCreateTime(new java.util.Date());
        kmExpertGroup.setCreateUser(this.getUserId(request));

        KmExpertGroupMgr kmExpertGroupMgr = (KmExpertGroupMgr) getBean("kmExpertGroupMgr");
        kmExpertGroupMgr.saveKmExpertGroup(kmExpertGroup);

        return search(mapping, kmExpertGroupForm, request, response);
    }

    /**
     * 删除团队基本信息
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
        String id = StaticMethod.null2String(request.getParameter("id"));

        KmExpertGroupMgr kmExpertGroupMgr = (KmExpertGroupMgr) getBean("kmExpertGroupMgr");
        kmExpertGroupMgr.removeKmExpertGroup(id);

        return search(mapping, form, request, response);
    }

    /**
     * 查询团队基本信息
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
                KmExpertGroupConstants.KMEXPERTGROUP_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        KmExpertGroupMgr kmExpertGroupMgr = (KmExpertGroupMgr) getBean("kmExpertGroupMgr");
        Map map = (Map) kmExpertGroupMgr.getKmExpertGroups(pageIndex, pageSize, "");

        List list = (List) map.get("result");

        request.setAttribute(KmExpertGroupConstants.KMEXPERTGROUP_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("list");
    }

}
