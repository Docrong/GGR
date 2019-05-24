package com.boco.eoms.workplan.dao.Jdbc;


import java.util.List;
import java.util.HashMap;


import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.common.dao.DAO;

import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;


public class TawWorkplanUserCacheDaoJDBC extends DAO {

	public TawWorkplanUserCacheDaoJDBC(com.boco.eoms.db.util.ConnectionPool ds) {
		super(ds);
	}

	public TawWorkplanUserCacheDaoJDBC() {
		super();
	}

	public HashMap getWorkplanData() {
		ITawSystemUserManager tawRmUserBO = (ITawSystemUserManager) ApplicationContextHolder
				.getInstance().getBean("itawSystemUserManager");

		List list = null;
		HashMap hashmap = new HashMap();
		TawSystemUser labelValueBean = null;
		try {
			list = tawRmUserBO.getUsersByName("");
			 

			// 循环处理人员信息
			for (int i = 0; i < list.size(); i++) {
				labelValueBean = (TawSystemUser) list.get(i);
				hashmap.put(labelValueBean.getUserid(),labelValueBean.getUsername());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			list = null;
		}
		return hashmap;
	}
}
