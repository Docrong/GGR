package com.boco.eoms.partdata.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partdata.model.TawpartLacBsc;

/**
 * <p>
 * Title:LAC的BSC分配
 * </p>
 * <p>
 * Description:LAC的BSC分配
 * </p>
 * <p>
 * Mon Jul 12 17:27:34 CST 2010
 * </p>
 * 
 * @author fengshaohong
 * @version 3.6
 * 
 */
 public interface TawpartLacBscMgr {
 
	/**
	 *
	 * 取LAC的BSC分配 列表
	 * @return 返回LAC的BSC分配列表
	 */
	public List getTawpartLacBscs();
    
	/**
	 * 根据主键查询LAC的BSC分配
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public TawpartLacBsc getTawpartLacBsc(final String id);
    
	/**
	 * 保存LAC的BSC分配
	 * @param tawpartLacBsc LAC的BSC分配
	 */
	public void saveTawpartLacBsc(TawpartLacBsc tawpartLacBsc);
    
	/**
	 * 根据主键删除LAC的BSC分配
	 * @param id 主键
	 */
	public void removeTawpartLacBsc(final String id);
    
	/**
	 * 根据条件分页查询LAC的BSC分配
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回LAC的BSC分配的分页列表
	 */
	public Map getTawpartLacBscs(final Integer curPage, final Integer pageSize,
			final String whereStr);
	 public Map getTawpartLacBscList(final String rangeId);
	 public void removeTawpartLacBscbyRangeid(String rangeId);
			
}