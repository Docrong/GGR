package com.boco.eoms.km.exam.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.exam.model.KmExamQuestions;

/**
 * <p>
 * Title:题库管理
 * </p>
 * <p>
 * Description:题库管理
 * </p>
 * <p>
 * Fri May 08 16:40:11 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
public interface KmExamQuestionsDao extends Dao {

    /**
    *
    *取题库管理列表
    * @return 返回题库管理列表
    */
    public List getKmExamQuestionss();
    
    
    /**
    * 根据主键查询题库管理
    * @param id 主键
    * @return 返回某id的题库管理
    */
    public KmExamQuestions getKmExamQuestions(final String id);
    
    /**
    *
    * 保存题库管理    
    * @param kmExamQuestions 题库管理
    * 
    */
    public void saveKmExamQuestions(KmExamQuestions kmExamQuestions);
    
    /**
    * 根据id删除题库管理
    * @param id 主键
    * 
    */
    public void removeKmExamQuestions(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmExamQuestionss(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}