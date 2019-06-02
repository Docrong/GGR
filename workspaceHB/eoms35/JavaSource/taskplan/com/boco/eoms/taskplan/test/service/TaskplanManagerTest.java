
package com.boco.eoms.taskplan.test.service;

import java.util.List;
import java.util.ArrayList;


import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.taskplan.dao.ITaskplanDao;
import com.boco.eoms.taskplan.model.Taskplan;
import com.boco.eoms.taskplan.service.impl.TaskplanManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TaskplanManagerTest extends BaseManagerTestCase {
    private final String taskplanId = "1";
    private TaskplanManagerImpl taskplanManager = new TaskplanManagerImpl();
    private Mock taskplanDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        taskplanDao = new Mock(ITaskplanDao.class);
        taskplanManager.setTaskplanDao((ITaskplanDao) taskplanDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        taskplanManager = null;
    }

    public void testGetTaskplans() throws Exception {
        List results = new ArrayList();
        Taskplan taskplan = new Taskplan();
        results.add(taskplan);

        // set expected behavior on dao
        taskplanDao.expects(once()).method("getTaskplans")
            .will(returnValue(results));

        List taskplans = taskplanManager.getTaskplans(null);
        assertTrue(taskplans.size() == 1);
        taskplanDao.verify();
    }

    public void testGetTaskplan() throws Exception {
        // set expected behavior on dao
        taskplanDao.expects(once()).method("getTaskplan")
            .will(returnValue(new Taskplan()));
        Taskplan taskplan = taskplanManager.getTaskplan(taskplanId);
        assertTrue(taskplan != null);
        taskplanDao.verify();
    }

    public void testSaveTaskplan() throws Exception {
        Taskplan taskplan = new Taskplan();

        // set expected behavior on dao
        taskplanDao.expects(once()).method("saveTaskplan")
            .with(same(taskplan)).isVoid();

        taskplanManager.saveTaskplan(taskplan);
        taskplanDao.verify();
    }

    public void testAddAndRemoveTaskplan() throws Exception {
        Taskplan taskplan = new Taskplan();

        // set required fields

        // set expected behavior on dao
        taskplanDao.expects(once()).method("saveTaskplan")
            .with(same(taskplan)).isVoid();
        taskplanManager.saveTaskplan(taskplan);
        taskplanDao.verify();

        // reset expectations
        taskplanDao.reset();

        taskplanDao.expects(once()).method("removeTaskplan").with(eq(new String(taskplanId)));
        taskplanManager.removeTaskplan(taskplanId);
        taskplanDao.verify();

        // reset expectations
        taskplanDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(Taskplan.class, taskplan.getId());
        taskplanDao.expects(once()).method("removeTaskplan").isVoid();
        taskplanDao.expects(once()).method("getTaskplan").will(throwException(ex));
        taskplanManager.removeTaskplan(taskplanId);
        try {
            taskplanManager.getTaskplan(taskplanId);
            fail("Taskplan with identifier '" + taskplanId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        taskplanDao.verify();
    }
}
