package com.boco.eoms.workbench.infopub.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.workbench.infopub.model.Forums;
import com.boco.eoms.workbench.infopub.dao.ForumsDao;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * 
 * <p>
 * Title:版块管理dao测试类
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
public class ForumsDaoTest extends BaseDaoTestCase {
	private String forumsId = new String("1");

	private ForumsDao dao = null;

	public void setForumsDao(ForumsDao dao) {
		this.dao = dao;
	}

	public void testAddForums() throws Exception {
		Forums forums = new Forums();

		// set required fields

		dao.saveForums(forums);

		// verify a primary key was assigned
		assertNotNull(forums.getId());

		// verify set fields are same after save
	}

	public void testGetForums() throws Exception {
		Forums forums = dao.getForums(forumsId);
		assertNotNull(forums);
	}

	public void testGetForumss() throws Exception {
		Forums forums = new Forums();

		List results = dao.getForumss(forums);
		assertTrue(results.size() > 0);
	}

	public void testSaveForums() throws Exception {
		Forums forums = dao.getForums(forumsId);

		// update required fields

		dao.saveForums(forums);

	}

	public void testRemoveForums() throws Exception {
		String removeId = new String("3");
		dao.removeForums(removeId);
		try {
			dao.getForums(removeId);
			fail("forums found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}
}
