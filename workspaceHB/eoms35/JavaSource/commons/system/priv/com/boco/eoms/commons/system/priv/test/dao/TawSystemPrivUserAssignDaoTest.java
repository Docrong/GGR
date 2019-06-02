package com.boco.eoms.commons.system.priv.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivUserAssignDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemPrivUserAssignDaoTest extends BaseDaoTestCase {
	private String tawSystemPrivUserAssignId = new String("1");

	private TawSystemPrivUserAssignDao dao = null;

	public void setTawSystemPrivUserAssignDao(TawSystemPrivUserAssignDao dao) {
		this.dao = dao;
	}

	public void testAddTawSystemPrivUserAssign() throws Exception {
		TawSystemPrivUserAssign tawSystemPrivUserAssign = new TawSystemPrivUserAssign();
		tawSystemPrivUserAssign.setCurrentprivid("9988");
		tawSystemPrivUserAssign.setCurrentprivname("guzhanggongdan");
		tawSystemPrivUserAssign.setHide(new Integer(1));
		tawSystemPrivUserAssign.setParentprivid("99");
		tawSystemPrivUserAssign.setParentprivname("gongdan");
		// set required fields

		dao.saveTawSystemPrivUserAssign(tawSystemPrivUserAssign);

		// verify a primary key was assigned
		assertNotNull(tawSystemPrivUserAssign.getId());

		// verify set fields are same after save
	}

	public void testGetTawSystemPrivUserAssign() throws Exception {
		TawSystemPrivUserAssign tawSystemPrivUserAssign = new TawSystemPrivUserAssign();
		tawSystemPrivUserAssign.setCurrentprivid("9988");
		tawSystemPrivUserAssign.setCurrentprivname("guzhanggongdan");
		tawSystemPrivUserAssign.setHide(new Integer(1));
		tawSystemPrivUserAssign.setParentprivid("99");
		tawSystemPrivUserAssign.setParentprivname("gongdan");
		// set required fields

		dao.saveTawSystemPrivUserAssign(tawSystemPrivUserAssign);
		TawSystemPrivUserAssign tawSystemPrivUserAssigns = dao
				.getTawSystemPrivUserAssign(tawSystemPrivUserAssign.getId());
		assertNotNull(tawSystemPrivUserAssign);
	}

	public void testGetTawSystemPrivUserAssigns() throws Exception {
		TawSystemPrivUserAssign tawSystemPrivUserAssign = new TawSystemPrivUserAssign();
		tawSystemPrivUserAssign.setCurrentprivid("9988");
		tawSystemPrivUserAssign.setCurrentprivname("guzhanggongdan");
		tawSystemPrivUserAssign.setHide(new Integer(1));
		tawSystemPrivUserAssign.setParentprivid("99");
		tawSystemPrivUserAssign.setParentprivname("gongdan");
		// set required fields

		dao.saveTawSystemPrivUserAssign(tawSystemPrivUserAssign);
		List results = dao.getTawSystemPrivUserAssigns(tawSystemPrivUserAssign);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawSystemPrivUserAssign() throws Exception {
		TawSystemPrivUserAssign tawSystemPrivUserAssign = new TawSystemPrivUserAssign();
		tawSystemPrivUserAssign.setCurrentprivid("9988");
		tawSystemPrivUserAssign.setCurrentprivname("guzhanggongdan");
		tawSystemPrivUserAssign.setHide(new Integer(1));
		tawSystemPrivUserAssign.setParentprivid("99");
		tawSystemPrivUserAssign.setParentprivname("gongdan");
		// set required fields

		dao.saveTawSystemPrivUserAssign(tawSystemPrivUserAssign);
		TawSystemPrivUserAssign tawSystemPrivUserAssigns = dao
				.getTawSystemPrivUserAssign(tawSystemPrivUserAssign.getId());

		// update required fields

		dao.saveTawSystemPrivUserAssign(tawSystemPrivUserAssigns);
		assertSame(tawSystemPrivUserAssigns.getId(), tawSystemPrivUserAssign
				.getId());

	}

	public void testRemoveTawSystemPrivUserAssign() throws Exception {
		TawSystemPrivUserAssign tawSystemPrivUserAssign = new TawSystemPrivUserAssign();
		tawSystemPrivUserAssign.setCurrentprivid("9988");
		tawSystemPrivUserAssign.setCurrentprivname("guzhanggongdan");
		tawSystemPrivUserAssign.setHide(new Integer(1));
		tawSystemPrivUserAssign.setParentprivid("99");
		tawSystemPrivUserAssign.setParentprivname("gongdan");
		// set required fields

		dao.saveTawSystemPrivUserAssign(tawSystemPrivUserAssign);
		dao.removeTawSystemPrivUserAssign(tawSystemPrivUserAssign.getId());
		try {
			dao.getTawSystemPrivUserAssign(tawSystemPrivUserAssign.getId());
			fail("tawSystemPrivUserAssign found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}
}
