package com.boco.eoms.commons.system.user.test.dao;

import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;

public class TawSystemUserRefRoleDaoTest extends BaseDaoTestCase {

	private TawSystemUserRefRoleDao dao = null;

	public void setTawSystemUserRefRoleDao(TawSystemUserRefRoleDao dao) {
		this.dao = dao;
	}

	public void testAddTawSystemUserRefRole() throws Exception {
		TawSystemUserRefRole tawSystemUserRefRole = new TawSystemUserRefRole();

		// set required fields
		tawSystemUserRefRole.setRoleid("111");
		tawSystemUserRefRole.setUserid("test");
		dao.saveTawSystemUserRefRole(tawSystemUserRefRole);

		// verify a primary key was assigned
		assertNotNull(tawSystemUserRefRole.getId());

		// verify set fields are same after save
	}

	public void testGetTawSystemUserRefRole() throws Exception {
		TawSystemUserRefRole tawSystemUserRefRole = new TawSystemUserRefRole();

		// set required fields
		tawSystemUserRefRole.setRoleid("111");
		tawSystemUserRefRole.setUserid("test");
		dao.saveTawSystemUserRefRole(tawSystemUserRefRole);

		TawSystemUserRefRole tawSystemUserRefRoles = dao
				.getTawSystemUserRefRole(tawSystemUserRefRole.getId());
		assertNotNull(tawSystemUserRefRoles);
	}

	public void testGetTawSystemUserRefRoles() throws Exception {
		TawSystemUserRefRole tawSystemUserRefRole = new TawSystemUserRefRole();

		// set required fields
		tawSystemUserRefRole.setRoleid("111");
		tawSystemUserRefRole.setUserid("test");
		dao.saveTawSystemUserRefRole(tawSystemUserRefRole);

		List results = dao.getTawSystemUserRefRoles(tawSystemUserRefRole);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawSystemUserRefRole() throws Exception {
		TawSystemUserRefRole tawSystemUserRefRole = new TawSystemUserRefRole();

		// set required fields
		tawSystemUserRefRole.setRoleid("111");
		tawSystemUserRefRole.setUserid("test");
		dao.saveTawSystemUserRefRole(tawSystemUserRefRole);

		TawSystemUserRefRole tawSystemUserRefRoles = dao
				.getTawSystemUserRefRole(tawSystemUserRefRole.getId());

		// update required fields

		dao.saveTawSystemUserRefRole(tawSystemUserRefRoles);
		TawSystemUserRefRole tawSystemUserRefRoless = dao
				.getTawSystemUserRefRole(tawSystemUserRefRoles.getId());
		assertSame(tawSystemUserRefRoless.getRoleid(), tawSystemUserRefRoles
				.getRoleid());
	}

	public void testRemoveTawSystemUserRefRole() throws Exception {
		TawSystemUserRefRole tawSystemUserRefRole = new TawSystemUserRefRole();

		// set required fields
		tawSystemUserRefRole.setRoleid("111");
		tawSystemUserRefRole.setUserid("test");
		dao.saveTawSystemUserRefRole(tawSystemUserRefRole);
		dao.removeTawSystemUserRefRole(tawSystemUserRefRole.getId());
		try {
			dao.getTawSystemUserRefRole(tawSystemUserRefRole.getId());
			fail("tawSystemUserRefRole found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}

	public void testGetSubrolesByUserId() {
		List list = dao.getSubrolesByUserId(new String[] { "infopub"},
				RoleConstants.systemRole, Constants.NOT_DELETED_FLAG);

		assertNotNull(list);
		assertTrue(!list.isEmpty());
		assertEquals(list.size(), 1);
		TawSystemSubRole subrole = (TawSystemSubRole) list.iterator().next();
		assertEquals(subrole.getId(), "8aa081281c31b8f4011c31bf03a90012");
	}
	// public void testUseridbyroleid() {
	// TawSystemUserRefRole tawSystemUserRefRole = new TawSystemUserRefRole();
	// tawSystemUserRefRole.setRemark("aas");
	// tawSystemUserRefRole.setRoleid("11");
	// tawSystemUserRefRole.setUserid("vv");
	// dao.saveTawSystemUserRefRole(tawSystemUserRefRole);
	// List list = dao.getUseridbyroleid("11");
	//
	// assertTrue(list.size() > 0);
	//
	// }
}
