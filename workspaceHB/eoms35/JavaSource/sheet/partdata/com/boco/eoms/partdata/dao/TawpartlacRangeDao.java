package com.boco.eoms.partdata.dao;

import com.boco.eoms.base.dao.Dao;
import java.util.List;
import java.util.Map;
import com.boco.eoms.partdata.model.TawpartlacRange;

/**
 * <p>
 * Title:LAC码号地市分配
 * </p>
 * <p>
 * Description:LAC码号地市分配
 * </p>
 * <p>
 * Mon Jul 12 09:21:06 CST 2010
 * </p>
 * 
 * @author fengshaohong
 * @version 3.6
 * 
 */
public interface TawpartlacRangeDao extends Dao {

    /**
    *
    *取LAC码号地市分配列表
    * @return 返回LAC码号地市分配列表
    */
    public List getTawpartlacRanges();
    
    /**
    * 根据主键查询LAC码号地市分配
    * @param id 主键
    * @return 返回某id的LAC码号地市分配
    */
    public TawpartlacRange getTawpartlacRange(final String id);
    
    /**
    *
    * 保存LAC码号地市分配    
    * @param tawpartlacRange LAC码号地市分配
    * 
    */
    public void saveTawpartlacRange(TawpartlacRange tawpartlacRange);
    
    /**
    * 根据id删除LAC码号地市分配
    * @param id 主键
    * 
    */
    public void removeTawpartlacRange(final String id);
    
    /**
    * 分页取列表
    * @param curPage 当前页
    * @param pageSize 每页显示条数
    * @param whereStr where条件
    * @return map中total为条数,result(list) curPage页的记录
    */
    public Map getTawpartlacRanges(final Integer curPage, final Integer pageSize,
			final String whereStr);
    public boolean isavailable(final int start,final int end);
    public boolean isavailablenotself(final int start,final int end,final String id); 
    public List getTawpartlacRangebyL1L2(final String l1l2) ;
}