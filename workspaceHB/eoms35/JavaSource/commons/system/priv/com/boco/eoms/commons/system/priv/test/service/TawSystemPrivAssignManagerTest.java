package com.boco.eoms.commons.system.priv.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.service.impl.TawSystemPrivAssignManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemPrivAssignManagerTest extends BaseManagerTestCase {
	private final String tawSystemPrivAssignId = "1";

	private TawSystemPrivAssignManagerImpl tawSystemPrivAssignManager = new TawSystemPrivAssignManagerImpl();

	private Mock tawSystemPrivAssignDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		tawSystemPrivAssignDao = new Mock(TawSystemPrivAssignDao.class);
		tawSystemPrivAssignManager
				.setTawSystemPrivAssignDao((TawSystemPrivAssignDao) tawSystemPrivAssignDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tawSystemPrivAssignManager = null;
	}

	public void testGetTawSystemPrivAssigns() throws Exception {
		List results = new ArrayList();
		TawSystemPrivAssign tawSystemPrivAssign = new TawSystemPrivAssign();
		results.add(tawSystemPrivAssign);

		// set expected behavior on dao
		tawSystemPrivAssignDao.expects(once())
				.method("getTawSystemPrivAssigns").will(returnValue(results));

		List tawSystemPrivAssigns = tawSystemPrivAssignManager
				.getTawSystemPrivAssigns(null);
		assertTrue(tawSystemPrivAssigns.size() == 1);
		tawSystemPrivAssignDao.verify();
	}

	public void testGetTawSystemPrivAssign() throws Exception {
		// set expected behavior on dao
		tawSystemPrivAssignDao.expects(once()).method("getTawSystemPrivAssign")
				.will(returnValue(new TawSystemPrivAssign()));
		TawSystemPrivAssign tawSystemPrivAssign = tawSystemPrivAssignManager
				.getTawSystemPrivAssign(tawSystemPrivAssignId);
		assertTrue(tawSystemPrivAssign != null);
		tawSystemPrivAssignDao.verify();
	}

	public void testSaveTawSystemPrivAssign() throws Exception {
		TawSystemPrivAssign tawSystemPrivAssign = new TawSystemPrivAssign();

		// set expected behavior on dao
		tawSystemPrivAssignDao.expects(once())
				.method("saveTawSystemPrivAssign").with(
						same(tawSystemPrivAssign)).isVoid();

		tawSystemPrivAssignManager.saveTawSystemPrivAssign(tawSystemPrivAssign);
		tawSystemPrivAssignDao.verify();
	}

	public void testAddAndRemoveTawSystemPrivAssign() throws Exception {
		TawSystemPrivAssign tawSystemPrivAssign = new TawSystemPrivAssign();

		// set required fields

		// set expected behavior on dao
		tawSystemPrivAssignDao.expects(once())
				.method("saveTawSystemPrivAssign").with(
						same(tawSystemPrivAssign)).isVoid();
		tawSystemPrivAssignManager.saveTawSystemPrivAssign(tawSystemPrivAssign);
		tawSystemPrivAssignDao.verify();

		// reset expectations
		tawSystemPrivAssignDao.reset();

		tawSystemPrivAssignDao.expects(once()).method(
				"removeTawSystemPrivAssign").with(
				eq(new String(tawSystemPrivAssignId)));
		tawSystemPrivAssignManager
				.removeTawSystemPrivAssign(tawSystemPrivAssignId);
		tawSystemPrivAssignDao.verify();

		// reset expectations
		tawSystemPrivAssignDao.reset();
		// remove
		Exception ex = new ObjectRetrievalFailureException(
				TawSystemPrivAssign.class, tawSystemPrivAssign.getId());
		tawSystemPrivAssignDao.expects(once()).method(
				"removeTawSystemPrivAssign").isVoid();
		tawSystemPrivAssignDao.expects(once()).method("getTawSystemPrivAssign")
				.will(throwException(ex));
		tawSystemPrivAssignManager
				.removeTawSystemPrivAssign(tawSystemPrivAssignId);
		try {
			tawSystemPrivAssignManager
					.getTawSystemPrivAssign(tawSystemPrivAssignId);
			fail("TawSystemPrivAssign with identifier '"
					+ tawSystemPrivAssignId + "' found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		tawSystemPrivAssignDao.verify();
	}
}
