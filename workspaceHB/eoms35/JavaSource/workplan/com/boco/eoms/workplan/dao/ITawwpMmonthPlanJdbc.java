package com.boco.eoms.workplan.dao;

import com.boco.eoms.base.dao.Dao;

public interface ITawwpMmonthPlanJdbc extends Dao{
	public String getNewMonthplanId(String _yearplanid,String _netId,String _monthFlag);
}
