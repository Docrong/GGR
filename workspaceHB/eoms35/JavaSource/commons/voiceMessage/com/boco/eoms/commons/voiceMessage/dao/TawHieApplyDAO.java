package com.boco.eoms.commons.voiceMessage.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.voiceMessage.webapp.form.TawHieApplyForm;

public interface TawHieApplyDAO extends Dao{
	public void addUser(TawHieApplyForm form);
	public int addConference(TawHieApplyForm form);
	public List getAllMems();
	public void addConfMems(int confNo, int[] userIds, String[] userNames, String[] userPhones,
            int[] userTypes, int[] joinModes);
	
	public int getConfInfoCount(String con);
	public List listConfInfo(int offset, int limit, String con);
	public List listConfInfo(TawHieApplyForm form);
	public List getConfInfo(TawHieApplyForm form);
	public List listConfInfo(int confNo);
	public List listMemInfo( int confNo);
}
