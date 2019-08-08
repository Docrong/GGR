package com.boco.eoms.commons.system.priv.test.dao;

import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivOperationDao;

import org.springframework.orm.ObjectRetrievalFailureException;

public class TawSystemPrivOperationDaoTest extends BaseDaoTestCase {
    private String tawSystemPrivOperationId = new String("1");

    private TawSystemPrivOperationDao dao = null;

    public void setTawSystemPrivOperationDao(TawSystemPrivOperationDao dao) {
        this.dao = dao;
    }

    public void testAddTawSystemPrivOperation() throws Exception {
        TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();

        tawSystemPrivOperation.setCode("1010");
        tawSystemPrivOperation.setIsApp("1");
        tawSystemPrivOperation.setName("工建系统");
        tawSystemPrivOperation.setParentcode("10");

        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);

        // verify a primary key was assigned
        assertNotNull(tawSystemPrivOperation.getId());

        // verify set fields are same after save
    }

    public void testGetTawSystemPrivOperation() throws Exception {
        TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
        tawSystemPrivOperation.setCode("1010");
        tawSystemPrivOperation.setIsApp("1");
        tawSystemPrivOperation.setName("工建系统");
        tawSystemPrivOperation.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);
        TawSystemPrivOperation tawSystemPrivOperation1 = dao
                .getTawSystemPrivOperation(tawSystemPrivOperation.getId());
        assertSame(tawSystemPrivOperation, tawSystemPrivOperation1);
    }

    public void testGetTawSystemPrivOperations() throws Exception {
        TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
        tawSystemPrivOperation.setCode("1010");
        tawSystemPrivOperation.setIsApp("1");
        tawSystemPrivOperation.setName("工建系统");
        tawSystemPrivOperation.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);

        TawSystemPrivOperation tawSystemPrivOperation1 = new TawSystemPrivOperation();

        List results = dao.getTawSystemPrivOperations(tawSystemPrivOperation1);
        assertTrue(results.size() > 0);
    }

    public void testSaveTawSystemPrivOperation() throws Exception {
        TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
        tawSystemPrivOperation.setCode("1010");
        tawSystemPrivOperation.setIsApp("1");
        tawSystemPrivOperation.setName("工建系统");
        tawSystemPrivOperation.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);

        TawSystemPrivOperation tawSystemPrivOperation1 = dao
                .getTawSystemPrivOperation(tawSystemPrivOperation.getId());

        // update required fields
        tawSystemPrivOperation1.setName("测试架构");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);

        assertSame(tawSystemPrivOperation, tawSystemPrivOperation1);

    }

    public void testRemoveTawSystemPrivOperation() throws Exception {
        TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
        tawSystemPrivOperation.setCode("1010");
        tawSystemPrivOperation.setIsApp("1");
        tawSystemPrivOperation.setName("工建系统");
        tawSystemPrivOperation.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);

        dao.removeTawSystemPrivOperation(tawSystemPrivOperation.getId());
        try {
            dao.getTawSystemPrivOperation(tawSystemPrivOperation.getId());
            fail("tawSystemPrivOperation found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }

    }

    /**
     * 得到某一模块下的所有对象（包括子模块与功能项,包括该模块）
     */
    public void testgetAllSubObjects() throws Exception {
        TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
        tawSystemPrivOperation.setCode("10");
        tawSystemPrivOperation.setIsApp("1");
        tawSystemPrivOperation.setName("工建系统");
        tawSystemPrivOperation.setParentcode("0");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);

        TawSystemPrivOperation tawSystemPrivOperation1 = new TawSystemPrivOperation();
        tawSystemPrivOperation1.setCode("1010");
        tawSystemPrivOperation1.setIsApp("1");
        tawSystemPrivOperation1.setName("工建子系统");
        tawSystemPrivOperation1.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation1);

        TawSystemPrivOperation tawSystemPrivOperation11 = new TawSystemPrivOperation();
        tawSystemPrivOperation11.setCode("101001");
        tawSystemPrivOperation11.setIsApp("1");
        tawSystemPrivOperation11.setName("子系统");
        tawSystemPrivOperation11.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation11);

        TawSystemPrivOperation tawSystemPrivOperation12 = new TawSystemPrivOperation();
        tawSystemPrivOperation12.setCode("101002");
        tawSystemPrivOperation12.setIsApp("0");
        tawSystemPrivOperation12.setName("离开地方军统");
        tawSystemPrivOperation12.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation12);

        TawSystemPrivOperation tawSystemPrivOperation2 = new TawSystemPrivOperation();
        tawSystemPrivOperation2.setCode("1011");
        tawSystemPrivOperation2.setIsApp("0");
        tawSystemPrivOperation2.setName("工建子系统");
        tawSystemPrivOperation2.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation2);

        String testCodeValue = "10";
        List list = null;
        list = dao.getAllSubObjects(testCodeValue);
        assertTrue(list.size() > 0);
    }

    /**
     * 得到某一模块下的所有子模块 （包括子模块的子模块，,包括该模块）
     */
    public void testgetAllSubModules() throws Exception {
        TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
        tawSystemPrivOperation.setCode("10");
        tawSystemPrivOperation.setIsApp("1");
        tawSystemPrivOperation.setName("工建系统");
        tawSystemPrivOperation.setParentcode("0");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);

        TawSystemPrivOperation tawSystemPrivOperation1 = new TawSystemPrivOperation();
        tawSystemPrivOperation1.setCode("1010");
        tawSystemPrivOperation1.setIsApp("1");
        tawSystemPrivOperation1.setName("工建子系统");
        tawSystemPrivOperation1.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation1);

        TawSystemPrivOperation tawSystemPrivOperation11 = new TawSystemPrivOperation();
        tawSystemPrivOperation11.setCode("101001");
        tawSystemPrivOperation11.setIsApp("1");
        tawSystemPrivOperation11.setName("子系统");
        tawSystemPrivOperation11.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation11);

        TawSystemPrivOperation tawSystemPrivOperation12 = new TawSystemPrivOperation();
        tawSystemPrivOperation12.setCode("101002");
        tawSystemPrivOperation12.setIsApp("0");
        tawSystemPrivOperation12.setName("离开地方军统");
        tawSystemPrivOperation12.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation12);

        TawSystemPrivOperation tawSystemPrivOperation2 = new TawSystemPrivOperation();
        tawSystemPrivOperation2.setCode("1011");
        tawSystemPrivOperation2.setIsApp("0");
        tawSystemPrivOperation2.setName("工建子系统");
        tawSystemPrivOperation2.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation2);

        String testCodeValue = tawSystemPrivOperation.getCode();
        List list = null;
        list = dao.getAllSubModules(testCodeValue);
        assertTrue(list.size() == 2);
    }

    /**
     * 得到某一模块下的所有功能项（包括子模块的功能项）
     */

    public void testgetAllSubOperations() throws Exception {
        TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
        tawSystemPrivOperation.setCode("10");
        tawSystemPrivOperation.setIsApp("1");
        tawSystemPrivOperation.setName("工建系统");
        tawSystemPrivOperation.setParentcode("0");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);

        TawSystemPrivOperation tawSystemPrivOperation1 = new TawSystemPrivOperation();
        tawSystemPrivOperation1.setCode("1010");
        tawSystemPrivOperation1.setIsApp("1");
        tawSystemPrivOperation1.setName("工建子系统");
        tawSystemPrivOperation1.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation1);

        TawSystemPrivOperation tawSystemPrivOperation11 = new TawSystemPrivOperation();
        tawSystemPrivOperation11.setCode("101001");
        tawSystemPrivOperation11.setIsApp("1");
        tawSystemPrivOperation11.setName("子系统");
        tawSystemPrivOperation11.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation11);

        TawSystemPrivOperation tawSystemPrivOperation12 = new TawSystemPrivOperation();
        tawSystemPrivOperation12.setCode("101002");
        tawSystemPrivOperation12.setIsApp("0");
        tawSystemPrivOperation12.setName("离开地方军统");
        tawSystemPrivOperation12.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation12);

        TawSystemPrivOperation tawSystemPrivOperation2 = new TawSystemPrivOperation();
        tawSystemPrivOperation2.setCode("1011");
        tawSystemPrivOperation2.setIsApp("0");
        tawSystemPrivOperation2.setName("工建子系统");
        tawSystemPrivOperation2.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation2);

        String testCodeValue = tawSystemPrivOperation.getCode();
        List list = null;
        list = dao.getAllSubOperations(testCodeValue);
        assertTrue(list.size() == 2);
    }

    /**
     * 得到某一模块下的对象（仅仅是有关该模块的）
     */
    public void testgetObjects() throws Exception {
        TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
        tawSystemPrivOperation.setCode("10");
        tawSystemPrivOperation.setIsApp("1");
        tawSystemPrivOperation.setName("工建系统");
        tawSystemPrivOperation.setParentcode("0");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);

        TawSystemPrivOperation tawSystemPrivOperation1 = new TawSystemPrivOperation();
        tawSystemPrivOperation1.setCode("1010");
        tawSystemPrivOperation1.setIsApp("1");
        tawSystemPrivOperation1.setName("工建子系统");
        tawSystemPrivOperation1.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation1);

        TawSystemPrivOperation tawSystemPrivOperation11 = new TawSystemPrivOperation();
        tawSystemPrivOperation11.setCode("101001");
        tawSystemPrivOperation11.setIsApp("1");
        tawSystemPrivOperation11.setName("子系统");
        tawSystemPrivOperation11.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation11);

        TawSystemPrivOperation tawSystemPrivOperation12 = new TawSystemPrivOperation();
        tawSystemPrivOperation12.setCode("101002");
        tawSystemPrivOperation12.setIsApp("0");
        tawSystemPrivOperation12.setName("离开地方军统");
        tawSystemPrivOperation12.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation12);

        TawSystemPrivOperation tawSystemPrivOperation2 = new TawSystemPrivOperation();
        tawSystemPrivOperation2.setCode("1011");
        tawSystemPrivOperation2.setIsApp("0");
        tawSystemPrivOperation2.setName("工建子系统");
        tawSystemPrivOperation2.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation2);

        String testCodeValue = tawSystemPrivOperation.getCode();
        List list = null;
        list = dao.getObjects(testCodeValue);
        assertTrue(list.size() == 2);
    }

    /**
     * 得到某一模块下的功能项（仅仅是有关该模块的）
     */
    public void testgetOperations() throws Exception {
        TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
        tawSystemPrivOperation.setCode("10");
        tawSystemPrivOperation.setIsApp("1");
        tawSystemPrivOperation.setName("工建系统");
        tawSystemPrivOperation.setParentcode("0");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);

        TawSystemPrivOperation tawSystemPrivOperation1 = new TawSystemPrivOperation();
        tawSystemPrivOperation1.setCode("1010");
        tawSystemPrivOperation1.setIsApp("1");
        tawSystemPrivOperation1.setName("工建子系统");
        tawSystemPrivOperation1.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation1);

        TawSystemPrivOperation tawSystemPrivOperation11 = new TawSystemPrivOperation();
        tawSystemPrivOperation11.setCode("101001");
        tawSystemPrivOperation11.setIsApp("1");
        tawSystemPrivOperation11.setName("子系统");
        tawSystemPrivOperation11.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation11);

        TawSystemPrivOperation tawSystemPrivOperation12 = new TawSystemPrivOperation();
        tawSystemPrivOperation12.setCode("101002");
        tawSystemPrivOperation12.setIsApp("0");
        tawSystemPrivOperation12.setName("离开地方军统");
        tawSystemPrivOperation12.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation12);

        TawSystemPrivOperation tawSystemPrivOperation2 = new TawSystemPrivOperation();
        tawSystemPrivOperation2.setCode("1011");
        tawSystemPrivOperation2.setIsApp("0");
        tawSystemPrivOperation2.setName("工建子系统");
        tawSystemPrivOperation2.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation2);

        String testCodeValue = tawSystemPrivOperation.getCode();
        List list = null;
        list = dao.getOperations(testCodeValue);
        assertTrue(list.size() == 1);
    }

    /**
     * 得到某一模块下的子模块(仅仅是有关该模块的)
     */
    public void testgetModules() throws Exception {
        TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
        tawSystemPrivOperation.setCode("10");
        tawSystemPrivOperation.setIsApp("1");
        tawSystemPrivOperation.setName("工建系统");
        tawSystemPrivOperation.setParentcode("0");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);

        TawSystemPrivOperation tawSystemPrivOperation1 = new TawSystemPrivOperation();
        tawSystemPrivOperation1.setCode("1010");
        tawSystemPrivOperation1.setIsApp("1");
        tawSystemPrivOperation1.setName("工建子系统");
        tawSystemPrivOperation1.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation1);

        TawSystemPrivOperation tawSystemPrivOperation11 = new TawSystemPrivOperation();
        tawSystemPrivOperation11.setCode("101001");
        tawSystemPrivOperation11.setIsApp("1");
        tawSystemPrivOperation11.setName("子系统");
        tawSystemPrivOperation11.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation11);

        TawSystemPrivOperation tawSystemPrivOperation12 = new TawSystemPrivOperation();
        tawSystemPrivOperation12.setCode("101002");
        tawSystemPrivOperation12.setIsApp("0");
        tawSystemPrivOperation12.setName("离开地方军统");
        tawSystemPrivOperation12.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation12);

        TawSystemPrivOperation tawSystemPrivOperation2 = new TawSystemPrivOperation();
        tawSystemPrivOperation2.setCode("1011");
        tawSystemPrivOperation2.setIsApp("0");
        tawSystemPrivOperation2.setName("工建子系统");
        tawSystemPrivOperation2.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation2);

        String testCodeValue = tawSystemPrivOperation.getCode();
        List list = null;
        list = dao.getModules(testCodeValue, "2");
        assertTrue(list.size() == 1);
    }

    /**
     * 得到某一模块下最大的CODE值
     *
     * @param fatherId
     * @return
     */
    public void testgetMaxCodeValue() throws Exception {
        TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
        tawSystemPrivOperation.setCode("10");
        tawSystemPrivOperation.setIsApp("1");
        tawSystemPrivOperation.setName("工建系统");
        tawSystemPrivOperation.setParentcode("0");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);

        TawSystemPrivOperation tawSystemPrivOperation1 = new TawSystemPrivOperation();
        tawSystemPrivOperation1.setCode("1010");
        tawSystemPrivOperation1.setIsApp("1");
        tawSystemPrivOperation1.setName("工建子系统");
        tawSystemPrivOperation1.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation1);

        TawSystemPrivOperation tawSystemPrivOperation11 = new TawSystemPrivOperation();
        tawSystemPrivOperation11.setCode("101001");
        tawSystemPrivOperation11.setIsApp("1");
        tawSystemPrivOperation11.setName("子系统");
        tawSystemPrivOperation11.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation11);

        TawSystemPrivOperation tawSystemPrivOperation12 = new TawSystemPrivOperation();
        tawSystemPrivOperation12.setCode("101002");
        tawSystemPrivOperation12.setIsApp("0");
        tawSystemPrivOperation12.setName("离开地方军统");
        tawSystemPrivOperation12.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation12);

        TawSystemPrivOperation tawSystemPrivOperation2 = new TawSystemPrivOperation();
        tawSystemPrivOperation2.setCode("1011");
        tawSystemPrivOperation2.setIsApp("0");
        tawSystemPrivOperation2.setName("工建子系统");
        tawSystemPrivOperation2.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation2);

        String testCodeValue = tawSystemPrivOperation.getCode();
        String max = null;
        max = dao.getMaxCodeValue(testCodeValue);
        assertTrue(max.equals("1011"));
    }

    /**
     * 得到某一模块的直接父模块
     *
     * @param childObjectCode
     * @return
     */
    public void testGetFatherModue() {
        TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
        tawSystemPrivOperation.setCode("10");
        tawSystemPrivOperation.setIsApp("1");
        tawSystemPrivOperation.setName("工建系统");
        tawSystemPrivOperation.setParentcode("-1");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);

        TawSystemPrivOperation tawSystemPrivOperation1 = new TawSystemPrivOperation();
        tawSystemPrivOperation1.setCode("1010");
        tawSystemPrivOperation1.setIsApp("1");
        tawSystemPrivOperation1.setName("工建子系统");
        tawSystemPrivOperation1.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation1);

        String testCodeValue = tawSystemPrivOperation1.getCode();
        TawSystemPrivOperation tawSystemPrivOperation99 = null;
        tawSystemPrivOperation99 = dao.getFatherModule(testCodeValue);
        assertNotNull(tawSystemPrivOperation99);
    }

    /**
     * 得到某一模块的所有父模块集合 LINK查询
     */

    public void testGetAllFatherModules() {
        TawSystemPrivOperation tawSystemPrivOperation = new TawSystemPrivOperation();
        tawSystemPrivOperation.setCode("10");
        tawSystemPrivOperation.setIsApp("1");
        tawSystemPrivOperation.setName("工建系统");
        tawSystemPrivOperation.setParentcode("0");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation);

        TawSystemPrivOperation tawSystemPrivOperation1 = new TawSystemPrivOperation();
        tawSystemPrivOperation1.setCode("1010");
        tawSystemPrivOperation1.setIsApp("1");
        tawSystemPrivOperation1.setName("工建子系统");
        tawSystemPrivOperation1.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation1);

        TawSystemPrivOperation tawSystemPrivOperation11 = new TawSystemPrivOperation();
        tawSystemPrivOperation11.setCode("101001");
        tawSystemPrivOperation11.setIsApp("1");
        tawSystemPrivOperation11.setName("子系统");
        tawSystemPrivOperation11.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation11);

        TawSystemPrivOperation tawSystemPrivOperation12 = new TawSystemPrivOperation();
        tawSystemPrivOperation12.setCode("101002");
        tawSystemPrivOperation12.setIsApp("0");
        tawSystemPrivOperation12.setName("离开地方军统");
        tawSystemPrivOperation12.setParentcode("1010");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation12);

        TawSystemPrivOperation tawSystemPrivOperation2 = new TawSystemPrivOperation();
        tawSystemPrivOperation2.setCode("1011");
        tawSystemPrivOperation2.setIsApp("0");
        tawSystemPrivOperation2.setName("工建子系统");
        tawSystemPrivOperation2.setParentcode("10");
        dao.saveTawSystemPrivOperation(tawSystemPrivOperation2);

        String testCodeValue = "101001";
        List list = null;
        list = dao.getAllFatherModules(testCodeValue);
        assertTrue(list.size() == 2);
    }
}
