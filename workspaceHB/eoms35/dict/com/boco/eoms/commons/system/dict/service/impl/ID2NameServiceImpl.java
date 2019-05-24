package com.boco.eoms.commons.system.dict.service.impl;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.util.Util;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-10-19 10:41:50
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class ID2NameServiceImpl extends BaseManager implements ID2NameService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.service.ID2NameManager#id2Name(java.lang.String,
	 *      java.lang.String)
	 */
	public String id2Name(String id, String beanId) {
		String name = null;
		try {
			// 通过beanid取bean
			ID2NameDAO dao = (ID2NameDAO) ApplicationContextHolder
					.getInstance().getBean(beanId);
			// 转换后的name
			name = dao.id2Name(id);
		} catch (Exception e) {
			// 取id2name失败后的name默认值
			name = Util.idNoName();
		}
		if (name == null || "".equals(name)) {
			name = Util.idNoName();
		}
		return name;
	}

}
