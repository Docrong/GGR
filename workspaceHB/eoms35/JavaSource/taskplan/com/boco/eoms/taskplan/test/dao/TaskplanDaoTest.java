package com.boco.eoms.taskplan.test.dao;

import java.util.List;


import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.taskplan.dao.ITaskplanDao;
import com.boco.eoms.taskplan.model.Taskplan;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TaskplanDaoTest extends BaseDaoTestCase {
    private String taskplanId = new String("1");
    private ITaskplanDao dao = null;

    public void setTaskplanDao(ITaskplanDao dao) {
        this.dao = dao;
    }

    public void testAddTaskplan() throws Exception {
        Taskplan taskplan = new Taskplan();

        // set required fields

        dao.saveTaskplan(taskplan);

        // verify a primary key was assigned
        assertNotNull(taskplan.getId());

        // verify set fields are same after save
    }

    public void testGetTaskplan() throws Exception {
        Taskplan taskplan = dao.getTaskplan(taskplanId);
        assertNotNull(taskplan);
    }

    public void testGetTaskplans() throws Exception {
        Taskplan taskplan = new Taskplan();

        List results = dao.getTaskplans(taskplan);
        assertTrue(results.size() > 0);
    }

    public void testSaveTaskplan() throws Exception {
        Taskplan taskplan = dao.getTaskplan(taskplanId);

        // update required fields

        dao.saveTaskplan(taskplan);

    }

    public void testRemoveTaskplan() throws Exception {
        String removeId = new String("3");
        dao.removeTaskplan(removeId);
        try {
            dao.getTaskplan(removeId);
            fail("taskplan found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
