package com.boco.eoms.commons.system.dict.test.dao.xml;

import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.system.dict.dao.IDictDao;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.model.IDict;
import com.boco.eoms.commons.system.dict.model.IDictItem;
import com.boco.eoms.commons.system.dict.util.Constants;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-24 14:30:14
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DictDaoXMLTest extends ConsoleTestCase {
    private IDictDao dictDaoXML;

    /*
     * @see TestCase#setUp()
     */
    protected void onSetUpInTransaction() throws Exception {
        super.setUp();
        dictDaoXML = (IDictDao) this.getBean("DictDaoXML");
    }



    public void testFindItem() {
        try {
            IDictItem item = dictDaoXML.findItem("sample"
                    + Constants.DICT_ID_SPLIT_CHAR + "major", "1");
            assertNotNull(item);
            assertEquals(item.getItemDescription(), "开发工程师");
            assertEquals(item.getItemId(), "1");
            assertEquals(item.getItemName(), "开发工程师");
        } catch (DictDAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    public void testFindItemList() {
        try {
            List list = dictDaoXML.findItemList("sample"
                    + Constants.DICT_ID_SPLIT_CHAR + "major");

            assertNotNull(list);
            assertFalse(list.isEmpty());
            for (Iterator it = list.iterator(); it.hasNext();) {
                IDictItem item = (IDictItem) it.next();
                //若为1则断方开发工程师
                if ("1".equals(item.getItemId())) {
                    assertEquals(item.getItemDescription(), "开发工程师");
                    assertEquals(item.getItemId(), "1");
                    assertEquals(item.getItemName(), "开发工程师");
                }
                //若为2则测试工程师
                else if ("2".equals(item.getItemId())) {
                    assertEquals(item.getItemDescription(), "测试工程师");
                    assertEquals(item.getItemId(), "2");
                    assertEquals(item.getItemName(), "测试工程师");
                }
                //测试xml文件中仅1,2；即出现其他id测试失败
                else {
                    fail();
                }
            }

        } catch (DictDAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    public void testFindDict() {
        try {
            IDict dict = dictDaoXML.findDict("sample"
                    + Constants.DICT_ID_SPLIT_CHAR + "major");
            assertNotNull(dict);
            assertEquals(dict.getDictDescription(), "专业");
            assertEquals(dict.getDictId(), "major");
        } catch (DictDAOException e) {
            e.printStackTrace();
            fail();
        }
    }

}
