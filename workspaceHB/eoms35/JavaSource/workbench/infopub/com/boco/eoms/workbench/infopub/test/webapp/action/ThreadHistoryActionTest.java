package com.boco.eoms.workbench.infopub.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadHistoryForm;
/**
 * 
 * <p>
 * Title:信息（贴子）阅读历史action测试
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:11:04 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 *
 */
public class ThreadHistoryActionTest extends BaseStrutsTestCase {

	public ThreadHistoryActionTest(String name) {
		super(name);
	}

	public void testAdd() throws Exception {
		setRequestPathInfo("/saveThreadHistory");
		addRequestParameter("method", "Save");

		ThreadHistoryForm threadHistoryForm = new ThreadHistoryForm();
		// set required fields

		request.setAttribute(InfopubConstants.THREADHISTORY_KEY,
				threadHistoryForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}

	public void testSearch() {
		setRequestPathInfo("/threadHistorys");
		addRequestParameter("method", "Search");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("list");
		assertNotNull(request.getAttribute(InfopubConstants.THREADHISTORY_LIST));
	}

	public void testEdit() throws Exception {
		setRequestPathInfo("/editThreadHistory");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");
		assertNotNull(request.getAttribute(InfopubConstants.THREADHISTORY_KEY));

	}

	public void testSave() throws Exception {
		setRequestPathInfo("/editThreadHistory");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		ThreadHistoryForm threadHistoryForm = (ThreadHistoryForm) request
				.getAttribute(InfopubConstants.THREADHISTORY_KEY);
		assertNotNull(threadHistoryForm);

		setRequestPathInfo("/saveThreadHistory");
		addRequestParameter("method", "Save");

		// update the form's required string fields and add it back to the
		// request

		request.setAttribute(InfopubConstants.THREADHISTORY_KEY,
				threadHistoryForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");

		// verify success messages
		verifyActionMessages(new String[] { "threadHistory.updated" });

	}

	public void testRemove() throws Exception {
		setRequestPathInfo("/editThreadHistory");
		addRequestParameter("method", "Delete");
		addRequestParameter("id", "2");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}
}
