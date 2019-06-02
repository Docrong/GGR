package com.boco.eoms.commons.system.priv.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.service.impl.TawSystemPrivOperationManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemPrivOperationManagerTest extends BaseManagerTestCase {
	private final String tawSystemPrivOperationId = "1";

	private TawSystemPrivOperationManagerImpl tawSystemPrivOperationManager = new TawSystemPrivOperationManagerImpl();

	private Mock tawSystemPrivOperationDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		tawSystemPrivOperationDao = new Mock(TawSystemPrivOperationDao.class);
		tawSystemPrivOperationManager
				.setTawSystemPrivOperationDao((TawSystemPrivOperationDao) tawSystemPrivOperationDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tawSystemPrivOperationManager = null;
	}

	public void testGetTawSystemPrivOperations() throws Exception {
		List results = new ArrayList();
		TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
		results.add(tawSystemPrivOperation);

		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method(
				"getTawSystemPrivOperations").will(returnValue(results));

		List tawSystemPrivOperations = tawSystemPrivOperationManager
				.getTawSystemPrivOperations(null);
		assertTrue(tawSystemPrivOperations.size() == 1);
		tawSystemPrivOperationDao.verify();
	}

	public void testGetTawSystemPrivOperation() throws Exception {
		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method(
				"getTawSystemPrivOperation").will(
				returnValue(new TawSystemPrivOperation()));
		TawSystemPrivOperation tawSystemPrivOperation = tawSystemPrivOperationManager
				.getTawSystemPrivOperation(tawSystemPrivOperationId);
		assertTrue(tawSystemPrivOperation != null);
		tawSystemPrivOperationDao.verify();
	}

	public void testSaveTawSystemPrivOperation() throws Exception {
		TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();

		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method(
				"saveTawSystemPrivOperation")
				.with(same(tawSystemPrivOperation)).isVoid();

		tawSystemPrivOperationManager
				.saveTawSystemPrivOperation(tawSystemPrivOperation);
		tawSystemPrivOperationDao.verify();
	}

	public void testAddAndRemoveTawSystemPrivOperation() throws Exception {
		TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();

		// set required fields

		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method(
				"saveTawSystemPrivOperation")
				.with(same(tawSystemPrivOperation)).isVoid();
		tawSystemPrivOperationManager
				.saveTawSystemPrivOperation(tawSystemPrivOperation);
		tawSystemPrivOperationDao.verify();

		// reset expectations
		tawSystemPrivOperationDao.reset();

		tawSystemPrivOperationDao.expects(once()).method(
				"removeTawSystemPrivOperation").with(
				eq(new String(tawSystemPrivOperationId)));
		tawSystemPrivOperationManager
				.removeTawSystemPrivOperation(tawSystemPrivOperationId);
		tawSystemPrivOperationDao.verify();

		// reset expectations
		tawSystemPrivOperationDao.reset();
		// remove
		Exception ex = new ObjectRetrievalFailureException(
				TawSystemPrivOperation.class, tawSystemPrivOperation.getId());
		tawSystemPrivOperationDao.expects(once()).method(
				"removeTawSystemPrivOperation").isVoid();
		tawSystemPrivOperationDao.expects(once()).method(
				"getTawSystemPrivOperation").will(throwException(ex));
		tawSystemPrivOperationManager
				.removeTawSystemPrivOperation(tawSystemPrivOperationId);
		try {
			tawSystemPrivOperationManager
					.getTawSystemPrivOperation(tawSystemPrivOperationId);
			fail("TawSystemPrivOperation with identifier '"
					+ tawSystemPrivOperationId + "' found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		tawSystemPrivOperationDao.verify();
	}

	/**
	 * 测试getTawSystemPrivOperations(final Integer curPage, final Integer
	 * pageSize)函数
	 */
	public void testGetTawSystemPrivOperations_Ex1() throws Exception {
		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method(
				"getTawSystemPrivOperations").will(returnValue(new HashMap()));

		Integer curPage = new Integer(1);
		Integer pageSize = new Integer(5);
		Map tawSystemPrivOperation = tawSystemPrivOperationManager
				.getTawSystemPrivOperations(curPage, pageSize);
		assertTrue(tawSystemPrivOperation != null);
		tawSystemPrivOperationDao.verify();
	}

	/**
	 * 测试getTawSystemPrivOperations(final Integer curPage, final Integer
	 * pageSize, final String whereStr)函数
	 */
	public void testGetTawSystemPrivOperations_Ex2() throws Exception {
		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method(
				"getTawSystemPrivOperations").will(returnValue(new HashMap()));

		Integer curPage = new Integer(1);
		Integer pageSize = new Integer(5);
		String whereStr = "";
		Map tawSystemPrivOperation = tawSystemPrivOperationManager
				.getTawSystemPrivOperations(curPage, pageSize, whereStr);
		assertTrue(tawSystemPrivOperation != null);
		tawSystemPrivOperationDao.verify();
	}

	/**
	 * 测试getAllSubObjects函数
	 */
	public void testGetAllSubObjects() throws Exception {
		List results = new ArrayList();
		TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
		results.add(tawSystemPrivOperation);

		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method("getAllSubObjects")
				.will(returnValue(results));

		String code = "99";
		List tawSystemPrivOperations = tawSystemPrivOperationManager
				.getAllSubObjects(code);
		assertTrue(tawSystemPrivOperations.size() >= 0);
		tawSystemPrivOperationDao.verify();

	}

	/**
	 * 测试getAllSubModules函数
	 */
	public void testGetAllSubModules() throws Exception {
		List results = new ArrayList();
		TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
		results.add(tawSystemPrivOperation);

		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method("getAllSubModules")
				.will(returnValue(results));

		String code = "99";
		List tawSystemPrivOperations = tawSystemPrivOperationManager
				.getAllSubModules(code);
		assertTrue(tawSystemPrivOperations.size() >= 0);
		tawSystemPrivOperationDao.verify();

	}

	/**
	 * 测试getAllSubOperations函数
	 */
	public void testGetAllSubOperations() throws Exception {
		List results = new ArrayList();
		TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
		results.add(tawSystemPrivOperation);

		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method("getAllSubOperations")
				.will(returnValue(results));

		String code = "99";
		List tawSystemPrivOperations = tawSystemPrivOperationManager
				.getAllSubOperations(code);
		assertTrue(tawSystemPrivOperations.size() >= 0);
		tawSystemPrivOperationDao.verify();
	}

	/**
	 * 测试getOperations函数
	 */
	public void testGetOperations() throws Exception {
		List results = new ArrayList();
		TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
		results.add(tawSystemPrivOperation);

		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method("getOperations").will(
				returnValue(results));

		String code = "99";
		List tawSystemPrivOperations = tawSystemPrivOperationManager
				.getOperations(code);
		assertTrue(tawSystemPrivOperations.size() >= 0);
		tawSystemPrivOperationDao.verify();
	}

	public void testGetObjects() throws Exception {
		List results = new ArrayList();
		TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
		results.add(tawSystemPrivOperation);

		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method("getObjects").will(
				returnValue(results));

		String code = "99";
		List tawSystemPrivOperations = tawSystemPrivOperationManager
				.getObjects(code);
		assertTrue(tawSystemPrivOperations.size() >= 0);
		tawSystemPrivOperationDao.verify();
	}

	public void testGetModules() throws Exception {
		List results = new ArrayList();
		TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
		results.add(tawSystemPrivOperation);

		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method("getModules").will(
				returnValue(results));

		String code = "99";
		List tawSystemPrivOperations = tawSystemPrivOperationManager
				.getModules(code, "2");
		assertTrue(tawSystemPrivOperations.size() >= 0);
		tawSystemPrivOperationDao.verify();
	}

	public void testGetDirectSubModules() throws Exception {
		List results = new ArrayList();
		TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
		results.add(tawSystemPrivOperation);

		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method("getDirectSubModules")
				.will(returnValue(results));

		String _strParentId = "99";
		List tawSystemPrivOperations = tawSystemPrivOperationManager
				.getDirectSubModules(_strParentId);
		assertTrue(tawSystemPrivOperations.size() >= 0);
		tawSystemPrivOperationDao.verify();
	}

	public void testGetParentModuleName() throws Exception {

		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method("getParentModuleName")
				.will(returnValue(new String()));

		String _strParentId = "99";
		String tawSystemPrivOperations = tawSystemPrivOperationManager
				.getParentModuleName(_strParentId);
		assertTrue(tawSystemPrivOperations != null);
		tawSystemPrivOperationDao.verify();
	}

	public void testGetFatherModule() throws Exception {
		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method("getFatherModule")
				.will(returnValue(new TawSystemPrivOperation()));

		String childObjectCode = "99";
		TawSystemPrivOperation tawSystemPrivOperation = tawSystemPrivOperationManager
				.getFatherModule(childObjectCode);
		assertTrue(tawSystemPrivOperation != null);
		tawSystemPrivOperationDao.verify();
	}

	public void testGetAllFatherModules() throws Exception {
		List results = new ArrayList();
		TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
		results.add(tawSystemPrivOperation);

		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method("getAllFatherModules")
				.will(returnValue(results));

		String code = "99";
		List tawSystemPrivOperations = tawSystemPrivOperationManager
				.getAllFatherModules(code);
		assertTrue(tawSystemPrivOperations.size() >= 0);
		tawSystemPrivOperationDao.verify();
	}

	public void testGetTawSystemPrivOpt() throws Exception {
		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method("getTawSystemPrivOpt")
				.will(returnValue(new TawSystemPrivOperation()));

		String code = "99";
		TawSystemPrivOperation tawSystemPrivOperation = tawSystemPrivOperationManager
				.getTawSystemPrivOpt(code);
		assertTrue(tawSystemPrivOperation != null);
		tawSystemPrivOperationDao.verify();
	}

	public void testRemoveTawSystemPrivOperationByCode() throws Exception {
		// set expected behavior on dao
		tawSystemPrivOperationDao.expects(once()).method(
				"removeTawSystemPrivOperationByCode").isVoid();

		String _strCode = "99";
		tawSystemPrivOperationManager
				.removeTawSystemPrivOperationByCode(_strCode);
		tawSystemPrivOperationDao.verify();
	}

}
