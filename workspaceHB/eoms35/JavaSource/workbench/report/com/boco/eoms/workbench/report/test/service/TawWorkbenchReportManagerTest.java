
package com.boco.eoms.workbench.report.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.workbench.report.dao.ITawWorkbenchReportDao;
import com.boco.eoms.workbench.report.model.TawWorkbenchReport;
import com.boco.eoms.workbench.report.service.impl.TawWorkbenchReportManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawWorkbenchReportManagerTest extends BaseManagerTestCase {
    private final String tawWorkbenchReportId = "1";
    private TawWorkbenchReportManagerImpl tawWorkbenchReportManager = new TawWorkbenchReportManagerImpl();
    private Mock tawWorkbenchReportDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawWorkbenchReportDao = new Mock(ITawWorkbenchReportDao.class);
        tawWorkbenchReportManager.setTawWorkbenchReportDao((ITawWorkbenchReportDao) tawWorkbenchReportDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawWorkbenchReportManager = null;
    }

    public void testGetTawWorkbenchReports() throws Exception {
        List results = new ArrayList();
        TawWorkbenchReport tawWorkbenchReport = new TawWorkbenchReport();
        results.add(tawWorkbenchReport);

        // set expected behavior on dao
        tawWorkbenchReportDao.expects(once()).method("getTawWorkbenchReports")
            .will(returnValue(results));

        List tawWorkbenchReports = tawWorkbenchReportManager.getTawWorkbenchReports(null);
        assertTrue(tawWorkbenchReports.size() == 1);
        tawWorkbenchReportDao.verify();
    }

    public void testGetTawWorkbenchReport() throws Exception {
        // set expected behavior on dao
        tawWorkbenchReportDao.expects(once()).method("getTawWorkbenchReport")
            .will(returnValue(new TawWorkbenchReport()));
        TawWorkbenchReport tawWorkbenchReport = tawWorkbenchReportManager.getTawWorkbenchReport(tawWorkbenchReportId);
        assertTrue(tawWorkbenchReport != null);
        tawWorkbenchReportDao.verify();
    }

    public void testSaveTawWorkbenchReport() throws Exception {
        TawWorkbenchReport tawWorkbenchReport = new TawWorkbenchReport();

        // set expected behavior on dao
        tawWorkbenchReportDao.expects(once()).method("saveTawWorkbenchReport")
            .with(same(tawWorkbenchReport)).isVoid();

        tawWorkbenchReportManager.saveTawWorkbenchReport(tawWorkbenchReport);
        tawWorkbenchReportDao.verify();
    }

    public void testAddAndRemoveTawWorkbenchReport() throws Exception {
        TawWorkbenchReport tawWorkbenchReport = new TawWorkbenchReport();

        // set required fields

        // set expected behavior on dao
        tawWorkbenchReportDao.expects(once()).method("saveTawWorkbenchReport")
            .with(same(tawWorkbenchReport)).isVoid();
        tawWorkbenchReportManager.saveTawWorkbenchReport(tawWorkbenchReport);
        tawWorkbenchReportDao.verify();

        // reset expectations
        tawWorkbenchReportDao.reset();

        tawWorkbenchReportDao.expects(once()).method("removeTawWorkbenchReport").with(eq(new String(tawWorkbenchReportId)));
        tawWorkbenchReportManager.removeTawWorkbenchReport(tawWorkbenchReportId);
        tawWorkbenchReportDao.verify();

        // reset expectations
        tawWorkbenchReportDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawWorkbenchReport.class, tawWorkbenchReport.getId());
        tawWorkbenchReportDao.expects(once()).method("removeTawWorkbenchReport").isVoid();
        tawWorkbenchReportDao.expects(once()).method("getTawWorkbenchReport").will(throwException(ex));
        tawWorkbenchReportManager.removeTawWorkbenchReport(tawWorkbenchReportId);
        try {
            tawWorkbenchReportManager.getTawWorkbenchReport(tawWorkbenchReportId);
            fail("TawWorkbenchReport with identifier '" + tawWorkbenchReportId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawWorkbenchReportDao.verify();
    }
}
