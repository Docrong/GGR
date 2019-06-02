package com.boco.eoms.commons.system.priv.test.service;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivUserAssignDao;
import com.boco.eoms.commons.system.priv.exception.AuthorizationException;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivUserAssignManager;
import com.boco.eoms.commons.system.priv.service.impl.TawSystemPrivUserAssignManagerImpl;

public class TawSystemPrivUserAssignManagerTest extends BaseManagerTestCase {
	private final String tawSystemPrivUserAssignId = "1";

	private TawSystemPrivUserAssignManagerImpl tawSystemPrivUserAssignManager = new TawSystemPrivUserAssignManagerImpl();

	private Mock tawSystemPrivUserAssignDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		tawSystemPrivUserAssignDao = new Mock(TawSystemPrivUserAssignDao.class);
		tawSystemPrivUserAssignManager
				.setTawSystemPrivUserAssignDao((TawSystemPrivUserAssignDao) tawSystemPrivUserAssignDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tawSystemPrivUserAssignManager = null;
	}

	public void testGetTawSystemPrivUserAssigns() throws Exception {
		List results = new ArrayList();
		TawSystemPrivUserAssign tawSystemPrivUserAssign = new TawSystemPrivUserAssign();
		results.add(tawSystemPrivUserAssign);

		// set expected behavior on dao
		tawSystemPrivUserAssignDao.expects(once()).method(
				"getTawSystemPrivUserAssigns").will(returnValue(results));

		List tawSystemPrivUserAssigns = tawSystemPrivUserAssignManager
				.getTawSystemPrivUserAssigns(null);
		assertTrue(tawSystemPrivUserAssigns.size() == 1);
		tawSystemPrivUserAssignDao.verify();
	}

	public void testGetTawSystemPrivUserAssign() throws Exception {
		// set expected behavior on dao
		tawSystemPrivUserAssignDao.expects(once()).method(
				"getTawSystemPrivUserAssign").will(
				returnValue(new TawSystemPrivUserAssign()));
		TawSystemPrivUserAssign tawSystemPrivUserAssign = tawSystemPrivUserAssignManager
				.getTawSystemPrivUserAssign(tawSystemPrivUserAssignId);
		assertTrue(tawSystemPrivUserAssign != null);
		tawSystemPrivUserAssignDao.verify();
	}

	public void testSaveTawSystemPrivUserAssign() throws Exception {
		TawSystemPrivUserAssign tawSystemPrivUserAssign = new TawSystemPrivUserAssign();

		// set expected behavior on dao
		tawSystemPrivUserAssignDao.expects(once()).method(
				"saveTawSystemPrivUserAssign").with(
				same(tawSystemPrivUserAssign)).isVoid();

		tawSystemPrivUserAssignManager
				.saveTawSystemPrivUserAssign(tawSystemPrivUserAssign);
		tawSystemPrivUserAssignDao.verify();
	}

	public void testAddAndRemoveTawSystemPrivUserAssign() throws Exception {
		TawSystemPrivUserAssign tawSystemPrivUserAssign = new TawSystemPrivUserAssign();

		// set required fields

		// set expected behavior on dao
		tawSystemPrivUserAssignDao.expects(once()).method(
				"saveTawSystemPrivUserAssign").with(
				same(tawSystemPrivUserAssign)).isVoid();
		tawSystemPrivUserAssignManager
				.saveTawSystemPrivUserAssign(tawSystemPrivUserAssign);
		tawSystemPrivUserAssignDao.verify();

		// reset expectations
		tawSystemPrivUserAssignDao.reset();

		tawSystemPrivUserAssignDao.expects(once()).method(
				"removeTawSystemPrivUserAssign").with(
				eq(new String(tawSystemPrivUserAssignId)));
		tawSystemPrivUserAssignManager
				.removeTawSystemPrivUserAssign(tawSystemPrivUserAssignId);
		tawSystemPrivUserAssignDao.verify();

		// reset expectations
		tawSystemPrivUserAssignDao.reset();
		// remove
		Exception ex = new ObjectRetrievalFailureException(
				TawSystemPrivUserAssign.class, tawSystemPrivUserAssign.getId());
		tawSystemPrivUserAssignDao.expects(once()).method(
				"removeTawSystemPrivUserAssign").isVoid();
		tawSystemPrivUserAssignDao.expects(once()).method(
				"getTawSystemPrivUserAssign").will(throwException(ex));
		tawSystemPrivUserAssignManager
				.removeTawSystemPrivUserAssign(tawSystemPrivUserAssignId);
		try {
			tawSystemPrivUserAssignManager
					.getTawSystemPrivUserAssign(tawSystemPrivUserAssignId);
			fail("TawSystemPrivUserAssign with identifier '"
					+ tawSystemPrivUserAssignId + "' found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		tawSystemPrivUserAssignDao.verify();
	}

	public void testAuthorization() {
		ITawSystemPrivUserAssignManager mgr = (ITawSystemPrivUserAssignManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemPrivUserAssignManager");
		TawSystemPrivAssign assign = new TawSystemPrivAssign();
		assign.setObjectid("1");
		assign.setAssigntype(StaticVariable.PRIV_ASSIGNTYPE_DEPT);
		assign.setPrivid("38");

		try {
			mgr.authorization(assign);
		} catch (AuthorizationException e) {
			e.printStackTrace();
			fail();
		}
	}
}
