package com.boco.eoms.commons.system.user.test.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.system.user.service.IUserMgr;

/**
 * <p>
 * Title:用户管理测试用例
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Nov 14, 2008 9:23:18 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */

public class UserMgrImplTest extends ConsoleTestCase {

	/**
	 * 用户管理
	 */
	private IUserMgr userMgr;

	private ITawSystemUserManager tawSystemUserManager;

	private ITawSystemRoleManager tawSystemRoleManager;

	private ITawSystemSubRoleManager tawSystemSubRoleManager;

	private ITawSystemUserRefRoleManager tawSystemUserRefRoleManager;

	/**
	 * 部门
	 */
	private ITawSystemDeptManager tawSystemDeptManager;

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		this.userMgr = (IUserMgr) applicationContext.getBean("UserMgrImpl");
		this.tawSystemUserManager = (ITawSystemUserManager) applicationContext
				.getBean("itawSystemUserManager");
		this.tawSystemRoleManager = (ITawSystemRoleManager) applicationContext
				.getBean("ItawSystemRoleManager");
		this.tawSystemSubRoleManager = (ITawSystemSubRoleManager) applicationContext
				.getBean("ItawSystemSubRoleManager");

		this.tawSystemUserRefRoleManager = (ITawSystemUserRefRoleManager) applicationContext
				.getBean("itawSystemUserRefRoleManager");

		this.tawSystemDeptManager = (ITawSystemDeptManager) applicationContext
				.getBean("ItawSystemDeptSaveManagerFlush");
	}

	/**
	 * 取虚拟组组长
	 * 
	 * @see com.boco.eoms.commons.system.user.service.IUserMgr#getLeaderOfGroup(String)
	 */
	public void testGetLeaderOfGroup() {
		// 构造两个用户，user1=虚拟组长，user2=虚拟组员
		TawSystemUser user1 = new TawSystemUser();
		user1.setAccountExpired(false);
		user1.setAccountLocked(false);
		user1.setCredentialsExpired(false);
		user1.setDeleted(Constants.NOT_DELETED_FLAG);
		user1.setDeptid("1");
		user1.setEnabled(true);
		user1.setPassword("1");
		user1.setUsername("虚拟组长");
		user1.setUserid("leaderofgroup");
		TawSystemUser user2 = new TawSystemUser();
		user2.setAccountExpired(false);
		user2.setAccountLocked(false);
		user2.setCredentialsExpired(false);
		user2.setDeleted(Constants.NOT_DELETED_FLAG);
		user2.setDeptid("1");
		user2.setEnabled(true);
		user2.setPassword("1");
		user2.setUsername("虚拟组员");
		user2.setUserid("memberofgroup");
		tawSystemUserManager.saveTawSystemUser(user1);
		tawSystemUserManager.saveTawSystemUser(user2);
		// 构造虚拟组
		TawSystemRole group = new TawSystemRole();
		group.setDeleted(new Integer(Constants.NOT_DELETED_FLAG));
		group.setDeptId("1");
		group.setParentId(1);
		group.setLeaf(new Integer(Constants.LEAF));
		group.setRoleName("测试虚拟组");
		group.setRoleTypeId(Long.parseLong(RoleConstants.ROLETYPE_VIRTUAL));
		tawSystemRoleManager.saveTawSystemRole(group);

		// 构造虚拟组的子组
		TawSystemSubRole subGroup = new TawSystemSubRole();
		subGroup.setArea("1");
		subGroup.setDeleted(Constants.NOT_DELETED_FLAG);
		subGroup.setDeptId("1");
		subGroup.setParentId(1);
		subGroup.setRoleId(group.getRoleId());
		subGroup.setLogo("");
		tawSystemSubRoleManager.saveTawSystemSubRole(subGroup);

		// 构造人员与虚拟组关系,ref1=组长，ref2=组员
		TawSystemUserRefRole ref1 = new TawSystemUserRefRole();
		ref1.setGroupType(RoleConstants.groupType_leader);
		ref1.setUserid("leaderofgroup");
		ref1.setRoleid(group.getRoleId() + "");
		ref1.setRoleType(RoleConstants.ROLETYPE_VIRTUAL);
		ref1.setSubRoleid(subGroup.getId());

		TawSystemUserRefRole ref2 = new TawSystemUserRefRole();
		ref2.setGroupType(RoleConstants.groupType_common);
		ref2.setUserid("memberofgroup");
		ref2.setRoleid(group.getRoleId() + "");
		ref2.setRoleType(RoleConstants.ROLETYPE_VIRTUAL);
		ref2.setSubRoleid(subGroup.getId());

		tawSystemUserRefRoleManager.saveTawSystemUserRefRole(ref1);
		tawSystemUserRefRoleManager.saveTawSystemUserRefRole(ref2);
		// 验证取出的是否为虚拟组长
		TawSystemUser leader = userMgr.getLeaderOfGroup(subGroup.getId());
		assertNotNull(leader);
		assertEquals(leader.getUserid(), "leaderofgroup");

	}

	/**
	 * 根据用户名、密码取用户
	 * 
	 * @see com.boco.eoms.commons.system.user.service.IUserMgr#getUser(String,
	 *      String)
	 */
	public void testGetUserStringString() {
		// 构造两个用户，user1=虚拟组长，user2=虚拟组员
		TawSystemUser user1 = new TawSystemUser();
		user1.setAccountExpired(false);
		user1.setAccountLocked(false);
		user1.setCredentialsExpired(false);
		user1.setDeleted(Constants.NOT_DELETED_FLAG);
		user1.setDeptid("1");
		user1.setEnabled(true);
		final String md5Passwd = new Md5PasswordEncoder().encodePassword("1",
				new String());
		user1.setPassword(md5Passwd);
		user1.setUsername("虚拟组长");
		user1.setUserid("leaderofgroup");
		tawSystemUserManager.saveTawSystemUser(user1);
		// 验证取出的是否为user1
		TawSystemUser user = userMgr.getUser("leaderofgroup", "1");
		assertNotNull(user);
		assertEquals(user.getUserid(), "leaderofgroup");

	}

	/**
	 * 根据用户名取用户
	 * 
	 * @see com.boco.eoms.commons.system.user.service.IUserMgr#getUser(String)
	 * 
	 */
	public void testGetUserString() {
		// 构造两个用户，user1=虚拟组长，user2=虚拟组员
		TawSystemUser user1 = new TawSystemUser();
		user1.setAccountExpired(false);
		user1.setAccountLocked(false);
		user1.setCredentialsExpired(false);
		user1.setDeleted(Constants.NOT_DELETED_FLAG);
		user1.setDeptid("1");
		user1.setEnabled(true);
		final String md5Passwd = new Md5PasswordEncoder().encodePassword("1",
				new String());
		user1.setPassword(md5Passwd);
		user1.setUsername("虚拟组长");
		user1.setUserid("leaderofgroup");
		tawSystemUserManager.saveTawSystemUser(user1);
		// 验证取出的是否为user1
		TawSystemUser user = userMgr.getUser("leaderofgroup");
		assertNotNull(user);
		assertEquals(user.getUserid(), "leaderofgroup");
	}

	/**
	 * 分页取用户
	 * 
	 * @see com.boco.eoms.commons.system.user.service.IUserMgr#mapUsers(Integer,
	 *      Integer, String)
	 * 
	 */
	public void testMapUsers() {
		// 模拟三个用户，fax=1818181818
		TawSystemUser user1 = new TawSystemUser();
		user1.setAccountExpired(false);
		user1.setAccountLocked(false);
		user1.setCredentialsExpired(false);
		user1.setDeleted(Constants.NOT_DELETED_FLAG);
		user1.setDeptid("1");
		user1.setEnabled(true);
		user1.setPassword("1");
		user1.setUsername("虚拟组长");
		user1.setUserid("leaderofgroup");
		user1.setFax("1818181818");
		TawSystemUser user2 = new TawSystemUser();
		user2.setAccountExpired(false);
		user2.setAccountLocked(false);
		user2.setCredentialsExpired(false);
		user2.setDeleted(Constants.NOT_DELETED_FLAG);
		user2.setDeptid("1");
		user2.setEnabled(true);
		user2.setPassword("1");
		user2.setUsername("虚拟组员");
		user2.setUserid("memberofgroup1");
		user2.setFax("1818181818");
		TawSystemUser user3 = new TawSystemUser();
		user3.setAccountExpired(false);
		user3.setAccountLocked(false);
		user3.setCredentialsExpired(false);
		user3.setDeleted(Constants.NOT_DELETED_FLAG);
		user3.setDeptid("1");
		user3.setEnabled(true);
		user3.setPassword("1");
		user3.setUsername("虚拟组员");
		user3.setUserid("memberofgroup2");
		user3.setFax("1818181818");
		tawSystemUserManager.saveTawSystemUser(user1);
		tawSystemUserManager.saveTawSystemUser(user2);
		tawSystemUserManager.saveTawSystemUser(user3);
		// 分页取用户
		Map map = this.userMgr.mapUser(new Integer(0), new Integer(2),
				" fax='1818181818'");
		List list = (List) map.get("result");
		Integer total = (Integer) map.get("total");
		assertNotNull(list);
		assertEquals(total, new Integer(3));
		assertEquals(list.size(), 2);
	}

	/**
	 * 用户是否存在
	 * 
	 * @see com.boco.eoms.commons.system.user.service.IUserMgr#isExistUser(String)
	 */
	public void testIsExistUser() {
		// 模拟一个用户
		TawSystemUser user3 = new TawSystemUser();
		user3.setAccountExpired(false);
		user3.setAccountLocked(false);
		user3.setCredentialsExpired(false);
		user3.setDeleted(Constants.NOT_DELETED_FLAG);
		user3.setDeptid("1");
		user3.setEnabled(true);
		user3.setPassword("1");
		user3.setUsername("虚拟组员");
		user3.setUserid("memberofgroup1");
		user3.setFax("1818181818");
		tawSystemUserManager.saveTawSystemUser(user3);

		assertEquals(this.userMgr.isExistUser("memberofgroup1"), true);
		assertEquals(this.userMgr.isExistUser("1132321312"), false);
	}

	/**
	 * 某个子角色是否拥有某用户
	 * 
	 * @see com.boco.eoms.commons.system.user.service.IUserMgr#isSubroleHasUser(String,
	 *      String)
	 * 
	 */
	public void testIsSubroleHasUser() {

		// 构造两个用
		TawSystemUser user1 = new TawSystemUser();
		user1.setAccountExpired(false);
		user1.setAccountLocked(false);
		user1.setCredentialsExpired(false);
		user1.setDeleted(Constants.NOT_DELETED_FLAG);
		user1.setDeptid("1");
		user1.setEnabled(true);
		user1.setPassword("1");
		user1.setUsername("虚拟组长");
		user1.setUserid("leaderofgroup");
		TawSystemUser user2 = new TawSystemUser();
		user2.setAccountExpired(false);
		user2.setAccountLocked(false);
		user2.setCredentialsExpired(false);
		user2.setDeleted(Constants.NOT_DELETED_FLAG);
		user2.setDeptid("1");
		user2.setEnabled(true);
		user2.setPassword("1");
		user2.setUsername("虚拟组员");
		user2.setUserid("memberofgroup");
		tawSystemUserManager.saveTawSystemUser(user1);
		tawSystemUserManager.saveTawSystemUser(user2);
		// 构造虚拟组
		TawSystemRole role = new TawSystemRole();
		role.setDeleted(new Integer(Constants.NOT_DELETED_FLAG));
		role.setDeptId("1");
		role.setParentId(1);
		role.setLeaf(new Integer(Constants.LEAF));
		role.setRoleName("测试虚拟组");
		role.setRoleTypeId(Long.parseLong(RoleConstants.ROLETYPE_ROLE));
		tawSystemRoleManager.saveTawSystemRole(role);

		// 构造虚拟组的子组
		TawSystemSubRole subrole = new TawSystemSubRole();
		subrole.setArea("1");
		subrole.setDeleted(Constants.NOT_DELETED_FLAG);
		subrole.setDeptId("1");
		subrole.setParentId(1);
		subrole.setRoleId(role.getRoleId());
		subrole.setLogo("");
		tawSystemSubRoleManager.saveTawSystemSubRole(subrole);

		// 构造人员与虚拟组关系,ref1=组长，ref2=组员
		TawSystemUserRefRole ref1 = new TawSystemUserRefRole();
		ref1.setGroupType(RoleConstants.groupType_leader);
		ref1.setUserid("leaderofgroup");
		ref1.setRoleid(subrole.getRoleId() + "");
		ref1.setRoleType(RoleConstants.ROLETYPE_SUBROLE);
		ref1.setSubRoleid(subrole.getId());

		TawSystemUserRefRole ref2 = new TawSystemUserRefRole();
		ref2.setGroupType(RoleConstants.groupType_common);
		ref2.setUserid("memberofgroup");
		ref2.setRoleid(role.getRoleId() + "");
		ref2.setRoleType(RoleConstants.ROLETYPE_SUBROLE);
		ref2.setSubRoleid(subrole.getId());

		tawSystemUserRefRoleManager.saveTawSystemUserRefRole(ref1);
		tawSystemUserRefRoleManager.saveTawSystemUserRefRole(ref2);

		// 验证subrole.getId()是否有用户leaderofgroup1

		assertTrue(this.userMgr.isSubroleHasUser("leaderofgroup", subrole
				.getId()));
		assertTrue(this.userMgr.isSubroleHasUser("memberofgroup", subrole
				.getId()));
		assertFalse(this.userMgr.isSubroleHasUser("noleaderofgroup", subrole
				.getId()));
	}

	/**
	 * 某部门的负责人
	 * 
	 * @see com.boco.eoms.commons.system.user.service.IUserMgr#getLeaderOfDept(String)
	 */
	public void testGetLeaderOfDept() {
		// 模拟部门负责人
		TawSystemUser user2 = new TawSystemUser();
		user2.setAccountExpired(false);
		user2.setAccountLocked(false);
		user2.setCredentialsExpired(false);
		user2.setDeleted(Constants.NOT_DELETED_FLAG);
		user2.setDeptid("1");
		user2.setEnabled(true);
		user2.setPassword("1");
		user2.setUsername("虚拟组员");
		user2.setUserid("memberofgroup");
		tawSystemUserManager.saveTawSystemUser(user2);
		// 模拟部门
		TawSystemDept dept = new TawSystemDept();
		dept.setDeleted(Constants.NOT_DELETED_FLAG);
		dept.setDeptemail("aa@boco.com.cn");
		dept.setDeptfax("123");
		// dept.setDeptId(deptId);
		dept.setDeptmanager("memberofgroup");
		dept.setDeptmobile("13810005432");
		dept.setDeptName("admin");
		dept.setDeptphone("88157289");
		dept.setDeptType("1");
		dept.setLeaf("0");
		dept.setDeptId("usermgrimpltest");
		tawSystemDeptManager.saveTawSystemDept(dept);
		TawSystemUser user = this.userMgr.getLeaderOfDept("usermgrimpltest");
		assertNotNull(user);
		assertEquals(user.getUserid(), "memberofgroup");

	}

	/**
	 * 取全部用户
	 * 
	 * @see com.boco.eoms.commons.system.user.service.IUserMgr#listUser(String)
	 * 
	 */
	public void testListUser() {
		// 构造两个用户
		TawSystemUser user1 = new TawSystemUser();
		user1.setAccountExpired(false);
		user1.setAccountLocked(false);
		user1.setCredentialsExpired(false);
		user1.setDeleted(Constants.NOT_DELETED_FLAG);
		user1.setDeptid("1");
		user1.setEnabled(true);
		user1.setPassword("1");
		user1.setUsername("虚拟组长");
		user1.setFax("18181818181818");
		user1.setUserid("leaderofgroup");
		TawSystemUser user2 = new TawSystemUser();
		user2.setAccountExpired(false);
		user2.setAccountLocked(false);
		user2.setCredentialsExpired(false);
		user2.setDeleted(Constants.NOT_DELETED_FLAG);
		user2.setDeptid("1");
		user2.setEnabled(true);
		user2.setPassword("1");
		user2.setUsername("虚拟组员");
		user2.setUserid("memberofgroup");
		user2.setFax("18181818181818");
		tawSystemUserManager.saveTawSystemUser(user1);
		tawSystemUserManager.saveTawSystemUser(user2);

		List list = this.userMgr.listUser("fax='18181818181818'");
		assertNotNull(list);
		assertEquals(list.size(), 2);
		for (Iterator it = list.iterator(); it.hasNext();) {
			TawSystemUser user = (TawSystemUser) it.next();
			assertTrue("leaderofgroup".equals(user.getUserid())
					|| "memberofgroup".equals(user.getUserid()));
		}
	}

	/**
	 * 
	 * 取某地市下的用户
	 * 
	 * @see com.boco.eoms.commons.system.user.service.IUserMgr#listUserOfArea(String)
	 */
	public void testListUserOfArea() {
		// 模拟部门
		TawSystemDept dept = new TawSystemDept();
		dept.setDeleted(Constants.NOT_DELETED_FLAG);
		dept.setDeptemail("aa@boco.com.cn");
		dept.setDeptfax("123");
		// dept.setDeptId(deptId);
		dept.setDeptmanager("memberofgroup");
		dept.setDeptmobile("13810005432");
		dept.setDeptName("admin");
		dept.setDeptphone("88157289");
		dept.setDeptType("1");
		dept.setLeaf("0");
		dept.setAreaid("usermgrimplareaidtest");
		dept.setDeptId("usermgrimpltest");
		tawSystemDeptManager.saveTawSystemDept(dept);
		// 构造两个用户
		TawSystemUser user1 = new TawSystemUser();
		user1.setAccountExpired(false);
		user1.setAccountLocked(false);
		user1.setCredentialsExpired(false);
		user1.setDeleted(Constants.NOT_DELETED_FLAG);
		user1.setDeptid("usermgrimpltest");
		user1.setEnabled(true);
		user1.setPassword("1");
		user1.setUsername("虚拟组长");
		user1.setFax("18181818181818");
		user1.setUserid("leaderofgroup");
		TawSystemUser user2 = new TawSystemUser();
		user2.setAccountExpired(false);
		user2.setAccountLocked(false);
		user2.setCredentialsExpired(false);
		user2.setDeleted(Constants.NOT_DELETED_FLAG);
		user2.setDeptid("usermgrimpltest");
		user2.setEnabled(true);
		user2.setPassword("1");
		user2.setUsername("虚拟组员");
		user2.setUserid("memberofgroup");
		user2.setFax("18181818181818");
		tawSystemUserManager.saveTawSystemUser(user1);
		tawSystemUserManager.saveTawSystemUser(user2);

		List users = userMgr.listUserOfArea("usermgrimplareaidtest");
		assertNotNull(users);
		assertEquals(2, users.size());
		for (Iterator it = users.iterator(); it.hasNext();) {
			TawSystemUser user = (TawSystemUser) it.next();
			assertTrue("leaderofgroup".equals(user.getUserid())
					|| "memberofgroup".equals(user.getUserid()));
		}
	}

	/**
	 * 取某部门下的用户
	 * 
	 * @see com.boco.eoms.commons.system.user.service.IUserMgr#listUserOfDept(String[])
	 */
	public void testListUserOfDept() {
		// 模拟部门
		TawSystemDept dept = new TawSystemDept();
		dept.setDeleted(Constants.NOT_DELETED_FLAG);
		dept.setDeptemail("aa@boco.com.cn");
		dept.setDeptfax("123");
		// dept.setDeptId(deptId);
		dept.setDeptmanager("memberofgroup");
		dept.setDeptmobile("13810005432");
		dept.setDeptName("admin");
		dept.setDeptphone("88157289");
		dept.setDeptType("1");
		dept.setLeaf("0");
		dept.setAreaid("usermgrimplareaidtest");
		dept.setDeptId("usermgrimpltest");
		tawSystemDeptManager.saveTawSystemDept(dept);
		// 构造两个用户
		TawSystemUser user1 = new TawSystemUser();
		user1.setAccountExpired(false);
		user1.setAccountLocked(false);
		user1.setCredentialsExpired(false);
		user1.setDeleted(Constants.NOT_DELETED_FLAG);
		user1.setDeptid("usermgrimpltest");
		user1.setEnabled(true);
		user1.setPassword("1");
		user1.setUsername("虚拟组长");
		user1.setFax("18181818181818");
		user1.setUserid("leaderofgroup");
		TawSystemUser user2 = new TawSystemUser();
		user2.setAccountExpired(false);
		user2.setAccountLocked(false);
		user2.setCredentialsExpired(false);
		user2.setDeleted(Constants.NOT_DELETED_FLAG);
		user2.setDeptid("usermgrimpltest");
		user2.setEnabled(true);
		user2.setPassword("1");
		user2.setUsername("虚拟组员");
		user2.setUserid("memberofgroup");
		user2.setFax("18181818181818");
		tawSystemUserManager.saveTawSystemUser(user1);
		tawSystemUserManager.saveTawSystemUser(user2);

		List users = userMgr.listUserOfDept(new String[] {
				"usermgrimpltest", "no" });

		assertNotNull(users);
		assertEquals(users.size(), 2);
		for (Iterator it = users.iterator(); it.hasNext();) {
			TawSystemUser user = (TawSystemUser) it.next();
			assertTrue("leaderofgroup".equals(user.getUserid())
					|| "memberofgroup".equals(user.getUserid()));
		}

	}

	/**
	 * 取机房下的用户
	 * 
	 * @see com.boco.eoms.commons.system.user.service.IUserMgr#listUserOfRoom(String[])
	 * 
	 */
	public void testListUserOfRoom() {

		// 构造两个用户
		TawSystemUser user1 = new TawSystemUser();
		user1.setAccountExpired(false);
		user1.setAccountLocked(false);
		user1.setCredentialsExpired(false);
		user1.setDeleted(Constants.NOT_DELETED_FLAG);
		user1.setDeptid("usermgrimpltest");
		user1.setEnabled(true);
		user1.setPassword("1");
		user1.setUsername("虚拟组长");
		user1.setFax("18181818181818");
		user1.setUserid("leaderofgroup");
		user1.setCptroomid("usermgrimplroomtest");
		TawSystemUser user2 = new TawSystemUser();
		user2.setAccountExpired(false);
		user2.setAccountLocked(false);
		user2.setCredentialsExpired(false);
		user2.setDeleted(Constants.NOT_DELETED_FLAG);
		user2.setDeptid("usermgrimpltest");
		user2.setEnabled(true);
		user2.setPassword("1");
		user2.setUsername("虚拟组员");
		user2.setUserid("memberofgroup");
		user2.setFax("18181818181818");
		user2.setCptroomid("usermgrimplroomtest");
		tawSystemUserManager.saveTawSystemUser(user1);
		tawSystemUserManager.saveTawSystemUser(user2);

		List users = userMgr.listUserOfRoom(new String[] {
				"usermgrimplroomtest", "no" });
		assertNotNull(users);
		assertEquals(users.size(), 2);
		for (Iterator it = users.iterator(); it.hasNext();) {
			TawSystemUser user = (TawSystemUser) it.next();
			assertTrue("leaderofgroup".equals(user.getUserid())
					|| "memberofgroup".equals(user.getUserid()));
		}
	}

	/**
	 * 取子角色下的用户
	 * 
	 * @see com.boco.eoms.commons.system.user.service.IUserMgr#listUserOfsubrole(String[])
	 */
	public void testListUserOfsubrole() {

		// 构造两个用户
		TawSystemUser user1 = new TawSystemUser();
		user1.setAccountExpired(false);
		user1.setAccountLocked(false);
		user1.setCredentialsExpired(false);
		user1.setDeleted(Constants.NOT_DELETED_FLAG);
		user1.setDeptid("usermgrimpltest");
		user1.setEnabled(true);
		user1.setPassword("1");
		user1.setUsername("虚拟组长");
		user1.setFax("18181818181818");
		user1.setUserid("leaderofgroup");
		user1.setCptroomid("usermgrimplroomtest");
		TawSystemUser user2 = new TawSystemUser();
		user2.setAccountExpired(false);
		user2.setAccountLocked(false);
		user2.setCredentialsExpired(false);
		user2.setDeleted(Constants.NOT_DELETED_FLAG);
		user2.setDeptid("usermgrimpltest");
		user2.setEnabled(true);
		user2.setPassword("1");
		user2.setUsername("虚拟组员");
		user2.setUserid("memberofgroup");
		user2.setFax("18181818181818");
		user2.setCptroomid("usermgrimplroomtest");
		tawSystemUserManager.saveTawSystemUser(user1);
		tawSystemUserManager.saveTawSystemUser(user2);

		// 构造大角色
		TawSystemRole role = new TawSystemRole();
		role.setDeleted(new Integer(Constants.NOT_DELETED_FLAG));
		role.setDeptId("1");
		role.setParentId(1);
		role.setLeaf(new Integer(Constants.LEAF));
		role.setRoleName("测试角色");
		role.setRoleTypeId(Long.parseLong(RoleConstants.ROLETYPE_ROLE));
		tawSystemRoleManager.saveTawSystemRole(role);
		// 构造大角色的子角色
		TawSystemSubRole subrole = new TawSystemSubRole();
		subrole.setArea("1");
		subrole.setDeleted(Constants.NOT_DELETED_FLAG);
		subrole.setDeptId("1");
		subrole.setParentId(1);
		subrole.setRoleId(role.getRoleId());
		subrole.setLogo("");
		tawSystemSubRoleManager.saveTawSystemSubRole(subrole);

		// 构造人员与虚拟组关系,ref1=组长，ref2=组员
		TawSystemUserRefRole ref1 = new TawSystemUserRefRole();
		ref1.setGroupType(RoleConstants.groupType_leader);
		ref1.setUserid("leaderofgroup");
		ref1.setRoleid(role.getRoleId() + "");
		ref1.setRoleType(RoleConstants.ROLETYPE_VIRTUAL);
		ref1.setSubRoleid(subrole.getId());

		TawSystemUserRefRole ref2 = new TawSystemUserRefRole();
		ref2.setGroupType(RoleConstants.groupType_common);
		ref2.setUserid("memberofgroup");
		ref2.setRoleid(role.getRoleId() + "");
		ref2.setRoleType(RoleConstants.ROLETYPE_VIRTUAL);
		ref2.setSubRoleid(subrole.getId());

		tawSystemUserRefRoleManager.saveTawSystemUserRefRole(ref1);
		tawSystemUserRefRoleManager.saveTawSystemUserRefRole(ref2);

		List users = userMgr
				.listUserOfsubrole(new String[] { subrole.getId() });
		assertNotNull(users);
		assertEquals(users.size(), 2);
		for (Iterator it = users.iterator(); it.hasNext();) {
			TawSystemUser user = (TawSystemUser) it.next();
			assertTrue("leaderofgroup".equals(user.getUserid())
					|| "memberofgroup".equals(user.getUserid()));
		}
	}

}
