package com.boco.eoms.commons.voiceMessage.bo;

import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.voiceMessage.dao.TawHieApplyDAO;
import com.boco.eoms.commons.voiceMessage.webapp.form.TawHieApplyForm;

public class TawHieApplyBO {
	

	public TawHieApplyBO() {
		super();
	}

	public void addUser(TawHieApplyForm form) {
		try {
			TawHieApplyDAO dao = (TawHieApplyDAO) ApplicationContextHolder
					.getInstance().getBean("tawHieApplyDAO");
			dao.addUser(form);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int addConference(TawHieApplyForm form) {
		// HashMap value = getConfCon(form);
		int confNo = 0;
		try {
			TawHieApplyDAO dao = (TawHieApplyDAO) ApplicationContextHolder
					.getInstance().getBean("tawHieApplyDAO");
			confNo = dao.addConference(form);
			// System.out.println("confNo = " + confNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return confNo;
	}

	public List getAllMems() {
		List list = new ArrayList();

		try {
			TawHieApplyDAO dao = (TawHieApplyDAO) ApplicationContextHolder
					.getInstance().getBean("tawHieApplyDAO");
			list = dao.getAllMems();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addConfMems(int confNo, int[] userIds, String[] userNames,
			String[] userPhones, int[] userTypes, int[] joinModes) {
		try {
			TawHieApplyDAO dao = (TawHieApplyDAO) ApplicationContextHolder
			.getInstance().getBean("tawHieApplyDAO");
			dao.addConfMems(confNo, userIds, userNames, userPhones,
					userTypes, joinModes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getConfInfoSize(TawHieApplyForm form) {
		int ret = 0;

		int confNo = form.getConfNo();


		String con = "where conf_No = " + confNo;
		try {

			TawHieApplyDAO dao = (TawHieApplyDAO) ApplicationContextHolder
			.getInstance().getBean("tawHieApplyDAO");
			ret = dao.getConfInfoCount(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public List getConfInfo(int offset, int length, TawHieApplyForm form)
    {
		List list = new ArrayList();

		int confNo = form.getConfNo();
		//System.out.println("confNo = " + confNo);
		String confName = form.getConfName();
		String confBeginTime = form.getConfBeginTime();
		System.out.println("confBeginTime = " + confBeginTime);

		String tCon = "";
		if(!confBeginTime.equals("") && confBeginTime != null){
                  confBeginTime = confBeginTime.substring(0, 11);
                  tCon = "and conf_begintime between '" + confBeginTime + "00:00:00.00000' " +
			       " and '" + confBeginTime + "23:59:59.99999'";
                }
                if(confNo != 0){

                  tCon =tCon+ " and conf_no =" + confNo ;

                }
                if(!confName.equals("") && confName != null){
                          tCon =tCon+" and conf_Name like '"+confName  +"%'";
                                }

			tCon = "where 1=1 " + tCon;

		System.out.println(tCon);

		TawHieApplyDAO dao = (TawHieApplyDAO) ApplicationContextHolder
		.getInstance().getBean("tawHieApplyDAO");
		list = dao.listConfInfo(offset, length, tCon);

		return list;
	}
	
	public List getConfInfo(TawHieApplyForm form) {
		List list = new ArrayList();

		
		try {
			TawHieApplyDAO dao = (TawHieApplyDAO) ApplicationContextHolder
			.getInstance().getBean("tawHieApplyDAO");
			list = dao.listConfInfo(form);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List getConfInfoAll(TawHieApplyForm form) {
		List list = new ArrayList();

		
		try {
			TawHieApplyDAO dao = (TawHieApplyDAO) ApplicationContextHolder
			.getInstance().getBean("tawHieApplyDAO");
			list = dao.getConfInfo(form);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List getConfInfo(int confNo) {
		List list = new ArrayList();

		
		try {
			TawHieApplyDAO dao = (TawHieApplyDAO) ApplicationContextHolder
			.getInstance().getBean("tawHieApplyDAO");
			list = dao.listConfInfo(confNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List getConfMem(int confNo) {
		List list = new ArrayList();

		
		try {
			TawHieApplyDAO dao = (TawHieApplyDAO) ApplicationContextHolder
			.getInstance().getBean("tawHieApplyDAO");
			list = dao.listMemInfo(confNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
}
