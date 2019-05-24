package com.boco.eoms.commons.system.priv.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.priv.webapp.form.TawSystemPrivAssignForm;

public class TawSystemPrivAssignActionTest extends BaseStrutsTestCase {

	public TawSystemPrivAssignActionTest(String name) {
		super(name);
	}

	public void testAdd() throws Exception {
		setRequestPathInfo("/saveTawSystemPrivAssign");
		addRequestParameter("method", "Save");

		TawSystemPrivAssignForm tawSystemPrivAssignForm = new TawSystemPrivAssignForm();
		// set required fields

		request.setAttribute(Constants.TAWSYSTEMPRIVASSIGN_KEY,
				tawSystemPrivAssignForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}

	public void testSearch() {
		setRequestPathInfo("/tawSystemPrivAssigns");
		addRequestParameter("method", "Search");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("list");
		assertNotNull(request.getAttribute(Constants.TAWSYSTEMPRIVASSIGN_LIST));
	}

	public void testEdit() throws Exception {
		setRequestPathInfo("/editTawSystemPrivAssign");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");
		assertNotNull(request.getAttribute(Constants.TAWSYSTEMPRIVASSIGN_KEY));

	}

	public void testSave() throws Exception {
		setRequestPathInfo("/editTawSystemPrivAssign");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		TawSystemPrivAssignForm tawSystemPrivAssignForm = (TawSystemPrivAssignForm) request
				.getAttribute(Constants.TAWSYSTEMPRIVASSIGN_KEY);
		assertNotNull(tawSystemPrivAssignForm);

		setRequestPathInfo("/saveTawSystemPrivAssign");
		addRequestParameter("method", "Save");

		// update the form's required string fields and add it back to the
		// request

		request.setAttribute(Constants.TAWSYSTEMPRIVASSIGN_KEY,
				tawSystemPrivAssignForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");

		// verify success messages
		verifyActionMessages(new String[] { "tawSystemPrivAssign.updated" });

	}

	public void testRemove() throws Exception {
		setRequestPathInfo("/editTawSystemPrivAssign");
		addRequestParameter("method", "Delete");
		addRequestParameter("id", "2");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}
}
