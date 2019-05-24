/**
 * 
 */
package com.boco.eoms.commons.system.priv.test.dao;

import java.util.Iterator;
import java.util.List;

import com.boco.eoms.base.test.dao.BaseDaoTestCase;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuCommonDao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class TawSystemPrivMenuCommonDaoTest extends BaseDaoTestCase {

	private TawSystemPrivMenuCommonDao dao = null;

	public void setTawSystemPrivMenuCommonDao(TawSystemPrivMenuCommonDao dao) {
		this.dao = dao;
	}

	/**
	 * 测试DAO层getSpecMenuItems函数实现
	 */
	public void testGetSpecMenuItems() throws Exception {
		String _strMenuCode = "1001";
		TawSystemPrivUserAssign _objTmp = new TawSystemPrivUserAssign();

		List _objReturn = dao.getSpecMenuItems(_strMenuCode);
		Iterator _objIterator = _objReturn.iterator();
		while (_objIterator.hasNext()) {
			_objTmp = (TawSystemPrivUserAssign) _objIterator.next();
			System.out.println("itemId: " + _objTmp.getCurrentprivid());
			System.out.println("ItemName: " + _objTmp.getCurrentprivname());
			System.out.println("parentItemId: " + _objTmp.getParentprivid());
			System.out
					.println("parentItemName: " + _objTmp.getParentprivname());
			System.out.println("isLeaf: " + _objTmp.getLeaf());
			System.out.println("isHide: " + _objTmp.getHide());
			System.out.println("url: " + _objTmp.getUrl());
			System.out.println("isOnePriv: " + _objTmp.getIsonepriv());
		}
	}
}
