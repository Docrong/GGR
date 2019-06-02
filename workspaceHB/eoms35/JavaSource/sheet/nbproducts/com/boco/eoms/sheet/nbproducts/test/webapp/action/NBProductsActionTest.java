
package com.boco.eoms.sheet.nbproducts.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.sheet.nbproducts.webapp.form.NBProductsForm;

public class NBProductsActionTest extends BaseStrutsTestCase {

    public NBProductsActionTest(String name) {
        super(name);
    }

    public void testSearch() {
        setRequestPathInfo("/nbproductss");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.NBPRODUCTS_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editNBProducts");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.NBPRODUCTS_KEY));

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editNBProducts");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
