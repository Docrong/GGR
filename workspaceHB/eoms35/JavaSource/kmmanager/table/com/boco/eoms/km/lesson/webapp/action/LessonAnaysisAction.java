package com.boco.eoms.km.lesson.webapp.action;

import java.util.ArrayList;
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
import com.boco.eoms.km.lesson.mgr.LessonAnaysisMgr;
import com.boco.eoms.km.lesson.model.LessonAnaysis;
import com.boco.eoms.km.lesson.util.LessonAnaysisConstants;
import com.boco.eoms.km.lesson.webapp.form.LessonAnaysisForm;

/**
 * <p>
 * Title:考试课程分析
 * </p>
 * <p>
 * Description:考试课程分析
 * </p>
 * <p>
 * Tue Jul 07 09:44:42 CST 2009
 * </p>
 *
 * @moudle.getAuthor() mosquito
 * @moudle.getVersion() 1.0
 */
public final class LessonAnaysisAction extends BaseAction {

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
     * 新增考试课程分析
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
     * 修改考试课程分析
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
        LessonAnaysisMgr lessonAnaysisMgr = (LessonAnaysisMgr) getBean("lessonAnaysisMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        LessonAnaysis lessonAnaysis = lessonAnaysisMgr.getLessonAnaysis(id);
        LessonAnaysisForm lessonAnaysisForm = (LessonAnaysisForm) convert(lessonAnaysis);
        updateFormBean(mapping, request, lessonAnaysisForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存考试课程分析
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
        LessonAnaysisMgr lessonAnaysisMgr = (LessonAnaysisMgr) getBean("lessonAnaysisMgr");
        LessonAnaysisForm lessonAnaysisForm = (LessonAnaysisForm) form;
        boolean isNew = (null == lessonAnaysisForm.getId() || "".equals(lessonAnaysisForm.getId()));
        LessonAnaysis lessonAnaysis = (LessonAnaysis) convert(lessonAnaysisForm);
        if (isNew) {
            lessonAnaysisMgr.saveLessonAnaysis(lessonAnaysis);
        } else {
            lessonAnaysisMgr.saveLessonAnaysis(lessonAnaysis);
        }
        return search(mapping, lessonAnaysisForm, request, response);
    }

    /**
     * 删除考试课程分析
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
        LessonAnaysisMgr lessonAnaysisMgr = (LessonAnaysisMgr) getBean("lessonAnaysisMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        lessonAnaysisMgr.removeLessonAnaysis(id);
        return search(mapping, form, request, response);
    }

    /**
     * 分页显示考试课程分析列表
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
                LessonAnaysisConstants.LESSONANAYSIS_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        LessonAnaysisMgr lessonAnaysisMgr = (LessonAnaysisMgr) getBean("lessonAnaysisMgr");
        Map map = (Map) lessonAnaysisMgr.getLessonAnaysiss(pageIndex, pageSize, "");

        List list1 = (List) map.get("result");
        List list2 = (List) map.get("resultlist");

//		LessonAnaysis lessonAnaysis = new LessonAnaysis();
        List list_result = new ArrayList();
        for (int i = 0; i < list2.size(); i++) {
            Object[] objs = (Object[]) list2.get(i);
            LessonAnaysis lessonAnaysis = new LessonAnaysis();
            lessonAnaysis.setTestID(objs[0] != null ? objs[0] + "" : "");
            lessonAnaysis.setTotaluser(objs[0] != null ? objs[1] + "" : "");
            lessonAnaysis.setAvgscore(objs[1] != null ? objs[2] + "" : "");

            list_result.add(lessonAnaysis);
        }
        map.put("result", list_result);

        List resultlist = (List) map.get("result");

        request.setAttribute(LessonAnaysisConstants.LESSONANAYSIS_LIST, list1);
        request.setAttribute(LessonAnaysisConstants.SUM_LIST, resultlist);

        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }
}