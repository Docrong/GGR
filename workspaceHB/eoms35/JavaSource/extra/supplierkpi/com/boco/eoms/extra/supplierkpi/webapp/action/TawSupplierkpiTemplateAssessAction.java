package com.boco.eoms.extra.supplierkpi.webapp.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.util.RoleManage;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplateAssess;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiAssessInstanceManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiDictManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiItemManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateAssessManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateManager;
import com.boco.eoms.extra.supplierkpi.util.SupplierkpiConstants;
import com.boco.eoms.extra.supplierkpi.util.TawSupplierkpiDictUtil;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSupplierkpiTemplateAssessForm;
import com.boco.eoms.extra.supplierkpi.webapp.util.SupplierkpiAttributes;

public final class TawSupplierkpiTemplateAssessAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		TawSupplierkpiTemplateAssessForm tawSupplierkpiTemplateAssessForm = (TawSupplierkpiTemplateAssessForm) form;

		// Exceptions are caught by ActionExceptionHandler
		ITawSupplierkpiTemplateAssessManager mgr = (ITawSupplierkpiTemplateAssessManager) getBean("ItawSupplierkpiTemplateAssessManager");
		mgr.removeTawSupplierkpiTemplateAssess(tawSupplierkpiTemplateAssessForm
				.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawSupplierkpiTemplateAssess.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("search");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSupplierkpiTemplateAssessForm tawSupplierkpiTemplateAssessForm = (TawSupplierkpiTemplateAssessForm) form;
		String specialType = StaticMethod.null2String(request
				.getParameter("specialType"));

		ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
		ITawSupplierkpiTemplateAssessManager templateAssessMgr = (ITawSupplierkpiTemplateAssessManager) getBean("ItawSupplierkpiTemplateAssessManager");
		String templateId = StaticMethod.null2String(templateMgr
				.getTemplateIdBySpecialType(specialType));
		TawSupplierkpiTemplate tawSupplierkpiTemplate = templateMgr
				.getTawSupplierkpiTemplate(templateId);
		String serviceType = StaticMethod.null2String(tawSupplierkpiTemplate
				.getServiceType());
		TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess = templateAssessMgr
				.getTawSupplierkpiTemplateAssessByTemplateId(templateId);
		if ((tawSupplierkpiTemplateAssess.getId() == null)
				|| ("".equals(tawSupplierkpiTemplateAssess.getId()))) {
			return null;
		}
		tawSupplierkpiTemplateAssessForm = (TawSupplierkpiTemplateAssessForm) convert(tawSupplierkpiTemplateAssess);
		ITawSystemRoleManager roleMgr = (ITawSystemRoleManager) getBean("ItawSystemRoleManager");
		String roleName = StaticMethod.null2String(roleMgr.getRoleNameById(Long
				.parseLong(tawSupplierkpiTemplateAssessForm.getAssessRole())));
		tawSupplierkpiTemplateAssessForm.setAssessRole(roleName);

		// 获取专业KPI列表
		ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
		List list = itemMgr.getSpecialkpi(specialType);
		// list追加最近删除指标
		/**
		 * List delItemList = itemMgr.getItems(" from TawSupplierkpiItem where
		 * assessStatus=6 and deleted=0"); for (int i = 0; i <
		 * delItemList.size(); i++) { list.add(delItemList.get(i)); }
		 */

		ListIterator it = list.listIterator();

		request.setAttribute("specialType", specialType);
		request.setAttribute("serviceType", serviceType);
		request.setAttribute("assessStatus", "待审核");
		request.setAttribute("it", it);
		updateFormBean(mapping, request, tawSupplierkpiTemplateAssessForm);

		return mapping.findForward("edit");
	}

	/**
	 * 保存或更新专业模型审核信息,具体操作据当前审核状态而定
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
		String specialType = request.getParameter("specialType");
		if ((specialType == null) || ("".equals(specialType))) {
			return mapping.findForward("fail");
		}

		TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess = new TawSupplierkpiTemplateAssess();
		// 获取此专业模型当前审核状态
		ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
		String templateId = templateMgr.getTemplateIdBySpecialType(specialType);
		ITawSupplierkpiTemplateAssessManager templateAssessMgr = (ITawSupplierkpiTemplateAssessManager) getBean("ItawSupplierkpiTemplateAssessManager");
		tawSupplierkpiTemplateAssess = templateAssessMgr
				.getTawSupplierkpiTemplateAssessByTemplateId(templateId);
		int assessStatus = tawSupplierkpiTemplateAssess.getAssessStatus();
		if ((tawSupplierkpiTemplateAssess.getId() == null)
				|| ("".equals(tawSupplierkpiTemplateAssess.getId()))) {
			assessStatus = 1;
		} else {
			if (1 == tawSupplierkpiTemplateAssess.getAssessStatus()) {
				String isUpdate = StaticMethod.null2String(request
						.getParameter("isUpdate"));
				if ("1".equals(isUpdate)) {
					tawSupplierkpiTemplateAssess.setIsUpdate(1);
				} else {
					tawSupplierkpiTemplateAssess.setIsUpdate(0);
				}
			}
		}

		// 新增的模型或者被驳回的模型状态设置为待审核
		if ((1 == assessStatus) || (4 == assessStatus)) {
			// 获取部门Id
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String deptId = sessionform.getDeptid();
			String roleId = ((SupplierkpiAttributes) getBean("supplierkpiAttributes"))
					.getPgAdminRole();
			String subRoleId = "";
			Hashtable filterHash = new Hashtable();
			filterHash.put("deptId", deptId);
			filterHash.put("class2", specialType);
			List list = RoleManage.getInstance()
					.getSubRoles(roleId, filterHash);
			if (list.size() > 0) {
				TawSystemSubRole tawSystemSubRole = (TawSystemSubRole) list
						.get(0);
				subRoleId = String.valueOf(tawSystemSubRole.getRoleId());
			} else {
				// String deptName = sessionform.getDeptname();
				// ITawSystemRoleManager roleMgr = (ITawSystemRoleManager)
				// getBean("ItawSystemRoleManager");
				// String roleName =
				// roleMgr.getRoleNameById(Long.parseLong(roleId));
				// TawSystemDictTypeDaoHibernate dao =
				// (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
				// .getInstance().getBean("ItawSystemDictTypeDao");
				// String specialName = dao.id2Name(specialType);
				// String failInfo = "未找到 角色: " + roleName + " 部门: " + deptName
				// + " 专业: " + specialName + " 的子角色!";
				String failInfo = "未找到子角色!";
				request.setAttribute("failInfo", failInfo);
				return mapping.findForward("fail");
			}

			Timestamp createTime = new Timestamp(System.currentTimeMillis());

			tawSupplierkpiTemplateAssess.setAssessTime(createTime);
			tawSupplierkpiTemplateAssess.setAssessRole(subRoleId);
			tawSupplierkpiTemplateAssess.setTemplateId(templateId);
			tawSupplierkpiTemplateAssess.setAssessStatus(2);
			if (1 == assessStatus) {
				tawSupplierkpiTemplateAssess.setIsKpiChanged(0);
			}

			templateAssessMgr
					.saveTawSupplierkpiTemplateAssess(tawSupplierkpiTemplateAssess);
		}
		// 模型已经送审,状态为"待审核"
		else if (2 == assessStatus) {
			// 获取相关信息
			String assessAttitude = request.getParameter("assessAttitude");
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String userName = sessionform.getUsername();
			Timestamp assessTime = new Timestamp(System.currentTimeMillis());
			tawSupplierkpiTemplateAssess.setAssessAttitude(assessAttitude);
			tawSupplierkpiTemplateAssess.setRealAssessor(userName);
			tawSupplierkpiTemplateAssess.setAssessTime(assessTime);
			String opertype = request.getParameter("opertype");
			// 用户选择通过审核
			if ("1".equals(opertype)) {
				tawSupplierkpiTemplateAssess.setAssessStatus(3);
				tawSupplierkpiTemplateAssess.setIsKpiChanged(0);
			}
			// 用户选择驳回
			else if ("2".equals(opertype)) {
				// 被驳回专业有问题的KPI状态为4
				ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
				String[] kpiIds = request.getParameterValues("checkbox");
				int size = 0;
				if (kpiIds != null) {
					size = kpiIds.length;
				}
				for (int i = 0; i < size; i++) {
					TawSupplierkpiItem item = itemMgr.getTawSupplierkpiItem(
							kpiIds[i], SupplierkpiConstants.UNDELETED);
					item.setAssessStatus(4);
					itemMgr.saveTawSupplierkpiItem(item);
				}
				tawSupplierkpiTemplateAssess.setAssessStatus(4);
				tawSupplierkpiTemplateAssess.setIsKpiChanged(0);
			} else {
				return mapping.findForward("fail");
			}
			templateAssessMgr
					.saveTawSupplierkpiTemplateAssess(tawSupplierkpiTemplateAssess);
		} else {
			return mapping.findForward("fail");
		}
		return mapping.findForward("success");
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				"tawDemoMytableList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE); // ҳ��Ĳ�����,����"tawDemoMytableList"��ҳ����displayTag��id
		final int pageSize = 25; // ÿҳ��ʾ������
		final int pageIndex = GenericValidator.isBlankOrNull(request
				.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request
				.getParameter(pageIndexName)) - 1); // ��ǰҳ��

		ITawSupplierkpiTemplateAssessManager mgr = (ITawSupplierkpiTemplateAssessManager) getBean("ItawSupplierkpiTemplateAssessManager");
		Map map = (Map) mgr.getTawSupplierkpiTemplateAssesss(pageIndex,
				pageSize); // map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
		List list = (List) map.get("result");
		request.setAttribute(Constants.TAWSUPPLIERKPITEMPLATEASSESS_LIST, list);
		request.setAttribute("resultSize", map.get("total"));

		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}

	/**
	 * 根据父节点的id得到未审核专业，只显示两层 liqiuye 071123
	 */
	public String getNodesUnchecked(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String treeRootId = ((SupplierkpiAttributes) getBean("supplierkpiAttributes"))
				.getTreeRootId();
		ITawSupplierkpiDictManager dictMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
		ITawSupplierkpiTemplateAssessManager templateAssessMgr = (ITawSupplierkpiTemplateAssessManager) getBean("ItawSupplierkpiTemplateAssessManager");

		ArrayList list = new ArrayList();
		ArrayList dictList = new ArrayList();
		JSONArray jsonRoot = new JSONArray();
		String nodeId = request.getParameter("node");

		if (nodeId.equals(treeRootId)) {
			String hql = "select distinct(serviceType) from TawSupplierkpiTemplate where id in (select distinct(templateId) from TawSupplierkpiTemplateAssess where assessStatus='2')";
			dictList = (ArrayList) templateAssessMgr
					.getNodesFromTemplateAssess(hql);

			for (Iterator dictIt = dictList.iterator(); dictIt.hasNext();) {
				String dictId = (String) dictIt.next();
				list = dictMgr.getDictSonsByDictId(nodeId);

				for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
					TawSupplierkpiDict _objDictType = (TawSupplierkpiDict) rowIt
							.next();
					if (dictId.equals(_objDictType.getDictId())) {

						JSONObject jitem = new JSONObject();
						jitem.put("id", _objDictType.getDictId());
						jitem.put("parentDictId", _objDictType
								.getParentDictId());
						jitem.put("text", _objDictType.getDictName());
						jitem.put("allowEdit", false);

						if ((_objDictType.getLeaf()) != null
								&& (_objDictType.getLeaf().equals("1"))) {
							jitem.put("leaf", true);
						}
						jsonRoot.put(jitem);
					}
				}
			}
		} else {
			String hql = "select distinct(specialType) from TawSupplierkpiTemplate where id in (select distinct(templateId) from TawSupplierkpiTemplateAssess where assessStatus='2')";
			dictList = (ArrayList) templateAssessMgr
					.getNodesFromTemplateAssess(hql);
			ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
			List tempList = dictList;
			// for (int i = 0; i < tempList.size(); i++) {
			// String special = tempList.get(i).toString();
			//				
			// while (special.length() >= 2) {
			// special = special.substring(0, special.length()-2);
			// String templateId =
			// templateMgr.getTemplateIdBySpecialType(special);
			// if ((templateId != null) && (!"".equals(templateId))) {
			// dictList.add(special);
			// }
			// }
			// }
			if (tempList.size() > 0) {
				String special = tempList.get(0).toString();
				while (special.length() >= 2) {
					special = special.substring(0, special.length()
							- TawSupplierkpiDictUtil.DICTID_BETWEEN_LENGTH);
					String templateId = templateMgr
							.getTemplateIdBySpecialType(special);
					if ((templateId != null) && (!"".equals(templateId))) {
						dictList.add(special);
					}
				}
			}
			for (Iterator dictIt = dictList.iterator(); dictIt.hasNext();) {
				String dictId = (String) dictIt.next();
				list = dictMgr.getDictSonsByDictId(nodeId);

				for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
					TawSupplierkpiDict _objDictType = (TawSupplierkpiDict) rowIt
							.next();
					if (dictId.equals(_objDictType.getDictId())) {

						JSONObject jitem = new JSONObject();
						jitem.put("id", _objDictType.getDictId());
						jitem.put("parentDictId", _objDictType
								.getParentDictId());
						jitem.put("text", _objDictType.getDictName());
						List sonList = dictMgr.getDictSonsByDictId(_objDictType
								.getDictId());
						boolean hasSon = false;
						for (int i = 0; i < sonList.size(); i++) {
							TawSupplierkpiDict dictType = (TawSupplierkpiDict) sonList
									.get(i);
							String id = templateMgr
									.getTemplateIdBySpecialType(dictType
											.getDictId());
							if ((id != null) && (!"".equals(id))) {
								TawSupplierkpiTemplateAssess templateAssess = templateAssessMgr
										.getTawSupplierkpiTemplateAssessByTemplateId(id);
								if ((templateAssess.getId() != null)
										&& (!"".equals(templateAssess.getId())))
									hasSon = true;
							}
						}
						if (hasSon == true) {
							jitem.put("leaf", false);
							jitem.put("allowEdit", false);
						} else {
							jitem.put("leaf", true);
							jitem.put("allowEdit", true);
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

	/**
	 * 根据父节点的id得到已审核专业，只显示两层 liqiuye 071124
	 */
	public String getNodesChecked(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String treeRootId = ((SupplierkpiAttributes) getBean("supplierkpiAttributes"))
				.getTreeRootId();
		ITawSupplierkpiDictManager dictMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
		ITawSupplierkpiTemplateAssessManager templateAssessMgr = (ITawSupplierkpiTemplateAssessManager) getBean("ItawSupplierkpiTemplateAssessManager");

		ArrayList list = new ArrayList();
		ArrayList dictList = new ArrayList();
		JSONArray jsonRoot = new JSONArray();
		String nodeId = request.getParameter("node");

		if (nodeId.equals(treeRootId)) {
			String hql = "select distinct(serviceType) from TawSupplierkpiTemplate where id in (select distinct(templateId) from TawSupplierkpiTemplateAssess where assessStatus='3')";
			dictList = (ArrayList) templateAssessMgr
					.getNodesFromTemplateAssess(hql);

			for (Iterator dictIt = dictList.iterator(); dictIt.hasNext();) {
				String dictId = (String) dictIt.next();
				list = dictMgr.getDictSonsByDictId(nodeId);

				for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
					TawSupplierkpiDict _objDictType = (TawSupplierkpiDict) rowIt
							.next();
					if (dictId.equals(_objDictType.getDictId())) {

						JSONObject jitem = new JSONObject();
						jitem.put("id", _objDictType.getDictId());
						jitem.put("parentDictId", _objDictType
								.getParentDictId());
						jitem.put("text", _objDictType.getDictName());
						jitem.put("allowEdit", false);
						jitem.put("allowView", false);

						if ((_objDictType.getLeaf()) != null
								&& (_objDictType.getLeaf().equals("1"))) {
							jitem.put("leaf", true);
						}
						jsonRoot.put(jitem);
					}
				}
			}
		} else {
			String hql = "select distinct(specialType) from TawSupplierkpiTemplate where id in (select distinct(templateId) from TawSupplierkpiTemplateAssess where assessStatus='3')";
			dictList = (ArrayList) templateAssessMgr
					.getNodesFromTemplateAssess(hql);
			ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
			List tempList = dictList;
			// for (int i = 0; i < tempList.size(); i++) {
			// String special = tempList.get(i).toString();
			//				
			// while (special.length() >= 2) {
			// special = special.substring(0, special.length()-2);
			// String templateId =
			// templateMgr.getTemplateIdBySpecialType(special);
			// if ((templateId != null) && (!"".equals(templateId))) {
			// dictList.add(special);
			// }
			// }
			// }
			if (tempList.size() > 0) {
				String special = tempList.get(0).toString();
				while (special.length() >= 2) {
					special = special.substring(0, special.length() - 2);
					String templateId = templateMgr
							.getTemplateIdBySpecialType(special);
					if ((templateId != null) && (!"".equals(templateId))) {
						dictList.add(special);
					}
				}
			}
			for (Iterator dictIt = dictList.iterator(); dictIt.hasNext();) {
				String dictId = (String) dictIt.next();
				list = dictMgr.getDictSonsByDictId(nodeId);

				for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
					TawSupplierkpiDict _objDictType = (TawSupplierkpiDict) rowIt
							.next();
					if (dictId.equals(_objDictType.getDictId())) {

						JSONObject jitem = new JSONObject();
						jitem.put("id", _objDictType.getDictId());
						jitem.put("parentDictId", _objDictType
								.getParentDictId());
						jitem.put("text", _objDictType.getDictName());
						List sonList = dictMgr.getDictSonsByDictId(_objDictType
								.getDictId());
						boolean hasSon = false;
						for (int i = 0; i < sonList.size(); i++) {
							TawSupplierkpiDict dictType = (TawSupplierkpiDict) sonList
									.get(i);
							String id = templateMgr
									.getTemplateIdBySpecialType(dictType
											.getDictId());
							if ((id != null) && (!"".equals(id))) {
								TawSupplierkpiTemplateAssess templateAssess = templateAssessMgr
										.getTawSupplierkpiTemplateAssessByTemplateId(id);
								if ((templateAssess.getId() != null)
										&& (!"".equals(templateAssess.getId())))
									hasSon = true;
							}
						}
						if (hasSon == true) {
							jitem.put("leaf", false);
							jitem.put("allowEdit", false);
							jitem.put("allowView", false);
						} else {
							jitem.put("leaf", true);
							jitem.put("allowEdit", true);
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

	public ActionForward unCheckedList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("unCheckedList");
	}

	public ActionForward checkedList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("checkedList");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSupplierkpiTemplateAssessForm tawSupplierkpiTemplateAssessForm = (TawSupplierkpiTemplateAssessForm) form;
		String specialType = StaticMethod.null2String(request
				.getParameter("specialType"));

		ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
		ITawSupplierkpiTemplateAssessManager templateAssessMgr = (ITawSupplierkpiTemplateAssessManager) getBean("ItawSupplierkpiTemplateAssessManager");
		String templateId = StaticMethod.null2String(templateMgr
				.getTemplateIdBySpecialType(specialType));
		TawSupplierkpiTemplate tawSupplierkpiTemplate = templateMgr
				.getTawSupplierkpiTemplate(templateId);
		String serviceType = StaticMethod.null2String(tawSupplierkpiTemplate
				.getServiceType());
		TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess = templateAssessMgr
				.getTawSupplierkpiTemplateAssessByTemplateId(templateId);
		if ((tawSupplierkpiTemplateAssess.getId() == null)
				|| ("".equals(tawSupplierkpiTemplateAssess.getId()))) {
			return null;
		}
		tawSupplierkpiTemplateAssessForm = (TawSupplierkpiTemplateAssessForm) convert(tawSupplierkpiTemplateAssess);
		request.setAttribute("specialType", specialType);
		request.setAttribute("serviceType", serviceType);
		updateFormBean(mapping, request, tawSupplierkpiTemplateAssessForm);
		// 获取专业KPI列表
		ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
		List list = itemMgr.getSpecialkpi(specialType);
		ListIterator it = list.listIterator();
		request.setAttribute("it", it);

		return mapping.findForward("view");
	}

	public void isInquireForUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String isInquire = "0";
		String specialType = StaticMethod.null2String(request
				.getParameter("specialType"));
		ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
		String templateId = StaticMethod.null2String(templateMgr
				.getTemplateIdBySpecialType(specialType));

		ITawSupplierkpiTemplateAssessManager templateAssessMgr = (ITawSupplierkpiTemplateAssessManager) getBean("ItawSupplierkpiTemplateAssessManager");
		TawSupplierkpiTemplateAssess tawSupplierkpiTemplateAssess = templateAssessMgr
				.getTawSupplierkpiTemplateAssessByTemplateId(templateId);

		if (1 == tawSupplierkpiTemplateAssess.getAssessStatus()) {
			ITawSupplierkpiAssessInstanceManager assessInstanceMgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
			List list = assessInstanceMgr.getCustomSuppliers(specialType);
			if (list.size() > 0) {
				isInquire = "1";
			}
		}

		response.setContentType("text/xml; charset=UTF-8");
		response.getWriter().print(isInquire);
	}
}
