
package com.boco.eoms.taskplan.test.webapp.action;

import com.boco.eoms.base.webapp.action.BaseStrutsTestCase;
import com.boco.eoms.base.Constants;
import com.boco.eoms.taskplan.webapp.form.TaskplanForm;

public class TaskplanActionTest extends BaseStrutsTestCase {

    public TaskplanActionTest(String name) {
        super(name);
    }

    public void testAdd() throws Exception {
        setRequestPathInfo("/saveTaskplan");
        addRequestParameter("method", "Save");

        TaskplanForm taskplanForm = new TaskplanForm();
        // set required fields

       

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }

    public void testSearch() {
        setRequestPathInfo("/taskplans");
        addRequestParameter("method", "Search");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("list");
        //assertNotNull(request.getAttribute(Constants.TASKPLAN_LIST));
    }

    public void testEdit() throws Exception {
        setRequestPathInfo("/editTaskplan");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");
        //assertNotNull(request.getAttribute(Constants.TASKPLAN_KEY));

    }

    public void testSave() throws Exception {
        setRequestPathInfo("/editTaskplan");
        addRequestParameter("method", "Edit");
        addRequestParameter("id", "1");

        actionPerform();

        //TaskplanForm taskplanForm = (TaskplanForm) request.getAttribute(Constants.TASKPLAN_KEY);
        //assertNotNull(taskplanForm);

        setRequestPathInfo("/saveTaskplan");
        addRequestParameter("method", "Save");

        // update the form's required string fields and add it back to the request

        //request.setAttribute(Constants.TASKPLAN_KEY, taskplanForm);

        actionPerform();

        verifyNoActionErrors();
        verifyForward("edit");

        // verify success messages
        verifyActionMessages(new String[] {"taskplan.updated"});

    }

    public void testRemove() throws Exception {
        setRequestPathInfo("/editTaskplan");
        addRequestParameter("method", "Delete");
        addRequestParameter("id", "2");

        actionPerform();

        verifyNoActionErrors();
        verifyForward("search");
    }
}
