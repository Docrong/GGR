package com.boco.eoms.commons.system.user.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.system.user.service.impl.TawSystemUserRefRoleManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemUserRefRoleManagerTest extends BaseManagerTestCase {
	private final String tawSystemUserRefRoleId = "1";

	private TawSystemUserRefRoleManagerImpl tawSystemUserRefRoleManager = new TawSystemUserRefRoleManagerImpl();

	private Mock tawSystemUserRefRoleDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		tawSystemUserRefRoleDao = new Mock(TawSystemUserRefRoleDao.class);
		tawSystemUserRefRoleManager
				.setTawSystemUserRefRoleDao((TawSystemUserRefRoleDao) tawSystemUserRefRoleDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tawSystemUserRefRoleManager = null;
	}

	public void testGetTawSystemUserRefRoles() throws Exception {
		List results = new ArrayList();
		TawSystemUserRefRole tawSystemUserRefRole = new TawSystemUserRefRole();
		results.add(tawSystemUserRefRole);

		// set expected behavior on dao
		tawSystemUserRefRoleDao.expects(once()).method(
				"getTawSystemUserRefRoles").will(returnValue(results));

		List tawSystemUserRefRoles = tawSystemUserRefRoleManager
				.getTawSystemUserRefRoles(null);
		assertTrue(tawSystemUserRefRoles.size() == 1);
		tawSystemUserRefRoleDao.verify();
	}

	public void testGetTawSystemUserRefRole() throws Exception {
		// set expected behavior on dao
		tawSystemUserRefRoleDao.expects(once()).method(
				"getTawSystemUserRefRole").will(
				returnValue(new TawSystemUserRefRole()));
		TawSystemUserRefRole tawSystemUserRefRole = tawSystemUserRefRoleManager
				.getTawSystemUserRefRole(tawSystemUserRefRoleId);
		assertTrue(tawSystemUserRefRole != null);
		tawSystemUserRefRoleDao.verify();
	}

	public void testSaveTawSystemUserRefRole() throws Exception {
		TawSystemUserRefRole tawSystemUserRefRole = new TawSystemUserRefRole();

		// set expected behavior on dao
		tawSystemUserRefRoleDao.expects(once()).method(
				"saveTawSystemUserRefRole").with(same(tawSystemUserRefRole))
				.isVoid();

		tawSystemUserRefRoleManager
				.saveTawSystemUserRefRole(tawSystemUserRefRole);
		tawSystemUserRefRoleDao.verify();
	}

	public void testAddAndRemoveTawSystemUserRefRole() throws Exception {
		TawSystemUserRefRole tawSystemUserRefRole = new TawSystemUserRefRole();

		// set required fields

		// set expected behavior on dao
		tawSystemUserRefRoleDao.expects(once()).method(
				"saveTawSystemUserRefRole").with(same(tawSystemUserRefRole))
				.isVoid();
		tawSystemUserRefRoleManager
				.saveTawSystemUserRefRole(tawSystemUserRefRole);
		tawSystemUserRefRoleDao.verify();

		// reset expectations
		tawSystemUserRefRoleDao.reset();

		tawSystemUserRefRoleDao.expects(once()).method(
				"removeTawSystemUserRefRole").with(
				eq(new String(tawSystemUserRefRoleId)));
		tawSystemUserRefRoleManager
				.removeTawSystemUserRefRole(tawSystemUserRefRoleId);
		tawSystemUserRefRoleDao.verify();

		// reset expectations
		tawSystemUserRefRoleDao.reset();
		// remove
		Exception ex = new ObjectRetrievalFailureException(
				TawSystemUserRefRole.class, tawSystemUserRefRole.getId());
		tawSystemUserRefRoleDao.expects(once()).method(
				"removeTawSystemUserRefRole").isVoid();
		tawSystemUserRefRoleDao.expects(once()).method(
				"getTawSystemUserRefRole").will(throwException(ex));
		tawSystemUserRefRoleManager
				.removeTawSystemUserRefRole(tawSystemUserRefRoleId);
		try {
			tawSystemUserRefRoleManager
					.getTawSystemUserRefRole(tawSystemUserRefRoleId);
			fail("TawSystemUserRefRole with identifier '"
					+ tawSystemUserRefRoleId + "' found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		tawSystemUserRefRoleDao.verify();
	}
}
