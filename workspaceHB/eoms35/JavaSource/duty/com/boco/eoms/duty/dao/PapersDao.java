package com.boco.eoms.duty.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.duty.model.Papers;
import com.boco.eoms.duty.model.PapersPart;

/**
 * <p>
 * Title:资料
 * </p>
 * <p>
 * Description:资料
 * </p>
 * <p>
 * Tue Apr 07 10:05:44 CST 2009
 * </p>
 * 
 * @author lixiaoming
 * @version 3.5
 * 
 */
public interface PapersDao extends Dao {

    /**
    *
    *取资料列表
    * @return 返回资料列表
    */
    public List getPaperss();
    
    /**
    * 根据主键查询资料
    * @param id 主键
    * @return 返回某id的资料
    */
    public Papers getPapers(final String id);
    
    /**
    *
    * 保存资料    
    * @param papers 资料
    * 
    */
    public void savePapers(Papers papers);
    /**
    *
    * 保存查看过得资料    
    * @param papers 资料
    * 
    */
    public void savePapersPart(PapersPart paperspart);
    
    /**
    * 根据id删除资料
    * @param id 主键
    * 
    */
    public void removePapers(final String id);
    /**
     * 根据id删除papers_part表相应记录
     * @param id 主键
     * 
     */
     public void removePapersPart(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getPaperss(final Integer curPage, final Integer pageSize,
			final String whereStr,String userId);
    public Map getsearchTixing(final Integer curPage, final Integer pageSize,
			final String whereStr,String userId);
    public Map getPerson();
    public Map getPapersId();
	
}