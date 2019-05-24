package com.boco.eoms.commons.failureRecord.dao;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.commons.failureRecord.model.FailureRecord;
import com.boco.eoms.commons.failureRecord.webapp.form.FailureRecordForm;


public interface FailureRecordDao {
	public Map getMonitoringLog(final Integer curPage, final Integer pageSize,FailureRecordForm failureRecordForm);
		
	public FailureRecord getFailureRecord(final String id); 
	public void save(FailureRecord failureRecord);
	public void removeFailureRecord(
			FailureRecord failureRecord);
	public FailureRecord findFailureRecord(String id);


}
