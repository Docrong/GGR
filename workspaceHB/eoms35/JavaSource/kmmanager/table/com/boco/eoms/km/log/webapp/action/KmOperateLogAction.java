package com.boco.eoms.km.log.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Feed;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.log.mgr.KmOperateLogMgr;
import com.boco.eoms.km.log.model.KmOperateLog;
import com.boco.eoms.km.log.util.KmOperateLogConstants;
import com.boco.eoms.km.log.webapp.form.KmOperateLogForm;

/**
 * <p>
 * Title:知识操作历史记录表
 * </p>
 * <p>
 * Description:知识操作历史记录表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 *
 * @moudle.getAuthor() ljt
 * @moudle.getVersion() 0.1
 */
public final class KmOperateLogAction extends BaseAction {

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
     * 新增知识操作历史记录表
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
     * 修改知识操作历史记录表
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
        KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        KmOperateLog kmOperateLog = kmOperateLogMgr.getKmOperateLog(id);
        KmOperateLogForm kmOperateLogForm = (KmOperateLogForm) convert(kmOperateLog);
        updateFormBean(mapping, request, kmOperateLogForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存知识操作历史记录表
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
        KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
        KmOperateLogForm kmOperateLogForm = (KmOperateLogForm) form;

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        kmOperateLogForm.setOperateTime(format.parse(format.format(new Date())));
        boolean isNew = (null == kmOperateLogForm.getId() || "".equals(kmOperateLogForm.getId()));
        KmOperateLog kmOperateLog = (KmOperateLog) convert(kmOperateLogForm);
        if (isNew) {
            kmOperateLogMgr.saveKmOperateLog(kmOperateLog);
        } else {
            kmOperateLogMgr.saveKmOperateLog(kmOperateLog);
        }
        return search(mapping, kmOperateLogForm, request, response);
    }

    /**
     * 删除知识操作历史记录表
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
        KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        kmOperateLogMgr.removeKmOperateLog(id);
        return search(mapping, form, request, response);
    }

    /**
     * 分页显示知识操作历史记录表列表
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
                KmOperateLogConstants.KMOPERATELOG_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
        Map map = (Map) kmOperateLogMgr.getKmOperateLogs(pageIndex, pageSize, "");
        List list = (List) map.get("result");
        request.setAttribute(KmOperateLogConstants.KMOPERATELOG_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 分页显示知识操作历史记录表列表，支持Atom方式接入Portal
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            // --------------用于分页，得到当前页号-------------
            final Integer pageIndex = new Integer(request
                    .getParameter("pageIndex"));
            final Integer pageSize = new Integer(request
                    .getParameter("pageSize"));
            KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
            Map map = (Map) kmOperateLogMgr.getKmOperateLogs(pageIndex, pageSize, "");
            List list = (List) map.get("result");
            KmOperateLog kmOperateLog = new KmOperateLog();

            //创建ATOM源
            Factory factory = Abdera.getNewFactory();
            Feed feed = factory.newFeed();

            // 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmOperateLog = (KmOperateLog) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmOperateLog/kmOperateLogs.do?method=edit&id="
//						+ kmOperateLog.getId() + "' target='_blank'>"
//						+ display name for list + "</a>");
//				entry.setSummary(summary);
//				entry.setContent(content);
//				entry.setLanguage(language);
//				entry.setText(text);
//				entry.setRights(tights);
//				
//				// 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
//				entry.setUpdated(new java.util.Date());
//				entry.setPublished(new java.util.Date());
//				entry.setEdited(new java.util.Date());
//				
//				// 为person的name属性赋值，entry.addAuthor可以随意赋值
//				Person person = entry.addAuthor(userId);
//				person.setName(userName);
//			}

            // 每页显示条数
            feed.setText(map.get("total").toString());
            OutputStream os = response.getOutputStream();
            PrintStream ps = new PrintStream(os);
            feed.getDocument().writeTo(ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}