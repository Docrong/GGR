package com.boco.eoms.workplan.test.dao;

import com.boco.eoms.base.test.console.ConsoleTestCase;
import com.boco.eoms.workplan.dao.ITawwpExecuteContentDao;
import com.boco.eoms.workplan.model.TawwpExecuteContent;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 25, 2008 11:00:19 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class TawwpExecuteContentDaoHibernateTest extends ConsoleTestCase {

	public void testLoadExecuteContent() {
		ITawwpExecuteContentDao dao = (ITawwpExecuteContentDao) this
				.getBean("tawwpExecuteContentDao");
		TawwpExecuteContent content = dao
				.loadExecuteContent("8a1597e419a8eb970119a90b24b00003");
		assertEquals("33333", content.getContent());
	}

}
