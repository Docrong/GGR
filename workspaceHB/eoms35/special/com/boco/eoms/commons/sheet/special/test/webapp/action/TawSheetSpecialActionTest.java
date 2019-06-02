package com.boco.eoms.commons.sheet.special.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.sheet.special.webapp.form.TawSheetSpecialForm;
/**
 * 
 * @author panlong
 *下午05:40:12
 */
public class TawSheetSpecialActionTest extends BaseStrutsTestCase {

    public TawSheetSpecialActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawSheetSpecial");
        addRequestParameter("method", "Save");

        TawSheetSpecialForm tawSheetSpecialForm = new TawSheetSpecialForm();
        // set required fields

        request.setAttribute(Constants.TAWSHEETSPECIAL_KEY, tawSheetSpecialForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawSheetSpecials");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        assertNotNull(request.getAttribute(Constants.TAWSHEETSPECIAL_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawSheetSpecial");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        assertNotNull(request.getAttribute(Constants.TAWSHEETSPECIAL_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawSheetSpecial");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        TawSheetSpecialForm tawSheetSpecialForm = (TawSheetSpecialForm) request.getAttribute(Constants.TAWSHEETSPECIAL_KEY);
        assertNotNull(tawSheetSpecialForm);

        setRequestPathInfo("/saveTawSheetSpecial");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        request.setAttribute(Constants.TAWSHEETSPECIAL_KEY, tawSheetSpecialForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawSheetSpecial.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawSheetSpecial");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
