package com.boco.eoms.commons.system.role.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.file.exception.FMException;
import com.boco.eoms.commons.system.role.dao.ITawSystemRoleImportDao;
import com.boco.eoms.commons.system.role.model.TawSystemRoleImport;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleImportManager;
import com.boco.eoms.commons.system.role.service.impl.TawSystemRoleImportManagerImpl;

public class TawSystemRoleImportManagerTest extends BaseManagerTestCase {
	private final String tawSystemRoleImportId = "1";

	private TawSystemRoleImportManagerImpl tawSystemRoleImportManager = new TawSystemRoleImportManagerImpl();

	private Mock tawSystemRoleImportDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		tawSystemRoleImportDao = new Mock(ITawSystemRoleImportDao.class);
		tawSystemRoleImportManager
				.setTawSystemRoleImportDao((ITawSystemRoleImportDao) tawSystemRoleImportDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tawSystemRoleImportManager = null;
	}

	public void testGetTawSystemRoleImports() throws Exception {
		List results = new ArrayList();
		TawSystemRoleImport tawSystemRoleImport = new TawSystemRoleImport();
		results.add(tawSystemRoleImport);

		// set expected behavior on dao
		tawSystemRoleImportDao.expects(once())
				.method("getTawSystemRoleImports").will(returnValue(results));

		List tawSystemRoleImports = tawSystemRoleImportManager
				.getTawSystemRoleImports(null);
		assertTrue(tawSystemRoleImports.size() == 1);
		tawSystemRoleImportDao.verify();
	}

	public void testGetTawSystemRoleImport() throws Exception {
		// set expected behavior on dao
		tawSystemRoleImportDao.expects(once()).method("getTawSystemRoleImport")
				.will(returnValue(new TawSystemRoleImport()));
		TawSystemRoleImport tawSystemRoleImport = tawSystemRoleImportManager
				.getTawSystemRoleImport(tawSystemRoleImportId);
		assertTrue(tawSystemRoleImport != null);
		tawSystemRoleImportDao.verify();
	}

	public void testSaveTawSystemRoleImport() throws Exception {
		TawSystemRoleImport tawSystemRoleImport = new TawSystemRoleImport();

		// set expected behavior on dao
		tawSystemRoleImportDao.expects(once())
				.method("saveTawSystemRoleImport").with(
						same(tawSystemRoleImport)).isVoid();

		tawSystemRoleImportManager.saveTawSystemRoleImport(tawSystemRoleImport);
		tawSystemRoleImportDao.verify();
	}

	public void testAddAndRemoveTawSystemRoleImport() throws Exception {
		TawSystemRoleImport tawSystemRoleImport = new TawSystemRoleImport();

		// set required fields
		tawSystemRoleImport
				.setRoleId("UhAbToGtTmZgLeNnMkZuInGzQsYgHoKlNwYpFbJnKvGzZvGvCp");
		tawSystemRoleImport
				.setSubRoleId("UpCpIlHqHdKnPfVvCsJaZtEjIbBkWaXqJpMiHnViXxNwWeXcVd");
		tawSystemRoleImport
				.setVersion("UvRdHhKyGdEcLfMrDpThIiNvKxMuUkRjPgVyScEbDqJdAyZzTc");
		tawSystemRoleImport.setVersionAt(new Date());

		// set expected behavior on dao
		tawSystemRoleImportDao.expects(once())
				.method("saveTawSystemRoleImport").with(
						same(tawSystemRoleImport)).isVoid();
		tawSystemRoleImportManager.saveTawSystemRoleImport(tawSystemRoleImport);
		tawSystemRoleImportDao.verify();

		// reset expectations
		tawSystemRoleImportDao.reset();

		tawSystemRoleImportDao.expects(once()).method(
				"removeTawSystemRoleImport").with(
				eq(new String(tawSystemRoleImportId)));
		tawSystemRoleImportManager
				.removeTawSystemRoleImport(tawSystemRoleImportId);
		tawSystemRoleImportDao.verify();

		// reset expectations
		tawSystemRoleImportDao.reset();
		// remove
		Exception ex = new ObjectRetrievalFailureException(
				TawSystemRoleImport.class, tawSystemRoleImport.getId());
		tawSystemRoleImportDao.expects(once()).method(
				"removeTawSystemRoleImport").isVoid();
		tawSystemRoleImportDao.expects(once()).method("getTawSystemRoleImport")
				.will(throwException(ex));
		tawSystemRoleImportManager
				.removeTawSystemRoleImport(tawSystemRoleImportId);
		try {
			tawSystemRoleImportManager
					.getTawSystemRoleImport(tawSystemRoleImportId);
			fail("TawSystemRoleImport with identifier '"
					+ tawSystemRoleImportId + "' found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		tawSystemRoleImportDao.verify();
	}

	public void testMappingRoleExcel() {
		ITawSystemRoleImportManager mgr = (ITawSystemRoleImportManager) ApplicationContextHolder
				.getInstance().getBean("tawSystemRoleImportManager");
		try {
			Map map = mgr.mappingRoleExcel("c:/role.xls");
		} catch (FMException e) {
			fail();
		}

	}

	public void testImportRole() {
		ITawSystemRoleImportManager mgr = (ITawSystemRoleImportManager) ApplicationContextHolder
				.getInstance().getBean("tawSystemRoleImportManager");
		TawSystemRoleImport tawSystemRoleImport = new TawSystemRoleImport();
		tawSystemRoleImport.setVersion("v3.5.2");
		Map map = null;
		try {
			map = mgr.mappingRoleExcel("c:/role.xls");
		} catch (FMException e) {
			fail();
		}
		assertNotNull(map);
		List list = (List) map.get(new Integer(0));
		try {
			mgr.importRole(list, tawSystemRoleImport);
		} catch (RuntimeException re) {
			re.printStackTrace();
			fail();
		}

	}
}
