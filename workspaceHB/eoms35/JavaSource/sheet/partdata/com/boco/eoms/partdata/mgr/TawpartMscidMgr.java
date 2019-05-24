package com.boco.eoms.partdata.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partdata.model.TawpartMscid;

/**
 * <p>
 * Title:MSCID码号管理
 * </p>
 * <p>
 * Description:MSCID码号管理
 * </p>
 * <p>
 * Mon Jul 05 09:11:48 CST 2010
 * </p>
 * 
 * @author fengshaohong
 * @version 3.6
 * 
 */
 public interface TawpartMscidMgr {
 
	/**
	 *
	 * 取MSCID码号管理 列表
	 * @return 返回MSCID码号管理列表
	 */
	public List getTawpartMscids();
    
	/**
	 * 根据主键查询MSCID码号管理
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public TawpartMscid getTawpartMscid(final String id);
    
	/**
	 * 保存MSCID码号管理
	 * @param tawpartMscid MSCID码号管理
	 */
	public void saveTawpartMscid(TawpartMscid tawpartMscid);
	
	/**
	 * 保存单个MSICID码号管理
	 * @param tawpartMscid MSICID BEAN
	 */
	public void saveOneTawpartMscid(TawpartMscid tawpartMscid);
    
	/**
	 * 根据主键删除MSCID码号管理
	 * @param id 主键
	 */
	public void removeTawpartMscid(final String id);
    
	/**
	 * 根据条件分页查询MSCID码号管理
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回MSCID码号管理的分页列表
	 */
	public Map getTawpartMscids(final Integer curPage, final Integer pageSize,
			final String whereStr);
    public List getM0byHeadNumber(final String headNumber);
    public List getM1byHeadNumber(final String headNumber);
    public List getM2byHeadNumber(final String headNumber);
    public List getIDbyHeadNumber(final String headNumber);
    public List getTawpartMscidsbyHeadnumber(final String headNumber);
    public void updateTawpartMscid(TawpartMscid tawpartMscid);
    public void removeTawpartMscid(final String headNumber,final String numberM0,final String numberM1,final String numberM2);
}