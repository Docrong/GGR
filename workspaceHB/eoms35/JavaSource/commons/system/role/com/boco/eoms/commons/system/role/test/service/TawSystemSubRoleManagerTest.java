package com.boco.eoms.commons.system.role.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.impl.TawSystemSubRoleManagerImpl;
import com.boco.eoms.commons.system.role.util.RoleConstants;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemSubRoleManagerTest extends BaseManagerTestCase {
    private final String tawSystemSubRoleId = "10";
    private TawSystemSubRoleManagerImpl tawSystemSubRoleManager = new TawSystemSubRoleManagerImpl();
    private Mock tawSystemSubRoleDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawSystemSubRoleDao = new Mock(TawSystemSubRoleDao.class);
        tawSystemSubRoleManager.setTawSystemSubRoleDao((TawSystemSubRoleDao) tawSystemSubRoleDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawSystemSubRoleManager = null;
    }

    public void testGetTawSystemSubRoles() throws Exception {
        List results = new ArrayList();
        TawSystemSubRole tawSystemSubRole = new TawSystemSubRole();
        results.add(tawSystemSubRole);

        // set expected behavior on dao
        tawSystemSubRoleDao.expects(once()).method("getTawSystemSubRoles")
            .will(returnValue(results));

        List tawSystemSubRoles = tawSystemSubRoleManager.getTawSystemSubRoles(1);
        assertTrue(tawSystemSubRoles.size() == 1);
        tawSystemSubRoleDao.verify();
    }

    public void testGetTawSystemSubRole() throws Exception {
        // set expected behavior on dao
        tawSystemSubRoleDao.expects(once()).method("getTawSystemSubRole")
            .will(returnValue(new TawSystemSubRole()));
        TawSystemSubRole tawSystemSubRole = tawSystemSubRoleManager.getTawSystemSubRole(tawSystemSubRoleId);
        assertTrue(tawSystemSubRole != null);
        tawSystemSubRoleDao.verify();
    }

    public void testSaveTawSystemSubRole() throws Exception {
        TawSystemSubRole tawSystemSubRole = new TawSystemSubRole();

        // set expected behavior on dao
        tawSystemSubRoleDao.expects(once()).method("saveTawSystemSubRole")
            .with(same(tawSystemSubRole)).isVoid();
        Map userMap = new HashMap();
        userMap.put("user1",RoleConstants.ROLETYPE_SUBROLE);
        userMap.put("user2",RoleConstants.ROLETYPE_SUBROLE);

        tawSystemSubRoleManager.saveTawSystemSubRole(tawSystemSubRole,userMap);
        tawSystemSubRoleDao.verify();
    }

    public void testAddAndRemoveTawSystemSubRole() throws Exception {
        TawSystemSubRole tawSystemSubRole = new TawSystemSubRole();

        // set required fields

        // set expected behavior on dao
        tawSystemSubRoleDao.expects(once()).method("saveTawSystemSubRole")
            .with(same(tawSystemSubRole)).isVoid();
        
        Map userMap = new HashMap();
        userMap.put("user1",RoleConstants.ROLETYPE_SUBROLE);
        userMap.put("user2",RoleConstants.ROLETYPE_SUBROLE);
        tawSystemSubRoleManager.saveTawSystemSubRole(tawSystemSubRole,userMap);
        tawSystemSubRoleDao.verify();

        // reset expectations
        tawSystemSubRoleDao.reset();

        tawSystemSubRoleDao.expects(once()).method("removeTawSystemSubRole").with(eq(tawSystemSubRoleId));
        tawSystemSubRoleManager.removeTawSystemSubRole(tawSystemSubRoleId);
        tawSystemSubRoleDao.verify();

        // reset expectations
        tawSystemSubRoleDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawSystemSubRole.class, new Long(tawSystemSubRole.getId()));
        tawSystemSubRoleDao.expects(once()).method("removeTawSystemSubRole").isVoid();
        tawSystemSubRoleDao.expects(once()).method("getTawSystemSubRole").will(throwException(ex));
        tawSystemSubRoleManager.removeTawSystemSubRole(tawSystemSubRoleId);
        try {
            tawSystemSubRoleManager.getTawSystemSubRole(tawSystemSubRoleId);
            fail("TawSystemSubRole with identifier '" + tawSystemSubRoleId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawSystemSubRoleDao.verify();
    }
    
    /**
     * 测试批量保存子角色
     * @throws Exception
     */
    public void testSaveTawSystemSubRoles() throws Exception{
    	List subRoleList = new ArrayList();
    	TawSystemSubRole tawSystemSubRole1 = new TawSystemSubRole();
    	tawSystemSubRole1.setSubRoleName("testLogo");
    	tawSystemSubRole1.setRoleId(1999);
    	tawSystemSubRole1.setDeptId("111");
    	tawSystemSubRole1.setType1("type1");
    	tawSystemSubRole1.setType2("type2");
    	tawSystemSubRole1.setType3("type3");
    	tawSystemSubRole1.setType4("type4");
    	subRoleList.add(tawSystemSubRole1);
    	
    	TawSystemSubRole tawSystemSubRole2 = new TawSystemSubRole();
    	tawSystemSubRole2.setSubRoleName("testLogo");
    	tawSystemSubRole2.setRoleId(1999);
    	tawSystemSubRole2.setDeptId("112");
    	tawSystemSubRole2.setType1("type1");
    	tawSystemSubRole2.setType2("type2");
    	tawSystemSubRole2.setType3("type3");
    	tawSystemSubRole2.setType4("type4");
    	subRoleList.add(tawSystemSubRole2);
    	
    	TawSystemSubRole tawSystemSubRole3 = tawSystemSubRole1;
    	subRoleList.add(tawSystemSubRole3);
    	
    	
    	
    	for(int i=0;i<subRoleList.size();i++){
    		TawSystemSubRole role = (TawSystemSubRole)subRoleList.get(i);
    		tawSystemSubRoleDao.expects(once()).method("saveTawSystemSubRole")
	        .with(same(role)).isVoid();
    		tawSystemSubRoleManager.saveTawSystemSubRole(role,null);
    		tawSystemSubRoleDao.reset();
    	}
	    tawSystemSubRoleDao.verify();
	    tawSystemSubRoleDao.reset();
	    
//	    List roleList = new ArrayList();
//	    tawSystemSubRoleDao.expects(once()).method("getTawSystemSubRoles").will(returnValue(roleList));
//	    tawSystemSubRoleManager.getTawSystemSubRoles(1999);
//    	assertTrue(roleList.size()==2);
    }
    public void testRemove(){
    	tawSystemSubRoleManager.deleteSubRoles("22,23");
    	try {
    		tawSystemSubRoleManager.getTawSystemSubRole("22");
    		tawSystemSubRoleManager.getTawSystemSubRole("23");
            fail("tawSystemSubRole found in database");
        } catch (Exception e) {
            assertNotNull(e.getMessage());
        }
    }
    
}
