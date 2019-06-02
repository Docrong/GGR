package com.boco.eoms.workbench.infopub.test.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.log4j.Logger;
import com.boco.eoms.workbench.infopub.dao.ThreadDao;
import com.boco.eoms.workbench.infopub.model.Thread;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadForm;

/**
 * 
 * <p>
 * Title:信息（贴子）dao测试类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:10:29 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadDaoTest extends BaseDaoTestCase {

	private final static Logger logger = Logger.getLogger(ThreadDaoTest.class);

	private String threadId = new String("1");

	private ThreadDao dao = null;

	public void setThreadDao(ThreadDao dao) {
		this.dao = dao;
	}

	public void testAddThread() throws Exception {
		Thread thread = new Thread();

		// set required fields

		dao.saveThread(thread);

		// verify a primary key was assigned
		assertNotNull(thread.getId());

		// verify set fields are same after save
	}

	public void testGetThread() throws Exception {
		Thread thread = dao.getThread(threadId);
		assertNotNull(thread);
	}

	public void testGetThreads() throws Exception {
		Thread thread = new Thread();

		List results = dao.getThreads(thread);
		assertTrue(results.size() > 0);
	}

	public void testSaveThread() throws Exception {
		Thread thread = dao.getThread(threadId);

		// update required fields

		dao.saveThread(thread);

	}

	public void testRemoveThread() throws Exception {
		String removeId = new String("3");
		dao.removeThread(removeId);
		try {
			dao.getThread(removeId);
			fail("thread found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}

	public void testGetUnreadThreads() throws Exception {
		Map results = dao.getUnreadThreads(new Integer(0), new Integer(25),
				"admin", null);
		assertTrue(((List) results.get("result")).size() > 0);
	}

	public void testListThreads() throws Exception {
		ThreadForm form = new ThreadForm();
		form.setContent("");
		form.setStatus(InfopubConstants.NO_AUDIT);
		form.setStartDate("2008-06-03");
		form.setEndDate("2008-08-30");
		form.setCreaterId("admin");
		Map results = dao.listThreads(new Integer(0), new Integer(25), form,
				" and threadPermissionOrg.orgId in ('1') and threadPermissionOrg.orgType='"
						+ InfopubConstants.ORG_DEPT
						+ "' and thread.id=threadPermissionOrg.threadId");
		assertTrue(((List) results.get("result")).size() > 0);
		assertEquals(((Integer) results.get("total")).intValue(),
				((List) results.get("result")).size());
	}
}
