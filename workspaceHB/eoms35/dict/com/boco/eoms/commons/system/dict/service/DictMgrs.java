package com.boco.eoms.commons.system.dict.service;

import com.boco.eoms.commons.system.dict.util.DictMgrLocator;

/**
 * <p>
 * Title:字典类集合
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Nov 18, 2008 2:58:12 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */

public class DictMgrs {
	/**
	 * xml字典mgr
	 * 
	 * @return xml字典mgr
	 */
	public IDictService getXMLDictMgr() {
		return DictMgrLocator.getDictService();
	}

	/**
	 * id2name的mgr
	 * 
	 * @return id2name的mgr
	 */
	public ID2NameService getID2NameMgr() {
		return DictMgrLocator.getId2NameService();
	}
	
	
}
