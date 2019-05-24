package com.boco.eoms.commons.system.priv.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.priv.webapp.form.TawSystemPrivUserAssignForm;

public class TawSystemPrivUserAssignActionTest extends BaseStrutsTestCase {

	public TawSystemPrivUserAssignActionTest(String name) {
		super(name);
	}

	public void testAdd() throws Exception {
		setRequestPathInfo("/saveTawSystemPrivUserAssign");
		addRequestParameter("method", "Save");

		TawSystemPrivUserAssignForm tawSystemPrivUserAssignForm = new TawSystemPrivUserAssignForm();
		// set required fields

		request.setAttribute(Constants.TAWSYSTEMPRIVUSERASSIGN_KEY,
				tawSystemPrivUserAssignForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}

	public void testSearch() {
		setRequestPathInfo("/tawSystemPrivUserAssigns");
		addRequestParameter("method", "Search");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("list");
		assertNotNull(request
				.getAttribute(Constants.TAWSYSTEMPRIVUSERASSIGN_LIST));
	}

	public void testEdit() throws Exception {
		setRequestPathInfo("/editTawSystemPrivUserAssign");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");
		assertNotNull(request
				.getAttribute(Constants.TAWSYSTEMPRIVUSERASSIGN_KEY));

	}

	public void testSave() throws Exception {
		setRequestPathInfo("/editTawSystemPrivUserAssign");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		TawSystemPrivUserAssignForm tawSystemPrivUserAssignForm = (TawSystemPrivUserAssignForm) request
				.getAttribute(Constants.TAWSYSTEMPRIVUSERASSIGN_KEY);
		assertNotNull(tawSystemPrivUserAssignForm);

		setRequestPathInfo("/saveTawSystemPrivUserAssign");
		addRequestParameter("method", "Save");

		// update the form's required string fields and add it back to the
		// request

		request.setAttribute(Constants.TAWSYSTEMPRIVUSERASSIGN_KEY,
				tawSystemPrivUserAssignForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");

		// verify success messages
		verifyActionMessages(new String[] { "tawSystemPrivUserAssign.updated" });

	}

	public void testRemove() throws Exception {
		setRequestPathInfo("/editTawSystemPrivUserAssign");
		addRequestParameter("method", "Delete");
		addRequestParameter("id", "2");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}
}
