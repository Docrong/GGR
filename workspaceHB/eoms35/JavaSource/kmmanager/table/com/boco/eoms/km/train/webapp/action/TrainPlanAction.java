package com.boco.eoms.km.train.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Person;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.train.mgr.TrainEnterMgr;
import com.boco.eoms.km.train.mgr.TrainFeedbackMgr;
import com.boco.eoms.km.train.mgr.TrainPlanMgr;
import com.boco.eoms.km.train.model.TrainPlan;
import com.boco.eoms.km.train.util.TrainEnterConstants;
import com.boco.eoms.km.train.util.TrainFeedbackConstants;
import com.boco.eoms.km.train.util.TrainPlanConstants;
import com.boco.eoms.km.train.webapp.form.TrainPlanForm;
import com.boco.eoms.km.train.webapp.form.TrainRequireForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;


/**
 * <p>
 * Title:培训计划
 * </p>
 * <p>
 * Description:培训计划
 * </p>
 * <p>
 * Fri Jul 10 10:50:46 CST 2009
 * </p>
 *
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 */
public final class TrainPlanAction extends BaseAction {

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
     * 新增培训计划
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
        TrainPlanForm trainPlanForm = (TrainPlanForm) form;
        trainPlanForm.setTrainUser(userId);
        trainPlanForm.setTrainDept(dept);
        updateFormBean(mapping, request, trainPlanForm);
        return mapping.findForward("edit");
    }

    /**
     * 修改培训计划
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
        TrainPlanMgr trainPlanMgr = (TrainPlanMgr) getBean("trainPlanMgr");
        String id = StaticMethod.null2String(request.getParameter("trainPlanId"));
        TrainPlan trainPlan = trainPlanMgr.getTrainPlan(id);
        TrainPlanForm trainPlanForm = (TrainPlanForm) convert(trainPlan);
        updateFormBean(mapping, request, trainPlanForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存培训计划
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
        TrainPlanMgr trainPlanMgr = (TrainPlanMgr) getBean("trainPlanMgr");
        TrainPlanForm trainPlanForm = (TrainPlanForm) form;
        boolean isNew = (null == trainPlanForm.getId() || "".equals(trainPlanForm.getId()));
        TrainPlan trainPlan = (TrainPlan) convert(trainPlanForm);
        trainPlan.setTrainSpeciality("101");
        if (isNew) {
            trainPlanMgr.saveTrainPlan(trainPlan);
        } else {
            trainPlanMgr.saveTrainPlan(trainPlan);
        }
        return mapping.findForward("success");
    }

    /**
     * 删除培训计划
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
        TrainPlanMgr trainPlanMgr = (TrainPlanMgr) getBean("trainPlanMgr");
        String id = StaticMethod.null2String(request.getParameter("trainPlanId"));
        trainPlanMgr.removeTrainPlan(id);
        return search(mapping, form, request, response);
    }

    /**
     * 分页显示培训计划列表
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
                TrainPlanConstants.TRAINPLAN_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        TrainPlanMgr trainPlanMgr = (TrainPlanMgr) getBean("trainPlanMgr");
        Map map = (Map) trainPlanMgr.getTrainPlans(pageIndex, pageSize, "");
        List list = (List) map.get("result");
        request.setAttribute(TrainPlanConstants.TRAINPLAN_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 查看培训计划详情
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
        TrainPlanMgr trainPlanMgr = (TrainPlanMgr) getBean("trainPlanMgr");
        TrainEnterMgr trainEnterMgr = (TrainEnterMgr) getBean("trainEnterMgr");
        TrainFeedbackMgr trainFeedbackMgr = (TrainFeedbackMgr) getBean("trainFeedbackMgr");
        //培训计划id
        String id = StaticMethod.null2String(request.getParameter("id"));
        TrainPlan trainPlan = trainPlanMgr.getTrainPlan(id);
        TrainPlanForm trainPlanForm = (TrainPlanForm) convert(trainPlan);
        updateFormBean(mapping, request, trainPlanForm);

        //查询该培训计划的报名情况（信息）
        List trainEnterList = trainEnterMgr.getTrainEntersByPlanId(id);
        request.setAttribute("trainEnterList", trainEnterList);
        request.setAttribute("enterPageSize", new Integer(trainEnterList.size()));
        request.setAttribute("trainEnterList1", trainEnterList);
        //查询该培训计划的反馈情况
        List trainFeedbackList = trainFeedbackMgr.getTrainFeedbacksByPlanId(id);
        request.setAttribute("trainFeedbackList", trainFeedbackList);
        request.setAttribute("FeedPageSize", new Integer(trainFeedbackList.size()));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("detail");
    }

    /**
     * 分页显示培训计划列表，支持Atom方式接入Portal
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
//			TrainPlanMgr trainPlanMgr = (TrainPlanMgr) getBean("trainPlanMgr");
//			Map map = (Map) trainPlanMgr.getTrainPlans(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			TrainPlan trainPlan = new TrainPlan();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				trainPlan = (TrainPlan) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/trainPlan/trainPlans.do?method=edit&id="
//						+ trainPlan.getId() + "' target='_blank'>"
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