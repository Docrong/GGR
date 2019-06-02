package com.boco.eoms.commons.message.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.message.model.TawCommonMessageSubscribe;
import com.boco.eoms.commons.message.dao.TawCommonMessageSubscribeDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageSubscribeDaoTest extends BaseDaoTestCase {

	private TawCommonMessageSubscribeDao dao = null;

	public void setTawCommonMessageSubscribeDao(TawCommonMessageSubscribeDao dao) {
		this.dao = dao;
	}

	public void testAddTawCommonMessageSubscribe() throws Exception {
		TawCommonMessageSubscribe tawCommonMessageSubscribe = new TawCommonMessageSubscribe();

		tawCommonMessageSubscribe.setEndday("5");
		tawCommonMessageSubscribe.setEndhour("6");
		dao.saveTawCommonMessageSubscribe(tawCommonMessageSubscribe);

		// verify a primary key was assigned
		assertNotNull(tawCommonMessageSubscribe.getId());

		// verify set fields are same after save
	}

	public void testGetTawCommonMessageSubscribe() throws Exception {
		TawCommonMessageSubscribe tawCommonMessageSubscribe = new TawCommonMessageSubscribe();

		tawCommonMessageSubscribe.setEndday("5");
		tawCommonMessageSubscribe.setEndhour("6");
		dao.saveTawCommonMessageSubscribe(tawCommonMessageSubscribe);
		TawCommonMessageSubscribe tawCommonMessageSubscribes = dao
				.getTawCommonMessageSubscribe(tawCommonMessageSubscribe.getId());
		assertSame(tawCommonMessageSubscribe.getId(),
				tawCommonMessageSubscribes.getId());
	}

	public void testGetTawCommonMessageSubscribes() throws Exception {
		TawCommonMessageSubscribe tawCommonMessageSubscribe = new TawCommonMessageSubscribe();
		tawCommonMessageSubscribe.setEndday("5");
		tawCommonMessageSubscribe.setEndhour("6");
		dao.saveTawCommonMessageSubscribe(tawCommonMessageSubscribe);
		List results = dao
				.getTawCommonMessageSubscribes(tawCommonMessageSubscribe);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawCommonMessageSubscribe() throws Exception {
		TawCommonMessageSubscribe tawCommonMessageSubscribe = new TawCommonMessageSubscribe();
		tawCommonMessageSubscribe.setEndday("5");
		tawCommonMessageSubscribe.setEndhour("6");
		dao.saveTawCommonMessageSubscribe(tawCommonMessageSubscribe);
		TawCommonMessageSubscribe tawCommonMessageSubscribes = dao
				.getTawCommonMessageSubscribe(tawCommonMessageSubscribe.getId());

		// update required fields

		dao.saveTawCommonMessageSubscribe(tawCommonMessageSubscribes);
		TawCommonMessageSubscribe tawCommonMessageSubscribess = dao
				.getTawCommonMessageSubscribe(tawCommonMessageSubscribes
						.getId());
		assertSame("6", tawCommonMessageSubscribess.getEndhour());
	}

	public void testRemoveTawCommonMessageSubscribe() throws Exception {
		TawCommonMessageSubscribe tawCommonMessageSubscribe = new TawCommonMessageSubscribe();
		tawCommonMessageSubscribe.setEndday("5");
		tawCommonMessageSubscribe.setEndhour("6");
		dao.saveTawCommonMessageSubscribe(tawCommonMessageSubscribe);
		dao.removeTawCommonMessageSubscribe(tawCommonMessageSubscribe.getId());
		try {
			dao.getTawCommonMessageSubscribe(tawCommonMessageSubscribe.getId());
			fail("tawCommonMessageSubscribe found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}

	public void testGetScbyuserid() throws Exception {
		TawCommonMessageSubscribe tawCommonMessageSubscribe = new TawCommonMessageSubscribe();
		tawCommonMessageSubscribe.setEndday("5");
		tawCommonMessageSubscribe.setEndhour("6");
		tawCommonMessageSubscribe.setUserid("admin");
		dao.saveTawCommonMessageSubscribe(tawCommonMessageSubscribe);

		List list = dao.getMessageScByuserid("admin");
		assertTrue(list.size() > 0);
	}

	public void testGetScbymoid() throws Exception {
		TawCommonMessageSubscribe tawCommonMessageSubscribe = new TawCommonMessageSubscribe();
		tawCommonMessageSubscribe.setEndday("5");
		tawCommonMessageSubscribe.setEndhour("6");
		tawCommonMessageSubscribe.setUserid("admin");
		tawCommonMessageSubscribe.setMessageid("88888888");
		dao.saveTawCommonMessageSubscribe(tawCommonMessageSubscribe);

		List list = dao.getMessageScByModelidAndOperid("88888888");
		assertTrue(list.size() > 0);
	}
}
