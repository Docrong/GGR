package com.boco.eoms.commons.voiceMessage.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.voiceMessage.webapp.form.TawHieApplyForm;

public interface TawHieDefDAO extends Dao{
	public int getUserId();
	public List getAllUser(TawHieApplyForm form);
	public List getOneRedUser(String id);
	public void redDel(String id);
	public void redDoUpdate(String id, String username, String usertel,
			String userType, String userCode);
	
	public List getOrgName();
	public List getConfName();
}
