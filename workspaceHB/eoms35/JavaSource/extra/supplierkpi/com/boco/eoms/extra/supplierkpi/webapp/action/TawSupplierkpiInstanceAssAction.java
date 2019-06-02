package com.boco.eoms.extra.supplierkpi.webapp.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.dao.hibernate.TawSystemDictTypeDaoHibernate;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleManage;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiDict;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInfo;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstanceAss;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiLog;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;
import com.boco.eoms.extra.supplierkpi.report.statistic.util.CreateXml;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiAssessInstanceManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiDictManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInstanceAssManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiInstanceManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiItemManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiLogManager;
import com.boco.eoms.extra.supplierkpi.service.ITawSupplierkpiTemplateManager;
import com.boco.eoms.extra.supplierkpi.util.SuppStaticVariable;
import com.boco.eoms.extra.supplierkpi.util.SupplierkpiConstants;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSupplierkpiInstanceAssForm;
import com.boco.eoms.extra.supplierkpi.webapp.form.TawSupplierkpiInstanceForm;
import com.boco.eoms.extra.supplierkpi.webapp.util.SupplierkpiAttributes;

public final class TawSupplierkpiInstanceAssAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		TawSupplierkpiInstanceAssForm tawSupplierkpiInstanceAssForm = (TawSupplierkpiInstanceAssForm) form;

		// Exceptions are caught by ActionExceptionHandler
		ITawSupplierkpiInstanceAssManager mgr = (ITawSupplierkpiInstanceAssManager) getBean("ItawSupplierkpiInstanceAssManager");
		mgr.removeTawSupplierkpiInstanceAss(tawSupplierkpiInstanceAssForm
				.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawSupplierkpiInstanceAss.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("search");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String specialType = request.getParameter("specialType");

		ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
		ITawSupplierkpiAssessInstanceManager assessInstanceMgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
		ITawSystemSubRoleManager subRoleMgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
		ITawSupplierkpiInstanceManager instanceMgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
		ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
		String templateId = StaticMethod.null2String(templateMgr
				.getTemplateIdBySpecialType(specialType));
		TawSupplierkpiTemplate tawSupplierkpiTemplate = templateMgr
				.getTawSupplierkpiTemplate(templateId);
		String serviceType = StaticMethod.null2String(tawSupplierkpiTemplate
				.getServiceType());

		// ---------组织表数据 Start
		List rowList = new ArrayList();
		List suppliers = new ArrayList();
		List kpis = new ArrayList();

		suppliers = assessInstanceMgr.getCustomSuppliers(specialType);
		ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
		kpis = itemMgr.getSpecialkpi(specialType);

		// 获取月份和年份
		String currentTime = StaticMethod.getCurrentDateTime();
		int date = Integer.parseInt(currentTime.substring(8, 10));
		String py = SuppStaticVariable.getLocalString(date, 0);
		String year = py.substring(0, 4);
		String month = py.substring(5, 7);
		String currentMonth = currentTime.substring(5, 7);
		int y = Integer.parseInt(month);
		String timeLatitude = "";
		if (y >= 1 && y < 4) {
			timeLatitude = "one";
		} else if (y >= 4 && y < 7) {
			timeLatitude = "two";
		} else if (y >= 7 && y < 10) {
			timeLatitude = "three";
		} else {
			timeLatitude = "four";
		}

		// 供应商对应的第一行title，对于代维是地市角色
		List supplierTitle = new ArrayList();
		supplierTitle.add("   ");

		// 如果是代维
		if ("10402".equals(serviceType)) {
			String roleQuery = "select distinct(fillRole) from TawSupplierkpiInstance where specialType='"
					+ specialType
					+ "' and fillRole in(select id from TawSystemSubRole where type2='"
					+ specialType + "')";
			List fillRoles = instanceMgr.getTawSupplierkpiInstances(roleQuery);
			for (int k = 0; k < fillRoles.size(); k++) {
				TawSystemSubRole tawSystemSubRole = subRoleMgr
						.getTawSystemSubRole(fillRoles.get(k).toString());
				String deptId = tawSystemSubRole.getDeptId();
				TawSystemDept tawSystemDept = deptMgr.getDeptinfobydeptid(
						deptId, "0");
				supplierTitle.add(tawSystemDept.getDeptName());
			}
			suppliers = fillRoles;
		} else {
			suppliers = assessInstanceMgr.getCustomSuppliers(specialType);
			for (int k = 0; k < suppliers.size(); k++) {
				TawSupplierkpiInfo sup = (TawSupplierkpiInfo) suppliers.get(k);
				supplierTitle.add(sup.getSupplierName());
			}
		}

		// 已经获得所有查询条件，接下来两层循环，查询每个供应商和kpi对应的填写值
		for (int i = 0; i < kpis.size(); i++) {
			List contentList = new ArrayList();
			TawSupplierkpiItem kpi = (TawSupplierkpiItem) kpis.get(i);
			TawSupplierkpiInstance instance = new TawSupplierkpiInstance();
			instance.setItemType(kpi.getItemType());
			instance.setKpiName(kpi.getKpiName());
			contentList.add(instance);
			for (int j = 0; j < suppliers.size(); j++) {
				String whereStr = "from TawSupplierkpiInstance where specialType='"
						+ specialType + "'";
				whereStr = whereStr + " and year='" + year + "'";
				whereStr = whereStr + " and kpiItemId='" + kpi.getId() + "'";
				if ("10402".equals(serviceType)) {
					whereStr = whereStr + " and fillRole='"
							+ suppliers.get(j).toString() + "'";
				} else {

					TawSupplierkpiInfo supplier = (TawSupplierkpiInfo) suppliers
							.get(j);
					whereStr = whereStr + " and manufacturerId='"
							+ supplier.getId() + "'";
				}

				if (SuppStaticVariable.INSTANCE_JAN.equals(currentMonth)) {
					whereStr = whereStr + " and (timeLatitude='" + month + "'";
					whereStr = whereStr + " or timeLatitude='" + timeLatitude
							+ "'";
					whereStr = whereStr + " or timeLatitude='year')";
				} else if (SuppStaticVariable.INSTANCE_APR.equals(currentMonth)
						|| SuppStaticVariable.INSTANCE_JUL.equals(currentMonth)
						|| SuppStaticVariable.INSTANCE_OCT.equals(currentMonth)) {
					whereStr = whereStr + " and (timeLatitude='" + month + "'";
					whereStr = whereStr + " or timeLatitude='" + timeLatitude
							+ "')";
				} else {
					whereStr = whereStr + " and timeLatitude='" + month + "'";
				}

				List instances = instanceMgr
						.getTawSupplierkpiInstances(whereStr);
				TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();
				if (instances.size() > 0) {
					tawSupplierkpiInstance = (TawSupplierkpiInstance) instances
							.get(0);
					if ((tawSupplierkpiInstance.getExamineContent() == null)
							|| ("".equals(tawSupplierkpiInstance
									.getExamineContent()))) {
						tawSupplierkpiInstance.setExamineContent("---");
					}
				} else {
					tawSupplierkpiInstance.setExamineContent("---");
				}
				contentList.add(tawSupplierkpiInstance);
			}
			rowList.add(contentList);
		}
		// --------组织表数据 End

		request.setAttribute("specialType", specialType);
		request.setAttribute("serviceType", serviceType);
		request.setAttribute("month", month);
		request.setAttribute("supplierTitle", supplierTitle);
		request.setAttribute("rowList", rowList);

		return mapping.findForward("edit");
	}

	/**
	 * 保存或更新实例填写审核信息,具体操作据当前审核状态而定
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
		String currentTime = StaticMethod.getCurrentDateTime();
		int date = Integer.parseInt(currentTime.substring(8, 10));
		String py = SuppStaticVariable.getLocalString(date, 0);
		String year = py.substring(0, 4);
		String month = py.substring(5, 7);

		ITawSupplierkpiInstanceAssManager instanceAssMgr = (ITawSupplierkpiInstanceAssManager) getBean("ItawSupplierkpiInstanceAssManager");
		TawSupplierkpiInstanceAss tawSupplierkpiInstanceAss = instanceAssMgr
				.getTawSupplierkpiInstanceAssBySpecialType(specialType);
		int assessStatus = tawSupplierkpiInstanceAss.getAssessStatus();
		if ((tawSupplierkpiInstanceAss.getId() == null)
				|| ("".equals(tawSupplierkpiInstanceAss.getId()))) {
			assessStatus = 1;
		}
		// 新增-状态设置为待审核
		if (1 == assessStatus) {
			String serviceType = request.getParameter("serviceType");
			// String timeLatitude = request.getParameter("timeLatitude");
			// 获取部门Id
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String deptId = sessionform.getDeptid();
			String roleId = ((SupplierkpiAttributes) getBean("supplierkpiAttributes"))
					.getPgAdminRole();
			String subRoleId = "";
			Hashtable filterHash = new Hashtable();
			filterHash.put("deptId", deptId);
			// edit by liqiuye 2008-1-15
			ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
			String parentSpecial = specialType.substring(0, specialType
					.length() - 2);
			if (templateMgr.getTemplateIdBySpecialType(parentSpecial) == null
					|| "".equals(templateMgr
							.getTemplateIdBySpecialType(parentSpecial))) {
				filterHash.put("class2", specialType);
			} else {
				filterHash.put("class3", specialType);
			}
			List list = RoleManage.getInstance()
					.getSubRoles(roleId, filterHash);
			if (list.size() > 0) {
				TawSystemSubRole tawSystemSubRole = (TawSystemSubRole) list
						.get(0);
				subRoleId = tawSystemSubRole.getId();
			} else {
				String deptName = sessionform.getDeptname();
				ITawSystemRoleManager roleMgr = (ITawSystemRoleManager) getBean("ItawSystemRoleManager");
				String roleName = roleMgr.getRoleNameById(Long
						.parseLong(roleId));
				TawSystemDictTypeDaoHibernate dao = (TawSystemDictTypeDaoHibernate) ApplicationContextHolder
						.getInstance().getBean("ItawSystemDictTypeDao");
				String specialName = dao.id2Name(specialType);
				String failInfo = "未找到 角色: " + roleName + " 部门: " + deptName
						+ " 专业: " + specialName + " 的子角色!";
				request.setAttribute("failInfo", failInfo);
				return mapping.findForward("fail");
			}

			Timestamp createTime = new Timestamp(System.currentTimeMillis());

			tawSupplierkpiInstanceAss.setServiceType(serviceType);
			tawSupplierkpiInstanceAss.setSpecialType(specialType);
			tawSupplierkpiInstanceAss.setTimeLatitude(month);
			tawSupplierkpiInstanceAss.setYear(year);
			tawSupplierkpiInstanceAss.setAssessTime(createTime);
			tawSupplierkpiInstanceAss.setSubRole(subRoleId);

			String opertype = request.getParameter("opertype");
			// 用户选择送审
			if ("1".equals(opertype)) {
				tawSupplierkpiInstanceAss.setAssessStatus(2);
			}
			// 用户选择归档
			else if ("2".equals(opertype)) {
				tawSupplierkpiInstanceAss.setAssessAttitude("");
				tawSupplierkpiInstanceAss.setAssessStatus(3);
			} else {
				return mapping.findForward("fail");
			}

			instanceAssMgr
					.saveTawSupplierkpiInstanceAss(tawSupplierkpiInstanceAss);
		}
		// 状态为"待审核"
		else if (2 == assessStatus) {
			// 获取相关信息
			String assessAttitude = request.getParameter("assessAttitude");
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String userName = sessionform.getUsername();
			tawSupplierkpiInstanceAss.setAssessAttitude(assessAttitude);
			tawSupplierkpiInstanceAss.setRealAssessor(userName);
			String opertype = request.getParameter("opertype");
			// 用户选择通过审核
			if ("1".equals(opertype)) {
				String degree = StaticMethod.null2String(request
						.getParameter("degree"));
				tawSupplierkpiInstanceAss.setDegree(degree);
				tawSupplierkpiInstanceAss.setAssessStatus(3);
			}
			// 用户选择驳回
			else if ("2".equals(opertype)) {
				// 目前暂时将驳回的实例设置为"新增"状态
				tawSupplierkpiInstanceAss.setAssessStatus(1);
			} else {
				return mapping.findForward("fail");
			}
			instanceAssMgr
					.saveTawSupplierkpiInstanceAss(tawSupplierkpiInstanceAss);
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

		ITawSupplierkpiInstanceAssManager mgr = (ITawSupplierkpiInstanceAssManager) getBean("ItawSupplierkpiInstanceAssManager");
		Map map = (Map) mgr.getTawSupplierkpiInstanceAsss(pageIndex, pageSize); // map����}��keyֵ��һ����"total",�����ܼ�¼������һ����"result"������Ҫ��ʾҳ���list
		List list = (List) map.get("result");
		request.setAttribute(Constants.TAWSUPPLIERKPIINSTANCEASS_LIST, list);
		request.setAttribute("resultSize", map.get("total"));

		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
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

	/**
	 * 根据父节点的id得到未审核实例，只显示两层 liqiuye 071126
	 */
	public String getNodesUnchecked(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String currentTime = StaticMethod.getCurrentDateTime();
		int date = Integer.parseInt(currentTime.substring(8, 10));
		String py = SuppStaticVariable.getLocalString(date, 0);
		String year = py.substring(0, 4);
		String month = py.substring(5, 7);

		String treeRootId = ((SupplierkpiAttributes) getBean("supplierkpiAttributes"))
				.getTreeRootId();
		ITawSupplierkpiDictManager dictMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
		ITawSupplierkpiInstanceAssManager instanceAssMgr = (ITawSupplierkpiInstanceAssManager) getBean("ItawSupplierkpiInstanceAssManager");

		ArrayList list = new ArrayList();
		ArrayList dictList = new ArrayList();
		JSONArray jsonRoot = new JSONArray();
		String nodeId = request.getParameter("node");

		if (nodeId.equals(treeRootId)) {
			String hql = "select distinct(serviceType) from TawSupplierkpiInstanceAss where assessStatus='2' and year='"
					+ year + "' and timeLatitude='" + month + "'";
			dictList = (ArrayList) instanceAssMgr.getNodesFromInstanceAss(hql);

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
			String hql = "select distinct(specialType) from TawSupplierkpiInstanceAss where assessStatus='2' and year='"
					+ year + "' and timeLatitude='" + month + "'";
			dictList = (ArrayList) instanceAssMgr.getNodesFromInstanceAss(hql);
			// edit by liqiuye 2008-1-15 存在三级专业的情况下,丢失二级专业
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
		String currentTime = StaticMethod.getCurrentDateTime();
		int date = Integer.parseInt(currentTime.substring(8, 10));
		String py = SuppStaticVariable.getLocalString(date, 0);
		String year = py.substring(0, 4);
		String month = py.substring(5, 7);

		String treeRootId = ((SupplierkpiAttributes) getBean("supplierkpiAttributes"))
				.getTreeRootId();
		ITawSupplierkpiDictManager dictMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
		ITawSupplierkpiInstanceAssManager instanceAssMgr = (ITawSupplierkpiInstanceAssManager) getBean("ItawSupplierkpiInstanceAssManager");

		ArrayList list = new ArrayList();
		ArrayList dictList = new ArrayList();
		JSONArray jsonRoot = new JSONArray();
		String nodeId = request.getParameter("node");

		if (nodeId.equals(treeRootId)) {
			String hql = "select distinct(serviceType) from TawSupplierkpiInstanceAss where assessStatus='3' and year='"
					+ year + "' and timeLatitude='" + month + "'";
			dictList = (ArrayList) instanceAssMgr.getNodesFromInstanceAss(hql);

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
						jitem.put("allowView", false);
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
			String hql = "select distinct(specialType) from TawSupplierkpiInstanceAss where assessStatus='3' and year='"
					+ year + "' and timeLatitude='" + month + "'";
			dictList = (ArrayList) instanceAssMgr.getNodesFromInstanceAss(hql);
			// edit by liqiuye 2008-1-15 存在三级专业的情况下,丢失二级专业
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
								hasSon = true;
							}
						}
						if (hasSon == true) {
							jitem.put("leaf", false);
							jitem.put("allowView", false);
							jitem.put("allowEdit", false);
						} else {
							jitem.put("leaf", true);
							jitem.put("allowView", true);
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

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String specialType = request.getParameter("specialType");
		ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
		ITawSupplierkpiAssessInstanceManager assessInstanceMgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
		ITawSystemSubRoleManager subRoleMgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
		ITawSupplierkpiInstanceManager instanceMgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
		ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
		String templateId = StaticMethod.null2String(templateMgr
				.getTemplateIdBySpecialType(specialType));
		TawSupplierkpiTemplate tawSupplierkpiTemplate = templateMgr
				.getTawSupplierkpiTemplate(templateId);
		String serviceType = StaticMethod.null2String(tawSupplierkpiTemplate
				.getServiceType());

		ITawSupplierkpiInstanceAssManager instanceAssMgr = (ITawSupplierkpiInstanceAssManager) getBean("ItawSupplierkpiInstanceAssManager");
		TawSupplierkpiInstanceAss instanceAss = instanceAssMgr
				.getTawSupplierkpiInstanceAssBySpecialType(specialType);
		String assessAttitude = StaticMethod.null2String(instanceAss
				.getAssessAttitude());
		String degree = StaticMethod.null2String(instanceAss.getDegree());

		// ---------组织表数据 Start
		List rowList = new ArrayList();
		List suppliers = new ArrayList();
		List kpis = new ArrayList();

		suppliers = assessInstanceMgr.getCustomSuppliers(specialType);
		ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
		kpis = itemMgr.getSpecialkpi(specialType);

		// 获取月份和年份
		String currentTime = StaticMethod.getCurrentDateTime();
		int date = Integer.parseInt(currentTime.substring(8, 10));
		String py = SuppStaticVariable.getLocalString(date, 0);
		String year = py.substring(0, 4);
		String month = py.substring(5, 7);
		String currentMonth = currentTime.substring(5, 7);
		int y = Integer.parseInt(month);
		String timeLatitude = "";
		if (y >= 1 && y < 4) {
			timeLatitude = "one";
		} else if (y >= 4 && y < 7) {
			timeLatitude = "two";
		} else if (y >= 7 && y < 10) {
			timeLatitude = "three";
		} else {
			timeLatitude = "four";
		}

		// 供应商对应的第一行title，对于代维是地市角色
		List supplierTitle = new ArrayList();
		supplierTitle.add("   ");

		// 如果是代维
		if ("10402".equals(serviceType)) {
			String roleQuery = "select distinct(fillRole) from TawSupplierkpiInstance where specialType='"
					+ specialType
					+ "' and fillRole in(select id from TawSystemSubRole where type2='"
					+ specialType + "')";
			List fillRoles = instanceMgr.getTawSupplierkpiInstances(roleQuery);
			for (int k = 0; k < fillRoles.size(); k++) {
				TawSystemSubRole tawSystemSubRole = subRoleMgr
						.getTawSystemSubRole(fillRoles.get(k).toString());
				String deptId = tawSystemSubRole.getDeptId();
				TawSystemDept tawSystemDept = deptMgr.getDeptinfobydeptid(
						deptId, "0");
				supplierTitle.add(tawSystemDept.getDeptName());
			}
			suppliers = fillRoles;
		} else {
			suppliers = assessInstanceMgr.getCustomSuppliers(specialType);
			for (int k = 0; k < suppliers.size(); k++) {
				TawSupplierkpiInfo sup = (TawSupplierkpiInfo) suppliers.get(k);
				supplierTitle.add(sup.getSupplierName());
			}
		}

		// 已经获得所有查询条件，接下来两层循环，查询每个供应商和kpi对应的填写值
		for (int i = 0; i < kpis.size(); i++) {
			List contentList = new ArrayList();
			TawSupplierkpiItem kpi = (TawSupplierkpiItem) kpis.get(i);
			TawSupplierkpiInstance instance = new TawSupplierkpiInstance();
			instance.setItemType(kpi.getItemType());
			instance.setKpiName(kpi.getKpiName());
			contentList.add(instance);
			for (int j = 0; j < suppliers.size(); j++) {
				String whereStr = "from TawSupplierkpiInstance where specialType='"
						+ specialType + "'";
				whereStr = whereStr + " and year='" + year + "'";
				whereStr = whereStr + " and kpiItemId='" + kpi.getId() + "'";
				if ("10402".equals(serviceType)) {
					whereStr = whereStr + " and fillRole='"
							+ suppliers.get(j).toString() + "'";
				} else {

					TawSupplierkpiInfo supplier = (TawSupplierkpiInfo) suppliers
							.get(j);
					whereStr = whereStr + " and manufacturerId='"
							+ supplier.getId() + "'";
				}

				if (SuppStaticVariable.INSTANCE_JAN.equals(currentMonth)) {
					whereStr = whereStr + " and (timeLatitude='" + month + "'";
					whereStr = whereStr + " or timeLatitude='" + timeLatitude
							+ "'";
					whereStr = whereStr + " or timeLatitude='year')";
				} else if (SuppStaticVariable.INSTANCE_APR.equals(currentMonth)
						|| SuppStaticVariable.INSTANCE_JUL.equals(currentMonth)
						|| SuppStaticVariable.INSTANCE_OCT.equals(currentMonth)) {
					whereStr = whereStr + " and (timeLatitude='" + month + "'";
					whereStr = whereStr + " or timeLatitude='" + timeLatitude
							+ "')";
				} else {
					whereStr = whereStr + " and timeLatitude='" + month + "'";
				}

				List instances = instanceMgr
						.getTawSupplierkpiInstances(whereStr);
				TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();
				if (instances.size() > 0) {
					tawSupplierkpiInstance = (TawSupplierkpiInstance) instances
							.get(0);
					if ((tawSupplierkpiInstance.getExamineContent() == null)
							|| ("".equals(tawSupplierkpiInstance
									.getExamineContent()))) {
						tawSupplierkpiInstance.setExamineContent("---");
					}
				} else {
					tawSupplierkpiInstance.setExamineContent("---");
				}
				contentList.add(tawSupplierkpiInstance);
			}
			rowList.add(contentList);
		}
		// --------组织表数据 End

		request.setAttribute("specialType", specialType);
		request.setAttribute("serviceType", serviceType);
		request.setAttribute("assessAttitude", assessAttitude);
		request.setAttribute("degree", degree);
		request.setAttribute("month", month);
		request.setAttribute("supplierTitle", supplierTitle);
		request.setAttribute("rowList", rowList);

		return mapping.findForward("view");
	}

	public ActionForward monitorList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("monitorList");
	}

	public ActionForward monitorForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
		ITawSupplierkpiAssessInstanceManager assessInstanceMgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
		ITawSystemSubRoleManager subRoleMgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
		ITawSupplierkpiInstanceManager instanceMgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
		ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
		String specialType = StaticMethod.null2String(request
				.getParameter("specialType"));
		if (specialType == null || "".equals(specialType)) {
			specialType = request.getAttribute("specialType").toString();
		}
		String templateId = templateMgr.getTemplateIdBySpecialType(specialType);
		if ((templateId == null) || ("".equals(templateId))) {
			return null;
		}
		TawSupplierkpiTemplate tawSupplierkpiTemplate = templateMgr
				.getTawSupplierkpiTemplate(templateId);
		String serviceType = StaticMethod.null2String(tawSupplierkpiTemplate
				.getServiceType());

		// 由于添加了三级专业,如果用户点击的专业下面还有专业,则不显示任何内容
		ITawSupplierkpiDictManager dictTypeMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
		List sonList = dictTypeMgr.getDictSonsByDictId(specialType);
		for (int i = 0; i < sonList.size(); i++) {
			TawSupplierkpiDict tawSystemDictType = (TawSupplierkpiDict) sonList
					.get(i);
			String dictId = tawSystemDictType.getDictId();
			String tempId = templateMgr.getTemplateIdBySpecialType(dictId);
			if ((tempId != null) && (!"".equals(tempId))) {
				return null;
			}
		}

		// ---------组织表数据 Start
		List rowList = new ArrayList();
		List suppliers = new ArrayList();
		List kpis = new ArrayList();

		ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
		kpis = itemMgr.getSpecialkpi(specialType);

		// 获取月份和年份
		String currentTime = StaticMethod.getCurrentDateTime();
		int date = Integer.parseInt(currentTime.substring(8, 10));
		String py = SuppStaticVariable.getLocalString(date, 0);
		String year = py.substring(0, 4);
		String month = py.substring(5, 7);
		String currentMonth = currentTime.substring(5, 7);
		int y = Integer.parseInt(month);
		String timeLatitude = "";
		if (y >= 1 && y < 4) {
			timeLatitude = "one";
		} else if (y >= 4 && y < 7) {
			timeLatitude = "two";
		} else if (y >= 7 && y < 10) {
			timeLatitude = "three";
		} else {
			timeLatitude = "four";
		}

		// 供应商对应的第一行title，对于代维是地市角色
		List supplierTitle = new ArrayList();
		supplierTitle.add("   ");

		// 如果是代维
		if ("10402".equals(serviceType)) {
			String roleQuery = "select distinct(fillRole) from TawSupplierkpiInstance where specialType='"
					+ specialType
					+ "' and fillRole in(select id from TawSystemSubRole where type2='"
					+ specialType + "')";
			List fillRoles = instanceMgr.getTawSupplierkpiInstances(roleQuery);
			for (int k = 0; k < fillRoles.size(); k++) {
				TawSystemSubRole tawSystemSubRole = subRoleMgr
						.getTawSystemSubRole(fillRoles.get(k).toString());
				String deptId = tawSystemSubRole.getDeptId();
				TawSystemDept tawSystemDept = deptMgr.getDeptinfobydeptid(
						deptId, "0");
				supplierTitle.add(tawSystemDept.getDeptName());
			}
			suppliers = fillRoles;
		} else {
			suppliers = assessInstanceMgr.getCustomSuppliers(specialType);
			for (int k = 0; k < suppliers.size(); k++) {
				TawSupplierkpiInfo sup = (TawSupplierkpiInfo) suppliers.get(k);
				supplierTitle.add(sup.getSupplierName());
			}
		}

		// 已经获得所有查询条件，接下来两层循环，查询每个供应商和kpi对应的填写值
		for (int i = 0; i < kpis.size(); i++) {
			List contentList = new ArrayList();
			TawSupplierkpiItem kpi = (TawSupplierkpiItem) kpis.get(i);
			TawSupplierkpiInstance instance = new TawSupplierkpiInstance();
			instance.setItemType(kpi.getItemType());
			instance.setKpiName(kpi.getKpiName());
			contentList.add(instance);
			for (int j = 0; j < suppliers.size(); j++) {
				String whereStr = "from TawSupplierkpiInstance where specialType='"
						+ specialType + "'";
				whereStr = whereStr + " and year='" + year + "'";
				whereStr = whereStr + " and kpiItemId='" + kpi.getId() + "'";
				if ("10402".equals(serviceType)) {
					whereStr = whereStr + " and fillRole='"
							+ suppliers.get(j).toString() + "'";
				} else {

					TawSupplierkpiInfo supplier = (TawSupplierkpiInfo) suppliers
							.get(j);
					whereStr = whereStr + " and manufacturerId='"
							+ supplier.getId() + "'";
				}

				if (SuppStaticVariable.INSTANCE_JAN.equals(currentMonth)) {
					whereStr = whereStr + " and (timeLatitude='" + month + "'";
					whereStr = whereStr + " or timeLatitude='" + timeLatitude
							+ "'";
					whereStr = whereStr + " or timeLatitude='year')";
				} else if (SuppStaticVariable.INSTANCE_APR.equals(currentMonth)
						|| SuppStaticVariable.INSTANCE_JUL.equals(currentMonth)
						|| SuppStaticVariable.INSTANCE_OCT.equals(currentMonth)) {
					whereStr = whereStr + " and (timeLatitude='" + month + "'";
					whereStr = whereStr + " or timeLatitude='" + timeLatitude
							+ "')";
				} else {
					whereStr = whereStr + " and timeLatitude='" + month + "'";
				}

				List instances = instanceMgr
						.getTawSupplierkpiInstances(whereStr);
				TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();
				if (instances.size() > 0) {
					tawSupplierkpiInstance = (TawSupplierkpiInstance) instances
							.get(0);
					if ((tawSupplierkpiInstance.getExamineContent() == null)
							|| ("".equals(tawSupplierkpiInstance
									.getExamineContent()))) {
						tawSupplierkpiInstance.setExamineContent("---");
					}
				} else {
					tawSupplierkpiInstance.setExamineContent("---");
				}
				contentList.add(tawSupplierkpiInstance);
			}
			rowList.add(contentList);
		}
		// --------组织表数据 End

		// 获取审核状态和审核意见
		ITawSupplierkpiInstanceAssManager instanceAssMgr = (ITawSupplierkpiInstanceAssManager) getBean("ItawSupplierkpiInstanceAssManager");
		TawSupplierkpiInstanceAss tawSupplierkpiInstanceAss = instanceAssMgr
				.getTawSupplierkpiInstanceAssBySpecialType(specialType);

		String assessStatus = String.valueOf(tawSupplierkpiInstanceAss
				.getAssessStatus());
		String assessAttitude = StaticMethod
				.null2String(tawSupplierkpiInstanceAss.getAssessAttitude());
		if ((assessStatus == null) || ("".equals(assessStatus))
				|| ("0".equals(assessStatus))) {
			assessStatus = "1";
		}
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

		request.setAttribute("specialType", specialType);
		request.setAttribute("serviceType", serviceType);
		request.setAttribute("month", month);
		request.setAttribute("supplierTitle", supplierTitle);
		request.setAttribute("rowList", rowList);
		request.setAttribute("assessStatus", assessStatus);
		request.setAttribute("statusName", statusName);
		request.setAttribute("assessAttitude", assessAttitude);

		return mapping.findForward("monitorForm");
	}

	public ActionForward monitorHistory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
		ITawSupplierkpiAssessInstanceManager assessInstanceMgr = (ITawSupplierkpiAssessInstanceManager) getBean("ItawSupplierkpiAssessInstanceManager");
		ITawSystemSubRoleManager subRoleMgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
		ITawSupplierkpiInstanceManager instanceMgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
		ITawSupplierkpiTemplateManager templateMgr = (ITawSupplierkpiTemplateManager) getBean("ItawSupplierkpiTemplateManager");
		String specialType = StaticMethod.null2String(request
				.getParameter("specialType"));
		String year = StaticMethod.null2String(request.getParameter("year"));
		String month = StaticMethod.null2String(request.getParameter("month"));
		if (specialType == null || "".equals(specialType)) {
			specialType = request.getAttribute("specialType").toString();
		}
		String templateId = templateMgr.getTemplateIdBySpecialType(specialType);
		if ((templateId == null) || ("".equals(templateId))) {
			return null;
		}
		TawSupplierkpiTemplate tawSupplierkpiTemplate = templateMgr
				.getTawSupplierkpiTemplate(templateId);
		String serviceType = StaticMethod.null2String(tawSupplierkpiTemplate
				.getServiceType());

		// 由于添加了三级专业,如果用户点击的专业下面还有专业,则不显示任何内容
		ITawSupplierkpiDictManager dictTypeMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
		List sonList = dictTypeMgr.getDictSonsByDictId(specialType);
		for (int i = 0; i < sonList.size(); i++) {
			TawSupplierkpiDict tawSystemDictType = (TawSupplierkpiDict) sonList
					.get(i);
			String dictId = tawSystemDictType.getDictId();
			String tempId = templateMgr.getTemplateIdBySpecialType(dictId);
			if ((tempId != null) && (!"".equals(tempId))) {
				return null;
			}
		}

		// ---------组织表数据 Start
		List rowList = new ArrayList();
		List suppliers = new ArrayList();
		List kpis = new ArrayList();

		suppliers = assessInstanceMgr.getCustomSuppliers(specialType);
		ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
		kpis = itemMgr.getSpecialkpi(specialType);

		if (year == null || "".equals(year) || month == null
				|| "".equals(month)) {
			String currentTime = StaticMethod.getCurrentDateTime();
			int date = Integer.parseInt(currentTime.substring(8, 10));
			String py = SuppStaticVariable.getLocalString(date, 0);
			year = py.substring(0, 4);
			month = py.substring(5, 7);
		}
		int y = Integer.parseInt(month);
		String timeLatitude = "";
		if (y >= 1 && y < 4) {
			timeLatitude = "one";
		} else if (y >= 4 && y < 7) {
			timeLatitude = "two";
		} else if (y >= 7 && y < 10) {
			timeLatitude = "three";
		} else {
			timeLatitude = "four";
		}
		String currentMonth = "";
		if ((y >= 1) && (y < 9)) {
			currentMonth = "0" + String.valueOf(y + 1);
		} else if ((y >= 9) && (y < 12)) {
			currentMonth = String.valueOf(y + 1);
		} else if (y == 12) {
			currentMonth = "01";
			year = String.valueOf(Integer.parseInt(year) - 1);
		}

		// 供应商对应的第一行title，对于代维是地市角色
		List supplierTitle = new ArrayList();
		supplierTitle.add("   ");

		// 如果是代维
		if ("10402".equals(serviceType)) {
			String roleQuery = "select distinct(fillRole) from TawSupplierkpiInstance where specialType='"
					+ specialType
					+ "' and fillRole in(select id from TawSystemSubRole where type2='"
					+ specialType + "')";
			List fillRoles = instanceMgr.getTawSupplierkpiInstances(roleQuery);
			for (int k = 0; k < fillRoles.size(); k++) {
				TawSystemSubRole tawSystemSubRole = subRoleMgr
						.getTawSystemSubRole(fillRoles.get(k).toString());
				String deptId = tawSystemSubRole.getDeptId();
				TawSystemDept tawSystemDept = deptMgr.getDeptinfobydeptid(
						deptId, "0");
				supplierTitle.add(tawSystemDept.getDeptName());
			}
			suppliers = fillRoles;
		} else {
			suppliers = assessInstanceMgr.getCustomSuppliers(specialType);
			for (int k = 0; k < suppliers.size(); k++) {
				TawSupplierkpiInfo sup = (TawSupplierkpiInfo) suppliers.get(k);
				supplierTitle.add(sup.getSupplierName());
			}
		}

		// 已经获得所有查询条件，接下来两层循环，查询每个供应商和kpi对应的填写值
		for (int i = 0; i < kpis.size(); i++) {
			List contentList = new ArrayList();
			TawSupplierkpiItem kpi = (TawSupplierkpiItem) kpis.get(i);
			TawSupplierkpiInstance instance = new TawSupplierkpiInstance();
			instance.setExamineContent(kpi.getKpiName());
			contentList.add(instance);
			for (int j = 0; j < suppliers.size(); j++) {
				String whereStr = "from TawSupplierkpiInstance where specialType='"
						+ specialType + "'";
				whereStr = whereStr + " and year='" + year + "'";
				whereStr = whereStr + " and kpiItemId='" + kpi.getId() + "'";
				if ("10402".equals(serviceType)) {
					whereStr = whereStr + " and fillRole='"
							+ suppliers.get(j).toString() + "'";
				} else {

					TawSupplierkpiInfo supplier = (TawSupplierkpiInfo) suppliers
							.get(j);
					whereStr = whereStr + " and manufacturerId='"
							+ supplier.getId() + "'";
				}

				if (SuppStaticVariable.INSTANCE_JAN.equals(currentMonth)) {
					whereStr = whereStr + " and (timeLatitude='" + month + "'";
					whereStr = whereStr + " or timeLatitude='" + timeLatitude
							+ "'";
					whereStr = whereStr + " or timeLatitude='year')";
				} else if (SuppStaticVariable.INSTANCE_APR.equals(currentMonth)
						|| SuppStaticVariable.INSTANCE_JUL.equals(currentMonth)
						|| SuppStaticVariable.INSTANCE_OCT.equals(currentMonth)) {
					whereStr = whereStr + " and (timeLatitude='" + month + "'";
					whereStr = whereStr + " or timeLatitude='" + timeLatitude
							+ "')";
				} else {
					whereStr = whereStr + " and timeLatitude='" + month + "'";
				}

				List instances = instanceMgr
						.getTawSupplierkpiInstances(whereStr);
				TawSupplierkpiInstance tawSupplierkpiInstance = new TawSupplierkpiInstance();
				if (instances.size() > 0) {
					tawSupplierkpiInstance = (TawSupplierkpiInstance) instances
							.get(0);
					if ((tawSupplierkpiInstance.getExamineContent() == null)
							|| ("".equals(tawSupplierkpiInstance
									.getExamineContent()))) {
						tawSupplierkpiInstance.setExamineContent("---");
					}
				} else {
					tawSupplierkpiInstance.setExamineContent("---");
				}
				contentList.add(tawSupplierkpiInstance);
			}
			rowList.add(contentList);
		}
		// --------组织表数据 End

		request.setAttribute("specialType", specialType);
		request.setAttribute("serviceType", serviceType);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("supplierTitle", supplierTitle);
		request.setAttribute("rowList", rowList);

		return mapping.findForward("monitorHistory");
	}

	public ActionForward editInstance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSupplierkpiInstanceForm tawSupplierkpiInstanceForm = (TawSupplierkpiInstanceForm) form;

		if (tawSupplierkpiInstanceForm.getId() != null) {
			ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
			TawSupplierkpiInstance tawSupplierkpiInstance = mgr
					.getTawSupplierkpiInstance(tawSupplierkpiInstanceForm
							.getId());
//			ITawSupplierkpiDictManager dictTypeMgr = (ITawSupplierkpiDictManager) getBean("ItawSupplierkpiDictManager");
//			String unit = dictTypeMgr.getDictByDictId(
//					tawSupplierkpiInstance.getUnit()).getDictName();
//			tawSupplierkpiInstance.setUnit(unit);
			tawSupplierkpiInstanceForm = (TawSupplierkpiInstanceForm) convert(tawSupplierkpiInstance);
			updateFormBean(mapping, request, tawSupplierkpiInstanceForm);
		}

		return mapping.findForward("editInstance");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionMessages messages = new ActionMessages();
		TawSupplierkpiInstanceForm tawSupplierkpiInstanceForm = (TawSupplierkpiInstanceForm) form;
		ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");

		String specialType = "";
		String id = tawSupplierkpiInstanceForm.getId();
		TawSupplierkpiInstance tawSupplierkpiInstance = mgr
				.getTawSupplierkpiInstance(id);

		String oldExamineContent = tawSupplierkpiInstance.getExamineContent();
		String oldMemo = tawSupplierkpiInstance.getMemo();

		specialType = tawSupplierkpiInstance.getSpecialType();
		tawSupplierkpiInstance.setExamineContent(tawSupplierkpiInstanceForm
				.getExamineContent());
		tawSupplierkpiInstance.setMemo(tawSupplierkpiInstanceForm.getMemo());
		tawSupplierkpiInstance.setFillFlag(1);
		mgr.saveTawSupplierkpiInstance(tawSupplierkpiInstance);
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"tawSupplierkpiInstance.updated"));
		saveMessages(request, messages);

		// 修改记录
		if (!oldExamineContent.equals(tawSupplierkpiInstance
				.getExamineContent())
				|| !oldMemo.equals(tawSupplierkpiInstance.getMemo())) {
			StringBuffer content = new StringBuffer("修改厂商<B>");
			content.append(tawSupplierkpiInstance.getManufacturerName().trim());
			content.append("</B>的指标<B>");
			content.append(tawSupplierkpiInstance.getKpiName().trim());
			content.append("</B>对应的填写数据；");

			if (!oldExamineContent.equals(tawSupplierkpiInstance
					.getExamineContent())) {
				content.append("填写数据由<B><font color=\"red\">");
				content.append(oldExamineContent.trim());
				content.append("</font></B>修改为<B><font color=\"red\">");
				content.append(tawSupplierkpiInstance.getExamineContent()
						.trim());
				content.append("</font></B>；");
			}
			if (!oldMemo.equals(tawSupplierkpiInstance.getMemo())) {
				content.append("备注由<B><font color=\"red\">");
				content.append(oldMemo.trim());
				content.append("</font></B>修改为<B><font color=\"red\">");
				content.append(tawSupplierkpiInstance.getMemo().trim());
				content.append("</font></B>；");
			}
			ITawSupplierkpiLogManager logMgr = (ITawSupplierkpiLogManager) getBean("ItawSupplierkpiLogManager");
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			String operator = sessionform.getUserid();
			String operatorName = sessionform.getUsername();
			String operTime = StaticMethod.getCurrentDateTime();
			String operType = "update";
			String operContent = content.toString();
			TawSupplierkpiLog tawSupplierkpiLog = new TawSupplierkpiLog();
			tawSupplierkpiLog.setDeleted(1);
			tawSupplierkpiLog.setOperator(operator);
			tawSupplierkpiLog.setOperatorName(operatorName);
			tawSupplierkpiLog.setOperContent(operContent);
			tawSupplierkpiLog.setOperTime(operTime);
			tawSupplierkpiLog.setOperType(operType);
			tawSupplierkpiLog.setSpecialType(specialType);
			logMgr.saveTawSupplierkpiLog(tawSupplierkpiLog);
		}

		request.setAttribute("specialType", specialType);
		return monitorForm(mapping, tawSupplierkpiInstanceForm, request,
				response);
	}

	public ActionForward rejectInstance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSupplierkpiInstanceForm tawSupplierkpiInstanceForm = (TawSupplierkpiInstanceForm) form;
		String id = StaticMethod.null2String(request.getParameter("id"));
		String specialType = StaticMethod.null2String(request
				.getParameter("specialType"));
		ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
		TawSupplierkpiInstance instance = mgr.getTawSupplierkpiInstance(id);
		instance.setFillFlag(2);
		mgr.saveTawSupplierkpiInstance(instance);
		request.setAttribute("specialType", specialType);
		return monitorForm(mapping, tawSupplierkpiInstanceForm, request,
				response);
	}

	public ActionForward viewInstance(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSupplierkpiInstanceForm tawSupplierkpiInstanceForm = (TawSupplierkpiInstanceForm) form;
		String specialType = request.getParameter("specialType"); 
		String autingState = request.getParameter("autingState");//此参数从已审核评估实例页面得来，已审核页面和未审核页面中的链接到同一页面。故返回按钮当前url要对应相关的页面
		request.setAttribute("specialType", specialType);
		request.setAttribute("autingState", autingState);

		if (tawSupplierkpiInstanceForm.getId() != null) {
			ITawSupplierkpiInstanceManager mgr = (ITawSupplierkpiInstanceManager) getBean("ItawSupplierkpiInstanceManager");
			TawSupplierkpiInstance tawSupplierkpiInstance = mgr
					.getTawSupplierkpiInstance(tawSupplierkpiInstanceForm
							.getId());
//			String unit = DictMgrLocator
//					.getDictService()
//					.itemId2name(
//							"dict-supplierkpi"
//									+ com.boco.eoms.commons.system.dict.util.Constants.DICT_ID_SPLIT_CHAR
//									+ "unit", tawSupplierkpiInstance.getUnit())
//					.toString();
//			tawSupplierkpiInstance.setUnit(unit);
			tawSupplierkpiInstanceForm = (TawSupplierkpiInstanceForm) convert(tawSupplierkpiInstance);
			updateFormBean(mapping, request, tawSupplierkpiInstanceForm);
		}

		return mapping.findForward("viewInstance");
	}

	// 横向报表图形报表
	public ActionForward openWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String modelId = StaticMethod.null2String(request
				.getParameter("modelId"));
		String reportTime = StaticMethod.null2String(request
				.getParameter("reportTime"));
		String specialType = StaticMethod.null2String(request
				.getParameter("specialType"));
		String kpiId = StaticMethod.null2String(request.getParameter("kpiId"));
		String kpiName = "";
		ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
		TawSupplierkpiItem item = itemMgr.getTawSupplierkpiItem(kpiId,
				SupplierkpiConstants.UNDELETED);

		if (item != null) {
			kpiName = item.getKpiName();
		}
		ITawSupplierkpiInstanceAssManager instanceAssMgr = (ITawSupplierkpiInstanceAssManager) getBean("ItawSupplierkpiInstanceAssManager");
		List list = instanceAssMgr.getStaticEntitis(modelId, reportTime,
				specialType, kpiId);
		String graphType = StaticMethod.null2String(request
				.getParameter("graphType"));
		String path = request.getSession().getServletContext().getRealPath("");
		if (path.endsWith("/")) {
			path += "reportSwfs";
		} else {
			path += "/reportSwfs";
		}
		String xmlPath = path + "/report/supplierkpi.xml";
		String templatePath = path + "/template/" + graphType;

		CreateXml.createXml(list, templatePath, xmlPath);
		String graphName = "统计图";
		if ("3D-Column.xml".equals(graphType)) {
			graphName = "柱状图";
		} else if ("3D-Pie.xml".equals(graphType)) {
			graphName = "饼状图";
		}
		CreateXml.setTitle(xmlPath, kpiName + "    " + graphName);

		request.setAttribute("graphType", graphType);
		return mapping.findForward("openWindow");
	}
	
	// 纵向报表图形报表
	public ActionForward openVerticalWindow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String modelId = StaticMethod.null2String(request
				.getParameter("modelId"));
		String reportTime = StaticMethod.null2String(request
				.getParameter("reportTime"));
		String specialType = StaticMethod.null2String(request
				.getParameter("specialType"));
		String manufacturerId = StaticMethod.null2String(request
				.getParameter("manufacturerId"));
		String kpiId = StaticMethod.null2String(request.getParameter("kpiId"));
		String kpiName = "";
		ITawSupplierkpiItemManager itemMgr = (ITawSupplierkpiItemManager) getBean("ItawSupplierkpiItemManager");
		TawSupplierkpiItem item = itemMgr.getTawSupplierkpiItem(kpiId,
				SupplierkpiConstants.UNDELETED);

		if (item != null) {
			kpiName = item.getKpiName();
		}
		ITawSupplierkpiInstanceAssManager instanceAssMgr = (ITawSupplierkpiInstanceAssManager) getBean("ItawSupplierkpiInstanceAssManager");
		List list = instanceAssMgr.getVerticalStaticEntitis(modelId, reportTime,
				specialType, kpiId, manufacturerId);
		String graphType = StaticMethod.null2String(request
				.getParameter("graphType"));
		String path = request.getSession().getServletContext().getRealPath("");
		if (path.endsWith("/")) {
			path += "reportSwfs";
		} else {
			path += "/reportSwfs";
		}
		String xmlPath = path + "/report/supplierkpi.xml";
		String templatePath = path + "/template/" + graphType;

		CreateXml.createXml(list, templatePath, xmlPath);
		String graphName = "统计图";
		if ("3D-Column.xml".equals(graphType)) {
			graphName = "柱状图";
		} else if ("3D-Pie.xml".equals(graphType)) {
			graphName = "饼状图";
		}
		CreateXml.setTitle(xmlPath, kpiName + "    " + graphName);

		request.setAttribute("graphType", graphType);
		return mapping.findForward("openWindow");
	}
}
