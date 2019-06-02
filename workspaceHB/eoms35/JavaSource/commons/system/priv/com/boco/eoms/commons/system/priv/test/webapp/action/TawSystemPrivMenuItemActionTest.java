package com.boco.eoms.commons.system.priv.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.priv.webapp.form.TawSystemPrivMenuItemForm;

public class TawSystemPrivMenuItemActionTest extends BaseStrutsTestCase {

	public TawSystemPrivMenuItemActionTest(String name) {
		super(name);
	}

	public void testAdd() throws Exception {
		setRequestPathInfo("/saveTawSystemPrivMenuItem");
		addRequestParameter("method", "Save");

		TawSystemPrivMenuItemForm tawSystemPrivMenuItemForm = new TawSystemPrivMenuItemForm();
		// set required fields
		tawSystemPrivMenuItemForm
				.setMenuid("DzHrIpOzJcUdTjMuQqDjPzYyTjVmHhUcReQkFuQzJbYjTaKqPiJlYsAdEzShTwPkOiOnRcHmIzTrSsHmOzJyQhFjFpMvPhJwFdPo");
		tawSystemPrivMenuItemForm
				.setCode("GmKuWqJtYuLxDjCaWyNjRwHbOmIoYbZsDjZrLrWyNlSiUkHrFwBbWrPfPpTtQaOeJiVxHiWoMrWuRpSiTaZhUsApCjGrEwBeMrMe");
		tawSystemPrivMenuItemForm
				.setParentcode("TzHdQsZvCvNxXtSwNiIqWjLuVjSvApTeTiJjFjNtCsUaEgFsUvKdVtDxCqTvYgZaSoDoWeJcLpNkKmSqFtYwJfHvLhRsJnAzNyTk");
		tawSystemPrivMenuItemForm.setIsApp("IbBfKkTsAh");
		tawSystemPrivMenuItemForm.setIsLeaf("QsBnYkSdSg");
		tawSystemPrivMenuItemForm.setIsHide("ZmIySgDeWo");

		request.setAttribute(Constants.TAWSYSTEMPRIVMENUITEM_KEY,
				tawSystemPrivMenuItemForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}

	public void testSearch() {
		setRequestPathInfo("/tawSystemPrivMenuItems");
		addRequestParameter("method", "Search");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("list");
		assertNotNull(request
				.getAttribute(Constants.TAWSYSTEMPRIVMENUITEM_LIST));
	}

	public void testEdit() throws Exception {
		setRequestPathInfo("/editTawSystemPrivMenuItem");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");
		assertNotNull(request.getAttribute(Constants.TAWSYSTEMPRIVMENUITEM_KEY));

	}

	public void testSave() throws Exception {
		setRequestPathInfo("/editTawSystemPrivMenuItem");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		TawSystemPrivMenuItemForm tawSystemPrivMenuItemForm = (TawSystemPrivMenuItemForm) request
				.getAttribute(Constants.TAWSYSTEMPRIVMENUITEM_KEY);
		assertNotNull(tawSystemPrivMenuItemForm);

		setRequestPathInfo("/saveTawSystemPrivMenuItem");
		addRequestParameter("method", "Save");

		// update the form's required string fields and add it back to the
		// request
		tawSystemPrivMenuItemForm
				.setMenuid("PtOzLhQiTlYtRqAkSzTbPsJmMyUxJvUjByZfRpFyYgDvJuRpCaUjPyEhNjMgAoJnZaGkZuAsOiAhNuLtYlAcCoGuOwLeNgZsGnVj");
		tawSystemPrivMenuItemForm
				.setCode("KtHfUdQrIiXkPpRhNrOrOuBgHoKcOkHqZlRiGlQsJzZcBjBiEdAgXkUyFjLjNeMxZeBeOpRcErVlCgDoDuZrDdRmAcUxWhFtTyGk");
		tawSystemPrivMenuItemForm
				.setParentcode("EuCeRePkRbDjPcMoZgCuGdZgEwAvBhFwPbVbJsTfPpPiFtAnNrUxZbQdNmKeWcImSaLdFaIzNxLlVdSeUdBfMnEgRoTpZvDeJiRp");
		tawSystemPrivMenuItemForm.setIsApp("FtUaCiRdOg");
		tawSystemPrivMenuItemForm.setIsLeaf("IlLlLjLqNp");
		tawSystemPrivMenuItemForm.setIsHide("InMsHzAkHx");

		request.setAttribute(Constants.TAWSYSTEMPRIVMENUITEM_KEY,
				tawSystemPrivMenuItemForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");

		// verify success messages
		verifyActionMessages(new String[] { "tawSystemPrivMenuItem.updated" });

	}

	public void testRemove() throws Exception {
		setRequestPathInfo("/editTawSystemPrivMenuItem");
		addRequestParameter("method", "Delete");
		addRequestParameter("id", "2");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}
}
