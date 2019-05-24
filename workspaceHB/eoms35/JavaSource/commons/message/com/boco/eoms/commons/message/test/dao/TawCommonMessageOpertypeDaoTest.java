package com.boco.eoms.commons.message.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.message.model.TawCommonMessageOpertype;
import com.boco.eoms.commons.message.dao.TawCommonMessageOpertypeDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageOpertypeDaoTest extends BaseDaoTestCase {

	private TawCommonMessageOpertypeDao dao = null;

	public void setTawCommonMessageOpertypeDao(TawCommonMessageOpertypeDao dao) {

		this.dao = dao;
	}

	public void testAddTawCommonMessageOpertype() throws Exception {
		TawCommonMessageOpertype tawCommonMessageOpertype = new TawCommonMessageOpertype();

		// set required fields
		tawCommonMessageOpertype.setModelid("88");
		tawCommonMessageOpertype.setModelname("xiaoxi");
		tawCommonMessageOpertype.setOperid("8866");
		tawCommonMessageOpertype.setOpername("fasong");
		dao.saveTawCommonMessageOpertype(tawCommonMessageOpertype);

		// verify a primary key was assigned
		assertNotNull(tawCommonMessageOpertype.getId());

		// verify set fields are same after save
	}

	public void testGetTawCommonMessageOpertype() throws Exception {
		TawCommonMessageOpertype tawCommonMessageOpertype = new TawCommonMessageOpertype();

		// set required fields
		tawCommonMessageOpertype.setModelid("88");
		tawCommonMessageOpertype.setModelname("xiaoxi");
		tawCommonMessageOpertype.setOperid("8866");
		tawCommonMessageOpertype.setOpername("fasong");
		dao.saveTawCommonMessageOpertype(tawCommonMessageOpertype);

		TawCommonMessageOpertype tawCommonMessageOpertypes = dao
				.getTawCommonMessageOpertype(tawCommonMessageOpertype.getId());
		assertNotNull(tawCommonMessageOpertypes);
	}

	public void testGetTawCommonMessageOpertypes() throws Exception {
		TawCommonMessageOpertype tawCommonMessageOpertype = new TawCommonMessageOpertype();
		tawCommonMessageOpertype.setModelid("88");
		tawCommonMessageOpertype.setModelname("xiaoxi");
		tawCommonMessageOpertype.setOperid("8866");
		tawCommonMessageOpertype.setOpername("fasong");
		dao.saveTawCommonMessageOpertype(tawCommonMessageOpertype);
		List results = dao
				.getTawCommonMessageOpertypes(tawCommonMessageOpertype);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawCommonMessageOpertype() throws Exception {
		TawCommonMessageOpertype tawCommonMessageOpertype = new TawCommonMessageOpertype();
		tawCommonMessageOpertype.setModelid("88");
		tawCommonMessageOpertype.setModelname("xiaoxi");
		tawCommonMessageOpertype.setOperid("8866");
		tawCommonMessageOpertype.setOpername("fasong");
		dao.saveTawCommonMessageOpertype(tawCommonMessageOpertype);
		TawCommonMessageOpertype tawCommonMessageOpertypes = dao
				.getTawCommonMessageOpertype(tawCommonMessageOpertype.getId());

		// update required fields

		dao.saveTawCommonMessageOpertype(tawCommonMessageOpertypes);
		assertSame(tawCommonMessageOpertype.getId(), tawCommonMessageOpertypes
				.getId());
	}

	public void testRemoveTawCommonMessageOpertype() throws Exception {
		TawCommonMessageOpertype tawCommonMessageOpertype = new TawCommonMessageOpertype();
		tawCommonMessageOpertype.setModelid("88");
		tawCommonMessageOpertype.setModelname("xiaoxi");
		tawCommonMessageOpertype.setOperid("8866");
		tawCommonMessageOpertype.setOpername("fasong");
		dao.saveTawCommonMessageOpertype(tawCommonMessageOpertype);
		dao.removeTawCommonMessageOpertype(tawCommonMessageOpertype.getId());
		try {
			dao.getTawCommonMessageOpertype(tawCommonMessageOpertype.getId());
			fail("tawCommonMessageOpertype found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}

	public void testGetOpertype() throws Exception {
		TawCommonMessageOpertype tawCommonMessageOpertype = new TawCommonMessageOpertype();
		tawCommonMessageOpertype.setModelid("88");
		tawCommonMessageOpertype.setModelname("xiaoxi");
		tawCommonMessageOpertype.setOperid("8866");
		tawCommonMessageOpertype.setOpername("fasong");
		dao.saveTawCommonMessageOpertype(tawCommonMessageOpertype);
		TawCommonMessageOpertype tawCommonMessageOpertypes = dao.getOpertype(
				tawCommonMessageOpertype.getModelid(), tawCommonMessageOpertype
						.getOperid());
		assertSame(tawCommonMessageOpertype.getOperid(),
				tawCommonMessageOpertypes.getOperid());

	}
	public void testSaveAndUp() throws Exception {
		TawCommonMessageOpertype tawCommonMessageOpertype = new TawCommonMessageOpertype();
		tawCommonMessageOpertype.setModelid("88");
		tawCommonMessageOpertype.setModelname("xiaoxi");
		tawCommonMessageOpertype.setOperid("8866");
		tawCommonMessageOpertype.setOpername("fasong");
		dao.saveTawCommonMessageOpertype(tawCommonMessageOpertype);
		dao.saveAndUpdateopertype("88", "8866", "99", "9988", "xiaoximm",
				"fasongxiaoxi", "admin", "hhh", "aaaaa");
		TawCommonMessageOpertype tawCommonMessageOpertypes = dao.getOpertype(
				tawCommonMessageOpertype.getModelid(), tawCommonMessageOpertype
						.getOperid());
		assertSame(tawCommonMessageOpertype.getOperid(),
				tawCommonMessageOpertypes.getOperid());

	}
	public void testRemoveOpertype() throws Exception {
		TawCommonMessageOpertype tawCommonMessageOpertype = new TawCommonMessageOpertype();
		tawCommonMessageOpertype.setModelid("88");
		tawCommonMessageOpertype.setModelname("xiaoxi");
		tawCommonMessageOpertype.setOperid("8866");
		tawCommonMessageOpertype.setOpername("fasong");
		dao.saveTawCommonMessageOpertype(tawCommonMessageOpertype);
		dao.removeOpertype(tawCommonMessageOpertype.getModelid(),
				tawCommonMessageOpertype.getOperid());
		try {
			dao.getTawCommonMessageOpertype(tawCommonMessageOpertype.getId());
			fail("tawCommonMessageOpertype found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}

	
}
