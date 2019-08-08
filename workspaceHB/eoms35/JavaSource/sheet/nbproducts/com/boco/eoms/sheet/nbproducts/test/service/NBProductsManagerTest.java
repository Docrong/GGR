
package com.boco.eoms.sheet.nbproducts.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.sheet.nbproducts.dao.INBProductsDao;
import com.boco.eoms.sheet.nbproducts.model.NBProducts;
import com.boco.eoms.sheet.nbproducts.service.impl.NBProductsManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class NBProductsManagerTest extends BaseManagerTestCase {
    private final String nbproductsId = "1";
    private NBProductsManagerImpl nbproductsManager = new NBProductsManagerImpl();
    private Mock nbproductsDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        nbproductsDao = new Mock(INBProductsDao.class);
        nbproductsManager.setNBProductsDao((INBProductsDao) nbproductsDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        nbproductsManager = null;
    }

    public void testGetNBProductss() throws Exception {
        List results = new ArrayList();
        NBProducts nbproducts = new NBProducts();
        results.add(nbproducts);

        // set expected behavior on dao
        nbproductsDao.expects(once()).method("getNBProductss")
                .will(returnValue(results));

        List nbproductss = nbproductsManager.getNBProductss();
        assertTrue(nbproductss.size() == 1);
        nbproductsDao.verify();
    }

    public void testGetNBProducts() throws Exception {
        // set expected behavior on dao
        nbproductsDao.expects(once()).method("getNBProducts")
                .will(returnValue(new NBProducts()));
        NBProducts nbproducts = nbproductsManager.getNBProducts(nbproductsId);
        assertTrue(nbproducts != null);
        nbproductsDao.verify();
    }

    public void testSaveNBProducts() throws Exception {
        NBProducts nbproducts = new NBProducts();

        // set expected behavior on dao
        nbproductsDao.expects(once()).method("saveNBProducts")
                .with(same(nbproducts)).isVoid();

        nbproductsManager.saveNBProducts(nbproducts);
        nbproductsDao.verify();
    }

}
