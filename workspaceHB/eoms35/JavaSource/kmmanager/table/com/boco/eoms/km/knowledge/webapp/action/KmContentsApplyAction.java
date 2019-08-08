package com.boco.eoms.km.knowledge.webapp.action;

import java.util.ArrayList;
import java.util.Date;
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
import com.boco.eoms.km.knowledge.mgr.KmContentsApplyMgr;
import com.boco.eoms.km.knowledge.mgr.KmContentsApplyRankMgr;
import com.boco.eoms.km.knowledge.mgr.KmContentsMgr;
import com.boco.eoms.km.knowledge.model.KmContents;
import com.boco.eoms.km.knowledge.model.KmContentsApply;
import com.boco.eoms.km.knowledge.util.KmContentsConstants;
import com.boco.eoms.km.knowledge.webapp.form.KmContentsApplyForm;
import com.boco.eoms.km.statics.util.StatisticMethod;

/**
 * <p>
 * Title:知识申请
 * </p>
 * <p>
 * Description:知识申请
 * </p>
 * <p>
 * Tue Jul 14 10:27:17 CST 2009
 * </p>
 *
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 */
public final class KmContentsApplyAction extends BaseAction {

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
     * 新增知识申请
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
        String userId = this.getUser(request).getUserid();
        String dept = this.getUser(request).getDeptid();
        KmContentsApplyForm kmContentsApplyForm = (KmContentsApplyForm) form;
        kmContentsApplyForm.setApplyUser(userId);
        kmContentsApplyForm.setApplyDept(dept);
        updateFormBean(mapping, request, kmContentsApplyForm);
        return mapping.findForward("edit");
    }

    /**
     * 修改知识申请
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
        KmContentsApplyMgr kmContentsApplyMgr = (KmContentsApplyMgr) getBean("kmContentsApplyMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        KmContentsApply kmContentsApply = kmContentsApplyMgr.getKmContentsApply(id);
        KmContentsApplyForm kmContentsApplyForm = (KmContentsApplyForm) convert(kmContentsApply);
        updateFormBean(mapping, request, kmContentsApplyForm);
        return mapping.findForward("edit");
    }

    /**
     * 查看知识申请详情
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
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        KmContentsApplyMgr kmContentsApplyMgr = (KmContentsApplyMgr) getBean("kmContentsApplyMgr");
        KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
        //申请记录id
        String id = StaticMethod.null2String(request.getParameter("id"));
        KmContentsApply kmContentsApply = kmContentsApplyMgr.getKmContentsApply(id);
        KmContentsApplyForm kmContentsApplyForm = (KmContentsApplyForm) convert(kmContentsApply);
        updateFormBean(mapping, request, kmContentsApplyForm);

        //查看该申请对应知识
        List kmContentsList = new ArrayList();
        String contentId = kmContentsApply.getContentId();
        if (contentId != null) {
            KmContents kmContents = kmContentsMgr.getKmContentsByContentId(contentId);
            String contentStatus = kmContents.getContentStatus();
            if (contentStatus.equals("2")) {
                kmContentsList.add(kmContents);
            }
        }
        request.setAttribute("kmContentsList", kmContentsList);
        request.setAttribute("kmContentsList1", kmContentsList);
        return mapping.findForward("detail");
    }

    /**
     * 查看知识申请详情
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward rankDetail(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmContentsConstants.KMCONTENTSAPPLY_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize =
                UtilMgrLocator.getEOMSAttributes()
                        .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        KmContentsApplyMgr kmContentsApplyMgr = (KmContentsApplyMgr) getBean("kmContentsApplyMgr");
        KmContentsApplyRankMgr kmContentsApplyRankMgr = (KmContentsApplyRankMgr) getBean("kmContentsApplyRankMgr");


        String startDate = StaticMethod.null2String(request.getParameter("startDate"));
        String endDate = StaticMethod.null2String(request.getParameter("endDate"));
        String applyTheme = StaticMethod.null2String(request.getParameter("applyTheme"));

        String orderStr = " order by kmContentsApply.applyDate desc";
        Map map = (Map) kmContentsApplyMgr.getKmContentsApplys(pageIndex, pageSize, applyTheme, startDate, endDate, orderStr);
        List list = (List) map.get("result");

        request.setAttribute(KmContentsConstants.KMCONTENTSAPPLY_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        String chartName = kmContentsApplyRankMgr.getKmContentsApplyRankDetail(applyTheme, startDate, endDate);
        request.setAttribute("chartName", chartName);
        return mapping.findForward("rankDetail");
    }

    /**
     * 保存知识申请
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
        KmContentsApplyMgr kmContentsApplyMgr = (KmContentsApplyMgr) getBean("kmContentsApplyMgr");
        KmContentsApplyForm kmContentsApplyForm = (KmContentsApplyForm) form;
        boolean isNew = (null == kmContentsApplyForm.getId() || "".equals(kmContentsApplyForm.getId()));
        KmContentsApply kmContentsApply = (KmContentsApply) convert(kmContentsApplyForm);
        Date applyDate = StaticMethod.getLocalTime();
        kmContentsApply.setApplyDate(applyDate);
        kmContentsApply.setIsAdd("0");
        kmContentsApply.setApplyTable(kmContentsApply.getApplyTheme().substring(0, 3));
        if (isNew) {
            kmContentsApplyMgr.saveKmContentsApply(kmContentsApply);
        } else {
            kmContentsApplyMgr.saveKmContentsApply(kmContentsApply);
        }
        return mapping.findForward("success");
    }

    /**
     * 删除知识申请
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
        KmContentsApplyMgr kmContentsApplyMgr = (KmContentsApplyMgr) getBean("kmContentsApplyMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        kmContentsApplyMgr.removeKmContentsApply(id);
        return search(mapping, form, request, response);
    }


    /**
     * 分页显示知识申请列表
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
                KmContentsConstants.KMCONTENTSAPPLY_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmContentsApplyMgr kmContentsApplyMgr = (KmContentsApplyMgr) getBean("kmContentsApplyMgr");

        String orderStr = " order by kmContentsApply.applyDate desc";
        Map map = (Map) kmContentsApplyMgr.getKmContentsApplys(pageIndex, pageSize, "", orderStr);
        List list = (List) map.get("result");
        request.setAttribute(KmContentsConstants.KMCONTENTSAPPLY_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }


    /**
     * 分页显示知识申请列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchX(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmContentsConstants.KMCONTENTSAPPLY_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize =
                UtilMgrLocator.getEOMSAttributes()
                        .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

        KmContentsApplyMgr kmContentsApplyMgr = (KmContentsApplyMgr) getBean("kmContentsApplyMgr");

        String applyTheme = StaticMethod.null2String(request.getParameter("applyTheme"));
        StringBuffer whereStr = new StringBuffer(" where 1=1 ");
        if (applyTheme != null && !"".equals(applyTheme))
            whereStr.append("and kmContentsApply.applyTheme like '" + applyTheme + "%'");


        String orderStr = " order by kmContentsApply.applyDate desc";
        Map map = (Map) kmContentsApplyMgr.getKmContentsApplys(pageIndex, pageSize, whereStr.toString(), orderStr);
        List list = (List) map.get("result");

        request.setAttribute(KmContentsConstants.KMCONTENTSAPPLY_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 显示知识申请排行列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchRank(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        KmContentsApplyMgr kmContentsApplyMgr = (KmContentsApplyMgr) getBean("kmContentsApplyMgr");

        KmContentsApplyForm kmContentsApplyForm = (KmContentsApplyForm) form;


        String startDate = StaticMethod.null2String(request.getParameter("startDate"));
        String endDate = StaticMethod.null2String(request.getParameter("endDate"));

        //默认统计一周的指标
        if ("".equals(endDate)) {
            Date endDateTime = new Date(System.currentTimeMillis());
            java.util.Date startDateTime = StatisticMethod.countDate(endDateTime, -6);

            endDate = StatisticMethod.dateToString(endDateTime, "yyyy-MM-dd");
            startDate = StatisticMethod.dateToString(startDateTime, "yyyy-MM-dd");
        }
        String userId = StaticMethod.null2String(request.getParameter("userId"));
        String deptId = StaticMethod.null2String(request.getParameter("deptId"));
        String themeId = StaticMethod.null2String(request.getParameter("themeId"));
        String maxSize = null;
        if (request.getParameter("maxSize") == null || "".equals(request.getParameter("maxSize")))
            maxSize = "10";
        else
            maxSize = StaticMethod.null2String(request.getParameter("maxSize"));

        System.out.println(request.getParameter("maxSize"));
        Map map = (Map) kmContentsApplyMgr.getKmContentsApplyRanks(startDate, endDate, userId, deptId, themeId, maxSize);
        List list = (List) map.get("result");
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.setAttribute(KmContentsConstants.KMCONTENTSAPPLY_LIST, list);

        return mapping.findForward("applyRank");
    }

    /**
     * 分页显示知识申请列表，支持Atom方式接入Portal
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
//	public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		try {
//			// --------------用于分页，得到当前页号-------------
//			final Integer pageIndex = new Integer(request
//					.getParameter("pageIndex"));
//			final Integer pageSize = new Integer(request
//					.getParameter("pageSize"));
//			KmContentsApplyMgr kmContentsApplyMgr = (KmContentsApplyMgr) getBean("kmContentsApplyMgr");
//			Map map = (Map) kmContentsApplyMgr.getKmContentsApplys(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			KmContentsApply kmContentsApply = new KmContentsApply();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmContentsApply = (KmContentsApply) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmContentsApply/kmContentsApplys.do?method=edit&id="
//						+ kmContentsApply.getId() + "' target='_blank'>"
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
//			
//			// 每页显示条数
//			feed.setText(map.get("total").toString());
//		    OutputStream os = response.getOutputStream();
//		    PrintStream ps = new PrintStream(os);
//		    feed.getDocument().writeTo(ps);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}