package com.boco.eoms.workbench.infopub.test.mgr;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.workbench.infopub.dao.ForumsDao;
import com.boco.eoms.workbench.infopub.mgr.impl.ForumsManagerImpl;
import com.boco.eoms.workbench.infopub.model.Forums;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;
/**
 * 
 * <p>
 * Title:版块管理mgr测试类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:10:08 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ForumsManagerTest extends BaseManagerTestCase {
	private final String forumsId = "1";

	private ForumsManagerImpl forumsManager = new ForumsManagerImpl();

	private Mock forumsDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		forumsDao = new Mock(ForumsDao.class);
		forumsManager.setForumsDao((ForumsDao) forumsDao.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		forumsManager = null;
	}

	public void testGetForumss() throws Exception {
		List results = new ArrayList();
		Forums forums = new Forums();
		results.add(forums);

		// set expected behavior on dao
		forumsDao.expects(once()).method("getForumss").will(
				returnValue(results));

		List forumss = forumsManager.getForumss(null);
		assertTrue(forumss.size() == 1);
		forumsDao.verify();
	}

	public void testGetForums() throws Exception {
		// set expected behavior on dao
		forumsDao.expects(once()).method("getForums").will(
				returnValue(new Forums()));
		Forums forums = forumsManager.getForums(forumsId);
		assertTrue(forums != null);
		forumsDao.verify();
	}

	public void testSaveForums() throws Exception {
		Forums forums = new Forums();

		// set expected behavior on dao
		forumsDao.expects(once()).method("saveForums").with(same(forums))
				.isVoid();

		forumsManager.saveForums(forums);
		forumsDao.verify();
	}

	public void testAddAndRemoveForums() throws Exception {
		Forums forums = new Forums();

		// set required fields

		// set expected behavior on dao
		forumsDao.expects(once()).method("saveForums").with(same(forums))
				.isVoid();
		forumsManager.saveForums(forums);
		forumsDao.verify();

		// reset expectations
		forumsDao.reset();

		forumsDao.expects(once()).method("removeForums").with(
				eq(new String(forumsId)));
		forumsManager.removeForums(forumsId);
		forumsDao.verify();

		// reset expectations
		forumsDao.reset();
		// remove
		Exception ex = new ObjectRetrievalFailureException(Forums.class, forums
				.getId());
		forumsDao.expects(once()).method("removeForums").isVoid();
		forumsDao.expects(once()).method("getForums").will(throwException(ex));
		forumsManager.removeForums(forumsId);
		try {
			forumsManager.getForums(forumsId);
			fail("Forums with identifier '" + forumsId + "' found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		forumsDao.verify();
	}
}
