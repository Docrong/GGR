package com.boco.eoms.workbench.memo.bo;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContact;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager;
import java.util.*;

public class TawWorkbenchMemoBO {
	private TawWorkbenchMemoBO() {

	}

	private static TawWorkbenchMemoBO instance = null;

	public static TawWorkbenchMemoBO getInstance() {
		if (instance == null) {
			instance = init();
		}
		return instance;
	}

	private static TawWorkbenchMemoBO init() {
		instance = new TawWorkbenchMemoBO();
		return instance;
	}

	// 根据页面选择的用户id 得到用户的集合
	public String getUserStrByIds(String id) {

		String userStr = "";
		TawWorkbenchContact tawWorkbenchContact = null;
		if (id != null || !id.equals("")) {
			String[] id_str = id.split(",");
			try {
				ITawWorkbenchContactManager mgr = (ITawWorkbenchContactManager) ApplicationContextHolder
						.getInstance().getBean("ItawWorkbenchContactManager");
				for (int i = 0; i < id_str.length; i++) {
					String StrId = id_str[i];
					if(!StrId.equals("")){
					tawWorkbenchContact = mgr.getTawWorkbenchContact(StrId);
				
					userStr += tawWorkbenchContact.getContactName()+",";
					}
				}
				userStr = userStr.substring(0, userStr.length() - 1);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userStr;
	}

	// 根据页面选择的用户id 得到用户的集合
	public String getUserStrByUserIds(String id) {

		String userStr = "";
		TawSystemUser tawSystemUser = null;
		if (id != null || !id.equals("")) {
			String[] id_str = id.split(",");
			try {
				ITawSystemUserManager mgr = (ITawSystemUserManager) ApplicationContextHolder
						.getInstance().getBean("itawSystemUserManager");
				for (int i = 0; i < id_str.length; i++) {
					String StrId = id_str[i];
					if(!StrId.equals("")){
						tawSystemUser = mgr.getUserByuserid(StrId);
				
					userStr += tawSystemUser.getUsername()+",";
					}
				}
				userStr = userStr.substring(0, userStr.length() - 1);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userStr;
	}
}
