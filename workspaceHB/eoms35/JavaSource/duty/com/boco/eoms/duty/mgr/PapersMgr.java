package com.boco.eoms.duty.mgr;

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
 public interface PapersMgr {
 
	/**
	 *
	 * 取资料 列表
	 * @return 返回资料列表
	 */
	public List getPaperss();
	/**
	 * 根据主键查询资料
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public Papers getPapers(final String id);
    
	/**
	 * 保存资料
	 * @param papers 资料
	 */
	public void savePapers(Papers papers);
	/**
	 * 保存查看过得资料
	 * @param papers 资料
	 */
	public void savePapersPart(PapersPart paperspart);
    
	/**
	 * 根据主键删除资料
	 * @param id 主键
	 */
	public void removePapers(final String id);
	/**
	 * 根据主键删除papers_part表相应记录
	 * @param id 主键
	 */
	public void removePapersPart(final String id);
    
	/**
	 * 根据条件分页查询资料
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回资料的分页列表
	 */
	public Map getPaperss(final Integer curPage, final Integer pageSize,
			final String whereStr,String userId);
	public Map searchTixing(final Integer curPage, final Integer pageSize,
			final String whereStr,String userId);
	public Map getPerson();
	public Map getPapersId();
			
}