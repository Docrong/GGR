package com.boco.eoms.km.ask.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.ask.model.KmAskVote;
import com.boco.eoms.km.ask.mgr.KmAskVoteMgr;
import com.boco.eoms.km.ask.dao.KmAskVoteDao;

/**
 * <p>
 * Title:投票
 * </p>
 * <p>
 * Description:投票
 * </p>
 * <p>
 * Fri Aug 14 15:39:20 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class KmAskVoteMgrImpl implements KmAskVoteMgr {
 
	private KmAskVoteDao  kmAskVoteDao;
 	
	public KmAskVoteDao getKmAskVoteDao() {
		return this.kmAskVoteDao;
	}
 	
	public void setKmAskVoteDao(KmAskVoteDao kmAskVoteDao) {
		this.kmAskVoteDao = kmAskVoteDao;
	}
 	
    public List getKmAskVotes() {
    	return kmAskVoteDao.getKmAskVotes();
    }
    
    /**
	 * 根据questionId查询所有投票信息
	 * @param questionId
	 * @return
	 */
	public List getKmAskVoteByQuestionId(final String questionId){
		return kmAskVoteDao.getKmAskVoteByQuestionId(questionId);
	}
    
	/**
	 * 根据问题id和投票人id查询投票信息
	 * @param questionId
	 * @param userId
	 * @return
	 */
	public KmAskVote getKmAskVote(final String questionId,final String userId){
		return kmAskVoteDao.getKmAskVote(questionId, userId);
	}
	
    public KmAskVote getKmAskVote(final String id) {
    	return kmAskVoteDao.getKmAskVote(id);
    }
    
    public void saveKmAskVote(KmAskVote kmAskVote) {
    	kmAskVoteDao.saveKmAskVote(kmAskVote);
    }
    
    public void removeKmAskVote(final String id) {
    	kmAskVoteDao.removeKmAskVote(id);
    }
    
    public Map getKmAskVotes(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return kmAskVoteDao.getKmAskVotes(curPage, pageSize, whereStr);
	}
	
}