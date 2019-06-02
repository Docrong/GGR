package com.boco.eoms.workbench.report.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.workbench.report.model.TawWorkbenchReport;
import com.boco.eoms.workbench.report.dao.ITawWorkbenchReportDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawWorkbenchReportDaoTest extends BaseDaoTestCase {
    private String tawWorkbenchReportId = new String("1");
    private ITawWorkbenchReportDao dao = null;

    public void setTawWorkbenchReportDao(ITawWorkbenchReportDao dao) {
        this.dao = dao;
    }

    public void testAddTawWorkbenchReport() throws Exception {
        TawWorkbenchReport tawWorkbenchReport = new TawWorkbenchReport();

        // set required fields

        dao.saveTawWorkbenchReport(tawWorkbenchReport);

        // verify a primary key was assigned
        assertNotNull(tawWorkbenchReport.getId());

        // verify set fields are same after save
    }

    public void testGetTawWorkbenchReport() throws Exception {
        TawWorkbenchReport tawWorkbenchReport = dao.getTawWorkbenchReport(tawWorkbenchReportId);
        assertNotNull(tawWorkbenchReport);
    }

    public void testGetTawWorkbenchReports() throws Exception {
        TawWorkbenchReport tawWorkbenchReport = new TawWorkbenchReport();

        List results = dao.getTawWorkbenchReports(tawWorkbenchReport);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawWorkbenchReport() throws Exception {
        TawWorkbenchReport tawWorkbenchReport = dao.getTawWorkbenchReport(tawWorkbenchReportId);

        // update required fields

        dao.saveTawWorkbenchReport(tawWorkbenchReport);

    }

    public void testRemoveTawWorkbenchReport() throws Exception {
        String removeId = new String("3");
        dao.removeTawWorkbenchReport(removeId);
        try {
            dao.getTawWorkbenchReport(removeId);
            fail("tawWorkbenchReport found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
