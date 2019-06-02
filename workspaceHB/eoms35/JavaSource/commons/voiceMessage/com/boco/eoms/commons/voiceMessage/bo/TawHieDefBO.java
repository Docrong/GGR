package com.boco.eoms.commons.voiceMessage.bo;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.hongxun.dao.TawCommonHongXunDao;
import com.boco.eoms.commons.voiceMessage.dao.TawHieDefDAO;
import com.boco.eoms.commons.voiceMessage.webapp.form.TawHieApplyForm;

public class TawHieDefBO {
	public TawHieDefBO() {
		super();
	}


	
	public int getUserId() {
		int userId = 0;
		TawHieDefDAO dao = (TawHieDefDAO) ApplicationContextHolder
		.getInstance().getBean("tawHieDefDAO");
		
		userId = dao.getUserId();
		
		return userId;
	}
	public List getAllUser(TawHieApplyForm form) {
		List list=new ArrayList();
		TawHieDefDAO dao = (TawHieDefDAO) ApplicationContextHolder
		.getInstance().getBean("tawHieDefDAO");
		
		list = dao.getAllUser(form);
		
		return list;
	}
	public List getOneUser(String id) {
		List list=new ArrayList();
		TawHieDefDAO dao = (TawHieDefDAO) ApplicationContextHolder
		.getInstance().getBean("tawHieDefDAO");
		
		list = dao.getOneRedUser(id);
		
		return list;
	}
	
	public void redDoUpdate(String id,String username, String usertel, String userType, String userCode)
	{
		TawHieDefDAO dao = (TawHieDefDAO) ApplicationContextHolder
		.getInstance().getBean("tawHieDefDAO");
		
		 dao.redDoUpdate(id, username, usertel, userType, userCode);
		
	}
	public void redDel(String id)
	{
		TawHieDefDAO dao = (TawHieDefDAO) ApplicationContextHolder
		.getInstance().getBean("tawHieDefDAO");
		dao.redDel(id);
	}
	
	public List getOrgDefSel() {
		List list = new ArrayList();
		ArrayList re = new ArrayList();
		TawHieDefDAO dao = (TawHieDefDAO) ApplicationContextHolder
		.getInstance().getBean("tawHieDefDAO");
		list = dao.getOrgName();
		for (int i = 0; i < list.size(); i++) {
			re.add(new org.apache.struts.util.LabelValueBean(String.valueOf(list.get(i)), String
					.valueOf(list.get(i))));
		}
		return re;
	}
	
	public List getConfDefSel() {
		List list = new ArrayList();
		ArrayList re = new ArrayList();
		re.add(new org.apache.struts.util.LabelValueBean("", String
						.valueOf(0)));
		TawHieDefDAO dao = (TawHieDefDAO) ApplicationContextHolder
		.getInstance().getBean("tawHieDefDAO");
		list = dao.getConfName();
		for (int i = 0; i < list.size(); i++) {
			re.add(new org.apache.struts.util.LabelValueBean(String.valueOf(list.get(i)), String
					.valueOf(list.get(i))));
		}
		return re;
	}
}
