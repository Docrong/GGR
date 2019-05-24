package com.boco.eoms.commons.system.area.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.dao.TawSystemAreaDao;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemAreaDaoTest extends BaseDaoTestCase {
	private Integer tawSystemAreaId = new Integer("1");

	private TawSystemAreaDao dao = null;

	public void setTawSystemAreaDao(TawSystemAreaDao dao) {
		this.dao = dao;
	}

	public void testAddTawSystemArea() throws Exception {
		TawSystemArea tawSystemArea = new TawSystemArea();

		// set required fields

		dao.saveTawSystemArea(tawSystemArea);

		// verify a primary key was assigned
		assertNotNull(tawSystemArea.getId());

		// verify set fields are same after save
	}

	public void testGetTawSystemArea() throws Exception {
		TawSystemArea tawSystemArea = dao.getTawSystemArea(tawSystemAreaId);
		assertNotNull(tawSystemArea);
	}

	public void testGetTawSystemAreas() throws Exception {
		TawSystemArea tawSystemArea = new TawSystemArea();

		List results = dao.getTawSystemAreas(tawSystemArea);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawSystemArea() throws Exception {
		TawSystemArea tawSystemArea = dao.getTawSystemArea(tawSystemAreaId);

		// update required fields

		dao.saveTawSystemArea(tawSystemArea);

	}

	public void testRemoveTawSystemArea() throws Exception {
		Integer removeId = new Integer("3");
		dao.removeTawSystemArea(removeId);
		try {
			dao.getTawSystemArea(removeId);
			fail("tawSystemArea found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}

	public void testId2name() {
		ID2NameDAO id2namedao = (ID2NameDAO) ApplicationContextHolder
				.getInstance().getBean("tawSystemAreaDao");

		try {
			assertEquals("贵州省", id2namedao.id2Name("101"));
		} catch (DictDAOException e) {
			fail(e.getMessage());
		}
	}
}
