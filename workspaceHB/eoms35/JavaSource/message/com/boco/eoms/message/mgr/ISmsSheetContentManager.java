package com.boco.eoms.message.mgr;

import java.util.List;

import com.boco.eoms.message.model.SmsSheetContent;

public interface ISmsSheetContentManager {
	
	public void save(Object obj);
	
	public List getRecordByFlowNameAndType(String workFlowName,String type);
	
	public void removeRecordById(SmsSheetContent ssc);
	
	public List getRecordByCondition(String sql);
	
	public SmsSheetContent getSSCById(String id);
	
	public int modifySheetContent(String[] id,String content);
	
}
