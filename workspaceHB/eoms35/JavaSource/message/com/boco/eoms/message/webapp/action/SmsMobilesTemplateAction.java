package com.boco.eoms.message.webapp.action;

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
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.message.mgr.ISmsMobilesTemplateManager;
import com.boco.eoms.message.model.SmsMobilesTemplate;
import com.boco.eoms.message.model.SmsUserMgr;
import com.boco.eoms.message.webapp.form.SmsMobilesTemplateForm;
import com.boco.eoms.message.webapp.form.SmsUserForm;
import com.boco.eoms.parter.baseinfo.contract.util.TawContractConstants;

/**
 * Action class to handle CRUD on a SmsMobilesTemplate object
 * 
 * @struts.action name="smsMobilesTemplateForm" path="/smsMobilesTemplates"
 *                scope="request" validate="false" parameter="method"
 *                input="main"
 * @struts.action-set-property property="cancellable" value="true"
 * 
 * @struts.action-forward name="main"
 *                        path="/WEB-INF/pages/smsMobilesTemplate/smsMobilesTemplateTree.jsp"
 *                        contextRelative="true"
 * 
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/smsMobilesTemplate/smsMobilesTemplateForm.jsp"
 *                        contextRelative="true"
 * 
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/smsMobilesTemplate/smsMobilesTemplateList.jsp"
 *                        contextRelative="true"
 */
public final class SmsMobilesTemplateAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// return mapping.findForward("search");
		return null;
	}

	public ActionForward main(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		List returnList = mgr.getMobileTempByDeleted("0");
		request.setAttribute("mobilesList", returnList);
		return mapping.findForward("list");
	}

	public ActionForward forward2New(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("newMsgSend");
	}

	/**
	 * 根据父节点的id得到扄1�7有子节点的JSON数据
	 */
	public void xGetChildNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");

		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		JSONArray json = mgr.xGetChildNodes(nodeId);

		JSONUtil.print(response, json.toString());
	}

	/**
	 * ajax保存
	 */
	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String deleted = StaticMethod.null2String(request
				.getParameter("deleted"));
		SmsMobilesTemplateForm smsMobilesTemplateForm = (SmsMobilesTemplateForm) form;

		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		SmsMobilesTemplate smsMobilesTemplate = (SmsMobilesTemplate) convert(smsMobilesTemplateForm);
		if (deleted != null && deleted.equals("2")) {
			smsMobilesTemplate.setDeleted("2");
		} else {
			smsMobilesTemplate.setDeleted("0");
		}

		mgr.saveSmsMobilesTemplate(smsMobilesTemplate);
		List returnList = new ArrayList();
		if (!deleted.equals("2")) {
			deleted = "0";
		}
		returnList = mgr.getMobileTempByDeleted(deleted);
		request.setAttribute("mobilesList", returnList);
		return mapping.findForward("list");
	}

	/**
	 * 根据模块或功能的编码，删除该对象
	 */
	public ActionForward xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SmsMobilesTemplateForm smsMobilesTemplateForm = (SmsMobilesTemplateForm) form;

		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		SmsMobilesTemplate smsMobilesTemplate = mgr
				.getSmsMobilesTemplate(smsMobilesTemplateForm.getId());
		if (smsMobilesTemplate.getDeleted().equals("2")) {
			mgr.removeSmsMobilesTemplate(smsMobilesTemplateForm.getId());
			return mapping.findForward("success");
		}
		mgr.removeSmsMobilesTemplate(smsMobilesTemplateForm.getId());
		return main(mapping, form, request, response);

	}

	public ActionForward xeditInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONArray jsonRoot = new JSONArray();
		String jsonroot = "";
		SmsMobilesTemplateForm smsMobilesTemplateForm = (SmsMobilesTemplateForm) form;
		ITawSystemUserManager uMgr = (ITawSystemUserManager) getBean("itawSystemUserManager");
		String id = request.getParameter("id");
		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		SmsMobilesTemplate smsMobilesTemplate = mgr.getSmsMobilesTemplate(id);
		smsMobilesTemplate.setRemark(smsMobilesTemplate.getRemark().trim());
		String delete = smsMobilesTemplate.getDeleted();
		if (smsMobilesTemplateForm.getId() != null) {
			smsMobilesTemplateForm = (SmsMobilesTemplateForm) convert(smsMobilesTemplate);
			request.setAttribute("smsMobilesTemplateForm",
					smsMobilesTemplateForm);
		}
		String users = smsMobilesTemplateForm.getUsers();
		if (users != null && !users.equals("")) {
			String[] userArray = users.split(",");
			for (int i = 0; i < userArray.length; i++) {
				JSONObject jitem = new JSONObject();
				jitem.put(UIConstants.JSON_NODETYPE, "user");
				jitem.put("id", userArray[i]);
				String userName = ((TawSystemUser) uMgr
						.getUserByuserid(userArray[i])).getUsername();
				jitem.put("name", userName);
				jsonRoot.put(jitem);
			}
			jsonroot = jsonRoot.toString();
		} else {
			jsonroot = "[]";
		}
		request.setAttribute("jsonString", jsonroot);
		if (delete.equals("2")) {
			return mapping.findForward("xedit");
		}

		return mapping.findForward("editInit");
	}

	/**
	 * ajax请求修改某节点的详细信息
	 */
	public ActionForward xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SmsMobilesTemplateForm smsMobilesTemplateForm = (SmsMobilesTemplateForm) form;

		if (smsMobilesTemplateForm.getId() != null) {
			ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
			SmsMobilesTemplate smsMobilesTemplate = (SmsMobilesTemplate) convert(smsMobilesTemplateForm);

			mgr.saveSmsMobilesTemplate(smsMobilesTemplate);
			// mgr.updateSmsMobilesTemplate(smsMobilesTemplate);
		}

		return mapping.findForward("success");
	}

	/**
	 * ajax请求获取某节点的详细信息
	 */
	public void xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String _strId = request.getParameter("id");
		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		SmsMobilesTemplate smsMobilesTemplate = mgr
				.getSmsMobilesTemplate(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(smsMobilesTemplate);

		JSONUtil.print(response, jsonRoot.toString());
	}

	public ActionForward getUsersList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("getUsersList");

	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		String id = StaticMethod.null2String(request.getParameter("id"));

		return mapping.findForward("success");

	}

	public void getNodes4Team(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取节点id
		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		// 取某版块对象
		List list = mgr.getNodes4Team();

		// json根（树根节点）
		JSONArray root = new JSONArray();
		// 遍历子版块
		if (null != list) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				SmsMobilesTemplate smsMobilesTemplate = (SmsMobilesTemplate) it
						.next();
				JSONObject item = new JSONObject();
				item.put("id", smsMobilesTemplate.getId());
				item.put("text", smsMobilesTemplate.getName());
				item.put(UIConstants.JSON_NODETYPE, "folder");
				item.put("leaf", "1");
				item.put("iconCls", "folder");
				item.put("allowNewForum", false);
				item.put("allowEditForum", true);
				item.put("allowDelForum", true);

				root.put(item);
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(root.toString());

	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SmsMobilesTemplateForm smsMobilesTemplateForm = (SmsMobilesTemplateForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (smsMobilesTemplateForm.getId() != null) {
			ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
			SmsMobilesTemplate smsMobilesTemplate = mgr
					.getSmsMobilesTemplate(smsMobilesTemplateForm.getId());
			smsMobilesTemplateForm = (SmsMobilesTemplateForm) convert(smsMobilesTemplate);
			updateFormBean(mapping, request, smsMobilesTemplateForm);
		}

		return mapping.findForward("detail");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		String id = StaticMethod.null2String(request.getParameter("id"));
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TawContractConstants.TAWCONTRACT_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		Map map = (Map) mgr.getUsersListById(pageIndex, pageSize, id);
		List list = (List) map.get("result");
		request.setAttribute("teamId", id);
		request.setAttribute(TawContractConstants.TAWCONTRACT_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("userList");

	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SmsMobilesTemplateForm smsfrom = (SmsMobilesTemplateForm) form;

		return mapping.findForward("add");

	}

	public ActionForward xsaveTeam(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String deleted = StaticMethod.null2String(request
				.getParameter("deleted"));
		SmsMobilesTemplateForm smsMobilesTemplateForm = (SmsMobilesTemplateForm) form;

		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		SmsMobilesTemplate smsMobilesTemplate = (SmsMobilesTemplate) convert(smsMobilesTemplateForm);
		smsMobilesTemplate.setDeleted("2");
		smsMobilesTemplate.setLeaf("1");

		mgr.saveSmsMobilesTemplate(smsMobilesTemplate);
		List returnList = new ArrayList();
		if (!deleted.equals("2")) {
			deleted = "0";
		}
		returnList = mgr.getMobileTempByDeleted(deleted);
		request.setAttribute("mobilesList", returnList);
		return mapping.findForward("success");
	}

	public ActionForward xeditTeam(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("xedit");
	}

	public ActionForward addNewUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// String id = (String)request.getParameter("teamId");
		// SmsMobilesTemplateForm smsMobilesTemplateForm =
		// (SmsMobilesTemplateForm) form;
		// System.out.println(request.getAttribute("teamId") + "&&&&&&&&&&&");

		return mapping.findForward("newUser");
	}

	public ActionForward saveUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SmsMobilesTemplateForm smsMobilesTemplateForm = (SmsMobilesTemplateForm) form;
		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		
		String teamId = (String) request.getParameter("teamId");
		String id = request.getParameter("id");
		
		if("".equals(id)){
		SmsUserMgr smsUserMgr = new SmsUserMgr();
		smsUserMgr.setMobile(smsMobilesTemplateForm.getMobile());
		smsUserMgr.setName(smsMobilesTemplateForm.getUserName());
		smsUserMgr.setTeamId(teamId);
		smsUserMgr.setDept(smsMobilesTemplateForm.getDept());
		smsUserMgr.setPosition(smsMobilesTemplateForm.getPosition());
		smsUserMgr.setTeamId(smsMobilesTemplateForm.getTeamId());
		mgr.saveSmsUser(smsUserMgr);
		}else{
			SmsUserMgr smsUserMgr = new SmsUserMgr();
			smsUserMgr.setId(id);
			smsUserMgr.setMobile(smsMobilesTemplateForm.getMobile());
			smsUserMgr.setName(smsMobilesTemplateForm.getUserName());
			smsUserMgr.setTeamId(teamId);
			smsUserMgr.setDept(smsMobilesTemplateForm.getDept());
			smsUserMgr.setPosition(smsMobilesTemplateForm.getPosition());
			smsUserMgr.setTeamId(smsMobilesTemplateForm.getTeamId());
			mgr.saveSmsUser(smsUserMgr);
		}
		
		return mapping.findForward("success");
	}

	public ActionForward editUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		String id = (String) request.getParameter("id");
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TawContractConstants.TAWCONTRACT_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		List list = mgr.getSmsUserMgr(id);
		SmsUserMgr smsUserMgr =(SmsUserMgr)list.get(0);
		SmsUserForm smsUserMgrForm =  new SmsUserForm();
		smsUserMgrForm.setDept(smsUserMgr.getDept());
		smsUserMgrForm.setMobile(smsUserMgr.getMobile());
		smsUserMgrForm.setId(smsUserMgr.getId());
		smsUserMgrForm.setName(smsUserMgr.getName());
		smsUserMgrForm.setPosition(smsUserMgr.getPosition());
		smsUserMgrForm.setTeamId(smsUserMgr.getTeamId());
		/*request.setAttribute("resultSize", new Integer(list.size()));
		request.setAttribute("pageSize", pageSize);*/
		request.setAttribute("smsUserMgrForm", smsUserMgrForm);
		return mapping.findForward("editInitUser");
	}

	public ActionForward removeUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ISmsMobilesTemplateManager mgr = (ISmsMobilesTemplateManager) getBean("IsmsMobilesTemplateManager");
		String id = (String) request.getParameter("id");
		mgr.removeUser(id);
		return mapping.findForward("success");

	}
}
