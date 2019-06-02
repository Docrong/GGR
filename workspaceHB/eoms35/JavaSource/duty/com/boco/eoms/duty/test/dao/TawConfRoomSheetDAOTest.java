package com.boco.eoms.duty.test.dao;

import java.sql.SQLException;

import com.boco.eoms.duty.dao.TawConfRoomSheetDAO;
import com.boco.eoms.duty.model.TawConfRoomSheet;

import junit.framework.TestCase;

public class TawConfRoomSheetDAOTest extends TestCase {
	private TawConfRoomSheetDAO dao = null;

	protected void setUp() throws Exception {

		super.setUp();
		com.boco.eoms.db.util.ConnectionPool ds = com.boco.eoms.db.util.ConnectionPool
				.getInstance();
		dao = new TawConfRoomSheetDAO(ds);

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testInsert() {
		TawConfRoomSheet tawConfRoomSheet = new TawConfRoomSheet();
		tawConfRoomSheet.setDeptName("123");
		tawConfRoomSheet.setId(1);
		try {
			dao.insert(tawConfRoomSheet);
		} catch (SQLException e) {
			fail(e.getMessage());
		}

	}
}
