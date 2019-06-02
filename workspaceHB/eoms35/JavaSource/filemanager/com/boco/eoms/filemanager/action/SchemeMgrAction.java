package com.boco.eoms.filemanager.action;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.resource.Pager;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

import com.boco.eoms.filemanager.dao.ITawFileMgrTopicDao;
import com.boco.eoms.filemanager.form.SchemeMgrForm;
import com.boco.eoms.filemanager.SchemeMgrDAO;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA. User: liqifei Date: 2005-9-15 Time: 10:16:13 Boco
 * Corporation ���ţ�������ͨ����о�Ժ EOMS ��ַ�������к������|׵�130��������� 12��3��
 * To change this template use File | Settings | File Templates.
 */
public class SchemeMgrAction extends Action {
	public static int PAGE_LENGTH = 10;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward myforward = null;
		String myaction = request.getParameter("act");
		if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure");
		} else if ("ADD".equalsIgnoreCase(myaction)) {
			myforward = performAdd(mapping, form, request, response);
		} else if ("VIEW".equalsIgnoreCase(myaction)) {
			myforward = performView(mapping, form, request, response);
		} else if ("EDIT".equalsIgnoreCase(myaction)) {
			myforward = performEdit(mapping, form, request, response);
		} else if ("DELETE".equalsIgnoreCase(myaction)) {
			myforward = performDelete(mapping, form, request, response);
		} else if ("UPDATE".equalsIgnoreCase(myaction)) {
			myforward = performUpdate(mapping, form, request, response);
		} else if ("SAVE".equalsIgnoreCase(myaction)) {
			myforward = performSave(mapping, form, request, response);
		} else
			myforward = mapping.findForward("failure");

		return myforward;
	}

	private ActionForward performAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		SchemeMgrForm myForm = (SchemeMgrForm) form;
		String topicId = request.getParameter("topicId");
		String topicName = null;
		SchemeMgrDAO dao = new SchemeMgrDAO();
		try {
			TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
			if("-1".equals(topicId)){
				topicId = "0";
				topicName = "数据上报";
				myForm.setTopicName(topicName);
			} else {
				ITawFileMgrTopicDao tawFileMgrTopicDao = (ITawFileMgrTopicDao) ApplicationContextHolder.getInstance().getBean("tawFileMgrTopicDao");
				topicName = tawFileMgrTopicDao.getNameById(topicId);
				myForm.setTopicName(topicName);
			}
			String userid = saveSessionBeanForm.getUserid();
			String deptid = saveSessionBeanForm.getDeptid();
			// TawRmUser tawRmUser = tawRmUserBO.retrieveNew(userid);
			
			String mobile = saveSessionBeanForm.getContactMobile();
			String deptname = saveSessionBeanForm.getDeptname();
			String username = saveSessionBeanForm.getUsername();
			myForm.setAct("save");
			myForm.setTopicId(topicId);
			myForm.setTopicName(topicName);
			myForm.setSendContact(mobile);
			myForm.setSendDeptName(deptname);
			myForm.setSendDeptId(deptid + "");
			myForm.setCreateUser(userid);
			myForm.setCreateUserName(username);
			myForm.setCycleType(SchemeMgrDAO.SCHEME_CYCLE_TYPE_TEMP + "");
			request.setAttribute("faultClass", dao.getFaultClassList());
			request.setAttribute("specialty", dao.getSpecialtyList());
			request.setAttribute("combinType", dao.getCombinTypeList());
			dao.release();
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("failure");
		}finally {
			dao.release();
		}
		return mapping.findForward("add");
	}

	private ActionForward performSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		SchemeMgrForm myForm = (SchemeMgrForm) form;
		if("0".equals(myForm.getTopicId())){
			String topicName = "数据上报";
			myForm.setTopicName(topicName);
		}
//		myForm.setAcceptDeptName(StaticMethod.strReverse(myForm
//				.getAcceptDeptName(), "ISO-8859-1", "UTF-8"));
//		myForm.setSendDeptName(StaticMethod.strReverse(
//				myForm.getSendDeptName(), "ISO-8859-1", "UTF-8"));
//		myForm.setTitle(StaticMethod.strReverse(myForm.getTitle(),
//				"ISO-8859-1", "UTF-8"));
//		myForm.setTopicName(StaticMethod.strReverse(myForm.getTopicName(),
//				"ISO-8859-1", "UTF-8"));
//		myForm.setReportDescription(StaticMethod.strReverse(myForm
//				.getReportDescription(), "ISO-8859-1", "UTF-8"));
//		myForm.setCycleDescription(StaticMethod.strReverse(myForm
//				.getCycleDescription(), "ISO-8859-1", "UTF-8"));
//		myForm.setAuditUserName(StaticMethod.strReverse(myForm
//				.getAuditUserName(), "ISO-8859-1", "UTF-8"));
//		myForm.setReportUserName(StaticMethod.strReverse(myForm
//				.getReportUserName(), "ISO-8859-1", "UTF-8"));
		// myForm.setAcceptDeptName(StaticMethod.strFromDBToPage(myForm.getAcceptDeptName()));
		// myForm.setAcceptDeptName(StaticMethod.strFromDBToPage(myForm.getAcceptDeptName()));
		String schemeId = null;
		String userId = saveSessionBeanForm.getUserid();
		myForm.setCreateUser(userId);
		SchemeMgrDAO dao = new SchemeMgrDAO();
		try {
			schemeId = dao.save(request.getRealPath(""), myForm);
		} catch (Exception e) {
			e.printStackTrace(); // To change body of catch statement use
									// File | Settings | File Templates.
			dao.release();
			return mapping.findForward("failure");
		}finally {
			dao.release();
		}
		dao.release();
		myForm.setSchemeId(schemeId);
//		return mapping.findForward("prepareView");
		return mapping.findForward("success");
	}

	private ActionForward performView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		SchemeMgrForm schemeForm = (SchemeMgrForm) form;
//		String schemeId = schemeForm.getSchemeId();
		String schemeId = request.getParameter("topicId");
		if (schemeId == null)
			return mapping.findForward("error");
		SchemeMgrDAO dao = new SchemeMgrDAO();
		SchemeMgrForm myForm = dao.getFormBean(schemeId);
		String topicId = myForm.getTopicId();
		if ("0".equals(topicId)){
			myForm.setTopicName("数据上报");
		}
		try {
			org.apache.commons.beanutils.BeanUtils.populate(schemeForm,
					org.apache.commons.beanutils.BeanUtils.describe(myForm));
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			dao.release();
		}
		schemeForm.setFileUrl(myForm.getFileUrl());
		dao.release();
		return mapping.findForward("view");
	}

	private ActionForward performEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		SchemeMgrForm schemeForm = (SchemeMgrForm) form;
//		String schemeId = schemeForm.getSchemeId();
		String schemeId = request.getParameter("schemeId");
		String s = "";
		String a = "";
		String r = "";
		if (schemeId == null)
			return mapping.findForward("error");
		SchemeMgrDAO dao = new SchemeMgrDAO();
		SchemeMgrForm myForm = dao.getFormBean(schemeId);
		try {
			org.apache.commons.beanutils.BeanUtils.populate(schemeForm,
					org.apache.commons.beanutils.BeanUtils.describe(myForm));
			s = dao.getAcceptDeptName(myForm);
			a = dao.getAuditUserName(myForm);//审核人
			r = dao.getReportUserName(myForm);//接收人
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		schemeForm.setAct("update");
		request.setAttribute("faultClass", dao.getFaultClassList());
		request.setAttribute("specialty", dao.getSpecialtyList());
		request.setAttribute("combinType", dao.getCombinTypeList());
		schemeForm.setFileUrl(myForm.getFileUrl());
		request.setAttribute("s", s);
		request.setAttribute("a", a);
		request.setAttribute("r", r);
		dao.release();
		return mapping.findForward("edit");
	}

	private ActionForward performList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		Vector list = new Vector();
		String pageOffset = "";
		String url = "";
		String pagerHeader = "";
		String userId = "admin";
		SchemeMgrDAO exp = new SchemeMgrDAO();
		String queried = request.getParameter("queried");
		try {
			int offset;
			int length = PAGE_LENGTH;
			// ��ǰ�ڼ�ҳ
			pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			int listStart = 0;
			if (offset == 0) {
				listStart = 0;
			} else {
				listStart = offset;
			}

			StringBuffer sql = new StringBuffer();
			if (queried != null)
				sql = (StringBuffer) request.getSession().getAttribute(
						"querySql");
			else {
				sql.append("select topic_id=d.topic_id  and a.user_id='"
						+ userId + "'order by a.order_id");
				request.getSession().setAttribute("querySql", sql);
			}
			System.out.println("query sql:" + sql);
			int size = exp.getResutCount(exp.getCountSql(sql));
			list = exp.getPage(listStart, PAGE_LENGTH, sql.toString(), size);
			url = request.getContextPath()
					+ "/report/PersonalReportAction.do?act=LIST&queried=1";
			pagerHeader = Pager.generate(offset, size, length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("reportList", list);
			exp.release();
		} catch (Exception e) {
			return mapping.findForward("failure");
		} catch (Throwable te) {
			te.printStackTrace();
		} finally {
			exp.release();
		}
		return mapping.findForward("list");
	}

	private ActionForward performDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ActionForward af = new ActionForward();
		String schemeId = request.getParameter("schemeId");
		SchemeMgrForm myForm = (SchemeMgrForm) form;
		myForm.setSchemeId(schemeId);
		String userId = null;
		// SaveSessionBeanForm saveSessionBeanForm =
		// (SaveSessionBeanForm) request.getSession().getAttribute(
		// "SaveSessionBeanForm");
		// if (saveSessionBeanForm == null) {
		// return mapping.findForward("timeout");
		// }
		// userId = saveSessionBeanForm.getWrf_UserID();
		SchemeMgrDAO dao = new SchemeMgrDAO();
		dao.delete(request.getRealPath(""), myForm);
		dao.release();
		return mapping.findForward("success");
//		return mapping.findForward("prepareAdd");
	}

	public ActionForward performUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SchemeMgrForm schemeForm = (SchemeMgrForm) form;
		String userId = null;
		SchemeMgrDAO dao = new SchemeMgrDAO();
		try {
//			schemeForm.setAcceptDeptName(StaticMethod.strReverse(schemeForm
//					.getAcceptDeptName(), "ISO-8859-1", "UTF-8"));
//			schemeForm.setSendDeptName(StaticMethod.strReverse(schemeForm
//					.getSendDeptName(), "ISO-8859-1", "UTF-8"));
//			schemeForm.setTitle(StaticMethod.strReverse(schemeForm.getTitle(),
//					"ISO-8859-1", "UTF-8"));
//			schemeForm.setTopicName(StaticMethod.strReverse(schemeForm
//					.getTopicName(), "ISO-8859-1", "UTF-8"));
//			schemeForm.setReportDescription(StaticMethod.strReverse(schemeForm
//					.getReportDescription(), "ISO-8859-1", "UTF-8"));
//			schemeForm.setCycleDescription(StaticMethod.strReverse(schemeForm
//					.getCycleDescription(), "ISO-8859-1", "UTF-8"));
//			schemeForm.setAuditUserName(StaticMethod.strReverse(schemeForm
//					.getAuditUserName(), "ISO-8859-1", "UTF-8"));
//			schemeForm.setReportUserName(StaticMethod.strReverse(schemeForm
//					.getReportUserName(), "ISO-8859-1", "UTF-8"));
			dao.update(request.getRealPath(""), schemeForm);
		} catch (Exception e) {
			e.printStackTrace(); // To change body of catch statement use
									// File | Settings | File Templates.
		}
		SchemeMgrForm myForm = dao.getFormBean(schemeForm.getSchemeId());
		try {
			org.apache.commons.beanutils.BeanUtils.populate(schemeForm,
					org.apache.commons.beanutils.BeanUtils.describe(myForm));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		dao.release();
		schemeForm.setFileUrl(myForm.getFileUrl());
		return mapping.findForward("success");
//		return mapping.findForward("view");
	}
}
