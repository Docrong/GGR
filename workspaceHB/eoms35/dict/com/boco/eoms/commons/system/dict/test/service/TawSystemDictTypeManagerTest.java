
package com.boco.eoms.commons.system.dict.test.service;

import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.base.test.service.BaseManagerTestCase;
import com.boco.eoms.commons.system.dict.dao.ITawSystemDictTypeDao;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.impl.TawSystemDictTypeManagerImpl;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemDictTypeManagerTest extends BaseManagerTestCase {
    private final String tawSystemDictTypeId = "1";
    private TawSystemDictTypeManagerImpl tawSystemDictTypeManager = new TawSystemDictTypeManagerImpl();
    private Mock tawSystemDictTypeDao = null;
    private Mock tawSystemApplicationDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        tawSystemDictTypeDao = new Mock(ITawSystemDictTypeDao.class);
        tawSystemDictTypeManager.setDictTypeDao((ITawSystemDictTypeDao) tawSystemDictTypeDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tawSystemDictTypeManager = null;
    }

    public void testGetTawSystemDictTypes() throws Exception {
        List results = new ArrayList();
        TawSystemDictType tawSystemDictType = new TawSystemDictType();
        results.add(tawSystemDictType);

        // set expected behavior on dao
        tawSystemDictTypeDao.expects(once()).method("getTawSystemDictTypes")
                .will(returnValue(results));

        List tawSystemDictTypes = tawSystemDictTypeManager.getTawSystemDictTypes(null);
        assertTrue(tawSystemDictTypes.size() == 1);
        tawSystemDictTypeDao.verify();
    }

    public void testGetTawSystemDictType() throws Exception {
        // set expected behavior on dao
        tawSystemDictTypeDao.expects(once()).method("getTawSystemDictType")
                .will(returnValue(new TawSystemDictType()));
        TawSystemDictType tawSystemDictType = tawSystemDictTypeManager.getTawSystemDictType(tawSystemDictTypeId);
        assertTrue(tawSystemDictType != null);
        tawSystemDictTypeDao.verify();
    }

    public void testSaveTawSystemDictType() throws Exception {
        TawSystemDictType tawSystemDictType = new TawSystemDictType();

        // set expected behavior on dao
        tawSystemDictTypeDao.expects(once()).method("saveTawSystemDictType")
                .with(same(tawSystemDictType)).isVoid();

        tawSystemDictTypeManager.saveTawSystemDictType(tawSystemDictType);
        tawSystemDictTypeDao.verify();
    }

    public void testAddAndRemoveTawSystemDictType() throws Exception {
        TawSystemDictType tawSystemDictType = new TawSystemDictType();

        // set required fields
        tawSystemDictType.setDictName("MuUxDaSxEfSuXwKgPuUpPlMmKcKdToElGoTkCkCoAhFqYcKtFd");
        tawSystemDictType.setModuleId(new Integer(1933658566));
        tawSystemDictType.setModuleName("AwCwYlGyGrEfBeIhWiBtViOrIwMiNePuDgQaGlXuDrXuGyKmMr");

        // set expected behavior on dao
        tawSystemDictTypeDao.expects(once()).method("saveTawSystemDictType")
                .with(same(tawSystemDictType)).isVoid();
        tawSystemDictTypeManager.saveTawSystemDictType(tawSystemDictType);
        tawSystemDictTypeDao.verify();

        // reset expectations
        tawSystemDictTypeDao.reset();

        tawSystemDictTypeDao.expects(once()).method("removeTawSystemDictType").with(eq(new Integer(tawSystemDictTypeId)));
        tawSystemDictTypeManager.removeTawSystemDictType(tawSystemDictTypeId);
        tawSystemDictTypeDao.verify();

        // reset expectations
        tawSystemDictTypeDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(TawSystemDictType.class, tawSystemDictType.getId());
        tawSystemDictTypeDao.expects(once()).method("removeTawSystemDictType").isVoid();
        tawSystemDictTypeDao.expects(once()).method("getTawSystemDictType").will(throwException(ex));
        tawSystemDictTypeManager.removeTawSystemDictType(tawSystemDictTypeId);
        try {
            tawSystemDictTypeManager.getTawSystemDictType(tawSystemDictTypeId);
            fail("TawSystemDictType with identifier '" + tawSystemDictTypeId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        tawSystemDictTypeDao.verify();
    }

    public void testGetDictNameByDictId() throws Exception {
        // set expected behavior on dao
        tawSystemDictTypeDao.expects(once()).method("getDictNameByDictId")
                .will(returnValue(new TawSystemDictType()));

        // 输入参数为节点编号,测试需要根据实际调整
        String _strDictId = "9922";
        TawSystemDictType tawSystemDictType = tawSystemDictTypeManager.getDictByDictId(_strDictId);
        assertTrue(tawSystemDictType != null);
        tawSystemDictTypeDao.verify();
    }

    public void testGetParentTypeName() throws Exception {
        // set expected behavior on dao
        tawSystemDictTypeDao.expects(once()).method("getParentTypeName")
                .will(returnValue(new String()));

        String _strCurCode = "99";
        String _strResult = tawSystemDictTypeManager.getParentTypeName(_strCurCode);
        assertTrue(_strResult != null);
        tawSystemDictTypeDao.verify();
    }


}
