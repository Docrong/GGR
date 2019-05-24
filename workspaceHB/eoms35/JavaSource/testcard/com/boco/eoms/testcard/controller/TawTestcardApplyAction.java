package com.boco.eoms.testcard.controller;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.webapp.action.BaseAction;

import java.sql.*;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.*;
import org.apache.struts.util.LabelValueBean;

import com.boco.eoms.common.util.Pager;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.testcard.bo.TawTestcardBO;
import com.boco.eoms.testcard.dao.TawEventDicDAO;
import com.boco.eoms.testcard.dao.TawTestcardDAO;
import com.boco.eoms.testcard.dao.TawTestcardApplyDAO;
import com.boco.eoms.testcard.model.TawEventDic;
import com.boco.eoms.testcard.model.TawTestcardApply;
import com.boco.eoms.testcard.model.TawTestcard;
import com.boco.eoms.testcard.model.TawTestcardApplyCardRel;
import com.boco.eoms.testcard.model.TawTestcardTask;
import com.boco.eoms.testcard.model.TawTestcardAuditInfo;
import com.boco.eoms.testcard.util.StaticValue;

public class TawTestcardApplyAction extends BaseAction {
	private com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
			.getInstance();

	/**
	 * ��ʾ�ļ�¼����
	 */
	private static int PAGE_LENGTH = 10;

	private static int PAGE_LENGTH_FORM = 15;

	static ResourceBundle prop = null;

	private String user_id = "";
	static {
		prop = ResourceBundle.getBundle("resources.application_zh_CN");
		try {
			PAGE_LENGTH = Integer.parseInt(prop.getString("list.page.length"));
		} catch (Exception e) {
		}
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ActionForward myforward = null;
		String myaction = mapping.getParameter();

		try {
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");

			if (sessionform == null)
				return mapping.findForward("timeout");
			user_id = StaticMethod.null2String(sessionform.getUserid());
		} catch (Exception ee) {
			ee.printStackTrace();
		}

		// Ȩ����֤
		try {
			/*
			 * TawValidatePrivBO tawValidateBO = new TawValidatePrivBO(ds); if
			 * (!tawValidateBO.validPriv(user_id, mapping.getPath())) return
			 * mapping.findForward("nopriv");
			 */
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure");
		} else if ("ADD".equalsIgnoreCase(myaction)) {
			myforward = performAdd(mapping, form, request, response);
		} else if ("EDIT".equalsIgnoreCase(myaction)) {
			myforward = performEdit(mapping, form, request, response);
		} else if ("SAVE".equalsIgnoreCase(myaction)) {
			myforward = performSave(mapping, form, request, response);
		} else if ("VIEW".equalsIgnoreCase(myaction)) {
			myforward = performView(mapping, form, request, response);
		} else if ("SEARCH".equalsIgnoreCase(myaction)) {
			myforward = performSearch(mapping, form, request, response);
		} else if ("SEARCHDO".equalsIgnoreCase(myaction)) {
			myforward = performSearchdo(mapping, form, request, response);
		} else if ("DEL".equalsIgnoreCase(myaction)) {
			myforward = performDel(mapping, form, request, response);
		} else if ("DELDO".equalsIgnoreCase(myaction)) {
			myforward = performDelDo(mapping, form, request, response);
		} else if ("SEARCHCARD".equalsIgnoreCase(myaction)) {
			myforward = performSearchCard(mapping, form, request, response);
		} else if ("SEARCHCARDDO".equalsIgnoreCase(myaction)) {
			myforward = performSearchCardDo(mapping, form, request, response);
		} else if ("AUDIT".equalsIgnoreCase(myaction)) {
			myforward = performAudit(mapping, form, request, response);
		} else if ("AUDITPAGE".equalsIgnoreCase(myaction)) {
			myforward = performAuditPage(mapping, form, request, response);
		} else if ("SUBMITAUDIT".equalsIgnoreCase(myaction)) {
			myforward = performSubmitAudit(mapping, form, request, response);
		} else if ("SUBMITCARDS".equalsIgnoreCase(myaction)) {
			myforward = performSubmitCards(mapping, form, request, response);
		} else if ("LISTAUDITINFO".equalsIgnoreCase(myaction)) {
			myforward = performListAuditInfo(mapping, form, request, response);
		} else if ("LIST".equalsIgnoreCase(myaction)) {
			myforward = performList(mapping, form, request, response);
		} else {
			myforward = mapping.findForward("failure");
		}
		return myforward;

	}

	/**
	 * @see 新增申请单页面������Կ�
	 */
	private ActionForward performAdd(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardApplyForm form = (TawTestcardApplyForm) actionForm;
		TawSystemSessionForm sessionform = null;
		sessionform = (TawSystemSessionForm) request.getSession().getAttribute(
				"sessionform");
		if (sessionform == null) {
			return mapping.findForward("timeout");
		}
		String deptid = sessionform.getDeptid();
		request.setAttribute("tawTestcardForm", form);
		TawTestcardBO bo = new TawTestcardBO();
		try {
			String StringTree = bo.getMyTreeStr();
			if (!StringTree.equals("")) {
				request.setAttribute("StringTree", StringTree);
			}
			TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
			ITawSystemDeptManager mgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager"); // 部门

			int tempdeptid = tawEventDicDAO.isprovince(StaticMethod
					.null2int(deptid));
//			while (tempdeptid == -1) {
//				TawSystemDept tawSystemDept = mgr.getTawSystemDept(new Integer(
//						Integer.parseInt(deptid)));
//				deptid = tawSystemDept.getParentDeptid();// 取这个部门的父部门
//				tempdeptid = tawEventDicDAO
//						.isprovince(Integer.parseInt(deptid));
//			}
			deptid = String.valueOf(tempdeptid);
			Vector vm1 = (Vector) tawEventDicDAO.list("test_leave",
					StaticMethod.null2int(deptid));
			if (vm1 == null || vm1.size() == 0) {
				//return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vm1.size());
				int size = vm1.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vm1.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				entries.add(new LabelValueBean("", ""));
				form.setBeanCollectionDN(entries);
			}
			Vector vmoper = (Vector) tawEventDicDAO.list("testcard_oper");
			if (vmoper == null || vmoper.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vmoper.size());
				int size = vmoper.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vmoper.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				entries.add(new LabelValueBean("", ""));
				form.setBeCollec(entries);
			}
			// 套餐
			Vector vmpackage = (Vector) tawEventDicDAO.list("packages");
			if (vmpackage == null || vmpackage.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vmpackage.size());
				int size = vmpackage.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vmpackage.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				entries.add(new LabelValueBean("", ""));
				form.setBeCollep(entries);
			}
			form.setStrutsAction(TawTestcardForm.ADD);
		} catch (Exception e) {
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

	/**
	 * 修改申请单页面
	 */
	private ActionForward performEdit(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardApplyForm form = (TawTestcardApplyForm) actionForm;
		TawSystemSessionForm sessionform = null;
		sessionform = (TawSystemSessionForm) request.getSession().getAttribute(
				"sessionform");
		if (sessionform == null) {
			return mapping.findForward("timeout");
		}
		try {
			int id = StaticMethod.nullObject2int(request.getParameter("id"));
			TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
			Vector vm1 = (Vector) tawEventDicDAO.list("test_leave");
			if (vm1 == null || vm1.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vm1.size());
				int size = vm1.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vm1.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				form.setBeanCollectionDN(entries);
			}

			Vector vmoper = (Vector) tawEventDicDAO.list("testcard_oper");
			if (vmoper == null || vmoper.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vmoper.size());
				int size = vmoper.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vmoper.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				entries.add(new LabelValueBean("", ""));
				form.setBeCollec(entries);
			}
			// 套餐
			Vector vmpackage = (Vector) tawEventDicDAO.list("packages");
			if (vmpackage == null || vmpackage.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vmpackage.size());
				int size = vmpackage.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vmpackage.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				entries.add(new LabelValueBean("", ""));
				form.setBeCollep(entries);
			}
			TawTestcardApplyDAO Dao = new TawTestcardApplyDAO(ds);
			TawTestcardApply tawTestcardApply = new TawTestcardApply();
			List cards = new ArrayList();
			if (id == 0) {
				tawTestcardApply = Dao.retrieve(form.getId());
				cards = Dao.getCardFromFormId(form.getId());
			} else {
				tawTestcardApply = Dao.retrieve(id);
				cards = Dao.getCardFromFormId(id);
			}
			org.apache.commons.beanutils.BeanUtils.populate(form,
					org.apache.commons.beanutils.BeanUtils
							.describe(tawTestcardApply));
			form.setStrutsAction(TawTestcardApplyForm.EDIT);
			request.setAttribute("tawTestcardApplyForm", form);
			request.setAttribute("jsonAudit", form.getAuditJson());
			request.setAttribute("tawTestcard", cards);
		} catch (Exception e) {
			System.out.println("取数据错误，请检查");
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see 保存申请单������Կ�
	 */
	private ActionForward performSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String forword = "";
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userid = sessionform.getUserid();
		TawTestcardApplyForm form = (TawTestcardApplyForm) actionForm;
		TawTestcardApplyDAO Dao = new TawTestcardApplyDAO(ds);
		TawTestcardApply tawTestcardApply = new TawTestcardApply();
		try {
			org.apache.commons.beanutils.BeanUtils.populate(tawTestcardApply,
					org.apache.commons.beanutils.BeanUtils.describe(form));
			tawTestcardApply.setUserId(sessionform.getUserid());
			tawTestcardApply.setDeptId(sessionform.getDeptid());
			tawTestcardApply.setInsertTime(StaticMethod.getLocalString());
			tawTestcardApply.setStatus(StaticValue.STATUS_DRAFT);
			if (form.getStrutsAction() == TawTestcardApplyForm.ADD) {
				Dao.insert(tawTestcardApply);
				forword = "successsave";
				request.setAttribute("leaveid", form.getLeaveid());
			} else if (form.getStrutsAction() == TawTestcardApplyForm.EDIT) {
				Dao.update(tawTestcardApply);
				forword = "successupdate";
			}
			int id = Dao.getFormId(userid);
			request.setAttribute("formId", Integer.toString(id));
		} catch (Exception e) {
			System.out.println("保存据错误，请检查");
			e.printStackTrace();
		}
		return mapping.findForward(forword);
	}

	/**
	 * @see 查询申请单������Կ�
	 */
	private ActionForward performSearch(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawSystemSessionForm sessionform = null;
		sessionform = (TawSystemSessionForm) request.getSession().getAttribute(
				"sessionform");
		if (sessionform == null) {
			return mapping.findForward("timeout");
		}
		TawTestcardApplyForm form = (TawTestcardApplyForm) actionForm;
		String deptid = sessionform.getDeptid();
		TawEventDicDAO tawEventDicDAO = new TawEventDicDAO(ds);
		try {
			Vector vm1 = (Vector) tawEventDicDAO.list("test_leave",
					StaticMethod.null2int(deptid));
			if (vm1 == null || vm1.size() == 0) {
				//return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vm1.size());
				int size = vm1.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vm1.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				entries.add(new LabelValueBean("", ""));
				form.setBeanCollectionDN(entries);
			}
			Vector vmoper = (Vector) tawEventDicDAO.list("testcard_oper");
			if (vmoper == null || vmoper.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vmoper.size());
				int size = vmoper.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vmoper.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				entries.add(new LabelValueBean("", ""));
				form.setBeCollec(entries);
			}
			// 套餐
			Vector vmpackage = (Vector) tawEventDicDAO.list("packages");
			if (vmpackage == null || vmpackage.size() == 0) {
				// return mapping.findForward("failure");
			} else {
				Vector entries = new Vector(vmpackage.size());
				int size = vmpackage.size();
				for (int i = 0; i < size; i++) {
					TawEventDic vm2 = (TawEventDic) vmpackage.elementAt(i);
					String sId = (Integer.toString(vm2.getId()));
					entries
							.add(new LabelValueBean((String) vm2.getName(), sId));
				}
				entries.add(new LabelValueBean("", ""));
				form.setBeCollep(entries);
			}
		} catch (Exception e) {
			System.out.println("保存据错误，请检查");
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see 查询申请单������Կ�
	 */
	private ActionForward performSearchdo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		int length = PAGE_LENGTH_FORM;
		int offset = StaticMethod.nullObject2int(request
				.getParameter("pager.offset"), 0);
		int size = StaticMethod.nullObject2int(request
				.getParameter("pager.size"), 0);
		int[] pagePra = { offset, length, size };
		String userid = sessionform.getUserid();
		TawTestcardApplyForm from = (TawTestcardApplyForm) actionForm;
		TawTestcardApplyDAO Dao = new TawTestcardApplyDAO(ds);
		String sql = Dao.getSearchSql(from, userid);
		try {
			List applyForm = Dao.getForms(pagePra, from, userid);
			pagePra[2] = Dao.getFormsCount(from, sessionform.getUserid());
			String url = request.getContextPath() + "/eventmanager"
					+ mapping.getPath() + ".do?sql=" + sql;
			String pagerHeader = Pager.generate(pagePra[0], pagePra[2],
					pagePra[1], url);
			request.setAttribute("applyForm", applyForm);
			request.setAttribute("status", from.getStatus());
			request.setAttribute("pagerHeader", pagerHeader);
		} catch (Exception e) {
			System.out.println("查询据错误，请检查");
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see 删除申请单 ������Կ�
	 */
	private ActionForward performDel(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardApplyForm form = (TawTestcardApplyForm) actionForm;
		int id = StaticMethod.nullObject2int(request.getParameter("id"));
		TawTestcardApplyDAO Dao = new TawTestcardApplyDAO(ds);
		try {
			TawTestcardApply tawTestcardApply = Dao.retrieve(id);
			org.apache.commons.beanutils.BeanUtils.populate(form,
					org.apache.commons.beanutils.BeanUtils
							.describe(tawTestcardApply));
			List tawTestcard = Dao.getCardFromFormId(id);
			request.setAttribute("tawTestcard", tawTestcard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see 删除申请单������Կ�
	 */
	private ActionForward performDelDo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardApplyForm form = (TawTestcardApplyForm) actionForm;
		TawTestcardApplyDAO Dao = new TawTestcardApplyDAO(ds);
		TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
		try {
			// 将与申请单相关的测试卡状态修改为未申请
			List list = Dao.getCardFromFormId(form.getId());
			for (int i = 0; i < list.size(); i++) {
				TawTestcard cardTemp = (TawTestcard) list.get(i);
				cardTemp.setState(StaticValue.STATUS_UNREQUEST);
				tawTestcardDAO.update(cardTemp);
			}
			// 删除申请单的信息
			Dao.delete(form.getId());
			// 删除关联信息
			Dao.deleteRel(form.getId());
			// 删除相关的审核任务信息
			Dao.deleteTask(form.getId());
		} catch (Exception e) {
			System.out.println("删除据错误，请检查");
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see 查询未激活的测试卡������Կ�
	 */
	private ActionForward performSearchCard(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String id = StaticMethod.null2String(request.getParameter("formId"));
		String leaveid = StaticMethod.null2String(request
				.getParameter("leaveid"));
		request.setAttribute("leaveid", leaveid);
		request.setAttribute("formId", id);
		return mapping.findForward("success");
	}

	/**
	 * @see 查询未激活的测试卡������Կ�
	 */
	private ActionForward performSearchCardDo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardApplyForm form = (TawTestcardApplyForm) actionForm;
		String pageOffset = null;
		List tawTestcard = null;
		String url = null;
		String pagerHeader = null;
		int formId = form.getId();
		if (formId == 0) {
			formId = StaticMethod
					.nullObject2int(request.getParameter("formId"));
		}
		String condition = StaticMethod.nullObject2String(request
				.getParameter("condition"));
		if (condition.equals("")) {
			if (!StaticMethod.nullObject2String(form.getPhoneNumber()).equals(
					"")) {
				condition += " and phone_number like '%"
						+ form.getPhoneNumber() + "%' ";
			}
			if (!StaticMethod.nullObject2String(form.getLeaveid()).equals("")) {
				condition += " and leave='" + form.getLeaveid() + "' ";
			}
			condition += " and is_alive = 0 and state = '-1'";
		}
		try {
			TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
			int offset;
			int length = 15;
			pageOffset = request.getParameter("pager.offset");
			if (pageOffset == null || pageOffset.equals("")) {
				offset = 0;
			} else {
				offset = Integer.parseInt(pageOffset);
			}
			int[] size = { 0 };
			tawTestcard = tawTestcardDAO.list(offset, length, size, condition);
			url = request.getContextPath() + "/eventmanager"
					+ mapping.getPath() + ".do?condition=" + condition
					+ "&formId=" + formId;
			pagerHeader = Pager.generate(offset, tawTestcardDAO
					.getLengh(condition), length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("tawTestcard", tawTestcard);
			request.setAttribute("formId", Integer.toString(formId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see 查看申请单信息������Կ�
	 */
	private ActionForward performView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardApplyForm form = (TawTestcardApplyForm) actionForm;
		int id = StaticMethod.nullObject2int(request.getParameter("id"));
		TawTestcardApplyDAO Dao = new TawTestcardApplyDAO(ds);
		try {
			TawTestcardApply tawTestcardApply = Dao.retrieve(id);
			org.apache.commons.beanutils.BeanUtils.populate(form,
					org.apache.commons.beanutils.BeanUtils
							.describe(tawTestcardApply));
			List tawTestcard = Dao.getCardFromFormId(id);
			String auditOrg = tawTestcardApply.getAuditJson();
			List list = jsonOrg2Audit(auditOrg, 0, 0);// 在view页面上显示提交审核组织的名称
			String orgName = "";
			for (int i = 0; i < list.size(); i++) {
				TawTestcardTask task = (TawTestcardTask) list.get(i);
				if (task.getAuditType().equals(
						StaticVariable.PRIV_ASSIGNTYPE_USER)) {
					ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
							.getInstance().getBean("itawSystemUserManager");
					orgName += userMgr.getUserByuserid(task.getAuditOrg())
							.getUsername()
							+ "(用户),";
				} else if (task.getAuditType().equals(
						StaticVariable.PRIV_ASSIGNTYPE_ROLE)) {
					ITawSystemSubRoleManager roleMgr = (ITawSystemSubRoleManager) ApplicationContextHolder
							.getInstance().getBean("ItawSystemSubRoleManager");
					orgName += roleMgr.getTawSystemSubRole(task.getAuditOrg())
							.getSubRoleName()
							+ "(角色),";
				} else if (task.getAuditType().equals(
						StaticVariable.PRIV_ASSIGNTYPE_DEPT)) {
					ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager"); // 部门
					orgName += deptMgr.getDeptinfobydeptid(task.getAuditOrg(),
							"0")
							+ "(部门),";
				}
			}
			if (orgName.length() > 0) {
				orgName = orgName.substring(0, orgName.length() - 1);
			}
			form.setAuditOrgName(orgName);
			request.setAttribute("tawTestcard", tawTestcard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see 查看申请单信息������Կ�
	 */
	private ActionForward performAuditPage(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardApplyForm form = (TawTestcardApplyForm) actionForm;
		int id = StaticMethod.nullObject2int(request.getParameter("id"));
		int taskId = StaticMethod
				.nullObject2int(request.getParameter("taskId"));
		TawTestcardApplyDAO Dao = new TawTestcardApplyDAO(ds);
		try {
			TawTestcardApply tawTestcardApply = Dao.retrieve(id);
			List tawTestcard = Dao.getCardFromFormId(id);
			org.apache.commons.beanutils.BeanUtils.populate(form,
					org.apache.commons.beanutils.BeanUtils
							.describe(tawTestcardApply));
			String auditOrg = tawTestcardApply.getAuditJson();
			List list = jsonOrg2Audit(auditOrg, 0, 0);// 在view页面上显示提交审核组织的名称
			String orgName = "";
			for (int i = 0; i < list.size(); i++) {
				TawTestcardTask task = (TawTestcardTask) list.get(i);
				if (task.getAuditType().equals(
						StaticVariable.PRIV_ASSIGNTYPE_USER)) {
					ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
							.getInstance().getBean("itawSystemUserManager");
					orgName += userMgr.getUserByuserid(task.getAuditOrg())
							.getUsername()
							+ "(用户),";
				} else if (task.getAuditType().equals(
						StaticVariable.PRIV_ASSIGNTYPE_ROLE)) {
					ITawSystemSubRoleManager roleMgr = (ITawSystemSubRoleManager) ApplicationContextHolder
							.getInstance().getBean("ItawSystemSubRoleManager");
					orgName += roleMgr.getTawSystemSubRole(task.getAuditOrg())
							.getSubRoleName()
							+ "(角色),";
				} else if (task.getAuditType().equals(
						StaticVariable.PRIV_ASSIGNTYPE_DEPT)) {
					ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager"); // 部门
					orgName += deptMgr.getDeptinfobydeptid(task.getAuditOrg(),
							"0")
							+ "(部门),";
				}
			}
			if (orgName.length() > 0) {
				orgName = orgName.substring(0, orgName.length() - 1);
			}
			form.setAuditOrgName(orgName);
			request.setAttribute("tawTestcard", tawTestcard);
			request.setAttribute("formId", Integer.toString(id));
			request.setAttribute("taskId", Integer.toString(taskId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see 审批 ������Կ�
	 */
	private ActionForward performAudit(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		TawTestcardApplyForm form = (TawTestcardApplyForm) actionForm;
		TawTestcardApplyDAO Dao = new TawTestcardApplyDAO(ds);
		TawTestcardDAO tawTestcardDAO = new TawTestcardDAO(ds);
		try {
			TawTestcardApply tawTestcardApply = Dao.retrieve(form.getFormId());
			TawTestcardTask task = Dao.getTaskByTaskId(form.getTaskId());
			if (form.getAuditflag().equals(StaticValue.STATUS_PASS)) {// 审核通过
				// 修改任务的执行状态为已执行
				task.setTaskStatus(StaticValue.STATUS_EXECUTEED);
				Dao.updateTask(task);
				// 修改单据的状态为审核通过
				tawTestcardApply.setStatus(StaticValue.STATUS_PASS);
				Dao.update(tawTestcardApply);
				// 给相关测试增加套餐和卡类型的属性
				List list = Dao.getCardFromFormId(tawTestcardApply.getId());
				for (int i = 0; i < list.size(); i++) {
					TawTestcard card = (TawTestcard) list.get(i);
					card.setCardpackage(tawTestcardApply.getCardpackage());
					card.setCardType(tawTestcardApply.getCardtype());
					card.setState(StaticValue.STATUS_PASS);
					tawTestcardDAO.update(card);
				}
				// 增加审核意见
				TawTestcardAuditInfo auditInfo = new TawTestcardAuditInfo();
				auditInfo.setAuditInfo(form.getAuditInfo());
				auditInfo.setAuditTime(StaticMethod.getLocalString());
				auditInfo.setAuditUser(sessionform.getUserid());
				auditInfo.setStatus(StaticValue.STATUS_PASS);
				auditInfo.setFormId(form.getFormId());

				Dao.insertAuditInfo(auditInfo);
			} else if (form.getAuditflag().equals(StaticValue.STATUS_REJECT)) {// 驳回
				// 修改任务的执行状态为已执行
				task.setTaskStatus(StaticValue.STATUS_EXECUTEED);
				Dao.updateTask(task);
				// 修改单据的状态为驳回
				tawTestcardApply.setStatus(StaticValue.STATUS_REJECT);
				Dao.update(tawTestcardApply);
				/* modiby by HN_yuanzhijun */
				/* 修改备注：
				 * 由于驳回后，取"被驳回"列表时，系统并没有用到taw_testcard_task表，所以实际上在这里生成一个
				 * 驳回处理任务是多余的。并且如果这个审批申请是自己给自己申请的话，那么在后面重新提交申请后，
				 * 将会在待审批列表中看到两条重复的记录。
				 */
				// 新增驳回的处理任务
//				TawTestcardTask taskTemp = new TawTestcardTask();
//				taskTemp.setAuditOrg(tawTestcardApply.getUserId());
//				taskTemp.setAuditType(StaticVariable.PRIV_ASSIGNTYPE_USER);
//				taskTemp.setFormId(tawTestcardApply.getId());
//				taskTemp.setTaskStatus(StaticValue.STATUS_EXECUTE);
//				Dao.insertTask(taskTemp);
				/* end by HN_yuanzhijun */
				// 增加审核意见
				TawTestcardAuditInfo auditInfo = new TawTestcardAuditInfo();
				auditInfo.setAuditInfo(form.getAuditInfo());
				auditInfo.setAuditTime(StaticMethod.getLocalString());
				auditInfo.setAuditUser(sessionform.getUserid());
				auditInfo.setFormId(form.getFormId());
				auditInfo.setStatus(StaticValue.STATUS_REJECT);
				Dao.insertAuditInfo(auditInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see 提交审批
	 */
	private ActionForward performSubmitAudit(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String forword = "";
		TawTestcardApplyForm form = (TawTestcardApplyForm) actionForm;
		int id = StaticMethod.nullObject2int(request.getParameter("formId"));
		TawTestcardApplyDAO Dao = new TawTestcardApplyDAO(ds);
		if (id == 0) {
			id = form.getId();
		}
		try {
			List list = Dao.getCardFromFormId(id);
			if (list.size() > 0) {
				Dao.updateStatus(id, StaticValue.STATUS_WAIT);
				TawTestcardApply tawTestcardApply = Dao.retrieve(id);
				List auditList = this.jsonOrg2Audit(tawTestcardApply
						.getAuditJson(), id, StaticValue.STATUS_EXECUTE);
				for (int i = 0; i < auditList.size(); i++) {
					TawTestcardTask task = (TawTestcardTask) auditList.get(i);
					Dao.insertTask(task);
				}
				forword = "success";
			} else {
				forword = "nocard";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(forword);
	}

	/**
	 * @see ������Կ�
	 */
	private ActionForward performSubmitCards(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		int formId = StaticMethod
				.nullObject2int(request.getParameter("formId"));
		String idsTemp = StaticMethod.nullObject2String(request
				.getParameter("ids"));
		TawTestcardApplyDAO Dao = new TawTestcardApplyDAO(ds);
		String[] ids = idsTemp.split(",");
		try {
			// 修正原来所选择的测试卡状态，并从申请单与测试卡关系表中删除原有数据（修改申请单改变所选测试卡时生效）
			List list = Dao.getCardFromFormId(formId);
			for (int j = 0; j < list.size(); j++) {
				TawTestcard cardTemp = (TawTestcard) list.get(j);
				Dao.upateCardState(StaticValue.STATUS_UNREQUEST, cardTemp
						.getId());
			}
			Dao.deleteRel(formId);
			for (int i = 0; i < ids.length; i++) {
				// 保存申请单与测试卡之间的关系
				TawTestcardApplyCardRel rel = new TawTestcardApplyCardRel();
				rel.setCardId(Integer.parseInt(ids[i]));
				rel.setFormId(formId);
				Dao.insertRel(rel);

				// 修改测试卡状态为占用
				Dao.upateCardState(StaticValue.STATUS_DRAFT, Integer
						.parseInt(ids[i]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ������Կ�
	 */
	private ActionForward performListAuditInfo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawTestcardApplyDAO Dao = new TawTestcardApplyDAO(ds);
		int id = StaticMethod.nullObject2int(request.getParameter("id"));
		try {
			List listAuditInfo = Dao.getAuditInfoByFormId(id);
			request.setAttribute("auditInfo", listAuditInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * @see ������Կ�
	 */
	private ActionForward performList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		int length = PAGE_LENGTH_FORM;
		int offset = StaticMethod.nullObject2int(request
				.getParameter("pager.offset"), 0);
		int size = StaticMethod.nullObject2int(request
				.getParameter("pager.size"), 0);
		int[] pagePra = { offset, length, size };
		TawTestcardApplyDAO Dao = new TawTestcardApplyDAO(ds);
		String status = StaticMethod.nullObject2String(request
				.getParameter("status"));
		try {
			List applyForm = Dao.getAuditForm(pagePra, status, sessionform
					.getUserid());
			pagePra[2] = Dao.getAuditFormCount(status, sessionform.getUserid());
			request.setAttribute("applyForm", applyForm);
			String url = request.getContextPath() + "/eventmanager"
					+ mapping.getPath() + ".do?status=" + status;
			String pagerHeader = Pager.generate(pagePra[0], pagePra[2],
					pagePra[1], url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("status", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("success");
	}

	/**
	 * 从json中取审批组织
	 * 
	 * @param auditers
	 *            json串
	 * @param threadId
	 *            信息ID
	 * @return 审批组织列表
	 */
	private List jsonOrg2Audit(String org, int formId, int status) {
		JSONArray jsonAudit = JSONArray.fromString(org);
		List auditList = new ArrayList();
		for (Iterator it = jsonAudit.iterator(); it.hasNext();) {
			JSONObject audit = (JSONObject) it.next();
			// 审核组织id
			String orgId = audit.getString(UIConstants.JSON_ID);
			// 节点类型
			String nodeType = audit.getString(UIConstants.JSON_NODETYPE);
			//
			// 写入orgList，供保存
			TawTestcardTask task = new TawTestcardTask();
			if (StaticValue.ORG_USER.equals(nodeType)
					|| StaticVariable.PRIV_ASSIGNTYPE_USER.equals(nodeType)) {
				task.setAuditOrg(orgId);
				task.setAuditType(StaticVariable.PRIV_ASSIGNTYPE_USER);
				task.setFormId(formId);
				task.setTaskStatus(status);
				auditList.add(task);
			} else if (StaticValue.ORG_DEPT.equals(nodeType)
					|| StaticVariable.PRIV_ASSIGNTYPE_DEPT.equals(nodeType)) {
				task.setAuditOrg(orgId);
				task.setAuditType(StaticVariable.PRIV_ASSIGNTYPE_DEPT);
				task.setFormId(formId);
				task.setTaskStatus(status);
				auditList.add(task);
			} else if (StaticValue.ORG_ROLE.equals(nodeType)
					|| StaticVariable.PRIV_ASSIGNTYPE_ROLE.equals(nodeType)) {
				task.setAuditOrg(orgId);
				task.setAuditType(StaticVariable.PRIV_ASSIGNTYPE_ROLE);
				task.setFormId(formId);
				task.setTaskStatus(status);
				auditList.add(task);
			}
		}
		return auditList;
	}

}
