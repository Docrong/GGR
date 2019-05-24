package com.boco.eoms.commons.demo.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.demo.model.TestTable;
import com.boco.eoms.commons.demo.service.TestTableManager;
import com.boco.eoms.commons.demo.webapp.form.TestTableForm;

/**
 * Action class to handle CRUD on a TestTable object
 * 
 * @struts.action name="testTableForm" path="/testTables" scope="request"
 *                validate="false" parameter="method" input="mainMenu"
 * @struts.action name="testTableForm" path="/editTestTable" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="testTableForm" path="/saveTestTable" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/testTable/testTableForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/testTable/testTableList.jsp"
 * @struts.action-forward name="search" path="/testTables.html" redirect="true"
 */
public final class TestTableAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}

		ActionMessages messages = new ActionMessages();
		TestTableForm testTableForm = (TestTableForm) form;

		// Exceptions are caught by ActionExceptionHandler
		TestTableManager mgr = (TestTableManager) getBean("testTableManager");
		mgr.removeTestTable(testTableForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"testTable.deleted"));

		// save messages in session, so they'll survive the redirect
		saveMessages(request.getSession(), messages);

		return mapping.findForward("search");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}

		TestTableForm testTableForm = (TestTableForm) form;

		// if an id is passed in, look up the user - otherwise
		// don't do anything - user is doing an add
		if (testTableForm.getId() != null) {
			TestTableManager mgr = (TestTableManager) getBean("testTableManager");
			TestTable testTable = mgr.getTestTable(testTableForm.getId());
			testTableForm = (TestTableForm) convert(testTable);
			updateFormBean(mapping, request, testTableForm);
		}

		return mapping.findForward("edit");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();
		TestTableForm testTableForm = (TestTableForm) form;
		boolean isNew = ("".equals(testTableForm.getId()) || testTableForm
				.getId() == null);

		TestTableManager mgr = (TestTableManager) getBean("testTableManager");
		TestTable testTable = (TestTable) convert(testTableForm);
		mgr.saveTestTable(testTable);

		// add success messages
		if (isNew) {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"testTable.added"));

			// save messages in session to survive a redirect
			saveMessages(request.getSession(), messages);

			return mapping.findForward("search");
		} else {
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"testTable.updated"));
			saveMessages(request, messages);

			return mapping.findForward("search");
		}
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		TestTableForm testTableForm = (TestTableForm) form;
		TestTable testTable = (TestTable) convert(testTableForm);

		TestTableManager mgr = (TestTableManager) getBean("testTableManager");
//		request.setAttribute(Constants.TESTTABLE_LIST, mgr
//				.getTestTables(testTable));

		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
}
