package com.boco.eoms.commons.log.test.service;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.log.dao.TawCommonLogDeployDao;
import com.boco.eoms.commons.log.model.TawCommonLogDeploy;

import com.boco.eoms.commons.log.service.impl.TawCommonLogDeployManagerImpl;

public class TawCommonLogDeployManagerTest extends BaseManagerTestCase {
	private final String tawCommonLogDeployId = "1";

	private TawCommonLogDeployManagerImpl tawCommonLogDeployManager = new TawCommonLogDeployManagerImpl();

	private Mock tawCommonLogDeployDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		tawCommonLogDeployDao = new Mock(TawCommonLogDeployDao.class);
		tawCommonLogDeployManager
				.setTawCommonLogDeployDao((TawCommonLogDeployDao) tawCommonLogDeployDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tawCommonLogDeployManager = null;
	}

	public void testGetTawCommonLogDeploys() throws Exception {
		List results = new ArrayList();
		TawCommonLogDeploy tawCommonLogDeploy = new TawCommonLogDeploy();
		results.add(tawCommonLogDeploy);
		// set expected behavior on dao
		tawCommonLogDeployDao.expects(once()).method("getTawCommonLogDeploys")
				.will(returnValue(results));

		List tawCommonLogDeploys = tawCommonLogDeployManager
				.getTawCommonLogDeploys(null);
		assertTrue(tawCommonLogDeploys.size() == 1);
		tawCommonLogDeployDao.verify();
	}

	public void testGetTawCommonLogDeploy() throws Exception {

		tawCommonLogDeployDao.expects(once()).method("getTawCommonLogDeploy")
				.will(returnValue(new TawCommonLogDeploy()));
		TawCommonLogDeploy tawCommonLogDeploy = tawCommonLogDeployManager
				.getTawCommonLogDeploy(tawCommonLogDeployId);
		assertTrue(tawCommonLogDeploy != null);
		tawCommonLogDeployDao.verify();
	}

	public void testSaveTawCommonLogDeploy() throws Exception {
		TawCommonLogDeploy tawCommonLogDeploy = new TawCommonLogDeploy();

		tawCommonLogDeployDao.expects(once()).method("saveTawCommonLogDeploy")
				.with(same(tawCommonLogDeploy)).isVoid();
		tawCommonLogDeployManager.saveTawCommonLogDeploy(tawCommonLogDeploy);
		tawCommonLogDeployDao.verify();
	}

	public void testAddAndRemoveTawCommonLogDeploy() throws Exception {
		TawCommonLogDeploy tawCommonLogDeploy = new TawCommonLogDeploy();

		tawCommonLogDeployDao.expects(once()).method("saveTawCommonLogDeploy")
				.with(same(tawCommonLogDeploy)).isVoid();
		tawCommonLogDeployManager.saveTawCommonLogDeploy(tawCommonLogDeploy);
		tawCommonLogDeployDao.verify();
		// reset expectations tawCommonLogDeployDao.reset();

		tawCommonLogDeployDao.expects(once())
				.method("removeTawCommonLogDeploy").with(
						eq(new String(tawCommonLogDeployId)));
		tawCommonLogDeployManager
				.removeTawCommonLogDeploy(tawCommonLogDeployId);
		tawCommonLogDeployDao.verify();
		// reset expectations tawCommonLogDeployDao.reset(); // remove Exception
		Exception ex = new ObjectRetrievalFailureException(
				TawCommonLogDeploy.class, tawCommonLogDeploy.getId());
		tawCommonLogDeployDao.expects(once())
				.method("removeTawCommonLogDeploy").isVoid();
		tawCommonLogDeployDao.expects(once()).method("getTawCommonLogDeploy")
				.will(throwException(ex));
		tawCommonLogDeployManager
				.removeTawCommonLogDeploy(tawCommonLogDeployId);
		try {
			tawCommonLogDeployManager
					.getTawCommonLogDeploy(tawCommonLogDeployId);
			fail("TawCommonLogDeploy with identifier '" + tawCommonLogDeployId
					+ "'+found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		tawCommonLogDeployDao.verify();
	}

}
