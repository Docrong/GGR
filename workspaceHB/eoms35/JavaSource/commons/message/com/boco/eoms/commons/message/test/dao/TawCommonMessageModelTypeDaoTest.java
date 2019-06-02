package com.boco.eoms.commons.message.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.message.model.TawCommonMessageModelType;
import com.boco.eoms.commons.message.dao.TawCommonMessageModelTypeDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawCommonMessageModelTypeDaoTest extends BaseDaoTestCase {

    private TawCommonMessageModelTypeDao dao = null;

    public void setTawCommonMessageModelTypeDao(TawCommonMessageModelTypeDao dao) {
        this.dao = dao;
    }

    public void testAddTawCommonMessageModelType() throws Exception {
        TawCommonMessageModelType tawCommonMessageModelType = new TawCommonMessageModelType();
        tawCommonMessageModelType.setModelid("88");
        tawCommonMessageModelType.setModelname("消息");
        tawCommonMessageModelType.setModelremark("消息配置测试");
        // set required fields

        dao.saveTawCommonMessageModelType(tawCommonMessageModelType);

        // verify a primary key was assigned
        assertNotNull(tawCommonMessageModelType.getId());

        // verify set fields are same after save
    }

    public void testGetTawCommonMessageModelType() throws Exception {
    	 TawCommonMessageModelType tawCommonMessageModelType = new TawCommonMessageModelType();
         tawCommonMessageModelType.setModelid("88");
         tawCommonMessageModelType.setModelname("消息");
         tawCommonMessageModelType.setModelremark("消息配置测试");
         // set required fields

         dao.saveTawCommonMessageModelType(tawCommonMessageModelType);
        TawCommonMessageModelType tawCommonMessageModelTypes = dao.getTawCommonMessageModelType(tawCommonMessageModelType.getId());
       
        
        assertNotNull(tawCommonMessageModelTypes);
    }

    public void testGetTawCommonMessageModelTypes() throws Exception {
    	 TawCommonMessageModelType tawCommonMessageModelType = new TawCommonMessageModelType();
         tawCommonMessageModelType.setModelid("88");
         tawCommonMessageModelType.setModelname("消息");
         tawCommonMessageModelType.setModelremark("消息配置测试");
         // set required fields

         dao.saveTawCommonMessageModelType(tawCommonMessageModelType);
        List results = dao.getTawCommonMessageModelTypes(tawCommonMessageModelType);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawCommonMessageModelType() throws Exception {
    	TawCommonMessageModelType tawCommonMessageModelType = new TawCommonMessageModelType();
        tawCommonMessageModelType.setModelid("88");
        tawCommonMessageModelType.setModelname("消息");
        tawCommonMessageModelType.setModelremark("消息配置测试");
        // set required fields

        dao.saveTawCommonMessageModelType(tawCommonMessageModelType);
        TawCommonMessageModelType tawCommonMessageModelTypes = dao.getTawCommonMessageModelType(tawCommonMessageModelType.getId());

        // update required fields

        dao.saveTawCommonMessageModelType(tawCommonMessageModelTypes);
        assertSame(tawCommonMessageModelType.getModelid(),tawCommonMessageModelTypes.getModelid());

    }

    public void testRemoveTawCommonMessageModelType() throws Exception {
        
    	TawCommonMessageModelType tawCommonMessageModelType = new TawCommonMessageModelType();
        tawCommonMessageModelType.setModelid("88");
        tawCommonMessageModelType.setModelname("消息");
        tawCommonMessageModelType.setModelremark("消息配置测试");
        // set required fields

        dao.saveTawCommonMessageModelType(tawCommonMessageModelType);
        dao.removeTawCommonMessageModelType(tawCommonMessageModelType.getId());
        try {
            dao.getTawCommonMessageModelType(tawCommonMessageModelType.getId());
            fail("tawCommonMessageModelType found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
    }
    
    public void testGetMessagType(){
    	TawCommonMessageModelType tawCommonMessageModelType = new TawCommonMessageModelType();
        tawCommonMessageModelType.setModelid("88");
        tawCommonMessageModelType.setModelname("消息");
        tawCommonMessageModelType.setModelremark("消息配置测试");
        // set required fields
        dao.saveTawCommonMessageModelType(tawCommonMessageModelType);
        TawCommonMessageModelType tawCommonMessageModelTypes = new TawCommonMessageModelType();
      
        tawCommonMessageModelTypes =  dao.getMessageType(tawCommonMessageModelType.getModelid());
        
        assertSame( tawCommonMessageModelType.getModelname(), tawCommonMessageModelTypes.getModelname());
    }
    
    public void testRemoveMessagType(){
    	TawCommonMessageModelType tawCommonMessageModelType = new TawCommonMessageModelType();
        tawCommonMessageModelType.setModelid("88");
        tawCommonMessageModelType.setModelname("消息");
        tawCommonMessageModelType.setModelremark("消息配置测试");
        // set required fields
        dao.saveTawCommonMessageModelType(tawCommonMessageModelType);
       
      
       dao.removeMessageType(tawCommonMessageModelType.getModelid());
        
       try {
           dao.getTawCommonMessageModelType(tawCommonMessageModelType.getId());
           fail("tawCommonMessageModelType found in database");
       } catch (ObjectRetrievalFailureException e) {
           assertNotNull(e.getMessage());
       }
    }
    
    
    public void testSaveUpMessagType(){
    	TawCommonMessageModelType tawCommonMessageModelType = new TawCommonMessageModelType();
        tawCommonMessageModelType.setModelid("88");
        tawCommonMessageModelType.setModelname("消息");
        tawCommonMessageModelType.setModelremark("消息配置测试");
        // set required fields
        dao.saveTawCommonMessageModelType(tawCommonMessageModelType);
       
      
       dao.saveAndUpdatemodeltype(tawCommonMessageModelType.getModelid(), "66", "66", "消息", "xiaoxipeizhiceshi");
        
       TawCommonMessageModelType tawCommonMessageModelTypes = new TawCommonMessageModelType();
       tawCommonMessageModelTypes = dao.getTawCommonMessageModelType(tawCommonMessageModelType.getId());
      
       assertSame(tawCommonMessageModelTypes.getModelid(),"66");
    }
    
}
