package com.boco.eoms.km.ask.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.ask.model.KmAskComment;
import com.boco.eoms.km.ask.mgr.KmAskCommentMgr;
import com.boco.eoms.km.ask.dao.KmAskCommentDao;

/**
 * <p>
 * Title:问答评论
 * </p>
 * <p>
 * Description:问答评论
 * </p>
 * <p>
 * Fri Aug 14 15:49:40 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class KmAskCommentMgrImpl implements KmAskCommentMgr {
 
	private KmAskCommentDao  kmAskCommentDao;
 	
	public KmAskCommentDao getKmAskCommentDao() {
		return this.kmAskCommentDao;
	}
 	
	public void setKmAskCommentDao(KmAskCommentDao kmAskCommentDao) {
		this.kmAskCommentDao = kmAskCommentDao;
	}
 	
    public List getKmAskComments() {
    	return kmAskCommentDao.getKmAskComments();
    }
    
    /**
	 * 查询某问题下的所有评论信息
	 * @param questionId 问答id
	 * @return 所有评论集合
	 */
	public List getKmAskComments(final String questionId){
		return kmAskCommentDao.getKmAskComments(questionId);
	}
    
    public KmAskComment getKmAskComment(final String id) {
    	return kmAskCommentDao.getKmAskComment(id);
    }
    
    public void saveKmAskComment(KmAskComment kmAskComment) {
    	kmAskCommentDao.saveKmAskComment(kmAskComment);
    }
    
    public void removeKmAskComment(final String id) {
    	kmAskCommentDao.removeKmAskComment(id);
    }
    
    public Map getKmAskComments(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return kmAskCommentDao.getKmAskComments(curPage, pageSize, whereStr);
	}
	
}