package com.boco.eoms.km.ask.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.ask.model.KmAskReply;

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
public interface KmAskReplyDao extends Dao {

    /**
    *
    *取答题列表
    * @return 返回答题列表
    */
    public List getKmAskReplys();
    
    /**
	 * 根据问题查询所有回答
	 * @param questionId
	 * @return
	 */
	public List getKmAskReplysByQuestionId(final String questionId);
    
    /**
    * 根据主键查询答题
    * @param id 主键
    * @return 返回某id的答题
    */
    public KmAskReply getKmAskReply(final String id);
    
    /**
    *
    * 保存答题    
    * @param kmAskReply 答题
    * 
    */
    public void saveKmAskReply(KmAskReply kmAskReply);
    
    /**
    * 根据id删除答题
    * @param id 主键
    * 
    */
    public void removeKmAskReply(final String id);
    
    /**
	 *  查询某人回答的所有问题
	 * @param answerUser
	 * @return
	 */
	public List getKmAskReplysByAnswerUser(final String answerUser);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmAskReplys(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}