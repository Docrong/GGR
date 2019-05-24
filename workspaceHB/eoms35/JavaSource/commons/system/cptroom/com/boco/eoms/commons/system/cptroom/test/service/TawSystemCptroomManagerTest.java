package com.boco.eoms.commons.system.cptroom.test.service;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.cptroom.dao.TawSystemCptroomDao;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager;
import com.boco.eoms.commons.system.cptroom.service.impl.TawSystemCptroomManagerImpl;

public class TawSystemCptroomManagerTest extends BaseManagerTestCase {
	private final String tawSystemCptroomId = "1";

	private TawSystemCptroomManagerImpl tawSystemCptroomManager = new TawSystemCptroomManagerImpl();

	private Mock tawSystemCptroomDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		tawSystemCptroomDao = new Mock(TawSystemCptroomDao.class);
		tawSystemCptroomManager
				.setTawSystemCptroomDao((TawSystemCptroomDao) tawSystemCptroomDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tawSystemCptroomManager = null;
	}

	public void testGetTawSystemCptrooms() throws Exception {
		List results = new ArrayList();
		TawSystemCptroom tawSystemCptroom = new TawSystemCptroom();
		results.add(tawSystemCptroom);

		// set expected behavior on dao
		tawSystemCptroomDao.expects(once()).method("getTawSystemCptrooms")
				.will(returnValue(results));

		List tawSystemCptrooms = tawSystemCptroomManager
				.getTawSystemCptrooms(null);
		assertTrue(tawSystemCptrooms.size() == 1);
		tawSystemCptroomDao.verify();
	}

	public void testGetTawSystemCptroom() throws Exception {
		// set expected behavior on dao
		tawSystemCptroomDao.expects(once()).method("getTawSystemCptroom").will(
				returnValue(new TawSystemCptroom()));
		TawSystemCptroom tawSystemCptroom = tawSystemCptroomManager
				.getTawSystemCptroom(tawSystemCptroomId);
		assertTrue(tawSystemCptroom != null);
		tawSystemCptroomDao.verify();
	}

	public void testSaveTawSystemCptroom() throws Exception {
		TawSystemCptroom tawSystemCptroom = new TawSystemCptroom();

		// set expected behavior on dao
		tawSystemCptroomDao.expects(once()).method("saveTawSystemCptroom")
				.with(same(tawSystemCptroom)).isVoid();

		tawSystemCptroomManager.saveTawSystemCptroom(tawSystemCptroom);
		tawSystemCptroomDao.verify();
		ITawSystemCptroomManager mgr = (ITawSystemCptroomManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemCptroomManager");
		tawSystemCptroom.setAddress("123");
		tawSystemCptroom.setDeleted(new Integer("1"));
		tawSystemCptroom.setDeptid("123");
		tawSystemCptroom.setEndtime("123");
		
		mgr.saveTawSystemCptroom(tawSystemCptroom );
	}

	public void testAddAndRemoveTawSystemCptroom() throws Exception {
		TawSystemCptroom tawSystemCptroom = new TawSystemCptroom();

		// set required fields

		// set expected behavior on dao
		tawSystemCptroomDao.expects(once()).method("saveTawSystemCptroom")
				.with(same(tawSystemCptroom)).isVoid();
		tawSystemCptroomManager.saveTawSystemCptroom(tawSystemCptroom);
		tawSystemCptroomDao.verify();

		// reset expectations
		tawSystemCptroomDao.reset();

		tawSystemCptroomDao.expects(once()).method("removeTawSystemCptroom")
				.with(eq(new Integer(tawSystemCptroomId)));
		tawSystemCptroomManager.removeTawSystemCptroom(tawSystemCptroomId);
		tawSystemCptroomDao.verify();

		// reset expectations
		tawSystemCptroomDao.reset();
		// remove
		Exception ex = new ObjectRetrievalFailureException(
				TawSystemCptroom.class, tawSystemCptroom.getId());
		tawSystemCptroomDao.expects(once()).method("removeTawSystemCptroom")
				.isVoid();
		tawSystemCptroomDao.expects(once()).method("getTawSystemCptroom").will(
				throwException(ex));
		tawSystemCptroomManager.removeTawSystemCptroom(tawSystemCptroomId);
		try {
			tawSystemCptroomManager.getTawSystemCptroom(tawSystemCptroomId);
			fail("TawSystemCptroom with identifier '" + tawSystemCptroomId
					+ "' found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		tawSystemCptroomDao.verify();
	}
}
