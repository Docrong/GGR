package com.boco.eoms.commons.system.priv.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemPrivAssignDaoTest extends BaseDaoTestCase {

	private TawSystemPrivAssignDao dao = null;

	public void setTawSystemPrivAssignDao(TawSystemPrivAssignDao dao) {
		this.dao = dao;
	}

	public void testAddTawSystemPrivAssign() throws Exception {
		TawSystemPrivAssign tawSystemPrivAssign = new TawSystemPrivAssign();
		tawSystemPrivAssign.setAssigntype("1");
		tawSystemPrivAssign.setObjectid("test");
		tawSystemPrivAssign.setOperuserid("admin");
		tawSystemPrivAssign.setPrivid("3");
		tawSystemPrivAssign.setRemark("ceshi");
		// set required fields

		dao.saveTawSystemPrivAssign(tawSystemPrivAssign);

		// verify a primary key was assigned
		assertNotNull(tawSystemPrivAssign.getId());

		// verify set fields are same after save
	}

	public void testGetTawSystemPrivAssign() throws Exception {
		TawSystemPrivAssign tawSystemPrivAssign = new TawSystemPrivAssign();
		tawSystemPrivAssign.setAssigntype("1");
		tawSystemPrivAssign.setObjectid("test");
		tawSystemPrivAssign.setOperuserid("admin");
		tawSystemPrivAssign.setPrivid("3");
		tawSystemPrivAssign.setRemark("ceshi");
		// set required fields

		dao.saveTawSystemPrivAssign(tawSystemPrivAssign);
		TawSystemPrivAssign tawSystemPrivAssigns = dao
				.getTawSystemPrivAssign(tawSystemPrivAssign.getId());
		assertNotNull(tawSystemPrivAssigns);
	}

	public void testGetTawSystemPrivAssigns() throws Exception {
		TawSystemPrivAssign tawSystemPrivAssign = new TawSystemPrivAssign();
		tawSystemPrivAssign.setAssigntype("1");
		tawSystemPrivAssign.setObjectid("test");
		tawSystemPrivAssign.setOperuserid("admin");
		tawSystemPrivAssign.setPrivid("3");
		tawSystemPrivAssign.setRemark("ceshi");
		// set required fields

		dao.saveTawSystemPrivAssign(tawSystemPrivAssign);
		List results = dao.getTawSystemPrivAssigns(tawSystemPrivAssign);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawSystemPrivAssign() throws Exception {
		TawSystemPrivAssign tawSystemPrivAssign = new TawSystemPrivAssign();
		tawSystemPrivAssign.setAssigntype("1");
		tawSystemPrivAssign.setObjectid("test");
		tawSystemPrivAssign.setOperuserid("admin");
		tawSystemPrivAssign.setPrivid("3");
		tawSystemPrivAssign.setRemark("ceshi");
		// set required fields

		dao.saveTawSystemPrivAssign(tawSystemPrivAssign);
		TawSystemPrivAssign tawSystemPrivAssigns = dao
				.getTawSystemPrivAssign(tawSystemPrivAssign.getId());

		// update required fields

		dao.saveTawSystemPrivAssign(tawSystemPrivAssigns);
		assertSame(tawSystemPrivAssign.getId(), tawSystemPrivAssigns.getId());

	}

	public void testRemoveTawSystemPrivAssign() throws Exception {
		TawSystemPrivAssign tawSystemPrivAssign = new TawSystemPrivAssign();
		tawSystemPrivAssign.setAssigntype("1");
		tawSystemPrivAssign.setObjectid("test");
		tawSystemPrivAssign.setOperuserid("admin");
		tawSystemPrivAssign.setPrivid("3");
		tawSystemPrivAssign.setRemark("ceshi");
		// set required fields

		dao.saveTawSystemPrivAssign(tawSystemPrivAssign);
		dao.removeTawSystemPrivAssign(tawSystemPrivAssign.getId());
		try {
			dao.getTawSystemPrivAssign(tawSystemPrivAssign.getId());
			fail("tawSystemPrivAssign found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}
}
