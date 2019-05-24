package com.boco.eoms.workbench.infopub.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.workbench.infopub.model.ThreadPermimissionOrg;
import com.boco.eoms.workbench.infopub.dao.ThreadPermimissionOrgDao;

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
public class ThreadPermimissionOrgDaoTest extends BaseDaoTestCase {
	private String threadPermimissionOrgId = new String("1");

	private ThreadPermimissionOrgDao dao = null;

	public void setThreadPermimissionOrgDao(ThreadPermimissionOrgDao dao) {
		this.dao = dao;
	}

	public void testAddThreadPermimissionOrg() throws Exception {
		ThreadPermimissionOrg threadPermimissionOrg = new ThreadPermimissionOrg();

		// set required fields

		dao.saveThreadPermimissionOrg(threadPermimissionOrg);

		// verify a primary key was assigned
		assertNotNull(threadPermimissionOrg.getId());

		// verify set fields are same after save
	}

	public void testGetThreadPermimissionOrg() throws Exception {
		ThreadPermimissionOrg threadPermimissionOrg = dao
				.getThreadPermimissionOrg(threadPermimissionOrgId);
		assertNotNull(threadPermimissionOrg);
	}

	public void testGetThreadPermimissionOrgs() throws Exception {
		ThreadPermimissionOrg threadPermimissionOrg = new ThreadPermimissionOrg();

		List results = dao.getThreadPermimissionOrgs(threadPermimissionOrg);
		assertTrue(results.size() > 0);
	}

	public void testSaveThreadPermimissionOrg() throws Exception {
		ThreadPermimissionOrg threadPermimissionOrg = dao
				.getThreadPermimissionOrg(threadPermimissionOrgId);

		// update required fields

		dao.saveThreadPermimissionOrg(threadPermimissionOrg);

	}

	public void testRemoveThreadPermimissionOrg() throws Exception {
		String removeId = new String("3");
		dao.removeThreadPermimissionOrg(removeId);
		try {
			dao.getThreadPermimissionOrg(removeId);
			fail("threadPermimissionOrg found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}
}
