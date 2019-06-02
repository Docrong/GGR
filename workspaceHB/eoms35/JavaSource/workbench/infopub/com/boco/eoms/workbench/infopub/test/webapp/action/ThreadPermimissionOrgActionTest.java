package com.boco.eoms.workbench.infopub.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.webapp.form.ThreadPermimissionOrgForm;

/**
 * 
 * <p>
 * Title:信息（贴子）记录组织结构权限
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:11:28 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadPermimissionOrgActionTest extends BaseStrutsTestCase {

	public ThreadPermimissionOrgActionTest(String name) {
		super(name);
	}

	public void testAdd() throws Exception {
		setRequestPathInfo("/saveThreadPermimissionOrg");
		addRequestParameter("method", "Save");

		ThreadPermimissionOrgForm threadPermimissionOrgForm = new ThreadPermimissionOrgForm();
		// set required fields

		request.setAttribute(InfopubConstants.THREADPERMIMISSIONORG_KEY,
				threadPermimissionOrgForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}

	public void testSearch() {
		setRequestPathInfo("/threadPermimissionOrgs");
		addRequestParameter("method", "Search");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("list");
		assertNotNull(request
				.getAttribute(InfopubConstants.THREADPERMIMISSIONORG_LIST));
	}

	public void testEdit() throws Exception {
		setRequestPathInfo("/editThreadPermimissionOrg");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");
		assertNotNull(request
				.getAttribute(InfopubConstants.THREADPERMIMISSIONORG_KEY));

	}

	public void testSave() throws Exception {
		setRequestPathInfo("/editThreadPermimissionOrg");
		addRequestParameter("method", "Edit");
		addRequestParameter("id", "1");

		actionPerform();

		ThreadPermimissionOrgForm threadPermimissionOrgForm = (ThreadPermimissionOrgForm) request
				.getAttribute(InfopubConstants.THREADPERMIMISSIONORG_KEY);
		assertNotNull(threadPermimissionOrgForm);

		setRequestPathInfo("/saveThreadPermimissionOrg");
		addRequestParameter("method", "Save");

		// update the form's required string fields and add it back to the
		// request

		request.setAttribute(InfopubConstants.THREADPERMIMISSIONORG_KEY,
				threadPermimissionOrgForm);

		actionPerform();

		verifyNoActionErrors();
		verifyForward("edit");

		// verify success messages
		verifyActionMessages(new String[] { "threadPermimissionOrg.updated" });

	}

	public void testRemove() throws Exception {
		setRequestPathInfo("/editThreadPermimissionOrg");
		addRequestParameter("method", "Delete");
		addRequestParameter("id", "2");

		actionPerform();

		verifyNoActionErrors();
		verifyForward("search");
	}
}
