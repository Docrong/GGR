package com.boco.eoms.workbench.infopub.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.webapp.form.ForumsForm;
/**
 * 
 * <p>
 * Title:版块管理action测试类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:10:08 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ForumsActionTest extends BaseStrutsTestCase {

	public ForumsActionTest(String name) {
		super(name);
	}

	public void testAdd() throws Exception {
		setRequestPathInfo("/saveForums");
		addRequestParameter("method", "Save");

		ForumsForm forumsForm = new ForumsForm();
		// set required fields

		request.setAttribute(InfopubConstants.FORUMS_KEY, forumsForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}

	public void testSearch() {
		setRequestPathInfo("/forumss");
		addRequestParameter("method", "Search");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("list");
		assertNotNull(request.getAttribute(InfopubConstants.FORUMS_LIST));
	}

	public void testEdit() throws Exception {
		setRequestPathInfo("/editForums");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");
		assertNotNull(request.getAttribute(InfopubConstants.FORUMS_KEY));

	}

	public void testSave() throws Exception {
		setRequestPathInfo("/editForums");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		ForumsForm forumsForm = (ForumsForm) request
				.getAttribute(InfopubConstants.FORUMS_KEY);
		assertNotNull(forumsForm);

		setRequestPathInfo("/saveForums");
		addRequestParameter("method", "Save");

		// update the form's required string fields and add it back to the
		// request

		request.setAttribute(InfopubConstants.FORUMS_KEY, forumsForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");

		// verify success messages
		verifyActionMessages(new String[] { "forums.updated" });

	}

	public void testRemove() throws Exception {
		setRequestPathInfo("/editForums");
		addRequestParameter("method", "Delete");
		addRequestParameter("id", "2");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}
}
