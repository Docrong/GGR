package com.boco.eoms.partdata.mgr;

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
 public interface TawpartlacRangeMgr {
 
	/**
	 *
	 * 取LAC码号地市分配 列表
	 * @return 返回LAC码号地市分配列表
	 */
	public List getTawpartlacRanges();
    
	/**
	 * 根据主键查询LAC码号地市分配
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public TawpartlacRange getTawpartlacRange(final String id);
    
	/**
	 * 保存LAC码号地市分配
	 * @param tawpartlacRange LAC码号地市分配
	 */
	public void saveTawpartlacRange(TawpartlacRange tawpartlacRange);
    
	/**
	 * 根据主键删除LAC码号地市分配
	 * @param id 主键
	 */
	public void removeTawpartlacRange(final String id);
    
	/**
	 * 根据条件分页查询LAC码号地市分配
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回LAC码号地市分配的分页列表
	 */
	public Map getTawpartlacRanges(final Integer curPage, final Integer pageSize,
			final String whereStr);
	public boolean isavailable( String start, String end);
	public boolean isavailablenotself(String start,String end, String id) ;
	public List getTawpartlacRangebyL1L2(String l1l2);
}