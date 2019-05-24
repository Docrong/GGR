package com.boco.eoms.commons.message.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.message.model.TawCommonMessageAddService;
import com.boco.eoms.commons.message.dao.TawCommonMessageAddServiceDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageAddServiceDaoTest extends BaseDaoTestCase {

	private TawCommonMessageAddServiceDao dao = null;

	public void setTawCommonMessageAddServiceDao(
			TawCommonMessageAddServiceDao dao) {
		this.dao = dao;
	}

	public void testAddTawCommonMessageAddService() throws Exception {
		TawCommonMessageAddService tawCommonMessageAddService = new TawCommonMessageAddService();

		tawCommonMessageAddService.setIssendimediat("1");
		tawCommonMessageAddService.setIssendnight("1");
		tawCommonMessageAddService.setMessagetype("1");
		dao.saveTawCommonMessageAddService(tawCommonMessageAddService);

		// verify a primary key was assigned
		assertNotNull(tawCommonMessageAddService.getId());

		// verify set fields are same after save
	}

	public void testGetTawCommonMessageAddService() throws Exception {
		TawCommonMessageAddService tawCommonMessageAddService = new TawCommonMessageAddService();

		tawCommonMessageAddService.setIssendimediat("1");
		tawCommonMessageAddService.setIssendnight("1");
		tawCommonMessageAddService.setMessagetype("1");
		dao.saveTawCommonMessageAddService(tawCommonMessageAddService);
		TawCommonMessageAddService tawCommonMessageAddServices = dao
				.getTawCommonMessageAddService(tawCommonMessageAddService
						.getId());
		assertNotNull(tawCommonMessageAddServices);
	}

	public void testGetTawCommonMessageAddServices() throws Exception {
		TawCommonMessageAddService tawCommonMessageAddService = new TawCommonMessageAddService();

		tawCommonMessageAddService.setIssendimediat("1");
		tawCommonMessageAddService.setIssendnight("1");
		tawCommonMessageAddService.setMessagetype("1");
		dao.saveTawCommonMessageAddService(tawCommonMessageAddService);
		List results = dao
				.getTawCommonMessageAddServices(tawCommonMessageAddService);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawCommonMessageAddService() throws Exception {

		TawCommonMessageAddService tawCommonMessageAddService = new TawCommonMessageAddService();

		tawCommonMessageAddService.setIssendimediat("1");
		tawCommonMessageAddService.setIssendnight("1");
		tawCommonMessageAddService.setMessagetype("1");
		dao.saveTawCommonMessageAddService(tawCommonMessageAddService);
		TawCommonMessageAddService tawCommonMessageAddServices = dao
				.getTawCommonMessageAddService(tawCommonMessageAddService
						.getId());

		// update required fields

		dao.saveTawCommonMessageAddService(tawCommonMessageAddServices);
		assertSame(tawCommonMessageAddService.getId(),
				tawCommonMessageAddServices.getId());

	}

	public void testRemoveTawCommonMessageAddService() throws Exception {
		TawCommonMessageAddService tawCommonMessageAddService = new TawCommonMessageAddService();

		tawCommonMessageAddService.setIssendimediat("1");
		tawCommonMessageAddService.setIssendnight("1");
		tawCommonMessageAddService.setMessagetype("1");
		dao.saveTawCommonMessageAddService(tawCommonMessageAddService);
		dao
				.removeTawCommonMessageAddService(tawCommonMessageAddService
						.getId());
		try {
			dao.getTawCommonMessageAddService(tawCommonMessageAddService
					.getId());
			fail("tawCommonMessageAddService found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}

	public void testgetServicebyuserid() throws Exception {

		TawCommonMessageAddService tawCommonMessageAddService = new TawCommonMessageAddService();

		tawCommonMessageAddService.setIssendimediat("1");
		tawCommonMessageAddService.setIssendnight("1");
		tawCommonMessageAddService.setMessagetype("1");
		tawCommonMessageAddService.setUserid("admin");
		dao.saveTawCommonMessageAddService(tawCommonMessageAddService);
		List list = dao.getMesageServicebyuserids("admin");

		// update required fields

		assertTrue(list.size() > 0);

	}

	public void testgetServicebyMOid() throws Exception {

		TawCommonMessageAddService tawCommonMessageAddService = new TawCommonMessageAddService();

		tawCommonMessageAddService.setIssendimediat("1");
		tawCommonMessageAddService.setIssendnight("1");
		tawCommonMessageAddService.setMessagetype("1");
		tawCommonMessageAddService.setUserid("admin");
		tawCommonMessageAddService.setModelid("99");
		tawCommonMessageAddService.setOperid("9988");
		dao.saveTawCommonMessageAddService(tawCommonMessageAddService);
		TawCommonMessageAddService tawCommonMessageAddServices = dao
				.getMessageService("99", "9988");

		// update required fields

		assertSame(tawCommonMessageAddService.getId(),
				tawCommonMessageAddServices.getId());

	}
}
