package com.boco.eoms.workbench.infopub.displaytag.support;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.workbench.infopub.model.ThreadCountHistory;

public class ThreadCountHistoryListDisplaytagDecorator extends TableDecorator {
	/**
	 * 浏览历史
	 * 
	 * @return
	 */
	public String getUserId() {
		ThreadCountHistory threadHistory = (ThreadCountHistory) getCurrentRowObject();
		String name = "";
		try {
			ID2NameDAO forumsDao = (ID2NameDAO) ApplicationContextHolder
					.getInstance().getBean("tawSystemUserDao");
			name = forumsDao.id2Name(threadHistory.getUserId());
		} catch (Exception e) {
			name = Util.idNoName();
		}
		return name;
	}
}
