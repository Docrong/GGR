package com.boco.eoms.commons.message.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.message.model.TawCommonMessageMonitorRef;
import com.boco.eoms.commons.message.dao.TawCommonMessageMonitorRefDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageMonitorRefDaoTest extends BaseDaoTestCase {

	private TawCommonMessageMonitorRefDao dao = null;

	public void setTawCommonMessageMonitorRefDao(
			TawCommonMessageMonitorRefDao dao) {
		this.dao = dao;
	}

	public void testAddTawCommonMessageMonitorRef() throws Exception {
		TawCommonMessageMonitorRef tawCommonMessageMonitorRef = new TawCommonMessageMonitorRef();
		tawCommonMessageMonitorRef.setMonitorid("1");
		tawCommonMessageMonitorRef.setTeloremail("13699142338");

		dao.saveTawCommonMessageMonitorRef(tawCommonMessageMonitorRef);

		// verify a primary key was assigned
		assertNotNull(tawCommonMessageMonitorRef.getId());

		// verify set fields are same after save
	}

	public void testGetTawCommonMessageMonitorRef() throws Exception {
		TawCommonMessageMonitorRef tawCommonMessageMonitorRef = new TawCommonMessageMonitorRef();
		tawCommonMessageMonitorRef.setMonitorid("1");
		tawCommonMessageMonitorRef.setTeloremail("13699142338");

		dao.saveTawCommonMessageMonitorRef(tawCommonMessageMonitorRef);
		TawCommonMessageMonitorRef tawCommonMessageMonitorRefs = dao
				.getTawCommonMessageMonitorRef(tawCommonMessageMonitorRef
						.getId());
		assertSame(tawCommonMessageMonitorRefs.getId(),
				tawCommonMessageMonitorRef.getId());
	}

	public void testGetTawCommonMessageMonitorRefs() throws Exception {
		TawCommonMessageMonitorRef tawCommonMessageMonitorRef = new TawCommonMessageMonitorRef();
		tawCommonMessageMonitorRef.setMonitorid("1");
		tawCommonMessageMonitorRef.setTeloremail("13699142338");

		dao.saveTawCommonMessageMonitorRef(tawCommonMessageMonitorRef);
		TawCommonMessageMonitorRef tawCommonMessageMonitorRefs = new TawCommonMessageMonitorRef();

		List results = dao
				.getTawCommonMessageMonitorRefs(tawCommonMessageMonitorRefs);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawCommonMessageMonitorRef() throws Exception {

		TawCommonMessageMonitorRef tawCommonMessageMonitorRef = new TawCommonMessageMonitorRef();
		tawCommonMessageMonitorRef.setMonitorid("1");
		tawCommonMessageMonitorRef.setTeloremail("13699142338");

		dao.saveTawCommonMessageMonitorRef(tawCommonMessageMonitorRef);
		TawCommonMessageMonitorRef tawCommonMessageMonitorRefs = dao
				.getTawCommonMessageMonitorRef(tawCommonMessageMonitorRef
						.getId());

		dao.saveTawCommonMessageMonitorRef(tawCommonMessageMonitorRefs);
		TawCommonMessageMonitorRef tawCommonMessageMonitorRefss = new TawCommonMessageMonitorRef();
		List list = dao
				.getTawCommonMessageMonitorRefs(tawCommonMessageMonitorRefss);
		assertTrue(list.size() > 0);
	}

	public void testRemoveTawCommonMessageMonitorRef() throws Exception {
		TawCommonMessageMonitorRef tawCommonMessageMonitorRef = new TawCommonMessageMonitorRef();
		tawCommonMessageMonitorRef.setMonitorid("1");
		tawCommonMessageMonitorRef.setTeloremail("13699142338");

		dao.saveTawCommonMessageMonitorRef(tawCommonMessageMonitorRef);
		dao
				.removeTawCommonMessageMonitorRef(tawCommonMessageMonitorRef
						.getId());
		try {
			dao.getTawCommonMessageMonitorRef(tawCommonMessageMonitorRef
					.getId());
			fail("tawCommonMessageMonitorRef found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
	}
}
