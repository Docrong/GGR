package com.boco.eoms.commons.system.priv.test.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.priv.util.PrivConstants;
import com.boco.eoms.commons.system.priv.util.PrivMgrLocator;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.user.model.TawSystemUser;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Sep 2, 2008 10:44:43 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class PrivMgrImplTest extends ConsoleTestCase {

	/**
	 * log4j
	 */
	private static final Logger logger = Logger
			.getLogger(PrivMgrImplTest.class);


	public void testListModuleMenu() {
		String userId = "qujingbo";
		List operations = PrivMgrLocator.getPrivMgr().listOpertion(userId,
				PrivConstants.LIST_OPERATION_TYPE_MOUDLE_FUNCTION,
				StaticVariable.ROOT_NODE);
		// 取出权限不为空
		assertNotNull(operations);
		assertTrue(!operations.isEmpty());
		// 菜单方案中不应重复
		assertTrue(!isRepeat(operations));

	}

	public void testsave() throws Exception {

	}

	/**
	 * 测试取通过用户取其所属角色、部门的合并后的菜单方案
	 * 
	 */
	public void testListMenu() {
		List menus = PrivMgrLocator.getPrivMgr().listMenu("priv");
		// 取出菜单方案不为空
		assertNotNull(menus);
		assertTrue(!menus.isEmpty());
		// 菜单方案中不应重复
		assertTrue(!isRepeat(menus));
	}

	/**
	 * 判断列表中是否有重复菜单
	 * 
	 * @param list
	 *            列表
	 * @return
	 */
	private boolean isRepeat(List list) {
		for (int i = 0; i < list.size(); i++) {
			Object objecti = list.get(i);
			for (int j = 0; j < list.size(); j++) {
				// 不判断本身
				if (i == j) {
					continue;
				}
				Object objectj = list.get(j);
				// 有重复
				if (objecti.equals(objectj)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 测试取某角色、人员、部门菜单方案
	 * 
	 */
	public void testListMenuStringString() {
		List userMenus = PrivMgrLocator.getPrivMgr().listMenu("qujingbo",
				StaticVariable.PRIV_ASSIGNTYPE_USER);
		// 8a4282a61ae1a808011ae1b28a030008 生产任务处理角色(贵阳值班管理)
		List roleMenus = PrivMgrLocator.getPrivMgr().listMenu(
				"8a4282a61ae1a808011ae1b28a030008",
				StaticVariable.PRIV_ASSIGNTYPE_ROLE);
		List deptMenus = PrivMgrLocator.getPrivMgr().listMenu("1",
				StaticVariable.PRIV_ASSIGNTYPE_DEPT);
		// 取出菜单方案不为空
		assertNotNull(userMenus);
		assertTrue(!userMenus.isEmpty());
		assertNotNull(roleMenus);
		assertTrue(!roleMenus.isEmpty());
		// assertNotNull(deptMenus);
		// assertTrue(!deptMenus.isEmpty());
		// 菜单方案中不应重复
		assertTrue(!isRepeat(userMenus));
		assertTrue(!isRepeat(roleMenus));
		// assertTrue(!isRepeat(deptMenus));
	}

	/**
	 * 测度是否拥有某菜单方案
	 * 
	 */
	public void testHasAssigned() {
		assertTrue(PrivMgrLocator
				.getPrivMgr()
				.hasAssigned(
						"8aa081281c2643cd011c264eb12d0020,8aa081281c2643cd011c264e7acb001f",
						StaticVariable.PRIV_ASSIGNTYPE_ROLE, "40"));

		assertTrue(PrivMgrLocator.getPrivMgr().hasAssigned(
				"8aa081281c2643cd011c264eb12d0020",
				StaticVariable.PRIV_ASSIGNTYPE_ROLE, "40"));

		assertTrue(!PrivMgrLocator
				.getPrivMgr()
				.hasAssigned(
						"8aa081281c2643cd011c264eb12d0020,8aa081281c2643cd011c264e7acb001f",
						StaticVariable.PRIV_ASSIGNTYPE_ROLE, "40111"));

		assertTrue(PrivMgrLocator.getPrivMgr().hasAssigned("priv",
				StaticVariable.PRIV_ASSIGNTYPE_USER, "40"));

		assertTrue(!PrivMgrLocator.getPrivMgr().hasAssigned("priv",
				StaticVariable.PRIV_ASSIGNTYPE_USER, "42"));

		List roleIds = new ArrayList();
		TawSystemSubRole role1 = new TawSystemSubRole();
		role1.setId("8aa081281c2643cd011c264eb12d0020");
		TawSystemSubRole role2 = new TawSystemSubRole();
		role2.setId("8aa081281c2643cd011c264e7acb001f");
		roleIds.add(role1);
		roleIds.add(role2);
		assertTrue(PrivMgrLocator.getPrivMgr().hasAssigned(roleIds, "40"));

	}

	/**
	 * 测度某人是否拥有某权限
	 */
	public void testHasPriv() {
		String userId = "priv";
		String priv = "/WORKBENCH/INFOPUB/PERMISSION/THREADAUDIT";

		assertTrue(PrivMgrLocator.getPrivMgr().hasPriv(userId, priv));

	}

	public void testListUserByUrl() {
		String priv = "/WORKBENCH/INFOPUB/PERMISSION/THREADAUDIT";
		Set set = PrivMgrLocator.getPrivMgr().listUserByUrl(priv);
		for (Iterator it = set.iterator(); it.hasNext();) {
			System.out.println(((TawSystemUser) it.next()).getUserid());
		}
		assertNotNull(set);
	}

}
