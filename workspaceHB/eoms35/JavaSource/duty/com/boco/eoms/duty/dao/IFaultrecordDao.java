package com.boco.eoms.duty.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.duty.faultrecord.model.Faultrecord;

public interface IFaultrecordDao extends Dao {

    public List getFaultrecords(Faultrecord faultrecord);

	public void saveFaultrecord(final Faultrecord faultrecord);
	
	public Faultrecord getFaultrecord(final String id);
	
	public Map getFaultrecords(final Integer curPage, final Integer pageSize,final String whereStr);

	public void removeFaultrecord(final String id);
}
