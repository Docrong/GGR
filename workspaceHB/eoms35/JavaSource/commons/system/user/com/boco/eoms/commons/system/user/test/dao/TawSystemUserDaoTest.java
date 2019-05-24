package com.boco.eoms.commons.system.user.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.dao.TawSystemUserDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemUserDaoTest extends BaseDaoTestCase {

	private TawSystemUserDao dao = null;

	public void setTawSystemUserDao(TawSystemUserDao dao) {
		this.dao = dao;
	}

	public void testAddTawSystemUser() throws Exception {
		TawSystemUser tawSystemUser = new TawSystemUser();

		tawSystemUser.setDeptid("111");
		tawSystemUser.setDeptname("ceshi");
		// set required fields

		dao.saveTawSystemUser(tawSystemUser);

		// verify a primary key was assigned
		assertNotNull(tawSystemUser.getId());

		// verify set fields are same after save
	}

	public void testGetTawSystemUser() throws Exception {
		TawSystemUser tawSystemUser = new TawSystemUser();

		tawSystemUser.setDeptid("111");
		tawSystemUser.setDeptname("ceshi");
		dao.saveTawSystemUser(tawSystemUser);
		TawSystemUser tawSystemUsers = dao.getTawSystemUser(tawSystemUser
				.getId());
		assertNotNull(tawSystemUsers);
	}

	public void testGetTawSystemUsers() throws Exception {

		TawSystemUser tawSystemUser = new TawSystemUser();

		tawSystemUser.setDeptid("111");
		tawSystemUser.setDeptname("ceshi");
		dao.saveTawSystemUser(tawSystemUser);

		List results = dao.getTawSystemUsers(tawSystemUser);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawSystemUser() throws Exception {
		TawSystemUser tawSystemUser = new TawSystemUser();

		tawSystemUser.setDeptid("111");
		tawSystemUser.setDeptname("ceshi");
		dao.saveTawSystemUser(tawSystemUser);
		TawSystemUser tawSystemUsers = dao.getTawSystemUser(tawSystemUser
				.getId());

		// update required fields

		dao.saveTawSystemUser(tawSystemUsers);
		TawSystemUser tawSystemUserss = dao.getTawSystemUser(tawSystemUsers
				.getId());
		assertSame(tawSystemUserss.getDeptid(), tawSystemUser.getDeptid());
	}

	public void testRemoveTawSystemUser() throws Exception {
		TawSystemUser tawSystemUser = new TawSystemUser();

		tawSystemUser.setDeptid("111");
		tawSystemUser.setDeptname("ceshi");
		dao.saveTawSystemUser(tawSystemUser);
		dao.removeTawSystemUser(tawSystemUser.getId());
		try {
			dao.getTawSystemUser(tawSystemUser.getId());
			fail("tawSystemUser found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}

	public void testIn() {

		TawSystemUser tawSystemUser = new TawSystemUser();

		tawSystemUser.setDeptid("1122");

		dao.saveTawSystemUser(tawSystemUser);

		TawSystemUser tawSystemUsers = new TawSystemUser();

		tawSystemUsers = dao.getTawSystemUser(tawSystemUser.getId());
		assertSame(tawSystemUser.getDeptid(), tawSystemUsers.getDeptid());
	}
}
