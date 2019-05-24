package com.boco.eoms.workbench.infopub.test.mgr;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.workbench.infopub.dao.ThreadPermimissionOrgDao;
import com.boco.eoms.workbench.infopub.mgr.impl.ThreadPermimissionOrgManagerImpl;
import com.boco.eoms.workbench.infopub.model.ThreadPermimissionOrg;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * 
 * <p>
 * Title:信息（贴子）记录组织结构权限
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:11:28 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadPermimissionOrgManagerTest extends BaseManagerTestCase {
	private final String threadPermimissionOrgId = "1";

	private ThreadPermimissionOrgManagerImpl threadPermimissionOrgManager = new ThreadPermimissionOrgManagerImpl();

	private Mock threadPermimissionOrgDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		threadPermimissionOrgDao = new Mock(ThreadPermimissionOrgDao.class);
		threadPermimissionOrgManager
				.setThreadPermimissionOrgDao((ThreadPermimissionOrgDao) threadPermimissionOrgDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		threadPermimissionOrgManager = null;
	}

	public void testGetThreadPermimissionOrgs() throws Exception {
		List results = new ArrayList();
		ThreadPermimissionOrg threadPermimissionOrg = new ThreadPermimissionOrg();
		results.add(threadPermimissionOrg);

		// set expected behavior on dao
		threadPermimissionOrgDao.expects(once()).method(
				"getThreadPermimissionOrgs").will(returnValue(results));

		List threadPermimissionOrgs = threadPermimissionOrgManager
				.getThreadPermimissionOrgs(null);
		assertTrue(threadPermimissionOrgs.size() == 1);
		threadPermimissionOrgDao.verify();
	}

	public void testGetThreadPermimissionOrg() throws Exception {
		// set expected behavior on dao
		threadPermimissionOrgDao.expects(once()).method(
				"getThreadPermimissionOrg").will(
				returnValue(new ThreadPermimissionOrg()));
		ThreadPermimissionOrg threadPermimissionOrg = threadPermimissionOrgManager
				.getThreadPermimissionOrg(threadPermimissionOrgId);
		assertTrue(threadPermimissionOrg != null);
		threadPermimissionOrgDao.verify();
	}

	public void testSaveThreadPermimissionOrg() throws Exception {
		ThreadPermimissionOrg threadPermimissionOrg = new ThreadPermimissionOrg();

		// set expected behavior on dao
		threadPermimissionOrgDao.expects(once()).method(
				"saveThreadPermimissionOrg").with(same(threadPermimissionOrg))
				.isVoid();

		threadPermimissionOrgManager
				.saveThreadPermimissionOrg(threadPermimissionOrg);
		threadPermimissionOrgDao.verify();
	}

	public void testAddAndRemoveThreadPermimissionOrg() throws Exception {
		ThreadPermimissionOrg threadPermimissionOrg = new ThreadPermimissionOrg();

		// set required fields

		// set expected behavior on dao
		threadPermimissionOrgDao.expects(once()).method(
				"saveThreadPermimissionOrg").with(same(threadPermimissionOrg))
				.isVoid();
		threadPermimissionOrgManager
				.saveThreadPermimissionOrg(threadPermimissionOrg);
		threadPermimissionOrgDao.verify();

		// reset expectations
		threadPermimissionOrgDao.reset();

		threadPermimissionOrgDao.expects(once()).method(
				"removeThreadPermimissionOrg").with(
				eq(new String(threadPermimissionOrgId)));
		threadPermimissionOrgManager
				.removeThreadPermimissionOrg(threadPermimissionOrgId);
		threadPermimissionOrgDao.verify();

		// reset expectations
		threadPermimissionOrgDao.reset();
		// remove
		Exception ex = new ObjectRetrievalFailureException(
				ThreadPermimissionOrg.class, threadPermimissionOrg.getId());
		threadPermimissionOrgDao.expects(once()).method(
				"removeThreadPermimissionOrg").isVoid();
		threadPermimissionOrgDao.expects(once()).method(
				"getThreadPermimissionOrg").will(throwException(ex));
		threadPermimissionOrgManager
				.removeThreadPermimissionOrg(threadPermimissionOrgId);
		try {
			threadPermimissionOrgManager
					.getThreadPermimissionOrg(threadPermimissionOrgId);
			fail("ThreadPermimissionOrg with identifier '"
					+ threadPermimissionOrgId + "' found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		threadPermimissionOrgDao.verify();
	}
}
