package com.boco.eoms.workbench.infopub.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadAuditHistoryForm;

public class ThreadAuditHistoryActionTest extends BaseStrutsTestCase {

	public ThreadAuditHistoryActionTest(String name) {
		super(name);
	}

	public void testAdd() throws Exception {
		setRequestPathInfo("/saveThreadAuditHistory");
		addRequestParameter("method", "Save");

		ThreadAuditHistoryForm threadAuditHistoryForm = new ThreadAuditHistoryForm();
		// set required fields

		request.setAttribute(InfopubConstants.THREADAUDITHISTORY_KEY,
				threadAuditHistoryForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}

	public void testSearch() {
		setRequestPathInfo("/threadAuditHistorys");
		addRequestParameter("method", "Search");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("list");
		assertNotNull(request
				.getAttribute(InfopubConstants.THREADAUDITHISTORY_LIST));
	}

	public void testEdit() throws Exception {
		setRequestPathInfo("/editThreadAuditHistory");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");
		assertNotNull(request
				.getAttribute(InfopubConstants.THREADAUDITHISTORY_KEY));

	}

	public void testSave() throws Exception {
		setRequestPathInfo("/editThreadAuditHistory");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		ThreadAuditHistoryForm threadAuditHistoryForm = (ThreadAuditHistoryForm) request
				.getAttribute(InfopubConstants.THREADAUDITHISTORY_KEY);
		assertNotNull(threadAuditHistoryForm);

		setRequestPathInfo("/saveThreadAuditHistory");
		addRequestParameter("method", "Save");

		// update the form's required string fields and add it back to the
		// request

		request.setAttribute(InfopubConstants.THREADAUDITHISTORY_KEY,
				threadAuditHistoryForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");

		// verify success messages
		verifyActionMessages(new String[] { "threadAuditHistory.updated" });

	}

	public void testRemove() throws Exception {
		setRequestPathInfo("/editThreadAuditHistory");
		addRequestParameter("method", "Delete");
		addRequestParameter("id", "2");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}
}
