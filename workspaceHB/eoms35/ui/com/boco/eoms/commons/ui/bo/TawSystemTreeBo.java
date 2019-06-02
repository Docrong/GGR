package com.boco.eoms.commons.ui.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.sheet.special.model.TawSheetSpecial;
import com.boco.eoms.commons.sheet.special.service.ITawSheetSpecialManager;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo;
import com.boco.eoms.commons.system.priv.bo.TawSystemPrivAssignOut;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenuItem;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivMenuItemManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivOperationManager;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager;
import com.boco.eoms.commons.system.priv.util.PrivConstants;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.role.model.TawSystemPost;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemPostManager;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleRefPostManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.system.user.service.bo.ITawSystemUserBo;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.commons.ui.listitem.TawCommonsUIListItem;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.duty.bo.TawRmAssignworkBO;
import com.boco.eoms.message.mgr.ISmsServiceManager;
import com.boco.eoms.message.model.SmsService;
import com.boco.eoms.portal.webservices.bo.PortalService;
import com.boco.eoms.portal.webservices.bo.PortalServiceLocator;
import com.boco.eoms.portal.webservices.bo.PortalServicePortType;
import com.boco.eoms.portal.webservices.bo.PortalServicePortTypeProxy;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContact;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup;
import com.boco.eoms.workbench.contact.service.bo.TawWorkbenchContactBO;

/**
 * 
 * panlong 下午04:02:15
 */
public class TawSystemTreeBo {

	private TawSystemTreeBo() {

	}

	private static TawSystemTreeBo systreebo;

	TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();

	TawSystemUserRoleBo userrolebo = TawSystemUserRoleBo.getInstance();

	TawSystemCptroomBo cptroombo = TawSystemCptroomBo.getInstance();

	public static TawSystemTreeBo getInstance() {
		if (systreebo == null) {
			systreebo = init();
		}
		return systreebo;
	}

	private static TawSystemTreeBo init() {
		systreebo = new TawSystemTreeBo();
		return systreebo;
	}

	/**
	 * 部门树
	 * 
	 * @param deptid
	 * @return
	 */
	public JSONArray getDeptTree(String deptid, String chkType) {
		JSONArray json = new JSONArray();
		ArrayList list = new ArrayList();
		try {
			TawSystemDept sept=deptbo.getDeptinfobydeptid(deptid, "0");
		
		if(sept.getTmpdeptid()!=null && !"".equals(sept.getTmpdeptid()) ){
			list = (ArrayList) deptbo.getNextLevecDepts(sept.getTmpdeptid(), "0");	
		}else{
		list = (ArrayList) deptbo.getNextLevecDepts(deptid, "0");
				}
			for (int i = 0; i < list.size(); i++) {
				TawSystemDept subDept = (TawSystemDept) list.get(i);
				String subDeptID = subDept.getDeptId();
				String subDeptName = subDept.getDeptName();
				int subDeptLeaf = new Integer(subDept.getLeaf()).intValue();
				JSONObject jitem = new JSONObject();
				jitem.put("id", subDeptID);
				jitem.put("text", subDeptName);
				jitem.put("parentDeptid", subDept.getParentDeptid());
				jitem.put(UIConstants.JSON_NODETYPE, "dept");
				// jitem.put("iconCls", "dept");
				jitem.put("leaf", subDeptLeaf);
				jitem.put("allowChild", true);
				jitem.put("allowDelete", true);

				if ("dept".equalsIgnoreCase(chkType)) {
					jitem.put("checked", false);
				}
				jitem.put("qtip", "部门负责人:" + subDept.getDeptmanager()
						+ "<br \\/>部门电话:" + subDept.getDeptphone()
						+ "<br \\/>备注:" + subDept.getRemark());
				jitem.put("qtipTitle", subDept.getDeptName());
				json.put(jitem);
			}

		} catch (Exception ex) {
			BocoLog.error(this, "生成部门树图时报错：" + ex);
		}
		return json;
	}
	public JSONArray getDeptTree(String deptid) {
		JSONArray json = new JSONArray();
		ArrayList list = new ArrayList();
		list = (ArrayList) deptbo.getNextLevecDepts(deptid, "0");
		try {

			for (int i = 0; i < list.size(); i++) {
				TawSystemDept subDept = (TawSystemDept) list.get(i);
				String subDeptID = subDept.getDeptId();
				String subDeptName = subDept.getDeptName();
				int subDeptLeaf = 0;
				//若不为空则取是否叶子节点
				if (subDept.getLeaf() != null)
					subDeptLeaf = new Integer(subDept.getLeaf()).intValue();

				JSONObject jitem = new JSONObject();
				jitem.put("id", subDeptID);
				jitem.put("text", subDeptName);
				jitem.put("parentDeptid", subDept.getParentDeptid());
				jitem.put(UIConstants.JSON_NODETYPE, "dept");
				// jitem.put("iconCls", "dept");
				jitem.put("leaf", subDeptLeaf);
				jitem.put("allowChild", true);
				jitem.put("allowDelete", true);

				jitem.put("qtip", "部门负责人:" + subDept.getDeptmanager()
						+ "<br \\/>部门电话:" + subDept.getDeptphone()
						+ "<br \\/>备注:" + subDept.getRemark());
				jitem.put("qtipTitle", subDept.getDeptName());
				json.put(jitem);
			}

		} catch (Exception ex) {
			BocoLog.error(this, "生成部门树图时报错：" + ex);
		}
		return json;
	}

	/**
	 * 部门树 值班用
	 * 
	 * @param deptid
	 * @return
	 */
	public JSONArray getDeptTreeForDept(String deptid, String chkType,
			String userId) {
		JSONArray json = new JSONArray();
		ArrayList list = new ArrayList();
		List privList = new ArrayList();

		TawSystemDept tawSystemDept = null;
		TawSystemAssignBo privBO = TawSystemAssignBo.getInstance();
		ITawSystemDeptManager mgr = (ITawSystemDeptManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDeptManager");
		List listFdept = new ArrayList();
		TawRmAssignworkBO tawRmAssignworkBO = null;
		TawSystemPrivRegion tawSystemPrivRegion = null;
		String deptStr = "";
		Vector deptPriv = new Vector();
		try {
			if (userId.equals(StaticVariable.ADMIN)) {
				list = (ArrayList) deptbo.getNextLevecDepts(deptid, "0");
				for (int i = 0; i < list.size(); i++) {
					TawSystemDept subDept = (TawSystemDept) list.get(i);
					String subDeptID = subDept.getDeptId();
					String subDeptName = subDept.getDeptName();
					int subDeptLeaf = new Integer(subDept.getLeaf()).intValue();

					JSONObject jitem = new JSONObject();
					jitem.put("id", subDeptID);
					jitem.put("text", subDeptName);
					jitem.put(UIConstants.JSON_NODETYPE, "dept");
					// jitem.put("iconCls", "dept");
					jitem.put("leaf", subDeptLeaf);
					jitem.put("allowChild", true);
					jitem.put("allowDelete", true);

					if ("dept".equalsIgnoreCase(chkType)) {
						jitem.put("checked", false);
					}
					jitem.put("qtip", "部门负责人:" + subDept.getDeptmanager()
							+ "<br \\/>部门电话:" + subDept.getDeptphone()
							+ "<br \\/>备注:" + subDept.getRemark());
					jitem.put("qtipTitle", subDept.getDeptName());
					json.put(jitem);
				}
			} else {

				privList = privBO
						.getPermissions(
								userId,
								com.boco.eoms.base.util.StaticVariable.PRIV_ASSIGNTYPE_USER,
								com.boco.eoms.base.util.StaticVariable.PRIV_TYPE_REGION_DEPT);

				if (privList.size() > 0) {
					for (int i = 0; i < privList.size(); i++) {

						tawSystemPrivRegion = (TawSystemPrivRegion) privList
								.get(i);

						String deptId = tawSystemPrivRegion.getRegionid();
						tawSystemDept = mgr.getDeptinfobydeptid(deptId, "0");
						JSONObject jitem = new JSONObject();

						jitem.put("id", tawSystemDept.getDeptId());
						jitem.put("text", tawSystemDept.getDeptName());
						jitem.put(UIConstants.JSON_NODETYPE, "dept");
						// jitem.put("iconCls", "dept");
						jitem.put("leaf", 1);
						jitem.put("allowChild", true);
						jitem.put("allowDelete", true);

						if ("dept".equalsIgnoreCase(chkType)) {
							jitem.put("checked", false);
						}

						json.put(jitem);
					}

				}
			}
		} catch (Exception ex) {
			BocoLog.error(this, "生成部门树图时报错：" + ex);
		}
		return json;
	}

	/**
	 * 机房树
	 * 
	 * @param parentid
	 * @param chkType
	 * @return
	 */
	public JSONArray getCptroomTree(String parentid, String chkType) {
		JSONArray json = new JSONArray();
		ArrayList list = new ArrayList();
		list = (ArrayList) cptroombo.getNextLevelCptrooms(parentid, "0");
		try {

			for (int i = 0; i < list.size(); i++) {
				TawSystemCptroom subCptroom = (TawSystemCptroom) list.get(i);
				String subCptroomID = subCptroom.getId().toString();
				String subCptroomName = subCptroom.getRoomname();
				int subCptroomLeaf = new Integer(subCptroom.getLeaf())
						.intValue();

				JSONObject jitem = new JSONObject();
				jitem.put("id", subCptroomID);
				jitem.put("text", subCptroomName);
				jitem.put(UIConstants.JSON_NODETYPE,
						UIConstants.NODETYPE_CPTROOM);
				jitem.put("leaf", subCptroomLeaf);
				//jitem.put("allowChild", true);
				jitem.put("allowDelete", true);

				if (chkType.indexOf(UIConstants.NODETYPE_CPTROOM) > -1) {
					jitem.put("checked", false);
				}
				jitem.put("qtip", "机房负责人:" + subCptroom.getManager()
						+ "<br \\/>机房电话:" + subCptroom.getPhone()
						+ "<br \\/>备注:" + subCptroom.getNotes());
				jitem.put("qtipTitle", subCptroom.getRoomname());
				json.put(jitem);
			}

		} catch (Exception ex) {
			BocoLog.error(this, "生成机房树图时报错：" + ex);
		}
		return json;
	}

	public JSONArray getCheckServiceTree(String parentid, String chkType) {
		JSONArray json = new JSONArray();
		ArrayList list = new ArrayList();
		ISmsServiceManager mgr = (ISmsServiceManager) ApplicationContextHolder
				.getInstance().getBean("IsmsServiceManager");
		list = (ArrayList) mgr.getNextLevelServices(parentid, "0");
		try {

			for (int i = 0; i < list.size(); i++) {
				SmsService subModule = (SmsService) list.get(i);
				String subModuleID = subModule.getId().toString();
				String subModuleName = subModule.getName();
				String status = subModule.getStatus();
				int subModuleLeaf = new Integer(subModule.getLeaf()).intValue();

				JSONObject jitem = new JSONObject();
				jitem.put("id", subModuleID);
				jitem.put("text", subModuleName);
				if (status != null && !status.equals("") && status.equals("1")) {
					jitem.put(UIConstants.JSON_NODETYPE, "smsModule");
					jitem.put("allowChild", true);
					// jitem.put("checked", false);
				} else {
					jitem.put(UIConstants.JSON_NODETYPE, "smsService");
					jitem.put("allowChild", false);
					jitem.put("checked", false);
					// jitem.put("leaf", 1);
				}

				jitem.put("leaf", subModuleLeaf);
				jitem.put("allowDelete", true);

				if ("smsService".equalsIgnoreCase(chkType)) {
					jitem.put("checked", true);
				}
				jitem.put("qtip", "模块业务名称:" + subModule.getName()
						+ "<br \\/>创建人:" + subModule.getUserId()
						+ "<br \\/>备注:" + subModule.getRemark());
				jitem.put("qtipTitle", subModule.getName());
				json.put(jitem);
			}

		} catch (Exception ex) {
			BocoLog.error(this, "生成取消服务树图时报错：" + ex);
		}
		return json;
	}

	/**
	 * 部门用户树
	 * 
	 * @param deptid
	 * @return
	 */
	public JSONArray getDeptUserTree(String deptid) {

		JSONArray json = new JSONArray();
		try {
			ArrayList list = (ArrayList) deptbo.getNextLevecDepts(deptid, "0");
			for (int i = 0; i < list.size(); i++) {
				TawSystemDept subDept = (TawSystemDept) list.get(i);
				String subDeptID = subDept.getDeptId();
				String subDeptName = subDept.getDeptName();
				JSONObject jitem = new JSONObject();
				jitem.put("id", subDeptID);
				jitem.put("text", subDeptName);
				jitem.put(UIConstants.JSON_NODETYPE, "dept");
				// jitem.put("iconCls", "dept");
				List flaguser = userrolebo
						.getUserBydeptids(subDept.getDeptId());
				List flagdept = deptbo.getNextLevecDepts(subDept.getDeptId(),
						"0");
				if (flagdept == null || flagdept.size() <= 0) {
					if (flaguser == null || flaguser.size() <= 0) {
						jitem.put("leaf", 1);
					}
				} else {
					jitem.put("leaf", 0);
				}
				json.put(jitem);

			}
			ArrayList userlist = new ArrayList();
			userlist = (ArrayList) userrolebo.getUserBydeptids(deptid);
			TawSystemUser sysuser = new TawSystemUser();
			if (userlist.size() > 0) {
				for (int j = 0; j < userlist.size(); j++) {
					sysuser = (TawSystemUser) userlist.get(j);

					JSONObject jitem = new JSONObject();
					jitem.put("id", sysuser.getUserid());
					jitem.put("text", sysuser.getUsername());
					jitem.put(UIConstants.JSON_NODETYPE, "user");
					jitem.put("mobile", sysuser.getMobile());
					jitem.put("iconCls", "user");
					jitem.put("leaf", 1);
					json.put(jitem);
				}
			}
		} catch (Exception ex) {
			BocoLog.error(this, "生成部门用户树图时报错：" + ex);
		}

		return json;
	}

	/**
	 * 子角色-用户树
	 * 
	 * @param subRoleId
	 *            子角色id
	 * @param chkType
	 *            选择类型 默认为user
	 * @return
	 */
	public JSONArray getSubRoleUserTree(String subRoleId, String chkType) {

		JSONArray json = new JSONArray();
		try {
			ITawSystemUserRefRoleManager mgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
					.getInstance().getBean("itawSystemUserRefRoleManager");

			List list = mgr.getUserbyroleid(subRoleId);

			for (int i = 0; i < list.size(); i++) {
				TawSystemUser user = (TawSystemUser) list.get(i);
				String userId = user.getUserid();
				String userName = user.getUsername();
				JSONObject jitem = new JSONObject();
				jitem.put("id", userId);
				jitem.put("text", userName);
				jitem.put(UIConstants.JSON_NODETYPE, "user");
				jitem.put("iconCls", "user");
				jitem.put("leaf", 1);
				if ("user".equalsIgnoreCase(chkType)) {
					jitem.put("checked", false);
				}
				json.put(jitem);

			}
		} catch (Exception ex) {
			BocoLog.error(this, "生成部门用户树图时报错：" + ex);
		}

		return json;
	}

	/**
	 * 用于用户管理的部门用户树
	 * 
	 * @param deptid
	 * @return
	 */
	public JSONArray getUserDeptTree(String deptid, String chkType) {

		JSONArray json = new JSONArray();
		try {
			ArrayList list = (ArrayList) deptbo.getNextLevecDepts(deptid, "0");
			for (int i = 0; i < list.size(); i++) {
				TawSystemDept subDept = (TawSystemDept) list.get(i);
				String subDeptID = subDept.getDeptId();
				String subDeptName = subDept.getDeptName();
				JSONObject jitem = new JSONObject();
				jitem.put("id", subDeptID);
				jitem.put("text", subDeptName);
				jitem.put(UIConstants.JSON_NODETYPE, "dept");
				// jitem.put("iconCls", "dept");
				List flaguser = userrolebo
						.getUserBydeptids(subDept.getDeptId());
				List flagdept = deptbo.getNextLevecDepts(subDept.getDeptId(),
						"0");
				if (flagdept == null || flagdept.size() <= 0) {
					if (flaguser == null || flaguser.size() <= 0) {
						jitem.put("leaf", 1);
					}
				} else {
					jitem.put("leaf", 0);
				}
				jitem.put("allowChild", true);
				jitem.put("allowDelete", false);
				jitem.put("allowEdit", false);
				if ("dept".equalsIgnoreCase(chkType)) {
					jitem.put("checked", false);
				}
				jitem.put("qtip", "部门负责人:" + subDept.getDeptmanager()
						+ "<br \\/>部门电话:" + subDept.getDeptphone()
						+ "<br \\/>备注:" + subDept.getRemark());
				jitem.put("qtipTitle", subDept.getDeptName());
				json.put(jitem);

			}
			ArrayList userlist = new ArrayList();
			userlist = (ArrayList) userrolebo.getUserBydeptids(deptid);
			TawSystemUser sysuser = new TawSystemUser();
			if (userlist.size() > 0) {
				for (int j = 0; j < userlist.size(); j++) {
					sysuser = (TawSystemUser) userlist.get(j);

					JSONObject jitem = new JSONObject();
					jitem.put("id", sysuser.getId());
					jitem.put("text", sysuser.getUsername());
					jitem.put(UIConstants.JSON_NODETYPE, "user");
					jitem.put("iconCls", "user");
					jitem.put("leaf", 1);
					jitem.put("allowChild", false);
					jitem.put("allowDelete", true);
					jitem.put("allowEdit", true);
					if ("user".equalsIgnoreCase(chkType)) {
						jitem.put("checked", false);
					}

					json.put(jitem);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			BocoLog.error(this, "生成部门用户树图时报错：" + ex);
		}

		return json;
	}

	/**
	 * 通过部门得到部门下的用户
	 * 
	 * @param deptid
	 * @return
	 */
	public JSONArray getUserByDeptTree(String deptid, String chkType) {

		JSONArray json = new JSONArray();
		try {
			ArrayList userlist = new ArrayList();
			userlist = (ArrayList) userrolebo.getUserBydeptids(deptid);
			TawSystemUser sysuser = new TawSystemUser();
			if (userlist.size() > 0) {
				for (int j = 0; j < userlist.size(); j++) {
					sysuser = (TawSystemUser) userlist.get(j);

					JSONObject jitem = new JSONObject();
					jitem.put("id", sysuser.getId());
					jitem.put("text", sysuser.getUsername());
					jitem.put(UIConstants.JSON_NODETYPE, "user");
					jitem.put("iconCls", "user");
					jitem.put("leaf", 1);
					jitem.put("allowChild", false);
					jitem.put("allowDelete", true);
					jitem.put("allowEdit", true);
					if ("user".equalsIgnoreCase(chkType)) {
						jitem.put("checked", false);
					}

					json.put(jitem);
				}
			}
		} catch (Exception ex) {
			BocoLog.error(this, "生成部门用户树图时报错：" + ex);
		}

		return json;
	}

	/**
	 * 通过部门得到部门下的用户 工作计划使用
	 * 
	 * @param deptid
	 * @return
	 */
	public JSONArray getUserByDeptTreeTaskplan(String deptid, String chkType) {

		JSONArray json = new JSONArray();
		try {
			ArrayList userlist = new ArrayList();
			userlist = (ArrayList) userrolebo.getUserBydeptids(deptid);
			TawSystemUser sysuser = new TawSystemUser();
			if (userlist.size() > 0) {
				for (int j = 0; j < userlist.size(); j++) {
					sysuser = (TawSystemUser) userlist.get(j);

					JSONObject jitem = new JSONObject();
					jitem.put("id", sysuser.getUserid());
					jitem.put("text", sysuser.getUsername());
					jitem.put(UIConstants.JSON_NODETYPE, "user");
					jitem.put("iconCls", "user");
					jitem.put("leaf", 1);
					jitem.put("allowChild", false);
					jitem.put("allowDelete", true);
					jitem.put("allowEdit", true);
					if ("user".equalsIgnoreCase(chkType)) {
						jitem.put("checked", false);
					}

					json.put(jitem);
				}
			}
		} catch (Exception ex) {
			BocoLog.error(this, "生成部门用户树图时报错：" + ex);
		}

		return json;
	}

	/**
	 * 部门角色树
	 * 
	 * @param deptid
	 * @param systemId
	 *            角色所在的流程
	 * @return
	 */
	public JSONArray getDeptRoleTree(String deptid, String systemId,
			String chkType, String roleId) {

		JSONArray json = new JSONArray();
		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemSubRoleManager");
		try {

			ArrayList list = (ArrayList) deptbo.getNextLevecDepts(deptid, "0");

			for (int i = 0; i < list.size(); i++) {
				TawSystemDept subDept = (TawSystemDept) list.get(i);

				String subDeptID = subDept.getDeptId();
				String subDeptName = subDept.getDeptName();

				JSONObject jitem = new JSONObject();
				jitem.put("id", subDeptID);
				jitem.put("text", subDeptName);
				jitem.put(UIConstants.JSON_NODETYPE, "dept");

				// List flagrole = mgr.getSubRolesByDeptId(subDept.getDeptId(),
				// systemId, roleId);
				// List flagdept = deptbo.getNextLevecDepts(subDept.getDeptId(),
				// "0");
				// if (flagdept == null || flagdept.size() <= 0) {
				// if (flagrole == null || flagrole.size() <= 0) {
				// jitem.put("leaf", 1);
				// }
				// }
				jitem.put("allowChild", true);
				jitem.put("allowDelete", true);

				json.put(jitem);

			}
			ArrayList rolelist = (ArrayList) mgr.getSubRolesByDeptId(deptid,
					systemId, roleId);
			if (rolelist.size() > 0) {
				for (int j = 0; j < rolelist.size(); j++) {
					TawSystemSubRole sysrole = (TawSystemSubRole) rolelist
							.get(j);
					JSONObject jitem = new JSONObject();
					jitem.put("id", sysrole.getId());
					jitem.put("text", sysrole.getSubRoleName());
					jitem.put(UIConstants.JSON_NODETYPE, "subrole");
					jitem.put("iconCls", "subrole");
					jitem.put("leaf", 1);
					jitem.put("allowChild", false);
					jitem.put("allowDelete", false);
					if ("subrole".equalsIgnoreCase(chkType)) {
						jitem.put("checked", false);
					}

					json.put(jitem);
				}

			}

		} catch (Exception ex) {
			BocoLog.error(this, "生成部门角色树图时报错：" + ex);
		}

		return json;
	}

	/**
	 * 部门角色用户树
	 * 
	 * @param deptid
	 * @return
	 */
	public JSONArray getDeptRoleUserTree(String id, String chkType) {

		JSONArray json = new JSONArray();

		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemSubRoleManager");
		try {

			ArrayList list = (ArrayList) deptbo.getNextLevecDepts(id, "0");

			for (int i = 0; i < list.size(); i++) {
				TawSystemDept subDept = (TawSystemDept) list.get(i);

				String subDeptID = subDept.getDeptId();
				String subDeptName = subDept.getDeptName();

				JSONObject jitem = new JSONObject();
				jitem.put("id", subDeptID);
				jitem.put("text", subDeptName);
				jitem.put(UIConstants.JSON_NODETYPE, "dept");
				// jitem.put("iconCls", "dept");
				List flagrole = mgr.getSubRolesByDeptId(subDept.getDeptId());
				List flagdept = deptbo.getNextLevecDepts(subDept.getDeptId(),
						"0");
				if (flagdept == null || flagdept.size() <= 0) {
					if (flagrole == null || flagrole.size() <= 0) {
						jitem.put("leaf", 1);
					}
				}
				jitem.put("allowChild", true);
				jitem.put("allowDelete", true);
				if (chkType.indexOf("dept") > -1) {
					jitem.put("checked", false);
				}
				json.put(jitem);

			}
			ArrayList rolelist = (ArrayList) mgr.getSubRolesByDeptId(id);
			if (rolelist.size() > 0) {
				for (int j = 0; j < rolelist.size(); j++) {
					TawSystemSubRole sysrole = (TawSystemSubRole) rolelist
							.get(j);

					JSONObject jitem = new JSONObject();
					jitem.put("id", sysrole.getId());
					jitem.put("text", sysrole.getSubRoleName());
					jitem.put(UIConstants.JSON_NODETYPE, "subrole");
					jitem.put("iconCls", "subrole");
					jitem.put("leaf", 1);
					jitem.put("allowChild", false);
					jitem.put("allowDelete", false);
					if (chkType.indexOf("subrole") > -1) {
						jitem.put("checked", false);
					}
					json.put(jitem);
				}

			}
			ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
					.getInstance().getBean("iTawSystemUserBo");
			ArrayList userlist = (ArrayList) sysuserbo.getUserBydeptids(id);
			for (int n = 0; n < userlist.size(); n++) {
				TawSystemUser sysuser = (TawSystemUser) userlist.get(n);
				JSONObject jitem = new JSONObject();
				jitem.put("id", sysuser.getUserid());
				jitem.put("text", sysuser.getUsername());
				jitem.put(UIConstants.JSON_NODETYPE, "user");
				jitem.put("iconCls", "user");
				jitem.put("leaf", 1);
				jitem.put("allowChild", false);
				jitem.put("allowDelete", false);
				if (chkType.indexOf("user") > -1) {
					jitem.put("checked", false);
				}
				jitem.put("leaf", true);
				json.put(jitem);
			}

		} catch (Exception ex) {
			BocoLog.error(this, "生成部门角色树图时报错：" + ex);
		}

		return json;
	}

	/**
	 * 地域树
	 * 
	 * @param areaid
	 * @return
	 */
	public JSONArray getAreaTree(String areaid, String chkType) {

		JSONArray json = new JSONArray();
		ITawSystemAreaManager mgr = (ITawSystemAreaManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemAreaManager");
		ArrayList list = new ArrayList();
		try {

			list = (ArrayList) mgr.getSonAreaByAreaId(areaid);

			for (int i = 0; i < list.size(); i++) {
				TawSystemArea sysarea = (TawSystemArea) list.get(i);

				JSONObject jitem = new JSONObject();
				jitem.put("id", sysarea.getAreaid());
				jitem.put("text", sysarea.getAreaname());
				jitem.put(UIConstants.JSON_NODETYPE, "area");
				jitem.put("iconCls", "area");
				if (sysarea.getLeaf().equals("1")) {
					jitem.put("leaf", 1);
				}
				jitem.put("allowChild", true);
				jitem.put("allowDelete", true);
				if ("area".equalsIgnoreCase(chkType)) {
					jitem.put("checked", false);
				}
				json.put(jitem);

			}

		} catch (Exception ex) {
			BocoLog.error(this, "生成地域树图时报错：" + ex);
		}
		return json;
	}

	/**
	 * 专业树图
	 * 
	 * @param speid
	 * @param chkType
	 * @return
	 */
	public JSONArray getSpecialTree(String speid, String chkType) {
		JSONArray json = new JSONArray();
		ITawSheetSpecialManager mgr = (ITawSheetSpecialManager) ApplicationContextHolder
				.getInstance().getBean("ItawSheetSpecialManager");
		ArrayList list = new ArrayList();
		try {
			list = (ArrayList) mgr.getSonspecialByspecialId(speid);
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				TawSheetSpecial special = (TawSheetSpecial) iter.next();
				JSONObject jitem = new JSONObject();
				jitem.put("id", special.getSpeid());
				jitem.put("text", special.getSpecialname());
				jitem.put(UIConstants.JSON_NODETYPE, "special");
				jitem.put("iconCls", "special");
				jitem.put("allowChild", true);
				jitem.put("allowDelete", true);
				if ("special".equalsIgnoreCase(chkType)) {
					jitem.put("checked", false);
				}
				json.put(jitem);
			}
			ITawSystemUserBo sysuserbo = (ITawSystemUserBo) ApplicationContextHolder
					.getInstance().getBean("iTawSystemUserBo");
			List userlist = mgr.getSpecialRefUserList(speid);
			for (int n = 0; n < userlist.size(); n++) {
				ListOrderedMap item = (ListOrderedMap) userlist.get(n);
				String id = (String) item.getValue(0);
				TawSystemUser sysuser = sysuserbo.getUserByuserid(id);
				JSONObject jitem = new JSONObject();
				jitem.put("id", sysuser.getUserid());
				jitem.put("text", sysuser.getUsername());
				jitem.put(UIConstants.JSON_NODETYPE, "user");
				jitem.put("iconCls", "user");
				jitem.put("leaf", 1);
				jitem.put("allowChild", false);
				jitem.put("allowDelete", false);
				jitem.put("allowEdit", false);
				String rest = sysuser.getIsrest();
				if (null == rest || "0".equals(rest)) {
					rest = "未出差";
				} else {
					rest = "出差";
				}
				jitem.put("qtip", "所在部门:" + sysuser.getDeptname()
						+ "<br \\/>是否出差:" + rest + "<br \\/>联系电话:"
						+ sysuser.getMobile());
				jitem.put("qtipTitle", sysuser.getUsername());
				jitem.put("leaf", true);
				json.put(jitem);
			}
		} catch (Exception ex) {
			BocoLog.error(this, "生成专业树图时报错：" + ex);
		}
		return json;
	}

	/**
	 * 部门岗位树
	 * 
	 * @param deptid
	 * @param chkType
	 * @return
	 */
	public JSONArray getDeptPostTree(String id, String chkType) {
		JSONArray json = new JSONArray();

		try {

			ITawSystemPostManager mgr = (ITawSystemPostManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemPostManager");
			ArrayList list = (ArrayList) deptbo.getNextLevecDepts(id, "0");
			for (int i = 0; i < list.size(); i++) {
				TawSystemDept subDept = (TawSystemDept) list.get(i);

				String subDeptID = subDept.getDeptId();
				String subDeptName = subDept.getDeptName();

				JSONObject jitem = new JSONObject();
				jitem.put("id", subDeptID);
				jitem.put("text", subDeptName);
				jitem.put(UIConstants.JSON_NODETYPE, "dept");
				// jitem.put("iconCls", "dept");
				List flagpost = mgr.getPostsByDeptId(subDept.getDeptId());
				List flagdept = deptbo.getNextLevecDepts(subDept.getDeptId(),
						"0");
				if (flagdept == null || flagdept.size() <= 0) {
					if (flagpost == null || flagpost.size() <= 0) {
						jitem.put("leaf", 1);
					}
				} else {
					jitem.put("leaf", 0);
				}
				jitem.put("allowChild", true);
				jitem.put("allowDelete", false);
				if ("dept".equalsIgnoreCase(chkType)) {
					jitem.put("checked", false);
				}
				jitem.put("qtip", "部门负责人:" + subDept.getDeptmanager()
						+ "<br \\/>部门ID:" + subDept.getDeptId() + "<br \\/>备注:"
						+ subDept.getRemark());
				jitem.put("qtipTitle", subDept.getDeptName());
				json.put(jitem);

			}
			ArrayList postlist = (ArrayList) mgr.getPostsByDeptId(id);
			if (postlist.size() > 0) {
				for (int j = 0; j < postlist.size(); j++) {
					TawSystemPost syspost = (TawSystemPost) postlist.get(j);

					JSONObject jitem = new JSONObject();
					jitem.put("id", syspost.getPostId());
					jitem.put("text", syspost.getPostName());
					jitem.put(UIConstants.JSON_NODETYPE, "post");
					jitem.put("iconCls", "post");
					jitem.put("leaf", 1);
					jitem.put("allowChild", false);
					jitem.put("allowDelete", true);
					if ("post".equalsIgnoreCase(chkType)) {
						jitem.put("checked", false);
					}

					json.put(jitem);
				}
			}
		}

		catch (Exception ex) {
			BocoLog.error(this, "生成岗位树图时报错：" + ex);
		}

		return json;
	}

	/**
	 * 权限配置树
	 * 
	 * @param privid
	 * @param chkType
	 * @return
	 */
	public JSONArray getPrivOperationTree(String privid, String chkType,
			String deleted) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();
		ITawSystemPrivOperationManager _objOptMgr = (ITawSystemPrivOperationManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivOperationManager");
		if ("-1".equals(privid)) {
			list = _objOptMgr.getModules(privid, deleted);
		} else {
			TawSystemPrivOperation opt = (TawSystemPrivOperation) _objOptMgr
					.getTawSystemPrivOperation(privid);
			list = _objOptMgr.getModules(opt.getCode(), deleted);
		}

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawSystemPrivOperation _objOneOpt = (TawSystemPrivOperation) rowIt
					.next();

			JSONObject jitem = new JSONObject();
			jitem.put("id", _objOneOpt.getId());
			jitem.put("parentcode", _objOneOpt.getParentcode());
			jitem.put("code", _objOneOpt.getCode());
			jitem.put("isApp", _objOneOpt.getIsApp());
			jitem.put("text", _objOneOpt.getName());
			jitem.put("url", _objOneOpt.getUrl());
			// 为moudle，可新增下级菜单
			jitem.put("allowChild", "0".equals(_objOneOpt.getIsApp()) ? true
					: false);
			jitem.put("allowDelete", true);
			jitem.put("qtip", "排序码：" + _objOneOpt.getOrderby() + "<br \\/>代码:"
					+ _objOneOpt.getCode() + "<br \\/>链接地址:"
					+ _objOneOpt.getUrl() + "<br \\/>备注:"
					+ _objOneOpt.getRemark());
			jitem.put("qtipTitle", _objOneOpt.getName());
			if (_objOneOpt.isLeaf()) {
				jitem.put("leaf", _objOneOpt.isLeaf());
			}
			if ("privoperation".equalsIgnoreCase(chkType)) {
				jitem.put("checked", false);
			}

			json.put(jitem);
		}
		return json;
	}

	/**
	 * 查询非管理员的权限树 用于菜单管理
	 * 
	 * @param userid
	 * @return
	 */
	public JSONArray getUserPrivTree(String privid, String userid) {
		JSONArray json = new JSONArray();
		// ITawSystemPrivUserAssignManager userassignpriv =
		// (ITawSystemPrivUserAssignManager) ApplicationContextHolder
		// .getInstance().getBean("ItawSystemPrivUserAssignManager");
		ITawSystemPrivOperationManager _objOptMgr = (ITawSystemPrivOperationManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivOperationManager");
		//
		// List list = new ArrayList();
		// if ("-1".equals(privid)) {
		// list = userassignpriv.getLowerleveMenus(userid, privid);
		// } else {
		// String operprivid = _objOptMgr.getTawSystemPrivOperation(privid)
		// .getCode();
		// TawSystemPrivUserAssign opt = (TawSystemPrivUserAssign)
		// userassignpriv
		// .getTawSystemUserAssign(userid, privid);
		// list = userassignpriv.getLowerleveMenus(userid, opt
		// .getCurrentprivid());
		// }
		List list = null;

		// 判断是否为根节点，根节点使用-1做为父节点，否则需要转换菜单ID为code，原因是修改菜单使用为id主键，而显示树型结构使用的是code,parentCode
		// TODO 由于数据原因，时间问题暂无修改，应该为parentCode指向id而非code
		if ("-1".equals(privid)) {
			list = PrivMgrLocator.getPrivMgr().listOpertion(userid,
					PrivConstants.LIST_OPERATION_TYPE_ALL, privid);
		} else {
			String operprivid = _objOptMgr.getTawSystemPrivOperation(privid)
					.getCode();
			list = PrivMgrLocator.getPrivMgr().listOpertion(userid,
					PrivConstants.LIST_OPERATION_TYPE_ALL, operprivid);
		}
		for (Iterator it = list.iterator(); it.hasNext();) {

			TawSystemPrivOperation operation = (TawSystemPrivOperation) it
					.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", operation.getId());
			jitem.put("parentcode", operation.getParentcode());
			jitem.put("code", operation.getCode());
			jitem.put("isApp", operation.getIsApp());
			jitem.put("text", operation.getName());
			jitem.put("url", operation.getUrl());
			// 为module，下面可建菜单
			jitem.put("allowChild", "1".equals(operation.getIsApp()) ? false
					: true);

			jitem.put("allowDelete", true);
			jitem.put("qtip", "排序码：" + operation.getOrderby() + "<br \\/>代码:"
					+ operation.getCode() + "<br \\/>链接地址:"
					+ operation.getUrl() + "<br \\/>备注:"
					+ operation.getRemark());
			jitem.put("qtipTitle", operation.getName());
			// jitem.put("leaf", "1".equals(operation.getIsApp()) ? true :
			// false);
			if (operation.isLeaf()) {
				jitem.put("leaf", operation.isLeaf());
			}
			json.put(jitem);
		}

		// for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
		// TawSystemPrivUserAssign userassign = (TawSystemPrivUserAssign) rowIt
		// .next();
		// JSONObject jitem = new JSONObject();
		// jitem.put("id", _objOptMgr.getTawSystemPrivOpt(
		// userassign.getCurrentprivid()).getId());
		// jitem.put("parentcode", userassign.getParentprivid());
		// jitem.put("code", userassign.getCurrentprivid());
		// jitem.put("isApp", userassign.getLeaf());
		// jitem.put("text", userassign.getCurrentprivname());
		// jitem.put("url", userassign.getUrl());
		// jitem.put("allowChild", "1".equals(String.valueOf(userassign
		// .getLeaf())) ? false : true);
		// jitem.put("allowDelete", true);
		// jitem.put("qtip", "代码:" + userassign.getCurrentprivid()
		// + "<br \\/>链接地址:" + userassign.getUrl() + "<br \\/>备注:"
		// + userassign.getRemark());
		// jitem.put("qtipTitle", userassign.getCurrentprivname());
		// if (userassign.getLeaf().intValue() == StaticVariable.LEAF) {
		// jitem.put("leaf", true);
		// }
		//
		// json.put(jitem);
		// }

		return json;
	}

	/**
	 * 查询非管理员的权限树 用于菜单方案
	 * 
	 * @param userid
	 * @return
	 */
	public JSONArray getUserPrivItemTree(String menuid, String userid,
			String parentprivid) {
		JSONArray json = new JSONArray();
		// ITawSystemPrivUserAssignManager userassignpriv =
		// (ITawSystemPrivUserAssignManager) ApplicationContextHolder
		// .getInstance().getBean("ItawSystemPrivUserAssignManager");

		List list = new ArrayList();

		TawSystemPrivAssignOut privHelper = TawSystemPrivAssignOut
				.getInstance();

		// list = userassignpriv
		// .getNextUserPrivMenus(menuid, userid, parentprivid);

		list = PrivMgrLocator.getPrivMgr().listOperation(userid, menuid,
				PrivConstants.LIST_OPERATION_TYPE_ALL, parentprivid);

		for (Iterator it = list.iterator(); it.hasNext();) {
			TawSystemPrivOperation operation = (TawSystemPrivOperation) it
					.next();
			// String menuItemCode = operation.getCode();
			// String menuText = privHelper.getNameBycode(menuItemCode);
			JSONObject jitem = new JSONObject();
			jitem.put("id", operation.getId());
			jitem.put("privid", menuid);
			jitem.put("code", operation.getCode());
			jitem.put("parentcode", operation.getParentcode());
			jitem.put("isApp", operation.getIsApp());
			jitem.put("text", operation.getName());
			jitem.put("allowDelete", true);
			// jitem.put("leaf", "1".equals(operation.getIsApp()) ? true :
			// false);
			if (operation.isLeaf()) {
				jitem.put("leaf", operation.isLeaf());
			}
			json.put(jitem);
		}

		// for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
		// TawSystemPrivUserAssign userassign = (TawSystemPrivUserAssign) rowIt
		// .next();
		// String menuItemCode = userassign.getCurrentprivid();
		// String menuText = privHelper.getNameBycode(menuItemCode);
		// JSONObject jitem = new JSONObject();
		// jitem.put("id", userassign.getId());
		// jitem.put("privid", userassign.getMenuid());
		// jitem.put("code", userassign.getCurrentprivid());
		// jitem.put("parentcode", userassign.getParentprivid());
		// jitem.put("isApp", userassign.getIsonepriv());
		// jitem.put("text", menuText);
		// jitem.put("allowDelete", true);
		// if (userassign.getLeaf().intValue() == 1) {
		// jitem.put("leaf", true);
		// } else {
		// jitem.put("leaf", false);
		// }
		//			
		//
		// json.put(jitem);
		// }
		return json;
	}

	/*
	 * 菜单方案配置时取节点 @param String privid 菜单方案id @param String parentCode 父菜单项code
	 */
	public JSONArray getPrivMenuItemTree(String privid, String parentCode) {
		JSONArray json = new JSONArray();
		List list = new ArrayList();
		ITawSystemPrivMenuItemManager menuItemMgr = (ITawSystemPrivMenuItemManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivMenuItemManager");

		TawSystemPrivAssignOut privHelper = TawSystemPrivAssignOut
				.getInstance();

		list = menuItemMgr.getNextLevelMenus(privid, parentCode);

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawSystemPrivMenuItem menuItem = (TawSystemPrivMenuItem) rowIt
					.next();
			String menuItemCode = menuItem.getCode();
			String menuText = privHelper.getNameBycode(menuItemCode);
			// 若菜单已经删除或隐藏则不在菜单方案中显示
			if (null == menuText || "".equals(menuText)) {
				continue;
			}
			JSONObject jitem = new JSONObject();
			jitem.put("id", menuItem.getId());
			jitem.put("privid", menuItem.getMenuid());
			jitem.put("code", menuItem.getCode());
			jitem.put("parentcode", menuItem.getParentcode());
			jitem.put("isApp", menuItem.getIsApp());
			jitem.put("text", menuText);
			jitem.put("allowDelete", true);
			if (privHelper.getIsleafbyCode(menuItemCode)) {
				jitem.put("leaf", privHelper.getIsleafbyCode(menuItemCode));
			}
			// if(menuItem.getIsLeaf().equals("1")){
			// jitem.put("leaf", true);
			// }

			json.put(jitem);
		}
		return json;
	}

	/**
	 * 岗位树
	 * 
	 * @param postid
	 * @param chkType
	 * @return
	 */
	public JSONArray getPostTree(String nodeId, String chkType) {
		JSONArray json = new JSONArray();
		try {

			if ("".equals(chkType) || "dept".equals(chkType)) {
				ArrayList list = (ArrayList) deptbo.getNextLevecDepts(nodeId,
						"0");

				for (int i = 0; i < list.size(); i++) {
					TawSystemDept subDept = (TawSystemDept) list.get(i);

					String subDeptID = subDept.getDeptId();
					String subDeptName = subDept.getDeptName();

					JSONObject jitem = new JSONObject();
					jitem.put("id", subDeptID);
					jitem.put("text", subDeptName);
					jitem.put(UIConstants.JSON_NODETYPE, "dept");
					jitem.put("allowChild", true);
					jitem.put("allowDelete", false);
					jitem.put("allowEdit", false);

					json.put(jitem);

				}

				ITawSystemPostManager mgr = (ITawSystemPostManager) ApplicationContextHolder
						.getInstance().getBean("ItawSystemPostManager");
				List postlist = mgr.getPostsByDeptId(nodeId);
				if (postlist.size() > 0) {
					for (int j = 0; j < postlist.size(); j++) {
						TawSystemPost syspost = (TawSystemPost) postlist.get(j);

						JSONObject jitem = new JSONObject();
						jitem.put("id", syspost.getPostId());
						jitem.put("text", syspost.getPostName());
						jitem.put(UIConstants.JSON_NODETYPE, "post");
						jitem.put("iconCls", "post");
						jitem.put("leaf", 0);
						jitem.put("allowChild", false);
						jitem.put("allowDelete", true);

						json.put(jitem);
					}
				}
			} else if ("post".equals(chkType)) {
				ITawSystemSubRoleRefPostManager mgr = (ITawSystemSubRoleRefPostManager) ApplicationContextHolder
						.getInstance().getBean(
								"ItawSystemSubRoleRefPostManager");
				List subRoleList = mgr.getSubRoleByPostId(new Long(nodeId)
						.longValue());
				for (int j = 0; j < subRoleList.size(); j++) {
					TawSystemSubRole subRole = (TawSystemSubRole) subRoleList
							.get(j);

					JSONObject jitem = new JSONObject();
					jitem.put("id", subRole.getId());
					jitem.put("text", subRole.getSubRoleName());
					jitem.put(UIConstants.JSON_NODETYPE, "subrole");
					jitem.put("iconCls", "role");
					jitem.put("leaf", 1);
					jitem.put("allowChild", false);
					jitem.put("allowDelete", false);
					jitem.put("allowEdit", false);

					json.put(jitem);
				}
			}

		} catch (Exception ex) {
			BocoLog.error(this, "生成岗位树图时报错：" + ex);
			ex.printStackTrace();
		}
		return json;
	}

	/**
	 * 得到门户的角色树
	 * 
	 * @param deptid
	 * @param chkType
	 * @return
	 */
	public JSONArray getPortalRoleTree(String portalrole) {
		JSONArray json = new JSONArray();
		String[] rolestr = null;
		PortalService locator = new PortalServiceLocator();
		PortalServicePortType portype = new PortalServicePortTypeProxy();
		try {
			portype = locator.getPortalServiceHttpPort();
			rolestr = portype.getPortalRoleList();
			if (rolestr != null) {
				for (int i = 0; i < rolestr.length; i++) {
					JSONObject jitem = new JSONObject();
					jitem.put("id", rolestr[i]);
					jitem.put("text", rolestr[i]);
					jitem.put(UIConstants.JSON_NODETYPE, "portalrole");
					jitem.put("leaf", true);

					jitem.put("checked", false);

					json.put(jitem);
				}
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			BocoLog.error(this, "生成门户角色树图时报错：" + ex);
		}

		return json;
	}

	/**
	 * 根据角色ID查询大角色以及大角色下所有的子角色 此树图提供给权限管理使用
	 * 
	 * @param roleid
	 * @return
	 */
	public JSONArray getRoleAndSubroleTreeForPriv(String roleId) {
		JSONArray json = new JSONArray();
		ITawSystemRoleManager mgr = (ITawSystemRoleManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemRoleManager");
		ITawSystemSubRoleManager subrolemgr = (ITawSystemSubRoleManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemSubRoleManager");
		// TawSystemAssignBo assignbo = TawSystemAssignBo.getInstance();
		List rolelist = null;
		try {
			rolelist = (ArrayList) mgr.getChildRoleByRoleIdPirv(Long
					.parseLong(roleId));
			if (rolelist != null) {
				String assignroleid = "";
				for (int i = 0; i < rolelist.size(); i++) {
					JSONObject jitem = new JSONObject();
					TawSystemRole sysrole = (TawSystemRole) rolelist.get(i);
					assignroleid = String.valueOf(sysrole.getRoleId());
					jitem.put("id", assignroleid);
					jitem.put("text", sysrole.getRoleName());
					jitem.put(UIConstants.JSON_NODETYPE, "role");
					// jitem.put("hasAsg",
					// assignbo.hasAssign(assignroleid,"role"));
					jitem.put("allowEdit", false);
					json.put(jitem);
				}
				List subrolelist = null;
				subrolelist = (ArrayList) subrolemgr.getTawSystemSubRoles(Long
						.parseLong(roleId));
				for (int i = 0; i < subrolelist.size(); i++) {
					JSONObject jitem = new JSONObject();
					TawSystemSubRole syssubrole = (TawSystemSubRole) subrolelist
							.get(i);
					String subroleid = String.valueOf(syssubrole.getId());
					// boolean hasAsg = assignbo.hasAssign(subroleid,"subrole");
					String subRoleName = syssubrole.getSubRoleName();
					jitem.put("id", subroleid);
					jitem.put("text", subRoleName);
					jitem.put(UIConstants.JSON_NODETYPE, "subrole");
					jitem.put("iconCls", "subrole");
					// jitem.put("hasAsg", hasAsg);
					jitem.put("allowEdit", true);
					jitem.put("leaf", true);
					json.put(jitem);
				}
			}
		} catch (Exception ex) {
			BocoLog.error(this, "生成权限使用的大角色和小角色树图时报错：" + ex);
		}
		return json;
	}

	/**
	 * 权限分配使用的部门用户树
	 * 
	 * @param deptid
	 * @return
	 */
	public JSONArray getDeptUserTreeForPriv(String deptid) {

		JSONArray json = new JSONArray();
		// TawSystemAssignBo assignbo = TawSystemAssignBo.getInstance();
		try {
			ArrayList list = (ArrayList) deptbo.getNextLevecDepts(deptid, "0");
			for (int i = 0; i < list.size(); i++) {
				TawSystemDept subDept = (TawSystemDept) list.get(i);
				String subDeptID = subDept.getDeptId();
				String subDeptName = subDept.getDeptName();
				JSONObject jitem = new JSONObject();
				jitem.put("id", subDeptID);
				jitem.put("text", subDeptName);
				jitem.put(UIConstants.JSON_NODETYPE, "dept");
				// jitem.put("hasAsg", false);
				jitem.put("allowEdit", false);

				List flaguser = userrolebo
						.getUserBydeptids(subDept.getDeptId());
				List flagdept = deptbo.getNextLevecDepts(subDept.getDeptId(),
						"0");
				if (flagdept == null || flagdept.size() <= 0) {
					if (flaguser == null || flaguser.size() <= 0) {
						jitem.put("leaf", true);
					}
				}
				json.put(jitem);
			}
			ArrayList userlist = new ArrayList();
			userlist = (ArrayList) userrolebo.getUserBydeptids(deptid);
			TawSystemUser sysuser = new TawSystemUser();
			if (userlist.size() > 0) {
				for (int j = 0; j < userlist.size(); j++) {
					sysuser = (TawSystemUser) userlist.get(j);
					// boolean hasAsg =
					// assignbo.hasAssign(sysuser.getUserid(),"user");
					String username = sysuser.getUsername();
					JSONObject jitem = new JSONObject();
					jitem.put("id", sysuser.getUserid());
					jitem.put("text", username);
					jitem.put(UIConstants.JSON_NODETYPE, "user");
					jitem.put("iconCls", "user");
					// jitem.put("hasAsg", hasAsg);
					jitem.put("allowEdit", true);
					jitem.put("leaf", true);

					json.put(jitem);
				}
			}
		} catch (Exception ex) {
			BocoLog.error(this, "生成权限使用的部门用户树图时报错：" + ex);
		}

		return json;
	}

	/**
	 * 角色或者用户有哪些菜单方案
	 * 
	 * @param objectid
	 * @return
	 */
	public JSONArray getObjectMenu(String objectid) {
		JSONArray json = new JSONArray();
		List list = null;
		TawSystemAssignBo assignbo = TawSystemAssignBo.getInstance();
		list = assignbo.getObjectPriv(objectid);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				JSONObject jitem = new JSONObject();
				TawSystemPrivAssign assign = (TawSystemPrivAssign) list.get(i);
				jitem.put("id", false);
				jitem.put("text", true);
			}
		}
		return json;
	}

	/**
	 * 根据用户ID获取用户当前的JSON菜单 非管理员和已经分配权限的管理员
	 * 
	 * @param userid
	 * @return panlong
	 */
	public List getPrivAdminMenu(final String userid) {
		ITawSystemPrivOperationManager privmgr = (ITawSystemPrivOperationManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivOperationManager");
		List list = new ArrayList();
		list = privmgr.getDirectSubModules(StaticVariable.ROOT_NODE);

		List menulist = null;
		if (list != null && list.size() > 0) {
			menulist = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				TawSystemPrivOperation obj = (TawSystemPrivOperation) list
						.get(i);
				TawCommonsUIListItem listItem = new TawCommonsUIListItem();
				listItem.setItemId(obj.getCode());
				listItem.setText(obj.getName());
				listItem.setUrl(obj.getUrl());
				menulist.add(listItem);
			}
		}
		return menulist;
	}

	/**
	 * 根据用户ID获取用户当前的JSON菜单 管理员和未分配权限的管理员
	 * 
	 * @param userid
	 * @return panlong
	 */
	public List getPrivUserMenu(final String userid) {
		ITawSystemPrivUserAssignManager assignmanager = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
		List list = new ArrayList();
		list = assignmanager.getOnePriv(userid, StaticVariable.ONE_PRIV);

		List menulist = null;
		if (list != null && list.size() > 0) {
			menulist = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				TawSystemPrivUserAssign obj = (TawSystemPrivUserAssign) list
						.get(i);
				TawCommonsUIListItem listItem = new TawCommonsUIListItem();
				listItem.setText(obj.getCurrentprivname());
				listItem.setUrl(obj.getUrl());
				menulist.add(listItem);
			}
		}
		return menulist;
	}

	public JSONArray getContactTree(String nodeId, String userId, String chkType) {
		TawWorkbenchContactBO contactbo = TawWorkbenchContactBO.getInstance();
		JSONArray json = new JSONArray();
		try {
			ArrayList list = (ArrayList) contactbo.getNextLevecGroups(nodeId,
					userId, "0");
			for (int i = 0; i < list.size(); i++) {
				TawWorkbenchContactGroup group = (TawWorkbenchContactGroup) list
						.get(i);
				String group_id = group.getGroupId();
				String group_name = group.getGroupName();
				JSONObject jitem = new JSONObject();
				jitem.put("id", group_id);
				jitem.put("text", group_name);
				jitem.put(UIConstants.JSON_NODETYPE, "group");
				jitem.put("iconCls", "file");

				jitem.put("leaf", 0);

				jitem.put("allowChild", false);
				jitem.put("allowEdit", true);
				jitem.put("allowChilds", true);
				jitem.put("allowEdits", false);
				jitem.put("allowLists", false);
				jitem.put("allowImp", true);
				jitem.put("allowExp", true);
				jitem.put("allowDelete", true);
				if ("group".equalsIgnoreCase(chkType)) {
					jitem.put("checked", false);
				}

				json.put(jitem);

			}
			ArrayList contactlist = new ArrayList();
			contactlist = (ArrayList) contactbo.getNextLevecContact(userId,
					nodeId, "0");
			TawWorkbenchContact tawWorkbenchContact = new TawWorkbenchContact();
			if (contactlist.size() > 0) {
				for (int j = 0; j < contactlist.size(); j++) {
					tawWorkbenchContact = (TawWorkbenchContact) contactlist
							.get(j);

					JSONObject jitem = new JSONObject();
					jitem.put("id", tawWorkbenchContact.getId());
					jitem.put("text", tawWorkbenchContact.getContactName());
					jitem.put(UIConstants.JSON_NODETYPE, "contact");
					jitem.put("iconCls", "user");
					jitem.put("leaf", 1);
					jitem.put("allowChild", false);
					jitem.put("allowEdit", false);
					jitem.put("allowChilds", false);
					jitem.put("allowEdits", true);
					jitem.put("allowLists", true);
					jitem.put("allowImp", true);
					jitem.put("allowExp", true);
					jitem.put("allowDelete", true);
					if ("contact".equalsIgnoreCase(chkType)) {
						jitem.put("checked", false);
					}

					jitem.put("qtip", "所属部门:"
							+ tawWorkbenchContact.getDeptName()
							+ "<br \\/>联系人职位:"
							+ tawWorkbenchContact.getPosition()
							+ "<br \\/>联系人电话:" + tawWorkbenchContact.getTele()
							+ "<br \\/>地址:" + tawWorkbenchContact.getAddress()
							+ "<br \\/>电子邮件:" + tawWorkbenchContact.getEmail());
					jitem.put("qtipTitle", tawWorkbenchContact.getDeptName());

					json.put(jitem);
				}
			}
		} catch (Exception ex) {
			BocoLog.error(this, "生成部门用户树图时报错：" + ex);
		}

		return json;
	}

	/**
	 * 机房树
	 * 
	 * @param parentid
	 * @param chkType
	 * @return
	 */
	public JSONArray getForumsTree(String parentid, String chkType) {
		JSONArray json = new JSONArray();
		ArrayList list = new ArrayList();
		list = (ArrayList) cptroombo.getNextLevelCptrooms(parentid, "0");
		try {

			for (int i = 0; i < list.size(); i++) {
				TawSystemCptroom subCptroom = (TawSystemCptroom) list.get(i);
				String subCptroomID = subCptroom.getId().toString();
				String subCptroomName = subCptroom.getRoomname();
				int subCptroomLeaf = new Integer(subCptroom.getLeaf())
						.intValue();

				JSONObject jitem = new JSONObject();
				jitem.put("id", subCptroomID);
				jitem.put("text", subCptroomName);
				jitem.put(UIConstants.JSON_NODETYPE,
						UIConstants.NODETYPE_CPTROOM);
				jitem.put("leaf", subCptroomLeaf);
				jitem.put("allowChild", true);
				jitem.put("allowDelete", true);

				if (chkType.indexOf(UIConstants.NODETYPE_CPTROOM) > -1) {
					jitem.put("checked", false);
				}
				jitem.put("qtip", "机房负责人:" + subCptroom.getManager()
						+ "<br \\/>机房电话:" + subCptroom.getPhone()
						+ "<br \\/>备注:" + subCptroom.getNotes());
				jitem.put("qtipTitle", subCptroom.getRoomname());
				json.put(jitem);
			}

		} catch (Exception ex) {
			BocoLog.error(this, "生成机房树图时报错：" + ex);
		}
		return json;
	}
}
