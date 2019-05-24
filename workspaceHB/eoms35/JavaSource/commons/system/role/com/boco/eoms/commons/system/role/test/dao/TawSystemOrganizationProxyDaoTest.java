package com.boco.eoms.commons.system.role.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.system.role.model.TawSystemOrganizationProxy;
import com.boco.eoms.commons.system.role.dao.TawSystemOrganizationProxyDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemOrganizationProxyDaoTest extends BaseDaoTestCase {
    private String tawSystemOrganizationProxyId = new String("1");
    private TawSystemOrganizationProxyDao dao = null;

    public void setTawSystemOrganizationProxyDao(TawSystemOrganizationProxyDao dao) {
        this.dao = dao;
    }

    public void testAddTawSystemOrganizationProxy() throws Exception {
        TawSystemOrganizationProxy tawSystemOrganizationProxy = new TawSystemOrganizationProxy();

        // set required fields
        tawSystemOrganizationProxy.setProxyRoleId(10);
        tawSystemOrganizationProxy.setFromUserId("admin");

        dao.saveTawSystemOrganizationProxy(tawSystemOrganizationProxy);

        // verify a primary key was assigned
        assertNotNull(tawSystemOrganizationProxy.getId());

        // verify set fields are same after save
    }

    public void testGetTawSystemOrganizationProxy() throws Exception {
    	TawSystemOrganizationProxy tawSystemOrganizationProxy = new TawSystemOrganizationProxy();

        // set required fields
        tawSystemOrganizationProxy.setProxyRoleId(10);
        tawSystemOrganizationProxy.setFromUserId("admin");

        dao.saveTawSystemOrganizationProxy(tawSystemOrganizationProxy);

        // verify a primary key was assigned
        assertNotNull(tawSystemOrganizationProxy.getId());
        
        
        
        tawSystemOrganizationProxy = dao.getTawSystemOrganizationProxy(tawSystemOrganizationProxy.getId());
        assertNotNull(tawSystemOrganizationProxy);
    }

    public void testGetTawSystemOrganizationProxys() throws Exception {
    	TawSystemOrganizationProxy tawSystemOrganizationProxy = new TawSystemOrganizationProxy();

        // set required fields
        tawSystemOrganizationProxy.setProxyRoleId(10);
        tawSystemOrganizationProxy.setFromUserId("admin");

        dao.saveTawSystemOrganizationProxy(tawSystemOrganizationProxy);

        // verify a primary key was assigned
        assertNotNull(tawSystemOrganizationProxy.getId());
        
        
        tawSystemOrganizationProxy = new TawSystemOrganizationProxy();

        List results = dao.getTawSystemOrganizationProxys(tawSystemOrganizationProxy);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawSystemOrganizationProxy() throws Exception {
    	TawSystemOrganizationProxy tawSystemOrganizationProxy = new TawSystemOrganizationProxy();

        // set required fields
        tawSystemOrganizationProxy.setProxyRoleId(10);
        tawSystemOrganizationProxy.setFromUserId("admin");

        dao.saveTawSystemOrganizationProxy(tawSystemOrganizationProxy);

        // verify a primary key was assigned
        assertNotNull(tawSystemOrganizationProxy.getId());
        
        
        tawSystemOrganizationProxy = dao.getTawSystemOrganizationProxy(tawSystemOrganizationProxy.getId());

        // update required fields
        tawSystemOrganizationProxy.setFromUserId("123");
        dao.saveTawSystemOrganizationProxy(tawSystemOrganizationProxy);
        
        tawSystemOrganizationProxy = dao.getTawSystemOrganizationProxy(tawSystemOrganizationProxy.getId());
        assertTrue(tawSystemOrganizationProxy.getFromUserId()=="123");

    }

    public void testRemoveTawSystemOrganizationProxy() throws Exception {
    	TawSystemOrganizationProxy tawSystemOrganizationProxy = new TawSystemOrganizationProxy();

        // set required fields
        tawSystemOrganizationProxy.setProxyRoleId(10);
        tawSystemOrganizationProxy.setFromUserId("admin");

        dao.saveTawSystemOrganizationProxy(tawSystemOrganizationProxy);

        // verify a primary key was assigned
        assertNotNull(tawSystemOrganizationProxy.getId());
        
        
        dao.removeTawSystemOrganizationProxy(tawSystemOrganizationProxy.getId());
        try {
            dao.getTawSystemOrganizationProxy(tawSystemOrganizationProxy.getId());
            fail("tawSystemOrganizationProxy found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
    
    public void testGetMain(){
    	List list = dao.getMain();
    	assertNotNull(list);
    }
}
