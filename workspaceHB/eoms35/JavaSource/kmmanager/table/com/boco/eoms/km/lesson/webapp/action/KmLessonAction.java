package com.boco.eoms.km.lesson.webapp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.km.lesson.mgr.KmLessonMgr;
import com.boco.eoms.km.lesson.model.KmLesson;
import com.boco.eoms.km.lesson.util.KmLessonConstants;
import com.boco.eoms.km.lesson.webapp.form.KmLessonForm;

/**
 * <p>
 * Title:课程创建
 * </p>
 * <p>
 * Description:课程创建
 * </p>
 * <p>
 * Wed Jul 01 15:12:52 CST 2009
 * </p>
 *
 * @moudle.getAuthor() mosquito
 * @moudle.getVersion() 1.0
 */
public final class KmLessonAction extends BaseAction {

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
     * 新增课程
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
     * 查看课程详情
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
        KmLessonMgr lessonMgr = (KmLessonMgr) getBean("kmLessonMgr");
        KmLesson kmLesson = lessonMgr.getKmLesson(id);
        KmLessonForm kmLessonForm = (KmLessonForm) convert(kmLesson);
        updateFormBean(mapping, request, kmLessonForm);
        return mapping.findForward("detail");// 转向课程列页面
    }

    /**
     * 页面跳转
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
        KmLessonMgr lessonMgr = (KmLessonMgr) getBean("kmLessonMgr");
        KmLesson kmLesson = lessonMgr.getKmLesson(id);

        KmLessonForm kmLessonForm = (KmLessonForm) convert(kmLesson);
        updateFormBean(mapping, request, kmLessonForm);

        return mapping.findForward("edit");
    }

    /**
     * 保存课程内容
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
        //取得当前用户信息
        TawSystemSessionForm sessionform = this.getUser(request);
        String operateUserId = sessionform.getUserid();

        KmLessonForm kmLessonForm = (KmLessonForm) form;
        KmLesson kmLesson = (KmLesson) convert(kmLessonForm);
        kmLesson.setCreateTime(StaticMethod.getCurrentDateTime());
        kmLesson.setCreateUser(operateUserId);
        long hours = KmLessonConstants.getIntervalDays(kmLesson.getStartTime(), kmLesson.getEndTime());
        kmLesson.setTimeLength(String.valueOf(hours));

        KmLessonMgr lessonMgr = (KmLessonMgr) getBean("kmLessonMgr");
        lessonMgr.saveKmLesson(kmLesson);

        return mapping.findForward("success");
    }

    /**
     * 删除课程
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
        KmLessonMgr creatlessonMgr = (KmLessonMgr) getBean("kmLessonMgr");
        creatlessonMgr.removeKmLesson(id);
        return mapping.findForward("success");
    }

    /**
     * 课程查询
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
        return mapping.findForward("search");
    }

    /**
     * 分页显示课程列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchDo(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String pageIndexName = new org.displaytag.util.ParamEncoder(//页数的参数名
                KmLessonConstants.KMLESSON_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();//每页包含记录条数
        final Integer pageIndex = new Integer(GenericValidator//当前页数
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));


        KmLessonForm kmLessonForm = (KmLessonForm) form;

        StringBuffer whereStr = new StringBuffer(" WHERE kmLesson.isDelete='0' ");
        whereStr.append(" and kmLesson.createTime between '");
        whereStr.append(kmLessonForm.getStartTime());
        whereStr.append("' and '");
        whereStr.append(kmLessonForm.getEndTime());
        whereStr.append("'");

        // 创建者
        String userId = kmLessonForm.getCreateUser();
        if (userId != null && !userId.equals("")) {
            whereStr.append(" and kmLesson.createUser = '");
            whereStr.append(userId);
            whereStr.append("'");
        }

        //课题名称（模糊匹配）
        String lessonName = kmLessonForm.getLessonName();
        if (lessonName != null && !lessonName.equals("")) {
            whereStr.append(" and kmLesson.lessonName like '%");
            whereStr.append(lessonName);
            whereStr.append("%'");
        }

        // 主题（模糊匹配）
        String lessonTheme = kmLessonForm.getLessonTheme();
        if (lessonTheme != null && !lessonTheme.equals("")) {
            whereStr.append(" and kmLesson.lessonTheme like '%");
            whereStr.append(lessonTheme);
            whereStr.append("%'");
        }

        // 业务类别
        String lessonClass = kmLessonForm.getLessonClass();
        if (lessonClass != null && !lessonClass.equals("")) {
            whereStr.append(" and kmLesson.lessonClass = '");
            whereStr.append(lessonClass);
            whereStr.append("'");
        }

        KmLessonMgr lessonMgr = (KmLessonMgr) getBean("kmLessonMgr");
        Map map = (Map) lessonMgr.getKmLessons(pageIndex, pageSize, whereStr.toString());
        List list = (List) map.get("result");

        request.setAttribute(KmLessonConstants.KMLESSON_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        return mapping.findForward("list");// 转向课程列页面
    }

    /**
     * 得到未结束课程
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showLesson(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        StringBuffer whereStr = new StringBuffer(" WHERE kmLesson.isDelete='0' ");
        whereStr.append(" and kmLesson.endTime between '");
        whereStr.append(StaticMethod.getCurrentDateTime());
        whereStr.append("' and '");
        whereStr.append("2079-08-01 10:53:56");
        whereStr.append("'");
        Integer pageSize = StaticMethod.nullObject2Integer(request.getParameter("pageSize"));
        KmLessonMgr lessonMgr = (KmLessonMgr) getBean("kmLessonMgr");
        Map map = (Map) lessonMgr.getKmLessons(new Integer(1), pageSize, "");
        List list = (List) map.get("result");
        JSONArray json = new JSONArray();
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            KmLesson kmLesson = (KmLesson) iterator.next();
            JSONObject jitem = new JSONObject();
            jitem.put("id", kmLesson.getId());
            jitem.put("text", kmLesson.getLessonName());
            json.put(jitem);
        }
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(json.toString());
        return null;
    }
}
