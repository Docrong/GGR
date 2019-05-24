package com.boco.eoms.commons.system.priv.test.dao;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenuItem;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuItemDao;

public class TawSystemPrivMenuItemDaoTest extends BaseDaoTestCase {
	private String tawSystemPrivMenuItemId = new String(
			"8a1583d812bb8f530112bba935a70009");

	private TawSystemPrivMenuItemDao dao = null;

	public void setTawSystemPrivMenuItemDao(TawSystemPrivMenuItemDao dao) {
		this.dao = dao;
	}

	public void testAddTawSystemPrivMenuItem() throws Exception {
		TawSystemPrivMenuItem tawSystemPrivMenuItem = new TawSystemPrivMenuItem();

		// set required fields

		java.lang.String menuCode = "WmPxQgGhXbDqGcMlWaFdNqSeLtHaVyLsVhRzKbDzLfOhHaIqCdJjRtVwZiGyUgPiCzOiDrXnIqVySuPtDvAwDcToCxGjVcRlRaYj";
		tawSystemPrivMenuItem.setMenuid(menuCode);

		java.lang.String itemId = "AtVtGxZrAlZiNuZbUjWtWhZoJgXeFrQeBrJdGkRrSgDtTwVjMvOcKfCmDcDwEyPmBjOqViPmNvObRdEuGmWdXdAfZxVbMpElWsNr";
		tawSystemPrivMenuItem.setCode(itemId);

		java.lang.String parentItemId = "PvJrOdTcDcZmRfBmRsWqUcJtBjOcZmJkBjRkHfPvUcElQiNhWfSjQuLsAwRpImCjTpMuVnVwGbQyCkYuTlWyIrWkWfDgFyZaJxSy";
		tawSystemPrivMenuItem.setParentcode(parentItemId);

		java.lang.String flag = "PdAjLoGqIb";
		tawSystemPrivMenuItem.setIsApp(flag);

		java.lang.String isLeaf = "UhCrSgDaOu";
		tawSystemPrivMenuItem.setIsLeaf(isLeaf);

		java.lang.String isHide = "XeLfScCzQx";
		tawSystemPrivMenuItem.setIsHide(isHide);

		dao.saveTawSystemPrivMenuItem(tawSystemPrivMenuItem);

		// verify a primary key was assigned
		assertNotNull(tawSystemPrivMenuItem.getId());

		// verify set fields are same after save
		assertEquals(menuCode, tawSystemPrivMenuItem.getMenuid());
		assertEquals(itemId, tawSystemPrivMenuItem.getCode());
		assertEquals(parentItemId, tawSystemPrivMenuItem.getParentcode());
		assertEquals(flag, tawSystemPrivMenuItem.getIsApp());
		assertEquals(isLeaf, tawSystemPrivMenuItem.getIsLeaf());
		assertEquals(isHide, tawSystemPrivMenuItem.getIsHide());
	}

	public void testGetTawSystemPrivMenuItem() throws Exception {
		TawSystemPrivMenuItem tawSystemPrivMenuItem = dao
				.getTawSystemPrivMenuItem(tawSystemPrivMenuItemId);
		assertNotNull(tawSystemPrivMenuItem);
	}

	public void testGetTawSystemPrivMenuItems() throws Exception {
		TawSystemPrivMenuItem tawSystemPrivMenuItem = new TawSystemPrivMenuItem();

		List results = dao.getTawSystemPrivMenuItems(tawSystemPrivMenuItem);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawSystemPrivMenuItem() throws Exception {
		TawSystemPrivMenuItem tawSystemPrivMenuItem = dao
				.getTawSystemPrivMenuItem(tawSystemPrivMenuItemId);

		// update required fields
		java.lang.String menuCode = "ZrFySfHzJfGnZfBwTfTbQoEpRcDfDeStVhKxQcPzKkGxSrPmNtKoEsEwByTkJlUsWoQyMjCfRaXaSxMyJxNnSbLzCoBoCgShLdKu";
		tawSystemPrivMenuItem.getMenuid();
		java.lang.String itemId = "RrVjRgGnSiXxYfHkGkUjWbSgCtHbNzIeLfVjJjCyYqSmFqRkRqEmJjKkJaNmRiBiGxBuWqViHgCsSdSzYfThNlKtJqZhMqUsMeHt";
		tawSystemPrivMenuItem.setCode(itemId);
		java.lang.String parentItemId = "JgEcGvZnXeUrFwYeAmRsCkZkZvSuBpVpCyLtNiDmAxPhJoCzRiEpUlHhTcYkVbVmAjVmNbFlUcZcRsGjUgJyPjRgQjNdTdHpDfHk";
		tawSystemPrivMenuItem.setParentcode(parentItemId);
		java.lang.String flag = "LdLvYuCkYr";
		tawSystemPrivMenuItem.setIsApp(flag);
		java.lang.String isLeaf = "ZmEyZhUxJz";
		tawSystemPrivMenuItem.setIsLeaf(isLeaf);
		java.lang.String isHide = "LrXbNrGzRa";
		tawSystemPrivMenuItem.setIsHide(isHide);

		dao.saveTawSystemPrivMenuItem(tawSystemPrivMenuItem);

		assertEquals(menuCode, tawSystemPrivMenuItem.getMenuid());
		assertEquals(itemId, tawSystemPrivMenuItem.getCode());
		assertEquals(parentItemId, tawSystemPrivMenuItem.getParentcode());
		assertEquals(flag, tawSystemPrivMenuItem.getIsApp());
		assertEquals(isLeaf, tawSystemPrivMenuItem.getIsLeaf());
		assertEquals(isHide, tawSystemPrivMenuItem.getIsHide());
	}

	/**
	 * 测试DAO层的getAllMenus函数
	 */
	public void testGetAllMenus() throws Exception {
		List results = dao.getAllMenus();
		assertTrue(results.size() > 0);
	}

	/**
	 * 测试DAO层的getSelfMenus函数
	 */
	public void testGetSelfMenus() throws Exception {
		String _strOwnerId = "1001";
		List results = dao.getSelfMenus(_strOwnerId);
		assertTrue(results.size() > 0);
	}

	/**
	 * 测试DAO层的getFirLevelMenus函数
	 */
	public void testGetFirLevelMenus() throws Exception {
		String _strMenuCode = "1001";
		List results = dao.getFirLevelMenus(_strMenuCode);
		assertTrue(results.size() > 0);
	}

	/**
	 * 测试DAO层的getNextLevelMenus函数
	 */
	public void testGetNextLevelMenus() throws Exception {
		String _strMenuCode = "2";
		String _strItemId = "-1";
		List results = dao.getNextLevelMenus(_strMenuCode, _strItemId);
		assertTrue(results.size() > 0);
	}

	/**
	 * 测试DAO层的getMenuName函数
	 */
	public void testGetMenuName() throws Exception {
		String _strMenuCode = "1001";
		Object _obj = dao.getMenuName(_strMenuCode);
		assertNotNull(_obj);
	}

	/**
	 * 测试DAO层的getSubMenuItems函数
	 */
	public void testGetSubMenuItems() throws Exception {
		List _objTotals = new ArrayList();
		List _objExists = new ArrayList();

		// 测试参数准备
		for (int i = 0; i < 10; i++) {
			TawSystemPrivMenuItem _obj = new TawSystemPrivMenuItem();
			_obj.setId("" + i);
			_obj.setCode("itemId" + i);
			_obj.setMenuid("menuCode" + i);
			_objTotals.add(_obj);
		}

		for (int j = 3; j < 8; j++) {
			TawSystemPrivMenuItem _obj = new TawSystemPrivMenuItem();
			_obj.setId("" + j);
			_obj.setCode("itemId" + j);
			_obj.setMenuid("menuCode" + j);
			_objExists.add(_obj);
		}

		List _objReturn = new ArrayList();
		_objReturn = dao.getSubMenuItems(_objTotals, _objExists);

		// 测试结果输出
		/*
		 * for (int k = 0; k < _objReturn.size(); k++) { TawSystemPrivMenuItem
		 * _obj = (TawSystemPrivMenuItem) _objReturn .get(k);
		 * System.out.println("----------" + k + "------------");
		 * System.out.println("menuCode: " + _obj.getMenuCode());
		 * System.out.println("itmeId: " + _obj.getItemId());
		 * System.out.println("id: " + _obj.getId());
		 * System.out.println("----------" + k + "------------");
		 * System.out.println(""); }
		 */
		assertEquals(_objTotals.size(), _objExists.size() + _objReturn.size());
	}

	/**
	 * 测试DAO层的getValidSubMenuItems函数
	 */
	public void testGetValidSubMenuItems() throws Exception {
		String _strUserId = "1001";
		String _strParentItemId = "99";

		List _objReturn = dao
				.getValidSubMenuItems(_strUserId, _strParentItemId);
		super.assertTrue(_objReturn.size() > 0);
	}

}
