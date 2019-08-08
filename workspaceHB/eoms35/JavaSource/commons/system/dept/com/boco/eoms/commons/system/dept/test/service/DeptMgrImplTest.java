package com.boco.eoms.commons.system.dept.test.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.IDeptMgr;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dept.util.DeptConstants;

/**
 * <p>
 * Title:部门mgr测试类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Nov 13, 2008 9:40:56 AM
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public class DeptMgrImplTest extends ConsoleTestCase {

    /**
     * 临时插入的部门id，用于测试
     */
    private final static String DEPT_ID = "99999999";

    /**
     * 部门api mgr
     */
    private IDeptMgr deptMgr;

    /**
     * 部门mgr
     */
    private ITawSystemDeptManager tawSystemDeptManager;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpInTransaction()
     */
    protected void onSetUpInTransaction() throws Exception {
        super.onSetUpInTransaction();
        this.tawSystemDeptManager = (ITawSystemDeptManager) getBean("ItawSystemDeptSaveManagerFlush");
        this.deptMgr = (IDeptMgr) getBean("DeptMgrImpl");
    }

    /**
     * 构造一个部门实例（添加基础信息）
     *
     * @param dept 部门对象
     * @return 构造的部门实例
     */
    private TawSystemDept constructDept(TawSystemDept dept) {
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
        return dept;
    }

    /**
     * 测试通过id取部门
     *
     * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#getDept(String)
     */
    public void testGetDept() {
        TawSystemDept dept = new TawSystemDept();
        dept.setDeptId(DeptMgrImplTest.DEPT_ID);
        dept = constructDept(dept);
        this.tawSystemDeptManager.saveTawSystemDept(dept);
        TawSystemDept savedDept = deptMgr.getDept(DeptMgrImplTest.DEPT_ID);
        // 验证通过DEPT_ID取出刚插入的部门
        assertNotNull(savedDept);
        assertEquals(dept.getDeptId(), savedDept.getDeptId());
        assertEquals(dept.getDeptmanager(), savedDept.getDeptmanager());
    }

    /**
     * 测试获取父部门
     *
     * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#getParentDept(String)
     */
    public void testGetParentDept() {
        // 构建父子对象
        TawSystemDept parentDept = new TawSystemDept();
        parentDept.setDeptId("parentDeptId");
        parentDept = constructDept(parentDept);
        TawSystemDept dept = new TawSystemDept();
        dept.setDeptId("deptId");
        dept.setParentDeptid("parentDeptId");
        dept = constructDept(dept);
        this.tawSystemDeptManager.saveTawSystemDept(parentDept);
        this.tawSystemDeptManager.saveTawSystemDept(dept);
        // 取刚构造子部门的父部门，并验证
        TawSystemDept savedParentDept = this.deptMgr.getParentDept("deptId");
        assertNotNull(savedParentDept);
        assertEquals(parentDept.getAreaid(), savedParentDept.getAreaid());
    }

    /**
     * 取根部门
     *
     * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#getRootDept()
     */
    public void testGetRootDept() {
        // 取根部门列表
        List list = this.deptMgr.getRootDept();
        // 验证所有根部门的ID是否为DeptConstants.ROOT_PARENTDEPT_ID
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            TawSystemDept dept = (TawSystemDept) it.next();
            assertEquals(DeptConstants.ROOT_PARENTDEPT_ID, dept
                    .getParentDeptid());
        }
    }

    /**
     * 取某部门下的子部门
     *
     * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#listAllSubDept(String)
     */
    public void testListAllSubDept() {
        // 构建父子对象，其关系如下：parentDept->dept1->dept2
        TawSystemDept parentDept = new TawSystemDept();
        parentDept.setDeptId("parentDeptId");
        parentDept = constructDept(parentDept);
        TawSystemDept dept1 = new TawSystemDept();
        dept1.setDeptId("deptId1");
        dept1.setParentDeptid("parentDeptId");
        dept1 = constructDept(dept1);
        TawSystemDept dept2 = new TawSystemDept();
        dept2.setDeptId("deptId2");
        dept2.setParentDeptid("deptId1");
        dept2 = constructDept(dept2);
        this.tawSystemDeptManager.saveTawSystemDept(parentDept);
        this.tawSystemDeptManager.saveTawSystemDept(dept1);
        this.tawSystemDeptManager.saveTawSystemDept(dept2);
        // 取parentDept子部门，应为dept1,dept2
        List list = this.deptMgr.listAllSubDept("parentDeptId");
        assertNotNull(list);
        // 应为3个子部门，包括父部门
        assertEquals(3, list.size());
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            TawSystemDept dept = (TawSystemDept) it.next();
            // 判断2个子部门是否为deptId2,deptId1
            if (!("deptId1".equals(dept.getDeptId())
                    || "deptId2".equals(dept.getDeptId()) || "parentDeptId"
                    .equals(dept.getDeptId()))) {
                fail();
            }
        }

    }

    /**
     * 取某区域下的所有部门
     *
     * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#listDeptOfArea(String)
     */
    public void testListDeptOfArea() {
        TawSystemDept dept1 = new TawSystemDept();
        dept1.setAreaid("areaId");
        dept1 = constructDept(dept1);
        TawSystemDept dept2 = new TawSystemDept();
        dept2.setAreaid("areaId");
        dept2 = constructDept(dept2);
        this.tawSystemDeptManager.saveTawSystemDept(dept1);
        this.tawSystemDeptManager.saveTawSystemDept(dept2);
        List list = deptMgr.listDeptOfArea("areaId");
        assertNotNull(list);
        // 地域下的2个部门
        assertEquals(2, list.size());
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            TawSystemDept dept = (TawSystemDept) it.next();
            // 判断2个子部门是否为deptId2,deptId1
            if (!("areaId".equals(dept.getAreaid()))) {
                fail();
            }
        }
    }

    /**
     * 取其所有父部门
     *
     * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#listParentDept(String)
     */
    public void testListParentDept() {
        // 构建父子对象，其关系如下：parentDept->dept1->dept2
        TawSystemDept parentDept = new TawSystemDept();
        parentDept.setDeptId("parentDeptId");
        parentDept = constructDept(parentDept);
        TawSystemDept dept1 = new TawSystemDept();
        dept1.setDeptId("deptId1");
        dept1.setParentDeptid("parentDeptId");
        dept1 = constructDept(dept1);
        TawSystemDept dept2 = new TawSystemDept();
        dept2.setDeptId("deptId2");
        dept2.setParentDeptid("deptId1");
        dept2 = constructDept(dept2);
        this.tawSystemDeptManager.saveTawSystemDept(parentDept);
        this.tawSystemDeptManager.saveTawSystemDept(dept1);
        this.tawSystemDeptManager.saveTawSystemDept(dept2);

        // 取parentDept子部门，应为dept1,dept2
        List list = this.deptMgr.listParentDept("deptId2");
        assertNotNull(list);
        // 应为2个父部门+本部门=3
        assertEquals(3, list.size());
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            TawSystemDept dept = (TawSystemDept) it.next();
            // 判断2个子部门是否为deptId2,deptId1
            if (!("deptId1".equals(dept.getDeptId())
                    || "deptId2".equals(dept.getDeptId()) || "parentDeptId"
                    .equals(dept.getDeptId()))) {
                fail();
            }
        }
    }

    /**
     * 取其子部门（下级）
     *
     * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#listSubDept(String)
     */
    public void testListSubDept() {
        // 构建父子对象，其关系如下：parentDept->dept1->dept2
        TawSystemDept parentDept = new TawSystemDept();
        parentDept.setDeptId("parentDeptId");
        parentDept = constructDept(parentDept);
        TawSystemDept dept1 = new TawSystemDept();
        dept1.setDeptId("deptId1");
        dept1.setParentDeptid("parentDeptId");
        dept1 = constructDept(dept1);
        TawSystemDept dept2 = new TawSystemDept();
        dept2.setDeptId("deptId2");
        dept2.setParentDeptid("deptId1");
        dept2 = constructDept(dept2);
        this.tawSystemDeptManager.saveTawSystemDept(parentDept);
        this.tawSystemDeptManager.saveTawSystemDept(dept1);
        this.tawSystemDeptManager.saveTawSystemDept(dept2);
        // 取parentDept子部门，应为dept1,dept2
        List list = this.deptMgr.listSubDept("parentDeptId");
        assertNotNull(list);
        // 应为3个子部门，包括父部门
        assertEquals(1, list.size());
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            TawSystemDept dept = (TawSystemDept) it.next();
            // 判断2个子部门是否为deptId2,deptId1
            if (!("deptId1".equals(dept.getDeptId()))) {
                fail();
            }
        }
    }

    /**
     * 分页取部门
     *
     * @see com.boco.eoms.commons.system.dept.service.IDeptMgr#mapDept(Integer,
     * Integer, String, String)
     */
    public void testMapDept() {
        // 构建父子对象，其关系如下：parentDept->dept1->dept2
        TawSystemDept parentDept = new TawSystemDept();
        parentDept.setDeptId("parentDeptId");
        parentDept = constructDept(parentDept);
        TawSystemDept dept1 = new TawSystemDept();
        dept1.setDeptId("deptId1");
        dept1.setParentDeptid("parentDeptId");
        dept1 = constructDept(dept1);
        TawSystemDept dept2 = new TawSystemDept();
        dept2.setDeptId("deptId2");
        dept2.setParentDeptid("deptId1");
        dept2 = constructDept(dept2);
        this.tawSystemDeptManager.saveTawSystemDept(parentDept);
        this.tawSystemDeptManager.saveTawSystemDept(dept1);
        this.tawSystemDeptManager.saveTawSystemDept(dept2);

        Map map = this.deptMgr.mapDept(new Integer(0), new Integer(2),
                " deptPhone='88157289'");
        List list = (List) map.get("result");
        Integer total = (Integer) map.get("total");
        assertNotNull(list);
        assertEquals(total, new Integer(3));
        assertEquals(list.size(), 2);

    }

    // public void testModifyDept() {
    // fail("Not yet implemented");
    // }
    //
    // public void testMoveDept() {
    // fail("Not yet implemented");
    // }
    //
    // public void testRemoveDept() {
    // fail("Not yet implemented");
    // }
    //
    // public void testRemoveDeptsList() {
    // fail("Not yet implemented");
    // }
    //
    // public void testRemoveDeptsStringArray() {
    // fail("Not yet implemented");
    // }
    //
    // public void testUsers2Dept() {
    // fail("Not yet implemented");
    // }
    //
    // public void testCreateDept() {
    // fail("Not yet implemented");
    // }
}
