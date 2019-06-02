package com.boco.eoms.duty.faultrecord.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.log.BocoLog;
import com.boco.eoms.common.util.MyBeanUtils;
import com.boco.eoms.common.util.Pager;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.duty.faultrecord.bo.FaultrecordBO;
import com.boco.eoms.duty.faultrecord.model.Faultrecord;
import com.boco.eoms.duty.faultrecord.vo.FaultrecordVO;
import com.boco.eoms.duty.faultrecord.qo.FaultrecordQO;
import com.boco.eoms.duty.util.DutyConstacts;
import com.boco.eoms.workbench.memo.util.MemoPage;

/**
 * <p>
 * Title: ���ϼ�¼
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author zhangxiaobo
 * @version 1.0
 */

public class FaultrecordAction extends BaseAction {

	// �����½�û��ĸ�����Ϣ��form
	private TawSystemSessionForm saveSessionBeanForm;

	private static int PAGE_LENGTH = 10;

	/**
	 * action ��ͨ����ת����
	 * 
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward myforward = null;

		// ��ȡ�����action����
		String myaction = mapping.getParameter();

		// session��ʱ����
		try {
			saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
					.getAttribute("sessionform");
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ����û�����ҳ�����󣬽���ҳ����ת
		if (isCancelled(request)) {
			return mapping.findForward("cancel"); // ��Ч������ת�����ҳ��
		} else if ("".equalsIgnoreCase(myaction)) {
			myforward = mapping.findForward("failure"); // ���Ϊ�գ�ת���ҳ
		} else if ("NEW".equalsIgnoreCase(myaction)) { // ����
			myforward = performNew(mapping, actionForm, request, response);
		} else if ("SAVE".equalsIgnoreCase(myaction)) { // ��������
			myforward = performSave(mapping, actionForm, request, response);
		} else if ("EDIT".equalsIgnoreCase(myaction)) { // �޸�
			myforward = performEdit(mapping, actionForm, request, response);
		} else if ("UPDATE".equalsIgnoreCase(myaction)) { // �����޸�
			myforward = performUpdate(mapping, actionForm, request, response);
		} else if ("VIEW".equalsIgnoreCase(myaction)) { // �鿴��Ϣ
			myforward = performView(mapping, actionForm, request, response);
		} else if ("LIST".equalsIgnoreCase(myaction)) { // ��������б�
			myforward = performList(mapping, actionForm, request, response);
		} else if ("DEL".equalsIgnoreCase(myaction)) { // ɾ��
			myforward = performDel(mapping, actionForm, request, response);
		} else if ("DELDO".equalsIgnoreCase(myaction)) { // ȷ��ɾ��
			myforward = performDelDo(mapping, actionForm, request, response);
		} else if ("SEARCH".equalsIgnoreCase(myaction)) { // ��ѯ
			myforward = performSearch(mapping, actionForm, request, response);
		} else if ("SEARCHDO".equalsIgnoreCase(myaction)) { // ִ�в�ѯ
			myforward = performSearchDo(mapping, actionForm, request, response);
		} else if ("STAT".equalsIgnoreCase(myaction)) { // ͳ��
			myforward = performStat(mapping, actionForm, request, response);
		} else if ("STATDO".equalsIgnoreCase(myaction)) { // ִ��ͳ��
			myforward = performStatDo(mapping, actionForm, request, response);
		} else if ("VIEWSTAT".equalsIgnoreCase(myaction)) { // ִ��ͳ��
			myforward = performViewStat(mapping, actionForm, request, response);
		} else if ("INPUTEXCEL".equalsIgnoreCase(myaction)) { // EXCEL����
			myforward = performInputExcel(mapping, actionForm, request,
					response);
		} else if ("INPUTEXCELDO".equalsIgnoreCase(myaction)) { // ִ��EXCEL����
			myforward = performInputExcelDo(mapping, actionForm, request,
					response);
		} else {
			myforward = mapping.findForward("failure"); // ��Ч������ת�����ҳ��
		}
		return myforward;
	}

	/**
	 * @����һ���¼��ִ�����ҳ����ת����Ϣ¼��ҳ��
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performNew(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String userid = saveSessionBeanForm.getUserid();
			String deptid = saveSessionBeanForm.getDeptid();

			ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
					.getInstance().getBean("itawSystemUserManager");

			String username = userMgr.getUserByuserid(userid).getUsername();
			String deptname = userMgr.getUserByuserid(userid).getDeptname();

			request.setAttribute("username", username);
			request.setAttribute("deptname", deptname);
			request.setAttribute("userid", userid);
			request.setAttribute("deptid", String.valueOf(deptid));

			return mapping.findForward("success");
		} catch (Exception e) {
			BocoLog.error(this, 0, "FaultrecordAction.performNew", e);
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @���������һ���¼
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performSave(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			FaultrecordBO faultrecordBO = (FaultrecordBO) getBean("FaultrecordBO");

			faultrecordBO.setActionFormMap(((DynaActionForm) actionForm)
					.getMap());
			faultrecordBO.saveInfo();

			return mapping.findForward("success");
		} catch (Exception e) {
			BocoLog.error(this, 0, "FaultrecordAction.performSave", e);
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @�༭һ���¼
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performEdit(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String id = request.getParameter("id");
			FaultrecordBO faultrecordBO = (FaultrecordBO) getBean("FaultrecordBO");
			FaultrecordVO faultrecord = faultrecordBO.viewInfo(id);

			request.setAttribute("BASEINFOVO", faultrecord);

			return mapping.findForward("success");
		} catch (Exception e) {
			BocoLog.error(this, 0, "FaultrecordAction.performEdit", e);
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @����Ҫ�޸ļ�¼
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performUpdate(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			FaultrecordBO faultrecordBO = (FaultrecordBO) getBean("FaultrecordBO");
			faultrecordBO.setActionFormMap(((DynaActionForm) actionForm)
					.getMap());
			faultrecordBO.saveInfo();

			return mapping.findForward("success");
		} catch (Exception e) {
			BocoLog.error(this, 0, "FaultrecordAction.performUpdate", e);
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @�鿴��Ϣ
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performView(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String id = request.getParameter("id");

			FaultrecordBO faultrecordBO = (FaultrecordBO) getBean("FaultrecordBO");
			FaultrecordVO faultrecord = faultrecordBO.viewInfo(id);

			request.setAttribute("BASEINFOVO", faultrecord);

			return mapping.findForward("success");
		} catch (Exception e) {
			BocoLog.error(this, 0, "FaultrecordAction.performView", e);
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @�б�
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performList(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
					.getPageSize();
			String pageIndexName = new org.displaytag.util.ParamEncoder(
					DutyConstacts.FAULTRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));

			String userid = saveSessionBeanForm.getUserid();
			String deptid = saveSessionBeanForm.getDeptid();

			FaultrecordBO faultrecordBO = (FaultrecordBO) getBean("FaultrecordBO");
			faultrecordBO.setActionFormMap(((DynaActionForm) actionForm)
					.getMap());

			String hSql = "from Faultrecord as faultrecord ";
			hSql += "where faultrecord.delFlag = 0 and faultrecord.userId='"
					+ userid;
			hSql += "' and faultrecord.deptId='" + deptid;
			hSql += "' order by faultrecord.insertTime desc";

			Map map = faultrecordBO.searchInfo(pageIndex, pageSize, hSql);

			List list = (List) map.get("result");
			request.setAttribute(DutyConstacts.FAULTRECORDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
			return mapping.findForward("success");
		} catch (Exception e) {
			BocoLog.error(this, 0, "FaultrecordAction.performList", e);
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @ɾ��ִ�����ҳ����ת��ɾ��ǰ����Ϣ�鿴ҳ��
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performDel(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String id = request.getParameter("id");

			FaultrecordBO faultrecordBO = (FaultrecordBO) getBean("FaultrecordBO");
			FaultrecordVO faultrecord = faultrecordBO.viewInfo(id);

			request.setAttribute("BASEINFOVO", faultrecord);

			return mapping.findForward("success");
		} catch (Exception e) {
			BocoLog.error(this, 0, "FaultrecordAction.performDel", e);
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @ִ��ɾ�����
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performDelDo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			FaultrecordBO faultrecordBO = (FaultrecordBO) getBean("FaultrecordBO");
			faultrecordBO.setActionFormMap(((DynaActionForm) actionForm)
					.getMap());
			faultrecordBO.updateDelFlag();

			return mapping.findForward("success");
		} catch (Exception e) {
			BocoLog.error(this, 0, "FaultrecordAction.performDelDo", e);
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @��ѯ��ִ�����ҳ����ת����ѯ���ѡ��ҳ��
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performSearch(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			// �� FaultrecordQueryForm ���г�ʼ��
			request.getSession().removeAttribute("FaultrecordQueryForm");
			return mapping.findForward("success");
		} catch (Exception e) {
			BocoLog.error(this, 0, "FaultrecordAction.performSearch", e);
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @ִ�в�ѯ����ִ�����ҳ����ת����ѯ���ҳ��
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performSearchDo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
					.getPageSize();
			String pageIndexName = new org.displaytag.util.ParamEncoder(
					DutyConstacts.FAULTRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));

			FaultrecordBO faultrecordBO = (FaultrecordBO) getBean("FaultrecordBO");
			faultrecordBO.setActionFormMap(((DynaActionForm) actionForm)
					.getMap());

			Map map = faultrecordBO.searchInfo(pageIndex, pageSize);

			List list = (List) map.get("result");
			request.setAttribute(DutyConstacts.FAULTRECORDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);
			return mapping.findForward("success");
		} catch (Exception e) {
			BocoLog.error(this, 0, "FaultrecordAction.performList", e);
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @ͳ�Ʋ�ѯ��ִ�����ҳ����ת��ͳ�Ʋ�ѯ���ѡ��ҳ��
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */

	private ActionForward performStat(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		return mapping.findForward("success");
	}

	/**
	 * @ִ�в�ѯ����ִ�����ҳ����ת����ѯ���ҳ��
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performStatDo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
					.getPageSize();
			String pageIndexName = new org.displaytag.util.ParamEncoder(
					DutyConstacts.FAULTRECORDLIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));

			FaultrecordBO faultrecordBO = (FaultrecordBO) getBean("FaultrecordBO");
			faultrecordBO.setActionFormMap(((DynaActionForm) actionForm)
					.getMap());
			String sql = StaticMethod.getPageString(faultrecordBO.getStatSql());

			Map map = faultrecordBO.getResultStat(sql, pageIndex, pageSize);

			List list = (List) map.get("result");

			request.setAttribute(DutyConstacts.FAULTRECORDLIST, list);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);

			return mapping.findForward("success");
		} catch (Exception e) {
			BocoLog.error(this, 0, "FaultrecordAction.performStatDo", e);
			generalError(request, e);
			return mapping.findForward("failure");
		}

	}

	/**
	 * @ִ�в�ѯ����ִ�����ҳ����ת����ѯ���ҳ��
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performViewStat(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int length = PAGE_LENGTH;
			int offset = StaticMethod.nullObject2int(request
					.getParameter("pager.offset"), 0);
			int size = StaticMethod.nullObject2int(request
					.getParameter("pager.size"), 0);
			int[] pagePra = { offset, length, size };

			FaultrecordBO faultrecordBO = (FaultrecordBO) getBean("FaultrecordBO");
			faultrecordBO.setActionFormMap(((DynaActionForm) actionForm)
					.getMap());
			// List list = faultrecordBO.searchInfo(pagePra);

			// request.setAttribute("BASEINFOLIST", list);

			String url = "/duty" + mapping.getPath() + ".do";
			String pagerHeader = Pager.generate(pagePra[0], pagePra[2],
					pagePra[1], url);
			request.setAttribute("pagerHeader", pagerHeader);

			return mapping.findForward("success");
		} catch (Exception e) {
			BocoLog.error(this, 0, "FaultrecordAction.performViewStat", e);
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @EXCEL����
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performInputExcel(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("success");
	}

	/**
	 * @ִ��EXCEL����
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private ActionForward performInputExcelDo(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			return mapping.findForward("success");
		} catch (Exception e) {
			BocoLog.error(this, 0, "FaultrecordAction.performInputExcelDo", e);
			generalError(request, e);
			return mapping.findForward("failure");
		}
	}

	/**
	 * @ִ���쳣�������
	 * @param mapping
	 *            ActionMapping
	 * @param form
	 *            ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @throws Exception
	 * @return ActionForward
	 */
	private void generalError(HttpServletRequest request, Exception e) {
		ActionMessages aes = new ActionMessages();
		aes.add("EOMS_WORKSHEET_ERROR", new ActionMessage("error.general", e
				.getMessage()));
		saveMessages(request, aes);
		BocoLog.error(this, 2, "[BaseworksheetAction] Error -", e);
	}
}
