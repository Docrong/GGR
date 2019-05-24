package com.boco.eoms.commons.message.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.message.model.TawCommonMessageMonitor;
import com.boco.eoms.commons.message.dao.TawCommonMessageMonitorDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageMonitorDaoTest extends BaseDaoTestCase {

	private TawCommonMessageMonitorDao dao = null;

	public void setTawCommonMessageMonitorDao(TawCommonMessageMonitorDao dao) {
		this.dao = dao;
	}

	public void testAddTawCommonMessageMonitor() throws Exception {
		TawCommonMessageMonitor tawCommonMessageMonitor = new TawCommonMessageMonitor();
		tawCommonMessageMonitor.setCycle(new Integer(1));
		tawCommonMessageMonitor.setHieAmount(new Integer(2));
		tawCommonMessageMonitor.setUrgent("2");

		dao.saveTawCommonMessageMonitor(tawCommonMessageMonitor);

		assertNotNull(tawCommonMessageMonitor.getMonitorId());

	}

	public void testGetTawCommonMessageMonitor() throws Exception {
		TawCommonMessageMonitor tawCommonMessageMonitor = new TawCommonMessageMonitor();
		tawCommonMessageMonitor.setCycle(new Integer(1));
		tawCommonMessageMonitor.setHieAmount(new Integer(2));
		tawCommonMessageMonitor.setUrgent("2");

		dao.saveTawCommonMessageMonitor(tawCommonMessageMonitor);

		TawCommonMessageMonitor tawCommonMessageMonitors = dao
				.getTawCommonMessageMonitor(tawCommonMessageMonitor
						.getMonitorId());
		assertSame(tawCommonMessageMonitor.getMonitorId(),
				tawCommonMessageMonitors.getMonitorId());
	}

	public void testGetTawCommonMessageMonitors() throws Exception {

		TawCommonMessageMonitor tawCommonMessageMonitor = new TawCommonMessageMonitor();
		tawCommonMessageMonitor.setCycle(new Integer(1));
		tawCommonMessageMonitor.setHieAmount(new Integer(2));
		tawCommonMessageMonitor.setUrgent("2");

		dao.saveTawCommonMessageMonitor(tawCommonMessageMonitor);
		TawCommonMessageMonitor tawCommonMessageMonitors = new TawCommonMessageMonitor();

		List results = dao
				.getTawCommonMessageMonitors(tawCommonMessageMonitors);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawCommonMessageMonitor() throws Exception {
		TawCommonMessageMonitor tawCommonMessageMonitor = new TawCommonMessageMonitor();
		tawCommonMessageMonitor.setCycle(new Integer(1));
		tawCommonMessageMonitor.setHieAmount(new Integer(2));
		tawCommonMessageMonitor.setUrgent("2");

		dao.saveTawCommonMessageMonitor(tawCommonMessageMonitor);
		TawCommonMessageMonitor tawCommonMessageMonitors = dao
				.getTawCommonMessageMonitor(tawCommonMessageMonitor
						.getMonitorId());

		// update required fields

		dao.saveTawCommonMessageMonitor(tawCommonMessageMonitors);
		assertSame(tawCommonMessageMonitor.getMonitorId(),
				tawCommonMessageMonitors.getMonitorId());

	}

	public void testRemoveTawCommonMessageMonitor() throws Exception {
		TawCommonMessageMonitor tawCommonMessageMonitor = new TawCommonMessageMonitor();
		tawCommonMessageMonitor.setCycle(new Integer(1));
		tawCommonMessageMonitor.setHieAmount(new Integer(2));
		tawCommonMessageMonitor.setUrgent("2");

		dao.saveTawCommonMessageMonitor(tawCommonMessageMonitor);
		dao.removeTawCommonMessageMonitor(tawCommonMessageMonitor
				.getMonitorId());
		try {
			dao.getTawCommonMessageMonitor(tawCommonMessageMonitor
					.getMonitorId());
			fail("tawCommonMessageMonitor found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}
}
