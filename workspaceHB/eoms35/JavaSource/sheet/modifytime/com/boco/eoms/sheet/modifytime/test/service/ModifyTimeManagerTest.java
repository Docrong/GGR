
package com.boco.eoms.sheet.modifytime.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.sheet.modifytime.dao.IModifyTimeDao;
import com.boco.eoms.sheet.modifytime.model.ModifyTime;
import com.boco.eoms.sheet.modifytime.service.impl.ModifyTimeManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class ModifyTimeManagerTest extends BaseManagerTestCase {
    private final String modifytimeId = "1";
    private ModifyTimeManagerImpl modifytimeManager = new ModifyTimeManagerImpl();
    private Mock modifytimeDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        modifytimeDao = new Mock(IModifyTimeDao.class);
        modifytimeManager.setDao((IModifyTimeDao) modifytimeDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        modifytimeManager = null;
    }

    public void testGetModifyTimes() throws Exception {
        List results = new ArrayList();
        ModifyTime modifytime = new ModifyTime();
        results.add(modifytime);

        // set expected behavior on dao
        modifytimeDao.expects(once()).method("getModifyTimes")
                .will(returnValue(results));

        List modifytimes = modifytimeManager.getModifyTimes();
        assertTrue(modifytimes.size() == 1);
        modifytimeDao.verify();
    }

    public void testGetModifyTime() throws Exception {
        // set expected behavior on dao
        modifytimeDao.expects(once()).method("getModifyTime")
                .will(returnValue(new ModifyTime()));
        ModifyTime modifytime = modifytimeManager.getModifyTime(modifytimeId);
        assertTrue(modifytime != null);
        modifytimeDao.verify();
    }

    public void testSaveModifyTime() throws Exception {
        ModifyTime modifytime = new ModifyTime();

        // set expected behavior on dao
        modifytimeDao.expects(once()).method("saveModifyTime")
                .with(same(modifytime)).isVoid();

        modifytimeManager.saveModifyTime(modifytime);
        modifytimeDao.verify();
    }

}
