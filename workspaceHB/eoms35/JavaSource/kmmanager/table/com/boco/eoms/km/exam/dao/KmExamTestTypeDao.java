package com.boco.eoms.km.exam.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.exam.model.KmExamTestType;

/**
 * <p>
 * Title:题型信息表
 * </p>
 * <p>
 * Description:题型信息表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
public interface KmExamTestTypeDao extends Dao {

    /**
    *
    *取题型信息表列表
    * @return 返回题型信息表列表
    */
    public List getKmExamTestTypes();
    
    /**
	 * 查询某试卷下的所有类型
	 * @param testID
	 * @return
	 */
	public List getKmExamTestTypesByTestID(final String testID);
    
    /**
    * 根据主键查询题型信息表
    * @param id 主键
    * @return 返回某id的题型信息表
    */
    public KmExamTestType getKmExamTestType(final String id);
    
    /**
    *
    * 保存题型信息表    
    * @param kmExamTestType 题型信息表
    * 
    */
    public void saveKmExamTestType(KmExamTestType kmExamTestType);
    
    /**
    * 根据id删除题型信息表
    * @param id 主键
    * 
    */
    public void removeKmExamTestType(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmExamTestTypes(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}