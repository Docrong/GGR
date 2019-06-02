package com.boco.eoms.commons.system.role.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.commons.system.role.webapp.form.TawSystemRoleImportForm;

public class TawSystemRoleImportActionTest extends BaseStrutsTestCase {

	public TawSystemRoleImportActionTest(String name) {
		super(name);
	}

	public void testAdd() throws Exception {
		setRequestPathInfo("/saveTawSystemRoleImport");
		addRequestParameter("method", "Save");

		TawSystemRoleImportForm tawSystemRoleImportForm = new TawSystemRoleImportForm();
		// set required fields
		tawSystemRoleImportForm
				.setRoleId("SjZcAuFfLqZbKiGkCeCiJaTwFxWnFnChCqKtTkTlTdPsFkLeTh");
		tawSystemRoleImportForm
				.setSubRoleId("JoIqGyHgLmNqWzXqKdXxKoOiHuHuIfGzGaIeNgIqYjOhKxBjCo");
		tawSystemRoleImportForm
				.setVersion("TcQkDtLrPyZfSdJiObFoVnGyYkIbToHsBbDyFlMlWvCdPyGbHw");
		tawSystemRoleImportForm.setVersionAt("KgAtEoFwXeZwRyChWgWxKnGtA");

		// request.setAttribute(Constants.TAWSYSTEMROLEIMPORT_KEY,
		// tawSystemRoleImportForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}

	public void testSearch() {
		setRequestPathInfo("/tawSystemRoleImports");
		addRequestParameter("method", "Search");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("list");
		// assertNotNull(request.getAttribute(Constants.TAWSYSTEMROLEIMPORT_LIST));
	}

	public void testEdit() throws Exception {
		setRequestPathInfo("/editTawSystemRoleImport");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");
		// assertNotNull(request.getAttribute(Constants.TAWSYSTEMROLEIMPORT_KEY));

	}

	public void testSave() throws Exception {
		setRequestPathInfo("/editTawSystemRoleImport");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		// TawSystemRoleImportForm tawSystemRoleImportForm =
		// (TawSystemRoleImportForm)
		// request.getAttribute(Constants.TAWSYSTEMROLEIMPORT_KEY);
		// assertNotNull(tawSystemRoleImportForm);

		setRequestPathInfo("/saveTawSystemRoleImport");
		addRequestParameter("method", "Save");
		TawSystemRoleImportForm tawSystemRoleImportForm = new TawSystemRoleImportForm();
		// update the form's required string fields and add it back to the
		// request
		tawSystemRoleImportForm
				.setRoleId("JbAvDkRiJtTlRmQjBcZgTcUbPxIvJjArVxUvSsIwWbApQpIfQt");
		tawSystemRoleImportForm
				.setSubRoleId("EvWyRzYeKeFnCjLhZmDxDwCoTmRoSsTvYfHcVbPhVbMaWlXiPs");
		tawSystemRoleImportForm
				.setVersion("ClRiQhRvJdUxZcZxPrDlYmLeZdHdQhZgFzByIuIxCnApQqPzRz");
		tawSystemRoleImportForm.setVersionAt("LoTzOaUyAyZmBeVvCwNmLtWpP");

		// request.setAttribute(Constants.TAWSYSTEMROLEIMPORT_KEY,
		// tawSystemRoleImportForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");

		// verify success messages
		verifyActionMessages(new String[] { "tawSystemRoleImport.updated" });

	}

	public void testRemove() throws Exception {
		setRequestPathInfo("/editTawSystemRoleImport");
		addRequestParameter("method", "Delete");
		addRequestParameter("id", "2");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}
}
