package com.boco.eoms.commons.system.user.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.system.user.dao.TawSystemUserDao;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.impl.TawSystemUserManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemUserManagerTest extends BaseManagerTestCase {
	private final String tawSystemUserId = "1";

	private TawSystemUserManagerImpl tawSystemUserManager = new TawSystemUserManagerImpl();

	private Mock tawSystemUserDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		tawSystemUserDao = new Mock(TawSystemUserDao.class);
		tawSystemUserManager
				.setTawSystemUserDao((TawSystemUserDao) tawSystemUserDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tawSystemUserManager = null;
	}

	public void testGetTawSystemUsers() throws Exception {
		List results = new ArrayList();
		TawSystemUser tawSystemUser = new TawSystemUser();
		results.add(tawSystemUser);

		// set expected behavior on dao
		tawSystemUserDao.expects(once()).method("getTawSystemUsers").will(
				returnValue(results));

		List tawSystemUsers = tawSystemUserManager.getTawSystemUsers(null);
		assertTrue(tawSystemUsers.size() == 1);
		tawSystemUserDao.verify();
	}

	public void testGetTawSystemUser() throws Exception {
		// set expected behavior on dao
		tawSystemUserDao.expects(once()).method("getTawSystemUser").will(
				returnValue(new TawSystemUser()));
		TawSystemUser tawSystemUser = tawSystemUserManager
				.getTawSystemUser(tawSystemUserId);
		assertTrue(tawSystemUser != null);
		tawSystemUserDao.verify();
	}

	public void testSaveTawSystemUser() throws Exception {
		TawSystemUser tawSystemUser = new TawSystemUser();

		// set expected behavior on dao
		tawSystemUserDao.expects(once()).method("saveTawSystemUser").with(
				same(tawSystemUser)).isVoid();

		tawSystemUserManager.saveTawSystemUser(tawSystemUser, "");
		tawSystemUserDao.verify();
	}

	public void testAddAndRemoveTawSystemUser() throws Exception {
		TawSystemUser tawSystemUser = new TawSystemUser();

		// set required fields

		// set expected behavior on dao
		tawSystemUserDao.expects(once()).method("saveTawSystemUser").with(
				same(tawSystemUser)).isVoid();
		tawSystemUserManager.saveTawSystemUser(tawSystemUser, "");
		tawSystemUserDao.verify();

		// reset expectations
		tawSystemUserDao.reset();

		tawSystemUserDao.expects(once()).method("removeTawSystemUser").with(
				eq(new String(tawSystemUserId)));
		tawSystemUserManager.removeTawSystemUser(tawSystemUserId);
		tawSystemUserDao.verify();

		// reset expectations
		tawSystemUserDao.reset();
		// remove
		Exception ex = new ObjectRetrievalFailureException(TawSystemUser.class,
				tawSystemUser.getId());
		tawSystemUserDao.expects(once()).method("removeTawSystemUser").isVoid();
		tawSystemUserDao.expects(once()).method("getTawSystemUser").will(
				throwException(ex));
		tawSystemUserManager.removeTawSystemUser(tawSystemUserId);
		try {
			tawSystemUserManager.getTawSystemUser(tawSystemUserId);
			fail("TawSystemUser with identifier '" + tawSystemUserId
					+ "' found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		tawSystemUserDao.verify();
	}

}
