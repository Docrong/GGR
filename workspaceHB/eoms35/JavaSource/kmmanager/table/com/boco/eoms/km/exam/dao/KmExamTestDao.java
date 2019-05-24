package com.boco.eoms.km.exam.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.km.exam.model.KmExamTest;

/**
 * <p>
 * Title:试卷
 * </p>
 * <p>
 * Description:试卷
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
public interface KmExamTestDao extends Dao {

    /**
    *
    *取试卷列表
    * @return 返回试卷列表
    */
    public List getKmExamTests();
    
    /**
	 * 得到发布的考试试题
	 * @return
	 */
	public List getKmExamPublicTests();
    
	/**
	 * 得到发布的随机考试试卷
	 * @return
	 */
	public List getKmExamRandomTests();
	
    /**
    * 根据主键查询试卷
    * @param id 主键
    * @return 返回某id的试卷
    */
    public KmExamTest getKmExamTest(final String id);
    
    /**
    *
    * 保存试卷    
    * @param kmExamTest 试卷
    * 
    */
    public void saveKmExamTest(KmExamTest kmExamTest);
    
    /**
    * 根据id删除试卷
    * @param id 主键
    * 
    */
    public void removeKmExamTest(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmExamTests(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}