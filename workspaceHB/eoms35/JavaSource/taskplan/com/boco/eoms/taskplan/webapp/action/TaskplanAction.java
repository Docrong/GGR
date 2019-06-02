package com.boco.eoms.taskplan.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.taskplan.model.Taskplan;
import com.boco.eoms.taskplan.service.ITaskplanManager;
import com.boco.eoms.taskplan.webapp.form.TaskplanForm;
import com.boco.eoms.workbench.memo.model.TawWorkbenchMemo;
import com.boco.eoms.workbench.memo.service.ITawWorkbenchMemoManager;
import com.boco.eoms.workbench.memo.webapp.form.TawWorkbenchMemoForm;

import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.taskplan.util.TaksplanConstacts;

/**
 * Action class to handle CRUD on a Taskplan object
 * 
 * @struts.action name="taskplanForm" path="/taskplans" scope="request"
 *                validate="false" parameter="method" input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main"
 *                        path="/WEB-INF/pages/taskplan/taskplanTree.jsp"
 *                        contextRelative="true"
 * 
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/taskplan/taskplanForm.jsp"
 *                        contextRelative="true"
 * 
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/taskplan/taskplanList.jsp"
 *                        contextRelative="true"
 */
public final class TaskplanAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// return mapping.findForward("search");
		return null;
	}

	public ActionForward main(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("main");
	}

	/**
	 * 根据父节点的id得到�?有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		ITaskplanManager mgr = (ITaskplanManager) getBean("ItaskplanManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TaskplanForm taskplanForm = (TaskplanForm) form;
		try {

			String id = StaticMethod.nullObject2String(request
					.getParameter("id"));
			if (taskplanForm.getId() != null) {
				ITaskplanManager mgr = (ITaskplanManager) getBean("ItaskplanManager");
				ITawSystemDeptManager deptmgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
				ITawSystemUserManager usermgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
				if (id != null && !id.equals("")) {
					Taskplan taskplan = mgr.getTaskplan(id);
					String deptid = taskplan.getDeptid();
					if (deptid != null) {
						taskplan.setDeptName(deptmgr.id2Name(deptid));
					}
					String userid = taskplan.getStakeholders();
					if(userid != null){
						taskplan.setStakeholdersName(usermgr.id2Name(userid));
					}
					taskplanForm = (TaskplanForm) convert(taskplan);
				}
			}

			updateFormBean(mapping, request, taskplanForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("edit");
	}

	/**
	 * ajax保存
	 */
	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TaskplanForm taskplanForm = (TaskplanForm) form;
		String creattime = StaticMethod.getLocalString();
		ITaskplanManager mgr = (ITaskplanManager) getBean("ItaskplanManager");
		Taskplan taskplan = (Taskplan) convert(taskplanForm);
		taskplan.setInsert_time(creattime);
		mgr.saveTaskplan(taskplan);
		JSONUtil.print(response, "操作成功");
		return search(mapping, form, request, response);
	}

	/**
	 * 
	 * @see 根据条件查询便签结果集
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

		try {

			String pageIndexName = new org.displaytag.util.ParamEncoder(
					TaksplanConstacts.TASKPLAN_LIST)
					.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

			final Integer pageIndex = new Integer(
					GenericValidator.isBlankOrNull(request
							.getParameter(pageIndexName)) ? 0 : (Integer
							.parseInt(request.getParameter(pageIndexName)) - 1));

			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			// 得到查询的条件
			String whereStr = "";
			final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
					.getPageSize();

			ITaskplanManager mgr = (ITaskplanManager) getBean("ItaskplanManager");
			ITawSystemDeptManager deptmgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
			ITawSystemDictTypeManager dictmgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
			ITawSystemUserManager usermgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
			Map map = (Map) mgr.getTaskplans(pageIndex, pageSize, whereStr);
			List list = (List) map.get("result");
			List taskList = new ArrayList();
			Taskplan taskplan = null;
			for (Iterator it = list.iterator(); it.hasNext();) {
				
				taskplan = (Taskplan) it.next();
				
				String deptid = taskplan.getDeptid();
				String project_name = taskplan.getProject_name();
				String project_decompose = taskplan.getProject_decompose();
				if (deptid != null) {
					taskplan.setDeptName(deptmgr.id2Name(deptid));
				}
				
				String userid = taskplan.getStakeholders();
				
				if(userid != null){
					taskplan.setStakeholdersName(usermgr.id2Name(userid));
				}
				if (null != project_name && !"".equals(project_name)) {
					TawSystemDictType dictType1 = dictmgr.getDictByDictId(project_name);
					taskplan.setProject_name(dictType1.getDictName());
				}
				if (null != project_decompose && !"".equals(project_decompose)) {
					TawSystemDictType dictType2 = dictmgr.getDictByDictId(project_decompose);
					taskplan.setProject_decompose(dictType2.getDictName());
				}
				taskList.add(taskplan);
			}
			//int size = ((Integer) map.get("total")).intValue();

			request.setAttribute(TaksplanConstacts.TASKPLAN_LIST, taskList);
			request.setAttribute("resultSize", map.get("total"));
			request.setAttribute("pageSize", pageSize);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}

	/**
	 * 根据模块或功能的编码，删除该对象
	 */
	public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TaskplanForm taskplanForm = (TaskplanForm) form;

		ITaskplanManager mgr = (ITaskplanManager) getBean("ItaskplanManager");
		mgr.removeTaskplan(taskplanForm.getId());

	}

	/**
	 * ajax请求修改某节点的详细信息
	 */
	public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TaskplanForm taskplanForm = (TaskplanForm) form;

		if (taskplanForm.getId() != null) {
			ITaskplanManager mgr = (ITaskplanManager) getBean("ItaskplanManager");
			Taskplan taskplan = (Taskplan) convert(taskplanForm);

			mgr.saveTaskplan(taskplan);
			// mgr.updateTaskplan(taskplan);
		}

		return null;
	}

	/**
	 * ajax请求获取某节点的详细信息
	 */
	public void xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String _strId = request.getParameter("id");
		ITaskplanManager mgr = (ITaskplanManager) getBean("ItaskplanManager");
		Taskplan taskplan = mgr.getTaskplan(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(taskplan);

		JSONUtil.print(response, jsonRoot.toString());
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TaskplanForm taskplanForm = (TaskplanForm) form;

		ITaskplanManager mgr = (ITaskplanManager) getBean("ItaskplanManager");
		mgr.removeTaskplan(taskplanForm.getId());
		return search(mapping, form, request, response);
	}
}
