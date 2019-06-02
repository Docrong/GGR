
package com.boco.eoms.sheet.modifytime.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.sheet.modifytime.webapp.form.ModifyTimeForm;

public class ModifyTimeActionTest extends BaseStrutsTestCase {

    public ModifyTimeActionTest(String name) {
        super(name);
    }

    public void testSearch() {
        setRequestPathInfo("/modifytimes");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.MODIFYTIME_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editModifyTime");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.MODIFYTIME_KEY));

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editModifyTime");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
