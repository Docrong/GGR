package com.boco.eoms.km.ask.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.ask.model.KmAskReply;
import com.boco.eoms.km.ask.mgr.KmAskReplyMgr;
import com.boco.eoms.km.ask.dao.KmAskReplyDao;

/**
 * <p>
 * Title:答题
 * </p>
 * <p>
 * Description:回答答案
 * </p>
 * <p>
 * Tue Aug 04 15:52:08 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class KmAskReplyMgrImpl implements KmAskReplyMgr {
 
	private KmAskReplyDao  kmAskReplyDao;
 	
	public KmAskReplyDao getKmAskReplyDao() {
		return this.kmAskReplyDao;
	}
 	
	public void setKmAskReplyDao(KmAskReplyDao kmAskReplyDao) {
		this.kmAskReplyDao = kmAskReplyDao;
	}
 	
    public List getKmAskReplys() {
    	return kmAskReplyDao.getKmAskReplys();
    }
    
    /**
	 * 根据问题查询所有回答
	 * @param questionId
	 * @return
	 */
	public List getKmAskReplysByQuestionId(final String questionId){
		return kmAskReplyDao.getKmAskReplysByQuestionId(questionId);
	}
    
    public KmAskReply getKmAskReply(final String id) {
    	return kmAskReplyDao.getKmAskReply(id);
    }
    
    public void saveKmAskReply(KmAskReply kmAskReply) {
    	kmAskReplyDao.saveKmAskReply(kmAskReply);
    }
    
    public void removeKmAskReply(final String id) {
    	kmAskReplyDao.removeKmAskReply(id);
    }
    
    public Map getKmAskReplys(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return kmAskReplyDao.getKmAskReplys(curPage, pageSize, whereStr);
	}
	
}