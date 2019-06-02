package com.boco.eoms.workbench.memo.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.workbench.memo.util.MemoConstants;
import com.boco.eoms.workbench.memo.webapp.form.TawWorkbenchMemoForm;

public class TawWorkbenchMemoActionTest extends BaseStrutsTestCase {

	public TawWorkbenchMemoActionTest(String name) {
		super(name);
	}

	public void testAdd() throws Exception {
		setRequestPathInfo("/saveTawWorkbenchMemo");
		addRequestParameter("method", "Save");

		TawWorkbenchMemoForm tawWorkbenchMemoForm = new TawWorkbenchMemoForm();
		// set required fields

		request.setAttribute(MemoConstants.TAWWORKBENCHMEMO_KEY,
				tawWorkbenchMemoForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}

	public void testSearch() {
		setRequestPathInfo("/tawWorkbenchMemos");
		addRequestParameter("method", "Search");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("list");
		assertNotNull(request.getAttribute(MemoConstants.TAWWORKBENCHMEMO_LIST));
	}

	public void testEdit() throws Exception {
		setRequestPathInfo("/editTawWorkbenchMemo");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");
		assertNotNull(request.getAttribute(MemoConstants.TAWWORKBENCHMEMO_KEY));

	}

	public void testSave() throws Exception {
		setRequestPathInfo("/editTawWorkbenchMemo");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		TawWorkbenchMemoForm tawWorkbenchMemoForm = (TawWorkbenchMemoForm) request
				.getAttribute(MemoConstants.TAWWORKBENCHMEMO_KEY);
		assertNotNull(tawWorkbenchMemoForm);

		setRequestPathInfo("/saveTawWorkbenchMemo");
		addRequestParameter("method", "Save");

		// update the form's required string fields and add it back to the
		// request

		request.setAttribute(MemoConstants.TAWWORKBENCHMEMO_KEY,
				tawWorkbenchMemoForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");

		// verify success messages
		verifyActionMessages(new String[] { "tawWorkbenchMemo.updated" });

	}

	public void testRemove() throws Exception {
		setRequestPathInfo("/editTawWorkbenchMemo");
		addRequestParameter("method", "Delete");
		addRequestParameter("id", "2");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}
}
