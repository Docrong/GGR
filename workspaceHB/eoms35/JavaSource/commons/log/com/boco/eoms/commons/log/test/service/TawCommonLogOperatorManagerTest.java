package com.boco.eoms.commons.log.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.log.dao.TawCommonLogOperatorDao;
import com.boco.eoms.commons.log.model.TawCommonLogOperator;
import com.boco.eoms.commons.log.service.impl.TawCommonLogOperatorManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonLogOperatorManagerTest extends BaseManagerTestCase {
	private final String tawCommonLogOperatorId = "1";

	private TawCommonLogOperatorManagerImpl tawCommonLogOperatorManager = new TawCommonLogOperatorManagerImpl();

	private Mock tawCommonLogOperatorDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		tawCommonLogOperatorDao = new Mock(TawCommonLogOperatorDao.class);
		tawCommonLogOperatorManager
				.setTawCommonLogOperatorDao((TawCommonLogOperatorDao) tawCommonLogOperatorDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tawCommonLogOperatorManager = null;
	}

	public void testGetTawCommonLogOperators() throws Exception {
		List results = new ArrayList();
		TawCommonLogOperator tawCommonLogOperator = new TawCommonLogOperator();
		results.add(tawCommonLogOperator);

		// set expected behavior on dao
		tawCommonLogOperatorDao.expects(once()).method(
				"getTawCommonLogOperators").will(returnValue(results));

		List tawCommonLogOperators = tawCommonLogOperatorManager
				.getTawCommonLogOperators(null);
		assertTrue(tawCommonLogOperators.size() == 1);
		tawCommonLogOperatorDao.verify();
	}

	public void testGetTawCommonLogOperator() throws Exception {
		// set expected behavior on dao
		tawCommonLogOperatorDao.expects(once()).method(
				"getTawCommonLogOperator").will(
				returnValue(new TawCommonLogOperator()));
		TawCommonLogOperator tawCommonLogOperator = tawCommonLogOperatorManager
				.getTawCommonLogOperator(tawCommonLogOperatorId);
		assertTrue(tawCommonLogOperator != null);
		tawCommonLogOperatorDao.verify();
	}

	public void testSaveTawCommonLogOperator() throws Exception {
		TawCommonLogOperator tawCommonLogOperator = new TawCommonLogOperator();

		// set expected behavior on dao
		tawCommonLogOperatorDao.expects(once()).method(
				"saveTawCommonLogOperator").with(same(tawCommonLogOperator))
				.isVoid();

		tawCommonLogOperatorManager
				.saveTawCommonLogOperator(tawCommonLogOperator);
		tawCommonLogOperatorDao.verify();
	}

	public void testAddAndRemoveTawCommonLogOperator() throws Exception {
		TawCommonLogOperator tawCommonLogOperator = new TawCommonLogOperator();

		// set required fields

		// set expected behavior on dao
		tawCommonLogOperatorDao.expects(once()).method(
				"saveTawCommonLogOperator").with(same(tawCommonLogOperator))
				.isVoid();
		tawCommonLogOperatorManager
				.saveTawCommonLogOperator(tawCommonLogOperator);
		tawCommonLogOperatorDao.verify();

		// reset expectations
		tawCommonLogOperatorDao.reset();

		tawCommonLogOperatorDao.expects(once()).method(
				"removeTawCommonLogOperator").with(
				eq(new String(tawCommonLogOperatorId)));
		tawCommonLogOperatorManager
				.removeTawCommonLogOperator(tawCommonLogOperatorId);
		tawCommonLogOperatorDao.verify();

		// reset expectations
		tawCommonLogOperatorDao.reset();
		// remove
		Exception ex = new ObjectRetrievalFailureException(
				TawCommonLogOperator.class, tawCommonLogOperator.getId());
		tawCommonLogOperatorDao.expects(once()).method(
				"removeTawCommonLogOperator").isVoid();
		tawCommonLogOperatorDao.expects(once()).method(
				"getTawCommonLogOperator").will(throwException(ex));
		tawCommonLogOperatorManager
				.removeTawCommonLogOperator(tawCommonLogOperatorId);
		try {
			tawCommonLogOperatorManager
					.getTawCommonLogOperator(tawCommonLogOperatorId);
			fail("TawCommonLogOperator with identifier '"
					+ tawCommonLogOperatorId + "' found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		tawCommonLogOperatorDao.verify();
	}

}
