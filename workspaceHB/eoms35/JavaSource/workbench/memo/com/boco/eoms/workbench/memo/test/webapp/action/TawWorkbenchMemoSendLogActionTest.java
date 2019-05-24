package com.boco.eoms.workbench.memo.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.workbench.memo.util.MemoConstants;
import com.boco.eoms.workbench.memo.webapp.form.TawWorkbenchMemoSendLogForm;

public class TawWorkbenchMemoSendLogActionTest extends BaseStrutsTestCase {

	public TawWorkbenchMemoSendLogActionTest(String name) {
		super(name);
	}

	public void testAdd() throws Exception {
		setRequestPathInfo("/saveTawWorkbenchMemoSendLog");
		addRequestParameter("method", "Save");

		TawWorkbenchMemoSendLogForm tawWorkbenchMemoSendLogForm = new TawWorkbenchMemoSendLogForm();
		// set required fields

		request.setAttribute(MemoConstants.TAWWORKBENCHMEMOSENDLOG_KEY,
				tawWorkbenchMemoSendLogForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}

	public void testSearch() {
		setRequestPathInfo("/tawWorkbenchMemoSendLogs");
		addRequestParameter("method", "Search");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("list");
		assertNotNull(request
				.getAttribute(MemoConstants.TAWWORKBENCHMEMOSENDLOG_LIST));
	}

	public void testEdit() throws Exception {
		setRequestPathInfo("/editTawWorkbenchMemoSendLog");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");
		assertNotNull(request
				.getAttribute(MemoConstants.TAWWORKBENCHMEMOSENDLOG_KEY));

	}

	public void testSave() throws Exception {
		setRequestPathInfo("/editTawWorkbenchMemoSendLog");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		TawWorkbenchMemoSendLogForm tawWorkbenchMemoSendLogForm = (TawWorkbenchMemoSendLogForm) request
				.getAttribute(MemoConstants.TAWWORKBENCHMEMOSENDLOG_KEY);
		assertNotNull(tawWorkbenchMemoSendLogForm);

		setRequestPathInfo("/saveTawWorkbenchMemoSendLog");
		addRequestParameter("method", "Save");

		// update the form's required string fields and add it back to the
		// request

		request.setAttribute(MemoConstants.TAWWORKBENCHMEMOSENDLOG_KEY,
				tawWorkbenchMemoSendLogForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");

		// verify success messages
		verifyActionMessages(new String[] { "tawWorkbenchMemoSendLog.updated" });

	}

	public void testRemove() throws Exception {
		setRequestPathInfo("/editTawWorkbenchMemoSendLog");
		addRequestParameter("method", "Delete");
		addRequestParameter("id", "2");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}
}
