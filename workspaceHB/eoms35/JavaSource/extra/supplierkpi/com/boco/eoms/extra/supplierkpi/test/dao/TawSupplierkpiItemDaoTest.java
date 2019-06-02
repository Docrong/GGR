package com.boco.eoms.extra.supplierkpi.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiItem;
import com.boco.eoms.extra.supplierkpi.util.SupplierkpiConstants;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiItemDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSupplierkpiItemDaoTest extends BaseDaoTestCase {
	private String tawSupplierkpiItemId = new String("1");

	private TawSupplierkpiItemDao dao = null;

	public void setTawSupplierkpiItemDao(TawSupplierkpiItemDao dao) {
		this.dao = dao;
	}

	public void testAddTawSupplierkpiItem() throws Exception {
		TawSupplierkpiItem tawSupplierkpiItem = new TawSupplierkpiItem();

		// set required fields

		dao.saveTawSupplierkpiItem(tawSupplierkpiItem);

		// verify a primary key was assigned
		assertNotNull(tawSupplierkpiItem.getId());

		// verify set fields are same after save
	}

	public void testGetTawSupplierkpiItem() throws Exception {
		TawSupplierkpiItem tawSupplierkpiItem = dao.getTawSupplierkpiItem(
				tawSupplierkpiItemId, SupplierkpiConstants.UNDELETED);
		assertNotNull(tawSupplierkpiItem);
	}

	public void testGetTawSupplierkpiItems() throws Exception {
		TawSupplierkpiItem tawSupplierkpiItem = new TawSupplierkpiItem();

		List results = dao.getTawSupplierkpiItems(tawSupplierkpiItem);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawSupplierkpiItem() throws Exception {
		TawSupplierkpiItem tawSupplierkpiItem = dao.getTawSupplierkpiItem(
				tawSupplierkpiItemId, SupplierkpiConstants.UNDELETED);

		// update required fields

		dao.saveTawSupplierkpiItem(tawSupplierkpiItem);

	}

	public void testRemoveTawSupplierkpiItem() throws Exception {
		String removeId = new String("3");
		dao.removeTawSupplierkpiItem(removeId);
		try {
			dao.getTawSupplierkpiItem(removeId, SupplierkpiConstants.UNDELETED);
			fail("tawSupplierkpiItem found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}
}
