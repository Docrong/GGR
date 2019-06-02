package com.boco.eoms.commons.system.priv.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.priv.webapp.form.TawSystemPrivMenuForm;

public class TawSystemPrivMenuActionTest extends BaseStrutsTestCase {

	public TawSystemPrivMenuActionTest(String name) {
		super(name);
	}

	public void testAdd() throws Exception {
		setRequestPathInfo("/saveTawSystemPrivMenu");
		addRequestParameter("method", "Save");

		TawSystemPrivMenuForm tawSystemPrivMenuForm = new TawSystemPrivMenuForm();
		// set required fields
		tawSystemPrivMenuForm
				.setName("NqLrYjFiVdWuEpMsWtJoYoCmExTlWnYnIhWtYaGeWgMwJyOrPkQlBfKgApUbHoZfOgSqCaJmLoEoVlCfEiAxUpVgLoAxMxUsWrOy");
		tawSystemPrivMenuForm
				.setOwnerId("QaEjDeGsMmGvFfPgAiMfGhXtLtJqPzRsYfHwJoAqFpHzPzJkUfJjZbDdCuJbJnAxArRdQcNqRaFeFcHlUxUmFhZxPwYsZhLzHhBkAuDuRrXbYsQjGsVeFrKzSaBfSvAsGsOnXoPuLgKfYsNeXaElRjApIpRvPrTnZuVqVkGeTkGpGaQzLlIoVjNoKvMfCqQsJxZcVzNx");
		// tawSystemPrivMenuForm.setCode("PoOpOkJtWqUzYgFwCmTrBvPhWxHqXmQyPsRkEfDwQcSvRbYlBzQjWjArMcUvTsPhKkRkIoUkHnTgCqGkFkHbTyMyWnErPhLeQeWy");

		request.setAttribute(Constants.TAWSYSTEMPRIVMENU_KEY,
				tawSystemPrivMenuForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}

	public void testSearch() {
		setRequestPathInfo("/tawSystemPrivMenus");
		addRequestParameter("method", "Search");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("list");
		assertNotNull(request.getAttribute(Constants.TAWSYSTEMPRIVMENU_LIST));
	}

	public void testEdit() throws Exception {
		setRequestPathInfo("/editTawSystemPrivMenu");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");
		assertNotNull(request.getAttribute(Constants.TAWSYSTEMPRIVMENU_KEY));

	}

	public void testSave() throws Exception {
		setRequestPathInfo("/editTawSystemPrivMenu");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		TawSystemPrivMenuForm tawSystemPrivMenuForm = (TawSystemPrivMenuForm) request
				.getAttribute(Constants.TAWSYSTEMPRIVMENU_KEY);
		assertNotNull(tawSystemPrivMenuForm);

		setRequestPathInfo("/saveTawSystemPrivMenu");
		addRequestParameter("method", "Save");

		// update the form's required string fields and add it back to the
		// request
		tawSystemPrivMenuForm
				.setName("SsOdFaAlUxQgMfVzOdPhVoFrOkVnPhMjIrAfVwQnZqDlAtWuRcGnYrWzBnMpUhSdLfKxQkAwMnIjNlYeBwYeRjIrOkNcIiAkUuEq");
		tawSystemPrivMenuForm
				.setOwnerId("FhAuEcJiWvVhSiNjHsUrNqNiPoQvJcTgMtTfJlIwZdDsKvBmMoRlRwYnInFlXrSnKiAvZsUmByFnMaBrCrCjIaZsYcBzEtXyOxYgIeVhTjGqBtZtWnPbIbOtVtGiZhDuLpTtSkZfAyVpMiQmVpBbWmLfJrEfBpWgSnVxRrQhTkKfWcJuPcZwQtVgSlHiLuIeQdAvJsTh");
		// tawSystemPrivMenuForm.setCode("EfGvBdDmDpUyPhByBzFnWsUqDiIhAnOnAsNpXkZeScRhIkQoInXgVcDdOoQkXvTxEeWfLsNnZjNaFrHoScGiDwRqGhWjKfXjRlVr");

		request.setAttribute(Constants.TAWSYSTEMPRIVMENU_KEY,
				tawSystemPrivMenuForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");

		// verify success messages
		verifyActionMessages(new String[] { "tawSystemPrivMenu.updated" });

	}

	public void testRemove() throws Exception {
		setRequestPathInfo("/editTawSystemPrivMenu");
		addRequestParameter("method", "Delete");
		addRequestParameter("id", "2");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}
}
