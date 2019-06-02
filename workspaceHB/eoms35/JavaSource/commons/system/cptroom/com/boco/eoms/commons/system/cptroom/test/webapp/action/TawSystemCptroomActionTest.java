package com.boco.eoms.commons.system.cptroom.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.commons.system.cptroom.webapp.form.TawSystemCptroomForm;

public class TawSystemCptroomActionTest extends BaseStrutsTestCase {

    public TawSystemCptroomActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTawSystemCptroom");
        addRequestParameter("method", "Save");

        TawSystemCptroomForm tawSystemCptroomForm = new TawSystemCptroomForm();
        // set required fields

        //request.setAttribute(Constants.TAWSYSTEMCPTROOM_KEY, tawSystemCptroomForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/tawSystemCptrooms");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.TAWSYSTEMCPTROOM_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTawSystemCptroom");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.TAWSYSTEMCPTROOM_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTawSystemCptroom");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();
//
//        TawSystemCptroomForm tawSystemCptroomForm = (TawSystemCptroomForm) request.getAttribute(Constants.TAWSYSTEMCPTROOM_KEY);
//        assertNotNull(tawSystemCptroomForm);

        setRequestPathInfo("/saveTawSystemCptroom");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        //request.setAttribute(Constants.TAWSYSTEMCPTROOM_KEY, tawSystemCptroomForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"tawSystemCptroom.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTawSystemCptroom");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
