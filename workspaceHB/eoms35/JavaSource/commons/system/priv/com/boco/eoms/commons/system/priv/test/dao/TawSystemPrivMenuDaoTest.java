package com.boco.eoms.commons.system.priv.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenu;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuDao;

public class TawSystemPrivMenuDaoTest extends BaseDaoTestCase {
	private String tawSystemPrivMenuId = new String(
			"8a1583d812bb8f530112bba4f9840003");

	private TawSystemPrivMenuDao dao = null;

	public void setTawSystemPrivMenuDao(TawSystemPrivMenuDao dao) {
		this.dao = dao;
	}

	public void testAddTawSystemPrivMenu() throws Exception {
		TawSystemPrivMenu tawSystemPrivMenu = new TawSystemPrivMenu();

		// set required fields

		java.lang.String name = "PuTyCoMfLjFwWyVhAwIvJuJhQdEiDkMbUvWxLxCtSbZqHnHfKiEoOrKbMbXxWsJaFnCjAiDdCmYgEdUnCfQwHzHpFnCpNkDwFgKm";
		tawSystemPrivMenu.setName(name);

		java.lang.String ownerId = "OrWuMyNmVjXfLlJlMdWjYcVkGjDySuCjUwFrNzMxBuJjXhGlSxKhKmFoQzXqUfKbKePyRwTsShEbMnHrJlHhTmLtQrEbZhHvNpBuCsYjVmMaHxKwJyKfTwJlLmGcGhLuDoLlCnYwKlKwNbRiNnOeKzItXvGsWrQpWtDkTvRvBuBkPlQaPcDwOxAhVsGfSlGjFjUdQdJm";
		tawSystemPrivMenu.setOwnerId(ownerId);

		// java.lang.String code =
		// "PtEgQoGlPrCfVvQkUzXgYgBeTmLuBdUzTrMtWmJhBaEiAnRtIzDqOhGkSsXiHpVzKwWbYqKwWlGuGeIuWlEsRyXpUfDsFhOpCnFn";
		// tawSystemPrivMenu.setCode(code);

		dao.saveTawSystemPrivMenu(tawSystemPrivMenu);

		// verify a primary key was assigned
		// assertNotNull(tawSystemPrivMenu.getId());

		// verify set fields are same after save
		assertEquals(name, tawSystemPrivMenu.getName());
		assertEquals(ownerId, tawSystemPrivMenu.getOwnerId());
		// assertEquals(code, tawSystemPrivMenu.getCode());
	}

	public void testGetTawSystemPrivMenu() throws Exception {
		TawSystemPrivMenu tawSystemPrivMenu = dao
				.getTawSystemPrivMenu(tawSystemPrivMenuId);
		assertNotNull(tawSystemPrivMenu);
	}

	public void testGetTawSystemPrivMenus() throws Exception {
		TawSystemPrivMenu tawSystemPrivMenu = new TawSystemPrivMenu();

		List results = dao.getTawSystemPrivMenus(tawSystemPrivMenu);
		assertTrue(results.size() > 0);
	}

	public void testSaveTawSystemPrivMenu() throws Exception {
		TawSystemPrivMenu tawSystemPrivMenu = dao
				.getTawSystemPrivMenu(tawSystemPrivMenuId);

		// update required fields
		java.lang.String name = "SuIeZsGxDpQyMoVtPfFmDtJpEyEjJwDhViQzXiXoIgTtOfZsNtOgWcYaAdHqUrGeJuCfZhZzZfJfSuLhCuNyLtTmOmUcFcMuSmCq";
		tawSystemPrivMenu.setName(name);
		java.lang.String ownerId = "AsWcCrKsEyVbSxZmOxUjBzMsXoUvUsVsIuEuKcBuFqXdRcZlFnZiBbHsIxVxJwDuXiRsDrBnZmCtQhEoZsHfOxQbYaNeTaKmVaOdJrDuIpHhNwFzPpZaIlXvKnXpSpUhYmVfLnTeGbUoNpTiUyGjNaNgWrKfQnLqVcDiToYuGiPiIuEmHpDvLrZsRpAwZiJuBlOtPrXo";
		tawSystemPrivMenu.setOwnerId(ownerId);
		java.lang.String code = "ElYzSrKbHsJyTqBaEyDaTuJmLrXsOqBsFsBmIzUtTmXdSaQeXjIuRpAjYmEmTcDiBmFvQwOgMbLbWtMzYrOnDkGcJbEuUnXpNhWg";
		// tawSystemPrivMenu.setCode(code);

		dao.saveTawSystemPrivMenu(tawSystemPrivMenu);

		assertEquals(name, tawSystemPrivMenu.getName());
		assertEquals(ownerId, tawSystemPrivMenu.getOwnerId());
		assertEquals(code, tawSystemPrivMenu.getPrivid());
	}

}
