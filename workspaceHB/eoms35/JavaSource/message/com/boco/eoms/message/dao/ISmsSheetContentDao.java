package com.boco.eoms.message.dao;

import java.util.List;

import com.boco.eoms.message.model.SmsSheetContent;

public interface ISmsSheetContentDao {

    public void save(Object o);

    public List getRecordByFlowNameAndType(String workFlowName, String type);

    public void removeRecordById(SmsSheetContent ssc);

    public List getRecordByCondition(String sql);

    public SmsSheetContent getSSCById(String id);

    public int modifySheetContent(String[] id, String content);
}
