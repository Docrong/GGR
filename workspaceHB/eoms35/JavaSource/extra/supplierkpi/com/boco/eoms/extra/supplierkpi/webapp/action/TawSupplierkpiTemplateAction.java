package com.boco.eoms.extra.supplierkpi.webapp.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;
//import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplateAssess;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiDictManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiItemManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateAssessManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateManager;
import com.boco.eoms.extra.supplierkpi.util.TawSupplierkpiDictUtil;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSupplierkpiTemplateForm;
import com.boco.eoms.extra.supplierkpi.webapp.util.SupplierkpiAttributes;

public final class TawSupplierkpiTemplateAction extends BaseAction {

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	/**
	 * 删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String dictId = request.getParameter("dictId");
		ITawSupplierkpiTemplateManager mgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
		String templateId = mgr.getTemplateIdBySpecialType(dictId);
		TawSupplierkpiTemplate template = mgr
				.getTawSupplierkpiTemplate(templateId);
		mgr.removeDictAndTemplate(template);

		return mapping.findForward("success");
	}

	/**
	 * 修改
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
		ITawSupplierkpiDictManager dictMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
		TawSupplierkpiTemplateForm tawSupplierkpiTemplateForm = (TawSupplierkpiTemplateForm) form;

		String dictIdNew = request.getParameter("dictIdNew");
		String dictIdEdit = request.getParameter("dictIdEdit");

		if (dictIdEdit != null) {
			ITawSupplierkpiTemplateManager mgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
			TawSupplierkpiTemplate tawSupplierkpiTemplate = mgr
					.getTawSupplierkpiTemplate(mgr
							.getTemplateIdBySpecialType(dictIdEdit));
			tawSupplierkpiTemplateForm = (TawSupplierkpiTemplateForm) convert(tawSupplierkpiTemplate);
			tawSupplierkpiTemplateForm.setSpecialType(dictIdEdit);
			tawSupplierkpiTemplateForm.setSpecialType(dictMgr
					.id2Name(dictIdEdit));
			updateFormBean(mapping, request, tawSupplierkpiTemplateForm);
			request.setAttribute("dictId", dictIdEdit);
			request.setAttribute("flag", new String("1"));
		}
		if (dictIdNew != null) {
			request.setAttribute("dictId", dictIdNew);
			request.setAttribute("flag", new String("0"));
		}

		saveToken(request);

		return mapping.findForward("edit");
	}

	/**
	 * 新增
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
		String treeRootId = ((SupplierkpiAttributes) getBean("supplierkpiAttributes"))
				.getTreeRootId();
		String dictName = request.getParameter("specialType");
		String dictId = request.getParameter("dictId");
		String flag = request.getParameter("flag");
		TawSupplierkpiTemplateForm tawSupplierkpiTemplateForm = (TawSupplierkpiTemplateForm) form;

		// 保存创建人和创建时间
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String operUser = sessionform.getUsername();
		tawSupplierkpiTemplateForm.setCreator(operUser);
		Timestamp createTime = new Timestamp(System.currentTimeMillis());
		tawSupplierkpiTemplateForm.setCreateTime(createTime.toString());
		tawSupplierkpiTemplateForm.setAssessStatus(1);

		ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
		TawSupplierkpiTemplate tawSupplierkpiTemplate = (TawSupplierkpiTemplate) convert(tawSupplierkpiTemplateForm);
		tawSupplierkpiTemplate.setAssessInstanceId("");
		tawSupplierkpiTemplate.setTemplateName("");

		// 字典
		ITawSupplierkpiDictManager dictMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
		TawSupplierkpiDict tawSupplierkpiDict = new TawSupplierkpiDict();

		// 修改
		if (flag.equals("1")) {
			tawSupplierkpiDict = dictMgr.getDictByDictId(dictId);
			tawSupplierkpiDict.setDictName(dictName);
		}// 新建
		else {
			String parentDictId = dictId;
			tawSupplierkpiDict.setDictName(dictName);
			tawSupplierkpiDict.setParentDictId(parentDictId);

			if (parentDictId == null || parentDictId.equals("")
					|| parentDictId.equals(treeRootId)) {
				String firstdictid = dictMgr.getMaxDictid(treeRootId);
				tawSupplierkpiDict.setParentDictId(treeRootId);
				tawSupplierkpiDict
						.setNodeType(TawSupplierkpiDictUtil.NODETYPE_SERVICE);
				tawSupplierkpiDict.setLeaf("1");
				tawSupplierkpiDict.setDictId(firstdictid);
			} else {
				String newdictid = dictMgr.getMaxDictid(parentDictId);
				TawSupplierkpiDict dict = new TawSupplierkpiDict();
				dict = dictMgr.getDictByDictId(parentDictId);
				if (tawSupplierkpiDict.getDictId() == null
						|| tawSupplierkpiDict.getDictId().equals("")) {
					tawSupplierkpiDict.setDictId(newdictid);
				}
				dict.setLeaf("0");
				tawSupplierkpiDict
						.setNodeType(TawSupplierkpiDictUtil.NODETYPE_SPECIAL);
				tawSupplierkpiDict.setLeaf("1");
				dictMgr.saveTawSupplierkpiDict(dict);
			}
		}
		// 防止重复提交
		if (isTokenValid(request, true)) {
			if (treeRootId.equals(dictId)) {
				dictMgr.saveTawSupplierkpiDict(tawSupplierkpiDict);
			} else {
				// 保存字典，根据返回dictId值保存专业模型
				templateMgr.saveDictAndTemplate(tawSupplierkpiDict,
						tawSupplierkpiTemplate);
			}
			return mapping.findForward("success");
		} else {
			saveToken(request);
			return mapping.findForward("success");
		}

	}

	/**
	 * 查询
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

		ITawSystemRoleManager mgr = (ITawSystemRoleManager) getBean("ItawSystemRoleManager");
		List list = mgr.getRolesByWorkflowFlag(4);
		String roleValues = "";
		for (int i = 0; i < list.size(); i++) {
			TawSystemRole tawSystemRole = (TawSystemRole) list.get(i);
			if (tawSystemRole.getParentId() != 1) {
				roleValues += "['" + tawSystemRole.getRoleName() + "', '"
						+ tawSystemRole.getRoleId() + "'],";
				request.setAttribute("defaultRoleValue", String
						.valueOf(tawSystemRole.getRoleId()));
			}
		}
		if (roleValues.endsWith(",")) {
			roleValues = roleValues.substring(0, roleValues.length() - 1);
		}
		request.setAttribute("roleValues", roleValues);
		return mapping.findForward("list");
	}

	/**
	 * 查看
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawSupplierkpiTemplateForm tawSupplierkpiTemplateForm = (TawSupplierkpiTemplateForm) form;
		String specialType = StaticMethod.null2String(tawSupplierkpiTemplateForm.getSpecialType());
		ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");

		ITawSupplierkpiDictManager dictMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
		List sonList = dictMgr.getDictSonsByDictId(specialType);
		for (int i = 0; i < sonList.size(); i++) {
			TawSupplierkpiDict tawSupplierkpiDict = (TawSupplierkpiDict) sonList
					.get(i);
			String dictId = tawSupplierkpiDict.getDictId();
			String tempId = templateMgr.getTemplateIdBySpecialType(dictId);
			if ((tempId != null) && (!"".equals(tempId))) {
				request.setAttribute("failInfo",
						"对不起,您所要查看的专业存在更详细的专业划分,请在下级专业节点上操作!");
				return mapping.findForward("fail");
			}
		}

		// 获取此专业模型当前审核状态,用于控制页面"送审"按钮是否显示
		String templateId = templateMgr.getTemplateIdBySpecialType(specialType);
		ITawSupplierkpiTemplateAssessManager templateAssessMgr = (ITawSupplierkpiTemplateAssessManager) getBean("ItawSupplierkpiTemplateAssessManager");
		TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess = templateAssessMgr
				.getTawSupplierkpiTemplateAssessByTemplateId(templateId);
		String assessStatus = String.valueOf(tawSupplierkpiTemplateAssess.getAssessStatus());
		String assessAttitude = "";
		String realAssessor = "";

		if ((tawSupplierkpiTemplateAssess.getId() == null)|| ("".equals(tawSupplierkpiTemplateAssess.getId()))) {
			assessStatus = "1";
		} else {
			realAssessor = StaticMethod.null2String(tawSupplierkpiTemplateAssess.getRealAssessor());
			assessAttitude = StaticMethod.null2String(tawSupplierkpiTemplateAssess.getAssessAttitude());
		}

		TawSupplierkpiTemplate tawSupplierkpiTemplate = templateMgr.getTawSupplierkpiTemplate(templateId);
		tawSupplierkpiTemplateForm = (TawSupplierkpiTemplateForm) convert(tawSupplierkpiTemplate);
		String statusName = "";
		if ("1".equals(assessStatus)) {
			statusName = "未送审";
		} else if ("2".equals(assessStatus)) {
			statusName = "待审核";
		} else if ("3".equals(assessStatus)) {
			statusName = "已审核";
		} else if ("4".equals(assessStatus)) {
			statusName = "已驳回";
		}
		// 借用一下模型名称
		tawSupplierkpiTemplateForm.setTemplateName(statusName);
		String specialName = dictMgr.getDictByDictId(
				tawSupplierkpiTemplateForm.getSpecialType()).getDictName();
		String serviceName = dictMgr.getDictByDictId(
				tawSupplierkpiTemplateForm.getServiceType()).getDictName();
		tawSupplierkpiTemplateForm.setSpecialType(specialName);
		tawSupplierkpiTemplateForm.setServiceType(serviceName);
		updateFormBean(mapping, request, tawSupplierkpiTemplateForm);

		// 获取专业KPI列表
		ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
		List list = itemMgr.getSpecialkpi(specialType);
		// list追加最近删除指标
		List delItemList = itemMgr
				.getItems(" from TawSupplierkpiItem where assessStatus=6 and templateId='"
						+ templateId + "' and deleted=0");
		for (int i = 0; i < delItemList.size(); i++) {
			list.add(delItemList.get(i));
		}

		ListIterator it = list.listIterator();

		request.setAttribute("specialType", specialType);
		request.setAttribute("assessStatus", assessStatus);
		request.setAttribute("realAssessor", realAssessor);
		request.setAttribute("assessAttitude", assessAttitude);
		request.setAttribute("it", it);
		request.setAttribute("size", String.valueOf(list.size()));

		return mapping.findForward("view");
	}

	/**
	 * 专业KPI树
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
		ITawSystemRoleManager mgr = (ITawSystemRoleManager) getBean("ItawSystemRoleManager");
		List list = mgr.getRolesByWorkflowFlag(4);
		String roleValues = "";
		for (int i = 0; i < list.size(); i++) {
			TawSystemRole tawSystemRole = (TawSystemRole) list.get(i);
			if (tawSystemRole.getParentId() != 1) {
				roleValues += "['" + tawSystemRole.getRoleName() + "', '"
						+ tawSystemRole.getRoleId() + "'],";
				request.setAttribute("defaultRoleValue", String
						.valueOf(tawSystemRole.getRoleId()));
			}
		}
		if (roleValues.endsWith(",")) {
			roleValues = roleValues.substring(0, roleValues.length() - 1);
		}
		request.setAttribute("roleValues", roleValues);
		return mapping.findForward("tree");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}

	/**
	 * 根据父节点的id得到所有子节点的JSON数据,服务+专业
	 */
	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String treeRootId = ((SupplierkpiAttributes) getBean("supplierkpiAttributes"))
				.getTreeRootId();
		ITawSupplierkpiDictManager dictMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
		ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");

		ArrayList list = new ArrayList();
		ArrayList dictList = new ArrayList();
		JSONArray jsonRoot = new JSONArray();
		String nodeId = request.getParameter("node");

		if (nodeId.equals(treeRootId)) {
			String hql = "select distinct(dictId) from TawSupplierkpiDict where parentDictId='"
					+ treeRootId + "'";
			dictList = (ArrayList) templateMgr.getNodesFromTemplate(hql);

			for (Iterator dictIt = dictList.iterator(); dictIt.hasNext();) {
				String dictId = (String) dictIt.next();
				list = dictMgr.getDictSonsByDictId(nodeId);

				for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
					TawSupplierkpiDict _objDict = (TawSupplierkpiDict) rowIt
							.next();
					if (dictId.equals(_objDict.getDictId())) {

						JSONObject jitem = new JSONObject();
						jitem.put("id", _objDict.getDictId());
						jitem.put("parentDictId", _objDict.getParentDictId());
						jitem.put("text", _objDict.getDictName());
						jitem.put("allowChild", false);
						jitem.put("allowNewSpecial", true);
						jitem.put("allowEditService", true);
						jitem.put("allowDelService", true);
						jitem.put("allowEdit", false);
						jitem.put("allowDelete", false);
						jitem.put("allowViewSpecial", false);
						jitem.put("qtip", "一级专业Id:" + _objDict.getDictId());

						if ((_objDict.getLeaf()) != null
								&& (_objDict.getLeaf().equals("1"))) {
							jitem.put("leaf", true);
						}
						jsonRoot.put(jitem);
					}
				}
			}
		} else {
			String hql = "select distinct(specialType) from TawSupplierkpiTemplate";
			dictList = (ArrayList) templateMgr.getNodesFromTemplate(hql);

			for (Iterator dictIt = dictList.iterator(); dictIt.hasNext();) {
				String dictId = (String) dictIt.next();
				list = dictMgr.getDictSonsByDictId(nodeId);

				for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
					TawSupplierkpiDict _objDict = (TawSupplierkpiDict) rowIt
							.next();
					if (dictId.equals(_objDict.getDictId())) {

						JSONObject jitem = new JSONObject();
						jitem.put("id", _objDict.getDictId());
						jitem.put("parentDictId", _objDict.getParentDictId());
						jitem.put("text", _objDict.getDictName());
						jitem.put("leaf", true);
						jitem.put("allowChild", false);
						jitem.put("allowNewSpecial", false);
						jitem.put("allowEditService", false);
						jitem.put("allowDelService", false);
						jitem.put("allowEdit", true);
						jitem.put("allowDelete", true);
						jitem.put("allowViewSpecial", true);
						jitem.put("qtip", "二级专业Id:" + _objDict.getDictId());
						// List sonList = dictMgr.getDictSonsByDictId(_objDict
						// .getDictId());
						// boolean hasSon = false;
						// for (int i = 0; i < sonList.size(); i++) {
						// TawSupplierkpiDict dictType = (TawSupplierkpiDict)
						// sonList
						// .get(i);
						// String id = templateMgr
						// .getTemplateIdBySpecialType(dictType
						// .getDictId());
						// if ((id != null) && (!"".equals(id))) {
						// hasSon = true;
						// }
						// }
						// if (hasSon == true) {
						// jitem.put("leaf", false);
						// jitem.put("allowNewSpecial", true);
						// jitem.put("allowEdit", true);
						// jitem.put("allowDelete", true);
						// jitem.put("allowView", false);
						// } else {
						// if (_objDict.getDictId().length() == treeRootId
						// .length() + 6) {
						// jitem.put("allowChild", false);
						// } else {
						// jitem.put("allowChild", true);
						// }
						// jitem.put("leaf", true);
						// jitem.put("allowEdit", true);
						// jitem.put("allowDelete", true);
						// jitem.put("allowView", true);
						// }
						jsonRoot.put(jitem);
					}
				}
			}
		}

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	/**
	 * 根据父节点的id得到所有子节点的JSON数据 liqiuye 071217
	 */
	public String getAllNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
//		ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");

		String nodeId = request.getParameter("node");
		ArrayList list = new ArrayList();

		ITawSupplierkpiDictManager dictMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
		list = dictMgr.getDictSonsByDictId(nodeId);

		JSONArray jsonRoot = new JSONArray();

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawSupplierkpiDict _objDict = (TawSupplierkpiDict) rowIt.next();

			JSONObject jitem = new JSONObject();
			jitem.put("id", _objDict.getDictId());
			jitem.put("dictId", _objDict.getDictId());
			jitem.put("parentDictId", _objDict.getParentDictId());
			jitem.put("text", _objDict.getDictName());

//			String templateIdBySpecialtype = StaticMethod
//					.null2String(templateMgr
//							.getTemplateIdBySpecialType(_objDict.getDictId()));
//			TawSupplierkpiItem tawSupplierkpiItem = itemMgr
//					.getItemByItemType(_objDict.getDictId());
//			String parentDictId = "";
//			if (_objDict.getDictId().length() > TawSupplierkpiDictUtil.DICTID_BETWEEN_LENGTH) {
//				parentDictId = _objDict.getDictId().substring(
//						0,
//						_objDict.getDictId().length()
//								- TawSupplierkpiDictUtil.DICTID_BETWEEN_LENGTH);
//			}
			if (TawSupplierkpiDictUtil.NODETYPE_SERVICE.equals(_objDict
					.getNodeType())) {
				jitem.put("qtip", "节点类型: 服务");
				jitem.put("allowNewItem", false);
				jitem.put("allowEdit", false);
				jitem.put("allowDelete", false);
				jitem.put("allowView", false);
				jitem.put("allowNewItemType", false);
				jitem.put("allowEditItemType", false);
			} else if (TawSupplierkpiDictUtil.NODETYPE_SPECIAL.equals(_objDict
					.getNodeType())) {
				jitem.put("qtip", "节点类型: 专业");
//				int flag = 0;
//				ITawSupplierkpiDictManager dictTypeMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
//				List sonList = dictTypeMgr.getDictSonsByDictId(_objDict
//						.getDictId());
				jitem.put("allowNewItem", true);
				jitem.put("allowEdit", false);
				jitem.put("allowDelete", false);
				jitem.put("allowView", false);
				jitem.put("allowNewItemType", true);
				jitem.put("allowEditItemType", false);
				//去掉三级专业
//				for (int i = 0; i < sonList.size(); i++) {
//					TawSupplierkpiDict tawSupplierkpiDict = (TawSupplierkpiDict) sonList
//							.get(i);
//					String dictId = tawSupplierkpiDict.getDictId();
//					String tempId = templateMgr
//							.getTemplateIdBySpecialType(dictId);
//					if ((tempId != null) && (!"".equals(tempId))) {
//						flag = 1;
//						break;
//					}
//				}
//				if (0 == flag) {
//					jitem.put("allowChild", true);
//					jitem.put("allowEdit", false);
//					jitem.put("allowDelete", false);
//					jitem.put("allowView", false);
//					jitem.put("allowNewItemType", true);
//					jitem.put("allowEditItemType", false);
//				} else {
//					jitem.put("allowChild", false);
//					jitem.put("allowEdit", false);
//					jitem.put("allowDelete", false);
//					jitem.put("allowView", false);
//					jitem.put("allowNewItemType", false);
//					jitem.put("allowEditItemType", false);
//				}
			} else if (TawSupplierkpiDictUtil.NODETYPE_ITEM.equals(_objDict
					.getNodeType())) {
				jitem.put("qtip", "节点类型: 评估指标");
				jitem.put("allowNewItem", false);
				jitem.put("allowEdit", true);
				jitem.put("allowDelete", true);
				jitem.put("allowView", true);
				jitem.put("allowNewItemType", false);
				jitem.put("allowEditItemType", false);
			} else if (TawSupplierkpiDictUtil.NODETYPE_ITEMTYPE.equals(_objDict
					.getNodeType())) {
				jitem.put("qtip", "节点类型: 项目分类");
				jitem.put("allowNewItem", true);
				jitem.put("allowEdit", false);
				jitem.put("allowDelete", true);
				jitem.put("allowView", false);
				jitem.put("allowNewItemType", true);
				jitem.put("allowEditItemType", true);
			}
			jitem.put("qtipTitle", _objDict.getDictName());

			if ((_objDict.getLeaf()) != null
					&& (_objDict.getLeaf().equals("1"))) {
				jitem.put("leaf", true);
			}
			jsonRoot.put(jitem);
		}

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	public String getMonitorNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String treeRootId = ((SupplierkpiAttributes) getBean("supplierkpiAttributes"))
				.getTreeRootId();
		ITawSupplierkpiDictManager dictMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
		ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");

		ArrayList list = new ArrayList();
		ArrayList dictList = new ArrayList();
		JSONArray jsonRoot = new JSONArray();
		String nodeId = request.getParameter("node");

		if (nodeId.equals(treeRootId)) {
			String hql = "select distinct(dictId) from TawSupplierkpiDict where parentDictId='"
					+ treeRootId + "'";
			dictList = (ArrayList) templateMgr.getNodesFromTemplate(hql);

			for (Iterator dictIt = dictList.iterator(); dictIt.hasNext();) {
				String dictId = (String) dictIt.next();
				list = dictMgr.getDictSonsByDictId(nodeId);

				for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
					TawSupplierkpiDict _objDict = (TawSupplierkpiDict) rowIt
							.next();
					if (dictId.equals(_objDict.getDictId())) {

						JSONObject jitem = new JSONObject();
						jitem.put("id", _objDict.getDictId());
						jitem.put("parentDictId", _objDict.getParentDictId());
						jitem.put("text", _objDict.getDictName());
						jitem.put("allowEdit", false);
						jitem.put("allowDelete", false);
						jitem.put("allowView", false);

						if ((_objDict.getLeaf()) != null
								&& (_objDict.getLeaf().equals("1"))) {
							jitem.put("leaf", true);
						}
						jsonRoot.put(jitem);
					}
				}
			}
		} else {
			String hql = "select distinct(specialType) from TawSupplierkpiTemplate";
			dictList = (ArrayList) templateMgr.getNodesFromTemplate(hql);

			for (Iterator dictIt = dictList.iterator(); dictIt.hasNext();) {
				String dictId = (String) dictIt.next();
				list = dictMgr.getDictSonsByDictId(nodeId);

				for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
					TawSupplierkpiDict _objDict = (TawSupplierkpiDict) rowIt
							.next();
					if (dictId.equals(_objDict.getDictId())) {

						JSONObject jitem = new JSONObject();
						jitem.put("id", _objDict.getDictId());
						jitem.put("parentDictId", _objDict.getParentDictId());
						jitem.put("text", _objDict.getDictName());
						List sonList = dictMgr.getDictSonsByDictId(_objDict
								.getDictId());
						boolean hasSon = false;
						for (int i = 0; i < sonList.size(); i++) {
							TawSupplierkpiDict dictType = (TawSupplierkpiDict) sonList
									.get(i);
							String id = templateMgr
									.getTemplateIdBySpecialType(dictType
											.getDictId());
							if ((id != null) && (!"".equals(id))) {
								hasSon = true;
							}
						}
						if (hasSon == true) {
							jitem.put("leaf", false);
							jitem.put("allowEdit", false);
							jitem.put("allowDelete", false);
							jitem.put("allowView", false);
						} else {
							jitem.put("leaf", true);
							jitem.put("allowEdit", true);
							jitem.put("allowDelete", true);
							jitem.put("allowView", true);
						}
						jsonRoot.put(jitem);
					}
				}
			}
		}

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	public ActionForward addItemType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String parentDictId = request.getParameter("parentDictId");

		request.setAttribute("oper", "new");
		request.setAttribute("parentDictId", parentDictId);
		return mapping.findForward("addItemType");
	}
}
