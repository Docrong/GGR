package com.boco.eoms.commons.system.role.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.dao.TawSystemRoleDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemRoleDaoTest extends BaseDaoTestCase {
    private long tawSystemRoleId = 1;
    private TawSystemRoleDao dao = null;

    public void setTawSystemRoleDao(TawSystemRoleDao dao) {
        this.dao = dao;
    }

    public void testAddTawSystemRole() throws Exception {
        TawSystemRole tawSystemRole = new TawSystemRole();

        // set required fields
        tawSystemRole.setDeleted(new Integer(1));
        tawSystemRole.setLeaf(new Integer(1));
        tawSystemRole.setRoleName("test");


        dao.saveTawSystemRole(tawSystemRole);

        // verify a primary key was assigned
        assertNotNull(new Long(tawSystemRole.getRoleId()));

        // verify set fields are same after save
        assertTrue("test".equals(tawSystemRole.getRoleName()));
    }

    public void testGetTawSystemRole() throws Exception {
        TawSystemRole tawSystemRole = dao.getTawSystemRole(tawSystemRoleId);
        assertNotNull(tawSystemRole);
    }

    public void testGetTawSystemRoles() throws Exception {

        List results = dao.getTawSystemRoles();
        assertTrue(results.size() > 0);
    }

    public void testSaveTawSystemRole() throws Exception {
        TawSystemRole tawSystemRole = dao.getTawSystemRole(tawSystemRoleId);

        // update required fields

        dao.saveTawSystemRole(tawSystemRole);

    }

    public void testRemoveTawSystemRole() throws Exception {
        long removeId = 34;
        dao.removeTawSystemRole(removeId);
        try {
            TawSystemRole role = dao.getTawSystemRole(removeId);
            assertTrue(role.getDeleted().equals(new Integer(1)));
            //fail("tawSystemRole found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }

    public void testGetDeptOfRole() {
        long roleId = 26;
        List list = dao.getDeptByRoleId(roleId);
        assertNotNull(list);
    }

    public void testGetRole() {
        Integer roleId = new Integer(4);
        TawSystemRole role = dao.getTawSystemRole(roleId, "");
        assertNotNull(role);
    }

}
