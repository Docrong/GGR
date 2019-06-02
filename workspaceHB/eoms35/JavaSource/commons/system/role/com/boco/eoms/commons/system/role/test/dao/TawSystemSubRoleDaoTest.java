package com.boco.eoms.commons.system.role.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemSubRoleDaoTest extends BaseDaoTestCase {
    private String tawSystemSubRoleId = "10";
    private TawSystemSubRoleDao dao = null;

    public void setTawSystemSubRoleDao(TawSystemSubRoleDao dao) {
        this.dao = dao;
    }

    public void testAddTawSystemSubRole() throws Exception {
        TawSystemSubRole tawSystemSubRole = new TawSystemSubRole();

        // set required fields
        tawSystemSubRole.setSubRoleName("test");
        tawSystemSubRole.setRoleId(20000);
        tawSystemSubRole.setDeptId("123");
        tawSystemSubRole.setType1("1");
        tawSystemSubRole.setType2("2");
        tawSystemSubRole.setType3("3");
        tawSystemSubRole.setType4("4");

        dao.saveTawSystemSubRole(tawSystemSubRole);

        // verify a primary key was assigned
        assertNotNull(new Long(tawSystemSubRole.getId()));

        // verify set fields are same after save
        tawSystemSubRole.setId("0");
        dao.saveTawSystemSubRole(tawSystemSubRole);
        List results = dao.getTawSystemSubRoles(20000);
        assertTrue(results.size()==1);
    }

    public void testGetTawSystemSubRole() throws Exception {
        TawSystemSubRole tawSystemSubRole = dao.getTawSystemSubRole(tawSystemSubRoleId);
        assertNotNull(tawSystemSubRole);
    }

    public void testGetTawSystemSubRoles() throws Exception {

        List results = dao.getTawSystemSubRoles(26);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawSystemSubRole() throws Exception {
        TawSystemSubRole tawSystemSubRole = dao.getTawSystemSubRole(tawSystemSubRoleId);

        // update required fields

        dao.saveTawSystemSubRole(tawSystemSubRole);

    }

    public void testRemoveTawSystemSubRole() throws Exception {
    	String removeId = "10";
        dao.removeTawSystemSubRole(removeId);
        try {
            dao.getTawSystemSubRole(removeId);
            fail("tawSystemSubRole found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
    
    public void testGetTawSystemSubRoleByLogo() throws Exception{
    	TawSystemSubRole tawSystemSubRole = new TawSystemSubRole();
    	tawSystemSubRole.setSubRoleName("testLogo");
    	tawSystemSubRole.setRoleId(26);
    	tawSystemSubRole.setDeptId("111");
    	tawSystemSubRole.setType1("type1");
    	tawSystemSubRole.setType2("type2");
    	tawSystemSubRole.setType3("type3");
    	tawSystemSubRole.setType4("type4");
    	
    	dao.saveTawSystemSubRole(tawSystemSubRole);
    	assertNotNull(new Long(tawSystemSubRole.getId()));
    	
    	String logo = ""+tawSystemSubRole.getDeptId()+tawSystemSubRole.getType1()+tawSystemSubRole.getType2()+tawSystemSubRole.getType3()+tawSystemSubRole.getType4()+tawSystemSubRole.getRoleId();
    	tawSystemSubRole = dao.getTawSystemSubRoleByLogo(logo);
    	assertNotNull(tawSystemSubRole);
    }
        

    public void testGetSubRoles() throws Exception {

        List results = dao.getSubRoles("lisi","72");
        assertTrue(results.size() > 0);
    }
}
