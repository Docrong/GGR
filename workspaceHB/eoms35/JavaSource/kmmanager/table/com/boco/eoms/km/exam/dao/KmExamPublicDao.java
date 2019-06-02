package com.boco.eoms.km.exam.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.km.exam.model.KmExamPublic;

/**
 * <p>
 * Title:考试发布
 * </p>
 * <p>
 * Description:考试发布
 * </p>
 * <p>
 * Mon May 11 10:55:38 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public interface KmExamPublicDao extends Dao {

    /**
    *
    *取考试发布列表
    * @return 返回考试发布列表
    */
    public List getKmExamPublics();
    
    /**
    * 根据主键查询考试发布
    * @param id 主键
    * @return 返回某id的考试发布
    */
    public KmExamPublic getKmExamPublic(final String id);
    
    /**
    *
    * 保存考试发布    
    * @param kmExamPublic 考试发布
    * 
    */
    public void saveKmExamPublic(KmExamPublic kmExamPublic);
    
    /**
    * 根据id删除考试发布
    * @param id 主键
    * 
    */
    public void removeKmExamPublic(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getKmExamPublics(final Integer curPage, final Integer pageSize,
			final String whereStr);
	
}