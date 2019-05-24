package com.boco.eoms.eva.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.eva.model.EvaOrg;
import com.boco.eoms.base.api.EOMSMgr;

/**
 * 
 * <p>
 * Title:考核静态方法类
 * </p>
 * <p>
 * Description:考核静态方法类
 * </p>
 * <p>
 * Date:2008-12-9 下午07:10:44
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public class EvaStaticMethod {

	/**
	 * 根据周期获得周期内的第一天
	 * 
	 * @param cycle
	 *            周期
	 * @param currentDateTime
	 *            当前时间，格式为yyyy-MM-dd
	 * @return
	 */
	public static String getStartTimeByCycle(String cycle,
			String currentDateTime) {
		String startTime = currentDateTime;
		if (EvaConstants.CYCLE_YEAR.equals(cycle)) {
			int year = Integer.parseInt(currentDateTime.substring(0, 4));
			Date date = DateTimeUtil.getFirstDayOfYear(year);
			startTime = DateTimeUtil.dateToString(date);
		} else if (EvaConstants.CYCLE_QUARTER.equals(cycle)) {
			int year = Integer.parseInt(currentDateTime.substring(0, 4));
			int month = Integer.parseInt(currentDateTime.substring(5, 7));
			int quarter = 0;
			if (month <= 3) {
				quarter = 1;
			} else if (month <= 6) {
				quarter = 2;
			} else if (month <= 9) {
				quarter = 3;
			} else if (month <= 12) {
				quarter = 4;
			}
			Date date = DateTimeUtil.getFirstDayOfQuarter(year, quarter);
			startTime = DateTimeUtil.dateToString(date);
		} else if (EvaConstants.CYCLE_MONTH.equals(cycle)) {
			int year = Integer.parseInt(currentDateTime.substring(0, 4));
			int month = Integer.parseInt(currentDateTime.substring(5, 7));
			Date date = DateTimeUtil.getFirstDayOfMonth(year, month);
			startTime = DateTimeUtil.dateToString(date);
		} else if (EvaConstants.CYCLE_WEEK.equals(cycle)) {
			Date currentDate = DateTimeUtil.stringToDate(currentDateTime);
			Date date = DateTimeUtil.getFirstDayOfWeek(currentDate);
			startTime = DateTimeUtil.dateToString(date);
		}
		return startTime;
	}

	/**
	 * 根据周期获得周期内的最后一天
	 * 
	 * @param cycle
	 * @return
	 */
	public static String getEndTimeByCycle(String cycle, String currentDateTime) {
		String endTime = currentDateTime;
		if (EvaConstants.CYCLE_YEAR.equals(cycle)) {
			int year = Integer.parseInt(currentDateTime.substring(0, 4));
			Date date = DateTimeUtil.getLastDayOfYear(year);
			endTime = DateTimeUtil.dateToString(date);
		} else if (EvaConstants.CYCLE_QUARTER.equals(cycle)) {
			int year = Integer.parseInt(currentDateTime.substring(0, 4));
			int month = Integer.parseInt(currentDateTime.substring(5, 7));
			int quarter = 0;
			if (month <= 3) {
				quarter = 1;
			} else if (month <= 6) {
				quarter = 2;
			} else if (month <= 9) {
				quarter = 3;
			} else if (month <= 12) {
				quarter = 4;
			}
			Date date = DateTimeUtil.getLastDayOfQuarter(year, quarter);
			endTime = DateTimeUtil.dateToString(date);
		} else if (EvaConstants.CYCLE_MONTH.equals(cycle)) {
			int year = Integer.parseInt(currentDateTime.substring(0, 4));
			int month = Integer.parseInt(currentDateTime.substring(5, 7));
			Date date = DateTimeUtil.getLastDayOfMonth(year, month);
			endTime = DateTimeUtil.dateToString(date);
		} else if (EvaConstants.CYCLE_WEEK.equals(cycle)) {
			Date currentDate = DateTimeUtil.stringToDate(currentDateTime);
			Date date = DateTimeUtil.getLastDayOfWeek(currentDate);
			endTime = DateTimeUtil.dateToString(date);
		}
		return endTime;
	}

	/**
	 * 根据orgId返回orgName
	 * 
	 * @param orgId
	 *            组织Id
	 * @param orgType
	 *            组织类型
	 * @return
	 */
	public static String orgId2Name(String orgId, String orgType)
			throws DictDAOException {
		String orgName = "";
		// 若为角色则显示角色名称
		if (EvaConstants.ORG_SUBROLE.equals(orgType)) {
			ITawSystemSubRoleManager subRoleMgr = (ITawSystemSubRoleManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemSubRoleManager");
			return subRoleMgr.getTawSystemSubRole(orgId).getSubRoleName();

		}
		// 若为用户则显示用户名称
		else if (EvaConstants.ORG_USER.equals(orgType)) {
			ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
					.getInstance().getBean("itawSystemUserManager");
			return userMgr.id2Name(orgId);
		}
		// 若为部门则显示部门名称
		else if (EvaConstants.ORG_DEPT.equals(orgType)) {
			ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			return deptMgr.id2Name(orgId);
		}
		return orgName;
	}

	/**
	 * 组织名称（暂时停用）
	 * 
	 * @return 组织名称
	 */
	public static String getOrgName(String orgId, String orgType) {
		String orgName = "";
		// 若为角色则显示角色名称
		if (StaticVariable.PRIV_ASSIGNTYPE_ROLE.equals(orgType)) {
			ITawSystemSubRoleManager roleMgr = (ITawSystemSubRoleManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemSubRoleManager");
			return roleMgr.getTawSystemSubRole(orgId).getSubRoleName();

		}
		// 若为用户则显示用户名称
		else if (StaticVariable.PRIV_ASSIGNTYPE_USER.equals(orgType)) {
			ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder
					.getInstance().getBean("itawSystemUserManager");
			return userMgr.getUserByuserid(orgId).getUsername();
		}
		// 若为部门则显示部门名称
		else if (StaticVariable.PRIV_ASSIGNTYPE_DEPT.equals(orgType)) {
			ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemDeptManager");
			return deptMgr.getDeptinfobydeptid(orgId, "0").getDeptName();
		}
		return orgName;
	}

	/**
	 * 从json中取发布组织列表
	 * 
	 * @param orgs
	 *            json串
	 * @return 部门列表
	 */
	public static List jsonOrg2Orgs(String orgs) {
		JSONArray jsonOrgs = JSONArray.fromString(orgs);
		ITawSystemDeptManager deptMgr = (ITawSystemDeptManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemDeptManager");
		List orgList = new ArrayList();
		for (Iterator it = jsonOrgs.iterator(); it.hasNext();) {
			JSONObject org = (JSONObject) it.next();
			// 发布组织id
			String id = org.getString(UIConstants.JSON_ID);
			// 节点类型
			String nodeType = org.getString(UIConstants.JSON_NODETYPE);
			// 限制发布范围
			if (EvaConstants.ORG_DEPT.equals(nodeType)
					|| StaticVariable.PRIV_ASSIGNTYPE_DEPT.equals(nodeType)) {
				// 获取所有子部门（包括本部门信息）
				List depts = EOMSMgr.getOrgMgrs().getDeptMgr().listAllSubDept(
						id);
				for (int i = 0; i < depts.size(); i++) {
					TawSystemDept dept = (TawSystemDept) depts.get(i);
					orgList.add(new EvaOrg(dept.getDeptId(),
							StaticVariable.PRIV_ASSIGNTYPE_DEPT));
				}
			} else if (EvaConstants.ORG_SUBROLE.equals(nodeType)
					|| StaticVariable.PRIV_ASSIGNTYPE_ROLE.equals(nodeType)) {
				orgList
						.add(new EvaOrg(id, StaticVariable.PRIV_ASSIGNTYPE_ROLE));
			} else if (EvaConstants.ORG_USER.equals(nodeType)
					|| StaticVariable.PRIV_ASSIGNTYPE_USER.equals(nodeType)) {
				orgList
						.add(new EvaOrg(id, StaticVariable.PRIV_ASSIGNTYPE_USER));
			}
		}
		return orgList;
	}

	/**
	 * 将取出的组织列表转换成json的字符串
	 * 
	 * @param threadPermimissionOrgs
	 *            信息权限列表
	 * @return 信息权限转换后的字符串
	 */
	public static String getOrgList(List orgs) {
		String jsonOrgs = "[]";
		if (null != orgs && !orgs.isEmpty()) {
			JSONArray jsonRoot = new JSONArray();
			for (Iterator it = orgs.iterator(); it.hasNext();) {
				EvaOrg org = (EvaOrg) it.next();
				int orgtype = StaticMethod.null2int(org.getOrgType());
				JSONObject item = new JSONObject();
				// 构造json对象
				item.put(UIConstants.JSON_ID, org.getOrgId());
				item.put(UIConstants.JSON_NAME, EvaStaticMethod.getOrgName(org
						.getOrgId(), Integer.toString(orgtype)));
				// 判断发布对象类型
				String orgtypestr = "";
				switch (orgtype) {
				case 1:
					orgtypestr = "dept";
					break;
				case 2:
					orgtypestr = "user";
					break;
				}
				item.put(UIConstants.JSON_NODETYPE, orgtypestr);
				jsonRoot.put(item);
			}
			jsonOrgs = jsonRoot.toString();
		}
		return jsonOrgs;
	}

	public static void main(String[] args) {
		System.out.println("本年第一天："
				+ getStartTimeByCycle(EvaConstants.CYCLE_YEAR, DateTimeUtil
						.getCurrentDateTime(EvaConstants.DATE_FORMAT)));
		System.out.println("本季度第一天："
				+ getStartTimeByCycle(EvaConstants.CYCLE_QUARTER, DateTimeUtil
						.getCurrentDateTime(EvaConstants.DATE_FORMAT)));
		System.out.println("本月第一天："
				+ getStartTimeByCycle(EvaConstants.CYCLE_MONTH, DateTimeUtil
						.getCurrentDateTime(EvaConstants.DATE_FORMAT)));
		System.out.println("本周第一天："
				+ getStartTimeByCycle(EvaConstants.CYCLE_WEEK, DateTimeUtil
						.getCurrentDateTime(EvaConstants.DATE_FORMAT)));
		System.out.println("本年最后一天："
				+ getEndTimeByCycle(EvaConstants.CYCLE_YEAR, DateTimeUtil
						.getCurrentDateTime(EvaConstants.DATE_FORMAT)));
		System.out.println("本季度最后一天："
				+ getEndTimeByCycle(EvaConstants.CYCLE_QUARTER, DateTimeUtil
						.getCurrentDateTime(EvaConstants.DATE_FORMAT)));
		System.out.println("本月最后一天："
				+ getEndTimeByCycle(EvaConstants.CYCLE_MONTH, DateTimeUtil
						.getCurrentDateTime(EvaConstants.DATE_FORMAT)));
		System.out.println("本周最后一天："
				+ getEndTimeByCycle(EvaConstants.CYCLE_WEEK, DateTimeUtil
						.getCurrentDateTime(EvaConstants.DATE_FORMAT)));
	}
}
