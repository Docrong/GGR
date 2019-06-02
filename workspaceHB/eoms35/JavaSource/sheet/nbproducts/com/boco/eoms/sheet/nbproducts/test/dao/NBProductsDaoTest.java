package com.boco.eoms.sheet.nbproducts.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.sheet.nbproducts.model.NBProducts;
import com.boco.eoms.sheet.nbproducts.dao.INBProductsDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class NBProductsDaoTest extends BaseDaoTestCase {
    private String nbproductsId = new String("1");
    private INBProductsDao dao = null;

    public void setNBProductsDao(INBProductsDao dao) {
        this.dao = dao;
    }

    public void testAddNBProducts() throws Exception {
        NBProducts nbproducts = new NBProducts();

        // set required fields

        java.lang.String creater = "KmIuMaAqRuTxCmZfZdPtBwOwAoHkSo";
        //nbproducts.setCreater(creater);        

        dao.saveNBProducts(nbproducts);

        // verify a primary key was assigned
        assertNotNull(nbproducts.getId());

        // verify set fields are same after save
        //assertEquals(creater, nbproducts.getCreater());
    }

    public void testGetNBProducts() throws Exception {
        NBProducts nbproducts = dao.getNBProducts(nbproductsId);
        assertNotNull(nbproducts);
    }

    public void testGetNBProductss() throws Exception {
        NBProducts nbproducts = new NBProducts();

        List results = dao.getNBProductss();
        assertTrue(results.size() > 0);
    }

    public void testSaveNBProducts() throws Exception {
        NBProducts nbproducts = dao.getNBProducts(nbproductsId);

        // update required fields
        java.lang.String creater = "QbKdYrWmEmHpYrLfSfJuEgIoNdFbOa";
        //nbproducts.setCreater(creater);        

        dao.saveNBProducts(nbproducts);

        //assertEquals(creater, nbproducts.getCreater());
    }

    public void testRemoveNBProducts() throws Exception {
        String removeId = new String("3");
        dao.removeNBProducts(removeId);
        try {
            dao.getNBProducts(removeId);
            fail("nbproducts found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
}
