package com.boco.eoms.workbench.infopub.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadForm;
/**
 * 
 * <p>
 * Title:信息（贴子）action测试类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:10:29 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadActionTest extends BaseStrutsTestCase {

	public ThreadActionTest(String name) {
		super(name);
	}

	public void testAdd() throws Exception {
		setRequestPathInfo("/saveThread");
		addRequestParameter("method", "Save");

		ThreadForm threadForm = new ThreadForm();
		// set required fields

		request.setAttribute(InfopubConstants.THREAD_KEY, threadForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}

	public void testSearch() {
		setRequestPathInfo("/threads");
		addRequestParameter("method", "Search");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("list");
		assertNotNull(request.getAttribute(InfopubConstants.THREAD_LIST));
	}

	public void testEdit() throws Exception {
		setRequestPathInfo("/editThread");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");
		assertNotNull(request.getAttribute(InfopubConstants.THREAD_KEY));

	}

	public void testSave() throws Exception {
		setRequestPathInfo("/editThread");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		ThreadForm threadForm = (ThreadForm) request
				.getAttribute(InfopubConstants.THREAD_KEY);
		assertNotNull(threadForm);

		setRequestPathInfo("/saveThread");
		addRequestParameter("method", "Save");

		// update the form's required string fields and add it back to the
		// request

		request.setAttribute(InfopubConstants.THREAD_KEY, threadForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");

		// verify success messages
		verifyActionMessages(new String[] { "thread.updated" });

	}

	public void testRemove() throws Exception {
		setRequestPathInfo("/editThread");
		addRequestParameter("method", "Delete");
		addRequestParameter("id", "2");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}
}
