package com.boco.eoms.commons.system.role.test.service;

import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.IRoleMgr;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;

/**
 * <p>
 * Title:角色管理api测试用例
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Nov 14, 2008 3:26:27 PM
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */

public class RoleMgrImplTest extends ConsoleTestCase {

    /**
     * 角色API
     */
    private IRoleMgr roleMgr;

    private ITawSystemUserManager tawSystemUserManager;

    private ITawSystemRoleManager tawSystemRoleManager;

    private ITawSystemSubRoleManager tawSystemSubRoleManager;

    private ITawSystemUserRefRoleManager tawSystemUserRefRoleManager;

    /**
     * 区域管理
     */
    private ITawSystemAreaManager tawSystemAreaManager;

    /**
     * 部门
     */
    private ITawSystemDeptManager tawSystemDeptManager;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpBeforeTransaction()
     */
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        this.roleMgr = (IRoleMgr) applicationContext.getBean("RoleMgrFlush");
        //this.roleMgr = (IRoleMgr) applicationContext.getBean("RoleMgrImpl");
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

        this.tawSystemAreaManager = (ITawSystemAreaManager) applicationContext
                .getBean("ItawSystemAreaManager");
    }

    /**
     * 取某大角色
     *
     * @see com.boco.eoms.commons.system.role.service.IRoleMgr#getRole(String)
     */
    public void testGetRole() {
        // 构造虚拟组
        TawSystemRole role = new TawSystemRole();
        role.setDeleted(new Integer(Constants.NOT_DELETED_FLAG));
        role.setDeptId("1");
        role.setParentId(1);
        role.setLeaf(new Integer(Constants.LEAF));
        role.setRoleName("测试虚拟组");
        role.setRoleTypeId(Long.parseLong(RoleConstants.ROLETYPE_VIRTUAL));
        tawSystemRoleManager.saveTawSystemRole(role);

        TawSystemRole savedRole = roleMgr.getRole(role.getRoleId() + "");
        assertNotNull(savedRole);
        assertEquals(savedRole.getRoleId(), role.getRoleId());
    }

    /**
     * 取某子角色所属的大角色
     *
     * @see com.boco.eoms.commons.system.role.service.IRoleMgr#getRoleBySubrole(String)
     */
    public void testGetRoleBySubrole() {
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

        TawSystemRole savedRole = roleMgr.getRoleBySubrole(subrole.getId());
        assertNotNull(savedRole);
        assertEquals(savedRole.getRoleId(), role.getRoleId());
    }

    /**
     * 返回大角色类型
     *
     * @see com.boco.eoms.commons.system.role.service.IRoleMgr#getRoleType(String)
     */
    public void testGetRoleType() {
        // 构造大角色
        TawSystemRole role = new TawSystemRole();
        role.setDeleted(new Integer(Constants.NOT_DELETED_FLAG));
        role.setDeptId("1");
        role.setParentId(1);
        role.setLeaf(new Integer(Constants.LEAF));
        role.setRoleName("测试角色");
        role.setRoleTypeId(Long.parseLong(RoleConstants.ROLETYPE_ROLE));
        tawSystemRoleManager.saveTawSystemRole(role);
        String type = roleMgr.getRoleType(role.getRoleId() + "");
        assertNotNull(type);
        assertEquals(role.getRoleTypeId() + "", type);
    }

    /**
     * 取某子角色
     *
     * @see com.boco.eoms.commons.system.role.service.IRoleMgr#getSubRole(String)
     */
    public void testGetSubRole() {
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
        TawSystemSubRole savedSubrole = roleMgr.getSubRole(subrole.getId());
        assertNotNull(savedSubrole);
        assertEquals(savedSubrole.getId(), subrole.getId());
    }

    /**
     * 判断是否为虚拟组
     *
     * @see com.boco.eoms.commons.system.role.service.IRoleMgr#isGroup(String)
     */
    public void testIsGroup() {
        // 构造虚拟组
        TawSystemRole group = new TawSystemRole();
        group.setDeleted(new Integer(Constants.NOT_DELETED_FLAG));
        group.setDeptId("1");
        group.setParentId(1);
        group.setLeaf(new Integer(Constants.LEAF));
        group.setRoleName("测试虚拟组");
        group.setRoleTypeId(Long.parseLong(RoleConstants.ROLETYPE_VIRTUAL));
        tawSystemRoleManager.saveTawSystemRole(group);

        assertTrue(roleMgr.isGroup(group.getRoleId() + ""));
        assertFalse(roleMgr.isGroup("1231312312"));
    }

    /**
     * 取角色的所属角色
     *
     * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listChildRole(String)
     */
    public void testListChildRole() {
        // 构造大角色
        TawSystemRole role = new TawSystemRole();
        role.setDeleted(new Integer(Constants.NOT_DELETED_FLAG));
        role.setDeptId("1");
        role.setParentId(1);
        role.setLeaf(new Integer(Constants.LEAF));
        role.setRoleName("测试角色");
        role.setRoleTypeId(Long.parseLong(RoleConstants.ROLETYPE_ROLE));
        tawSystemRoleManager.saveTawSystemRole(role);

        // 构造大角色
        TawSystemRole role1 = new TawSystemRole();
        role1.setDeleted(new Integer(Constants.NOT_DELETED_FLAG));
        role1.setDeptId("1");
        role1.setParentId(role.getRoleId());
        role1.setLeaf(new Integer(Constants.LEAF));
        role1.setRoleName("测试角色");
        role1.setRoleTypeId(Long.parseLong(RoleConstants.ROLETYPE_ROLE));
        tawSystemRoleManager.saveTawSystemRole(role1);

        List roles = roleMgr.listChildRole(role.getRoleId() + "");
        assertNotNull(roles);
        assertEquals(roles.size(), 1);
        TawSystemRole savedRole = (TawSystemRole) roles.iterator().next();
        assertEquals(savedRole.getRoleId(), role1.getRoleId());
    }

    /**
     * 获取subroleId（子角色）的大角色下所属的其他子角色
     *
     * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listOtherSubroleOfRole(String)
     */
    public void testListOtherSubroleOfRole() {
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
        subrole.setVersion("subrole");
        tawSystemSubRoleManager.saveTawSystemSubRole(subrole);

        // 构造大角色的子角色
        TawSystemSubRole subrole1 = new TawSystemSubRole();
        subrole1.setArea("2");
        subrole1.setDeleted(Constants.NOT_DELETED_FLAG);
        subrole1.setDeptId("2");
        subrole1.setParentId(1);
        subrole1.setRoleId(role.getRoleId());
        subrole1.setLogo("");
        subrole1.setVersion("subrole1");

        tawSystemSubRoleManager.saveTawSystemSubRole(subrole1);

        List subroles = roleMgr.listOtherSubroleOfRole(subrole.getId());

        assertNotNull(subroles);
        assertEquals(subroles.size(), 1);

        // 验证subrole1（子角色）的大角色下所属的其他子角色
        for (Iterator it = subroles.iterator(); it.hasNext(); ) {
            TawSystemSubRole item = (TawSystemSubRole) it.next();
            assertEquals(item.getVersion(), "subrole1");
        }

    }

    /**
     * 取某地域下的角色
     *
     * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listRoleOfArea(String)
     */
    public void testListRoleOfArea() {
        // 虚拟区域
        TawSystemArea area = new TawSystemArea();
        area.setAreaid("areaid");
        area.setAreaname("areaid");
        area.setCapital("true");
        area.setLeaf("0");
        area.setParentAreaid("-1");
        tawSystemAreaManager.saveTawSystemArea(area);

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
        TawSystemSubRole subrole1 = new TawSystemSubRole();
        subrole1.setArea(area.getAreaid());
        subrole1.setDeleted(Constants.NOT_DELETED_FLAG);
        subrole1.setDeptId("2");
        subrole1.setParentId(1);
        subrole1.setRoleId(role.getRoleId());
        subrole1.setLogo("");
        subrole1.setVersion("subrole1");
        tawSystemSubRoleManager.saveTawSystemSubRole(subrole1);

        // 断言取某地域下的角色
        List roles = this.roleMgr.listRoleOfArea(area.getAreaid());
        assertNotNull(roles);
        assertEquals(roles.size(), 1);
        TawSystemRole assertRole = (TawSystemRole) roles.iterator().next();
        assertEquals(assertRole.getRoleId(), role.getRoleId());
    }

    /**
     * 取用户的大角色
     *
     * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listRoleOfUser(String)
     */
    public void testListRoleOfUser() {
        // 构造大角色
        TawSystemRole role = new TawSystemRole();
        role.setDeleted(new Integer(Constants.NOT_DELETED_FLAG));
        role.setDeptId("1");
        role.setParentId(1);
        role.setLeaf(new Integer(Constants.LEAF));
        role.setRoleName("测试角色");
        role.setRoleTypeId(Long.parseLong(RoleConstants.ROLETYPE_ROLE));
        tawSystemRoleManager.saveTawSystemRole(role);

        // 构造大角色
        TawSystemRole role1 = new TawSystemRole();
        role1.setDeleted(new Integer(Constants.NOT_DELETED_FLAG));
        role1.setDeptId("1");
        role1.setParentId(1);
        role1.setLeaf(new Integer(Constants.LEAF));
        role1.setRoleName("测试角色");
        role1.setRoleTypeId(Long.parseLong(RoleConstants.ROLETYPE_ROLE));
        tawSystemRoleManager.saveTawSystemRole(role1);

        // 构造大角色的子角色
        TawSystemSubRole subrole1 = new TawSystemSubRole();
        subrole1.setArea("");
        subrole1.setDeleted(Constants.NOT_DELETED_FLAG);
        subrole1.setDeptId("2");
        subrole1.setParentId(1);
        subrole1.setRoleId(role.getRoleId());
        subrole1.setLogo("");
        subrole1.setVersion("subrole1");
        tawSystemSubRoleManager.saveTawSystemSubRole(subrole1);

        // 构造大角色的子角色
        TawSystemSubRole subrole = new TawSystemSubRole();
        subrole.setArea("");
        subrole.setDeleted(Constants.NOT_DELETED_FLAG);
        subrole.setDeptId("2");
        subrole.setParentId(1);
        subrole.setRoleId(role1.getRoleId());
        subrole.setLogo("");
        subrole.setVersion("subrole1");
        tawSystemSubRoleManager.saveTawSystemSubRole(subrole);

        // 构造两个用户，user1=虚拟组长
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
        tawSystemUserManager.saveTawSystemUser(user1);

        // 构造人员与虚拟组关系,ref1=组长
        TawSystemUserRefRole ref1 = new TawSystemUserRefRole();
        ref1.setGroupType(RoleConstants.groupType_leader);
        ref1.setUserid("leaderofgroup");
        ref1.setRoleid(role.getRoleId() + "");
        ref1.setRoleType(RoleConstants.ROLETYPE_VIRTUAL);
        ref1.setSubRoleid(subrole.getId());

        // 构造人员与虚拟组关系,ref1=组长
        TawSystemUserRefRole ref = new TawSystemUserRefRole();
        ref.setGroupType(RoleConstants.groupType_leader);
        ref.setUserid("leaderofgroup");
        ref.setRoleid(role.getRoleId() + "");
        ref.setRoleType(RoleConstants.ROLETYPE_VIRTUAL);
        ref.setSubRoleid(subrole1.getId());
        tawSystemUserRefRoleManager.saveTawSystemUserRefRole(ref1);
        tawSystemUserRefRoleManager.saveTawSystemUserRefRole(ref);

        List roles = roleMgr.listRoleOfUser("leaderofgroup");

        assertNotNull(roles);
        assertEquals(roles.size(), 2);
        for (Iterator it = roles.iterator(); it.hasNext(); ) {
            TawSystemRole assertRole = (TawSystemRole) it.next();
            assertTrue(assertRole.getRoleId() == role1.getRoleId()
                    || assertRole.getRoleId() == role.getRoleId());
        }

    }

    /**
     * 取某流程的角色
     *
     * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listRoleOfWorkflow(String)
     */
    public void testListRoleOfWorkflow() {
        // 构造大角色
        TawSystemRole role = new TawSystemRole();
        role.setDeleted(new Integer(Constants.NOT_DELETED_FLAG));
        role.setDeptId("1");
        role.setParentId(1);
        role.setLeaf(new Integer(Constants.LEAF));
        role.setRoleName("测试角色");
        role.setWorkflowFlag(new Integer(9999));
        role.setRoleTypeId(Long.parseLong(RoleConstants.flowRole));
        tawSystemRoleManager.saveTawSystemRole(role);

        List roles = this.roleMgr.listRoleOfWorkflow("9999");
        assertEquals(1, roles.size());
        TawSystemRole assertRole = (TawSystemRole) roles.iterator().next();
        assertNotNull(assertRole);
        assertEquals(assertRole.getRoleId(), role.getRoleId());
    }

    /**
     * 过滤子角色
     *
     * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listSubroleByCon(String,
     * String)
     */
    public void testListSubroleByCon() {

        // 构造大角色
        TawSystemRole role = new TawSystemRole();
        role.setDeleted(new Integer(Constants.NOT_DELETED_FLAG));
        role.setDeptId("1");
        role.setParentId(1);
        role.setLeaf(new Integer(Constants.LEAF));
        role.setRoleName("测试角色");
        role.setWorkflowFlag(new Integer(9999));
        role.setRoleTypeId(Long.parseLong(RoleConstants.ROLETYPE_ROLE));
        tawSystemRoleManager.saveTawSystemRole(role);

        // 构造大角色的子角色
        TawSystemSubRole subrole1 = new TawSystemSubRole();
        subrole1.setArea("");
        subrole1.setDeleted(Constants.NOT_DELETED_FLAG);
        subrole1.setDeptId("2");
        subrole1.setParentId(1);
        subrole1.setRoleId(role.getRoleId());
        subrole1.setLogo("");
        subrole1.setVersion("subrole1");
        tawSystemSubRoleManager.saveTawSystemSubRole(subrole1);

        // 构造大角色的子角色
        TawSystemSubRole subrole = new TawSystemSubRole();
        subrole.setArea("");
        subrole.setDeleted(Constants.NOT_DELETED_FLAG);
        subrole.setDeptId("2");
        subrole.setParentId(1);
        subrole.setRoleId(role.getRoleId());
        subrole.setLogo("");
        subrole.setVersion("subrole1");
        tawSystemSubRoleManager.saveTawSystemSubRole(subrole);

        List subroles = roleMgr.listSubroleByCon(role.getRoleId() + "", null);
        assertEquals(subroles.size(), 2);
        for (Iterator it = subroles.iterator(); it.hasNext(); ) {
            TawSystemSubRole item = (TawSystemSubRole) it.next();
            assertEquals(item.getRoleId(), role.getRoleId());
        }

    }

    /**
     * 取某地域下的大角色下的子角色
     *
     * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listSubrole(String,
     * String)
     */
    public void testListSubrole() {

        // 虚拟区域
        TawSystemArea area = new TawSystemArea();
        area.setAreaid("areaid");
        area.setAreaname("areaid");
        area.setCapital("true");
        area.setLeaf("0");
        area.setParentAreaid("-1");
        tawSystemAreaManager.saveTawSystemArea(area);

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
        TawSystemSubRole subrole1 = new TawSystemSubRole();
        subrole1.setArea(area.getAreaid());
        subrole1.setDeleted(Constants.NOT_DELETED_FLAG);
        subrole1.setDeptId("2");
        subrole1.setParentId(1);
        subrole1.setRoleId(role.getRoleId());
        subrole1.setLogo("");
        subrole1.setVersion("subrole1");
        tawSystemSubRoleManager.saveTawSystemSubRole(subrole1);

        List subroles = this.roleMgr.listSubrole(area.getAreaid(), role
                .getRoleId()
                + "");
        assertEquals(subroles.size(), 1);
        TawSystemSubRole assertSubrole = (TawSystemSubRole) subroles.iterator()
                .next();
        assertNotNull(assertSubrole);
        assertEquals(assertSubrole.getRoleId(), subrole1.getRoleId());

    }

    /**
     * 得到某部门下所有子角色
     *
     * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listSubroleOfDept(String,
     * String)
     */
    public void testListSubroleOfDeptUsers() {
        TawSystemDept dept = new TawSystemDept();
        dept.setDeleted(Constants.NOT_DELETED_FLAG);
        dept.setDeptemail("aa@boco.com.cn");
        dept.setDeptfax("123");
        // dept.setDeptId(deptId);
        dept.setDeptmanager("admin");
        dept.setDeptmobile("13810005432");
        dept.setDeptName("admin");
        dept.setDeptphone("88157289");
        dept.setDeptType("1");
        dept.setLeaf("0");
        dept.setDeptId("testdeptid");
        tawSystemDeptManager.saveTawSystemDept(dept);

        // 构造用户，
        TawSystemUser user1 = new TawSystemUser();
        user1.setAccountExpired(false);
        user1.setAccountLocked(false);
        user1.setCredentialsExpired(false);
        user1.setDeleted(Constants.NOT_DELETED_FLAG);
        user1.setDeptid(dept.getDeptId());
        user1.setEnabled(true);
        user1.setPassword("1");
        user1.setUsername("虚拟组长");
        user1.setUserid("user1");
        tawSystemUserManager.saveTawSystemUser(user1);

        // 构造用户
        TawSystemUser user2 = new TawSystemUser();
        user2.setAccountExpired(false);
        user2.setAccountLocked(false);
        user2.setCredentialsExpired(false);
        user2.setDeleted(Constants.NOT_DELETED_FLAG);
        user2.setDeptid(dept.getDeptId());
        user2.setEnabled(true);
        user2.setPassword("1");
        user2.setUsername("虚拟组长");
        user2.setUserid("user2");
        tawSystemUserManager.saveTawSystemUser(user2);

        // 构造大角色
        TawSystemRole role = new TawSystemRole();
        role.setDeleted(new Integer(Constants.NOT_DELETED_FLAG));
        role.setDeptId(dept.getDeptId());
        role.setParentId(1);
        role.setLeaf(new Integer(Constants.LEAF));
        role.setRoleName("测试角色");
        role.setRoleTypeId(Long.parseLong(RoleConstants.ROLETYPE_ROLE));
        tawSystemRoleManager.saveTawSystemRole(role);

        // 构造大角色的子角色
        TawSystemSubRole subrole1 = new TawSystemSubRole();
        subrole1.setDeleted(Constants.NOT_DELETED_FLAG);
        subrole1.setDeptId("2");
        subrole1.setParentId(1);
        subrole1.setRoleId(role.getRoleId());
        subrole1.setLogo("");
        subrole1.setVersion("subrole1");
        tawSystemSubRoleManager.saveTawSystemSubRole(subrole1);

        // 构造大角色的子角色
        TawSystemSubRole subrole2 = new TawSystemSubRole();
        subrole2.setDeleted(Constants.NOT_DELETED_FLAG);
        subrole2.setDeptId("2");
        subrole2.setParentId(1);
        subrole2.setRoleId(role.getRoleId());
        subrole2.setLogo("");
        subrole2.setVersion("subrole2");
        tawSystemSubRoleManager.saveTawSystemSubRole(subrole2);

        // 构造人员与虚拟组关系,ref1=组长，ref2=组员
        TawSystemUserRefRole ref1 = new TawSystemUserRefRole();
        ref1.setGroupType(RoleConstants.groupType_leader);
        ref1.setUserid("user1");
        ref1.setRoleid(role.getRoleId() + "");
        ref1.setRoleType(RoleConstants.ROLETYPE_VIRTUAL);
        ref1.setSubRoleid(subrole1.getId());

        TawSystemUserRefRole ref2 = new TawSystemUserRefRole();
        ref2.setGroupType(RoleConstants.groupType_common);
        ref2.setUserid("user2");
        ref2.setRoleid(role.getRoleId() + "");
        ref2.setRoleType(RoleConstants.ROLETYPE_SUBROLE);
        ref2.setSubRoleid(subrole1.getId());

        TawSystemUserRefRole ref3 = new TawSystemUserRefRole();
        ref3.setGroupType(RoleConstants.groupType_common);
        ref3.setUserid("user2");
        ref3.setRoleid(role.getRoleId() + "");
        ref3.setRoleType(RoleConstants.ROLETYPE_SUBROLE);
        ref3.setSubRoleid(subrole2.getId());

        tawSystemUserRefRoleManager.saveTawSystemUserRefRole(ref1);
        tawSystemUserRefRoleManager.saveTawSystemUserRefRole(ref2);
        tawSystemUserRefRoleManager.saveTawSystemUserRefRole(ref3);

        List subroles = roleMgr.listSubroleOfDeptUsers(dept.getDeptId(),
                RoleConstants.ROLETYPE_SUBROLE);
        assertEquals(subroles.size(), 2);

        for (Iterator it = subroles.iterator(); it.hasNext(); ) {
            TawSystemSubRole item = (TawSystemSubRole) it.next();
            assertTrue(item.getId().equals(subrole1.getId())
                    || item.getId().equals(subrole2.getId()));

        }

    }

    /**
     * 获取某人的所有子角色
     *
     * @see com.boco.eoms.commons.system.role.service.IRoleMgr#listSubroleOfUser(String,
     * String)
     */
    public void testListSubroleOfUser() {
        // 构造大角色
        TawSystemRole role = new TawSystemRole();
        role.setDeleted(new Integer(Constants.NOT_DELETED_FLAG));
        role.setDeptId("1");
        role.setParentId(1);
        role.setLeaf(new Integer(Constants.LEAF));
        role.setRoleName("测试角色");
        role.setRoleTypeId(Long.parseLong(RoleConstants.ROLETYPE_VIRTUAL));
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

        TawSystemSubRole subrole1 = new TawSystemSubRole();
        subrole1.setArea("1");
        subrole1.setDeleted(Constants.NOT_DELETED_FLAG);
        subrole1.setDeptId("1");
        subrole1.setParentId(1);
        subrole1.setRoleId(role.getRoleId());
        subrole1.setLogo("");
        tawSystemSubRoleManager.saveTawSystemSubRole(subrole1);

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
        tawSystemUserManager.saveTawSystemUser(user1);

        // 构造人员与虚拟组关系,ref1=组长，ref2=组员
        TawSystemUserRefRole ref1 = new TawSystemUserRefRole();
        ref1.setGroupType(RoleConstants.groupType_leader);
        ref1.setUserid("leaderofgroup");
        ref1.setRoleid(role.getRoleId() + "");
        ref1.setRoleType(RoleConstants.ROLETYPE_VIRTUAL);
        ref1.setSubRoleid(subrole.getId());

        TawSystemUserRefRole ref2 = new TawSystemUserRefRole();
        ref2.setGroupType(RoleConstants.groupType_common);
        ref2.setUserid("leaderofgroup");
        ref2.setRoleid(role.getRoleId() + "");
        ref2.setRoleType(RoleConstants.ROLETYPE_SUBROLE);
        ref2.setSubRoleid(subrole1.getId());

        tawSystemUserRefRoleManager.saveTawSystemUserRefRole(ref1);
        tawSystemUserRefRoleManager.saveTawSystemUserRefRole(ref2);

        // 验证虚拟组+子角色
        List subroles = roleMgr.listSubroleOfUser("leaderofgroup",
                RoleConstants.ROLETYPE_VIRTUAL + ","
                        + RoleConstants.ROLETYPE_SUBROLE);
        assertNotNull(subroles);
        assertEquals(subroles.size(), 2);
        for (Iterator it = subroles.iterator(); it.hasNext(); ) {
            TawSystemSubRole savedsubrole = (TawSystemSubRole) it.next();
            assertTrue(subrole1.getId().equals(savedsubrole.getId())
                    || subrole.getId().equals(savedsubrole.getId()));
        }

        // 验证虚拟组
        List virtualsubrole = roleMgr.listSubroleOfUser("leaderofgroup",
                RoleConstants.ROLETYPE_VIRTUAL);

        assertNotNull(virtualsubrole);
        assertEquals(virtualsubrole.size(), 1);
        for (Iterator it = virtualsubrole.iterator(); it.hasNext(); ) {
            TawSystemSubRole savedsubrole = (TawSystemSubRole) it.next();
            assertTrue(subrole.getId().equals(savedsubrole.getId()));
        }

    }

    /**
     * 取与地域ID，角色ID匹配但是没有配置一级网络分类的子角色列表
     */
    public void testListSubRole() {
        List list = roleMgr.listSubrole("2", "206");
        if (list != null) {
            for (Iterator it = list.iterator(); it.hasNext(); ) {
                TawSystemSubRole subrole = (TawSystemSubRole) it.next();
                System.out.println(subrole.getRoleId());
                System.out.println(subrole.getArea());
                System.out.println(subrole.getType1());

                assertNull(subrole.getType1());
                assertEquals(subrole.getArea(), "2");
                assertEquals(subrole.getRoleId(), 206);

            }
        }
    }

    public void testGetRoleLeaderBySubRoleid() {
        String tt[] = roleMgr.getRoleLeaderBySubRoleid("8aa082a81e67399d011e6d075c17051d");
        System.out.println("----" + tt[0] + "--------");
        System.out.println("----" + tt[1] + "--------");
        assertEquals(tt[0], "yjtxbzgzz1");

        tt = roleMgr.getRoleLeaderBySubRoleid("8aa082a81e67399d011e6d075c17051d");
        System.out.println("----" + tt[0] + "--------");
        System.out.println("----" + tt[1] + "--------");
        assertEquals(tt[0], "yjtxbzgzz1");

        tt = roleMgr.getRoleLeaderBySubRoleid("8aa082a81e67399d011e6d075c17051d");
        System.out.println("----" + tt[0] + "--------");
        System.out.println("----" + tt[1] + "--------");
        assertEquals(tt[0], "yjtxbzgzz1");

        tt = roleMgr.getRoleLeaderBySubRoleid("8aa082a81e67399d011e6d075c1705");
        if (tt == null) {
            System.out.println("----isNull--------");
            assertNull(tt);
        } else {
            System.out.println("----" + tt[0] + "--------");
            System.out.println("----" + tt[1] + "--------");
            assertEquals(tt[0], "yjtxbzgzz1");
        }

    }

}
