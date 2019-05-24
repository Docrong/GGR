package com.boco.eoms.commons.demo.test.dao;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.demo.model.TawDemoMytable;
import com.boco.eoms.commons.demo.dao.TawDemoMytableDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawDemoMytableDaoTest extends BaseDaoTestCase {
	private int tawDemoMytableId = 6;

	private java.lang.String remark = "备注信息"; 

	private java.lang.String tableName = "表名称";

	private TawDemoMytableDao dao = null;

	public void setTawDemoMytableDao(TawDemoMytableDao dao) {
		this.dao = dao;
	}

	public void testAddTawDemoMytable() throws Exception {
		TawDemoMytable tawDemoMytable = new TawDemoMytable();

		// set required fields

		tawDemoMytable.setRemark(remark);
		tawDemoMytable.setTableName(tableName);

		dao.saveTawDemoMytable(tawDemoMytable);
		List list = new ArrayList();
		list = dao.getTawDemoMytables(tawDemoMytable);

		assertNotNull(list.get(0));
		// verify a primary key was assigned
		assertNotNull(tawDemoMytable.getId());

		// verify set fields are same after save
		assertEquals(remark, tawDemoMytable.getRemark());
		assertEquals(tableName, tawDemoMytable.getTableName());
	}

	/**
	 * 保存并验证存在
	 * 
	 * @throws Exception
	 */
	public void testGetTawDemoMytable() throws Exception {
		TawDemoMytable tawDemoMytable = new TawDemoMytable();

		tawDemoMytable.setRemark(remark);
		tawDemoMytable.setTableName(tableName);
		dao.saveTawDemoMytable(tawDemoMytable);

		TawDemoMytable testTawDemoMytable = dao
				.getTawDemoMytable(tawDemoMytable.getId());

		assertSame(tawDemoMytable, testTawDemoMytable);
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void testGetTawDemoMytables() throws Exception {
		TawDemoMytable tawDemoMytable = new TawDemoMytable();

		tawDemoMytable.setRemark(remark);
		tawDemoMytable.setTableName(tableName);
		dao.saveTawDemoMytable(tawDemoMytable);

		List results = dao.getTawDemoMytables(tawDemoMytable);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawDemoMytable() throws Exception {
		TawDemoMytable tawDemoMytable = new TawDemoMytable();

		// update required fields
		tawDemoMytable.setRemark(remark);
		tawDemoMytable.setTableName(tableName);
		tawDemoMytable.setId(new Integer(tawDemoMytableId));

		dao.saveTawDemoMytable(tawDemoMytable);

		TawDemoMytable testTawDemoMytable = new TawDemoMytable();

		testTawDemoMytable = dao.getTawDemoMytable(new Integer(tawDemoMytableId));
		assertEquals(remark, testTawDemoMytable.getRemark());
		assertEquals(remark, tawDemoMytable.getRemark());
		assertEquals(tableName, tawDemoMytable.getTableName());
	}

	public void testRemoveTawDemoMytable() throws Exception {
		TawDemoMytable tawDemoMytable = new TawDemoMytable();
		// update required fields
		tawDemoMytable.setRemark(remark);
		tawDemoMytable.setTableName(tableName);

		dao.saveTawDemoMytable(tawDemoMytable);
		dao.getTawDemoMytable(tawDemoMytable.getId());
		dao.removeTawDemoMytable(tawDemoMytable.getId());

		try {
			dao.getTawDemoMytable(new Integer(tawDemoMytableId));
			fail("tawDemoMytable found in database");

		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}

	}
}
