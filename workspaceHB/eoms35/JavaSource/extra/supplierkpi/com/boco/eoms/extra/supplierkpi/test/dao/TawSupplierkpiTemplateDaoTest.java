package com.boco.eoms.extra.supplierkpi.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiTemplate;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiTemplateDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSupplierkpiTemplateDaoTest extends BaseDaoTestCase {
    private String tawSupplierkpiTemplateId = new String("1");
    private TawSupplierkpiTemplateDao dao = null;

    public void setTawSupplierkpiTemplateDao(TawSupplierkpiTemplateDao dao) {
        this.dao = dao;
    }

    public void testAddTawSupplierkpiTemplate() throws Exception {
        TawSupplierkpiTemplate tawSupplierkpiTemplate = new TawSupplierkpiTemplate();

        // set required fields

        dao.saveTawSupplierkpiTemplate(tawSupplierkpiTemplate);

        // verify a primary key was assigned
        assertNotNull(tawSupplierkpiTemplate.getId());

        // verify set fields are same after save
    }

    public void testGetTawSupplierkpiTemplate() throws Exception {
        TawSupplierkpiTemplate tawSupplierkpiTemplate = dao.getTawSupplierkpiTemplate(tawSupplierkpiTemplateId);
        assertNotNull(tawSupplierkpiTemplate);
    }

    public void testGetTawSupplierkpiTemplates() throws Exception {
        TawSupplierkpiTemplate tawSupplierkpiTemplate = new TawSupplierkpiTemplate();

        List results = dao.getTawSupplierkpiTemplates(tawSupplierkpiTemplate);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawSupplierkpiTemplate() throws Exception {
        TawSupplierkpiTemplate tawSupplierkpiTemplate = dao.getTawSupplierkpiTemplate(tawSupplierkpiTemplateId);

        // update required fields

        dao.saveTawSupplierkpiTemplate(tawSupplierkpiTemplate);

    }

    public void testRemoveTawSupplierkpiTemplate() throws Exception {
        String removeId = new String("3");
        dao.removeTawSupplierkpiTemplate(removeId);
        try {
            dao.getTawSupplierkpiTemplate(removeId);
            fail("tawSupplierkpiTemplate found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
