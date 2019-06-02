package com.boco.eoms.commons.system.priv.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.priv.webapp.form.TawSystemPrivOperationForm;

public class TawSystemPrivOperationActionTest extends BaseStrutsTestCase {

	public TawSystemPrivOperationActionTest(String name) {
		super(name);
	}

	public void testAdd() throws Exception {
		setRequestPathInfo("/saveTawSystemPrivOperation");
		addRequestParameter("method", "Save");

		TawSystemPrivOperationForm tawSystemPrivOperationForm = new TawSystemPrivOperationForm();
		// set required fields

		request.setAttribute(Constants.TAWSYSTEMPRIVOPERATION_KEY,
				tawSystemPrivOperationForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}

	public void testSearch() {
		setRequestPathInfo("/tawSystemPrivOperations");
		addRequestParameter("method", "Search");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("list");
		assertNotNull(request
				.getAttribute(Constants.TAWSYSTEMPRIVOPERATION_LIST));
	}

	public void testEdit() throws Exception {
		setRequestPathInfo("/editTawSystemPrivOperation");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");
		assertNotNull(request
				.getAttribute(Constants.TAWSYSTEMPRIVOPERATION_KEY));

	}

	public void testSave() throws Exception {
		setRequestPathInfo("/editTawSystemPrivOperation");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		TawSystemPrivOperationForm tawSystemPrivOperationForm = (TawSystemPrivOperationForm) request
				.getAttribute(Constants.TAWSYSTEMPRIVOPERATION_KEY);
		assertNotNull(tawSystemPrivOperationForm);

		setRequestPathInfo("/saveTawSystemPrivOperation");
		addRequestParameter("method", "Save");

		// update the form's required string fields and add it back to the
		// request

		request.setAttribute(Constants.TAWSYSTEMPRIVOPERATION_KEY,
				tawSystemPrivOperationForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");

		// verify success messages
		verifyActionMessages(new String[] { "tawSystemPrivOperation.updated" });

	}

	public void testRemove() throws Exception {
		setRequestPathInfo("/editTawSystemPrivOperation");
		addRequestParameter("method", "Delete");
		addRequestParameter("id", "2");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}
}
