package com.boco.eoms.filemanager.action;

import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.util.LabelValueBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.common.resource.Pager;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.filemanager.CombineFile;
import com.boco.eoms.filemanager.ReportMgrDAO;
import com.boco.eoms.filemanager.SchemeMgrDAO;
import com.boco.eoms.filemanager.WorkSheetAccess;
import com.boco.eoms.filemanager.form.ReportListBean;
import com.boco.eoms.filemanager.form.ReportMgrBean;
import com.boco.eoms.filemanager.form.ReportMgrForm;
import com.boco.eoms.filemanager.form.SchemeMgrForm;
import com.boco.eoms.filemanager.form.SearchListForm;

/**
 * Created by IntelliJ IDEA. User: liqifei Date: 2005-9-15 Time: 10:24:08 Boco
 * Corporation 部门：亿阳信通软件研究院 EOMS 地址：北京市海淀区亮甲店130号亿阳大厦 12层3室 To change this
 * template use File | Settings | File Templates.
 */
public final class ReportMgrAction extends BaseAction {

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
		} else if ("UPDATE".equalsIgnoreCase(myaction)) {
			myforward = performUpdate(mapping, form, request, response);
		} else if ("allFileView".equalsIgnoreCase(myaction)) {
			myforward = performViewAllFile(mapping, form, request, response);
		} else if ("CollectReportList".equalsIgnoreCase(myaction)) {
			myforward = CollectReportList(mapping, form, request, response);
		} else if ("CollectReportAuditList".equalsIgnoreCase(myaction)) {
			myforward = CollectReportAuditList(mapping, form, request, response);
		} else if ("CollectReportNoAuditList".equalsIgnoreCase(myaction)) {
			myforward = CollectReportNoAuditList(mapping, form, request,
					response);
		} else if ("SeparateReportList".equalsIgnoreCase(myaction)) {
			myforward = SeparateReportList(mapping, form, request, response);
		} else if ("SeparateReportNeedList".equalsIgnoreCase(myaction)) {
			myforward = SeparateReportNeedList(mapping, form, request, response);
		} else if ("SeparateReportNoNeedList".equalsIgnoreCase(myaction)) {
			myforward = SeparateReportNoNeedList(mapping, form, request,
					response);
		} else if ("NewReportList".equalsIgnoreCase(myaction)) {
			myforward = performNewReportList(mapping, form, request, response);
			// ZF add:
		} else if ("COMBINE".equalsIgnoreCase(myaction)) {
			myforward = performCombine(mapping, form, request, response);
			// wangsixuan add:
		} else if ("AuditReportList".equalsIgnoreCase(myaction)) {
			myforward = AuditReportList(mapping, form, request, response);
		} else if ("AuditReportAuditList".equalsIgnoreCase(myaction)) {
			myforward = AuditReportAuditList(mapping, form, request, response);
		} else if ("AuditReportNoAuditList".equalsIgnoreCase(myaction)) {
			myforward = AuditReportNoAuditList(mapping, form, request, response);
		} else if ("auditFileView".equalsIgnoreCase(myaction)) {
			myforward = performViewAllAuditFile(mapping, form, request,
					response);
		} else if ("AuditList".equalsIgnoreCase(myaction)) {
			myforward = performAuditList(mapping, form, request, response);
		} else if ("overtimeQuery".equalsIgnoreCase(myaction)) {
			myforward = overtimeQuery(mapping, form, request, response);
		} else if ("overtimeList".equalsIgnoreCase(myaction)) {
			myforward = overtimeList(mapping, form, request, response);
		} else if ("executeAudit".equalsIgnoreCase(myaction)) {
			myforward = executeAudit(mapping, form, request, response);
		} else
			myforward = mapping.findForward("failure");

		return myforward;

	}

	private ActionForward performCombine(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ReportMgrForm schemeForm = (ReportMgrForm) form;
		String templatePath = schemeForm.getTemplatFile();
		String[] filelist = schemeForm.getFileList();
		String combinType = schemeForm.getCombinType();
		if (templatePath == null || filelist == null || filelist.length < 2) {
			request.setAttribute("MSG", "选择不合理。");
			return mapping.findForward("fail");
		}

		String path = request.getRealPath("");
		try {
			CombineFile cf = new CombineFile();
			// String retFile = new MainClass().getCombineFile( path,
			// templatePath, filelist );
			String retFile = cf.DoCombinFile(path, filelist[0], filelist,
					combinType);
			// response.sendRedirect(request.getContextPath()+"/filemanager/fileUpload/download.jsp?url="+retFile+"&showName=loaddown.xls");
			response.sendRedirect(request.getContextPath()
					+ "/filemanager/getCombineFile.jsp?filePath=" + retFile);
		} catch (Exception e) {
			request.setAttribute("MSG", e.getMessage());
			return mapping.findForward("failure");
		}
		return null;
	}

	private ActionForward performNewReportList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Vector list = new Vector();
		String pageOffset = "";
		String url = "";
		String pagerHeader = "";
		ReportMgrForm myForm = (ReportMgrForm) form;
		String topicId = myForm.getTopicId();
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		// int deptId = saveSessionBeanForm.getWrf_DeptID();
		String deptId = saveSessionBeanForm.getDeptid();
		ReportMgrDAO dao = new ReportMgrDAO();

		// ReportMgrForm reportForm = dao.getReportMgrForm(reportId, deptId +
		// "");
		String queried = request.getParameter("queried");
		try {
			int offset;
			int length = PAGE_LENGTH;
			// 当前第几页
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
				sql
						.append("select a.*,(select topic_name from taw_file_mgr_topic b where a.topic_id=b.topic_id ) as topic_name,"
								+ "(select dept_name from taw_dept e where e.dept_id=c.mgr_dept ) as send_dept_name ,c.report_name ,c.deadline"
								+ " from taw_file_mgr_list a ,taw_file_mgr_report c where a.report_id=c.report_id  and a.acccept_dept_id='"
								+ deptId
								+ "' and a.status=0 order by a.send_time desc"); // ?ζ???????б?
				request.getSession().setAttribute("querySql", sql);
			}
			int size = dao.getResutCount2((sql));
			list = dao.getPageSeparateReport(listStart, PAGE_LENGTH, sql
					.toString(), size);
			url = request.getContextPath()
					+ "/filemanager/ReportMgrAction.do?act=NewReportList&queried=1";
			pagerHeader = Pager.generate(offset, size, length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("ReportList", list);
			dao.release();
		} catch (Exception e) {
			return mapping.findForward("failure");
		} catch (Throwable te) {
			te.printStackTrace();
		} finally {
			dao.release();
		}
		return mapping.findForward("NewReportList");
	}

	// 用户上报的全部信息
	private ActionForward SeparateReportList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return performSeparateReportList(mapping, form, request, response, "");
	}

	// 用户上报的需要上报的信息
	private ActionForward SeparateReportNeedList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return performSeparateReportList(mapping, form, request, response,
				"and (a.status='0' or a.status='1' or a.status='2')");
	}

	// 用户上报的不需要上报的信息
	private ActionForward SeparateReportNoNeedList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return performSeparateReportList(mapping, form, request, response,
				"and (a.status!='0' and a.status!='1' and a.status!='2')");
	}

	private ActionForward performSeparateReportList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String type) {
		Vector list = new Vector();
		String pageOffset = "";
		String url = "";
		String pagerHeader = "";
		// ReportMgrForm myForm = (ReportMgrForm) form;
		// String topicId = myForm.getTopicId();

		String topicId = request.getParameter("topicId");
		if ("-1".equals(topicId)) {
			topicId = "0";
		}

		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String deptId = saveSessionBeanForm.getDeptid();
		ReportMgrDAO dao = new ReportMgrDAO();

		// ReportMgrForm reportForm = dao.getReportMgrForm(reportId, deptId +
		// "");
		String queried = request.getParameter("queried");
		try {
			int offset;
			int length = PAGE_LENGTH;
			// 当前第几页
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
				if (topicId != null)
					sql
							.append("select a.*,(select topic_name from taw_file_mgr_topic b where a.topic_id=b.topic_id ) as topic_name,"
									+ "(select deptname from taw_system_dept e where e.deptid=c.mgr_dept ) as send_dept_name ,c.report_name ,c.deadline"
									+ " from taw_file_mgr_list a ,taw_file_mgr_report c where a.report_id=c.report_id and  a.topic_id='"
									+ topicId
									+ "' and a.acccept_dept_id='"
									+ deptId
									+ "' "
									+ type
									+ " order by a.send_time desc"); //
				else
					sql
							.append("select a.*,(select topic_name from taw_file_mgr_topic b where a.topic_id=b.topic_id ) as topic_name,"
									+ "(select deptname from taw_system_dept e where e.deptid=c.mgr_dept ) as send_dept_name ,c.report_name ,c.deadline"
									+ " from taw_file_mgr_list a ,taw_file_mgr_report c where a.report_id=c.report_id  and a.acccept_dept_id='"
									+ deptId
									+ "' "
									+ type
									+ " order by a.send_time desc"); //
				request.getSession().setAttribute("querySql", sql);
			}
			int size = dao.getResutCount2((sql));
			list = dao.getPageSeparateReport(listStart, PAGE_LENGTH, sql
					.toString(), size);
			url = request.getContextPath()
					+ "/filemanager/ReportMgrAction.do?act=SeparateReportList&queried=1";
			pagerHeader = Pager.generate(offset, size, length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("ReportList", list);
			dao.release();
		} catch (Exception e) {
			return mapping.findForward("failure");
		} catch (Throwable te) {
			te.printStackTrace();
		} finally {
			dao.release();
		}
		return mapping.findForward("SeparateReportList");
	}

	private ActionForward CollectReportList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return performCollectReportList(mapping, form, request, response, "");
	}

	private ActionForward CollectReportAuditList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return performCollectReportList(mapping, form, request, response,
				"and (l.status='3' or (l.is_audit='0' and l.status='1'))");
	}

	private ActionForward CollectReportNoAuditList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return performCollectReportList(
				mapping,
				form,
				request,
				response,
				"and (l.status!='3' and ((l.is_audit!='0' and l.status!='1')or(l.is_audit='0' and l.status!='1')or(l.is_audit!='0' and l.status='1')))");
	}

	private ActionForward performCollectReportList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String type) {

		ReportMgrForm myForm = (ReportMgrForm) form;
		// String topicId = myForm.getTopicId();
		String topicId = request.getParameter("topicId");
		if ("-1".equals(topicId)) {
			topicId = "0";
		}
		ReportMgrDAO dao = new ReportMgrDAO();
		Vector list = new Vector();
		String pageOffset = "";
		String url = "";
		String pagerHeader = "";
		String queried = request.getParameter("queried");
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String deptId = saveSessionBeanForm.getDeptid();
		String userId = saveSessionBeanForm.getUserid();
		try {
			int offset;
			int length = PAGE_LENGTH;
			// 当前第几页
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
				if (topicId != null) // 列出全部当前部门派发报表
					sql
							.append("select distinct a.*,(select topic_name from taw_file_mgr_topic d where a.topic_id=d.topic_id) as topic_name ,(select deptname from taw_system_dept e where e.deptid=a.mgr_dept) as mgr_dept_name "
									+ ",s.report_user_id as report_user_id from taw_file_mgr_report a,taw_file_mgr_scheme s,taw_file_mgr_list l where a.topic_id='"
									+ topicId
									+ "' and s.file_mgr_scheme_id=a.file_mgr_scheme_id and l.report_id = a.report_id "
									+ type
									+ " and s.report_user_id like '%,"
									+ userId
									+ ",%'"
									+ " order by a.report_id desc");// where
				// a.report_id='"
				// +
				// reportId
				// + "'
				else
					sql
							.append("select distinct a.*,(select topic_name from taw_file_mgr_topic d where a.topic_id=d.topic_id) as topic_name ,(select deptname from taw_system_dept e where e.deptid=a.mgr_dept) as mgr_dept_name"
									+ ",s.report_user_id as report_user_id from taw_file_mgr_report a,taw_file_mgr_scheme s,taw_file_mgr_list l where s.file_mgr_scheme_id=a.file_mgr_scheme_id and l.report_id = a.report_id "
									+ type
									+ " and s.report_user_id like '%,"
									+ userId + ",%' order by a.report_id desc");
				request.getSession().setAttribute("querySql", sql);
			}
			int size = dao.getResutCount2((sql));
			list = dao.getPageCollectReport(listStart, PAGE_LENGTH, sql
					.toString(), size);
			url = request.getContextPath()
					+ "/filemanager/ReportMgrAction.do?act=CollectReportList&topicId="
					+ topicId + "&queried=1";
			pagerHeader = Pager.generate(offset, size, length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("ReportList", list);
			dao.release();
		} catch (Exception e) {
			return mapping.findForward("failure");
		} catch (Throwable te) {
			te.printStackTrace();
		} finally {
			dao.release();
		}
		return mapping.findForward("CollectReportList");
	}

	private ActionForward performViewAllFile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		ReportMgrForm myForm = (ReportMgrForm) form;
		// String reportId = myForm.getReportId();
		String reportId = request.getParameter("reportId");
		ReportMgrDAO dao = new ReportMgrDAO();
		ReportMgrBean reportBean = dao.getReportMgrFormBean(reportId);
		SchemeMgrDAO schemeDao = new SchemeMgrDAO(dao.getConnection());
		SchemeMgrForm schemeForm = schemeDao.getFormBean(reportBean
				.getSchemeId());
		Vector list = new Vector();
		String pageOffset = "";
		String url = "";
		String pagerHeader = "";
		String queried = request.getParameter("queried");
		try {
			int offset;
			int length = 100;
			// 当前第几页
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
			StringBuffer sqlForStatus = new StringBuffer();
			if (queried != null) {
				sql = (StringBuffer) request.getSession().getAttribute(
						"querySql");
				sqlForStatus = (StringBuffer) request.getSession()
						.getAttribute("sqlForStatus");
			} else {
				sql
						.append("select a.*,(select username from taw_system_user d where a.deal_user=d.userid) as user_name,"
								+ "(select r.combintype from taw_file_mgr_report r where a.report_id=r.report_id) as combintype,"
								+ "(select s.is_audit from taw_file_mgr_scheme s,taw_file_mgr_report r where s.file_mgr_scheme_id = r.file_mgr_scheme_id and a.report_id=r.report_id) as is_audit "
								+ " from taw_file_mgr_list a where  a.report_id='"
								+ reportId + "' order by a.flow_id desc");
				request.getSession().setAttribute("querySql", sql);
				sqlForStatus
						.append("select a.*,(select username from taw_system_user d where a.deal_user=d.userid) as user_name,"
								+ "(select r.combintype from taw_file_mgr_report r where a.report_id=r.report_id) as combintype,"
								+ "(select s.is_audit from taw_file_mgr_scheme s,taw_file_mgr_report r where s.file_mgr_scheme_id = r.file_mgr_scheme_id and a.report_id=r.report_id) as is_audit "
								+ " from taw_file_mgr_list a where  a.report_id='"
								+ reportId
								+ "' and a.status in(5,7) order by a.flow_id desc");
				request.getSession().setAttribute("sqlForStatus", sqlForStatus);
			}

			// wangsixuan add:
			String combintype = dao.getCombintype(sql);
			int sizeForUse = dao.getResutCount2((sql));
			int size = dao.getResutCount2((sqlForStatus));
			list = dao.getPageFile(listStart, 100, sql.toString(), sizeForUse);
			Vector listForStatus = new Vector();
			listForStatus = dao.getPageFile(listStart, 100, sqlForStatus
					.toString(), size);
			url = request.getContextPath()
					+ "/filemanager/ReportMgrAction.do?act=prepareView&reportId="
					+ reportId + "&queried=1";
			pagerHeader = Pager.generate(offset, sizeForUse, length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("ReportList", list);
			request.setAttribute("size", String.valueOf(size));
			request.setAttribute("combintype", String.valueOf(combintype));
			request.setAttribute("ListForStatus", listForStatus);
			dao.release();
		} catch (Exception e) {
			return mapping.findForward("failure");
		} catch (Throwable te) {
			te.printStackTrace();
		} finally {
			dao.release();
		}
		request.setAttribute("SchemeMgrForm", schemeForm);
		request.setAttribute("ReportMgrBean", reportBean);
		return mapping.findForward("allFileView");
	}

	private ActionForward performUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ReportMgrForm schemeForm = (ReportMgrForm) form;
//		schemeForm.setReplyInfo(StaticMethod.strReverse(schemeForm
//				.getReplyInfo(), "ISO-8859-1", "UTF-8"));//
//		schemeForm.setAuditUserName(StaticMethod.strReverse(schemeForm
//				.getAuditUserName(), "ISO-8859-1", "UTF-8"));
		ReportMgrDAO dao = new ReportMgrDAO();
		try {
			dao.update(request.getRealPath(""), schemeForm);
		} catch (Exception e) {
			e.printStackTrace(); // To change body of catch statement use
			// File | Settings | File Templates.
		}
		schemeForm.setReportId(schemeForm.getReportId());
		dao.release();
		return mapping.findForward("prepareAdd");
	}

	private ActionForward performAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ReportMgrForm myForm = (ReportMgrForm) form;
		String reportId = myForm.getReportId();
		try {
			TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
			// TawRmUserBO tawRmUserBO = new TawRmUserBO();
			String userid = saveSessionBeanForm.getUserid();
			String deptId = saveSessionBeanForm.getDeptid();
			// TawRmUser tawRmUser = tawRmUserBO.retrieveNew(userid);
			String mobile = saveSessionBeanForm.getContactMobile();
			String deptname = saveSessionBeanForm.getDeptname();
			String username = saveSessionBeanForm.getUsername();
			ReportMgrDAO dao = new ReportMgrDAO();
			ReportMgrForm reportForm = dao.getReportMgrForm(reportId, deptId
					+ "");
			String usernameJson = "";
			try {
				org.apache.commons.beanutils.BeanUtils.populate(myForm,
						org.apache.commons.beanutils.BeanUtils
								.describe(reportForm));
				usernameJson = dao.getAuditUserName(reportForm); // 审核人
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			ReportMgrBean reportBean = dao.getReportMgrFormBean(reportId);
			SchemeMgrDAO schemeDao = new SchemeMgrDAO(dao.getConnection());
			SchemeMgrForm schemeForm = schemeDao.getFormBean(reportBean
					.getSchemeId());
			myForm.setAct("UPDATE");
			myForm.setDealUserId(userid);
			myForm.setDealUserName(username);
			if (myForm.getAcceptContact() == null
					|| "".equals(myForm.getAcceptContact()))
				myForm.setAcceptContact(mobile);
			myForm.setAcceptDeptId(deptId + "");
			myForm.setAcceptDeptName(deptname);
			request.setAttribute("SchemeMgrForm", schemeForm);
			request.setAttribute("ReportMgrBean", reportBean);
			request.setAttribute("usernameJson", usernameJson);
			request.setAttribute("aUserId", reportForm.getAuditUserId());
			request.setAttribute("aUserName", reportForm.getAuditUserName());
			dao.release();
			schemeDao.release();
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("failure");
		} finally {
		}
		return mapping.findForward("add");
	}

	// wangsixuan add:
	// 审核人看到的所有信息
	private ActionForward AuditReportList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return performAuditReport(mapping, form, request, response, "");
	}

	// 审核人看到的需要审核的信息
	private ActionForward AuditReportAuditList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return performAuditReport(mapping, form, request, response,
				"and (l.status='1' or l.status='4')");
	}

	// 审核人看到的不需要审核的信息
	private ActionForward AuditReportNoAuditList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return performAuditReport(mapping, form, request, response,
				"and (l.status!='1' and l.status!='4')");
	}

	private ActionForward performAuditReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String type) {
		ReportMgrForm myForm = (ReportMgrForm) form;
		// String topicId = myForm.getTopicId();
		String topicId = request.getParameter("topicId");
		if ("-1".equals(topicId)) {
			topicId = "0";
		}
		ReportMgrDAO dao = new ReportMgrDAO();
		Vector list = new Vector();
		String pageOffset = "";
		String url = "";
		String pagerHeader = "";
		String queried = request.getParameter("queried");
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		if (saveSessionBeanForm == null) {
			return mapping.findForward("timeout");
		}
		String deptId = saveSessionBeanForm.getDeptid();
		String userId = saveSessionBeanForm.getUserid();
		try {
			int offset;
			int length = PAGE_LENGTH;
			// 当前第几页
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
				if (topicId != null) // 列出全部审核人派发报表
					sql
							.append("select distinct a.*,(select topic_name from taw_file_mgr_topic d where a.topic_id=d.topic_id) as topic_name ,(select deptname from taw_system_dept e where e.deptid=a.mgr_dept) as mgr_dept_name "
									+ " from taw_file_mgr_report a,taw_file_mgr_scheme s,taw_file_mgr_list l where "
									+ " a.topic_id='"
									+ topicId
									+ "' and s.file_mgr_scheme_id=a.file_mgr_scheme_id and l.report_id = a.report_id "
									+ type
									+ " and l.audit_user_id like '%,"
									+ userId
									+ ",%'"
									+ " order by a.report_id desc");
				else
					sql
							.append("select distinct a.*,(select topic_name from taw_file_mgr_topic d where a.topic_id=d.topic_id) as topic_name ,(select deptname from taw_system_dept e where e.deptid=a.mgr_dept) as mgr_dept_name"
									+ " from taw_file_mgr_report a,taw_file_mgr_scheme s,taw_file_mgr_list l where s.file_mgr_scheme_id=a.file_mgr_scheme_id and l.report_id = a.report_id "
									+ type
									+ " and l.audit_user_id like '%,"
									+ userId + ",%' order by a.report_id desc");
				request.getSession().setAttribute("querySql", sql);
			}
			int size = dao.getResutCount2((sql));
			list = dao.getPageCollectReport(listStart, PAGE_LENGTH, sql
					.toString(), size);
			url = request.getContextPath()
					+ "/filemanager/ReportMgrAction.do?act=AuditReportList&topicId="
					+ topicId + "&queried=1";
			pagerHeader = Pager.generate(offset, size, length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("ReportList", list);
			dao.release();
		} catch (Exception e) {
			return mapping.findForward("failure");
		} catch (Throwable te) {
			te.printStackTrace();
		} finally {
			dao.release();
		}
		return mapping.findForward("AuditReportList");
	}

	// wangsixuan add:
	private ActionForward performViewAllAuditFile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		ReportMgrForm myForm = (ReportMgrForm) form;
		// String reportId = myForm.getReportId();
		String reportId = request.getParameter("reportId");
		ReportMgrDAO dao = new ReportMgrDAO();
		ReportMgrBean reportBean = dao.getReportMgrFormBean(reportId);
		SchemeMgrDAO schemeDao = new SchemeMgrDAO(dao.getConnection());
		SchemeMgrForm schemeForm = schemeDao.getFormBean(reportBean
				.getSchemeId());
		Vector list = new Vector();
		String pageOffset = "";
		String url = "";
		String pagerHeader = "";
		String queried = request.getParameter("queried");
		try {
			int offset;
			int length = 100;
			// 当前第几页
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
				sql
						.append("select a.*,(select username from taw_system_user d where a.deal_user=d.userid) as user_name,"
								+ "(select s.is_audit from taw_file_mgr_scheme s,taw_file_mgr_report r where s.file_mgr_scheme_id = r.file_mgr_scheme_id and a.report_id=r.report_id) as is_audit "
								+ " from taw_file_mgr_list a where  a.report_id='"
								+ reportId + "' order by a.flow_id desc");
				request.getSession().setAttribute("querySql", sql);
			}
			int size = dao.getResutCount2((sql));
			list = dao.getPageFile(listStart, 100, sql.toString(), size);
			url = request.getContextPath()
					+ "/filemanager/ReportMgrAction.do?act=prepareAuditView&reportId="
					+ reportId + "&queried=1";
			pagerHeader = Pager.generate(offset, size, length, url);
			request.setAttribute("pagerHeader", pagerHeader);
			request.setAttribute("ReportList", list);
			request.setAttribute("size", String.valueOf(size));
			dao.release();
		} catch (Exception e) {
			return mapping.findForward("failure");
		} catch (Throwable te) {
			te.printStackTrace();
		} finally {
			dao.release();
		}
		request.setAttribute("SchemeMgrForm", schemeForm);
		request.setAttribute("ReportMgrBean", reportBean);
		request.setAttribute("reportId", reportId);
		return mapping.findForward("auditFileView");
	}

	// wangsixuan:audit list
	private ActionForward performAuditList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String flowId = request.getParameter("flowId");
		ReportMgrDAO dao = new ReportMgrDAO();
		Vector list = new Vector();
		try {
			list = dao.getAuditListInfo(flowId);
			request.setAttribute("auditInfoList", list);
			response.setContentType("text/html; charset=UTF-8");
			dao.release();
		} catch (Exception e) {
			return mapping.findForward("failure");
		} catch (Throwable te) {
			te.printStackTrace();
		} finally {
			dao.release();
		}
		return mapping.findForward("auditInfoList");
	}

	// wangsixuan add: overtimeSearch
	private ActionForward overtimeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		SearchListForm myForm = (SearchListForm) form;
		ReportMgrDAO dao = new ReportMgrDAO();
		Vector list = new Vector();
		// 取JSON对象中的部门ID
		String subCompany = StaticMethod.null2String(request
				.getParameter("subCompany"));
		if (subCompany != null && !("").equals(subCompany)
				&& !"[]".equals(subCompany)) {
			JSONArray jsonDept = JSONArray.fromString(subCompany);
			JSONObject jobj = (JSONObject) jsonDept.get(0);
			String deptid = jobj.getString("id");
			myForm.setAcceptDeptId(deptid);
		}
			try {
				list = dao.getOvertimeList(myForm);
				request.setAttribute("overtimeList", list);
				dao.release();
			} catch (Exception e) {
				return mapping.findForward("failure");
			} catch (Throwable te) {
				te.printStackTrace();
			} finally {
				dao.release();
			}
		return mapping.findForward("overtimeList");
	}
//	 wangsixuan add: overtimeSearch
	private ActionForward overtimeQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ReportMgrDAO dao = new ReportMgrDAO();
		try {
			request.setAttribute("reportId", dao.getFlowList());
			dao.release();
		} catch (Exception e) {
			return mapping.findForward("failure");
		} catch (Throwable te) {
			te.printStackTrace();
		} finally {
			dao.release();
		}
		return mapping.findForward("overtimeQuery");
	}
//	 wangsixuan add:
	private ActionForward executeAudit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		       String userId= request.getParameter("userId");
		       String flowId = request.getParameter("flowId");
		       String type = request.getParameter("type");
		       String auditInfo = request.getParameter("auditInfo");
		       String reportId = request.getParameter("reportId");
		       String strDoc="";
		       WorkSheetAccess wsa = new WorkSheetAccess();
		       if(type.equals("reject1")){
		        strDoc=wsa.rejectReport(userId,flowId,auditInfo);
		       }else if(type.equals("pass1")){
		       	strDoc=wsa.passReport(userId,flowId,auditInfo);
		       }else if(type.equals("reject2")){
		       	strDoc=wsa.rejectReport2(userId,flowId,auditInfo);
		       }else if(type.equals("pass2")){
		       	strDoc=wsa.passReport2(userId,flowId,auditInfo);
		       }else if(type.equals("reject3")){
		       	strDoc=wsa.rejectReport3(userId,flowId,auditInfo);
		       }else if(type.equals("pass3")){
		       	strDoc=wsa.passReport3(userId,flowId,auditInfo);
		       }
		       wsa.release();
		       return this.performViewAllAuditFile(mapping,form, request,response);
	}
}
