package com.boco.eoms.commons.system.role.test.service;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.system.role.dao.TawSystemRoleDao;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.service.impl.TawSystemRoleManagerImpl;

public class TawSystemRoleManagerTest extends BaseManagerTestCase {
    private final long tawSystemRoleId = 26;
    private TawSystemRoleManagerImpl tawSystemRoleManager = new TawSystemRoleManagerImpl();
    private Mock tawSystemRoleDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawSystemRoleDao = new Mock(TawSystemRoleDao.class);
        tawSystemRoleManager.setTawSystemRoleDao((TawSystemRoleDao) tawSystemRoleDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawSystemRoleManager = null;
    }

    public void TestGetTawSystemRoles() throws Exception {
        List results = new ArrayList();
        TawSystemRole tawSystemRole = new TawSystemRole();
        results.add(tawSystemRole);

        // set expected behavior on dao
        tawSystemRoleDao.expects(once()).method("getTawSystemRoles")
            .will(returnValue(results));

        List tawSystemRoles = tawSystemRoleManager.getTawSystemRoles();
        assertTrue(tawSystemRoles.size() == 1);
        tawSystemRoleDao.verify();
    }

    public void TestGetTawSystemRole() throws Exception {
        // set expected behavior on dao
        tawSystemRoleDao.expects(once()).method("getTawSystemRole")
            .will(returnValue(new TawSystemRole()));
        TawSystemRole tawSystemRole = tawSystemRoleManager.getTawSystemRole(tawSystemRoleId);
        assertTrue(tawSystemRole != null);
        tawSystemRoleDao.verify();
    }

    public void TestSaveTawSystemRole() throws Exception {
        TawSystemRole tawSystemRole = new TawSystemRole();

        // set expected behavior on dao
        tawSystemRoleDao.expects(once()).method("saveTawSystemRole")
            .with(same(tawSystemRole)).isVoid();

        tawSystemRoleManager.saveTawSystemRole(tawSystemRole);
        tawSystemRoleDao.verify();
    }

    public void TestAddAndRemoveTawSystemRole() throws Exception {
        TawSystemRole tawSystemRole = new TawSystemRole();

        // set required fields

        // set expected behavior on dao
        tawSystemRoleDao.expects(once()).method("saveTawSystemRole")
            .with(same(tawSystemRole)).isVoid();
        tawSystemRoleManager.saveTawSystemRole(tawSystemRole);
        tawSystemRoleDao.verify();

        // reset expectations
        tawSystemRoleDao.reset();

        tawSystemRoleDao.expects(once()).method("removeTawSystemRole").with(eq(tawSystemRoleId));
        tawSystemRoleManager.removeTawSystemRole(tawSystemRoleId);
        tawSystemRoleDao.verify();

        // reset expectations
        tawSystemRoleDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawSystemRole.class, new Long(tawSystemRole.getRoleId()));
        tawSystemRoleDao.expects(once()).method("removeTawSystemRole").isVoid();
        tawSystemRoleDao.expects(once()).method("getTawSystemRole").will(throwException(ex));
        tawSystemRoleManager.removeTawSystemRole(tawSystemRoleId);
        try {
            tawSystemRoleManager.getTawSystemRole(tawSystemRoleId);
            fail("TawSystemRole with identifier '" + tawSystemRoleId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawSystemRoleDao.verify();
    }
  /*  
    public void test(){
    	 

    	ITawSystemUserRefRoleManager userMgr = (ITawSystemUserRefRoleManager) ApplicationContextHolder
                      .getInstance().getBean("itawSystemUserRefRoleManager");
    	List userList = userMgr.getUserbyroleid(subrole.getId());
		Map map = userMgr.getGroupUserbyroleid(subrole.getId());
		JSONArray userArray = new JSONArray();
		
		int userListSize = userList.size();
		for(int j=0;j<userListSize;j++){
    		TawSystemUser user = (TawSystemUser)userList.get(j);
    		JSONObject useritem = new JSONObject();
    		String userId = user.getUserid();
    		String userName = user.getUsername();
    		useritem.put("id",userId);
    		useritem.put("name",userName);
    		useritem.put("text",userName);
    		
    		useritem.put("groupType",map.get(user));
    		if(map.get(user).equals(RoleConstants.groupType_leader)){
    		  jitem.put("leaderId",userId);
    		  jitem.put("leaderName",userName);
    		  jitem.put("info","("+RoleConstants.TEXT_LEADER+":"+userName+")");
    		}
    		
    		useritem.put("iconCls","user");
    		useritem.put("nodeType","user");
    		useritem.put("leaf",true);
    		 
    		userArray.put(useritem);
    	}
		jitem.put("user",userArray);
		if(userList.size()==0){
			jitem.put("leaf",true);
		}
    }
    */
}
