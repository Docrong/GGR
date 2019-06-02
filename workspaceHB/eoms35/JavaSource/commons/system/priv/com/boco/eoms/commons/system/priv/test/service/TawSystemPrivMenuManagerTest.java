package com.boco.eoms.commons.system.priv.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuDao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenu;
import com.boco.eoms.commons.system.priv.service.impl.TawSystemPrivMenuManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemPrivMenuManagerTest extends BaseManagerTestCase {
	private final String tawSystemPrivMenuId = "1";

	private TawSystemPrivMenuManagerImpl tawSystemPrivMenuManager = new TawSystemPrivMenuManagerImpl();

	private Mock tawSystemPrivMenuDao = null;

	protected void setUp() throws Exception {
		super.setUp();
		tawSystemPrivMenuDao = new Mock(TawSystemPrivMenuDao.class);
		tawSystemPrivMenuManager
				.setTawSystemPrivMenuDao((TawSystemPrivMenuDao) tawSystemPrivMenuDao
						.proxy());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		tawSystemPrivMenuManager = null;
	}

	public void testGetTawSystemPrivMenus() throws Exception {
		List results = new ArrayList();
		TawSystemPrivMenu tawSystemPrivMenu = new TawSystemPrivMenu();
		results.add(tawSystemPrivMenu);

		// set expected behavior on dao
		tawSystemPrivMenuDao.expects(once()).method("getTawSystemPrivMenus")
				.will(returnValue(results));

		List tawSystemPrivMenus = tawSystemPrivMenuManager
				.getTawSystemPrivMenus(null);
		assertTrue(tawSystemPrivMenus.size() == 1);
		tawSystemPrivMenuDao.verify();
	}

	public void testGetTawSystemPrivMenu() throws Exception {
		// set expected behavior on dao
		tawSystemPrivMenuDao.expects(once()).method("getTawSystemPrivMenu")
				.will(returnValue(new TawSystemPrivMenu()));
		TawSystemPrivMenu tawSystemPrivMenu = tawSystemPrivMenuManager
				.getTawSystemPrivMenu(tawSystemPrivMenuId);
		assertTrue(tawSystemPrivMenu != null);
		tawSystemPrivMenuDao.verify();
	}

	public void testSaveTawSystemPrivMenu() throws Exception {
		TawSystemPrivMenu tawSystemPrivMenu = new TawSystemPrivMenu();

		// set expected behavior on dao
		tawSystemPrivMenuDao.expects(once()).method("saveTawSystemPrivMenu")
				.with(same(tawSystemPrivMenu)).isVoid();

		tawSystemPrivMenuManager.saveTawSystemPrivMenu(tawSystemPrivMenu);
		tawSystemPrivMenuDao.verify();
	}

	public void testAddAndRemoveTawSystemPrivMenu() throws Exception {
		TawSystemPrivMenu tawSystemPrivMenu = new TawSystemPrivMenu();

		// set required fields
		tawSystemPrivMenu
				.setName("IaHeZuGeUrGjKzMeRbBkUkRmOpRqFeIfTcUjZkGpIeCmRfHiReGjYvIpPtKjOiMfSiAkUyHaStZmBtHhFuUhStIkKzWxBaUfQjMn");
		tawSystemPrivMenu
				.setOwnerId("GoVcKxUgPqImFmDmIuCxHaIqTkMwPmSjRqSbWpGpJmZwXoWjKzEkGnWoChEpCxDnIgFkLjLkOgJeLoSyLkJqHqXmTjRiRmDvWxJbBfIlMsYqVcSmLxLtTzXyAiXvAnFjFhRnOpPaAqPrBuPgXcNwFpJdHvCeIzRgMoUsQlWeFyEgMmKxCuGsIfBaTxNoRuIeOuQkYuYz");
		// tawSystemPrivMenu
		// .setCode("EdZyQwOsHkZoZyMxKlCeUpRrKmTqJgMeCbVzWeVePvBkAcQdJgMpJnBaZqVrTfVvWyZpVgBdWlViSeByHzGeZhHhJuRyOyDtLmTq");

		// set expected behavior on dao
		tawSystemPrivMenuDao.expects(once()).method("saveTawSystemPrivMenu")
				.with(same(tawSystemPrivMenu)).isVoid();
		tawSystemPrivMenuManager.saveTawSystemPrivMenu(tawSystemPrivMenu);
		tawSystemPrivMenuDao.verify();

		// reset expectations
		tawSystemPrivMenuDao.reset();

		tawSystemPrivMenuDao.expects(once()).method("removeTawSystemPrivMenu")
				.with(eq(new String(tawSystemPrivMenuId)));
		tawSystemPrivMenuManager.removeTawSystemPrivMenu(tawSystemPrivMenuId);
		tawSystemPrivMenuDao.verify();

		// reset expectations
		tawSystemPrivMenuDao.reset();
		// remove
		Exception ex = new ObjectRetrievalFailureException(
				TawSystemPrivMenu.class, tawSystemPrivMenu.getPrivid());
		tawSystemPrivMenuDao.expects(once()).method("removeTawSystemPrivMenu")
				.isVoid();
		tawSystemPrivMenuDao.expects(once()).method("getTawSystemPrivMenu")
				.will(throwException(ex));
		tawSystemPrivMenuManager.removeTawSystemPrivMenu(tawSystemPrivMenuId);
		try {
			tawSystemPrivMenuManager.getTawSystemPrivMenu(tawSystemPrivMenuId);
			fail("TawSystemPrivMenu with identifier '" + tawSystemPrivMenuId
					+ "' found in database");
		} catch (ObjectRetrievalFailureException e) {
			assertNotNull(e.getMessage());
		}
		tawSystemPrivMenuDao.verify();
	}

	/**
	 * 测试getTawSystemPrivMenus(final Integer curPage, final Integer pageSize)函数
	 */
	public void testGetTawSystemPrivMenus_Ex1() throws Exception {
		tawSystemPrivMenuDao.expects(once()).method("getTawSystemPrivMenus")
				.will(returnValue(new HashMap()));

		Integer curPage = new Integer(1);
		Integer pageSize = new Integer(5);
		Map _obj = tawSystemPrivMenuManager.getTawSystemPrivMenus(curPage,
				pageSize);
		assertTrue(_obj != null);
	}

	/**
	 * 测试getTawSystemPrivMenus(final Integer curPage, final Integer pageSize,
	 * final String whereStr)函数
	 */
	public void testGetTawSystemPrivMenus_Ex2() throws Exception {
		tawSystemPrivMenuDao.expects(once()).method("getTawSystemPrivMenus")
				.will(returnValue(new HashMap()));

		Integer curPage = new Integer(1);
		Integer pageSize = new Integer(5);
		String whereStr = "";
		Map _obj = tawSystemPrivMenuManager.getTawSystemPrivMenus(curPage,
				pageSize, whereStr);
		assertTrue(_obj != null);
	}

}
