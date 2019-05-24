package com.boco.eoms.km.exam.webapp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.km.exam.mgr.KmExamChoiceMgr;
import com.boco.eoms.km.exam.mgr.KmExamQuestionsMgr;
import com.boco.eoms.km.exam.mgr.KmExamSpecialtyMgr;
import com.boco.eoms.km.exam.model.KmExamChoice;
import com.boco.eoms.km.exam.model.KmExamQuestions;
import com.boco.eoms.km.exam.model.KmExamSpecialty;
import com.boco.eoms.km.exam.sample.ExcelImportSample;
import com.boco.eoms.km.exam.util.FileUtil;
import com.boco.eoms.km.exam.util.KmExamQuestionsConstants;
import com.boco.eoms.km.exam.webapp.form.KmExamQuestionsForm;
import com.boco.eoms.km.exam.webapp.form.UploadFile;

/**
 * <p>
 * Title:题库管理
 * </p>
 * <p>
 * Description:题库管理
 * </p>
 * <p>
 * Fri May 08 16:40:11 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() hsl
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmExamQuestionsAction extends BaseAction {

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
	 * 新增题库管理
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
		request.setAttribute("listLength", String.valueOf(0));
		String questionsType = StaticMethod.null2String(request
				.getParameter("questionsType"));
		request.setAttribute("questionType", questionsType);
		return mapping.findForward("edit");
	}

	/**
	 * 修改题库管理
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
		KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
		String id = StaticMethod
				.null2String(request.getParameter("questionID"));
		KmExamQuestions kmExamQuestions = kmExamQuestionsMgr
				.getKmExamQuestions(id);
		KmExamQuestionsForm kmExamQuestionsForm = (KmExamQuestionsForm) convert(kmExamQuestions);
		updateFormBean(mapping, request, kmExamQuestionsForm);
		KmExamChoiceMgr kmExamChoiceMgr = (KmExamChoiceMgr) getBean("kmExamChoiceMgr");
		List choiceList = kmExamChoiceMgr.getKmExamChoicesByQuestionID(id);
		String questionType = kmExamQuestionsForm.getQuestionType();
		request.setAttribute("questionType", questionType);
		request.setAttribute("choiceList", choiceList);
		request.setAttribute("listLength", String.valueOf(choiceList.size()));
		return mapping.findForward("edit");
	}

	/**
	 * 保存题库管理
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
		KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
		KmExamQuestionsForm kmExamQuestionsForm = (KmExamQuestionsForm) form;
		kmExamQuestionsForm.setQuestion(new String(kmExamQuestionsForm
				.getQuestion().getBytes("iso8859-1"), "utf-8"));
		boolean isNew = (null == kmExamQuestionsForm.getQuestionID() || ""
				.equals(kmExamQuestionsForm.getQuestionID()));
		KmExamQuestions kmExamQuestions = (KmExamQuestions) convert(kmExamQuestionsForm);
		StringBuffer accessory = new StringBuffer(kmExamQuestionsForm.getAccessory());

		List myFiles = kmExamQuestionsForm.getMyFiles();
		for (int i = 0; i < myFiles.size(); i++) {
			UploadFile uploadFile = (UploadFile) myFiles.get(i);
			FormFile file = uploadFile.getFile();

			if (file == null) {
				System.out.println("file  is  null");
			} else {

				// 能运行到这里，就可以使用单个文件上传的方法进行上传了。循环而已
				System.out.println("filename>>>>>>>>>>" + new String(file.getFileName().getBytes("iso8859-1"), "utf-8"));
				System.out.println("file  size>>>>>>>>>>" + file.getFileSize());
				
				String path = this.getClass().getClassLoader().getResource("").getPath();
				String rootPath = path.substring(0, path
						.lastIndexOf("WEB-INF/classes"))
						+ "kmpictures/kmExamAccessory/";
				String fileName = FileUtil.uploadFile(file, rootPath);
				String question = kmExamQuestions.getQuestion();
				kmExamQuestions.setQuestion(question.replace("[attachimg]"+new String(file.getFileName().getBytes("iso8859-1"), "utf-8")+"[/attachimg]", "[attachimg]"+fileName+"[/attachimg]"));
				accessory.append(fileName+"#");
			}
		}

		TawSystemSessionForm tawSystemSessionForm = this.getUser(request);
		kmExamQuestions.setCreateDept(tawSystemSessionForm.getDeptid());
		kmExamQuestions.setCreateUser(tawSystemSessionForm.getUserid());
		if(myFiles.size()>0)
			kmExamQuestions.setAccessory(accessory.toString());
		Date createTime = StaticMethod.getLocalTime();
		kmExamQuestions.setCreateTime(createTime);
		if (isNew) {
			kmExamQuestionsMgr.saveKmExamQuestions(kmExamQuestions);
		} else {
			kmExamQuestionsMgr.saveKmExamQuestions(kmExamQuestions);
		}
		String result = StaticMethod
				.null2String(request.getParameter("result"));
		if (!result.equals("")) {
			JSONArray data = JSONArray.fromString(result);
			KmExamChoiceMgr kmExamChoiceMgr = (KmExamChoiceMgr) getBean("kmExamChoiceMgr");

			for (Iterator iter = data.iterator(); iter.hasNext();) {
				JSONObject obj = (JSONObject) iter.next();
				KmExamChoice kmExamChoice = new KmExamChoice();
				kmExamChoice.setQuestionsID(kmExamQuestions.getQuestionID());
				kmExamChoice.setContent(new String(obj.getString("content")
						.trim().getBytes("iso8859-1"), "utf-8"));
				kmExamChoice.setOrderBy(obj.getString("orderBy"));
				kmExamChoice.setChoiceID(obj.getString("id"));
				kmExamChoiceMgr.saveKmExamChoice(kmExamChoice);
			}
		}

		if (request.getAttribute("kmExamQuestionsForm") != null) {
			request.removeAttribute("kmExamQuestionsForm");
		}
		return mapping.findForward("success");
	}

	/**
	 * 删除题库管理
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
		KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
		String id = StaticMethod
				.null2String(request.getParameter("questionID"));
		kmExamQuestionsMgr.removeKmExamQuestions(id);
		return mapping.findForward("success");
	}

	/**
	 * 分页显示题库管理列表
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
				KmExamQuestionsConstants.KMEXAMQUESTIONS_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
		// .getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
		List list = kmExamQuestionsMgr.getKmExamQuestionss();
		// 始终是每页显示的条数大于总条数 防止分页
		final Integer pageSize = new Integer(list.size() + 10);

		// 得到各个题型下的题目
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		List list3 = new ArrayList();
		List list4 = new ArrayList();
		List list5 = new ArrayList();

		for (int i = 0; i < list.size(); i++) {
			KmExamQuestions kmExamQuestions = (KmExamQuestions) list.get(i);
			if (kmExamQuestions.getQuestionType().equals("1")) {
				list1.add(kmExamQuestions);
			}
			if (kmExamQuestions.getQuestionType().equals("2")) {
				list2.add(kmExamQuestions);
			}
			if (kmExamQuestions.getQuestionType().equals("3")) {
				list3.add(kmExamQuestions);
			}
			if (kmExamQuestions.getQuestionType().equals("4")) {
				list4.add(kmExamQuestions);
			}
			if (kmExamQuestions.getQuestionType().equals("5")) {
				list5.add(kmExamQuestions);
			}
		}

		request.setAttribute("resultSize1", new Integer(list1.size()));
		request.setAttribute("resultSize2", new Integer(list2.size()));
		request.setAttribute("resultSize3", new Integer(list3.size()));
		request.setAttribute("resultSize4", new Integer(list4.size()));
		request.setAttribute("resultSize5", new Integer(list5.size()));

		// 定义一个可配的首页每种题型显示的记录数
		int showSize = 2;
		request.setAttribute("showSize", new Integer(showSize));

		// 在第一个页面上显示的每个题型问题的数目不超过两个
		if (list1.size() > showSize) {
			// 得到list中的前两条记录
			KmExamQuestions kmExamQuestions1 = (KmExamQuestions) list1.get(0);
			KmExamQuestions kmExamQuestions2 = (KmExamQuestions) list1.get(1);
			list1 = new ArrayList();
			list1.add(kmExamQuestions1);
			list1.add(kmExamQuestions2);
		}
		if (list2.size() > showSize) {
			// 得到list中的前两条记录
			KmExamQuestions kmExamQuestions1 = (KmExamQuestions) list2.get(0);
			KmExamQuestions kmExamQuestions2 = (KmExamQuestions) list2.get(1);
			list2 = new ArrayList();
			list2.add(kmExamQuestions1);
			list2.add(kmExamQuestions2);
		}
		if (list3.size() > showSize) {
			// 得到list中的前两条记录
			KmExamQuestions kmExamQuestions1 = (KmExamQuestions) list3.get(0);
			KmExamQuestions kmExamQuestions2 = (KmExamQuestions) list3.get(1);
			list3 = new ArrayList();
			list3.add(kmExamQuestions1);
			list3.add(kmExamQuestions2);
		}
		if (list4.size() > showSize) {
			// 得到list中的前两条记录
			KmExamQuestions kmExamQuestions1 = (KmExamQuestions) list4.get(0);
			KmExamQuestions kmExamQuestions2 = (KmExamQuestions) list4.get(1);
			list4 = new ArrayList();
			list4.add(kmExamQuestions1);
			list4.add(kmExamQuestions2);
		}
		if (list5.size() > showSize) {
			// 得到list中的前两条记录
			KmExamQuestions kmExamQuestions1 = (KmExamQuestions) list5.get(0);
			KmExamQuestions kmExamQuestions2 = (KmExamQuestions) list5.get(1);
			list5 = new ArrayList();
			list5.add(kmExamQuestions1);
			list5.add(kmExamQuestions2);
		}
		request.setAttribute("kmExamQuestionsList1", list1);
		request.setAttribute("kmExamQuestionsList2", list2);
		request.setAttribute("kmExamQuestionsList3", list3);
		request.setAttribute("kmExamQuestionsList4", list4);
		request.setAttribute("kmExamQuestionsList5", list5);

		request.setAttribute(KmExamQuestionsConstants.KMEXAMQUESTIONS_LIST,
				list);
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}

	/**
	 * 查询不同类型题型下的所有题目列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchForQuestions(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmExamQuestionsConstants.KMEXAMQUESTIONS_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		// 获得要查询的题目类型
		String questionsType = StaticMethod.null2String(request
				.getParameter("questionsType"));
		String whereStr = " where kmExamQuestions.questionType = "
				+ questionsType + "and kmExamQuestions.isDeleted='0'";
		KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
		Map map = (Map) kmExamQuestionsMgr.getKmExamQuestionss(pageIndex,
				pageSize, whereStr);
		List list = (List) map.get("result");

		request.setAttribute(KmExamQuestionsConstants.KMEXAMQUESTIONS_LIST,
				list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("listQuestions");
	}

	/**
	 * 分页显示题库管理列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @re turn
	 * @throws Exception
	 */
	public ActionForward searchForChoice(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmExamQuestionsConstants.KMEXAMQUESTIONS_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
		String questionType = StaticMethod.null2String(request
				.getParameter("type"));
		String whereStr = " where questionType=" + questionType;
		Map map = (Map) kmExamQuestionsMgr.getKmExamQuestionss(pageIndex,
				pageSize, whereStr);
		List list = (List) map.get("result");
		request.setAttribute(KmExamQuestionsConstants.KMEXAMQUESTIONS_LIST,
				list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("questionType", questionType);

		return mapping.findForward("choicelist");
	}

	/**
	 * 分页显示题库管理列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchForShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String questionIDStr = StaticMethod.null2String(request
				.getParameter("questionIDStr"));
		String contentIDStr = StaticMethod.null2String(request
				.getParameter("contentIDStr"));
		String scoreStr = StaticMethod.null2String(request
				.getParameter("scoreStr"));
		KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
		String questionType = StaticMethod.null2String(request
				.getParameter("questionType"));
		String flagValue = StaticMethod.null2String(request
				.getParameter("flagValue"));
		String isPublic = StaticMethod.null2String(request
				.getParameter("isPublic"));
		int count = StaticMethod.null2int(request.getParameter("count"));
		String[] idArray = questionIDStr.split(",");
		List list = new ArrayList();
		for (int i = 0; i < idArray.length; i++) {
			String id = idArray[i];
			KmExamQuestions kmExamQuestions = kmExamQuestionsMgr
					.getKmExamQuestions(id);
			list.add(kmExamQuestions);
		}
		request.setAttribute(KmExamQuestionsConstants.KMEXAMQUESTIONS_LIST,
				list);
		request.setAttribute("questionType", questionType);
		request.setAttribute("questionIDStr", questionIDStr);
		request.setAttribute("contentIDStr", contentIDStr);
		request.setAttribute("scoreStr", scoreStr);
		request.setAttribute("flagValue", flagValue);
		request.setAttribute("count", count + "");
		request.setAttribute("isPublic", isPublic);
		return mapping.findForward("showlist");
	}

	/**
	 * 根据查询条件分页显示模块信息表列表
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
				KmExamQuestionsConstants.KMEXAMQUESTIONS_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmExamQuestionsForm kmExamQuestionsForm = (KmExamQuestionsForm) form;

		String whereStr = " where 1=1";
		if (kmExamQuestionsForm.getSpecialtyID() != null
				&& kmExamQuestionsForm.getSpecialtyID() != "") {
			whereStr += " and kmExamQuestions.specialtyID like '"
					+ kmExamQuestionsForm.getSpecialtyID() + "%'";
		}
		if (kmExamQuestionsForm.getDeptId() != null
				&& kmExamQuestionsForm.getDeptId() != "") {
			whereStr += " and kmExamQuestions.deptId = '"
					+ kmExamQuestionsForm.getDeptId() + "'";
		}
		if (kmExamQuestionsForm.getQuestionType() != null
				&& kmExamQuestionsForm.getQuestionType() != "") {
			whereStr += " and kmExamQuestions.questionType= '"
					+ kmExamQuestionsForm.getQuestionType() + "'";
		}

		whereStr += " and kmExamQuestions.isDeleted='0'";
		KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
		Map map = (Map) kmExamQuestionsMgr.getKmExamQuestionss(pageIndex,
				pageSize, whereStr);
		List list = (List) map.get("result");
		request.setAttribute(KmExamQuestionsConstants.KMEXAMQUESTIONS_LIST,
				list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("kmExamQuestionsForm", kmExamQuestionsForm);
		return mapping.findForward("list");
	}

	/**
	 * 根据查询条件分页显示模块信息表列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchXChoice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmExamQuestionsConstants.KMEXAMQUESTIONS_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmExamQuestionsForm kmExamQuestionsForm = (KmExamQuestionsForm) form;

		String whereStr = " where 1=1";
		if (kmExamQuestionsForm.getSpecialtyID() != null
				&& kmExamQuestionsForm.getSpecialtyID() != "") {
			whereStr += " and kmExamQuestions.specialtyID like '"
					+ kmExamQuestionsForm.getSpecialtyID() + "%'";
		}
		if (kmExamQuestionsForm.getDeptId() != null
				&& kmExamQuestionsForm.getDeptId() != "") {
			whereStr += " and kmExamQuestions.deptId = '"
					+ kmExamQuestionsForm.getDeptId() + "'";
		}
		if (kmExamQuestionsForm.getQuestionType() != null
				&& kmExamQuestionsForm.getQuestionType() != "") {
			whereStr += " and kmExamQuestions.questionType= '"
					+ kmExamQuestionsForm.getQuestionType() + "'";
		}

		whereStr += " and kmExamQuestions.isDeleted='0'";
		KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
		Map map = (Map) kmExamQuestionsMgr.getKmExamQuestionss(pageIndex,
				pageSize, whereStr);
		List list = (List) map.get("result");
		request.setAttribute(KmExamQuestionsConstants.KMEXAMQUESTIONS_LIST,
				list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("kmExamQuestionsForm", kmExamQuestionsForm);
		request.setAttribute("questionType", kmExamQuestionsForm
				.getQuestionType());
		return mapping.findForward("choicelist");
	}

	/**
	 * 专业表树页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("tree");
	}

	/**
	 * 生成专业表树JSON数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = StaticMethod.null2String(request.getParameter("node"));
		JSONArray jsonRoot = new JSONArray();
		KmExamSpecialtyMgr kmExamSpecialtyMgr = (KmExamSpecialtyMgr) getBean("kmExamSpecialtyMgr");
		// 取下级节点
		List list = kmExamSpecialtyMgr.getNextLevelKmExamSpecialtys(nodeId);
		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
			KmExamSpecialty kmExamSpecialty = (KmExamSpecialty) nodeIter.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", kmExamSpecialty.getNodeId());
			// TODO 添加节点名称
			jitem.put("text", kmExamSpecialty.getSpecialtyName());
			// 设置右键菜单
			jitem.put("allowChild", true);
			jitem.put("allowEdit", true);
			jitem.put("allowDelete", true);
			// 设置左键点击可触发action
			jitem.put("allowClick", true);
			// 设置是否为叶子节点
			boolean leafFlag = true;
			if (kmExamSpecialtyMgr.isHasNextLevel(kmExamSpecialty.getNodeId())) {
				leafFlag = false;
			}
			jitem.put("leaf", leafFlag);
			// TODO 设置鼠标悬浮提示
			// jitem.put("qtip", your tips here);
			jsonRoot.put(jitem);
		}
		JSONUtil.print(response, jsonRoot.toString());
		return null;
	}

	/**
	 * 进入批量上传界面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("upload");
	}

	/**
	 * 上传
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
		KmExamChoiceMgr kmExamChoiceMgr = (KmExamChoiceMgr) getBean("kmExamChoiceMgr");
		// 获取上传的excel文件
		KmExamQuestionsForm keef = (KmExamQuestionsForm) form;
		FormFile ff = keef.getFile();
		InputStream inputStream = ff.getInputStream();
		int input = 0;

		String exceptionStr = "";
		ArrayList list = new ArrayList();
		try {
			// 通过得到文件输入流inputStream 来创建一个HSSFWorkbook
			HSSFWorkbook readWorkBook = new HSSFWorkbook(inputStream);
			// 得到第一个工作表
			HSSFSheet readSheet = readWorkBook.getSheetAt(0);

			ExcelImportSample excelImportSample = null;
			int maxrow = readSheet.getPhysicalNumberOfRows();
			int col = 14;
			for (int i = 1; i < maxrow; i++) {
				HSSFRow readRow = readSheet.getRow(i);
				Map map = new HashMap();
				excelImportSample = new ExcelImportSample();
				for (int j = 0; j < col - 1; j++) {
					exceptionStr = "";
					HSSFCell readCell = readRow.getCell((short) j);
					int a = i + 1;
					int b = j + 1;
					// 题干
					if (j == 0) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						HSSFCell k = readRow.getCell((short) j);
						if (readRow.getCell((short) j) == null) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = k.toString();
							if (str.indexOf(".0") > -1) {
								int index = str.indexOf(".0");
								str = str.substring(0, index);
							}
						}
						excelImportSample.setQuestion(str);
					}
					// //问题所属专业
					// if (j == 1) {
					// String str = "";
					// exceptionStr = "你上传的列表第"+a+"行第"+b+"列出现问题。请检查！";
					// if (readRow.getCell((short) j)==null) {
					// str = "";
					// request.setAttribute("falseMessage",exceptionStr);
					// return mapping.findForward("falsePage");
					// } else {
					// //根据问题所在的专业 查询专业的
					//							 
					// str = readCell.toString();
					// }
					// excelImportSample.setSpecialtyID(str);
					// }
					// 问题类型
					if (j == 1) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j) == null) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
							if (str.equals("单选题")) {
								str = "1";
							} else if (str.equals("多选题")) {
								str = "2";
							} else if (str.equals("判断题")) {
								str = "3";
							} else if (str.equals("填空题")) {
								str = "4";
							} else if (str.equals("简答题")) {
								str = "5";
							}
						}
						excelImportSample.setQuestionType(str);
					}
					// 问题难度
					if (j == 2) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j) == null) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString();
							if (str.equals("简单")) {
								str = "1";
							} else if (str.equals("一般")) {
								str = "2";
							} else if (str.equals("难")) {
								str = "3";
							}
						}
						excelImportSample.setDifficulty(str);
					}
					// 问题答案
					if (j == 3) {
						String str = "";
						exceptionStr = "你上传的列表第" + a + "行第" + b + "列出现问题。请检查！";
						if (readRow.getCell((short) j) == null) {
							str = "";
							request.setAttribute("falseMessage", exceptionStr);
							return mapping.findForward("falsePage");
						} else {
							str = readCell.toString().toUpperCase();
							if (excelImportSample.getQuestionType() == "1"
									|| excelImportSample.getQuestionType() == "3") {
								if (str.length() > 1) {
									request.setAttribute("falseMessage",
											exceptionStr);
									return mapping.findForward("falsePage");
								}
							}
							if (excelImportSample.getQuestionType() == "1"
									|| excelImportSample.getQuestionType() == "2"
									|| excelImportSample.getQuestionType() == "3") {
								int len = str.length();
								String test = "ABCDEF";
								for (int k = 0; k < len; k++) {
									exceptionStr = "你上传的列表第" + a + "行第" + b
											+ "列出现问题。请检查！";
									// 答案中的每一个字符
									String answer = String.valueOf(str
											.charAt(k));
									if (test.indexOf(answer) < 0) {
										request.setAttribute("falseMessage",
												exceptionStr);
										return mapping.findForward("falsePage");
									}
								}
							}
							// 去除以.0结尾的
							if (str.indexOf(".0") > -1) {
								int index = str.indexOf(".0");
								str = str.substring(0, index);
							}
						}
						excelImportSample.setAnswer(str);
					}
					// 对于选择 判断题
					if (excelImportSample.getQuestionType() == "1"
							|| excelImportSample.getQuestionType() == "2"
							|| excelImportSample.getQuestionType() == "3") {
						// 选项A
						if (j == 4) {
							String str = "";
							exceptionStr = "你上传的列表第" + a + "行第" + b
									+ "列出现问题。请检查！答案中有的选项不能为空";
							// 如果答案中有A 则A一定要有
							if (readRow.getCell((short) j) == null) {
								if (excelImportSample.getAnswer().indexOf("A") > -1) {
									request.setAttribute("falseMessage",
											exceptionStr);
									return mapping.findForward("falsePage");
								} else {
									str = "";
								}
							} else {
								str = readCell.toString();
								// 去除以.0结尾的
								if (str.indexOf(".0") > -1) {
									int index = str.indexOf(".0");
									str = str.substring(0, index);
								}
							}
							excelImportSample.setA(str);
						}
						// 选项B
						if (j == 5) {
							String str = "";
							exceptionStr = "你上传的列表第" + a + "行第" + b
									+ "列出现问题。请检查！答案中有的选项不能为空";
							// 如果答案中有B 则B一定要有
							if (readRow.getCell((short) j) == null) {
								if (excelImportSample.getAnswer().indexOf("B") > -1) {
									request.setAttribute("falseMessage",
											exceptionStr);
									return mapping.findForward("falsePage");
								} else {
									str = "";
								}
							} else {
								str = readCell.toString();
								// 去除以.0结尾的
								if (str.indexOf(".0") > -1) {
									int index = str.indexOf(".0");
									str = str.substring(0, index);
								}
							}
							excelImportSample.setB(str);
						}
						// 选项C
						if (j == 6) {
							String str = "";
							exceptionStr = "你上传的列表第" + a + "行第" + b
									+ "列出现问题。请检查！答案中有的选项不能为空";
							// 如果答案中有C 则C一定要有
							if (readRow.getCell((short) j) == null) {
								if (excelImportSample.getAnswer().indexOf("C") > -1) {
									request.setAttribute("falseMessage",
											exceptionStr);
									return mapping.findForward("falsePage");
								} else {
									str = "";
								}
							} else {
								str = readCell.toString();
								// 去除以.0结尾的
								if (str.indexOf(".0") > -1) {
									int index = str.indexOf(".0");
									str = str.substring(0, index);
								}
							}
							excelImportSample.setC(str);
						}
						// 选项D
						if (j == 7) {
							String str = "";
							exceptionStr = "你上传的列表第" + a + "行第" + b
									+ "列出现问题。请检查！答案中有的选项不能为空";
							// 如果答案中有D 则D一定要有
							if (readRow.getCell((short) j) == null) {
								if (excelImportSample.getAnswer().indexOf("D") > -1) {
									request.setAttribute("falseMessage",
											exceptionStr);
									return mapping.findForward("falsePage");
								} else {
									str = "";
								}
							} else {
								str = readCell.toString();
								// 去除以.0结尾的
								if (str.indexOf(".0") > -1) {
									int index = str.indexOf(".0");
									str = str.substring(0, index);
								}
							}
							excelImportSample.setD(str);
						}
						// 选项E
						if (j == 8) {
							String str = "";
							exceptionStr = "你上传的列表第" + a + "行第" + b
									+ "列出现问题。请检查！答案中有的选项不能为空";
							// 如果答案中有E 则E一定要有
							if (readRow.getCell((short) j) == null) {
								if (excelImportSample.getAnswer().indexOf("E") > -1) {
									request.setAttribute("falseMessage",
											exceptionStr);
									return mapping.findForward("falsePage");
								} else {
									str = "";
								}
							} else {
								str = readCell.toString();
								// 去除以.0结尾的
								if (str.indexOf(".0") > -1) {
									int index = str.indexOf(".0");
									str = str.substring(0, index);
								}
							}
							excelImportSample.setE(str);
						}
						// 选项F
						if (j == 9) {
							String str = "";
							exceptionStr = "你上传的列表第" + a + "行第" + b
									+ "列出现问题。请检查！答案中有的选项不能为空";
							// 如果答案中有F 则F一定要有
							if (readRow.getCell((short) j) == null) {
								if (excelImportSample.getAnswer().indexOf("F") > -1) {
									request.setAttribute("falseMessage",
											exceptionStr);
									return mapping.findForward("falsePage");
								} else {
									str = "";
								}
							} else {
								str = readCell.toString();
								// 去除以.0结尾的
								if (str.indexOf(".0") > -1) {
									int index = str.indexOf(".0");
									str = str.substring(0, index);
								}
							}
							excelImportSample.setF(str);
						}
						if (j == 11) {
							exceptionStr = "你上传的模板错误，大于" + j + "列。请检查！";
							if (readRow.getCell((short) j) != null) {
								request.setAttribute("falseMessage",
										exceptionStr);
								return mapping.findForward("falsePage");
							}
						}
					}
				}
				list.add(excelImportSample);
			}
			ExcelImportSample eis = new ExcelImportSample();
			if (list.size() == 0) {
				request.setAttribute("falseMessage", "你上传的列表为空或者列表格式不对。请检查！");
				return mapping.findForward("falsePage");
			}

			// 当前时间
			Date createTime = StaticMethod.getLocalTime();
			// 创建人
			String createUser = this.getUser(request).getUserid();
			String createDept = this.getUser(request).getDeptid();
			// 问题所属的专业
			String specialtyID = StaticMethod.null2String(request
					.getParameter("specialtyID"));

			// 进行循环保存
			for (int i = 0; i < list.size(); i++) {
				eis = (ExcelImportSample) list.get(i);
				KmExamQuestions kmExamQuestions = new KmExamQuestions();
				kmExamQuestions.setAnswer(eis.getAnswer());
				kmExamQuestions.setCreateDept(createDept);
				kmExamQuestions.setCreateUser(createUser);
				kmExamQuestions.setCreateTime(createTime);
				// 默认谁上传 就是属于那个部门
				kmExamQuestions.setDeptId(createDept);
				kmExamQuestions.setSpecialtyID(specialtyID);
				kmExamQuestions.setDifficulty(eis.getDifficulty());
				kmExamQuestions.setQuestion(eis.getQuestion());
				kmExamQuestions.setQuestionType(eis.getQuestionType());
				kmExamQuestions.setIsDeleted("0");
				kmExamQuestionsMgr.saveKmExamQuestions(kmExamQuestions);
				if (eis.getQuestionType().equals("1")
						|| eis.getQuestionType().equals("2")
						|| eis.getQuestionType().equals("3")) {
					KmExamChoice kmExamChoice = new KmExamChoice();
					if (eis.getA() != "") {
						kmExamChoice = new KmExamChoice();
						kmExamChoice.setOrderBy("A");
						kmExamChoice.setQuestionsID(kmExamQuestions
								.getQuestionID());
						kmExamChoice.setContent(eis.getA());
						kmExamChoiceMgr.saveKmExamChoice(kmExamChoice);
					}
					if (eis.getB() != "") {
						kmExamChoice = new KmExamChoice();
						kmExamChoice.setOrderBy("B");
						kmExamChoice.setQuestionsID(kmExamQuestions
								.getQuestionID());
						kmExamChoice.setContent(eis.getB());
						kmExamChoiceMgr.saveKmExamChoice(kmExamChoice);
					}
					if (eis.getC() != "") {
						kmExamChoice = new KmExamChoice();
						kmExamChoice.setOrderBy("C");
						kmExamChoice.setQuestionsID(kmExamQuestions
								.getQuestionID());
						kmExamChoice.setContent(eis.getC());
						kmExamChoiceMgr.saveKmExamChoice(kmExamChoice);
					}
					if (eis.getD() != "") {
						kmExamChoice = new KmExamChoice();
						kmExamChoice.setOrderBy("D");
						kmExamChoice.setQuestionsID(kmExamQuestions
								.getQuestionID());
						kmExamChoice.setContent(eis.getD());
						kmExamChoiceMgr.saveKmExamChoice(kmExamChoice);
					}
					if (eis.getE() != "") {
						kmExamChoice = new KmExamChoice();
						kmExamChoice.setOrderBy("E");
						kmExamChoice.setQuestionsID(kmExamQuestions
								.getQuestionID());
						kmExamChoice.setContent(eis.getE());
						kmExamChoiceMgr.saveKmExamChoice(kmExamChoice);
					}
					if (eis.getF() != "") {
						kmExamChoice = new KmExamChoice();
						kmExamChoice.setOrderBy("F");
						kmExamChoice.setQuestionsID(kmExamQuestions
								.getQuestionID());
						kmExamChoice.setContent(eis.getF());
						kmExamChoiceMgr.saveKmExamChoice(kmExamChoice);
					}
				}
			}
			return mapping.findForward("success");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("falseMessage", exceptionStr);
			return mapping.findForward("falsePage");
		}
	}

	/**
	 * 下载模版
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String resource = "/com/boco/eoms/km/exam/config/excel.xls";
		URL configFileResource = (com.boco.eoms.km.exam.webapp.action.KmExamQuestionsAction.class)
				.getResource(resource);
		File file = new File(configFileResource.toURI());
		// 读到流中
		InputStream inStream = new FileInputStream(file);// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("application/x-msdownload;charset=GBK");
		response.setCharacterEncoding("UTF-8");
		String fileName = URLEncoder.encode(file.getName(), "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(fileName.getBytes("UTF-8"), "GBK"));

		// 循环取出流中的数据
		byte[] b = new byte[1024];
		int len;
		while ((len = inStream.read(b)) > 0)
			response.getOutputStream().write(b, 0, len);
		inStream.close();
	}

	/**
	 * 分页显示题库管理列表，支持Atom方式接入Portal
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
		// try {
		// // --------------用于分页，得到当前页号-------------
		// final Integer pageIndex = new Integer(request
		// .getParameter("pageIndex"));
		// final Integer pageSize = new Integer(request
		// .getParameter("pageSize"));
		// KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr)
		// getBean("kmExamQuestionsMgr");
		// Map map = (Map) kmExamQuestionsMgr.getKmExamQuestionss(pageIndex,
		// pageSize, "");
		// List list = (List) map.get("result");
		// KmExamQuestions kmExamQuestions = new KmExamQuestions();
		//			
		// //创建ATOM源
		// Factory factory = Abdera.getNewFactory();
		// Feed feed = factory.newFeed();
		//			
		// // 分页
		// for (int i = 0; i < list.size(); i++) {
		// kmExamQuestions = (KmExamQuestions) list.get(i);
		//				
		// // TODO 请按照下面的实例给entry赋值
		// Entry entry = feed.insertEntry();
		// entry.setTitle("<a href='" + request.getScheme() + "://"
		// + request.getServerName() + ":"
		// + request.getServerPort()
		// + request.getContextPath()
		// + "/kmExamQuestions/kmExamQuestionss.do?method=edit&id="
		// + kmExamQuestions.getId() + "' target='_blank'>"
		// + display name for list + "</a>");
		// entry.setSummary(summary);
		// entry.setContent(content);
		// entry.setLanguage(language);
		// entry.setText(text);
		// entry.setRights(tights);
		//				
		// // 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
		// entry.setUpdated(new java.util.Date());
		// entry.setPublished(new java.util.Date());
		// entry.setEdited(new java.util.Date());
		//				
		// // 为person的name属性赋值，entry.addAuthor可以随意赋值
		// Person person = entry.addAuthor(userId);
		// person.setName(userName);
		// }
		//			
		// // 每页显示条数
		// feed.setText(map.get("total").toString());
		// OutputStream os = response.getOutputStream();
		// PrintStream ps = new PrintStream(os);
		// feed.getDocument().writeTo(ps);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return null;
	}
}