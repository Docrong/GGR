package com.boco.eoms.partner.baseinfo.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partner.baseinfo.model.TawPartnerOil;

/**
 * <p>
 * Title:油机信息
 * </p>
 * <p>
 * Description:油机信息
 * </p>
 * <p>
 * Thu Feb 05 13:56:15 CST 2009
 * </p>
 * 
 * @author fengshaohong
 * @version 3.5
 * 
 */
 public interface TawPartnerOilMgr {
 
	/**
	 *
	 * 取油机信息 列表
	 * @return 返回油机信息列表
	 */
	public List getTawPartnerOils();
    
	/**
	 * 根据主键查询油机信息
	 * @param id 主键
	 * @return 返回某id的对象
	 */
	public TawPartnerOil getTawPartnerOil(final String id);
    
	/**
	 * 保存油机信息
	 * @param tawPartnerOil 油机信息
	 */
	public void saveTawPartnerOil(TawPartnerOil tawPartnerOil);
    
	/**
	 * 根据主键删除油机信息
	 * @param id 主键
	 */
	public void removeTawPartnerOil(final String id);
    
	/**
	 * 根据条件分页查询油机信息
	 * @param curPage 当前页
	 * @param pageSize 每页包含记录条数
	 * @param whereStr 查询条件
	 * @return 返回油机信息的分页列表
	 */
	public Map getTawPartnerOils(final Integer curPage, final Integer pageSize,
			final String whereStr);
	 /**
	 * 判断油机编号是否唯一
	 * @see com.boco.eoms.commons.system.dict.dao.isunique(java.lang.String)
	 *      
	 */
	public Boolean isunique(final String oil_number);
	/**
	 * name2Id，即字典id转为字典名称 added by fengshaohong
	 * 
	 * @see com.boco.eoms.base.dao.Name2IDDAO#name2Id(java.lang.String)
	 */
	public String name2Id(final String dictName,final String parentDictId);
}