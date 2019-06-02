package com.boco.eoms.commons.system.dict.test.service;

import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.model.IDictItem;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.Constants;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-24 15:15:15
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class DictServiceImplTest extends ConsoleTestCase {
    private IDictService service;

    /*
     * @see TestCase#setUp()
     */
    protected void onSetUpInTransaction() throws Exception {
        super.setUp();
        service = (IDictService) this.getBean("DictService");
    }


    public void testGetDictItem() {
        try {
            IDictItem item = service.getDictItem("sample&major", "1");
            assertNotNull(item);
            assertEquals(item.getItemName(), "开发工程师");
            assertEquals(item.getItemDescription(), "开发工程师");
            assertEquals(item.getItemId(), "1");
        } catch (DictServiceException e) {
            e.printStackTrace();
            fail();
        }
    }

    public void testGetItemList() {
        try {
            List list = service.getDictItems("sample"
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

        } catch (DictServiceException e) {
            e.printStackTrace();
            fail();
        }
    }

    public void testItemId2description() {
        try {
            assertEquals(service.itemId2description("sample&major", "1"),
                    "开发工程师");
        } catch (DictServiceException e) {
            e.printStackTrace();
            fail();
        }
    }

    public void testItemId2name() {
        try {
            assertEquals(service.itemId2name("sample&major", "1"), "开发工程师");
        } catch (DictServiceException e) {
            e.printStackTrace();
            fail();
        }
    }

    public void testDictId2description() {
        try {
            assertEquals(service.dictId2description("sample&major"), "专业");
        } catch (DictServiceException e) {
            e.printStackTrace();
            fail();
        }
    }

}
